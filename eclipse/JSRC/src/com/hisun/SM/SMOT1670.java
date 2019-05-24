package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1670 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1670_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1670_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP17 BPCXP17 = new BPCXP17();
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
    BPRAPT BPRAPT = new BPRAPT();
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
        CEP.TRC(SCCGWA, "SMOT1670 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1600_AWA_1600>");
        SMB1600_AWA_1600 = (SMB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SMB1600_AWA_1600.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'M') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
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
        CEP.TRC(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.STUS);
        if ((SMB1600_AWA_1600.STUS != 'Y' 
            && SMB1600_AWA_1600.STUS != 'N')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.STUS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.STR_EXIT);
        if ((SMB1600_AWA_1600.STR_EXIT != 'Y' 
            && SMB1600_AWA_1600.STR_EXIT != 'N')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.STR_EXIT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.DOWN_EXI);
        if ((SMB1600_AWA_1600.DOWN_EXI != 'Y' 
            && SMB1600_AWA_1600.DOWN_EXI != 'N')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.DOWN_EXI_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.REST_EXI);
        if ((SMB1600_AWA_1600.REST_EXI != 'Y' 
            && SMB1600_AWA_1600.REST_EXI != 'N')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.REST_EXI_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.RUN_MODE);
        if ((SMB1600_AWA_1600.RUN_MODE != 'D' 
            && SMB1600_AWA_1600.RUN_MODE != 'B' 
            && SMB1600_AWA_1600.RUN_MODE != 'R' 
            && SMB1600_AWA_1600.RUN_MODE != 'N' 
            && SMB1600_AWA_1600.RUN_MODE != 'A')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.RUN_MODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.RUN_HDAY);
        if ((SMB1600_AWA_1600.RUN_HDAY != 'Y' 
            && SMB1600_AWA_1600.RUN_HDAY != 'N')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.RUN_HDAY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
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
        SCRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        SCRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        SCCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        SCRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        SCCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        SCRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        SCCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        SCCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        SCRPRMT.DESC = SMB1600_AWA_1600.DESC;
        SCRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRAPT.DATA_TXT);
        BPRAPT.DATA_TXT.AP_NAME = SMB1600_AWA_1600.AP_NAME;
        BPRAPT.DATA_TXT.AP_MMO = SMB1600_AWA_1600.AP_MMO;
        BPRAPT.DATA_TXT.AP_EXT_MMO = SMB1600_AWA_1600.AP_EXT_M;
        BPRAPT.DATA_TXT.STUS = SMB1600_AWA_1600.STUS;
        BPRAPT.DATA_TXT.STR_EXIT = SMB1600_AWA_1600.STR_EXIT;
        BPRAPT.DATA_TXT.DOWN_EXIT = SMB1600_AWA_1600.DOWN_EXI;
        BPRAPT.DATA_TXT.REST_EXIT = SMB1600_AWA_1600.REST_EXI;
        BPRAPT.DATA_TXT.CLD_NO = SMB1600_AWA_1600.CLD_NO;
        BPRAPT.DATA_TXT.RUN_MODE = SMB1600_AWA_1600.RUN_MODE;
        BPRAPT.DATA_TXT.RUN_HDAY = SMB1600_AWA_1600.RUN_HDAY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.AP_NAME);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.AP_MMO);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.AP_EXT_M);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.STUS);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.STR_EXIT);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.DOWN_EXI);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.REST_EXI);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.CLD_NO);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.RUN_MODE);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.RUN_HDAY);
        CEP.TRC(SCCGWA, BPRAPT.DATA_TXT);
        CEP.TRC(SCCGWA, SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        SCRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        SCCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        SCRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        SCCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        SCCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        SCRPRMT.DESC = SMB1600_AWA_1600.DESC;
        SCRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRAPT.DATA_TXT);
        BPRAPT.DATA_TXT.AP_NAME = SMB1600_AWA_1600.AP_NAME;
        BPRAPT.DATA_TXT.AP_MMO = SMB1600_AWA_1600.AP_MMO;
        BPRAPT.DATA_TXT.AP_EXT_MMO = SMB1600_AWA_1600.AP_EXT_M;
        BPRAPT.DATA_TXT.STUS = SMB1600_AWA_1600.STUS;
        BPRAPT.DATA_TXT.STR_EXIT = SMB1600_AWA_1600.STR_EXIT;
        BPRAPT.DATA_TXT.DOWN_EXIT = SMB1600_AWA_1600.DOWN_EXI;
        BPRAPT.DATA_TXT.REST_EXIT = SMB1600_AWA_1600.REST_EXI;
        BPRAPT.DATA_TXT.CLD_NO = SMB1600_AWA_1600.CLD_NO;
        BPRAPT.DATA_TXT.RUN_MODE = SMB1600_AWA_1600.RUN_MODE;
        BPRAPT.DATA_TXT.RUN_HDAY = SMB1600_AWA_1600.RUN_HDAY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP17);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP17.FUNC = SMB1600_AWA_1600.FUNC;
        BPCXP17.TYPE = SCRPRMT.KEY.TYP;
        BPCXP17.CODE = SCRPRMT.KEY.CD;
        BPCXP17.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP17.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP17.DESC = SCRPRMT.DESC;
        BPCXP17.CDESC = SCRPRMT.CDESC;
        BPCXP17.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRAPT.DATA_TXT);
        BPCXP17.AP_NAME = BPRAPT.DATA_TXT.AP_NAME;
        BPCXP17.AP_MMO = BPRAPT.DATA_TXT.AP_MMO;
        BPCXP17.AP_EXT_MMO = BPRAPT.DATA_TXT.AP_EXT_MMO;
        BPCXP17.STUS = BPRAPT.DATA_TXT.STUS;
        BPCXP17.STR_EXIT = BPRAPT.DATA_TXT.STR_EXIT;
        BPCXP17.DOWN_EXIT = BPRAPT.DATA_TXT.DOWN_EXIT;
        BPCXP17.REST_EXIT = BPRAPT.DATA_TXT.REST_EXIT;
        BPCXP17.CLD_NO = BPRAPT.DATA_TXT.CLD_NO;
        BPCXP17.RUN_MODE = BPRAPT.DATA_TXT.RUN_MODE;
        BPCXP17.RUN_HDAY = BPRAPT.DATA_TXT.RUN_HDAY;
        SCCFMT.FMTID = "BPP17";
        SCCFMT.DATA_PTR = BPCXP17;
        SCCFMT.DATA_LEN = 199;
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
