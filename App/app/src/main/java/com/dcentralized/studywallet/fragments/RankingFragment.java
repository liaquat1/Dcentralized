package com.dcentralized.studywallet.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.adapters.RankListAdapter;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.squareup.okhttp.internal.Platform;

import java.util.List;

/**
 * Fragment for viewing ranks
 *
 * @author Tom de Wildt
 */
public class RankingFragment extends Fragment {
    private View layout;
    private List<User> users;
    private ListView lvHighscore;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public RankingFragment() {
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
        layout = inflater.inflate(R.layout.fragment_ranking, container, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                users = StudyWallet.getInstance(getActivity()).getAllUsers();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lvHighscore = layout.findViewById(R.id.lvHighscore);
                        lvHighscore.setAdapter(new RankListAdapter(getActivity(), R.id.lvHighscore, users));
                    }
                });
            }
        }).start();
        return layout;
    }
}
