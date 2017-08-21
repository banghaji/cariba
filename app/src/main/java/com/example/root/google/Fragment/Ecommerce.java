package com.example.root.google.Fragment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.root.google.ProductActivity;
import com.example.root.google.R;

public class Ecommerce extends Fragment {
    private EditText search;
    private ImageButton do_search;

    public Ecommerce() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ecommerce, container, false);
        search = (EditText) view.findViewById(R.id.search_key);
        do_search = (ImageButton) view.findViewById(R.id.search_button);
        // set font
        Typeface yanoneka_font = Typeface.createFromAsset(search.getContext().getAssets(),  "fonts/YanoneKaffeesatz-Regular.ttf");

        search.setTypeface(yanoneka_font);
        do_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra(getString(R.string.search_key), String.valueOf(search.getText()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return view;
    }
}