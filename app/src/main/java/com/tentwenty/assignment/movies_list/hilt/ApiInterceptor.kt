package com.tentwenty.assignment.movies_list.hilt

import com.tentwenty.assignment.movies_list.common.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val httpUrl: HttpUrl = original.url
        val url: HttpUrl = httpUrl.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .build()
        val request: Request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
