package com.finastra.starkslab.webservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.starkslab.webservice.model.Category;
import com.finastra.starkslab.webservice.model.Icon;
import com.finastra.starkslab.webservice.model.Media;
import com.finastra.starkslab.webservice.model.ToolMaxPage;
import com.finastra.starkslab.webservice.model.Person;
import com.finastra.starkslab.webservice.model.Review;
import com.finastra.starkslab.webservice.model.ReviewRequest;
import com.finastra.starkslab.webservice.model.Tool;
import com.finastra.starkslab.webservice.model.Version;
import com.finastra.starkslab.webservice.provider.ConnectionProvider;

@RestController
@RequestMapping("/rest/tools")
public class ToolController {

	@RequestMapping(value = "/get/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Tool getTool(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from view_tools_001 "
				+ "where id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Tool tool = new Tool();
			tool.setId(resultSet.getInt(1));
			tool.setName(resultSet.getString(2));
			tool.setDescription(resultSet.getString(3));
			tool.setText(resultSet.getString(4));
			tool.setLaunchDate(resultSet.getDate(5));
			tool.setUpdateDate(resultSet.getDate(6));
			tool.setDownloads(resultSet.getInt(7));
			tool.setRating(resultSet.getInt(8));
			tool.setIcon(getIcon(toolId));
			tool.setDevelopers(getDevelopers(toolId));
			tool.setWishMaster(getWishMaster(toolId));
			tool.setWishers(getWishers(toolId));
			tool.setCategories(getCategories(toolId));
			tool.setReviews(getReviews(toolId));
			tool.setCurrentVersion(getCurrentVersion(toolId));
			tool.setVersions(getVersions(toolId));
			tool.setMedias(getMedias(toolId));
			tool.setTags(getTags(toolId));
			connection.close();
			connection = null;
			return tool;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/icon/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Icon getIcon(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select small_size_link, normal_size_link, large_size_link "
				+ "from tool_icons "
				+ "where tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Icon icon = new Icon();
			icon.setSmallSizeLink(resultSet.getString(1));
			icon.setNormalSizeLink(resultSet.getString(2));
			icon.setLargeSizeLink(resultSet.getString(3));
			connection.close();
			connection = null;
			return icon;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/developers/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Person> getDevelopers(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select b.id, b.last_name, b.first_name, b.middle_name "
				+ "from tool_developers a inner join persons b on a.person_id = b.id "
				+ "where a.tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Person> persons = new ArrayList<Person>();
			Person person = new Person();
			person.setId(resultSet.getString(1));
			person.setLastName(resultSet.getString(2));
			person.setFirstName(resultSet.getString(3));
			person.setMiddleName(resultSet.getString(4));
			persons.add(person);
			while (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getString(1));
				person.setLastName(resultSet.getString(2));
				person.setFirstName(resultSet.getString(3));
				person.setMiddleName(resultSet.getString(4));
				persons.add(person);
			}
			connection.close();
			connection = null;
			return persons;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/wishmaster/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Person getWishMaster(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select b.id, b.last_name, b.first_name, b.middle_name "
				+ "from tool_wishers a inner join persons b on a.person_id = b.id "
				+ "where a.tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Person person = new Person();
			person.setId(resultSet.getString(1));
			person.setLastName(resultSet.getString(2));
			person.setFirstName(resultSet.getString(3));
			person.setMiddleName(resultSet.getString(4));
			connection.close();
			connection = null;
			return person;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/wishers/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Person> getWishers(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select b.id, b.last_name, b.first_name, b.middle_name "
				+ "from tool_wishers a inner join persons b on a.person_id = b.id "
				+ "where a.tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Person> persons = new ArrayList<Person>();
			Person person = new Person();
			person.setId(resultSet.getString(1));
			person.setLastName(resultSet.getString(2));
			person.setFirstName(resultSet.getString(3));
			person.setMiddleName(resultSet.getString(4));
			persons.add(person);
			while (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getString(1));
				person.setLastName(resultSet.getString(2));
				person.setFirstName(resultSet.getString(3));
				person.setMiddleName(resultSet.getString(4));
				persons.add(person);
			}
			connection.close();
			connection = null;
			return persons;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/categories/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Category> getCategories(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select b.id, b.name, b.description "
				+ "from tool_categories a inner join categories b on a.category_id = b.id "
				+ "where a.tool_id = ?");
		preparedStatement.setInt(1, toolId);
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

	@RequestMapping(value = "/get/reviews/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Review> getReviews(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select a.id, a.person_id, b.last_name, b.first_name, b.middle_name, a.timestamp, a.rating, a.text "
				+ "from tool_reviews a left join persons b on a.person_id = b.id "
				+ "where a.tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Review> reviews = new ArrayList<Review>();
			Review review = new Review();
			Person person = new Person();
			review.setId(resultSet.getInt(1));
			person.setId(resultSet.getString(2));
			person.setLastName(resultSet.getString(3));
			person.setFirstName(resultSet.getString(4));
			person.setMiddleName(resultSet.getString(5));
			review.setUser(person);
			review.setDate(resultSet.getDate(6));
			review.setRating(resultSet.getByte(7));
			review.setText(resultSet.getString(8));
			reviews.add(review);
			while (resultSet.next()) {
				review = new Review();
				person = new Person();
				review.setId(resultSet.getInt(1));
				person.setId(resultSet.getString(2));
				person.setLastName(resultSet.getString(3));
				person.setFirstName(resultSet.getString(4));
				person.setMiddleName(resultSet.getString(5));
				review.setUser(person);
				review.setDate(resultSet.getDate(6));
				review.setRating(resultSet.getByte(7));
				review.setText(resultSet.getString(8));
				reviews.add(review);
			}
			connection.close();
			connection = null;
			return reviews;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "get/review/{toolId}/{personId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Review getReview(@PathVariable("toolId") String toolId, @PathVariable("personId") String personId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"SELECT * "
				+ "FROM tool_reviews "
				+ "WHERE tool_id = ? AND person_id = ?");
		preparedStatement.setString(1, toolId);
		preparedStatement.setString(2, personId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Review review = new Review();
			review.setId(resultSet.getInt("id"));
			review.setDate(resultSet.getTimestamp("timestamp"));
			review.setRating(resultSet.getByte("rating"));
			review.setText(resultSet.getString("text"));
			
			Person person = new Person();
			person.setId(resultSet.getString("person_id"));
			review.setUser(person);
			
			connection.close();
			connection = null;
			return review;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/currentversion/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public Version getCurrentVersion(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select prefix, major, minor, built, revision, link "
				+ "from tool_versions "
				+ "where tool_id = ? and current = 1");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Version version = new Version();
			version.setPrefix(resultSet.getString(2));
			version.setMajor(resultSet.getInt(3));
			version.setMinor(resultSet.getInt(4));
			version.setBuilt(resultSet.getInt(5));
			version.setRevision(resultSet.getInt(6));
			version.setDownloadLink(resultSet.getString(2));
			connection.close();
			connection = null;
			return version;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/get/versions/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Version> getVersions(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select prefix, major, minor, built, revision, link "
				+ "from tool_versions "
				+ "where tool_id = ?");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Version> versions = new ArrayList<Version>();
			Version version = new Version();
			version.setPrefix(resultSet.getString(2));
			version.setMajor(resultSet.getInt(3));
			version.setMinor(resultSet.getInt(4));
			version.setBuilt(resultSet.getInt(5));
			version.setRevision(resultSet.getInt(6));
			version.setDownloadLink(resultSet.getString(2));
			versions.add(version);
			while (resultSet.next()) {
				version = new Version();
				version.setPrefix(resultSet.getString(2));
				version.setMajor(resultSet.getInt(3));
				version.setMinor(resultSet.getInt(4));
				version.setBuilt(resultSet.getInt(5));
				version.setRevision(resultSet.getInt(6));
				version.setDownloadLink(resultSet.getString(2));
				versions.add(version);
			}
			connection.close();
			connection = null;
			return versions;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/get/medias/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Media> getMedias(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, type, link, description "
				+ "from tool_medias "
				+ "where tool_id = ? "
				+ "order by sort_id");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<Media> medias = new ArrayList<Media>();
			Media media = new Media();
			media.setId(resultSet.getInt(1));
			media.setType(resultSet.getByte(2));
			media.setLink(resultSet.getString(3));
			media.setDescription(resultSet.getString(4));
			medias.add(media);
			while (resultSet.next()) {
				media = new Media();
				media.setId(resultSet.getInt(1));
				media.setType(resultSet.getByte(2));
				media.setLink(resultSet.getString(3));
				media.setDescription(resultSet.getString(4));
				medias.add(media);
			}
			connection.close();
			connection = null;
			return medias;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/get/tags/{toolId}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public String[] getTags(@PathVariable("toolId") int toolId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select tag "
				+ "from tool_tags "
				+ "where tool_id = ? "
				+ "order by tag");
		preparedStatement.setInt(1, toolId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			List<String> list = new ArrayList<String>();
			list.add(resultSet.getString(1));
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			connection.close();
			connection = null;
			String[] tags = new String[list.size()];
			list.toArray(tags);			
			return tags;
		}
		connection.close();
		connection = null;
		return null;
	}
	
	@RequestMapping(value = "/get/maxpage/{count}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ToolMaxPage getMaxPage(@PathVariable("count") int count) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select count(*) record_number from tools");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			ToolMaxPage toolMaxPage = new ToolMaxPage();  
			int maxPage = resultSet.getInt(1);
			if ((maxPage % count) == 0) {
				maxPage /= count;
			} else {
				maxPage /= count;
				maxPage += 1;
			}
			toolMaxPage.setMaxPage(maxPage);
			connection.close();
			connection = null;
			return toolMaxPage;
		}
		connection.close();
		connection = null;
		return null;
	}

	@RequestMapping(value = "/get/category/{categoryId}/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsCategory(@PathVariable("categoryId") int categoryId, @PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a inner join tool_categories b on a.id = b.tool_id, (select @row_index := 0) c where b.category_id = ?) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, categoryId);
		preparedStatement.setInt(2, count);
		preparedStatement.setInt(3, page - 1);
		preparedStatement.setInt(4, count);
		preparedStatement.setInt(5, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/get/rating/desc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsRatingDescending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.rating_average desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(3, count);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/get/rating/asc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsRatingAscending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.rating_average desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(3, count);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}
	
	@RequestMapping(value = "/get/added/desc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsAddedDescending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.launch_date desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/get/added/asc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsAddedAscending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.launch_date desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(3, count);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}
	
	@RequestMapping(value = "/get/updated/desc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsUpdatedDescending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.update_date desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(3, count);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/get/updated/asc/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsUpdatedAscending(@PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from view_tools_001 a, (select @row_index := 0) b order by a.update_date desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setInt(1, count);
		preparedStatement.setInt(2, page - 1);
		preparedStatement.setInt(3, count);
		preparedStatement.setInt(4, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/get/search/{text}/{count}/{page}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<Tool> getToolsSearch(@PathVariable("text") String text, @PathVariable("count") int count, @PathVariable("page") int page) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(
				"select id, name, description, text, launch_date, update_date, download_count, rating_average "
				+ "from (select a.*, @row_index := @row_index + 1 as row_index from (select * from view_tools_001 where upper(trim(name)) like trim(concat('%', upper(trim(?)), '%'))) a, (select @row_index := 0) b order by a.rating_average desc) a "
				+ "where row_index between (? * ?) + 1 and ? * ?");
		preparedStatement.setString(1, text);
		preparedStatement.setInt(2, count);
		preparedStatement.setInt(3, page - 1);
		preparedStatement.setInt(4, count);
		preparedStatement.setInt(5, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Tool> tools = populateToolList(resultSet);
		connection.close();
		connection = null;
		return tools;
	}

	@RequestMapping(value = "/download/{tool_id}/{user_id}", method = RequestMethod.GET)
	@CrossOrigin(origins="*")
	public boolean addDownloadCount (@PathVariable("tool_id") String tool_id, @PathVariable("user_id") String user_id) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionProvider.getConnection();
			preparedStatement = connection.prepareStatement(
					"call starkslabdb.proc_add_download_count(?, ?)");
			preparedStatement.setString(1, tool_id);
			preparedStatement.setString(2, user_id);
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
	
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	@CrossOrigin(origins="*")
	public boolean addReview(@RequestBody ReviewRequest reviewRequest) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = ConnectionProvider.getConnection();
			preparedStatement = connection.prepareStatement(
					"call starkslabdb.proc_add_review(?, ?, ?, ?)");
			preparedStatement.setInt(1, reviewRequest.getId());
			preparedStatement.setString(2, reviewRequest.getPersonId());
			preparedStatement.setInt(3, reviewRequest.getRating());
			preparedStatement.setString(4, reviewRequest.getText());
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
	
	private List<Tool> populateToolList (ResultSet resultSet) throws ClassNotFoundException, SQLException {
		if (resultSet.next()) {
			List<Tool> tools = new ArrayList<Tool>();
			Tool tool = new Tool();
			tool.setId(resultSet.getInt(1));
			tool.setName(resultSet.getString(2));
			tool.setDescription(resultSet.getString(3));
			tool.setText(resultSet.getString(4));
			tool.setLaunchDate(resultSet.getDate(5));
			tool.setUpdateDate(resultSet.getDate(6));
			tool.setDownloads(resultSet.getInt(7));
			tool.setRating(resultSet.getInt(8));
			tool.setIcon(getIcon(tool.getId()));
			tool.setDevelopers(getDevelopers(tool.getId()));
			tool.setWishMaster(getWishMaster(tool.getId()));
			tool.setWishers(getWishers(tool.getId()));
			tool.setCategories(getCategories(tool.getId()));
			tool.setReviews(getReviews(tool.getId()));
			tool.setCurrentVersion(getCurrentVersion(tool.getId()));
			tool.setVersions(getVersions(tool.getId()));
			tool.setMedias(getMedias(tool.getId()));
			tool.setTags(getTags(tool.getId()));
			tools.add(tool);
			while (resultSet.next()) {
				tool = new Tool();
				tool.setId(resultSet.getInt(1));
				tool.setName(resultSet.getString(2));
				tool.setDescription(resultSet.getString(3));
				tool.setText(resultSet.getString(4));
				tool.setLaunchDate(resultSet.getDate(5));
				tool.setUpdateDate(resultSet.getDate(6));
				tool.setDownloads(resultSet.getInt(7));
				tool.setRating(resultSet.getInt(8));
				tool.setIcon(getIcon(tool.getId()));
				tool.setDevelopers(getDevelopers(tool.getId()));
				tool.setWishMaster(getWishMaster(tool.getId()));
				tool.setWishers(getWishers(tool.getId()));
				tool.setCategories(getCategories(tool.getId()));
				tool.setReviews(getReviews(tool.getId()));
				tool.setCurrentVersion(getCurrentVersion(tool.getId()));
				tool.setVersions(getVersions(tool.getId()));
				tool.setMedias(getMedias(tool.getId()));
				tool.setTags(getTags(tool.getId()));
				tools.add(tool);
			}
			return tools;
		}
		return null;
	}
	
}
