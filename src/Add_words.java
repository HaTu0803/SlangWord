import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add_words extends JFrame implements ActionListener {

    JPanel Panel_Add_words;
    JLabel Label_Add_words;
    JLabel Label_Add_Slang_Word;
    JLabel Label_Add_Definition;
    JTextField Text_Slang_Word;
    JTextField Text_Definition;

    JButton Button_Add_words;
    
    JPanel Panel_Back;
    JButton Button_Back;
    
    Container container;
    SlangWord slangWord;
    Color color;
    public Add_words(SlangWord s) throws Exception {
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("ADD WORDS");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        color = new Color(255,227,225);

        Panel_Add_words = new JPanel();
        Panel_Add_words.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Add_words.setLayout(null);


        Font font = new Font("Times New Roman", Font.BOLD,25);
        Font font1 = new Font("Times New Roman", Font.PLAIN,15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);


        // Tiêu đề
        Label_Add_words = new JLabel("ADD SLANG WORD");
        
        Label_Add_words.setBounds(180, 50, 400, 40);
        Label_Add_words.setFont(font);

        Label_Add_Slang_Word = new JLabel("Slang Word");
        Label_Add_Slang_Word.setFont(font2);
        Label_Add_Slang_Word.setBounds(30, 150, 200, 40);
        Label_Add_Definition = new JLabel("Definition");
        Label_Add_Definition.setBounds(30, 200, 200, 40);
        Label_Add_Definition.setFont(font2);
        //Nhập text
        Text_Slang_Word = new JTextField("");
        Text_Slang_Word.setBounds(150, 150, 350, 40);
        Text_Slang_Word.setFont(font1);

        Text_Definition = new JTextField("");
        Text_Definition.setBounds(150, 210, 350, 40);
        Text_Definition.setFont(font1);

        //Button search
        Button_Add_words = new JButton("Add");
        Button_Add_words.setBounds(560, 170, 100, 30);
        Button_Add_words.setFont(font2);
        Button_Add_words.addActionListener(this);
        Button_Add_words.setBackground(color);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setBackground(color);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        add(Panel_Back,BorderLayout.SOUTH);

        Panel_Add_words.add(Label_Add_words);
        Panel_Add_words.add(Label_Add_Slang_Word);
        Panel_Add_words.add(Label_Add_Definition);
        Panel_Add_words.add(Text_Slang_Word);
        Panel_Add_words.add(Text_Definition);
        Panel_Add_words.add(Button_Add_words);

        Panel_Add_words.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Panel_Add_words, BorderLayout.NORTH);
    }
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == Button_Add_words){
        		String S = Text_Slang_Word.getText();
    			String D = Text_Definition.getText();
    			if(S.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Please enter Slang Word", "Attention",JOptionPane.ERROR_MESSAGE);
                    return;
                }
    			if(D.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Please enter Definition", "Attention",JOptionPane.ERROR_MESSAGE);
                    return;
                }
    			if(slangWord.hasSlang(S)) {
    				String[] options = {"Duplicate", "Overwrite"};
    				JOptionPane pane = new JOptionPane();
    				int choice = pane.showOptionDialog(null, "This slang has already existed", "Existed slang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
    				if(choice == 0) {
    					slangWord.addDefinition(S, D);
    				}
    				else {
    					slangWord.addNew(S, D);
    				}
                    JOptionPane.showMessageDialog(null, "Added successfully");
    			}
    			else {
    				slangWord.addNew(S, D);
    				JOptionPane.showMessageDialog(null, "Added successfully");
    			}
    			slangWord.Save();
        	}
        	else if(e.getSource() == Button_Back){
                this.dispose();
                new Menu();
            }
        }
    }


