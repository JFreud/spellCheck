// thanks Mr. K for the bulk of this code!

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JFrame implements ActionListener{
    private Container pane, inside, inside1, inside2;
    private JLabel j, k, l;
    private JTextField t, output, input;

    public Window() {
    	this.setTitle("Spell Checker");
    	this.setSize(600,400);
    	this.setLocation(100,100);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    	pane = this.getContentPane();
    	inside = new Container();
    	inside1 = new Container();
    	inside2 = new Container();
    	pane.setLayout(new BorderLayout(10, 20));
    	inside.setLayout(new GridLayout());
        inside1.setLayout(new GridLayout());
    	inside2.setLayout(new BoxLayout(inside2, BoxLayout.PAGE_AXIS));
    	JButton b = new JButton("Spell check!");
    	b.addActionListener(this);
    	b.setActionCommand("Correct");
    	t = new JTextField(2);
    	output = new JTextField(10);
    	input = new JTextField(10);
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
    }

    public void actionPerformed(ActionEvent e){
	output.setText(SpellCheck.checkWords(input.getText()));
	t.setText(SpellCheck.getChanged());
	//output.setText(input.getText());
	//t.setText("no changed words yet!");
//SpellCheck.inputtedToArray(input.getText());

      //SpellCheck.checkWords();
    }
}
