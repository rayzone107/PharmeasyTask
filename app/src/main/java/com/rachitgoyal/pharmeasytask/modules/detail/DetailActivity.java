package com.rachitgoyal.pharmeasytask.modules.detail;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rachitgoyal.pharmeasytask.R;
import com.rachitgoyal.pharmeasytask.models.User;
import com.rachitgoyal.pharmeasytask.modules.base.BaseActivity;
import com.rachitgoyal.pharmeasytask.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends BaseActivity implements DetailContract.View {

	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@BindView(R.id.user_iv)
	CircleImageView mUserIV;

	@BindView(R.id.name_tv)
	TextView mNameTV;

	@BindView(R.id.profession_tv)
	TextView mProfessionTV;

	@BindView(R.id.followers_tv)
	TextView mFollowersTV;

	@BindView(R.id.following_tv)
	TextView mFollowingTV;

	@BindView(R.id.purchases_tv)
	TextView mPurchasesTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Window w = getWindow();
		w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		ButterKnife.bind(this);

		Bundle extras = getIntent().getExtras();
		assert extras != null;
		User user = (User) extras.getSerializable(Constants.EXTRAS.USER_EXTRA);
		if (user != null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				String imageTransitionName = extras.getString(Constants.EXTRAS.IMAGE_TRANSITION_NAME);
				mUserIV.setTransitionName(imageTransitionName);
			}

			setSupportActionBar(mToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);

			Glide.with(mContext)
					.load(user.getImageUrl())
					.listener(new RequestListener<Drawable>() {
						@Override
						public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
							supportStartPostponedEnterTransition();
							return false;
						}

						@Override
						public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource,
													   boolean isFirstResource) {
							supportStartPostponedEnterTransition();
							return false;
						}
					})
					.into(mUserIV);

			String name = user.getFirstName() + " " + user.getLastName();
			mNameTV.setText(name);
			mProfessionTV.setText(user.getProfession());
			mFollowersTV.setText(String.valueOf(user.getFollowers()));
			mFollowingTV.setText(String.valueOf(user.getFollowing()));
			mPurchasesTV.setText(String.valueOf(user.getPurchases()));
		} else {
			finish();
		}
	}

	@OnClick(R.id.add_friend_button)
	public void onAddFriendClicked() {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				super.onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
