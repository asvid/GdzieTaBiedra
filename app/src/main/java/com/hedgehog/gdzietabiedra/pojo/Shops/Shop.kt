package com.hedgehog.gdzietabiedra.pojo.Shops

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Shop : RealmObject() {

    /**

     * @return
     * *     The id
     */
    /**

     * @param id
     * *     The id
     */
    @Expose
    @PrimaryKey
    var id: String? = null
    /**

     * @return
     * *     The name
     */
    /**

     * @param name
     * *     The name
     */
    @Expose
    var name: String? = null
    /**

     * @return
     * *     The city
     */
    /**

     * @param city
     * *     The city
     */
    @Expose
    var city: String? = null
    /**

     * @return
     * *     The citySlug
     */
    /**

     * @param citySlug
     * *     The city_slug
     */
    @SerializedName("city_slug")
    @Expose
    var citySlug: String? = null
    /**

     * @return
     * *     The street
     */
    /**

     * @param street
     * *     The street
     */
    @Expose
    var street: String? = null
    /**

     * @return
     * *     The streetNumber
     */
    /**

     * @param streetNumber
     * *     The street_number
     */
    @SerializedName("street_number")
    @Expose
    var streetNumber: String? = null
    /**

     * @return
     * *     The latitude
     */
    /**

     * @param latitude
     * *     The latitude
     */
    @Expose
    var latitude: String? = null
    /**

     * @return
     * *     The longitude
     */
    /**

     * @param longitude
     * *     The longitude
     */
    @Expose
    var longitude: String? = null
    /**

     * @return
     * *     The hours
     */
    /**

     * @param hours
     * *     The hours
     */
    @Expose
    var hours: String? = null
    /**

     * @return
     * *     The hoursSaturday
     */
    /**

     * @param hoursSaturday
     * *     The hours_saturday
     */
    @SerializedName("hours_saturday")
    @Expose
    var hoursSaturday: String? = null
    /**

     * @return
     * *     The hoursSunday
     */
    /**

     * @param hoursSunday
     * *     The hours_sunday
     */
    @SerializedName("hours_sunday")
    @Expose
    var hoursSunday: String? = null
    /**

     * @return
     * *     The bakery
     */
    /**

     * @param bakery
     * *     The bakery
     */
    @Expose
    var bakery: String? = null
    /**

     * @return
     * *     The relax
     */
    /**

     * @param relax
     * *     The relax
     */
    @Expose
    var relax: String? = null
    /**

     * @return
     * *     The atm
     */
    /**

     * @param atm
     * *     The atm
     */
    @Expose
    var atm: String? = null
    /**

     * @return
     * *     The cardPayment
     */
    /**

     * @param cardPayment
     * *     The card_payment
     */
    @SerializedName("card_payment")
    @Expose
    var cardPayment: String? = null
    //    @SerializedName("_new")
    //    @Expose
    //    private String _new;
    /**

     * @return
     * *     The _new
     */
    //    public String getNew() {
    //        return _new;
    //    }
    //
    //    /**
    //     *
    //     * @param _new
    //     *     The new
    //     */
    //    public void setNew(String _new) {
    //        this._new = _new;
    //    }

    /**

     * @return
     * *     The special
     */
    /**

     * @param special
     * *     The special
     */
    @Expose
    var special: String? = null
    /**

     * @return
     * *     The sublease
     */
    /**

     * @param sublease
     * *     The sublease
     */
    @Expose
    var sublease: String? = null
    /**

     * @return
     * *     The shopNumber
     */
    /**

     * @param shopNumber
     * *     The shop_number
     */
    @SerializedName("shop_number")
    @Expose
    var shopNumber: String? = null
    /**

     * @return
     * *     The importQuery
     */
    /**

     * @param importQuery
     * *     The import_query
     */
    @SerializedName("import_query")
    @Expose
    var importQuery: String? = null
    /**

     * @return
     * *     The importUpdate
     */
    /**

     * @param importUpdate
     * *     The import_update
     */
    @SerializedName("import_update")
    @Expose
    var importUpdate: String? = null
    /**

     * @return
     * *     The distance
     */
    /**

     * @param distance
     * *     The distance
     */
    @Expose
    var distance: String? = null

}
