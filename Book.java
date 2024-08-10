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
            System.out.println("1. thêm sách");
            System.out.println("2. tìm sách theo ISBN");
            System.out.println("3. xửa sách theo ISBN");
            System.out.println("4. xóa sách theo ISBN");
            System.out.println("5. danh sách theo Author");
            System.out.println("6. sắp sếp sách the Year Published");
            System.out.println("7. toàn bộ sách");
            System.out.println("8. thoát");
            System.out.print("chọn chức năng: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("nhập ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("nhập Title: ");
                    String title = scanner.nextLine();
                    System.out.print("nhập Author: ");
                    String author = scanner.nextLine();
                    System.out.print("nhập Year Published: ");
                    int yearPublished = scanner.nextInt();
                    library.addBook(new Book(isbn, title, author, yearPublished));
                    break;
                case 2:
                    System.out.print("nhập ISBN: ");
                    isbn = scanner.nextLine();
                    Book foundBook = library.searchBookByIsbn(isbn);
                    if (foundBook != null) {
                        System.out.println("sách: " + foundBook);
                    } else {
                        System.out.println("không tìm thấy sách.");
                    }
                    break;
                case 3:
                    System.out.print("nhập ISBN của sách cần sửa: ");
                    isbn = scanner.nextLine();
                    System.out.print("nhập mới Title: ");
                    title = scanner.nextLine();
                    System.out.print("nhập mới Author: ");
                    author = scanner.nextLine();
                    System.out.print("nhập mới Year Published: ");
                    yearPublished = scanner.nextInt();
                    if (library.updateBookByIsbn(isbn, title, author, yearPublished)) {
                        System.out.println("đã sửa sách.");
                    } else {
                        System.out.println("không tìm thấy sách.");
                    }
                    break;
                case 4:
                    System.out.print("nhập ISBN của sách cần xóa: ");
                    isbn = scanner.nextLine();
                    if (library.removeBookByIsbn(isbn)) {
                        System.out.println("đã xóa sách.");
                    } else {
                        System.out.println("không tìm thấy.");
                    }
                    break;
                case 5:
                    System.out.print("nhập tên Author: ");
                    author = scanner.nextLine();
                    List<Book> booksByAuthor = library.listBooksByAuthor(author);
                    if (!booksByAuthor.isEmpty()) {
                        System.out.println("sách của " + author + ":");
                        for (Book book : booksByAuthor) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("không có sách của author này.");
                    }
                    break;
                case 6:
                    library.sortBooksByYearPublished();
                    System.out.println("sắp xếp theo year published.");
                    break;
                case 7:
                    library.listAllBooks();
                    break;
                case 8:
                    System.out.println("đang thoát...");
                    scanner.close();
                    return;
                default:
                    System.out.println("không có chức năng này. hãy thử lại.");
            }
        }
    }
}
