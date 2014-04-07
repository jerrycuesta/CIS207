package edu.cuesta.cis207.jerry.lab9;

import android.support.v4.app.Fragment;

public class CourseActivity extends SingleFragmentActivity {

    public CourseActivity()
    {
        super(R.layout.course_list_fragment, R.id.fragmentContainer);
    }

	@Override
    protected Fragment createFragment() {
        String courseCrn = (String)getIntent()
            .getSerializableExtra(CourseFragment.EXTRA_COURSE_CRN);
        return CourseFragment.newInstance(courseCrn);
    }
}
