package com.example.rms_finalexer1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context myContext;
    String[] productNames;
    String[] productPrices;
    TypedArray productImages;

    public ProductAdapter(Context context){
        myContext = context;

        // Fetch data from resources
        productNames = myContext.getResources().getStringArray(R.array.productNames);
        productPrices = myContext.getResources().getStringArray(R.array.productPrices);
        productImages = myContext.getResources().obtainTypedArray(R.array.productImages);

        // Log data to ensure it's not empty
        Log.d("ProductAdapter", "Product Names: " + productNames.length);
        Log.d("ProductAdapter", "Product Prices: " + productPrices.length);
        Log.d("ProductAdapter", "Product Images: " + productImages.length());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate product layout for each item in RecyclerView
        View view = LayoutInflater.from(myContext).inflate(R.layout.product_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return number of items in the list (must not return 0)
        if (productNames == null || productNames.length == 0) {
            Log.e("ProductAdapter", "No products to display");
            return 0;
        }
        return productNames.length;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind data to each view (image, name, price)
        Log.d("ProductAdapter", "Binding data for position: " + position);
        holder.pdceoLatte.setText(productNames[position]);
        holder.priceceoLatte.setText(productPrices[position]);

        // Set product image
        holder.imgceoLatte.setImageDrawable(productImages.getDrawable(position));

        // Log image being set
        Log.d("ProductAdapter", "Set image for product: " + productNames[position]);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder for each item in the list
        ImageView imgceoLatte;
        TextView pdceoLatte;
        TextView priceceoLatte;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgceoLatte = itemView.findViewById(R.id.img_ceoLatte);
            pdceoLatte = itemView.findViewById(R.id.pd_ceoLatte);
            priceceoLatte = itemView.findViewById(R.id.price_ceoLatte);
        }
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        // Recycling the image drawable resource after use to prevent memory leaks
        productImages.recycle();
    }
}
