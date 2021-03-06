package com.wf.wholesale.metadata.controller;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wf.wholesale.metadata.dao.ModuleRepository;
import com.wf.wholesale.metadata.exception.MetadataNotFoundException;
import com.wf.wholesale.metadata.model.Module;
import com.wf.wholesale.metadata.model.Section;

@RestController
@RequestMapping("/api/metadata")
public class ModuleController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ModuleRepository moduleRepository;
	
	/**
	 * This Operation is used to retrieve all the Modules
	 * 
	 * @return List<Module>
	 * @throws Exception
	 */
	@GetMapping("/modules")
	public List<Module> getModules() throws Exception {
		List<Module> modules = moduleRepository.findAll();
		if (null != modules && !modules.isEmpty()) {
			for (Module module : modules) {
				logger.debug("Application Id : "+module.getApplicationId());
				
			}
		} else {
			throw new MetadataNotFoundException("Applications not found.");
		}
		return modules;
	}


	/**
	 * This Operation is used to ADD new Module
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/modules")
	public ResponseEntity<Object> saveModules(@RequestBody Module module) throws Exception {
		Module savedModule = moduleRepository.save(module);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{moduleId}")
				.buildAndExpand(savedModule.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	/**
	 * This Operation is used to retrieve the Module for a given moduleId
	 * 
	 * @param moduleId
	 * @return Module
	 */
	@GetMapping("/modules/{moduleId}")
	public Module getMetadataByModuleId(@PathVariable Long moduleId) {
		Optional<Module> module = moduleRepository.findById(moduleId);
		if (!module.isPresent()) {
			throw new MetadataNotFoundException("Module not found for given Module Id " + moduleId);
		}
		logger.debug(module.get().getModuleName());
		return module.get();
	}
	
	/**
	 * This Operation is used to retrieve the Sections for a given ModuleId
	 * 
	 * @param ModuleId
	 * @return List<Sections>
	 */
	@GetMapping("/modules/{moduleId}/sections")
	public List<Section> getSectionsByModuleId(@PathVariable Long moduleId) {
		Optional<Module> module = moduleRepository.findById(moduleId);
		List<Section> sections=null;
		if (!module.isPresent()) {
			throw new MetadataNotFoundException("Module not found for given Module Id " + moduleId);
		}else {
			sections = module.get().getSections();
		}
		return sections;
	}
	
	/**
	 * This Operation is used to Delete the module for a given ModuleId
	 * 
	 * @param moduleId
	 * @return Module
	 */
	@DeleteMapping("/modules/{moduleId}")
	public void deleteModuleById(@PathVariable Long moduleId) {
		moduleRepository.deleteById(moduleId);
	}
	
	/**
	 * This operation is to update the Module for a given moduleId
	 * 
	 * @param moduleId
	 * @param Module
	 * @return
	 */

	@PutMapping("/modules/{moduleId}")
	public ResponseEntity<Object> updateModule(@PathVariable Long moduleId,
			@RequestBody Module updatedModule) {

		Optional<Module> module = moduleRepository.findById(moduleId);
		module.orElseThrow(() -> new MetadataNotFoundException("Module not found for given Module Id "+moduleId));

		Module updateModule = module.get();
		updateModule.setModuleName(updatedModule.getModuleName());
		updateModule.setDescription(updatedModule.getDescription());
		updateModule.setActiveFlag(updatedModule.getActiveFlag());
		updateModule.setDeleteFlag(updatedModule.getDeleteFlag());
		updateModule.setModifiedBy(updatedModule.getModifiedBy());
		updateModule.setModifiedOn(Date.valueOf(LocalDate.now()));

		Module savedModule = moduleRepository.save(updateModule);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(savedModule.getId()).toUri();

		logger.debug("location :" + location);
		return ResponseEntity.created(location).build();
	}
}
