package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controller.Controller_Sort;
import Controller.Controller_Sort_2;
import Controller.Controller_File;
import Controller.sort_not_delay;
import java.awt.Font;


public class MainFrame extends JFrame implements Controller_Sort.SortedListener,Panel_Controller.Listener_Button {
	public static final long serialVersionUID = 10L;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static final int WIDTH = 1280, HEIGHT = 840;
	private static int COLUMNS = 40, SPEED = 40;				// default
	int cWidth = WIDTH - 250 - 10;
	int cHeight = HEIGHT - 80;
	private JPanel mainPanel;
	private String language;
	
	private Integer[] array; //randown value;
	private Integer[] Datas_input;
	private Integer[] Datas_output;
	private String choose_default = "Selection Sort";
	private String choose_2_default = "Selection Sort";
	private Boolean Have_random = false;
	
	private Panel_Controller panel_Controller;
	private Panel_Select_Sort panel_Button_2;
	private Panel_Info panel_Info, panel_Info_2;
	private Sort sort;
	private Sort_2 sort_2;
	
	private comparison_algorithm comparison_algorithm;
	
	private Controller_Sort controller;
	private Controller_Sort_2 controller_2;
	private Form_In_Out_File form_In_Out_File;
	private Controller_File controller_File;
	private sort_not_delay sort_not_delay;
	private boolean check = true;
	
	private Thread Thread_1, Thread_2;
	
	
	public MainFrame() {
//		this.setAlwaysOnTop( true );			//luôn nằm trên cùng
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
		setMinimumSize(new Dimension(WIDTH, HEIGHT + 40));
		setPreferredSize(new Dimension(WIDTH, HEIGHT + 40));
		setLocationRelativeTo(null);
		
//		setResizable(false);
		
		setBackground(List_Color.BACKGROUND);
		language = new String("English");

		setTitle("Simulating sorting algorithm with graphics");
		set_icon();
		init();
	}
	
	public void set_icon() {
										// lấy đường dẫn đến nơi chứa dự án
		ImageIcon webIcon = new ImageIcon(System.getProperty("user.dir")+"\\Icon\\icon.jpg");
        setIconImage(webIcon.getImage());
	}
	
	private void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(List_Color.BACKGROUND);
		getContentPane().add(mainPanel);
		
		new MenuBar(this);
		
		controller_File = new Controller_File();
		
		panel_Info = new Panel_Info(WIDTH, HEIGHT);
		panel_Info.setBounds(20,110,(int)(WIDTH*0.15)-20,50);
		mainPanel.add(panel_Info);
		
		panel_Info_2 = new Panel_Info(WIDTH, HEIGHT);
		panel_Info_2.setBounds(20,480,(int)(WIDTH*0.15)-20,50);
		mainPanel.add(panel_Info_2);
		
		panel_Controller = new Panel_Controller(WIDTH, HEIGHT,this);
		panel_Controller.setBounds(0,730,(int)(WIDTH)-30,80);	//
		panel_Controller.setVisible(true);
		mainPanel.add(panel_Controller);
		
		
		panel_Button_2 = new Panel_Select_Sort(WIDTH, HEIGHT,this);
		panel_Button_2.setBounds(0,0,(int)(WIDTH*0.15),(int)(HEIGHT));	//
		panel_Button_2.setVisible(true);
		mainPanel.add(panel_Button_2);
		
		cHeight = 350;
		cWidth = 1020+40;
		
		sort = new Sort(this);
		sort.setFocusable(false);
		sort.setMaximumSize(new Dimension(cWidth, cHeight));
		sort.setMinimumSize(new Dimension(cWidth, cHeight));
		sort.setPreferredSize(new Dimension(cWidth, cHeight));
		sort.setBounds((int)(WIDTH*0.15)+10,10, cWidth-10, cHeight);
		mainPanel.add(sort);
		
		sort_2 = new Sort_2(this);
		sort_2.setFocusable(false);
		sort_2.setMaximumSize(new Dimension(cWidth, cHeight));
		sort_2.setMinimumSize(new Dimension(cWidth, cHeight));
		sort_2.setPreferredSize(new Dimension(cWidth, cHeight));
		sort_2.setBounds((int)(WIDTH*0.15)+10,cHeight+10 +10, cWidth-10, cHeight);
		mainPanel.add(sort_2);
		
		setVisible(true);
		controller = new Controller_Sort(get_Columns(), get_Speed(), this);
		controller_2 = new Controller_Sort_2(get_Columns(), get_Speed(), this);
		sort_not_delay = new sort_not_delay(this);
		
		// Khởi tạo luồng điều khiển
		 Thread_1 = new Thread(controller);
		 controller.Name("");
	     Thread_2 = new Thread(controller_2);
	     controller_2.Name("");
	     
	     Thread_1.start();
		 Thread_2.start();
	

		 this.revalidate();					
		 this.repaint();
	}
		
	public BufferStrategy getBufferStrategy()
	{
		BufferStrategy bs = sort.getBufferStrategy();
		if (bs == null)
		{
			sort.createBufferStrategy(2);		// để để lớn hơn 2 thì ctr sẽ bị delay
			bs = sort.getBufferStrategy();
		}
		return bs;
	}
	public BufferStrategy getBufferStrategy2()
	{
		BufferStrategy bs_2 = sort_2.getBufferStrategy();
		if (bs_2 == null)
		{
			sort_2.createBufferStrategy(2);		// để để lớn hơn 2 thì ctr sẽ bị delay
			bs_2 = sort_2.getBufferStrategy();
		}
		return bs_2;
	}
	
	public void Click_select_1(String name){
		choose_default = name;
		switch (name){
			// dùng cho tiếng anh
			case "Selection Sort":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Selection Sort");
				choose_default = "Selection Sort";
				break;
			}
			case "Insertion Sort":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Insertion Sort");
				break;
			}
			case "Bubble Sort": {
				sort.setVisible(true);
				controller.pause();
				controller.Name("Bubble Sort");
				break;
			}
			case "QuickSort":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("QuickSort");
				break;
			}
			case "Merge Sort":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Merge Sort");
				break;
			}
			case "Heap Sort":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Heap Sort");
				break;
			}
			case "None":{
				sort.setVisible(false);
				break;
			}
			// dùng cho tiếng việt
			case "Sắp xếp chọn":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Selection Sort");
				choose_default = "Selection Sort";
				break;
			}
			case "Sắp xếp xen":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Insertion Sort");
				choose_default = "Insertion Sort";
				break;
			}
			case "Sắp xếp nổi bọt": {
				sort.setVisible(true);
				controller.pause();
				controller.Name("Bubble Sort");
				choose_default = "Bubble Sort";
				break;
			}
			case "Sắp xếp nhanh":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("QuickSort");
				choose_default = "QuickSort";
				break;
			}
			case "Sắp xếp trộn":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Merge Sort");
				choose_default = "Merge Sort";
				break;
			}
			case "Sắp xếp vun đống":{
				sort.setVisible(true);
				controller.pause();
				controller.Name("Heap Sort");
				choose_default = "Heap Sort";
				break;
			}
			case "Không":{
				controller.Name("");
				sort.setVisible(false);
				break;
			}
		}
	}
	public void Click_select_2(String name){
		choose_2_default = name;
		switch (name){
			case "Selection Sort":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Selection Sort");
				break;
			}
			case "Insertion Sort":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Insertion Sort");
				break;
			}
			case "Bubble Sort": {
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Bubble Sort");
				break;
			}
			case "QuickSort":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("QuickSort");
				break;
			}
			case "Merge Sort":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Merge Sort");
				break;
			}
			case "Heap Sort":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Heap Sort");
				break;
			}
			case "None":{
				sort_2.setVisible(false);
				controller_2.Name("");
				break;
			}
			// dùng cho tiếng việt
			case "Sắp xếp chọn":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Selection Sort");
				choose_2_default = "Selection Sort";
				break;
			}
			case "Sắp xếp xen":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Insertion Sort");
				choose_2_default = "Insertion Sort";
				break;
			}
			case "Sắp xếp nổi bọt": {
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Bubble Sort");
				choose_2_default = "Bubble Sort";
				break;
			}
			case "Sắp xếp nhanh":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("QuickSort");
				choose_2_default = "QuickSort";
				break;
			}
			case "Sắp xếp trộn":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Merge Sort");
				choose_2_default = "Merge Sort";
				break;
			}
			case "Sắp xếp vun đống":{
				sort_2.setVisible(true);
				controller_2.pause();
				controller_2.Name("Heap Sort");
				choose_2_default = "Heap Sort";
				break;
			}
			case "Không":{
				sort_2.setVisible(false);
				controller_2.Name("");
				break;
			}
		}
	}
	
	public void Click(String name){
		get_Speed();
		get_Columns();
		switch (name){
			case "Random data":
				Have_random = true;
				int value_ran;
				Integer[] Array_Data = new Integer[COLUMNS];
				for(int i = 0;i<COLUMNS;i++) {
					Random rand = new Random();
					value_ran = rand.nextInt(300)+1;
			        Array_Data[i] = value_ran;
				}
								
				controller.createRandomArray(sort.getWidth(), sort.getHeight(),Array_Data);
				controller_2.createRandomArray(sort.getWidth(), sort.getHeight(),Array_Data);
				sort_not_delay.create_Array_With_Data_new(Array_Data);
				
				
				Set_Info(0,0);
				Set_Info_2(0,0);

				break;
			case "Run":{
				if (!Have_random) {
					Errol("Bạn cần phải tạo data trước khi chạy","Lỗi");
				}else {
				
					if (choose_default.equals("Selection Sort")) {			// sử lý khởi chạy lần đầu
						controller.Name("Selection Sort");
					}else {
						controller.Name(choose_default);		// nếu không chọn giải thuật khác
					}
					
					if (choose_2_default.equals("Insertion Sort")) {
						controller_2.Name("Insertion Sort");
					}else {
						controller_2.Name(choose_2_default);
					}
					
					controller.resume();
					controller_2.resume();
				}
				
				break;
			}
			case "Resume/pause":{
				if (!Have_random) {
					Errol("Bạn cần phải tạo data trước khi chạy","Lỗi");
				}else {
					if (check) {
						check = false;
						controller.pause();
						controller_2.pause();
					}
					else {
						check = true;
						controller.resume();
						controller_2.resume();
					}
				}
				break;
			}
			case "Comparison":{
				if (!Have_random) {
					Errol("Bạn cần phải tạo data trước khi sử dụng chức năng này","Lỗi");
				}
				else {
					comparison_algorithm = new comparison_algorithm(sort_not_delay);
					comparison_algorithm.setVisible(true);
				}
				break;
			}
			case "So sánh":{
				if (!Have_random) {
					Errol("Bạn cần phải tạo data trước khi chạy","Lỗi");
				}
				else {
					comparison_algorithm = new comparison_algorithm(sort_not_delay);
					comparison_algorithm.setVisible(true);
				}
				break;
			}
		}
	}
		
	public void set_Speed(int speed) {
		MainFrame.SPEED = speed;
	}
	
	public void set_Columns(int columns) {
		MainFrame.COLUMNS = columns;
		int value_ran;
		Integer[] Array_Data = new Integer[COLUMNS];
		for(int i = 0;i<COLUMNS;i++) {
			Random rand = new Random();
			value_ran = rand.nextInt(300)+1;
	        Array_Data[i] = value_ran;
		}
		Have_random = true ;// đã có data
		controller.createRandomArray(sort.getWidth(), sort.getHeight(),Array_Data);
		controller_2.createRandomArray(sort.getWidth(), sort.getHeight(),Array_Data);
		sort_not_delay.create_Array_With_Data_new(Array_Data);
	}
	
	public void set_Columns_with_import_data(int columns) {
		MainFrame.COLUMNS = columns;
		panel_Controller.set_Columns(MainFrame.COLUMNS);
	}
	
	public int get_Speed() {
		return MainFrame.SPEED;
	}
	
	public int get_Columns() {
		return MainFrame.COLUMNS;
	}
	
	public boolean have_random_array() {
		return Have_random;
	}
	
	public void Set_Info(int comparison,int swap) {
		panel_Info.set_Comparison(comparison);
		panel_Info.set_Swap(swap);
	}
	
	public void Set_Info_2(int comparison,int swap) {
		panel_Info_2.set_Comparison(comparison);
		panel_Info_2.set_Swap(swap);
	}
	
	public void Language_Vietnamese() {
		this.setTitle("Mô phỏng giải thuật sắp xếp bằng đồ họa");
		language = "Vietnamese";
		panel_Controller.set_Language("Vietnamese");
		panel_Controller.set_Columns(COLUMNS);
		panel_Info.set_Language("Vietnamese");
		panel_Info_2.set_Language("Vietnamese");
		panel_Button_2.set_Language("Vietnamese");
		mainPanel.revalidate();					// xóa đối tượng cũ 
		mainPanel.repaint();
	}
	
	public void Language_English() {
		this.setTitle("Simulating sorting algorithm with graphics");
		language = "English";
		panel_Controller.set_Language("English");
		panel_Controller.set_Columns(COLUMNS);
		panel_Info.set_Language("English");
		panel_Info_2.set_Language("English");
		panel_Button_2.set_Language("English");
		mainPanel.revalidate();					
		mainPanel.repaint();
	}
	
	public void Click_Input() {
		form_In_Out_File = new Form_In_Out_File();
		String name_file = form_In_Out_File.GetNameFile("Open");
		if (name_file.equals("No_file")) {
			if (language.equals("English")) {
				Have_random = false;
				Errol("File is not selected","Error");
			}
			else {
				Errol("Chưa chọn file","Lỗi");
				Have_random = false;
			}
		}else {
			Have_random = true;
			
			Datas_input = controller_File.readDatasFromFile(name_file);
//			System.out.println(Arrays.toString(Datas_input));
			controller.create_Array_With_Data(cWidth,cHeight,Datas_input);
			controller_2.create_Array_With_Data(cWidth,cHeight,Datas_input);
			sort_not_delay.create_Array_With_Data_new(Datas_input);
			set_Columns_with_import_data(Datas_input.length);
		}
	}
	
	public void Click_Output() {
		form_In_Out_File = new Form_In_Out_File();
		String name_file = form_In_Out_File.GetNameFile("Save");
		if (name_file.equals("No_file")) {
			if (language.equals("English"))
				Errol("File is not selected","Error");
			else
				Errol("Chưa chọn file","Lỗi");
		}else {
			
			Calendar c = Calendar.getInstance();
			Datas_output = controller.get_Datas();
			
			controller_File.writeStringToFile(name_file,"Thời gian tạo: "+c.getTime());
			controller_File.writeStringToFile(name_file,"Giải thuật: "+choose_default);
			controller_File.writeStringToFile(name_file,"Số lần so sánh: "+controller.get_comparison()+"\nSố lần hoán đổi: "+controller.get_swap());
			controller_File.writeStringToFile(name_file,"Dữ liệu đầu vào: ");
			controller_File.writeDatasToFile(name_file,Datas_output);
			controller_File.writeStringToFile(name_file,"\nDữ liệu đầu ra: ");
			controller_File.writeDatasToFile(name_file,Datas_output);
		}
	}
	
	public void Change_Display(String color) {
		
		Have_random = false;						// khắc phục lỗi khi đổi BACKGROUND mà bấm run sẽ lỗi màu canvas
		
		if (color.equals("white")){
			mainPanel.setBackground(List_Color.BACKGROUND_WHITE);
		}else {
			mainPanel.setBackground(List_Color.BACKGROUND);
		}
		panel_Info.Change_Display(color);
		panel_Info_2.Change_Display(color);
		
		panel_Controller.Change_Display(color);
		panel_Button_2.Change_Display(color);
		
		sort.Change_Display(color);
		sort_2.Change_Display(color);
		
		controller.Change_Display(color);
		controller_2.Change_Display(color);
	}
	
	public void Errol(String text, String title) {
		JOptionPane.showMessageDialog(this,text,title,JOptionPane.INFORMATION_MESSAGE);
    }
}

	// chưa cập nhật số hàng cột khi thay đổi ngôn ngữ
	// nếu đã chạy sắp xếp xong thu nhỏ cửa sổ mở lại thì bị mất nội dung
