package com.ieli.claims.model.app;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty(message = "*Please provide total number of claims")
	private Integer totalNumber;

	@Column(name = "allowed_number")
	@NotEmpty(message = "*Please provide allowed number of claims")
	private Integer allowedNumber;

	@Column(name = "objected_number")
	@NotEmpty(message = "*Please provide objected number of claims")
	private String objectedNumber;

	@Column(name = "objected_statue")
	@NotEmpty(message = "*Please provide objected statue of claims")
	private String objectedStatue;

	@Column(name = "allowable_claims")
	@NotEmpty(message = "*Please provide allowable number of claims")
	private String allowableClaims;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "claim_id")
	private Set<RejectedClaims> rejectedClaims;

	public Claim() {

	}

	public Claim(Set<RejectedClaims> rejectedClaims) {
		super();
		this.rejectedClaims = rejectedClaims;
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

	public Set<RejectedClaims> getRejectedClaims() {
		return rejectedClaims;
	}

	public void setRejectedClaims(Set<RejectedClaims> rejectedClaims) {
		this.rejectedClaims = rejectedClaims;
	}

}
