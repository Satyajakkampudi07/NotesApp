package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {notesEn.class}, version = 1, exportSchema = false)
public abstract class notesDatabase extends RoomDatabase {

    public abstract notesDao notesdao();


    private static volatile notesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static notesDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (notesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            notesDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
