package com.stadiumplayers.stadium.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.stadiumplayers.stadium.AppData;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.dialogs.DialogLogin;
import com.stadiumplayers.stadium.dialogs.DialogLogin.OnDialogLoginListener;

public class ActivitySplashPage extends FragmentActivity implements OnClickListener, OnDialogLoginListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashpage);
        
        Button btnLogin = (Button) findViewById(R.id.splashpage_btn_login);
        Button btnBrowser = (Button) findViewById(R.id.splashpage_btn_browse);
        
        btnLogin.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        
        switch (view.getId()) {
        case R.id.splashpage_btn_login:
            Session.openActiveSession(this, true, new Session.StatusCallback() {

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
                                    Log.e("ryan", "got da info");
                                    AppData.init(Long.parseLong(user.getId()), user.getFirstName(),
                                            user.getLastName(), true);
                                    ActivitySplashPage.this.finish();
                                }
                            }
                        });
                    }
                }
            });
            break;
            
        case R.id.splashpage_btn_browse:
            ActivitySplashPage.this.finish();
            break;
        }
    }

    @Override
    public void onLoginSuccessful() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onLoginFailure() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onKeepBrowsing() {
        // TODO Auto-generated method stub
        
    }
    

}
