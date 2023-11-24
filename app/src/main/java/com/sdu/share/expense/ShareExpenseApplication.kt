package com.sdu.share.expense

import android.app.Application
import com.sdu.share.expense.data.AppContainer
import com.sdu.share.expense.data.AppDataContainer

class ShareExpenseApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}