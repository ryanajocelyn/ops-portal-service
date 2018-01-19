package com.cognizant.pmo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="department_id")
	private int departmentId;

	@Column(name="department_name")
	private String departmentName;

	@Column(name="department_type")
	private String departmentType;

	//bi-directional many-to-one association to Associate
	@OneToMany(mappedBy="department")
	private List<Associate> associates;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="department")
	private List<Project> projects;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public List<Associate> getAssociates() {
		return this.associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}

	public Associate addAssociate(Associate associate) {
		getAssociates().add(associate);
		associate.setDepartment(this);

		return associate;
	}

	public Associate removeAssociate(Associate associate) {
		getAssociates().remove(associate);
		associate.setDepartment(null);

		return associate;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setDepartment(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setDepartment(null);

		return project;
	}

}