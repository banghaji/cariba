package com.example.root.google;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.example.root.google.Adapter.ProductRecycleAdapter;
import com.example.root.google.Api.ApiClient;
import com.example.root.google.Interface.ApiInterface;
import com.example.root.google.Interface.ItemClickListener;
import com.example.root.google.model.JsonRequest;
import com.example.root.google.model.Product;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {

    private static final String TAG_ACTIVITY_NAME = ProductActivity.class.getSimpleName();
    private static String product_key;
    public List<Product> product_list = new ArrayList<>();
    public AVLoadingIndicatorView load_indicator;
    private RecyclerView recycle_view;
    private ProductRecycleAdapter adapter;
    private Toolbar toolbar;
    private LinearLayout sort;
    FrameLayout mRevealView;
    boolean hidden = true;
    boolean is_cheaper = false;
    Button re_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_product);
        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_clear_mtrl_alpha));
        adapter = new ProductRecycleAdapter(product_list);
        sort = (LinearLayout) findViewById(R.id.layout_sort);
        sort.setOnClickListener(this);
        mRevealView = (FrameLayout) findViewById(R.id.frame_bottom);
        mRevealView.setVisibility(View.GONE);
        re_search = (Button) findViewById(R.id.buttonresearch);
        re_search.setOnClickListener(this);

        product_key = getIntent().getStringExtra(getString(R.string.search_key));
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(product_key);
        JsonRequest jsonRequest = new JsonRequest(product_key, getString(R.string.elevenia));
        JsonRequest jsonRequestBukalapak = new JsonRequest(product_key, getString(R.string.bukalapak));
        JsonRequest jsonRequestTokopedia = new JsonRequest(product_key, getString(R.string.tokopedia));
        JsonRequest jsonRequestBliBli = new JsonRequest(product_key, getString(R.string.blibli));
        recycle_view = (RecyclerView) findViewById(R.id.recycleProduct);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.setAdapter(adapter);
        recycle_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mRevealView.setVisibility(View.GONE);
                hidden = true;
            }
        });
        load_indicator = (AVLoadingIndicatorView) findViewById(R.id.indicatorloadproduct);
        load_indicator.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        parseResponse(jsonRequest, apiService, is_cheaper);
        parseResponse(jsonRequestBukalapak, apiService, is_cheaper);
        parseResponse(jsonRequestTokopedia, apiService, is_cheaper);
    }

    public void parseResponseClear() {
        product_list.clear();
        adapter.notifyDataSetChanged();
    }

    public void parseResponse(JsonRequest jsonRequest, ApiInterface apiService, final boolean isCheaper) {

        Call<List<Product>> call = apiService.getAscProduct(jsonRequest);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {


                load_indicator.setVisibility(View.GONE);
                List<Product> responseProduct = response.body();
                Log.e(TAG_ACTIVITY_NAME, response.toString());
                for (int i = 0; i < responseProduct.size(); i++) {
                    Product product = new Product(
                            responseProduct.get(i).getVendor(),
                            responseProduct.get(i).getName(),
                            responseProduct.get(i).getUri(),
                            responseProduct.get(i).getImage_uri(),
                            responseProduct.get(i).getPrice_display(),
                            responseProduct.get(i).getPrice(),
                            responseProduct.get(i).getRating(),
                            responseProduct.get(i).getCity());
                    product_list.add(product);
                }
                if (isCheaper) {
                    Collections.sort(product_list, new Comparator<Product>() {
                        @Override
                        public int compare(Product lhs, Product rhs) {
                            Integer a = Integer.parseInt(lhs.getPrice().trim());
                            Integer b = Integer.parseInt(rhs.getPrice().trim());
                            return a.compareTo(b);
                        }
                    });
                }
                adapter.notifyDataSetChanged();
                adapter.setClick_listener(ProductActivity.this);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }

        });

    }

    @Override
    public void onClick(View v, int position) {
        final Product product = product_list.get(position);
        Intent intent = new Intent(this, Webview.class);
        intent.putExtra(getString(R.string.name), product.getName());
        intent.putExtra(getString(R.string.url), product.getUri());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_attachment) {
            if (hidden) {
                mRevealView.setVisibility(View.VISIBLE);
                hidden = false;
            } else {
                mRevealView.setVisibility(View.GONE);
                hidden = true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.c1:
                if (checked) {
                } else {
                }
                break;
            case R.id.c2:
                if (checked) {
                } else {
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        CheckBox c = (CheckBox) findViewById(R.id.c1);
        boolean checked = c.isChecked();
        switch (v.getId()) {
            case R.id.buttonresearch:
                mRevealView.setVisibility(View.GONE);
                hidden = true;
                load_indicator.setVisibility(View.VISIBLE);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                JsonRequest jsonRequest = new JsonRequest(product_key, getString(R.string.elevenia));
                JsonRequest jsonRequestBukalapak = new JsonRequest(product_key, getString(R.string.bukalapak));
                JsonRequest jsonRequestTokopedia = new JsonRequest(product_key, getString(R.string.tokopedia));
                parseResponseClear();
                parseResponse(jsonRequest, apiService, checked);
                parseResponse(jsonRequestBukalapak, apiService, checked);
                parseResponse(jsonRequestTokopedia, apiService, checked);
                break;
        }
    }

}