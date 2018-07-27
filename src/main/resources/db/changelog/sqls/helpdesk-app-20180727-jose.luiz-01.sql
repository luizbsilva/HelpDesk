ALTER TABLE usuario DROP COLUMN e_mail;
ALTER TABLE usuario ADD COLUMN email Character Varying( 80 ) NOT NULL;
