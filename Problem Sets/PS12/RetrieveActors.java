
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

/*
 * Class for reading in actor records from the IMDB actors.list.gz or
 * actressess.list.gz.
 */

public class RetrieveActors {
  BufferedReader in;
  String content;
  String delim; // delimiter

  /*
   * fileName is the path to the actors file.
   */

  public RetrieveActors(String fileName) throws Exception {
    delim = "@@@";
/*
    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
        new FileInputStream(fileName)), "ISO-8859-1"));
*/
    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
        new FileInputStream(fileName)), "UTF-8"));

    /*
     * Read and ignore lines up through the line that gives the Name, Title
     * header.
     */
    while ((content = in.readLine()) != null) {
	//System.out.println(content);
      if (content.contains("Name") && content.contains("Title")) {
        content = in.readLine();
	//System.out.println(content);
        break;
      }
    }
  }
  
  public void close() throws Exception {
    in.close();
  }

  /**
   * 
   * @return the string containing actor's name and roles, separated by
   * "@@@".
   * @throws Exception
   */
  public String getNext() throws Exception {
    String rec = "";
    content = in.readLine();
	//System.out.println(content);
    if (content.contains("-----------"))
      return null; // end of entries
    String[] nameMovie;
    nameMovie = content.split("[\t]+");
    rec += nameMovie[0]; // actor's name
    rec += delim;
    String firstMovie = nameMovie[1]
        .substring(0, 1 + nameMovie[1].indexOf(")"));
    rec += getType(firstMovie);
    rec += firstMovie;
    rec += delim;
    while (true) {
      content = in.readLine();
	//System.out.println(content);
      if (content.length() < 1)
        break;
      content = content.replaceAll("\t", "");
      rec += getType(content);
      // System.out.println("Adding movie " + content);
      rec += content.substring(0, 1 + content.indexOf(")"));
      rec += delim;
    }
    return rec;
  }

  /*
   * Get the movie type; e.g., tv, video,...
   */
  private String getType(String s) {
    String tp = "";
    if (s.contains("(TV)"))
      tp += "TV"; // tv movie
    else if (s.contains("(V)"))
      tp += "VO"; // video
    else if (s.charAt(0) == '"')
      tp += "TS"; // tv series or miniseries
    else
      tp += "FM"; // it is a film.
    return tp;
  }
}
