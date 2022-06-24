package com.example.demo.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddSettingDto;
import com.example.demo.entities.AppSettingsEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.AppSettingsRepository;
import com.example.demo.services.AppSettingServiceInterface;


@Service("appSettingsServiceImpl")
public class AppSettingsServiceImpl implements AppSettingServiceInterface {

	@Autowired
	private AppSettingsRepository appSettingsRepository;

	@Override
	public List<AppSettingsEntity> getAllSetting() {

		List<AppSettingsEntity> appSettings = appSettingsRepository.findAll();
		return appSettings;

	}

	@Override
	public void addSetting(AddSettingDto settingDetail) {

		AppSettingsEntity newSetting = new AppSettingsEntity();
		newSetting.setIsAdminOnly(settingDetail.getIsAdminOnly());
		newSetting.setKey(settingDetail.getKey());
		newSetting.setValue(settingDetail.getValue());
		appSettingsRepository.save(newSetting);
		return;

	}

	@Override
	public void updateSettingById(Long id, AddSettingDto settingDetail) throws ResourceNotFoundException {

		AppSettingsEntity appSetting = appSettingsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("App Setting Not Found"));
		appSetting.setIsAdminOnly(settingDetail.getIsAdminOnly());
		appSetting.setKey(settingDetail.getKey());
		appSetting.setValue(settingDetail.getValue());
		appSettingsRepository.save(appSetting);
		return;

	}

}
