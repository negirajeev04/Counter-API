package org.rajnegi.spring.rest.counterapi.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class CounterUtil {

	private static Map<String, Integer> wordsCountMap = new HashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(CounterUtil.class);
	
	static {
		try {
			
			File file = ResourceUtils.getFile("classpath:test.txt");
			Map<String, Integer> wordsMap = new HashMap<>();
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				
				String str = scan.next().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "");
				Integer count = wordsMap.get(str);
				
				if(count != null) {
					count++;
				}else {
					count = 1;
				}
				wordsMap.put(str, count);
			}
			scan.close();
			
			wordsCountMap = wordsMap.entrySet().stream()
								   .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
								   .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                                  (e1,e2) -> e1, LinkedHashMap::new));
			
		} catch (IOException e) {
			LOGGER.error("Error while loading the file from class path - {}", e.getMessage());
		}
	}
	
	public static int getStringCount(String searchString){
		
		Integer countOfOccur = wordsCountMap.get(searchString.toLowerCase());
		if(countOfOccur == null) {
			return 0;
		}
		return countOfOccur;
	}
	
	public static Map<String, Integer> getTopX(int topX){
		return wordsCountMap.entrySet().stream()
							    .limit(topX)
							    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
		                                  (e1,e2) -> e1, LinkedHashMap::new));
	}
	
}
