import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete_words extends JFrame implements ActionListener {
    JPanel Panel_Delete_words;
    JLabel Label_Delete_words;
    JLabel Label_Delete_Slang_Word;
    JTextField Text_Slang_Word;

    JButton Button_Delete_words;
    
    JPanel Panel_Back;
    JButton Button_Back;
    
    Container container;
    SlangWord slangWord;
    public Delete_words(SlangWord s) throws Exception {
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("DELETE WORDS");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    	
        Panel_Delete_words = new JPanel();
        Panel_Delete_words.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Delete_words.setLayout(null);


        Font font = new Font("Times New Roman", Font.BOLD, 25);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);

        // Tiêu đề
        Label_Delete_words = new JLabel("DELETE SLANG WORD");
        Label_Delete_words.setBounds(180, 50, 400, 40);
        Label_Delete_words.setFont(font);

        Label_Delete_Slang_Word = new JLabel("Slang Word");
        Label_Delete_Slang_Word.setFont(font2);
        Label_Delete_Slang_Word.setBounds(30, 150, 200, 40);

        //Nhập text
        Text_Slang_Word = new JTextField("");
        Text_Slang_Word.setBounds(150, 150, 350, 40);
        Text_Slang_Word.setFont(font1);

        //Button search
        Button_Delete_words = new JButton("Delete");
        Button_Delete_words.setBounds(560, 150, 100, 30);
        Button_Delete_words.setFont(font2);
        Button_Delete_words.addActionListener(this);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        add(Panel_Back,BorderLayout.SOUTH);

        Panel_Delete_words.add(Label_Delete_words);
        Panel_Delete_words.add(Label_Delete_Slang_Word);
        Panel_Delete_words.add(Text_Slang_Word);
        Panel_Delete_words.add(Button_Delete_words);

        Panel_Delete_words.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Panel_Delete_words, BorderLayout.NORTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == Button_Delete_words){
    		String S = Text_Slang_Word.getText();
			if(S.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter Slang Word", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
			if(slangWord.hasSlang(S)) {
				int choice = JOptionPane.showConfirmDialog(this, "This slang will remove from dictionary", "Delete", JOptionPane.YES_NO_OPTION);
				if(choice == 0) {
					slangWord.deleteSlang(S);
					JOptionPane.showMessageDialog(null, "Delete successfully");
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
