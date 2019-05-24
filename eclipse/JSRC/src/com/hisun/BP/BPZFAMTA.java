package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFAMTA {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_P_EX = "BP-EX               ";
    String CPN_R_TAMT = "BP-R-MGM-TAMT       ";
    String K_USD_CCY = "USA";
    String WS_LOCAL_CCY = " ";
    short WS_I = 0;
    short WS_J = 0;
    String WS_ERR_MSG = " ";
    char WS_MSG_TYPE = ' ';
    String WS_INFO = " ";
    BPZFAMTA_WS_LVL WS_LVL = new BPZFAMTA_WS_LVL();
    String WS_AMT = " ";
    double WS_9_AMT = 0;
    char WS_TBL_AMTA_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPTAMT BPRPTAMT = new BPRPTAMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    BPCFAMTA BPCFAMTA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCFAMTA BPCFAMTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFAMTA = BPCFAMTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFAMTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_PROC();
        if (pgmRtn) return;
        B200_CHK_AUTH();
        if (pgmRtn) return;
    }
    public void B100_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFAMTA);
        CEP.TRC(SCCGWA, BPCFAMTA.AP_MMO);
        CEP.TRC(SCCGWA, BPCFAMTA.TBL_NO);
        CEP.TRC(SCCGWA, BPCFAMTA.CHNL);
        CEP.TRC(SCCGWA, BPCFAMTA.BR);
        if (!(BPCFAMTA.FUNC == ' ' 
            || BPCFAMTA.FUNC == 'Q')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCFAMTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPTAMT);
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCFAMTA.AP_MMO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.NO = BPCFAMTA.TBL_NO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        if (BPCFAMTA.CHNL.trim().length() == 0) {
            BPRPTAMT.KEY.REDEFINES6.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        } else {
            BPRPTAMT.KEY.REDEFINES6.CHNL = BPCFAMTA.CHNL;
            BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        }
        if (BPCFAMTA.BR == 0) {
            BPRPTAMT.KEY.REDEFINES6.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        } else {
            BPRPTAMT.KEY.REDEFINES6.BR = BPCFAMTA.BR;
            BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        }
        CEP.TRC(SCCGWA, BPRPTAMT.KEY.REDEFINES6.APP);
        CEP.TRC(SCCGWA, BPRPTAMT.KEY.REDEFINES6.NO);
        CEP.TRC(SCCGWA, BPRPTAMT.KEY.REDEFINES6.CHNL);
        CEP.TRC(SCCGWA, BPRPTAMT.KEY.REDEFINES6.BR);
        CEP.TRC(SCCGWA, BPRPTAMT.KEY.CD);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.init(SCCGWA, BPCPRMR);
            BPRPTAMT.KEY.REDEFINES6.BR = SCCGWA.COMM_AREA.HQT_BANK;
            BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
            CEP.TRC(SCCGWA, BPRPTAMT.KEY.REDEFINES6.BR);
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPRMR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBL_NOTFND, BPCFAMTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPRPTAMT);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LIMIT);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LVL);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].REASON);
        if (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LIMIT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBL_NOTFND, BPCFAMTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_CHK_AUTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFAMTA.CCY);
        WS_LOCAL_CCY = BPCRBANK.LOC_CCY1;
        if (BPCFAMTA.CCY.trim().length() == 0) {
            BPCFAMTA.CCY = BPCRBANK.LOC_CCY1;
        }
        if (!BPCFAMTA.CCY.equalsIgnoreCase(WS_LOCAL_CCY) 
            && !BPCFAMTA.CCY.equalsIgnoreCase(K_USD_CCY)) {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.SELL_AMT = BPCFAMTA.AMT;
            BPCFX.SELL_CCY = BPCFAMTA.CCY;
            BPCFX.BUY_CCY = BPCRBANK.LOC_CCY1;
            BPCFX.EXR_TYPE = BPRPTAMT.DATA_TXT.EX_TYP;
            BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFX.FUNC = '3';
            CEP.TRC(SCCGWA, BPCFX);
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            BPCFAMTA.CCY = WS_LOCAL_CCY;
            BPCFAMTA.AMT = BPCFX.BUY_AMT;
        }
        WS_J = 0;
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        WS_LOCAL_CCY = BPCRBANK.LOC_CCY1;
        if (BPCFAMTA.CCY.equalsIgnoreCase(WS_LOCAL_CCY)) {
            B210_CHK_LOCAL_AUTH();
            if (pgmRtn) return;
        } else {
            if (BPCFAMTA.CCY.equalsIgnoreCase(K_USD_CCY)) {
                B220_CHK_USD_AUTH();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCFAMTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_CHK_LOCAL_AUTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.FLAG);
        CEP.TRC(SCCGWA, BPCFAMTA.AMT);
        CEP.TRC(SCCGWA, BPCFAMTA.AMT);
        if (BPRPTAMT.DATA_TXT.FLAG.equalsIgnoreCase(">=")) {
            for (WS_I = 1; WS_I <= 6 
                && (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LVL.trim().length() != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].REASON.trim().length() != 0) 
                && BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT <= BPCFAMTA.AMT; WS_I += 1) {
                if (BPCFAMTA.AMT >= BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT) {
                    WS_J = WS_I;
                }
            }
        } else if (BPRPTAMT.DATA_TXT.FLAG.equalsIgnoreCase("<=")) {
            for (WS_I = 1; WS_I <= 6 
                && (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LVL.trim().length() != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].REASON.trim().length() != 0) 
                && BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT >= BPCFAMTA.AMT; WS_I += 1) {
                if (BPCFAMTA.AMT <= BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].LIMIT) {
                    WS_J = WS_I;
                }
            }
        }
        CEP.TRC(SCCGWA, "MSG");
        CEP.TRC(SCCGWA, WS_J);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LVL);
        CEP.TRC(SCCGWA, BPCFAMTA.FUNC);
        if (WS_J != 0) {
            if (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].LVL.equalsIgnoreCase("99")) {
                IBS.CPY2CLS(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].REASON, BPCFAMTA.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (BPCFAMTA.FUNC == ' ') {
                    if (!BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].LVL.equalsIgnoreCase("0")) {
                        CEP.TRC(SCCGWA, "OUT MSG");
                        WS_ERR_MSG = BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].REASON;
                        WS_9_AMT = BPCFAMTA.AMT;
                        WS_AMT = "" + WS_9_AMT;
                        JIBS_tmp_int = WS_AMT.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) WS_AMT = "0" + WS_AMT;
                        WS_INFO = WS_AMT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    BPCFAMTA.LEVEL = BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].LVL;
                }
            }
        }
    }
    public void B220_CHK_USD_AUTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.FLAG);
        CEP.TRC(SCCGWA, BPCFAMTA.AMT);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].U_LIMIT);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[2-1].U_LIMIT);
        if (BPRPTAMT.DATA_TXT.FLAG.equalsIgnoreCase(">=")) {
            for (WS_I = 1; WS_I <= 6 
                && (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LVL.trim().length() != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_REASON.trim().length() != 0) 
                && BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT <= BPCFAMTA.AMT; WS_I += 1) {
                if (BPCFAMTA.AMT >= BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT) {
                    WS_J = WS_I;
                }
            }
        } else if (BPRPTAMT.DATA_TXT.FLAG.equalsIgnoreCase("<=")) {
            for (WS_I = 1; WS_I <= 6 
                && (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LVL.trim().length() != 0 
                || BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_REASON.trim().length() != 0) 
                && BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT >= BPCFAMTA.AMT; WS_I += 1) {
                if (BPCFAMTA.AMT <= BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_I-1].U_LIMIT) {
                    WS_J = WS_I;
                }
            }
        }
        CEP.TRC(SCCGWA, "MSG");
        CEP.TRC(SCCGWA, WS_J);
        CEP.TRC(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LVL);
        CEP.TRC(SCCGWA, BPCFAMTA.FUNC);
        CEP.TRC(SCCGWA, "OUT MSG");
        if (WS_J != 0) {
            if (BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].U_LVL.equalsIgnoreCase("99")) {
                IBS.CPY2CLS(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].U_REASON, BPCFAMTA.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (!BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].U_LVL.equalsIgnoreCase("0")) {
                    WS_ERR_MSG = BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[WS_J-1].U_REASON;
                    WS_9_AMT = BPCFAMTA.AMT;
                    WS_AMT = "" + WS_9_AMT;
                    JIBS_tmp_int = WS_AMT.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) WS_AMT = "0" + WS_AMT;
                    WS_INFO = WS_AMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPTAMT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFAMTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.TRC(SCCGWA, WS_MSG_TYPE);
        CEP.TRC(SCCGWA, WS_LVL.WS_LVL1);
        CEP.TRC(SCCGWA, WS_LVL.WS_LVL2);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFAMTA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFAMTA = ");
            CEP.TRC(SCCGWA, BPCFAMTA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
