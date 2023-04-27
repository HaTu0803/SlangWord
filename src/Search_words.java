import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Search_words extends JFrame implements ActionListener {
    Container container;
    JPanel Panel_Search_words;
    SlangWord slangWord;
    JButton Button_Search;
    JLabel Label_Slangword;
    JLabel Label_Search;
    JTextField Text_Slangword;
    
    JPanel Panel_Back;
    JButton Button_Back;
    
    DefaultListModel<String> listModel;
    
    JPanel Panel_Result;
    JList List_Result;
    JScrollPane Scroll_Result;
    
	JPanel Panel_History;
	JScrollPane historyPane;
	JTextArea Area_History;
	
	JPanel Panel_Center;
	
	JButton Button_Clear_history;

    JLabel Label_History;
    JLabel Label_Result;
    Color color;
    public Search_words(SlangWord s) throws Exception{
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("SEARCH BY SLANG WORD");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        color = new Color(255,227,225);
        //Panel_Search_words.setBackground(new Color(255,255,255));


        Panel_Search_words = new JPanel();
        Panel_Search_words.setPreferredSize(new Dimension(650,200 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Search_words.setLayout(null);


        Font font = new Font("Times New Roman", Font.BOLD,25);
        Font font1 = new Font("Times New Roman", Font.PLAIN,15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);


        // Tiêu đề
        Label_Slangword = new JLabel("SEARCH BY SLANG WORD");
        Label_Slangword.setBounds(150, 50, 400, 40);
        Label_Slangword.setFont(font);
        Label_Search = new JLabel("Slang Word");
        Label_Search.setBounds(20, 120, 100, 40);
        Label_Search.setFont(font2);
        //Nhập text
        Text_Slangword = new JTextField("");
        Text_Slangword.setBounds(130, 120, 380, 40);
        Text_Slangword.setFont(font1);

        //Button search
        Button_Search = new JButton("Search");
        Button_Search.setBounds(560, 125, 100, 30);
        Button_Search.setFont(font2);
        Button_Search.addActionListener(this);
        Button_Search.setBackground(color);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        Button_Back.setBackground(color);
        add(Panel_Back,BorderLayout.SOUTH);
        
        Panel_Search_words.add( Label_Slangword);
        Panel_Search_words.add( Label_Search);
        Panel_Search_words.add(Text_Slangword);
        Panel_Search_words.add(Button_Search);
        Panel_Search_words.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(Panel_Search_words,BorderLayout.NORTH);
        
        Button_Clear_history = new JButton("Clear history");
        Button_Clear_history.setBounds(300, 550, 100, 30);
        Button_Clear_history.setFont(font2);
        Button_Clear_history.addActionListener(this);
        Button_Clear_history.setBackground(color);

        Panel_Result = new JPanel();
        Panel_Result.setLayout(new BoxLayout(Panel_Result, BoxLayout.Y_AXIS));

        listModel = new DefaultListModel<String>();
        Label_Result = new JLabel("                Result");
        Label_Result.setFont(font2);
        Label_Result.setBounds(250,180,100,40);

        List_Result = new JList(listModel);
		Scroll_Result = new JScrollPane(List_Result);
		Scroll_Result.setPreferredSize(new Dimension(500, 75));
		Panel_Result.add(Label_Result);
		Panel_Result.add(Scroll_Result);

        Panel_History = new JPanel();
		Panel_History.setLayout(new BoxLayout(Panel_History, BoxLayout.Y_AXIS));
		Area_History = new JTextArea();
		Label_History = new JLabel("           Search history");
        Label_History.setFont(font2);
        Label_History.setBounds(280,250,100,40);
		Area_History.setEditable(false);
		historyPane = new JScrollPane(Area_History);
		historyPane.setPreferredSize(new Dimension(500, 200));
		Panel_History.add(Label_History);
		Panel_History.add(historyPane);
		Panel_History.add(Button_Clear_history);

		LinkedList<String> history = slangWord.getHistory();
		for (Iterator iterator = history.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			Area_History.append("\n" + string);
			
		}
		
		Panel_Center = new JPanel();
		Panel_Center.add(Panel_Result);
		Panel_Center.add(Panel_History);

		add(Panel_Center,BorderLayout.CENTER);

        /*container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(Panel_Search_words);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(Panel_Center);

        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(Button_Back);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(Button_Clear_history);
        container.setBackground(new );*/
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Button_Search){
            String S = Text_Slangword.getText();
            if(S.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter Slang Word", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
            	listModel.removeAllElements();
    			HashSet<String> defSet = slangWord.searchSlang(S);
    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    			Date date = new Date();  
    			String time = formatter.format(date).toString();
    			String history = time + " |   " + S;
    			if (defSet != null) {				
    				for(String s : defSet) {
    					listModel.addElement(s.strip());
    				}
    			}
    			else {
    				JOptionPane.showMessageDialog(this, "Slang is not in dictionary");
    			}
    			slangWord.addHistory(history);
    			Area_History.append("\n" + history);
    			slangWord.Save();
            }
        }
        else if(e.getSource() == Button_Clear_history) {
        	slangWord.clearHistory();
        	slangWord.Save();
        	Area_History.selectAll();
        	Area_History.replaceSelection("");
        }
        else if(e.getSource() == Button_Back){
            this.dispose();
            new Menu();
        }
    }
}
