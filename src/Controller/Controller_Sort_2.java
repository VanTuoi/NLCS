package Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import Model.Columns_value;
import View.List_Color;
import View.MainFrame;;

public class Controller_Sort_2 implements Runnable{
	public static volatile boolean running = true;
	public static volatile boolean running_2 = false;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
	
	private static final int PADDING = 20;
	private int MAX_BAR_HEIGHT = 0, MIN_BAR_HEIGHT = 0;
	private Integer[] array;
	private int columns, speed;
	private Columns_value[] columns_values;
	
	private int comparison, swap;

	private Color originalColor, swappingColor, comparingColor;
	private Color BACKGROUND_Canvas = List_Color.CANVAS_BACKGROUND;

	private BufferStrategy bs;
	private Graphics g;

	private MainFrame mainFrame;
	private String name;
	
	public void Name(String name) {
		this.name = name;
	}
	
	@Override
    public void run() {
        while (running) {
        	try {
    			TimeUnit.MILLISECONDS.sleep(10);
    		} catch (Exception ex) {}
        	
            synchronized (pauseLock) {
//            	System.out.println("run_2");
                if (!running) {
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait(); 
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) {
                        break;
                    }
                }
            }
	            //code
	            running_2 = false;
	            switch (name){
				case "Selection Sort":{
					selectionSort();
					name = "";
					break;
				}
				case "Insertion Sort":{
					insertionSort();
					name = "";
					break;
				}
				case "Bubble Sort": {
					bubbleSort();
					name = "";
					break;
				}
				case "QuickSort":{
					quickSort();
					name = "";
					break;
				}
				case "Merge Sort":{
					mergeSort();
					name = "";
					break;
				}
				case "Random":{
					speed = 1;
					break;
				}
				default:{
					break;
				}
	        }
        }
    }
    public void stop() {
        running = false;
        resume();
    }

    public void pause() {
        paused = true;
        running_2 = true;
    }

    public void resume() {
    	running_2 = false;
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }
    
	public Controller_Sort_2(int capacity, int fps,MainFrame mainFrame){
		
		name = new String();
		this.columns = mainFrame.get_Columns();
		this.mainFrame = mainFrame;
		
		comparison = swap = 0;
		originalColor = List_Color.BAR_DEFAULT;
		comparingColor = Color.YELLOW;
		swappingColor = List_Color.BAR_RED;

		bs = mainFrame.getBufferStrategy2();
	}
	
	public void Change_Display(String color) {
		if (color.equals("white")){
			BACKGROUND_Canvas = List_Color.CANVAS_BACKGROUND_WHITE;
			originalColor = List_Color.BAR_DEFAULT_BLACK;
		}else {
			BACKGROUND_Canvas = List_Color.CANVAS_BACKGROUND;
			originalColor = List_Color.BAR_DEFAULT;
		}
	}
	
	public int get_comparison() {
		return comparison;
	}
	
	public int get_swap() {
		return swap;
	}
	
	public int get_font_size() {
		if (columns<=10) return 40;
		if (columns<=20) return 27;
		if (columns<=30) return 18;
		if (columns<=40) return 12;
		if (columns<=50) return 9;
		return 0;
	}
	
	public void createRandomArray(int Width, int Height,Integer[] array_2)
	{
		MAX_BAR_HEIGHT = (int)(Height*0.8);
		MIN_BAR_HEIGHT = (int)(Height*0.1);
		this.columns = mainFrame.get_Columns();
		array = new Integer[columns];
		columns_values = new Columns_value[columns];

		// initial position
		double x = PADDING;
		int y = Height- PADDING;

		// width of all columns_values
		double width = (double) (Width - PADDING*1.5) / columns;

		// get graphics
        g = bs.getDrawGraphics();
		g.setColor(BACKGROUND_Canvas);
		g.fillRect(0, 0, Width, Height);

		int value;
		Columns_value columns_value;
		for (int i = 0; i < array.length; i++)
		{
			value = array_2[i];
			array[i] = value;

			
			columns_value = new Columns_value((int)x, y, (int) width, value, originalColor,get_font_size(),BACKGROUND_Canvas);
			columns_value.draw(g);
			
			columns_values[i] = columns_value;

			// move to the next bar
			x += width;
		}

		bs.show();
		g.dispose();
	}
	
	public void create_Array_With_Data(int Width, int Height,Integer[] array_value)
	{
		MAX_BAR_HEIGHT = (int)(Height*0.8);
		MIN_BAR_HEIGHT = 0;
		this.columns = array_value.length;
		array = new Integer[columns];
		columns_values = new Columns_value[columns];

		// initial position
		double x = PADDING;
		int y = Height- PADDING;

		// width of all columns_values
		double width = (double) (Width - PADDING*1.5) / columns;

		// get graphics
        g = bs.getDrawGraphics();
		g.setColor(BACKGROUND_Canvas);
		g.fillRect(0, 0, Width, Height);
		int value;
		Columns_value columns_value;
		for (int i = 0; i < array.length; i++)
		{
			value = array_value[i] + MIN_BAR_HEIGHT;
			array[i] = value;

			columns_value = new Columns_value((int)x, y, (int) width, value, originalColor,get_font_size(),BACKGROUND_Canvas);
			columns_value.draw(g);
			columns_values[i] = columns_value;

			// move to the next bar
			x += width;
		}

		bs.show();
		g.dispose();
	}
	
	public void bubbleSort(){
		// get graphics
        g = bs.getDrawGraphics();

        comparison = swap = 0;		
//		int count = 0;				// cải tiến giải thuật giảm số lần so sánh đối với mảng đã sắp xếp
		
		for (int i = 0; i <= array.length-2; i++)
		{
			
//			count = 0;
			for (int j = array.length - 1; j >= i+1; j--)
			{
				colorPair(j, j-1, comparingColor);
				if (array[j] < array[j-1])
				{
					swap(j, j-1);
//					count++;
					swap++;
				}
				comparison++;
				
			}

			columns_values[i].setColor(getBarColor(i));
			columns_values[i].draw(g);
			bs.show();
//			if (count == 0)  // the array is sorted
//				break;
			
		}
		

		finishAnimation();

		g.dispose();
		
	}
	/* SELECTION SORT */
	public void selectionSort(){

		// get graphics
        g = bs.getDrawGraphics();
        
        comparison = swap = 0;
		for (int i = 0; i <= array.length - 2; i++)
		{
			// find the max
			int index = i;
			int max = array[i];
			for (int j = i + 1; j <= array.length -1; j++)
			{
				colorPair(index, j, comparingColor);
				comparison++;
				if (array[j]<max){
					max = array[j];
					index = j;
				}
			}

			swap(i, index);
			swap++;

			columns_values[i].setColor(getBarColor(i));
			columns_values[i].draw(g);
			bs.show();
		}

		finishAnimation();

		g.dispose();
	}


	/* INSERTION SORT */
	public void insertionSort(){

		g = bs.getDrawGraphics();
		comparison = swap = 0;

		for (int i = 1; i <= array.length - 1; i++)
		{
			columns_values[i].setColor(getBarColor(i));
			int j = i;
			colorPair(j, j-1, comparingColor);
			comparison++;
			while (j > 0 && array[j] < array[j-1])
			{
				colorPair(j, j-1, comparingColor);
				swap(j, j-1);
				j--;
				comparison++;
				swap++;
			}
			bs.show();
		}

		finishAnimation();
		g.dispose();
	}
	
	/* QUICK SORT */
	public void quickSort(){
		g = bs.getDrawGraphics();
		comparison = swap = 0;
		quickSort(0, array.length-1);
		finishAnimation();
		g.dispose();
	}
	private void quickSort(int i, int j){
		int pivot = findPivot(i,j);
		if (pivot != -1){
			int k = partition(i,j,pivot, array[pivot]);
//			columns_values[pivot].setColor(Color.green);
//			columns_values[pivot].draw(g);
//			columns_values[i].setColor(Color.orange);
//			columns_values[i].draw(g);
//			columns_values[j].setColor(Color.orange);
//			columns_values[j].draw(g);
//			bs.show();
			quickSort(i, k-1);
			quickSort(k, j);
		}
	}
	private int findPivot(int i, int j) {
		int k = i + 1;
		while( (k <= j) && (array[i].equals(array[k]))) {
			k++;
		}
		if (k>j) return -1;
		if (array[i]<array[k]) return k;
		return i;
	}
	private int partition(int i, int j,int index,int pivot){
		Color oldColor = columns_values[index].getColor();
		columns_values[index].setColor(Color.green);
		columns_values[index].draw(g);
		int L = i;
		int R = j;
		while(L<=R) {
			while(array[L] < pivot) 	L++;
			while(array[R] >= pivot)	R--;
			colorPair(L, R, comparingColor);
			if (L <= R) {
	            swap(L, R);
	            swap++;
	        }
			comparison++;
		}
//		System.out.println(Arrays.toString(array));
		columns_values[index].setColor(oldColor);
		columns_values[index].draw(g);
		return L;
	}

	/* MERGE SORT */
	public void mergeSort(){
		g = bs.getDrawGraphics();

		comparison = swap = 0;

		mergeSort(0, array.length-1);

		finishAnimation();
		g.dispose();
	}


	// recursive mergeSort
	private void mergeSort(int left, int right)
	{
		if (left >= right)
			return;

		// find the middle
		int middle = (right + left) / 2;

		// sort the left half
		mergeSort(left, middle);

		// sort the second half
		mergeSort(middle+1, right);

		// merge them
		merge(left, middle, right);
	}


	// merge for mergeSort
	private void merge(int left, int middle, int right)
	{
		Color mergeColor = getBarColor(middle);

		// number of items in the first half
		int n1 = middle - left + 1;
		int n2 = right - middle;  // second half

		// create array for those parts
		int[] leftArr = new int[n1];
		for (int i = 0; i < n1; i++)
			leftArr[i] = array[left+i];

		int[] rightArr = new int[n2];
		for (int i = 0; i < n2; i++)
			rightArr[i] = array[middle+i+1];

		// starting index
		int l = 0, r = 0, k = left;  // k: for the original array

		// merge
		while (l < n1 && r < n2)
		{
			Columns_value cl = columns_values[k];
			cl.clear(g);
			if (leftArr[l] < rightArr[r]) {
				array[k] = leftArr[l];
				cl.setValue(leftArr[l]);
				l++;
			} else {
				array[k] = rightArr[r];
				cl.setValue(rightArr[r]);
				r++;
			}

			cl.setColor(mergeColor);
			colorBar(k, swappingColor);
			k++;
			comparison++;
			swap++;
		}


		// add the remaining in the two arrays if there are any
		while (l < n1)
		{
			Columns_value cl = columns_values[k];
			cl.clear(g);

			array[k] = leftArr[l];
			cl.setValue(leftArr[l]);
			cl.setColor(mergeColor);
			colorBar(k, swappingColor);
			l++;
			k++;
			swap++;
		}

		while (r < n2)
		{
			Columns_value cl = columns_values[k];
			cl.clear(g);

			array[k] = rightArr[r];
			cl.setValue(rightArr[r]);
			cl.setColor(mergeColor);
			colorBar(k, swappingColor);
			r++;
			k++;
			swap++;
		}
	}

//	private Color getBarColor(int value)
//	{
//		int interval = (int) (columns / 5.0);
//		if (value < interval)
//			return List_Color.BAR_ORANGE;
//		else if (value < interval * 2)
//			return List_Color.BAR_YELLOW;
//		else if (value < interval * 3)
//			return List_Color.BAR_GREEN;
//		else if (value < interval * 4)
//			return List_Color.BAR_CYAN;
//		return List_Color.BAR_BLUE;
//
//	}
	private Color getBarColor(int value)
	{
		return List_Color.BAR_CYAN;

	}
		
	public void finishAnimation()
	{
		// swiping to green
		for (int i = 0; i < columns_values.length; i++)
		{
			colorBar(i, comparingColor);
			columns_values[i].setColor(getBarColor(i));
			columns_values[i].draw(g);
			bs.show();
		}
		mainFrame.Set_Info_2(comparison,swap);
		// show elapsed time and comparisons
	}
	
	private void colorPair(int i, int j, Color color)
	{
		this.speed = 10000/mainFrame.get_Speed();							////1000
		Color color1 = columns_values[i].getColor(), color2 = columns_values[j].getColor();
		// drawing
		columns_values[i].setColor(color);
		columns_values[i].draw(g);

		columns_values[j].setColor(color);
		columns_values[j].draw(g);

		bs.show();
		// delay
		try {
			TimeUnit.MILLISECONDS.sleep(speed);
		} catch (Exception ex) {}
		
		while (running_2) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (Exception ex) {}
		}
		mainFrame.Set_Info_2(comparison,swap); //
		// put back to original color
		columns_values[i].setColor(color1);
		columns_values[i].draw(g);

		columns_values[j].setColor(color2);
		columns_values[j].draw(g);
		bs.show();
	}

	// color the bar in speed time and put it
	// back to its original color
	private void colorBar(int index, Color color)
	{
		this.speed = 10000/mainFrame.get_Speed();     ///1000
		Columns_value columns_value = columns_values[index];
		Color oldColor = columns_value.getColor();

		columns_value.setColor(color);
		columns_value.draw(g);
		bs.show();

		try {
			TimeUnit.MILLISECONDS.sleep(speed);
		} catch (Exception ex) {}

		while (running_2) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (Exception ex) {}
		}
		
		mainFrame.Set_Info_2(comparison,swap); ///
		
		columns_value.setColor(oldColor);
		columns_value.draw(g);

		bs.show();
	}
	private void swap(int i, int j)
	{
		// swap the elements
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;

		// clear the bar
		columns_values[i].clear(g);
		columns_values[j].clear(g);

		// swap the drawings
		columns_values[j].setValue(columns_values[i].getValue());
		columns_values[i].setValue(temp);

		colorPair(i, j,swappingColor);								//
	}
	
	public void drawArray()
	{
		g = bs.getDrawGraphics();

		for (int i = 0; i < columns_values.length; i++)
		{
			columns_values[i].draw(g);
		}
		bs.show();
		g.dispose();
	}
	
	public Integer[] get_Datas() {
		Integer[] Array_Data = new Integer[columns];
		for (int i = 0; i< columns;i++) {
        	Array_Data[i] = columns_values[i].getValue();
        }
		return Array_Data;
	}
	
	public interface SortedListener
	{
		BufferStrategy getBufferStrategy2();
		void Set_Info_2(int comparison,int swap);
	}
}