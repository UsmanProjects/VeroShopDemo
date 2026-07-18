package com.veroshop.demo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.veroshop.demo.R;
import com.veroshop.demo.models.Order;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public interface OnOrderClickListener {
        void onOrderClick(Order order);
    }

    private List<Order> orders;
    private OnOrderClickListener listener;

    public OrderAdapter(List<Order> orders, OnOrderClickListener listener) {
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order o = orders.get(position);
        holder.tvOrderId.setText(o.getOrderId());
        holder.tvStatus.setText(o.getStatus());
        holder.tvDate.setText(o.getDate());
        holder.tvTotal.setText(String.format("$%.2f", o.getTotal()));
        holder.tvItemCount.setText(o.getItemCount() + " item" + (o.getItemCount() != 1 ? "s" : ""));
        holder.itemView.setOnClickListener(v -> listener.onOrderClick(o));
        holder.itemView.setContentDescription("Order " + o.getOrderId());
    }

    @Override
    public int getItemCount() { return orders.size(); }

    public void updateOrders(List<Order> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvStatus, tvDate, tvTotal, tvItemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvStatus = itemView.findViewById(R.id.tv_order_status);
            tvDate = itemView.findViewById(R.id.tv_order_date);
            tvTotal = itemView.findViewById(R.id.tv_order_total);
            tvItemCount = itemView.findViewById(R.id.tv_order_items_count);
        }
    }
}
