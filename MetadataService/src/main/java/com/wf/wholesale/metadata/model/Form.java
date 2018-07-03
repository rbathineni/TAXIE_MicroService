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
public class Form implements Serializable{

	
	private static final long serialVersionUID = -8819726603185994936L;

	@Id
	@GeneratedValue
	@Column(name="form_id")
	private Long id;
	
	@Column(name="section_id", nullable=false)
	private Long sectionId;
	
	@Column(name="form_name")
	private String formName;
	
	private String description;
	
	@Column(name="form_config")
	private String formConfig;
	
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
    @JoinColumn(name = "section_id" , insertable=false, updatable=false)
	@JsonIgnore
    private Section section;
	
	@OneToMany(mappedBy = "form", cascade = CascadeType.ALL,
			// fetch = FetchType.LAZY,
			orphanRemoval = true

	)

	private List<Field> field = new ArrayList<>();
	
	
 
    public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section )) return false;
        return id != null && id.equals(((Form) o).id);
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

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getFormConfig() {
		return formConfig;
	}

	public void setFormConfig(String formConfig) {
		this.formConfig = formConfig;
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

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
