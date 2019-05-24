package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8200 {
    String WS_ERR_MSG = " ";
    char WS_TXREA_FL = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSEQRL GDCSEQRL = new GDCSEQRL();
    SCCGWA SCCGWA;
    GDB8200_AWA_8200 GDB8200_AWA_8200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT8200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8200_AWA_8200>");
        GDB8200_AWA_8200 = (GDB8200_AWA_8200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.PAGE_ROW);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.PAGE_NUM);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXREA_FL);
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((GDB8200_AWA_8200.PAGE_ROW > 25) 
            || (GDB8200_AWA_8200.PAGE_ROW == 0)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PAGE_ROW_INVAILD;
            CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
        }
        if (GDB8200_AWA_8200.TXREA_FL == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUN_FLG_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_TXREA_FL = GDB8200_AWA_8200.TXREA_FL;
        if (WS_TXREA_FL == '1') {
            if (GDB8200_AWA_8200.TXRSEQ.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '2'
            || WS_TXREA_FL == '3') {
            if (GDB8200_AWA_8200.TXAC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '4') {
            if (GDB8200_AWA_8200.TXAC.trim().length() == 0 
                && GDB8200_AWA_8200.TXSEQ == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SEQ_MST_INPT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXREA_FL == '5') {
            if (GDB8200_AWA_8200.CTA_NO.trim().length() == 0 
                && GDB8200_AWA_8200.REF_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSEQRL);
        GDCSEQRL.RSEQ = GDB8200_AWA_8200.TXRSEQ;
        GDCSEQRL.AC = GDB8200_AWA_8200.TXAC;
        GDCSEQRL.AC_SEQ = GDB8200_AWA_8200.TXSEQ;
        GDCSEQRL.SDT = GDB8200_AWA_8200.TXSDT;
        GDCSEQRL.EDT = GDB8200_AWA_8200.TXEDT;
        GDCSEQRL.PAGE_ROW = GDB8200_AWA_8200.PAGE_ROW;
        GDCSEQRL.PAGE_NUM = GDB8200_AWA_8200.PAGE_NUM;
        GDCSEQRL.CTA_NO = GDB8200_AWA_8200.CTA_NO;
        GDCSEQRL.REF_NO = GDB8200_AWA_8200.REF_NO;
        GDCSEQRL.TXREA_FL = GDB8200_AWA_8200.TXREA_FL;
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXRSEQ);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXAC);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXSEQ);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXSDT);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.TXEDT);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.PAGE_ROW);
        CEP.TRC(SCCGWA, GDB8200_AWA_8200.PAGE_NUM);
        CEP.TRC(SCCGWA, GDCSEQRL.PAGE_ROW);
        S000_CALL_GDZSEQRL();
    }
    public void S000_CALL_GDZSEQRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSEQRL", GDCSEQRL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
