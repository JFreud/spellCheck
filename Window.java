// thanks Mr. K for the bulk of this code!

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JFrame implements ActionListener{
    private Container pane, inside, inside1, inside2, changes;
    private JLabel j, k, l;
    private JButton b, r;
    private JTextArea t, output, input, numbers;

    public Window() {
    	this.setTitle("Spell Checker");
    	this.setSize(600,400);
    	this.setLocation(100,100);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    	pane = this.getContentPane();
    	inside = new Container();
    	inside1 = new Container();
    	inside2 = new Container();
    	changes = new Container();

    	pane.setLayout(new BorderLayout(10, 20));
    	inside.setLayout(new GridLayout());
        inside1.setLayout(new GridLayout());
    	inside2.setLayout(new BoxLayout(inside2, BoxLayout.PAGE_AXIS));
    	changes.setLayout(new FlowLayout());

    	b = new JButton("Spell check!");
    	b.addActionListener(this);
    	b.setActionCommand("Correct");
    	r = new JButton("Reject changes");
    	r.addActionListener(this);
    	r.setActionCommand("Rejection");

    	t = new JTextArea(2, 20);
    	numbers = new JTextArea(4, 35);
    	output = new JTextArea(10, 100);
    	input = new JTextArea(10, 100);
    	t.setEditable(false);
    	output.setEditable(false);
    	t.setLineWrap(true);
    	numbers.setLineWrap(true);
    	input.setLineWrap(true);
    	output.setLineWrap(true);
        input.setWrapStyleWord(true);
        output.setWrapStyleWord(true);
        t.setWrapStyleWord(true);
        numbers.setWrapStyleWord(true);
    	input.setBorder(new JTextField().getBorder());
    	output.setBorder(new JTextField().getBorder());
    	t.setBorder(new JTextField().getBorder());
    	numbers.setBorder(new JTextField().getBorder());

    	j = new JLabel("Input:");
    	k = new JLabel("Words changed:");
    	l = new JLabel("Output:");

    	pane.add(inside2, BorderLayout.PAGE_END);
    	pane.add(inside1, BorderLayout.CENTER);
    	pane.add(inside, BorderLayout.PAGE_START);
    	inside.add(j);
    	inside.add(l);
    	inside1.add(input);
    	inside1.add(output);
    	inside2.add(k);
    	inside2.add(t);
    	inside2.add(b);
    	inside2.add(changes);
    	changes.add(numbers);
    	changes.add(r);
    }

    public void actionPerformed(ActionEvent e){
    	String event = e.getActionCommand();
    	if (event.equals("Correct")){
    		output.setText(SpellCheck.checkWords(input.getText()));
    	}
    	else{
    		output.setText(SpellCheck.rejects(output.getText(), numbers.getText()));
    	}
    	t.setText(SpellCheck.getChanged());
	    numbers.setText("If you would like to reject any changed words, do so here by entering the index of the word in the list in ''words changed'' (first list is 0, second list is 1, etc.) separated by a space each. DELETE THIS TEXT FIRST!");
    }
}
