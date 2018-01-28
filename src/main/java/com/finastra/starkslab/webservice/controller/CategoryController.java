package com.finastra.starkslab.webservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.starkslab.webservice.model.Category;
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

	@RequestMapping(value = "/get/list", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Category> getCategories() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description "
				+ "from categories");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Category> categories = new ArrayList<Category>();
			Category category = new Category();
			category.setId(resultSet.getInt(1));
			category.setName(resultSet.getString(2));
			category.setDescription(resultSet.getString(3));
			categories.add(category);
			while (resultSet.next()) {
				category = new Category();
				category.setId(resultSet.getInt(1));
				category.setName(resultSet.getString(2));
				category.setDescription(resultSet.getString(3));
				categories.add(category);
			}
			connection.close();
			connection = null;
			return categories;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/get/listdetails/{status}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Category> getCategoriesDetails(@PathVariable("status") String status) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description "
				+ "from categories");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Category> categories = new ArrayList<Category>();
			Category category = new Category();
			category.setId(resultSet.getInt(1));
			category.setName(resultSet.getString(2));
			category.setDescription(resultSet.getString(3));
			category.setToolCount(getCategoriesToolsCountStatus(category.getId(), status));
			categories.add(category);
			while (resultSet.next()) {
				category = new Category();
				category.setId(resultSet.getInt(1));
				category.setName(resultSet.getString(2));
				category.setDescription(resultSet.getString(3));
				category.setToolCount(getCategoriesToolsCountStatus(category.getId(), status));
				categories.add(category);
			}
			connection.close();
			connection = null;
			return categories;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	public int getCategoriesToolsCount(int categoryId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select count(*) from tool_categories where category_id = ?");
		preparedStatement.setInt(1, categoryId);
		ResultSet resultSet = preparedStatement.executeQuery();
		int count = 0;
		if (resultSet.next()) {
			count =  resultSet.getInt(1);
			connection.close();
			connection = null;
		}
		//connection.close();
		//connection = null;
		return count;
	}
	
	public int getCategoriesToolsCountStatus(int categoryId, String status) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select count(*) from tool_categories a inner join tools b " +
		        "on a.tool_id = b.id "+
				"where a.category_id = ? and b.status = ?");
		preparedStatement.setInt(1, categoryId);
		preparedStatement.setString(2, status);
		ResultSet resultSet = preparedStatement.executeQuery();
		int count = 0;
		if (resultSet.next()) {
			count =  resultSet.getInt(1);
			connection.close();
			connection = null;
		}
		//connection.close();
		//connection = null;
		return count;
	}
}
