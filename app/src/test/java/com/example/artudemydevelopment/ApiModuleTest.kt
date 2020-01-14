package com.example.artudemydevelopment

import com.example.artudemydevelopment.di.ApiModule
import com.example.artudemydevelopment.model.AnimalApiService

class ApiModuleTest(val mockService:AnimalApiService):ApiModule() {


    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }

}