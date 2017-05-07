package io.github.mcasper3.prep.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.BuildConfig
import io.github.mcasper3.prep.data.api.PrepApi
import io.github.mcasper3.prep.data.sources.DataManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    abstract fun provideDataManager(dataManager: DataManagerImpl): DataManager

    @Module
    companion object {
        @JvmStatic @Provides fun provideOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                                .newBuilder()
                                .addHeader("apiKey", BuildConfig.API_KEY)
                                .build()

                        chain.proceed(request)
                    }

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }

            return builder.build()
        }

        @JvmStatic @Provides @Singleton fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://api.ocr.space")
                .client(okHttpClient)
                .build()

        @JvmStatic @Provides fun provideRecipeApi(retrofit: Retrofit): PrepApi = retrofit
                .create(PrepApi::class.java)
    }
}
