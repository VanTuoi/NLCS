package View;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class About_app extends JDialog {

	private JPanel contentPane;

	public About_app() {
		this.setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setSize(450, 245);
		setLocation(700,400);
		setResizable(false);
		setContentPane(contentPane);
		setTitle("Thông tin ứng dụng");
		
		JLabel lblNewLabel = new JLabel();
		String Title = new String("<b style=\"color:green;font-size:16px\">Niên luận cơ sở ngành</b><br>");
		String paragraph = new String("Đề tài: Mô phỏng các giải thuật sắp xếp"
				+ " với dãy số cho trước<br>SV thực hiện: Trần Văn Tươi"
				+ "<br> GV hướng dẫn: Trương Thị Thanh Tuyền"
				+ "<br> HK2, năm học 2022-2023"
				+ "<br> Cập nhật lần cuối: 23/4/2023 13h30");
		lblNewLabel.setText("<html>"+Title+"<p style=\"width:300px;font-size:12px\">"+paragraph+"</p></html>");
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font(".VnArial", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnNewButton);
	}
}
