import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit_words extends JFrame implements ActionListener {
    JPanel Panel_Edit_words;
    JLabel Label_Edit_words;
    JLabel Label_Edit_Slang_Word;
    JLabel Label_Edit_OldDefinition;
    JLabel Label_Edit_NewDefinition;
    JTextField Text_Slang_Word;
    JTextField Text_OldDefinition;
    JTextField Text_NewDefinition;

    JButton Button_Edit_words;
    
    JPanel Panel_Back;
    JButton Button_Back;
    
    Container container;
    SlangWord slangWord;
    public Edit_words(SlangWord s) throws Exception {
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("EDDIT WORDS");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    	
        Panel_Edit_words = new JPanel();
        Panel_Edit_words.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Edit_words.setLayout(null);


        Font font = new Font("Times New Roman", Font.BOLD, 25);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);

        // Tiêu đề
        Label_Edit_words = new JLabel("EDIT SLANG WORD");
        Label_Edit_words.setBounds(180, 50, 400, 40);
        Label_Edit_words.setFont(font);

        Label_Edit_Slang_Word = new JLabel("Slang Word");
        Label_Edit_Slang_Word.setFont(font2);
        Label_Edit_Slang_Word.setBounds(30, 150, 200, 40);
        Label_Edit_OldDefinition = new JLabel("Old Definition");
        Label_Edit_OldDefinition.setBounds(30, 200, 200, 40);
        Label_Edit_OldDefinition.setFont(font2);
        
        Label_Edit_NewDefinition = new JLabel("New Definition");
        Label_Edit_NewDefinition.setBounds(30, 250, 200, 40);
        Label_Edit_NewDefinition.setFont(font2);
        //Nhập text
        Text_Slang_Word = new JTextField("");
        Text_Slang_Word.setBounds(150, 150, 350, 40);
        Text_Slang_Word.setFont(font1);

        Text_OldDefinition = new JTextField("");
        Text_OldDefinition.setBounds(150, 210, 350, 40);
        Text_OldDefinition.setFont(font1);
        
        Text_NewDefinition = new JTextField("");
        Text_NewDefinition.setBounds(150, 260, 350, 40);
        Text_NewDefinition.setFont(font1);

        //Button search
        Button_Edit_words = new JButton("Edit");
        Button_Edit_words.setBounds(560, 200, 100, 30);
        Button_Edit_words.setFont(font2);
        Button_Edit_words.addActionListener(this);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        add(Panel_Back,BorderLayout.SOUTH);

        Panel_Edit_words.add(Label_Edit_words);
        Panel_Edit_words.add(Label_Edit_Slang_Word);
        Panel_Edit_words.add(Label_Edit_OldDefinition);
        Panel_Edit_words.add(Label_Edit_NewDefinition);
        Panel_Edit_words.add(Text_Slang_Word);
        Panel_Edit_words.add(Text_OldDefinition);
        Panel_Edit_words.add(Text_NewDefinition);
        Panel_Edit_words.add(Button_Edit_words);

        Panel_Edit_words.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Panel_Edit_words, BorderLayout.NORTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == Button_Edit_words){
    		String S = Text_Slang_Word.getText();
			String DO = Text_OldDefinition.getText();
			String DN = Text_NewDefinition.getText();
			if(S.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter Slang Word", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
			if(DO.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter Old Definition", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
			if(DN.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter New Definition", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
			if(slangWord.hasSlang(S)) {
				if (slangWord.EditSlang(S, DO, DN)) {
					JOptionPane.showMessageDialog(null, "Edit successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Edit fail");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Slang is not in dictionary");
                return;
			}
			slangWord.Save();
    	}
    	else if(e.getSource() == Button_Back){
            this.dispose();
            new Menu();
        }
    }
}
