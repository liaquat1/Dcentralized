package nl.fhict.liwei.researchduo1_poc.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nl.fhict.liwei.researchduo1_poc.Activity.HomeScreen;
import nl.fhict.liwei.researchduo1_poc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnLoginClick(View view){
        EditText email = findViewById(R.id.tbEmail);
        EditText password = findViewById(R.id.tbPassword);
        TextView errorMessage = findViewById(R.id.tvErrorMessage);

        if((email != null && password != null)){
            if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                Intent intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
            } else {
                errorMessage.setText("Email or password is incorrect!");
            }
        } else {
            errorMessage.setText("Error while logging in.");
        }

    }
}
