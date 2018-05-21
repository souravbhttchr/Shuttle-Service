//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.beans.User;
import com.dao.contract.ShuttleServiceCommonDAO;
import com.resources.contract.DBConnectService;

public class ShuttleServiceCommonDAOImpl implements ShuttleServiceCommonDAO {

	@Autowired
	DBConnectService dbConnectService;

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

}
