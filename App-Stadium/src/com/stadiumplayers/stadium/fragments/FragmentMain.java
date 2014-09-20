package com.stadiumplayers.stadium.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stadiumplayers.stadium.App;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.modals.ModalLogin;

public class FragmentMain extends Fragment implements View.OnClickListener {
    
    private TextView mTextHelloWorld;
    private Button mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null, false);
        
        mTextHelloWorld = (TextView) rootView.findViewById(R.id.main_text_hello_world);
        mBtnLogin = (Button) rootView.findViewById(R.id.main_btn_login);
        
        mBtnLogin.setOnClickListener(this);
        
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (App.getContext() == null) {
            Log.e("ryan", "omg its null");
        }
        
        DialogFragment modalLogin = ModalLogin.newInstance("create an event");
        modalLogin.show(getFragmentManager(), ModalLogin.class.getSimpleName());
    }
    
}
