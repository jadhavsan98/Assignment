package com.filipkart.script;


import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ReadTxtFile {

	public static void main(String[] args) {
		
		int [] data = readFiles("input.txt");
		
		System.out.println(Arrays.toString(data));
		  } 
	
	public static int[] readFiles (String file) {
		
		try {
			File f = new File (file);
			Scanner s = new Scanner (f);
			int ctr = 0;
			while (s.hasNextLine()){
				ctr++;
				s.nextInt();
			}
			
			int [] arr = new int[ctr];
			Scanner s1 = new Scanner (f);
			for (int i =0; i<arr.length; i++)
				
				arr [i]=s1.nextInt();
			
			return arr;
		}
		
		catch (Exception e) {
			return null;
		}
		
		
	}


	}


