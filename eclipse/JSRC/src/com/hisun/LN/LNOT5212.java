package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5212 {
    String WS_ERR_MSG = " ";
    LNOT5212_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT5212_WS_TEMP_VARIABLE();
    LNOT5212_WS_MSG_INFO WS_MSG_INFO = new LNOT5212_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCSBRWC LNCSBRWC = new LNCSBRWC();
    LNRLOAN LNRLOAN = new LNRLOAN();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT5212 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB5210_AWA_5210.CTA_NO.compareTo(SPACE) <= 0) {
            LNB5210_AWA_5210.CTA_NO = " ";
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSBRWC);
        LNCSBRWC.CTA_NO = LNB5210_AWA_5210.CTA_NO;
        S000_CALL_LNZSBRWC();
    }
    public void S000_CALL_LNZSBRWC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-BRW-CONTRACT", LNCSBRWC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
