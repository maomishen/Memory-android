package com.maomishen.memory.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maomishen.memory.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArchiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArchiveFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private ArchiveAdapter mAdapter;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MemoryFragment.
     */
    public static ArchiveFragment newInstance() {
        ArchiveFragment fragment = new ArchiveFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_archive);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new ArchiveAdapter());
        return view;
    }

    class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>
    {

        @Override
        public ArchiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            ArchiveViewHolder holder = new ArchiveViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_archive, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(ArchiveViewHolder holder, int position)
        {
//            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return 1;
        }

        class ArchiveViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            ArchiveViewHolder(View view)
            {
                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
