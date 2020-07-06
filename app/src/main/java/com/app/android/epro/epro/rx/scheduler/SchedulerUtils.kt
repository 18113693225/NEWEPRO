package com.app.android.epro.epro.rx.scheduler

object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }

}