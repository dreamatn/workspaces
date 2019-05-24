package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5983 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_LOCAL_CCY = "CNY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMRAT DDCSMRAT = new DDCSMRAT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5980_AWA_5980 DDB5980_AWA_5980;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5983 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5980_AWA_5980>");
        DDB5980_AWA_5980 = (DDB5980_AWA_5980) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.FUNC);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.PARM_CD);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.CCY);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.EFFDAT);
        CEP.TRC(SCCGWA, DDB5980_AWA_5980.EXPDAT);
        if (DDB5980_AWA_5980.PARM_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5980_AWA_5980.PARM_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5980_AWA_5980.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5980_AWA_5980.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_DELETE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMRAT);
        DDCSMRAT.KEY.PARM_CODE = DDB5980_AWA_5980.PARM_CD;
        DDCSMRAT.KEY.CCY = DDB5980_AWA_5980.CCY;
        DDCSMRAT.FUNC = 'D';
        S000_CALL_DDZSMRAT();
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
