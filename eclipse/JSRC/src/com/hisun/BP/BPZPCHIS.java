package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCHIS {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_MAINT_CHIS = "BP-R-ADW-CHIS";
    String CPN_R_MAINT_HPAR = "BP-R-ADW-HPAR";
    int K_PAR_MAX_CNT = 20;
    String WS_ERR_MSG = " ";
    int WS_PAR_CNT = 0;
    int WS_CONF_NO = 0;
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPRHPAR BPRHPAR = new BPRHPAR();
    BPCRCHIS BPCRCHIS = new BPCRCHIS();
    BPCRHPAR BPCRHPAR = new BPCRHPAR();
    BPCTCHIB BPCTCHIB = new BPCTCHIB();
    SCCGWA SCCGWA;
    BPCPCHIS BPCPCHIS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPCHIS BPCPCHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCHIS = BPCPCHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_WRITE_HISTORY();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPCHIS.TLR.trim().length() == 0 
            || BPCPCHIS.CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, BPCPCHIS.TLR);
            CEP.TRC(SCCGWA, BPCPCHIS.CCY);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPCHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_HISTORY() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCRCHIS);
            IBS.init(SCCGWA, BPRCHIS);
            BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRCHIS.KEY.JRN = SCCGWA.COMM_AREA.JRN_NO;
            BPRCHIS.KEY.TLR = BPCPCHIS.TLR;
            BPRCHIS.KEY.PLBOX_NO = BPCPCHIS.PLBOX_NO;
            BPRCHIS.KEY.CCY = BPCPCHIS.CCY;
            BPRCHIS.KEY.CASH_TYP = BPCPCHIS.CASH_TYP;
            BPRCHIS.AMT = BPCPCHIS.AMT;
            BPRCHIS.KEY.CONF_NO = BPCPCHIS.CONF_SEQ;
            BPRCHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCHIS.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            BPRCHIS.STS = '0';
            BPRCHIS.VB_FLG = BPCPCHIS.VB_FLG;
            BPRCHIS.IN_OUT = BPCPCHIS.IN_OUT;
            BPRCHIS.CS_KIND = BPCPCHIS.CS_KIND;
            BPRCHIS.AP_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_AP;
            JIBS_tmp_int = BPRCHIS.AP_CODE.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPRCHIS.AP_CODE = "0" + BPRCHIS.AP_CODE;
            BPRCHIS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = BPRCHIS.TX_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) BPRCHIS.TX_CODE = "0" + BPRCHIS.TX_CODE;
            BPRCHIS.CONTR_AC = BPCPCHIS.CONTR_AC;
            CEP.TRC(SCCGWA, "DEV");
            CEP.TRC(SCCGWA, BPRCHIS.CONTR_AC);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.AC_DATE);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.JRN);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.TLR);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.CCY);
            CEP.TRC(SCCGWA, BPRCHIS.KEY.CASH_TYP);
            CEP.TRC(SCCGWA, BPRCHIS.AMT);
            CEP.TRC(SCCGWA, BPRCHIS.BR);
            CEP.TRC(SCCGWA, BPRCHIS.VCH_NO);
            CEP.TRC(SCCGWA, BPRCHIS.VB_FLG);
            CEP.TRC(SCCGWA, BPRCHIS.IN_OUT);
            CEP.TRC(SCCGWA, BPRCHIS.CS_KIND);
            CEP.TRC(SCCGWA, BPRCHIS.AP_CODE);
            CEP.TRC(SCCGWA, BPRCHIS.TX_CODE);
            BPCRCHIS.INFO.FUNC = 'A';
            BPCRCHIS.POINTER = BPRCHIS;
            BPCRCHIS.LEN = 153;
            S000_CALL_BPZRCHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRCHIS.RC);
            CEP.TRC(SCCGWA, BPCPCHIS.PAR_INFO);
            for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_PAR_MAX_CNT; WS_PAR_CNT += 1) {
                CEP.TRC(SCCGWA, BPCPCHIS.PAR_INFO.PAR_REC[1-1].PAR_VAL);
                CEP.TRC(SCCGWA, BPCPCHIS.PAR_INFO.PAR_REC[1-1].PAR_NUM);
                if (BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                    && BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                    IBS.init(SCCGWA, BPCRHPAR);
                    IBS.init(SCCGWA, BPRHPAR);
                    BPRHPAR.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPRHPAR.KEY.JRN = SCCGWA.COMM_AREA.JRN_NO;
                    BPRHPAR.KEY.TLR = BPCPCHIS.TLR;
                    BPRHPAR.KEY.CCY = BPCPCHIS.CCY;
                    BPRHPAR.KEY.VAL = BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL;
                    BPRHPAR.KEY.MFLG = BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG;
                    BPRHPAR.NUM = BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM;
                    BPCRHPAR.INFO.FUNC = 'A';
                    BPCRHPAR.POINTER = BPRHPAR;
                    BPCRHPAR.LEN = 85;
                    CEP.TRC(SCCGWA, BPRHPAR.NUM);
                    CEP.TRC(SCCGWA, BPRHPAR.NUM);
                    CEP.TRC(SCCGWA, BPCPCHIS);
                    CEP.TRC(SCCGWA, BPRHPAR);
                    CEP.TRC(SCCGWA, BPRHPAR);
                    S000_CALL_BPZRHPAR();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, BPRCHIS);
            IBS.init(SCCGWA, BPCTCHIB);
            BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRCHIS.KEY.JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            BPRCHIS.KEY.TLR = BPCPCHIS.TLR;
            BPRCHIS.KEY.PLBOX_NO = BPCPCHIS.PLBOX_NO;
            BPRCHIS.KEY.CCY = BPCPCHIS.CCY;
            BPRCHIS.KEY.CASH_TYP = BPCPCHIS.CASH_TYP;
            BPCTCHIB.INFO.FUNC = '1';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
            BPCTCHIB.INFO.FUNC = 'N';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
            while (BPCTCHIB.RETURN_INFO != 'N') {
                WS_CNT = WS_CNT + 1;
                if (BPCPCHIS.CONF_NO == BPRCHIS.KEY.CONF_NO 
                    || BPCPCHIS.CONF_NO == 0) {
                    WS_CONF_NO = 0;
                    WS_CONF_NO = BPRCHIS.KEY.CONF_NO;
                    IBS.init(SCCGWA, BPCRCHIS);
                    IBS.init(SCCGWA, BPRCHIS);
                    BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPRCHIS.KEY.JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
                    BPRCHIS.KEY.TLR = BPCPCHIS.TLR;
                    BPRCHIS.KEY.PLBOX_NO = BPCPCHIS.PLBOX_NO;
                    BPRCHIS.KEY.CCY = BPCPCHIS.CCY;
                    BPRCHIS.KEY.CASH_TYP = BPCPCHIS.CASH_TYP;
                    BPRCHIS.KEY.CONF_NO = WS_CONF_NO;
                    BPCRCHIS.INFO.FUNC = 'R';
                    BPCRCHIS.POINTER = BPRCHIS;
                    BPCRCHIS.LEN = 153;
                    S000_CALL_BPZRCHIS();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCRCHIS.RC);
                    IBS.init(SCCGWA, BPCRCHIS.RC);
                    BPRCHIS.STS = '1';
                    BPCRCHIS.INFO.FUNC = 'U';
                    BPCRCHIS.POINTER = BPRCHIS;
                    BPCRCHIS.LEN = 153;
                    S000_CALL_BPZRCHIS();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCRCHIS.RC);
                }
                BPCTCHIB.INFO.FUNC = 'N';
                BPCTCHIB.POINTER = BPRCHIS;
                BPCTCHIB.LEN = 153;
                S000_CALL_BPZTCHIB();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR145);
            }
            BPCTCHIB.INFO.FUNC = 'E';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-CHIS ", BPCTCHIB);
    }
    public void S000_CALL_BPZRCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CHIS, BPCRCHIS);
        if (BPCRCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRHPAR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_HPAR, BPCRHPAR);
        if (BPCRHPAR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHPAR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCHIS = ");
            CEP.TRC(SCCGWA, BPCPCHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
