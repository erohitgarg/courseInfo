package org.example.cli.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class PluralsightCoursesTest {

@ParameterizedTest
@CsvSource({
        "12:00:00, 720",
        "3:05:123456, 185",
        "00:00:00, 0"
})
    void getDurationInMinutes(String duration, long expected) {
        PluralsightCourses pluralsightCourses = new PluralsightCourses("title", "id", duration, "contentUrl", true);
        assertEquals(expected, pluralsightCourses.getDurationInMinutes());
    }
}