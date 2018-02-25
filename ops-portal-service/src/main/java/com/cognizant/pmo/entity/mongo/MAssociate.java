/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 238209
 *
 */
@Document(collection="associate")
public class MAssociate {

	@Id
	private BigInteger id;
	private String associateName;
	private Date dateOfJoining;
	private String bgvStatus;

	private Long resourceWorkdayId;
	private Date fgTerminationDate;
	private String fgWorkerId;

	private String location;
	private String locationId;
	private String city;
	private String state;
	private String country;

	private String department;
	private String departmentType;

	private String grade;
	private String gradeDescription;

	private List<MProjectAllocation> projectAllocations;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public void addProjectAllocation(MProjectAllocation allocation) {
		if (this.projectAllocations == null) {
			this.projectAllocations = new ArrayList<MProjectAllocation>();
		}
		
		this.projectAllocations.add(allocation);
	}
	
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the associateId
	 */
	public BigInteger getAssociateId() {
		return id;
	}

	/**
	 * @return the associateName
	 */
	public String getAssociateName() {
		return associateName;
	}

	/**
	 * @param associateName the associateName to set
	 */
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	/**
	 * @return the bgvStatus
	 */
	public String getBgvStatus() {
		return bgvStatus;
	}

	/**
	 * @param bgvStatus the bgvStatus to set
	 */
	public void setBgvStatus(String bgvStatus) {
		this.bgvStatus = bgvStatus;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the fgTerminationDate
	 */
	public Date getFgTerminationDate() {
		return fgTerminationDate;
	}

	/**
	 * @param fgTerminationDate the fgTerminationDate to set
	 */
	public void setFgTerminationDate(Date fgTerminationDate) {
		this.fgTerminationDate = fgTerminationDate;
	}

	/**
	 * @return the fgWorkerId
	 */
	public String getFgWorkerId() {
		return fgWorkerId;
	}

	/**
	 * @param fgWorkerId the fgWorkerId to set
	 */
	public void setFgWorkerId(String fgWorkerId) {
		this.fgWorkerId = fgWorkerId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the gradeDescription
	 */
	public String getGradeDescription() {
		return gradeDescription;
	}

	/**
	 * @param gradeDescription the gradeDescription to set
	 */
	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the departmentType
	 */
	public String getDepartmentType() {
		return departmentType;
	}

	/**
	 * @param departmentType the departmentType to set
	 */
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	/**
	 * @return the projectAllocations
	 */
	public List<MProjectAllocation> getProjectAllocations() {
		if (projectAllocations == null) {
			projectAllocations = new ArrayList<MProjectAllocation>();
		}
		return projectAllocations;
	}

	/**
	 * @param projectAllocations the projectAllocations to set
	 */
	public void setProjectAllocations(List<MProjectAllocation> projectAllocations) {
		this.projectAllocations = projectAllocations;
	}

	/**
	 * @return the resourceWorkdayId
	 */
	public Long getResourceWorkdayId() {
		return resourceWorkdayId;
	}

	/**
	 * @param resourceWorkdayId the resourceWorkdayId to set
	 */
	public void setResourceWorkdayId(Long resourceWorkdayId) {
		this.resourceWorkdayId = resourceWorkdayId;
	}
}
