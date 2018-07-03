package com.wf.wholesale.metadata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.wholesale.metadata.dao.FieldRepository;

@RestController
@RequestMapping("/api/metadata")
public class FieldController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FieldRepository fieldRepository;
}
