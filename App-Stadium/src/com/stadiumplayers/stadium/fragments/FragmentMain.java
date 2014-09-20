package com.stadiumplayers.stadium.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stadiumplayer.android.R;

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
        Toast.makeText(getActivity(), R.string.hello_world, Toast.LENGTH_SHORT).show();
    }
    
}
