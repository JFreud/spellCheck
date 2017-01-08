import java.util.*;
import java.io.*;

public class SpellCheck{


     /* public int commonSubstring(String A, String B) {
    String current = ""; emacs test!!!!
    for (int i = 0; i < A.length(); i++) {
        for (int j = B.length(); j > 0; j++) {
    */

    private static ArrayList<String> alphabetical, reversed, input;
    private static ArrayList<String[]> changed;
    
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
    	    input.add(sc.next().toLowerCase());
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


    private static ArrayList<String> listPotential(ArrayList<String> dict, String word) {
	ArrayList<String> potential = new ArrayList<String>();
	int low = 0;
	int high = dict.size() - 1;
	while (high >= low) {
	    int mid = (low + high) / 2;
            String test_word=dict.get(mid);
	    if(test_word.compareTo(word) == 0) {
		potential.clear();
		potential.add(word);
		return potential;
	    }
	    if(test_word.compareTo(word) < 0) {
		low = mid + 1;
		System.out.println(test_word + " was lower");
		if (matchRatio(dict.get(mid), word) > 0.2) {
		    potential.add(dict.get(mid));
		}
	    }
	    if(test_word.compareTo(word) > 0) {
		high = mid - 1;
		System.out.println(test_word+" was higher");
		if (matchRatio(dict.get(mid), word) > 0.2) {
		    potential.add(dict.get(mid));
		}
	    }
	}
	System.out.println("finished");
	return potential;
    }

    public static String testPotential(String word) {
	ArrayList<String> p = listPotential(alphabetical, word);
	String out = "";
	for (int i = 0; i < p.size(); i++) {
	    out += p.get(i) + " ";
	}
	return out;
    }
	    

	
     public static String fLetterSearch(String word) {
	 String bestMatch = "";
	 ArrayList<String> toBeSearch = listPotential(alphabetical, word);
	 for (int i = 0; i < toBeSearch.size(); i++) {
	     if (matchRatio(word, toBeSearch.get(i)) > matchRatio(word, bestMatch)) {
		 bestMatch = toBeSearch.get(i);
	     }
	 }
	 return bestMatch;
     }

    /* public static void checkWords(String input){
	dictionaryToArray();
        changed = new ArrayList<String[]>();
    	for (int i = 0; i < input.size(); i ++){
    	    if (!alphabetical.contains(input.get(i))){
    	        //function
    	    }
    	}
	return;
    }
    */

    
    
    public static void main(String[] args){
        Window w = new Window();
	 w.setVisible(true);
	 //System.out.println(matchRatio("quadratic","chicken"));
	 //System.out.println(matchRatio("pisza", "pizza"));
	 //System.out.println(matchRatio("pissza", "pizza"));
        dictionaryToArray();
	//System.out.println(SpellCheck.testPotential("chicken"));
	//System.out.println(alphabetical);
    }

}
