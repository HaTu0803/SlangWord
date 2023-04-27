import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class On_this_day extends JFrame implements ActionListener {
    JPanel Panel_Random;
    JButton Button_Random;
    JLabel Label_Random;
    JLabel Slangword_Random;
    JLabel Definition_R;
    

    JPanel Panel_Back;
    JButton Button_Back;
    
    Container container;
    SlangWord slangWord;
    Color color;
    public On_this_day(SlangWord s) throws Exception {
    	slangWord = s;
        container = this.getContentPane();
        this.setTitle("ON THIS DAY");
        this.setSize(700,700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        color = new Color(255,227,225);
        
        Panel_Random = new JPanel();
        Panel_Random.setPreferredSize(new Dimension(700, 600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        Panel_Random.setLayout(null);
        
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 16);
        Font font2 = new Font("Times New Roman", Font.BOLD,16);

        Label_Random = new JLabel("WORD FOR TO DAY IS: ");
        Label_Random.setBounds(240, 80, 400, 40);
        Label_Random.setFont(font);
        
        Slangword_Random = new JLabel();
        Slangword_Random.setBounds(300, 230, 400, 40);
        Slangword_Random.setFont(font1);
        Slangword_Random.setForeground(Color.RED);
        
        Definition_R = new JLabel();
        Definition_R.setBounds(300, 270, 400, 40);
        Definition_R.setFont(font1);
        
        
        Button_Random = new JButton("Get a word");
        Button_Random.setBounds(290, 150, 150, 50);
        Button_Random.setFont(font2);
        Button_Random.addActionListener(this);
        Button_Random.setBackground(color);
        
        Panel_Back = new JPanel();

        Button_Back = new JButton("Back");
        Button_Back.setFocusable(false);
        Panel_Back.add(Button_Back);
        Button_Back.addActionListener(this);
        Button_Back.setAlignmentX(BOTTOM_ALIGNMENT);
        Button_Back.setBackground(color);
        add(Panel_Back,BorderLayout.SOUTH);
        
        Panel_Random.add(Label_Random);
        Panel_Random.add(Slangword_Random);
        Panel_Random.add(Definition_R);
        Panel_Random.add(Button_Random);
        
        Panel_Random.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Panel_Random, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == Button_Random){
    		String SR = slangWord.randomSlang();
    		Slangword_Random.setText(SR);
			String defs = "";
			for (Iterator iterator = slangWord.searchSlang(Slangword_Random.getText()).iterator(); iterator.hasNext();) {
				String str = (String) iterator.next();
				defs += str + ", ";
			}
			Definition_R.setText(defs.substring(0, defs.length()-2));
    	}
    	else if(e.getSource() == Button_Back){
            this.dispose();
            new Menu();
        }
    }
}
