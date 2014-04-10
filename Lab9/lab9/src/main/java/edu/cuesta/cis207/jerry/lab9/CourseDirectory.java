package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;

public class CourseDirectory {
    private ArrayList<Course> mCourses;
    private HashMap<String, String> mTitlesById = new HashMap<String, String>();
    private HashMap<String, String> mDescriptionbyId = new HashMap<String, String>();

    private static CourseDirectory sCourseDirectory;

    private CourseDirectory() {
        mCourses = CourseData.GenerateCourses();

        for (Course c : mCourses)
        {
            mTitlesById.put(c.getId(), c.getTitle());
            mDescriptionbyId.put(c.getId(), c.getDescription());
        }
    }

    public static CourseDirectory get() {
        if (sCourseDirectory == null) {
            sCourseDirectory = new CourseDirectory();
        }
        return sCourseDirectory;
    }

    public Map<String, String> getIdsAndTitles() {
        return mTitlesById;
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

        return false;
    }

    public boolean hasCourse(Integer crn) {

        for (int i=0; i<mCourses.size(); i++) {
            if (mCourses.get(i).getCrn().equals(crn))
            {
                return true;
            }
        }

        return false;
    }

    public boolean addCourse(Integer crn, String id, int capacity) {

        // make sure CRN does not already exist

        for (int i=0; i<mCourses.size(); i++) {
            if (mCourses.get(i).getCrn().equals(crn))
            {
                return false;
            }
        }

        Course newCourse = new Course(crn, id,
                mTitlesById.get(id),
                mDescriptionbyId.get(id),
                0, capacity);

        mCourses.add(newCourse);

        return true;
    }
    
    public ArrayList<Course> getCourses() {
        return mCourses;
    }
}

