import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.SOUTH;

public class Menu extends JFrame implements ActionListener {
    Container container;
    SlangWord slangWord;

    JPanel Panel_Menu;
    JPanel Panel_Menu1;
    JButton BT1, BT2, BT3, BT4, BT5, BT6, BT7, BT8, BT9, BT10;
    JLabel Label_Menu;
    Color color;

    public Menu(){
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		this.setTitle("Slang word | 20126031 | Hà Thị Thanh Tú | KTPM02");
 		this.setVisible(true);
 		this.setSize(700, 700);
 		this.setLocationRelativeTo(null);
        color = new Color(255,227,225);


 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        Font font = new Font("Arial", Font.BOLD, 15);
        Font font1 = new Font("Arial", Font.BOLD, 25);

        Label_Menu = new JLabel("MENU");
        Label_Menu.setForeground(Color.BLACK);
        Label_Menu.setFont(font1);
        Label_Menu.setAlignmentX(CENTER_ALIGNMENT);
        //Label_Search.setLayout(new BoxLayout(Label_Search, BoxLayout.Y_AXIS));


        BT1 = new JButton("Search by Slang word");
        BT1.setFont(font);
        BT1.addActionListener(this);
        BT1.setFocusable(false);
        BT1.setBackground(color);

        BT2 = new JButton("Search by Definition");
        BT2.setFont(font);
        BT2.addActionListener(this);
        BT2.setFocusable(false);
        BT2.setBackground(color);

        BT3 = new JButton("Add Slang Word");
        BT3.setFont(font);
        BT3.addActionListener(this);
        BT3.setFocusable(false);
        BT3.setBackground(color);

        BT4 = new JButton("Edit Slang Word");
        BT4.setFont(font);
        BT4.addActionListener(this);
        BT4.setFocusable(false);
        BT4.setBackground(color);

        BT5 = new JButton("Delete Slang Word");
        BT5.setFont(font);
        BT5.addActionListener(this);
        BT5.setFocusable(false);
        BT5.setBackground(color);

        BT6 = new JButton("Reset Slang Word");
        BT6.setFont(font);
        BT6.addActionListener(this);
        BT6.setFocusable(false);
        BT6.setBackground(color);

        BT7 = new JButton("On this day");
        BT7.setFont(font);
        BT7.addActionListener(this);
        BT7.setFocusable(false);
        BT7.setBackground(color);

        BT8 = new JButton("Funny question Random");
        BT8.setFont(font);
        BT8.addActionListener(this);
        BT8.setFocusable(false);
        BT8.setBackground(color);

        BT9 = new JButton("Funny question Definition");
        BT9.setFont(font);
        BT9.addActionListener(this);
        BT9.setFocusable(false);
        BT9.setBackground(color);

        BT10 = new JButton("");
        BT10.setFont(font);
        BT10.addActionListener(this);
        BT10.setFocusable(false);
        BT10.setBackground(new Color(255,255,255));

        Panel_Menu = new JPanel();
        Panel_Menu1 = new JPanel();
        Panel_Menu.setLayout(new GridLayout(5,2));
        Panel_Menu.add(BT1);
        Panel_Menu.add(BT2);
        Panel_Menu.add(BT3);
        Panel_Menu.add(BT4);
        Panel_Menu.add(BT5);
        Panel_Menu.add(BT6);
        Panel_Menu.add(BT7);
        Panel_Menu.add(BT8);

        Panel_Menu.add(BT9);
        Panel_Menu.add(BT10);

        //Color Blue = new Color(255,159,159);
        //Panel_Menu.setForeground(Blue);

        Dimension size2 = new Dimension(600, 500);
        Panel_Menu.setMaximumSize(size2);
        Panel_Menu.setPreferredSize(size2);
        Panel_Menu.setMinimumSize(size2);
        Panel_Menu.setPreferredSize(new Dimension(600, 400));
        container = this.getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.setBackground(new Color(255,255,255));
        container.add(Label_Menu);
        container.add(Panel_Menu);
        slangWord = new SlangWord();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BT1){
            this.dispose();
            try{
                new Search_words(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == BT2) {
            this.dispose();
            try{
                new Search_definition(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT3) {
            this.dispose();
            try {
                new Add_words(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT4) {
            this.dispose();
            try {
                new Edit_words(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == BT5) {
            this.dispose();
            try {
                new Delete_words(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT6) {
            this.dispose();
            try {
                new Reset_words(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT7) {
            this.dispose();
            try {
                new On_this_day(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT8) {
            this.dispose();
            try {
                new Dovui_random(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == BT9) {
            this.dispose();
            try {
                new Dovui_definition(slangWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
