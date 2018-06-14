package com.rachitgoyal.pharmeasytask.utils;

import com.rachitgoyal.pharmeasytask.R;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public enum ErrorState {

	NO_INTERNET(R.raw.no_internet_connection, R.drawable.ic_launcher_background, R.string.oh_snap, R.string.no_internet_error_message, true, R.string.try_again),
	SOMETHING_WENT_WRONG(R.raw.error_message, R.drawable.ic_launcher_background, R.string.oops_something_went_wrong, R.string.something_went_wrong_error_message, true,
			R.string.try_again);

	private final int mErrorLottieResId;
	private final int mErrorImageResId;
	private final int mErrorTitleResId;
	private final int mErrorMessageResId;
	private final boolean mShowRetry;
	private final int mRetryTextResId;

	ErrorState(int errorLottieResId, int errorImageResId, int errorTitleResId, int errorMessageResId, boolean showRetry, int retryTextResId) {
		mErrorLottieResId = errorLottieResId;
		mErrorImageResId = errorImageResId;
		mErrorTitleResId = errorTitleResId;
		mErrorMessageResId = errorMessageResId;
		mShowRetry = showRetry;
		mRetryTextResId = retryTextResId;
	}

	public int getErrorLottieResId() {
		return mErrorLottieResId;
	}

	public int getErrorImageResId() {
		return mErrorImageResId;
	}

	public int getErrorTitleResId() {
		return mErrorTitleResId;
	}

	public int getErrorMessageResId() {
		return mErrorMessageResId;
	}

	public boolean isShowRetry() {
		return mShowRetry;
	}

	public int getRetryTextResId() {
		return mRetryTextResId;
	}
}
