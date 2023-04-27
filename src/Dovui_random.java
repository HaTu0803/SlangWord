import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class Dovui_random extends JFrame implements ActionListener {
	JLabel solution;
	JLabel Word;
	JButton Button_Start_Game;
    JPanel Panel_Back;
    JButton Button_Back;
    JLabel Label1;
    
    Container container;
    SlangWord slangWord;
    
    JRadioButton A;
	JRadioButton B;
	JRadioButton C;
	JRadioButton D;
	
	JPanel Panel_Dovui_definition;

	
	ButtonGroup Answer_Group;
	JPanel Panel_Answer;
	Color color;
	public Dovui_random(SlangWord s) throws Exception {
		slangWord = s;
        container = this.getContentPane();
        this.setTitle("QUIZ SLANG");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		color = new Color(255,227,225);
        Panel_Dovui_definition = new JPanel();
        Panel_Dovui_definition.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Dovui_definition.setLayout(null);
        
        Font font = new Font("Times New Roman", Font.BOLD, 25);
		Font font1 = new Font("Times New Roman", Font.PLAIN, 15);
		Font font2 = new Font("Times New Roman", Font.BOLD,16);

		Button_Start_Game = new JButton("Start Game");
		Button_Start_Game.setBounds(280, 500, 150, 50);
		Button_Start_Game.setFont(font2);
		Button_Start_Game.addActionListener(this);
		Button_Start_Game.setBackground(color);

		Panel_Answer = new JPanel();
		Panel_Answer.setLayout(new BoxLayout(Panel_Answer, BoxLayout.X_AXIS));
		Answer_Group = new ButtonGroup();
		A = new JRadioButton();
		B = new JRadioButton();
		C = new JRadioButton();
		D = new JRadioButton();
		
		A.addActionListener(this);
		B.addActionListener(this);
		C.addActionListener(this);
		D.addActionListener(this);
		Answer_Group.add(A);
		Answer_Group.add(B);
		Answer_Group.add(C);
		Answer_Group.add(D);
		
		Panel_Answer.add(A);
		Panel_Answer.add(B);
		Panel_Answer.add(C);
		Panel_Answer.add(D);
		Panel_Answer.setBounds(40, 260, 680, 40);
		Panel_Answer.setVisible(false);
		
		Word = new JLabel();
		Word.setBounds(180, 100, 400, 30);
	
		Label1 = new JLabel();
		Label1.setBounds(180, 130, 400, 30);
	
		solution = new JLabel("");
		solution.setBounds(180, 170, 400, 30);
		
		Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
		Button_Back.setBackground(color);
        add(Panel_Back,BorderLayout.SOUTH);
		
        Panel_Dovui_definition.add(Button_Start_Game);
		Panel_Dovui_definition.add(Word);
		Panel_Dovui_definition.add(Panel_Answer);
		Panel_Dovui_definition.add(Label1);
		Panel_Dovui_definition.add(solution);
		
		Panel_Dovui_definition.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    add(Panel_Dovui_definition, BorderLayout.NORTH);
	}
	String key = "";
	boolean isSlang = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		A.setEnabled(true);
		B.setEnabled(true);
		C.setEnabled(true);
		D.setEnabled(true);
		solution.setText("");
		// TODO Auto-generated method stub
		if(e.getSource() == Button_Start_Game){
			Answer_Group.clearSelection();
			isSlang = true;
			
			Label1.setText("");
			Random r = new Random();
			HashMap<String, HashSet<String>> myGame = slangWord.slangGame();
			
			ArrayList<String> slangs = new ArrayList<String>(myGame.keySet());
			key = slangs.get(r.nextInt(slangs.size()));
			Word.setText("Slang: " + key);
			int count = 1;
			for (Map.Entry<String, HashSet<String>> entry : myGame.entrySet()) {
				HashSet<String> value = entry.getValue();
				if (count == 1) {					
					A.setText(value.iterator().next());
				}
				else if (count == 2) {
					B.setText(value.iterator().next());
				}
				else if (count == 3) {
					C.setText(value.iterator().next());
				}
				else {
					D.setText(value.iterator().next());
				}
				count++;
				Panel_Answer.setVisible(true);
			}
    	}
    	else if(e.getSource() == Button_Back){
            this.dispose();
            new Menu();
        }
		if(A.isSelected()) {
			B.setEnabled(false);
			C.setEnabled(false);
			D.setEnabled(false);
			Label1.setText("");
			boolean isCorrect = false;
			String sol = "";
			if (isSlang) {			
				sol = slangWord.searchSlang(key).iterator().next();			
				if (A.getText() == sol) {
					isCorrect = true;
				}
			}
			else {
				sol = slangWord.searchDefinition(key).iterator().next();
				if (A.getText() == sol) {
					isCorrect = true;
				}
			}
			if (isCorrect) {
				Label1.setText("Correct");
				Label1.setForeground(new Color(0,102,0));
			}
			else {
				Label1.setForeground(Color.RED);
				Label1.setText("Incorrect");
				solution.setText("Answer: " + sol);
				solution.setForeground(new Color(0,102,0));
			}
		}
		if(B.isSelected()) {
			A.setEnabled(false);
			C.setEnabled(false);
			D.setEnabled(false);
			Label1.setText("");
			boolean isCorrect = false;
			String sol = "";
			if (isSlang) {			
				sol = slangWord.searchSlang(key).iterator().next();
				if (B.getText() == sol) {
					isCorrect = true;
				}
			}
			else {
				sol = slangWord.searchDefinition(key).iterator().next();
				if (B.getText() == sol) {
					isCorrect = true;
				}
			}
			if (isCorrect) {
				Label1.setText("Correct");
				Label1.setForeground(new Color(0,102,0));
			}
			else {
				Label1.setForeground(Color.RED);
				Label1.setText("Incorrect");
				solution.setText("Answer: " + sol);
				solution.setForeground(new Color(0,102,0));
			}
		}
		if(C.isSelected()) {
			A.setEnabled(false);
			B.setEnabled(false);
			D.setEnabled(false);
			Label1.setText("");
			boolean isCorrect = false;
			String sol = "";
			if (isSlang) {			
				sol = slangWord.searchSlang(key).iterator().next();			
				if (C.getText() == sol) {
					isCorrect = true;
				}
			}
			else {
				sol = slangWord.searchDefinition(key).iterator().next();
				if (C.getText() == sol) {
					isCorrect = true;
				}
			}
			if (isCorrect) {
				Label1.setText("Correct");
				Label1.setForeground(new Color(0,102,0));
			}
			else {
				Label1.setForeground(Color.RED);
				Label1.setText("Incorrect");
				solution.setText("Answer: " + sol);
				solution.setForeground(new Color(0,102,0));
			}
		}
		if(D.isSelected()) {
			A.setEnabled(false);
			B.setEnabled(false);
			C.setEnabled(false);
			Label1.setText("");
			boolean isCorrect = false;
			String sol = "";
			if (isSlang) {			
				sol = slangWord.searchSlang(key).iterator().next();			
				if (D.getText() == sol) {
					isCorrect = true;
				}
			}
			else {
				sol = slangWord.searchDefinition(key).iterator().next();
				if (D.getText() == sol) {
					isCorrect = true;
				}
			}
			if (isCorrect) {
				Label1.setText("Correct");
				Label1.setForeground(new Color(0,102,0));
			}
			else {
				Label1.setForeground(Color.RED);
				Label1.setText("Incorrect");
				solution.setText("Answer: " + sol);
				solution.setForeground(new Color(0,102,0));
			}
		}
	}
	
}
