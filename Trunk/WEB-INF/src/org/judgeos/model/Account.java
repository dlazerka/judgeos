package org.judgeos.model;

import java.util.Date;
import java.io.Serializable;


/**
 * Represents an registered (signed up) user for the whole system.
 * Todo: consider making id, createdOn final.
 */
public class Account implements Serializable {
	private Integer id;
	private String codename;
	private String password;
	private String firstName;
	private String lastName;
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

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
