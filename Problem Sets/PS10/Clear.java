import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Arrays;
/**
 * Clear.java uses 17 PPM images (named birds1.ppm,
 * birds2.ppm, ..., birds17.ppm). The images are sampled
 * from a scene in the Alfred Hitchcock move "The Birds".
 * The background is similar for each image, but each
 * has some clutter (attacking birds). The program produces 
 * an image (PPM) file  with the clutter removed.
 */

public class Clear{
  // Starter Code
  public static void main(String[] args) throws IOException {
    int pics = 17;
    File[] file = new File[pics];
    //String prefix = "../resource/asnlib/publicdata/";
    String prefix = "";
    for (int p = 1; p <= pics; ++p) {
      String suff = String.valueOf(p);
      String filename = prefix + "birds" + suff + ".ppm";
      System.out.println("Opening "+filename);
      file[p-1] = new File(filename);
    }

    long start = System.currentTimeMillis();

    // create a scanner for each of the pictures
    //
    Scanner[] scan = new Scanner[pics];

    int rows = 0, cols = 0, mx = 0;
    for (int i = 0; i < pics; ++i) {
      try {
        scan[i] = new Scanner(file[i]);
        String line = scan[i].nextLine();
        cols = Integer.parseInt(scan[i].next());
        rows = Integer.parseInt(scan[i].next());
        mx = Integer.parseInt(scan[i].next());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    /*
     * Fill in code to unclutter the image
     * 
     */
    // open output file and print out header

    BufferedWriter output = new BufferedWriter(new FileWriter("clear.ppm"));
    output.write(String.format("%s\n", "P3"));
    output.write(String.format("%d  %d\n", cols, rows));
    output.write(String.format("%d\n", mx));
    System.out.println(rows + ", " + cols + ", " + mx);

    Select.rm = new Random();
    for (int i = 0; i < cols; ++i) {
      for (int j = 0; j < rows; ++j) {
        // read in red, green, blue
        for (int c = 0; c < 3; ++c) {
	        int[] colors = new int[pics];
          for (int k = 0; k < pics; ++k){
	          colors[k]= Integer.parseInt(scan[k].next());
          }
          //Select the color value in the third quartile
	        int best = Select.select(colors, 0, colors.length-1, 3*(pics/4)+1);
          output.write(String.format("%d ", best));
        }
      }
      output.write(String.format("\n"));
    }

    for (int i = 0; i < pics; ++i) {
      scan[i].close();
    }
    output.close();
  }
}
