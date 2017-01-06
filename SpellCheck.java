import java.util.*;
import java.io.*;

public class SpellCheck{


     /* public int commonSubstring(String A, String B) {
    String current = "";
    for (int i = 0; i < A.length(); i++) {
        for (int j = B.length(); j > 0; j++) {
    */

   private static ArrayList<String> alphabetical, reversed, input;
    
    public static void dictionaryToArray(){
        alphabetical = new ArrayList<String>();
        File fileName = new File("words.txt");
        try {
            Scanner sc = new Scanner(fileName);
            sc.useDelimiter("\n");
            while (sc.hasNext()){
                alphabetical.add(sc.next());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("You failed!");
        }
        reversed = new ArrayList<String>();
        for (int i = alphabetical.size() - 1; i >= 0; i --){
            reversed.add(alphabetical.get(i));
        }
    }

    public static void inputtedToArray(String inputted){
        Scanner sc = new Scanner(inputted);
	input = new ArrayList<String>();
	sc.useDelimiter(" ");
	while (sc.hasNext()){
	    input.add(sc.next());
	}
	return input;
    }

    public static checkWords(){
	for (int i = 0; i < input.size(); i ++){
	    if (!alphabetized.contains(input.get(i))){
		// add called functions here
	    }
	}
    }

    public static int charMatches(String A, String B) {
    int matchCount = 0;
    String first, second;
    if (A.length() <= B.length()) {//this is to prevent index out of bounds error
        first = A;
        second = B;
    }
    else {
        first = B;
        second = A;
    }
    for (int i = 1; i < first.length()-1; i++) {//runs through shorter word and checks whether the other word has a matching character within one position
        if (first.charAt(i) == second.charAt(i) ||
        first.charAt(i) == second.charAt(i-1) ||
        first.charAt(i) == second.charAt(i+1)) {
            //System.out.println(first.charAt(i));
            //System.out.println(second.charAt(i));
            matchCount += 1;
        }
    }
    return matchCount;
    }

    public static double matchRatio(String A, String B) {
        return (double)charMatches(A, B)/A.length();
    }
    
    public static void main(String[] args){
        Window w = new Window();
        w.setVisible(true);
        System.out.println(charMatches("quadratic","chicken"));
        System.out.println(charMatches("pisza", "pizza"));
        System.out.println(charMatches("pissza", "pizza"));
        dictionaryToArray();
    }

}
