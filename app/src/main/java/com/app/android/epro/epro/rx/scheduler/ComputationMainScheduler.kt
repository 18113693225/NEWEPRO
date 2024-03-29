package com.app.android.epro.epro.rx.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ComputationMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())
