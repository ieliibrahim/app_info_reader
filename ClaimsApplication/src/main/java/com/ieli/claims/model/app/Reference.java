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
@Table(name = "references")
public class Reference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reference_id")
	private Integer referenceId;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide reference name")
	private String name;

	@Column(name = "publication_number")
	@NotEmpty(message = "*Please provide publication number")
	private String publicationNumber;

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
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
