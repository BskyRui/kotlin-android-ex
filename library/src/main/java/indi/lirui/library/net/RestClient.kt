package indi.lirui.library.net

import indi.lirui.library.net.callback.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

// 在所有依赖library的中的app中对外暴露使用的客户端
class RestClient internal constructor(
    private val url: String?,
    private val params: WeakHashMap<String, Any>?,
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?
) {
    companion object {
        fun builder(): RestClientBuilder {
            return RestClientBuilder()
        }
    }

    private fun request(method: HttpMethod) {
        val service = RestCreator.restService
        val call: Call<String>?
        request?.onRequestStart()

        call = when (method) {
            HttpMethod.GET -> service.get(url, params)
            HttpMethod.POST -> service.post(url, params)
            HttpMethod.PUT -> service.put(url, params)
            HttpMethod.DELETE -> service.delete(url, params)

            HttpMethod.UPLOAD -> TODO()
            HttpMethod.DOWNLOAD -> TODO()
        }

        call.enqueue(requestCallback)
    }

    private val requestCallback: Callback<String> get() = RequestCallbacks(request, success, failure, error, complete)

    fun get() {
        request(HttpMethod.GET)
    }

    fun post() {
        request(HttpMethod.POST)
    }

    fun put() {
        request(HttpMethod.PUT)
    }

    fun delete() {
        request(HttpMethod.DELETE)
    }
}