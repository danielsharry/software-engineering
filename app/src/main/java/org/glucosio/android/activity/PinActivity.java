package org.glucosio.android.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.glucosio.android.GlucosioApplication;
import org.glucosio.android.R;
import org.glucosio.android.db.DatabaseHandler;

public class PinActivity extends AppCompatActivity {

    private Button mSubmit;
    private EditText mPinOne, mPinTwo, mPinThree, mPinFour;
    private DatabaseHandler dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pin);

        GlucosioApplication app = (GlucosioApplication) getApplication();
        dB = app.getDBHandler();

        mSubmit = (Button)findViewById(R.id.btn_submit_pin);
        mPinOne = (EditText)findViewById(R.id.txt_pin_1);
        mPinTwo = (EditText)findViewById(R.id.txt_pin_2);
        mPinThree = (EditText)findViewById(R.id.txt_pin_3);
        mPinFour = (EditText)findViewById(R.id.txt_pin_4);
    }

    public void submitPin(View v)
    {
        int pin = Integer.valueOf(mPinOne.getText().toString().concat(mPinTwo.getText().toString().concat(mPinThree.getText().toString().concat(mPinFour.getText().toString()))));
        Intent i = new Intent(this, MainActivity.class);
        boolean isAccepted = false;

        if (dB.getUser(1).getPin() == pin)
        {
            isAccepted = true;
        }

        i.putExtra("pin_state", isAccepted);
        startActivity(i);
        finish();
    }
}