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
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoryFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MemoryAdapter mAdapter;

    public MemoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MemoryFragment.
     */
    public static MemoryFragment newInstance() {
        return new MemoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_memory);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new MemoryAdapter());
        return view;
    }

    class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>
    {

        @Override
        public MemoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MemoryViewHolder holder = new MemoryViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_memory, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MemoryViewHolder holder, int position)
        {
//            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return 10;
        }

        class MemoryViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            MemoryViewHolder(View view)
            {
                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
