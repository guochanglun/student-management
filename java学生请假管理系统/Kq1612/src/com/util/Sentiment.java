package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

public class Sentiment {
	private static List<String> negList;
	private static List<String> posList;
	private static List<String> notList;
	
	private static JiebaSegmenter segmenter = new JiebaSegmenter();
	
	static{
		String negtiveFileName = "C:/Users/游/Desktop/student-management/java学生请假管理系统/Kq1612/src/negtive.txt";
		String positiveFileName = "C:/Users/游/Desktop/student-management/java学生请假管理系统/Kq1612/src/positive.txt";
		String notFileName = "C:/Users/游/Desktop/student-management/java学生请假管理系统/Kq1612/src/not.txt";
		
		File negtiveFile = new File(negtiveFileName);
		File positiveFile = new File(positiveFileName);
		File notFile = new File(notFileName);
		
		try{
			BufferedReader negBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(negtiveFile), Charset.forName("utf-8")));
			BufferedReader posBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(positiveFile), Charset.forName("utf-8")));
			BufferedReader notBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(notFile), Charset.forName("utf-8")));
			
			negList = getListFromBufferedReader(negBufferedReader);
			posList = getListFromBufferedReader(posBufferedReader);
			notList = getListFromBufferedReader(notBufferedReader);
			
			System.out.println(negList);
			System.out.println(posList);
			System.out.println(notList);
			
//			negBufferedReader.close();
//			posBufferedReader.close();
//			notBufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sentiment classify
	 */
	public static boolean classify(String sentence) {
		System.out.println("--------------------------------------------------");
		// split words
		List<SegToken> segTokenList = segmenter.process(sentence, SegMode.INDEX);
		double positiveScore = 10E100d;
		double negtiveScore = 10E100d;
		
		String preWord = "";
		
		for(SegToken token: segTokenList){
			String word = token.word;
			if(word.trim().length() == 0) continue;
//			System.out.println(word);
			if((negList.contains(word) && !"".equals(preWord) && !notList.contains(preWord)) || 
					(posList.contains(word) && !"".equals(preWord) && notList.contains(preWord))) {
				negtiveScore *= 2.0 / (negList.size() + 1);
			}else {
				negtiveScore *= 1.0 / (negList.size() + 1);
			}
			
			if((posList.contains(word) && !"".equals(preWord) && !notList.contains(preWord)) || 
					(negList.contains(word) && !"".equals(preWord) && notList.contains(preWord))) {
				positiveScore *= 2.0 / (posList.size() + 1);
			}else {
				positiveScore *= 1.0 / (posList.size() + 1);
			}
			preWord = word;
		}
		System.out.println("--------------------------------------------------");
		System.out.println("negtiveScore: " + negtiveScore);
		System.out.println("positiveScore: " + positiveScore);
		System.out.println("--------------------------------------------------");
		
		if(negtiveScore > positiveScore){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * read all lines from BufferedReader to list, then return list
	 */
	private static List<String> getListFromBufferedReader(BufferedReader reader) {
		
		List<String> list = new ArrayList<String>();
		
		try {
			String line;
			while((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
