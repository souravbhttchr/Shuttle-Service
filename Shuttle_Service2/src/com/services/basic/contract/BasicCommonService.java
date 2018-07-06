//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.services.basic.contract;

import com.beans.LoginCredentials;
import com.beans.User;

public interface BasicCommonService {

	public boolean register(User user);
	public String login(LoginCredentials loginCredentials);
}
