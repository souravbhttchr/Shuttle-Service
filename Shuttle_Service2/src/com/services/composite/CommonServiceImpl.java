//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.services.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.LoginCredentials;
import com.beans.User;
import com.services.basic.contract.BasicCommonService;
import com.services.composite.contract.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	BasicCommonService basicCommonService;
	
	@Override
	public boolean register(User user) {
		return basicCommonService.register(user);
	}

	@Override
	public String login(LoginCredentials loginCredentials) {
		return basicCommonService.login(loginCredentials);
	}

}
