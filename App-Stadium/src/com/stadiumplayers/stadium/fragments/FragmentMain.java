package com.stadiumplayers.stadium.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.dialogs.ModalLogin;

public class FragmentMain extends Fragment implements View.OnClickListener {
    
    private Button mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null, false);
        
        mBtnLogin = (Button) rootView.findViewById(R.id.main_btn_login);
        
        mBtnLogin.setOnClickListener(this);
        
        return rootView;
    }

    @Override
    public void onClick(View v) {        
        DialogFragment modalLogin = ModalLogin.newInstance("create an event");
        modalLogin.show(getFragmentManager(), ModalLogin.class.getSimpleName());
    }
    
}
