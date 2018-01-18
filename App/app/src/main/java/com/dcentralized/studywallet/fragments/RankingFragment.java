package com.dcentralized.studywallet.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.adapters.RankListAdapter;
import com.dcentralized.studywallet.models.User;
import com.dcentralized.studywallet.tasks.ProjectsAllTask;
import com.dcentralized.studywallet.tasks.RanksTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Fragment for viewing ranks
 *
 * @author Tom de Wildt
 */
public class RankingFragment extends Fragment implements Observer {
    private View layout;
    private List<User> users;
    private SwipeRefreshLayout refreshAllUsers;
    private RankListAdapter adapter;
    private ListView lvHighscore;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public RankingFragment() {
        // Required empty public constructor
        users = new ArrayList<>();
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
        lvHighscore = layout.findViewById(R.id.lvHighscore);
        refreshAllUsers = layout.findViewById(R.id.refreshAllUsers);
        adapter = new RankListAdapter(getActivity(), R.id.lvHighscore, users);
        refreshAllUsers.setRefreshing(true);
        getHighscores();
        lvHighscore.setAdapter(adapter);

        refreshAllUsers.setColorSchemeResources(R.color.colorPrimary);
        refreshAllUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                users.clear();
                adapter.notifyDataSetChanged();
                getHighscores();
            }
        });

        return layout;
    }

    private void getHighscores() {
        new Thread(new RanksTask(this)).start();
    }

    @Override
    public void update(Observable observable, final Object o) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (o != null) {
                    User user = (User) o;
                    users.add(user);
                    adapter.notifyDataSetChanged();
                } else {
                    refreshAllUsers.setRefreshing(false);
                }
            }
        });
    }
}
