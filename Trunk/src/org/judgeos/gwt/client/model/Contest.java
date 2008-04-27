package org.judgeos.gwt.client.model;


import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.Date;

public class Contest implements IsSerializable {
	private int id;
	private String name;
	private String description;
	private boolean publicParticipate;
	private Date start;
	private Date stop;

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Problem>
	 */
	private ArrayList problems;

	public Contest() {
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
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

	public boolean isPublicParticipate() {
		return publicParticipate;
	}

	public void setPublicParticipate(boolean publicParticipate) {
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

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Problem>
	 */
	public ArrayList getProblems() {
		return problems;
	}

	/**
	 * @gwt.typeArgs problems <org.judgeos.gwt.client.model.Problem>
	 */
	public void setProblems(ArrayList problems) {
		this.problems = problems;
	}
}
