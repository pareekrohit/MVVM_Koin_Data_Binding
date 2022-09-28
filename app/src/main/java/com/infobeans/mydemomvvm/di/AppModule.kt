package com.infobeans.mydemomvvm.di


import com.infobeans.mydemomvvm.network.ApiService
/*import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent*/
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*import javax.inject.Singleton*/
/*import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory*/
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import org.koin.java.KoinJavaComponent.inject
import kotlin.text.Typography.dagger


/*@Module
@InstallIn(SingletonComponent::class)*/
object AppModule {

    /*@Provides*/
    fun providesUrl() = "https://jsonplaceholder.typicode.com/"

    /*@Provides
    @Singleton*/
    fun providesApiService(url: String): ApiService {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()


        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            /*.addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())*/
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}