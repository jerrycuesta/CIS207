package edu.cuesta.cis207.jerry.lab9;

import android.app.Fragment;

public class CourseListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }
}
