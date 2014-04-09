package edu.cuesta.cis207.jerry.lab9;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends Activity {

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, createFragment())
                    .commit();
        }
    }
}
