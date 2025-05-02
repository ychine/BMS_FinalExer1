package com.example.rms_finalexer1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button getBtnList;
    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;
    RecyclerView rvCart;
    CartAdapter cartAdapter;
    TextView tvTotal;
    double totalAmount = 0;
    Button btnList;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

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

        rvCart = root.findViewById(R.id.recyclerViewCart);
        rvCart.setLayoutManager(new LinearLayoutManager(root.getContext()));
        cartAdapter = new CartAdapter(root.getContext(), cartNames, cartPrices, cartQuantity, cartSubTotal);
        rvCart.setAdapter(cartAdapter);

        tvTotal = root.findViewById(R.id.textTotal);
        for (int i = 0; i < cartNames.size(); i++) {
            totalAmount = totalAmount + Double.parseDouble(cartSubTotal.get(i));
        }
        tvTotal.setText(String.valueOf(totalAmount));

        btnList = root.findViewById(R.id.btnList);
        btnList.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("cartNames", cartNames);
            bundle.putStringArrayList("cartPrices", cartPrices);
            bundle.putStringArrayList("cartQuantity", cartQuantity);
            bundle.putStringArrayList("cartSubTotal", cartSubTotal);
            Navigation.findNavController(v).navigate(R.id.action_cartFragment_to_productFragment, bundle);
        });

        return root;
    }
}
