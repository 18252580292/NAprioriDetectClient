package jskj.com.naprioridetectclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jskj.com.naprioridetectclient.R;

/**
 * Created by cui on 17-3-26.
 */

public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(container.getContext(), R.layout.home_tab_item, null);
    }
}
