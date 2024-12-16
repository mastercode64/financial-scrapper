# financial-scrapper
[![CircleCI](https://circleci.com/gh/mastercode64/financial-scrapper/tree/main.svg?style=shield)](https://circleci.com/gh/mastercode64/financial-scrapper/tree/master)
<p>This project is used to fetch stock information using scrapper library <b>Jsoup</b> and export all data to csv file.</p>

### Project requirements
- Java 17
- Maven 3.8.7

### How to run the project locally
- Insert your stocks in the application.yaml file, it supports multiple values separated by comma. Example: "AAZQ11,ALZR11,BCFF11"
- Navigate to the project root folder in your terminal and run
```shell
./mvnw spring-boot:run
```

How to run all tests
```shell
mvn clean test
```

How to format code using ktlint
```shell
mvn antrun:run@ktlint-format
```