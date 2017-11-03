package com.finastra.starkslab.webservice.model;

public class Icon {
	private String smallSizeLink;
	private String normalSizeLink;
	private String largeSizeLink;

	public Icon() {
		super();
	}

	public Icon(String smallSizeLink, String normalSizeLink, String largeSizeLink) {
		super();
		this.smallSizeLink = smallSizeLink;
		this.normalSizeLink = normalSizeLink;
		this.largeSizeLink = largeSizeLink;
	}

	public String getSmallSizeLink() {
		return smallSizeLink;
	}

	public void setSmallSizeLink(String smallSizeLink) {
		this.smallSizeLink = smallSizeLink;
	}

	public String getNormalSizeLink() {
		return normalSizeLink;
	}

	public void setNormalSizeLink(String normalSizeLink) {
		this.normalSizeLink = normalSizeLink;
	}

	public String getLargeSizeLink() {
		return largeSizeLink;
	}

	public void setLargeSizeLink(String largeSizeLink) {
		this.largeSizeLink = largeSizeLink;
	}
}
