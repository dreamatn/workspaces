package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8100 {
    String CPN_GD_S_QLDR_PROC = "GD-S-QLDR-PROC";
    String WS_ERR_MSG = " ";
    char WS_TXREA_FL = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSQLDR GDCSQLDR = new GDCSQLDR();
    SCCGWA SCCGWA;
    GDB8100_AWA_8100 GDB8100_AWA_8100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT8100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8100_AWA_8100>");
        GDB8100_AWA_8100 = (GDB8100_AWA_8100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_QUERY_DEPOSIT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8100_AWA_8100.TXRSEQ);
        CEP.TRC(SCCGWA, GDB8100_AWA_8100.TXDD_AC);
        CEP.TRC(SCCGWA, GDB8100_AWA_8100.TXAC_SEQ);
        CEP.TRC(SCCGWA, GDB8100_AWA_8100.PAGE_NUM);
        CEP.TRC(SCCGWA, GDB8100_AWA_8100.PAGE_ROW);
        if (GDB8100_AWA_8100.TXREA_FL == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUN_FLG_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_TXREA_FL = GDB8100_AWA_8100.TXREA_FL;
        if (WS_TXREA_FL == '1') {
            if (GDB8100_AWA_8100.TXRSEQ.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '2'
            || WS_TXREA_FL == '3') {
            if (GDB8100_AWA_8100.TXDD_AC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '4') {
            if (GDB8100_AWA_8100.TXDD_AC.trim().length() == 0 
                && GDB8100_AWA_8100.TXAC_SEQ == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SEQ_MST_INPT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '5') {
            if (GDB8100_AWA_8100.CTA_NO.trim().length() == 0 
                && GDB8100_AWA_8100.REF_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else {
        }
    }
    public void B020_QUERY_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSQLDR);
        GDCSQLDR.TXRSEQ = GDB8100_AWA_8100.TXRSEQ;
        GDCSQLDR.TXDD_AC = GDB8100_AWA_8100.TXDD_AC;
        GDCSQLDR.TXAC_SEQ = GDB8100_AWA_8100.TXAC_SEQ;
        GDCSQLDR.PAGE_NUM = GDB8100_AWA_8100.PAGE_NUM;
        GDCSQLDR.PAGE_ROW = GDB8100_AWA_8100.PAGE_ROW;
        GDCSQLDR.CTA_NO = GDB8100_AWA_8100.CTA_NO;
        GDCSQLDR.REF_NO = GDB8100_AWA_8100.REF_NO;
        GDCSQLDR.TXREA_FL = GDB8100_AWA_8100.TXREA_FL;
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_NUM);
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_ROW);
        CEP.TRC(SCCGWA, GDCSQLDR.TXREA_FL);
        S000_CALL_GDZSQLDR();
    }
    public void S000_CALL_GDZSQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-S-QLDR-PROC", GDCSQLDR);
        if (GDCSQLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCSQLDR.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
