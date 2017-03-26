package jskj.com.naprioridetectclient.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.util.GlideImageLoader;
import okio.BufferedSource;
import okio.Okio;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private final int ITEM_TYPE_VIRUS_RANK = 0x01;
    private List<Integer> mImages;
    private RecyclerView mRecyclerView;
    private String[] virusType;
    private List<String> mVirusContent = new ArrayList<>();

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
        getDetailVirusInfo();
        return view;
    }

    /**
     * 得到病毒的详细描述信息
     */
    private void getDetailVirusInfo() {
        try {
            InputStream open = getResources().getAssets().open("info.json");
            BufferedSource buffer = Okio.buffer(Okio.source(open));
            String string = buffer.readByteString().string(Charset.forName("UTF-8"));
            JSONArray array = new JSONArray(string);
            for (int i = 0; i < 10; i++) {
                mVirusContent.add((String) array.opt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(new HomeFragmentAdapter());
    }

    private void initData() {
        virusType = getResources().getStringArray(R.array.virus_rank);
    }

    class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentViewHolder> {

        @Override
        public HomeFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_recycler_home_frag, null);
            return new HomeFragmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HomeFragmentViewHolder holder, final int position) {
            holder.txtName.setText(virusType[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showVirusInfoDialog(position);
//                    Toast.makeText(getActivity(), "i am click " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return virusType.length;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        /**
         * 显示病毒详情展示框
         * @param position
         */
        private void showVirusInfoDialog(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(virusType[position]);
            builder.setMessage(mVirusContent.get(position));
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
        }
    }

    class HomeFragmentViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private View itemView;

        public HomeFragmentViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            txtName = (TextView) itemView.findViewById(R.id.tv_item_recycler_name);
        }
    }
}
