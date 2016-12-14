package com.centit.dde.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sx on 2014/12/10.
 */
@Embeddable
@Table(name="D_USER_DATAOPTID")
public class UserDataOptId implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Column(name="UDID")
    private Long udId;

    @Column(name="USERCODE")
    private String usercode;

    @Column(name="DATAOPTID")
    private String dataOptId;

    @Column(name="CREATEDATE")
    private Date createDate;

    @Column(name="LASTMODIFYDATE")
    private Date lastModifyDate;

    @Column(name="DESCRIBE")
    private String describe;
    
    @Column(name="DATAOPTTYPE")
    private String dataoptType;

    public String getDataoptType() {
        return dataoptType;
    }

    public void setDataoptType(String dataoptType) {
        this.dataoptType = dataoptType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getUdId() {
        return udId;
    }

    public void setUdId(Long udId) {
        this.udId = udId;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getDataOptId() {
        return dataOptId;
    }

    public void setDataOptId(String dataOptId) {
        this.dataOptId = dataOptId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public UserDataOptId() {
    }

    public UserDataOptId(String usercode, String dataOptId) {
        this.usercode = usercode;
        this.dataOptId = dataOptId;

        this.createDate = new Date();
    }


    public void copy(UserDataOptId other) {
        setUdId(other.getUdId());
        setCreateDate(other.getCreateDate());
        setLastModifyDate(other.getLastModifyDate());
        setDataOptId(other.getDataOptId());
        setDescribe(other.getDescribe());
        setUsercode(other.getUsercode());
        setDataoptType(other.getDataoptType());

    }

    public void copyNotNullProperty(UserDataOptId other) {
        if (null != other.getUdId()) {
            setUdId(other.getUdId());
        }
        if (null != other.getCreateDate()) {
            setCreateDate(other.getCreateDate());
        }
        if (null != other.getLastModifyDate()) {
            setLastModifyDate(other.getLastModifyDate());
        }
        if (null != other.getDataOptId()) {
            setDataOptId(other.getDataOptId());
        }
        if (null != other.getDescribe()) {
            setDescribe(other.getDescribe());
        }
        if (null != other.getUsercode()) {
            setUsercode(other.getUsercode());
        }
        if (null != other.getDataoptType()) {
            setDataoptType(other.getDataoptType());
        }

    }
}
