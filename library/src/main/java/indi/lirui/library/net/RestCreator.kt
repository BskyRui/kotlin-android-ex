package indi.lirui.library.net

import indi.lirui.library.global.GlobalKeys
import indi.lirui.library.global.Mall


// 创建Retrofit实例
class RestCreator {

    private object RetrofitHolder{
        private val BASE_URL: String = Mall.getConfiguration(GlobalKeys.API_HOST)
    }
}