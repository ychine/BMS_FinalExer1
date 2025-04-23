package com.example.rms_finalexer1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductFragment extends Fragment {

    // Define parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView rvProduct;
    ProductAdapter productAdapter;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        // Initialize RecyclerView
        rvProduct = root.findViewById(R.id.recyclerViewProduct);
        rvProduct.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        // Initialize Adapter
        productAdapter = new ProductAdapter(getContext());

        // Logging item count after adapter is set
        Log.d("ProductFragment", "Product names count: " + productAdapter.getItemCount());

        // Check for empty dataset
        if (productAdapter.getItemCount() == 0) {
            Log.d("ProductFragment", "The product adapter has no items!");
        }

        // Set the adapter to RecyclerView
        rvProduct.setAdapter(productAdapter);

        return root;
    }
}
