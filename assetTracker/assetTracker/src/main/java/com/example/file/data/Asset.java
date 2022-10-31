package com.example.file.data;

import java.util.Date;
import java.io.Serializable;

public class Asset implements Serializable {
    private Integer id;
    private String assets_sn;
    private String assets_desc;
    private Date assets_cd;
    private Date assets_handover;
    private String assets_status;

    public Asset(){
        setId(0);
    } //default constructor

    //All args constructor
    public Asset(String assets_sn, String assets_desc, Date assets_cd,  Date assets_handover,String assets_status) {
        super();
        //this.setId(id);
        this.setAssetSerialNumber(assets_sn);
        this.setDescription(assets_desc);
        this.setCreatedDate(assets_cd);
        this.setHandoverDate(assets_handover);
        this.setAssetsStatus(assets_status);
    }

    @Override
    public String toString(){
        return "id = " + getId()
                + " name = " + getAssetSerialNumber()
                + " description = " + getDescription()
                + " serialAlphaNumber = " + getHandoverDate()
                + " createdDate = " + getCreatedDate()
                + " status = " + getAssetsStatus()
                ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetSerialNumber() {
        return assets_sn;
    }

    public void setAssetSerialNumber(String assets_sn) {
        this.assets_sn = assets_sn;
    }

    public String getDescription() {
        return assets_desc;
    }

    public void setDescription(String assets_desc) {
        this.assets_desc = assets_desc;
    }

    public Date getHandoverDate() {
        return assets_handover;
    }

    public void setHandoverDate(Date assets_handover) {
        this.assets_handover = assets_handover;
    }

    public Date getCreatedDate() {
        return assets_cd;
    }

    public void setCreatedDate(Date assets_cd) {
        this.assets_cd = assets_cd;
    }

    public String getAssetsStatus() {
        return assets_status;
    }

    public void setAssetsStatus(String assets_status) {
        this.assets_status = assets_status;
    }
}
