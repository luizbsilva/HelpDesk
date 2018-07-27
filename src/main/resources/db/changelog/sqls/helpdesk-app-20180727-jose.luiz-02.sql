CREATE TABLE ticket ( 
	id_ticket Integer,
	id_user Integer,
	date_ticket Date,
	title Character Varying( 80 ),
	number Integer,
	status Character Varying( 80 ),
	priority Character Varying( 2044 ),
	id_assigneduser Integer,
	description Character Varying( 2044 ) NOT NULL,
	image Character Varying( 2044 ) NOT NULL, 
	PRIMARY KEY (id_ticket));