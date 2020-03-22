package com.thepascal.soccerstats.repository;

import androidx.room.TypeConverter;

import com.thepascal.soccerstats.model.Bet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<Bet> fromString(String value){
        Type listType = new TypeToken<List<Bet>>(){}.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Bet> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);

        return json;
    }
}
