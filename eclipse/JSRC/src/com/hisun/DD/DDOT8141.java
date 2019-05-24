package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8141 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSACCH DDCSACCH = new DDCSACCH();
    SCCGWA SCCGWA;
    DDB8141_AWA_8141 DDB8141_AWA_8141;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8141 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8141_AWA_8141>");
        DDB8141_AWA_8141 = (DDB8141_AWA_8141) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8141_AWA_8141.FUNC);
        CEP.TRC(SCCGWA, DDB8141_AWA_8141.AC);
        if (DDB8141_AWA_8141.FUNC != 'A' 
            && DDB8141_AWA_8141.FUNC != 'M' 
            && DDB8141_AWA_8141.FUNC != 'R' 
            && DDB8141_AWA_8141.FUNC != 'I' 
            && DDB8141_AWA_8141.FUNC != 'B' 
            && DDB8141_AWA_8141.FUNC != 'C' 
            && DDB8141_AWA_8141.FUNC != 'D' 
            && DDB8141_AWA_8141.FUNC != 'E') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACTION_INVALID;
            WS_FLD_NO = DDB8141_AWA_8141.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB8141_AWA_8141.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB8141_AWA_8141.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSACCH);
        DDCSACCH.DATA.FUNC = DDB8141_AWA_8141.FUNC;
        DDCSACCH.DATA.AC = DDB8141_AWA_8141.AC;
        DDCSACCH.DATA.AC_CSTS = DDB8141_AWA_8141.AC_CSTS;
        DDCSACCH.DATA.TAMT_S = DDB8141_AWA_8141.TAMT_S;
        CEP.TRC(SCCGWA, DDB8141_AWA_8141.TAMT_S);
        DDCSACCH.DATA.STOP_FLG = DDB8141_AWA_8141.STOP_FLG;
        DDCSACCH.DATA.STOP_REASON = DDB8141_AWA_8141.STOP_RSN;
        DDCSACCH.DATA.CLS_FLG = DDB8141_AWA_8141.CLS_FLG;
        DDCSACCH.DATA.CLS_TYP = DDB8141_AWA_8141.CLS_TYP;
        DDCSACCH.DATA.CLOSE_REASON = DDB8141_AWA_8141.CLS_RSN;
        DDCSACCH.DATA.ACMT_FLG = DDB8141_AWA_8141.ACMT_FLG;
        CEP.TRC(SCCGWA, DDB8141_AWA_8141.DEDU_AMT);
        DDCSACCH.DATA.DEDUCT_AMT = DDB8141_AWA_8141.DEDU_AMT;
        DDCSACCH.DATA.TRF_FLG = DDB8141_AWA_8141.TRF_FLG;
        CEP.TRC(SCCGWA, DDB8141_AWA_8141.TRF_AMT);
        DDCSACCH.DATA.TRF_AMT = DDB8141_AWA_8141.TRF_AMT;
        DDCSACCH.DATA.OTH_AC = DDB8141_AWA_8141.OTH_AC;
        DDCSACCH.DATA.AC_CNM = DDB8141_AWA_8141.AC_CNM;
        DDCSACCH.DATA.OTH_BK = DDB8141_AWA_8141.OTH_BK;
        S000_CALL_DDZSACCH();
    }
    public void S000_CALL_DDZSACCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-ACCH", DDCSACCH);
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
