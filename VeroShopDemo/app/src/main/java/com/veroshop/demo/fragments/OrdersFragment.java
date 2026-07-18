package com.veroshop.demo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.snackbar.Snackbar;
import com.veroshop.demo.R;
import com.veroshop.demo.adapters.OrderAdapter;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.models.Order;

public class OrdersFragment extends Fragment {

    private RecyclerView rvOrders;
    private OrderAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvOrders = view.findViewById(R.id.rv_orders);
        swipeRefresh = view.findViewById(R.id.swipe_orders);

        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderAdapter(MockDataManager.getInstance().getOrders(), order ->
                Snackbar.make(view, "Order " + order.getOrderId() +
                        " — " + order.getStatus(), Snackbar.LENGTH_LONG).show());
        rvOrders.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(() -> {
            adapter.updateOrders(MockDataManager.getInstance().getOrders());
            swipeRefresh.setRefreshing(false);
            Snackbar.make(view, "Orders refreshed", Snackbar.LENGTH_SHORT).show();
        });
        swipeRefresh.setColorSchemeResources(R.color.primary);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.updateOrders(MockDataManager.getInstance().getOrders());
    }
}
