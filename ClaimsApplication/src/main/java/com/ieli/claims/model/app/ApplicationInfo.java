package com.ieli.claims.model.app;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "application_info")
public class ApplicationInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Integer applicationId;

	@Column(name = "application_for")
	@NotEmpty(message = "*Please provide (For) input")
	private String applicationFor;

	@Column(name = "number")
	@NotEmpty(message = "*Please provide number")
	private String number;

	@Column(name = "applicant")
	@NotEmpty(message = "*Please provide applicant")
	private String applicant;

	@Column(name = "examiner")
	@NotEmpty(message = "*Please provide examiner")
	private String examiner;

	@Column(name = "field_date")
	@NotEmpty(message = "*Please provide field date")
	private String fieldDate;

	@Column(name = "group_art_unit")
	@NotEmpty(message = "*Please provide group art unit")
	private String groupArtUnit;

	@Column(name = "confirmation_number")
	@NotEmpty(message = "*Please provide confirmation number")
	private String confirmationNumber;

	@Column(name = "attorney_docket_number")
	@NotEmpty(message = "*Please provide attorney docket number")
	private String attorneyDocketNumber;

	@Column(name = "is_final_office_action")
	private boolean isFinalOfficeAction;

	@Column(name = "filling_date")
	private String fillingDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "application_id")
	private Claim claim;

	@Transient
	private List<String> titles;

	@Transient
	private List<String> subTitles;

	@Transient
	private int totalNumOfClaims;

	@Transient
	private int allowedClaimsNumbers;

	@Transient
	private String allowableClaims;

	@Transient
	private List<String> claimsNumbersAsStr;

	@Transient
	private List<String> claimsStatueAsStr;

	@Transient
	private List<String> claimsReferenceAsStr;

	@Transient
	private List<String> claimsPubNumAsStr;

	@Transient
	private int groupsClaimsTotal;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationFor() {
		return applicationFor;
	}

	public void setApplicationFor(String applicationFor) {
		this.applicationFor = applicationFor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getFieldDate() {
		return fieldDate;
	}

	public void setFieldDate(String fieldDate) {
		this.fieldDate = fieldDate;
	}

	public String getGroupArtUnit() {
		return groupArtUnit;
	}

	public void setGroupArtUnit(String groupArtUnit) {
		this.groupArtUnit = groupArtUnit;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getAttorneyDocketNumber() {
		return attorneyDocketNumber;
	}

	public void setAttorneyDocketNumber(String attorneyDocketNumber) {
		this.attorneyDocketNumber = attorneyDocketNumber;
	}

	public boolean isFinalOfficeAction() {
		return isFinalOfficeAction;
	}

	public void setFinalOfficeAction(boolean isFinalOfficeAction) {
		this.isFinalOfficeAction = isFinalOfficeAction;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(String fillingDate) {
		this.fillingDate = fillingDate;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<String> getSubTitles() {
		return subTitles;
	}

	public void setSubTitles(List<String> subTitles) {
		this.subTitles = subTitles;
	}

	public int getTotalNumOfClaims() {
		return totalNumOfClaims;
	}

	public void setTotalNumOfClaims(int totalNumOfClaims) {
		this.totalNumOfClaims = totalNumOfClaims;
	}

	public int getAllowedClaimsNumbers() {
		return allowedClaimsNumbers;
	}

	public void setAllowedClaimsNumbers(int allowedClaimsNumbers) {
		this.allowedClaimsNumbers = allowedClaimsNumbers;
	}

	public String getAllowableClaims() {
		return allowableClaims;
	}

	public void setAllowableClaims(String allowableClaims) {
		this.allowableClaims = allowableClaims;
	}

	public List<String> getClaimsNumbersAsStr() {
		return claimsNumbersAsStr;
	}

	public void setClaimsNumbersAsStr(List<String> claimsNumbersAsStr) {
		this.claimsNumbersAsStr = claimsNumbersAsStr;
	}

	public int getGroupsClaimsTotal() {
		return groupsClaimsTotal;
	}

	public void setGroupsClaimsTotal(int groupsClaimsTotal) {
		this.groupsClaimsTotal = groupsClaimsTotal;
	}

	public List<String> getClaimsStatueAsStr() {
		return claimsStatueAsStr;
	}

	public void setClaimsStatueAsStr(List<String> claimsStatueAsStr) {
		this.claimsStatueAsStr = claimsStatueAsStr;
	}

	public List<String> getClaimsReferenceAsStr() {
		return claimsReferenceAsStr;
	}

	public void setClaimsReferenceAsStr(List<String> claimsReferenceAsStr) {
		this.claimsReferenceAsStr = claimsReferenceAsStr;
	}

	public List<String> getClaimsPubNumAsStr() {
		return claimsPubNumAsStr;
	}

	public void setClaimsPubNumAsStr(List<String> claimsPubNumAsStr) {
		this.claimsPubNumAsStr = claimsPubNumAsStr;
	}

}
