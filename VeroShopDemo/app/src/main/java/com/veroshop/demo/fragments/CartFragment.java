package com.veroshop.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.veroshop.demo.CheckoutActivity;
import com.veroshop.demo.R;
import com.veroshop.demo.adapters.CartAdapter;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.models.CartItem;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartListener {

    private RecyclerView rvCart;
    private CartAdapter adapter;
    private TextView tvTotal, tvCount;
    private LinearLayout llEmpty, llBottom;
    private MockDataManager data;
    private List<CartItem> cartItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = MockDataManager.getInstance();
        cartItems = data.getCartItems();

        rvCart = view.findViewById(R.id.rv_cart);
        tvTotal = view.findViewById(R.id.tv_cart_total);
        tvCount = view.findViewById(R.id.tv_cart_count);
        llEmpty = view.findViewById(R.id.ll_cart_empty);
        llBottom = view.findViewById(R.id.ll_cart_bottom);

        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartAdapter(cartItems, this);
        rvCart.setAdapter(adapter);

        view.findViewById(R.id.btn_checkout).setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Snackbar.make(view, "Your cart is empty!", Snackbar.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getActivity(), CheckoutActivity.class));
            }
        });

        view.findViewById(R.id.btn_continue_shopping).setOnClickListener(v ->
                requireActivity().onBackPressed());

        view.findViewById(R.id.btn_continue_shopping_empty).setOnClickListener(v ->
                requireActivity().onBackPressed());

        refreshUI(view);
    }

    private void refreshUI(View root) {
        View v = root != null ? root : getView();
        if (v == null) return;

        boolean empty = cartItems.isEmpty();
        rvCart.setVisibility(empty ? View.GONE : View.VISIBLE);
        llBottom.setVisibility(empty ? View.GONE : View.VISIBLE);
        llEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);

        tvTotal.setText(String.format("$%.2f", data.getCartTotal()));
        tvCount.setText(cartItems.size() + " item" + (cartItems.size() != 1 ? "s" : ""));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onIncrease(CartItem item, int position) {
        data.updateCartItemQuantity(item.getProduct().getId(), item.getQuantity() + 1);
        refreshUI(null);
    }

    @Override
    public void onDecrease(CartItem item, int position) {
        int newQty = item.getQuantity() - 1;
        data.updateCartItemQuantity(item.getProduct().getId(), newQty);
        refreshUI(null);
    }

    @Override
    public void onRemove(CartItem item, int position) {
        String name = item.getProduct().getName();
        data.removeFromCart(item.getProduct().getId());
        refreshUI(null);
        if (getView() != null) {
            Snackbar.make(getView(), name + " removed", Snackbar.LENGTH_SHORT)
                    .setAction(R.string.undo, v -> {
                        data.addToCart(item.getProduct(), item.getQuantity());
                        refreshUI(null);
                    }).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUI(null);
    }
}
