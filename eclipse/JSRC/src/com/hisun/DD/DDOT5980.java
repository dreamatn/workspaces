package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5980 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMRAT DDCSMRAT = new DDCSMRAT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5980_AWA_5980 DDB5980_AWA_5980;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5980 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5980_AWA_5980>");
        DDB5980_AWA_5980 = (DDB5980_AWA_5980) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_RETURN_INFO();
        B030_BRW_CCY_RAT_INF_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.FUNC);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.PARM_CD);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.CCY);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.EFFDAT);
        if (DDB5980_AWA_5980.PARM_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5980_AWA_5980.PARM_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.FUNC);
        WS_FUNC_FLG = DDB5980_AWA_5980.FUNC;
        if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5909;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5909;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5909;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5904;
            S000_SET_SUBS_TRN();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDB5980_AWA_5980.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B030_BRW_CCY_RAT_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMRAT);
        DDCSMRAT.KEY.PARM_CODE = DDB5980_AWA_5980.PARM_CD;
        DDCSMRAT.FUNC = 'B';
        S000_CALL_DDZSMRAT();
    }
    public void S000_CALL_DDZSMRAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MAIN-MRAT", DDCSMRAT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
