package com.example.demo.dto;

import java.util.HashMap;
import java.util.Map;

public class AppSettingDto {

	private static final long serialVersionUID = 1L;

	private final Map<String, String> settings = new HashMap<>();

	public void setSettings(String name, String value) {

		settings.put(name, value);

	}

	public Map<String, String> getSettings() {

		return settings;

	}

}
