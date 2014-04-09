package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import android.content.Context;

import static junit.framework.Assert.assertNotNull;

public class CourseDirectory {
    private ArrayList<Course> mCourses;
    private HashSet<String> mTitles = new HashSet<String>();

    private static CourseDirectory sCourseDirectory;

    private CourseDirectory() {
        mCourses = CourseData.GenerateCourses();

        for (Course c : mCourses)
            mTitles.add(c.getTitle());
    }

    public static CourseDirectory get() {
        if (sCourseDirectory == null) {
            sCourseDirectory = new CourseDirectory();
        }
        return sCourseDirectory;
    }

    public HashSet<String> getCoursesTitles() {
        return mTitles;
    }

    public Course getCourse(Integer crn) {
        Course course = null;

        for (Course c : mCourses) {
            if (c.getCrn().equals(crn))
            {
                course = c;
                break;
            }
        }

        assertNotNull(course);
        return course;
    }

    public boolean deleteCourse(Integer crn) {

        for (int i=0; i<mCourses.size(); i++) {
            if (mCourses.get(i).getCrn().equals(crn))
            {
                mCourses.remove(i);
                return true;
            }
        }

        assert(false);
        return false;
    }
    
    public ArrayList<Course> getCourses() {
        return mCourses;
    }
}

