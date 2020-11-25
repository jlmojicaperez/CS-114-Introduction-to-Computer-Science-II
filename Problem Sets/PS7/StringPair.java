import java.util.LinkedList;
import java.util.Scanner;

public class StringPair {
	private String string1;
	private String string2;

	StringPair(String p1, String p2) {
                string1 = p1;
                string2 = p2;
	}

	public String getString1() {
		return string1;
	}

	public String getString2() {
		return string2;
	}

	public String toString() {
		String s = string1 + " " + string2;
		return s;
	}
}
