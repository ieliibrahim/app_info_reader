package com.ieli.claims.model.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty(message = "*Please provide claim numbers")
	private Integer claimNumbers;

	@Column(name = "claim_statue")
	@NotEmpty(message = "*Please provide claim statue")
	private String claimStatue;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide reference name")
	private String name;

	@Column(name = "publication_number")
	@NotEmpty(message = "*Please provide publication number")
	private String publicationNumber;

	public RejectedClaims(@NotEmpty(message = "*Please provide claim numbers") Integer claimNumbers,
			@NotEmpty(message = "*Please provide claim statue") String claimStatue) {
		super();
		this.claimNumbers = claimNumbers;
		this.claimStatue = claimStatue;
	}

	public Integer getRejectedClaimsId() {
		return rejectedClaimsId;
	}

	public void setRejectedClaimsId(Integer rejectedClaimsId) {
		this.rejectedClaimsId = rejectedClaimsId;
	}

	public Integer getClaimNumbers() {
		return claimNumbers;
	}

	public void setClaimNumbers(Integer claimNumbers) {
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

}
