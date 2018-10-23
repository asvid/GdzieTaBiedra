package com.hedgehog.gdzietabiedra.utils

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> asyncSingle(): SingleTransformer<T, T> {
  return SingleTransformer { upstream ->
    upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}

fun <T> asyncObservable(): ObservableTransformer<T, T> {
  return ObservableTransformer { upstream ->
    upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}

fun <T> Observable<T>.async() = this.compose(asyncObservable())
fun <T> Single<T>.async() = this.compose(asyncSingle())