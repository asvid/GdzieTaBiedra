
package com.hedgehog.gdzietabiedra.pojo.Shops;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.*;

public class Shop extends RealmObject{

    @Expose
    @PrimaryKey
    private String id;
    @Expose
    private String name;
    @Expose
    private String city;
    @SerializedName("city_slug")
    @Expose
    private String citySlug;
    @Expose
    private String street;
    @SerializedName("street_number")
    @Expose
    private String streetNumber;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String hours;
    @SerializedName("hours_saturday")
    @Expose
    private String hoursSaturday;
    @SerializedName("hours_sunday")
    @Expose
    private String hoursSunday;
    @Expose
    private String bakery;
    @Expose
    private String relax;
    @Expose
    private String atm;
    @SerializedName("card_payment")
    @Expose
    private String cardPayment;
//    @SerializedName("_new")
//    @Expose
//    private String _new;
    @Expose
    private String special;
    @Expose
    private String sublease;
    @SerializedName("shop_number")
    @Expose
    private String shopNumber;
    @SerializedName("import_query")
    @Expose
    private String importQuery;
    @SerializedName("import_update")
    @Expose
    private String importUpdate;
    @Expose
    private String distance;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The citySlug
     */
    public String getCitySlug() {
        return citySlug;
    }

    /**
     * 
     * @param citySlug
     *     The city_slug
     */
    public void setCitySlug(String citySlug) {
        this.citySlug = citySlug;
    }

    /**
     * 
     * @return
     *     The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *     The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return
     *     The streetNumber
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * 
     * @param streetNumber
     *     The street_number
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * 
     * @param hours
     *     The hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * 
     * @return
     *     The hoursSaturday
     */
    public String getHoursSaturday() {
        return hoursSaturday;
    }

    /**
     * 
     * @param hoursSaturday
     *     The hours_saturday
     */
    public void setHoursSaturday(String hoursSaturday) {
        this.hoursSaturday = hoursSaturday;
    }

    /**
     * 
     * @return
     *     The hoursSunday
     */
    public String getHoursSunday() {
        return hoursSunday;
    }

    /**
     * 
     * @param hoursSunday
     *     The hours_sunday
     */
    public void setHoursSunday(String hoursSunday) {
        this.hoursSunday = hoursSunday;
    }

    /**
     * 
     * @return
     *     The bakery
     */
    public String getBakery() {
        return bakery;
    }

    /**
     * 
     * @param bakery
     *     The bakery
     */
    public void setBakery(String bakery) {
        this.bakery = bakery;
    }

    /**
     * 
     * @return
     *     The relax
     */
    public String getRelax() {
        return relax;
    }

    /**
     * 
     * @param relax
     *     The relax
     */
    public void setRelax(String relax) {
        this.relax = relax;
    }

    /**
     * 
     * @return
     *     The atm
     */
    public String getAtm() {
        return atm;
    }

    /**
     * 
     * @param atm
     *     The atm
     */
    public void setAtm(String atm) {
        this.atm = atm;
    }

    /**
     * 
     * @return
     *     The cardPayment
     */
    public String getCardPayment() {
        return cardPayment;
    }

    /**
     * 
     * @param cardPayment
     *     The card_payment
     */
    public void setCardPayment(String cardPayment) {
        this.cardPayment = cardPayment;
    }

    /**
     * 
     * @return
     *     The _new
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
     * 
     * @return
     *     The special
     */
    public String getSpecial() {
        return special;
    }

    /**
     * 
     * @param special
     *     The special
     */
    public void setSpecial(String special) {
        this.special = special;
    }

    /**
     * 
     * @return
     *     The sublease
     */
    public String getSublease() {
        return sublease;
    }

    /**
     * 
     * @param sublease
     *     The sublease
     */
    public void setSublease(String sublease) {
        this.sublease = sublease;
    }

    /**
     * 
     * @return
     *     The shopNumber
     */
    public String getShopNumber() {
        return shopNumber;
    }

    /**
     * 
     * @param shopNumber
     *     The shop_number
     */
    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    /**
     * 
     * @return
     *     The importQuery
     */
    public String getImportQuery() {
        return importQuery;
    }

    /**
     * 
     * @param importQuery
     *     The import_query
     */
    public void setImportQuery(String importQuery) {
        this.importQuery = importQuery;
    }

    /**
     * 
     * @return
     *     The importUpdate
     */
    public String getImportUpdate() {
        return importUpdate;
    }

    /**
     * 
     * @param importUpdate
     *     The import_update
     */
    public void setImportUpdate(String importUpdate) {
        this.importUpdate = importUpdate;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

}
