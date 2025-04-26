package com.example.rms_finalexer1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     String mParam1;
     String mParam2;

    private int position;
     ImageView ivDetail;
     TextView tvDetailName, tvDetailPrice, tvDescription;
     EditText etQuantity;
     DecimalFormat decimalFormat;
     Button btnAdd;


     ArrayList<String> cartNames;
     ArrayList<String> cartPrices;
     ArrayList<String> cartQuantity;
     ArrayList<String> cartSubTotal;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        ivDetail = root.findViewById(R.id.imageViewDetail);
        tvDetailName = root.findViewById(R.id.textDetailName);
        tvDetailPrice = root.findViewById(R.id.textDetailPrice);
        tvDescription = root.findViewById(R.id.textDescription);
        etQuantity = root.findViewById(R.id.editTextQuantity);
        btnAdd = root.findViewById(R.id.buttonAdd);

        // Initialize cart ArrayLists
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

            if (cartNames == null) cartNames = new ArrayList<>();
            if (cartPrices == null) cartPrices = new ArrayList<>();
            if (cartQuantity == null) cartQuantity = new ArrayList<>();
            if (cartSubTotal == null) cartSubTotal = new ArrayList<>();
        }

        position = getArguments().getInt("position", 0);

        // Product data
        int[] productImages = {
                R.drawable.ceolatte,
                R.drawable.buttercremelatte,
                R.drawable.spanishlatte,
                R.drawable.gulamelaka,
                R.drawable.seasaltbrownsugar,
                R.drawable.australianchoco,
                R.drawable.saltedcaramel,
                R.drawable.matchacraze
        };
        String[] productNames = getResources().getStringArray(R.array.productNames);
        String[] productPrices = getResources().getStringArray(R.array.productPrices);

        // Set product details
        ivDetail.setImageResource(productImages[position]);
        tvDetailName.setText(productNames[position]);
        tvDetailPrice.setText("₱ " + productPrices[position]);

        // Set description based on position
        switch (position) {
            case 0: tvDescription.setText(R.string.CEOLattee_desc); break;
            case 1: tvDescription.setText(R.string.ButtercremeLatte_desc); break;
            case 2: tvDescription.setText(R.string.SpanishLatte_desc); break;
            case 3: tvDescription.setText(R.string.GulaMelaka_desc); break;
            case 4: tvDescription.setText(R.string.SeasaltBrownSugar_desc); break;
            case 5: tvDescription.setText(R.string.AustralianChoco_desc); break;
            case 6: tvDescription.setText(R.string.SaltedCaramel_desc); break;
            case 7: tvDescription.setText(R.string.MatchaCraze_desc); break;
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etQuantity.length() == 0 || Integer.parseInt(etQuantity.getText().toString()) == 0) {
                    Snackbar.make(v, "Invalid Quantity", Snackbar.LENGTH_SHORT).show();
                } else {
                    String quantityStr = etQuantity.getText().toString();
                    int quantity = Integer.parseInt(quantityStr);

                    double price = Double.parseDouble(productPrices[position]);
                    double subtotal = quantity * price;


                    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
                    String formattedSubtotal = decimalFormat.format(subtotal);

                    cartNames.add(productNames[position]);
                    cartPrices.add(productPrices[position]);
                    cartQuantity.add(quantityStr);
                    cartSubTotal.add(formattedSubtotal);

                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArrayList("cartNames", cartNames);
                    bundle2.putStringArrayList("cartPrices", cartPrices);
                    bundle2.putStringArrayList("cartQuantity", cartQuantity);
                    bundle2.putStringArrayList("cartSubTotal", cartSubTotal);

                    Navigation.findNavController(v).navigate(R.id.action_detailFragment_to_cartFragment, bundle2);

                    Snackbar.make(v, "Added to Cart!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
