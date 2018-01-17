package com.dcentralized.studywallet.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.models.User;

/**
 * Fragment to view transfer coins
 *
 * @author Tom de Wildt
 */
public class TransferFragment extends Fragment {
    private View layout;
    private Button btnTransfer;
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
        btnTransfer = layout.findViewById(R.id.btnTransfer);
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textStudentNr = layout.findViewById(R.id.textTransferNumber);
                EditText textAmount = layout.findViewById(R.id.textAmount);
                User user = StudyWallet.getInstance(layout.getContext()).getCurrentUser();
                if(user.transferCoins(textStudentNr.getText().toString(),Integer.valueOf(textAmount.getText().toString()))){
                    Toast.makeText(layout.getContext(), "Transfer completed", Toast.LENGTH_SHORT).show();
                }else
                    {Toast.makeText(layout.getContext(),"Transfer failed", Toast.LENGTH_SHORT).show();}
            }
        });
        return layout;
    }
}
