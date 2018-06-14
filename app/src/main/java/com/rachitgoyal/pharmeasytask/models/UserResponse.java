package com.rachitgoyal.pharmeasytask.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public class UserResponse {

	private int page;

	@SerializedName("per_page")
	private int perPage;

	private int total;

	@SerializedName("total_pages")
	private int totalPages;

	private List<User> data;

	public UserResponse() {
	}

	public UserResponse(int page, int perPage, int total, int totalPages, List<User> data) {
		this.page = page;
		this.perPage = perPage;
		this.total = total;
		this.totalPages = totalPages;
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}
}
