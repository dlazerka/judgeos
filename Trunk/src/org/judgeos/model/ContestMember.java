package org.judgeos.model;

import java.io.Serializable;
import java.util.Date;

public class ContestMember implements Serializable {
	private Integer id;
	private Contest contest;
	private String name;
	private String password;
	private ContestMemberRole role;
	private Account responsibleAccount;
	private Date createdOn;


	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ContestMemberRole getRole() {
		return role;
	}

	public void setRole(ContestMemberRole role) {
		this.role = role;
	}

	public Account getResponsibleAccount() {
		return responsibleAccount;
	}

	public void setResponsibleAccount(Account responsibleAccount) {
		this.responsibleAccount = responsibleAccount;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	private void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
