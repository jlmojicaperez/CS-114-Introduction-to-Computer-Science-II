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
 * The program outputs the largest number of anagrams found followed by a list of the
 * words that have that number of anagrams, ordered alphabetically
 * 
 * @author Jose Mojica Perez
 * @version 2.0
 * @since 2020-09-24
 *
 */

/*
 * The program first iterates twice (to avoid errors if the user asks for more lines than there are available)
 * over n lines in the file to read and populate two arrays of string objects with n strings.
 * While doing that it sorts the characters of the string alphabetically before saving 
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
		ArrayList<String> words = new ArrayList<String>();
		try{
			Scanner reader = new Scanner(new File(fileName));
			for(int i = 0; i < lines && reader.hasNextLine(); i++){
				String word = reader.nextLine();
				words.add(word);
			} 
		}
		catch(FileNotFoundException e) {e.printStackTrace();}


		ArrayList<String> mostAnagrams = maxAnagrams(words);
		if(mostAnagrams.size() > 1){	
			System.out.println(mostAnagrams.size());
			for(int j = 0; j < mostAnagrams.size(); j++){
			System.out.println(mostAnagrams.get(j));
			}
		}
		else{
			System.out.println(0);
		}
	}
	public static ArrayList<String> maxAnagrams(ArrayList<String> words){
		
		int size = words.size();
		WordArray wordArray = new WordArray(words, size);
		for(int i = 0; i < size; i++){
			String word = words.get(i);
			char[] wordChars = word.toCharArray();
			Arrays.sort(wordChars);
			wordArray.array[i].str = new String(wordChars);
		
		} 
		
		Arrays.sort(wordArray.array, new WordComparator());
		
		Word currWord = wordArray.array[0];
		Word bestWord = wordArray.array[0];
		int currCount = 1;
		int maxCount = 1;
		int currIndex = 0;
		int bestIndex = 0;

		for(int j = 0; j < size; j++){
			if(currWord.str.equals(wordArray.array[j].str)){
				currCount++;
			}
			else if(maxCount < currCount){
				maxCount = currCount;
				bestWord = currWord;
				bestIndex = currIndex;

			}
			else{
				currWord = wordArray.array[j];
				currCount = 1;
				currIndex = j;
			}
		}
		if(currCount > maxCount){
			bestWord = currWord;
			maxCount = currCount;
			bestIndex = currIndex;
		}
		
		ArrayList<String> mostAnagrams = new ArrayList<String>();
		
		for(int k = bestIndex; k < bestIndex + maxCount; k++){
			if(wordArray.array[k].str.equals(bestWord.str)){
				mostAnagrams.add(words.get(wordArray.array[k].index));
			}
		}
		return mostAnagrams;
	}
	static class Word{
		String str;
		int index;

		public Word(String str, int index){
			this.str = str;
			this.index = index;
		}
	}
	static class WordArray{
		Word[] array;
		int size;

		public WordArray(ArrayList<String> str, int size){
			this.size = size;
			this.array = new Word[size];

			for(int index = 0; index < size; index++){
				this.array[index] = new Word(str.get(index), index);
			}
		}
	}
	static class WordComparator implements Comparator<Word>{
		public int compare(Word a, Word b){
			return a.str.compareTo(b.str);
		}		
	}
}
