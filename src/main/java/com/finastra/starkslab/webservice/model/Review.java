package com.finastra.starkslab.webservice.model;

import java.util.Date;

public class Review {
	public static final byte RATING_ONESTAR = 1;
	public static final byte RATING_TWOSTAR = 2;
	public static final byte RATING_THREESTAR = 3;
	public static final byte RATING_FOURSTAR = 4;
	public static final byte RATING_FIVESTAR = 5;
	
	private int id;
	private Person person;
	private Date date;
	private float rating;
	private String text;

	public Review() {
	}

	public Review(int id, Person person, Date date, byte rating, String text) {
		super();
		this.id = id;
		this.person = person;
		this.date = date;
		this.rating = rating;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getUser() {
		return person;
	}

	public void setUser(Person person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
