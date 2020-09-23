import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * The program MostAnagrams finds the word with the largest number of anagrams
 * within a given number of lines from the text file words.txt
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
public class MostAnagrams{
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		//int lines = Integer.parseInt(args[0]);
		int lines = Integer.parseInt(scan.next());
		
		String file_name = "words.txt";
		ArrayList<String> rtn = new ArrayList<String>();
		
		try{
			Scanner reader_lines = new Scanner(new File(file_name));
			for(int i = 0; i < lines; i++){
				String word_a = reader_lines.nextLine();
				char[] chars_a = word_a.toCharArray();	

				ArrayList<String> temp = new ArrayList<String>();
				Scanner reader = new Scanner(new File(file_name));
				int anagram_count = 0;

				for(int p = 0; p < lines; p++){
					boolean isAnagram = true;
					String word_b = reader.nextLine();
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
				reader.close();
			}

			reader_lines.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		System.out.println(rtn.size());
		for(int n = 0; n < rtn.size(); n++){
			System.out.println(rtn.get(n));
		}
	}
}
