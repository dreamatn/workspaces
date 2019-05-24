package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPINI {
    int JIBS_tmp_int;
    DBParm LNTEXTN_RD;
    DBParm LNTRPOFF_RD;
    brParm LNTTRAN_BR = new brParm();
    DBParm LNTTRAN_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATN_RD;
    brParm LNTRCVD_BR = new brParm();
    brParm LNTINTA_BR = new brParm();
    DBParm LNTINTA_RD;
    brParm LNTPLPI_BR = new brParm();
    DBParm LNTRCVD_RD;
    DBParm LNTCTPY_RD;
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String CPN_CI_CIZCUST = "CI-INQ-CUST";
    String K_HIS_REMARKS = "DELAY TRADE";
    LNZSPINI_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSPINI_WS_TEMP_VARIABLE();
    LNZSPINI_WS_DD_NARRATIVE WS_DD_NARRATIVE = new LNZSPINI_WS_DD_NARRATIVE();
    LNZSPINI_WS_TOT_PIN_I_AMT WS_TOT_PIN_I_AMT = new LNZSPINI_WS_TOT_PIN_I_AMT();
    LNZSPINI_WS_PIN_I_AMT WS_PIN_I_AMT = new LNZSPINI_WS_PIN_I_AMT();
    int WS_LAST_TX_SEQ = 0;
    int WS_LAST_TX_DT = 0;
    short WS_DUE_TERM = 0;
    int WS_LAST_F_VAL_DATE = 0;
    int WS_LAST_DU_DT = 0;
    int WS_INT_CUT_DT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_FND_FLG = ' ';
    char WS_FOUND_TRAN = ' ';
    char WS_LNTINTA_FLG = ' ';
    char WS_UPDATE_INT_CUT_DT_FLG = ' ';
    char WS_UPDATE_STOP_DT_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCURTL LNCSRTL = new LNCURTL();
    LNCURTN LNCSRTN = new LNCURTN();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCIGECR LNCIGECR = new LNCIGECR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    CICCUST CICCUST = new CICCUST();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCCMTPM LNCCMTPM = new LNCCMTPM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    CICSSTC CICSSTC = new CICSSTC();
    LNRICTL LNRICTL = new LNRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCRFWDH LNCRFWDH = new LNCRFWDH();
    LNRRATN LNRRATN = new LNRRATN();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNCRRATL LNCRRATL = new LNCRRATL();
    LNRRATL LNRRATL = new LNRRATL();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCICAL LNCICAL = new LNCICAL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNCICUT LNCICUT = new LNCICUT();
    LNRINTA LNRINTA = new LNRINTA();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCRTRAN LNCRTRAN = new LNCRTRAN();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNRRPOFF LNRRPOFF = new LNRRPOFF();
    LNREXTN LNREXTN = new LNREXTN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    LNCSPINI LNCSPINI;
    public void MP(SCCGWA SCCGWA, LNCSPINI LNCSPINI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPINI = LNCSPINI;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPINI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSPINI.RC.RC_APP = "LN";
        LNCSPINI.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_INFO();
        if (pgmRtn) return;
        B020_CHECK_DATA();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_CAL_INT();
            if (pgmRtn) return;
            B032_DEAL_REPAY_PLAN();
            if (pgmRtn) return;
            B033_CAL_LNZCNEV();
            if (pgmRtn) return;
            B032_UPDATE_CONT();
            if (pgmRtn) return;
            B032_ADD_TRAN();
            if (pgmRtn) return;
        } else {
            R100_DEAL_REPAY_PLAN();
            if (pgmRtn) return;
            R200_UPDATE_CONT_ICTL_TRAN();
            if (pgmRtn) return;
            B033_CAL_LNZCNEV();
            if (pgmRtn) return;
        }
    }
    public void R000_CAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.FUNC_TYPE = ' ';
        LNCICAL.COMM_DATA.LN_AC = LNCSPINI.COMM_DATA.CONT_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void B010_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        LNCSPINI.COMM_DATA.CCY = LNRCONT.CCY;
        LNCSPINI.COMM_DATA.AC_MATDT = LNRCONT.MAT_DATE;
        LNCSPINI.COMM_DATA.AMT = LNRCONT.LN_TOT_AMT;
        LNCSPINI.COMM_DATA.BOOK_CRE = LNRCONT.BOOK_BR;
        LNCSPINI.COMM_DATA.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, "S000-CALL-LNZRICTL1111");
        CEP.TRC(SCCGWA, "ICTL-CTL-STSW");
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        WS_INT_CUT_DT = LNRICTL.INT_CUT_DT;
        CEP.TRC(SCCGWA, LNRICTL.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(35 - 1, 35 + 1 - 1));
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.CI_CNM);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.BOOK_CRE);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.PROD_DES);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.AMT);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.BAL);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.AC_MATDT);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.VAL_DT);
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        CEP.TRC(SCCGWA, "1111");
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(35 - 1, 35 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (!LNRICTL.CTL_STSW.substring(35 - 1, 35 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NON_DELAY_STS, WS_TEMP_VARIABLE.WS_MSGID);
            if (LNRICTL.CTL_STSW.trim().length() == 0) WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            else WS_TEMP_VARIABLE.WS_FLD_NO = Short.parseShort(LNRICTL.CTL_STSW);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNREXTN.KEY.EXT_FLG = '3';
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "EXT_FLG = :LNREXTN.KEY.EXT_FLG "
            + "AND CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO";
        LNTEXTN_RD.fst = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNRCONT.LAST_F_VAL_DATE);
        if (LNCSPINI.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            || LNCSPINI.COMM_DATA.VAL_DT < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VAL_DT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = (short) LNCSPINI.COMM_DATA.VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B032_DEAL_REPAY_PLAN() throws IOException,SQLException,Exception {
        R000_GET_LAST_CAL_DT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_TOT_PIN_I_AMT);
        R000_BEFORE_RCVD();
        if (pgmRtn) return;
        R000_MIDDLE_RCVD();
        if (pgmRtn) return;
    }
    public void R000_GET_LAST_CAL_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        WS_LAST_DU_DT = LNRRCVD.DUE_DT;
        WS_DUE_TERM = LNRRCVD.KEY.TERM;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
    }
    public void R000_BEFORE_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        IBS.init(SCCGWA, LNCRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.VAL_DT = LNCSPINI.COMM_DATA.VAL_DT;
        T000_STARTBR_RCVD_PROC1();
        if (pgmRtn) return;
        T000_READNEXT_RCVD_PROC();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
        while (LNCRRCVD.RETURN_INFO != 'E') {
            WS_PIN_I_AMT.WS_O_REC_AMT = 0;
            WS_PIN_I_AMT.WS_L_REC_AMT = 0;
            WS_PIN_I_AMT.WS_I_REC_AMT = 0;
            WS_UPDATE_STOP_DT_FLG = ' ';
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.VAL_DT);
            if (LNREXTN.KEY.VAL_DT == LNCSPINI.COMM_DATA.VAL_DT) {
                R000_UPDATE_RCVD1();
                if (pgmRtn) return;
            } else {
                R000_UPDATE_RCVD2();
                if (pgmRtn) return;
            }
            T000_WRITE_RPOFF_PROC();
            if (pgmRtn) return;
            T000_WRITE_TRAN_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRRCVD.TERM_STS);
            if (LNRRCVD.TERM_STS == '0') {
                WS_TEMP_VARIABLE.WS_TOT_N_INT += WS_PIN_I_AMT.WS_I_REC_AMT;
            } else {
                WS_TEMP_VARIABLE.WS_TOT_P_INT += WS_PIN_I_AMT.WS_I_REC_AMT;
            }
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT += WS_PIN_I_AMT.WS_I_REC_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT += WS_PIN_I_AMT.WS_O_REC_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT += WS_PIN_I_AMT.WS_L_REC_AMT;
            T000_READUP_RCVD_PROC();
            if (pgmRtn) return;
            LNRRCVD.I_REC_AMT = LNRRCVD.I_REC_AMT - WS_PIN_I_AMT.WS_I_REC_AMT;
            LNRRCVD.O_REC_AMT = LNRRCVD.O_REC_AMT - WS_PIN_I_AMT.WS_O_REC_AMT;
            LNRRCVD.L_REC_AMT = LNRRCVD.L_REC_AMT - WS_PIN_I_AMT.WS_L_REC_AMT;
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_PST_AMT);
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
            LNRRCVD.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT + LNRRCVD.O_LST_PST_AMT - WS_PIN_I_AMT.WS_O_REC_AMT;
            LNRRCVD.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT + LNRRCVD.L_LST_PST_AMT - WS_PIN_I_AMT.WS_L_REC_AMT;
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
            if (WS_UPDATE_STOP_DT_FLG == 'Y') {
                if (LNCSPINI.COMM_DATA.VAL_DT > LNRRCVD.PI_STOP_DT) {
                    LNRRCVD.PI_STOP_DT = LNCSPINI.COMM_DATA.VAL_DT;
                }
            }
            if (LNRRCVD.I_REC_AMT == LNRRCVD.I_PAY_AMT 
                && LNRRCVD.O_REC_AMT == LNRRCVD.O_PAY_AMT 
                && LNRRCVD.L_REC_AMT == LNRRCVD.L_PAY_AMT 
                && LNRRCVD.P_REC_AMT == LNRRCVD.P_PAY_AMT 
                && LNRRCVD.F_REC_AMT == LNRRCVD.F_PAY_AMT) {
                LNRRCVD.REPY_STS = '2';
            } else {
                if (LNRRCVD.I_PAY_AMT == 0 
                    && LNRRCVD.O_PAY_AMT == 0 
                    && LNRRCVD.L_PAY_AMT == 0 
                    && LNRRCVD.P_PAY_AMT == 0 
                    && LNRRCVD.F_PAY_AMT == 0) {
                    LNRRCVD.REPY_STS = '0';
                } else {
                    LNRRCVD.REPY_STS = '1';
                }
            }
            T000_UPDATE_RCVD_PROC();
            if (pgmRtn) return;
            T000_READNEXT_RCVD_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_RCVD_PROC();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_RCVD1() throws IOException,SQLException,Exception {
        if (LNRRCVD.KEY.AMT_TYP == 'P') {
            CEP.TRC(SCCGWA, "P-RCVD1");
            CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_PAY_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
            WS_PIN_I_AMT.WS_O_REC_AMT = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT;
            WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
        }
        if (LNRRCVD.KEY.AMT_TYP == 'I') {
            CEP.TRC(SCCGWA, "I-RCVD1");
            CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
            WS_PIN_I_AMT.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT;
            R000_GET_L_INT();
            if (pgmRtn) return;
            WS_UPDATE_STOP_DT_FLG = 'Y';
            CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
            WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT + LNCLCCM.COMM_DATA.L_AMT - LNRRCVD.L_PAY_AMT;
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
        }
        if (LNRRCVD.KEY.AMT_TYP == 'C') {
            CEP.TRC(SCCGWA, "C-RCVD1");
            CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_PAY_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
            WS_PIN_I_AMT.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT;
            WS_PIN_I_AMT.WS_O_REC_AMT = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT;
            WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
            CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
        }
    }
    public void R000_UPDATE_RCVD2() throws IOException,SQLException,Exception {
        if (LNRRCVD.KEY.AMT_TYP == 'P') {
            CEP.TRC(SCCGWA, "P-RCVD2");
            if (LNCSPINI.COMM_DATA.VAL_DT < LNRRCVD.DUE_DT) {
                CEP.TRC(SCCGWA, "BACK MID P");
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
            } else {
                CEP.TRC(SCCGWA, "BACK NOT MID P");
                R000_GET_O_INT();
                if (pgmRtn) return;
                WS_UPDATE_STOP_DT_FLG = 'Y';
                CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
                CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.O_PAY_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
                CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
                WS_PIN_I_AMT.WS_O_REC_AMT = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT;
                WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
            }
        }
        if (LNRRCVD.KEY.AMT_TYP == 'I') {
            CEP.TRC(SCCGWA, "I-RCVD2");
            if (LNCSPINI.COMM_DATA.VAL_DT < LNRRCVD.DUE_DT) {
                CEP.TRC(SCCGWA, "BACK MID I");
                R000_GET_I_INT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
            } else {
                CEP.TRC(SCCGWA, "BACK NOT MID I");
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
                WS_PIN_I_AMT.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT;
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
                R000_GET_L_INT();
                if (pgmRtn) return;
                WS_UPDATE_STOP_DT_FLG = 'Y';
                CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
                CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
                WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            }
        }
        if (LNRRCVD.KEY.AMT_TYP == 'C') {
            CEP.TRC(SCCGWA, "C-RCVD2");
            if (LNCSPINI.COMM_DATA.VAL_DT < LNRRCVD.DUE_DT) {
                CEP.TRC(SCCGWA, "BACK MID C");
                R000_GET_I_INT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
            } else {
                CEP.TRC(SCCGWA, "BACK NOT MID C");
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
                WS_PIN_I_AMT.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT;
                CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
                R000_GET_L_INT();
                if (pgmRtn) return;
                WS_UPDATE_STOP_DT_FLG = 'Y';
                CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
                CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
                WS_PIN_I_AMT.WS_L_REC_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            }
        }
    }
    public void R100_DEAL_REPAY_PLAN() throws IOException,SQLException,Exception {
        T000_STRBR_LNTTRAN();
        if (pgmRtn) return;
        T000_READNEXT_LNTTRAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FOUND_TRAN);
        while (WS_FOUND_TRAN == 'Y') {
            CEP.TRC(SCCGWA, WS_LAST_TX_SEQ);
            CEP.TRC(SCCGWA, LNRTRAN.TR_SEQ);
            if (WS_LAST_TX_SEQ != LNRTRAN.TR_SEQ) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCSPINI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
            LNRRCVD.KEY.SUB_CTA_NO = 0;
            LNRRCVD.KEY.AMT_TYP = LNRTRAN.KEY.TXN_TYP;
            LNRRCVD.KEY.TERM = LNRTRAN.KEY.TXN_TERM;
            LNRRCVD.KEY.SUBS_PROJ_NO = 0;
            T000_READUP_RCVD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNRRCVD.I_REC_AMT = LNRRCVD.I_REC_AMT + LNRTRAN.I_WAV_AMT;
                CEP.TRC(SCCGWA, LNRTRAN.L_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
                CEP.TRC(SCCGWA, LNRTRAN.O_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
                CEP.TRC(SCCGWA, LNRTRAN.PI_STOP_DT);
                CEP.TRC(SCCGWA, LNRRCVD.PI_STOP_DT);
                LNRRCVD.O_REC_AMT = LNRTRAN.O_AMT;
                LNRRCVD.L_REC_AMT = LNRTRAN.L_AMT;
                LNRRCVD.PI_STOP_DT = LNRTRAN.PI_STOP_DT;
                if (LNRRCVD.I_REC_AMT == LNRRCVD.I_PAY_AMT 
                    && LNRRCVD.O_REC_AMT == LNRRCVD.O_PAY_AMT 
                    && LNRRCVD.L_REC_AMT == LNRRCVD.L_PAY_AMT 
                    && LNRRCVD.P_REC_AMT == LNRRCVD.P_PAY_AMT 
                    && LNRRCVD.F_REC_AMT == LNRRCVD.F_PAY_AMT) {
                    LNRRCVD.REPY_STS = '2';
                } else {
                    if (LNRRCVD.I_PAY_AMT == 0 
                        && LNRRCVD.O_PAY_AMT == 0 
                        && LNRRCVD.L_PAY_AMT == 0 
                        && LNRRCVD.P_PAY_AMT == 0 
                        && LNRRCVD.F_PAY_AMT == 0) {
                        LNRRCVD.REPY_STS = '0';
                    } else {
                        LNRRCVD.REPY_STS = '1';
                    }
                }
                T000_UPDATE_RCVD_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRRCVD.TERM_STS);
                if (LNRRCVD.TERM_STS == '0') {
                    WS_TEMP_VARIABLE.WS_TOT_N_INT += LNRTRAN.I_WAV_AMT;
                } else {
                    WS_TEMP_VARIABLE.WS_TOT_P_INT += LNRTRAN.I_WAV_AMT;
                }
                WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT += LNRTRAN.I_WAV_AMT;
                WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT += LNRTRAN.O_WAV_AMT;
                WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT += LNRTRAN.L_WAV_AMT;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TOT_N_INT);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TOT_P_INT);
                CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT);
                CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT);
                CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT);
            } else {
                WS_TEMP_VARIABLE.WS_TOT_NOCAL_INT += LNRTRAN.I_WAV_AMT;
                WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT += LNRTRAN.I_WAV_AMT;
                WS_UPDATE_INT_CUT_DT_FLG = 'Y';
            }
            IBS.init(SCCGWA, LNRRPOFF);
            LNRRPOFF.KEY.AMT_TYP = LNRTRAN.KEY.TXN_TYP;
            LNRRPOFF.KEY.TERM = LNRTRAN.KEY.TXN_TERM;
            LNRRPOFF.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
            LNRRPOFF.KEY.SUB_CTA_NO = 0;
            T000_READUP_RPOFF_PROC();
            if (pgmRtn) return;
            T000_DELETE_RPOFF_PROC();
            if (pgmRtn) return;
            T000_READUP_TRAN();
            if (pgmRtn) return;
            LNRTRAN.TRAN_STS = 'R';
            T000_UPDATE_TRAN();
            if (pgmRtn) return;
            T000_READNEXT_LNTTRAN();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTTRAN();
        if (pgmRtn) return;
    }
    public void R000_MIDDLE_RCVD() throws IOException,SQLException,Exception {
        if (WS_LAST_DU_DT < LNCSPINI.COMM_DATA.VAL_DT) {
            R000_MIDDLE_RCVD_2();
            if (pgmRtn) return;
            R000_UPDATE_ICTL();
            if (pgmRtn) return;
        }
    }
    public void R000_UPDATE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        LNCRICTL.FUNC = 'U';
        LNRICTL.INT_CUT_DT = LNCSPINI.COMM_DATA.VAL_DT;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void R000_MIDDLE_RCVD_2() throws IOException,SQLException,Exception {
        WS_PIN_I_AMT.WS_O_REC_AMT = 0;
        WS_PIN_I_AMT.WS_L_REC_AMT = 0;
        WS_PIN_I_AMT.WS_I_REC_AMT = 0;
        IBS.init(SCCGWA, LNRRPOFF);
        IBS.init(SCCGWA, LNRRCVD);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNRRPOFF.KEY.AMT_TYP = 'C';
        } else {
            LNRRPOFF.KEY.AMT_TYP = 'I';
        }
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.LN_AC = LNCSPINI.COMM_DATA.CONT_NO;
        LNCICUT.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.BEG_DATE = WS_LAST_DU_DT;
        LNCICUT.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNCICUT.COMM_DATA.TERM = (short) (WS_DUE_TERM + 1);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.TYPE = 'A';
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TYPE);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        WS_PIN_I_AMT.WS_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
        WS_TEMP_VARIABLE.WS_TOT_NOCAL_INT = LNCICUT.COMM_DATA.INT_AMT;
        LNRRPOFF.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRRPOFF.KEY.SUB_CTA_NO = 0;
        LNRRPOFF.KEY.TERM = (short) (1 + WS_DUE_TERM);
        CEP.TRC(SCCGWA, WS_DUE_TERM);
        CEP.TRC(SCCGWA, LNRRPOFF.KEY.TERM);
        LNRRPOFF.VAL_DT = WS_LAST_DU_DT;
        LNRRPOFF.DUE_DT = LNCSPINI.COMM_DATA.VAL_DT;
        LNRRPOFF.I_REC_AMT = WS_PIN_I_AMT.WS_I_REC_AMT;
        LNRRPOFF.O_REC_AMT = 0;
        LNRRPOFF.L_REC_AMT = 0;
        CEP.TRC(SCCGWA, LNRRPOFF.I_REC_AMT);
        CEP.TRC(SCCGWA, LNRRPOFF.O_REC_AMT);
        CEP.TRC(SCCGWA, LNRRPOFF.L_REC_AMT);
        WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT += WS_PIN_I_AMT.WS_I_REC_AMT;
        CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        LNTRPOFF_RD.upd = true;
        IBS.READ(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        LNRRCVD.KEY.CONTRACT_NO = LNRRPOFF.KEY.CONTRACT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = LNRRPOFF.KEY.SUB_CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNRRPOFF.KEY.AMT_TYP;
        LNRRCVD.KEY.TERM = LNRRPOFF.KEY.TERM;
        LNRRCVD.VAL_DT = LNRRPOFF.VAL_DT;
        LNRRCVD.DUE_DT = LNRRPOFF.DUE_DT;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_REWRITE_RPOFF();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_WRITE_REPOFF();
            if (pgmRtn) return;
        }
        T000_WRITE_TRAN_PROC();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_LNTINTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRINTA.KEY.SUB_CTA_NO = 0;
        LNRINTA.ADJ_RSN = "FOR0139811DF";
        LNRINTA.KEY.REPY_MTH = 'I';
        LNRINTA.KEY.REPY_TERM = LNCICUT.COMM_DATA.TERM;
        T000_STRATBR_LNTINTA();
        if (pgmRtn) return;
        T000_READ_NEXT_LNTINTA();
        if (pgmRtn) return;
        while (WS_LNTINTA_FLG != 'N') {
            T000_READUP_LNTINTA();
            if (pgmRtn) return;
            LNRINTA.ADJ_AMT = 0;
            T000_UPDATE_LNTINTA();
            if (pgmRtn) return;
            T000_READ_NEXT_LNTINTA();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTINTA();
        if (pgmRtn) return;
    }
    public void R000_GET_O_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.TYPE = LNRRCVD.KEY.AMT_TYP;
        LNCLCCM.COMM_DATA.LC_TYPE = 'O';
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNRRCVD.KEY.CONTRACT_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + LNRRCVD.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TERM = LNRRCVD.KEY.TERM;
        LNCLCCM.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
    }
    public void R000_GET_L_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.TYPE = LNRRCVD.KEY.AMT_TYP;
        LNCLCCM.COMM_DATA.LC_TYPE = 'L';
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNRRCVD.KEY.CONTRACT_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + LNRRCVD.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TERM = LNRRCVD.KEY.TERM;
        LNCLCCM.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
    }
    public void R000_GET_I_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.LN_AC = LNRRCVD.KEY.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = "" + LNRRCVD.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNCICUT.COMM_DATA.TERM = LNRRCVD.KEY.TERM;
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.TYPE = 'A';
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TYPE);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        WS_PIN_I_AMT.WS_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
    }
    public void B032_CUT_INT() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == 'I' 
            || LNRPLPI.KEY.REPY_MTH == 'C') {
            R000_COMP_INT();
            if (pgmRtn) return;
            WS_PIN_I_AMT.WS_O_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
            IBS.init(SCCGWA, LNCLCCM);
            LNCLCCM.COMM_DATA.TYPE = LNRPLPI.KEY.REPY_MTH;
            LNCLCCM.COMM_DATA.LC_TYPE = 'L';
            R000_COMP_INT2();
            if (pgmRtn) return;
            WS_PIN_I_AMT.WS_L_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
        }
        if (LNRPLPI.KEY.REPY_MTH == 'P' 
            || LNRPLPI.KEY.REPY_MTH == 'C') {
            IBS.init(SCCGWA, LNCLCCM);
            LNCLCCM.COMM_DATA.TYPE = LNRPLPI.KEY.REPY_MTH;
            LNCLCCM.COMM_DATA.LC_TYPE = 'L';
            R000_COMP_INT2();
            if (pgmRtn) return;
            WS_PIN_I_AMT.WS_L_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
            IBS.init(SCCGWA, LNCLCCM);
            LNCLCCM.COMM_DATA.TYPE = LNRPLPI.KEY.REPY_MTH;
            LNCLCCM.COMM_DATA.LC_TYPE = 'O';
            R000_COMP_INT2();
            if (pgmRtn) return;
            WS_PIN_I_AMT.WS_O_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
            WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT = LNCLCCM.COMM_DATA.LC_AMT;
        }
    }
    public void R000_COMP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.LN_AC = LNRPLPI.KEY.CONTRACT_NO;
        LNCICUT.COMM_DATA.SUF_NO = "" + LNRPLPI.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        LNCICUT.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNCICUT.COMM_DATA.TERM = LNRPLPI.KEY.TERM;
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.TYPE = 'A';
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TYPE);
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
    }
    public void R000_COMP_INT2() throws IOException,SQLException,Exception {
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNRPLPI.KEY.CONTRACT_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + LNRPLPI.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TERM = LNRPLPI.KEY.TERM;
        LNCLCCM.COMM_DATA.TYPE = LNRPLPI.KEY.REPY_MTH;
        LNCLCCM.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        LNCLCCM.COMM_DATA.END_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_AMT);
    }
    public void B033_CAL_LNZCNEV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TOT_NOCAL_INT);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TOT_N_INT);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TOT_P_INT);
        CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT);
        CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT);
        CEP.TRC(SCCGWA, WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT);
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "CANCINT";
        LNCCNEV.COMM_DATA.LN_AC = LNCSPINI.COMM_DATA.CONT_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCNEV.COMM_DATA.ACM_EVENT = "IA";
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_TEMP_VARIABLE.WS_TOT_NOCAL_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_TEMP_VARIABLE.WS_TOT_N_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_TEMP_VARIABLE.WS_TOT_P_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT;
        LNCCNEV.COMM_DATA.I_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT;
        LNCCNEV.COMM_DATA.O_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT;
        LNCCNEV.COMM_DATA.L_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT;
        LNCSPINI.COMM_DATA.AMT = LNCCNEV.COMM_DATA.I_AMT + LNCCNEV.COMM_DATA.O_AMT + LNCCNEV.COMM_DATA.L_AMT;
        CEP.TRC(SCCGWA, LNCSPINI.COMM_DATA.AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNCCNEV.COMM_DATA.RVS_IND = 'Y';
        } else {
            LNCCNEV.COMM_DATA.RVS_IND = 'N';
        }
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void R200_UPDATE_CONT_ICTL_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'I';
        LNRTRAN.KEY.TR_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        if ("0".trim().length() == 0) LNRTRAN.KEY.TXN_TERM = 0;
        else LNRTRAN.KEY.TXN_TERM = Short.parseShort("0");
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.KEY.CTL_SEQ = 0;
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.REC_FLAG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CTL_SEQ);
        T000_READUP_TRAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LAST_TX_SEQ);
        CEP.TRC(SCCGWA, LNRTRAN.TR_SEQ);
        if (WS_LAST_TX_SEQ != LNRTRAN.TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCSPINI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNRTRAN.TRAN_STS = 'R';
        T000_UPDATE_TRAN();
        if (pgmRtn) return;
        if (WS_UPDATE_INT_CUT_DT_FLG == 'Y' 
            && LNRICTL.INT_CUT_DT <= LNCSPINI.COMM_DATA.VAL_DT) {
            IBS.init(SCCGWA, LNRICTL);
            IBS.init(SCCGWA, LNCRICTL);
            LNCRICTL.FUNC = 'R';
            LNRICTL.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            LNCRICTL.FUNC = 'U';
            LNRICTL.INT_CUT_DT = LNRTRAN.INT_CUT_DT;
            LNRICTL.NEXT_LC_CAL_DAT = LNRTRAN.LAST_LC_CAL_DAT;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRICTL.NEXT_LC_CAL_DAT);
            CEP.TRC(SCCGWA, LNRICTL.INT_CUT_DT);
        }
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'R';
        LNRCONT.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        LNCRCONT.FUNC = 'U';
        LNRCONT.LAST_TX_SEQ -= 1;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_F_VAL_DATE = LNRTRAN.LAST_F_VAL_DATE;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B032_UPDATE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'R';
        LNRCONT.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        WS_LAST_TX_DT = LNRCONT.LAST_TX_DT;
        WS_LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCRCONT.FUNC = 'U';
        LNRCONT.LAST_TX_SEQ += 1;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_F_VAL_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B032_ADD_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'I';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        if ("0".trim().length() == 0) LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        else LNCTRANM.REC_DATA.KEY.TXN_TERM = Short.parseShort("0");
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.I_WAV_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_I_REC_AMT;
        LNCTRANM.REC_DATA.O_WAV_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_O_REC_AMT;
        LNCTRANM.REC_DATA.L_WAV_AMT = WS_TOT_PIN_I_AMT.WS_TOT_PIN_L_REC_AMT;
        CEP.TRC(SCCGWA, WS_LAST_DU_DT);
        CEP.TRC(SCCGWA, WS_INT_CUT_DT);
        LNCTRANM.REC_DATA.INT_CUT_DT = WS_INT_CUT_DT;
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = LNRICTL.NEXT_LC_CAL_DAT;
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.LAST_LC_CAL_DAT);
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNRCONT.LAST_TX_DT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = LNRCONT.LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TXN_CCY = LNCSPINI.COMM_DATA.CCY;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void T000_WRITE_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRRPOFF.KEY.AMT_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRRPOFF.KEY.TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'I';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.O_AMT = LNRRCVD.O_REC_AMT;
        LNCTRANM.REC_DATA.L_AMT = LNRRCVD.L_REC_AMT;
        LNCTRANM.REC_DATA.PI_STOP_DT = LNRRCVD.PI_STOP_DT;
        LNCTRANM.REC_DATA.I_WAV_AMT = WS_PIN_I_AMT.WS_I_REC_AMT;
        LNCTRANM.REC_DATA.O_WAV_AMT = WS_PIN_I_AMT.WS_O_REC_AMT;
        LNCTRANM.REC_DATA.L_WAV_AMT = WS_PIN_I_AMT.WS_L_REC_AMT;
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNRCONT.LAST_TX_DT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT;
        LNCTRANM.REC_DATA.O_LST_PST_AMT = LNRRCVD.O_LST_PST_AMT;
        LNCTRANM.REC_DATA.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT;
        LNCTRANM.REC_DATA.L_LST_PST_AMT = LNRRCVD.L_LST_PST_AMT;
        LNCTRANM.REC_DATA.PI_STOP_DT = LNRRCVD.PI_STOP_DT;
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.O_LST_CAL_AMT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.O_LST_PST_AMT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.L_LST_CAL_AMT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.L_LST_PST_AMT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.LAST_LC_CAL_DAT);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.PI_STOP_DT);
        LNCTRANM.REC_DATA.TR_SEQ += 1;
        LNCTRANM.REC_DATA.TXN_CCY = LNCSPINI.COMM_DATA.CCY;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void T000_STRBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.TR_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_TR);
        if (GWA_BP_AREA.CANCEL_AREA.CAN_TR.equalsIgnoreCase("139801")) {
            LNRTRAN.TR_CODE = 9801;
        } else {
            LNRTRAN.TR_CODE = 9821;
        }
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.LOAN_STSW = "1";
        LNRTRAN.TRAN_STS = 'N';
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_CODE, TR_JRN_NO,TRAN_FLG,TRAN_STS";
        LNTTRAN_BR.rp.where = "TXN_TERM > :LNRTRAN.KEY.TXN_TERM";
        LNTTRAN_BR.rp.order = "TXN_TERM";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        WS_FOUND_TRAN = ' ';
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_TRAN = 'Y';
        }
    }
    public void T000_UPDATE_TRAN() throws IOException,SQLException,Exception {
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.REWRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void T000_READUP_TRAN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, LNRTRAN, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TRAN_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_RPOFF_PROC() throws IOException,SQLException,Exception {
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        LNTRPOFF_RD.upd = true;
        IBS.READ(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RPOFF_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_RPOFF_PROC() throws IOException,SQLException,Exception {
        LNRRPOFF.I_REC_AMT = LNRRPOFF.I_REC_AMT - LNRTRAN.I_WAV_AMT;
        LNRRPOFF.O_REC_AMT = LNRRPOFF.O_REC_AMT - LNRTRAN.O_WAV_AMT;
        LNRRPOFF.L_REC_AMT = LNRRPOFF.L_REC_AMT - LNRTRAN.L_WAV_AMT;
        LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        IBS.REWRITE(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRTRAN() throws IOException,SQLException,Exception {
        LNCRTRAN.REC_PTR = LNRTRAN;
        LNCRTRAN.REC_LEN = 1035;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTTRAN", LNCRTRAN);
        if (LNCRTRAN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRTRAN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-RATN-MAINT", LNCSRTN);
        CEP.TRC(SCCGWA, LNCSRTN.RC.RC_RTNCODE);
        if (LNCSRTN.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSRTN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT = :LNRRATN.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        }
    }
    public void T000_STARTBR_RCVD_PROC1() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND VAL_DT <= :LNRRCVD.VAL_DT";
        LNTRCVD_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        CEP.TRC(SCCGWA, "START RADE RCVD1");
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
    }
    public void T000_STARTBR_RCVD_PROC2() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND DUE_DT > :LNRRCVD.DUE_DT "
            + "AND VAL_DT < :LNRRCVD.VAL_DT";
        LNTRCVD_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        CEP.TRC(SCCGWA, "START RADE RCVD2");
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
    }
    public void T000_STRATBR_LNTINTA() throws IOException,SQLException,Exception {
        LNTINTA_BR.rp = new DBParm();
        LNTINTA_BR.rp.TableName = "LNTINTA";
        LNTINTA_BR.rp.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND ADJ_RSN = :LNRINTA.ADJ_RSN "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH";
        IBS.STARTBR(SCCGWA, LNRINTA, this, LNTINTA_BR);
    }
    public void T000_READ_NEXT_LNTINTA() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRINTA, this, LNTINTA_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LNTINTA_FLG = 'Y';
        } else {
            WS_LNTINTA_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void T000_READUP_LNTINTA() throws IOException,SQLException,Exception {
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.upd = true;
        IBS.READ(SCCGWA, LNRINTA, LNTINTA_RD);
    }
    public void T000_UPDATE_LNTINTA() throws IOException,SQLException,Exception {
        LNRINTA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        IBS.REWRITE(SCCGWA, LNRINTA, LNTINTA_RD);
    }
    public void T000_STRBR_PLPI() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPLPI_BR.rp.where = "SUB_CTA_NO >= :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND DUE_DT > :LNRPLPI.DUE_DT "
            + "AND VAL_DT < :LNRPLPI.VAL_DT";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_READNEXT_RCVD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RCVD NOT FOUND");
            LNCRRCVD.RETURN_INFO = 'E';
        }
        CEP.TRC(SCCGWA, "RADE NEXT RCVD");
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
    }
    public void T000_READNEXT_PLPI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RPLPI NOT FOUND");
            LNCRPLPI.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_ENDBR_PLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
    }
    public void T000_READUP_RCVD_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T000_UPDATE_RCVD_PROC() throws IOException,SQLException,Exception {
        LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T000_WRITE_RPOFF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRPOFF);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.VAL_DT);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
        LNRRPOFF.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRRPOFF.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRRPOFF.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNRRPOFF.KEY.TERM = LNRRCVD.KEY.TERM;
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        LNTRPOFF_RD.upd = true;
        IBS.READ(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_REWRITE_RPOFF();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_WRITE_REPOFF();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_RPOFF() throws IOException,SQLException,Exception {
        LNRRPOFF.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRRPOFF.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRRPOFF.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNRRPOFF.KEY.TERM = LNRRCVD.KEY.TERM;
        LNRRPOFF.VAL_DT = LNRRCVD.VAL_DT;
        LNRRPOFF.DUE_DT = LNRRCVD.DUE_DT;
        LNRRPOFF.I_REC_AMT += WS_PIN_I_AMT.WS_I_REC_AMT;
        LNRRPOFF.O_REC_AMT += WS_PIN_I_AMT.WS_O_REC_AMT;
        LNRRPOFF.L_REC_AMT += WS_PIN_I_AMT.WS_L_REC_AMT;
        LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        IBS.REWRITE(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
    }
    public void T000_WRITE_REPOFF() throws IOException,SQLException,Exception {
        LNRRPOFF.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRRPOFF.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRRPOFF.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNRRPOFF.KEY.TERM = LNRRCVD.KEY.TERM;
        LNRRPOFF.VAL_DT = LNRRCVD.VAL_DT;
        LNRRPOFF.DUE_DT = LNRRCVD.DUE_DT;
        LNRRPOFF.I_REC_AMT = WS_PIN_I_AMT.WS_I_REC_AMT;
        LNRRPOFF.O_REC_AMT = WS_PIN_I_AMT.WS_O_REC_AMT;
        LNRRPOFF.L_REC_AMT = WS_PIN_I_AMT.WS_L_REC_AMT;
        LNRRPOFF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        IBS.WRITE(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
    }
    public void T000_UPDATE_RPOFF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRPOFF);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.VAL_DT);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_I_REC_AMT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_O_REC_AMT);
        CEP.TRC(SCCGWA, WS_PIN_I_AMT.WS_L_REC_AMT);
        LNRRPOFF.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRRPOFF.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRRPOFF.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNRRPOFF.KEY.TERM = LNRRCVD.KEY.TERM;
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        LNTRPOFF_RD.upd = true;
        IBS.READ(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
        LNRRPOFF.VAL_DT = LNRRCVD.VAL_DT;
        LNRRPOFF.DUE_DT = LNRRCVD.DUE_DT;
        LNRRPOFF.I_REC_AMT = WS_PIN_I_AMT.WS_I_REC_AMT;
        LNRRPOFF.O_REC_AMT = WS_PIN_I_AMT.WS_O_REC_AMT;
        LNRRPOFF.L_REC_AMT = WS_PIN_I_AMT.WS_L_REC_AMT;
        LNRRPOFF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_INT_CAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.FUNC_TYPE = 'I';
        LNCICAL.COMM_DATA.LN_AC = LNCSPINI.COMM_DATA.CONT_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCICAL.RC.RC_APP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_LAST_RPOFF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSPINI.COMM_DATA.CONT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.DUE_DT = LNCSPINI.COMM_DATA.VAL_DT;
        LNRPLPI.VAL_DT = LNCSPINI.COMM_DATA.VAL_DT;
        T000_STRBR_PLPI();
        if (pgmRtn) return;
        T000_READNEXT_PLPI();
        if (pgmRtn) return;
        while (LNCRPLPI.RETURN_INFO != 'E') {
            B032_CUT_INT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNRRPOFF);
            LNRRPOFF.KEY.CONTRACT_NO = LNRPLPI.KEY.CONTRACT_NO;
            LNRRPOFF.KEY.SUB_CTA_NO = LNRPLPI.KEY.SUB_CTA_NO;
            LNRRPOFF.KEY.AMT_TYP = LNRPLPI.KEY.REPY_MTH;
            LNRRPOFF.KEY.TERM = LNRPLPI.KEY.TERM;
            LNRRPOFF.VAL_DT = LNRPLPI.VAL_DT;
            LNRRPOFF.DUE_DT = LNRPLPI.DUE_DT;
            LNRRPOFF.I_REC_AMT = WS_PIN_I_AMT.WS_I_REC_AMT;
            LNRRPOFF.O_REC_AMT = WS_PIN_I_AMT.WS_O_REC_AMT;
            LNRRPOFF.L_REC_AMT = WS_PIN_I_AMT.WS_L_REC_AMT;
            LNRRPOFF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRRPOFF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNRRPOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRRPOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNTRPOFF_RD = new DBParm();
            LNTRPOFF_RD.TableName = "LNTRPOFF";
            IBS.WRITE(SCCGWA, LNRRPOFF, LNTRPOFF_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                CEP.TRC(SCCGWA, "LNTPORFF-DUPKEY-LAST");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_PLPI();
            if (pgmRtn) return;
        }
        T000_ENDBR_PLPI();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCSPINI.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCSPINI.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPINI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTCTPY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRRCVD.DUE_DT = LNRCONT.START_DATE;
            LNRRCVD.KEY.TERM = 0;
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLCCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LC-CMP", LNCLCCM);
        if (LNCLCCM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLCCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSPINI.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPINI=");
            CEP.TRC(SCCGWA, LNCSPINI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
