package org.example.cli;

import org.example.cli.service.courseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        courseRetrievalService courses = new courseRetrievalService();
        LOG.info(courses.findCourses(authorId));
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
