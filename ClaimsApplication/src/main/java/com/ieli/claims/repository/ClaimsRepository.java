package com.ieli.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ieli.claims.model.app.Claim;

public interface ClaimsRepository extends JpaRepository<Claim, Integer> {

	Claim findByApplicationInfoApplicationId(Integer appInfoId);


}
