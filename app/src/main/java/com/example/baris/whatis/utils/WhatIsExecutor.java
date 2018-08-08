package com.example.baris.whatis.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WhatIsExecutor {

    private static final Object OBJECT = new Object();
    private static WhatIsExecutor appExecutors;
    private final Executor diskIO;

    private WhatIsExecutor(Executor diskIO)
    {
        this.diskIO = diskIO;
    }

    public static WhatIsExecutor getInstance()
    {
        if (appExecutors == null)
        {
            synchronized (OBJECT)
            {
                appExecutors = new WhatIsExecutor(Executors.newSingleThreadExecutor());
            }
        }
        return appExecutors;
    }

    public Executor diskIO()
    {
        return diskIO;
    }
}