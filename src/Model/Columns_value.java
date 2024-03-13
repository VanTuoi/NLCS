package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import View.List_Color;

public class Columns_value {
	private final int MARGIN = 1; 			// Khoảng cách giữa các cột
	private int x, y, width, value;
	private Color color;
	private Color color_background;
	private int font_size;
	public Columns_value(int x, int y, int width, int value, Color color,int font_size, Color color_background){
		this.font_size = font_size;
		this.x = x;
		this.y = y;
		this.width = width;
		this.value = value;
		this.color = color;
		this.color_background = color_background;
	}


	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(x + MARGIN, y-value, width - MARGIN * 2, value);
		if (font_size>0) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, getFontSize()));
			g.drawString(Integer.toString(value), x + MARGIN+1, y-value-1);
		}else {
		}
	}
	public void clear(Graphics g)
	{
		// clear the space
		g.setColor(color_background);//
		g.fillRect(x + MARGIN, y-value-font_size, width - MARGIN * 2, value+font_size);	// font_size là chiều cao chữ
		
	}
	
	
	public void setValue(int value) { this.value = value; }

	public int getValue() { return value; }

	public void setColor(Color color) { this.color = color; }

	public Color getColor() { return color; }
	
	public int getFontSize() {return this.font_size;}
}
