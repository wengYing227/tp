package seedu.tracker.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AcademicYearTest {

    @Test
    public void constructor_invalidAcademicYear_throwsIllegalArgumentException() {
        int invalidAcademicYearZero = 0;
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear(invalidAcademicYearZero));
    }

    @Test
    public void isValidYear() {
        // null tag name
        int invalidYearZero = 0;
        int invalidYearSeven = 7;
        int validYear = 2;
        assertEquals(false, Semester.isValidSemester(invalidYearZero));
        assertEquals(false, Semester.isValidSemester(invalidYearSeven));
        assertEquals(true, Semester.isValidSemester(validYear));
    }

    @Test
    public void testToString() {
        assertEquals("1", new AcademicYear(1).toString());
    }

    @Test
    public void equals_sameAcademicYear_true() {
        AcademicYear test1 = new AcademicYear(1);
        AcademicYear test2 = new AcademicYear(1);
        assertEquals(true, test1.equals(test2));
    }

    @Test
    public void equals_differentAcademicYear_true() {
        AcademicYear test1 = new AcademicYear(1);
        AcademicYear test2 = new AcademicYear(2);
        assertEquals(false, test1.equals(test2));
    }

}
