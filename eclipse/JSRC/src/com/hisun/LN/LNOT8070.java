package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8070 {
    String JIBS_tmp_str[] = new String[10];
    LNOT8070_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT8070_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCBEXT LNCBEXT = new LNCBEXT();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    SCCGWA SCCGWA;
    LNB8070_AWA_8070 LNB8070_AWA_8070;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT8070 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8070_AWA_8070>");
        LNB8070_AWA_8070 = (LNB8070_AWA_8070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8070_AWA_8070.LOAN_AC);
        if (LNB8070_AWA_8070.LOAN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNB8070_AWA_8070.PAGE_ROW);
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBEXT);
        LNCBEXT.CTA_NO = LNB8070_AWA_8070.LOAN_AC;
        if (LNB8070_AWA_8070.PAGE_ROW != 0 
            && LNB8070_AWA_8070.PAGE_ROW != 0) {
            LNCBEXT.PAGE_ROW = LNB8070_AWA_8070.PAGE_ROW;
        } else {
            LNCBEXT.PAGE_ROW = 25;
        }
        if (LNB8070_AWA_8070.PAGE_NUM != 0 
            && LNB8070_AWA_8070.PAGE_NUM != 0) {
            LNCBEXT.PAGE_NUM = LNB8070_AWA_8070.PAGE_NUM;
        } else {
            LNCBEXT.PAGE_NUM = 1;
        }
        S000_CALL_LNZSBEXT();
    }
    public void S000_CALL_LNZSBEXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-BRW-SEXT", LNCBEXT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
