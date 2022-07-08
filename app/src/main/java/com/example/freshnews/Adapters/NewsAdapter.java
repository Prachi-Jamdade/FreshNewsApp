package com.example.freshnews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshnews.Entities.NewsArticles;
import com.example.freshnews.Activities.NewsDetailsActivity;
import com.example.freshnews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsArticles> newsArticlesList;
    private Context context;

    public NewsAdapter(ArrayList<NewsArticles> newsArticlesList, Context context) {
        this.newsArticlesList = newsArticlesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsArticles newsArticle = newsArticlesList.get(position);
        holder.newsTitleTV.setText(newsArticle.getTitle());
        holder.newsSubTitleTV.setText(newsArticle.getDescription());
        Picasso.get().load(newsArticle.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("title", newsArticle.getTitle());
            intent.putExtra("subtitle", newsArticle.getDescription());
            intent.putExtra("content", newsArticle.getContent());
            intent.putExtra("image", newsArticle.getUrlToImage());
            intent.putExtra("url", newsArticle.getUrl());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return newsArticlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitleTV, newsSubTitleTV;
        private ImageView newsIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitleTV = itemView.findViewById(R.id.TVNewsHeading);
            newsSubTitleTV = itemView.findViewById(R.id.TVNewsSubHeading);
            newsIV = itemView.findViewById(R.id.IVNews);
        }
    }
}
