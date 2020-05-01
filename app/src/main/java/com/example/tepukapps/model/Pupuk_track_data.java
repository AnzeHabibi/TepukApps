package com.example.tepukapps.model;

import com.example.tepukapps.R;

import java.util.ArrayList;

public class Pupuk_track_data {
    private static String[] pupukNames = {
        "Pupuk 1", "Pupuk 2"
    };

    private static String[] pupukDetail = {
            "Dikemas", "Dikirim"
    };


    private static int[] pupukImages = {
            R.drawable.pupuk1,
            R.drawable.pupuk2
    };

    public static ArrayList<Pupuk_track> getListData() {
        ArrayList<Pupuk_track> list = new ArrayList<>();
        for (int position = 0; position < pupukNames.length; position++) {
            Pupuk_track pupuk_track = new Pupuk_track();
            pupuk_track.setName(pupukNames[position]);
            pupuk_track.setDetail(pupukDetail[position]);
            pupuk_track.setPhoto(pupukImages[position]);
            list.add(pupuk_track);
        }
        return list;
    }

}
