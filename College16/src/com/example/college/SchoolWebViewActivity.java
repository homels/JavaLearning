package com.example.college;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.example.college.R;

public class SchoolWebViewActivity extends Activity{
    private WebView webView;
    private ProgressBar bar;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Log.i("like","enter webview activity");
        webView = (WebView)findViewById(R.id.myWebView);
        bar=(ProgressBar)findViewById(R.id.myProgressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        //设置此属性，仅支持双击缩放，不支持触摸缩放
        webView.getSettings().setSupportZoom(true);
        //设置是否可缩放
        webView.getSettings().setBuiltInZoomControls(true);
        //设置此属性，可任意比例缩放。
        webView.getSettings().setUseWideViewPort(true);
        //设置初始缩放值
        webView.setInitialScale(50);
        Intent intent = getIntent();
        String school_website = intent.getStringExtra("school_website");
        Log.d("like",school_website);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
				return true;
            }
        });
		
	    webView.setWebChromeClient(new WebChromeClient() {
	        @Override
	        public void onProgressChanged(WebView view, int newProgress) {
	            if (newProgress == 100) {
	                bar.setVisibility(View.INVISIBLE);
	            } 
	            else if(View.INVISIBLE == bar.getVisibility()) {
	            	bar.setVisibility(View.VISIBLE);
	            }
	            bar.setProgress(newProgress);
	            super.onProgressChanged(view, newProgress);
	            }
	    });
	    webView.loadUrl(school_website);
	}
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		}else
			return super.onKeyDown(keyCode, event);
	}
}
