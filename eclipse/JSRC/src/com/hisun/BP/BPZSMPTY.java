package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPTY {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "PARMETER TYPE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHPTY";
    String K_PARM_TYPE_TYPE = "PARMT";
    String K_PARM_CODE_TYPE = "PARMC";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP350";
    BPZSMPTY_WS_RC WS_RC = new BPZSMPTY_WS_RC();
    short WS_I = 0;
    short WS_FIND_CNT = 0;
    char WS_TYPE_LVL1 = ' ';
    char WS_TYPE_LVL2 = ' ';
    BPZSMPTY_WS_UP_TYPE_DATA WS_UP_TYPE_DATA = new BPZSMPTY_WS_UP_TYPE_DATA();
    BPZSMPTY_WS_PARMT_KEY WS_PARMT_KEY = new BPZSMPTY_WS_PARMT_KEY();
    int WS_OLD_EFF_DATE = 0;
    int WS_OLD_EXP_DATE = 0;
    BPZSMPTY_WS_VAL WS_VAL = new BPZSMPTY_WS_VAL();
    BPZSMPTY_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMPTY_WS_OUTPUT_DATA();
    BPZSMPTY_WS_FMT_DATA WS_FMT_DATA = new BPZSMPTY_WS_FMT_DATA();
    BPZSMPTY_WS_HIS_REF WS_HIS_REF = new BPZSMPTY_WS_HIS_REF();
    BPZSMPTY_WS_PARMC_KEY WS_PARMC_KEY = new BPZSMPTY_WS_PARMC_KEY();
    BPZSMPTY_WS_PARMC_VAL WS_PARMC_VAL = new BPZSMPTY_WS_PARMC_VAL();
    BPZSMPTY_WS_INT_QUERY_OT_DATA WS_INT_QUERY_OT_DATA = new BPZSMPTY_WS_INT_QUERY_OT_DATA();
    BPZSMPTY_WS_INT_BRW_OT_C_DATA WS_INT_BRW_OT_C_DATA = new BPZSMPTY_WS_INT_BRW_OT_C_DATA();
    BPZSMPTY_WS_INT_BRW_OT_T_DATA WS_INT_BRW_OT_T_DATA = new BPZSMPTY_WS_INT_BRW_OT_T_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_REC_FIND_FLG = ' ';
    char WS_READ_TYPE_FLG = ' ';
    char WS_READ_CODE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHPTY BPCOHPTY = new BPCHPTY();
    BPCHPTY BPCNHPTY = new BPCHPTY();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMPTY BPCSMPTY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMPTY BPCSMPTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPTY = BPCSMPTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMPTY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPTY);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMPTY.FUNC == 'Q'
            || BPCSMPTY.FUNC == 'X'
            || BPCSMPTY.FUNC == 'A'
            || BPCSMPTY.FUNC == 'U'
            || BPCSMPTY.FUNC == 'C'
            || BPCSMPTY.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPTY.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPTY.FUNC == 'I') {
            B230_INT_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPTY.FUNC == 'N') {
            B240_INT_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            CEP.TRC(SCCGWA, "CGB001");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSMPTY.OUTPUT_FLG == 'Y' 
            || BPCSMPTY.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            CEP.TRC(SCCGWA, "CGB002");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPTY.FUNC != 'B') {
            if (BPCSMPTY.TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                CEP.TRC(SCCGWA, "CGB003");
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMPTY.FUNC == 'A') {
            if (BPCSMPTY.EFF_DATE == 0 
                || BPCSMPTY.EXP_DATE == 0 
                || BPCSMPTY.INFO.NAME.trim().length() == 0 
                || BPCSMPTY.INFO.CHG_NAME.trim().length() == 0 
                || BPCSMPTY.INFO.LVL == ' ' 
                || BPCSMPTY.INFO.DOWNLOAD_FLG == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                CEP.TRC(SCCGWA, "CGB004");
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMPTY.FUNC == 'B') {
            if (BPCSMPTY.TYPE.trim().length() == 0 
                && BPCSMPTY.INFO.UP_TYPE.trim().length() == 0 
                && BPCSMPTY.INFO.LVL == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                CEP.TRC(SCCGWA, "CGB005");
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMPTY.EXP_DATE != 0 
            && BPCSMPTY.EFF_DATE != 0 
            && BPCSMPTY.EXP_DATE < BPCSMPTY.EFF_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPTY.INFO.LVL != ' ') {
            CEP.TRC(SCCGWA, WS_TYPE_LVL1);
            WS_TYPE_LVL1 = BPCSMPTY.INFO.LVL;
            if ((WS_TYPE_LVL1 != 'T' 
                && WS_TYPE_LVL1 != 'M' 
                && WS_TYPE_LVL1 != 'B' 
                && WS_TYPE_LVL1 != 'X')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_LVL_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPTY.FUNC != 'N') {
                if (WS_TYPE_LVL1 == 'B' 
                    || WS_TYPE_LVL1 == 'M') {
                    if (BPCSMPTY.FUNC == 'U' 
                        || BPCSMPTY.FUNC == 'A') {
                        if (BPCSMPTY.INFO.UP_TYPE.trim().length() == 0) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_MUST_IN, WS_RC);
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        } else {
                            R000_CHECK_UP_TYPE();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_NOTALL_IN, WS_RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        B211_COMMON_PROCESS();
        if (pgmRtn) return;
        if (BPCSMPTY.FUNC == 'D') {
            B212_DELETE_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPTY.FUNC == 'U') {
            B213_UPDATE_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPTY.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B211_COMMON_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPTY.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCSMPTY.EFF_DATE;
        BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCSMPTY.EXP_DATE;
        CEP.TRC(SCCGWA, "TEST");
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EFF_DATE);
        if (BPCSMPTY.FUNC == 'A') {
            BPCSMPTY.INFO.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMPTY.INFO.C_LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSMPTY.FUNC == 'A' 
            || BPCSMPTY.FUNC == 'U') {
            BPCSMPTY.INFO.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, BPCSMPTY.INFO.LST_DATE);
            BPCSMPTY.INFO.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 446;
        BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPCSMPTY.INFO);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN);
        CEP.TRC(SCCGWA, BPCSMPTY.INFO);
        if (BPCSMPTY.FUNC == 'Q'
            || BPCSMPTY.FUNC == 'X'
            || BPCSMPTY.FUNC == 'C') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        } else if (BPCSMPTY.FUNC == 'A') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'A';
        } else if (BPCSMPTY.FUNC == 'U'
            || BPCSMPTY.FUNC == 'D') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'U';
        }
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSMPTY.FUNC == 'Q' 
            || BPCSMPTY.FUNC == 'A' 
            || BPCSMPTY.FUNC == 'X' 
            || (BPCSMPTY.FUNC == 'C' 
            && BPCPARM.PARM_FLG == 'E')) {
            BPCSMPTY.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCSMPTY.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPTY.INFO);
            CEP.TRC(SCCGWA, BPCSMPTY.INFO.LOWER_TYPE_CNT);
            CEP.TRC(SCCGWA, BPCSMPTY.INFO.LOWER_CODE_CNT);
        }
        CEP.TRC(SCCGWA, BPCSMPTY.INFO);
        if (BPCSMPTY.FUNC == 'A') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
            if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
                WS_UP_TYPE_DATA.WS_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
                WS_CNT_FLG = 'A';
                R000_LOWER_CNT_OF_UP_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void B212_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPTY.INFO);
        if (BPCSMPTY.INFO.LOWER_TYPE_CNT != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOWER_TYPE_EXIST, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMPTY.INFO.LOWER_CODE_CNT);
        if (BPCSMPTY.INFO.LOWER_CODE_CNT != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOWER_CODE_EXIST, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPARM.PARM_DATA.PARM_FUNC = 'D';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        BPCSMPTY.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
        BPCSMPTY.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPTY.INFO);
        R000_TXN_HIS_PROCESS();
        if (pgmRtn) return;
        if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
            WS_UP_TYPE_DATA.WS_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
            WS_CNT_FLG = 'S';
            R000_LOWER_CNT_OF_UP_TYPE();
            if (pgmRtn) return;
        }
    }
    public void B213_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
        WS_OLD_EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
        WS_OLD_EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'U';
        BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCSMPTY.EFF_DATE;
        BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCSMPTY.EXP_DATE;
        BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 446;
        BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPCSMPTY.INFO);
        CEP.TRC(SCCGWA, BPCSMPTY.INFO);
        CEP.TRC(SCCGWA, "DATA NOT CHANGE");
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_VAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPTY.INFO);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && BPCSMPTY.EFF_DATE == WS_OLD_EFF_DATE 
            && BPCSMPTY.EXP_DATE == WS_OLD_EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        BPCSMPTY.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
        BPCSMPTY.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPTY.INFO);
        CEP.TRC(SCCGWA, BPCSMPTY.INFO);
        R000_TXN_HIS_PROCESS();
        if (pgmRtn) return;
        if (WS_VAL.WS_V_LVL != BPCSMPTY.INFO.LVL) {
            R000_DEAL_LVL_CHANGE();
            if (pgmRtn) return;
        }
        if (!WS_VAL.WS_V_UP_TYPE.equalsIgnoreCase(BPCSMPTY.INFO.UP_TYPE)) {
            if (WS_VAL.WS_V_UP_TYPE.trim().length() > 0) {
                WS_UP_TYPE_DATA.WS_UP_TYPE = WS_VAL.WS_V_UP_TYPE;
                WS_CNT_FLG = 'S';
                R000_LOWER_CNT_OF_UP_TYPE();
                if (pgmRtn) return;
            }
            if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
                WS_UP_TYPE_DATA.WS_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
                WS_CNT_FLG = 'A';
                R000_LOWER_CNT_OF_UP_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPTY.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCSMPTY.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCPARM);
            BPCPARM.PARM_DATA.PARM_FUNC = 'B';
            BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCPARM.PARM_FLG == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMT_KEY);
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
                CEP.TRC(SCCGWA, WS_VAL.WS_V_LVL);
                CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSMPTY.OUTPUT_FLG);
                if (BPCSMPTY.OUTPUT_FLG == 'Y' 
                    && WS_FIT_COND_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "OUT-DATA");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            S000_TS_OUTPUT_TITLE();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_TYPE = WS_PARMT_KEY.WS_PARMT_TYPE;
            WS_OUTPUT_DATA.WS_OT_NAME = WS_VAL.WS_V_NAME;
            WS_OUTPUT_DATA.WS_OT_CHG_NAME = WS_VAL.WS_V_CHG_NAME;
            WS_OUTPUT_DATA.WS_OT_EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            WS_OUTPUT_DATA.WS_OT_EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            WS_OUTPUT_DATA.WS_OT_LVL = WS_VAL.WS_V_LVL;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_TYPE);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_EFF_DATE);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_EXP_DATE);
            if (WS_VAL.WS_V_UP_TYPE.trim().length() > 0) {
                WS_OUTPUT_DATA.WS_OT_UP_TYPE = WS_VAL.WS_V_UP_TYPE;
                WS_UP_TYPE_DATA.WS_UP_TYPE = WS_VAL.WS_V_UP_TYPE;
                R000_GET_UP_TYPE_INFO();
                if (pgmRtn) return;
                WS_OUTPUT_DATA.WS_OT_UP_NAME = WS_UP_TYPE_DATA.WS_UP_VAL.WS_UP_NAME;
                WS_OUTPUT_DATA.WS_OT_UP_CHG_NAME = WS_UP_TYPE_DATA.WS_UP_VAL.WS_UP_CHG_NAME;
                CEP.TRC(SCCGWA, WS_VAL.WS_V_UP_TYPE);
                CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_UP_NAME);
                CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_UP_CHG_NAME);
            }
            WS_OUTPUT_DATA.WS_OT_DOWNLOAD_FLG = WS_VAL.WS_V_DOWNLOAD_FLG;
            WS_OUTPUT_DATA.WS_OT_OPEN_DATE = WS_VAL.WS_V_OPEN_DATE;
            WS_OUTPUT_DATA.WS_OT_LST_DATE = WS_VAL.WS_V_LST_DATE;
            WS_OUTPUT_DATA.WS_OT_RAMARKS = WS_VAL.WS_V_REMARKS;
            WS_OUTPUT_DATA.WS_OT_CODE_MAX_LEN = WS_VAL.WS_V_CODE_MAX_LEN;
            WS_OUTPUT_DATA.WS_OT_DESCE_MAX_LEN = WS_VAL.WS_V_DESCE_MAX_LEN;
            WS_OUTPUT_DATA.WS_OT_DESCC_MAX_LEN = WS_VAL.WS_V_DESCC_MAX_LEN;
            WS_OUTPUT_DATA.WS_OT_MAINT_BR1 = WS_VAL.WS_V_MAINT_BR1;
            WS_OUTPUT_DATA.WS_OT_MAINT_BR2 = WS_VAL.WS_V_MAINT_BR2;
            WS_OUTPUT_DATA.WS_OT_MAINT_BR3 = WS_VAL.WS_V_MAINT_BR3;
            WS_OUTPUT_DATA.WS_OT_MAINT_BR4 = WS_VAL.WS_V_MAINT_BR4;
            WS_OUTPUT_DATA.WS_OT_MAINT_BR5 = WS_VAL.WS_V_MAINT_BR5;
            WS_OUTPUT_DATA.WS_OT_REP_FLG = WS_VAL.WS_V_REP_FLG;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 686;
            CEP.TRC(SCCGWA, "START B-MPAG");
            B_MPAG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "END B-MPAG");
        }
    }
    public void B230_INT_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPTY.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        S000_TS_OUTPUT_TITLE();
        if (pgmRtn) return;
        WS_REC_FIND_FLG = 'Y';
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            WS_REC_FIND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMT_KEY);
            BPCSMPTY.INFO.NAME = WS_VAL.WS_V_NAME;
            BPCSMPTY.INFO.CHG_NAME = WS_VAL.WS_V_CHG_NAME;
            BPCSMPTY.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCSMPTY.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            if (!WS_PARMT_KEY.WS_PARMT_TYPE.equalsIgnoreCase(BPCSMPTY.TYPE)) {
                WS_REC_FIND_FLG = 'N';
            }
        }
        if (WS_REC_FIND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        WS_REC_FIND_FLG = 'N';
        WS_TYPE_LVL1 = WS_VAL.WS_V_LVL;
        if (WS_TYPE_LVL1 == 'T' 
            || WS_TYPE_LVL1 == 'M') {
            R000_BROWSE_PARM_TYPE();
            if (pgmRtn) return;
        } else {
            R000_BROWSE_PARM_CODE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, WS_INT_QUERY_OT_DATA);
        if (WS_REC_FIND_FLG == 'Y') {
            WS_INT_QUERY_OT_DATA.WS_IQOT_FLG = '+';
        } else {
            WS_INT_QUERY_OT_DATA.WS_IQOT_FLG = '-';
        }
        WS_INT_QUERY_OT_DATA.WS_IQOT_TYPE = BPCSMPTY.TYPE;
        WS_INT_QUERY_OT_DATA.WS_IQOT_CHG_NAME = BPCSMPTY.INFO.CHG_NAME;
        JIBS_tmp_str[0] = "" + BPCSMPTY.EFF_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_YY = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCSMPTY.EFF_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_MM = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCSMPTY.EFF_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_DD = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCSMPTY.EXP_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_YY = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCSMPTY.EXP_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_MM = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCSMPTY.EXP_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_DD = 0;
        else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        WS_INT_QUERY_OT_DATA.WS_IQOT_LVL = WS_VAL.WS_V_LVL;
        WS_INT_QUERY_OT_DATA.WS_IQOT_UP_TYPE = WS_VAL.WS_V_UP_TYPE;
        WS_INT_QUERY_OT_DATA.WS_IQOT_DOWNLOAD_FLG = WS_VAL.WS_V_DOWNLOAD_FLG;
        CEP.TRC(SCCGWA, WS_INT_QUERY_OT_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_INT_QUERY_OT_DATA);
        SCCMPAG.DATA_LEN = 155;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B240_INT_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        WS_TYPE_LVL1 = BPCSMPTY.INFO.LVL;
        if (WS_TYPE_LVL1 == 'M' 
            || WS_TYPE_LVL1 == 'T') {
            B241_BROWSE_TYPE_INFO();
            if (pgmRtn) return;
        } else {
            B242_BROWSE_CODE_INFO();
            if (pgmRtn) return;
        }
    }
    public void B241_BROWSE_TYPE_INFO() throws IOException,SQLException,Exception {
        S000_TS_OUTPUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = " ";
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        WS_READ_TYPE_FLG = 'N';
        WS_READ_CODE_FLG = 'N';
        WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT = 0;
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPARM);
            BPCPARM.PARM_DATA.PARM_FUNC = 'B';
            BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCPARM.PARM_FLG == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
                if (WS_VAL.WS_V_UP_TYPE.equalsIgnoreCase(BPCSMPTY.TYPE)) {
                    WS_TYPE_LVL1 = WS_VAL.WS_V_LVL;
                    if (WS_TYPE_LVL1 == 'M' 
                        || WS_TYPE_LVL1 == 'T') {
                        WS_READ_TYPE_FLG = 'Y';
                    } else {
                        WS_READ_CODE_FLG = 'Y';
                    }
                    IBS.init(SCCGWA, WS_INT_QUERY_OT_DATA);
                    WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT += 1;
                    WS_INT_QUERY_OT_DATA.WS_IQOT_FLG = '-';
                    IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMT_KEY);
                    WS_INT_QUERY_OT_DATA.WS_IQOT_TYPE = WS_PARMT_KEY.WS_PARMT_TYPE;
                    WS_INT_QUERY_OT_DATA.WS_IQOT_CHG_NAME = WS_VAL.WS_V_CHG_NAME;
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_YY = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_MM = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_DD = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EFF_DATE.WS_IQOT_EFF_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_YY = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_MM = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_DD = 0;
                    else WS_INT_QUERY_OT_DATA.WS_IQOT_EXP_DATE.WS_IQOT_EXP_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                    WS_INT_QUERY_OT_DATA.WS_IQOT_LVL = WS_VAL.WS_V_LVL;
                    WS_INT_QUERY_OT_DATA.WS_IQOT_UP_TYPE = WS_VAL.WS_V_UP_TYPE;
                    WS_INT_QUERY_OT_DATA.WS_IQOT_DOWNLOAD_FLG = WS_VAL.WS_V_DOWNLOAD_FLG;
                    WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT-1] = IBS.CLS2CPY(SCCGWA, WS_INT_QUERY_OT_DATA);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT);
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (WS_READ_TYPE_FLG == 'Y') {
            R000_BROWSE_PARM_TYPE_SECOND();
            if (pgmRtn) return;
        }
        if (WS_READ_CODE_FLG == 'Y') {
            R000_BROWSE_PARM_CODE_SECOND();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT; WS_I += 1) {
            IBS.CPY2CLS(SCCGWA, WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1], WS_INT_QUERY_OT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_INT_QUERY_OT_DATA);
            SCCMPAG.DATA_LEN = 155;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B242_BROWSE_CODE_INFO() throws IOException,SQLException,Exception {
        S000_TS_OUTPUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMC_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
        WS_PARMC_KEY.WS_PARMC_TYPE = BPCSMPTY.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPARM);
            BPCPARM.PARM_DATA.PARM_FUNC = 'B';
            BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCPARM.PARM_FLG == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMC_KEY);
                if (WS_PARMC_KEY.WS_PARMC_TYPE.equalsIgnoreCase(BPCSMPTY.TYPE)) {
                    IBS.init(SCCGWA, WS_INT_BRW_OT_C_DATA);
                    IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMC_KEY);
                    WS_INT_BRW_OT_C_DATA.WS_IBOT_C_TYPE = WS_PARMC_KEY.WS_PARMC_TYPE;
                    WS_INT_BRW_OT_C_DATA.WS_IBOT_C_CODE = WS_PARMC_KEY.WS_PARMC_CODE;
                    WS_INT_BRW_OT_C_DATA.WS_IBOT_C_NAME = BPCPARM.PARM_DATA.PARM_DESC;
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_YY = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_MM = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EFF_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_DD = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_C_EFF_DATE.WS_IBOT_C_EFF_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_YY = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_MM = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                    JIBS_tmp_str[0] = "" + BPCPARM.PARM_DATA.PARM_EXP_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_DD = 0;
                    else WS_INT_BRW_OT_C_DATA.WS_IBOT_EXP_DATE.WS_IBOT_C_EXP_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                    IBS.init(SCCGWA, SCCMPAG);
                    SCCMPAG.FUNC = 'D';
                    SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_INT_BRW_OT_C_DATA);
                    SCCMPAG.DATA_LEN = 98;
                    B_MPAG();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_BROWSE_PARM_TYPE_SECOND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = " ";
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        WS_FIND_CNT = 0;
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPARM);
            BPCPARM.PARM_DATA.PARM_FUNC = 'B';
            BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCPARM.PARM_FLG == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
                for (WS_I = 1; WS_I <= WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT; WS_I += 1) {
                    IBS.CPY2CLS(SCCGWA, WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1], WS_INT_QUERY_OT_DATA);
                    CEP.TRC(SCCGWA, WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1]);
                    CEP.TRC(SCCGWA, WS_VAL.WS_V_UP_TYPE);
                    if (WS_VAL.WS_V_UP_TYPE.equalsIgnoreCase(WS_INT_QUERY_OT_DATA.WS_IQOT_TYPE)) {
                        WS_FIND_CNT += 1;
                        WS_INT_QUERY_OT_DATA.WS_IQOT_FLG = '+';
                        WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1] = IBS.CLS2CPY(SCCGWA, WS_INT_QUERY_OT_DATA);
                    }
                }
                if (WS_FIND_CNT >= WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT) {
                    WS_STOP_FLG = 'Y';
                }
            }
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_BROWSE_PARM_CODE_SECOND() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_INT_BRW_OT_T_DATA.WS_IBOT_CNT; WS_I += 1) {
            IBS.CPY2CLS(SCCGWA, WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1], WS_INT_QUERY_OT_DATA);
            WS_TYPE_LVL2 = WS_INT_QUERY_OT_DATA.WS_IQOT_LVL;
            if (WS_TYPE_LVL2 == 'B' 
                || WS_TYPE_LVL2 == 'X') {
                IBS.init(SCCGWA, BPCPARM);
                IBS.init(SCCGWA, WS_PARMC_KEY);
                BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
                WS_PARMC_KEY.WS_PARMC_TYPE = WS_INT_QUERY_OT_DATA.WS_IQOT_TYPE;
                BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
                BPCPARM.PARM_DATA.PARM_FUNC = 'B';
                BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCPARM);
                BPCPARM.PARM_DATA.PARM_FUNC = 'B';
                BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
                if (BPCPARM.PARM_FLG != 'N') {
                    IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMC_KEY);
                    if (WS_PARMC_KEY.WS_PARMC_TYPE.equalsIgnoreCase(WS_INT_QUERY_OT_DATA.WS_IQOT_TYPE)) {
                        WS_INT_QUERY_OT_DATA.WS_IQOT_FLG = '+';
                        WS_INT_BRW_OT_T_DATA.WS_IBOT_T_DATA[WS_I-1] = IBS.CLS2CPY(SCCGWA, WS_INT_QUERY_OT_DATA);
                    }
                }
                IBS.init(SCCGWA, BPCPARM);
                BPCPARM.PARM_DATA.PARM_FUNC = 'B';
                BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_BROWSE_PARM_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = " ";
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPARM);
            BPCPARM.PARM_DATA.PARM_FUNC = 'B';
            BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCPARM.PARM_FLG == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_UP_TYPE_DATA.WS_UP_VAL);
                if (WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_UP_TYPE.equalsIgnoreCase(BPCSMPTY.TYPE)) {
                    WS_STOP_FLG = 'Y';
                    WS_REC_FIND_FLG = 'Y';
                }
            }
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_BROWSE_PARM_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMC_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
        WS_PARMC_KEY.WS_PARMC_TYPE = BPCSMPTY.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'N';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMC_KEY);
            if (WS_PARMC_KEY.WS_PARMC_TYPE.equalsIgnoreCase(BPCSMPTY.TYPE)) {
                WS_REC_FIND_FLG = 'Y';
            }
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_TYPE = BPCSMPTY.TYPE;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_TYPE);
        WS_FMT_DATA.WS_FMT_EFF_DATE = BPCSMPTY.EFF_DATE;
        WS_FMT_DATA.WS_FMT_EXP_DATE = BPCSMPTY.EXP_DATE;
        WS_FMT_DATA.WS_FMT_LVL = BPCSMPTY.INFO.LVL;
        if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
            WS_FMT_DATA.WS_FMT_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
            WS_UP_TYPE_DATA.WS_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
            R000_GET_UP_TYPE_INFO();
            if (pgmRtn) return;
        }
        WS_FMT_DATA.WS_FMT_UP_NAME = WS_UP_TYPE_DATA.WS_UP_VAL.WS_UP_NAME;
        WS_FMT_DATA.WS_FMT_UP_CHG_NAME = WS_UP_TYPE_DATA.WS_UP_VAL.WS_UP_CHG_NAME;
        WS_FMT_DATA.WS_FMT_DOWNLOAD_FLG = BPCSMPTY.INFO.DOWNLOAD_FLG;
        WS_FMT_DATA.WS_FMT_CODE_MAX_LEN = BPCSMPTY.INFO.CODE_MAX_LEN;
        WS_FMT_DATA.WS_FMT_DESCE_MAX_LEN = BPCSMPTY.INFO.DESCE_MAX_LEN;
        WS_FMT_DATA.WS_FMT_DESCC_MAX_LEN = BPCSMPTY.INFO.DESCC_MAX_LEN;
        WS_FMT_DATA.WS_FMT_MAINT_BR1 = BPCSMPTY.INFO.MAINT_BR1;
        WS_FMT_DATA.WS_FMT_MAINT_BR2 = BPCSMPTY.INFO.MAINT_BR2;
        WS_FMT_DATA.WS_FMT_MAINT_BR3 = BPCSMPTY.INFO.MAINT_BR3;
        WS_FMT_DATA.WS_FMT_MAINT_BR4 = BPCSMPTY.INFO.MAINT_BR4;
        WS_FMT_DATA.WS_FMT_MAINT_BR5 = BPCSMPTY.INFO.MAINT_BR5;
        WS_FMT_DATA.WS_FMT_NAME = BPCSMPTY.INFO.NAME;
        WS_FMT_DATA.WS_FMT_CHG_NAME = BPCSMPTY.INFO.CHG_NAME;
        WS_FMT_DATA.WS_FMT_REP_FLG = BPCSMPTY.INFO.REP_FLG;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_CODE_MAX_LEN);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DESCE_MAX_LEN);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_DESCC_MAX_LEN);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_MAINT_BR1);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_MAINT_BR2);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_MAINT_BR3);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_MAINT_BR4);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_MAINT_BR5);
        WS_FMT_DATA.WS_FMT_REMARKS = BPCSMPTY.INFO.REMARKS;
        WS_FMT_DATA.WS_FMT_OPEN_DATE = BPCSMPTY.INFO.OPEN_DATE;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_OPEN_DATE);
        WS_FMT_DATA.WS_FMT_LST_DATE = BPCSMPTY.INFO.LST_DATE;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_LST_DATE);
        WS_FMT_DATA.WS_FMT_LST_TLT = BPCSMPTY.INFO.LST_TLT;
        WS_FMT_DATA.WS_FMT_TYPE_CNT = BPCSMPTY.INFO.LOWER_TYPE_CNT;
        WS_FMT_DATA.WS_FMT_CODE_CNT = BPCSMPTY.INFO.LOWER_CODE_CNT;
        WS_FMT_DATA.WS_FMT_C_LST_DATE = BPCSMPTY.INFO.C_LST_DATE;
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_NAME);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_CHG_NAME);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMPTY.FUNC == 'C' 
            || BPCSMPTY.FUNC == 'X') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 712;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        if (BPCPARM.PARM_FLG == 'N') {
            if (BPCSMPTY.FUNC == 'Q' 
                || BPCSMPTY.FUNC == 'U' 
                || BPCSMPTY.FUNC == 'D' 
                || BPCSMPTY.FUNC == 'X') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPTY.FUNC == 'C') {
                BPCSMPTY.CHECK_RESULT = 'N';
            }
        }
        if (BPCPARM.PARM_FLG == 'E') {
            if (BPCSMPTY.FUNC == 'A') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPTY.FUNC == 'C') {
                BPCSMPTY.CHECK_RESULT = 'E';
            }
        }
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_VAL);
        if (BPCSMPTY.TYPE.trim().length() > 0 
            && !BPCSMPTY.TYPE.equalsIgnoreCase(WS_PARMT_KEY.WS_PARMT_TYPE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSMPTY.INFO.LVL != ' ' 
            && BPCSMPTY.INFO.LVL != WS_VAL.WS_V_LVL) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0 
            && !BPCSMPTY.INFO.UP_TYPE.equalsIgnoreCase(WS_VAL.WS_V_UP_TYPE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSMPTY.INFO.DOWNLOAD_FLG != ' ' 
            && BPCSMPTY.INFO.DOWNLOAD_FLG != WS_VAL.WS_V_DOWNLOAD_FLG) {
            WS_FIT_COND_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_FIT_COND_FLG);
    }
    public void R000_CHECK_UP_TYPE() throws IOException,SQLException,Exception {
        if (BPCSMPTY.INFO.UP_TYPE.trim().length() > 0) {
            WS_UP_TYPE_DATA.WS_UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
            R000_GET_UP_TYPE_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSMPTY.EXP_DATE);
            CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_EXP_DATE);
            if (BPCSMPTY.EXP_DATE > WS_UP_TYPE_DATA.WS_UP_EXP_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_EXP_DATE_BIGGER, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_TYPE_LVL2 = WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LVL;
            if (WS_TYPE_LVL2 == 'B' 
                || WS_TYPE_LVL2 == 'X') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_LVL_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_DEAL_LVL_CHANGE() throws IOException,SQLException,Exception {
        WS_TYPE_LVL1 = BPCSMPTY.INFO.LVL;
        WS_TYPE_LVL2 = WS_VAL.WS_V_LVL;
        if ((WS_TYPE_LVL2 == 'B' 
            && WS_TYPE_LVL1 == 'T') 
            || (WS_TYPE_LVL2 == 'B' 
            && WS_TYPE_LVL1 == 'M') 
            || (WS_TYPE_LVL2 == 'X' 
            && WS_TYPE_LVL1 == 'T') 
            || (WS_TYPE_LVL2 == 'X' 
            && WS_TYPE_LVL1 == 'M')) {
            CEP.TRC(SCCGWA, WS_VAL.WS_V_LOWER_CODE_CNT);
            if (WS_VAL.WS_V_LOWER_CODE_CNT != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOWER_CODE_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((WS_TYPE_LVL2 == 'T' 
            && WS_TYPE_LVL1 == 'X') 
            || (WS_TYPE_LVL2 == 'T' 
            && WS_TYPE_LVL1 == 'B') 
            || (WS_TYPE_LVL2 == 'M' 
            && WS_TYPE_LVL1 == 'X') 
            || (WS_TYPE_LVL2 == 'M' 
            && WS_TYPE_LVL1 == 'B')) {
            if (WS_VAL.WS_V_LOWER_TYPE_CNT != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOWER_TYPE_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_LOWER_CNT_OF_UP_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = WS_UP_TYPE_DATA.WS_UP_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'U';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20140412A");
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_UP_TYPE_DATA.WS_UP_VAL);
        CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT);
        CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_CODE_CNT);
        if (WS_CNT_FLG == 'A') {
            WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT = WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT + 1;
        } else {
            WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT = WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT - 1;
        }
        CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_TYPE_CNT);
        CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL.WS_U_LOWER_CODE_CNT);
        BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL);
        BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 446;
        CEP.TRC(SCCGWA, WS_UP_TYPE_DATA.WS_UP_VAL);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN);
        BPCPARM.PARM_DATA.PARM_FUNC = 'U';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_GET_UP_TYPE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = WS_UP_TYPE_DATA.WS_UP_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCSMPTY.EFF_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            CEP.TRC(SCCGWA, "20140412B");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_UP_TYPE_DATA.WS_UP_VAL);
        WS_UP_TYPE_DATA.WS_UP_EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSMPTY.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMPTY.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSMPTY.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_HIS_REF.WS_HIS_TYPE = BPCSMPTY.TYPE;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF);
        if (BPCSMPTY.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHPTY);
            BPCOHPTY.NAME = WS_VAL.WS_V_NAME;
            BPCOHPTY.CHG_NAME = WS_VAL.WS_V_CHG_NAME;
            BPCOHPTY.EFF_DATE = WS_OLD_EFF_DATE;
            BPCOHPTY.EXP_DATE = WS_OLD_EXP_DATE;
            BPCOHPTY.LVL = WS_VAL.WS_V_LVL;
            BPCOHPTY.UP_TYPE = WS_VAL.WS_V_UP_TYPE;
            BPCOHPTY.DOWNLOAD_FLG = WS_VAL.WS_V_DOWNLOAD_FLG;
            BPCOHPTY.REMARKS = WS_VAL.WS_V_REMARKS;
            BPCOHPTY.CODE_MAX_LEN = WS_VAL.WS_V_CODE_MAX_LEN;
            BPCOHPTY.DESCE_MAX_LEN = WS_VAL.WS_V_DESCE_MAX_LEN;
            BPCOHPTY.DESCC_MAX_LEN = WS_VAL.WS_V_DESCC_MAX_LEN;
            BPCOHPTY.MAINT_BR1 = WS_VAL.WS_V_MAINT_BR1;
            BPCOHPTY.MAINT_BR2 = WS_VAL.WS_V_MAINT_BR2;
            BPCOHPTY.MAINT_BR3 = WS_VAL.WS_V_MAINT_BR3;
            BPCOHPTY.MAINT_BR4 = WS_VAL.WS_V_MAINT_BR4;
            BPCOHPTY.MAINT_BR5 = WS_VAL.WS_V_MAINT_BR5;
            BPCOHPTY.REP_FLG = WS_VAL.WS_V_REP_FLG;
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHPTY;
            IBS.init(SCCGWA, BPCNHPTY);
            BPCNHPTY.NAME = BPCSMPTY.INFO.NAME;
            BPCNHPTY.CHG_NAME = BPCSMPTY.INFO.CHG_NAME;
            BPCNHPTY.EFF_DATE = BPCSMPTY.EFF_DATE;
            BPCNHPTY.EXP_DATE = BPCSMPTY.EXP_DATE;
            BPCNHPTY.LVL = BPCSMPTY.INFO.LVL;
            BPCNHPTY.UP_TYPE = BPCSMPTY.INFO.UP_TYPE;
            BPCNHPTY.DOWNLOAD_FLG = BPCSMPTY.INFO.DOWNLOAD_FLG;
            BPCNHPTY.REMARKS = BPCSMPTY.INFO.REMARKS;
            BPCNHPTY.CODE_MAX_LEN = BPCSMPTY.INFO.CODE_MAX_LEN;
            BPCNHPTY.DESCE_MAX_LEN = BPCSMPTY.INFO.DESCE_MAX_LEN;
            BPCNHPTY.DESCC_MAX_LEN = BPCSMPTY.INFO.DESCC_MAX_LEN;
            BPCNHPTY.MAINT_BR1 = BPCSMPTY.INFO.MAINT_BR1;
            BPCNHPTY.MAINT_BR2 = BPCSMPTY.INFO.MAINT_BR2;
            BPCNHPTY.MAINT_BR3 = BPCSMPTY.INFO.MAINT_BR3;
            BPCNHPTY.MAINT_BR4 = BPCSMPTY.INFO.MAINT_BR4;
            BPCNHPTY.MAINT_BR5 = BPCSMPTY.INFO.MAINT_BR5;
            BPCNHPTY.REP_FLG = BPCSMPTY.INFO.REP_FLG;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHPTY;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPARM);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EXP_DATE);
        R000_PARM_DATA_PROCESS_NEW();
        if (pgmRtn) return;
        if (BPCPARM.PARM_RC.PARM_RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARM.PARM_RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_TS_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 686;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void R000_PARM_DATA_PROCESS_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_FUNC);
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'A' 
            && BPCPARM.PARM_DATA.PARM_EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_TOO_SMALL, BPCPARM.PARM_RC);
            S000_ERR_MSG_PROC_PARM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_EFF_DATE != 0 
            && BPCPARM.PARM_DATA.PARM_EXP_DATE != 0 
            && BPCPARM.PARM_DATA.PARM_EXP_DATE <= BPCPARM.PARM_DATA.PARM_EFF_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, BPCPARM.PARM_RC);
            S000_ERR_MSG_PROC_PARM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'Q') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            if (BPCPARM.PARM_DATA.PARM_OPT_2 == 'O') {
                BPCPRMM.FUNC = '3';
            } else {
                BPCPRMM.FUNC = '4';
            }
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            if (BPCPARM.PARM_DATA.PARM_EFF_DATE == 0) {
                BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPCPARM.PARM_DATA.PARM_DESC = BPRPRMT.CDESC;
            BPCPARM.PARM_DATA.PARM_DESC_S = BPRPRMT.DESC;
            BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCPRMM.EFF_DT;
            BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCPRMM.EXP_DT;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'A') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPRPRMT.DESC = BPCPARM.PARM_DATA.PARM_DESC_S;
            BPRPRMT.CDESC = BPCPARM.PARM_DATA.PARM_DESC;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPRPRMT.DAT_TXT);
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCPRMM.FUNC = '0';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'U') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPRPRMT.DESC = BPCPARM.PARM_DATA.PARM_DESC_S;
            BPRPRMT.CDESC = BPCPARM.PARM_DATA.PARM_DESC;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL);
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EFF_DATE);
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EXP_DATE);
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
            BPCPRMM.FUNC = '2';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'D') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCPRMM.FUNC = '1';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'B') {
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'S') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPRPARM.KEY.TYPE = BPCPARM.PARM_DATA.PARM_TYPE;
                BPRPARM.KEY.CODE = BPCPARM.PARM_DATA.PARM_CODE;
                BPCRMBPM.FUNC = 'S';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
            }
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'N') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPCRMBPM.FUNC = 'R';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
                BPCPARM.PARM_DATA.PARM_TYPE = BPRPARM.KEY.TYPE;
                BPCPARM.PARM_DATA.PARM_CODE = BPRPARM.KEY.CODE;
                BPCPARM.PARM_DATA.PARM_DESC_S = BPRPARM.DESC;
                BPCPARM.PARM_DATA.PARM_DESC = BPRPARM.CDESC;
                BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = BPRPARM.BLOB_VAL;
                BPCPARM.PARM_DATA.PARM_EFF_DATE = BPRPARM.EFF_DATE;
                BPCPARM.PARM_DATA.PARM_EXP_DATE = BPRPARM.EXP_DATE;
            }
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'E') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPCRMBPM.FUNC = 'E';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_FUNC);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_OPT_2);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TYPE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EFF_DATE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EXP_DATE);
        CEP.TRC(SCCGWA, "IDONTKNOW");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPARM.PARM_RC);
            if (BPCPARM.PARM_RC.PARM_RC_CODE == 180) {
                BPCPARM.PARM_FLG = 'N';
                IBS.init(SCCGWA, BPCPARM.PARM_RC);
            } else {
                S000_ERR_MSG_PROC_PARM();
                if (pgmRtn) return;
            }
        } else {
            if (BPCPRMM.FUNC == '3') {
                BPCPARM.PARM_FLG = 'E';
            }
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            || BPCRMBPM.RETURN_INFO == 'L') {
            BPCPARM.PARM_FLG = 'N';
        } else {
            if (BPCRMBPM.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPARM.PARM_RC);
                S000_ERR_MSG_PROC_PARM();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC_PARM() throws IOException,SQLException,Exception {
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
