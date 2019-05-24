package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCHPW {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_QUERY_BK_PSW = "BP-P-QUERY-BKPSW    ";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_L = 0;
    int WS_CNT = 0;
    int WS_CNT_CAP = 0;
    int WS_CNT_LOWER = 0;
    int WS_CNT_NUM = 0;
    int WS_CNT_SPE = 0;
    short WS_PSW_LEN = 0;
    short WS_TLR_LEN = 0;
    int WS_CNT_CONTIN = 0;
    char WS_TEMP_CHAR = ' ';
    char WS_SPECIAL_CHAR = ' ';
    String WS_INFO = " ";
    char WS_TBL_TXIF_FLAG = ' ';
    char WS_PSW_CHAR_PRO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTLT BPRTLT = new BPRTLT();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCPASS SCCPASS = new SCCPASS();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCPQBPW BPCPQBPW = new BPCPQBPW();
    SCCGWA SCCGWA;
    BPCFCHPW BPCFCHPW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFCHPW BPCFCHPW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCHPW = BPCFCHPW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCHPW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFOR INIT");
        CEP.TRC(SCCGWA, BPCFCHPW);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQBPW);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, SCCENPSW);
        CEP.TRC(SCCGWA, "AFTER INIT");
        CEP.TRC(SCCGWA, BPCFCHPW);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSW_LEN = 20;
        WS_TLR_LEN = 8;
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        CEP.TRC(SCCGWA, WS_TLR_LEN);
        B010_QUERY_BKPSW_AND_TLT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_BKPSW_AND_TLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        BPCRTLTM.INFO.FUNC = 'Q';
        BPRTLT.KEY.TLR = BPCFCHPW.TLR;
        BPCRTLTM.INFO.LEN = 1404;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQBPW);
        BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQBPW.DATA_INFO.PSW_TYP = BPRTLT.PSW_TYP;
        S000_CALL_BPZPQBPW();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_CHECK_PSW_REPEAT_TIME();
        if (pgmRtn) return;
        WS_PSW_CHAR_PRO = 'N';
        if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
        JIBS_tmp_int = BPCFCHPW.PSW.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
        for (WS_CNT = 1; WS_CNT <= WS_PSW_LEN 
            && BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() != 0; WS_CNT += 1) {
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            if () {
                WS_CNT_NUM += 1;
                WS_PSW_CHAR_PRO = 'Y';
            }
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            if () {
                WS_CNT_LOWER += 1;
                WS_PSW_CHAR_PRO = 'Y';
            }
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            if () {
                WS_CNT_CAP += 1;
                WS_PSW_CHAR_PRO = 'Y';
            }
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            WS_SPECIAL_CHAR = BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
            if ((WS_SPECIAL_CHAR == '!' 
                || WS_SPECIAL_CHAR == '@' 
                || WS_SPECIAL_CHAR == '#' 
                || WS_SPECIAL_CHAR == '$' 
                || WS_SPECIAL_CHAR == '%' 
                || WS_SPECIAL_CHAR == '5' 
                || WS_SPECIAL_CHAR == '&' 
                || WS_SPECIAL_CHAR == '*' 
                || WS_SPECIAL_CHAR == '(' 
                || WS_SPECIAL_CHAR == ')')) {
                WS_CNT_SPE += 1;
                WS_PSW_CHAR_PRO = 'Y';
            }
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            if (BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase(WS_TEMP_CHAR+"")) {
                WS_CNT_CONTIN += 1;
            } else {
                WS_CNT_CONTIN = 1;
            }
            if (WS_PSW_CHAR_PRO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_CHAR_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "TEST");
            CEP.TRC(SCCGWA, WS_CNT_CONTIN);
            CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.CON_MAX_NUM);
            if (BPCPQBPW.DATA_INFO.CON_MAX_NUM > 0) {
                if (WS_CNT_CONTIN >= BPCPQBPW.DATA_INFO.CON_MAX_NUM) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_CON_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            WS_TEMP_CHAR = BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
            WS_PSW_CHAR_PRO = 'N';
        }
        WS_CNT += -1;
        if (WS_CNT < BPCPQBPW.DATA_INFO.LEN_MIN 
            || WS_CNT > BPCPQBPW.DATA_INFO.LEN_MAX) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_LENGTH_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CNT_NUM < BPCPQBPW.DATA_INFO.NUM_MIN 
            || WS_CNT_NUM > BPCPQBPW.DATA_INFO.NUM_MAX) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_NUMBER_E;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CNT_CAP < BPCPQBPW.DATA_INFO.CAP_MIN 
            || WS_CNT_CAP > BPCPQBPW.DATA_INFO.CAP_MAX) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_UPPER_ER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CNT_LOWER < BPCPQBPW.DATA_INFO.LOW_MIN 
            || WS_CNT_LOWER > BPCPQBPW.DATA_INFO.LOW_MAX) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_LOWER_ER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CNT_SPE < BPCPQBPW.DATA_INFO.SPE_C_MIN 
            || WS_CNT_SPE > BPCPQBPW.DATA_INFO.SPE_C_MAX) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_SPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_I = 0;
        if (WS_CNT_NUM > 0) {
            WS_I += 1;
        }
        if (WS_CNT_CAP > 0) {
            WS_I += 1;
        }
        if (WS_CNT_LOWER > 0) {
            WS_I += 1;
        }
        if (WS_CNT_SPE > 0) {
            WS_I += 1;
        }
        if (WS_I < BPCPQBPW.DATA_INFO.ELEMENT_NUM) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_ELE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BEFORE-CHECK-CON");
        if (BPCPQBPW.DATA_INFO.USER_CON_MAX > 0) {
            if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
            JIBS_tmp_int = BPCFCHPW.PSW.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
            for (WS_CNT = 1; WS_CNT <= WS_PSW_LEN 
                && BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() != 0 
                && WS_K <= WS_PSW_LEN; WS_CNT += 1) {
                for (WS_J = 1; WS_J <= WS_TLR_LEN 
                    && WS_L <= WS_PSW_LEN; WS_J += 1) {
                    if (BPCFCHPW.PSW == null) BPCFCHPW.PSW = "";
                    JIBS_tmp_int = BPCFCHPW.PSW.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPCFCHPW.PSW += " ";
                    if (BPCFCHPW.TLR == null) BPCFCHPW.TLR = "";
                    JIBS_tmp_int = BPCFCHPW.TLR.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) BPCFCHPW.TLR += " ";
                    if (BPCFCHPW.PSW.substring(WS_CNT - 1, WS_CNT + BPCPQBPW.DATA_INFO.USER_CON_MAX - 1).equalsIgnoreCase(BPCFCHPW.TLR.substring(WS_J - 1, WS_J + BPCPQBPW.DATA_INFO.USER_CON_MAX - 1))) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NUM_OF_USER_ERR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    WS_L = WS_J + BPCPQBPW.DATA_INFO.USER_CON_MAX;
                }
                WS_K = WS_CNT + BPCPQBPW.DATA_INFO.USER_CON_MAX;
            }
        }
        CEP.TRC(SCCGWA, "END-CHECK-CON");
    }
    public void B030_CHECK_PSW_REPEAT_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.LEN = WS_PSW_LEN;
        SCCPASS.KEY = BPCFCHPW.TLR;
        SCCPASS.DATA = BPCFCHPW.PSW;
        S000_CALL_BPZPASS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAAAA");
        if (BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[10-1].LAST_KBPSW.trim().length() > 0) {
            CEP.TRC(SCCGWA, "11111");
            WS_I = 10 - BPCPQBPW.DATA_INFO.RE_FLG_TIMES;
            for (WS_CNT = 10; WS_CNT > WS_I; WS_CNT += -1) {
                CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_CNT-1].LAST_KBPSW);
                if (SCCPASS.DATA.equalsIgnoreCase(BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_CNT-1].LAST_KBPSW)) {
                    WS_INFO = " ";
                    if (WS_INFO == null) WS_INFO = "";
                    JIBS_tmp_int = WS_INFO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                    WS_INFO = "T" + WS_INFO.substring(1);
                    if (WS_INFO == null) WS_INFO = "";
                    JIBS_tmp_int = WS_INFO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                    JIBS_tmp_str[0] = "" + BPCPQBPW.DATA_INFO.RE_FLG_TIMES;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_INFO = WS_INFO.substring(0, 2 - 1) + JIBS_tmp_str[0] + WS_INFO.substring(2 + 2 - 1);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_OLD_PSWD_TIMES;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            CEP.TRC(SCCGWA, "22222");
            for (WS_CNT = 10; WS_CNT != 0 
                && BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_CNT-1].LAST_KBPSW.trim().length() <= 0; WS_CNT += -1) {
                CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_CNT-1].LAST_KBPSW);
            }
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.RE_FLG_TIMES);
            if (WS_CNT != 0) {
                WS_J = 0;
                for (WS_I = WS_CNT; WS_I != 0 
                    && WS_J < BPCPQBPW.DATA_INFO.RE_FLG_TIMES; WS_I += -1) {
                    CEP.TRC(SCCGWA, SCCPASS.DATA);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_I-1].LAST_KBPSW);
                    if (SCCPASS.DATA.equalsIgnoreCase(BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_I-1].LAST_KBPSW)) {
                        WS_INFO = " ";
                        if (WS_INFO == null) WS_INFO = "";
                        JIBS_tmp_int = WS_INFO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                        WS_INFO = "T" + WS_INFO.substring(1);
                        if (WS_INFO == null) WS_INFO = "";
                        JIBS_tmp_int = WS_INFO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                        JIBS_tmp_str[0] = "" + BPCPQBPW.DATA_INFO.RE_FLG_TIMES;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        WS_INFO = WS_INFO.substring(0, 2 - 1) + JIBS_tmp_str[0] + WS_INFO.substring(2 + 2 - 1);
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_OLD_PSWD_TIMES;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    WS_J += 1;
                }
            }
        }
    }
    public void S000_CALL_BPZPQBPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BK_PSW, BPCPQBPW);
        if (BPCPQBPW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBPW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCHPW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        CEP.TRC(SCCGWA, BPCRTLTM.RC.RC_CODE);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ENCRYPT_PASSWORD, SCCENPSW);
        CEP.TRC(SCCGWA, SCCENPSW.RC.RC_CODE);
        if (SCCENPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCENPSW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPASS() throws IOException,SQLException,Exception {
        SCZPASS SCZPASS = new SCZPASS();
        SCZPASS.MP(SCCGWA, SCCPASS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.TRC(SCCGWA, WS_INFO);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCHPW.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCHPW = ");
            CEP.TRC(SCCGWA, BPCFCHPW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
