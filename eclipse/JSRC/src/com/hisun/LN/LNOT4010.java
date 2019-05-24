package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4010 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CTL_STSW = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCICSTS LNCICSTS = new LNCICSTS();
    SCCGWA SCCGWA;
    LNB4010_AWA_4010 LNB4010_AWA_4010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT4010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4010_AWA_4010>");
        LNB4010_AWA_4010 = (LNB4010_AWA_4010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_PASTDUE_ENQUIRY();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB4010_AWA_4010.CTA_NO);
        CEP.TRC(SCCGWA, LNB4010_AWA_4010.TR_VALDT);
        CEP.TRC(SCCGWA, LNB4010_AWA_4010.BK_CINO);
        if (LNB4010_AWA_4010.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4010_AWA_4010.CTA_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
        S000_ERR_MSG_PROC_LAST();
        IBS.init(SCCGWA, LNCICSTS);
        LNCICSTS.COMM_DATA.CONTRACT_NO = LNB4010_AWA_4010.CTA_NO;
        S000_CALL_LNZICSTS();
        WS_CTL_STSW = LNCICSTS.COMM_DATA.CTL_STSW;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_PASTDUE_ENQUIRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNB4010_AWA_4010.CTA_NO;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = LNB4010_AWA_4010.TR_VALDT;
        LNCSPDQ.COMM_DATA.PARTI_INFO.PARTI_CINO = LNB4010_AWA_4010.BK_CINO;
        LNCSPDQ.COMM_DATA.CTA_INFO.PD_DESC = LNB4010_AWA_4010.PD_DESC;
        LNCSPDQ.COMM_DATA.PAGE_ROW = LNB4010_AWA_4010.PAGE_ROW;
        LNCSPDQ.COMM_DATA.PAGE_NUM = LNB4010_AWA_4010.PAGE_NUM;
        LNCSPDQ.ERR_FLG = LNB4010_AWA_4010.ERR_FLG;
        S000_CALL_LNZSPDQ();
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_AMT);
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZICSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-ICTL-STS", LNCICSTS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
