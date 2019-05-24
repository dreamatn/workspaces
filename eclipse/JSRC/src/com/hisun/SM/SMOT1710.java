package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1710 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMOT1710_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1710_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP20 BPCXP20 = new BPCXP20();
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
    BPRJRN0 BPRJRN0 = new BPRJRN0();
    SCCGWA SCCGWA;
    SMB1710_AWA_1710 SMB1710_AWA_1710;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1710 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1710_AWA_1710>");
        SMB1710_AWA_1710 = (SMB1710_AWA_1710) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (SMB1710_AWA_1710.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1710_AWA_1710.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1710_AWA_1710.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1710_AWA_1710.FUNC == 'D') {
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
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if ((!SMB1710_AWA_1710.CODE.substring(0, 1).equalsIgnoreCase("1") 
            && !SMB1710_AWA_1710.CODE.substring(0, 1).equalsIgnoreCase("2"))) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1710_AWA_1710.CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SMB1710_AWA_1710.BAT_STUS != 'B' 
            && SMB1710_AWA_1710.BAT_STUS != 'C' 
            && SMB1710_AWA_1710.BAT_STUS != 'O')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1710_AWA_1710.BAT_STUS_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = SMB1710_AWA_1710.PTYP;
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = SMB1710_AWA_1710.CODE.substring(0, 1) + BPRPRMT.KEY.CD.substring(1);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = " ";
        for (int i=1;i<" ".length();i++) JIBS_tmp_str[0] += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(2 + " ".length() - 1);
        BPCPRMM.EFF_DT = SMB1710_AWA_1710.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = SMB1710_AWA_1710.PTYP;
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = SMB1710_AWA_1710.CODE.substring(0, 1) + BPRPRMT.KEY.CD.substring(1);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = " ";
        for (int i=1;i<" ".length();i++) JIBS_tmp_str[0] += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(2 + " ".length() - 1);
        BPCPRMM.EFF_DT = SMB1710_AWA_1710.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = SMB1710_AWA_1710.PTYP;
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = SMB1710_AWA_1710.CODE.substring(0, 1) + BPRPRMT.KEY.CD.substring(1);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = " ";
        for (int i=1;i<" ".length();i++) JIBS_tmp_str[0] += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(2 + " ".length() - 1);
        BPCPRMM.EFF_DT = SMB1710_AWA_1710.EFFDATE;
        BPCPRMM.EXP_DT = SMB1710_AWA_1710.EXPDATE;
        BPRPRMT.DESC = SMB1710_AWA_1710.DESC;
        BPRPRMT.CDESC = SMB1710_AWA_1710.CDESC;
        IBS.init(SCCGWA, BPRJRN0.DATA_TXT);
        BPRJRN0.DATA_TXT.FILE_IND = SMB1710_AWA_1710.FILE_IND;
        BPRJRN0.DATA_TXT.JRN_NO = SMB1710_AWA_1710.JRN_NO;
        BPRJRN0.DATA_TXT.REEN_JRN_NO = SMB1710_AWA_1710.REEN_JRN;
        BPRJRN0.DATA_TXT.NEXT_JRN_NO = SMB1710_AWA_1710.NEXT_JRN;
        BPRJRN0.DATA_TXT.MAX_JRN_NO = SMB1710_AWA_1710.MAX_JRN0;
        BPRJRN0.DATA_TXT.WARN_JRN_NO = SMB1710_AWA_1710.WARN_JRN;
        BPRJRN0.DATA_TXT.BAT_STUS = SMB1710_AWA_1710.BAT_STUS;
        BPRJRN0.DATA_TXT.AC_DATE = SMB1710_AWA_1710.AC_DATE;
        BPRJRN0.DATA_TXT.REEN_DATE = SMB1710_AWA_1710.REN_DATE;
        BPRJRN0.DATA_TXT.REEN_TIME = SMB1710_AWA_1710.REN_TIME;
        BPRJRN0.DATA_TXT.CUT_OFF_DATE = SMB1710_AWA_1710.OFF_DATE;
        BPRJRN0.DATA_TXT.CUT_OFF_TIME = SMB1710_AWA_1710.OFF_TIME;
        BPRJRN0.DATA_TXT.BAT_DATE = SMB1710_AWA_1710.BAT_DATE;
        BPRJRN0.DATA_TXT.BAT_TIME = SMB1710_AWA_1710.BAT_TIME;
        BPRJRN0.DATA_TXT.REEN_TASK_NO = SMB1710_AWA_1710.REEN_TAS;
        BPRJRN0.DATA_TXT.USER_BAT_STUS = SMB1710_AWA_1710.USER_BAT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRJRN0.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = SMB1710_AWA_1710.PTYP;
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = SMB1710_AWA_1710.CODE.substring(0, 1) + BPRPRMT.KEY.CD.substring(1);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = " ";
        for (int i=1;i<" ".length();i++) JIBS_tmp_str[0] += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(2 + " ".length() - 1);
        BPCPRMM.EFF_DT = SMB1710_AWA_1710.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = SMB1710_AWA_1710.PTYP;
        if (SMB1710_AWA_1710.CODE == null) SMB1710_AWA_1710.CODE = "";
        JIBS_tmp_int = SMB1710_AWA_1710.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1710_AWA_1710.CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = SMB1710_AWA_1710.CODE.substring(0, 1) + BPRPRMT.KEY.CD.substring(1);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = " ";
        for (int i=1;i<" ".length();i++) JIBS_tmp_str[0] += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(2 + " ".length() - 1);
        BPCPRMM.EFF_DT = SMB1710_AWA_1710.EFFDATE;
        BPCPRMM.EXP_DT = SMB1710_AWA_1710.EXPDATE;
        BPRPRMT.DESC = SMB1710_AWA_1710.DESC;
        BPRPRMT.CDESC = SMB1710_AWA_1710.CDESC;
        IBS.init(SCCGWA, BPRJRN0.DATA_TXT);
        BPRJRN0.DATA_TXT.FILE_IND = SMB1710_AWA_1710.FILE_IND;
        BPRJRN0.DATA_TXT.JRN_NO = SMB1710_AWA_1710.JRN_NO;
        BPRJRN0.DATA_TXT.REEN_JRN_NO = SMB1710_AWA_1710.REEN_JRN;
        BPRJRN0.DATA_TXT.NEXT_JRN_NO = SMB1710_AWA_1710.NEXT_JRN;
        BPRJRN0.DATA_TXT.MAX_JRN_NO = SMB1710_AWA_1710.MAX_JRN0;
        BPRJRN0.DATA_TXT.WARN_JRN_NO = SMB1710_AWA_1710.WARN_JRN;
        BPRJRN0.DATA_TXT.BAT_STUS = SMB1710_AWA_1710.BAT_STUS;
        BPRJRN0.DATA_TXT.AC_DATE = SMB1710_AWA_1710.AC_DATE;
        BPRJRN0.DATA_TXT.REEN_DATE = SMB1710_AWA_1710.REN_DATE;
        BPRJRN0.DATA_TXT.REEN_TIME = SMB1710_AWA_1710.REN_TIME;
        BPRJRN0.DATA_TXT.CUT_OFF_DATE = SMB1710_AWA_1710.OFF_DATE;
        BPRJRN0.DATA_TXT.CUT_OFF_TIME = SMB1710_AWA_1710.OFF_TIME;
        BPRJRN0.DATA_TXT.BAT_DATE = SMB1710_AWA_1710.BAT_DATE;
        BPRJRN0.DATA_TXT.BAT_TIME = SMB1710_AWA_1710.BAT_TIME;
        BPRJRN0.DATA_TXT.REEN_TASK_NO = SMB1710_AWA_1710.REEN_TAS;
        BPRJRN0.DATA_TXT.USER_BAT_STUS = SMB1710_AWA_1710.USER_BAT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRJRN0.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP20);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP20.FUNC = SMB1710_AWA_1710.FUNC;
        BPCXP20.TYPE = BPRPRMT.KEY.TYP;
        BPCXP20.CODE = BPRPRMT.KEY.CD;
        BPCXP20.EFF_DATE = BPCPRMM.EFF_DT;
        BPCXP20.EXP_DATE = BPCPRMM.EXP_DT;
        BPCXP20.DESC = BPRPRMT.DESC;
        BPCXP20.CDESC = BPRPRMT.CDESC;
        BPCXP20.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRJRN0.DATA_TXT);
        BPCXP20.FILE_IND = BPRJRN0.DATA_TXT.FILE_IND;
        BPCXP20.JRN_NO = BPRJRN0.DATA_TXT.JRN_NO;
        BPCXP20.REEN_JRN_NO = BPRJRN0.DATA_TXT.REEN_JRN_NO;
        BPCXP20.NEXT_JRN_NO = BPRJRN0.DATA_TXT.NEXT_JRN_NO;
        BPCXP20.MAX_JRN_NO = BPRJRN0.DATA_TXT.MAX_JRN_NO;
        BPCXP20.WARN_JRN_NO = BPRJRN0.DATA_TXT.WARN_JRN_NO;
        BPCXP20.BAT_STUS = BPRJRN0.DATA_TXT.BAT_STUS;
        BPCXP20.AC_DATE = BPRJRN0.DATA_TXT.AC_DATE;
        BPCXP20.REEN_DATE = BPRJRN0.DATA_TXT.REEN_DATE;
        BPCXP20.REEN_TIME = BPRJRN0.DATA_TXT.REEN_TIME;
        BPCXP20.CUT_OFF_DATE = BPRJRN0.DATA_TXT.CUT_OFF_DATE;
        BPCXP20.CUT_OFF_TIME = BPRJRN0.DATA_TXT.CUT_OFF_TIME;
        BPCXP20.BAT_DATE = BPRJRN0.DATA_TXT.BAT_DATE;
        BPCXP20.BAT_TIME = BPRJRN0.DATA_TXT.BAT_TIME;
        BPCXP20.REEN_TASK_NO = BPRJRN0.DATA_TXT.REEN_TASK_NO;
        BPCXP20.USER_BAT_STUS = BPRJRN0.DATA_TXT.USER_BAT_STUS;
        SCCFMT.FMTID = "BPP20";
        SCCFMT.DATA_PTR = BPCXP20;
        SCCFMT.DATA_LEN = 317;
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
