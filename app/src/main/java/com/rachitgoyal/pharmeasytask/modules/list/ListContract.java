package com.rachitgoyal.pharmeasytask.modules.list;

import com.rachitgoyal.pharmeasytask.models.User;
import com.rachitgoyal.pharmeasytask.utils.ErrorState;
import com.rachitgoyal.pharmeasytask.utils.ViewState;

import java.util.List;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public interface ListContract {

	interface View {
		void showUsers(List<User> users);

		void changeViewState(@ViewState.ItemTypeDef int viewState);

		void showError(ErrorState errorState);

		void showFetchingMoreProgress(boolean show);

		void showNoMoreResults();
	}

	interface Presenter {
		void fetchUsers();

		void retry();
	}
}
