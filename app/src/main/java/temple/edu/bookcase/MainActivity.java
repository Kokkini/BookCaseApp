package temple.edu.bookcase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements BookListFragment.OnFragmentInteractionListener {

    BookListFragment bookListFragment;
    BookDetailsFragment bookDetailsFragment;
    String[] books;
    ViewPageFragment viewPageFragment;
    Fragment f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        books = res.getStringArray(R.array.Books);
        bookListFragment = BookListFragment.newInstance(books);
        bookDetailsFragment = BookDetailsFragment.newInstance("");
        viewPageFragment = ViewPageFragment.newInstance(books);

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

    @Override
    public void onFragmentInteraction(int index) {
        Log.d("UUUU" ,"Book index:" + index);
        bookDetailsFragment.displayBook(books[index]);
    }
}
