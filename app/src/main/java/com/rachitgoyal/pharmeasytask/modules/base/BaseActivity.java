package com.rachitgoyal.pharmeasytask.modules.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rachit Goyal on 13/06/18
 */

public abstract class BaseActivity extends AppCompatActivity {

	public Context mContext;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
	}
}
