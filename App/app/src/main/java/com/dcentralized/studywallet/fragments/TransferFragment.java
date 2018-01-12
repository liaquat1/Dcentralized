package com.dcentralized.studywallet.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcentralized.studywallet.R;

/**
 * Fragment to view transfer coins
 *
 * @author Tom de Wildt
 */
public class TransferFragment extends Fragment {
    private View layout;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public TransferFragment() {
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
        layout = inflater.inflate(R.layout.fragment_transfer, container, false);

        return layout;
    }
}
