# NoPHP
This program provides the means to generate and maintain HTML 5 websites and structures with a MariaDB (Database) and Java 8. See the LICENSE file for information about my liabilities toward any usage, distribution as the lead programmer of this project.
## Usage
This program saves its configuration in `.NoPHPSettings.xml` under the users home directory. The HTML files are stored in `/var/www/`, where they will be loaded by whatever webserver is used.
### Terminal
Download the latest build (atm no builds exist) and start the server with `java -jar NoPHP.X.Y.Z.jar` (X,Y,Z describe the version).
If no configuration (`.NoPHPSettings.xml`) exists, the server will generate a generic one.
Data you will need for the config file:
+ The address of the MariaDB / MySQL server
+ A JDBC driver for the Database (included driver: `org.mariadb.jdbc.Driver`)
+ The username of a user that can create and manipulate the database and its tables
+ The password for that user (ATTENTION: the password is saved as clear text)

## Installation
Before you install and use this program you should have a good knowledge of:
+ The usage/administration of MariaDB/MySQL databases and MariaDB/MySQL scripts
+ Technical know-how

The application uses MariaDB/MySQL to save data.
The script located at /MySQL/Create.sql will generate the basic datastructure for the server.

