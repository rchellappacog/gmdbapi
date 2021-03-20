# The G Movie Database

<p align="center">
  <a href="#">
    <img src="./img/logo.png" alt="movie database logo" width="85" height="85">
  </a>
</p>

<h3 align="center">The G Movie Database (Back-End Spring Boot REST API)</h3>

<p align="center">
  The G Movie is a spring boot service the store the information of a movie into the database system.
  <br>

  <br>
</p>

## Table of contents

- [Stories and Acceptance Criteria](#stories-and-acceptance-criteria)
- [Technology Used](#technology-used)
- [General Approach](#general-approach)
- [Architecture](#architecture)
- [Installation Instruction](#installation-instruction)
- [API](#api)
- [What's Included](#whats-included)
- [Creators](#developers)


## Stories and Acceptance Criteria

As a user, I should see a list of movies when I visit GMDB.


Application Goals from User Stories are:

[The G Movie Database (Back-End Spring boot REST API)](https://learn-2.galvanize.com/cohorts/2560/blocks/1156/content_files/units/04-gmdb/01-unit-overview.md)


## Technology Used

- Gradle as Build Tool
- Spring boot is used for the backend Services.
- H2 database
- Junit and Mockito for Tests.
- Postgres is Used for the database.

## General Approach

We discussed the architecture of the project and use TDD approach. We figured out
how to proceed with the development and created the corresponding user stories that will lead us to completion.

We used pair programming and collaborated with each other and tried to stick to our schedule.


## Installation Instruction

Software Requirement:
1. Gradle ([install](https://gradle.org/install/))

2. Java (minimum java 8) ([install](https://www.oracle.com/java/technologies/javase-downloads.html))

Follow these easy step:


1. Clone the repository:
     ```
     https://github.com/rchellappacog/gmdbapi.git
     ```
2. Execute this on command line:

     ```
     $ cd gmdbapi
     $ gradle build
     $ cd \build\libs
     $ java -jar GMDB-0.0.1-SNAPSHOT.jar
     ```
3. Enjoy


## API
```text
Base URL: /, Version: 1.1

Default request content-types: application/json

Default response content-types: application/json

Schemes: http 
```

## API Reference
<table style="
    width: 100%;
    max-width: 100%;
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-collapse: collapse;
    border-spacing: 0;
    background-color: transparent;
    display: table;
">
    <thead>
    <tr>
        <th>Path</th>
        <th>Operation</th>
        <th>Description</th>
        <th>Controller -> Method</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;" rowspan="2">
            <a href="#summary">/movies/</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#get-movies">GET</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Get list of movies. </p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>MovieController -> getMovie()</p>
        </td>
    </tr>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#post-movies">POST</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Create a Movie Into the Database</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>MovieController -> createMovie()</p>
        </td>
    </tr>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;" rowspan="1">
            <a href="#summary">/movies/{movieName}</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#get-moviesmoviename">GET</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Get Movie by Movie Name</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>MovieController -> getMovie()</p>
        </td>
    </tr>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;" rowspan="1">
            <a href="#summary">/rating</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#post-rating">POST</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Submit Rating of a Movie</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>RatingController -> addRatingToMovie()</p>
        </td>
    </tr>
    </tbody>
</table>

#### API Details

#### POST /movies/
Response Header
```text
    Status: 201 CREATED
```
Request Body
```json5
{
  "title": "Avengers",
  "director": "Joss",
  "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
  "releasedYear": "2012",
  "description": "Avengeeeeeeee"
}
```

Response Header
```text
[Content-Type:"application/json"]
```

Response Body
```json5
{
  "message": "Movie has been created."
}
```

#### GET /movies/
Response Header
```text
    Status: 200 OK
```

Response Body
```json5
[
   {
      "title": "Avengers",
      "director": "Joss",
      "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
      "releasedYear": "2012",
      "description": "Avengeeeeeeee"
   },
   {
      "title": "Avengers",
      "director": "Joss",
      "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
      "releasedYear": "2012",
      "description": "Avengeeeeeeee"
   },
   ...
]
```
#### GET /movies/{movieName}
Response Header
```text
    Status: 200 OK
```
Response Body
```json5

{
   "title": "Avengers",
   "director": "Joss",
   "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
   "releasedYear": "2012",
   "description": "Avengeeeeeeee"
}

```

Response Header
```text
    Status: 404 NOT FOUND
```
Response Body
```json5

{
   "message": "Movie not available"
}

```


#### POST /rating
Request Body 
```json5
{
  "movieTitle": "Avengers",
  "stars": "5",
  "review": "Hate Thanos."
}
```

Response Header
```text
Status: 201 Created
STatus: 400 Client Error
```

Respnse Body
```json5
{
  "message": "A rating has been added"
}
```

```json5
{
  "message": "Star rating is required"
}
```

## What's included

Within the download you'll find the following directories and files, logically grouping common assets and providing both compiled and minified variations. You'll see something like this:

<details>
<summary>
File Tree.
</summary>


```text
│   .gitignore
│   build.gradle
│   gradlew
│   gradlew.bat
│   README.md
│   settings.gradle
│   story.html
│   table.html
└───src
    ├───main
    │   ├───generated
    │   ├───java
    │   │   └───com
    │   │       └───galvanize
    │   │           └───GMDB
    │   │               │   GmdbApplication.java
    │   │               │
    │   │               ├───Controller
    │   │               │       MovieController.java
    │   │               │       RatingController.java
    │   │               │
    │   │               ├───Entity
    │   │               │       Movie.java
    │   │               │       MovieRating.java
    │   │               │
    │   │               ├───Exception
    │   │               │       MovieNotFoundException.java
    │   │               │       MyExceptionHandler.java
    │   │               │       RatingStarException.java
    │   │               │
    │   │               ├───repository
    │   │               │       MovieRepository.java
    │   │               │       RatingRepository.java
    │   │               │
    │   │               ├───request
    │   │               │       MovieRequest.java
    │   │               │       RatingRequest.java
    │   │               │
    │   │               ├───response
    │   │               │       CustomResponse.java
    │   │               │       MovieResponse.java
    │   │               │
    │   │               └───Service
    │   │                       MovieService.java
    │   │                       RatingService.java
    │   │
    │   └───resources
    │           application.properties
    │
    └───test
        └───java
            └───com
                └───galvanize
                    └───GMDB
                        │   GmdbApplicationTests.java
                        │
                        └───integration
                                MovieControllerTest.java
                                RatingControllerTest.java



```

</details>


## Developers

**Ravi Chellappa**

- <https://github.com/rchellappacog>

**Zxander Rodriguez**

- <https://github.com/zxander-D>

**Mohammad Javed**

- <https://github.com/gajjuCoderBoi>
