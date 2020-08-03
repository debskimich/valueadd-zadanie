import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//read the file
		String content = "";
		try {
			InputStreamReader streamReader = new InputStreamReader(
					new FileInputStream("zadanie.txt"), "UTF8");
			BufferedReader buffReader = new BufferedReader(streamReader);
			
			String temp;
			while((temp = buffReader.readLine()) != null) {
				content += (temp + "\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//get rid of all the special characters and unnecessary whitespaces
		//if we don't want to treat any instances of strings with numbers as words, use second line instead
		content = content.replaceAll("[^A-Za-z0-9¹æê³ñóœ¿Ÿ\n]", " ").trim().replaceAll(" +", " ");
//		content = content.replaceAll("[^A-Za-z¹æê³ñóœ¿Ÿ\n]", " ").trim().replaceAll(" +", " ");

		
		//optional - make all the characters lowercase, in case we wanted not to distinguish the words on the basis of them being lower/upper case
//		content = content.toLowerCase();
		
		
		//split the strings into separate words
		List<String> lineList = new ArrayList<String>(
				Arrays.asList(content.split("\n")));
		List<List<String>> contentList = new ArrayList<>();
		for(String s: lineList) {
			contentList.add(Arrays.asList(s.split(" ")));
		}
		
		
		//iterate over the word containers and catch multiple occurrences of a word
		String output = "";
		
		for(int i = 0; i < contentList.size(); i++) {
			for(int j = 0; j < contentList.get(i).size(); j++) {
				String currWord = contentList.get(i).get(j);
				if(!currWord.equals("")) {
					contentList.get(i).set(j, "");
					List<Integer> occurrences = new ArrayList<Integer>();
					occurrences.add(j);
					
					for(int k = i; k < contentList.size(); k++) {
						while(contentList.get(k).contains(currWord)) {
							int index = contentList.get(k).indexOf(currWord);
							occurrences.add(index);
							contentList.get(k).set(index, "");
						}
					}
					output += String.format("%s - %s - pozycje -> %s\n", currWord, occurrences.size(), occurrences);
					
				}
			}
		}
		System.out.println(output);
		
	}
}
