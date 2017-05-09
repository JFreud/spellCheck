# spellCheck

-Brief description of your project

    Our project is a spell checker. The user inputs text that he or she wishes to be spell checked and our spell checker will return a corrected output. The user can then see which words were changed and can decide to either reject a change or respellcheck it.

-List of Working features / Things I should test

    * Program can take in a word, a phrase, a sentence, and more (we don't recommend inputting more than a sentence, because the program will run slowly): correctly spelled words will be unchanged, misspelled words will be changed
    * Changes can be rejected by typing <index of change>r
    * Changes can be respellchecked by typing <index of change>s
    * Program can both reject some changes and respellcheck at the same time
    * Program still works if you add too much whitespace
    * Program restores punctuation at end of a sentence
    * Program restores newlines
    * When a word hits the end of the input box, it moves to the next line
    * The user can edit the input and the reject/respell text boxes but cannot edit the output and the words changed text boxes
    * We added Javadocs to explain our SpellCheck class

-List of unresolved bugs

    * Respellcheck doesn't do anything sometimes or will switch between words - this is possibly because of there being no other possible words found in our dictionary search
    * Capitalization is not restored - this is because the code normalizes the input by lowercasing the text and it is too complicated to keep track of all letters that were possibly capitalized
    * It takes a longer than optimal time to run - this is because of the large dictionary that we have, which has over 300k words, and our inability to find a faster algorithm
    * When rejecting words, if there are common substrings between the word you're rejecting and other words in your output, all substrings will be changed - this is because we used replaceAll() in our rejects function
    * Punctuation does not work sometimes when inside the phrase (e.g. "hello hi, hey") - this can be fixed by slightly modifying the while loop inside our checkwords() function

-Directions on how to compile and run your code 

    Step 1: javac SpellCheck.java
    Step 2: java SpellCheck
    Step 3: A GUI will pop up to the user to interact with our code.

-Directions on how to use your program

    Step 1: Input text in top left box labeled "Input: ". The spell checked text will be printed out in the top right box labeled "Output: ".
    Step 2: Look at changes made in second box from bottom. (This will be blank if there were no changes performed.)
    Step 3: To reject a change type the index of the change in the changes box (starting from 0) followed by the letter 'r' for reject. (e.g. if I want to reject the second change, I would type "1r" into the reject box.)
        NOTE: Separate change/reject requests by ONLY one space
    Step 4: To respellcheck a word, do the same as step 3 but use an 's' instead of an 'r'
    Step 5: Have fun!
