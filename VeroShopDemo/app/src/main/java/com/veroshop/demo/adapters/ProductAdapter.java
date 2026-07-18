package com.veroshop.demo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.veroshop.demo.R;
import com.veroshop.demo.models.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    private List<Product> products;
    private OnProductClickListener listener;

    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.tvName.setText(p.getName());
        holder.tvPrice.setText(String.format("$%.2f", p.getPrice()));
        holder.tvRating.setText(String.format("⭐ %.1f (%d)", p.getRating(), p.getReviewCount()));
        holder.tvEmoji.setText(p.getEmoji());
        holder.itemView.setOnClickListener(v -> listener.onProductClick(p));
        holder.itemView.setContentDescription("Product: " + p.getName());
    }

    @Override
    public int getItemCount() { return products.size(); }

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvRating, tvEmoji;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_product_name);
            tvPrice = itemView.findViewById(R.id.tv_item_product_price);
            tvRating = itemView.findViewById(R.id.tv_item_product_rating);
            tvEmoji = itemView.findViewById(R.id.tv_item_product_emoji);
        }
    }
}
