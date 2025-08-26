package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 45.15 คะแนน")
    void testAddScore(){
        Student s = new Student("6xxxxxxxx", "StudentTest");
        s.addScore(45.15);
        assertEquals(45.15, s.getScore());

    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 85 คะแนน และให้ Object คำนวนเกรดออกมา")
    void testCalculateGrade(){
        Student s = new Student("6xxxxxxxxx", "StudentTest");
        s.addScore(85);
        assertEquals("A", s.grade());
    }
    @Test
    void testConstructorWithoutScore() {
        Student s = new Student("07740", "Ck");
        assertEquals("07740", s.getId());
        assertEquals("Ck", s.getName());
        assertEquals(0.0, s.getScore());
    }

    @Test
    void testConstructorWithScore() {
        Student s = new Student("002", "Bob", 75.5);
        assertEquals("002", s.getId());
        assertEquals("Bob", s.getName());
        assertEquals(75.5, s.getScore());
    }

    @Test
    void testChangeNameValid() {
        Student s = new Student("003", "Charlie");
        s.changeName(" David ");
        assertEquals("David", s.getName()); // trim แล้ว
    }

    @Test
    void testChangeNameInvalidEmpty() {
        Student s = new Student("004", "Eve");
        s.changeName("   "); // ไม่ควรเปลี่ยน
        assertEquals("Eve", s.getName());
    }

    @Test
    void testAddScorePositive() {
        Student s = new Student("005", "Frank", 10);
        s.addScore(15);
        assertEquals(25.0, s.getScore());
    }

    @Test
    void testAddScoreNegativeOrZero() {
        Student s = new Student("006", "Grace", 20);
        s.addScore(0);   // ไม่เพิ่ม
        s.addScore(-5);  // ไม่เพิ่ม
        assertEquals(20.0, s.getScore());
    }

    @Test
    void testIsId() {
        Student s = new Student("009", "Jack");
        assertTrue(s.isId("009"));
        assertFalse(s.isId("010"));
    }

    @Test
    void testIsNameContains() {
        Student s = new Student("010", "Katherine");
        assertTrue(s.isNameContains("kath")); // case-insensitive
        assertFalse(s.isNameContains("xyz"));
    }

    @Test
    void testToString() {
        Student s = new Student("011", "Leo", 30);
        String result = s.toString();
        assertTrue(result.contains("id: '011'"));
        assertTrue(result.contains("name: 'Leo'"));
        assertTrue(result.contains("score: 30.0"));
    }

}