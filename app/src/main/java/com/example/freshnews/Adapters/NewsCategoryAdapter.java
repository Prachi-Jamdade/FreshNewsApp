package com.example.freshnews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshnews.Entities.NewsCategory;
import com.example.freshnews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.ViewHolder> {

    private ArrayList<NewsCategory> newsCategoriesList;
    private Context context;
    private CategoriesClick categoriesClick;

    public NewsCategoryAdapter(ArrayList<NewsCategory> newsCategoriesList, Context context, CategoriesClick categoriesClick) {
        this.newsCategoriesList = newsCategoriesList;
        this.context = context;
        this.categoriesClick = categoriesClick;
    }

    public NewsCategoryAdapter(ArrayList<NewsCategory> newsCategoriesList, Context context) {
        this.newsCategoriesList = newsCategoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryAdapter.ViewHolder holder, int position) {
        NewsCategory newsCategory = newsCategoriesList.get(position);
        holder.categoryTV.setText(newsCategory.getNewsCategory());
        Picasso.get().load(newsCategory.getNewsCategoryImageUrl()).into(holder.categoryIV);

        holder.itemView.setOnClickListener(v -> {
            categoriesClick.onCategoryClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return newsCategoriesList.size();
    }

    public interface CategoriesClick {
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIV = itemView.findViewById(R.id.IVCategory);
            categoryTV = itemView.findViewById(R.id.TVCategory);
        }
    }
}
