

import java.util.ArrayList;
import java.util.ArrayList;

/*
 * Record movie title and list of actors who had a role.
 */

public class MovieRecord {
  public String name;
  ArrayList<Integer> actors;

  public MovieRecord(String n) {
    name = n;
    actors = new ArrayList<Integer>();
  }

  /* Add a movie to the list for this actor.  */
  public void addActor(Integer m) {
    actors.add(m);
  }
  
  public String toString(){
    String s = name+": ";
    for(Integer m : actors)
      s += m+"; ";
    return s;
  }
}
