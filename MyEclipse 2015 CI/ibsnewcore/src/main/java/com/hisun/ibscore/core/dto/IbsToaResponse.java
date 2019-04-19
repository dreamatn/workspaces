package com.hisun.ibscore.core.dto;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class IbsToaResponse {
    private     String      versionNo;
    private     Boolean     compInd;
    private     Boolean     encrInd;
    private     boolean     tranInd;
    private     boolean     pageInd;
    private     boolean     feeInd;
    private     String      reqSystem;
    private     String      reqSysJrn;
    private     Date        reqSysDate;
    private     String      reqSysTime;
    private     String      macCode;
    private     String      filler1;
    private     Date        trDate;
    private     Date        trTime;
    private     Date        acDate;
    private     long        jrnNo;
    private     String      vchNo;
    private     String      msgType;
    private     String      msgApMmo;
    private     String      msgCode;
    private     int         httpCode;
    private     int         cursPos;
    private     String      trBank;
    private     String      trBranch;
    private     boolean     lastApvFlg;
    private     String      serverId;
    private     String      filler2;
    private     boolean     batAuthInd;
    private     String      subsTrAp;
    private     String      subsTrCode;
    private     String      apvRef;
    private     String      apvPrpy;
    private     String      apRef;
    private     int         outpLen;
    private     HashMap     outpData = new HashMap();

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public Boolean getCompInd() {
        return compInd;
    }

    public void setCompInd(Boolean compInd) {
        this.compInd = compInd;
    }

    public Boolean getEncrInd() {
        return encrInd;
    }

    public void setEncrInd(Boolean encrInd) {
        this.encrInd = encrInd;
    }

    public boolean isTranInd() {
        return tranInd;
    }

    public void setTranInd(boolean tranInd) {
        this.tranInd = tranInd;
    }

    public boolean isPageInd() {
        return pageInd;
    }

    public void setPageInd(boolean pageInd) {
        this.pageInd = pageInd;
    }

    public boolean isFeeInd() {
        return feeInd;
    }

    public void setFeeInd(boolean feeInd) {
        this.feeInd = feeInd;
    }

    public String getReqSystem() {
        return reqSystem;
    }

    public void setReqSystem(String reqSystem) {
        this.reqSystem = reqSystem;
    }

    public String getReqSysJrn() {
        return reqSysJrn;
    }

    public void setReqSysJrn(String reqSysJrn) {
        this.reqSysJrn = reqSysJrn;
    }

    public Date getReqSysDate() {
        return reqSysDate;
    }

    public void setReqSysDate(Date reqSysDate) {
        this.reqSysDate = reqSysDate;
    }

    public String getReqSysTime() {
        return reqSysTime;
    }

    public void setReqSysTime(String reqSysTime) {
        this.reqSysTime = reqSysTime;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public Date getTrTime() {
        return trTime;
    }

    public void setTrTime(Date trTime) {
        this.trTime = trTime;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
    }

    public long getJrnNo() {
        return jrnNo;
    }

    public void setJrnNo(long jrnNo) {
        this.jrnNo = jrnNo;
    }

    public String getVchNo() {
        return vchNo;
    }

    public void setVchNo(String vchNo) {
        this.vchNo = vchNo;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgApMmo() {
        return msgApMmo;
    }

    public void setMsgApMmo(String msgApMmo) {
        this.msgApMmo = msgApMmo;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public int getHttpCode() { return httpCode;
    }

    public void setHttpCode(int httpCode) { this.httpCode = httpCode;
    }

    public int getCursPos() {
        return cursPos;
    }

    public void setCursPos(int cursPos) {
        this.cursPos = cursPos;
    }

    public String getTrBank() {
        return trBank;
    }

    public void setTrBank(String trBank) {
        this.trBank = trBank;
    }

    public String getTrBranch() {
        return trBranch;
    }

    public void setTrBranch(String trBranch) {
        this.trBranch = trBranch;
    }

    public boolean isLastApvFlg() {
        return lastApvFlg;
    }

    public void setLastApvFlg(boolean lastApvFlg) {
        this.lastApvFlg = lastApvFlg;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public boolean isBatAuthInd() {
        return batAuthInd;
    }

    public void setBatAuthInd(boolean batAuthInd) {
        this.batAuthInd = batAuthInd;
    }

    public String getSubsTrAp() {
        return subsTrAp;
    }

    public void setSubsTrAp(String subsTrAp) {
        this.subsTrAp = subsTrAp;
    }

    public String getSubsTrCode() {
        return subsTrCode;
    }

    public void setSubsTrCode(String subsTrCode) {
        this.subsTrCode = subsTrCode;
    }

    public String getApvRef() {
        return apvRef;
    }

    public void setApvRef(String apvRef) {
        this.apvRef = apvRef;
    }

    public String getApvPrpy() {
        return apvPrpy;
    }

    public void setApvPrpy(String apvPrpy) {
        this.apvPrpy = apvPrpy;
    }

    public String getApRef() {
        return apRef;
    }

    public void setApRef(String apRef) {
        this.apRef = apRef;
    }

    public int getOutpLen() {
        return outpLen;
    }

    public void setOutpLen(int outpLen) {
        this.outpLen = outpLen;
    }

    public HashMap getOutpData() {
        return outpData;
    }

    public void setOutpData(HashMap outpData) {
        this.outpData = outpData;
    }

    @Override
    public String toString() {
        return "IbsToaResponse{" +
                "versionNo='" + versionNo + '\'' +
                ", compInd=" + compInd +
                ", encrInd=" + encrInd +
                ", tranInd=" + tranInd +
                ", pageInd=" + pageInd +
                ", feeInd=" + feeInd +
                ", reqSystem='" + reqSystem + '\'' +
                ", reqSysJrn='" + reqSysJrn + '\'' +
                ", reqSysDate=" + reqSysDate +
                ", reqSysTime='" + reqSysTime + '\'' +
                ", macCode='" + macCode + '\'' +
                ", filler1='" + filler1 + '\'' +
                ", trDate=" + trDate +
                ", trTime=" + trTime +
                ", acDate=" + acDate +
                ", jrnNo=" + jrnNo +
                ", vchNo='" + vchNo + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgApMmo='" + msgApMmo + '\'' +
                ", msgCode='" + msgCode + '\'' +
                ", httpCode='" + httpCode + '\'' +
                ", cursPos=" + cursPos +
                ", trBank='" + trBank + '\'' +
                ", trBranch='" + trBranch + '\'' +
                ", lastApvFlg=" + lastApvFlg +
                ", serverId='" + serverId + '\'' +
                ", filler2='" + filler2 + '\'' +
                ", batAuthInd=" + batAuthInd +
                ", subsTrAp='" + subsTrAp + '\'' +
                ", subsTrCode='" + subsTrCode + '\'' +
                ", apvRef='" + apvRef + '\'' +
                ", apvPrpy='" + apvPrpy + '\'' +
                ", apRef='" + apRef + '\'' +
                ", outpLen=" + outpLen +
                ", outpData=" + outpData +
                '}';
    }
}
