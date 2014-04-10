package edu.cuesta.cis207.jerry.lab9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

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

    private void Log(String s)
    {
        Log.i("CourseListFragment", s);
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
        ArrayList<String> titles = new ArrayList<String>();

        for (Map.Entry<String, String> e : courses.getIdsAndTitles().entrySet())
            titles.add(e.getKey() + " - " + e.getValue());

        String cancel = getActivity().getString(R.string.button_cancel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_dialog, null);

        // fields in the dialog
        final ListView addListView = (ListView) dialogView.findViewById(R.id.listViewAdd);
        final EditText crnView = (EditText) dialogView.findViewById(R.id.add_crn);
        final EditText capacityView = (EditText) dialogView.findViewById(R.id.add_capacity);

        final SingleHighlightAdapter adapter = new SingleHighlightAdapter(getActivity(), addListView,
                titles, Color.YELLOW, 0xffffff);

        addListView.setAdapter(adapter);

        addListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View v,
                                    int position, long id) {
                adapter.setSelectedIndex(position);
            }
        });

        /*
        Inflate and set the layout for the dialog
        Pass null as the parent view because its going in the dialog layout
        */

        class CourseValidator implements View.OnClickListener {
            private final Dialog dialog;

            public CourseValidator(Dialog dialog) {
                this.dialog = dialog;
            }

            String error;
            int crn;
            int capacity;
            String id;

            boolean ValidateParameters()
            {
                if (!adapter.haveSelection()) {
                    error =  "You did not select a course.";
                    return false;
                }

                String selectedCourse = adapter.getSelectedText();
                id = selectedCourse.split(" - ")[0];

                String crnText = crnView.getText().toString();
                if (crnText.isEmpty())
                {
                    error = "You did not provide a CRN.";
                    return false;
                }
                else
                {
                    crn = Integer.parseInt(crnText);
                    if (crn<10000 || crn > 99999)
                    {
                        error = "A CRN must have 5 digits.";
                        return false;
                    }
                }

                String capacityText = capacityView.getText().toString();
                if (capacityText.isEmpty())
                {
                    error = "You did not provide a capacity.";
                    return false;
                }
                else
                {
                    capacity = Integer.parseInt(capacityText.toString());
                    if (capacity<12 || capacity > 36)
                    {
                        error = "Capacity must be between 12 and 36";
                        return false;
                    }
                }

                if (CourseDirectory.get().hasCourse(crn))
                {
                    error = "Duplicate CRN " + crn + ".";
                    return false;
                }

                return true;
            }

            @Override
            public void onClick(View v) {

                if (!ValidateParameters())
                {
                    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                }
                else
                {
                    CourseDirectory.get().addCourse(crn, id, capacity);
                    dialog.dismiss();
                    setAdapter();
                }
            }
        }

        builder .setTitle("Add Course")
                .setView(dialogView)
                .setPositiveButton("Add",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // validation is done in the OnClickListener
                    }})
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog =  builder.create();
        alertDialog.show();
        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new CourseValidator(alertDialog));
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


    /**
     * List Adapter for Course
     */

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
                        checkedCourses.add(course.getCrn());
                    else
                        checkedCourses.remove(course.getCrn());
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

