package com.dcentralized.studywallet.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcentralized.studywallet.R;

/**
 * Fragment to change settings
 *
 * @author Tom de Wildt
 */
public class SettingsFragment extends Fragment {
    private View layout;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Sets the view for the fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     * @author Tom de Wildt
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_projects, container, false);

        return layout;
    }
}
