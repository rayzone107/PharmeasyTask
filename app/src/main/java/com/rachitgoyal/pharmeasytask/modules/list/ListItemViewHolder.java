package com.rachitgoyal.pharmeasytask.modules.list;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rachitgoyal.pharmeasytask.R;
import com.rachitgoyal.pharmeasytask.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rachit Goyal on 13/06/18
 */

class ListItemViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.user_item_cv)
	CardView mUserItemCV;

	@BindView(R.id.image_iv)
	CircleImageView mImageIV;

	@BindView(R.id.name_tv)
	TextView mNameTV;

	@BindView(R.id.profession_tv)
	TextView mProfessionTV;

	ListItemViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	void bindData(final User user, final OnItemClickListener itemClickListener) {

		mUserItemCV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				itemClickListener.onItemClick(user, mImageIV);
			}
		});

		ViewCompat.setTransitionName(mImageIV, user.getFirstName());

		RequestOptions requestOptions = new RequestOptions()
				.placeholder(R.mipmap.ic_launcher)
				.error(R.mipmap.ic_launcher_round);

		Glide.with(mImageIV.getContext())
				.setDefaultRequestOptions(requestOptions)
				.load(user.getImageUrl())
				.into(mImageIV);

		String name = user.getFirstName() + " " + user.getLastName();
		mNameTV.setText(name);
		mProfessionTV.setText(user.getProfession());
	}

	public interface OnItemClickListener {
		void onItemClick(User user, CircleImageView sharedImageView);
	}
}
