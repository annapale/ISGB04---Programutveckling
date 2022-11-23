import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;

public class test {
	public static void main(String[] args)throws IOException {
		Window w = new Window();
		
	}
}

class Window implements ActionListener{
	
	private JFrame f;
	private JTextArea t;
	private JScrollPane sb;
	private JMenuBar mainMenu;
	private JMenu menu;
	private JMenuItem slumpa, clear, save, read;
	
	private BufferedReader reader;
	private String in;
	private BufferedWriter writer;
	
	public void read() {
		try {
			reader = new BufferedReader (new InputStreamReader(new BufferedInputStream(new FileInputStream("lotto.txt"))));
			in = reader.readLine();
			while (in != null) {
				t.append(in);
				t.append("\n");
				in = reader.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("File is empty");
		}
	}
	
	public void write() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("lotto.txt"))));
			in = t.getText();
			
				writer.write(in + '\n');
				in = t.getText();
;			
		    writer.close();
		}
		catch (IOException e) {
			System.out.println("issue with file");
		}
	}

	public Window(){
		
		f=new JFrame("Lotto");
		t=new JTextArea();
		sb=new JScrollPane(t);
		
		mainMenu = new JMenuBar();
		menu = new JMenu("Menu");
		slumpa = new JMenuItem("slumpa"); 
		clear = new JMenuItem("clear");
		save = new JMenuItem("save");
		read = new JMenuItem("read");
		
		menu.add(slumpa);
		menu.add(clear);
		menu.add(save);
		menu.add(read);
		menu.addSeparator();
		mainMenu.add(menu);
		f.setJMenuBar(mainMenu);
		
		slumpa.addActionListener(this);
		clear.addActionListener(this);
		save.addActionListener(this);
		read.addActionListener(this);
		
		f.getContentPane().add(sb);
		f.setSize(250,200);
		f.setLocation(100,100);
		f.setVisible(true);
	}
	
	public void print(int[] lotto) {
		for(int i = 0; i < 7; i++) {
			t.append(lotto[i] + " ");
		}
		t.append("\n");
	}
	
	public void clear() {
		System.out.println("clear");
		t.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == slumpa) {
			int lotto[] = new int[7];
			
			for(int i = 0; i < 7; i++) {
				Random nr = new Random();
			    lotto[i] = nr.nextInt(35);
			}
			print(lotto);	
		}
		
		else if(e.getSource() == clear) {
			clear();
		}
		
		else if(e.getSource() == save) {
			write();
		}
		
		else if(e.getSource() == read) {
			read();
		}
		
		
}
		
	public void windowClosing (WindowEvent e) {
		System.exit(1);
	}
	
	
	}
	


  
