package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String FINISHED_BOOKS_KEY = "finished_books";
    private static final String WISHLIST_KEY = "wishlist";
    private static final String CURRENT_BOOKS_KEY = "current_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;


//    private static ArrayList<Book>  allBooks;
//    private static ArrayList<Book>  finishedBooks;
//    private static ArrayList<Book>  wishList;
//    private static ArrayList<Book>  currentBooks;
//    private static ArrayList<Book>  favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);

        if (null == getAllBooks())   {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getFinishedBooks())   {
            editor.putString(FINISHED_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWishList())   {
            editor.putString(WISHLIST_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentBooks())   {
            editor.putString(CURRENT_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks())   {
            editor.putString(FAVORITE_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1,"1Q84", "Haruki Murakammi",1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg",
                "A work of maddening brilliance", "Long Description"));
        books.add(new Book(2,"The Myth of Sisyphus", "Albert Camus",250, "https://images-na.ssl-images-amazon.com/images/I/51VLai+9nWS.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought", "Long Description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();

    }

    public static synchronized Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getFinishedBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FINISHED_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getWishList() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISHLIST_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getCurrentBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENT_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY,null),type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books)  {
            for (Book b: books)  {
                if (b.getId() == id)    {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToFinished(Book book) {
        ArrayList<Book> books = getFinishedBooks();
        if (null != books)  {
            if (books.add(book))    {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FINISHED_BOOKS_KEY);
                editor.putString(FINISHED_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishlist(Book book) {
        ArrayList<Book> books = getWishList();
        if (null != books)  {
            if (books.add(book))    {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_KEY);
                editor.putString(WISHLIST_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentBooks(Book book) {
        ArrayList<Book> books = getCurrentBooks();
        if (null != books)  {
            if (books.add(book))    {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENT_BOOKS_KEY);
                editor.putString(CURRENT_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books)  {
            if (books.add(book))    {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromFinished(Book book)    {
        ArrayList<Book> books = getFinishedBooks();
        if (null != books)  {
            for (Book b: books) {
                if (b.getId() == book.getId())  {
                    if (books.remove(b))    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FINISHED_BOOKS_KEY);
                        editor.putString(FINISHED_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWishlist(Book book)    {
        ArrayList<Book> books = getWishList();
        if (null != books)  {
            for (Book b: books) {
                if (b.getId() == book.getId())  {
                    if (books.remove(b))    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST_KEY);
                        editor.putString(WISHLIST_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentBooks(Book book)    {
        ArrayList<Book> books = getCurrentBooks();
        if (null != books)  {
            for (Book b: books) {
                if (b.getId() == book.getId())  {
                    if (books.remove(b))    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENT_BOOKS_KEY);
                        editor.putString(CURRENT_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavoriteBooks(Book book)    {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books)  {
            for (Book b: books) {
                if (b.getId() == book.getId())  {
                    if (books.remove(b))    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
