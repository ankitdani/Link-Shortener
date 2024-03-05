# URL Shortener

This is a URL shortener application built with Java and Vaadin.

## Overview

This URL shortener converts long URLs into shorter, more manageable links. This project provides a web-based interface for shortening URLs.

## Algorithm Used

Base 62 is a way of using 62 (0-9a-zA-Z) characters for encoding.

## Technologies Used

- ![Java](https://img.shields.io/badge/Java-Latest-orange)
- ![Vaadin](https://img.shields.io/badge/Vaadin-Latest-blue)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Latest-brightgreen)
- ![Redis](for storing shortened URLs)(https://img.shields.io/badge/Redis-Latest-red)
- ![SonarLint](https://img.shields.io/badge/SonarLint-Latest-yellow)

## Features

URL length is not fixed. It goes up with the ID.

Collision is impossible because ID is unique. 

## Drawbacks

It is easy to figure out the next available short URL if the ID increments by 1 for a new entry. This can be a security concern. 

## Getting Started

To start the URL shortener locally, follow these steps:

1. Clone this repository:

```
git clone https://github.com/your-username/url-shortener.git
```

### To run

Start up Redis' Server
```
redis-server
```

Build the project
```
mvn build
```

Run the project
```
mvn spring-boot:run
```

By default, the server will run on 
```
localhost:8080/shortener/v1
```
