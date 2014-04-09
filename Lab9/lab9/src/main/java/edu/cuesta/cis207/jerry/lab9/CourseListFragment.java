package edu.cuesta.cis207.jerry.lab9;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class CourseListFragment extends Fragment {

    private static final int DIALOG_ADD = 1;
    private static final int DIALOG_DELETE = DIALOG_ADD + 1;

    private ArrayList<Course> mCourses;
    CourseAdapter mListAdapter;
    private ListView listView;
    private Button addButton;
    private Button deleteButton;
    private HashSet<Integer> checkedCourses = new HashSet<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.courses_title);
    }

    private void setAdapter()
    {
        mCourses = CourseDirectory.get().getCourses();
        mListAdapter = new CourseAdapter(mCourses);
        listView.setAdapter(mListAdapter);
        Log.i("CourseListFragment", "setAdapter — set adapter for " + mCourses.size() + " courses.");
        checkedCourses.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_list_fragment, container, false);

        listView = (ListView) rootView.findViewById(R.id.listViewCourses);
        setAdapter();

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
                onAddPressed();
            }
        });

        deleteButton = (Button) rootView.findViewById(R.id.button_delete_section);
        deleteButton.setText("Delete Button");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onDeletePressed();
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

    private void onDeletePressed()
    {
        String message = "Delete " + checkedCourses.size() + " courses?";
        String title = getString(R.string.delete_dialog_title);
        String yes = getString(R.string.delete_button_yes);
        String no = getString(R.string.delete_button_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onDeleteYes();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    /**
     * Handle add in button
     */

    private void onAddPressed()
    {
        CourseDirectory courses = CourseDirectory.get();
        HashSet<String> titles = courses.getCoursesTitles();
        final String[] strTitles = titles.toArray(new String[titles.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Section to Add")
                .setItems(strTitles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("CourseListFragment", "selected to add  " + strTitles[which] + ".");
                    }
                });
        builder.show();
    }


    /**
     * Handle delete in dialog
     */

    private void onDeleteYes()
    {
        int startingCount = CourseDirectory.get().getCourses().size();

        Log.i("CourseListFragment", "onDeleteYes — before " + startingCount + " courses.");

        for (Integer item : checkedCourses)
        {
            boolean deleted = CourseDirectory.get().deleteCourse(item);
            assert(deleted);
            Log.i("CourseListFragment", "onDeleteYes — deleted course CRN  " + item + ".");
        }

        int finalCount = CourseDirectory.get().getCourses().size();
        assert ((startingCount - finalCount) == checkedCourses.size());

        Log.i("CourseListFragment", "onDeleteYes — after " + finalCount + " courses.");

        setAdapter();
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

            final Course course = getItem(position);
            int textColor = course.isOverCapacity()? Color.RED : Color.BLACK;

            final CheckBox checkBox = (CheckBox)
                    convertView.findViewById(R.id.course_list_check_box);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked())
                    {
                        checkedCourses.add(course.getCrn());
                    }
                    else
                    {
                        checkedCourses.remove(course.getCrn());
                    }
                }
            });

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

