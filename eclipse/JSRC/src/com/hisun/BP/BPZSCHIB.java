package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCHIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_CHIS = "BP-R-BRW-CHIS ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 30;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSCHIB_WS_THIS_HEAD WS_THIS_HEAD = new BPZSCHIB_WS_THIS_HEAD();
    BPZSCHIB_WS_CHIS_DETAIL WS_CHIS_DETAIL = new BPZSCHIB_WS_CHIS_DETAIL();
    char WS_TBL_THIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPCTCHIB BPCTCHIB = new BPCTCHIB();
    SCCGWA SCCGWA;
    BPCSCHIB BPCSCHIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCHIB BPCSCHIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCHIB = BPCSCHIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCHIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCTCHIB);
        IBS.init(SCCGWA, BPCSCHIB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSCHIB.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCHIS);
        if (BPCSCHIB.START_DT == 0) {
            BPCTCHIB.START_DT = 0;
        } else {
            BPCTCHIB.START_DT = BPCSCHIB.START_DT;
        }
        if (BPCSCHIB.END_DT == 0) {
            BPCTCHIB.END_DT = 99999999;
        } else {
            BPCTCHIB.END_DT = BPCSCHIB.END_DT;
        }
        BPRCHIS.BR = BPCSCHIB.BR;
        BPRCHIS.KEY.TLR = BPCSCHIB.TLR;
        BPRCHIS.KEY.CCY = BPCSCHIB.CCY;
        BPRCHIS.KEY.CASH_TYP = BPCSCHIB.CASH_TYP;
        BPRCHIS.KEY.JRN = BPCSCHIB.JRN;
        BPRCHIS.KEY.CONF_NO = BPCSCHIB.CONF_NO;
        CEP.TRC(SCCGWA, BPRCHIS.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.JRN);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.TLR);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.CCY);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCHIS.BR);
        CEP.TRC(SCCGWA, BPCTCHIB.START_DT);
        CEP.TRC(SCCGWA, BPCTCHIB.END_DT);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.CONF_NO);
        if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '3';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'B';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '4';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'D';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '5';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'F';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '6';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'G';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '7';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'H';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '8';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'I';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = '9';
        } else if (BPCSCHIB.TLR.trim().length() > 0 
                && BPCSCHIB.CCY.trim().length() > 0 
                && BPCSCHIB.JRN != 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'J';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO == 0) {
            BPCTCHIB.INFO.FUNC = 'A';
        } else if (BPCSCHIB.TLR.trim().length() == 0 
                && BPCSCHIB.CCY.trim().length() == 0 
                && BPCSCHIB.JRN == 0 
                && BPCSCHIB.CONF_NO != 0) {
            BPCTCHIB.INFO.FUNC = 'K';
        } else {
            BPCTCHIB.INFO.FUNC = 'A';
        }
        CEP.TRC(SCCGWA, BPCTCHIB.INFO.FUNC);
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        BPCTCHIB.INFO.FUNC = 'N';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCHIS.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.JRN);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.TLR);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.CCY);
        CEP.TRC(SCCGWA, BPRCHIS.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCHIS.BR);
        CEP.TRC(SCCGWA, BPCTCHIB.START_DT);
        CEP.TRC(SCCGWA, BPCTCHIB.END_DT);
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTCHIB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTCHIB.INFO.FUNC = 'N';
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
        }
        BPCTCHIB.INFO.FUNC = 'E';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 85;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_CHIS_DETAIL.WS_CHIS_DATE = BPRCHIS.KEY.AC_DATE;
        WS_CHIS_DETAIL.WS_CHIS_BR = BPRCHIS.BR;
        WS_CHIS_DETAIL.WS_CHIS_TLR = BPRCHIS.KEY.TLR;
        WS_CHIS_DETAIL.WS_CHIS_VB_FLG = BPRCHIS.VB_FLG;
        WS_CHIS_DETAIL.WS_CHIS_PLBOX_NO = BPRCHIS.KEY.PLBOX_NO;
        WS_CHIS_DETAIL.WS_CHIS_CCY = BPRCHIS.KEY.CCY;
        WS_CHIS_DETAIL.WS_CHIS_AMT = BPRCHIS.AMT;
        WS_CHIS_DETAIL.WS_CHIS_STS = BPRCHIS.STS;
        WS_CHIS_DETAIL.WS_CHIS_IN_OUT = BPRCHIS.IN_OUT;
        WS_CHIS_DETAIL.WS_CHIS_CS_KIND = BPRCHIS.CS_KIND;
        WS_CHIS_DETAIL.WS_CHIS_AP_CODE = BPRCHIS.AP_CODE;
        WS_CHIS_DETAIL.WS_TR_CODE = BPRCHIS.TX_CODE;
        WS_CHIS_DETAIL.WS_CHIS_VCH_NO = BPRCHIS.VCH_NO;
        WS_CHIS_DETAIL.WS_CHIS_JRN = BPRCHIS.KEY.JRN;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CHIS_DETAIL);
        SCCMPAG.DATA_LEN = 85;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_BR);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_DATE);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_JRN);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_TLR);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_VB_FLG);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_PLBOX_NO);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_CCY);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_AMT);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_STS);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_IN_OUT);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_CS_KIND);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_AP_CODE);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_TR_CODE);
        CEP.TRC(SCCGWA, WS_CHIS_DETAIL.WS_CHIS_VCH_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CHIS, BPCTCHIB);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCTCHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCHIB.RC);
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
