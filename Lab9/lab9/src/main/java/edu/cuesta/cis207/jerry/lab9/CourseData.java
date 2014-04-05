package edu.cuesta.cis207.jerry.lab9;

import java.util.ArrayList;

public class CourseData {

    public static ArrayList<Course> GenerateCourses()
    {
        ArrayList<Course> result = new ArrayList<Course>();

        result.add(new Course(
                31119,
                "WELD 175",
                "Blueprnt Rdng & Mat Proc",
                "Provides students with instruction and experience with structural, architectural and shop blueprints commonly utilized in the welding industry. Interpretation, drawing, and application of skills will be applied to determining job cost, material acquisition and project construction. Prerequisite: WELD 70A, and completion of or concurrent enrollment in WELD 70B. Advisory: MATH 23. (Formerly WELD175)",
                22,
                20
        ));
        result.add(new Course(
                31093,
                "WELD 270A",
                "Basic Welding",
                "Provides for basic instruction in oxyacetylene and shielded metal arc welding. Transfer: CSU. Materials fee $30.00. (Formerly WELD70A)",
                21,
                20
        ));
        result.add(new Course(
                31094,
                "WELD 270A",
                "Basic Welding",
                "Provides for basic instruction in oxyacetylene and shielded metal arc welding. Transfer: CSU. Materials fee $30.00. (Formerly WELD70A)",
                17,
                20
        ));
        result.add(new Course(
                31097,
                "WELD 270A",
                "Basic Welding",
                "Provides for basic instruction in oxyacetylene and shielded metal arc welding. Transfer: CSU. Materials fee $30.00. (Formerly WELD70A)",
                22,
                20
        ));
        result.add(new Course(
                31100,
                "WELD 270A",
                "Basic Welding",
                "Provides for basic instruction in oxyacetylene and shielded metal arc welding. Transfer: CSU. Materials fee $30.00. (Formerly WELD70A)",
                20,
                20
        ));
        result.add(new Course(
                31120,
                "WELD 270B",
                "Advanced Welding",
                "Continues Welding Technology 270A with emphasis placed on vertical and over head welding with the shielded metal arc processes. Prerequisite: WELD 270A Transfer: CSU. Materials fee $30.00. (Formerly WELD70B)",
                22,
                20
        ));
        result.add(new Course(31123, "WELD 277",
                "Metal Fabrication",
                "Provides an opportunity to design and construct major projects from metal. Prerequisite: WELD 270A Transfer: CSU. (Formerly WELD77)",
                22,
                20
        ));
        result.add(new Course(
                31123,
                "WELD 280A",
                "Structural Steel Welding Cert",
                "Continues WELD 270B. Prepares the student to meet industry standards in shielded metal arc welding on plate steel. Provides opportunity for certification to American Welding Society D1.1 Structural Welding Code. Prerequisite: Completion of or concurrent enrollment in WELD 270B Transfer: CSU. Materials fee $30.00. (Formerly WELD280)",
                18,
                18
        ));
        result.add(new Course(
                32552,
                "WELD 280B",
                "Pipe Weld Cert",
                "Prepares the student to meet industry standards in Shielded Metal Arc Welding (SMAW) on plate and pipe, as well as introduces Gas Tungsten Arc Welding (GTAW) on pipe. Includes workmanship and pipe fitting techniques, providing opportunity for certification to the American Society of Mechanical Engineers (ASME) Section IX Boiler and Pressure Vessel code. Prerequisite: WELD 270B",
                16,
                18
        ));

        return result;
    }
}
