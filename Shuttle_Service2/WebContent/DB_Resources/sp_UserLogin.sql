delimiter $$
CREATE PROCEDURE sp_UserLogin(IN username varchar(255), IN pass varchar(255), OUT fullName varchar(255))
	
BEGIN

	DECLARE user_first_name_var varchar(255);
    DECLARE user_last_name_var varchar(255);
	DECLARE user_logout_time_var TIMESTAMP;
    DECLARE user_login_time_var TIMESTAMP;


	DROP TABLE IF EXISTS login_user_temp;

	CREATE TEMPORARY TABLE login_user_temp (
		login_userName varchar(255),
        login_password varchar(255),
        login_user_objId int,
        user_register_count int,
        login_count int,
        login_status varchar(255)
    );
    
    INSERT INTO login_user_temp (login_userName, login_password, login_user_objId, user_register_count, login_count, login_status)
        
    VALUES(
		username,
        pass,
        NULL,
        (SELECT count(*)
		FROM userregister
		WHERE email = username
		AND password = pass
		),                        
		NULL,               
		NULL
        );
    
    
    IF((SELECT user_register_count FROM login_user_temp) > 0)
    THEN
		UPDATE login_user_temp
        SET 
        login_user_objId = 	(SELECT objId
							FROM userregister
							WHERE email = username
							AND password = pass
                            ),
		
        login_count = 	(SELECT count(*)
						FROM loginDtl
						JOIN userregister
						ON loginDtl.userObjId = userregister.objId
						WHERE userregister.objId = login_user_temp.login_user_objId
						);
                        
		IF((SELECT login_count FROM login_user_temp) = 0)
        THEN
			INSERT INTO loginDtl
            VALUES ((SELECT login_user_objId FROM login_user_temp), now(), null, 1);
            
			SET 
            user_first_name_var = 	(SELECT firstName
								FROM userregister
                                JOIN login_user_temp ON userregister.objId = login_user_temp.login_user_objId
								),
			user_last_name_var = 	(SELECT lastName
								FROM userregister
                                JOIN login_user_temp ON userregister.objId = login_user_temp.login_user_objId
								);
            
            set fullName = concat(user_first_name_var, user_last_name_var);
            
            ELSE IF((SELECT login_count FROM login_user_temp) > 0)
            THEN
				
                UPDATE login_user_temp
				SET login_status = 	(SELECT firstTimeLoginInd
									FROM logindtl
                                    WHERE userObjId = login_user_temp.login_user_objId
									);
                                    
				IF((SELECT login_status FROM login_user_temp) = 1)
                THEN
                
					set fullName = 'SESSION_ACTIVE';
				
					ELSE IF((SELECT login_status FROM login_user_temp) = 0)
					THEN
						
						SET user_logout_time_var = 	(SELECT logOutDTime FROM logindtl),
                        
                        user_login_time_var = 	(SELECT loginDTime FROM logindtl);
                         
                        IF(timestampdiff(SECOND, user_logout_time_var, user_login_time_var) < 0)
                        THEN
							
                            UPDATE logindtl
                            SET loginDTime = now()
                            WHERE userObjId = (select login_user_objId from login_user_temp);
                            
                            UPDATE login_user_temp
							SET 
							user_first_name = 	(SELECT firstName
												FROM userregister
												),
							user_last_name = 	(SELECT lastName
												FROM userregister
												);
            
							set fullName = concat(user_first_name, user_last_name);
                            
							ELSE
								
                                set fullName = 'SESSION_ACTIVE';
                                
                                
                        END IF;
                        
					END IF;
                
                END IF;
                
            END IF;
            
            
        END IF;
                        
	ELSE
    
		set fullName = 'NONAME';
    
    END IF;

	SELECT fullName as fullName;

END;
$$
delimiter ;