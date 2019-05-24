package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5750 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSFUND LNCSFUND = new LNCSFUND();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB5300_AWA_5300 LNB5300_AWA_5300;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5750 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5300_AWA_5300>");
        LNB5300_AWA_5300 = (LNB5300_AWA_5300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSFUND.RC.RC_MMO = "LN";
        LNCSFUND.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FUNDMULA_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CI_NO = LNB5300_AWA_5300.CI_NO;
        S000_CALL_LNZSSTBL();
    }
    public void B020_FUNDMULA_MAIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5300_AWA_5300.PROJ_NO);
        CEP.TRC(SCCGWA, LNB5300_AWA_5300.CI_NO);
        CEP.TRC(SCCGWA, LNB5300_AWA_5300.CI_CNM);
        IBS.init(SCCGWA, LNCSFUND);
        LNCSFUND.FUNC = 'B';
        if (LNB5300_AWA_5300.PAGE_ROW == 0) {
            if ("15".trim().length() == 0) LNCSFUND.PAGE_ROW = 0;
            else LNCSFUND.PAGE_ROW = Integer.parseInt("15");
        } else {
            if (LNB5300_AWA_5300.PAGE_ROW > 15) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSFUND.PAGE_ROW = LNB5300_AWA_5300.PAGE_ROW;
            }
        }
        if (LNB5300_AWA_5300.PAGE_NUM == 0) {
            if ("0".trim().length() == 0) LNCSFUND.PAGE_NUM = 0;
            else LNCSFUND.PAGE_NUM = Integer.parseInt("0");
        } else {
            LNCSFUND.PAGE_NUM = LNB5300_AWA_5300.PAGE_NUM;
        }
        LNCSFUND.FUNC = LNB5300_AWA_5300.FUN_CD;
        LNCSFUND.DATA.KEY.PROJ_NO = LNB5300_AWA_5300.PROJ_NO;
        LNCSFUND.PROJ_NM = LNB5300_AWA_5300.PROJ_NM;
        LNCSFUND.CI_NO = LNB5300_AWA_5300.CI_NO;
        LNCSFUND.CI_CNM = LNB5300_AWA_5300.CI_CNM;
        LNCSFUND.BOOK_BR = LNB5300_AWA_5300.MBANK_BR;
        LNCSFUND.FUND_AC = LNB5300_AWA_5300.FUND_AC;
        LNCSFUND.P_AC = LNB5300_AWA_5300.P_AC;
        LNCSFUND.I_AC = LNB5300_AWA_5300.I_AC;
        LNCSFUND.PAY_FLG = LNB5300_AWA_5300.PA_MIDFG;
        LNCSFUND.TRANS_FREQ = LNB5300_AWA_5300.PA_MIDTP;
        LNCSFUND.SMR = LNB5300_AWA_5300.SMR;
        S000_CALL_LNZSFUND();
    }
    public void S000_CALL_LNZSFUND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-FUND-MAIN", LNCSFUND);
        if (LNCSFUND.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + LNCSFUND.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
