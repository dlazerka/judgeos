package org.judgeos.model;

import java.util.Date;
import java.io.Serializable;


/**
 * Account is a user who deals with the whole project, not the one contest.
 * <p/>
 * Todo: consider making id, createdOn final.
 */
public class Account implements Serializable {
	private Integer id;
	private String email;
	private String password;
	private String fullName;
	private Date createdOn;

	public Account() {}

	public Integer getId() {
		return id;
	}

	/**
	 * id is final
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * No one needed the password.
	 * @return password
	 */
	private String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * createdOn is final
	 * @param createdOn
	 */
	private void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
