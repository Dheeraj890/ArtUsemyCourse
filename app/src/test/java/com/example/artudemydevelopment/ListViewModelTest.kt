package com.example.artudemydevelopment

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artudemydevelopment.di.ApiModule
import com.example.artudemydevelopment.di.AppModule
import com.example.artudemydevelopment.di.DaggerViewModelComponent
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.model.AnimalApiService
import com.example.artudemydevelopment.model.ApiKey
import com.example.artudemydevelopment.util.SharedPrefrencesHelper
import com.example.artudemydevelopment.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Mock
    lateinit var animalApiService: AnimalApiService


    @Mock
    lateinit var prefs: SharedPrefrencesHelper

    val application = Mockito.mock(Application::class.java)

    var listViewModel = ListViewModel(application, true)


    private val key = "Test Key"

    @Before
    fun setUp() {


        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(animalApiService))
            .prefsModule(PrefsModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }

    @Before
    fun setUpRxSchedulers() {

        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {

                return ExecutorScheduler.ExecutorWorker(Executor {

                    it.run()
                }, true)
            }

        }


        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }


    @Test
    fun getAnimalSuccess() {

        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val animal = Animal("Cow", null, null, null, null, null, null)

        val animalList = listOf(animal)
        val testSingle = Single.just(animalList)
        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)

        Assert.assertEquals(false, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)

    }

    @Test
    fun getAnimalFailure() {

        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val testSingle = Single.error<List<Animal>>(Throwable())


        val keySingle = Single.just(ApiKey("OK", key))

        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)

        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(null, listViewModel.animals.value)
        Assert.assertEquals(true, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)
    }


    @Test
    fun getKeySuccessWithNullKey() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(null)

        val keySingle = Single.just(ApiKey("OK", null))

        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(true, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getKeySuccessWithoutNullKey() {

        Mockito.`when`(prefs.getApiKey()).thenReturn(null)

        val keySingle = Single.just(ApiKey("OK", key))

        Mockito.`when`(animalApiService.getApiKey()).thenReturn(keySingle)

        val animal = Animal("Cow", null, null, null, null, null, null)

        val animalList = listOf(animal)

        val testSingle=Single.just(animalList)

        Mockito.`when`(animalApiService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)

        Assert.assertEquals(false, listViewModel.loadError.value)

        Assert.assertEquals(false, listViewModel.loading.value)
    }


}