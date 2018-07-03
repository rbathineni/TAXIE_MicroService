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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wf.wholesale.metadata.dao.SectionRepository;
import com.wf.wholesale.metadata.exception.MetadataNotFoundException;
import com.wf.wholesale.metadata.model.Section;

@RestController
@RequestMapping("/api/metadata")
public class SectionsMetadataController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SectionRepository sectionRepository;

	/**
	 * This Operation is used to retrieve all the sections
	 * 
	 * @return List<Section>
	 * @throws Exception
	 */
	@GetMapping("/sections")
	public List<Section> getAllSections() throws Exception {
		List<Section> sectionsList = sectionRepository.findAll();
		
		if (null == sectionsList || sectionsList.isEmpty()) {
			throw new MetadataNotFoundException("Applications not found.");
		}
		return sectionsList;
	}

	/**
	 * This Operation is used to ADD new section
	 * @param section
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sections")
	public ResponseEntity<Object> saveSections(@RequestBody Section section) throws Exception {
		Section savedSection = sectionRepository.save(section);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sectionId}")
				.buildAndExpand(savedSection.getId()).toUri();
		logger.debug(location.toString());
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * This Operation is used to retrieve the sections for a given Section Id
	 * 
	 * @param sectionId
	 * @return Section
	 */
	@GetMapping("/sections/{sectionId}")
	public Section getMetadataBySectionId(@PathVariable Long sectionId) {
		Optional<Section> section = sectionRepository.findById(sectionId);
		if (!section.isPresent()) {
			throw new MetadataNotFoundException("Section not found for given section Id " + sectionId);
		}
		logger.debug(section.get().getSectionName());
		return section.get();
	}
	
	/**
	 * This Operation is used to retrieve the sections for a given Section Id
	 * 
	 * @param sectionId
	 * @return Section
	 */
	@GetMapping("/sections/moduelId")
	public List<Section> getSectionsByAppliationId(@RequestParam Long moduleId) {
		logger.debug("ModuleId :"+moduleId);
		List<Section> sectionsList = sectionRepository.findByModuleId(moduleId);
		if (null == sectionsList || sectionsList.isEmpty()) {
			throw new MetadataNotFoundException("Section not found for given Module Id " + moduleId);
		}
		return sectionsList;
	}
	
	
	/**
	 * This Operation is used to Delete the section for a given SectionId
	 * 
	 * @param sectionId
	 * @return Section
	 */
	@DeleteMapping("/sections/{sectionId}")
	public void deleteSectionById(@PathVariable Long sectionId) {
		sectionRepository.deleteById(sectionId);
	}
	
	/**
	 * This operation is to update Section for a given section Id
	 * 
	 * @param sectionId
	 * @param Section
	 * @return
	 */

	@PutMapping("/sections/{sectionId}")
	public ResponseEntity<Object> updateSection(@PathVariable Long sectionId,
			@RequestBody Section reqSection) {

		Optional<Section> section = sectionRepository.findById(sectionId);
		section.orElseThrow(() -> new MetadataNotFoundException("Section not found for given Section Id "+sectionId));

		Section updateSection = section.get();
		updateSection.setSectionName(reqSection.getSectionName());
		updateSection.setDescription(reqSection.getDescription());
		updateSection.setSectionConfig(reqSection.getSectionConfig());
		updateSection.setModifiedBy(reqSection.getModifiedBy());
		updateSection.setModifiedOn(Date.valueOf(LocalDate.now()));

		Section savedSection = sectionRepository.save(updateSection);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(savedSection.getId()).toUri();

		logger.debug("location :" + location);
		return ResponseEntity.created(location).build();
	}
}
