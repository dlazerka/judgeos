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
	,description VARCHAR NOT NULL DEFAULT ''
	,owner INTEGER NOT NULL REFERENCES account ON UPDATE CASCADE
	-- is or isn't there a contestMember with password=NULL ,public_observe BOOLEAN NOT NULL DEFAULT FALSE
	,public_participate BOOLEAN NOT NULL DEFAULT TRUE
	,startTime TIMESTAMP(0) WITH TIME ZONE
	,stopTime TIMESTAMP(0) WITH TIME ZONE
	,createdOn TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX contest_codename_idx ON contest(codename);
INSERT INTO contest(codename, owner) VALUES('mmf2006-05-15', currval('account_id_seq'));

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
