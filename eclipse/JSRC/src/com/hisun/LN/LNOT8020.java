package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8020 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSMARQ LNCSMARQ = new LNCSMARQ();
    SCCGWA SCCGWA;
    LNB8020_AWA_8020 LNB8020_AWA_8020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8020_AWA_8020>");
        LNB8020_AWA_8020 = (LNB8020_AWA_8020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_SMARQ_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8020_AWA_8020.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CALL_SMARQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSMARQ);
        if (LNB8020_AWA_8020.PAGE_ROW == 0) {
            LNCSMARQ.DATA.PAGE_ROW = 20;
        } else {
            if (LNB8020_AWA_8020.PAGE_ROW > 20) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSMARQ.DATA.PAGE_ROW = LNB8020_AWA_8020.PAGE_ROW;
            }
        }
        if (LNB8020_AWA_8020.PAGE_NUM == 0) {
            LNCSMARQ.DATA.PAGE_NUM = 1;
        } else {
            LNCSMARQ.DATA.PAGE_NUM = LNB8020_AWA_8020.PAGE_NUM;
        }
        LNCSMARQ.DATA.CONT_NO = LNB8020_AWA_8020.CONT_NO;
        S000_CALL_LNZSMARQ();
    }
    public void S000_CALL_LNZSMARQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-MAR-Q", LNCSMARQ);
        if (LNCSMARQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSMARQ.RC);
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
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
