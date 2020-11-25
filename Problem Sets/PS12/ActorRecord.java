import java.util.ArrayList;

/*
 * Record actor's name and list of movies in which actor had a role.
 */

public class ActorRecord {
  public String name;
  public ArrayList<String> movies;

  public ActorRecord(String n) {
    name = n;
    movies = new ArrayList<String>();
  }

  /* Add a movie to the list for this actor.  */
  public void addMovie(String m) {
    movies.add(m);
  }

  public boolean appearedIn(String movie) {
    return movies.contains(movie);
  }
  
  public String toString(){
    String s = name;
    for(String m : movies)
      s += " "+m;
    return s;
  }
}
