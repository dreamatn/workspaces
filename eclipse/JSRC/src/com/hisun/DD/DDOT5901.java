package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5901 {
    String K_OUTPUT_FMT = "DDX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDOT5901_WS_PRD_KEY WS_PRD_KEY = new DDOT5901_WS_PRD_KEY();
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMPRD DDCSMPRD = new DDCSMPRD();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5900_AWA_5900 DDB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5901 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5900_AWA_5900>");
        DDB5900_AWA_5900 = (DDB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_NXT_TXN();
        if (WS_FUNC_FLG == 'A') {
            B030_ADD_PRD_OUTPUT();
        } else {
            B040_QUERY_PRD_INFO();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.FUNC);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PRD_CD);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PRD_MOD);
        if (DDB5900_AWA_5900.PRD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5900_AWA_5900.PRD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_SET_NXT_TXN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = DDB5900_AWA_5900.FUNC;
        if (WS_FUNC_FLG == 'M') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5902;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5903;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5904;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'I') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5901;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_FUNC_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B030_ADD_PRD_OUTPUT() throws IOException,SQLException,Exception {
        WS_PRD_KEY.WS_PROD_CD = DDB5900_AWA_5900.PRD_CD;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_PRD_KEY;
        SCCFMT.DATA_LEN = 10;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMPRD);
        DDCSMPRD.KEY.PARM_CODE = DDB5900_AWA_5900.PRD_CD;
        DDCSMPRD.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_FUNC_FLG == 'Q') {
            DDCSMPRD.FUNC = 'Q';
        } else {
            DDCSMPRD.FUNC = 'I';
        }
        S000_CALL_DDZSMPRD();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_DDZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MAIN-PRD", DDCSMPRD);
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
