package org.rajnegi.spring.rest.counterapi.beans;

public class SearchText {

	private String searchString;
	private Integer count;
	
	public SearchText(String searchString, Integer count) {
		super();
		this.searchString = searchString;
		this.count = count;
	}

	public SearchText() {
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return String.format("SearchText [searchString=%s, count=%s]", searchString, count);
	}
	
	
}
