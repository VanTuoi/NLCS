package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MenuBar {
	
	private MainFrame mainFrame;
	private JMenuBar menuBar;
	private JMenu File;
	private JMenu Tools;
	private JMenu About;
	private JMenu Menu_Language ;
	private JMenu Display ;
	private JMenuItem File_Inpurt;
	private JMenuItem File_Outpurt;
	private JMenuItem Display_white;
	private JMenuItem Display_black;
	private JMenuItem Language_English;
	private JMenuItem Language_Vietnamese;
	private JMenuItem Exit;
	private JMenuItem about;
	
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		Font f = new Font("Arial", Font.BOLD, 14);			// đổi font chữ cho menubar
		UIManager.put("Menu.font", f);							// Bị lỗi làm cho màn hình bị delay đen 1-2s khi mở
		UIManager.put("MenuItem.font", f);						// đã khắc phục , khởi tạo xong mới gọi setVison
		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		/*
		 * Menu File
		 */
		
		File = new JMenu("File");
		menuBar.add(File);
		File_Inpurt = new JMenuItem("Input Data");
		File_Inpurt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mainFrame.Click_Input();
				mainFrame.repaint();
			}
		});
		File.add(File_Inpurt);
		File_Outpurt = new JMenuItem("Output Data");
		File_Outpurt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mainFrame.Click_Output();
				mainFrame.repaint();
			}
		});
		File.add(File_Outpurt);
		
		/*
		 * Menu Tools
		 */
		
		Tools = new JMenu("Tools");
		menuBar.add(Tools);

		Menu_Language = new JMenu("Language");
		Tools.add(Menu_Language);
		
		Language_English = new JMenuItem("English");
		Language_English.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File.setText("File");
				File_Inpurt.setText("Input Data");
				File_Outpurt.setText("Output Data");
				Tools.setText("Tools");
				Menu_Language.setText("Language");
				Language_Vietnamese.setText("Vietnamese");
				Language_English.setText("English");
				Language_English.setBackground(List_Color.BAR_ORANGE);
				Language_Vietnamese.setBackground(null);
				
				Display.setText("Display");
				Display_white.setText("White");
				Display_black.setText("Black");
				
				Exit.setText("Exit");
				About.setText("About");
				about.setText("About");
				mainFrame.Language_English();
			}
		});
		
		Language_Vietnamese = new JMenuItem("Vietnamese");
		Language_Vietnamese.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	File.setText("Dữ liệu");
				File_Inpurt.setText("Nhập dữ liệu");
				File_Outpurt.setText("Xuất dữ liệu");
				Tools.setText("Công cụ");
				Menu_Language.setText("Ngôn ngữ");
				Language_Vietnamese.setText("Tiếng việt");
				Language_Vietnamese.setBackground(List_Color.BAR_ORANGE);
				Language_English.setBackground(null);
				Language_English.setText("Tiếng anh");
				
				Display.setText("Giao diên");
				Display_white.setText("Sáng");
				Display_black.setText("Tối");
				
				Exit.setText("Thoát");
				About.setText("Thông tin");
				about.setText("Thông tin");
				mainFrame.Language_Vietnamese();
            }
        });
		Menu_Language.add(Language_English);
		Menu_Language.add(Language_Vietnamese);
		

		/*
		 * 
		 * Menu Display this application
		 */
		
		Display = new JMenu("Display");			//
		Tools.add(Display);
		Display_white =  new JMenuItem("White");
		Display_white.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	mainFrame.Change_Display("white");
            	Display_white.setBackground(List_Color.BAR_ORANGE);
            	Display_black.setBackground(null);
            }
		});
		Display.add(Display_white);
		
		Display_black =  new JMenuItem("Black");
		Display_black.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	mainFrame.Change_Display("black");
            	Display_black.setBackground(List_Color.BAR_ORANGE);
            	Display_white.setBackground(null);
            }
		});
		Display.add(Display_black);
		
		Tools.add(Display);
		
		
		/*
		 * 
		 * Menu exit this application
		 */
		Exit = new JMenuItem("Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Tools.add(Exit);
	
		/*
		 * Menu Help
		 */
		About = new JMenu("About");
		menuBar.add(About);
		about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	new About_app();
            }
        });
		About.add(about);
	}
}
