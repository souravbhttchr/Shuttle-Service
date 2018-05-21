//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.resources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.resources.contract.DBConnectService;

public class DBConnectServiceImpl implements DBConnectService {

	@Autowired
	Configuration cfg;
	
	@Override
	public Session connectDB() {
		
		Session session = null;
		
		try {
			cfg.configure("com/resources/dbResources/mySql.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			session = sf.openSession();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return session;
	}

	@Override
	public void closeSession(Session session) {
		session.close();
	}

	@Override
	public void closeSessionFactory(SessionFactory sessionFactory) {
		sessionFactory.close();
	}

}
