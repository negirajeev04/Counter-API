package org.rajnegi.spring.rest.counterapi.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.rajnegi.spring.rest.counterapi.beans.SearchText;
import org.rajnegi.spring.rest.counterapi.util.CounterUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchTextController {

	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SearchText>> getCountPost(@RequestBody List<SearchText> searchReq) {

		for (SearchText text : searchReq) {
			int countStringInFile = CounterUtil.getStringCount(text.getSearchString());
			text.setCount(countStringInFile);
		}

		return ResponseEntity.ok(searchReq);
	}

	@GetMapping(value = "/top/{topX}", produces = "text/csv")
	public ResponseEntity<String> getTopOccurences(@PathVariable int topX, HttpServletResponse response) {

		String responseData = "";
		Map<String, Integer> topXResult = CounterUtil.getTopX(topX);
		for(Map.Entry<String, Integer> entry : topXResult.entrySet()) {
			responseData = responseData +"\n" + entry.getKey()+"|"+entry.getValue();
		}
		return ResponseEntity.ok(responseData);
	}
	
}
