package com.unorg.nasa.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.unorg.nasa.BuildConfig
import com.unorg.nasa.network.BaseRepository
import com.unorg.nasa.network.InterfaceGlobalAPI
import com.unorg.nasa.repo.RoverPagerRepo
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import javax.net.ssl.*
import okhttp3.OkHttpClient


object NetworkModule {

    const val WaitTimeOut: Long = 30
    const val ConnTimeOut: Long = 10

    val networkModule = module {

        single { provideDefaultOkhttpClient() }
        single { provideGson() }
        single { provideBaseRepository() }
        single { provideRetrofit(get(), get()) }
        single { provideInterfaceGlobalAPI(get()) }
        single { provideRoverPagerRepo(get()) }


    }

    fun provideDefaultOkhttpClient(): OkHttpClient {


        return if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            logging.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addNetworkInterceptor(logging)
                .callTimeout(WaitTimeOut, TimeUnit.SECONDS)
                .connectTimeout(ConnTimeOut, TimeUnit.SECONDS)
                .readTimeout(WaitTimeOut, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .callTimeout(WaitTimeOut, TimeUnit.SECONDS)
                .connectTimeout(ConnTimeOut, TimeUnit.SECONDS)
                .readTimeout(WaitTimeOut, TimeUnit.SECONDS)
                .build()
        }

    }


    fun provideGson() = GsonBuilder()
        .create()

    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    fun provideInterfaceGlobalAPI(retrofit: Retrofit): InterfaceGlobalAPI =
        retrofit.create(InterfaceGlobalAPI::class.java)

    fun provideBaseRepository(): BaseRepository = BaseRepository()

    fun provideRoverPagerRepo(globalAPI: InterfaceGlobalAPI): RoverPagerRepo = RoverPagerRepo(globalAPI)


}