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

import com.wf.wholesale.metadata.dao.FormRepository;
import com.wf.wholesale.metadata.exception.MetadataNotFoundException;
import com.wf.wholesale.metadata.model.Field;
import com.wf.wholesale.metadata.model.Form;

@RestController
@RequestMapping("/api/metadata")
public class FormController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	FormRepository formRepository;
	
	/**
	 * This Operation is used to retrieve all the Forms
	 * 
	 * @return List<Form>
	 * @throws Exception
	 */
	@GetMapping("/forms")
	public List<Form> getForms() throws Exception {
		List<Form> forms = formRepository.findAll();
		if (null != forms && !forms.isEmpty()) {
			for (Form form : forms) {
				logger.debug("Form Id : "+form.getId());
				
			}
		} else {
			throw new MetadataNotFoundException("Forms not found.");
		}
		return forms;
	}


	/**
	 * This Operation is used to ADD new Form
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/forms")
	public ResponseEntity<Object> saveForms(@RequestBody Form form) throws Exception {
		Form savedForm = formRepository.save(form);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{formId}")
				.buildAndExpand(savedForm.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	/**
	 * This Operation is used to retrieve the Form for a given formId
	 * 
	 * @param formId
	 * @return Form
	 */
	@GetMapping("/forms/{formId}")
	public Form getMetadataByFormId(@PathVariable Long formId) {
		Optional<Form> form = formRepository.findById(formId);
		if (!form.isPresent()) {
			throw new MetadataNotFoundException("Form not found for given Form Id " + formId);
		}
		logger.debug(form.get().getFormName());
		return form.get();
	}
	
	/**
	 * This Operation is used to retrieve the Fields for a given FormId
	 * 
	 * @param ModuleId
	 * @return List<Fields>
	 */
	@GetMapping("/forms/{formId}/fields")
	public List<Field> getSectionsByFormId(@PathVariable Long formId) {
		Optional<Form> form = formRepository.findById(formId);
		List<Field> fields=null;
		if (!form.isPresent()) {
			throw new MetadataNotFoundException("Form not found for given Form Id " + formId);
		}else {
			fields = form.get().getField();
		}
		return fields;
	}
	
	/**
	 * This Operation is used to Delete the module for a given FormId
	 * 
	 * @param formId
	 * @return Form
	 */
	@DeleteMapping("/forms/{formId}")
	public void deleteFormById(@PathVariable Long formId) {
		formRepository.deleteById(formId);
	}
	
	/**
	 * This operation is to update the Form for a given formId
	 * 
	 * @param formId
	 * @param Form
	 * @return
	 */

	@PutMapping("/forms/{formId}")
	public ResponseEntity<Object> updateForm(@PathVariable Long formId,
			@RequestBody Form updatedForm) {

		Optional<Form> form = formRepository.findById(formId);
		form.orElseThrow(() -> new MetadataNotFoundException("Form not found for given Form Id "+formId));

		Form updateForm = form.get();
		updateForm.setFormName(updatedForm.getFormName());
		updateForm.setDescription(updatedForm.getDescription());
		updateForm.setFormConfig(updatedForm.getFormConfig());
		updateForm.setActiveFlag(updatedForm.getActiveFlag());
		updateForm.setDeleteFlag(updatedForm.getDeleteFlag());
		updateForm.setModifiedBy(updatedForm.getModifiedBy());
		updateForm.setModifiedOn(Date.valueOf(LocalDate.now()));

		Form savedForm = formRepository.save(updateForm);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(savedForm.getId()).toUri();

		logger.debug("location :" + location);
		return ResponseEntity.created(location).build();
	}
}
