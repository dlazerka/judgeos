package org.judgeos.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.HashMap;

public class Member implements IsSerializable {
	private int id;
	private Contest contest;
	private String name;
	private MemberRole role;
	private Account responsibleAccount;
	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Problem, org.judgeos.gwt.client.model.MemberProblemStatus>
	 */
	private HashMap problemStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public MemberRole getRole() {
		return role;
	}

	public void setRole(MemberRole role) {
		this.role = role;
	}

	public Account getResponsibleAccount() {
		return responsibleAccount;
	}

	public void setResponsibleAccount(Account responsibleAccount) {
		this.responsibleAccount = responsibleAccount;
	}

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Problem, org.judgeos.gwt.client.model.MemberProblemStatus>
	 * @return
	 */
	public HashMap getProblemStatus() {
		return problemStatus;
	}

	/**
	 * @gwt.typeArgs problemStatus <org.judgeos.gwt.client.model.Problem, org.judgeos.gwt.client.model.MemberProblemStatus>
	 * @return
	 */
	public void setProblemStatus(HashMap problemStatus) {
		this.problemStatus = problemStatus;
	}
}