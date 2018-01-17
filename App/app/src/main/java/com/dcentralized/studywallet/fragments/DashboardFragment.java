package com.dcentralized.studywallet.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.adapters.TransactionListAdapter;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.tasks.TransactionsTask;

import java.util.concurrent.CyclicBarrier;

/**
 * Home fragment for the application
 *
 * @author Tom de Wildt
 */
public class DashboardFragment extends Fragment implements OnClickListener {
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private View layout;
    private TextView textCoins;
    private TextView textRank;
    private SwipeRefreshLayout refreshTransactions;
    private ListView listTransactions;
    private TransactionListAdapter adapter;
    private TransactionsTask task;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public DashboardFragment() {
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
        layout = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textCoins = layout.findViewById(R.id.textCoins);
        textRank = layout.findViewById(R.id.textRank);
        refreshTransactions = layout.findViewById(R.id.refreshTransactions);
        listTransactions = layout.findViewById(R.id.listTransactions);

        // Setup UI
        Activity activity = getActivity();
        textCoins.setText(String.valueOf(StudyWallet.getInstance(activity).getCurrentUser().getBalance()));
        textRank.setText(String.format("#%s", StudyWallet.getInstance(activity).getCurrentUser().getRank()));
        adapter = new TransactionListAdapter(activity, R.layout.transaction_list_item, StudyWallet.getInstance(activity).getCurrentUser().getTransactions());
        listTransactions.setAdapter(adapter);
        layout.findViewById(R.id.buttonAddProject).setOnClickListener(this);

        refreshTransactions.setColorSchemeResources(R.color.colorPrimary);
        refreshTransactions.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StudyWallet.getInstance(getActivity()).getCurrentUser().setTransactions(task.getTransactionsFromDatabase());
                                StudyWallet.getInstance(getActivity()).getCurrentUser().setRank(task.getRank());
                                StudyWallet.getInstance(getActivity()).getCurrentUser().setBalance(task.getBalance());
                                adapter.notifyDataSetChanged();
                                textCoins.setText(String.valueOf(task.getBalance()));
                                textRank.setText(String.format("#%s", task.getRank()));
                                refreshTransactions.setRefreshing(false);
                            }
                        });
                    }
                });
                task = new TransactionsTask(StudyWallet.getInstance(getActivity()).getCurrentUser().getId(), barrier);
                new Thread(task).start();
            }
        });

        return layout;
    }

    /**
     * Handles click events
     *
     * @param view
     * @author Tom de Wildt
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddProject:
                ((MainActivity)getActivity()).changeFragment(R.id.nav_all_projects);
                break;
        }
    }
}
