package com.veroshop.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.veroshop.demo.ProductListActivity;
import com.veroshop.demo.R;
import com.veroshop.demo.adapters.CategoryAdapter;
import com.veroshop.demo.data.MockDataManager;

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rv_categories);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setAdapter(new CategoryAdapter(MockDataManager.getInstance().getCategories(), cat -> {
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            intent.putExtra("category", cat.getName());
            startActivity(intent);
        }));
    }
}
