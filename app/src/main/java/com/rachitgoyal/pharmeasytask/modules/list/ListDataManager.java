package com.rachitgoyal.pharmeasytask.modules.list;

import com.rachitgoyal.pharmeasytask.models.UserResponse;

import io.reactivex.Observable;

/**
 * Created by Rachit Goyal on 13/06/18
 */

class ListDataManager {

	private ListServiceApi mServiceApi;

	ListDataManager(ListServiceApi serviceApi) {
		mServiceApi = serviceApi;
	}

	Observable<UserResponse> fetchUsers(int pageNumber) {
		return mServiceApi.fetchUserResponse(pageNumber);
	}
}
