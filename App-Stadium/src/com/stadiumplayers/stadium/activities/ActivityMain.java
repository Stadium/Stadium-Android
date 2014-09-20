
package com.stadiumplayers.stadium.activities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.stadiumplayers.stadium.R;
import com.stadiumplayers.stadium.fragments.FragmentMain;

public class ActivityMain extends Activity implements OnClickListener, Session.StatusCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, new FragmentMain(), FragmentMain.class.getSimpleName())
                    .commit();
        }
        
        logKeyHash();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ActivityMain.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
    }
    
    private void logKeyHash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.stadiumplayers.stadium",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                // String something = new
                // String(Base64.encodeBytes(md.digest()));
                Log.e("hash-key", something);
            }
        } catch (NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    @Override
    public void call(Session session, SessionState state, Exception exception) {
        if (session.isOpened()) {
            
        }
    }
}
