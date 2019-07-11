package indi.lirui.library.global

import android.content.Context
import indi.lirui.library.util.storage.MemoryStore

object Mall {
    val configurator: Configurator get() = Configurator.instance

    fun init(context: Context): Configurator {
        MemoryStore.instance.addData(GlobalKeys.APPLICATION_CONTEXT, context.applicationContext)
        return Configurator.instance
    }

    fun <T> getConfiguration(k: String): T {
        return configurator.getConfiguration(k)
    }

    fun <T> getConfiguration(k: Enum<GlobalKeys>): T {
        return getConfiguration(k.name)
    }
}