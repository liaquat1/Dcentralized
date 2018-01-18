package com.dcentralized.studywallet.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.models.User;

import java.util.regex.Pattern;

/**
 * Fragment to view transfer coins
 *
 * @author Tom de Wildt
 */
public class TransferFragment extends Fragment {
    private View layout;
    private Button btnTransfer;
    private EditText textStudentId;
    private EditText textName;
    private EditText textAmount;

    /**
     * Checks if a string is only containing numbers
     * @author Tom de Wildt
     */
    private static final String NUMBER_REGEX = "\\d+";

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
        textStudentId = layout.findViewById(R.id.textStudentId);
        textName = layout.findViewById(R.id.textName);
        textAmount = layout.findViewById(R.id.textAmount);

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonTransferClick(v);
            }
        });
        return layout;
    }

    private void onButtonTransferClick(View v) {
        if (textStudentId.getText() != null && textStudentId.getText().length() == 7 && textName.getText() != null && textAmount.getText() != null && Pattern.matches(NUMBER_REGEX, textAmount.getText())) {
            if (StudyWallet.getInstance(getActivity()).getCurrentUser().transferCoins(textStudentId.getText().toString(), textName.getText().toString(), Integer.parseInt(textAmount.getText().toString()))) {
                ((MainActivity)getActivity()).closeKeyboard();
                textStudentId.setText("");
                textName.setText("");
                textAmount.setText("");
                Toast.makeText(getActivity(), "Coins transferred", Toast.LENGTH_SHORT).show();
            } else {
                ((MainActivity)getActivity()).closeKeyboard();
                Toast.makeText(getActivity(), "An error occurred while transferring the coins", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please enter valid transaction info", Toast.LENGTH_SHORT).show();
        }
    }
}
