package com.sanmidev.yetanotherrecipeapp.di.modules

import com.sanmidev.yetanotherrecipeapp.BuildConfig
import com.sanmidev.yetanotherrecipeapp.data.remote.services.MealDbService
import com.sanmidev.yetanotherrecipeapp.di.scopes.PerApplicationScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    //TODO add caching at network layer.
//    @Provides
//    @PerApplicationScope
//    fun providesOkHTTPCache(application: Application) : Cache {
//        val cacheSize = 10 * 1024 * 1024
//        return Cache(application.cacheDir, cacheSize.toLong())
//    }


    @Provides
    @PerApplicationScope
    fun providesOkHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.BUILD_TYPE != "release") {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return loggingInterceptor
    }


    @Provides
    @PerApplicationScope
    fun providesOkHTTPClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    @PerApplicationScope
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add((KotlinJsonAdapterFactory()))
            .build()

    }

    @Provides
    @PerApplicationScope
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @PerApplicationScope
    fun providesMealDBService(retrofit: Retrofit): MealDbService {
        return retrofit.create(MealDbService::class.java)
    }

}