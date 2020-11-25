import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * The program MostAnagrams finds the word with the largest number of anagrams
 * within n lines from the text file words.txt, where n is a given positive integer
 *
 * words.txt contains words separated by the new line character
 *
 * The program outputs how many anagrams exist in n lines followed by the largest 
 * number of anagrams found followed by a list of the words that have that number 
 * of anagrams, ordered alphabetically
 * 
 * @author Jose Mojica Perez
 * @version 3.0
 * @since 2020-09-28
 **/

/*
 * The program first iterates over n lines in the file to read and populate two arrays of string objects 
 * with n strings. While doing that it sorts the characters of the string alphabetically before saving 
 * it to one of the arrays. Then it iterates over n strings to find the word with the highest number
 * of anagrams, k. Finally it iterates over k anagrams and stores them to the list which contains the 
 * final result. If we let m be the maximum length of the strings, then we have a time complexity of:
 *
 * 	O(n*m*log(m))
 */

public class MostAnagrams{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int lines = Integer.parseInt(scan.next());
		String fileName = "words.txt";
		scan.close();

		ArrayList<String> mostAnagrams = maxAnagrams(fileName, lines);
		
		System.out.println(mostAnagrams.size());
		for(int j = 0; j < mostAnagrams.size(); j++){
			System.out.println(mostAnagrams.get(j));
		}
	}
	public static ArrayList<String> maxAnagrams(String fileName, int lines){
		String[] originalWords = new String[lines];
		Word[] wordArray = new Word[lines];
		try{
			Scanner reader = new Scanner(new File(fileName));
			for(int i = 0; i < lines && reader.hasNextLine(); i++){
				String word = reader.nextLine();
				originalWords[i] = word;
				wordArray[i] = new Word(word, i);
		
			} 
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		Arrays.sort(wordArray, new WordComparator());
		
		Word currWord = wordArray[0];
		Word bestWord = wordArray[0];
		int currCount = 1;
		int maxCount = 1;
		int currIndex = 0;
		int bestIndex = 0;
		int anagramCount = 1;

		for(int j = 0; j < lines; j++){
			if(currWord.getSortedChars().equals(wordArray[j].getSortedChars())){
				currCount++;
				anagramCount++;
			}
			else if(maxCount < currCount){
				maxCount = currCount;
				bestWord = currWord;
				bestIndex = currIndex;

			}
			else{
				currWord = wordArray[j];
				currCount = 1;
				currIndex = j;
			}
		}
		if(currCount > maxCount){
			bestWord = currWord;
			maxCount = currCount;
			bestIndex = currIndex;
		}
		System.out.println(anagramCount);
		ArrayList<String> mostAnagrams = new ArrayList<String>();
		for(int k = bestIndex; k < bestIndex + maxCount; k++){
			if(wordArray[k].getSortedChars().equals(bestWord.getSortedChars())){
				mostAnagrams.add(originalWords[wordArray[k].getIndex()]);
			}
		}
		return mostAnagrams;
	}
}
class Word{
	private String str;
	private int index;
	private String sortedChars;
	public Word(String str, int index){
		this.str = str;
		this.index = index;
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		this.sortedChars = new String(chars);
	}
	public int getIndex(){
		return this.index;
	}
	public String getStr(){
		return this.str;
	}
	public String getSortedChars(){
		return this.sortedChars;
	}
}
class WordComparator implements Comparator<Word>{
	public int compare(Word a, Word b){
		return a.getSortedChars().compareTo(b.getSortedChars());
	}		
}
