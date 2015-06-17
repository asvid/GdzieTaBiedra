package com.hedgehog.gdzietabiedra.utils;

import com.hedgehog.gdzietabiedra.pojo.Shops.ShopList;

import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Adam on 2015-06-15.
 */
public class Api {
    public interface Shop{
        @FormUrlEncoded
        @POST("/shop")
        void getShops(@Field("lat") Double lat,
                      @Field("lon") Double lon,
                      Callback<ShopList> cb);
    }
}
