package com.ieli.claims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ieli.claims.model.app.RejectedClaims;

public interface RejectedClaimsRepository extends JpaRepository<RejectedClaims, Integer> {

	List<RejectedClaims> findByClaimId(Integer claimId);

}
