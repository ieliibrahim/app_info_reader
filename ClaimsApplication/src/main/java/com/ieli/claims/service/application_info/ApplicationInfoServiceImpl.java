package com.ieli.claims.service.application_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieli.claims.model.app.ApplicationInfo;
import com.ieli.claims.repository.ApplicationInfoRepository;

@Service("iApplicationInfoService")
public class ApplicationInfoServiceImpl implements IApplicationInfoService {

	@Autowired
	private ApplicationInfoRepository applicationInfoRepository;

	@Override
	public ApplicationInfo saveApplicationInfo(ApplicationInfo applicationInfo) {
		return applicationInfoRepository.saveAndFlush(applicationInfo);
	}

	@Override
	public List<ApplicationInfo> getAllAplicationInfos() {
		return applicationInfoRepository.findAll();
	}

}
