package com.hisun.ibscore.app.bp.domain;

import oracle.sql.RAW;
import oracle.sql.TIMESTAMP;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 柜员
 * 柜员的实体类封装起来了
 */
public class User implements Serializable {

    private     String      tlr;
    private     int         tlrBr;
    private     String      tlrCnNm;
    private     String      tlrEnNm;
    private     long        expDt;
    private     long        updDt;
    private     String      updTlr;
    private     String      signSts;
    private     String      tlrSts;
    private     long        signDt;
//    private     RAW         kbPsw;
//    private     TIMESTAMP   ts;
    private     String      ts;


    public String getTlr() {
        return tlr;
    }

    public void setTlr(String tlr) {
        this.tlr = tlr;
    }

    public int getTlrBr() {
        return tlrBr;
    }

    public void setTlrBr(int tlrBr) {
        this.tlrBr = tlrBr;
    }

    public String getTlrCnNm() {
        return tlrCnNm;
    }

    public void setTlrCnNm(String tlrCnNm) {
        this.tlrCnNm = tlrCnNm;
    }

    public String getTlrEnNm() {
        return tlrEnNm;
    }

    public void setTlrEnNm(String tlrEnNm) {
        this.tlrEnNm = tlrEnNm;
    }

    public long getExpDt() {
        return expDt;
    }

    public void setExpDt(long expDt) {
        this.expDt = expDt;
    }

    public long getUpdDt() {
        return updDt;
    }

    public void setUpdDt(long updDt) {
        this.updDt = updDt;
    }

    public String getUpdTlr() {
        return updTlr;
    }

    public void setUpdTlr(String updTlr) {
        this.updTlr = updTlr;
    }

    public String getSignSts() {
        return signSts;
    }

    public void setSignSts(String signSts) {
        this.signSts = signSts;
    }

    public String getTlrSts() {
        return tlrSts;
    }

    public void setTlrSts(String tlrSts) {
        this.tlrSts = tlrSts;
    }

    public long getSignDt() {
        return signDt;
    }

    public void setSignDt(long sign_dt) {
        this.signDt = signDt;
    }

//    public RAW getKbPsw() {
//        return kbPsw;
//    }

//    public void setKbPsw(RAW kbPsw) {
//        this.kbPsw = kbPsw;
//    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "User{" +
                "tlr='" + tlr + '\'' +
                ", tlrBr=" + tlrBr +
                ", tlrCnNm='" + tlrCnNm + '\'' +
                ", tlrEnNm='" + tlrEnNm + '\'' +
                ", expDt=" + expDt +
                ", updDt=" + updDt +
                ", updTlr='" + updTlr + '\'' +
                ", signSts='" + signSts + '\'' +
                ", tlrSts='" + tlrSts + '\'' +
                ", signDt=" + signDt +
//                ", kbPsw=" + kbPsw +
                ", ts=" + ts +
                '}';
    }
}
