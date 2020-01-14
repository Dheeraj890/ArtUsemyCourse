package com.example.artudemydevelopment

import android.app.Application
import com.example.artudemydevelopment.di.PrefsModule
import com.example.artudemydevelopment.util.SharedPrefrencesHelper

class PrefsModuleTest(val mockPrefs: SharedPrefrencesHelper): PrefsModule() {

    override fun providesSharedPrefrences(app: Application): SharedPrefrencesHelper {
        return mockPrefs
    }



}