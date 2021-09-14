package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentBooks, btnFinishedBooks, btnWishList, btnFavoriteBooks, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        btnFinishedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FinishedBookActivity.class);
                startActivity(intent);
            }
        });

        Utils.getInstance();
    }

    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentBooks = findViewById(R.id.btnCurrentBooks);
        btnFinishedBooks = findViewById(R.id.btnFinishedBooks);
        btnWishList = findViewById(R.id.btnWishList);
        btnFavoriteBooks = findViewById(R.id.btnFavoriteBooks);
        btnAbout = findViewById(R.id.btnAbout);
    }
}