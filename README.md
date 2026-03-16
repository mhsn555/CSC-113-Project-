# CSC 113 Project - Phase 1

This repository contains Phase 1 of our project for the `CSC 113` course at `KSU`.

The project was done by:

- `Abdulmohsen Alahmary`
- `Alfarouq Alshukri`

Instructor:

- `Khaja Mustafa`

## Project Overview

This project implements a console-based school management system in Java. The system models the main parts of a school environment using object-oriented programming concepts we took throughout the course such as:

- composition and aggregation
- encapsulation
- inheritance
- abstraction
- interfaces
- recursion


The program manages people and school resources, including:

- students
- teachers
- employees
- admin staff
- classrooms
- clubs

## What The Project Does

The system allows a school to:

- add and manage people in the school
- add and manage classrooms
- add and manage clubs
- search for people, classrooms, and clubs
- count students recursively
- calculate monthly school-related amounts such as student fees and staff salaries

The interactive `SchoolTest` program also provides role-based menus for:

- students to view their information and joined clubs
- teachers to manage students in their supervised clubs
- admin staff to display school data and remove clubs
- employees to calculate totals and update salary or fee information

## Main Classes

- `Person`: abstract base class for all people in the school
- `Student`: represents students and their club memberships
- `Employee`: represents school employees and salary information
- `Teacher`: specialized employee who supervises clubs
- `AdminStaff`: specialized employee for administrative roles
- `School`: central class that stores and manages the whole system
- `Club`: represents student clubs and their members
- `Classroom`: represents classrooms in the school
- `Payable`: interface for classes that calculate monthly amounts

## UML Diagram

The following UML diagram shows the structure of the project:

![Project UML Diagram](UML.png)

## How To Run

Compile the project:

```bash
javac *.java
```

Run the main program:

```bash
java SchoolTest
```

## Phase 1 Scope

This repository represents the first phase of the project. The focus of this phase is building the core object-oriented design and a working console application for managing a school system.
