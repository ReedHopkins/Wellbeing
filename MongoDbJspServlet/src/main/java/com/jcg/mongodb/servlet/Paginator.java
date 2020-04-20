package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Arrays;

public class Paginator {

	String servlet;
	String spageid;
	int pageId;
	int size;
	int total;
	int start;
	int end;
	int last;
	String previous = "#";
	String next = "#";
	String firstLink = "#";
	String lastLink = "#";
	String searchTerm;
	String sortTerm;
	String filterTerm;
	ArrayList<Integer> pageNums;

	public Paginator() {
	}

	public Paginator(String model, String spage, int s, int t) {

		this.servlet = "ModelServlet?model=" + model;
		this.spageid = spage;
		this.pageId = Integer.parseInt(this.spageid);
		this.size = s;
		this.total = t;
		this.start = setStartIndex(this.pageId, this.total);
		this.end = setEndIndex(this.start, this.size, this.total);
		this.last = setLastPage(this.size, this.total);
		this.pageNums = setPaginatorNums(this.pageId, this.last);

		if (this.pageId > 1)
			this.previous = this.servlet + "&page=" + (pageId - 1);
		if (this.pageId < this.last)
			this.next = this.servlet + "&page=" + (pageId + 1);
	}

	public void setSearchTerm(String s) {
		this.searchTerm = s;
	}

	public void setSortTerm(String s) {
		this.sortTerm = s;
	}

	public void setFilterTerm(String s) {
		this.filterTerm = s;
	}

	private int setStartIndex(int start, int total) {
		int output = 0;
		if (start == 1) {
			output = 0;
		} else {
			output = start - 1;
			output = output * total;
		}
		return output;
	}

	private int setEndIndex(int start, int size, int total) {
		int end = size;
		if (!(size - start < total))
			end = start + total;

		return end;
	}

	private int setLastPage(int size, int total) {
		int last = size / total;
		if (size % total > 0) {
			last++;
		}
		return last;
	}

	private ArrayList<Integer> setPaginatorNums(int origPageId, int last) {
		ArrayList<Integer> output = new ArrayList<Integer>();

		if (last == 4) {
			output.addAll(Arrays.asList(1, 2, 3, 4));
		} else if (last == 3) {
			output.addAll(Arrays.asList(1, 2, 3));
		} else if (last == 2) {
			output.addAll(Arrays.asList(1, 2));
		} else if (last == 1) {
			output.addAll(Arrays.asList(1));
		} else if (last > 4) {

			int pageNum = 1;
			if (origPageId > 3) {
				pageNum = origPageId - 2;
			}
			if (origPageId > last - 2) {
				pageNum = origPageId - 3;
			}
			if (origPageId > last - 1) {
				pageNum = origPageId - 4;
			}
			for (int i = 0; i < 5; i++, pageNum++) {
				output.add(pageNum);
			}

		}

		return output;
	}

	public int getStartIndex() {
		return this.start;
	}

	public int getEndIndex() {
		return this.end;
	}

	public String getPreviousPageLink() {
		if (this.pageId > 1)
			this.previous = this.servlet + "&search_term=" + this.searchTerm + "&page=" + (this.pageId - 1) + "&sort="
					+ this.sortTerm;
		return this.previous;
	}

	public String getNextPageLink() {
		if (this.pageId < this.last)
			this.next = this.servlet + "&search_term=" + this.searchTerm + "&page=" + (this.pageId + 1) + "&sort="
					+ this.sortTerm;
		return this.next;
	}

	public String getFirstPageLink() {
		this.firstLink = this.servlet + "&search_term=" + this.searchTerm + "&page=1" + "&sort=" + this.sortTerm;
		return this.firstLink;
	}

	public String getLastPageLink() {
		this.lastLink = this.servlet + "&search_term=" + this.searchTerm + "&page=" + this.last + "&sort="
				+ this.sortTerm;
		return this.lastLink;
	}

	public ArrayList<Integer> getPageNums() {
		return this.pageNums;
	}

}
