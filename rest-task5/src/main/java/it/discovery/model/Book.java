package it.discovery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {
	private int id;

	@NotNull
	private String author;

	@JsonProperty("title")
	private String name;

	@Min(1900)
	private int year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", author='" + author + '\'' +
				", name='" + name + '\'' +
				", year=" + year +
				'}';
	}
}
