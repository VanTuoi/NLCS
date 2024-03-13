package View;
import javax.swing.JFileChooser;
import java.awt.Color;

public class Form_In_Out_File{
	private int rVal;
	public Form_In_Out_File() {
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public String GetNameFile(String name) {
		JFileChooser c_File = new JFileChooser();
		c_File.setDialogTitle(name);
		c_File.setMultiSelectionEnabled(false);
		c_File.setBackground(new Color(255, 255, 255));
		c_File.setFileHidingEnabled(false);
		rVal = c_File.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
		     String filename = c_File.getSelectedFile().getName();
		     String dir = c_File.getCurrentDirectory().toString();
		     String filePath = dir+"\\"+filename;
		     return filePath;
		}
		else return "No_file";
	}
}
