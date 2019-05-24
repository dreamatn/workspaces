package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7510 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    LNOT7510_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7510_WS_TEMP_VARIABLE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCICAL LNCICAL = new LNCICAL();
    LNCRCAL LNCRCAL = new LNCRCAL();
    SCCGWA SCCGWA;
    LNB7510_AWA_7510 LNB7510_AWA_7510;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7510 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7510_AWA_7510>");
        LNB7510_AWA_7510 = (LNB7510_AWA_7510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, LNB7510_AWA_7510.LOAN_AC);
        CEP.TRC(SCCGWA, LNB7510_AWA_7510.VAL_DTE);
        CEP.TRC(SCCGWA, LNB7510_AWA_7510.ICAL_ID);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (LNB7510_AWA_7510.ICAL_ID == 'R') {
            B200_MAIN_RCAL_PROCESS();
        } else {
            B200_MAIN_ICAL_PROCESS();
        }
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R00_CHECK_ERROR();
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_ICAL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNB7510_AWA_7510.LOAN_AC;
        LNCICAL.COMM_DATA.SUF_NO = "" + LNB7510_AWA_7510.SUFFIX;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = LNB7510_AWA_7510.VAL_DTE;
        if (LNB7510_AWA_7510.ADJUST == 'Y') {
            S000_CALL_LNZICAL_BASE();
        } else {
            S000_CALL_LNZICAL();
        }
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.PRI_AMT);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.INT_AMT);
        LNB7510_AWA_7510.PRIN_AMT = LNCICAL.COMM_DATA.PRI_AMT;
        LNB7510_AWA_7510.INT_AMT = LNCICAL.COMM_DATA.INT_AMT;
    }
    public void B200_MAIN_RCAL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCAL);
        LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
        LNCRCAL.COMM_DATA.LN_AC = LNB7510_AWA_7510.LOAN_AC;
        LNCRCAL.COMM_DATA.SUF_NO = "" + LNB7510_AWA_7510.SUFFIX;
        JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
        LNCRCAL.COMM_DATA.VAL_DATE = LNB7510_AWA_7510.VAL_DTE;
        if (LNB7510_AWA_7510.ADJUST == 'Y') {
            S000_CALL_LNZRCAL_BASE();
        } else {
            S000_CALL_LNZRCAL();
        }
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.PRI_AMT);
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.INT_AMT);
        LNB7510_AWA_7510.PRIN_AMT = LNCRCAL.COMM_DATA.PRI_AMT;
        LNB7510_AWA_7510.INT_AMT = LNCRCAL.COMM_DATA.INT_AMT;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZICAL_BASE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL-BASE", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCICAL.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCICAL.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCICAL.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCICAL.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRCAL_BASE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCRCAL.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCRCAL.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCRCAL.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCRCAL.RC.RC_RTNCODE;
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
