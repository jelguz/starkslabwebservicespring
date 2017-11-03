package com.finastra.starkslab.webservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.starkslab.webservice.model.Person;
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/persons")
public class PersonsController {
	
	@RequestMapping(value = "/get/{personId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Person getPerson(@PathVariable("personId") String personId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"SELECT * "
				+ "FROM persons "
				+ "WHERE id = ?");
		preparedStatement.setString(1, personId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Person person = new Person();
			person.setId(resultSet.getString("id"));
			person.setLastName(resultSet.getString("last_name"));
			person.setFirstName(resultSet.getString("first_name"));
			person.setMiddleName(resultSet.getString("middle_name"));
			connection.close();
			connection = null;
			return person;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CrossOrigin(origins="*")
	public boolean addPerson(@RequestBody Person person) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionProvider.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO persons "
					+ "(id, last_name, first_name, middle_name) "
					+ "VALUES(?, ?, ?, ?)");
			preparedStatement.setString(1, person.getId());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getFirstName());
			preparedStatement.setString(4, person.getMiddleName());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}
}
