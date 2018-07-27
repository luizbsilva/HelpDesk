CREATE TABLE changestatus ( 
	id_changestatus Integer,
	id_userchange Integer,
	datechangestatus Date,
	status Character Varying( 80 ) NOT NULL, 
	PRIMARY KEY (id_changestatus) );