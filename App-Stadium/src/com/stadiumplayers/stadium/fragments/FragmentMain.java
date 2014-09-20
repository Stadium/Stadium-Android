
package com.stadiumplayers.stadium.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.dialogs.DialogLogin;

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
        DialogFragment dialogLogin = DialogLogin.newInstance("create an event");
        dialogLogin.show(getChildFragmentManager(), DialogLogin.class.getSimpleName());
    }

}
