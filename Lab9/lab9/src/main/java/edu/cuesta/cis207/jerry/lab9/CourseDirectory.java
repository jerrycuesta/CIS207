package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

import static junit.framework.Assert.assertNotNull;

public class CourseDirectory {
    private ArrayList<Course> mCourses;

    private static CourseDirectory sCourseDirectory;

    private CourseDirectory() {
        mCourses = CourseData.GenerateCourses();
    }

    public static CourseDirectory get() {
        if (sCourseDirectory == null) {
            sCourseDirectory = new CourseDirectory();
        }
        return sCourseDirectory;
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
    
    public ArrayList<Course> getCourses() {
        return mCourses;
    }
}

