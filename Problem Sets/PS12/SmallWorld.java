/*
 * Students: Jose Mojica Perez and Justin Diaz
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class SmallWorld{
	public static void main(String[] args) throws Exception{
		ArrayList<ActorRecord> act = new ArrayList<ActorRecord>();

		String fname = "actors.list.gz";
		RetrieveActors ra = new RetrieveActors(fname);

		String content;
		String[] tkn;
        int count = -1;
        
        BST<String, MovieRecord> movieBST = new BST<String, MovieRecord>(); 

		long startTime = System.currentTimeMillis();

		while ((content = ra.getNext()) != null) {
			++count;
			tkn = content.split("@@@");
			ActorRecord ar = new ActorRecord(tkn[0]);
			for (int i = 1; i < tkn.length; ++i){
				if(tkn[i].substring(0, 2).equals("FM")) // only keep tv series
					ar.addMovie(tkn[i].substring(2));
            }
            for(String movie : ar.movies){
                MovieRecord currMovie = movieBST.find(movie);
                if(currMovie == null){
                    MovieRecord newMovieRecord = new MovieRecord(movie);
                    newMovieRecord.addActor(count);
                    movieBST.insert(movie, newMovieRecord);
                }
                else{
                    currMovie.addActor(count);
                }
            }
			act.add(ar);
        }
		ra.close();
        
        long endTime = System.currentTimeMillis();
        System.out.println("Read in "+act.size()+" actors in time "+(endTime-startTime)+" ms.");

        startTime = System.currentTimeMillis();

        Graphl actorGraph = new Graphl(act.size());
    
		for(MovieRecord curRecord : movieBST.values()){            
            for(int i : curRecord.actors){
                for(int j : curRecord.actors){
                    if(i==j || actorGraph.isEdge(i,j)){
                        continue;
                    }
                    actorGraph.setEdge(i, j, 0);
                    actorGraph.setEdge(j, i, 0);
                }
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Construted graph in time " + (endTime-startTime)+ " ms.");
        
        Scanner scan = new Scanner(System.in);
        int start, goal;
        do{
            start = Integer.parseInt(scan.next());
            goal = Integer.parseInt(scan.next());
            startTime = System.currentTimeMillis();
            if(start != 0 || goal != 0)
                printShortestDistance(act, actorGraph, start, goal);
            endTime = System.currentTimeMillis();
            System.out.println("Search and path reconstruction in time: " + (endTime-startTime)+ " ms.");
        }while(start != 0 || goal != 0);	
        scan.close();
    }
	private static void printShortestDistance(ArrayList<ActorRecord> act, Graphl graph, int start, int goal){
        
        // pred[i] = vertex that pecedes the ith vertex
		int pred[] = new int[graph.n()];
        // dist[i] = distance from start vertex to ith vertex
        int dist[] = new int[graph.n()];
        
        if (BFS(graph, start, goal, pred, dist) == false) {
            System.out.println("No connection.");
            return;
        }
 
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = goal;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        String chain = "";
        for (int i = path.size() - 1; i > 0; i--) {
            ActorRecord currActor = act.get(path.get(i));
            ActorRecord predActor = act.get(path.get(i-1));
            String commonMovie = "";
            for(int j = 0; j < currActor.movies.size(); j++){
                if(predActor.appearedIn(currActor.movies.get(j))){
                    commonMovie = currActor.movies.get(j);
                    break;
                }
            }
            chain += currActor.name + " appeared with " + predActor.name + " in " + commonMovie + "\n";
        }
        // Print header
        System.out.println("Shortest path between " + act.get(start).name + " and " + act.get(goal).name );
        // Print distance
        System.out.println("Distance: "+ dist[goal] +"; the chain is:");
        // Print chain
        System.out.println(chain);
    }

	private static boolean BFS(Graphl graph, int start, int goal, int pred[], int dist[]){

        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // bool visited[] stores whether or not the a vertex 
		// has been visited in the Breadth first search
        //boolean visited[] = new boolean[graph.n()];
 
        // initially all vertices are unvisited
        // dist[i] for all i set to infinity
        for (int i = 0; i < graph.n(); i++) {
            //visited[i] = false;
            graph.setMark(i, 0);
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        graph.setMark(start, 1);
        dist[start] = 0;
        queue.add(start);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            for (int i : graph.neighbors(curr)) {
                if (graph.getMark(i) == 0) {
                    graph.setMark(i, 1);
                    dist[i] = dist[curr] + 1;
                    pred[i] = curr;
                    queue.add(i);
                    //stop if goal vertex is found
                    if (i == goal)
                        return true;
                }
            }
        }
        return false;
    }
}
