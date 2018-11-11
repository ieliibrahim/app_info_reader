package com.ieli.claims.model.app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "claims")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claim_id")
	private Integer claimId;

	@Column(name = "total_number")
	private Integer totalNumber;

	@Column(name = "allowed_number")
	private Integer allowedNumber;

	@Column(name = "objected_number")
	private String objectedNumber;

	@Column(name = "objected_statue")
	private String objectedStatue;

	@Column(name = "allowable_claims")
	private String allowableClaims;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "application_id")
	private ApplicationInfo applicationInfo;

	@Transient
	private List<RejectedClaims> rejectedClaims;

	public Claim() {

	}

	public Claim(ApplicationInfo applicationInfo) {
		super();
		this.applicationInfo = applicationInfo;
	}

	public Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getAllowedNumber() {
		return allowedNumber;
	}

	public void setAllowedNumber(Integer allowedNumber) {
		this.allowedNumber = allowedNumber;
	}

	public String getObjectedNumber() {
		return objectedNumber;
	}

	public void setObjectedNumber(String objectedNumber) {
		this.objectedNumber = objectedNumber;
	}

	public String getObjectedStatue() {
		return objectedStatue;
	}

	public void setObjectedStatue(String objectedStatue) {
		this.objectedStatue = objectedStatue;
	}

	public String getAllowableClaims() {
		return allowableClaims;
	}

	public void setAllowableClaims(String allowableClaims) {
		this.allowableClaims = allowableClaims;
	}

	/**
	 * @return the applicationInfo
	 */
	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	/**
	 * @param applicationInfo
	 *            the applicationInfo to set
	 */
	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	/**
	 * @return the rejectedClaims
	 */
	public List<RejectedClaims> getRejectedClaims() {
		return rejectedClaims;
	}

	/**
	 * @param rejectedClaims
	 *            the rejectedClaims to set
	 */
	public void setRejectedClaims(List<RejectedClaims> rejectedClaims) {
		this.rejectedClaims = rejectedClaims;
	}

}
