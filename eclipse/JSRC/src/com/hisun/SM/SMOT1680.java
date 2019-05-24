package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1680 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMOT1680_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1680_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP18 BPCXP18 = new BPCXP18();
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
    BPRSMG BPRSMG = new BPRSMG();
    SCCGWA SCCGWA;
    SMB1600_AWA_1600 SMB1600_AWA_1600;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1680 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1600_AWA_1600>");
        SMB1600_AWA_1600 = (SMB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.RC.RC_APP = "SM";
        BPCPRMM.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1600_AWA_1600.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        BPCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        BPRPRMT.DESC = SMB1600_AWA_1600.DESC;
        BPRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRSMG.DATA_TXT);
        BPRSMG.DATA_TXT.SMG_TEXT = SMB1600_AWA_1600.SMG_TEXT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSMG.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        BPCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        BPRPRMT.DESC = SMB1600_AWA_1600.DESC;
        BPRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRSMG.DATA_TXT);
        BPRSMG.DATA_TXT.SMG_TEXT = SMB1600_AWA_1600.SMG_TEXT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSMG.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP18);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP18.FUNC = SMB1600_AWA_1600.FUNC;
        BPCXP18.TYPE = BPRPRMT.KEY.TYP;
        BPCXP18.CODE = BPRPRMT.KEY.CD;
        BPCXP18.EFF_DATE = BPCPRMM.EFF_DT;
        BPCXP18.EXP_DATE = BPCPRMM.EXP_DT;
        BPCXP18.DESC = BPRPRMT.DESC;
        BPCXP18.CDESC = BPRPRMT.CDESC;
        BPCXP18.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRSMG.DATA_TXT);
        BPCXP18.SMG_TEXT = BPRSMG.DATA_TXT.SMG_TEXT;
        SCCFMT.FMTID = "BPP18";
        SCCFMT.DATA_PTR = BPCXP18;
        SCCFMT.DATA_LEN = 235;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
