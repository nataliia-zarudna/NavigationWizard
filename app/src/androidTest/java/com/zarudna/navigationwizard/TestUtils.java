package com.zarudna.navigationwizard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.zarudna.navigationwizard.model.persistence.db.AppDatabase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

/**
 * Created by nsirobaba on 4/28/18.
 */

public class TestUtils {

    public static AppDatabase buildInMemoryDB() {

        Context context = InstrumentationRegistry.getContext();
        return Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    public static <T> T getLiveDataValueSync(LiveData<T> liveData) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        final T[] valueArr = (T[]) new Object[1];
        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(@Nullable T value) {

                valueArr[0] = value;
                latch.countDown();
            }
        });

        latch.await(3000, TimeUnit.MILLISECONDS);

        return valueArr[0];
    }

}
