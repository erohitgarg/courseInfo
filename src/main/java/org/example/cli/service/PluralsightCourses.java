package org.example.cli.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourses(String title, String id, String duration, String contentUrl, boolean isRetired) {

    public long getDurationInMinutes() {
        String[] time = duration.split(":");
        return  Long.parseLong(time[0])*60 + Long.parseLong(time[1]);
    }
}
