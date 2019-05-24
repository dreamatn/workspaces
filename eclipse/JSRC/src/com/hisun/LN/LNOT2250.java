package com.hisun.LN;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2250 {
    int JIBS_tmp_int;
    DBParm LNTAGRE_RD;
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_AMT_Y = 0;
    double WS_AMT_1 = 0;
    String WS_AC_TYP = " ";
    LNOT2250_WS_OUT WS_OUT = new LNOT2250_WS_OUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    CIRBAS CIRBAS = new CIRBAS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSCNTA LNCSCNTA = new LNCSCNTA();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCICTLM LNCICTLM = new LNCICTLM();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    LNCSFPAY LNCSFPAY = new LNCSFPAY();
    SCCGWA SCCGWA;
    LNCI2250 LNCI2250;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCI2250 LNCI2250) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCI2250 = LNCI2250;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2250 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCI2250 = new LNCI2250();
        IBS.init(SCCGWA, LNCI2250);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, LNCI2250);
        LNCSFPAY.RC.RC_MMO = "LN";
        LNCSFPAY.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        B100_FUNC_MAIN();
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCI2250.APREF_NO);
        CEP.TRC(SCCGWA, LNCI2250.AC);
        if (LNCI2250.APREF_NO.trim().length() == 0 
            || LNCI2250.AC.trim().length() == 0 
            || LNCI2250.CI_NAME.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNCI2250.AMT_S + LNCI2250.INT <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AMT_NOT_LAR_ZERO;
            S000_ERR_MSG_PROC();
        }
        if (LNCI2250.FLG_1 != '1' 
            && LNCI2250.FLG_1 != '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ALL_PAY_FLG;
            S000_ERR_MSG_PROC();
        }
        if (LNCI2250.FLG_2 != '1' 
            && LNCI2250.FLG_2 != '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PRE_REP_FLG;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSFPAY);
        B110_GET_CONT_NO();
        B120_CHECK_CONT_INFO();
        B130_CHECK_REPAY_AC_INFO();
        B140_INQ_BAL();
        WS_AMT_Y = LNCI2250.AMT_S + LNCI2250.INT;
        CEP.TRC(SCCGWA, WS_AMT_Y);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        if (LNCI2250.FLG_1 == '1' 
            && DDCIQBAL.DATA.AVL_BAL < WS_AMT_Y) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LESS_AC_AMT;
            S000_ERR_MSG_PROC();
        }
        if (DDCIQBAL.DATA.AVL_BAL < LNCI2250.INT) {
            WS_OUT.WS_INT = DDCIQBAL.DATA.AVL_BAL;
        } else {
            WS_OUT.WS_INT = LNCI2250.INT;
            WS_AMT_1 = DDCIQBAL.DATA.AVL_BAL - WS_OUT.WS_INT;
            if (WS_AMT_1 < LNCI2250.AMT_S) {
                WS_OUT.WS_AMT_S = WS_AMT_1;
            } else {
                WS_OUT.WS_AMT_S = LNCI2250.AMT_S;
            }
        }
        CEP.TRC(SCCGWA, WS_OUT.WS_AMT_S);
        LNCSFPAY.DATA.LN_AC = WS_OUT.WS_AC;
        LNCSFPAY.DATA.TOT_P_AMT = WS_OUT.WS_AMT_S;
        LNCSFPAY.DATA.TOT_I_AMT = WS_OUT.WS_INT;
        LNCSFPAY.DATA.TOT_O_AMT = 0;
        LNCSFPAY.DATA.TOT_L_AMT = 0;
        LNCSFPAY.DATA.REPAY_FLG = LNCI2250.FLG_2;
        LNCSFPAY.DATA.P_AMT = LNCI2250.AMT_S;
        LNCSFPAY.DATA.I_AMT = LNCI2250.INT;
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.REPAY_FLG);
        LNCSFPAY.DATA.ACAMT.AC_FLG = '0';
        if (WS_AC_TYP.equalsIgnoreCase(K_DC_AC)) {
            LNCSFPAY.DATA.ACAMT.STL_MTH = "05";
        } else {
            if (WS_AC_TYP.equalsIgnoreCase(K_DD_AC)) {
                LNCSFPAY.DATA.ACAMT.STL_MTH = "01";
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AC_TYPE;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.ACAMT.STL_MTH);
        LNCSFPAY.DATA.ACAMT.REC_AC = LNCI2250.AC;
        WS_AMT_1 = WS_OUT.WS_AMT_S + WS_OUT.WS_INT;
        LNCSFPAY.DATA.ACAMT.PAY_AMT = WS_AMT_1;
        LNCSFPAY.DATA.ACAMT.REC_CCY = LNCCLNQ.DATA.CCY;
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.ACAMT.PAY_AMT);
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.ACAMT.REC_CCY);
        S000_CALL_LNZSFPAY();
        B200_WRITE_PUTOUT();
    }
    public void B110_GET_CONT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.DRAW_NO = LNCI2250.APREF_NO;
        T000_READ_LNTAGRE();
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        WS_OUT.WS_AC = LNRAGRE.KEY.CONTRACT_NO;
    }
    public void B120_CHECK_CONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = WS_OUT.WS_AC;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '0';
        S000_CALL_LNZICLNQ();
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        if (LNCCLNQ.DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LOAN_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        if (LNCCLNQ.DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.ERR_LOAN_CANCELLED;
            S000_ERR_MSG_PROC();
        }
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CTL_STSW.substring(53 - 1, 53 + 1 - 1));
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CTL_STSW.substring(70 - 1, 70 + 1 - 1));
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        if (!LNCCLNQ.DATA.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || !LNCCLNQ.DATA.CTL_STSW.substring(70 - 1, 70 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NOT_HALF_HRG;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_OUT.WS_AC;
        S000_CALL_CIZQACAC();
        if (!CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM.equalsIgnoreCase(LNCI2250.CI_NAME)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NAME_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B130_CHECK_REPAY_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = LNCI2250.AC;
        S000_CALL_CIZQACAC();
        B101_GET_AC_TYPE();
        CEP.TRC(SCCGWA, WS_AC_TYP);
        if (WS_AC_TYP.equalsIgnoreCase(K_DC_AC)) {
            IBS.init(SCCGWA, DCCPCDCK);
            if (LNCI2250.PSW.trim().length() > 0) {
                if (LNCI2250.TRK2_DAT.trim().length() == 0) {
                    DCCPCDCK.FUNC_CODE = 'P';
                } else {
                    DCCPCDCK.FUNC_CODE = 'B';
                }
            } else {
                if (LNCI2250.TRK2_DAT.trim().length() > 0) {
                    DCCPCDCK.FUNC_CODE = 'T';
                } else {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                }
            }
            DCCPCDCK.CARD_NO = LNCI2250.AC;
            DCCPCDCK.CARD_PSW = LNCI2250.PSW;
            DCCPCDCK.TRK2_DAT = LNCI2250.TRK2_DAT;
            DCCPCDCK.TRK3_DAT = LNCI2250.TRK3_DAT;
            S000_CALL_DCZPCDCK();
        }
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CCY);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_CCY);
        if (!LNCCLNQ.DATA.CCY.equalsIgnoreCase(CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0394;
            S000_ERR_MSG_PROC();
        }
    }
    public void B140_INQ_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = LNCI2250.AC;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
        CEP.TRC(SCCGWA, WS_AC_TYP);
        S000_CALL_DDZIQBAL();
    }
    public void B200_WRITE_PUTOUT() throws IOException,SQLException,Exception {
        B201_PUTOUT_DATA_PREPARE();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNW27";
        SCCFMT.DATA_PTR = WS_OUT;
        SCCFMT.DATA_LEN = 581;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B201_PUTOUT_DATA_PREPARE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = LNCI2250.AC;
        S000_CALL_CIZQACAC();
        WS_OUT.WS_DC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_OUT.WS_APREF_NO = LNCI2250.APREF_NO;
        WS_OUT.WS_CI_NAME = LNCI2250.CI_NAME;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = WS_OUT.WS_AC;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        WS_OUT.WS_FLG_3 = LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).charAt(0);
    }
    public void B101_GET_AC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB")) {
            WS_AC_TYP = K_IB_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            WS_AC_TYP = K_DD_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            WS_AC_TYP = K_DC_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("AI")) {
            WS_AC_TYP = K_INTERNAL;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NOEXIST_AC_TYP;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_AC_TYP);
    }
    public void S000_CALL_LNZSFPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-FY-REPAY", LNCSFPAY);
        CEP.TRC(SCCGWA, LNCSFPAY.RC);
        if (LNCSFPAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSFPAY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO";
        LNTAGRE_RD.fst = true;
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
