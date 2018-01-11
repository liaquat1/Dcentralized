package com.dcentralized.studywallet.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcentralized.studywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    private View layout;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_project, container, false);
        return layout;
    }

}
