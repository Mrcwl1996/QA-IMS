# Inventory Management System 

The application is designed so that an end user can interact with via a Command-Line Interface (CLI). 
The inventory management system is designed to Add,View,Update and delete and customer infomation in the database.
Secondly it is able to also Add,View,Update and delate and item from the item database. 
Lastly, the application is able to integrate the item from the itemlist and automatically generate a unique order with the customer details and calculate the total cost of the order. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Install: Eclipse IDE for Java

Install: Java Development Kit 14

Install: Maven

Install: MySQL Server (for localhost running/testing) and MySQL Workbench 

OR

Run a MySQL Server on a GCP instance

## Installing
Once this repo has been downloaded or cloned, open the project in Eclipse:

File -> Import -> Select Maven/Existing Maven Projects -> Browse to the folder and click Finish
Ensure that a localhost MySQL is set-up on your computer by opening MySQL Workbench, and under MySQL Connections is

root
localhost:3306
Right click Runner.Java -> Run With... -> Java Application

Username:

root
Password:

root

The domain menu is then shown.

## Example with Customer
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
TYPE customer
then it will give you and option to CREATE, READ , UPDATE, DELETE:

TYPE create

then enter the name of the customer you would like to create. 
e.g William Chu
to access the database table 

TYPE read
and you will see:

ID       First Name:     Surname:
 1       William         Chu

## Running the tests

There are three main test suites in the project. These are stored under:

src/test/java
The test are split into Controllers -DAO -Domain

Each test suite has a corresponding test suite that tests if the program has failed correctly. An example of this is:

CustomerDAOTest.java

## Deployment
To run the .jar file from the command-line:
- navigate to the directory .jar file
- execute the following command once you have ensured that a MySQL localhost Server is running on your machine:

Example:

java -jar ims-0.0.1-jar-with-dependencies.jar
Username:

root
Password:

root

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **William Chu** - *Additional work* - [WilliamChu](https://github.com/mrcwl1996)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
