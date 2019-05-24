package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSACM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_PARM_TYPE = "AMACM";
    String K_ACM_TYPE = "SEQ";
    String K_ACM_CODE = "AC MOD NO";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP211";
    String K_HIS_REMARKS = "ACCOUNT MODEL MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_I = 0;
    int WS_J = 0;
    BPZSACM_WS_KEY WS_KEY = new BPZSACM_WS_KEY();
    BPZSACM_WS_VAL WS_VAL = new BPZSACM_WS_VAL();
    BPZSACM_WS_OLD_HIS WS_OLD_HIS = new BPZSACM_WS_OLD_HIS();
    BPZSACM_WS_NEW_HIS WS_NEW_HIS = new BPZSACM_WS_NEW_HIS();
    char WS_DATA_FLG = ' ';
    char WS_MODEL_FLG = ' ';
    char WS_EVENT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRACM BPRACM = new BPRACM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHACM BPCHACM = new BPCHACM();
    BPCHACM BPCHACMN = new BPCHACM();
    BPCHACM BPCHACMO = new BPCHACM();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCOACM BPCOACM = new BPCOACM();
    BPCPQENT BPCPQENT = new BPCPQENT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSACM BPCSACM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSACM BPCSACM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSACM = BPCSACM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSACM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPCHACMO);
        IBS.init(SCCGWA, BPCHACMN);
        IBS.init(SCCGWA, BPCOACM);
        IBS.init(SCCGWA, BPCPQENT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACM.KEY);
        CEP.TRC(SCCGWA, BPCSACM.DATA);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSACM.FUNC == 'Q') {
            B010_QUERY_ASM_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACM.FUNC == 'A') {
            B020_ADD_ASM_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACM.FUNC == 'U') {
            B030_UPDATE_ASM_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACM.FUNC == 'D') {
            B040_DELETE_ASM_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACM.FUNC == 'B') {
            B050_BROWSE_ASM_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSACM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACM.KEY.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCSACM.KEY.BR);
        if (BPCSACM.FUNC == 'A' 
            || BPCSACM.FUNC == 'U') {
            if (BPCSACM.DATA.MOD_NO.trim().length() == 0 
                && BPCSACM.FUNC == 'U') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSACM.DATA.MOD_NAME.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACMD_NM_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSACM.DATA.MOD_TYP != 'C' 
                && BPCSACM.DATA.MOD_TYP != 'O') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_MOD_TYP);
            }
            WS_DATA_FLG = 'N';
            for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
                if (BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE.trim().length() > 0) {
                    WS_DATA_FLG = 'Y';
                }
            }
            if (WS_DATA_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EVENT_TYP_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_ASM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRACM.KEY.TYPE = "AMACM";
        WS_KEY.WS_CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        WS_KEY.WS_PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        WS_KEY.WS_BR = BPCSACM.KEY.BR;
        BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, BPRACM.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSACM.EFF_DT;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        S000_02_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_ASM_PROCESS() throws IOException,SQLException,Exception {
        BPCSACM.DATA.MOD_NO = " ";
        BPCSACM.DATA.MOD_NO = BPCSACM.KEY.CNTR_TYPE;
        CEP.TRC(SCCGWA, BPCSACM.DATA.MOD_NO);
        B020_01_BROWSE_MODEL_PROCESS();
        if (pgmRtn) return;
        if (WS_MODEL_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        S000_01_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCHACMO);
        IBS.init(SCCGWA, BPCHACMN);
        BPCHACMN.CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        BPCHACMN.PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        BPCHACMN.BR = BPCSACM.KEY.BR;
        BPCHACMN.MOD_NO = BPCSACM.DATA.MOD_NO;
        BPCHACMN.MOD_NAME = BPCSACM.DATA.MOD_NAME;
        BPCHACMN.MOD_TYP = BPCSACM.DATA.MOD_TYP;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPCHACMN.EVENT[WS_CNT-1].EVENT_TYPE = BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
        }
        S000_HISTORY_PROCESS();
        if (pgmRtn) return;
        S000_02_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_01_BROWSE_MODEL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_TYPE;
        BPRPARM.KEY.CODE = " ";
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        WS_MODEL_FLG = 'N';
        CEP.TRC(SCCGWA, WS_MODEL_FLG);
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'N' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && WS_MODEL_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_VAL);
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_KEY);
            CEP.TRC(SCCGWA, WS_VAL);
            CEP.TRC(SCCGWA, BPCSACM.DATA.MOD_NO);
            CEP.TRC(SCCGWA, WS_VAL.WS_MOD_NO);
            if (BPCSACM.KEY.CNTR_TYPE.equalsIgnoreCase(WS_KEY.WS_CNTR_TYPE) 
                && BPCSACM.KEY.PROD_TYPE.equalsIgnoreCase(WS_KEY.WS_PROD_TYPE) 
                && BPCSACM.KEY.BR == WS_KEY.WS_BR) {
                WS_MODEL_FLG = 'Y';
            }
            if (BPCSACM.DATA.MOD_NO.equalsIgnoreCase(WS_VAL.WS_MOD_NO)) {
                WS_MODEL_FLG = 'Y';
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_MODEL_FLG);
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_ASM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCHACMO);
        IBS.init(SCCGWA, BPCHACMN);
        BPCHACMN.CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        BPCHACMN.PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        BPCHACMN.BR = BPCSACM.KEY.BR;
        BPCHACMN.MOD_NO = BPCSACM.DATA.MOD_NO;
        BPCHACMN.MOD_NAME = BPCSACM.DATA.MOD_NAME;
        BPCHACMN.MOD_TYP = BPCSACM.DATA.MOD_TYP;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPCHACMN.EVENT[WS_CNT-1].EVENT_TYPE = BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
            WS_NEW_HIS.WS_NEW_DATA[WS_CNT-1].WS_NEW_TYPE = BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
        }
        BPRACM.KEY.TYPE = "AMACM";
        WS_KEY.WS_CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        WS_KEY.WS_PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        WS_KEY.WS_BR = BPCSACM.KEY.BR;
        BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, BPRACM.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSACM.EFF_DT;
        BPCPRMM.EXP_DT = BPCSACM.EXP_DT;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, WS_KEY);
        BPCHACMO.CNTR_TYPE = WS_KEY.WS_CNTR_TYPE;
        BPCHACMO.PROD_TYPE = WS_KEY.WS_PROD_TYPE;
        BPCHACMO.BR = WS_KEY.WS_BR;
        BPCHACMO.MOD_NO = BPRACM.DATA.MOD_NO;
        BPCHACMO.MOD_NAME = BPRACM.DATA.MOD_NAME;
        BPCHACMO.MOD_TYP = BPRACM.DATA.MOD_TYP;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPCHACMO.EVENT[WS_CNT-1].EVENT_TYPE = BPRACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
            WS_OLD_HIS.WS_OLD_DATA[WS_CNT-1].WS_OLD_TYPE = BPRACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
        }
        R000_CHECK_EVENT_CODE_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_NEW_HIS);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_OLD_HIS);
        if (BPCHACMN.CNTR_TYPE.equalsIgnoreCase(BPCHACMO.CNTR_TYPE) 
            && BPCHACMN.PROD_TYPE.equalsIgnoreCase(BPCHACMO.PROD_TYPE) 
            && BPCHACMN.BR == BPCHACMO.BR 
            && BPCHACMN.MOD_NO.equalsIgnoreCase(BPCHACMO.MOD_NO) 
            && BPCHACMN.MOD_NAME.equalsIgnoreCase(BPCHACMO.MOD_NAME) 
            && JIBS_tmp_str[2].equalsIgnoreCase(JIBS_tmp_str[1])) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_01_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        S000_HISTORY_PROCESS();
        if (pgmRtn) return;
        S000_02_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_ASM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        S000_01_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        S000_01_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCHACMO);
        IBS.init(SCCGWA, BPCHACMN);
        BPCHACMO.CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        BPCHACMO.PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        BPCHACMO.BR = BPCSACM.KEY.BR;
        BPCHACMO.MOD_NO = BPCSACM.DATA.MOD_NO;
        BPCHACMO.MOD_NAME = BPCSACM.DATA.MOD_NAME;
        BPCHACMO.MOD_TYP = BPCSACM.DATA.MOD_TYP;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPCHACMO.EVENT[WS_CNT-1].EVENT_TYPE = BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
        }
        S000_HISTORY_PROCESS();
        if (pgmRtn) return;
        S000_02_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_ASM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 649;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_TYPE;
        BPRPARM.KEY.CODE = IBS.CLS2CPY(SCCGWA, BPCSACM.KEY);
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        B050_00_READ_NEXT_PROCESS();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        if (WS_CNT1 == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_00_READ_NEXT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSACM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCSACM.KEY.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCSACM.KEY.BR);
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'N' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && SCCMPAG.FUNC != 'E') {
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_VAL);
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_KEY);
            if ((BPCSACM.KEY.CNTR_TYPE.equalsIgnoreCase(WS_KEY.WS_CNTR_TYPE) 
                || BPCSACM.KEY.CNTR_TYPE.trim().length() == 0) 
                && (BPCSACM.KEY.PROD_TYPE.equalsIgnoreCase(WS_KEY.WS_PROD_TYPE) 
                || BPCSACM.KEY.PROD_TYPE.trim().length() == 0) 
                && (BPCSACM.KEY.BR == WS_KEY.WS_BR 
                || BPCSACM.KEY.BR == 0)) {
                S000_01_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSACM.FUNC == 'A' 
            || BPCSACM.FUNC == 'D') {
            S000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSACM.FUNC == 'U') {
            S000_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSACM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSACM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPCHACM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 627;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCHACMO;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCHACMN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPCHACM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 627;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCHACMO;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCHACMN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_02_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOACM);
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, WS_KEY);
        BPCOACM.CNTR_TYPE = WS_KEY.WS_CNTR_TYPE;
        if (WS_KEY.WS_PROD_TYPE.trim().length() == 0) {
            BPCOACM.PROD_TYPE = "*";
        } else {
            BPCOACM.PROD_TYPE = WS_KEY.WS_PROD_TYPE;
        }
        if (WS_KEY.WS_BR == 0) {
            BPCOACM.BR = 999999;
        } else {
            BPCOACM.BR = WS_KEY.WS_BR;
        }
        BPCOACM.MOD_NO = BPRACM.DATA.MOD_NO;
        BPCOACM.MOD_NAME = BPRACM.DATA.MOD_NAME;
        BPCOACM.MOD_TYP = BPRACM.DATA.MOD_TYP;
        BPCOACM.EFF_DT = BPCPRMM.EFF_DT;
        BPCOACM.EXP_DT = BPCPRMM.EXP_DT;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            if (BPRACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE.trim().length() > 0) {
                BPCOACM.EVENT[WS_CNT-1].EVENT_TYPE = BPRACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
                BPCOACM.CNT = WS_CNT;
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSACM.FUNC == 'Q') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = BPCOACM;
        SCCFMT.DATA_LEN = 649;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_01_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VAL);
        CEP.TRC(SCCGWA, WS_KEY);
        if (BPCSACM.DATA.MOD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_VAL.WS_MOD_NO);
            CEP.TRC(SCCGWA, BPCSACM.DATA.MOD_NO);
            if (WS_VAL.WS_MOD_NO.compareTo(BPCSACM.DATA.MOD_NO) >= 0) {
                IBS.init(SCCGWA, BPCOACM);
                BPCOACM.CNTR_TYPE = WS_KEY.WS_CNTR_TYPE;
                if (WS_KEY.WS_PROD_TYPE.trim().length() == 0) {
                    BPCOACM.PROD_TYPE = "*";
                } else {
                    BPCOACM.PROD_TYPE = WS_KEY.WS_PROD_TYPE;
                }
                if (WS_KEY.WS_BR == 0) {
                    BPCOACM.BR = 999999;
                } else {
                    BPCOACM.BR = WS_KEY.WS_BR;
                }
                BPCOACM.MOD_NO = WS_VAL.WS_MOD_NO;
                BPCOACM.MOD_NAME = WS_VAL.WS_MOD_NAME;
                BPCOACM.MOD_TYP = WS_VAL.WS_MOD_TYP;
                BPCOACM.EFF_DT = BPRPARM.EFF_DATE;
                BPCOACM.EXP_DT = BPRPARM.EXP_DATE;
                for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
                    BPCOACM.EVENT[WS_CNT-1].EVENT_TYPE = WS_VAL.WS_EVENT[WS_CNT-1].WS_EVENT_TYPE;
                }
                S000_01_01_SCCMPAG();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, BPCOACM);
            BPCOACM.CNTR_TYPE = WS_KEY.WS_CNTR_TYPE;
            if (WS_KEY.WS_PROD_TYPE.trim().length() == 0) {
                BPCOACM.PROD_TYPE = "*";
            } else {
                BPCOACM.PROD_TYPE = WS_KEY.WS_PROD_TYPE;
            }
            if (WS_KEY.WS_BR == 0) {
                BPCOACM.BR = 999999;
            } else {
                BPCOACM.BR = WS_KEY.WS_BR;
            }
            BPCOACM.MOD_NO = WS_VAL.WS_MOD_NO;
            BPCOACM.MOD_NAME = WS_VAL.WS_MOD_NAME;
            BPCOACM.MOD_TYP = WS_VAL.WS_MOD_TYP;
            BPCOACM.EFF_DT = BPRPARM.EFF_DATE;
            BPCOACM.EXP_DT = BPRPARM.EXP_DATE;
            for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
                BPCOACM.EVENT[WS_CNT-1].EVENT_TYPE = WS_VAL.WS_EVENT[WS_CNT-1].WS_EVENT_TYPE;
            }
            S000_01_01_SCCMPAG();
            if (pgmRtn) return;
        }
    }
    public void S000_01_01_SCCMPAG() throws IOException,SQLException,Exception {
        WS_CNT1 = WS_CNT1 + 1;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, BPCOACM);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOACM);
        SCCMPAG.DATA_LEN = 649;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHECK_EVENT_CODE_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            CEP.TRC(SCCGWA, "111111111111111111");
            CEP.TRC(SCCGWA, BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE);
            if (BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE.trim().length() > 0) {
                WS_EVENT_FLG = 'N';
                for (WS_J = 1; WS_J <= 60; WS_J += 1) {
                    CEP.TRC(SCCGWA, BPCSACM.DATA.EVENT[WS_J-1].EVENT_TYPE);
                    if (BPCSACM.DATA.EVENT[WS_J-1].EVENT_TYPE.trim().length() > 0) {
                        if (BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE.equalsIgnoreCase(BPCSACM.DATA.EVENT[WS_J-1].EVENT_TYPE)) {
                            WS_EVENT_FLG = 'Y';
                        }
                    }
                }
                if (WS_EVENT_FLG == 'N') {
                    R000_RECORD_CHECK_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_RECORD_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQENT);
        CEP.TRC(SCCGWA, BPCSACM.DATA.MOD_NO);
        CEP.TRC(SCCGWA, BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE);
        BPCPQENT.DATA_INFO.MODNO = BPCSACM.DATA.MOD_NO;
        BPCPQENT.DATA_INFO.EVENT_TYPE = BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE;
        BPCPQENT.FUNC = 'B';
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
        if (BPCPQENT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQENT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPQENT.FUNC = 'N';
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO);
        if (BPCPQENT.RC.RC_CODE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MODEL_EXIST_ENTY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPQENT.FUNC = 'E';
        S000_CALL_BPZPQENT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQENT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-EVENT", BPCPQENT);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_ACM_TYPE;
        BPCSGSEQ.CODE = K_ACM_CODE;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSACM.DATA.MOD_NO = JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRACM.DATA.MOD_NO = JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSACM.DATA.MOD_NO);
        CEP.TRC(SCCGWA, BPRACM.DATA.MOD_NO);
        if (BPRACM.DATA.MOD_NO.trim().length() == 0 
            || BPRACM.DATA.MOD_NO.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACMD_NM_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_01_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPRACM.KEY.TYPE = "AMACM";
        CEP.TRC(SCCGWA, BPRACM.KEY.TYPE);
        WS_KEY.WS_CNTR_TYPE = BPCSACM.KEY.CNTR_TYPE;
        WS_KEY.WS_PROD_TYPE = BPCSACM.KEY.PROD_TYPE;
        WS_KEY.WS_BR = BPCSACM.KEY.BR;
        BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, BPRACM.KEY.REDEFINES6);
        BPRACM.DATA.MOD_NO = BPCSACM.DATA.MOD_NO;
        BPRACM.DATA.MOD_NAME = BPCSACM.DATA.MOD_NAME;
        BPRACM.DATA.MOD_TYP = BPCSACM.DATA.MOD_TYP;
        BPCPRMM.EFF_DT = BPCSACM.EFF_DT;
        BPCPRMM.EXP_DT = BPCSACM.EXP_DT;
        CEP.TRC(SCCGWA, BPRACM.KEY.CD);
        CEP.TRC(SCCGWA, BPRACM.DATA.MOD_NO);
        CEP.TRC(SCCGWA, BPRACM.DATA.MOD_NAME);
        CEP.TRC(SCCGWA, BPRACM.DATA.MOD_TYP);
        CEP.TRC(SCCGWA, BPCSACM.EFF_DT);
        CEP.TRC(SCCGWA, BPCSACM.EXP_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPRACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE = BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE;
        }
        BPRACM.DATA.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRACM.DATA.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRACM.DATA.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRACM.DATA.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRACM.DATA.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRACM.DATA.UPD_TEL);
        CEP.TRC(SCCGWA, BPRACM.DATA.UPD_DATE);
        CEP.TRC(SCCGWA, BPRACM.DATA.UPD_TIME);
        CEP.TRC(SCCGWA, BPRACM.DATA.SUP_TEL1);
        CEP.TRC(SCCGWA, BPRACM.DATA.SUP_TEL2);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRACM.DATA_LEN = 645;
        CEP.TRC(SCCGWA, BPRACM.DATA_LEN);
        BPCPRMM.DAT_PTR = BPRACM;
        CEP.TRC(SCCGWA, "CALL BP-PARM-MAINTAIN");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AAAAA");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
