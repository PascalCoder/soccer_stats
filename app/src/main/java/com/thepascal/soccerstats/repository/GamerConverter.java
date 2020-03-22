package com.thepascal.soccerstats.repository;

import androidx.room.TypeConverter;

import com.thepascal.soccerstats.model.Gamer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

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

    @TypeConverter
    static <T> T fromTString(String value) {
        Type listType = new TypeToken<T>(){}.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    static <T> String fromTypeT(T t){
        Gson gson = new Gson();

        return gson.toJson(t);
    }
}
