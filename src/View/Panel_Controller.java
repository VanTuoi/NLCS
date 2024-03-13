package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.event.MouseAdapter;
import java.awt.Font;

public class Panel_Controller extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private MainFrame mainFrame;
	private JButton[] Button;
	private JScrollBar scrollBar_Speed,scrollBar_columns;
	private JLabel View_Speed,View_columns;
	
	private Color color_view = List_Color.TEXT;
	
	private int Speed,columns;
	private String language;
	private String[] Name_Button = {"Comparison","Random data","Run","Resume/pause"};
	private String[] Name_Button_v = {"So sánh","Tạo random","Bắt đầu","Dừng/tiếp tục"};
	
	public Panel_Controller(float WIDTH, float HEIGHT,MainFrame mainFrame) {
		Speed = mainFrame.get_Speed();
		columns = mainFrame.get_Columns();
		language = new String("English");
		this.mainFrame = mainFrame;
		this.setLayout(null);
		this.setSize(1080,120);
		setBackground(List_Color.PANEL_BUTTON);
		init_1();
		setLayout(new FlowLayout(FlowLayout.CENTER,25, 20));
	}

	public void init_1() {
		Button = new JButton[Name_Button.length];
		for(int i = 0; i < Name_Button.length; i++) {
			Button[i] = new JButton(Name_Button[i]);
			Button[i].setPreferredSize(new Dimension(140, 45));
			Button[i].setFont(new Font("Arial", Font.BOLD, 14));
			Button[i].setBackground(List_Color.BUTTON);
			Button[i].setForeground(List_Color.PANEL_BUTTON_TEXT);
//			Button[i].setOpaque(true);				// Dùng cho trường hợp JLabel
			init_button(Button[i],Name_Button[i]);
		}
		init_View_Speed();
		init_scrollBar_Speed();
		init_View_columns();
		init_scrollBar_columns();
		
	}
	
	public void init_2() {
		Button = new JButton[Name_Button.length];
		for(int i = 0; i < Name_Button_v.length; i++) {
			Button[i] = new JButton(Name_Button_v[i]);
			Button[i].setPreferredSize(new Dimension(140, 45));
			Button[i].setFont(new Font("Arial", Font.BOLD, 14));
			Button[i].setBackground(List_Color.BUTTON);
			Button[i].setForeground(List_Color.PANEL_BUTTON_TEXT);
//			Button[i].setOpaque(true);				// Dùng cho trường hợp JLabel
			init_button(Button[i],Name_Button[i]);
		}
		init_View_Speed();
		init_scrollBar_Speed();
		init_View_columns();
		init_scrollBar_columns();
		
	}
	
	public void init_button(JButton Button, String name) {
		Button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Button.setBackground(List_Color.BUTTON_Click);
				mainFrame.Click(name);
				
				if (mainFrame.have_random_array()) {
					if (Button.getText().equals("Resume/pause")||Button.getText().equals("Resume")||Button.getText().equals("Pause")
						||Button.getText().equals("Dừng/tiếp tục")||Button.getText().equals("Tiếp tục")||Button.getText().equals("Dừng")){
						if (!language.equals("English")) {
							if (Button.getText().equals("Tiếp tục"))
								Button.setText("Dừng");
							else Button.setText("Tiếp tục");
						}else {
							if (Button.getText().equals("Resume"))
								Button.setText("Pause");
							else Button.setText("Resume");
						}
					}
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Button.setBackground(List_Color.BUTTON_Entered);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Button.setBackground(List_Color.BUTTON_Exited);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Button.setBackground(List_Color.BUTTON_Click);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Button.setBackground(List_Color.BUTTON_Click);
			}
		});
		this.add(Button);
	}
	public void init_scrollBar_Speed() {		// Điều chỉnh tốc độ
		scrollBar_Speed = new JScrollBar(JScrollBar.HORIZONTAL,40,10,10,1010); 	// 10 mức
		scrollBar_Speed.setPreferredSize(new Dimension(120, 30));
		scrollBar_Speed.setBackground(List_Color.BAR_CYAN);
		scrollBar_Speed.setForeground(List_Color.SCROLLBAR);
		scrollBar_Speed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Update_View_Speed();
			}
		});
		this.add(scrollBar_Speed);
	}
	public void init_View_Speed() {
		View_Speed = new JLabel(Get_Language_SPEED()+": " +Integer.toString(Speed),JLabel.CENTER);
		View_Speed.setFont(new Font("Arial", Font.BOLD, 16));
		View_Speed.setForeground(color_view);
		this.add(View_Speed);
	}
	public void init_scrollBar_columns() {		// Điều chỉnh số lượng cột
		scrollBar_columns = new JScrollBar(JScrollBar.HORIZONTAL,40,1,10,101); 	//300 mức
		scrollBar_columns.setPreferredSize(new Dimension(120, 30));
		scrollBar_columns.setBackground(List_Color.BAR_CYAN);
		scrollBar_columns.setForeground(List_Color.SCROLLBAR);
		scrollBar_columns.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Update_View_columns();
			}
		});
		this.add(scrollBar_columns);
	}
	public void init_View_columns() {
		View_columns = new JLabel(Get_Language_COLUMNS()+": " +Integer.toString(columns),JLabel.CENTER);
		View_columns.setFont(new Font("Arial", Font.BOLD, 16));
		View_columns.setForeground(color_view);
//		View_columns.setPreferredSize(new Dimension(100, 30));
		this.add(View_columns);
	}
	
	
	

	
	
	public void Update_View_Speed() {
		Speed = scrollBar_Speed.getValue()/10*10;			// Làm tròn bỏ khoảng lẽ
		if (Speed>70) {										// Doi mau
			scrollBar_Speed.setBackground(List_Color.BAR_ORANGE);
		}
		else {
			scrollBar_Speed.setBackground(List_Color.BAR_CYAN);
		}
		mainFrame.set_Speed(Speed);
		View_Speed.setText(Get_Language_SPEED()+": " +Integer.toString(Speed));
	}
	
	public void Update_View_columns() {
		columns = scrollBar_columns.getValue();			// Làm tròn bỏ khoảng lẽ
		if (columns>80) {										// Doi mau
			scrollBar_columns.setBackground(List_Color.BAR_ORANGE);
		}
		else {
			scrollBar_columns.setBackground(List_Color.BAR_CYAN);
		}
		mainFrame.set_Columns(columns);
		View_columns.setText((Get_Language_COLUMNS()+": " +Integer.toString(columns)));
	}
	
	public int get_Speed() {
		return this.Speed;
	}
	
	public int get_Columns() {
		return this.columns;
	}
	
	public void set_Speed(int speed) {
		this.Speed = speed;
	}
	
	public void set_Columns(int columns) {
		this.columns = columns;
		View_columns.setText((Get_Language_COLUMNS()+": " +Integer.toString(this.columns)));
	}
	
	public void set_Language(String language) {
		this.language = language;
		this.removeAll();
		if (language.equals("English")) {
			init_1();
		}else init_2();
	}
	
	public String Get_Language_SPEED() {
		if (language.equals("English")) {
			return "SPEED";
		}else return "TỐC ĐỘ";
	}
	
	public String Get_Language_COLUMNS() {
		if (language.equals("English")) {
			return "COLUMNS";
		}else return "SỐ CỘT";
	}
	
	public void Change_Display(String color) {
		if (color.equals("white")){
			this.setBackground(List_Color.BACKGROUND_WHITE);
			View_Speed.setForeground(List_Color.TEXT_BLACK);
			View_columns.setForeground(List_Color.TEXT_BLACK);
			color_view = List_Color.TEXT_BLACK;
		}else {
			this.setBackground(List_Color.BACKGROUND);
			View_Speed.setForeground(List_Color.TEXT);
			View_columns.setForeground(List_Color.TEXT);
			color_view = List_Color.TEXT;
		}
	}
		
	public interface Listener_Button
	{
		void Click(String name);
		void set_Speed(int speed);
		void set_Columns(int columns);
	}
}
