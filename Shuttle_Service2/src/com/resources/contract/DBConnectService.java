//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.resources.contract;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface DBConnectService {

	public Session connectDB();
	public void closeSession(Session session);
	public void closeSessionFactory(SessionFactory sessionFactory);
}
