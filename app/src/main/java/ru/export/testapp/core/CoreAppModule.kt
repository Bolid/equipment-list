package ru.export.testapp.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreAppModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://eam-demo.aspectnet.ru")
        retrofit.build()
    }

    single<ResourceProvider> {
        ResourceProviderImpl(context = get())
    }
}