package com.hcl.cqrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.cqrs.entities.CreateproductReq;
/**
 * 
 * @author Brijendra and Kapil
 *
 */
public interface ProductRepository extends JpaRepository<CreateproductReq, String>{

}
