package com.veroshop.demo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.veroshop.demo.R;
import com.veroshop.demo.models.CartItem;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface CartListener {
        void onIncrease(CartItem item, int position);
        void onDecrease(CartItem item, int position);
        void onRemove(CartItem item, int position);
    }

    private List<CartItem> items;
    private CartListener listener;

    public CartAdapter(List<CartItem> items, CartListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.tvName.setText(item.getProduct().getName());
        holder.tvPrice.setText(String.format("$%.2f", item.getProduct().getPrice()));
        holder.tvQty.setText(String.valueOf(item.getQuantity()));
        holder.tvEmoji.setText(item.getProduct().getEmoji());

        holder.btnIncrease.setOnClickListener(v -> listener.onIncrease(item, holder.getAdapterPosition()));
        holder.btnDecrease.setOnClickListener(v -> listener.onDecrease(item, holder.getAdapterPosition()));
        holder.btnRemove.setOnClickListener(v -> listener.onRemove(item, holder.getAdapterPosition()));
        holder.itemView.setContentDescription("Cart item: " + item.getProduct().getName());
    }

    @Override
    public int getItemCount() { return items.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQty, tvEmoji;
        View btnIncrease, btnDecrease, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cart_item_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_item_price);
            tvQty = itemView.findViewById(R.id.tv_cart_qty);
            tvEmoji = itemView.findViewById(R.id.iv_cart_item_image);
            btnIncrease = itemView.findViewById(R.id.btn_cart_increase);
            btnDecrease = itemView.findViewById(R.id.btn_cart_decrease);
            btnRemove = itemView.findViewById(R.id.btn_cart_remove);
        }
    }
}
