package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8060 {
    String JIBS_tmp_str[] = new String[10];
    LNOT8060_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT8060_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCSICOT LNCSICOT = new LNCSICOT();
    SCCGWA SCCGWA;
    LNB8060_AWA_8060 LNB8060_AWA_8060;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT8060 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8060_AWA_8060>");
        LNB8060_AWA_8060 = (LNB8060_AWA_8060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSICOT);
        CEP.TRC(SCCGWA, LNB8060_AWA_8060.DD_AC);
        CEP.TRC(SCCGWA, LNB8060_AWA_8060.PAGE_ROW);
        CEP.TRC(SCCGWA, LNB8060_AWA_8060.PAGE_NUM);
        LNCSICOT.FUN = 'N';
        LNCSICOT.DD_AC = LNB8060_AWA_8060.DD_AC;
        LNCSICOT.PAGE_ROW = LNB8060_AWA_8060.PAGE_ROW;
        LNCSICOT.PAGE_NUM = LNB8060_AWA_8060.PAGE_NUM;
        CEP.TRC(SCCGWA, LNCSICOT.DD_AC);
        CEP.TRC(SCCGWA, LNCSICOT.PAGE_ROW);
        CEP.TRC(SCCGWA, LNCSICOT.PAGE_NUM);
        S000_CALL_LNZSICOT();
    }
    public void S000_CALL_LNZSICOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-I-COT", LNCSICOT);
        if (LNCSICOT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSICOT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
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
