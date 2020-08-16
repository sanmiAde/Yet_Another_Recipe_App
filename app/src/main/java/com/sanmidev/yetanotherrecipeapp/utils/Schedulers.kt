package com.sanmidev.yetanotherrecipeapp.utils

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface AppScheduler {
    fun IO(): Scheduler

    fun Main(): Scheduler
}

class RxSchedulers @Inject constructor() : AppScheduler {
    override fun IO(): Scheduler {
        return Schedulers.io()
    }

    override fun Main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}


class TestSchedulers @Inject constructor() : AppScheduler {
    override fun IO(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun Main(): Scheduler {
        return Schedulers.trampoline()
    }

}

fun <T> Observable<T>.applySchedulers(appScheduler: AppScheduler): Observable<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}

fun <T> Maybe<T>.applySchedulers(appScheduler: AppScheduler): Maybe<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}

fun <T> Single<T>.applySchedulers(appScheduler: AppScheduler): Single<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}



