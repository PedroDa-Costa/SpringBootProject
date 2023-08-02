-- Create new user for the spring project
-- run this script with root

create user 'projectuser'@'localhost' identified by 'projectuser';

grant all privileges on *.* to 'projectuser'@'localhost';




-- just in case
-- drop user if exists 'projectuser'@'localhost';