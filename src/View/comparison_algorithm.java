package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import Controller.sort_not_delay;

public class comparison_algorithm extends JFrame {

	private JPanel contentPane;
	private Object[][] data;
	private sort_not_delay  sort_not_delay;
	private JLabel JLabel, JLabel_2;
	private JTextArea JTextArea;

	public comparison_algorithm(sort_not_delay sort_not_delay)
    {
		this.sort_not_delay = sort_not_delay;
		this.setTitle("Comparison algorithm");
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));			// chỉ đóng jframe hiện tại
		setBounds(550, 300, 750,515);
		this.setVisible(true);
		
        String[] columnNames = {"Tên giải thuật", "Số lần so sánh", "Số lần hoán đổi"};
        data = new  Object[][] {
     	       {"Selection Sort","<html><p>	Null	</p></html>","<html><p>	Null	</p></html>"},
     	       {"Insertion Sort","<html><p>	Null	</p></html>","<html><p>	Null	</p></html>"},
     	       {"Bubble Sort","<html><p> Null	</p></html>","<html><p>	Null	</p></html>"},
     	       {"QuickSort","<html><p>	Null	</p></html>","<html><p>	Null	</p></html>"},
     	       {"Merge Sort","<html><p>	Null	</p></html>","<html><p>	Null	</p></html>"},
     	   };

        JTable table = new JTable(data,columnNames );
        Font f = new Font("Arial", Font.BOLD, 17);	
        table.getTableHeader().setFont(f);
        table.setRowHeight(0, 50);
        table.setRowHeight(1, 50);
        table.setRowHeight(2, 50);
        table.setRowHeight(3, 50);
        table.setRowHeight(4, 50);
        table.setVisible(true);
        table.setDefaultRenderer(Object.class, new MultiLabelRenderer());
        
        contentPane = new JPanel(); 
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        this.add(contentPane);
        
     
        JScrollPane scrollPane = new JScrollPane(table);
        JLabel = new JLabel("Số lượng cột: Null");
        JLabel.setFont(f);
        JLabel_2 = new JLabel("Dữ liệu đã dùng");
        JLabel_2.setFont(f);
        JTextArea = new JTextArea(8,1);
        JTextArea.setWrapStyleWord(true);
        JTextArea.setLineWrap(true);
        JTextArea.setFont(f);
        contentPane.add(scrollPane);
        contentPane.add(JLabel);
        contentPane.add(JLabel_2);
        contentPane.add(JTextArea);
        Create_data_table();
       
    }
	public void Create_data_table() {
		String[][] value = new String[5][2];
		
		value =  sort_not_delay.tinh();
		
		for (int i = 0;i<=4;i++) {
			for (int j = 0;j<=1;j++) {
				data[i][j+1] = value[i][j];
			}
		}
		set_columns_and_data(sort_not_delay.get_Column(),sort_not_delay.get_data());
	}
	
	public void set_columns_and_data(int number, Integer[] array) {
		JLabel.setText("Số lượng cột: "+number);
		String array_data = "";
		for (int i = 0;i<number;i++) {
			array_data  +=  Integer.toString(array[i]) +" ";
		}
		JTextArea.setText(array_data);
	}
	
	class MultiLabelRenderer implements TableCellRenderer
    {
        private JPanel panel;
        private JLabel TA; 
        public MultiLabelRenderer()
        {
            panel = new JPanel(new BorderLayout());
            TA = new JLabel();
        }
 
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, final int row, final int column)
        {
            panel.removeAll();
 
            if (isSelected)
                panel.setBackground( table.getSelectionBackground() );
            else
                panel.setBackground( table.getBackground() );
 
            if (value == null	||  value.toString().length() == 0)
                return panel;
            
            	TA.setText((String) value);
            	Font f = new Font("Arial", Font.BOLD, 17);	
            	TA.setFont(f);
                panel.add(TA, BorderLayout.CENTER);
                panel.setVisible(true);    
                
                // đổi màu nền table
                Color alternateColor = new Color(230, 221, 220);
                Color whiteColor = Color.WHITE;
                Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                panel.setBackground(c);
                c = null;
        
            return panel;
        }
    }

}
