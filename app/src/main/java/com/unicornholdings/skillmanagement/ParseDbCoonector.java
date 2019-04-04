package com.unicornholdings.skillmanagement;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ParseDbCoonector {

    private HashMap<String, Courses> getAllRoundsFromDb() {

        HashMap<String, Courses> allRounds = new HashMap<String, Courses>();
        HashMap<String,Courses> allRoundsByID = new HashMap<String,Courses>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rounds");
        try {
            List<ParseObject> objects = query.find();
            for (ParseObject roundObj : objects) {
                Courses round = createRoundFromObject(roundObj);
                allRounds.put(String.valueOf(round.getCourseName()),round);
                allRoundsByID.put(String.valueOf(round.getCourseDuration()),round);
            }

        }
        catch (ParseException e) {
            e.printStackTrace();
        }


        return allRounds;
    }

    private Courses createRoundFromObject(ParseObject roundObj) {

        return null;
    }
}
