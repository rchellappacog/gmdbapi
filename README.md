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
    * if don't have Gradle install from [here](https://gradle.org/install/)
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
            <a href="#">GET</a>
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
            <a href="#">POST</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Create a Movie Into the Database</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>MovieController -> createMovie()</p>
        </td>
    </tr>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;" rowspan="2">
            <a href="#summary">/movies/{movieName}</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#">GET</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Get Movie by Movie Name</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>MovieController -> getMovie()</p>
        </td>
    </tr>
    </tbody>
</table>

## What's included

Within the download you'll find the following directories and files, logically grouping common assets and providing both compiled and minified variations. You'll see something like this:

<details>
<summary>
File Tree.
</summary>


```text

└───src
    ├───main
    │   ├───generated
    │   ├───java
    │   │   └───com
    │   │       └───galvanize
    │   │           └───GMDB
    │   │               ├───Controller
    │   │               ├───Pojo
    │   │               ├───request
    │   │               ├───response
    │   │               └───Service
    │   └───resources
    └───test
        └───java
            └───com
                └───galvanize
                    └───GMDB
                        └───integration

```

</details>


## Developers

**Ravi Chellappa**

- <https://github.com/rchellappacog>

**Zxander Rodriguez**

- <https://github.com/zxander-D>

**Mohammad Javed**

- <https://github.com/gajjuCoderBoi>
