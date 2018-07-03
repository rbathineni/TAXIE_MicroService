package com.wf.wholesale.metadata.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("metadata-service")
public class Configuration {
	
	private String connectionUrl;
	private String mysqlUserName;
	private String mysqlPassword;
	
	
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}
	public String getMysqlUserName() {
		return mysqlUserName;
	}
	public void setMysqlUserName(String mysqlUserName) {
		this.mysqlUserName = mysqlUserName;
	}
	public String getMysqlPassword() {
		return mysqlPassword;
	}
	public void setMysqlPassword(String mysqlPassword) {
		this.mysqlPassword = mysqlPassword;
	}
	
	
}
