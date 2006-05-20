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
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
	,password VARCHAR
	,firstName VARCHAR NOT NULL
	,lastName VARCHAR NOT NULL
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
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
);

CREATE TABLE contest (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
	,name VARCHAR NOT NULL
	,description VARCHAR NOT NULL DEFAULT ''
	,owner INTEGER NOT NULL REFERENCES account ON UPDATE CASCADE
	-- is or isn't there a contestMember with password=NULL ,public_observe BOOLEAN NOT NULL DEFAULT FALSE
	,public_participate BOOLEAN NOT NULL DEFAULT TRUE
	,start TIMESTAMP(0) WITH TIME ZONE
	,stop TIMESTAMP(0) WITH TIME ZONE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX contest_codename_idx ON contest(codename);
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005', 'MMF 2005', currval('account_id_seq'), '2005-01-01 00:00:00 UTC', '2005-12-31 23:59:59 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005-05-15', 'MMF Training 15 May 2005', currval('account_id_seq'), '2005-05-15 15:45:00 UTC', '2005-05-15 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2005-05-19', 'MMF Training 19 May 2005', currval('account_id_seq'), '2005-05-19 15:45:00 UTC', '2005-05-19 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006', 'MMF 2006', currval('account_id_seq'), '2006-01-01 00:00:00 UTC', NULL);
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006-05-15', 'MMF Training 20 May 2006', currval('account_id_seq'), '2006-05-20 15:45:00 UTC', '2006-05-20 19:45:00 UTC');
INSERT INTO contest(codename, name, owner, start, stop) VALUES('mmf2006-05-20', 'MMF Training 23 May 2006', currval('account_id_seq'), '2006-05-23 15:45:00 UTC', '2006-05-23 19:45:00 UTC');

CREATE TABLE contestMemberRole (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
);
CREATE UNIQUE INDEX contestMemberRole_codename_idx ON contestMemberRole(codename);
INSERT INTO contestMemberRole(codename) VALUES('jury');
INSERT INTO contestMemberRole(codename) VALUES('participator');
INSERT INTO contestMemberRole(codename) VALUES('observer');

CREATE TABLE contestMember (
	id SERIAL PRIMARY KEY
	,contest VARCHAR NOT NULL
	,codename VARCHAR NOT NULL
	,password VARCHAR -- todo through procedure CHECK (password IS NOT NULL OR role = 'observer')
	,name VARCHAR NOT NULL
	,role INTEGER NOT NULL REFERENCES contestMemberRole ON UPDATE CASCADE
	,created_by INTEGER NOT NULL REFERENCES account ON UPDATE CASCADE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX contestMember_codename_idx ON contestMember(codename);

CREATE TABLE contestMember2account (
	contestMember INTEGER NOT NULL
		REFERENCES contestMember ON UPDATE CASCADE ON DELETE CASCADE
	,account INTEGER NOT NULL
		REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE

	,PRIMARY KEY(contestMember, account)
);

CREATE TABLE problem (
	id SERIAL PRIMARY KEY
	,contest INTEGER REFERENCES contest ON UPDATE CASCADE ON DELETE CASCADE
	,codename VARCHAR NOT NULL
	,situations VARCHAR NOT NULL
	,inputFile VARCHAR NOT NULL
	,outputFile VARCHAR NOT NULL
	,test_time INTEGER
);
CREATE UNIQUE INDEX problem_contest_codename_idx ON problem(contest, codename);

CREATE TABLE judgeosSetting (
	id SERIAL NOT NULL
	,key VARCHAR NOT NULL
	,value VARCHAR
);
CREATE UNIQUE INDEX judgeosSetting_key_idx ON judgeosSetting(key);
INSERT INTO judgeosSetting(key, value) VALUES('hotContest', '1');
COMMIT;
