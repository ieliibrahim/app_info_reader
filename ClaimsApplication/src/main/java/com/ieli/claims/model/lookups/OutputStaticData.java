package com.ieli.claims.model.lookups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "output_static_data")
public class OutputStaticData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "static_data_id")
	private Integer staticDataId;

	@Column(name = "trademark_office_title")
	private String trademarkOfficeTitle;

	@Column(name = "in_reapplication_of_title")
	private String in_reapplicationOfTitle;

	@Column(name = "application_number_title")
	private String applicationNumberTitle;

	@Column(name = "field_title")
	private String fieldTitle;

	@Column(name = "for_title")
	private String forTitle;

	@Column(name = "group_art_unit_title")
	private String groupArtUnitTitle;

	@Column(name = "examiner_title")
	private String examinerTitle;

	@Column(name = "confirmation_number_title")
	private String confirmationNumberTitle;

	@Column(name = "attorney_docket_title")
	private String attorneyDocketTitle;

	@Column(name = "via_title")
	private String viaTitle;

	@Column(name = "via_address")
	private String viaAddress;

	@Column(name = "year")
	private String year;

	@Column(name = "amendment_title")
	private String amendmentTitle;

	@Column(name = "amendment_header")
	private String amendmentHeader;

	@Column(name = "amendment_msg_start")
	private String amendmentMsgStart;

	@Column(name = "amendment_msg_end")
	private String amendmentMsgEnd;

	@Column(name = "amendment_footer")
	private String amendmentFooter;

	@Column(name = "amendment_claim_title")
	private String amendmentClaimTitle;

	@Column(name = "remarks_title")
	private String remarksTitle;

	@Column(name = "remarks_header")
	private String remarksHeader;

	@Column(name = "objected_claims_title")
	private String objectedClaimsTitle;

	@Column(name = "objected_claims_middle_msg")
	private String objectedClaimsMiddleMsg;

	@Column(name = "rejected_claims_title")
	private String rejectedClaimsTitle;

	@Column(name = "rejected_claims_under")
	private String rejectedClaimsUnder;

	@Column(name = "rejected_claims_middle_msg")
	private String rejectedClaimsMiddleMsg;

	@Column(name = "pub_number_title")
	private String pubNumberTitle;

	@Column(name = "to_msg")
	private String toMsg;

	@Column(name = "al_msg")
	private String alMsg;

	@Column(name = "claim_objections_title")
	private String claimObjectionsTitle;

	@Column(name = "allawable_subject_matter_title")
	private String allawableSubjectMatterTitle;

	@Column(name = "conclusion_title")
	private String conclusionTitle;

	public Integer getStaticDataId() {
		return staticDataId;
	}

	public void setStaticDataId(Integer staticDataId) {
		this.staticDataId = staticDataId;
	}

	public String getTrademarkOfficeTitle() {
		return trademarkOfficeTitle;
	}

	public void setTrademarkOfficeTitle(String trademarkOfficeTitle) {
		this.trademarkOfficeTitle = trademarkOfficeTitle;
	}

	public String getIn_reapplicationOfTitle() {
		return in_reapplicationOfTitle;
	}

	public void setIn_reapplicationOfTitle(String in_reapplicationOfTitle) {
		this.in_reapplicationOfTitle = in_reapplicationOfTitle;
	}

	public String getApplicationNumberTitle() {
		return applicationNumberTitle;
	}

	public void setApplicationNumberTitle(String applicationNumberTitle) {
		this.applicationNumberTitle = applicationNumberTitle;
	}

	public String getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	public String getForTitle() {
		return forTitle;
	}

	public void setForTitle(String forTitle) {
		this.forTitle = forTitle;
	}

	public String getGroupArtUnitTitle() {
		return groupArtUnitTitle;
	}

	public void setGroupArtUnitTitle(String groupArtUnitTitle) {
		this.groupArtUnitTitle = groupArtUnitTitle;
	}

	public String getExaminerTitle() {
		return examinerTitle;
	}

	public void setExaminerTitle(String examinerTitle) {
		this.examinerTitle = examinerTitle;
	}

	public String getConfirmationNumberTitle() {
		return confirmationNumberTitle;
	}

	public void setConfirmationNumberTitle(String confirmationNumberTitle) {
		this.confirmationNumberTitle = confirmationNumberTitle;
	}

	public String getAttorneyDocketTitle() {
		return attorneyDocketTitle;
	}

	public void setAttorneyDocketTitle(String attorneyDocketTitle) {
		this.attorneyDocketTitle = attorneyDocketTitle;
	}

	public String getViaTitle() {
		return viaTitle;
	}

	public void setViaTitle(String viaTitle) {
		this.viaTitle = viaTitle;
	}

	public String getViaAddress() {
		return viaAddress;
	}

	public void setViaAddress(String viaAddress) {
		this.viaAddress = viaAddress;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAmendmentTitle() {
		return amendmentTitle;
	}

	public void setAmendmentTitle(String amendmentTitle) {
		this.amendmentTitle = amendmentTitle;
	}

	public String getAmendmentHeader() {
		return amendmentHeader;
	}

	public void setAmendmentHeader(String amendmentHeader) {
		this.amendmentHeader = amendmentHeader;
	}

	public String getAmendmentMsgStart() {
		return amendmentMsgStart;
	}

	public void setAmendmentMsgStart(String amendmentMsgStart) {
		this.amendmentMsgStart = amendmentMsgStart;
	}

	public String getAmendmentMsgEnd() {
		return amendmentMsgEnd;
	}

	public void setAmendmentMsgEnd(String amendmentMsgEnd) {
		this.amendmentMsgEnd = amendmentMsgEnd;
	}

	public String getAmendmentFooter() {
		return amendmentFooter;
	}

	public void setAmendmentFooter(String amendmentFooter) {
		this.amendmentFooter = amendmentFooter;
	}

	public String getAmendmentClaimTitle() {
		return amendmentClaimTitle;
	}

	public void setAmendmentClaimTitle(String amendmentClaimTitle) {
		this.amendmentClaimTitle = amendmentClaimTitle;
	}

	public String getRemarksTitle() {
		return remarksTitle;
	}

	public void setRemarksTitle(String remarksTitle) {
		this.remarksTitle = remarksTitle;
	}

	public String getRemarksHeader() {
		return remarksHeader;
	}

	public void setRemarksHeader(String remarksHeader) {
		this.remarksHeader = remarksHeader;
	}

	public String getObjectedClaimsTitle() {
		return objectedClaimsTitle;
	}

	public void setObjectedClaimsTitle(String objectedClaimsTitle) {
		this.objectedClaimsTitle = objectedClaimsTitle;
	}

	public String getObjectedClaimsMiddleMsg() {
		return objectedClaimsMiddleMsg;
	}

	public void setObjectedClaimsMiddleMsg(String objectedClaimsMiddleMsg) {
		this.objectedClaimsMiddleMsg = objectedClaimsMiddleMsg;
	}

	public String getRejectedClaimsTitle() {
		return rejectedClaimsTitle;
	}

	public void setRejectedClaimsTitle(String rejectedClaimsTitle) {
		this.rejectedClaimsTitle = rejectedClaimsTitle;
	}

	public String getRejectedClaimsUnder() {
		return rejectedClaimsUnder;
	}

	public void setRejectedClaimsUnder(String rejectedClaimsUnder) {
		this.rejectedClaimsUnder = rejectedClaimsUnder;
	}

	public String getRejectedClaimsMiddleMsg() {
		return rejectedClaimsMiddleMsg;
	}

	public void setRejectedClaimsMiddleMsg(String rejectedClaimsMiddleMsg) {
		this.rejectedClaimsMiddleMsg = rejectedClaimsMiddleMsg;
	}

	public String getPubNumberTitle() {
		return pubNumberTitle;
	}

	public void setPubNumberTitle(String pubNumberTitle) {
		this.pubNumberTitle = pubNumberTitle;
	}

	public String getToMsg() {
		return toMsg;
	}

	public void setToMsg(String toMsg) {
		this.toMsg = toMsg;
	}

	public String getAlMsg() {
		return alMsg;
	}

	public void setAlMsg(String alMsg) {
		this.alMsg = alMsg;
	}

	public String getClaimObjectionsTitle() {
		return claimObjectionsTitle;
	}

	public void setClaimObjectionsTitle(String claimObjectionsTitle) {
		this.claimObjectionsTitle = claimObjectionsTitle;
	}

	public String getAllawableSubjectMatterTitle() {
		return allawableSubjectMatterTitle;
	}

	public void setAllawableSubjectMatterTitle(String allawableSubjectMatterTitle) {
		this.allawableSubjectMatterTitle = allawableSubjectMatterTitle;
	}

	public String getConclusionTitle() {
		return conclusionTitle;
	}

	public void setConclusionTitle(String conclusionTitle) {
		this.conclusionTitle = conclusionTitle;
	}

}
