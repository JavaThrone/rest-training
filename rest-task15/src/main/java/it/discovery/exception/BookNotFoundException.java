package it.discovery.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int bookId) {
        super(String.format("Book %s not found", bookId));
    }
}
