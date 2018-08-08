package com.example.baris.whatis;

import android.support.annotation.NonNull;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.baris.whatis.data.ManagerOfData;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private ManagerOfData managerOfData;
    private Application application;

    MainViewModelFactory(Application application, ManagerOfData managerOfData) {
        this.managerOfData = managerOfData;
        this.application = application;
    }

    @NonNull
    @Override
    public MainViewModel create(@NonNull Class mClass) {
        return new MainViewModel(application, managerOfData);
    }
}
