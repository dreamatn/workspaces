package com.hisun.ibscore.core.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Tia
 */

@Component
public class IbsTiaRequest implements Serializable {
    /**
     * 版本号
     */
    private     String  versionNo;
    private     Boolean compInd;
    private     Boolean encrInd;
    private     Boolean tranInd;
    private     String  reqType;
    private     String  resendFlg;
    private     String  reqChnl;
    private     String  reqChnlJrn;
    private     Date    reqChnlDate;
    private     Time    reqChnlTime;
    private     String  chnlTrId;
    private     String  reqSystem;
    private     String  reqSysJrn;
    private     Date    reqSysDate;
    private     String  reqSysTime;
    private     int     servSeq;
    private     String  trId;
        private String  trAph;
        private String  trAp;
        private String  trCode;
    private     String  trFrom;
    private     String  sessData;
        private String  termId;
        private String  fil1;
        private String  sessNo;
        private String  subSess;
        private String  subFil2;
    private     String  trBank;
    private     String  trBr;
    private     String  apvType;
    private     String  apvRef;
    private     String  apvInpPrpy;
    private     String  authInd;
    private     String  feeDataInd;
    private     String  agentFlg;
    private     String  reqRef;
    private     Date    postDate;
    private     String  tlId;
    private     String  fil3;
    private     long    lastJrnNo;
    private     Date    clearDate;
    private     String  reqChnl2;
    private     String  chnlDtl;
    private     String  serverId;
    private     String  fil4;
    private     int     authDataLen;
    private     int     agentDataLen;
    private     int     feeDataLen;
    private     int     inpDataLen;
    private     int     updDataLen;
    private     String  macChkField;
    private     String  fill;
    private     String  authData;
    private     HashMap agentData;
    private     HashMap feeData;
    private     HashMap inpData;
    private     HashMap updData;

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

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getResendFlg() {
        return resendFlg;
    }

    public void setResendFlg(String resendFlg) {
        this.resendFlg = resendFlg;
    }

    public String getReqChnl() {
        return reqChnl;
    }

    public void setReqChnl(String reqChnl) {
        this.reqChnl = reqChnl;
    }

    public String getReqChnlJrn() {
        return reqChnlJrn;
    }

    public void setReqChnlJrn(String reqChnlJrn) {
        this.reqChnlJrn = reqChnlJrn;
    }

    public Date getReqChnlDate() {
        return reqChnlDate;
    }

    public void setReqChnlDate(Date reqChnlDate) {
        this.reqChnlDate = reqChnlDate;
    }

    public Time getReqChnlTime() {
        return reqChnlTime;
    }

    public void setReqChnlTime(Time reqChnlTime) {
        this.reqChnlTime = reqChnlTime;
    }

    public String getChnlTrId() {
        return chnlTrId;
    }

    public void setChnlTrId(String chnlTrId) {
        this.chnlTrId = chnlTrId;
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

    public int getServSeq() {
        return servSeq;
    }

    public void setServSeq(int servSeq) {
        this.servSeq = servSeq;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
        this.setTrAph(trId.substring(0,1));
        this.setTrAp(trId.substring(1,3));
        this.setTrCode(trId.substring(3,7));
    }

    public String getTrAph() {
        return trAph;
    }

    private void setTrAph(String trAph) {
        this.trAph = trAph;
    }

    public String getTrAp() {
        return trAp;
    }

    private void setTrAp(String trAp) {
        this.trAp = trAp;
    }

    public String getTrCode() {
        return trCode;
    }

    private void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getTrFrom() {
        return trFrom;
    }

    public void setTrFrom(String trFrom) {
        this.trFrom = trFrom;
    }

    public String getSessData() {
        return sessData;
    }

    public void setSessData(String sessData) {
        this.sessData = sessData;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getFil1() {
        return fil1;
    }

    public void setFil1(String fil1) {
        this.fil1 = fil1;
    }

    public String getSessNo() {
        return sessNo;
    }

    public void setSessNo(String sessNo) {
        this.sessNo = sessNo;
    }

    public String getSubSess() {
        return subSess;
    }

    public void setSubSess(String subSess) {
        this.subSess = subSess;
    }

    public String getSubFil2() {
        return subFil2;
    }

    public void setSubFil2(String subFil2) {
        this.subFil2 = subFil2;
    }

    public String getTrBank() {
        return trBank;
    }

    public void setTrBank(String trBank) {
        this.trBank = trBank;
    }

    public String getTrBr() {
        return trBr;
    }

    public void setTrBr(String trBr) {
        this.trBr = trBr;
    }

    public String getApvType() {
        return apvType;
    }

    public void setApvType(String apvType) {
        this.apvType = apvType;
    }

    public String getApvRef() {
        return apvRef;
    }

    public void setApvRef(String apvRef) {
        this.apvRef = apvRef;
    }

    public String getApvInpPrpy() {
        return apvInpPrpy;
    }

    public void setApvInpPrpy(String apvInpPrpy) {
        this.apvInpPrpy = apvInpPrpy;
    }

    public String getAuthInd() {
        return authInd;
    }

    public void setAuthInd(String authInd) {
        this.authInd = authInd;
    }

    public String getFeeDataInd() {
        return feeDataInd;
    }

    public void setFeeDataInd(String feeDataInd) {
        this.feeDataInd = feeDataInd;
    }

    public String getAgentFlg() {
        return agentFlg;
    }

    public void setAgentFlg(String agentFlg) {
        this.agentFlg = agentFlg;
    }

    public String getReqRef() {
        return reqRef;
    }

    public void setReqRef(String reqRef) {
        this.reqRef = reqRef;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getTlId() {
        return tlId;
    }

    public void setTlId(String tlId) {
        this.tlId = tlId;
    }

    public String getFil3() {
        return fil3;
    }

    public void setFil3(String fil3) {
        this.fil3 = fil3;
    }

    public long getLastJrnNo() {
        return lastJrnNo;
    }

    public void setLastJrnNo(long lastJrnNo) {
        this.lastJrnNo = lastJrnNo;
    }

    public Date getClearDate() {
        return clearDate;
    }

    public void setClearDate(Date clearDate) {
        this.clearDate = clearDate;
    }

    public String getReqChnl2() {
        return reqChnl2;
    }

    public void setReqChnl2(String reqChnl2) {
        this.reqChnl2 = reqChnl2;
    }

    public String getChnlDtl() {
        return chnlDtl;
    }

    public void setChnlDtl(String chnlDtl) {
        this.chnlDtl = chnlDtl;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getFil4() {
        return fil4;
    }

    public void setFil4(String fil4) {
        this.fil4 = fil4;
    }

    public int getAuthDataLen() {
        return authDataLen;
    }

    public void setAuthDataLen(int authDataLen) {
        this.authDataLen = authDataLen;
    }

    public int getAgentDataLen() {
        return agentDataLen;
    }

    public void setAgentDataLen(int agentDataLen) {
        this.agentDataLen = agentDataLen;
    }

    public int getFeeDataLen() {
        return feeDataLen;
    }

    public void setFeeDataLen(int feeDataLen) {
        this.feeDataLen = feeDataLen;
    }

    public int getInpDataLen() {
        return inpDataLen;
    }

    public void setInpDataLen(int inpDataLen) {
        this.inpDataLen = inpDataLen;
    }

    public int getUpdDataLen() {
        return updDataLen;
    }

    public void setUpdDataLen(int updDataLen) {
        this.updDataLen = updDataLen;
    }

    public String getMacChkField() {
        return macChkField;
    }

    public void setMacChkField(String macChkField) {
        this.macChkField = macChkField;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }

    public HashMap getAgentData() {
        return agentData;
    }

    public void setAgentData(HashMap agentData) {
        this.agentData = agentData;
    }

    public HashMap getFeeData() {
        return feeData;
    }

    public void setFeeData(HashMap feeData) {
        this.feeData = feeData;
    }

    public HashMap getInpData() {
        return inpData;
    }

    public void setInpData(HashMap inpData) {
        this.inpData = inpData;
    }

    public HashMap getUpdData() {
        return updData;
    }

    public void setUpdData(HashMap updData) {
        this.updData = updData;
    }

    @Override
    public String toString() {
        return "IbsTiaRequest{" +
                "versionNo='" + versionNo + '\'' +
                ", compInd=" + compInd +
                ", encrInd=" + encrInd +
                ", tranInd=" + tranInd +
                ", reqType='" + reqType + '\'' +
                ", resendFlg='" + resendFlg + '\'' +
                ", reqChnl='" + reqChnl + '\'' +
                ", reqChnlJrn='" + reqChnlJrn + '\'' +
                ", reqChnlDate='" + reqChnlDate + '\'' +
                ", reqChnlTime='" + reqChnlTime + '\'' +
                ", chnlTrId='" + chnlTrId + '\'' +
                ", reqSystem='" + reqSystem + '\'' +
                ", reqSysJrn='" + reqSysJrn + '\'' +
                ", reqSysDate='" + reqSysDate + '\'' +
                ", reqSysTime='" + reqSysTime + '\'' +
                ", servSeq=" + servSeq +
                ", trId='" + trId + '\'' +
                ", trAph='" + trAph + '\'' +
                ", trAp='" + trAp + '\'' +
                ", trCode='" + trCode + '\'' +
                ", trFrom='" + trFrom + '\'' +
                ", sessData='" + sessData + '\'' +
                ", termId='" + termId + '\'' +
                ", fil1='" + fil1 + '\'' +
                ", sessNo='" + sessNo + '\'' +
                ", subSess='" + subSess + '\'' +
                ", subFil2='" + subFil2 + '\'' +
                ", trBank='" + trBank + '\'' +
                ", trBr='" + trBr + '\'' +
                ", apvType='" + apvType + '\'' +
                ", apvRef='" + apvRef + '\'' +
                ", apvInpPrpy='" + apvInpPrpy + '\'' +
                ", authInd='" + authInd + '\'' +
                ", feeDataInd='" + feeDataInd + '\'' +
                ", agentFlg='" + agentFlg + '\'' +
                ", reqRef='" + reqRef + '\'' +
                ", postDate='" + postDate + '\'' +
                ", tlId='" + tlId + '\'' +
                ", fil3='" + fil3 + '\'' +
                ", lastJrnNo=" + lastJrnNo +
                ", clearDate='" + clearDate + '\'' +
                ", reqChnl2='" + reqChnl2 + '\'' +
                ", chnlDtl='" + chnlDtl + '\'' +
                ", serverId='" + serverId + '\'' +
                ", fil4='" + fil4 + '\'' +
                ", authDataLen=" + authDataLen +
                ", agentDataLen=" + agentDataLen +
                ", feeDataLen=" + feeDataLen +
                ", inpDataLen=" + inpDataLen +
                ", updDataLen=" + updDataLen +
                ", macChkField='" + macChkField + '\'' +
                ", fill='" + fill + '\'' +
                ", authData='" + authData + '\'' +
                ", agentData=" + agentData +
                ", feeData=" + feeData +
                ", inpData=" + inpData +
                ", updData=" + updData +
                '}';
    }
}
