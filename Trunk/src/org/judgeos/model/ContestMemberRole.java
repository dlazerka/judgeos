package org.judgeos.model;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

public class ContestMemberRole implements Serializable {
	private static final String PARTICIPANT_ROLE_NAME = "participant";

	private Integer id;
	private String name;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ContestMemberRole getParticipantRole() {
		Session session = HibernateUtil.getCurrentSession();
		ContestMemberRole contestMemberRole = (ContestMemberRole) session.createCriteria(ContestMemberRole.class)
			.add(Restrictions.eq("name", PARTICIPANT_ROLE_NAME))
			.uniqueResult();

		if (contestMemberRole == null) {
			throw new IllegalStateException("There must be a participant role");
		}
		
		return contestMemberRole;
	}
}
