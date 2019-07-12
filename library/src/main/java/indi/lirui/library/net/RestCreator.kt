package indi.lirui.library.net

import indi.lirui.library.global.GlobalKeys
import indi.lirui.library.global.Mall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


// 创建Retrofit实例
object RestCreator {

    private object OkHttpHolder {
        private const val TIME_OUT = 60
        private val BUILDER = OkHttpClient.Builder()
        internal val OK_HTTP_CLIENT = BUILDER
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    private object RetrofitHolder{
        private val BASE_URL: String = Mall.getConfiguration(GlobalKeys.API_HOST)
        // 模块内可见
        internal val RETROFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    private object RestServiceHolder {
        internal val REST_SERVICE = RetrofitHolder
            .RETROFIT_CLIENT
            .create(RestService::class.java)
    }

    // 自定义getter属性, 每次访问的时候从 RestServiceHolder.REST_SERVICE 获取
    val restService: RestService get() = RestServiceHolder.REST_SERVICE

}