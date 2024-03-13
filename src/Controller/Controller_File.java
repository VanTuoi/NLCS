package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class Controller_File {
		private FileWriter fileWriter;
	    private BufferedWriter bufferedWriter;
	    private PrintWriter printWriter;
	    private Scanner scanner;

	    public Controller_File() {
	    	//
	    }
	    public void openFileToWrite(String fileName) {
	        try {
	            fileWriter = new FileWriter(fileName, true);	// ghi cuối tệp
	            bufferedWriter = new BufferedWriter(fileWriter);
	            printWriter = new PrintWriter(bufferedWriter);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }    
	    }
	    public void closeFileAfterWrite() {
	        try {
	            printWriter.close();
	            bufferedWriter.close();
	            fileWriter.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public void openFileToRead(String fileName) {
	        try {
	            File file = new File(fileName);
	            
	            if (!file.exists()) {
	                file.createNewFile(); 
	            }
	            scanner = new Scanner(Paths.get(fileName), "UTF-8");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void closeFileAfterRead(String fileName) {
	        try {
	            scanner.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public Integer[] readDatasFromFile(String fileName) {
	    	Queue<Object> Data = new ArrayDeque<>();
	    	openFileToRead(fileName);
	    	int number = 0;
	        while (scanner.hasNextLine()) {
	            String piece_data = scanner.nextLine();
	            Data.add(piece_data);
	            number++;
	        }
	        Integer[] Array_Data = new Integer[number];
	        for (int i = 0; i< number;i++) {
	        	Array_Data[i] = Integer.parseInt((String) (Data.poll()));
	        }
	        closeFileAfterRead(fileName);
	        return Array_Data;
	    }
	    public void writeDatasToFile(String fileName,Integer[] Datas) {
	    	openFileToWrite(fileName);
	    	
	    	for(int i = 0; i < Datas.length; i++) {
		        printWriter.print(Datas[i]+" ");
	        }
	    	 closeFileAfterWrite();
	    }
	    public void writeStringToFile(String fileName,String text) {
	    	openFileToWrite(fileName);
		    printWriter.println(text);
	    	closeFileAfterWrite();
	    }
}
