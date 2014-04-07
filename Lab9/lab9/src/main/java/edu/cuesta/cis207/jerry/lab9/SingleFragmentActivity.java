package edu.cuesta.cis207.jerry.lab9;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

    int m_layoutResId; // R.layout.activity_fragment
    int m_fragmentId; // R.id.fragmentContainer

    protected SingleFragmentActivity(int layoutResId, int fragmentId)
    {
        m_layoutResId = layoutResId;
        m_fragmentId = fragmentId;
    }

    private SingleFragmentActivity() {}

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SetLayoutResID();

        setContentView(m_layoutResId);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(m_fragmentId);
        //Fragment fragment = manager.findFragmentById(m_layoutResID);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();
        }
    }
}
