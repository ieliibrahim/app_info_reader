package com.ieli.claims.model.app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private String applicationFor;

	@Column(name = "number")
	private String number;

	@Column(name = "applicant")
	private String applicant;

	@Column(name = "examiner")
	private String examiner;

	@Column(name = "group_art_unit")
	private String groupArtUnit;

	@Column(name = "confirmation_number")
	private String confirmationNumber;

	@Column(name = "attorney_docket_number")
	private String attorneyDocketNumber;

	@Column(name = "is_final_office_action")
	private int isFinalOfficeAction;

	@Column(name = "filling_date")
	private String fillingDate;

	@Column(name = "notification_date")
	private String notificationDate;

	@Column(name = "titles")
	private String titles;

	@Column(name = "sub_titles")
	private String subTitles;

	@Transient
	private List<String> titlesTrans;

	@Transient
	private List<String> subTitlesTrans;

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

	public int getFinalOfficeAction() {
		return isFinalOfficeAction;
	}

	public void setFinalOfficeAction(int isFinalOfficeAction) {
		this.isFinalOfficeAction = isFinalOfficeAction;
	}

	public String getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(String fillingDate) {
		this.fillingDate = fillingDate;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getSubTitles() {
		return subTitles;
	}

	public void setSubTitles(String subTitles) {
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

	/**
	 * @return the notificationDate
	 */
	public String getNotificationDate() {
		return notificationDate;
	}

	/**
	 * @param notificationDate
	 *            the notificationDate to set
	 */
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	/**
	 * @return the titlesTrans
	 */
	public List<String> getTitlesTrans() {
		return titlesTrans;
	}

	/**
	 * @param titlesTrans
	 *            the titlesTrans to set
	 */
	public void setTitlesTrans(List<String> titlesTrans) {
		this.titlesTrans = titlesTrans;
	}

	/**
	 * @return the subTitlesTrans
	 */
	public List<String> getSubTitlesTrans() {
		return subTitlesTrans;
	}

	/**
	 * @param subTitlesTrans
	 *            the subTitlesTrans to set
	 */
	public void setSubTitlesTrans(List<String> subTitlesTrans) {
		this.subTitlesTrans = subTitlesTrans;
	}

}
