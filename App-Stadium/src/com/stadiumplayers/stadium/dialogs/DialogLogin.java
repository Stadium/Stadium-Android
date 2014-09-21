
package com.stadiumplayers.stadium.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.stadiumplayers.stadium.AppData;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.common.Utils;

public class DialogLogin extends DialogFragment implements View.OnClickListener {
    
    public interface OnDialogLoginListener {
        public void onLoginSuccessful();
        public void onLoginFailure();
        public void onKeepBrowsing();
    }
    
    private static final String KEY_STRING_ACTION = "keyStringAction";
    private static final String KEY_FULL_MESSAGE = "keyfullMessage";

    public static DialogLogin newInstanceWithAction(String deferredAction) {
        DialogLogin modalLogin = new DialogLogin();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STRING_ACTION, deferredAction);
        modalLogin.setArguments(bundle);
        return modalLogin;
    }
    
    public static DialogLogin newInstanceWithMessage(String fullMessage) {
        DialogLogin modalLogin = new DialogLogin();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FULL_MESSAGE, fullMessage);
        modalLogin.setArguments(bundle);
        return modalLogin;
    }

    private OnDialogLoginListener mListener;
    private TextView mTextMessage;
    private Button mBtnFacebookLogin;
    private Button mBtnKeepBrowsing;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnDialogLoginListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_login, null, false);

        mTextMessage = (TextView) rootView.findViewById(R.id.login_text_message);
        mBtnFacebookLogin = (Button) rootView.findViewById(R.id.login_btn_login);
        mBtnKeepBrowsing = (Button) rootView.findViewById(R.id.login_btn_cancel);

        mTextMessage.setText(String.format(Utils.getString(R.string.login_prompt_event),
                getArguments().getString(KEY_STRING_ACTION)));
        mBtnFacebookLogin.setOnClickListener(this);
        mBtnKeepBrowsing.setOnClickListener(this);

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        
        switch (view.getId()) {
        case R.id.login_btn_login:
         // Create a facebook login request
            Session.openActiveSession(getActivity(), true, new Session.StatusCallback() {

                @Override
                public void call(Session session, SessionState state, Exception exception) {
                    
                    // If we logged in
                    if (session.isOpened()) {

                        // Create a facebook request for the logged in user's info
                        Request.newMeRequest(session, new Request.GraphUserCallback() {

                            @Override
                            public void onCompleted(GraphUser user, Response response) {
                                
                                // If the request was successful
                                if (user != null) {
                                    
                                    // Save the user information
                                    AppData.init(Long.parseLong(user.getId()), user.getFirstName(),
                                            user.getLastName(), true);
                                }
                            }
                        });
                    }
                }
            });
            break;
            
        case R.id.login_btn_cancel:
            DialogLogin.this.dismiss();
            break;
        }
    }
}
