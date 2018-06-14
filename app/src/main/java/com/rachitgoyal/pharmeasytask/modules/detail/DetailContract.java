package com.rachitgoyal.pharmeasytask.modules.detail;

import com.rachitgoyal.pharmeasytask.models.User;

/**
 * Created by Rachit Goyal on 14/06/18
 */

public interface DetailContract {

	interface View {
		void displayData(User user);

		void updateFriendState(boolean isFriend);

		void showFriendStatusSnackbar(boolean isFriend);
	}

	interface Presenter {
		void fetchData(int userId);

		void addFriendClicked(int userId);
	}
}
