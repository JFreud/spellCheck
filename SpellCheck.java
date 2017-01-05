public class SpellCheck{


     /* public int commonSubstring(String A, String B) {
	String current = "";
	for (int i = 0; i < A.length(); i++) {
	    for (int j = B.length(); j > 0; j++) {
    */


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
<<<<<<< HEAD
        Window w = new Window();
        w.setVisible(true);
        System.out.println(charMatches("quadratic","chicken"));
	System.out.println(charMatches("pisza", "pizza"));
	System.out.println(charMatches("pissza", "pizza"));
=======
		Window w = new Window();
		w.setVisible(true);
        System.out.println(matchRatio("quadratic","chicken"));
	System.out.println(matchRatio("pisza", "pizza"));
	System.out.println(matchRatio("pissza", "pizza"));
>>>>>>> eb38fe2368fcfbc70b8295cfbc2157a06b320916
    }

    

}
