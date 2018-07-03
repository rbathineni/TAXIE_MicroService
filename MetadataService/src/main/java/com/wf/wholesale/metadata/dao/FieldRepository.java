package com.wf.wholesale.metadata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.wholesale.metadata.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long>{

}
