package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1804 {
    String JIBS_tmp_str[] = new String[10];
    SMOT1804_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1804_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP30 BPCXP30 = new BPCXP30();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    SMB1804_AWA_1804 SMB1804_AWA_1804;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SMOT1804 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1804_AWA_1804>");
        SMB1804_AWA_1804 = (SMB1804_AWA_1804) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
        CEP.TRC(SCCGWA, SMB1804_AWA_1804);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READO_PROCESS();
        WS_TEMP_VARIABLE.WS_TXT = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        B100_READN_PROCESS();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            B100_WRITE_PROCESS();
        } else {
            if (SMB1804_AWA_1804.CPY == 'Y') {
                B100_READU_PROCESS();
                B100_REWRITE_PROCESS();
            } else {
                IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_RECORD_EXIST, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B100_READO_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.EFFDATE;
        S000_CALL_SCZPRMM();
    }
    public void B100_READN_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.NEW_PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.NEW_CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.NEW_EFFD;
        S000_CALL_SCZPRMM();
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.NEW_PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.NEW_CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.NEW_EFFD;
        S000_CALL_SCZPRMM();
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.NEW_PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.NEW_CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.NEW_EFFD;
        SCCPRMM.EXP_DT = SMB1804_AWA_1804.NEW_EXPD;
        SCRPRMT.DESC = SMB1804_AWA_1804.NEW_DESC;
        SCRPRMT.CDESC = SMB1804_AWA_1804.NEW_CDES;
        IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_TXT, SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.EFFDATE;
        S000_CALL_SCZPRMM();
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB1804_AWA_1804.NEW_PTYP;
        SCRPRMT.KEY.CD = SMB1804_AWA_1804.NEW_CODE;
        SCCPRMM.EFF_DT = SMB1804_AWA_1804.NEW_EFFD;
        SCCPRMM.EXP_DT = SMB1804_AWA_1804.NEW_EXPD;
        SCRPRMT.DESC = SMB1804_AWA_1804.NEW_DESC;
        SCRPRMT.CDESC = SMB1804_AWA_1804.NEW_CDES;
        IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_TXT, SCRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, SMB1804_AWA_1804.PTYP);
        CEP.TRC(SCCGWA, SMB1804_AWA_1804.NEW_CODE);
        CEP.TRC(SCCGWA, SMB1804_AWA_1804.NEW_PTYP);
        S000_CALL_SCZPRMM();
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP30);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP30.FUNC = SMB1804_AWA_1804.FUNC;
        BPCXP30.TYPE = SCRPRMT.KEY.TYP;
        BPCXP30.CODE = SCRPRMT.KEY.CD;
        BPCXP30.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP30.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP30.DESC = SCRPRMT.DESC;
        BPCXP30.CDESC = SCRPRMT.CDESC;
        BPCXP30.AWAD_FLD_TXT = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCXP30;
        SCCFMT.DATA_LEN = 5161;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCXP30);
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCPRMM);
        CEP.TRC(SCCGWA, SCCPRMM);
        WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = SCCPRMM.RC.RC_APP;
        WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCPRMM.RC.RC_RTNCODE;
        if (((WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE != 0) 
            && (SCCPRMM.FUNC != '3'))) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
