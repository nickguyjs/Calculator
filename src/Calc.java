import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JPanel {
	
	private JFrame frame; 
	private static GraphicsConfiguration gc;
	// the String version of the double value displayed on the screen
	private String display;
	// the JLabel of the display String
	private static JLabel d;
	// the currentNum the user is typing in
	private String currentNum;
	// the ArrayList of nums to operate
	private double prevNum;
	
	private boolean plusC;
	private boolean minusC;
	private boolean divideC;
	private boolean timesC;
	
	// a boolean that is turned to false after = is pressed, will be turned true after the screen is cleared
	private boolean canEdit;
	
	public Calc() {
		display = Integer.toString(0);
		frame = new JFrame(gc);	
		currentNum = "";
		prevNum = 0;
		
		plusC = false;
		minusC = false;
		divideC = false;
		timesC = false;
		
		canEdit = true;
		// sets the initial value on calculator
		
		d = new JLabel(display);
		//d = new JLabel("000,000,000.0"); // FONT 50 (12 characters)
		//d = new JLabel("000,000,000.0000"); // FONT 40 (16 characters)
		//d = new JLabel("000,000,000.0000000000"); // FONT 30 (22 characters)
		//d = new JLabel("000,000,000.000000000000000000000"); // FONT 20 (33 characters)

		d.setBounds(5, 25, 406, 50);
		d.setFont(new Font("Courier", Font.PLAIN, 50));
		frame.getContentPane().add(d);
		
		// adds buttons, sets basics
		setButtons();
		setBasics("Calculator", 406, 529, true);

	}
	
	public void setBasics(String name, int x, int y, boolean visible) {
		frame.setTitle(name);
		frame.setSize(x, y);
		frame.setLayout(null);
		frame.setVisible(visible);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	public void setScreen(String value) {
		d.setText(value);
		if(value.length() <= 12) {
			d.setFont(new Font("Courier", Font.PLAIN, 50));
		} else if(value.length() <= 16 && value.length() > 12) {
			d.setFont(new Font("Courier", Font.PLAIN, 40));
		} else if(value.length() <= 22 && value.length() > 16) {
			d.setFont(new Font("Courier", Font.PLAIN, 30));
		} else if(value.length() <= 33 && value.length() > 22) {
			d.setFont(new Font("Courier", Font.PLAIN, 20));
		} else {
			d.setFont(new Font("Courier", Font.PLAIN, 20));
		}
		super.update(this.getGraphics());
	}
	
	public void editCurrentNum(char c) {
		
		if(!canEdit) {
			clear();
		}
		if(currentNum.length() == 1) {
			if(c != '.' && currentNum.charAt(0) == '0') {
				currentNum = "";
			}
		}
		currentNum += "" + c;
		setScreen(currentNum);
	}
	
	public String getCurrentNum() {
		return currentNum;
	}
	
	public void clear() {
		prevNum = 0;
		currentNum = "0";
		d.setText("0");
		super.update(this.getGraphics());
		canEdit = true;
	}
	
	public void operation(String op) {
		if(!canEdit) {
			canEdit = true;
		}
		d.setText("0");
		d.setFont(new Font("Courier", Font.PLAIN, 50));
		super.update(this.getGraphics());
		
		if(currentNum.charAt(currentNum.length()-1) == '.') {
			currentNum = currentNum.substring(0, currentNum.length()-1);
		}
	
		
		// implement if not plus thing, then move on
		if(plusC) {
			currentNum = Double.toString(Double.parseDouble(currentNum) + prevNum);
			plusC = false;
		} else if(minusC) {
			currentNum = Double.toString(Double.parseDouble(currentNum) - prevNum);
			minusC = false;
		} else if(divideC) {
			currentNum = Double.toString(prevNum / Double.parseDouble(currentNum));
			divideC = false;
		} else if(timesC) {
			currentNum = Double.toString(Double.parseDouble(currentNum) * prevNum);
			timesC = false;
		}
		prevNum = Double.parseDouble(currentNum);
		currentNum = "0";
		if(op.equals("plus")) {
			plusC = true;
		} else if(op.equals("minus")) {
			minusC = true;
		} else if(op.equals("times")) {
			timesC = true;
		} else if(op.equals("divide")) {
			divideC = true;
		}
	}
	
	public void goEquals() {
		canEdit = false;
		if(currentNum.charAt(currentNum.length()-1) == '.') {
			currentNum = currentNum.substring(0, currentNum.length()-1);
		}
	
		if(plusC) {
			System.out.println(currentNum);
			currentNum = Double.toString(Double.parseDouble(currentNum) + prevNum);
			plusC = false;
		} else if(minusC) {
			System.out.println(currentNum + ", " + prevNum);
			currentNum = Double.toString(prevNum - Double.parseDouble(currentNum));
			minusC = false;
		} else if(divideC) {
			currentNum = Double.toString(prevNum / Double.parseDouble(currentNum));
			divideC = false;
		} else if(timesC) {
			System.out.println(Double.parseDouble(currentNum));
			currentNum = Double.toString(Double.parseDouble(currentNum) * prevNum);
			timesC = false;
		}
		
		mySetText(currentNum);
		super.update(this.getGraphics());
	}
	
	public void plusMinus() {
		currentNum = Double.toString(Double.parseDouble(currentNum)*-1);
		mySetText(currentNum);
	}
	
	public void mySetText(String text) {        
		DecimalFormat df = new DecimalFormat("0.#####################");
		d.setText("" + df.format(Double.parseDouble(currentNum)));
		System.out.println(currentNum);
		//d = new JLabel("000,000,000.0"); // FONT 50 (12 characters)
		//d = new JLabel("000,000,000.0000"); // FONT 40 (16 characters)
		//d = new JLabel("000,000,000.0000000000"); // FONT 30 (22 characters)
		//d = new JLabel("000,000,000.000000000000000000000"); // FONT 20 (33 characters)
		if(text.length() <= 12) {
			d.setFont(new Font("Courier", Font.PLAIN, 50));
		} else if(text.length() <= 16 && text.length() > 12) {
			d.setFont(new Font("Courier", Font.PLAIN, 40));
		} else if(text.length() <= 22 && text.length() > 16) {
			d.setFont(new Font("Courier", Font.PLAIN, 30));
		} else if(text.length() <= 33 && text.length() > 22) {
			d.setFont(new Font("Courier", Font.PLAIN, 20));
		} else {
			d.setFont(new Font("Courier", Font.PLAIN, 20));
		}
	}
	
	public void setButtons() {
		JButton one = new JButton("1");
		one.setBackground(Color.ORANGE);
		JButton two = new JButton("2");    
		two.setBackground(Color.ORANGE);
		JButton three = new JButton("3");    
		three.setBackground(Color.ORANGE);
		JButton four = new JButton("4");    
		four.setBackground(Color.ORANGE);
		JButton five = new JButton("5");    
		five.setBackground(Color.ORANGE);
		JButton six = new JButton("6");    
		six.setBackground(Color.ORANGE);
		JButton seven = new JButton("7");    
		seven.setBackground(Color.ORANGE);
		JButton eight = new JButton("8");    
		eight.setBackground(Color.ORANGE);
		JButton nine = new JButton("9");    
		nine.setBackground(Color.ORANGE);
		JButton zero = new JButton("0");
		zero.setBackground(Color.ORANGE);

		JButton plus = new JButton("+");
		plus.setBackground(Color.GRAY);
		JButton minus = new JButton("-");
		minus.setBackground(Color.GRAY);
		JButton divide = new JButton("/");
		divide.setBackground(Color.GRAY);
		JButton times = new JButton("X");
		times.setBackground(Color.GRAY);
		JButton equals = new JButton("=");
		equals.setBackground(Color.GRAY);
		JButton clear = new JButton("Clear");
		clear.setBackground(Color.GRAY);
		JButton decimal = new JButton(".");
		decimal.setBackground(Color.GRAY);
		JButton pm = new JButton("+/-");
		pm.setBackground(Color.GRAY);
		
		one.setBounds(0, 100, 100, 100);
		four.setBounds(0, 200, 100, 100);    
		seven.setBounds(0, 300, 100, 100);
		equals.setBounds(0, 400, 100, 50);
		pm.setBounds(0, 450, 100, 50);

		two.setBounds(100, 100, 100, 100);    
		five.setBounds(100, 200, 100, 100);    
		eight.setBounds(100, 300, 100, 100);
		zero.setBounds(100, 400, 100, 100);
		
		three.setBounds(200, 100, 100, 100);    
		six.setBounds(200, 200, 100, 100);    
		nine.setBounds(200, 300, 100, 100);
		clear.setBounds(200, 400, 100, 50);
		decimal.setBounds(200, 450, 100, 50);
				
		divide.setBounds(300, 100, 100, 100);
		plus.setBounds(300, 200, 100, 100);
		minus.setBounds(300, 300, 100, 100);
		times.setBounds(300, 400, 100, 100);
		
		one.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e) {	System.out.println("one Pressed"); editCurrentNum('1');	}  
		}); 
		two.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("two Pressed"); editCurrentNum('2'); }  
		}); 
		three.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("three Pressed"); editCurrentNum('3'); }  
		}); 
		four.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("four Pressed"); editCurrentNum('4'); }  
		}); 
		five.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("five Pressed"); editCurrentNum('5'); }  
		});
		six.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("six Pressed"); editCurrentNum('6'); }  
		}); 
		seven.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("seven Pressed"); editCurrentNum('7'); }  
		}); 
		eight.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("eight Pressed"); editCurrentNum('8'); }  
		}); 
		nine.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("nine Pressed"); editCurrentNum('9'); }  
		}); 
		zero.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("zero Pressed"); editCurrentNum('0'); }  
		}); 
		
		clear.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("clear Pressed"); clear(); }  
		}); 
		plus.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("plus Pressed");	operation("plus"); }  
		}); 
		minus.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("minus Pressed"); operation("minus"); }  
		}); 
		equals.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("equals Pressed"); goEquals(); }  
		}); 
		divide.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("divide Pressed"); operation("divide"); }  
		}); 
		times.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("times Pressed"); operation("times");}  
		}); 
		decimal.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("decimal Pressed");	editCurrentNum('.'); }  
		}); 
		pm.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {	System.out.println("pm Pressed"); plusMinus(); }  
		}); 
		
		frame.add(one);
		frame.add(two);
		frame.add(three);
		frame.add(four);
		frame.add(five);
		frame.add(six);
		frame.add(seven);
		frame.add(eight);
		frame.add(nine);
		frame.add(zero);
		
		frame.add(clear);
		frame.add(plus);
		frame.add(minus);
		frame.add(equals);
		frame.add(divide);
		frame.add(times);
		frame.add(decimal);  
		frame.add(pm);
	}
}
