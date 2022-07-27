package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AddSettingDto;
import com.example.demo.entities.AppSettingsEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;




public interface AppSettingServiceInterface {

	public List<AppSettingsEntity> getAllSetting();

	public void addSetting(AddSettingDto settingDetail);

	public void updateSettingById(Long id, AddSettingDto settingDetail) throws ResourceNotFoundException;

}
