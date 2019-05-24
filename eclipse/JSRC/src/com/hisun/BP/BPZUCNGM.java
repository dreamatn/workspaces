package com.hisun.BP;

import com.hisun.SC.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCNGM {
    String JIBS_tmp_str[] = new String[10];
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "CNGM DETAILS INFO ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_TR_DATE = 0;
    int WS_TR_TIME = 0;
    char WS_DATA_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCNGM BPRNCNGM = new BPRCNGM();
    BPRCNGM BPROCNGM = new BPRCNGM();
    BPRCNGM BPRCNGM = new BPRCNGM();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCRCNGM BPCRCNGM = new BPCRCNGM();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCUCNGM BPCUCNGM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUCNGM BPCUCNGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCNGM = BPCUCNGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCNGM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCNGM);
        IBS.init(SCCGWA, BPCRCNGM);
        IBS.init(SCCGWA, BPCPQAMO);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUCNGM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCUCNGM.FUNC == 'Q') {
            B010_QUERY_CNGM_PROCESS();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUCNGM.FUNC == 'A') {
            B020_ADD_CNGM_PROCESS();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUCNGM.FUNC == 'U') {
            B030_UPDATE_CNGM_PROCESS();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUCNGM.FUNC == 'D') {
            B040_DELETE_CNGM_PROCESS();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCUCNGM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUCNGM.FUNC);
        if (BPCUCNGM.FUNC != 'Q' 
            && BPCUCNGM.FUNC != 'A' 
            && BPCUCNGM.FUNC != 'U' 
            && BPCUCNGM.FUNC != 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUCNGM.KEY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUCNGM.FUNC == 'A') {
            CEP.TRC(SCCGWA, BPCUCNGM.KEY.AC);
            if (BPCUCNGM.KEY.AC.trim().length() == 0 
                || BPCUCNGM.KEY.AC.equalsIgnoreCase("0")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCUCNGM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCUCNGM.PROD_TYPE);
            CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
            if (BPCUCNGM.KEY.CNTR_TYPE.trim().length() == 0 
                && BPCUCNGM.PROD_TYPE.trim().length() == 0 
                && BPCUCNGM.MOD_NO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ACCU_MOD_MUST_INPUT, BPCUCNGM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCUCNGM.FUNC);
        if (BPCUCNGM.FUNC == 'A' 
            || BPCUCNGM.FUNC == 'U') {
            if (BPCUCNGM.KEY.CNTR_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCUCNGM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_I = 0;
            WS_J = 0;
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                CEP.TRC(SCCGWA, BPCUCNGM.DATA[WS_I-1].GLMST);
                if (BPCUCNGM.DATA[WS_I-1].GLMST != 0 
                    && BPCUCNGM.DATA[WS_I-1].GLMST != ' ') {
                    CEP.TRC(SCCGWA, BPCUCNGM.DATA[WS_I-1].GLMST);
                    IBS.init(SCCGWA, BPCPQGLM);
                    BPCPQGLM.DAT.INPUT.MSTNO = BPCUCNGM.DATA[WS_I-1].GLMST;
                    S000_CALL_BPZPQGLM();
                    if (pgmRtn) return;
                    WS_J = WS_J + 1;
                }
            }
            if (WS_J == 0 
                && !BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("IB")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT, BPCUCNGM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCUCNGM.PROD_TYPE);
            CEP.TRC(SCCGWA, BPCUCNGM.BR);
            CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
            if (BPCUCNGM.MOD_NO.trim().length() == 0 
                || BPCUCNGM.MOD_NO.charAt(0) == 0X00) {
                IBS.init(SCCGWA, BPCPQAMO);
                BPCPQAMO.FUNC = 'Q';
                BPCPQAMO.DATA_INFO.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
                BPCPQAMO.DATA_INFO.PROD_TYPE = BPCUCNGM.PROD_TYPE;
                BPCPQAMO.DATA_INFO.BR = BPCUCNGM.BR;
                S000_CALL_BPZPQAMO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
            }
        }
    }
    public void B010_QUERY_CNGM_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.REL_SEQ);
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        IBS.init(SCCGWA, BPCRCNGM);
        IBS.init(SCCGWA, BPRCNGM);
        BPRCNGM.KEY.AC = BPCUCNGM.KEY.AC;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.REL_SEQ);
        if (BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("MMDP")) {
            BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
        }
        BPRCNGM.KEY.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        BPCRCNGM.FUNC = 'Q';
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
        if (BPCRCNGM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_CNGM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCNGM);
        IBS.init(SCCGWA, BPRCNGM);
        BPCRCNGM.FUNC = 'A';
        BPRCNGM.KEY.AC = BPCUCNGM.KEY.AC;
        BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
        if (BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("MMDP")) {
            BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
            CEP.TRC(SCCGWA, BPCUCNGM.KEY.REL_SEQ);
        }
        BPRCNGM.KEY.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
        if (BPCUCNGM.MOD_NO.trim().length() == 0 
            || BPCUCNGM.MOD_NO.charAt(0) == 0X00) {
            BPRCNGM.MOD_NO = BPCPQAMO.DATA_INFO.MOD_NO;
        } else {
            BPRCNGM.MOD_NO = BPCUCNGM.MOD_NO;
        }
        BPRCNGM.GLMST1 = BPCUCNGM.DATA[1-1].GLMST;
        BPRCNGM.GLMST2 = BPCUCNGM.DATA[2-1].GLMST;
        BPRCNGM.GLMST3 = BPCUCNGM.DATA[3-1].GLMST;
        BPRCNGM.GLMST4 = BPCUCNGM.DATA[4-1].GLMST;
        BPRCNGM.GLMST5 = BPCUCNGM.DATA[5-1].GLMST;
        BPRCNGM.GLMST6 = BPCUCNGM.DATA[6-1].GLMST;
        BPRCNGM.GLMST7 = BPCUCNGM.DATA[7-1].GLMST;
        BPRCNGM.GLMST8 = BPCUCNGM.DATA[8-1].GLMST;
        BPRCNGM.GLMST9 = BPCUCNGM.DATA[9-1].GLMST;
        BPRCNGM.GLMST10 = BPCUCNGM.DATA[10-1].GLMST;
        BPRCNGM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPRCNGM.KEY.REF);
        CEP.TRC(SCCGWA, BPRCNGM.KEY.CNTR_TYPE);
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
        if (BPCRCNGM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_CNGM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCNGM);
        IBS.init(SCCGWA, BPRCNGM);
        BPRCNGM.KEY.AC = BPCUCNGM.KEY.AC;
        BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
        BPRCNGM.KEY.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.REL_SEQ);
        BPCRCNGM.FUNC = 'M';
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
        if (BPCRCNGM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCNGM, BPROCNGM);
        BPRCNGM.KEY.AC = BPCUCNGM.KEY.AC;
        BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
        BPRCNGM.KEY.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        if (BPCUCNGM.MOD_NO.trim().length() == 0 
            || BPCUCNGM.MOD_NO.charAt(0) == 0X00) {
            BPRCNGM.MOD_NO = BPCPQAMO.DATA_INFO.MOD_NO;
        } else {
            BPRCNGM.MOD_NO = BPCUCNGM.MOD_NO;
        }
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        if (JIBS_sdf.format(JIBS_date).substring(0, 8).trim().length() == 0) WS_TR_DATE = 0;
        else WS_TR_DATE = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(0, 8));
        if (JIBS_sdf.format(JIBS_date).substring(8, 16).trim().length() == 0) WS_TR_TIME = 0;
        else WS_TR_TIME = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(8, 16));
        CEP.TRC(SCCGWA, WS_TR_DATE);
        CEP.TRC(SCCGWA, WS_TR_TIME);
        BPRCNGM.EC_LMT_DATE = WS_TR_DATE;
        BPRCNGM.EC_LMT_TIME = WS_TR_TIME;
        BPRCNGM.GLMST1 = BPCUCNGM.DATA[1-1].GLMST;
        BPRCNGM.GLMST2 = BPCUCNGM.DATA[2-1].GLMST;
        BPRCNGM.GLMST3 = BPCUCNGM.DATA[3-1].GLMST;
        BPRCNGM.GLMST4 = BPCUCNGM.DATA[4-1].GLMST;
        BPRCNGM.GLMST5 = BPCUCNGM.DATA[5-1].GLMST;
        BPRCNGM.GLMST6 = BPCUCNGM.DATA[6-1].GLMST;
        BPRCNGM.GLMST7 = BPCUCNGM.DATA[7-1].GLMST;
        BPRCNGM.GLMST8 = BPCUCNGM.DATA[8-1].GLMST;
        BPRCNGM.GLMST9 = BPCUCNGM.DATA[9-1].GLMST;
        BPRCNGM.GLMST10 = BPCUCNGM.DATA[10-1].GLMST;
        BPRCNGM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRCNGM.FUNC = 'U';
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRCNGM, BPRNCNGM);
    }
    public void B040_DELETE_CNGM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCNGM);
        IBS.init(SCCGWA, BPRCNGM);
        BPRCNGM.KEY.AC = BPCUCNGM.KEY.AC;
        BPRCNGM.KEY.REF = BPCUCNGM.KEY.REL_SEQ;
        BPRCNGM.KEY.CNTR_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.REL_SEQ);
        BPCRCNGM.FUNC = 'M';
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
        if (BPCRCNGM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRCNGM.FUNC = 'D';
        S000_CALL_BPZRCNGM();
        if (pgmRtn) return;
    }
    public void S000_HISTORY_PROCESS_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPROCNGM);
        IBS.init(SCCGWA, BPRNCNGM);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPRCNGM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.AC = BPRCNGM.KEY.AC;
        IBS.CLONE(SCCGWA, BPRCNGM, BPRNCNGM);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 184;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCNGM;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRNCNGM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_HISTORY_PROCESS_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPRCNGM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 184;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCNGM;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRNCNGM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_HISTORY_PROCESS_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPROCNGM);
        IBS.init(SCCGWA, BPRNCNGM);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = "BPRCNGM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        IBS.CLONE(SCCGWA, BPRCNGM, BPROCNGM);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 184;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCNGM;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRNCNGM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCUCNGM.KEY.AC = BPRCNGM.KEY.AC;
        BPCUCNGM.KEY.REL_SEQ = BPRCNGM.KEY.REF;
        BPCUCNGM.KEY.CNTR_TYPE = BPRCNGM.KEY.CNTR_TYPE;
        BPCUCNGM.MOD_NO = BPRCNGM.MOD_NO;
        BPCUCNGM.EC_LMT_DATE = BPRCNGM.EC_LMT_DATE;
        BPCUCNGM.EC_LMT_TIME = BPRCNGM.EC_LMT_TIME;
        BPCUCNGM.DATA[1-1].GLMST = BPRCNGM.GLMST1;
        BPCUCNGM.DATA[2-1].GLMST = BPRCNGM.GLMST2;
        BPCUCNGM.DATA[3-1].GLMST = BPRCNGM.GLMST3;
        BPCUCNGM.DATA[4-1].GLMST = BPRCNGM.GLMST4;
        BPCUCNGM.DATA[5-1].GLMST = BPRCNGM.GLMST5;
        BPCUCNGM.DATA[6-1].GLMST = BPRCNGM.GLMST6;
        BPCUCNGM.DATA[7-1].GLMST = BPRCNGM.GLMST7;
        BPCUCNGM.DATA[8-1].GLMST = BPRCNGM.GLMST8;
        BPCUCNGM.DATA[9-1].GLMST = BPRCNGM.GLMST9;
        BPCUCNGM.DATA[10-1].GLMST = BPRCNGM.GLMST10;
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT     ", BPCPQGLM);
        if (BPCPQGLM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCNGM() throws IOException,SQLException,Exception {
        BPCRCNGM.LEN = 184;
        CEP.TRC(SCCGWA, BPCRCNGM.LEN);
        BPCRCNGM.POINTER = BPRCNGM;
        CEP.TRC(SCCGWA, "BP-R-MAINT-CNGM");
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-CNGM", BPCRCNGM);
        CEP.TRC(SCCGWA, BPCRCNGM.RC);
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD", BPCPQAMO);
        CEP.TRC(SCCGWA, BPCPQAMO.RC);
        if (BPCPQAMO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCUCNGM = ");
            CEP.TRC(SCCGWA, BPCUCNGM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
