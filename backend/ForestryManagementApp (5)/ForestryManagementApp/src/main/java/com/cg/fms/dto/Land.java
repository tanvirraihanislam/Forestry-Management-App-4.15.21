package com.cg.fms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="landDetails")
public class Land {
	
	@Id
	@Column(name="landId")
	private String landId;

	@Column(name="surveyNumber")
	private String surveyNumber;

	@Column(name="ownerName")
	private String ownerName;

	@Column(name="landArea")
	private String landArea;
	
	@OneToOne(mappedBy = "land")
	@JsonIgnore
	private Contract contract;

	public Land() {
		super();
	}

	public Land(String landId, String surveyNumber, String ownerName, String landArea) {
		super();
		this.landId = landId;
		this.surveyNumber = surveyNumber;
		this.ownerName = ownerName;
		this.landArea = landArea;
	}

	public String getLandId() {
		return landId;
	}

	public void setLandId(String landId) {
		this.landId = landId;
	}

	public String getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public String toString() {
		return "Land [landId=" + landId + ", surveyNumber=" + surveyNumber + ", ownerName=" + ownerName + ", landArea="
				+ landArea + ", contract=" + contract + "]";
	}
	

	
	
}
