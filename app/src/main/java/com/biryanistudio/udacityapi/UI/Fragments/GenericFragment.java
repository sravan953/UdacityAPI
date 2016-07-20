package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biryanistudio.udacityapi.FragmentType;
import com.biryanistudio.udacityapi.Interfaces.IGenericUpdateUI;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.GenericTask;
import com.biryanistudio.udacityapi.UI.Adapters.AssignedAdapter;
import com.biryanistudio.udacityapi.UI.Adapters.AvailableAdapter;
import com.biryanistudio.udacityapi.UI.Adapters.FeedbackAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.List;

/**
 * Created by Sravan on 20-Jul-16.
 */
public class GenericFragment extends Fragment implements IGenericUpdateUI,
        SwipeRefreshLayout.OnRefreshListener {
    public FragmentType mFragmentType;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;

    public static GenericFragment newInstance(FragmentType fragmentType) {
        Bundle args = new Bundle();
        args.putSerializable("TYPE", fragmentType);
        GenericFragment fragment = new GenericFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentType = (FragmentType) getArguments().getSerializable("TYPE");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorSecondary),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter = getRecyclerAdapter(null);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        if (MainActivity.API_TOKEN_present) new GenericTask().execute(this);
        return view;
    }

    @Override
    public void newData(List data) {
        try {
            mSwipeRefreshLayout.setRefreshing(false);
            mRecyclerAdapter = getRecyclerAdapter(data);
            mRecyclerView.setAdapter(mRecyclerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        if (MainActivity.API_TOKEN_present) new GenericTask().execute(this);
    }

    private RecyclerView.Adapter getRecyclerAdapter(List data) {
        switch(getFragmentType()) {
            case ASSIGNED:
                return new AssignedAdapter(data);
            case AVAILABLE:
                return new AvailableAdapter(data);
            case FEEDBACK:
                return new FeedbackAdapter(data);
            default:
                return null;
        }
    }

    public FragmentType getFragmentType() {
        return mFragmentType;
    }
}
