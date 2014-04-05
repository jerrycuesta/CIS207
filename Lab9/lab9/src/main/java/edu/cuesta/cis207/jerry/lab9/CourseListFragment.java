package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.ListFragment;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CourseListFragment extends ListFragment {
    private ArrayList<Course> mCourses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.courses_title);
        mCourses = CourseDirectory.get().getCourses();
        CourseAdapter adapter = new CourseAdapter(mCourses);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Course c = ((CourseAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), CourseActivity.class);
        i.putExtra(CourseFragment.EXTRA_COURSE_CRN, c.getCrn().toString());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((CourseAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class CourseAdapter extends ArrayAdapter<Course> {
        public CourseAdapter(ArrayList<Course> courses) {
            super(getActivity(), android.R.layout.simple_list_item_1, courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.list_item_course, null);
            }

            Course course = getItem(position);
            int textColor = course.isOverCapacity()? Color.RED : Color.BLACK;

            TextView textviewTitle =
                (TextView)convertView.findViewById(R.id.course_list_item_details);
            textviewTitle.setText(course.toString());
            textviewTitle.setTextColor(textColor);

            TextView textviewCounts =
                    (TextView)convertView.findViewById(R.id.course_list_item_counts);
            String textCounts = course.getEnrolled().toString() + "/" + course.getCapacity().toString();
            textviewCounts.setText(textCounts);
            textviewCounts.setTextColor(textColor);

            return convertView;
        }
    }
}

