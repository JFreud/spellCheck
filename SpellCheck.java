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


    /**
     * Creates two arraylists containing the words: one sorted alphabetically, one sorted inverse alphabetically
     *
     */
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

    /**
     *Creates arraylist of common words (from: http://www.ef.com/english-resources/english-vocabulary/top-3000-words/)
     *
     */
    public static void commoners(){ // this function takes in the inputted text and turns it into an ArrayList
        File fileName = new File("common.txt");
        common = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(fileName);
            sc.useDelimiter("\n");
            while (sc.hasNext()){
                common.add(sc.next());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("You failed!");
        }
    }
    /**
     *Compares the similarity of two strings by checking to see if each letter in one word is also in the other word (within one position)
     *
     *@param A a string one wishs to compare
     *@param B another string one wishes to compare
     */
    public static int charMatches(String A, String B){
        int matchCount = 0;
        if (A.length() == B.length()){
            for (int i = 0; i < A.length(); i ++){
                if (i == 0){
                    if (A.length() == 1){ //if the word is one letter long it just matches the letter
                        if (A.charAt(i) == B.charAt(i)){
                            matchCount ++;
                        }
                    }
                    else {
                        if (A.charAt(i) == B.charAt(i) || A.charAt(i) == B.charAt(i + 1)){ //checks if first letter within1 position in other word
                            matchCount ++;
                        }
                    }
                }
                else if (i == A.length() - 1){ //checks if last letter within 1 position in other word
                    if (A.charAt(i) == B.charAt(i) || A.charAt(i) == B.charAt(i - 1)){
                        matchCount ++;
                    }
                }
                else{
                    if (A.charAt(i) == B.charAt(i) || A.charAt(i) == B.charAt(i-1) || A.charAt(i) == B.charAt(i+1)) {//checks if non-end letter is within 1 position in other word
                        matchCount ++;
                    }
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
            for (int i = 0; i < first.length(); i ++){
                if (i == 0){
                    if (first.charAt(i) == second.charAt(i) || first.charAt(i) == second.charAt(i + 1)){
                        matchCount ++;
                    }
                }
                else if (i == first.length() - 1){
                    if (first.charAt(i) == second.charAt(i) || first.charAt(i) == second.charAt(i - 1)){
                        matchCount ++;
                    }
                }
                else{
                    if (first.charAt(i) == second.charAt(i) || first.charAt(i) == second.charAt(i-1) || first.charAt(i) == second.charAt(i+1)) {
                        matchCount ++;
                    }
                }
            }
        }
        if (common.contains(B)){ //increases matchRatio if the word being compared is common
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

    /**
     *Compares the number of matches with the length of the word, creating the match ratio. This function calls charMatches.
     *
     *@param A string one wishes to compare
     *@param B another string one wishes to comapre
     */
    public static double matchRatio(String A, String B) {
        return (double)charMatches(A, B)/A.length();
    }

    /**
     *Creates an arraylist of potential words that have higher than a 0.2 matchRatio. It does this by running a binary search and taking the words centered around a word with high enough similarity. This attempts to stop the binary search from skipping important words.
     *
     *@param dict The dictionary that we want to use to compare with the word.
     *@param word The word that we are searching for similarity to.
     */
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
                    for(int i = -2000; i <= 2000; i++) {//if there is a similar word it adds the entire region as potential match
                        if(!(dict.get(mid + i).length() - 2 >= word.length())){
                            potential.add(dict.get(mid + i));
                        }
                    }
                } 
            }
            if(testWord.compareTo(word) > 0) {
                high = mid - 1;
                System.out.println(testWord+" was higher");
                if (matchRatio(testWord, word) > 0.2) {
                        for(int i = -2000; i <= 2000; i++) {
                            if(!(dict.get(mid + i).length() - 2 >= word.length())) {
                                potential.add(dict.get(mid + i));
                            }
                        }
                    }
                }
            }
        //System.out.println("finished");
        System.out.println(potential.toString());
        return potential;
    }

    /**
     *A function written to test the production of the arraylist of potential words
     *
     *@param the word you are looking for similarity to.
     */
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
        

    /**
     *A function that compares a word to the alphabetical and inverse alphabetical dictionary and returns the word with the highest similarity.
     *
     *@param word the word that is being compared to the arraylists of words.
     */
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

    /**
     *Takes a sentence and corrects each word.
     *
     *@param input the sentence that is being spell checked.
     */
    public static String checkWords(String input){
        String output = "";
        String punc;
        String[] inputText = input.replaceAll("\n", " ").toLowerCase().split(" ");
        changed = new ArrayList<String[]>();
        for(int i = 0; i < inputText.length; i++) {
            punc = "";
            while (inputText[i].charAt(inputText[i].length() - 1) < 65 || inputText[i].charAt(inputText[i].length() - 1) > 122 || (inputText[i].charAt(inputText[i].length() - 1) > 90 && inputText[i].charAt(inputText[i].length() - 1) < 97)){
                punc += String.valueOf(inputText[i].charAt(inputText[i].length() - 1));
                inputText[i] = inputText[i].substring(0, inputText[i].length() - 1);
            }
            if (alphabetical.contains(inputText[i])){
                output += inputText[i] + punc + " ";
            }
            else {
                output += bestMatcher(inputText[i]) + punc + " ";
            }
        }
        return output;
    }
    
    public static void main(String[] args){
        Window w = new Window();
        w.setVisible(true);
        dictionaryToArray();
        commoners();
        //System.out.println(matchRatio("quadratic","chicken"));
        //System.out.println(matchRatio("pisza", "pizza"));
        //System.out.println(matchRatio("helo", "hello"));
        //System.out.println(matchRatio("teh", "these"));
        //System.out.println(matchRatio("teh", "the"));
    }

}
