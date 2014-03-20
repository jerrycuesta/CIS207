package edu.cuesta.cis207.jerry.lab8;

public class Course {

    private Integer mCrn;
    private String mId;
    private String mTitle;
    private String mDescription;
    private Integer mEnrolled;
    private Integer mCapacity;

    public Course() {
    }

    public Course(  Integer crn,
                    String  id,
                    String  title,
                    String  description,
                    Integer enrolled,
                    Integer capacity) {
        mCrn = crn;
        mId = id;
        mTitle = title;
        mDescription = description;
        mEnrolled = enrolled;
        mCapacity = capacity;
    }

    @Override
    public String toString() {
        return mCrn.toString() + ": " + mId + " - " + mTitle;
    }

    public Integer getCrn() {
        return mCrn;
    }

    public void setCrn(Integer crn) {
        mCrn = crn;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Integer getEnrolled() {
        return mEnrolled;
    }

    public void setEnrolled(Integer enrolled) {
        mEnrolled = enrolled;
    }

    public Integer getCapacity() {
        return mCapacity;
    }

    public void setCapacity(Integer capacity) {
        mCapacity = capacity;
    }

    public boolean isOverCapacity() { return mEnrolled > mCapacity; }
}
