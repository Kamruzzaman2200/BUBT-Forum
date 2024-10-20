package com.example.bubtforun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bubtforun.databinding.ActivityNewsFeedBinding;

public class NewsFeedActivity extends AppCompatActivity {
    ActivityNewsFeedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goCreatePost
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(NewsFeedActivity.this, CreatePostActivity.class));
                    }
                });

    }
}