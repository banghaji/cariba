package com.example.root.google;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.root.google.Activity.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;
public class Webview extends BaseActivity {
    private WebView web_view;
    private Toolbar toolbar;
    public AVLoadingIndicatorView load_indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_web_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(getApplicationContext(),R.drawable.abc_ic_clear_mtrl_alpha));
        web_view = (WebView) findViewById(R.id.web_view);
        load_indicator = (AVLoadingIndicatorView) findViewById(R.id.load_indicator_web_view);
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        web_view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        web_view.setScrollbarFadingEnabled(true);
        web_view.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    load_indicator.setVisibility(View.GONE);
                } else {
                    load_indicator.setVisibility(View.VISIBLE);
                }
            }
        });
        web_view.setWebViewClient(new MyWebViewClient());
        Intent intent = getIntent();

        String url = intent.getStringExtra(getString(R.string.url));
        getSupportActionBar().setTitle("web_view");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            web_view.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        }else{
            web_view.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }
        web_view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_view.loadUrl(url);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }else if(id == R.id.back_action){
            web_view.goBack();
            return true;
        } else if (id == R.id.next_action){
            web_view.goForward();
        }
        return super.onOptionsItemSelected(item);
    }
}