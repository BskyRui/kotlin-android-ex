package indi.lirui.kmall

import android.app.Application
import indi.lirui.library.global.Mall

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Mall.init(this).withApiHost("http://example.com").configure()
    }
}