package com.example.s530742.fragmentcommunication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectCurrencyFragment extends Fragment {

    private fragmentTwoListener tCallback;
    private Spinner spinner;
    ArrayList<String> listOfCurrency;
    String selectCurrency;

    ArrayAdapter listAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.selectcurrencyfragment, container, false);

        listOfCurrency = new ArrayList<>();
        listOfCurrency.add("INR");
        listOfCurrency.add("British Pound");
        listOfCurrency.add("Euro");
        listOfCurrency.add("Bitcoin");
        listOfCurrency.add("Chinese Yuan");
        listOfCurrency.add("Fijian Dollar");


        listAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listOfCurrency);

        final ListView listItems;
        listItems = (ListView) v.findViewById(R.id.currencySelector);
        listItems.setAdapter(listAdapter);

        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectCurrency = listOfCurrency.get(i);
            }
        });

        Button button = v.findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(selectCurrency != null) {
                    String text = selectCurrency;
                    tCallback.SelectCurrencyCallback(text);
                }else
                {
                    Toast.makeText(getContext(), "Choose currency from the list", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
    public interface fragmentTwoListener {
        void SelectCurrencyCallback(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectCurrencyFragment.fragmentTwoListener) {
            tCallback = (SelectCurrencyFragment.fragmentTwoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        tCallback = null;
    }
}
