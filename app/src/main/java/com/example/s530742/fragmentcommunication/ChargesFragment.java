package com.example.s530742.fragmentcommunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChargesFragment extends Fragment {

    private fragmentChargeListener tCallback;
    Spinner spinner2;
    public ChargesFragment() {
        // Required empty public constructor
    }
    public interface fragmentChargeListener {
        void discount(String text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_charges, container, false);

        spinner2 =(Spinner)v.findViewById(R.id.spi);
        List<String> list = new ArrayList<String>();
        list.add("5");
        list.add("10");
        list.add("15");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        Button button = v.findViewById(R.id.total);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               String dis = spinner2.getSelectedItem().toString();

                tCallback.discount(dis);

            }
        });


        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChargesFragment.fragmentChargeListener) {
            tCallback = (ChargesFragment.fragmentChargeListener) context;
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



