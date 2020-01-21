package com.example.soccerstats.repository;

import androidx.room.TypeConverter;

import com.example.soccerstats.model.Bet;
import com.example.soccerstats.model.Gamer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GamerConverter {

    @TypeConverter
    public static Gamer fromString (String value){
        Type listType = new TypeToken<Gamer>(){}.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromGamer(Gamer gamer){
        Gson gson = new Gson();

        String json = gson.toJson(gamer);

        return json;
    }
}
