package temple.edu.bookcase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    BookDetailsFragment[] detailArray;
    public PagerAdapter(FragmentManager fm, BookDetailsFragment[] detailArray) {
        super(fm);
        this.detailArray = detailArray;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return detailArray[position];
    }

    @Override
    public int getCount() {
        return detailArray.length;
    }
}
