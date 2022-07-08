package com.example.freshnews.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.freshnews.Adapters.NewsAdapter;
import com.example.freshnews.Adapters.NewsCategoryAdapter;
import com.example.freshnews.Entities.News;
import com.example.freshnews.Entities.NewsArticles;
import com.example.freshnews.Entities.NewsCategory;
import com.example.freshnews.API.RetrofitAPI;
import com.example.freshnews.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsCategoryAdapter.CategoriesClick {
    public String API_KEY = "9c57845c931a4a159655d2dfebf24491";

    private ActivityMainBinding mBinding;
    private ArrayList<NewsArticles> newsArticlesList;
    private ArrayList<NewsCategory> newsCategoryList;
    private NewsAdapter newsAdapter;
    private NewsCategoryAdapter newsCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        newsArticlesList = new ArrayList<>();
        newsCategoryList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsArticlesList, this);
        newsCategoryAdapter = new NewsCategoryAdapter(newsCategoryList, this, this::onCategoryClick);
        mBinding.RVNews.setLayoutManager(new LinearLayoutManager(this));
        mBinding.RVNews.setAdapter(newsAdapter);
        mBinding.RVCategories.setAdapter(newsCategoryAdapter);
        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }

    private void getCategories() {
        newsCategoryList.add(new NewsCategory("All",
                "https://images.unsplash.com/photo-1612031736184-77bc60f94c06?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxjb2xsZWN0aW9uLXBhZ2V8MXw4NTYwODUyfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("Technology",
                "https://images.unsplash.com/photo-1656275544060-1c0515d192b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"));
        newsCategoryList.add(new NewsCategory("Science",
                "https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8c2NpZW5jZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("Sports",
                "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnR8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("Business",
                "https://images.unsplash.com/photo-1581092583537-20d51b4b4f1b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxjb2xsZWN0aW9uLXBhZ2V8MTZ8MzY3MTk2OXx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("Entertainment",
                "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("General",
                "https://images.unsplash.com/photo-1493612276216-ee3925520721?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        newsCategoryList.add(new NewsCategory("Health",
                "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));

        newsCategoryAdapter.notifyDataSetChanged();
    }

    private void getNews(String category) {
        mBinding.PBLoading.setVisibility(View.VISIBLE);
        newsArticlesList.clear();
        String categoryURL = "http://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apikey=" + API_KEY;
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=" + API_KEY;

        String BASE_URL = "http://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<News> call;

        if(category.equals("All")) {
            call = retrofitAPI.getAllNews(url);
        }
        else {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                mBinding.PBLoading.setVisibility(View.GONE);
                ArrayList<NewsArticles> newsArticles = news.getArticles();
                for(int i=0; i<newsArticles.size(); i++) {
                    newsArticlesList.add(new NewsArticles(newsArticles.get(i).getTitle(),
                            newsArticles.get(i).getDescription(),
                            newsArticles.get(i).getUrlToImage(),
                            newsArticles.get(i).getUrl(),
                            newsArticles.get(i).getContent()));
                }

                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = newsCategoryList.get(position).getNewsCategory();
        getNews(category);
    }
}