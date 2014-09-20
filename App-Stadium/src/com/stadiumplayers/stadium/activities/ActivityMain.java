
package com.stadiumplayers.stadium.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.stadiumplayer.android.R;
import com.stadiumplayers.stadium.fragments.FragmentMain;

public class ActivityMain extends Activity implements OnClickListener {

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
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ActivityMain.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
    }

}
