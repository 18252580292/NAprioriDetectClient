package jskj.com.naprioridetectclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.util.GlideImageLoader;

/**
 * Created by cui on 17-3-26.
 */

public class HomeFragment extends Fragment {
    private List<Integer> mImages;
    private RecyclerView mRecyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImages = new ArrayList<>();
        mImages.add(R.drawable.lbe_soft);
        mImages.add(R.drawable.dynamic_detec);
        mImages.add(R.drawable.report);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_home_fragment);
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(mImages);
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
