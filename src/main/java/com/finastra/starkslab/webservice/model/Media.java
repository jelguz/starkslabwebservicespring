package com.finastra.starkslab.webservice.model;

public class Media {
	public static final byte TYPE_PICTURE = 1;
	public static final byte TYPE_MOVIE = 2;
	private int id;
	private byte type;
	private String link;
	private String description;

	public Media() {
		super();
	}

	public Media(int id, byte type, String link, String description) {
		super();
		this.id = id;
		this.type = type;
		this.link = link;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
