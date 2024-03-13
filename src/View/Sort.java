package View;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Sort extends Canvas {
	public static final long serialVersionUID = 2L;

	private Color color_sort = List_Color.CANVAS_BACKGROUND;
	
	private MainFrame mainFrame;
	public Sort(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
	}

    public void paint(Graphics g){
    	this.setBackground(color_sort);
        super.paint(g);
		clear(g);		
    }

	public void clear(Graphics g){
		g.setColor(color_sort);
        g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public void Change_Display(String color) {
		if (color.equals("white")){
			this.setBackground(List_Color.CANVAS_BACKGROUND_WHITE);
			color_sort = List_Color.CANVAS_BACKGROUND_WHITE;
		}else {
			this.setBackground(List_Color.CANVAS_BACKGROUND);
			color_sort = List_Color.CANVAS_BACKGROUND;
		}
	}
}
