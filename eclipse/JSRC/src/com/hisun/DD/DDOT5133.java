package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5133 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDOT5133_WS_LIST WS_LIST = new DDOT5133_WS_LIST();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDDTD DDCSDDTD = new DDCSDDTD();
    BPCUINTI BPCUINTI = new BPCUINTI();
    DDCSRATE DDCSRATE = new DDCSRATE();
    SCCGWA SCCGWA;
    DDB5130_AWA_5130 DDB5130_AWA_5130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5133 return!");
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
        if (DDB5130_AWA_5130.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4011;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.PROL_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4021;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.EXP_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.VAL_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "111111111111111111111");
        CEP.TRC(SCCGWA, DDB5130_AWA_5130.RATE);
        CEP.TRC(SCCGWA, DDB5130_AWA_5130.PMT_RATE);
        if (DDB5130_AWA_5130.PMT_RATE != 0) {
            DDB5130_AWA_5130.RATE = DDB5130_AWA_5130.PMT_RATE;
        }
        if (DDB5130_AWA_5130.RATE == 0) {
            IBS.init(SCCGWA, DDCSRATE);
            DDCSRATE.RATE_TYP = DDB5130_AWA_5130.RATE_TYP;
            DDCSRATE.RAT_TERM = DDB5130_AWA_5130.RAT_TERM;
            DDCSRATE.FLOAT_TP = DDB5130_AWA_5130.FLOAT_TP;
            DDCSRATE.F_SPRD = DDB5130_AWA_5130.F_SPRD;
            DDCSRATE.F_PCNT = DDB5130_AWA_5130.F_PCNT;
            DDCSRATE.CCY = DDB5130_AWA_5130.CCY;
            CEP.TRC(SCCGWA, DDCSRATE.RATE_TYP);
            CEP.TRC(SCCGWA, DDCSRATE.RAT_TERM);
            CEP.TRC(SCCGWA, DDCSRATE.FLOAT_TP);
            CEP.TRC(SCCGWA, DDCSRATE.F_SPRD);
            CEP.TRC(SCCGWA, DDCSRATE.F_PCNT);
            CEP.TRC(SCCGWA, DDCSRATE.CCY);
            S000_CALL_DDZSRATE();
            CEP.TRC(SCCGWA, DDCSRATE.CON_RATE);
            DDB5130_AWA_5130.RATE = DDCSRATE.CON_RATE;
        }
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_BASE_RATE);
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_RATE);
        if ((DDB5130_AWA_5130.FLOAT_TP == '1' 
            && DDB5130_AWA_5130.F_SPRD == 0) 
            || (DDB5130_AWA_5130.BF_TYPE == '1' 
            && DDB5130_AWA_5130.BF_SPRD == 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4024;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5130_AWA_5130.FLOAT_TP == '2' 
            && DDB5130_AWA_5130.F_PCNT == 0) 
            || (DDB5130_AWA_5130.BF_TYPE == '2' 
            && DDB5130_AWA_5130.BF_PCNT == 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4025;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5130_AWA_5130.FLOAT_TP != ' ' 
            && DDB5130_AWA_5130.PMT_RATE != 0) 
            || (DDB5130_AWA_5130.BF_TYPE != ' ' 
            && DDB5130_AWA_5130.BP_RATE != 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4027;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5130_AWA_5130.RATE_TYP.trim().length() == 0 
            && DDB5130_AWA_5130.PMT_RATE == 0) 
            || (DDB5130_AWA_5130.RATE_TYP.trim().length() > 0 
            && DDB5130_AWA_5130.PMT_RATE != 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4026;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5130_AWA_5130.B_RAT_TP.trim().length() == 0 
            && DDB5130_AWA_5130.BP_RATE == 0) 
            || (DDB5130_AWA_5130.B_RAT_TP.trim().length() > 0 
            && DDB5130_AWA_5130.BP_RATE != 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4026;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDDTD);
        DDCSDDTD.FUNC = DDB5130_AWA_5130.FUNC;
        DDCSDDTD.AC = DDB5130_AWA_5130.AC;
        DDCSDDTD.CCY = DDB5130_AWA_5130.CCY;
        DDCSDDTD.AC_CNM = DDB5130_AWA_5130.AC_CNM;
        DDCSDDTD.AC_ENM = DDB5130_AWA_5130.AC_ENM;
        DDCSDDTD.CCY_TYPE = DDB5130_AWA_5130.CCY_TYPE;
        DDCSDDTD.PROL_AMT = DDB5130_AWA_5130.PROL_AMT;
        DDCSDDTD.TENOR = DDB5130_AWA_5130.TENOR;
        DDCSDDTD.VAL_DATE = DDB5130_AWA_5130.VAL_DATE;
        CEP.TRC(SCCGWA, DDCSDDTD.VAL_DATE);
        DDCSDDTD.RATE_TYP = DDB5130_AWA_5130.RATE_TYP;
        DDCSDDTD.RAT_TERM = DDB5130_AWA_5130.RAT_TERM;
        DDCSDDTD.FLOAT_TP = DDB5130_AWA_5130.FLOAT_TP;
        DDCSDDTD.F_SPRD = DDB5130_AWA_5130.F_SPRD;
        DDCSDDTD.F_PCNT = DDB5130_AWA_5130.F_PCNT;
        DDCSDDTD.PMT_RATE = DDB5130_AWA_5130.PMT_RATE;
        if (DDB5130_AWA_5130.RATE == 0) {
            DDCSDDTD.RATE = WS_LIST.WS_LIST_BASE_RATE;
        } else {
            DDCSDDTD.RATE = DDB5130_AWA_5130.RATE;
        }
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_BASE_RATE);
        CEP.TRC(SCCGWA, DDB5130_AWA_5130.RATE);
        DDCSDDTD.B_RAT_TP = DDB5130_AWA_5130.B_RAT_TP;
        DDCSDDTD.B_RAT_TM = DDB5130_AWA_5130.B_RAT_TM;
        DDCSDDTD.BF_TYPE = DDB5130_AWA_5130.BF_TYPE;
        DDCSDDTD.BF_SPRD = DDB5130_AWA_5130.BF_SPRD;
        DDCSDDTD.BF_PCNT = DDB5130_AWA_5130.BF_PCNT;
        DDCSDDTD.BP_RATE = DDB5130_AWA_5130.BP_RATE;
        DDCSDDTD.BRK_RATE = DDB5130_AWA_5130.BRK_RATE;
        DDCSDDTD.REMARKS = DDB5130_AWA_5130.REMARKS;
        DDCSDDTD.EXP_DT = DDB5130_AWA_5130.EXP_DT;
        DDCSDDTD.DH_FLG = DDB5130_AWA_5130.DH_FLG;
        DDCSDDTD.CALR_STD = DDB5130_AWA_5130.CALR_STD;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCSDDTD.PROL_NO = DDB5130_AWA_5130.PROL_NO;
            CEP.TRC(SCCGWA, DDCSDDTD.PROL_NO);
        }
        S000_CHECK_TERM_AND_RATETYP();
        S000_CALL_DDZSDDTD();
        CEP.TRC(SCCGWA, DDCSDDTD.PROL_NO);
        DDB5130_AWA_5130.PROL_NO = DDCSDDTD.PROL_NO;
    }
    public void S000_CHECK_TERM_AND_RATETYP() throws IOException,SQLException,Exception {
        if (DDB5130_AWA_5130.RATE_TYP.trim().length() > 0 
            && DDB5130_AWA_5130.B_RAT_TM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCUINTI);
            BPCUINTI.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCUINTI.CCY = DDB5130_AWA_5130.CCY;
            BPCUINTI.BASE_TYP = DDB5130_AWA_5130.RATE_TYP;
            BPCUINTI.TENOR = DDB5130_AWA_5130.RAT_TERM;
            S000_CALL_BPZUINTI();
        }
        if (DDB5130_AWA_5130.B_RAT_TP.trim().length() > 0 
            && DDB5130_AWA_5130.B_RAT_TM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCUINTI);
            BPCUINTI.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCUINTI.CCY = DDB5130_AWA_5130.CCY;
            BPCUINTI.BASE_TYP = DDB5130_AWA_5130.B_RAT_TP;
            BPCUINTI.TENOR = DDB5130_AWA_5130.B_RAT_TM;
            S000_CALL_BPZUINTI();
        }
    }
    public void S000_CALL_BPZUINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INTR-INQ", BPCUINTI);
        CEP.TRC(SCCGWA, BPCUINTI.RC);
        if (BPCUINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUINTI.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RATE", DDCSRATE);
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
