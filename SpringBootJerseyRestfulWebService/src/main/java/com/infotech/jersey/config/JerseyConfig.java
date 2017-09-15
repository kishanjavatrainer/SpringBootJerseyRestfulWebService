package com.infotech.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.infotech.jersey.service.UserRestService;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(UserRestService.class);
	}
}
