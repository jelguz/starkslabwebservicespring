package com.finastra.starkslab.webservice.model;

import java.util.Date;
import java.util.List;

public class Tool {
	public static final byte RATING_ONESTAR = 1;
	public static final byte RATING_TWOSTAR = 2;
	public static final byte RATING_THREESTAR = 3;
	public static final byte RATING_FOURSTAR = 4;
	public static final byte RATING_FIVESTAR = 5;

	private int id;
	private String name;
	private String description;
	private Icon icon;
	private String text;
	private List<Person> developers;
	private Person wishMaster;
	private List<Person> wishers;
	private List<Category> categories;
	private List<Review> reviews;
	private float rating;
	private Date launchDate;
	private Date updateDate;
	private Version currentVersion;
	private List<Version> versions;
	private List<Media> medias;
	private String tags[];
	private int downloads;
	private String categoryString;
	private String tagsString;


	public Tool() {
	}

	public Tool(int id, String name, String description, Icon icon, String text, List<Person> developers,
			Person wishMaster, List<Person> wishers, List<Category> categories, List<Review> reviews, float rating, Date launchDate,
			Date updateDate, Version currentVersion, List<Version> versions, List<Media> medias, String[] tags, int downloads) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.text = text;
		this.developers = developers;
		this.wishMaster = wishMaster;
		this.wishers = wishers;
		this.categories = categories;
		this.reviews = reviews;
		this.rating = rating;
		this.launchDate = launchDate;
		this.updateDate = updateDate;
		this.currentVersion = currentVersion;
		this.versions = versions;
		this.medias = medias;
		this.tags = tags;
		this.downloads = downloads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Person> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<Person> developers) {
		this.developers = developers;
	}

	public Person getWishMaster() {
		return wishMaster;
	}

	public void setWishMaster(Person wishMaster) {
		this.wishMaster = wishMaster;
	}

	public List<Person> getWishers() {
		return wishers;
	}

	public void setWishers(List<Person> wishers) {
		this.wishers = wishers;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Version getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	
	public void setCategoryString() {	/* Special Functions ~ Mark */
		categoryString = "";
		if (categories != null)
			for (Category category : categories){
				categoryString = categoryString + category.name + ',';
			}	
	}

	public String getCategoryString(){    /* Special Functions ~ Mark */
		return categoryString;
	}
	
	public void setTagsString() {	/* Special Functions ~ Mark */
		tagsString = "";
		if (tags != null)
			for (String tag : tags){
				tagsString = tagsString + tag + ',';
			}	
	}

	public String getTagsString(){    /* Special Functions ~ Mark */
		return tagsString;
	}
	
}
