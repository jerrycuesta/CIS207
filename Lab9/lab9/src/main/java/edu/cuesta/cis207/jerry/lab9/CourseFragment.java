package edu.cuesta.cis207.jerry.lab9;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static junit.framework.Assert.assertNotNull;

public class CourseFragment extends Fragment {
    public static final String EXTRA_COURSE_CRN = "lab9.COURSE_ID";

    Course mCourse;
    TextView mCrn;
    TextView mTitle;
    TextView mDescription;
    TextView mEnrolled;
    TextView mCapacity;

    public static CourseFragment newInstance(String courseId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COURSE_CRN, courseId);

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String arg = (String)getArguments().getSerializable(EXTRA_COURSE_CRN);
        Integer crn = Integer.parseInt(arg);
        mCourse = CourseDirectory.get().getCourse(crn);
    }

    private static void AdjustEnrolledCount(View v, int delta, Course course, TextView enrolled)
    {
        Integer nowEnrolled = course.getEnrolled() + delta;
        if (nowEnrolled>0)
        {
            course.setEnrolled(nowEnrolled);
            enrolled.setText(nowEnrolled.toString());
            if (course.isOverCapacity())
                enrolled.setTextColor(Color.RED);
            else
                enrolled.setTextColor(Color.BLACK);
        }
        else
        {
            Toast.makeText(v.getContext(), R.string.msg_no_students, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.course_fragment, parent, false);

        mCrn = (TextView)v.findViewById(R.id.course_crn);
        mTitle = (TextView)v.findViewById(R.id.course_title);
        mDescription = (TextView)v.findViewById(R.id.course_description);
        mEnrolled = (TextView)v.findViewById(R.id.course_enrolled);
        mCapacity = (TextView)v.findViewById(R.id.course_capacity);

        final Button buttonAdd = (Button) v.findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdjustEnrolledCount(v, +1, mCourse, mEnrolled);
            }
        });

        final Button buttonDrop = (Button) v.findViewById(R.id.button_drop);
        buttonDrop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdjustEnrolledCount(v, -1, mCourse, mEnrolled);
            }
        });

        mCrn.setText(mCourse.getCrn().toString());
        mTitle.setText(mCourse.toString());
        mDescription.setText(mCourse.getDescription());
        mEnrolled.setText(mCourse.getEnrolled().toString());
        mCapacity.setText(mCourse.getCapacity().toString());

        if (mCourse.isOverCapacity())
            mEnrolled.setTextColor(Color.RED);
        else
            mEnrolled.setTextColor(Color.BLACK);

        return v; 
    }
}
