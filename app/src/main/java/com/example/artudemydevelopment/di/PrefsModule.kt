package com.example.artudemydevelopment.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.artudemydevelopment.util.SharedPrefrencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class PrefsModule {


    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_APP)
    fun providesSharedPrefrences(app: Application): SharedPrefrencesHelper {


        return SharedPrefrencesHelper(app)
    }


    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_ACTIVITY)
    fun provideActivity(activity:AppCompatActivity):SharedPrefrencesHelper{

        return SharedPrefrencesHelper(activity)
    }
}

const val CONTEXT_APP="Application Context"
const val CONTEXT_ACTIVITY="Activity Context"


@Qualifier
annotation class TypeOfContext(val type : String)
