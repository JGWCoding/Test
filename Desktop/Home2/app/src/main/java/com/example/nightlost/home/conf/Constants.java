package com.example.nightlost.home.conf;

import com.example.nightlost.home.utils.LogUtils;

public class Constants {
	/**
	 LogUtils.LEVEL_ALL:显示所有日志
	 LogUtils.LEVEL_OFF:不显示所有日志
	 */
	public static final int		DEBUGLEVEL		    = LogUtils.LEVEL_ALL;
	public static final long	    PROTOCOLTIMEOUT	= 5 * 60 * 1000;//5分钟
	public static final long      CACHE_OUT_TIME    = 10 * 60 * 1000;//10分钟

	public static boolean islogin = false;

	public static final class Req {
		public static final String	  BASEURL			= "http://";
		public static final String	  BASEIMAGEURL	= BASEURL + "image?name=";
	}

}
