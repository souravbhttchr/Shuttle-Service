//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.services.composite.contract;

import com.beans.LoginCredentials;
import com.beans.User;

public interface CommonService {

	public boolean register(User user);
	public String login(LoginCredentials loginCredentials);
	
}
