package com.rachitgoyal.pharmeasytask.network;

import com.rachitgoyal.pharmeasytask.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public interface NetworkService {

	@GET("users")
	Call<UserResponse> getUsers(@Query("page") int pageNumber);

}
