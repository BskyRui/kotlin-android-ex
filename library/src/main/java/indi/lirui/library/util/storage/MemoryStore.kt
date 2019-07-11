package indi.lirui.library.util.storage



// 全局的东西最好是线程安全的单例
class MemoryStore private constructor(){

    private object Holder {
        internal val INSTANCE  = MemoryStore()
    }

    companion object {
        val instance: MemoryStore get() = Holder.INSTANCE
    }

    private val mDataMap = HashMap<String, Any>()


    fun addData(k: String, value: Any): MemoryStore {
        mDataMap[k] = value
        return this
    }

    fun <T> getData(k: String): T {
        @Suppress("UNCHECKED_CAST")
        return mDataMap[k] as T
    }

    fun addData(k: Enum<*>, value: Any): MemoryStore {
        addData(k.name, value)
        return this
    }

    fun <T> getData(k: Enum<*>): T {
        return getData(k.name)
    }
}