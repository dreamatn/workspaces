package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7070 {
    String JIBS_tmp_str[] = new String[10];
    LNOT7070_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7070_WS_TEMP_VARIABLE();
    LNCXP07 LNCXP07 = new LNCXP07();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCBKCTM LNCBKCTM = new LNCBKCTM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    LNB7070_AWA_7070 LNB7070_AWA_7070;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7070 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7070_AWA_7070>");
        LNB7070_AWA_7070 = (LNB7070_AWA_7070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, LNCBKCTM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMM.RC.RC_APP = "LN";
        BPCPRMR.RC.RC_APP = "LN";
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMR.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_TRANS_DATA();
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        if (LNB7070_AWA_7070.FUNC != 'A') {
            BPRPRMT.KEY.TYP = LNB7070_AWA_7070.PTYP;
            BPRPRMT.KEY.CD = LNB7070_AWA_7070.CODE;
            S000_CALL_BPZPRMR();
            B100_OUTPUT_PROCESS();
        } else {
            IBS.init(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.init(SCCGWA, LNCBKCTM.DATA_TXT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBKCTM.DATA_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            B100_OUTPUT_PROCESS();
        }
    }
    public void B100_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXP07);
        IBS.init(SCCGWA, SCCFMT);
        LNCXP07.FUNC = LNB7070_AWA_7070.FUNC;
        LNCXP07.TYPE = LNB7070_AWA_7070.PTYP;
        LNCXP07.CODE = LNB7070_AWA_7070.CODE;
        LNCXP07.EFF_DATE = LNB7070_AWA_7070.EFFDATE;
        LNCXP07.EXP_DATE = LNB7070_AWA_7070.EXPDATE;
        LNCXP07.DESC = LNB7070_AWA_7070.DESC;
        LNCXP07.CDESC = LNB7070_AWA_7070.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBKCTM.DATA_TXT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = LNCXP07;
        SCCFMT.DATA_LEN = 299;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
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
