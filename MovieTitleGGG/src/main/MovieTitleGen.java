package main;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieTitleGen {

	public static void main(String[] args) {

		DB db = new DB();

		// Adjective adjective=db.getRandomAdjective();
		// System.out.println(adjective.getId());
		// System.out.println(adjective.getAdjective());

		// Noun noun=new Noun();
		// noun=db.getNounById(4);
		//
		// System.out.println(noun.getNoun());
		// System.out.println(noun.getId());

		MovieTitle movietitle = new MovieTitle();

		Scanner sc = new Scanner(System.in);
		String generateMovie = "y";

		while (generateMovie.equals("y")) {

			movietitle = db.getRandomMovieTitle();
			System.out.println("your movie name is   " + movietitle.getMovieTitle());

			System.out.println("pls input the movie description");
			String desc = sc.nextLine();

			// movie.setMovieTitle(movie.getDescription());
			movietitle.setDescription(desc);
			db.saveMoiveTitle(movietitle);

			System.out.println("want more?(y/n)");
			generateMovie = sc.next();
			sc.nextLine();
		}

		ArrayList<MovieTitle> movie = new ArrayList<MovieTitle>();
		movie = db.getAllMovieTitle();

		// for(int i=0;i<movie.size();i++){
		// System.out.println("movie title= "+movie.get(i).getMovieTitle());
		// System.out.println("movie description=
		// "+movie.get(i).getDescription());
		// }

		for (MovieTitle movies : movie) {
			System.out.println(
					movies.getMovieTitle() + "\n \t" + movies.getMovieTitle() + "\n--------------------------------\n");
		}

	}
}

// for(MovieTitle movie:movies)
// {
// System.out.println(
// movie.getMovieTitle() + "\n \t" + movie.getDescription() +
// "\n--------------------------------\n");
// }

// public static void main(String[] args) throws Exception
// {
//
//
// DB db = new DB();
//
//
// Scanner sc = new Scanner(System.in);
// String generateMovie = "y";
//
// while(generateMovie.equals("y"))
// {
// MovieTitle movie = db.getRandomMovieTitle();
// System.out.println( "Your movie title is: " + movie.getMovieTitle() );
//
// System.out.println("Please input your movie description");
// String desc = sc.nextLine();
//
// movie.setDescription(desc);
// db.saveMovie(movie);
//
// System.out.println("Want more? y/n");
// generateMovie = sc.next();
// sc.nextLine();
// }
//
// ArrayList<MovieTitle> movies = db.getAllMovies();
// System.out.println(movies.size());
// for(MovieTitle movie:movies)
// {
// System.out.println(movie.getMovieTitle()+ "\n \t" + movie.getDescription()
// +"\n--------------------------------\n");
// }
//
// }
//
//
//
// }
