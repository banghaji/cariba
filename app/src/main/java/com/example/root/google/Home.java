package com.example.root.google;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.google.Adapter.CustomViewPager;
import com.example.root.google.Adapter.ViewPagerAdapter;
import com.example.root.google.Fragment.Ecommerce;
import com.example.root.google.Fragment.Etravel;

public class Home extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout layout_tab;
    private CustomViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setIcon(R.drawable.abc_ic_menu_paste_mtrl_am_alpha);
        getSupportActionBar().setElevation(10);
        getSupportActionBar().hide();
        view_pager = (CustomViewPager) findViewById(R.id.viewpager);
        view_pager.setAnimationCacheEnabled(false);
        setupViewPager(view_pager);
        layout_tab = (TabLayout) findViewById(R.id.tabs);
        layout_tab.setupWithViewPager(view_pager);

        for (int i = 0; i < layout_tab.getTabCount(); i++) {

            TabLayout.Tab tab = layout_tab.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.setTextSize(18);
                tabTextView.setTextColor(Color.parseColor("#FFFFFF"));
                tabTextView.setAllCaps(true);
                if(i == 0){
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shopping_cart_white_24dp, 0, 0, 0);
                }else{
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_flight_white_24dp, 0, 0, 0);
                }
                tabTextView.setCompoundDrawablePadding(10);
                tabTextView.setText(tab.getText());
                Typeface yanoneka_font = Typeface.createFromAsset(tabTextView.getContext().getAssets(),  "fonts/YanoneKaffeesatz-Bold.ttf");

                tabTextView.setTypeface(yanoneka_font);

            }

        }
    }

    private void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Ecommerce(), getString(R.string.e_commerce));
        adapter.addFragment(new Etravel(), getString(R.string.e_travel));
        viewpager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(0, 0);
    }
}