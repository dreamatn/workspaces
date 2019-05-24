package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCXP28;
import com.hisun.BP.BPRTRT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCWOUT;
import com.hisun.SC.SCRPRMT;

public class SMOT1791 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1791_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1791_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP28 BPCXP28 = new BPCXP28();
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
    BPRTRT BPRTRT = new BPRTRT();
    SCCGWA SCCGWA;
    SMB1790_AWA_1790 SMB1790_AWA_1790;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1791 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1790_AWA_1790>");
        SMB1790_AWA_1790 = (SMB1790_AWA_1790) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1790_AWA_1790.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1790_AWA_1790.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1790_AWA_1790.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1790_AWA_1790.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, SMB1790_AWA_1790);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.STUS);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.RUN_MODE);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.REEN_IND);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.CLS);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.SELF_GRN);
        CEP.TRC(SCCGWA, SMB1790_AWA_1790.AUTH_LVL);
        if (SMB1790_AWA_1790.STUS != 'Y' 
            && SMB1790_AWA_1790.STUS != 'N') {
            CEP.TRC(SCCGWA, "WHY1");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.STUS_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-STUS IS " + SMB1790_AWA_1790.STUS + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.RUN_MODE != 'D' 
            && SMB1790_AWA_1790.RUN_MODE != 'A' 
            && SMB1790_AWA_1790.RUN_MODE != 'N' 
            && SMB1790_AWA_1790.RUN_MODE != 'R') {
            CEP.TRC(SCCGWA, "WHY2");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.RUN_MODE_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-STUS IS " + SMB1790_AWA_1790.RUN_MODE + "! BUT MUST BE " + "D OR A OR N OR R!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.REEN_IND != 'Y' 
            && SMB1790_AWA_1790.REEN_IND != 'N') {
            CEP.TRC(SCCGWA, "WHY3");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.REEN_IND_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-REEN-IND IS " + SMB1790_AWA_1790.REEN_IND + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1790_AWA_1790.CLS+"") 
            || SMB1790_AWA_1790.CLS > 24) {
            CEP.TRC(SCCGWA, "WHY4");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.CLS_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-CLS IS " + SMB1790_AWA_1790.CLS + "! BUT MUST BE NUMERIC " + "AND LESS THEN 24";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.SELF_GRN != 'Y' 
            && SMB1790_AWA_1790.SELF_GRN != 'N') {
            CEP.TRC(SCCGWA, "WHY5");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.SELF_GRN_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-SELF-GRN IS " + SMB1790_AWA_1790.SELF_GRN + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if ((SMB1790_AWA_1790.AUTH_LVL.compareTo("0") < 0 
            || SMB1790_AWA_1790.AUTH_LVL.compareTo("99") > 0)) {
            CEP.TRC(SCCGWA, "WHY6");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.AUTH_LVL_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-AUTH-LVL IS " + SMB1790_AWA_1790.AUTH_LVL + "! BUT MUST BETWEEN 0 AND 99 ";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
            JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
            if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
            JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
            if (SMB1790_AWA_1790.AUTH_LVL.substring(0, 1).compareTo(SMB1790_AWA_1790.AUTH_LVL.substring(2 - 1, 2 + 1 - 1)) < 0) {
                if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
                JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
                if (SMB1790_AWA_1790.AUTH_LVL.substring(0, 1).trim().length() == 0) WS_TEMP_VARIABLE.WS_AUTH_LVL = 0;
                else WS_TEMP_VARIABLE.WS_AUTH_LVL = Short.parseShort(SMB1790_AWA_1790.AUTH_LVL.substring(0, 1));
                if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
                JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
                if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
                JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
                SMB1790_AWA_1790.AUTH_LVL = SMB1790_AWA_1790.AUTH_LVL.substring(2 - 1, 2 + 1 - 1) + SMB1790_AWA_1790.AUTH_LVL.substring(1);
                if (SMB1790_AWA_1790.AUTH_LVL == null) SMB1790_AWA_1790.AUTH_LVL = "";
                JIBS_tmp_int = SMB1790_AWA_1790.AUTH_LVL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) SMB1790_AWA_1790.AUTH_LVL += " ";
                JIBS_tmp_str[0] = "" + WS_TEMP_VARIABLE.WS_AUTH_LVL;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                SMB1790_AWA_1790.AUTH_LVL = SMB1790_AWA_1790.AUTH_LVL.substring(0, 2 - 1) + JIBS_tmp_str[0] + SMB1790_AWA_1790.AUTH_LVL.substring(2 + 1 - 1);
            }
        }
        if (SMB1790_AWA_1790.LOG_IND != 'Y' 
            && SMB1790_AWA_1790.LOG_IND != 'N') {
            CEP.TRC(SCCGWA, "AAA");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.LOG_IND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.HIS_REC != 'Y' 
            && SMB1790_AWA_1790.HIS_REC != 'N') {
            CEP.TRC(SCCGWA, "BBB");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.HIS_REC_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-HIS-REC IS " + SMB1790_AWA_1790.HIS_REC + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1790_AWA_1790.SUBS_TX)) {
            CEP.TRC(SCCGWA, "CCC");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.SUBS_TX_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-SUBS-TX IS " + SMB1790_AWA_1790.SUBS_TX + "! BUT MUST BE " + "NUMERIC";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.ODM_PRN != 'A' 
            && SMB1790_AWA_1790.ODM_PRN != 'N' 
            && SMB1790_AWA_1790.ODM_PRN != 'W') {
            CEP.TRC(SCCGWA, "DDD");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.ODM_PRN_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-ODM-PRN IS " + SMB1790_AWA_1790.ODM_PRN + "! BUT MUST BE " + "A OR N OR W!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.AWA_IND != 'Y' 
            && SMB1790_AWA_1790.AWA_IND != 'N') {
            CEP.TRC(SCCGWA, "EEE");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.AWA_IND_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-AWA-IND IS " + SMB1790_AWA_1790.AWA_IND + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1790_AWA_1790.INP_FMT)) {
            CEP.TRC(SCCGWA, "FFF");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.INP_FMT_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-INP-FMT IS " + SMB1790_AWA_1790.INP_FMT + "! BUT MUST BE " + "NUMERIC";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1790_AWA_1790.OUTP_FMT)) {
            CEP.TRC(SCCGWA, "GGG");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.OUTP_FMT_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-OUTP-FMT IS " + SMB1790_AWA_1790.OUTP_FMT + "! BUT MUST BE " + "NUMERIC";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.TRAN_IND != 'Y' 
            && SMB1790_AWA_1790.TRAN_IND != 'N') {
            CEP.TRC(SCCGWA, "HHH");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.TRAN_IND_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-TRAN-IND IS " + SMB1790_AWA_1790.TRAN_IND + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1790_AWA_1790.REVT_IND != 'Y' 
            && SMB1790_AWA_1790.REVT_IND != 'N') {
            CEP.TRC(SCCGWA, "III");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1790_AWA_1790.REVT_IND_NO;
            WS_TEMP_VARIABLE.WS_MSG_INFO = "AWA-REVT-IND IS " + SMB1790_AWA_1790.REVT_IND + "! BUT MUST BE " + "Y OR N!";
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 5; WS_TEMP_VARIABLE.WS_COUNT += 1) {
