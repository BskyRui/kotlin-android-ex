package indi.lirui.library.net.callback

import android.os.Handler
import indi.lirui.library.global.GlobalKeys
import indi.lirui.library.global.Mall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCallbacks(
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?
) : Callback<String> {

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                if (success != null) {
                    if (response.body() != null) {
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        } else {
            error?.onError(response.code(), response.message())
        }

        onRequestFinish()
    }

    private fun onRequestFinish() {
//        val delayed = Mall.getConfiguration<Long>(GlobalKeys.LOADING_DELAYED)
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        failure?.onFailure()
        request?.onRequestEnd()
    }

    companion object {
        private val HANDLER = Mall.getConfiguration<Handler>(GlobalKeys.HANDLER)
    }

}