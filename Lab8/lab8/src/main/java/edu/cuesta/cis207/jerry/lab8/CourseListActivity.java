package edu.cuesta.cis207.jerry.lab8;

import android.support.v4.app.Fragment;

public class CourseListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }
}
