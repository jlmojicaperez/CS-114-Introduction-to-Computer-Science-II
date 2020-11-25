/*
 * Find out which actress had the most roles.
 */

import java.util.ArrayList;

public class BusyActress {

  public static void main(String[] args) throws Exception {
    ArrayList<ActorRecord> act = new ArrayList<ActorRecord>();

    String fname = "actresses.list.gz";
    RetrieveActors ra = new RetrieveActors(fname);

    String content;
    String[] tkn;
    int count = 0;

    long start = System.currentTimeMillis();

    // Read in the acress records, filtering out some.

    while ((content = ra.getNext()) != null) {
      ++count;
      tkn = content.split("@@@");
      ActorRecord ar = new ActorRecord(tkn[0]);
      for (int i = 1; i < tkn.length; ++i){
        if(tkn[i].substring(0, 2).equals("FM")) // only keep tv series
        ar.addMovie(tkn[i].substring(2));
      }
      act.add(ar);
    }
    ra.close();
    long end = System.currentTimeMillis();
    System.out.println("Read in "+act.size()+" actresses in time "+(end-start)+" msec.");

    // Have the information, now determine the busiest actress.
    
    String busyActress = "";
    int max = 0;
    for (ActorRecord a : act)
      if (a.movies.size() > max) {
        max = a.movies.size();
        busyActress = a.name;
      }
    System.out.println("Busiest actress: " + busyActress + ", " + max + " movies.");
  }
}
