package com.example.s530742.fragmentcommunication;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ChooseAmountFragment.OnGreenFragmentListener,SelectCurrencyFragment.fragmentTwoListener,ChargesFragment.fragmentChargeListener {

    SelectCurrencyFragment mCurrencyFragment;
    ChooseAmountFragment mChooseAmountFragment;
    FragmentManager fragmentManager;
    ChargesFragment chargesFragment;
    private TextView amount1,amount2,currency,decimal,disc;
    private double dollarAmount;
    Button decimalButton;
    double conv =0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        currency = (TextView) findViewById(R.id.currency);
        decimalButton = (Button) findViewById(R.id.decimalButton);
        decimal = (TextView) findViewById(R.id.decimal);
        disc = (TextView)findViewById(R.id.disc);
        fragmentManager = getSupportFragmentManager();
        if (mChooseAmountFragment == null) {
            mChooseAmountFragment = new ChooseAmountFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, mChooseAmountFragment).commit();

    }

    @Override
    public void ChooseAmountCallBack (String message) {
        System.out.println("Data from fragment one "+message);
        amount1.setText(message);
        dollarAmount = Double.parseDouble(message);
        if (mCurrencyFragment == null) {
            mCurrencyFragment = new SelectCurrencyFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, mCurrencyFragment).commit();

    }
    public void decimal(View v)
    {
        DecimalFormat df = new DecimalFormat("##.##");

        decimal.setText(df.format(conv)+"");
    }
    public void SelectCurrencyCallback(String message) {

        if(message.equals("INR"))
        {
            conv = dollarAmount * 65;
        }else if(message.equals("Euro"))
        {
            conv = dollarAmount * 0.81;
        }else if(message.equals("Bitcoin"))
        {
            conv = dollarAmount * 0.00013;
        }else if(message.equals("Chinese Yuan"))
        {
            conv = dollarAmount * 6.27;
        }else if(message.equals("Fijian Dollar"))
        {
            conv = dollarAmount * 2.04;
        }else if(message.equals("British Pound"))
        {
            conv = dollarAmount * 0.71;
        }

        DecimalFormat df = new DecimalFormat("##.#####");
        amount2.setText(df.format(conv)+"");
        currency.setText(message);

        if (chargesFragment == null) {
            chargesFragment = new ChargesFragment();
        }
       fragmentManager.beginTransaction().replace(R.id.fragment_container, chargesFragment).commit();
    }
    public void discount(String str)
    {
        Double d = 0.0;
        if(str.equals("5"))
            d = conv * (0.95);
        else if(str.equals("10"))
            d = conv * (0.90);
        else if(str.equals("15"))
            d = conv * (0.85);

        DecimalFormat df = new DecimalFormat("##.##");

        disc.setText(df.format(d)+"");
    }
    public void background(View v)
    {
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.cl);
        Random rnd = new Random();
        cl.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }
    public void reset(View v)
    {
        recreate();
    }
}
