package com.finastra.starkslab.webservice.model;

public class Version {
	private String prefix;
	private int major;
	private int minor;
	private int built;
	private int revision;
	private String downloadLink;

	public Version() {
	}

	public Version(String prefix, int major, int minor, int built, int revision, String downloadLink) {
		super();
		this.prefix = prefix;
		this.major = major;
		this.minor = minor;
		this.built = built;
		this.revision = revision;
		this.downloadLink = downloadLink;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getBuilt() {
		return built;
	}

	public void setBuilt(int built) {
		this.built = built;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
}
