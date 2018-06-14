package com.rachitgoyal.pharmeasytask.modules.list;

import android.content.Context;

import com.rachitgoyal.pharmeasytask.models.UserResponse;
import com.rachitgoyal.pharmeasytask.network.ServiceAbstract;
import com.rachitgoyal.pharmeasytask.utils.NoInternetException;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public class ListServiceImpl extends ServiceAbstract implements ListServiceApi {

	public ListServiceImpl(Context context) {
		super(context);
	}

	@Override
	public Observable<UserResponse> fetchUserResponse(final int pageNumber) {
		return Observable.create(new ObservableOnSubscribe<UserResponse>() {
			@Override
			public void subscribe(ObservableEmitter<UserResponse> emitter) throws Exception {
				if (isNetworkConnected()) {
					Call<UserResponse> call = mNetworkService.getUsers(pageNumber);
					try {
						Response<UserResponse> response = call.execute();
						if (response.isSuccessful()) {
							UserResponse userResponse = response.body();
							emitter.onNext(userResponse);
						} else {
							emitter.onError(new Error(response.message()));
						}
					} catch (IOException e) {
						emitter.onError(e);
					}
				} else {
					emitter.onError(new NoInternetException());
				}
				emitter.onComplete();
			}
		});
	}
}
