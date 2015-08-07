
package com.hedgehog.gdzietabiedra.pojo.Shops;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ShopList {

    @Expose
    private Integer timestamp;
    @Expose
    private Integer result;
    @Expose
    private String error;
    @SerializedName("cdn_base")
    @Expose
    private String cdnBase;
    @Expose
    private List<Shop> shops = new ArrayList<Shop>();

    /**
     * 
     * @return
     *     The timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The error
     */
    public String getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The cdnBase
     */
    public String getCdnBase() {
        return cdnBase;
    }

    /**
     * 
     * @param cdnBase
     *     The cdn_base
     */
    public void setCdnBase(String cdnBase) {
        this.cdnBase = cdnBase;
    }

    /**
     * 
     * @return
     *     The shops
     */
    public List<Shop> getShops() {
        return shops;
    }

    /**
     * 
     * @param shops
     *     The shops
     */
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

}
