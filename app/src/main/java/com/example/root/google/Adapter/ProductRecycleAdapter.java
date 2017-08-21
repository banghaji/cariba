package com.example.root.google.Adapter;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.root.google.Interface.ItemClickListener;
import com.example.root.google.R;
import com.example.root.google.Utility.Util;
import com.example.root.google.model.Product;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.CustomViewHolder> {
    private List<Product> product_item_list;
    private ItemClickListener click_listener;

    public ProductRecycleAdapter(List<Product> product_item_list) {
        this.product_item_list = product_item_list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent,false);
        parent.addView(view);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Product productItem = product_item_list.get(position);

//        if(productItem.getName().length()>35)
//        {
//            holder.product.setText(Html.fromHtml(productItem.getName().substring(0,35) + " ... "));
//        }
//        else
        //{
            holder.product.setText(Html.fromHtml(productItem.getName()));
        //}
        Integer star = productItem.getRating();
        String city = productItem.getCity();
        if(star > 0){
            holder.rating.setText("Rating : "+star);
        }
        if(!city.isEmpty()){
            //holder.city.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location_on_black_18dp, 0, 0, 0);
            holder.city.setText("kota : "+city);
        }
        holder.drawee_view.setImageURI(productItem.getImage_uri());
        holder.vendor.setText(Html.fromHtml(productItem.getVendor()));
        holder.price.setText(Html.fromHtml(productItem.getPrice_display()));
        // setting font
        Typeface roboto_font = Typeface.createFromAsset(holder.city.getContext().getAssets(),  "fonts/roboto.ttf");
        holder.city.setTypeface(roboto_font);
        Typeface yanoneka_font = Typeface.createFromAsset(holder.product.getContext().getAssets(),  "fonts/YanoneKaffeesatz-Bold.ttf");
        holder.product.setTypeface(yanoneka_font);
        Typeface teko_font = Typeface.createFromAsset(holder.price.getContext().getAssets(),  "fonts/Teko-Regular.ttf");
        holder.price.setTypeface(teko_font);
        holder.vendor.setTypeface(teko_font);
    }

    @Override
    public int getItemCount() {
        return (null != product_item_list ? product_item_list.size() : 0);
    }

    public void setClick_listener(ItemClickListener itemClickListener) {
        this.click_listener = itemClickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView product,price,vendor,rating,city;
        protected SimpleDraweeView drawee_view;
        protected Button button;

        public CustomViewHolder(View view) {
            super(view);
            this.product = (TextView) view.findViewById(R.id.titleProduct);
            this.drawee_view = (SimpleDraweeView) view.findViewById(R.id.imageProduct);
            this.vendor = (TextView) view.findViewById(R.id.vendorProduct);
            this.price = (TextView) view.findViewById(R.id.Productprice);
            this.rating = (TextView) view.findViewById(R.id.rating);
            this.city = (TextView) view.findViewById(R.id.city);
            this.button = (Button) view.findViewById(R.id.buy);
            this.button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (click_listener != null) click_listener.onClick(v, getAdapterPosition());
        }

    }
}