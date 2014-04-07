package edu.cuesta.cis207.jerry.lab9;

import android.support.v4.app.Fragment;

public class CourseListActivity extends SingleFragmentActivity {

    public CourseListActivity()
    {
        super(R.layout.course_list_fragment, R.id.fragmentContainer);
    }

    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }
}
