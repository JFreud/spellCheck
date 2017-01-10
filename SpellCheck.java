import java.util.*;
import java.io.*;

//dictionary used is from: https://github.com/dwyl/english-words.git

public class SpellCheck{

     /* public int commonSubstring(String A, String B) {
    String current = ""; emacs test!!!!
    for (int i = 0; i < A.length(); i++) {
        for (int j = B.length(); j > 0; j++) {
    */

    private static ArrayList<String> alphabetical, reversed, common;
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
        for (int i = 0; i < alphabetical.size(); i++){
            reversed.add(i, new StringBuilder(alphabetical.get(i)).reverse().toString());
        }
        Collections.sort(reversed);
    }

    public static void commoners(){ // this function takes in the inputted text and turns it into an ArrayList
        File fileName = new File("common.txt");
        Scanner sc = new Scanner(fileName);
        common = new ArrayList<String>();
        sc.useDelimiter(" ");
        while (sc.hasNext()){
            common.add(sc.next().toLowerCase());
        }
    }

    public static int charMatches(String A, String B){
        int matchCount = 0;
        if (A.length() == B.length()){
            for (int i = 0; i < A.length(); i ++){
                if (A.charAt(i) == B.charAt(i)){
                    matchCount ++;
                }
            }
        }
        else {
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
                    first.charAt(i) == second.charAt(i+1))
                    {
                    //System.out.println(first.charAt(i));
                    //System.out.println(second.charAt(i));
                    matchCount += 1;
                    }
            }
        }
        if (common.contains(B)){
            matchCount ++;
        }
        return matchCount;
    }

    // public static int charMatches(String A, String B) {
    //     int matchCount = 0;
    //     String first, second;
    //     if (A.length() <= B.length()) {//this is to prevent index out of bounds error
    //         first = A;
    //         second = B;
    //     }
    //     else {
    //         first = B;
    //         second = A;
    //     }
    //     for (int i = 1; i < first.length()-1; i++) {//runs through shorter word and checks whether the other word has a matching character within one position
    //         if (first.charAt(i) == second.charAt(i) ||
    //      first.charAt(i) == second.charAt(i-1) ||
    //       first.charAt(i) == second.charAt(i+1))
    //      {
    //             //System.out.println(first.charAt(i));
    //             //System.out.println(second.charAt(i));
    //             matchCount += 1;
    //         }
    //     }
    //  if (first.length() == second.length()) { //will increase matchRatio if words are same length
    //      matchCount += 1;
    //  }
    //     return matchCount;
    // }

    public static double matchRatio(String A, String B) {
        return (double)charMatches(A, B)/A.length();
    }


    private static ArrayList<String> listPotential(ArrayList<String> dict, String word) {
        ArrayList<String> potential = new ArrayList<String>();
        int low = 0;
        int high = dict.size() - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            String testWord=dict.get(mid);
            if (testWord.compareTo(word) == 0) {
                potential.clear();
                potential.add(word);
                return potential;
            }
            if (testWord.compareTo(word) < 0) {
                low = mid + 1;
                System.out.println(testWord + " was lower");
                if (matchRatio(testWord, word) > 0.2) {
                    for(int i = -150; i <= 150; i++) {//if there is a similar word it adds the entire region as potential match
                        if(!(dict.get(mid + i).length() - 3 >= word.length())){
                            potential.add(dict.get(mid + i));
                        }
                    }
                } 
            }
            if(testWord.compareTo(word) > 0) {
                high = mid - 1;
                System.out.println(testWord+" was higher");
                if (matchRatio(testWord, word) > 0.2) {
                        for(int i = -150; i <= 150; i++) {
                            if(!(dict.get(mid + i).length() - 3 >= word.length())) {
                                potential.add(dict.get(mid + i));
                            }
                        }
                    }
                }
            }
        System.out.println("finished");
        System.out.println(potential.toString());
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

    public static ArrayList<String> reversePotential(ArrayList<String> potent) {
        for (int i = 0; i < potent.size(); i++) {
            potent.set(i, new StringBuilder(potent.get(i)).reverse().toString());
        }
        return potent;
    }
        

    
    public static String bestMatcher(String word) {
        String bestMatch = "";
        ArrayList<String> toBeSearch = listPotential(alphabetical, word);
        toBeSearch.addAll(reversePotential(listPotential(reversed, new StringBuilder(word).reverse().toString())));
        for (int i = 0; i < toBeSearch.size(); i++) {
            //System.out.println(toBeSearch.size());
            if (matchRatio(word, toBeSearch.get(i)) > matchRatio(word, bestMatch)) {
                bestMatch = toBeSearch.get(i);
            }
        }
        return bestMatch;
    }

    public static String checkWords(String input){
        String output = "";
        String[] inputText = input.replaceAll("\\p{P}", " ").toLowerCase().split(" ");
        changed = new ArrayList<String[]>();
        for(int i = 0; i < inputText.length; i++) {
            if (alphabetical.contains(inputText[i])){
                output += inputText[i] + " ";
            }
            else {
                output += bestMatcher(inputText[i]) + " ";
            }
        }
        return output;
    }
    
    public static void main(String[] args){
        Window w = new Window();
        w.setVisible(true);
        //System.out.println(matchRatio("quadratic","chicken"));
        //System.out.println(matchRatio("pisza", "pizza"));
        //System.out.println(matchRatio("helo", "hello"));
        //System.out.println(charMatches("watermelon", "watermolon"));
        //System.out.println(charMatches("watermolon", "watercolor"));
        dictionaryToArray();
        commoners();
    }

}
