package com.finastra.starkslab.webservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.starkslab.webservice.model.Category;
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

	@RequestMapping(value = "/get/list", method = RequestMethod.GET)
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
}
