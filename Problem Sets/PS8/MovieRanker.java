import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

/* Starter code for PS8.
 */

public class MovieRanker {

	public static void main(String[] args) {
        File file = new File("../resource/asnlib/publicdata/ratings.tsv");
		//File file = new File("ratings.tsv");
		ArrayList<MovieRating> rl = new ArrayList<MovieRating>();

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
				rl.add(nr);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int minVotes = 1;
		int numRecords = 1;
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			numRecords = input.nextInt();
			if (minVotes * numRecords == 0)
				break;
			long startTime = System.currentTimeMillis();

			/* Fill in code to determine the top numRecords movies that have at least
			* minVotes votes. For each record mr, in decreaseing order of average rating,
			* execute a System.out.println(mr).
			* Do not sort the movie list!
			*/
			MaxHeap<MovieRating> movieRatings = new MaxHeap<MovieRating>();
			for(MovieRating mr : rl){
				if(mr.getVotes() >= minVotes){
					movieRatings.insert(mr);
				}
			}
			System.out.println();
			for(int i = 0; i < numRecords; i++){
				System.out.println(movieRatings.removemax());
			}

			System.out.println();;
			System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");

			MaxHeap<MovieRating> worstMovies  = new MaxHeap<MovieRating>();
			for(int i = 0; i < rl.size(); i++){
				MovieRating mr = rl.get(i);
				if(worstMovies.heapsize() > numRecords){
					worstMovies.removemax();
				}
				if(mr.getVotes() >= minVotes){
					worstMovies.insert(mr);
				}
			}
			if(worstMovies.heapsize() > numRecords){
				worstMovies.removemax();
			}
			System.out.println("\nWorst Movies:\n");
			for(int i = 0; i < numRecords; i++){
				System.out.println(worstMovies.removemax());
			}
		}
	}
}
