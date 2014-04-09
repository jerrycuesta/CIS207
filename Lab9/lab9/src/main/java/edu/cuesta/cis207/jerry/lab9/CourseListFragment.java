package edu.cuesta.cis207.jerry.lab9;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseListFragment extends Fragment {

    private static final int DIALOG_ADD = 1;
    private static final int DIALOG_DELETE = DIALOG_ADD + 1;

    private ArrayList<Course> mCourses;
    private ListView listView;
    private Button addButton;
    private Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.courses_title);
        mCourses = CourseDirectory.get().getCourses();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_list_fragment, container, false);

        final CourseAdapter adapter = new CourseAdapter(mCourses);

//        listView = (ListView) getActivity().findViewById(R.id.listViewCourses);

        listView = (ListView) rootView.findViewById(R.id.listViewCourses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View v,
                                    int position, long id) {
                onListItemClick(listView, v, position, id);
            }
        });

        addButton=(Button) rootView.findViewById(R.id.button_add_section);

        addButton.setText("Add Button");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        deleteButton = (Button) rootView.findViewById(R.id.button_delete_section);
        deleteButton.setText("Delete Button");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        return rootView;
    }


    public void onListItemClick(ListView l, View v, int position, long id) {
        Course c = ((CourseAdapter)l.getAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), CourseActivity.class);
        i.putExtra(CourseFragment.EXTRA_COURSE_CRN, c.getCrn().toString());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((CourseAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    private class CourseAdapter extends ArrayAdapter<Course> {
        public CourseAdapter(ArrayList<Course> courses) {
            super(getActivity(), android.R.layout.simple_list_item_1, courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.course_list_item, null);
            }

            Course course = getItem(position);
            int textColor = course.isOverCapacity()? Color.RED : Color.BLACK;

//            CheckBox checkBox = (CheckBox)
//                    convertView.findViewById(R.id.course_list_check_box);
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });

            TextView textViewTitle =
                (TextView)convertView.findViewById(R.id.course_list_item_details);
            textViewTitle.setText(course.toString());
            textViewTitle.setTextColor(textColor);

            TextView textViewCounts =
                    (TextView)convertView.findViewById(R.id.course_list_item_counts);
            String textCounts = course.getEnrolled().toString() + "/" + course.getCapacity().toString();
            textViewCounts.setText(textCounts);
            textViewCounts.setTextColor(textColor);

            return convertView;
        }
    }
}

