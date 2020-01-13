package com.example.artudemydevelopment.model

import com.example.artudemydevelopment.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AnimalApiService {



    @Inject
    lateinit var  api:AnimalApi

   init{


       DaggerApiComponent.create().inject(this)
   }
    fun getApiKey():Single<ApiKey>{

        return  api.getApiKey()
    }

    fun getAnimals(key:String):Single<List<Animal>>{

        return api.getAnimals(key)
    }
}