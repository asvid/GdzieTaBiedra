package com.hedgehog.gdzietabiedra.utils

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

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

fun <T> Observable<T>.subscribeWithErrorLogging(onNext: (T) -> Unit): Disposable {
  return this.subscribe(onNext) {
    Timber.e(it)
  }
}

fun <T> Observable<T>.subscribeWithErrorLogging(): Disposable {
  return this.subscribeWithErrorLogging {}
}

fun Completable.subscribeWithErrorLogging(onComplete: () -> Unit): Disposable {
  return this.subscribe(onComplete) {
    Timber.e(it)
  }
}

fun Completable.subscribeWithErrorLogging(): Disposable {
  return this.subscribeWithErrorLogging {}
}

fun <T> Single<T>.subscribeWithErrorLogging(onNext: (T) -> Unit): Disposable {
  return this.subscribe(onNext) {
    Timber.e(it)
  }
}

fun <T> Single<T>.subscribeWithErrorLogging(): Disposable {
  return this.subscribeWithErrorLogging {}
}

fun <T> Flowable<T>.subscribeWithErrorLogging(onNext: (T) -> Unit): Disposable {
  return this.subscribe(onNext) {
    Timber.e(it)
  }
}