package com.spstes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.spstes.dao.LoginDao;
import com.spstes.model.Admin;
import com.spstes.model.UserInfo;
import com.spstes.service.LoginService;

@Service
@Scope("prototype")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	@Override
	public UserInfo findUserByAccountAndPwd(String login_name, String pwd) {
		UserInfo user = loginDao.findUserByAccountAndPwd(login_name, pwd);
		return user;
	}

	@Override
	public Admin findAdminByAccountAndPwd(String login_name, String pwd) {
		Admin admin = loginDao.findAdminByAccountAndPwd(login_name, pwd);
		return admin;
	}

}
