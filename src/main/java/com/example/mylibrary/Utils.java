package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;


    private static ArrayList<Book>  allBooks;
    private static ArrayList<Book>  finishedBooks;
    private static ArrayList<Book>  wishList;
    private static ArrayList<Book>  currentBooks;
    private static ArrayList<Book>  favoriteBooks;

    private Utils() {
        if (null == allBooks)   {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == finishedBooks)   {
            finishedBooks = new ArrayList<>();
        }
        if (null == wishList)   {
            wishList = new ArrayList<>();
        }
        if (null == currentBooks)   {
            currentBooks = new ArrayList<>();
        }
        if (null == favoriteBooks)   {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add(new Book(1,"1Q84", "Haruki Murakammi",1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg",
                "A work of maddening brilliance", "Long Description"));
        allBooks.add(new Book(2,"The Myth of Sisyphus", "Albert Camus",250, "https://images-na.ssl-images-amazon.com/images/I/51VLai+9nWS.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought", "Long Description"));

    }

    public static synchronized Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getFinishedBooks() {
        return finishedBooks;
    }

    public static ArrayList<Book> getWishList() {
        return wishList;
    }

    public static ArrayList<Book> getCurrentBooks() {
        return currentBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id) {
        for (Book b: allBooks)  {
            if (b.getId() == id)    {
                return b;
            }
        }
        return null;
    }

    public boolean addToFinished(Book book) {
        return finishedBooks.add(book);
    }

    public boolean addToWishlist(Book book) {
        return wishList.add(book);
    }

    public boolean addToCurrentBooks(Book book) {
        return currentBooks.add(book);
    }

    public boolean addToFavoriteBooks(Book book) {
        return favoriteBooks.add(book);
    }

    public boolean removeFromFinished(Book book)    {
        return finishedBooks.remove(book);
    }

    public boolean removeFromWishlist(Book book)    {
        return wishList.remove(book);
    }

    public boolean removeFromCurrentBooks(Book book)    {
        return currentBooks.remove(book);
    }

    public boolean removeFromFavoriteBooks(Book book)    {
        return favoriteBooks.remove(book);
    }
}
