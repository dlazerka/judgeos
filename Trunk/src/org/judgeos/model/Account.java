package org.judgeos.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.Date;


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
	 * No one needs the password.
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

	/**
	 * Returns new account instance which considered as "guest" or "anonymous".
	 * @return account bean
	 */
	public static Account getGuestInstance() {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		Account account = (Account) session.createCriteria(Account.class).
			add(Restrictions.eq("email", Constants.guestEmail))
			.uniqueResult();
		session.close();

		return account;
	}
}
