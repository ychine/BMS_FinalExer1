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
    String[] productNames;
    String[] productPrices;
    TypedArray productImages;

    ItemClickListener itemClickListener;

    public ProductAdapter(Context context) {
        myContext = context;
        productNames = myContext.getResources().getStringArray(R.array.productNames);
        productPrices = myContext.getResources().getStringArray(R.array.productPrices);
        productImages = myContext.getResources().obtainTypedArray(R.array.productImages);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.product_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pdceoLatte.setText(productNames[position]);
        holder.priceceoLatte.setText("₱ " + productPrices[position]);
        holder.imgceoLatte.setImageDrawable(productImages.getDrawable(position));
    }

    @Override
    public int getItemCount() {
        return (productNames == null) ? 0 : productNames.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgceoLatte;
        TextView pdceoLatte;
        TextView priceceoLatte;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgceoLatte = itemView.findViewById(R.id.img_ceoLatte);
            pdceoLatte = itemView.findViewById(R.id.pd_ceoLatte);
            priceceoLatte = itemView.findViewById(R.id.price_ceoLatte);

            itemView.setOnClickListener(view -> {
                if (itemClickListener != null) {
                    itemClickListener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
