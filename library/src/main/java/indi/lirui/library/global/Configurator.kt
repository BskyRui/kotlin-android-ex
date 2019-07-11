package indi.lirui.library.global

import indi.lirui.library.util.storage.MemoryStore
import kotlin.RuntimeException

// 全局配置控制类
class Configurator private constructor() {

    private object Holder {
        internal val INSTANCE = Configurator()
    }

    companion object {
        // 这里获取全局的存储容器
        private val mStore: MemoryStore = MemoryStore.instance
        // Handler需要反复使用
        private val mHadler = android.os.Handler()
        internal val instance: Configurator get() = Holder.INSTANCE
    }

    init {
        // 加一个标签, 判断是否配置完成
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, false)
        mStore.addData(GlobalKeys.HANDLER, mHadler)
    }

    // 配置api地址
    fun withApiHost(host: String): Configurator {
        mStore.addData(GlobalKeys.API_HOST, host)
        return this
    }

    // 结束配置
    fun configure() {
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, true)
        // 下面可以做一些回收动作
    }

    private fun checkConfiguration() {
        val isReady: Boolean = mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURE_READY)
        if (!isReady) {
            throw RuntimeException("config is not ready!")
        }
    }

    fun <T> getConfiguration(k: String): T {
        return mStore.getData(k)
    }

    fun <T> getConfiguration(k: Enum<*>): T {
        return getConfiguration(k.name)
    }
}