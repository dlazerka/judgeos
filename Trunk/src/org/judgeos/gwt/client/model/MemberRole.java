package org.judgeos.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MemberRole implements IsSerializable {
	private static final String PARTICIPANT_ROLE_NAME = "participant";

	private int id;
	private String name;


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
}

