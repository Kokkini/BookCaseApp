package temple.edu.bookcase;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BOOKLIST = "bookList";

    // TODO: Rename and change types of parameters
    private static ArrayList<Book> bookList;

    public ViewPageFragment() {
        // Required empty public constructor
    }

    public static ViewPageFragment newInstance(ArrayList<Book> bookList) {
        ViewPageFragment fragment = new ViewPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(BOOKLIST, bookList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookList = (ArrayList<Book>) getArguments().getSerializable(BOOKLIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_page, container, false);
        ViewPager viewPager = view.findViewById(R.id.ViewPager);
        BookDetailsFragment[] detailArray = new BookDetailsFragment[bookList.size()];
        Book theBook = new Book(10, "ten", "ten ten", 1010, "10101010");
        for(int i=0; i<detailArray.length; i++){
            detailArray[i] = BookDetailsFragment.newInstance(bookList.get(i));
        }
        PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(), detailArray);
        viewPager.setAdapter(pagerAdapter);
        return view;
    }
}
