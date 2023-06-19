# financial-scrapper
[![CircleCI](https://circleci.com/gh/mastercode64/financial-scrapper/tree/main.svg?style=shield)](https://circleci.com/gh/mastercode64/financial-scrapper/tree/master)
<p>This project is used to fetch stock information using scrapper library <b>Jsoup</b></p>

## Project requirements
- Java 17
- Maven 3.8.7

## How to run the project locally
<h4>Navigate to the project **root** folder in your terminal and run</h4>
```
mvn spring-boot:run
```

<h4>How to run all tests</h4>
```
mvn clean test
```

<h4>How to format code using ktlint</h4>
```
mvn antrun:run@ktlint-format
```