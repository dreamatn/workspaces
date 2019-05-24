package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBLBI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRE_CLBI = "BP-R-BRE-CLBI       ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    char K_NUM_3 = '3';
    char K_NUM_4 = '4';
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_BR = 0;
    BPZSBLBI_WS_CLBI_DETAIL WS_CLBI_DETAIL = new BPZSBLBI_WS_CLBI_DETAIL();
    char WS_UNDER_OGRM = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCTLBIB BPCTLBIB = new BPCTLBIB();
    SCCGWA SCCGWA;
    BPCSBLBI BPCSBLBI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBLBI BPCSBLBI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBLBI = BPCSBLBI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBLBI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBLBI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSBLBI.FUNC == 'B') {
                B010_BROWSE_MAIN_PROC();
                if (pgmRtn) return;
            } else if (BPCSBLBI.FUNC == 'P') {
                B010_BROWSE_PVAL_PROC_FOR_CN();
                if (pgmRtn) return;
            } else if (BPCSBLBI.FUNC == 'A') {
                B020_BROWSE_ALL_BR_PROC_FOR_CN();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSBLBI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSBLBI.FUNC == 'B') {
                B010_BROWSE_MAIN_PROC();
                if (pgmRtn) return;
            } else if (BPCSBLBI.FUNC == 'P') {
                B010_BROWSE_PVAL_PROC();
                if (pgmRtn) return;
            } else if (BPCSBLBI.FUNC == 'A') {
                B020_BROWSE_ALL_BR_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSBLBI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_BROWSE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, WS_CLBI_DETAIL);
        BPRCLBI.KEY.VB_BR = BPCSBLBI.BR;
        BPRCLBI.KEY.CCY = BPCSBLBI.CCY;
        BPCTLBIB.INFO.FUNC = '2';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCTLBIB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            B010_04_OUTPUT_DETAIL();
            if (pgmRtn) return;
            WS_CNT += 1;
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
        }
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_PVAL_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, WS_CLBI_DETAIL);
        if (BPCSBLBI.BR == 0) {
            BPRCLBI.KEY.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPRCLBI.KEY.VB_BR = BPCSBLBI.BR;
        }
        if (BPCSBLBI.PLBOX_NO.trim().length() == 0) {
            BPRCLBI.KEY.PLBOX_NO = "%%%%%%";
        } else {
            BPRCLBI.KEY.PLBOX_NO = BPCSBLBI.PLBOX_NO;
        }
        if (BPCSBLBI.CCY.trim().length() == 0) {
            BPRCLBI.KEY.CCY = "%%%";
        } else {
            BPRCLBI.KEY.CCY = BPCSBLBI.CCY;
        }
        if (BPCSBLBI.M_FLG == ' ') {
            BPRCLBI.KEY.M_FLG = ALL.charAt(0);
        } else {
            BPRCLBI.KEY.M_FLG = BPCSBLBI.M_FLG;
        }
        BPRCLBI.KEY.PAR_VAL = BPCSBLBI.PAR_VAL;
        if (BPCSBLBI.PAR_VAL == 0) {
            BPCTLBIB.INFO.FUNC = '3';
        } else {
            BPCTLBIB.INFO.FUNC = '4';
        }
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCTLBIB.RETURN_INFO != 'N') {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            B010_04_OUTPUT_DETAIL();
            if (pgmRtn) return;
            WS_CNT += 1;
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
        }
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ALL_BR_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, WS_CLBI_DETAIL);
        if (BPCSBLBI.BR == 0) {
            BPRCLBI.KEY.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPRCLBI.KEY.VB_BR = BPCSBLBI.BR;
        }
        if (BPCSBLBI.CCY.trim().length() == 0) {
            BPRCLBI.KEY.CCY = "%%%";
        } else {
            BPRCLBI.KEY.CCY = BPCSBLBI.CCY;
        }
        if (BPCSBLBI.M_FLG == ' ') {
            BPRCLBI.KEY.M_FLG = ALL.charAt(0);
        } else {
            BPRCLBI.KEY.M_FLG = BPCSBLBI.M_FLG;
        }
        BPRCLBI.KEY.PAR_VAL = BPCSBLBI.PAR_VAL;
        if (BPCSBLBI.PAR_VAL == 0) {
            BPCTLBIB.INFO.FUNC = '7';
        } else {
            BPCTLBIB.INFO.FUNC = '8';
        }
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCTLBIB.RETURN_INFO != 'N') {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            B010_04_OUTPUT_DETAIL();
            if (pgmRtn) return;
            WS_CNT += 1;
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
        }
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_PVAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, WS_CLBI_DETAIL);
        if (BPCSBLBI.BR == 0) {
            BPRCLBI.KEY.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPRCLBI.KEY.VB_BR = BPCSBLBI.BR;
        }
        if (BPCSBLBI.PLBOX_NO.trim().length() == 0) {
            BPRCLBI.KEY.PLBOX_NO = "%%%%%%";
        } else {
            BPRCLBI.KEY.PLBOX_NO = BPCSBLBI.PLBOX_NO;
        }
        if (BPCSBLBI.CCY.trim().length() == 0) {
            BPRCLBI.KEY.CCY = "%%%";
        } else {
            BPRCLBI.KEY.CCY = BPCSBLBI.CCY;
        }
        if (BPCSBLBI.M_FLG == ' ') {
            BPRCLBI.KEY.M_FLG = ALL.charAt(0);
        } else {
            BPRCLBI.KEY.M_FLG = BPCSBLBI.M_FLG;
        }
        BPRCLBI.KEY.PAR_VAL = BPCSBLBI.PAR_VAL;
        if (BPCSBLBI.PAR_VAL == 0) {
            BPCTLBIB.INFO.FUNC = '3';
        } else {
            BPCTLBIB.INFO.FUNC = '4';
        }
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCTLBIB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000) {
            if (BPRCLBI.KEY.PLBOX_NO == null) BPRCLBI.KEY.PLBOX_NO = "";
            JIBS_tmp_int = BPRCLBI.KEY.PLBOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRCLBI.KEY.PLBOX_NO += " ";
            if (BPRCLBI.KEY.PLBOX_NO == null) BPRCLBI.KEY.PLBOX_NO = "";
            JIBS_tmp_int = BPRCLBI.KEY.PLBOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRCLBI.KEY.PLBOX_NO += " ";
            if (!BPRCLBI.KEY.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_NUM_3+"") 
                && !BPRCLBI.KEY.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_NUM_4+"")) {
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            WS_CNT += 1;
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
        }
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ALL_BR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, WS_CLBI_DETAIL);
        if (BPCSBLBI.CCY.trim().length() == 0) {
            BPRCLBI.KEY.CCY = "%%%";
        } else {
            BPRCLBI.KEY.CCY = BPCSBLBI.CCY;
        }
        if (BPCSBLBI.M_FLG == ' ') {
            BPRCLBI.KEY.M_FLG = ALL.charAt(0);
        } else {
            BPRCLBI.KEY.M_FLG = BPCSBLBI.M_FLG;
        }
        BPRCLBI.KEY.PAR_VAL = BPCSBLBI.PAR_VAL;
        if (BPCSBLBI.PAR_VAL == 0) {
            BPCTLBIB.INFO.FUNC = '5';
        } else {
            BPCTLBIB.INFO.FUNC = '6';
        }
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCTLBIB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000) {
            if (BPRCLBI.KEY.PLBOX_NO == null) BPRCLBI.KEY.PLBOX_NO = "";
            JIBS_tmp_int = BPRCLBI.KEY.PLBOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRCLBI.KEY.PLBOX_NO += " ";
            if (BPRCLBI.KEY.PLBOX_NO == null) BPRCLBI.KEY.PLBOX_NO = "";
            JIBS_tmp_int = BPRCLBI.KEY.PLBOX_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRCLBI.KEY.PLBOX_NO += " ";
            if (!BPRCLBI.KEY.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_NUM_3+"") 
                && !BPRCLBI.KEY.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_NUM_4+"")) {
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            WS_CNT += 1;
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
        }
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 120;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CLBI_DETAIL);
        SCCMPAG.DATA_LEN = 120;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPCSBLBI.FUNC == 'A') {
            WS_CLBI_DETAIL.WS_CLBI_BR = BPCSBLBI.BR;
            WS_CLBI_DETAIL.WS_PLBOX_NO = " ";
        } else {
            WS_CLBI_DETAIL.WS_CLBI_BR = BPRCLBI.KEY.VB_BR;
            WS_CLBI_DETAIL.WS_PLBOX_NO = BPRCLBI.KEY.PLBOX_NO;
        }
        WS_CLBI_DETAIL.WS_CASH_TYPE = BPRCLBI.KEY.CASH_TYP;
        WS_CLBI_DETAIL.WS_CLBI_CCY = BPRCLBI.KEY.CCY;
        WS_CLBI_DETAIL.WS_CLBI_PAR_VAL = BPRCLBI.KEY.PAR_VAL;
        WS_CLBI_DETAIL.WS_CLBI_M_FLG = BPRCLBI.KEY.M_FLG;
        WS_CLBI_DETAIL.WS_CLBI_GD_NUM = BPRCLBI.GD_NUM;
        WS_CLBI_DETAIL.WS_CLBI_BD_NUM = BPRCLBI.BD_NUM;
        WS_CLBI_DETAIL.WS_CLBI_HBD_NUM = BPRCLBI.HBD_NUM;
        WS_CLBI_DETAIL.WS_CLBI_GD_AMT = BPRCLBI.KEY.PAR_VAL * BPRCLBI.GD_NUM;
        WS_CLBI_DETAIL.WS_CLBI_BD_AMT = BPRCLBI.KEY.PAR_VAL * BPRCLBI.BD_NUM;
        WS_CLBI_DETAIL.WS_CLBI_HBD_AMT = BPRCLBI.KEY.PAR_VAL * BPRCLBI.HBD_NUM / 2;
        WS_CLBI_DETAIL.WS_TLR_NO = BPCSBLBI.TLR_NO;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTLBIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLBI, BPCTLBIB);
        if (BPCTLBIB.RC.RC_CODE != 0 
            && BPCTLBIB.RETURN_INFO != 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIB.RC);
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
