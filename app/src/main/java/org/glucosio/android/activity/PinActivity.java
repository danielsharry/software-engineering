package org.glucosio.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.glucosio.android.R;

public class PinActivity extends AppCompatActivity {

    private Button mSubmit;
    private EditText mPinOne, mPinTwo, mPinThree, mPinFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSubmit = (Button)findViewById(R.id.btn_submit_pin);
        mPinOne = (EditText)findViewById(R.id.txt_pin_1);
        mPinTwo = (EditText)findViewById(R.id.txt_pin_2);
        mPinThree = (EditText)findViewById(R.id.txt_pin_3);
        mPinFour = (EditText)findViewById(R.id.txt_pin_4);



        setContentView(R.layout.activity_pin);
    }

    public void submitPin(View v)
    {

    }
}
