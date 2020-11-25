import java.util.LinkedList;
import java.util.Scanner;

public class Pronunciation {
	private String word;
	private String pronounce;

	Pronunciation(String p) {
		int i = p.indexOf(' ');
		word = p.substring(0, i);
		pronounce = p.substring(i+1);
	}

	public String getWord() {
		return word;
	}

	public String getPhonemes() {
		return pronounce;
	}

	public String toString() {
		String s = word + " " + pronounce;
		return s;
	}
}