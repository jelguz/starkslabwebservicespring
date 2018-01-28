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

import com.finastra.starkslab.webservice.model.Notification;
import com.finastra.starkslab.webservice.model.Tool;
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/activity")
public class ActivityController {
	
	@RequestMapping(value = "/notification/seen/{username}/{newsfeedId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public boolean setNotificationSeen(@PathVariable("username") String username, @PathVariable("newsfeedId") String newsfeedId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionProvider.getConnection();
			preparedStatement = connection.prepareStatement(
					"update notifications set is_seen = '1' where person_id = ? and newsfeed_id = ?");
			preparedStatement.setString(1, username.toLowerCase());
			preparedStatement.setString(2, newsfeedId.toLowerCase());
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
	
	@RequestMapping(value = "/notifications/{username}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Notification> getUserNotifications(@PathVariable("username") String username) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"SELECT newsfeed_id, developer, is_seen, type, privacy, "
				+ "tool_id, tool_name, originator, last_name, first_name, "
				+ "timestamp, rating, text "
				+ "FROM view_notifications "
				+ "WHERE developer = ?"
				+ "ORDER BY timestamp DESC ");
		preparedStatement.setString(1, username.toLowerCase());
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Notification> notifications = new ArrayList<Notification>();
			Notification notification = new Notification();
			notification.setNewsfeedId(resultSet.getInt(1));
			notification.setDeveloper(resultSet.getString(2));
			notification.setIsSeen(resultSet.getInt(3));
			notification.setType(resultSet.getString(4));
			notification.setPrivacy(resultSet.getString(5));
			notification.setToolId(resultSet.getString(6));
			notification.setToolName(resultSet.getString(7));
			notification.setOriginator(resultSet.getString(8));
			notification.setOriginatorLastName(resultSet.getString(9));
			notification.setOriginatorFirstName(resultSet.getString(10));
			notification.setActivityDate(resultSet.getString(11));
			notification.setRating(resultSet.getString(12));
			notification.setText(resultSet.getString(13));
			notifications.add(notification);

			while (resultSet.next()) {
				notification = new Notification();
				notification.setNewsfeedId(resultSet.getInt(1));
				notification.setDeveloper(resultSet.getString(2));
				notification.setIsSeen(resultSet.getInt(3));
				notification.setType(resultSet.getString(4));
				notification.setPrivacy(resultSet.getString(5));
				notification.setToolId(resultSet.getString(6));
				notification.setToolName(resultSet.getString(7));
				notification.setOriginator(resultSet.getString(8));
				notification.setOriginatorLastName(resultSet.getString(9));
				notification.setOriginatorFirstName(resultSet.getString(10));
				notification.setActivityDate(resultSet.getString(11));
				notification.setRating(resultSet.getString(12));
				notification.setText(resultSet.getString(13));
				notifications.add(notification);
			}
			connection.close();
			connection = null;
			return notifications;
		}
		connection.close();
		connection = null;
		return null;
	}
	
}
