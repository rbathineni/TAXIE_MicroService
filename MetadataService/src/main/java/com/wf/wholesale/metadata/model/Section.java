package com.wf.wholesale.metadata.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section implements Serializable {

	private static final long serialVersionUID = 3302325563526143937L;

	@Id
	@GeneratedValue
	@Column(name = "section_id")
	private Long id;

	@Column(name = "module_id", nullable = false)
	private Long moduleId;

	@Column(name = "section_name")
	private String sectionName;

	private String description;

	@Column(name = "section_config")
	private String sectionConfig;

	@Column(name="is_active")
	private String activeFlag;
	
	@Column(name="is_deleted")
	private String deleteFlag;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "created_on")
	private Date cretedOn;

	@Column(name = "modified_on")
	private Date modifiedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id", insertable = false, updatable = false)
	@JsonIgnore
	private Module module;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL,
			// fetch = FetchType.LAZY,
			orphanRemoval = true

	)

	private List<Form> forms = new ArrayList<>();

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Section))
			return false;
		return id != null && id.equals(((Section) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSectionConfig() {
		return sectionConfig;
	}

	public void setSectionConfig(String sectionConfig) {
		this.sectionConfig = sectionConfig;
	}


	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCretedOn() {
		return cretedOn;
	}

	public void setCretedOn(Date cretedOn) {
		this.cretedOn = cretedOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

}
