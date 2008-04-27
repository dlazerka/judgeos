package org.judgeos.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MemberProblemStatus implements IsSerializable {
	private Member member;
	private Problem problem;
	private boolean accepted;
	private int solutionsCount;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public int getSolutionsCount() {
		return solutionsCount;
	}

	public void setSolutionsCount(int solutionsCount) {
		this.solutionsCount = solutionsCount;
	}
}
