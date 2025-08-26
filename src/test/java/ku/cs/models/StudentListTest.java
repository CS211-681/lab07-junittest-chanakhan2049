package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    private StudentList studentList;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        studentList.addNewStudent("001", "Alice", 50);
        studentList.addNewStudent("002", "Bob", 70);
        studentList.addNewStudent("003", "Charlie", 90);
    }

    @Test
    void testAddNewStudentWithoutScore() {
        studentList.addNewStudent("004", "David");
        Student s = studentList.findStudentById("004");
        assertNotNull(s);
        assertEquals("David", s.getName());
        assertEquals(0.0, s.getScore());
    }

    @Test
    void testAddNewStudentWithScore() {
        studentList.addNewStudent("005", "Eva", 80);
        Student s = studentList.findStudentById("005");
        assertNotNull(s);
        assertEquals("Eva", s.getName());
        assertEquals(80.0, s.getScore());
    }

    @Test
    void testAddNewStudentDuplicateIdNotAdded() {
        studentList.addNewStudent("001", "AliceDuplicate", 60);
        ArrayList<Student> students = studentList.getStudents();
        long count = students.stream().filter(s -> s.getId().equals("001")).count();
        assertEquals(1, count); // มีแค่ตัวเดียว
    }

    @Test
    void testAddNewStudentInvalidIdOrNameNotAdded() {
        studentList.addNewStudent("   ", "NoId");
        studentList.addNewStudent("006", "   ");
        assertNull(studentList.findStudentById("006"));
        assertNull(studentList.findStudentById(""));
    }

    @Test
    void testFindStudentByIdFound() {
        Student s = studentList.findStudentById("002");
        assertNotNull(s);
        assertEquals("Bob", s.getName());
    }

    @Test
    void testFindStudentByIdNotFound() {
        Student s = studentList.findStudentById("999");
        assertNull(s);
    }

    @Test
    void testFilterByName() {
        StudentList filtered = studentList.filterByName("a"); // Alice, Charlie
        assertEquals(2, filtered.getStudents().size());
        assertTrue(filtered.getStudents().stream().anyMatch(s -> s.getName().equals("Alice")));
        assertTrue(filtered.getStudents().stream().anyMatch(s -> s.getName().equals("Charlie")));
    }

    @Test
    void testGiveScoreToIdValid() {
        studentList.giveScoreToId("001", 20);
        Student s = studentList.findStudentById("001");
        assertEquals(70.0, s.getScore());
    }

    @Test
    void testGiveScoreToIdInvalid() {
        studentList.giveScoreToId("999", 30); // ไม่มี id นี้
        assertNull(studentList.findStudentById("999"));
    }

    @Test
    void testViewGradeOfIdB() {
        // Bob มี 70 คะแนน ควรได้ B
        assertEquals("B", studentList.viewGradeOfId("002"));
    }

    @Test
    void testViewGradeOfIdD() {
        // Alice มี 50 คะแนน ควรได้ D
        assertEquals("D", studentList.viewGradeOfId("001"));
    }

    @Test
    void testViewGradeOfIdNotFound() {
        assertNull(studentList.viewGradeOfId("999"));
    }
}