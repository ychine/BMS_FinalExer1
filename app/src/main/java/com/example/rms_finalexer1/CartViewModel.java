package com.example.rms_finalexer1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {

    // LiveData for cart details (using LiveData to encapsulate MutableLiveData)
    public final MutableLiveData<ArrayList<String>> cartNames = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<ArrayList<String>> cartPrices = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<ArrayList<String>> cartQuantity = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<ArrayList<String>> cartSubTotal = new MutableLiveData<>(new ArrayList<>());

    // Public getters for LiveData to observe data changes
    public LiveData<ArrayList<String>> getCartNames() {
        return cartNames;
    }

    public LiveData<ArrayList<String>> getCartPrices() {
        return cartPrices;
    }

    public LiveData<ArrayList<String>> getCartQuantity() {
        return cartQuantity;
    }

    public LiveData<ArrayList<String>> getCartSubTotal() {
        return cartSubTotal;
    }

    // Method to add an item to the cart
    public void addItemToCart(String name, String price, String quantity, String subtotal) {
        ArrayList<String> names = cartNames.getValue();
        ArrayList<String> prices = cartPrices.getValue();
        ArrayList<String> quantities = cartQuantity.getValue();
        ArrayList<String> subtotals = cartSubTotal.getValue();

        if (names != null && prices != null && quantities != null && subtotals != null) {
            names.add(name);
            prices.add(price);
            quantities.add(quantity);
            subtotals.add(subtotal);

            cartNames.setValue(names);
            cartPrices.setValue(prices);
            cartQuantity.setValue(quantities);
            cartSubTotal.setValue(subtotals);
        }
    }

    // Method to remove an item from the cart
    public void removeItemFromCart(int position) {
        ArrayList<String> names = cartNames.getValue();
        ArrayList<String> prices = cartPrices.getValue();
        ArrayList<String> quantities = cartQuantity.getValue();
        ArrayList<String> subtotals = cartSubTotal.getValue();

        if (names != null && prices != null && quantities != null && subtotals != null) {
            if (position >= 0 && position < names.size()) {
                names.remove(position);
                prices.remove(position);
                quantities.remove(position);
                subtotals.remove(position);

                cartNames.setValue(names);
                cartPrices.setValue(prices);
                cartQuantity.setValue(quantities);
                cartSubTotal.setValue(subtotals);
            }
        }
    }

    public void clearCart() {
        cartNames.setValue(new ArrayList<>());
        cartPrices.setValue(new ArrayList<>());
        cartQuantity.setValue(new ArrayList<>());
        cartSubTotal.setValue(new ArrayList<>());
    }
}
