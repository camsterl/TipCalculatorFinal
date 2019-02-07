package edu.miracosta.cs134.ckerr.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percentage = NumberFormat.getPercentInstance(Locale.getDefault());

    private EditText EnterNum;
    private TextView percent;
    private TextView Tip;
    private TextView Total;
    private SeekBar seekBar;

    private Bill currentBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterNum = findViewById(R.id.EnterNum);
        percent = findViewById(R.id.percent);
        Tip = findViewById(R.id.TipTotal);
        Total = findViewById(R.id.Totalinput);
        seekBar = findViewById(R.id.seekBar);


        currentBill = new Bill();
        //set tip percent
        currentBill.setTipPercent(seekBar.getProgress() / 100.0);

        //connect code to the event onProgressChanged for seek bar
        seekBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //change the label of the tip percent
                currentBill.setTipPercent(progress / 100.0);
                percent.setText(percentage.format(currentBill.getTipPercent()));
                Tip.setText((currency.format(currentBill.getTipAmount())));
                Total.setText(currency.format(currentBill.getTotalAmount()));




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //does nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //does nothing
            }
        });

        EnterNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //update model


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    currentBill.setAmount(Double.parseDouble(EnterNum.getText().toString()));
                } catch (NumberFormatException e)
                {
                    currentBill.setAmount(0.0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //leave alone
            }
        });

    }



}
