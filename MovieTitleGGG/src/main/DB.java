package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DB {

	public static Connection getConnection() throws SQLException {
		// URL of Oracle database server

		String url = "jdbc:oracle:thin:testuser/password@localhost";
		// properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", "SYSTEM");
		props.setProperty("password", "1610");

		// creating connection to Oracle database using JDBC
		Connection conn = DriverManager.getConnection(url, props);

		return conn;
	}

	public ArrayList<MovieTitle> getAllMovieTitle() {

		ArrayList<MovieTitle> list = new ArrayList<MovieTitle>();
		String sql = "select * from movietitle";
		Connection conn = null;
		ResultSet result = null;

		try {
			conn = getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();

			while (result.next()) {
				MovieTitle movie = new MovieTitle();
				movie.setDescription(result.getString("description"));
				movie.setMovieTitle(result.getString("title"));
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Noun getRandomNoun() {

		Noun noun = new Noun();
		String sql = "SELECT * FROM (SELECT * FROM NOUN ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum =1";
		Connection conn = null;
		ResultSet result = null;
		try {
			conn = getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();

			while (result.next()) {
				noun.setId(result.getInt("id"));
				noun.setNoun(result.getString("noun"));
			}

		} catch (SQLException e) {
			System.out.println(e);
			// e.printStackTrace();
		}

		return noun;
	}

	public Adjective getRandomAdjective() {

		Adjective adjective = new Adjective();
		String sql = "SELECT * FROM (SELECT * FROM adjective ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum =1";
		Connection conn = null;
		ResultSet result = null;
		try {
			conn = getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();

			while (result.next()) {
				adjective.setId(result.getInt("id"));
				adjective.setAdjective(result.getString("adjective"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return adjective;
	}

	public MovieTitle getRandomMovieTitle() {
		Adjective adjective = getRandomAdjective();
		Noun noun = getRandomNoun();

		MovieTitle movieTitle = new MovieTitle();
		movieTitle.setMovieTitle(adjective.getAdjective() + " " + noun.getNoun());

		return movieTitle;
	}

	public void saveMoiveTitle(MovieTitle movie) {

		String sql = "INSERT INTO MOVIETITLE (TITLE,DESCRIPTION) VALUES (?,?)";
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);

			preStatement.setString(1, movie.getMovieTitle());
			preStatement.setString(2, movie.getDescription());
			preStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	public Noun getNounById(int id) {

		String sql = "SELECT * FROM NOUN WHERE ID= " + id;

		Noun noun = new Noun();
		Connection conn = null;
		ResultSet result = null;

		try {
			conn = getConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();

			while (result.next()) {
				noun.setId(result.getInt("id"));
				noun.setNoun(result.getString("noun"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return noun;
	}

	// public void saveMovie(MovieTitle movie) {
	// String sql = "INSERT INTO MOVIETITLE (TITLE,DESCRIPTION) VALUES (?,?)";
	// try {
	// PreparedStatement prepareStatement =
	// getConnection().prepareStatement(sql);
	// prepareStatement.setString(1, movie.getMovieTitle());
	// prepareStatement.setString(2, movie.getDescription());
	//
	// prepareStatement.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	// public Adjective getRandomAdjective() {
	// String sql = "SELECT * FROM (SELECT * FROM adjective ORDER BY
	// DBMS_RANDOM.RANDOM) WHERE rownum =1";
	//
	// Adjective adjective = new Adjective();
	//
	// Connection conn = null;
	// ResultSet result = null;
	// try {
	// conn = getConnection();
	// PreparedStatement preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	//
	// while (result.next()) {
	// adjective.setId(result.getInt("ID"));
	// adjective.setAdjective(result.getString("WORD"));
	// }
	// conn.close();
	// } catch (SQLException e) {
	// try {
	// conn.close();
	// } catch (SQLException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// e.printStackTrace();
	// }
	// return adjective;
	// }

	// public ArrayList<MovieTitle> getAllMovies() {
	// ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
	// String sql = "SELECT * FROM MOVIETITLE";
	// Connection conn = null;
	// ResultSet result = null;
	// try {
	// conn = getConnection();
	// PreparedStatement preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	//
	// while (result.next()) {
	// MovieTitle movie = new MovieTitle();
	// System.out.println("Title " + result.getString("TITLE"));
	//
	// movie.setMovieTitle(result.getString("TITLE"));
	// movie.setDescription(result.getString("DESCRIPTION"));
	// movies.add(movie);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return movies;
	// }

	// public MovieTitle getRandomMovieTitle()
	// {
	// Adjective adjective = getRandomAdjective();
	// Noun noun = getRandomNoun();
	//
	// MovieTitle movieTitle = new MovieTitle();
	// movieTitle.setMovieTitle(adjective.getAdjective() + " " +
	// noun.getNoun());
	//
	// return movieTitle;
	// }
	//
	// public void saveMovie(MovieTitle movie)
	// {
	// String sql = "INSERT INTO MOVIETITLE (TITLE,DESCRIPTION) VALUES (?,?)";
	// try
	// {
	// PreparedStatement prepareStatement =
	// getConnection().prepareStatement(sql);
	// prepareStatement.setString(1, movie.getMovieTitle());
	// prepareStatement.setString(2, movie.getDescription());
	//
	// prepareStatement.executeUpdate();
	// }
	// catch(SQLException e)
	// {
	// e.printStackTrace();
	// }
	// }
	// public ArrayList<MovieTitle> getAllMovies()
	// {
	// ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
	// String sql = "SELECT * FROM MOVIETITLE";
	// Connection conn = null;
	// ResultSet result = null;
	// try
	// {
	// conn = getConnection();
	// PreparedStatement preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	//
	// while(result.next())
	// {
	// MovieTitle movie =new MovieTitle();
	// System.out.println("Title " + result.getString("TITLE"));
	//
	// movie.setMovieTitle(result.getString("TITLE"));
	// movie.setDescription(result.getString("DESCRIPTION"));
	// movies.add(movie);
	// }
	// }
	// catch(SQLException e)
	// {
	// e.printStackTrace();
	// }
	//
	// return movies;
	// }

	// public Adjective getRandomAdjective()
	// {
	// String sql = "SELECT * FROM (SELECT * FROM adjective ORDER BY
	// DBMS_RANDOM.RANDOM) WHERE rownum =1";
	//
	// Adjective adjective = new Adjective();
	//
	// Connection conn = null;
	// ResultSet result = null;
	// try
	// {
	// conn = getConnection();
	// PreparedStatement preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	//
	// while(result.next())
	// {
	// adjective.setId(result.getInt("ID"));
	// adjective.setAdjective(result.getString("WORD"));
	// }
	// conn.close();
	// }
	// catch (SQLException e)
	// {
	// try
	// {
	// conn.close();
	// } catch (SQLException e1)
	// {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// e.printStackTrace();
	// }
	// return adjective;
	// }
	//
	// public Noun getRandomNoun()
	// {
	// String sql = "SELECT * FROM (SELECT * FROM noun ORDER BY
	// DBMS_RANDOM.RANDOM) WHERE rownum =1";
	//
	// Noun noun = new Noun();
	//
	// Connection conn = null;
	// ResultSet result = null;
	// try
	// {
	// conn = getConnection();
	// PreparedStatement preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	//
	// while(result.next())
	// {
	// noun.setId(result.getInt("ID"));
	// noun.setNoun(result.getString("WORD"));
	// }
	// conn.close();
	// }
	// catch (SQLException e)
	// {
	// try
	// {
	// conn.close();
	// } catch (SQLException e1)
	// {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// e.printStackTrace();
	// }
	// return noun;
	// }
	////
	//// public void saveMovieTitle(MovieTitle movie)
	//// {
	////
	//// }
	////
	//// public ArrayList<MovieTitle> getAllMovieTitles()
	//// {
	////
	//// }
	// public static Connection getConnection() throws SQLException
	// {
	// //URL of Oracle database server
	//
	// String url = "jdbc:oracle:thin:testuser/password@localhost";
	// //properties for creating connection to Oracle database
	// Properties props = new Properties();
	// props.setProperty("user", "testdb");
	// props.setProperty("password", "password");
	//
	// //creating connection to Oracle database using JDBC
	// Connection conn = DriverManager.getConnection(url,props);
	//
	// return conn;
	// }
}