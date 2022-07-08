package com.example.freshnews.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.freshnews.databinding.ActivityNewsDetailsBinding;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    String title, subtitle, url, imageUrl, content;
    private ActivityNewsDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("image");
        content = getIntent().getStringExtra("content");

        mBinding.TVTitle.setText(title);
        mBinding.TVContent.setText(content);
        mBinding.TVSubTitle.setText(subtitle);

        Picasso.get().load(imageUrl).into(mBinding.IVNews);

        mBinding.BtnReadNews.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        mBinding.BtnShareNews.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                String body = title + "\n" + url + "\n";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "Share with: "));
            }
            catch (Exception e){
                Toast.makeText(NewsDetailsActivity.this, "Sorry. \nCan't be Share", Toast.LENGTH_SHORT).show();
            }
        });
    }
}