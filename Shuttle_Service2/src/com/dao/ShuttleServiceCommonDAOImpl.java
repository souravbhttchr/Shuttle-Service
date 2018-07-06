//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.beans.LoginCredentials;
import com.beans.User;
import com.dao.contract.ShuttleServiceCommonDAO;
import com.resources.contract.DBConnectService;

public class ShuttleServiceCommonDAOImpl implements ShuttleServiceCommonDAO {

	@Autowired
	DBConnectService dbConnectService;
	
	private String USER_NOT_REGISTERED = "NONAME";
	private String USER_SESSION_ACTIVE = "SESSION_ACTIVE";
	private String USER_NOT_REGISTERED_RESPONSE = "No such user is registered in the system. Kindly register and login.";
	private String USER_SESSION_ACTIVE_RESPONSE = "User is already logged in the system. Kindly logout and re-login.";
	private String VALID_LOGIN_RESPONSE = "Welcome ";

	@Override
	public boolean register(User user) {

		try {
		Session session = dbConnectService.connectDB();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		
		dbConnectService.closeSession(session);
		dbConnectService.closeSessionFactory(session.getSessionFactory());
		
		return true;
		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String login(LoginCredentials loginCredentials) {

		try {
			Session session = dbConnectService.connectDB();
			Transaction tx = session.beginTransaction();

			StoredProcedureQuery spQuery = session.createStoredProcedureQuery("sp_UserLogin");

			spQuery.registerStoredProcedureParameter("username", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("pass", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("fullName", String.class, ParameterMode.OUT);

			spQuery.setParameter("username", "sourav.bhttchr@gmail.com");
			spQuery.setParameter("pass", "neelindra");

			spQuery.executeUpdate();

			String name = (String) spQuery.getSingleResult();//  getOutputParameterValue("fullName");
			
			tx.commit();
			
			dbConnectService.closeSession(session);
			dbConnectService.closeSessionFactory(session.getSessionFactory());

			if (name.equals(USER_NOT_REGISTERED)) {
				return USER_NOT_REGISTERED_RESPONSE;
			} else if (name.equals(USER_SESSION_ACTIVE)) {
				return USER_SESSION_ACTIVE_RESPONSE;
			} else {
				return VALID_LOGIN_RESPONSE + name;
			}

		} catch(Exception e) {
			e.printStackTrace();
			return "Exception Caught";
		}
	}

}
