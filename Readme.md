# SWIFT Analyzer
This project parses data from .xlsx file, stores it in a database and provides REST endpoints to add and retrieve data.

## Used Technologies
* Java
* Maven
* SpringBoot
* MongoDB

## Using this programme
1. To load data from `.xlsx` file uncomment relevant part of Main class, change file path. Then compile and run `Main.java`.
2. To use REST endpoints simply compile and run `Main.java`.
3. Please note that in order to connect to database you need correct URI which is **NOT** included in this repository as it contains login and password.
4. This programme was created and tested in `IntelliJ IDEA` as it provides useful tools for running such project.

## Tests
There are provided tests for all methods implementing REST endpoints in `src/test/java/app/controller/DataEndpointsControllerTest.java`. This tests show correct usage of those methods.

## Database structure
Data is stored in MongoDB database. It consists of collections named after country ISO2 codes. Each collection contains documents with fields listed in ExtendedResponse class, which are:
* "address": string,
* "bankName": string,
* "countryISO2": string,
* "countryName": string,
* "isHeadquarter": bool,
* "swiftCode": string

Such representation allows for efficient organization and retrieval of data based on country codes, enabling quick lookups and better scalability for adding new SWIFT codes to the database.

## Extended decription
The "SWIFT Analyzer" project is an application that enables the analysis of data from .xlsx files, stores it in a MongoDB database, and provides REST endpoints for adding and retrieving data. It's possible to retrieve data for a single SWIFT code or certain country, add new data or delete it. The application stores information about bank SWIFT codes, including fields such as address, bank name, country ISO code, country name, headquarters status, and the SWIFT code itself. Data is organized into MongoDB collections named after country ISO2 codes, ensuring easy access and fast retrieval of information. The project uses Java, SpringBoot, and MongoDB technologies, with tests covering all methods implementing the REST endpoints.