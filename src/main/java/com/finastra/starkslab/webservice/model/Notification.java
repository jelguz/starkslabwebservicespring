package com.finastra.starkslab.webservice.model;

public class Notification {
	private int newsfeedId;
	private String developer;
	private int isSeen;
	private String type;
	private String privacy;
	private String toolId;
	private String toolName;
	private String originator;
	private String originatorFirstName;
	private String originatorLastName;
	private String activityDate;
	private String rating;
	private String text;

	public Notification() {
	}
	
	public Notification(int newsfeedId, String developer, int isSeen, String type, String privacy, String toolId,
			String toolName, String originator, String originatorFirstName, String originatorLastName,
			String activityDate, String rating, String text) {
		super();
		this.newsfeedId = newsfeedId;
		this.developer = developer;
		this.isSeen = isSeen;
		this.type = type;
		this.privacy = privacy;
		this.toolId = toolId;
		this.toolName = toolName;
		this.originator = originator;
		this.originatorFirstName = originatorFirstName;
		this.originatorLastName = originatorLastName;
		this.activityDate = activityDate;
		this.rating = rating;
		this.text = text;
	}

	public int getNewsfeedId() {
		return newsfeedId;
	}

	public void setNewsfeedId(int newsfeedId) {
		this.newsfeedId = newsfeedId;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(int isSeen) {
		this.isSeen = isSeen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getOriginatorFirstName() {
		return originatorFirstName;
	}

	public void setOriginatorFirstName(String originatorFirstName) {
		this.originatorFirstName = originatorFirstName;
	}

	public String getOriginatorLastName() {
		return originatorLastName;
	}

	public void setOriginatorLastName(String originatorLastName) {
		this.originatorLastName = originatorLastName;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
