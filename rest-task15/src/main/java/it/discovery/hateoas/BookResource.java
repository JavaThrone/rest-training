package it.discovery.hateoas;

import it.discovery.controller.BookController;
import it.discovery.model.Book;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookResource extends ResourceSupport {
    private int id;

    private String author;

    private String name;

    private int year;

    private boolean rented;

    public BookResource(Book book) {
        id = book.getId();
        author = book.getAuthor();
        name = book.getName();
        year = book.getYear();
        rented = book.isRented();
        add(linkTo(methodOn(BookController.class)
                .findById(id)).withSelfRel());
        if (!rented) {
            add(linkTo(methodOn(BookController.class)
                    .rent(id)).withRel("rent"));
        }

    }
}
