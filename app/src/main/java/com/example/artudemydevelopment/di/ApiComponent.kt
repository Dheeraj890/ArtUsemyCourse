package com.example.artudemydevelopment.di

import com.example.artudemydevelopment.model.AnimalApi
import com.example.artudemydevelopment.model.AnimalApiService
import dagger.Component


@Component(modules =[ApiModule::class])
interface ApiComponent {


fun inject(service:AnimalApiService){


}

}