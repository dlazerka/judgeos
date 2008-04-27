package org.judgeos.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.HashMap;

public class Problem implements IsSerializable {
	private int id;
	private String name;
	private Contest contest;
	private String content;
	private String inputFileName;
	private String outputFileName;
	private int testTime;

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Member, org.judgeos.gwt.client.model.MemberProblemStatus>
	 */
	private HashMap memberStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Member, org.judgeos.gwt.client.model.MemberProblemStatus>
	 */
	public HashMap getMemberStatus() {
		return memberStatus;
	}

	/**
	 * @gwt.typeArgs memberStatus <org.judgeos.gwt.client.model.Member, org.judgeos.gwt.client.model.MemberProblemStatus>
	 */
	public void setMemberStatus(HashMap memberStatus) {
		this.memberStatus = memberStatus;
	}
}
