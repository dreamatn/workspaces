package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPCD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "PARMETER CODE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHPCD";
    String K_PARM_TYPE_TYPE = "PARMT";
    String K_PARM_CODE_TYPE = "PARMC";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP351";
    BPZSMPCD_WS_RC WS_RC = new BPZSMPCD_WS_RC();
    short WS_I = 0;
    char WS_TYPE_LVL = ' ';
    BPZSMPCD_WS_PARMT_KEY WS_PARMT_KEY = new BPZSMPCD_WS_PARMT_KEY();
    BPZSMPCD_WS_PARMT_VAL WS_PARMT_VAL = new BPZSMPCD_WS_PARMT_VAL();
    BPZSMPCD_WS_PARMC_KEY WS_PARMC_KEY = new BPZSMPCD_WS_PARMC_KEY();
    BPZSMPCD_WS_PARMC_VAL WS_PARMC_VAL = new BPZSMPCD_WS_PARMC_VAL();
    BPZSMPCD_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMPCD_WS_OUTPUT_DATA();
    BPZSMPCD_WS_FMT_DATA WS_FMT_DATA = new BPZSMPCD_WS_FMT_DATA();
    BPZSMPCD_WS_HIS_REF WS_HIS_REF = new BPZSMPCD_WS_HIS_REF();
    String WS_OLD_NAME = " ";
    String WS_OLD_NAME_S = " ";
    int WS_OLD_EFF_DATE = 0;
    int WS_OLD_EXP_DATE = 0;
    short WS_CNT = 0;
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_CNT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHPCD BPCOHPCD = new BPCHPCD();
    BPCHPCD BPCNHPCD = new BPCHPCD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMPCD BPCSMPCD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMPCD BPCSMPCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPCD = BPCSMPCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMPCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPCD);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMPCD.FUNC == 'Q'
            || BPCSMPCD.FUNC == 'X'
            || BPCSMPCD.FUNC == 'A'
            || BPCSMPCD.FUNC == 'U'
            || BPCSMPCD.FUNC == 'C'
            || BPCSMPCD.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPCD.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPCD.FUNC == 'I') {
            B230_BROWSE_CHECK_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSMPCD.OUTPUT_FLG == 'Y' 
            || BPCSMPCD.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPCD.TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPCD.TYPE.trim().length() > 0) {
            if (BPCSMPCD.FUNC == 'A' 
                || BPCSMPCD.FUNC == 'C' 
                || BPCSMPCD.FUNC == 'U') {
                R000_CHECK_PARM_TYPE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "3");
        if (BPCSMPCD.FUNC != 'B') {
            if (BPCSMPCD.CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "4");
        if (BPCSMPCD.FUNC == 'A') {
            if (BPCSMPCD.EFF_DATE == 0 
                || BPCSMPCD.EXP_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMPCD.EFF_DATE != 0 
            && BPCSMPCD.EXP_DATE != 0 
            && BPCSMPCD.EXP_DATE < BPCSMPCD.EFF_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
        WS_PARMC_KEY.WS_PARMC_TYPE = BPCSMPCD.TYPE;
        WS_PARMC_KEY.WS_PARMC_CODE = BPCSMPCD.CODE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMC_KEY);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCSMPCD.EFF_DATE;
        BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCSMPCD.EXP_DATE;
        BPCPARM.PARM_DATA.PARM_DESC_S = BPCSMPCD.INFO.CODE_NAME;
        BPCPARM.PARM_DATA.PARM_DESC = BPCSMPCD.INFO.CODE_NAME_S;
        CEP.TRC(SCCGWA, BPCSMPCD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSMPCD.EXP_DATE);
        if (BPCSMPCD.FUNC == 'A') {
            BPCSMPCD.INFO.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSMPCD.FUNC == 'A' 
            || BPCSMPCD.FUNC == 'U') {
            BPCSMPCD.INFO.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMPCD.INFO.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 684;
        BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPCSMPCD.INFO);
        if (BPCSMPCD.FUNC == 'Q'
            || BPCSMPCD.FUNC == 'X'
            || BPCSMPCD.FUNC == 'C') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        } else if (BPCSMPCD.FUNC == 'A') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'A';
        } else if (BPCSMPCD.FUNC == 'U'
            || BPCSMPCD.FUNC == 'D') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'U';
        }
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSMPCD.FUNC == 'A' 
            || BPCSMPCD.FUNC == 'Q' 
            || BPCSMPCD.FUNC == 'X' 
            || (BPCSMPCD.FUNC == 'C' 
            && BPCPARM.PARM_FLG == 'E')) {
            BPCSMPCD.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCSMPCD.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPCD.INFO);
            BPCSMPCD.CHECK_RESULT = 'E';
        }
        if (BPCSMPCD.FUNC == 'A') {
            WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPCD.TYPE;
            WS_CNT_FLG = 'A';
            R000_LOWER_CODE_CNT();
            if (pgmRtn) return;
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPCD.FUNC == 'D') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'D';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            BPCSMPCD.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCSMPCD.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPCD.INFO);
            WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPCD.TYPE;
            WS_CNT_FLG = 'S';
            R000_LOWER_CODE_CNT();
            if (pgmRtn) return;
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPCD.FUNC == 'U') {
            WS_OLD_NAME = BPCPARM.PARM_DATA.PARM_DESC;
            WS_OLD_NAME_S = BPCPARM.PARM_DATA.PARM_DESC_S;
            WS_OLD_EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            WS_OLD_EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMC_VAL);
            CEP.TRC(SCCGWA, "DATA COMPARE");
            CEP.TRC(SCCGWA, WS_OLD_EFF_DATE);
            CEP.TRC(SCCGWA, WS_OLD_EXP_DATE);
            CEP.TRC(SCCGWA, BPCSMPCD.EFF_DATE);
            CEP.TRC(SCCGWA, BPCSMPCD.EXP_DATE);
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL);
            CEP.TRC(SCCGWA, BPCSMPCD.INFO);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPCD.INFO);
            if (BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL.equalsIgnoreCase(JIBS_tmp_str[0])) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCPARM.PARM_DATA.PARM_FUNC = 'U';
            BPCPARM.PARM_DATA.PARM_DESC_S = BPCSMPCD.INFO.CODE_NAME;
            BPCPARM.PARM_DATA.PARM_DESC = BPCSMPCD.INFO.CODE_NAME_S;
            BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCSMPCD.EFF_DATE;
            BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCSMPCD.EXP_DATE;
            BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 684;
            BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPCSMPCD.INFO);
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            BPCSMPCD.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCSMPCD.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCSMPCD.INFO.CODE_NAME_S = BPCPARM.PARM_DATA.PARM_DESC;
            BPCSMPCD.INFO.CODE_NAME = BPCPARM.PARM_DATA.PARM_DESC_S;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMPCD.INFO);
            WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPCD.TYPE;
            WS_CNT_FLG = 'N';
            R000_LOWER_CODE_CNT();
            if (pgmRtn) return;
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPCD.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
        WS_PARMC_KEY.WS_PARMC_TYPE = BPCSMPCD.TYPE;
        WS_PARMC_KEY.WS_PARMC_CODE = BPCSMPCD.CODE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMC_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TYPE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
        if (BPCSMPCD.OUTPUT_FLG == 'Y') {
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
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE, WS_PARMC_KEY);
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMC_VAL);
                CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
                CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL);
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSMPCD.OUTPUT_FLG == 'Y' 
                    && WS_FIT_COND_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
            WS_CNT = (short) (WS_CNT + 1);
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B230_BROWSE_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMPCD.TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_INPUT_TYPE_CODE, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = BPCSMPCD.TYPE;
        BPCPARM.PARM_DATA.PARM_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCSMPCD.EXP_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMT_VAL);
        if (WS_PARMT_VAL.WS_T_LVL == 'T' 
            || WS_PARMT_VAL.WS_T_LVL == 'M') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_PARMT_VAL.WS_T_REP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_CODE_TYPE;
        WS_PARMC_KEY.WS_PARMC_TYPE = BPCSMPCD.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMC_KEY);
        BPCPARM.PARM_DATA.PARM_FUNC = 'B';
        BPCPARM.PARM_DATA.PARM_OPT_1 = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
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
                IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMC_VAL);
            }
            if (WS_PARMC_KEY.WS_PARMC_TYPE.equalsIgnoreCase(BPCSMPCD.TYPE)) {
                if (BPCSMPCD.INFO.CODE_NAME.equalsIgnoreCase(WS_PARMC_VAL.WS_C_CODE_NAME) 
                    && BPCSMPCD.INFO.CODE_NAME_S.equalsIgnoreCase(WS_PARMC_VAL.WS_C_CODE_NAME_S) 
                    && !BPCSMPCD.CODE.equalsIgnoreCase(BPCPARM.PARM_DATA.PARM_CODE)) {
                    WS_STOP_FLG = 'Y';
                    BPCSMPCD.CHECK_RESULT = 'E';
                }
            } else {
                WS_STOP_FLG = 'Y';
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
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 515;
            SCCMPAG.SCR_ROW_CNT = 10;
            SCCMPAG.SCR_COL_CNT = 0;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_TYPE = WS_PARMC_KEY.WS_PARMC_TYPE;
            WS_OUTPUT_DATA.WS_OT_CODE = WS_PARMC_KEY.WS_PARMC_CODE;
            WS_OUTPUT_DATA.WS_OT_CODE_NAME = WS_PARMC_VAL.WS_C_CODE_NAME;
            WS_OUTPUT_DATA.WS_OT_CODE_NAME_S = WS_PARMC_VAL.WS_C_CODE_NAME_S;
            WS_OUTPUT_DATA.WS_OT_EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            WS_OUTPUT_DATA.WS_OT_EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            WS_PARMT_KEY.WS_PARMT_TYPE = WS_PARMC_KEY.WS_PARMC_TYPE;
            R000_GET_TYPE_INFO();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_NAME = WS_PARMT_VAL.WS_T_NAME;
            WS_OUTPUT_DATA.WS_OT_CHG_NAME = WS_PARMT_VAL.WS_T_CHG_NAME;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 515;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_TYPE = BPCSMPCD.TYPE;
        WS_FMT_DATA.WS_FMT_NAME = BPCSMPCD.TYPE_NAME;
        WS_FMT_DATA.WS_FMT_CHG_NAME = BPCSMPCD.TYPE_CHG_NAME;
        WS_FMT_DATA.WS_FMT_CODE = BPCSMPCD.CODE;
        WS_FMT_DATA.WS_FMT_CODE_CNAME = BPCSMPCD.INFO.CODE_NAME;
        WS_FMT_DATA.WS_FMT_CODE_ENAME = BPCSMPCD.INFO.CODE_NAME_S;
        WS_FMT_DATA.WS_FMT_REMARKS = BPCSMPCD.INFO.REMARKS;
        WS_FMT_DATA.WS_FMT_EFF_DATE = BPCSMPCD.EFF_DATE;
        WS_FMT_DATA.WS_FMT_EXP_DATE = BPCSMPCD.EXP_DATE;
        WS_FMT_DATA.WS_FMT_OPEN_DATE = BPCSMPCD.INFO.OPEN_DATE;
        WS_FMT_DATA.WS_FMT_LST_DATE = BPCSMPCD.INFO.LST_DATE;
        WS_FMT_DATA.WS_FMT_LST_TLT = BPCSMPCD.INFO.LST_TLT;
        WS_FMT_DATA.WS_FMT_RBASE_TYP = BPCSMPCD.INFO.RBASE_TYP;
        CEP.TRC(SCCGWA, BPCSMPCD.INFO.RBASE_TYP);
        CEP.TRC(SCCGWA, BPCSMPCD.INFO.LST_DATE);
        CEP.TRC(SCCGWA, WS_FMT_DATA.WS_FMT_LST_DATE);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMPCD.FUNC == 'C' 
            || BPCSMPCD.FUNC == 'X') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 960;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        if (BPCPARM.PARM_FLG == 'N') {
            if (BPCSMPCD.FUNC == 'Q' 
                || BPCSMPCD.FUNC == 'U' 
                || BPCSMPCD.FUNC == 'D' 
                || BPCSMPCD.FUNC == 'X') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPCD.FUNC == 'C') {
                BPCSMPCD.CHECK_RESULT = 'N';
            }
        }
        if (BPCPARM.PARM_FLG == 'E') {
            if (BPCSMPCD.FUNC == 'A') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_CODE_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPCD.FUNC == 'C') {
                BPCSMPCD.CHECK_RESULT = 'E';
            }
        }
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        if (BPCSMPCD.TYPE.trim().length() > 0 
            && !BPCSMPCD.TYPE.equalsIgnoreCase(WS_PARMC_KEY.WS_PARMC_TYPE)) {
            WS_FIT_COND_FLG = 'N';
            WS_STOP_FLG = 'Y';
        }
        if (BPCSMPCD.CODE.trim().length() > 0 
            && !BPCSMPCD.CODE.equalsIgnoreCase(WS_PARMC_KEY.WS_PARMC_CODE)) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_CHECK_PARM_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        WS_PARMT_KEY.WS_PARMT_TYPE = BPCSMPCD.TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMT_VAL);
        if (BPCSMPCD.FUNC == 'A' 
            || BPCSMPCD.FUNC == 'U') {
            if (BPCSMPCD.EXP_DATE > BPCPARM.PARM_DATA.PARM_EXP_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CODE_EXP_DATE_BIGGER, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_TYPE_LVL = WS_PARMT_VAL.WS_T_LVL;
        if (WS_TYPE_LVL == 'T' 
            || WS_TYPE_LVL == 'M') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_LVL_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_LOWER_CODE_CNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'U';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMT_VAL);
        WS_PARMT_VAL.WS_T_C_LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_CNT_FLG == 'A') {
            WS_PARMT_VAL.WS_T_LOWER_CODE_CNT = WS_PARMT_VAL.WS_T_LOWER_CODE_CNT + 1;
        }
        if (WS_CNT_FLG == 'S') {
            WS_PARMT_VAL.WS_T_LOWER_CODE_CNT = WS_PARMT_VAL.WS_T_LOWER_CODE_CNT - 1;
        }
        BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, WS_PARMT_VAL);
        BPCPARM.PARM_DATA.PARM_TXT.PARM_LEN = 446;
        BPCPARM.PARM_DATA.PARM_FUNC = 'U';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_GET_TYPE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, WS_PARMT_KEY);
        BPCPARM.PARM_DATA.PARM_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMT_VAL);
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSMPCD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            IBS.init(SCCGWA, BPCNHPCD);
            BPCNHPCD.NAME = BPCSMPCD.INFO.CODE_NAME;
            BPCNHPCD.NAME_S = BPCSMPCD.INFO.CODE_NAME_S;
            BPCNHPCD.EFF_DATE = BPCSMPCD.EFF_DATE;
            BPCNHPCD.EXP_DATE = BPCSMPCD.EXP_DATE;
            BPCNHPCD.REMARKS = BPCSMPCD.INFO.REMARKS;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHPCD;
        }
        if (BPCSMPCD.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            IBS.init(SCCGWA, BPCOHPCD);
            BPCOHPCD.NAME = WS_PARMC_VAL.WS_C_CODE_NAME;
            BPCOHPCD.NAME_S = WS_PARMC_VAL.WS_C_CODE_NAME_S;
            BPCOHPCD.EFF_DATE = WS_OLD_EFF_DATE;
            BPCOHPCD.EXP_DATE = WS_OLD_EXP_DATE;
            BPCOHPCD.REMARKS = WS_PARMC_VAL.WS_C_REMARKS;
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHPCD;
        }
        if (BPCSMPCD.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_HIS_REF.WS_HIS_TYPE = BPCSMPCD.TYPE;
        WS_HIS_REF.WS_HIS_CODE = BPCSMPCD.CODE;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF);
        if (BPCSMPCD.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHPCD);
            BPCOHPCD.NAME = WS_PARMC_VAL.WS_C_CODE_NAME;
            BPCOHPCD.NAME_S = WS_PARMC_VAL.WS_C_CODE_NAME_S;
            BPCOHPCD.EFF_DATE = WS_OLD_EFF_DATE;
            BPCOHPCD.EXP_DATE = WS_OLD_EXP_DATE;
            BPCOHPCD.REMARKS = WS_PARMC_VAL.WS_C_REMARKS;
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHPCD;
            IBS.init(SCCGWA, BPCNHPCD);
            BPCNHPCD.NAME = BPCSMPCD.INFO.CODE_NAME;
            BPCNHPCD.NAME_S = BPCSMPCD.INFO.CODE_NAME_S;
            BPCNHPCD.EFF_DATE = BPCSMPCD.EFF_DATE;
            BPCNHPCD.EXP_DATE = BPCSMPCD.EXP_DATE;
            BPCNHPCD.REMARKS = BPCSMPCD.INFO.REMARKS;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHPCD;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        R000_PARM_DATA_PROCESS_NEW();
        if (pgmRtn) return;
        if (BPCPARM.PARM_RC.PARM_RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARM.PARM_RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
