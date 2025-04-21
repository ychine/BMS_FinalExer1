package com.example.rms_finalexer1;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context myContext;
    String productNames[];
    String productPrices[];
    TypedArray productImages;

    public ProductAdapter(Context context){
        myContext = context;
        productNames = myContext.getResources().getStringArray(R.array.productNames);
        productPrices = myContext.getResources().getStringArray(R.array.productPrices);
        productImages = myContext.getResources().obtainTypedArray(R.array.productImages);

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.product_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (productNames == null)
        return 0;
        else
            return productNames.length;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pdceoLatte.setText(productNames[position]);
        holder.priceceoLatte.setText(productPrices[position]);
        holder.imgceoLatte.setImageDrawable(productImages.getDrawable(position));

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgceoLatte;
        TextView  pdceoLatte;
        TextView  priceceoLatte;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgceoLatte = itemView.findViewById(R.id.img_ceoLatte);
            pdceoLatte = itemView.findViewById(R.id.pd_ceoLatte);
            priceceoLatte = itemView.findViewById(R.id.price_ceoLatte);
        }
    }

}
