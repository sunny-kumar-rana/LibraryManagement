# Library Management System (Core Java)

A **console-based Library Management System** built using **Core Java and Object-Oriented Programming** concepts.
The application simulates basic library operations such as managing books, registering members, and handling book borrowing and returns.

## Features

* Add and remove books
* Support for different book types (Physical Book, EBook)
* Member registration
* Borrow and return books
* Search books by title
* Display all available books

## Tech Used

* Java (Core Java)
* OOP (Abstraction, Inheritance, Encapsulation)
* Collections (`ArrayList`)
* Exception Handling
* Console-based UI

## Project Structure

```text
books
 ├─ Book (abstract)
 ├─ PhysicalBook
 └─ EBook

member
 └─ Member

library
 └─ Library

main
 └─ Main
```

## Example Flow

```
Add Book → Register Member → Borrow Book → Return Book → Search Book
```

## Run

Compile and run `Main.java`:

```bash
javac Main.java
java Main
```

The application starts a menu-driven interface where users can manage library operations.

## Purpose

This project demonstrates:

* Object-oriented design with inheritance and abstraction
* Managing application state using collections
* Basic system architecture for real-world applications
* Handling user input and exceptions in Java
