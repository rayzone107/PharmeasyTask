package com.rachitgoyal.pharmeasytask.modules.list;

import com.rachitgoyal.pharmeasytask.models.User;
import com.rachitgoyal.pharmeasytask.models.UserResponse;
import com.rachitgoyal.pharmeasytask.utils.Constants;
import com.rachitgoyal.pharmeasytask.utils.ErrorState;
import com.rachitgoyal.pharmeasytask.utils.NoInternetException;
import com.rachitgoyal.pharmeasytask.utils.ViewState;

import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public class ListPresenter implements ListContract.Presenter {

	private ListContract.View mView;
	private ListDataManager mListDataManager;
	private int mCurrentPage = 1;

	ListPresenter(ListContract.View view, ListDataManager listDataManager) {
		mView = view;
		mListDataManager = listDataManager;
	}

	@Override
	public void fetchUsers() {
		if (mCurrentPage == 1) {
			mView.changeViewState(ViewState.LOADING);
		} else {
			mView.showFetchingMoreProgress(true);
		}
		mListDataManager.fetchUsers(mCurrentPage)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<UserResponse>() {
					@Override
					public void onSubscribe(Disposable d) {
					}

					@Override
					public void onNext(UserResponse userResponse) {
						if (mCurrentPage == 1) {
							mView.changeViewState(ViewState.DATA);
						} else {
							mView.showFetchingMoreProgress(false);
						}
						mCurrentPage++;
						userResponse = generateRawData(userResponse);
						if (userResponse.getData().isEmpty()) {
							mView.showNoMoreResults();
						} else {
							mView.showUsers(userResponse.getData());
						}
					}

					@Override
					public void onError(Throwable e) {
						mView.changeViewState(ViewState.ERROR);
						if (e instanceof NoInternetException) {
							mView.showError(ErrorState.NO_INTERNET);
						} else {
							mView.showError(ErrorState.SOMETHING_WENT_WRONG);
						}
					}

					@Override
					public void onComplete() {
					}
				});
	}

	@Override
	public void retry() {
		mCurrentPage = 1;
		fetchUsers();
	}

	private UserResponse generateRawData(UserResponse userResponse) {
		for (int i = 0; i < 2; i++) {
			userResponse.getData().addAll(userResponse.getData());
		}

		for (User user : userResponse.getData()) {
			Random random = new Random();
			user.setProfession(Constants.professionList[random.nextInt(Constants.professionList.length)]);
			user.setFollowers(random.nextInt(200));
			user.setFollowing(random.nextInt(200));
			user.setPurchases(random.nextInt(500));
		}
		return userResponse;
	}
}
