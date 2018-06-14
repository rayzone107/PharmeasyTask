package com.rachitgoyal.pharmeasytask.modules.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rachitgoyal.pharmeasytask.R;
import com.rachitgoyal.pharmeasytask.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final int USER_ITEM_VIEW = 0;
	private static final int LOADING_MORE_RESULTS = 1;

	private List<User> mUsers;
	private ListItemViewHolder.OnItemClickListener mItemClickListener;
	private boolean mIsFetchingMore;

	ListAdapter(List<User> users, ListItemViewHolder.OnItemClickListener onItemClickListener) {
		mUsers = users;
		mItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		switch (viewType) {
			case USER_ITEM_VIEW:
				View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
				return new ListItemViewHolder(itemView);
			default:
				View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_more, parent, false);
				return new LoadMoreViewHolder(loadingView);
		}
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		if (position < mUsers.size()) {
			((ListItemViewHolder) holder).bindData(mUsers.get(position), mItemClickListener);
		}
	}

	@Override
	public int getItemCount() {
		return mUsers == null || mUsers.size() <= 0 ? 0 : mIsFetchingMore ? mUsers.size() + 1 : mUsers.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (position < mUsers.size()) {
			return USER_ITEM_VIEW;
		} else if (position == mUsers.size()) {
			return LOADING_MORE_RESULTS;
		}
		return RecyclerView.NO_POSITION;
	}

	void addUsers(List<User> users) {
		int size = getItemCount();
		if (mUsers == null) {
			mUsers = new ArrayList<>();
		}
		mUsers.addAll(users);
		notifyItemRangeInserted(size, mUsers.size());
	}

	void setIsFetchingMore(boolean isFetchingMore) {
		if (mIsFetchingMore == isFetchingMore) {
			return;
		}
		mIsFetchingMore = isFetchingMore;
		if (mIsFetchingMore) {
			notifyItemInserted(getItemCount());
		} else {
			notifyItemRemoved(getItemCount());
		}
	}

	void clear() {
		if (mUsers != null && mUsers.size() > 0) {
			mUsers.clear();
			notifyDataSetChanged();
		}
	}

	boolean getIsFetchingMore() {
		return mIsFetchingMore;
	}
}
