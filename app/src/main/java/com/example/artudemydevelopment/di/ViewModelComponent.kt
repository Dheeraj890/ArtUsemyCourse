package com.example.artudemydevelopment.di

import com.example.artudemydevelopment.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class,PrefsModule::class,AppModule::class])
@Singleton
interface ViewModelComponent {


    fun inject(viewModel: ListViewModel)

}