package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1720 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1720_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1720_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    char WS_ERR_FLG = 'N';
    BPCXP21 BPCXP21 = new BPCXP21();
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
    BPRCPN BPRCPN = new BPRCPN();
    SCCGWA SCCGWA;
    SMB1720_AWA_1720 SMB1720_AWA_1720;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT BPRTLT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1720 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1720_AWA_1720>");
        SMB1720_AWA_1720 = (SMB1720_AWA_1720) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SMB1720_AWA_1720.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1720_AWA_1720.FUNC == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1720_AWA_1720.FUNC == 'M') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1720_AWA_1720.FUNC == 'D') {
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
        if (SMB1720_AWA_1720.CODE == null) SMB1720_AWA_1720.CODE = "";
        JIBS_tmp_int = SMB1720_AWA_1720.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1720_AWA_1720.CODE += " ";
        if (!SMB1720_AWA_1720.CODE.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("-")) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_CPN_NAME_INVALID, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1720_AWA_1720.CODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1720_AWA_1720.CLASS != '1' 
            && SMB1720_AWA_1720.CLASS != '2' 
            && SMB1720_AWA_1720.CLASS != '3' 
            && SMB1720_AWA_1720.CLASS != '4' 
            && SMB1720_AWA_1720.CLASS != '5') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1720_AWA_1720.CLASS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1720_AWA_1720.FUN_TYPE != '1' 
            && SMB1720_AWA_1720.FUN_TYPE != '2' 
            && SMB1720_AWA_1720.FUN_TYPE != '3') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1720_AWA_1720.FUN_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1720_AWA_1720.STATUS != 'Y' 
            && SMB1720_AWA_1720.STATUS != 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1720_AWA_1720.STATUS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_FLG = 'N';
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 32 
            && WS_ERR_FLG != 'Y'; WS_TEMP_VARIABLE.WS_I += 1) {
            if (SMB1720_AWA_1720.CNTRL_WO == null) SMB1720_AWA_1720.CNTRL_WO = "";
            JIBS_tmp_int = SMB1720_AWA_1720.CNTRL_WO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SMB1720_AWA_1720.CNTRL_WO += " ";
            if (!SMB1720_AWA_1720.CNTRL_WO.substring(WS_TEMP_VARIABLE.WS_I - 1, WS_TEMP_VARIABLE.WS_I + 1 - 1).equalsIgnoreCase("0") 
                && !SMB1720_AWA_1720.CNTRL_WO.substring(WS_TEMP_VARIABLE.WS_I - 1, WS_TEMP_VARIABLE.WS_I + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_CTL_WORD_INVALID, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = SMB1720_AWA_1720.CNTRL_WO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
                WS_ERR_FLG = 'Y';
            }
        }
        R00_CHECK_ERROR();
        if (pgmRtn) return;
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1720_AWA_1720.PTYP;
        SCRPRMT.KEY.CD = SMB1720_AWA_1720.CODE;
        SCCPRMM.EFF_DT = SMB1720_AWA_1720.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1720_AWA_1720.PTYP;
        SCRPRMT.KEY.CD = SMB1720_AWA_1720.CODE;
        SCCPRMM.EFF_DT = SMB1720_AWA_1720.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1720_AWA_1720.PTYP;
        SCRPRMT.KEY.CD = SMB1720_AWA_1720.CODE;
        SCCPRMM.EFF_DT = SMB1720_AWA_1720.EFFDATE;
        SCCPRMM.EXP_DT = SMB1720_AWA_1720.EXPDATE;
        SCRPRMT.DESC = SMB1720_AWA_1720.DESC;
        SCRPRMT.CDESC = SMB1720_AWA_1720.CDESC;
        IBS.init(SCCGWA, BPRCPN.DATA_TXT);
        BPRCPN.DATA_TXT.PGM_NAME = SMB1720_AWA_1720.PGM_NAME;
        BPRCPN.DATA_TXT.CLASS = SMB1720_AWA_1720.CLASS;
        BPRCPN.DATA_TXT.FUN_TYPE = SMB1720_AWA_1720.FUN_TYPE;
        BPRCPN.DATA_TXT.CNTRL_WORD = SMB1720_AWA_1720.CNTRL_WO;
        BPRCPN.DATA_TXT.STATUS = SMB1720_AWA_1720.STATUS;
        BPRCPN.DATA_TXT.CREUSR = BPRTLT.KEY.TLR;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCPN.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB1720_AWA_1720.PTYP;
        SCRPRMT.KEY.CD = SMB1720_AWA_1720.CODE;
        SCCPRMM.EFF_DT = SMB1720_AWA_1720.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB1720_AWA_1720.PTYP;
        SCRPRMT.KEY.CD = SMB1720_AWA_1720.CODE;
        SCCPRMM.EFF_DT = SMB1720_AWA_1720.EFFDATE;
        SCCPRMM.EXP_DT = SMB1720_AWA_1720.EXPDATE;
        SCRPRMT.DESC = SMB1720_AWA_1720.DESC;
        SCRPRMT.CDESC = SMB1720_AWA_1720.CDESC;
        IBS.init(SCCGWA, BPRCPN.DATA_TXT);
        BPRCPN.DATA_TXT.PGM_NAME = SMB1720_AWA_1720.PGM_NAME;
        BPRCPN.DATA_TXT.CLASS = SMB1720_AWA_1720.CLASS;
        BPRCPN.DATA_TXT.FUN_TYPE = SMB1720_AWA_1720.FUN_TYPE;
        BPRCPN.DATA_TXT.CNTRL_WORD = SMB1720_AWA_1720.CNTRL_WO;
        BPRCPN.DATA_TXT.STATUS = SMB1720_AWA_1720.STATUS;
        BPRCPN.DATA_TXT.CREUSR = BPRTLT.KEY.TLR;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCPN.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, SCRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, SCRPRMT.KEY.CD);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP21);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP21.FUNC = SMB1720_AWA_1720.FUNC;
        BPCXP21.TYPE = SCRPRMT.KEY.TYP;
        BPCXP21.CODE = SCRPRMT.KEY.CD;
        BPCXP21.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP21.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP21.DESC = SCRPRMT.DESC;
        BPCXP21.CDESC = SCRPRMT.CDESC;
        BPCXP21.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCPN.DATA_TXT);
        BPCXP21.PGM_NAME = BPRCPN.DATA_TXT.PGM_NAME;
        BPCXP21.CLASS = BPRCPN.DATA_TXT.CLASS;
        BPCXP21.FUN_TYPE = BPRCPN.DATA_TXT.FUN_TYPE;
        BPCXP21.CNTRL_WORD = BPRCPN.DATA_TXT.CNTRL_WORD;
        BPCXP21.STATUS = BPRCPN.DATA_TXT.STATUS;
        BPCXP21.CREUSR = BPRCPN.DATA_TXT.CREUSR;
        SCCFMT.FMTID = "BPP21";
        SCCFMT.DATA_PTR = BPCXP21;
        SCCFMT.DATA_LEN = 198;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCPRMM);
        if (SCCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = SCCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCPRMM.RC.RC_RTNCODE;
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
