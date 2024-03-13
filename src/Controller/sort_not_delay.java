package Controller;
import java.awt.Color;

import View.MainFrame;

public class sort_not_delay{
	public static volatile boolean running = true;
	
	private Integer[] array;
	
	// array để lưu trữ giá trị chưa được sắp xếp
	private Integer[] array_2;
	
	private int columns;
	
	private int comparison, swap;


	private MainFrame mainFrame;

	public sort_not_delay(MainFrame mainFrame){
		this.columns = mainFrame.get_Columns();
		this.mainFrame = mainFrame;
		comparison = swap = 0;
	}
	
	public String[][] tinh() {
		
		String[][] value = new String[5][2];
		
		//selectionSort
		create_Array_With_Data_old();
		selectionSort();
		value[0][0] = String.valueOf(comparison);
		value[0][1] = String.valueOf(swap);
		
		
		//insertionSort
		create_Array_With_Data_old();
		insertionSort();
		value[1][0] = String.valueOf(comparison);
		value[1][1] = String.valueOf(swap);
		
		
		//bubbleSort
		create_Array_With_Data_old();
		bubbleSort();
		value[2][0] = String.valueOf(comparison);
		value[2][1] = String.valueOf(swap);
		
		
		//quickSort
		create_Array_With_Data_old();
		quickSort();
		value[3][0] = String.valueOf(comparison);
		value[3][1] = String.valueOf(swap);
		
		
		//mergeSort
		create_Array_With_Data_old();
		mergeSort();
		value[4][0] = String.valueOf(comparison);
		value[4][1] = String.valueOf(swap);
		
		return value;
	}
	
	public int get_Column() {
		return columns;
	}
	
	public Integer[] get_data() {
		return array_2;
	}
	
	public int get_comparison() {
		return comparison;
	}
	
	public int get_swap() {
		return swap;
	}

	public void create_Array_With_Data_new(Integer[] array_value)
	{
		this.columns = array_value.length;
		array_2 = new Integer[columns];
		
		for (int i = 0; i < array_2.length; i++){
			array_2[i] = array_value[i];
		}
	}
	
	public void create_Array_With_Data_old()
	{
		this.columns = array_2.length;
		array = new Integer[columns];
		for (int i = 0; i < array.length; i++){
			array[i] = array_2[i];
		}
	}
	
	public void bubbleSort(){
		// get graphics

        comparison = swap = 0;		
//		int count = 0;				// cải tiến giải thuật giảm số lần so sánh đối với mảng đã sắp xếp
		
		for (int i = 0; i <= array.length-2; i++)
		{
			
//			count = 0;
			for (int j = array.length - 1; j >= i+1; j--)
			{
				if (array[j] < array[j-1])
				{
					swap(j, j-1);
//					count++;
					swap++;
				}
				comparison++;
				
			}
		}	
	}
	/* SELECTION SORT */
	public void selectionSort(){

        
        comparison = swap = 0;
		for (int i = 0; i <= array.length - 2; i++)
		{
			// find the max
			int index = i;
			int max = array[i];
			for (int j = i + 1; j <= array.length -1; j++)
			{
				comparison++;
				if (array[j]<max){
					max = array[j];
					index = j;
				}
			}

			swap(i, index);
			swap++;
		}
	}


	/* INSERTION SORT */
	public void insertionSort(){

		comparison = swap = 0;

		for (int i = 1; i <= array.length - 1; i++)
		{
			int j = i;
			comparison++;
			while (j > 0 && array[j] < array[j-1])
			{
				swap(j, j-1);
				j--;
				comparison++;
				swap++;
			}
		}
	}
	
	/* QUICK SORT */
	public void quickSort(){
		comparison = swap = 0;
		quickSort(0, array.length-1);
	}
	private void quickSort(int i, int j){
		int pivot = findPivot(i,j);
		if (pivot != -1){
			int k = partition(i,j,pivot, array[pivot]);
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
		int L = i;
		int R = j;
		while(L<=R) {
			while(array[L] < pivot) 	L++;
			while(array[R] >= pivot)	R--;
			if (L <= R) {
	            swap(L, R);
	            swap++;
	        }
			comparison++;
		}
		return L;
	}

	/* MERGE SORT */
	public void mergeSort(){

		comparison = swap = 0;

		mergeSort(0, array.length-1);
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
			if (leftArr[l] < rightArr[r]) {
				array[k] = leftArr[l];
				l++;
			} else {
				array[k] = rightArr[r];
				r++;
			}
			k++;
			comparison++;
			swap++;
		}


		// add the remaining in the two arrays if there are any
		while (l < n1)
		{
			array[k] = leftArr[l];
			l++;
			k++;
			swap++;
		}

		while (r < n2)
		{
			array[k] = rightArr[r];
			r++;
			k++;
			swap++;
		}
	}
	
	private void swap(int i, int j)
	{
		// swap the elements
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;

	}
}