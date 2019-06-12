package com.hcl.cqrs.service;

import java.util.List;

import org.springframework.core.env.Environment;

import com.hcl.cqrs.entities.CreateproductReq;

public interface IProductService {
	
	//public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env);
	
	List<CreateproductReq> viewProducts(Environment env);
	
	public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env);
	
}
