package com.rachitgoyal.pharmeasytask.modules.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.rachitgoyal.pharmeasytask.R;
import com.rachitgoyal.pharmeasytask.models.User;
import com.rachitgoyal.pharmeasytask.modules.base.BaseActivity;
import com.rachitgoyal.pharmeasytask.modules.detail.DetailActivity;
import com.rachitgoyal.pharmeasytask.utils.Constants;
import com.rachitgoyal.pharmeasytask.utils.ErrorState;
import com.rachitgoyal.pharmeasytask.utils.ViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListActivity extends BaseActivity implements ListContract.View, ListItemViewHolder.OnItemClickListener {

	private static final int VISIBLE_THRESHOLD = 2;

	@BindView(R.id.user_rv)
	RecyclerView mUsersRV;

	@BindView(R.id.loading_lav)
	LottieAnimationView mLoadingLAV;

	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@BindView(R.id.error_layout)
	View mErrorLayout;

	@BindView(R.id.error_lav)
	LottieAnimationView mErrorLav;

	@BindView(R.id.error_title_tv)
	TextView mErrorTitleTV;

	@BindView(R.id.error_message_tv)
	TextView mErrorMessageTV;

	@BindView(R.id.error_button)
	Button mErrorButton;

	private ListAdapter mListAdapter;
	private ListPresenter mPresenter;

	private RecyclerView.OnScrollListener mOnScrollListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Window w = getWindow();
		w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);

		mPresenter = new ListPresenter(this, new ListDataManager(new ListServiceImpl(mContext)));
		mPresenter.fetchUsers();

		mListAdapter = new ListAdapter(new ArrayList<User>(), this);
		mUsersRV.setLayoutManager(new LinearLayoutManager(mContext));
		mUsersRV.setAdapter(mListAdapter);

		mOnScrollListener = new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

				int totalItemCount = linearLayoutManager.getItemCount();
				int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
				if (!mListAdapter.getIsFetchingMore() && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
					mPresenter.fetchUsers();
				}
			}
		};

		mUsersRV.addOnScrollListener(mOnScrollListener);
	}

	@Override
	public void changeViewState(@ViewState.ItemTypeDef int viewState) {
		switch (viewState) {
			case ViewState.LOADING:
				mLoadingLAV.setVisibility(View.VISIBLE);
				mUsersRV.setVisibility(View.GONE);
				mErrorLayout.setVisibility(View.GONE);
				break;
			case ViewState.ERROR:
				mLoadingLAV.setVisibility(View.GONE);
				mUsersRV.setVisibility(View.GONE);
				mErrorLayout.setVisibility(View.VISIBLE);
				break;
			case ViewState.DATA:
				mLoadingLAV.setVisibility(View.GONE);
				mUsersRV.setVisibility(View.VISIBLE);
				mErrorLayout.setVisibility(View.GONE);
				break;
		}
	}

	@Override
	public void showUsers(List<User> users) {
		mListAdapter.addUsers(users);
		mListAdapter.notifyDataSetChanged();
	}

	@Override
	public void showFetchingMoreProgress(boolean show) {
		mListAdapter.setIsFetchingMore(show);
	}

	@Override
	public void showNoMoreResults() {
		mUsersRV.removeOnScrollListener(mOnScrollListener);
	}

	@Override
	public void showError(ErrorState errorState) {
		mErrorLav.setAnimation(errorState.getErrorLottieResId());
		mErrorLav.playAnimation();
		mErrorTitleTV.setText(errorState.getErrorTitleResId());
		mErrorMessageTV.setText(errorState.getErrorMessageResId());
		mErrorButton.setVisibility(errorState.isShowRetry() ? View.VISIBLE : View.GONE);
		mErrorButton.setText(errorState.getRetryTextResId());
	}

	@OnClick(R.id.error_button)
	public void onRetryClick() {
		mListAdapter.clear();
		mPresenter.retry();
	}

	@Override
	public void onItemClick(User user, CircleImageView sharedImageView) {
		Intent intent = new Intent(ListActivity.this, DetailActivity.class);
		intent.putExtra(Constants.EXTRAS.USER_ID_EXTRA, user.getUserId());
		intent.putExtra(Constants.EXTRAS.IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
				this,
				sharedImageView,
				ViewCompat.getTransitionName(sharedImageView));

		startActivity(intent, options.toBundle());
	}
}
