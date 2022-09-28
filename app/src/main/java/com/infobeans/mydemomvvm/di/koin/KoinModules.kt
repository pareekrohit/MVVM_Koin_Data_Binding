package com.infobeans.mydemomvvm.di.koin

import com.google.gson.GsonBuilder
import com.infobeans.mydemomvvm.BuildConfig
import com.infobeans.mydemomvvm.network.ApiService
import com.infobeans.mydemomvvm.repository.MainRepository
import com.infobeans.mydemomvvm.util.Constants.Companion.BASE_URL
import com.infobeans.mydemomvvm.viewModel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val module = module {
    factory { }
    /** this will create many objects as we go on using*/
    single {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            /*.addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())*/
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
    /** this will create single object*/


    single {
        MainRepository(get())
    }

    viewModel { MainViewModel(get()) }


}