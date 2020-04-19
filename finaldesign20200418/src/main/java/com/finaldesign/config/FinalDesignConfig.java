package com.finaldesign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.face.AipFace;

@Configuration
public class FinalDesignConfig {
	public static final String APP_ID = "16126843";
	public static final String API_KEY = "A4eQSnRyZU8T0C35uKKeFVTK";
	public static final String SECRET_KEY = "DQauirD5drTBaOozCwsh7LXhrHlXH8Go";

	@Bean
	public AipFace getAipFace() {
		// 初始化一个AipFace
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}
}
