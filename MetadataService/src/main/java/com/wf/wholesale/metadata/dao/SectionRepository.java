package com.wf.wholesale.metadata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.wholesale.metadata.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

	List<Section> findByModuleId(Long moduleId);

}
