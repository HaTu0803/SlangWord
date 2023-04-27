import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Reset_words extends JFrame implements ActionListener {
	JPanel Panel_Reset_words;
	JButton Button_Reset_words;
	
	JPanel Panel_Back;
    JButton Button_Back;
    
    Container container;
    SlangWord slangWord;
    Color color;
	
	public Reset_words(SlangWord s) throws Exception {
		slangWord = s;
        container = this.getContentPane();
        this.setTitle("RESET WORDS");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        color = new Color(255,227,225);
        
        Panel_Reset_words = new JPanel();
        Panel_Reset_words.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Reset_words.setLayout(null);

        Font font = new Font("Times New Roman", Font.BOLD,25);
        Font font1 = new Font("Times New Roman", Font.PLAIN,15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);
        
        Button_Reset_words = new JButton("Reset");
        Button_Reset_words.setBounds(300, 170, 100, 30);
        Button_Reset_words.setFont(font2);
        Button_Reset_words.addActionListener(this);
        Button_Reset_words.setBackground(color);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        Button_Back.setBackground(color);
        add(Panel_Back,BorderLayout.SOUTH);
        
        Panel_Reset_words.add(Button_Reset_words);

        Panel_Reset_words.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Panel_Reset_words, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Button_Reset_words){
			int choice = JOptionPane.showConfirmDialog(this, "Reset all", "Reset", JOptionPane.YES_NO_OPTION);
			if(choice == 0) {
				slangWord.reset();
				JOptionPane.showMessageDialog(null, "Reset successfully");
			}
			slangWord.Save();
    	}
    	else if(e.getSource() == Button_Back){
            this.dispose();
            new Menu();
        }
	}
}
