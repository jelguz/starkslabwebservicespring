package com.finastra.starkslab.webservice.model;

public class ReviewRequest {
	public static final byte RATING_ONESTAR = 1;
	public static final byte RATING_TWOSTAR = 2;
	public static final byte RATING_THREESTAR = 3;
	public static final byte RATING_FOURSTAR = 4;
	public static final byte RATING_FIVESTAR = 5;
	
	private int id;
	private String personId;
	private byte rating;
	private String text;

	public ReviewRequest() {
	}

	public ReviewRequest(int id, String personId, byte rating, String text) {
		super();
		this.id = id;
		this.personId = personId;
		this.rating = rating;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
