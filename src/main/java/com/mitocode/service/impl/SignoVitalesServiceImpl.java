package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.SignoVital;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISignoVitalRepo;
import com.mitocode.service.ISignoVitalesService;

@Service
public class SignoVitalesServiceImpl  extends CRUDImpl<SignoVital, Integer> implements ISignoVitalesService{

	@Autowired
	private ISignoVitalRepo repo;
	
	@Override
	protected IGenericRepo<SignoVital, Integer> getRepo() {
		return repo;
	}

	@Override
	public Page<SignoVital> listarPageable(Pageable page) {
		return repo.findAll(page);
	}

}
