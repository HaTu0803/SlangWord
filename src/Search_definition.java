import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

public class Search_definition extends JFrame implements ActionListener {
    Container container;
    SlangWord slangWord;
    JPanel Panel_Search_Definition;
    
    JButton Button_Search;
    JLabel Label_Definition;
    JLabel Label_Search;
    JTextField Text_Definition;
    
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
    Color color;
    public Search_definition(SlangWord s){
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("SEARCH BY SLANG DEFINITION");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        color = new Color(255,227,225);


        Panel_Search_Definition = new JPanel();
        Panel_Search_Definition.setPreferredSize(new Dimension(650,200 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Search_Definition.setLayout(null);


        Font font = new Font("Times New Roman", Font.BOLD,25);
        Font font1 = new Font("Times New Roman", Font.PLAIN,15);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);

        // Tiêu đề
        Label_Definition = new JLabel("SEARCH BY DEFINITION");
        Label_Definition.setBounds(150, 50, 400, 40);
        Label_Definition.setFont(font);
        Label_Search = new JLabel("Definition");
        Label_Search.setBounds(20, 150, 100, 40);
        Label_Search.setFont(font2);
        //Nhập text
        Text_Definition = new JTextField("");
        Text_Definition.setBounds(130, 150, 380, 40);
        Text_Definition.setFont(font1);

        //Button search
        Button_Search = new JButton("Search");
        Button_Search.setBounds(560, 155, 100, 30);
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

        Panel_Search_Definition.add( Label_Definition);
        Panel_Search_Definition.add( Label_Search);
        Panel_Search_Definition.add(Text_Definition);
        Panel_Search_Definition.add(Button_Search);
        Panel_Search_Definition.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(Panel_Search_Definition,BorderLayout.NORTH);
        
        Button_Clear_history = new JButton("Clear history");
        Button_Clear_history.setBounds(300, 550, 100, 30);
        Button_Clear_history.setFont(font2);
        Button_Clear_history.addActionListener(this);
        Button_Clear_history.setBackground(color);
        
        Panel_Result = new JPanel();
        Panel_Result.setLayout(new BoxLayout(Panel_Result, BoxLayout.Y_AXIS));
        listModel = new DefaultListModel<String>();
        JLabel Label_Result = new JLabel("Result");
		List_Result = new JList(listModel);
		Scroll_Result = new JScrollPane(List_Result);
		Scroll_Result.setPreferredSize(new Dimension(500, 75));
		Panel_Result.add(Label_Result);
		Panel_Result.add(Scroll_Result);
        
        Panel_History = new JPanel();
		Panel_History.setLayout(new BoxLayout(Panel_History, BoxLayout.Y_AXIS));
		Area_History = new JTextArea();
		JLabel Label_History = new JLabel("                         Search history");
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
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Button_Search){
            String S = Text_Definition.getText();
            if(S.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter Definition", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
            	listModel.removeAllElements();
            	ArrayList<String> slangList = slangWord.searchDefinition(S);
            	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    			Date date = new Date();  
    			String time = formatter.format(date).toString();
    			String history = time + " |   " + S;
    			if (slangList.size() != 0) {				
    				for(String s : slangList) {
    					listModel.addElement(s);
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
