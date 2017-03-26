package jskj.com.naprioridetectclient.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

public class FragmentFactory {
    private static SparseArrayCompat<Fragment> frags = new SparseArrayCompat<>();

    public static Fragment getFragmentByPosition(int position) {
        Fragment fragment = frags.get(position);
        if (fragment != null) {
            return frags.get(position);
        }
        switch (position) {
            case 0:
                frags.put(position, new HomeFragment());
                break;
            case 1:
                frags.put(position, new LocalFragment());
                break;
            case 2:
                frags.put(position, new RemoteFragment());
                break;
            case 3:
                frags.put(position, new MyFragment());
                break;
        }
        return frags.get(position);
    }
}
