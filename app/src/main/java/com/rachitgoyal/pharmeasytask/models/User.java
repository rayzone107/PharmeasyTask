package com.rachitgoyal.pharmeasytask.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public class User implements Serializable {

	private int id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("avatar")
	private String imageUrl;

	private String profession;
	private int followers;
	private int following;
	private int purchases;

	public User() {
	}

	public User(int id, String firstName, String lastName, String imageUrl) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
	}

	public User(int id, String firstName, String lastName, String imageUrl, String profession, int followers, int following, int purchases) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
		this.profession = profession;
		this.followers = followers;
		this.following = following;
		this.purchases = purchases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
}
