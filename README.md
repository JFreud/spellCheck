# spellCheck
Our final project for semester 1 of APCS at Stuy.

Update: Final Project Readme file MUST include:

-Brief description of your project

    Our project is a spellchecker. The user inputs a sentence that he or she wishes to be corrected and the Spellchecker will return a corrected output. The user can then see which words were changed and can decide to either reject a change or respellcheck it.

-List of Working features / Things I should test

    * Program can take sentence: correctly spelled words will be unchanged, mispelled words will be changed
    * Changes can be rejected by typing <index of change> + r
    * Changes can be respellchecked by typing <index of change> + s
    * Program can both reject some changes and respellcheck at the same time
    * Program still works if you add too much whitespace
    * Program restores punctuation at end of a sentence.
    * Program restores newlines
    * When a word hits the end of the input box it moves to the next line

-List of unresolved bugs

    * Respellcheck doesn't do anything sometimes (I think when there aren't any other options)
    * Respellcheck only works once, otherwise it just turns it back into the original correction
    * Capitalization is not restored
    * It takes a longer than optimal time to run
    

-Directions on how to compile and run your code 

Step 1: javac SpellCheck.java
Step 2: java SpellCheck
Step 3: Use program


-Directions on how to use your program

Step 1: Input text in top left box labeled "Input: "
Step 2: Look at changes made in second box from bottom (will be blank if no changes performed)
Step 3: To reject a change type the index of the change in the changes box (starting from 0) followed by the letter 'r' for reject. (e.g. if I want to reject the second change I would type "1r" into the reject box.
     NOTE: Separate change/reject requests by ONLY one space
Step 4: To respellcheck a word, do the same as step 5 but use an 's' instead of an 'r'
Step 5: Have fun!
