package temple.edu.bookcase;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookListFragment.OnFragmentInteractionListener {

    BookListFragment bookListFragment;
    BookDetailsFragment bookDetailsFragment;
    ArrayList<String> bookTitles;
    ViewPageFragment viewPageFragment;
    Fragment f;
    Book theBook;
    ArrayList<Book> bookList;
    RequestQueue requestQueue;
    boolean allBooksLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allBooksLoaded = false;
        String URL = "https://kamorris.com/lab/audlib/booksearch.php";
        requestQueue = Volley.newRequestQueue(this);
        theBook = new Book(-1, null, null, -1, null);

        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("UUUU", "First line in onResponse");
                        bookList = makeBookList(response);
                        bookTitles = makeBookTitleList(bookList);
                        bookListFragment = BookListFragment.newInstance(bookTitles);
                        bookDetailsFragment = BookDetailsFragment.newInstance(theBook);
                        viewPageFragment = ViewPageFragment.newInstance(bookList);

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft= fragmentManager.beginTransaction();
                        ConstraintLayout container_2 = (ConstraintLayout) findViewById(R.id.container_2);

                        if (container_2 == null){
                            //portrait
                            f = fragmentManager.findFragmentByTag("list");
                            if(f!=null) ft.remove(f);
                            ft.add(R.id.container_1, viewPageFragment, "pager");
                        } else{
                            f = fragmentManager.findFragmentByTag("pager");
                            if(f!=null) ft.remove(f);
                            ft.add(R.id.container_1, bookListFragment, "list");
                            ft.add(R.id.container_2, bookDetailsFragment, "detail");
                        }
                        ft.commit();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("UUUUResponse", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);

        Log.i("UUUU", "What the hell is this?");

//        Log.i("UUUU", "bookList length: " + bookList.size());
//
//        for(int i=0; i < bookList.size(); i++){
//            Log.i("UUUU", "fire");
//            Log.i("UUUU", bookList.get(i).title);
//        }





        Resources res = getResources();


//        viewPageFragment = ViewPageFragment.newInstance(bookTitles);





    }

    @Override
    public void onFragmentInteraction(int index) {
        Log.d("UUUU" ,"Book index:" + index);
        bookDetailsFragment.displayBook(bookList.get(index));
    }

    ArrayList<Book> makeBookList(JSONArray response){
        ArrayList<Book> bookArrayList = new ArrayList<>();
        Log.i("UUUU", response.length() + "");
        for(int i=0; i<response.length(); i++) {
            try {
                JSONObject bookJson = response.getJSONObject(i);
                int id = bookJson.getInt("book_id");
                String title = bookJson.getString("title");
                String author = bookJson.getString("author");
                int published = bookJson.getInt("published");
                String coverURL = bookJson.getString("cover_url");
                bookArrayList.add(new temple.edu.bookcase.Book(id, title, author, published, coverURL));
                Log.i("UUUU","Title: " + title);
            } catch (JSONException e) {
                Log.e("UUUU", e.toString());
                e.printStackTrace();
            }
        }
        return bookArrayList;
    }

    ArrayList<String> makeBookTitleList(ArrayList<Book> bookList){
        ArrayList<String> titles = new ArrayList<>();
        for(Book book : bookList){
            titles.add(book.title);
        }
        return titles;
    }
}
