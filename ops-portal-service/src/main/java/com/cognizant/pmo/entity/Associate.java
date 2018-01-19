package com.cognizant.pmo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the associate database table.
 * 
 */
@Entity
@NamedQuery(name="Associate.findAll", query="SELECT a FROM Associate a")
public class Associate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="associate_id")
	private int associateId;

	@Column(name="associate_name")
	private String associateName;

	@Column(name="bgv_status")
	private String bgvStatus;

	private String city;

	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_joining")
	private Date dateOfJoining;

	@Temporal(TemporalType.DATE)
	@Column(name="fg_termination_date")
	private Date fgTerminationDate;

	@Column(name="fg_worker_id")
	private String fgWorkerId;

	private String location;

	@Column(name="location_id")
	private String locationId;

	private String state;

	//bi-directional many-to-one association to Associate
	@ManyToOne
	@JoinColumn(name="hcm_supervisor_id")
	private Associate associate;

	//bi-directional many-to-one association to Associate
	@OneToMany(mappedBy="associate")
	private List<Associate> associates;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	//bi-directional many-to-one association to Grade
	@ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="associate")
	private List<Project> projects;

	//bi-directional many-to-one association to ProjectAllocation
	@OneToMany(mappedBy="associate")
	private List<ProjectAllocation> projectAllocations;

	public Associate() {
	}

	public int getAssociateId() {
		return this.associateId;
	}

	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}

	public String getAssociateName() {
		return this.associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getBgvStatus() {
		return this.bgvStatus;
	}

	public void setBgvStatus(String bgvStatus) {
		this.bgvStatus = bgvStatus;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getFgTerminationDate() {
		return this.fgTerminationDate;
	}

	public void setFgTerminationDate(Date fgTerminationDate) {
		this.fgTerminationDate = fgTerminationDate;
	}

	public String getFgWorkerId() {
		return this.fgWorkerId;
	}

	public void setFgWorkerId(String fgWorkerId) {
		this.fgWorkerId = fgWorkerId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Associate getAssociate() {
		return this.associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public List<Associate> getAssociates() {
		return this.associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}

	public Associate addAssociate(Associate associate) {
		getAssociates().add(associate);
		associate.setAssociate(this);

		return associate;
	}

	public Associate removeAssociate(Associate associate) {
		getAssociates().remove(associate);
		associate.setAssociate(null);

		return associate;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setAssociate(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setAssociate(null);

		return project;
	}

	public List<ProjectAllocation> getProjectAllocations() {
		return this.projectAllocations;
	}

	public void setProjectAllocations(List<ProjectAllocation> projectAllocations) {
		this.projectAllocations = projectAllocations;
	}

	public ProjectAllocation addProjectAllocation(ProjectAllocation projectAllocation) {
		getProjectAllocations().add(projectAllocation);
		projectAllocation.setAssociate(this);

		return projectAllocation;
	}

	public ProjectAllocation removeProjectAllocation(ProjectAllocation projectAllocation) {
		getProjectAllocations().remove(projectAllocation);
		projectAllocation.setAssociate(null);

		return projectAllocation;
	}

}