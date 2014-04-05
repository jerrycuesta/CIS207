package edu.cuesta.cis207.jerry.lab9;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class CourseActivity extends SingleFragmentActivity {
	@Override
    protected Fragment createFragment() {
        String courseCrn = (String)getIntent()
            .getSerializableExtra(CourseFragment.EXTRA_COURSE_CRN);
        return CourseFragment.newInstance(courseCrn);
    }
}
