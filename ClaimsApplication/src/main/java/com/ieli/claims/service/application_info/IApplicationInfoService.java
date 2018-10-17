package com.ieli.claims.service.application_info;

import java.util.List;

import com.ieli.claims.model.app.ApplicationInfo;

public interface IApplicationInfoService {

	ApplicationInfo saveApplicationInfo(ApplicationInfo applicationInfo);

	List<ApplicationInfo> getAllAplicationInfos();
}
