package com.cognizant.pmo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the project_allocation database table.
 * 
 */
@Entity
@Table(name="project_allocation")
@NamedQuery(name="ProjectAllocation.findAll", query="SELECT p FROM ProjectAllocation p")
public class ProjectAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_allocation_id")
	private int projectAllocationId;

	@Column(name="allocation_percent")
	private int allocationPercent;

	@Column(name="assignment_id")
	private int assignmentId;

	@Column(name="biilability_status")
	private String biilabilityStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Associate
	@ManyToOne
	@JoinColumn(name="associate_id")
	private Associate associate;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	public ProjectAllocation() {
	}

	public int getProjectAllocationId() {
		return this.projectAllocationId;
	}

	public void setProjectAllocationId(int projectAllocationId) {
		this.projectAllocationId = projectAllocationId;
	}

	public int getAllocationPercent() {
		return this.allocationPercent;
	}

	public void setAllocationPercent(int allocationPercent) {
		this.allocationPercent = allocationPercent;
	}

	public int getAssignmentId() {
		return this.assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getBiilabilityStatus() {
		return this.biilabilityStatus;
	}

	public void setBiilabilityStatus(String biilabilityStatus) {
		this.biilabilityStatus = biilabilityStatus;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Associate getAssociate() {
		return this.associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}