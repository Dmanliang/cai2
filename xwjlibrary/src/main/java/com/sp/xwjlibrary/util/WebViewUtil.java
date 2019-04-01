package com.sp.xwjlibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewUtil{

	private static OnWebViewListener listener;
	private static boolean isLoading;
	private static Context context;

	public interface OnWebViewListener{
		void onProgressChanged(WebView view, int newProgress);
		void onPageStarted(WebView view, String url, Bitmap favicon);
		void onPageFinished(WebView view, String url);
	}

	@SuppressLint("SetJavaScriptEnabled")
	public synchronized static void loadUrl(WebView webView,String url, OnWebViewListener listener) {
		WebViewUtil.listener = listener;
		setting(webView);

		webView.loadUrl(url);
		//防止跳出到浏览器
		webView.setWebChromeClient(new WebChromeClient(){
			//重写这个方法得到加载进度
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if(WebViewUtil.listener != null){
					WebViewUtil.listener.onProgressChanged(view,newProgress);
				}
			}
		});
		webView.setWebViewClient(new MyClient());
		webView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return isLoading;
			}
		});
	}

	private static void setting(WebView webView) {
		//设置开启支持JS方法
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setAllowFileAccess(true);
		settings.setAllowContentAccess(true);
		settings.setDomStorageEnabled(true);
//		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		//自适应屏幕
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		//自动缩放
		settings.setBuiltInZoomControls(true);
		settings.setSupportZoom(true);
		settings.setDisplayZoomControls(false);
		//支持获取手势焦点
		webView.requestFocusFromTouch();
	}

	/**
	 * 内部WebView的H5页面中调出第三方APP页面(如微信支付)
	 * @param context
	 * @param webView
	 * @param url
	 * @param listener
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public synchronized static void loadUrl(Context context,WebView webView, String url, OnWebViewListener listener) {
		WebViewUtil.context = context;
		loadUrl(webView,url,listener);
	}

	private static class MyClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// 如下方案可在非微信内部WebView的H5页面中调出微信支付
			if (url.startsWith("weixin://wap/pay?") && context != null) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				context.startActivity(intent);
				return true;
			}
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			isLoading = true;
			WebViewUtil.listener.onPageStarted(view,url,favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			isLoading = false;
			WebViewUtil.listener.onPageFinished(view,url);
		}
	}
	
	public synchronized static void loadHtmlText(WebView webView,String htmlText){
		setting(webView);
		webView.loadDataWithBaseURL(null,htmlText,"text/html","utf-8",null);
	}
	
}
