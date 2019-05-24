package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7867 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    LNOT7867_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7867_WS_TEMP_VARIABLE();
    int WS_I = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    LNCXP44 LNCXP44 = new LNCXP44();
    LNCPSHQ LNCPSHQ = new LNCPSHQ();
    SCCGWA SCCGWA;
    LNB7810_AWA_7810 LNB7810_AWA_7810;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7867 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7810_AWA_7810>");
        LNB7810_AWA_7810 = (LNB7810_AWA_7810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.AC);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.SUFFIX);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.PAY_TYP);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.START_TE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.STRIN_TE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TOTAL_TE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_INQUIRE_PROC();
        B300_TRANS_DATA();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPSHQ);
        LNCPSHQ.COMM_DATA.CONTRACT_NO = LNB7810_AWA_7810.AC;
        LNCPSHQ.COMM_DATA.SUB_CTA_NO = LNB7810_AWA_7810.SUFFIX;
        LNCPSHQ.COMM_DATA.PAY_TYP = LNB7810_AWA_7810.PAY_TYP;
        LNCPSHQ.COMM_DATA.START_TE = LNB7810_AWA_7810.START_TE;
        LNCPSHQ.COMM_DATA.STRIN_TE = LNB7810_AWA_7810.STRIN_TE;
        LNCPSHQ.COMM_DATA.TOTAL_TE = LNB7810_AWA_7810.TOTAL_TE;
        S000_CALL_LNZPSHQ();
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXP44);
        IBS.init(SCCGWA, SCCFMT);
        LNCXP44.AC = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        LNCXP44.SUFFIX = "" + LNCPSHQ.COMM_DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCXP44.SUFFIX.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCXP44.SUFFIX = "0" + LNCXP44.SUFFIX;
        LNCXP44.PAY_TYP = LNCPSHQ.COMM_DATA.PAY_TYP;
        LNCXP44.START_TE = LNCPSHQ.COMM_DATA.START_TE;
        LNCXP44.STRIN_TE = LNCPSHQ.COMM_DATA.STRIN_TE;
        LNCXP44.TOTAL_TE = LNCPSHQ.COMM_DATA.TOTAL_TE;
        for (WS_I = 1; WS_I <= LNCPSHQ.COMM_DATA.TOTAL_TE 
            && WS_I <= 20; WS_I += 1) {
            LNCXP44.TERMINFO[WS_I-1].TERMNO = LNCPSHQ.COMM_DATA.TERMINFO[WS_I-1].TERMNO;
            LNCXP44.TERMINFO[WS_I-1].TERM_DTE = LNCPSHQ.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
            LNCXP44.TERMINFO[WS_I-1].TERM_AMT = LNCPSHQ.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT;
        }
        SCCFMT.FMTID = "LNP44";
        SCCFMT.DATA_PTR = LNCXP44;
        SCCFMT.DATA_LEN = 637;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZPSHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PSCH-INQUIRY", LNCPSHQ);
        if (LNCPSHQ.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCPSHQ.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCPSHQ.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
