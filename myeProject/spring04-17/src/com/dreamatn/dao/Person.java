package com.dreamatn.dao;

public class Person {

	private String name;
	private int age;
	private Book book;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", book=" + book + "]";
	}

}
