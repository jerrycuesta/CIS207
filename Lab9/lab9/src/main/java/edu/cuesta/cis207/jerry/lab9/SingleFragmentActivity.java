package edu.cuesta.cis207.jerry.lab9;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected int m_layoutResId; // R.layout.activity_fragment
    protected int m_fragmentId; // R.id.fragmentContainer

    protected SingleFragmentActivity(int layoutResId, int fragmentId)
    {
        m_layoutResId = layoutResId;
        m_fragmentId = fragmentId;
    }

    // disallow access to default constructor
    private SingleFragmentActivity() {}

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(m_layoutResId);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(m_fragmentId);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();
        }
    }
}
