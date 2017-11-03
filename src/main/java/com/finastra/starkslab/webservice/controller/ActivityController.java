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
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/activity")
public class ActivityController {
	
	private static final int ACTION_TYPE_DOWNLOAD = 1;
	private static final int ACTION_TYPE_REVIEW = 2;
	
	@RequestMapping(value = "/notifications/{username}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Notification> getUserNotifications(@PathVariable("username") String username) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"SELECT newsfeed_id, person_id, is_seen, timestamp FROM notifications "
				+ "WHERE person_id = ? AND is_seen = '0' "
				+ "ORDER BY timestamp DESC ");
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Notification> notifications = new ArrayList<Notification>();
			Notification notification = new Notification();
			notification.setNewsfeedId(resultSet.getString(1));
			notification.setPersonId(resultSet.getString(2));
			notification.setIsSeen(resultSet.getInt(3));
			notification.setTimestamp(resultSet.getString(4));
			notifications.add(notification);

			while (resultSet.next()) {
				notification = new Notification();
				notification.setNewsfeedId(resultSet.getString(1));
				notification.setPersonId(resultSet.getString(2));
				notification.setIsSeen(resultSet.getInt(3));
				notification.setTimestamp(resultSet.getString(4));
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
