# Book Browser Application

## Overview

The **Book Browser Application** is a Java-based GUI application for browsing and managing a collection of books. It demonstrates the use of Java Swing for GUI development and a custom TreeMap for data management.

## Features

- **Book Management**: Add, view, and search books by attributes like ISBN, authors, and more.
- **Custom TreeMap**: Efficient storage and retrieval of book data using a custom TreeMap.
- **Sorting**: Sort books by ISBN, authors, year, etc.
- **GUI Interface**: User-friendly interface built with Java Swing.

## Structure

- **Book**: Represents a book with various attributes and provides methods to access them.
- **BookBrowser**: The main GUI class; initializes components, loads books, and manages navigation.
- **SearchTree**: Generic binary search tree used for ordering books.
- **TreeMap**: Custom TreeMap implementation for key-value storage.
- **TreeMapInterface**: Defines methods for TreeMap functionality.

## Installation

1. **Clone the Repo**: `git clone <repository_url>`
2. **Build the Project**: `javac *.java`
3. **Run the Application**: `java BookBrowser`

## Usage

1. **Load Books**: Ensure `BooksDataFile.txt` is in the directory.
2. **Navigate**: Use the GUI buttons to browse books.
3. **Sort**: Select a sort criterion (e.g., ISBN, authors) from the dropdown menu.
4. **View Details**: Book details appear in the respective fields.

## Custom TreeMap

The application uses a custom `TreeMap` for efficient data storage. The TreeMap supports adding, retrieving, and clearing data, as well as converting keys and values to arrays.

## Testing

JUnit tests in `TreeMapTest` ensure the correctness of the TreeMap implementation.

## Future Enhancements

- **Search Functionality**: Implement in-GUI search for books.
- **Data Export**: Enable exporting book lists to a file.
- **Book Editing**: Allow editing book details directly in the GUI.

## License

This project is for educational purposes as part of a Computer Programming 2 course.

## Author

Developed by Samuel Awud for Computer Programming 2.
