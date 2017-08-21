package com.example.root.google.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragment_list = new ArrayList<>();
    private final List<String> title_list = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragment_list.add(fragment);
        title_list.add(title.toLowerCase());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}