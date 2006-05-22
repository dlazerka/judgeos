DROP TABLE account CASCADE;
DROP TABLE contest CASCADE;
DROP TABLE judgeosRole CASCADE;
DROP TABLE judgeosRole2account CASCADE;
DROP TABLE contestMemberRole CASCADE;
DROP TABLE contestMember CASCADE;
DROP TABLE contestMember2account CASCADE;
DROP TABLE problem CASCADE;
DROP TABLE judgeosSetting CASCADE;

BEGIN;


CREATE TABLE judgeosRole (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
);
CREATE UNIQUE INDEX judgeosRole_codename_idx ON judgeosRole(codename);
INSERT INTO judgeosRole(codename) VALUES('admin');


CREATE TABLE account (
	id SERIAL
	,codename VARCHAR NOT NULL
	,password VARCHAR
	,firstName VARCHAR NOT NULL
	,lastName VARCHAR NOT NULL
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
	,PRIMARY KEY(id)
);
CREATE UNIQUE INDEX account_codename_idx ON account(codename);
INSERT INTO account(codename, password, firstName, lastName) VALUES('guest', NULL, 'Anonymous', 'User');
INSERT INTO account(codename, password, firstName, lastName) VALUES('dlazerka', 'pass', 'Dzmitry', 'Lazerka');

CREATE TABLE judgeosRole2account (
	judgeosRole INTEGER NOT NULL
		REFERENCES judgeosRole ON UPDATE CASCADE ON DELETE CASCADE
	,account INTEGER NOT NULL
		REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE

	,PRIMARY KEY(judgeosRole, account)
	,FOREIGN KEY(judgeosRole) REFERENCES judgeosRole ON UPDATE CASCADE ON DELETE CASCADE
	,FOREIGN KEY(account) REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE contest (
	id SERIAL
	,codename VARCHAR NOT NULL
	,name VARCHAR NOT NULL
	,description VARCHAR NOT NULL DEFAULT ''
	,owner INTEGER NOT NULL
	-- is or isn't there a contestMember with password=NULL ,publicObserve BOOLEAN NOT NULL DEFAULT FALSE
	,publicParticipate BOOLEAN NOT NULL DEFAULT TRUE
	,start TIMESTAMP(0) WITH TIME ZONE
	,stop TIMESTAMP(0) WITH TIME ZONE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE

	,PRIMARY KEY(id)
	,FOREIGN KEY(owner) REFERENCES account ON UPDATE CASCADE
);
CREATE UNIQUE INDEX contest_codename_idx ON contest(codename);
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005', 'MMF 2005', currval('account_id_seq'), '2005-01-01 00:00:00 UTC', '2005-12-31 23:59:59 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005-05-15', 'MMF Training 15 May 2005', currval('account_id_seq'), '2005-05-15 15:45:00 UTC', '2005-05-15 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005-05-19', 'MMF Training 19 May 2005', currval('account_id_seq'), '2005-05-19 15:45:00 UTC', '2005-05-19 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006', 'MMF 2006', currval('account_id_seq'), '2006-01-01 00:00:00 UTC', NULL);
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006-05-22', 'MMF Training 21 May 2006', currval('account_id_seq'), '2006-05-22 15:45:00 UTC', '2006-05-22 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006-05-23', 'MMF Training 22-23 May 2006', currval('account_id_seq'), '2006-05-22 15:45:00 UTC', '2006-05-23 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006-06-20', 'MMF Training 20 June 2006', currval('account_id_seq'), '2006-06-20 15:45:00 UTC', '2006-06-20 19:45:00 UTC');

CREATE TABLE contestMemberRole (
	id SERIAL
	,codename VARCHAR NOT NULL
	,PRIMARY KEY(id)
);
CREATE UNIQUE INDEX contestMemberRole_codename_idx ON contestMemberRole(codename);
INSERT INTO contestMemberRole(codename) VALUES('jury');
INSERT INTO contestMemberRole(codename) VALUES('participator');
INSERT INTO contestMemberRole(codename) VALUES('observer');

CREATE TABLE contestMember (
	id SERIAL
	,contest VARCHAR NOT NULL
	,codename VARCHAR NOT NULL
	,password VARCHAR
	,name VARCHAR NOT NULL
	,role INTEGER NOT NULL
	,createdBy INTEGER NOT NULL
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE

	,PRIMARY KEY(id)
	,FOREIGN KEY(role) REFERENCES contestMemberRole ON UPDATE CASCADE
	,FOREIGN KEY(createdBy) REFERENCES account ON UPDATE CASCADE
);
CREATE UNIQUE INDEX contestMember_codename_idx ON contestMember(codename);

CREATE TABLE contestMember2account (
	contestMember INTEGER NOT NULL
	,account INTEGER NOT NULL

	,PRIMARY KEY(contestMember, account)
	,FOREIGN KEY(contestMember) REFERENCES contestMember ON UPDATE CASCADE ON DELETE CASCADE
	,FOREIGN KEY(account) REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE problem (
	id SERIAL
	,contest INTEGER
	,codename VARCHAR NOT NULL
	,situations VARCHAR NOT NULL
	,inputFile VARCHAR NOT NULL
	,outputFile VARCHAR NOT NULL
	,testTime INTEGER

	,PRIMARY KEY(id)
	,FOREIGN KEY(contest) REFERENCES contest ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE UNIQUE INDEX problem_contest_codename_idx ON problem(contest, codename);

CREATE TABLE judgeosSetting (
	id SERIAL
	,key VARCHAR NOT NULL
	,value VARCHAR
	,PRIMARY KEY(id)
);
CREATE UNIQUE INDEX judgeosSetting_key_idx ON judgeosSetting(key);
INSERT INTO judgeosSetting(key, value) VALUES('hotContest', '1');
COMMIT;
