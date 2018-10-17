package com.ieli.claims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ieli.claims.model.app.ApplicationInfo;

public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, Integer> {

}
