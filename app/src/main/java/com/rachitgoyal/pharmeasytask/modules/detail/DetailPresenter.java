package com.rachitgoyal.pharmeasytask.modules.detail;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.rachitgoyal.pharmeasytask.models.User;

/**
 * Created by Rachit Goyal on 14/06/18
 */

class DetailPresenter implements DetailContract.Presenter {

	private DetailContract.View mView;

	DetailPresenter(DetailContract.View view) {
		mView = view;
	}

	@Override
	public void fetchData(int userId) {
		User user = Select.from(User.class)
				.where(Condition.prop("user_id").eq(userId))
				.first();

		mView.displayData(user);
	}

	@Override
	public void addFriendClicked(int userId) {
		User user = Select.from(User.class)
				.where(Condition.prop("user_id").eq(userId))
				.first();

		user.setFriend(!user.isFriend());
		SugarRecord.save(user);

		mView.updateFriendState(user.isFriend());
		mView.showFriendStatusSnackbar(user.isFriend());
	}
}
