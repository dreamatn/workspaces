package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTMUP {
    String JIBS_NumStr;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TLR = "BP-R-TLR-MAINTAIN   ";
    String CPN_R_TMUP = "BP-R-MGM-TMUP       ";
    String CPN_R_TMUG = "BP-R-MGM-TMUG       ";
    String CPN_F_ORG = "BP-P-INQ-ORG        ";
    String K_OUTPUT_FMT_1 = "BP508";
    String K_OUTPUT_FMT_2 = "BPX01";
    int K_MAX_DATE = 99991231;
    String K_HIS_REMARK = "PRIVILEGE UPGRADE TEMPORARILY MAINTAIN";
    String K_HIS_COPYBOOK = "BPCTUP  ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_BROWSE_BPTTMUP = "BP-R-MGM-TMUB       ";
    BPZSTMUP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSTMUP_WS_TEMP_VARIABLE();
    BPZSTMUP_WS_OUT_DATE WS_OUT_DATE = new BPZSTMUP_WS_OUT_DATE();
    char WS_OPER_LVL = ' ';
    char WS_AUTH_LVL = ' ';
    char WS_MAX_OPER_LVL = ' ';
    char WS_MAX_AUTH_LVL = ' ';
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTMUP BPRTMUP = new BPRTMUP();
    BPCTUP BPCTUP = new BPCTUP();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCRTMUP BPCRTMUP = new BPCRTMUP();
    BPCRTMUG BPCRTMUG = new BPCRTMUG();
    BPCOTUP1 BPCOTUP1 = new BPCOTUP1();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRTMUB BPCRTMUB = new BPCRTMUB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTMUP BPCSTMUP;
    public void MP(SCCGWA SCCGWA, BPCSTMUP BPCSTMUP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTMUP = BPCSTMUP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTMUP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUP);
        IBS.init(SCCGWA, BPCTUP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTMUP);
        if (BPCSTMUP.FUNC == 'A') {
            B010_TEMP_UPD_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTMUP.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTMUP.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTMUP.FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTMUP.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_TEMP_UPD_PROCESS() throws IOException,SQLException,Exception {
        B010_01_CHECK_INPUT();
        if (pgmRtn) return;
        B010_02_TRANS_TXN();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_OUT;
        BPCRTLTM.INFO.FUNC = 'Q';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.SIGN_STS != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_SIGN_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OPER_LVL = BPRTLT.TX_LVL;
        WS_AUTH_LVL = BPRTLT.ATH_LVL;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_IN;
        BPCRTLTM.INFO.FUNC = 'Q';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.SIGN_STS != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_SIGN_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.TLR_STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSTMUP.FUNC == 'A' 
            || BPCSTMUP.FUNC == 'U') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPRTLT.TLR_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_MAX_OPER_LVL = BPCPQORG.TLR_MAX;
            WS_MAX_AUTH_LVL = BPCPQORG.ATH_MAX;
            if (BPCSTMUP.PRIV_TYP == '0' 
                || BPCSTMUP.PRIV_TYP == '2') {
                if (BPRTLT.TX_LVL > WS_OPER_LVL) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_OPER_LVL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSTMUP.PRIV_TYP == '1' 
                || BPCSTMUP.PRIV_TYP == '2') {
                if (BPRTLT.ATH_LVL > WS_AUTH_LVL) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_AUTH_LVL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSTMUP.FUNC == 'A') {
                if (BPCSTMUP.MOV_EXP_DATE == 0) {
                    BPCSTMUP.MOV_EXP_DATE = K_MAX_DATE;
                }
                if (BPCSTMUP.MOV_EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_MOV_EXP_DATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPRTMUP);
                IBS.init(SCCGWA, BPCRTMUG);
                BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
                BPRTMUP.STS = 'Y';
                BPRTMUP.MOV_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCRTMUG.INFO.FUNC = '1';
                S000_CALL_BPZRTMUG();
                if (pgmRtn) return;
                if (BPCRTMUG.INFO.REC_COUNT > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_HAD_OUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPRTMUP);
                IBS.init(SCCGWA, BPCRTMUG);
                BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_OUT;
                BPRTMUP.STS = 'Y';
                BPRTMUP.MOV_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCRTMUG.INFO.FUNC = '2';
                S000_CALL_BPZRTMUG();
                if (pgmRtn) return;
                if (BPCRTMUG.INFO.REC_COUNT > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_IS_IN;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPRTMUP);
                IBS.init(SCCGWA, BPCRTMUG);
                BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_IN;
                BPRTMUP.STS = 'Y';
                BPRTMUP.MOV_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCRTMUG.INFO.FUNC = '1';
                S000_CALL_BPZRTMUG();
                if (pgmRtn) return;
                if (BPCRTMUG.INFO.REC_COUNT > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_IS_OUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPRTMUP);
                IBS.init(SCCGWA, BPCRTMUG);
                BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
                BPRTMUP.STS = 'Y';
                BPRTMUP.MOV_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCRTMUG.INFO.FUNC = '2';
                S000_CALL_BPZRTMUG();
                if (pgmRtn) return;
                if (BPCRTMUG.INFO.REC_COUNT > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_HAD_IN;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSTMUP.PRIV_TYP == '0' 
                || BPCSTMUP.PRIV_TYP == '2') {
                if (WS_OPER_LVL > WS_MAX_OPER_LVL) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_OVER_MAX_OP;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSTMUP.PRIV_TYP == '1' 
                || BPCSTMUP.PRIV_TYP == '2') {
                if (WS_AUTH_LVL > WS_MAX_AUTH_LVL) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_OVER_MAX_AU;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_02_TRANS_TXN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_IN;
        BPCRTLTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSTMUP.PRIV_TYP == '0' 
            || BPCSTMUP.PRIV_TYP == '2') {
            BPRTLT.TMP_TX_LVL = WS_OPER_LVL;
        }
        if (BPCSTMUP.PRIV_TYP == '1' 
            || BPCSTMUP.PRIV_TYP == '2') {
            BPRTLT.TMP_ATH_LVL = WS_AUTH_LVL;
        }
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCSTMUP.ATTR == '0') {
            IBS.init(SCCGWA, BPRTLT);
            IBS.init(SCCGWA, BPCRTLTM);
            BPRTLT.KEY.TLR = BPCSTMUP.TLR_OUT;
            BPCRTLTM.INFO.FUNC = 'R';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
            if (BPCRTLTM.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSTMUP.PRIV_TYP == '0' 
                || BPCSTMUP.PRIV_TYP == '2') {
                JIBS_NumStr = "" + 0;
                BPRTLT.TMP_TX_LVL = JIBS_NumStr.charAt(0);
            }
            if (BPCSTMUP.PRIV_TYP == '1' 
                || BPCSTMUP.PRIV_TYP == '2') {
                JIBS_NumStr = "" + 0;
                BPRTLT.TMP_ATH_LVL = JIBS_NumStr.charAt(0);
            }
            BPCRTLTM.INFO.FUNC = 'M';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUP);
        BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
        BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
        BPCSTMUP.MOV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPRTMUP.KEY.MOV_JRN_NO = BPCSTMUP.MOV_JRN_NO;
        BPRTMUP.KEY.MOV_DT = BPCSTMUP.MOV_DATE;
        BPRTMUP.STS = 'Y';
        BPRTMUP.MOV_LVL_TYP = BPCSTMUP.PRIV_TYP;
        BPRTMUP.MOV_KIND = BPCSTMUP.ATTR;
        BPRTMUP.MOV_EXP_DT = BPCSTMUP.MOV_EXP_DATE;
        BPRTMUP.UPD_DT = BPCSTMUP.UPD_DATE;
        BPRTMUP.UPD_TLR = BPCSTMUP.UPD_TLR;
        BPCRTMUP.INFO.FUNC = 'A';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
        if (BPCRTMUP.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TMUP_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B020_01_READ_TS_HEAD_TIT();
        if (pgmRtn) return;
        B020_02_OUT_PUT_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_READ_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
    }
    public void B020_02_OUT_PUT_RECORD() throws IOException,SQLException,Exception {
        B020_03_START_BROWSE();
        if (pgmRtn) return;
        while (BPCRTMUB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B020_04_READ_NEXT();
            if (pgmRtn) return;
            if (BPCRTMUB.RETURN_INFO == 'F') {
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        B020_05_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B020_03_START_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTMUP.TLR_OUT);
        CEP.TRC(SCCGWA, BPCSTMUP.TLR_IN);
        CEP.TRC(SCCGWA, BPCSTMUP.MOV_DATE);
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUB);
        if (BPCSTMUP.TLR_OUT.trim().length() > 0 
            || BPCSTMUP.TLR_IN.trim().length() > 0 
            || BPCSTMUP.MOV_DATE != 0) {
            BPRTMUP.KEY.MOV_DT = BPCSTMUP.MOV_DATE;
            if (BPCSTMUP.TLR_OUT.trim().length() > 0) {
                BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
            } else {
                BPRTMUP.KEY.TLR_MOV_OUT = "%%%%%%%%";
            }
            if (BPCSTMUP.TLR_IN.trim().length() > 0) {
                BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
            } else {
                BPRTMUP.KEY.TLR_MOV_IN = "%%%%%%%%";
            }
            BPCRTMUB.INFO.FUNC = '1';
        } else {
            BPCRTMUB.INFO.FUNC = '2';
        }
        CEP.TRC(SCCGWA, BPCRTMUB);
        BPCRTMUB.INFO.POINTER = BPRTMUP;
        BPCRTMUB.INFO.LEN = 63;
        S000_CALL_BPZRTMUB();
        if (pgmRtn) return;
    }
    public void B020_04_READ_NEXT() throws IOException,SQLException,Exception {
        BPCRTMUB.INFO.FUNC = 'N';
        S000_CALL_BPZRTMUB();
        if (pgmRtn) return;
    }
    public void B020_05_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRTMUB.INFO.FUNC = 'E';
        S000_CALL_BPZRTMUB();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        WS_OUT_DATE.WS_TLR_MOV_OUT = BPRTMUP.KEY.TLR_MOV_OUT;
        WS_OUT_DATE.WS_TLR_MOV_IN = BPRTMUP.KEY.TLR_MOV_IN;
        WS_OUT_DATE.WS_MOV_DT = BPRTMUP.KEY.MOV_DT;
        WS_OUT_DATE.WS_MOV_JRN_NO = BPRTMUP.KEY.MOV_JRN_NO;
        WS_OUT_DATE.WS_STS = BPRTMUP.STS;
        WS_OUT_DATE.WS_MOV_LVL_TYP = BPRTMUP.MOV_LVL_TYP;
        WS_OUT_DATE.WS_MOV_KIND = BPRTMUP.MOV_KIND;
        WS_OUT_DATE.WS_MOV_EXP_DT = BPRTMUP.MOV_EXP_DT;
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_OUT_DATE);
        WS_TEMP_VARIABLE.WS_LEN = 47;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUP);
        BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
        BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
        BPRTMUP.KEY.MOV_JRN_NO = BPCSTMUP.MOV_JRN_NO;
        BPRTMUP.KEY.MOV_DT = BPCSTMUP.MOV_DATE;
        BPCRTMUP.INFO.FUNC = 'Q';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
        if (BPCRTMUP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TMUP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B010_01_CHECK_INPUT();
        if (pgmRtn) return;
        B040_02_MODIFY_TMP_LVL();
        if (pgmRtn) return;
    }
    public void B040_02_MODIFY_TMP_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUP);
        BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
        BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
        BPRTMUP.KEY.MOV_JRN_NO = BPCSTMUP.MOV_JRN_NO;
        BPRTMUP.KEY.MOV_DT = BPCSTMUP.MOV_DATE;
        BPCRTMUP.INFO.FUNC = 'R';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
        if (BPCRTMUP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TMUP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTMUP.STS == 'N' 
            || BPRTMUP.MOV_EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSTMUP.MOV_EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_IN;
        BPCRTLTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTMUP.MOV_LVL_TYP == '0' 
            || BPRTMUP.MOV_LVL_TYP == '2') {
            BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
        }
        if (BPRTMUP.MOV_LVL_TYP == '1' 
            || BPRTMUP.MOV_LVL_TYP == '2') {
            BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
        }
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPRTMUP.MOV_KIND == '0') {
            IBS.init(SCCGWA, BPRTLT);
            IBS.init(SCCGWA, BPCRTLTM);
            BPRTLT.KEY.TLR = BPCSTMUP.TLR_OUT;
            BPCRTLTM.INFO.FUNC = 'R';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
            if (BPCRTLTM.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTMUP.MOV_LVL_TYP == '0' 
                || BPRTMUP.MOV_LVL_TYP == '2') {
                BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
            }
            if (BPRTMUP.MOV_LVL_TYP == '1' 
                || BPRTMUP.MOV_LVL_TYP == '2') {
                BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
            }
            BPCRTLTM.INFO.FUNC = 'M';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_IN;
        BPCRTLTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSTMUP.PRIV_TYP == '0' 
            || BPCSTMUP.PRIV_TYP == '2') {
            BPRTLT.TMP_TX_LVL = WS_OPER_LVL;
        }
        if (BPCSTMUP.PRIV_TYP == '1' 
            || BPCSTMUP.PRIV_TYP == '2') {
            BPRTLT.TMP_ATH_LVL = WS_AUTH_LVL;
        }
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCSTMUP.ATTR == '0') {
            IBS.init(SCCGWA, BPRTLT);
            IBS.init(SCCGWA, BPCRTLTM);
            BPRTLT.KEY.TLR = BPCSTMUP.TLR_OUT;
            BPCRTLTM.INFO.FUNC = 'R';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
            if (BPCRTLTM.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSTMUP.PRIV_TYP == '0' 
                || BPCSTMUP.PRIV_TYP == '2') {
                JIBS_NumStr = "" + 0;
                BPRTLT.TMP_TX_LVL = JIBS_NumStr.charAt(0);
            }
            if (BPCSTMUP.PRIV_TYP == '1' 
                || BPCSTMUP.PRIV_TYP == '2') {
                JIBS_NumStr = "" + 0;
                BPRTLT.TMP_ATH_LVL = JIBS_NumStr.charAt(0);
            }
            BPCRTLTM.INFO.FUNC = 'M';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
        }
        BPRTMUP.MOV_LVL_TYP = BPCSTMUP.PRIV_TYP;
        BPRTMUP.MOV_KIND = BPCSTMUP.ATTR;
        BPRTMUP.MOV_EXP_DT = BPCSTMUP.MOV_EXP_DATE;
        BPRTMUP.UPD_DT = BPCSTMUP.UPD_DATE;
        BPRTMUP.UPD_TLR = BPCSTMUP.UPD_TLR;
        BPCRTMUP.INFO.FUNC = 'U';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B010_01_CHECK_INPUT();
        if (pgmRtn) return;
        B050_02_DELETE_TMP_LVL();
        if (pgmRtn) return;
    }
    public void B050_02_DELETE_TMP_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMUP);
        IBS.init(SCCGWA, BPCRTMUP);
        BPRTMUP.KEY.TLR_MOV_OUT = BPCSTMUP.TLR_OUT;
        BPRTMUP.KEY.TLR_MOV_IN = BPCSTMUP.TLR_IN;
        BPRTMUP.KEY.MOV_JRN_NO = BPCSTMUP.MOV_JRN_NO;
        BPRTMUP.KEY.MOV_DT = BPCSTMUP.MOV_DATE;
        BPCRTMUP.INFO.FUNC = 'R';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
        if (BPCRTMUP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TMUP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPRTLT.KEY.TLR = BPCSTMUP.TLR_IN;
        BPCRTLTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPCRTLTM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTMUP.MOV_LVL_TYP == '0' 
            || BPRTMUP.MOV_LVL_TYP == '2') {
            BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
        }
        if (BPRTMUP.MOV_LVL_TYP == '1' 
            || BPRTMUP.MOV_LVL_TYP == '2') {
            BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
        }
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        if (BPRTMUP.MOV_KIND == '0') {
            IBS.init(SCCGWA, BPRTLT);
            IBS.init(SCCGWA, BPCRTLTM);
            BPRTLT.KEY.TLR = BPCSTMUP.TLR_OUT;
            BPCRTLTM.INFO.FUNC = 'R';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
            if (BPCRTLTM.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTMUP.MOV_LVL_TYP == '0' 
                || BPRTMUP.MOV_LVL_TYP == '2') {
                BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
            }
            if (BPRTMUP.MOV_LVL_TYP == '1' 
                || BPRTMUP.MOV_LVL_TYP == '2') {
                BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
            }
            BPCRTLTM.INFO.FUNC = 'M';
            S000_CALL_BPZRTLTM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOTUP1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        BPCRTMUP.INFO.FUNC = 'D';
        S000_CALL_BPZRTMUP();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCTUP.TLR_OUT = BPCSTMUP.TLR_OUT;
        BPCTUP.TLR_IN = BPCSTMUP.TLR_IN;
        BPCTUP.PRIV_TYP = BPCSTMUP.PRIV_TYP;
        BPCTUP.ATTR = BPCSTMUP.ATTR;
        BPCTUP.MOV_DATE = BPCSTMUP.MOV_DATE;
        BPCTUP.MOV_EXP_DATE = BPCSTMUP.MOV_EXP_DATE;
        BPCTUP.UPD_DATE = BPCSTMUP.UPD_DATE;
        BPCTUP.UPD_TLR = BPCSTMUP.UPD_TLR;
        BPCPNHIS.INFO.TX_TYP = 'A';
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSTMUP.FUNC != 'D') {
            IBS.init(SCCGWA, BPCOTUP1);
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSTMUP.FUNC == 'Q') {
            SCCFMT.FMTID = K_OUTPUT_FMT_2;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT_1;
        }
        SCCFMT.DATA_PTR = BPCOTUP1;
        SCCFMT.DATA_LEN = 62;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOTUP1.TLR_OUT = BPRTMUP.KEY.TLR_MOV_OUT;
        BPCOTUP1.TLR_IN = BPRTMUP.KEY.TLR_MOV_IN;
        BPCOTUP1.MOV_JRN_NO = BPRTMUP.KEY.MOV_JRN_NO;
        BPCOTUP1.PRIV_TYP = BPRTMUP.MOV_LVL_TYP;
        BPCOTUP1.ATTR = BPRTMUP.MOV_KIND;
        BPCOTUP1.MOV_DATE = BPRTMUP.KEY.MOV_DT;
        BPCOTUP1.MOV_EXP_DATE = BPRTMUP.MOV_EXP_DT;
        BPCOTUP1.UPD_DATE = BPRTMUP.UPD_DT;
        BPCOTUP1.UPD_TLR = BPRTMUP.UPD_TLR;
    }
    public void S000_CALL_BPZRTMUP() throws IOException,SQLException,Exception {
        BPCRTMUP.INFO.POINTER = BPRTMUP;
        BPCRTMUP.INFO.LEN = 63;
        IBS.CALLCPN(SCCGWA, CPN_R_TMUP, BPCRTMUP);
        if (BPCRTMUP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMUP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTMUG() throws IOException,SQLException,Exception {
        BPCRTMUG.INFO.POINTER = BPRTMUP;
        BPCRTMUG.INFO.LEN = 63;
        IBS.CALLCPN(SCCGWA, CPN_R_TMUG, BPCRTMUG);
        if (BPCRTMUG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMUG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTMUB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BROWSE_BPTTMUP, BPCRTMUB);
        if (BPCRTMUB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMUB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
