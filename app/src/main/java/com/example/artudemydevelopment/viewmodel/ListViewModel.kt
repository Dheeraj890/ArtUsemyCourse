package com.example.artudemydevelopment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.artudemydevelopment.model.Animal


//We use AndroidViewModel instead of model because this model may need context object to perform some operations which are not possible
//without the context object so its recommended to pass only application level context rather then activity context
//because activity context are transients and  may destroy on time to time like on orientation change process.
class ListViewModel(application: Application):AndroidViewModel(application) {

    //Only need to be created when it is needed
    val animals by lazy{MutableLiveData<List<Animal>>()}
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    fun refresh(){

        getAnimal()

    }

    private fun getAnimal(){

        val a1=Animal("Aligator")
        val a2=Animal("bee")

        val a3=Animal("cat")

        val a4=Animal("dog")
        val a5=Animal("elephant")
        val a6=Animal("flamingo")


        val animalList= arrayListOf(a1,a2,a3,a4,a5,a6)

        animals.value=animalList
        loadError.value=false
        loading.value=false





    }


}