package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1700 {
    String WS_ERR_MSG = " ";
    int WS_A = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSELRL GDCSELRL = new GDCSELRL();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDB1700_AWA_1700 GDB1700_AWA_1700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1700 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1700_AWA_1700>");
        GDB1700_AWA_1700 = (GDB1700_AWA_1700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.CTA_NO);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.REF_NO);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.R_TYP);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.CNT);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].ACTYP);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].AC);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].AC_SEQ);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].CCY);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].CTYP);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].AMT);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[1-1].RMK);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].ACTYP);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].AC);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].AC_SEQ);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].CCY);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].CTYP);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].AMT);
        CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[2-1].RMK);
        if (GDB1700_AWA_1700.CNT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_CNT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.R_TYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.ARRY_CNT[1-1].ACTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.ARRY_CNT[1-1].AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.ARRY_CNT[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1700_AWA_1700.ARRY_CNT[1-1].AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSELRL);
        GDCSELRL.CTA_NO = GDB1700_AWA_1700.CTA_NO;
        GDCSELRL.R_TYP = GDB1700_AWA_1700.R_TYP;
        GDCSELRL.CNT = GDB1700_AWA_1700.CNT;
        GDCSELRL.RSEQ = GDB1700_AWA_1700.RSEQ;
        GDCSELRL.REF_NO = GDB1700_AWA_1700.REF_NO;
        for (WS_A = 1; WS_A <= GDB1700_AWA_1700.CNT 
            && GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AC.trim().length() != 0; WS_A += 1) {
            GDCSELRL.AC_INFO[WS_A-1].ACTYP = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].ACTYP;
            GDCSELRL.AC_INFO[WS_A-1].AC = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AC;
            GDCSELRL.AC_INFO[WS_A-1].AC_SEQ = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AC_SEQ;
            GDCSELRL.AC_INFO[WS_A-1].CCY = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].CCY;
            GDCSELRL.AC_INFO[WS_A-1].CTYP = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].CTYP;
            GDCSELRL.AC_INFO[WS_A-1].AMT = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AMT;
            GDCSELRL.AC_INFO[WS_A-1].RMK = GDB1700_AWA_1700.ARRY_CNT[WS_A-1].RMK;
            if (GDB1700_AWA_1700.ARRY_CNT[WS_A-1].ACTYP != '1' 
                && GDB1700_AWA_1700.ARRY_CNT[WS_A-1].ACTYP != '0') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AC);
            CEP.TRC(SCCGWA, GDB1700_AWA_1700.ARRY_CNT[WS_A-1].AC_SEQ);
        }
        S000_CALL_GDZSELRL();
    }
    public void S000_CALL_GDZSELRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSELRL", GDCSELRL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
