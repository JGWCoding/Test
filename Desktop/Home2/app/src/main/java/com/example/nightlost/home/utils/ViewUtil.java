package com.example.nightlost.home.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by NightLost on 2017/5/6.
 */

public class ViewUtil {

    public static View getView(Context context,int reID){
        View view = View.inflate(context,reID,null);
        return view;
    }

}
