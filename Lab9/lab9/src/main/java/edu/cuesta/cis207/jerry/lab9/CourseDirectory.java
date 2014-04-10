package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;

public class CourseDirectory {
    private ArrayList<Course> mCourses;
    private HashMap<String, String> mIdsAndTitles = new HashMap<String, String>();

    private static CourseDirectory sCourseDirectory;

    private CourseDirectory() {
        mCourses = CourseData.GenerateCourses();

        for (Course c : mCourses)
            mIdsAndTitles.put(c.getId(), c.getTitle());
    }

    public static CourseDirectory get() {
        if (sCourseDirectory == null) {
            sCourseDirectory = new CourseDirectory();
        }
        return sCourseDirectory;
    }

    public Map<String, String> getIdsAndTitles() {
        return mIdsAndTitles;
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

