package com.wf.wholesale.metadata.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Field implements Serializable{

	private static final long serialVersionUID = 726097654842016951L;

	@Id
	@GeneratedValue
	@Column(name="field_id")
	private Long fieldId;
	
	@Column(name="field_name")
	private String fieldName;
	
	private String description;
	
	@Column(name="field_config")
	private String sectionConfig;
	
	@Column(name="is_active")
	private String activeFlag;
	
	@Column(name="is_deleted")
	private String deleteFlag;
		
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="created_on")
	private Date cretedOn;
	
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id" , insertable=false, updatable=false)
	@JsonIgnore
    private Form form;

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	
	
}
