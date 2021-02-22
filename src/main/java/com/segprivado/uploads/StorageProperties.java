package com.segprivado.uploads;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


@ConfigurationProperties(prefix="storage")
@Service
public class StorageProperties {
	
	
	private String location = "photos";// images

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
