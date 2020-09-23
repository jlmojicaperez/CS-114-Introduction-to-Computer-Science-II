import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * The program MostAnagrams finds the word with the largest number of anagrams
 * within n lines from the text file words.txt, where n is a given positive integer
 *
 * words.txt contains words separated by the new line character
 *
 * The program outputs the largest number of anagrams found followed by a list of the
 * words that have that number of anagrams, ordered alphabetically
 * 
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-09-23
 *
 */

/* The program compares iterates over n words, comparing each word to all n words, 
 * finding the anagrams. If we let m be the maximum length of the words, then to find 
 * the anagrams we iterate over at most m characters twice, and then we iterate over 
 * 26 character frequencies to see if word_a has the same character frequencies as word_b,
 * thus making it an anagram of word_a. This would give us a time complexity of:
 *
 * 	O(n^2 + 2m*n^2 + 26n^2) = O(2m*n^2 + 27n^2)
 */

public class MostAnagrams{
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		//int lines = Integer.parseInt(args[0]);
		int lines = Integer.parseInt(scan.next());
		
		String file_name = "words.txt";
		ArrayList<String> rtn = new ArrayList<String>();
		
		try{
			Scanner reader_1 = new Scanner(new File(file_name));
			for(int i = 0; i < lines; i++){
				if(!reader_1.hasNextLine())
					break;
				String word_a = reader_1.nextLine();
				char[] chars_a = word_a.toCharArray();	

				ArrayList<String> temp = new ArrayList<String>();
				Scanner reader_2 = new Scanner(new File(file_name));
				int anagram_count = 0;

				for(int p = 0; p < lines; p++){
					if(!reader_2.hasNextLine())
						break;
					boolean isAnagram = true;
					String word_b = reader_2.nextLine();
					char[] chars_b = word_b.toCharArray();
					
					int[] char_frequency = new int[26];
					
					for(int j = 0; j < chars_a.length; j++){
						int index = chars_a[j] - 'a';
						char_frequency[index]++;
					}

					for(int	k = 0; k < chars_b.length; k++){
						int index = chars_b[k]-'a';
						char_frequency[index]--;
					}
					for(int m = 0; m < 26; m++){
						if(char_frequency[m] != 0){
							isAnagram = false;
						}
					}
					if(isAnagram){
						temp.add(word_b);
					}
				}
				if(temp.size() > rtn.size()){
					rtn = temp;
				}
				reader_2.close();
			}

			reader_1.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		System.out.println(rtn.size());
		rtn.sort(String::compareTo);
		for(int n = 0; n < rtn.size(); n++){
			System.out.println(rtn.get(n));
		}
	}
}
