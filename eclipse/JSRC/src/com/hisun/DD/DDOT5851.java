package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5851 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGRHD DDCUGRHD = new DDCUGRHD();
    SCCGWA SCCGWA;
    DDB5851_AWA_5851 DDB5851_AWA_5851;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5851 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5851_AWA_5851>");
        DDB5851_AWA_5851 = (DDB5851_AWA_5851) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5851_AWA_5851.FUNC);
        CEP.TRC(SCCGWA, DDB5851_AWA_5851.AC_NO);
        CEP.TRC(SCCGWA, DDB5851_AWA_5851.UP_AC);
        if (DDB5851_AWA_5851.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            WS_FLD_NO = DDB5851_AWA_5851.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5851_AWA_5851.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            if (DDB5851_AWA_5851.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5851_AWA_5851.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5851_AWA_5851.UP_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_UP_AC_MST_IN;
            WS_FLD_NO = DDB5851_AWA_5851.UP_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGRHD);
        DDCUGRHD.INPUT.FUNC = DDB5851_AWA_5851.FUNC;
        DDCUGRHD.INPUT.AC = DDB5851_AWA_5851.AC_NO;
        DDCUGRHD.INPUT.UP_AC = DDB5851_AWA_5851.UP_AC;
        S000_CALL_DDZUGRHD();
    }
    public void S000_CALL_DDZUGRHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-HOLD", DDCUGRHD);
        CEP.TRC(SCCGWA, DDCUGRHD.RC);
        if (DDCUGRHD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGRHD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
