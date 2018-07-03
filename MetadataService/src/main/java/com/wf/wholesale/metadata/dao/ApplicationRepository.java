package com.wf.wholesale.metadata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.wholesale.metadata.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Application findByApplicationName(String applicationName);

}
