package com.cognizant.pmo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_id")
	private int projectId;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="project_name")
	private String projectName;

	@Column(name="project_type")
	private String projectType;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	//bi-directional many-to-one association to Associate
	@ManyToOne
	@JoinColumn(name="project_pm_id")
	private Associate associate;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="project_owning_department")
	private Department department;

	//bi-directional many-to-one association to ProjectAllocation
	@OneToMany(mappedBy="project")
	private List<ProjectAllocation> projectAllocations;

	public Project() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Associate getAssociate() {
		return this.associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ProjectAllocation> getProjectAllocations() {
		return this.projectAllocations;
	}

	public void setProjectAllocations(List<ProjectAllocation> projectAllocations) {
		this.projectAllocations = projectAllocations;
	}

	public ProjectAllocation addProjectAllocation(ProjectAllocation projectAllocation) {
		getProjectAllocations().add(projectAllocation);
		projectAllocation.setProject(this);

		return projectAllocation;
	}

	public ProjectAllocation removeProjectAllocation(ProjectAllocation projectAllocation) {
		getProjectAllocations().remove(projectAllocation);
		projectAllocation.setProject(null);

		return projectAllocation;
	}

}