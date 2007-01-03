package org.judgeos.model;

import java.util.Date;
import java.io.Serializable;

public class Contest implements Serializable {
	private Integer id;
	private String codename;
	private String name;
	private String description;
	private Account owner;
	private Boolean publicParticipate;
	private Date start;
	private Date stop;
	private Date createdOn;

	public Contest() {}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public Boolean getPublicParticipate() {
		return publicParticipate;
	}

	public void setPublicParticipate(Boolean publicParticipate) {
		this.publicParticipate = publicParticipate;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	private void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
