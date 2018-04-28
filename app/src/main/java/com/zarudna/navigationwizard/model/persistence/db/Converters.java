package com.zarudna.navigationwizard.model.persistence.db;

import android.arch.persistence.room.TypeConverter;

import java.util.UUID;

/**
 * Created by nsirobaba on 4/28/18.
 */

public class Converters {

    @TypeConverter
    public static UUID stringToUUID(String uuidStr) {
        return uuidStr != null ? UUID.fromString(uuidStr) : null;
    }

    @TypeConverter
    public static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
}
