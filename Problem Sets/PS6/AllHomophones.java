import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The program AllHomophones finds and outputs all the words that are homopohones
 * of a given word, if they exist in the pronunciation dictionary (file cmudict.0.7a.txt) 
 * 
 * The input is a single word, in all upper case 
 * The output is all words in the pronunciation dictionary that are homophones 
 * of the input word, including the input word, in alphabetical order
 * 
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-10-13
 */

public class AllHomophones{
	public static void main(String[] args) {
		UALDictionary<String, Pronunciation> PDict = new UALDictionary<String, Pronunciation>();
		File file = new File("cmudict.0.7a.txt");
        Scanner scan = new Scanner(System.in);
        String desiredWord = scan.next();
        scan.close();
        final int len = desiredWord.length();
        
		long start = System.currentTimeMillis();
        Pronunciation goal = null;
        boolean foundGoal = false;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.substring(0, 3).equals(";;;")){
                    continue; // skip comment lines
                }
				Pronunciation p = new Pronunciation(line);
				if (p.getWord().equals(desiredWord)){
                    goal = new Pronunciation(line);
                    foundGoal = true;
                }
                PDict.insert(p.getWord(), p);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		long middle = System.currentTimeMillis();
		//System.out.println("Loaded dictionary.");
        
        if(!foundGoal){
            System.out.println("Desired word is not in dictionary.");
            return;
        }
		for (Pronunciation p : PDict.values()) {
			if (p.getPhonemes().equals(goal.getPhonemes())){
				System.out.println(p.getWord());
			}
		}
		long end = System.currentTimeMillis();
		//System.out.println("Run times: load dictionary= " + (middle - start) + " process= " + (end - middle) + " total= " + (end - start));
	}
}
