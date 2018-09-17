package com.spstes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.spstes.dao.CatDao;
import com.spstes.model.Cat;
import com.spstes.service.CatService;

@Service
@Scope("prototype")
public class CatServiceImpl implements CatService {
	@Autowired
	private CatDao catDao;

	@Override
	public Cat findCatById(int id) {
		return catDao.findCatById(id);
	}
}
