package com.cognizant.pmo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Associate
	@OneToMany(mappedBy="grade")
	private List<Associate> associates;

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

	public List<Associate> getAssociates() {
		return this.associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}

	public Associate addAssociate(Associate associate) {
		getAssociates().add(associate);
		associate.setGrade(this);

		return associate;
	}

	public Associate removeAssociate(Associate associate) {
		getAssociates().remove(associate);
		associate.setGrade(null);

		return associate;
	}

}