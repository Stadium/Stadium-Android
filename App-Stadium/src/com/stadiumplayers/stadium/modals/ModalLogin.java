package com.stadiumplayers.stadium.modals;

import com.stadiumplayers.stadium.R;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ModalLogin extends DialogFragment {
    
    private static String KEY_STRING_ACTION = "keyStringAction";
    
    public static ModalLogin newInstance(String deferredAction) {
        ModalLogin modalLogin = new ModalLogin();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STRING_ACTION, deferredAction);
        modalLogin.setArguments(bundle);
        return modalLogin;
    }
    
    private Button mBtnFacebookLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_login, null, false);
        
        return null;
    }
    
}
