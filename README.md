# CSC 113 Project - Phase 1 & 2

This repository contains Phase 1 & 2 of our project for the `CSC 113` course at `KSU`.

The project was done by:

- `Abdulmohsen Alahmary`
- `Alfarouq Alshukri`

Instructor:

- `Khaja Mustafa`

## Project Overview

This project implements a school management system in Java with both a Swing GUI and a console mode. The system models the main parts of a school environment using object-oriented programming concepts we took throughout the course such as:

- composition and aggregation
- encapsulation
- inheritance
- abstraction
- interfaces
- recursion
- custom generic linked lists


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
- GUI users to enter login information in one frame and view results in another frame
- save entered login information to the text file `user_input.txt` and read it back from the GUI or admin menu

## Main Classes

- `Person`: abstract base class for all people in the school
- `Student`: represents students and their club memberships
- `Employee`: represents school employees and salary information
- `Teacher`: specialized employee who supervises clubs
- `AdminStaff`: specialized employee for administrative roles
- `School`: central class that stores and manages the whole system
- `Club`: represents student clubs and their members
- `Classroom`: represents classrooms in the school
- `CustomLinkedList`: generic linked list implementation used instead of Java predefined linked lists
- `SchoolGUI`: Swing user interface with separate input and result frames
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

Run the old console menu instead:

```bash
java SchoolTest console
```

## Phase 1 Scope

This repository represents the first phase of the project. The focus of this phase is building the core object-oriented design and a working application for managing a school system.

## Phase 2 Scope

Phase 2 adds the required exception handling, file handling, GUI, and custom data structure updates.

The system now meets the Phase 2 requirements in these ways:

- Checked exception handling: `ClubFullException` is a checked, user-defined exception used when a club has reached its maximum size. It is thrown from the club logic and handled by the calling menu methods. File methods also use checked exceptions such as `IOException`, `FileNotFoundException`, and `EOFException`.
- Unchecked exception handling: invalid user input is handled with unchecked exceptions such as `InputMismatchException` and `IllegalArgumentException`. These are caught in the same methods where the wrong input is read or validated.
- User-defined exception: `ClubFullException` supports the school design because clubs have limited capacity, so the system needs a clear exception when adding a student would exceed the club limit.
- File saving and reading: the system saves entered login information into the text file `user_input.txt` and can read it back. It also saves login-count information into `login.txt`.
- GUI frames: `SchoolGUI` creates two Swing frames. The first frame accepts user input, and the second frame displays login results or saved file contents.
- GUI action events: the GUI buttons use action listeners. The `Log in` button validates and saves input, while the `Read saved input` button reads previous input from the file and displays it.
- Custom linked list: predefined Java linked lists were removed. `CustomLinkedList<T>` is a generic linked list implemented in the project and used by `School`, `Student`, and `Club`.

### GUI Behavior

Running `java SchoolTest` opens the Swing interface. The user selects a role, enters a name and ID, then clicks `Log in`.

If the name, ID, and role match a person stored in the school system, the result frame shows a successful login message with that person's information. The login count is updated and saved to `login.txt`.

Whether the login succeeds or fails, the entered role, name, and ID are saved to `user_input.txt`. The user can click `Read saved input` to display the saved file contents in the result frame.

The original console menus are still available by running `java SchoolTest console`.
