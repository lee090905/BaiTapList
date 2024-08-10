package day10.BaiTapList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int yearPublished;

    public Book(String isbn, String title, String author, int yearPublished) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBookByIsbn(String isbn, String newTitle, String newAuthor, int newYearPublished) {
        Book book = searchBookByIsbn(isbn);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setYearPublished(newYearPublished);
            return true;
        }
        return false;
    }

    public boolean removeBookByIsbn(String isbn) {
        Book book = searchBookByIsbn(isbn);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public List<Book> listBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public void sortBooksByYearPublished() {
        Collections.sort(books, Comparator.comparingInt(Book::getYearPublished));
    }

    public void listAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by ISBN");
            System.out.println("3. Update Book by ISBN");
            System.out.println("4. Remove Book by ISBN");
            System.out.println("5. List Books by Author");
            System.out.println("6. Sort Books by Year Published");
            System.out.println("7. List All Books");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Year Published: ");
                    int yearPublished = scanner.nextInt();
                    library.addBook(new Book(isbn, title, author, yearPublished));
                    break;
                case 2:
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.nextLine();
                    Book foundBook = library.searchBookByIsbn(isbn);
                    if (foundBook != null) {
                        System.out.println("Book Found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to update: ");
                    isbn = scanner.nextLine();
                    System.out.print("Enter new Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new Author: ");
                    author = scanner.nextLine();
                    System.out.print("Enter new Year Published: ");
                    yearPublished = scanner.nextInt();
                    if (library.updateBookByIsbn(isbn, title, author, yearPublished)) {
                        System.out.println("Book updated successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ISBN of the book to remove: ");
                    isbn = scanner.nextLine();
                    if (library.removeBookByIsbn(isbn)) {
                        System.out.println("Book removed successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Author's name: ");
                    author = scanner.nextLine();
                    List<Book> booksByAuthor = library.listBooksByAuthor(author);
                    if (!booksByAuthor.isEmpty()) {
                        System.out.println("Books by " + author + ":");
                        for (Book book : booksByAuthor) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("No books found by this author.");
                    }
                    break;
                case 6:
                    library.sortBooksByYearPublished();
                    System.out.println("Books sorted by year published.");
                    break;
                case 7:
                    library.listAllBooks();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
