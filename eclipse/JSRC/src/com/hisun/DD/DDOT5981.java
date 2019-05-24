package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5981 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCSMRAT DDCSMRAT = new DDCSMRAT();
    SCCGWA SCCGWA;
    DDB5981_AWA_5981 DDB5981_AWA_5981;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5981 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5981_AWA_5981>");
        DDB5981_AWA_5981 = (DDB5981_AWA_5981) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NXT_TXN();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.FUNC);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.PARM_CD);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.CCY);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EFFDAT);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EXPDAT);
        if (DDB5981_AWA_5981.PARM_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.PARM_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B015_SET_NXT_TXN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.FUNC);
        WS_FUNC_FLG = DDB5981_AWA_5981.FUNC;
        if (WS_FUNC_FLG == 'M') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5982;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5983;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5984;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'I') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 11;
            SCCSUBS.TR_CODE = 5981;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_FUNC_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMRAT);
        DDCSMRAT.KEY.PARM_CODE = DDB5981_AWA_5981.PARM_CD;
        DDCSMRAT.KEY.CCY = DDB5981_AWA_5981.CCY;
        if (WS_FUNC_FLG == 'Q') {
            DDCSMRAT.FUNC = 'Q';
        } else {
            DDCSMRAT.FUNC = 'I';
        }
        S000_CALL_DDZSMRAT();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-SET-SUBS-TRANS";
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = DDB5981_AWA_5981.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_DDZSMRAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MAIN-MRAT", DDCSMRAT);
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
