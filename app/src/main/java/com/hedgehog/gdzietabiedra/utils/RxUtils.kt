package com.hedgehog.gdzietabiedra.utils

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> applySchedulers(): SingleTransformer<T, T> {
  return SingleTransformer { upstream ->
    upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}