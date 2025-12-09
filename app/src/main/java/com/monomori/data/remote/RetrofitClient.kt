package com.monomori.data.remote

import com.monomori.BuildConfig
import com.monomori.data.remote.api.DiscogsApi
import com.monomori.data.remote.api.GoogleBooksApi
import com.monomori.data.remote.api.TmdbApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit client factory for API services
 */
object RetrofitClient {
    
    private const val TIMEOUT_SECONDS = 30L
    
    /**
     * Create OkHttpClient with logging and custom headers
     */
    private fun createOkHttpClient(userAgent: String? = null): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .apply {
                userAgent?.let { agent ->
                    addInterceptor(Interceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", agent)
                            .build()
                        chain.proceed(request)
                    })
                }
            }
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }
    
    /**
     * Create Retrofit instance with given base URL and OkHttpClient
     */
    private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    /**
     * Google Books API service
     */
    val googleBooksApi: GoogleBooksApi by lazy {
        val client = createOkHttpClient()
        val retrofit = createRetrofit("https://www.googleapis.com/", client)
        retrofit.create(GoogleBooksApi::class.java)
    }
    
    /**
     * TMDB API service
     */
    val tmdbApi: TmdbApi by lazy {
        val client = createOkHttpClient()
        val retrofit = createRetrofit(TmdbApi.BASE_URL, client)
        retrofit.create(TmdbApi::class.java)
    }
    
    /**
     * Discogs API service
     * Note: Discogs requires a User-Agent header
     */
    val discogsApi: DiscogsApi by lazy {
        val client = createOkHttpClient(DiscogsApi.USER_AGENT)
        val retrofit = createRetrofit(DiscogsApi.BASE_URL, client)
        retrofit.create(DiscogsApi::class.java)
    }
}
