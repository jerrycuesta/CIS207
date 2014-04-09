package edu.cuesta.cis207.jerry.lab9;

import android.app.Fragment;

public class CourseActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        String courseCrn = (String)getIntent()
            .getSerializableExtra(CourseFragment.EXTRA_COURSE_CRN);
        return CourseFragment.newInstance(courseCrn);
    }
}
