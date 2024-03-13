package View;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Panel_Select_Sort extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private MainFrame mainFrame;
	private String language;
	public Panel_Select_Sort(float WIDTH, float HEIGHT,MainFrame mainFrame) {
		language = new String("English");
		this.mainFrame = mainFrame;
		this.setLayout(null);
		setBackground(List_Color.PANEL_BUTTON);
		this.setSize(192,900);
		
		init_1();
	}

	public void init_1() {
		JComboBox select_1 = new JComboBox();
		select_1.setFont(new Font("Arial", Font.BOLD, 16));
		select_1.setModel(new DefaultComboBoxModel(new String[] {"Selection Sort","Insertion Sort","Bubble Sort","QuickSort","Merge Sort","None"}));
		select_1.setBounds(20, 160, 150, 40);
		add(select_1);
		
		JComboBox select_2 = new JComboBox();
		select_2.setFont(new Font("Arial", Font.BOLD, 16));
		select_2.setModel(new DefaultComboBoxModel(new String[] {"Selection Sort","Insertion Sort","Bubble Sort","QuickSort","Merge Sort","None"}));
		select_2.setBounds(20, 530, 150, 40);
		add(select_2);
		
		MyActionListener actionListener = new MyActionListener(mainFrame);
		MyActionListener_2 actionListener_2 = new MyActionListener_2(mainFrame);
		select_1.addActionListener(actionListener);
		select_2.addActionListener(actionListener_2);
	}
	public void init_2() {
		JComboBox select_1 = new JComboBox();
		select_1.setFont(new Font("Arial", Font.BOLD, 16));
		select_1.setModel(new DefaultComboBoxModel(new String[] {"Sắp xếp chọn","Sắp xếp xen","Sắp xếp nổi bọt","Sắp xếp nhanh","Sắp xếp trộn","Không"}));
		select_1.setBounds(20, 160, 150, 40);
		add(select_1);
		
		JComboBox select_2 = new JComboBox();
		select_2.setFont(new Font("Arial", Font.BOLD, 16));
		select_2.setModel(new DefaultComboBoxModel(new String[] {"Sắp xếp chọn","Sắp xếp xen","Sắp xếp nổi bọt","Sắp xếp nhanh","Sắp xếp trộn","Không"}));
		select_2.setBounds(20, 530, 150, 40);
		add(select_2);
		
		MyActionListener actionListener = new MyActionListener(mainFrame);
		MyActionListener_2 actionListener_2 = new MyActionListener_2(mainFrame);
		select_1.addActionListener(actionListener);
		select_2.addActionListener(actionListener_2);
	}
	
	public void set_Language(String language) {
		this.language = language;
		this.removeAll();
		if (language.equals("English")) {
			init_1();
		}else init_2();
	}
	
	public void Change_Display(String color) {
		if (color.equals("white")){
			this.setBackground(List_Color.BACKGROUND_WHITE);
		}else {
			this.setBackground(List_Color.BACKGROUND);
		}
	}
}
	class MyActionListener implements ActionListener {
		  Object oldItem;
		  private MainFrame mainFrame;
		  
		  public MyActionListener(MainFrame mainFrame) {
			  this.mainFrame = mainFrame;
		  }
		  
		  public void actionPerformed(ActionEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();
		    Object newItem = cb.getSelectedItem();

		    boolean same = newItem.equals(oldItem);
		    oldItem = newItem;

		    if ("comboBoxEdited".equals(evt.getActionCommand())) {
		    	
		    } else if ("comboBoxChanged".equals(evt.getActionCommand())) {	
		    	mainFrame.Click_select_1((String)newItem);
		    }
		  }
		  public interface Listener_Button
			{
				void Click_select_1(String name);
			}
}
	class MyActionListener_2 implements ActionListener {
		  Object oldItem;
		  private MainFrame mainFrame;
		  
		  public MyActionListener_2(MainFrame mainFrame) {
			  this.mainFrame = mainFrame;
		  }
		  
		  public void actionPerformed(ActionEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();
		    Object newItem = cb.getSelectedItem();

		    boolean same = newItem.equals(oldItem);
		    oldItem = newItem;

		    if ("comboBoxEdited".equals(evt.getActionCommand())) {
		    	
		    } else if ("comboBoxChanged".equals(evt.getActionCommand())) {
		    	mainFrame.Click_select_2((String)newItem);
		    }
		  }
		  public interface Listener_Button
			{
				void Click_select_2(String name);
			}
}
