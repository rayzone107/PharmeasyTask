package com.rachitgoyal.pharmeasytask.models;

import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;

/**
 * Created by Rachit Goyal on 13/06/18
 */

@Table
public class User {

	private transient Long id;

	@SerializedName("id")
	private int userId;

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
	private boolean isFriend = false;

	public User() {
	}

	public User(int userId, String firstName, String lastName, String imageUrl, String profession, int followers, int following, int purchases) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
		this.profession = profession;
		this.followers = followers;
		this.following = following;
		this.purchases = purchases;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean friend) {
		isFriend = friend;
	}
}
