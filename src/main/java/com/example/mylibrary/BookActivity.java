package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWishlist, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorites;
    private ImageView imgBook;
    private static final String TAG = "BookActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        String longDescription = "A story of how a woman named Aomame begins to \n" +
//                "notice strange changes occurring in the world. \n" +
//                "She is quickly caught up in a plot involving Sakigake,\n" +
//                " a religious cult, and her childhood love, Tengo,\n" +
//                " and embarks on a journey to discover what is \"real\".";
//
//
//        Book book = new Book(1,"1Q84", "Haruki Murakammi",1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg",
//                "A work of maddening brilliance", longDescription);

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId != -1)   {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook)   {
                    setData(incomingBook);

                    handleFinished(incomingBook);
                    handleWishlist(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavoriteBook(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBook(Book book) {
        ArrayList<Book> favorites = Utils.getInstance().getFavoriteBooks();

        boolean existsInFavorites = false;

        for (Book b : favorites) {
            if (b.getId() == book.getId())  {
                existsInFavorites = true;
            }
        }

        if (existsInFavorites)   {
            btnAddToFavorites.setEnabled(false);
        } else  {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavoriteBooks(book))    {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                    } else  {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(Book book) {
        ArrayList<Book> currentlyReading = Utils.getInstance().getCurrentBooks();

        boolean existsInCurrentlyReading = false;

        for (Book b : currentlyReading) {
            if (b.getId() == book.getId())  {
                existsInCurrentlyReading = true;
            }
        }

        if (existsInCurrentlyReading)   {
            btnAddToCurrentlyReading.setEnabled(false);
        } else  {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentBooks(book))    {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentBooksActivity.class);
                        startActivity(intent);
                    } else  {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishlist(Book book) {
        Log.d(TAG, "handleWishlist: IN HERE");
        ArrayList<Book> wishList = Utils.getInstance().getWishList();

        boolean existsInWishList = false;

        for (Book b : wishList) {
            if (b.getId() == book.getId())  {
                existsInWishList = true;
            }
        }

        if (existsInWishList)   {
            btnAddToWishlist.setEnabled(false);
        } else  {
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWishlist(book))    {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WishlistActivity.class);
                        startActivity(intent);
                    } else  {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and Disable button
     * Add the book to Finished Book ArrayList
     * @param book
     */
    private void handleFinished(Book book) {
        ArrayList<Book> finished = Utils.getInstance().getFinishedBooks();

        boolean existsInFinished = false;

        for (Book b : finished) {
            if (b.getId() == book.getId())  {
                existsInFinished = true;
            }
        }

        if (existsInFinished)   {
            btnAddToAlreadyRead.setEnabled(false);
        } else  {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFinished(book))    {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FinishedBookActivity.class);
                        startActivity(intent);
                    } else  {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDisc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgBook);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtBookAuthor);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtBookPages);
        txtDescription = findViewById(R.id.txtBookLongDesc);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorites = findViewById(R.id.btnAddFavorite);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);

        imgBook = findViewById(R.id.imgBook);
    }


}