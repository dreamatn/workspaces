package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5134 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDDTD DDCSDDTD = new DDCSDDTD();
    SCCGWA SCCGWA;
    DDB5130_AWA_5130 DDB5130_AWA_5130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5134 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5130_AWA_5130>");
        DDB5130_AWA_5130 = (DDB5130_AWA_5130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TEST001");
        if (DDB5130_AWA_5130.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4011;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST002");
        if (DDB5130_AWA_5130.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST003");
        if (DDB5130_AWA_5130.PROL_NO.trim().length() == 0 
            || DDB5130_AWA_5130.CON_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4020;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST004");
        CEP.TRC(SCCGWA, "TEST005");
        if (DDB5130_AWA_5130.BF_TYPE == '1' 
            && DDB5130_AWA_5130.BF_SPRD == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4024;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST006");
        if (DDB5130_AWA_5130.BF_TYPE == '2' 
            && DDB5130_AWA_5130.BF_PCNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4025;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST007");
        if (DDB5130_AWA_5130.BF_TYPE != ' ' 
            && DDB5130_AWA_5130.BP_RATE != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4027;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST008");
        if (DDB5130_AWA_5130.RATE_TYP.trim().length() > 0 
            && DDB5130_AWA_5130.PMT_RATE != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4026;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST009");
        if (DDB5130_AWA_5130.B_RAT_TP.trim().length() > 0 
            && DDB5130_AWA_5130.BP_RATE != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4026;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "TEST010");
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TEST012");
        IBS.init(SCCGWA, DDCSDDTD);
        DDCSDDTD.FUNC = DDB5130_AWA_5130.FUNC;
        DDCSDDTD.AC = DDB5130_AWA_5130.AC;
        DDCSDDTD.PROL_NO = DDB5130_AWA_5130.PROL_NO;
        DDCSDDTD.CON_NO = DDB5130_AWA_5130.CON_NO;
        DDCSDDTD.AC_CNM = DDB5130_AWA_5130.AC_CNM;
        DDCSDDTD.AC_ENM = DDB5130_AWA_5130.AC_ENM;
        DDCSDDTD.CCY = DDB5130_AWA_5130.CCY;
        CEP.TRC(SCCGWA, "TEST013");
        DDCSDDTD.CCY_TYPE = DDB5130_AWA_5130.CCY_TYPE;
        DDCSDDTD.PROL_DT = DDB5130_AWA_5130.PROL_DT;
        DDCSDDTD.PROL_AMT = DDB5130_AWA_5130.PROL_AMT;
        DDCSDDTD.PROL_BAL = DDB5130_AWA_5130.PROL_BAL;
        DDCSDDTD.TENOR = DDB5130_AWA_5130.TENOR;
        DDCSDDTD.VAL_DATE = DDB5130_AWA_5130.VAL_DATE;
        DDCSDDTD.MAT_DATE = DDB5130_AWA_5130.MAT_DATE;
        CEP.TRC(SCCGWA, "TEST014");
        DDCSDDTD.B_RAT_TP = DDB5130_AWA_5130.B_RAT_TP;
        CEP.TRC(SCCGWA, "TEST015");
        DDCSDDTD.B_RAT_TM = DDB5130_AWA_5130.B_RAT_TM;
        CEP.TRC(SCCGWA, "TEST016");
        DDCSDDTD.BF_TYPE = DDB5130_AWA_5130.BF_TYPE;
        CEP.TRC(SCCGWA, "TEST017");
        CEP.TRC(SCCGWA, DDB5130_AWA_5130.BF_SPRD);
        DDCSDDTD.BF_SPRD = DDB5130_AWA_5130.BF_SPRD;
        CEP.TRC(SCCGWA, "TEST018");
        DDCSDDTD.BF_PCNT = DDB5130_AWA_5130.BF_PCNT;
        CEP.TRC(SCCGWA, "TEST019");
        DDCSDDTD.BP_RATE = DDB5130_AWA_5130.BP_RATE;
        CEP.TRC(SCCGWA, "TEST011");
        CEP.TRC(SCCGWA, DDB5130_AWA_5130.BRK_RATE);
        DDCSDDTD.BRK_RATE = DDB5130_AWA_5130.BRK_RATE;
        DDCSDDTD.REMARKS = DDB5130_AWA_5130.REMARKS;
        S000_CALL_DDZSDDTD();
    }
    public void S000_CALL_DDZSDDTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDTD", DDCSDDTD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
