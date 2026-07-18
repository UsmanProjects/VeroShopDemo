package com.veroshop.demo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private int quantity = 1;
    private boolean isFavorite = false;
    private Product product;
    private TextView tvQty;
    private ImageButton btnFavorite;
    private MockDataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        data = MockDataManager.getInstance();
        int productId = getIntent().getIntExtra("product_id", -1);
        product = data.getProductById(productId);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_product_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (product == null) { finish(); return; }

        toolbar.setTitle(product.getName());
        ((TextView) findViewById(R.id.tv_product_image_emoji)).setText(product.getEmoji());
        ((TextView) findViewById(R.id.tv_product_name)).setText(product.getName());
        ((TextView) findViewById(R.id.tv_product_price)).setText(String.format("$%.2f", product.getPrice()));
        ((TextView) findViewById(R.id.tv_product_rating)).setText(
                String.format("⭐ %.1f (%d reviews)", product.getRating(), product.getReviewCount()));
        ((TextView) findViewById(R.id.tv_product_description)).setText(product.getDescription());

        tvQty = findViewById(R.id.tv_quantity);
        btnFavorite = findViewById(R.id.btn_favorite);

        findViewById(R.id.btn_increase_qty).setOnClickListener(v -> {
            quantity++;
            tvQty.setText(String.valueOf(quantity));
        });

        findViewById(R.id.btn_decrease_qty).setOnClickListener(v -> {
            if (quantity > 1) { quantity--; tvQty.setText(String.valueOf(quantity)); }
        });

        btnFavorite.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            btnFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
            String msg = isFavorite ? getString(R.string.added_to_favorites) : getString(R.string.removed_from_favorites);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btn_add_to_cart).setOnClickListener(v -> {
            data.addToCart(product, quantity);
            Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.added_to_cart), Snackbar.LENGTH_LONG)
                    .setAction("View Cart", sv -> finish())
                    .show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { onBackPressed(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
