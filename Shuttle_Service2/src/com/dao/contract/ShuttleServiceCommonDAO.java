//@Author : Sourav Bhattacharya
//@Date : 21 May 2018


package com.dao.contract;

import com.beans.LoginCredentials;
import com.beans.User;

public interface ShuttleServiceCommonDAO {

	public boolean register(User user);
	public String login(LoginCredentials loginCredentials);
}
