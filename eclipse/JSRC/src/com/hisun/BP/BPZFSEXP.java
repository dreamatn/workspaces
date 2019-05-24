package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSEXP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String U_MAINTAIN_FEXP = "BP-F-U-MAINTAIN-FEXP";
    BPZFSEXP_WS_VARIABLES WS_VARIABLES = new BPZFSEXP_WS_VARIABLES();
    BPZFSEXP_WS_COND_FLG WS_COND_FLG = new BPZFSEXP_WS_COND_FLG();
    BPZFSEXP_WS_EXP_OUT_DATE WS_EXP_OUT_DATE = new BPZFSEXP_WS_EXP_OUT_DATE();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOEXPO BPCOEXPO = new BPCOEXPO();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    BPCOFEXP BPCOFEXP;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFEXP BPCOFEXP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOFEXP = BPCOFEXP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSEXP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_EXP_OUT_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOFEXP.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFEXP.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFEXP.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFEXP.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFEXP.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOFEXP.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOFEXP.VAL.EXP_DATE);
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
        S000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
        S000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
        S000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
        S000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        BPCOFEXP.FUNC = 'S';
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
        S000_INITIALIZE_B_MPAG();
        if (pgmRtn) return;
        while (BPCOFEXP.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCOFEXP.FUNC = 'N';
            S000_CALL_BPZFUEXP();
            if (pgmRtn) return;
            if (BPCOFEXP.FOUND_FLG != 'N') {
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        BPCOFEXP.FUNC = 'E';
        S000_CALL_BPZFUEXP();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void S000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOFEXP.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOEXPO;
        SCCFMT.DATA_LEN = 5236;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_INITIALIZE_B_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 882;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT_BROWSE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_EXP_OUT_DATE);
        SCCMPAG.DATA_LEN = 882;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOEXPO);
        BPCOEXPO.KEY.DER_CODE = BPCOFEXP.KEY.DER_CODE;
        BPCOEXPO.VAL.DER_DESC = BPCOFEXP.VAL.DER_DESC;
        BPCOEXPO.VAL.EFF_DATE = BPCOFEXP.VAL.EFF_DATE;
        BPCOEXPO.VAL.EXP_DATE = BPCOFEXP.VAL.EXP_DATE;
        for (WS_VARIABLES.CNT1 = 1; WS_VARIABLES.CNT1 <= 50 
            && WS_COND_FLG.TBL_SEXP_FLAG != 'Y'; WS_VARIABLES.CNT1 += 1) {
            if (BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_CODE.trim().length() > 0) {
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_CODE = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_CODE;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_DESC = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_DESC;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].CCY = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].CCY;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].DER_FLG = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].DER_FLG;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].MAX_PERCENT = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].MAX_PERCENT;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].MAX_AMT = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].MAX_AMT;
                BPCOEXPO.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].AUTH_LVL = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].AUTH_LVL;
                BPCOEXPO.VAL.ARRAY_LENGTH = WS_VARIABLES.CNT1;
            } else {
                WS_COND_FLG.TBL_SEXP_FLAG = 'Y';
                CEP.TRC(SCCGWA, WS_VARIABLES.CNT1);
            }
        }
    }
    public void R000_TRANS_DATA_OUTPUT_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_EXP_OUT_DATE);
        WS_EXP_OUT_DATE.WS_EXPO_KEY.EXPO_DER_CODE = BPCOFEXP.KEY.DER_CODE;
        WS_EXP_OUT_DATE.WS_EXPO_VAL.EXPO_DER_DESC = BPCOFEXP.VAL.DER_DESC;
        WS_VARIABLES.CNT1 = 1;
        for (WS_VARIABLES.CNT1 = 1; WS_VARIABLES.CNT1 <= 50; WS_VARIABLES.CNT1 += 1) {
            WS_EXP_OUT_DATE.WS_EXPO_VAL.WS_EXPO_EXP_DATA[WS_VARIABLES.CNT1-1].EXPO_FEE_CODE = BPCOFEXP.VAL.EXP_DATA[WS_VARIABLES.CNT1-1].FEE_CODE;
        }
        WS_EXP_OUT_DATE.WS_EXPO_VAL.EXPO_EFF_DATE = BPCOFEXP.VAL.EFF_DATE;
        WS_EXP_OUT_DATE.WS_EXPO_VAL.EXPO_EXP_DATE = BPCOFEXP.VAL.EXP_DATE;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_CALL_BPZFUEXP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_MAINTAIN_FEXP, BPCOFEXP);
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
