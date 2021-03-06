//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.services.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.LoginCredentials;
import com.beans.User;
import com.dao.contract.ShuttleServiceCommonDAO;
import com.services.basic.contract.BasicCommonService;

@Service
public class BasicCommonServiceImpl implements BasicCommonService {

	@Autowired
	ShuttleServiceCommonDAO shuttleServiceCommonDAO;
	
	@Override
	public boolean register(User user) {
		return shuttleServiceCommonDAO.register(user);
	}

	@Override
	public String login(LoginCredentials loginCredentials) {
		return shuttleServiceCommonDAO.login(loginCredentials);
	}

}
