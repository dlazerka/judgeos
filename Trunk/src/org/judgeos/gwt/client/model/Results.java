package org.judgeos.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

public class Results implements IsSerializable {
	private Contest contest;
	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Member>
	 */
	private ArrayList positions;

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	/**
	 * @gwt.typeArgs <org.judgeos.gwt.client.model.Member>
	 */
	public ArrayList getPositions() {
		return positions;
	}

	/**
	 * @gwt.typeArgs positions <org.judgeos.gwt.client.model.Member>
	 */
	public void setPositions(ArrayList positions) {
		this.positions = positions;
	}
}
