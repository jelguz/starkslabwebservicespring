package com.finastra.starkslab.webservice.model;

public class ToolCategory {
	private int toolId;
	private int categoryId;
	
	public ToolCategory() {
	}
	
	public ToolCategory(int toolId, int categoryId) {
		super();
		this.toolId = toolId;
		this.categoryId = categoryId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
