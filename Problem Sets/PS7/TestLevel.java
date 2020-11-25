import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Print out all dictionary records in a given range of keys.
 */
public class TestLevel {

  public static void main(String[] args) {
     BST<String, StringPair> Dict = new BST<String, StringPair>();

     Dict.insert("D", new StringPair("D","D"));
     Dict.insert("B", new StringPair("B","B"));
     Dict.insert("G", new StringPair("G","G"));
     Dict.insert("C", new StringPair("C","C"));
     Dict.insert("E", new StringPair("E","E"));
     Dict.insert("A", new StringPair("A","A"));
     Dict.insert("H", new StringPair("H","H"));

    for(StringPair p : Dict.values())
      System.out.println(p.getString1());
  }
}
