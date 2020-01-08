package com.example.artudemydevelopment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.model.AnimalApiService
import com.example.artudemydevelopment.model.ApiKey
import com.example.artudemydevelopment.util.SharedPrefrencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


//We use AndroidViewModel instead of model because this model may need context object to perform some operations which are not possible
//without the context object so its recommended to pass only application level context rather then activity context
//because activity context are transients and  may destroy on time to time like on orientation change process.
class ListViewModel(application: Application):AndroidViewModel(application) {

    //Only need to be created when it is needed
    val animals by lazy{MutableLiveData<List<Animal>>()}
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private  val disposable=CompositeDisposable()
    private val apiService=AnimalApiService()

    private val prefs=SharedPrefrencesHelper(getApplication())

    private var invalidApiKey=false




    fun refresh(){
        invalidApiKey=false
        val key=prefs.getApiKey()
        if(key.isNullOrEmpty())
        {
            getKey()
        }
        else{

            getAnimal(key)
        }
        loading.value=true

    }

    fun hardRefreshKey(){

        loading.value=true
        getKey()

    }

    private fun getKey(){
disposable.add(apiService.getApiKey()
    .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    .subscribeWith(object :DisposableSingleObserver<ApiKey>(){
        override fun onSuccess(key: ApiKey) {

            if (key.key.isNullOrEmpty()){

                loadError.value=true
                loading.value=false
            }else
            {
                prefs.saveApiKey(key.key)
                getAnimal(key.key)
            }


        }

        override fun onError(e: Throwable) {

            loadError.value=true
            loading.value=false
            e.printStackTrace()

        }
    }))

    }

    private fun getAnimal(key:String){

        disposable.add(apiService.getAnimals(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<List<Animal>>(){

                override fun onSuccess(list: List<Animal>) {


                    animals.value=list
                    loadError.value=false
                    loading.value=false
                }

                override fun onError(e: Throwable) {

                    if(!invalidApiKey){

                        invalidApiKey=true
                        getKey()
                    }else {
                        e.printStackTrace()
                        loading.value = false
                        animals.value = null
                        loadError.value = true
                    }
                }


            }))



    }




    //        val a1=Animal("Aligator")
//        val a2=Animal("bee")
//
//        val a3=Animal("cat")
//
//        val a4=Animal("dog")
//        val a5=Animal("elephant")
//        val a6=Animal("flamingo")
//
//
//        val animalList= arrayListOf(a1,a2,a3,a4,a5,a6)
//
//        animals.value=animalList
//        loadError.value=false
//        loading.value=false


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}