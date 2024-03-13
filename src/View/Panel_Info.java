package View;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Panel_Info extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float WIDTH_1, HEIGHT_2;
	private JLabel View_comparison,View_Swap;
	private int comparison,swap;
	private String language;
	
	public Panel_Info(float WIDTH, float HEIGHT) {
		language = new String("English");
		
		WIDTH_1 = (float) (WIDTH*0.8);
		HEIGHT_2 = (float) (HEIGHT*0.1);
		setVisible(true);
		setBackground(List_Color.BACKGROUND);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		init();
	}
	
	public void init() {
		comparison = 0;
		swap = 0;
		View_comparison = new JLabel(Get_Language_Comparison()+": " +Integer.toString(comparison),JLabel.CENTER);
		View_comparison.setFont(new Font("Arial", Font.BOLD, 16));
		View_comparison.setForeground(List_Color.TEXT_RED);
		View_comparison.setSize((int)(WIDTH_1*0.2),50);
		this.add(View_comparison);
		
		View_Swap = new JLabel(Get_Language_Swap()+": " +Integer.toString(swap),JLabel.CENTER);
		View_Swap.setFont(new Font("Arial", Font.BOLD, 16));
		View_Swap.setForeground(List_Color.TEXT_GREEN);
		View_Swap.setSize((int)(WIDTH_1*0.2),50);
		this.add(View_Swap);
	}
	
	public void Change_Display(String color) {
		if (color.equals("white")){
			this.setBackground(List_Color.BACKGROUND_WHITE);
		}else {
			this.setBackground(List_Color.BACKGROUND);
		}
	}
	
	public int get_Comparison() {
		return comparison;
	}
	
	public int get_Swap() {
		return swap;
	}
	
	public void set_Comparison(int comparison) {
		this.comparison = comparison;
		View_comparison.setText(Get_Language_Comparison()+": " +Integer.toString(comparison));
	}
	
	public void set_Swap(int swap) {
		this.swap = swap;
		View_Swap.setText(Get_Language_Swap()+": " +Integer.toString(swap));
	}
	public void set_Language(String language) {
		this.language = language;
		this.removeAll();
		init();
	}
	
	public String Get_Language_Swap() {
		if (language.equals("English")) {
			return "SWAP";
		}else 
			return "HOÁN ĐỔI";
	}
	
	public String Get_Language_Comparison() {
		if (language.equals("English")) {
			return "COMPARISON";
		}else 
			return "SO SÁNH";
	}
}
