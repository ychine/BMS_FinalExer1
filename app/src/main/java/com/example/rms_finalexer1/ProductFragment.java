package com.example.rms_finalexer1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView rvProduct;
    ProductAdapter productAdapter;
    Button btnCart;
    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        // Initialize the cart lists, either from arguments or empty lists
        if (getArguments() == null) {
            cartNames = new ArrayList<>();
            cartPrices = new ArrayList<>();
            cartQuantity = new ArrayList<>();
            cartSubTotal = new ArrayList<>();
        } else {
            cartNames = getArguments().getStringArrayList("cartNames");
            cartPrices = getArguments().getStringArrayList("cartPrices");
            cartQuantity = getArguments().getStringArrayList("cartQuantity");
            cartSubTotal = getArguments().getStringArrayList("cartSubTotal");
        }

        rvProduct = root.findViewById(R.id.recyclerViewProduct);
        rvProduct.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        productAdapter = new ProductAdapter(root.getContext());
        productAdapter.setItemClickListener((view, position) -> {
            Bundle bundle1 = new Bundle();
            bundle1.putInt("position", position);
            bundle1.putStringArrayList("cartNames", cartNames);
            bundle1.putStringArrayList("cartPrices", cartPrices);
            bundle1.putStringArrayList("cartQuantity", cartQuantity);
            bundle1.putStringArrayList("cartSubTotal", cartSubTotal);
            Navigation.findNavController(view).navigate(R.id.action_productFragment_to_detailFragment, bundle1);
        });
        rvProduct.setAdapter(productAdapter);

        btnCart = root.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("cartNames", cartNames);
            bundle.putStringArrayList("cartPrices", cartPrices);
            bundle.putStringArrayList("cartQuantity", cartQuantity);
            bundle.putStringArrayList("cartSubTotal", cartSubTotal);
            Navigation.findNavController(view).navigate(R.id.action_productFragment_to_cartFragment, bundle);
        });

        return root;
    }
}
