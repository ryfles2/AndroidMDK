package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.core.BaseFragment;

public class fragment_event_list extends BaseFragment {

    //private TextView txtTools;
    private View view;


    public static fragment_event_list newInstance() {

        Bundle args = new Bundle();

        fragment_event_list fragment = new fragment_event_list();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tools, container, false);
        //view.findViewById(R.id.txtTools);

        //txtTools.setText();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //getActivity().setTitle("Tools");
        getActivity().setTitle(getString(R.string.category_help));
    }
}
