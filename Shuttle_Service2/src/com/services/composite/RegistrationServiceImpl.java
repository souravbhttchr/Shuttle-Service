//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.services.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.User;
import com.services.basic.contract.BasicRegistrationService;
import com.services.composite.contract.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	BasicRegistrationService basicRegistrationService;
	
	@Override
	public boolean register(User user) {
		return basicRegistrationService.register(user);
	}

}
