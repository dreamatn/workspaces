package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1840 {
    String CDD_A_DETAIL_AC = "DD-A-DETAIL-AC";
    String K_OUTPUT_FMT = "DD870";
    char K_FUNC_QUERY = 'I';
    char K_CR_TRAN = 'C';
    char K_DR_TRAN = 'D';
    String WS_MSG_ID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUADVT DDCUADVT = new DDCUADVT();
    SCCGWA SCCGWA;
    DDB8850_AWA_8850 DDB8850_AWA_8850;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1840 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8850_AWA_8850>");
        DDB8850_AWA_8850 = (DDB8850_AWA_8850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.PARE_AC);
        if (DDB8850_AWA_8850.PARE_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.P_AC_NM);
        if (DDB8850_AWA_8850.P_AC_NM.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NM_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.TRAN_DT);
        if (DDB8850_AWA_8850.TRAN_DT == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAL_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.VS_TNJNL);
        if (DDB8850_AWA_8850.VS_TNJNL == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_JNL_MUST_IPT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.CCY);
        if (DDB8850_AWA_8850.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.CCY_TYP);
        if (DDB8850_AWA_8850.CCY_TYP == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB8850_AWA_8850.DR_CR_F);
        if (DDB8850_AWA_8850.DR_CR_F == K_CR_TRAN 
            || DDB8850_AWA_8850.DR_CR_F == K_DR_TRAN) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_DRCR_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDB8850_AWA_8850.TRAN_AMT == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8850_AWA_8850.VS_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8850_AWA_8850.VS_AC_NM.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VACN_MST_IPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUADVT);
        DDCUADVT.VS_AC = DDB8850_AWA_8850.VS_AC;
        DDCUADVT.CCY = DDB8850_AWA_8850.CCY;
        DDCUADVT.CCY_TYP = DDB8850_AWA_8850.CCY_TYP;
        DDCUADVT.VALUE_DT = DDB8850_AWA_8850.TRAN_DT;
        DDCUADVT.DD_AC = DDB8850_AWA_8850.PARE_AC;
        DDCUADVT.TNJNL = DDB8850_AWA_8850.VS_TNJNL;
        DDCUADVT.DR_CR_F = DDB8850_AWA_8850.DR_CR_F;
        DDCUADVT.TRAN_AMT = DDB8850_AWA_8850.TRAN_AMT;
        CEP.TRC(SCCGWA, DDCUADVT.TRAN_AMT);
        DDCUADVT.UPD_FHIS = 'Y';
        DDCUADVT.SMR = DDB8850_AWA_8850.SMR;
        S000_CALL_DDZUADVT();
    }
    public void S000_CALL_DDZUADVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_A_DETAIL_AC, DDCUADVT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
