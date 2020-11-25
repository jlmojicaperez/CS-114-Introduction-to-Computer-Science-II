import java.util.LinkedList;
import java.util.Scanner;

public class MovieRating implements Comparable<MovieRating> {
        private int votes;
	private float rating;
	private String title;

	MovieRating(String v, String r, String t) {
                votes = Integer.parseInt(v);
                rating = Float.parseFloat(r);
                title = t;
	}

	public int getVotes() {
		return votes;
	}

	public float getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String toString() {
		String s = Integer.toString(votes)+"  "+
                        Float.toString(rating) + " " + title;
		return s;
	}

        public int compareTo(MovieRating m) {
          if(this.rating - m.rating > 0.0)
             return 1;
          else if(this.rating - m.rating < 0.0)
             return -1;
          else
             return 0;
        }
}
