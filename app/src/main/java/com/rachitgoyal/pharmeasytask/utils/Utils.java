package com.rachitgoyal.pharmeasytask.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Rachit Goyal on 14/06/18
 */

public class Utils {

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
		return !(info == null || !info.isConnected());
	}
}
