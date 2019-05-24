package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5457 {
    String CPN_REC_UHIS = "CI-REC-UHIS         ";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICUHIS CICUHIS = new CICUHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5450_AWA_5450 CIB5450_AWA_5450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5457 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5450_AWA_5450>");
        CIB5450_AWA_5450 = (CIB5450_AWA_5450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICUHIS);
        CICUHIS.FUNC = 'Q';
        CICUHIS.QUERY_OPT = '7';
        CICUHIS.KEY.FMT_NO = "CIRAGT  ";
        CICUHIS.KEY.AC_DATE = CIB5450_AWA_5450.AC_DATE;
        CICUHIS.KEY.JRN_NO = CIB5450_AWA_5450.JRN_NO;
        CICUHIS.CI_STS = CIB5450_AWA_5450.CI_STS;
        CICUHIS.PRI_CI = CIB5450_AWA_5450.PRI_CI;
        CICUHIS.SEQ = CIB5450_AWA_5450.SEQ;
        S000_CALL_CIZUHIS();
    }
    public void S000_CALL_CIZUHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-REC-UHIS", CICUHIS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
