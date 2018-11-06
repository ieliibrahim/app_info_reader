package com.ieli.claims.model.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rejected_claims")
public class RejectedClaims {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rejected_claims_id")
	private Integer rejectedClaimsId;

	@Column(name = "claim_numbers")
	private String claimNumbers;

	@Column(name = "claim_statue")
	private String claimStatue;

	@Column(name = "name")
	private String name;

	@Column(name = "publication_number")
	private String publicationNumber;

	@Column(name = "claim_id")
	private Integer claimId;

	public Integer getRejectedClaimsId() {
		return rejectedClaimsId;
	}

	public void setRejectedClaimsId(Integer rejectedClaimsId) {
		this.rejectedClaimsId = rejectedClaimsId;
	}

	public String getClaimNumbers() {
		return claimNumbers;
	}

	public void setClaimNumbers(String claimNumbers) {
		this.claimNumbers = claimNumbers;
	}

	public String getClaimStatue() {
		return claimStatue;
	}

	public void setClaimStatue(String claimStatue) {
		this.claimStatue = claimStatue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(String publicationNumber) {
		this.publicationNumber = publicationNumber;
	}

	/**
	 * @return the claimId
	 */
	public Integer getClaimId() {
		return claimId;
	}

	/**
	 * @param claimId
	 *            the claimId to set
	 */
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

}
