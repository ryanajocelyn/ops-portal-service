package com.cognizant.pmo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
@NamedQuery(name="Grade.findAll", query="SELECT g FROM Grade g")
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="grade_id")
	private int gradeId;

	@Column(name="grade_description")
	private String gradeDescription;

	@Column(name="grade_short_desc")
	private String gradeShortDesc;

	public Grade() {
	}

	public int getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeDescription() {
		return this.gradeDescription;
	}

	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}

	public String getGradeShortDesc() {
		return this.gradeShortDesc;
	}

	public void setGradeShortDesc(String gradeShortDesc) {
		this.gradeShortDesc = gradeShortDesc;
	}
}