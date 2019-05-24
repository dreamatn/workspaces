package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1600 {
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_A = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSINRL GDCSINRL = new GDCSINRL();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    GDB1600_AWA_1600 GDB1600_AWA_1600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1600 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1600_AWA_1600>");
        GDB1600_AWA_1600 = (GDB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1600_AWA_1600.CNT);
        CEP.TRC(SCCGWA, GDB1600_AWA_1600.CNT);
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].BILL_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BILL_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].R_TYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].ACTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].ACTYP != '1' 
            && GDB1600_AWA_1600.ARAY_CNT[1-1].ACTYP != '0') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1600_AWA_1600.ARAY_CNT[1-1].AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; WS_I <= GDB1600_AWA_1600.CNT 
            && GDB1600_AWA_1600.ARAY_CNT[WS_I-1].BILL_NO.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].BILL_NO);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].R_TYP);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].ACTYP);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].CCY);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].AC);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_I-1].AMT);
            if ((GDB1600_AWA_1600.CNT < 1) 
                || (GDB1600_AWA_1600.CNT > 5)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CNT_INVAILD;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].BILL_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BILL_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].R_TYP.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].ACTYP == ' ') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].ACTYP != '1' 
                && GDB1600_AWA_1600.ARAY_CNT[WS_I-1].ACTYP != '0') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].AC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (GDB1600_AWA_1600.ARAY_CNT[WS_I-1].AMT == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSINRL);
        GDCSINRL.CNT = GDB1600_AWA_1600.CNT;
        for (WS_A = 1; WS_A <= GDB1600_AWA_1600.CNT; WS_A += 1) {
            GDCSINRL.ARAY_CNT[WS_A-1].RSEQ = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].RSEQ;
            GDCSINRL.ARAY_CNT[WS_A-1].BILL_NO = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].BILL_NO;
            GDCSINRL.ARAY_CNT[WS_A-1].R_TYP = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].R_TYP;
            GDCSINRL.ARAY_CNT[WS_A-1].ACTYP = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].ACTYP;
            GDCSINRL.ARAY_CNT[WS_A-1].AC = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AC;
            GDCSINRL.ARAY_CNT[WS_A-1].AC_SEQ = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AC_SEQ;
            GDCSINRL.ARAY_CNT[WS_A-1].CCY = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].CCY;
            GDCSINRL.ARAY_CNT[WS_A-1].AMT = GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AMT;
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].RSEQ);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].BILL_NO);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].R_TYP);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].ACTYP);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AC);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AC_SEQ);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].CCY);
            CEP.TRC(SCCGWA, GDB1600_AWA_1600.ARAY_CNT[WS_A-1].AMT);
        }
        S000_CALL_GDZSINRL();
    }
    public void S000_CALL_GDZSINRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSINRL", GDCSINRL);
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
