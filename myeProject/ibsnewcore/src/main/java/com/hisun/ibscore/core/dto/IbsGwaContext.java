package com.hisun.ibscore.core.dto;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@Component
public class IbsGwaContext {

    private     String      apMmo;
    private     String      apExtMmo;
    private     String      msgType;
    private     String      msgId;
    private     String      dbioFlg;
    private     String      cancelInd;
    private     String      reenInd;
    private     long        jrnNo;
    private     long        cancelJrnNo;
    private     String      servCode;
    private     String      trId;
        private String      trAp;
        private String      trCode;
    private     String      trMmo;
    private     String      reqSystem;
    private     String      chnl;
    private     String      termId;
    private     Date        trDate;
    private     String      trTime;
    private     Date        acDate;
    private     Date        postDate;
    private     String      tlId;
    private     String      vchNo;
    private     String      hqtBank;
    private     String      trBank;
    private     String      brDp;
    private     String      prodCode;
    private     int         servSeq;
    private     int         callSeq;
    private     int         pgmNo;
    private     String      errMsgId;
        private String      errMsgIdAp;
        private String      getErrMsgIdCode;
    private     String      errMsgInfo;
    private     boolean     feeInd;
    private     boolean     feeDataInd;
    private     boolean     reversalInd;
    private     boolean     prdtFuncCtrlInd;
    private     String      sup1Id;
    private     String      sup2Id;
    private     String      bussMode;
    private     String      modifyFlag;
    private     String      bspFlg;
    private     String      sysOpt;
    private     String      filler1;
    private     String      dateInd;
    private     String      batAreaPtr;
    private     String      callDepth;
    private     boolean     jrnInUse;
    private     boolean     mstInUse;
    private     boolean     parmInUse;
    private     boolean     excpFlg;
    private     boolean     agentFlg;
    private     String      parmChanged;
    private     String      cwaPtr;
    private     String      loadParmInd;
    private     String      torSysid;
    private     String      mobileMsgSeq;
    private     String      aorSysid;
    private     String      aiBatchNo;
    private     Date        clearDate;
    private     String      reqChnl2;
    private     String      chnlDtl;
    private     String      dbUpdFlg;
    private     String      proc;
    private     String      trc;
        private String      trcLvl;
        private String      trcLvlBat;
        private String      trcLvlOper;
        private String      trcTrId;
        private String      trcTlId;
        private String      trcProc;
    private     String[]    trcArrPgm;
    private     String[]    trcArrLvl;
    private     String      mbankNo;
    private     boolean     lofFlg;
    private     String      filler2;
    private     String      performacePtr;
    private     String      scMmsgPtr;
    private     String      awaAreaPtr;
    private     String      awacAreaPtr;
    private     String      tltAreaPtr;
    private     String      trBrPtr;
    private     String      BankAreaPtr;
    private     String      scAreaPtr;
    private     String      bpAreaPtr;
    private     String      agentAreaPtr;
    private     HashMap     awaArea;
    private     HashMap     outpArea = new HashMap();



    public String getApMmo() {
        return apMmo;
    }

    public void setApMmo(String apMmo) {
        this.apMmo = apMmo;
    }

    public String getApExtMmo() {
        return apExtMmo;
    }

    public void setApExtMmo(String apExtMmo) {
        this.apExtMmo = apExtMmo;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getDbioFlg() {
        return dbioFlg;
    }

    public void setDbioFlg(String dbioFlg) {
        this.dbioFlg = dbioFlg;
    }

    public String getCancelInd() {
        return cancelInd;
    }

    public void setCancelInd(String cancelInd) {
        this.cancelInd = cancelInd;
    }

    public String getReenInd() {
        return reenInd;
    }

    public void setReenInd(String reenInd) {
        this.reenInd = reenInd;
    }

    public long getJrnNo() {
        return jrnNo;
    }

    public void setJrnNo(long jrnNo) {
        this.jrnNo = jrnNo;
    }

    public long getCancelJrnNo() {
        return cancelJrnNo;
    }

    public void setCancelJrnNo(long cancelJrnNo) {
        this.cancelJrnNo = cancelJrnNo;
    }

    public String getServCode() {
        return servCode;
    }

    public void setServCode(String servCode) {
        this.servCode = servCode;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getTrAp() {
        return trAp;
    }

    public void setTrAp(String trAp) {
        this.trAp = trAp;
    }

    public String getTrCode() {
        return trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getTrMmo() {
        return trMmo;
    }

    public void setTrMmo(String trMmo) {
        this.trMmo = trMmo;
    }

    public String getReqSystem() {
        return reqSystem;
    }

    public void setReqSystem(String reqSystem) {
        this.reqSystem = reqSystem;
    }

    public String getChnl() {
        return chnl;
    }

    public void setChnl(String chnl) {
        this.chnl = chnl;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public String getTrTime() {
        return trTime;
    }

    public void setTrTime(String trTime) {
        this.trTime = trTime;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
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

    public String getVchNo() {
        return vchNo;
    }

    public void setVchNo(String vchNo) {
        this.vchNo = vchNo;
    }

    public String getHqtBank() {
        return hqtBank;
    }

    public void setHqtBank(String hqtBank) {
        this.hqtBank = hqtBank;
    }

    public String getTrBank() {
        return trBank;
    }

    public void setTrBank(String trBank) {
        this.trBank = trBank;
    }

    public String getBrDp() {
        return brDp;
    }

    public void setBrDp(String brDp) {
        this.brDp = brDp;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public int getServSeq() {
        return servSeq;
    }

    public void setServSeq(int servSeq) {
        this.servSeq = servSeq;
    }

    public int getCallSeq() {
        return callSeq;
    }

    public void setCallSeq(int callSeq) {
        this.callSeq = callSeq;
    }

    public int getPgmNo() {
        return pgmNo;
    }

    public void setPgmNo(int pgmNo) {
        this.pgmNo = pgmNo;
    }

    public String getErrMsgId() {
        return errMsgId;
    }

    public void setErrMsgId(String errMsgId) {
        this.errMsgId = errMsgId;
    }

    public String getErrMsgIdAp() {
        return errMsgIdAp;
    }

    public void setErrMsgIdAp(String errMsgIdAp) {
        this.errMsgIdAp = errMsgIdAp;
    }

    public String getGetErrMsgIdCode() {
        return getErrMsgIdCode;
    }

    public void setGetErrMsgIdCode(String getErrMsgIdCode) {
        this.getErrMsgIdCode = getErrMsgIdCode;
    }

    public String getErrMsgInfo() {
        return errMsgInfo;
    }

    public void setErrMsgInfo(String errMsgInfo) {
        this.errMsgInfo = errMsgInfo;
    }

    public boolean isFeeInd() {
        return feeInd;
    }

    public void setFeeInd(boolean feeInd) {
        this.feeInd = feeInd;
    }

    public boolean isFeeDataInd() {
        return feeDataInd;
    }

    public void setFeeDataInd(boolean feeDataInd) {
        this.feeDataInd = feeDataInd;
    }

    public boolean isReversalInd() {
        return reversalInd;
    }

    public void setReversalInd(boolean reversalInd) {
        this.reversalInd = reversalInd;
    }

    public boolean isPrdtFuncCtrlInd() {
        return prdtFuncCtrlInd;
    }

    public void setPrdtFuncCtrlInd(boolean prdtFuncCtrlInd) {
        this.prdtFuncCtrlInd = prdtFuncCtrlInd;
    }

    public String getSup1Id() {
        return sup1Id;
    }

    public void setSup1Id(String sup1Id) {
        this.sup1Id = sup1Id;
    }

    public String getSup2Id() {
        return sup2Id;
    }

    public void setSup2Id(String sup2Id) {
        this.sup2Id = sup2Id;
    }

    public String getBussMode() {
        return bussMode;
    }

    public void setBussMode(String bussMode) {
        this.bussMode = bussMode;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getBspFlg() {
        return bspFlg;
    }

    public void setBspFlg(String bspFlg) {
        this.bspFlg = bspFlg;
    }

    public String getSysOpt() {
        return sysOpt;
    }

    public void setSysOpt(String sysOpt) {
        this.sysOpt = sysOpt;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getDateInd() {
        return dateInd;
    }

    public void setDateInd(String dateInd) {
        this.dateInd = dateInd;
    }

    public String getBatAreaPtr() {
        return batAreaPtr;
    }

    public void setBatAreaPtr(String batAreaPtr) {
        this.batAreaPtr = batAreaPtr;
    }

    public String getCallDepth() {
        return callDepth;
    }

    public void setCallDepth(String callDepth) {
        this.callDepth = callDepth;
    }

    public boolean isJrnInUse() {
        return jrnInUse;
    }

    public void setJrnInUse(boolean jrnInUse) {
        this.jrnInUse = jrnInUse;
    }

    public boolean isMstInUse() {
        return mstInUse;
    }

    public void setMstInUse(boolean mstInUse) {
        this.mstInUse = mstInUse;
    }

    public boolean isParmInUse() {
        return parmInUse;
    }

    public void setParmInUse(boolean parmInUse) {
        this.parmInUse = parmInUse;
    }

    public boolean isExcpFlg() {
        return excpFlg;
    }

    public void setExcpFlg(boolean excpFlg) {
        this.excpFlg = excpFlg;
    }

    public boolean isAgentFlg() {
        return agentFlg;
    }

    public void setAgentFlg(boolean agentFlg) {
        this.agentFlg = agentFlg;
    }

    public String getParmChanged() {
        return parmChanged;
    }

    public void setParmChanged(String parmChanged) {
        this.parmChanged = parmChanged;
    }

    public String getCwaPtr() {
        return cwaPtr;
    }

    public void setCwaPtr(String cwaPtr) {
        this.cwaPtr = cwaPtr;
    }

    public String getLoadParmInd() {
        return loadParmInd;
    }

    public void setLoadParmInd(String loadParmInd) {
        this.loadParmInd = loadParmInd;
    }

    public String getTorSysid() {
        return torSysid;
    }

    public void setTorSysid(String torSysid) {
        this.torSysid = torSysid;
    }

    public String getMobileMsgSeq() {
        return mobileMsgSeq;
    }

    public void setMobileMsgSeq(String mobileMsgSeq) {
        this.mobileMsgSeq = mobileMsgSeq;
    }

    public String getAorSysid() {
        return aorSysid;
    }

    public void setAorSysid(String aorSysid) {
        this.aorSysid = aorSysid;
    }

    public String getAiBatchNo() {
        return aiBatchNo;
    }

    public void setAiBatchNo(String aiBatchNo) {
        this.aiBatchNo = aiBatchNo;
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

    public String getDbUpdFlg() {
        return dbUpdFlg;
    }

    public void setDbUpdFlg(String dbUpdFlg) {
        this.dbUpdFlg = dbUpdFlg;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getTrc() {
        return trc;
    }

    public void setTrc(String trc) {
        this.trc = trc;
    }

    public String getTrcLvl() {
        return trcLvl;
    }

    public void setTrcLvl(String trcLvl) {
        this.trcLvl = trcLvl;
    }

    public String getTrcLvlBat() {
        return trcLvlBat;
    }

    public void setTrcLvlBat(String trcLvlBat) {
        this.trcLvlBat = trcLvlBat;
    }

    public String getTrcLvlOper() {
        return trcLvlOper;
    }

    public void setTrcLvlOper(String trcLvlOper) {
        this.trcLvlOper = trcLvlOper;
    }

    public String getTrcTrId() {
        return trcTrId;
    }

    public void setTrcTrId(String trcTrId) {
        this.trcTrId = trcTrId;
    }

    public String getTrcTlId() {
        return trcTlId;
    }

    public void setTrcTlId(String trcTlId) {
        this.trcTlId = trcTlId;
    }

    public String getTrcProc() {
        return trcProc;
    }

    public void setTrcProc(String trcProc) {
        this.trcProc = trcProc;
    }

    public String[] getTrcArrPgm() {
        return trcArrPgm;
    }

    public void setTrcArrPgm(String[] trcArrPgm) {
        this.trcArrPgm = trcArrPgm;
    }

    public String[] getTrcArrLvl() {
        return trcArrLvl;
    }

    public void setTrcArrLvl(String[] trcArrLvl) {
        this.trcArrLvl = trcArrLvl;
    }

    public String getMbankNo() {
        return mbankNo;
    }

    public void setMbankNo(String mbankNo) {
        this.mbankNo = mbankNo;
    }

    public boolean isLofFlg() {
        return lofFlg;
    }

    public void setLofFlg(boolean lofFlg) {
        this.lofFlg = lofFlg;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getPerformacePtr() {
        return performacePtr;
    }

    public void setPerformacePtr(String performacePtr) {
        this.performacePtr = performacePtr;
    }

    public String getScMmsgPtr() {
        return scMmsgPtr;
    }

    public void setScMmsgPtr(String scMmsgPtr) {
        this.scMmsgPtr = scMmsgPtr;
    }

    public String getAwaAreaPtr() {
        return awaAreaPtr;
    }

    public void setAwaAreaPtr(String awaAreaPtr) {
        this.awaAreaPtr = awaAreaPtr;
    }

    public String getAwacAreaPtr() {
        return awacAreaPtr;
    }

    public void setAwacAreaPtr(String awacAreaPtr) {
        this.awacAreaPtr = awacAreaPtr;
    }

    public String getTltAreaPtr() {
        return tltAreaPtr;
    }

    public void setTltAreaPtr(String tltAreaPtr) {
        this.tltAreaPtr = tltAreaPtr;
    }

    public String getTrBrPtr() {
        return trBrPtr;
    }

    public void setTrBrPtr(String trBrPtr) {
        this.trBrPtr = trBrPtr;
    }

    public String getBankAreaPtr() {
        return BankAreaPtr;
    }

    public void setBankAreaPtr(String bankAreaPtr) {
        BankAreaPtr = bankAreaPtr;
    }

    public String getScAreaPtr() {
        return scAreaPtr;
    }

    public void setScAreaPtr(String scAreaPtr) {
        this.scAreaPtr = scAreaPtr;
    }

    public String getBpAreaPtr() {
        return bpAreaPtr;
    }

    public void setBpAreaPtr(String bpAreaPtr) {
        this.bpAreaPtr = bpAreaPtr;
    }

    public String getAgentAreaPtr() {
        return agentAreaPtr;
    }

    public void setAgentAreaPtr(String agentAreaPtr) {
        this.agentAreaPtr = agentAreaPtr;
    }

    public HashMap getAwaArea() {
        return awaArea;
    }

    public void setAwaArea(HashMap awaArea) {
        this.awaArea = awaArea;
    }

    public HashMap getOutpArea() {
        return outpArea;
    }

    public void setOutpArea(HashMap outpArea) {
        this.outpArea = outpArea;
    }

//    public void setOutpArea(String k, String v) {
//        this.outpArea.put(k,v);
//    }

    @Override
    public String toString() {
        return "IbsGwaContext{" +
                "apMmo='" + apMmo + '\'' +
                ", apExtMmo='" + apExtMmo + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", dbioFlg='" + dbioFlg + '\'' +
                ", cancelInd='" + cancelInd + '\'' +
                ", reenInd='" + reenInd + '\'' +
                ", jrnNo=" + jrnNo +
                ", cancelJrnNo=" + cancelJrnNo +
                ", servCode='" + servCode + '\'' +
                ", trId='" + trId + '\'' +
                ", trAp='" + trAp + '\'' +
                ", trCode='" + trCode + '\'' +
                ", trMmo='" + trMmo + '\'' +
                ", reqSystem='" + reqSystem + '\'' +
                ", chnl='" + chnl + '\'' +
                ", termId='" + termId + '\'' +
                ", trDate=" + trDate +
                ", trTime='" + trTime + '\'' +
                ", acDate=" + acDate +
                ", postDate=" + postDate +
                ", tlId='" + tlId + '\'' +
                ", vchNo='" + vchNo + '\'' +
                ", hqtBank='" + hqtBank + '\'' +
                ", trBank='" + trBank + '\'' +
                ", brDp='" + brDp + '\'' +
                ", prodCode='" + prodCode + '\'' +
                ", servSeq=" + servSeq +
                ", callSeq=" + callSeq +
                ", pgmNo=" + pgmNo +
                ", errMsgId='" + errMsgId + '\'' +
                ", errMsgIdAp='" + errMsgIdAp + '\'' +
                ", getErrMsgIdCode='" + getErrMsgIdCode + '\'' +
                ", errMsgInfo='" + errMsgInfo + '\'' +
                ", feeInd=" + feeInd +
                ", feeDataInd=" + feeDataInd +
                ", reversalInd=" + reversalInd +
                ", prdtFuncCtrlInd=" + prdtFuncCtrlInd +
                ", sup1Id='" + sup1Id + '\'' +
                ", sup2Id='" + sup2Id + '\'' +
                ", bussMode='" + bussMode + '\'' +
                ", modifyFlag='" + modifyFlag + '\'' +
                ", bspFlg='" + bspFlg + '\'' +
                ", sysOpt='" + sysOpt + '\'' +
                ", filler1='" + filler1 + '\'' +
                ", dateInd='" + dateInd + '\'' +
                ", batAreaPtr='" + batAreaPtr + '\'' +
                ", callDepth='" + callDepth + '\'' +
                ", jrnInUse=" + jrnInUse +
                ", mstInUse=" + mstInUse +
                ", parmInUse=" + parmInUse +
                ", excpFlg=" + excpFlg +
                ", agentFlg=" + agentFlg +
                ", parmChanged='" + parmChanged + '\'' +
                ", cwaPtr='" + cwaPtr + '\'' +
                ", loadParmInd='" + loadParmInd + '\'' +
                ", torSysid='" + torSysid + '\'' +
                ", mobileMsgSeq='" + mobileMsgSeq + '\'' +
                ", aorSysid='" + aorSysid + '\'' +
                ", aiBatchNo='" + aiBatchNo + '\'' +
                ", clearDate=" + clearDate +
                ", reqChnl2='" + reqChnl2 + '\'' +
                ", chnlDtl='" + chnlDtl + '\'' +
                ", dbUpdFlg='" + dbUpdFlg + '\'' +
                ", proc='" + proc + '\'' +
                ", trc='" + trc + '\'' +
                ", trcLvl='" + trcLvl + '\'' +
                ", trcLvlBat='" + trcLvlBat + '\'' +
                ", trcLvlOper='" + trcLvlOper + '\'' +
                ", trcTrId='" + trcTrId + '\'' +
                ", trcTlId='" + trcTlId + '\'' +
                ", trcProc='" + trcProc + '\'' +
                ", trcArrPgm=" + Arrays.toString(trcArrPgm) +
                ", trcArrLvl=" + Arrays.toString(trcArrLvl) +
                ", mbankNo='" + mbankNo + '\'' +
                ", lofFlg=" + lofFlg +
                ", filler2='" + filler2 + '\'' +
                ", performacePtr='" + performacePtr + '\'' +
                ", scMmsgPtr='" + scMmsgPtr + '\'' +
                ", awaAreaPtr='" + awaAreaPtr + '\'' +
                ", awacAreaPtr='" + awacAreaPtr + '\'' +
                ", tltAreaPtr='" + tltAreaPtr + '\'' +
                ", trBrPtr='" + trBrPtr + '\'' +
                ", BankAreaPtr='" + BankAreaPtr + '\'' +
                ", scAreaPtr='" + scAreaPtr + '\'' +
                ", bpAreaPtr='" + bpAreaPtr + '\'' +
                ", agentAreaPtr='" + agentAreaPtr + '\'' +
                ", awaArea=" + awaArea + '\'' +
                ", outpArea=" + outpArea +
                '}';
    }
}
