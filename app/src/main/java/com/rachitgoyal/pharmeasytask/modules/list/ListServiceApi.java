package com.rachitgoyal.pharmeasytask.modules.list;

import com.rachitgoyal.pharmeasytask.models.UserResponse;

import io.reactivex.Observable;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public interface ListServiceApi {

	Observable<UserResponse> fetchUserResponse(int pageNumber);
}
