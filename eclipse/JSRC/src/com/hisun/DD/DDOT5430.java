package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5430 {
    DBParm DDTMST_RD;
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "DD543";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    String WS_PSW_FLG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUZFMM DDCUZFMM = new DDCUZFMM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDB5430_AWA_5430 DDB5430_AWA_5430;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5430 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5430_AWA_5430>");
        DDB5430_AWA_5430 = (DDB5430_AWA_5430) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CHK_AC_STS();
        B030_CHECK_PASSWORD();
        B040_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.AC_NO);
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.TYPE);
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.ISSU_DT);
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.CHQ_NO);
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.AMT);
        CEP.TRC(SCCGWA, DDB5430_AWA_5430.PSWORD);
        if (DDB5430_AWA_5430.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5430_AWA_5430.TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5430_AWA_5430.ISSU_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_ISSU_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5430_AWA_5430.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP;
            S000_ERR_MSG_PROC();
        }
        if (DDB5430_AWA_5430.AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_INQ_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DDB5430_AWA_5430.AC_NO;
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB5430_AWA_5430.AC_NO;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUZFMM);
        DDCUZFMM.INPUT.AC_NO = DDB5430_AWA_5430.AC_NO;
        DDCUZFMM.STD_AC = DDB5430_AWA_5430.AC_NO;
        DDCUZFMM.INPUT.CHQ_NO = DDB5430_AWA_5430.CHQ_NO;
        DDCUZFMM.INPUT.CHQ_TYP = DDB5430_AWA_5430.TYPE;
        DDCUZFMM.INPUT.CHQ_ISSU_DATE = DDB5430_AWA_5430.ISSU_DT;
        DDCUZFMM.INPUT.CHQ_PSW = DDB5430_AWA_5430.PSWORD;
        DDCUZFMM.INPUT.AMT = DDB5430_AWA_5430.AMT;
        S000_CALL_DDZUZFMM();
        CEP.TRC(SCCGWA, DDCUZFMM.OUTPUT_DATE.CHK_RESULT);
        if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '1') {
            WS_PSW_FLG = "03";
        } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '2') {
            WS_PSW_FLG = "04";
        } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '3') {
            WS_PSW_FLG = "02";
        } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '4') {
            WS_PSW_FLG = "01";
        } else {
            WS_PSW_FLG = "02";
        }
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 2;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUZFMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUZFMM", DDCUZFMM);
        CEP.TRC(SCCGWA, DDCUZFMM.RC.RC_CODE);
        if (DDCUZFMM.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + DDCUZFMM.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-DCZPACTY-BEGIN");
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
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
