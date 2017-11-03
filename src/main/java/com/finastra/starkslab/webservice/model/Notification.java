package com.finastra.starkslab.webservice.model;

public class Notification {
	private String newsfeedId;
	private String personId;
	private int isSeen;
	private String timestamp;

	public Notification() {
	}

	public Notification(String newsfeedId, String personId, int isSeen, String timestamp) {
		this.newsfeedId = newsfeedId;
		this.personId = personId;
		this.isSeen = isSeen;
		this.timestamp = timestamp;
	}

	public String getNewsfeedId() {
		return newsfeedId;
	}

	public void setNewsfeedId(String newsfeedId) {
		this.newsfeedId = newsfeedId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public int getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(int isSeen) {
		this.isSeen = isSeen;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
