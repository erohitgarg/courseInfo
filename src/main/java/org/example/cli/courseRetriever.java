package org.example.cli;

import org.example.cli.service.PluralsightCourses;
import org.example.cli.service.courseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class courseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(courseRetriever.class);
    public static void main(String[] args) {
        if (args.length == 0) {
            LOG.warn("The author is not present.");
            return;
        }
        try {
            retrieveCourse(args[0]);
        } catch (Exception e) {
            LOG.error("Could not retrieve courses!", e);
        }
    }

    private static void retrieveCourse(String authorId) {
        String author = breakName(authorId);
        LOG.info("Retrieving courses for author: " + author);
        courseRetrievalService courseRetrievalService = new courseRetrievalService();
        List<PluralsightCourses> courses = courseRetrievalService
                .findCourses(authorId);
        List<PluralsightCourses> activeCourses = courses
                .stream()
                        .filter(core -> !core.isRetired())
                                .toList();
        LOG.info("{} Courses found by Author {}, /n Courses are: {}", activeCourses.size(), author, activeCourses);
    }

    public static String breakName(String authorId) {
        StringBuilder sb = new StringBuilder();
        sb.append(authorId.substring(0, 1).toUpperCase());
        for (int i = 1; i<authorId.length(); i++) {
            if (authorId.charAt(i) == '-') {
                sb.append(" ");
                i++;
                sb.append(authorId.substring(i, i+1).toUpperCase());
            }
            else sb.append(authorId.charAt(i));
        }
        return sb.toString();
    }
}
