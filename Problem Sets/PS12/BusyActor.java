/*
 * Find out which actor had the most roles.
 */

import java.util.ArrayList;

public class BusyActor {

  public static void main(String[] args) throws Exception {
    ArrayList<ActorRecord> act = new ArrayList<ActorRecord>();
		String fname = "../resource/asnlib/public/actresses.list.gz";
		RetrieveActors ra = new RetrieveActors(fname);
		String content;
		String[] tkn;
		int count = 0;
		long starting = System.currentTimeMillis();
		long start = System.currentTimeMillis();
		while ((content = ra.getNext()) != null) {
			tkn = content.split("@@@");
			ActorRecord ar = new ActorRecord(tkn[0]);
			for (int i = 1; i < tkn.length; ++i) {
				if (tkn[i].substring(0, 2).equals("FM"))
					ar.addMovie(tkn[i].substring(2));
			}
			act.add(ar);
			++count;
		}
		ra.close();

		System.out.println("Read in " + count + " actresses.");

		fname = "../resource/asnlib/public/actors.list.gz";
		ra = new RetrieveActors(fname);
		count = 0;
		start = System.currentTimeMillis();
		while ((content = ra.getNext()) != null) {
			tkn = content.split("@@@");
			ActorRecord ar = new ActorRecord(tkn[0]);
			for (int i = 1; i < tkn.length; ++i) {
				if (tkn[i].substring(0, 2).equals("FM"))
					ar.addMovie(tkn[i].substring(2));
			}
			act.add(ar);
			++count;
		}
		ra.close();
		System.out.println("Read in " + count + " actors.");
		

		long end1 = System.currentTimeMillis();
		System.out.println("Reading in took " + ((end1 - start) / 1000) + " sec.");

    // Have the information, now determine the busiest actor.
    
    String busyActor = "";
    int max = 0;
    for (ActorRecord a : act)
      if (a.movies.size() > max) {
        max = a.movies.size();
        busyActor = a.name;
      }
    System.out.println("Busiest actor: " + busyActor + ", " + max + " movies.");
  }
}
