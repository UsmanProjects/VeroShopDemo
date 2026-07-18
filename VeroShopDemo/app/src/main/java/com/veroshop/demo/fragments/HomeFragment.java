package com.veroshop.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.veroshop.demo.ProductDetailActivity;
import com.veroshop.demo.ProductListActivity;
import com.veroshop.demo.R;
import com.veroshop.demo.adapters.CategoryAdapter;
import com.veroshop.demo.adapters.ProductAdapter;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.models.Category;
import com.veroshop.demo.models.Product;

public class HomeFragment extends Fragment {

    private RecyclerView rvCategories, rvProducts;
    private ProductAdapter productAdapter;
    private MockDataManager data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = MockDataManager.getInstance();

        rvCategories = view.findViewById(R.id.rv_home_categories);
        rvProducts = view.findViewById(R.id.rv_home_products);

        // Categories horizontal
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCategories.setAdapter(new CategoryAdapter(data.getCategories(), cat -> {
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            intent.putExtra("category", cat.getName());
            startActivity(intent);
        }));

        // Products grid
        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productAdapter = new ProductAdapter(data.getAllProducts(), product -> openProductDetail(product));
        rvProducts.setAdapter(productAdapter);

        // Search
        SearchView searchView = view.findViewById(R.id.sv_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                productAdapter.updateProducts(data.searchProducts(query));
                return true;
            }
            @Override public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) productAdapter.updateProducts(data.getAllProducts());
                else productAdapter.updateProducts(data.searchProducts(newText));
                return true;
            }
        });

        view.findViewById(R.id.tv_see_all).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            intent.putExtra("category", "All");
            startActivity(intent);
        });
    }

    private void openProductDetail(Product product) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("product_id", product.getId());
        startActivity(intent);
    }
}
