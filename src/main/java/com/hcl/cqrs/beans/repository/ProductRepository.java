package com.hcl.cqrs.beans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.cqrs.entities.CreateproductReq;
/**
 * 
 * @author Brijendra and Kapil
 *
 */
public interface ProductRepository extends JpaRepository<CreateproductReq, String>{

}
