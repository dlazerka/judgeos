DROP TABLE solution CASCADE;
DROP TABLE account CASCADE;
DROP TABLE contest CASCADE;
-- DROP TABLE judgeosRole CASCADE;
-- DROP TABLE judgeosRole2account CASCADE;
DROP TABLE memberRole CASCADE;
DROP TABLE member CASCADE;
DROP TABLE problem CASCADE;
DROP TABLE judgeosSetting CASCADE;

BEGIN;

/*
CREATE TABLE judgeosRole (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
);
ALTER TABLE judgeosRole OWNER TO judgeos;
CREATE UNIQUE INDEX judgeosRole_codename_idx ON judgeosRole(codename);
INSERT INTO judgeosRole(codename) VALUES('admin');
*/

-- TODO: passwords should be encrypted.
CREATE TABLE account (
	id SERIAL
	,email VARCHAR NOT NULL
	,password VARCHAR NOT NULL
	,fullName VARCHAR NOT NULL
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL DEFAULT NOW()
	,PRIMARY KEY(id)
);
ALTER TABLE account OWNER TO judgeos;
CREATE UNIQUE INDEX account_email_idx ON account(email);
INSERT INTO account(email, password, fullName) VALUES('guest@judgeos.org', '', 'Anonymous Guest');
INSERT INTO account(email, password, fullName) VALUES('dlazerka@gmail.com', 'pass', 'Dzmitry Lazerka');
/*
CREATE TABLE judgeosRole2account (
	judgeosRole INTEGER NOT NULL
		REFERENCES judgeosRole ON UPDATE CASCADE ON DELETE CASCADE
	,account INTEGER NOT NULL
		REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE

	,PRIMARY KEY(judgeosRole, account)
	,FOREIGN KEY(judgeosRole) REFERENCES judgeosRole ON UPDATE CASCADE ON DELETE CASCADE
	,FOREIGN KEY(account) REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE
);
ALTER TABLE judgeosRole2account OWNER TO judgeos;
*/

CREATE TABLE contest (
	id SERIAL
	,name VARCHAR NOT NULL
	,description VARCHAR NOT NULL DEFAULT ''
	,owner INTEGER NOT NULL
	-- is or isn't there a member with password=NULL ,publicObserve BOOLEAN NOT NULL DEFAULT FALSE
	,publicParticipate BOOLEAN NOT NULL DEFAULT TRUE
	,start TIMESTAMP(0) WITH TIME ZONE
	,stop TIMESTAMP(0) WITH TIME ZONE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL DEFAULT NOW()

	,PRIMARY KEY(id)
	,FOREIGN KEY(owner) REFERENCES account ON UPDATE CASCADE
);
ALTER TABLE contest OWNER TO judgeos;
INSERT INTO contest(name, owner, start, stop) VALUES('MMF 2005', currval('account_id_seq'), '2005-01-01 00:00:00 UTC', '2005-12-31 23:59:59 UTC');
INSERT INTO contest(name, owner, start, stop) VALUES('MMF Training 15 May 2005', currval('account_id_seq'), '2005-05-15 15:45:00 UTC', '2005-05-15 19:45:00 UTC');
INSERT INTO contest(name, owner, start, stop) VALUES('MMF Training 19 May 2005', currval('account_id_seq'), '2005-05-19 15:45:00 UTC', '2005-05-19 19:45:00 UTC');
INSERT INTO contest(name, owner, start, stop) VALUES('MMF 2006', currval('account_id_seq'), '2006-01-01 00:00:00 UTC', NULL);
INSERT INTO contest(name, owner, start, stop) VALUES('MMF Training 21 May 2006', currval('account_id_seq'), '2006-05-22 15:45:00 UTC', '2006-05-22 19:45:00 UTC');
INSERT INTO contest(name, owner, start, stop) VALUES('MMF Training 22-23 May 2006', currval('account_id_seq'), '2006-05-22 15:45:00 UTC', '2006-05-23 19:45:00 UTC');
INSERT INTO contest(name, owner, start, stop) VALUES('MMF Training 20 June 2006', currval('account_id_seq'), '2006-06-20 15:45:00 UTC', '2006-06-20 19:45:00 UTC');

CREATE TABLE memberRole (
	id SERIAL
	,name VARCHAR NOT NULL
	,PRIMARY KEY(id)
);
ALTER TABLE memberRole OWNER TO judgeos;
CREATE UNIQUE INDEX memberRole_name_idx ON memberRole(name);
INSERT INTO memberRole(name) VALUES('jury');
INSERT INTO memberRole(name) VALUES('participant');
INSERT INTO memberRole(name) VALUES('observer');

/**
 * Someone (not necessary an account) who deals with a contest.
 * Password is null iff contest.publicParticipate is allowed.
 */
CREATE TABLE member (
	id SERIAL
	,contest INTEGER NOT NULL
	,name VARCHAR NOT NULL
	,password VARCHAR
	,role INTEGER NOT NULL
	,responsibleAccount INTEGER NOT NULL
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL DEFAULT NOW()

	,PRIMARY KEY(id)
	,FOREIGN KEY(contest) REFERENCES contest ON UPDATE CASCADE
	,FOREIGN KEY(role) REFERENCES memberRole ON UPDATE CASCADE
	,FOREIGN KEY(responsibleAccount) REFERENCES account ON UPDATE CASCADE
);
ALTER TABLE member OWNER TO judgeos;
CREATE UNIQUE INDEX member_contest_name_idx ON member(contest, name);
CREATE UNIQUE INDEX member_contest_password_idx ON member(contest, password);

CREATE TABLE problem (
	id SERIAL
	,contest INTEGER
	,codename VARCHAR NOT NULL
	,content VARCHAR NOT NULL
	,inputFile VARCHAR NOT NULL
	,outputFile VARCHAR NOT NULL
	,testTime INTEGER

	,PRIMARY KEY(id)
	,FOREIGN KEY(contest) REFERENCES contest ON UPDATE CASCADE ON DELETE CASCADE
);
ALTER TABLE problem OWNER TO judgeos;
CREATE UNIQUE INDEX problem_contest_codename_idx ON problem(contest, codename);

CREATE TABLE solution (
	id SERIAL
	,member INTEGER NOT NULL
	,problem INTEGER NOT NULL
	,accepted BOOLEAN NOT NULL DEFAULT FALSE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL DEFAULT NOW()

	,PRIMARY KEY(id)
	,FOREIGN KEY(member) REFERENCES member ON UPDATE CASCADE ON DELETE CASCADE
	,FOREIGN KEY(problem) REFERENCES problem ON UPDATE CASCADE ON DELETE CASCADE
);

COMMIT;

