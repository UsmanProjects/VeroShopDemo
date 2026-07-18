package com.veroshop.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.veroshop.demo.adapters.ProductAdapter;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.models.Product;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        String category = getIntent().getStringExtra("category");
        if (category == null) category = "All";

        MaterialToolbar toolbar = findViewById(R.id.toolbar_product_list);
        toolbar.setTitle(category.equals("All") ? "All Products" : category);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MockDataManager data = MockDataManager.getInstance();
        List<Product> products = category.equals("All") ?
                data.getAllProducts() : data.getProductsByCategory(category);

        RecyclerView rv = findViewById(R.id.rv_product_list);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new ProductAdapter(products, product -> {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId());
            startActivity(intent);
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { onBackPressed(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
