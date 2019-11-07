package temple.edu.bookcase;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "param1";
    private static final String BOOK = "book";

    // TODO: Rename and change types of parameters
    private Book book;
    TextView titleTextView;
    TextView authorTextView;
    TextView publishedTextView;
    ImageView bookCover;


    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable(BOOK);
        }
    }

    public void displayBook(Book book){
        titleTextView.setText(book.title);
        authorTextView.setText(book.author);
        if(book.title != null)
            publishedTextView.setText(book.published+"");
        else
            publishedTextView.setText(null);
        new DownloadImageTask(bookCover).execute(book.coverURL);
//        bookCover.setImageDrawable(getImage(book.coverURL));
//        bookCover.setImageURI(book.coverURL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        titleTextView = view.findViewById(R.id.titleTextView);
        authorTextView = view.findViewById(R.id.authorTextView);
        publishedTextView = view.findViewById(R.id.publishedTextView);
        bookCover = view.findViewById(R.id.bookCover);
        displayBook(book);
        return view;
    }

//    public static Drawable getImage(String url) {
//        try {
//            Log.i("UUUU", "Getting images");
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src name");
//            return d;
//        } catch (Exception e) {
//            Log.i("UUUU", e.toString());
//            Log.i("UUUU", "image error");
//            return null;
//        }
//    }

    public Bitmap getImage(String url){
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return mIcon11;
    }

}
