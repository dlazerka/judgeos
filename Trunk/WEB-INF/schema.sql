DROP TABLE account CASCADE;
DROP TABLE contest CASCADE;
DROP TABLE judgeos_role CASCADE;
DROP TABLE judgeos_role2account CASCADE;
DROP TABLE contest_member_role CASCADE;
DROP TABLE contest_member CASCADE;
DROP TABLE contest_member2account CASCADE;
DROP TABLE problem CASCADE;
DROP TABLE judgeos_setting CASCADE;

BEGIN;


CREATE TABLE judgeos_role (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
);
CREATE UNIQUE INDEX judgeos_role_codename_idx ON judgeos_role(codename);
INSERT INTO judgeos_role(codename) VALUES('admin');


CREATE TABLE account (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
	,password VARCHAR
	,first_name VARCHAR NOT NULL
	,last_name VARCHAR NOT NULL
	,created_on TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX account_codename_idx ON account(codename);
INSERT INTO account(codename, password, first_name, last_name) VALUES('guest', NULL, 'Anonymous', 'User');
INSERT INTO account(codename, password, first_name, last_name) VALUES('dlazerka', 'pass', 'Dzmitry', 'Lazerka');

CREATE TABLE judgeos_role2account (
	judgeos_role INTEGER NOT NULL
		REFERENCES judgeos_role ON UPDATE CASCADE ON DELETE CASCADE
	,account INTEGER NOT NULL
		REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE

	,PRIMARY KEY(judgeos_role, account)
);

CREATE TABLE contest (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
	,description VARCHAR
	,owner INTEGER NOT NULL REFERENCES account ON UPDATE CASCADE
	-- is or isn't there a contest_member with password=NULL ,public_observe BOOLEAN NOT NULL DEFAULT FALSE
	,public_participate BOOLEAN NOT NULL DEFAULT TRUE
	,start_time TIMESTAMP(0) WITH TIME ZONE
	,stop_time TIMESTAMP(0) WITH TIME ZONE
	,created_on TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX contest_codename_idx ON contest(codename);
INSERT INTO contest(codename, owner) VALUES('mmf2006-05-15', currval('account_id_seq'));

CREATE TABLE contest_member_role (
	id SERIAL PRIMARY KEY
	,codename VARCHAR NOT NULL
);
CREATE UNIQUE INDEX contest_member_role_codename_idx ON contest_member_role(codename);
INSERT INTO contest_member_role(codename) VALUES('jury');
INSERT INTO contest_member_role(codename) VALUES('participator');
INSERT INTO contest_member_role(codename) VALUES('observer');

CREATE TABLE contest_member (
	id SERIAL PRIMARY KEY
	,contest VARCHAR NOT NULL
	,codename VARCHAR NOT NULL
	,password VARCHAR -- todo through procedure CHECK (password IS NOT NULL OR role = 'observer')
	,name VARCHAR NOT NULL
	,role INTEGER NOT NULL REFERENCES contest_member_role ON UPDATE CASCADE
	,created_by INTEGER NOT NULL REFERENCES account ON UPDATE CASCADE
	,created_on TIMESTAMP(0) WITH TIME ZONE NOT NULL
		DEFAULT ('now'::TEXT)::TIMESTAMP(0) WITH TIME ZONE
);
CREATE UNIQUE INDEX contest_member_codename_idx ON contest_member(codename);

CREATE TABLE contest_member2account (
	contest_member INTEGER NOT NULL
		REFERENCES contest_member ON UPDATE CASCADE ON DELETE CASCADE
	,account INTEGER NOT NULL
		REFERENCES account ON UPDATE CASCADE ON DELETE CASCADE

	,PRIMARY KEY(contest_member, account)
);

CREATE TABLE problem (
	id SERIAL PRIMARY KEY
	,contest INTEGER REFERENCES contest ON UPDATE CASCADE ON DELETE CASCADE
	,codename VARCHAR NOT NULL
	,situations VARCHAR NOT NULL
	,input_file VARCHAR NOT NULL
	,output_file VARCHAR NOT NULL
	,test_time INTEGER
);
CREATE UNIQUE INDEX problem_contest_codename_idx ON problem(contest, codename);

CREATE TABLE judgeos_setting (
	id SERIAL NOT NULL
	,key VARCHAR NOT NULL
	,value VARCHAR
);
CREATE UNIQUE INDEX judgeos_setting_key_idx ON judgeos_setting(key);
INSERT INTO judgeos_setting(key, value) VALUES('hot_contest', '1');
COMMIT;
