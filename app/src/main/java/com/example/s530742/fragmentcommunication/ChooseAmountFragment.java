package com.example.s530742.fragmentcommunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseAmountFragment extends Fragment {

    private OnGreenFragmentListener mCallback;
    EditText et;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.chooseamountfragment, container, false);
        Button button = v.findViewById(R.id.button);
        et = v.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!et.getText().toString().isEmpty()) {
                    double check = Double.parseDouble(et.getText().toString());
                    if(check != 0.0) {
                        String message = et.getText().toString();
                        mCallback.ChooseAmountCallBack (message);

                    }else {
                        Toast.makeText(getContext(), "Amount Should not be Zero", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(getContext(),"Amount Field should not be empty!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public interface OnGreenFragmentListener {
        void ChooseAmountCallBack (String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGreenFragmentListener) {
            mCallback = (OnGreenFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
