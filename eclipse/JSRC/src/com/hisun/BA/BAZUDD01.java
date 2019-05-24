package com.hisun.BA;

import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.GD.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUDD01 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_REV_NO = " ";
    double WS_TMP_AMT = 0;
    double WS_GUAR_TX_AMT = 0;
    double WS_DD_TX_AMT = 0;
    double WS_TMP_AMT1 = 0;
    short WS_GUAR_NUM = 0;
    String WS_RVS_NO = " ";
    BAZUDD01_WS_TRF_NARRATIVE WS_TRF_NARRATIVE = new BAZUDD01_WS_TRF_NARRATIVE();
    char WS_FOUND_FLG = ' ';
    char WS_COMP_FLG = ' ';
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    AICUGRNO AICUGRNO = new AICUGRNO();
    AICUPRVS AICUPRVS = new AICUPRVS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCSIBAL DDCSIBAL = new DDCSIBAL();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCRWBSP SCCRWBSP = new SCCRWBSP();
    SCCBINF SCCBINF = new SCCBINF();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACLBA BPCACLBA = new BPCACLBA();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCBKPO SCCBKPO;
    SCCBATH SCCBATH;
    BACUDD01 BACUDD01;
    public void MP(SCCGWA SCCGWA, BACUDD01 BACUDD01) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUDD01 = BACUDD01;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUDD01 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        BACUDD01.RC.RC_MMO = "LN";
        BACUDD01.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCBATH.JPRM.AC_DATE);
        CEP.TRC(SCCGWA, SCCBATH.JPRM.NEXT_AC_DATE);
        CEP.TRC(SCCGWA, BACUDD01.DATA.BILL_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_GET_MST1_INF();
        if (pgmRtn) return;
        if (WS_TMP_AMT > 0) {
            B500_DEDUC_FROM_ACCOUNT();
            if (pgmRtn) return;
        }
        B200_INQUIRE_DEPOSIT_INF();
        if (pgmRtn) return;
        if (GDCIQLDR.TCNT > 0 
            && WS_TMP_AMT <= 0) {
            for (WS_GUAR_NUM = 1; WS_GUAR_NUM <= GDCIQLDR.TCNT; WS_GUAR_NUM += 1) {
                if (GDCIQLDR.INFO[WS_GUAR_NUM-1].RAMT > 0) {
                    B700_RELEASE_DEPOSIT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_DD_TX_AMT > 0) {
            B600_SET_EWA_EVENT();
            if (pgmRtn) return;
            B900_CREATE_TXDL_PROC();
            if (pgmRtn) return;
        }
        B800_MST1_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B000_GET_MST1_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'T';
        CEP.TRC(SCCGWA, BACUDD01.DATA.BILL_NO);
        BARMST1.BILL_NO = BACUDD01.DATA.BILL_NO;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111111");
        WS_TMP_AMT = BARMST1.REM_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
    }
    public void B200_INQUIRE_DEPOSIT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCIQLDR);
        if (BARMST1.B_REF_NO.trim().length() > 0) {
            GDCIQLDR.RSEQ = BARMST1.B_REF_NO;
            CEP.TRC(SCCGWA, GDCIQLDR.RSEQ);
            S000_CALL_GDZIQLDR();
            if (pgmRtn) return;
        }
    }
    public void B500_DEDUC_FROM_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIBAL);
        DDCSIBAL.FUNC = 'C';
        DDCSIBAL.AC_NO = BARMST1.DRWR_AC;
        CEP.TRC(SCCGWA, DDCSIBAL.AC_NO);
        DDCSIBAL.CCY_TYPE = '1';
        DDCSIBAL.CCY = BARMST1.BILL_CUR;
        CEP.TRC(SCCGWA, DDCSIBAL.CCY);
        S000_CALL_DDZSIBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSIBAL.AVL_BAL);
        if (DDCSIBAL.AVL_BAL > 0) {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.BANK_DR_FLG = 'Y';
            DDCUDRAC.TX_BAL_FLG = 'Y';
            DDCUDRAC.TSTS_TABL = "0014";
            DDCUDRAC.OTHER_AC = BARMST1.CTA_NO;
            DDCUDRAC.OTHER_CCY = BARMST1.BILL_CUR;
            DDCUDRAC.OTHER_AMT = WS_TMP_AMT;
            DDCUDRAC.NARRATIVE = IBS.CLS2CPY(SCCGWA, WS_TRF_NARRATIVE);
            DDCUDRAC.BV_TYP = '3';
            DDCUDRAC.CCY_TYPE = '1';
            DDCUDRAC.AC = BARMST1.DRWR_AC;
            DDCUDRAC.CCY = BARMST1.BILL_CUR;
            DDCUDRAC.TX_AMT = WS_TMP_AMT;
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.TX_MMO = "034";
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            WS_DD_TX_AMT = DDCUDRAC.TX_AMT;
            CEP.TRC(SCCGWA, WS_DD_TX_AMT);
            WS_TMP_AMT = WS_TMP_AMT - DDCUDRAC.TX_AMT;
        }
    }
    public void B700_RELEASE_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'R';
        GDCUMPLD.RSEQ = GDCIQLDR.RSEQ;
        CEP.TRC(SCCGWA, WS_GUAR_NUM);
        GDCUMPLD.JRN_SEQ = WS_GUAR_NUM;
        GDCUMPLD.AC = GDCIQLDR.INFO[WS_GUAR_NUM-1].OUT_AC;
        GDCUMPLD.AC_SEQ = GDCIQLDR.INFO[WS_GUAR_NUM-1].OUT_SEQ;
        GDCUMPLD.AC_TYPE = GDCIQLDR.INFO[WS_GUAR_NUM-1].AC_TYP;
        GDCUMPLD.CCY = GDCIQLDR.INFO[WS_GUAR_NUM-1].CCY;
        GDCUMPLD.TX_AMT = GDCIQLDR.INFO[WS_GUAR_NUM-1].RAMT;
        GDCUMPLD.CCY_TYPE = '1';
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_GUAR_NUM-1].OUT_AC);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_GUAR_NUM-1].AC_TYP);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_GUAR_NUM-1].CCY);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_GUAR_NUM-1].RAMT);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY);
        CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B600_SET_EWA_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CLBA";
        BPCPOEWA.DATA.PROD_CODE = BARMST1.PROD_CD;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        BPCPOEWA.DATA.BR_OLD = BARMST1.ACPT_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("662170")) {
            BPCPOEWA.DATA.AMT_INFO[11-1].AMT = WS_DD_TX_AMT;
            BPCPOEWA.DATA.AMT_INFO[12-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[13-1].AMT = 0;
            BPCPOEWA.DATA.EVENT_CODE = "BADRSD";
            if (BARMST1.BILL_TYP == 'E') {
                BPCPOEWA.DATA.AMT_INFO[15-1].AMT = WS_DD_TX_AMT;
            } else {
                B700_REM_CR_PROCESS();
                if (pgmRtn) return;
            }
        } else {
            BPCPOEWA.DATA.AMT_INFO[21-1].AMT = WS_DD_TX_AMT;
            BPCPOEWA.DATA.AMT_INFO[22-1].AMT = 0;
            BPCPOEWA.DATA.EVENT_CODE = "BADRED";
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B700_REM_CR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "2";
        AICPQIA.CD.BUSI_KND = "BADRPAY";
        AICPQIA.BR = BARMST1.ACPT_BR;
        AICPQIA.CCY = BARMST1.BILL_CUR;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        WS_RVS_NO = BARMST1.SUSP_NO;
        AICUUPIA.DATA.RVS_NO = WS_RVS_NO;
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.CCY = BARMST1.BILL_CUR;
        AICUUPIA.DATA.AMT = WS_DD_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = BARMST1.BILL_MAT_DT;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        WS_RVS_NO = AICUUPIA.DATA.RVS_NO;
        CEP.TRC(SCCGWA, WS_RVS_NO);
    }
    public void B800_MST1_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        if (WS_TMP_AMT > 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("662170")) {
                BARMST1.AMT_STS = '1';
            } else {
                BARMST1.AMT_STS = '3';
            }
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("662170")) {
                BARMST1.AMT_STS = '2';
            } else {
                BARMST1.AMT_STS = '5';
            }
        }
        CEP.TRC(SCCGWA, BARMST1.AMT_STS);
        BARMST1.REM_AMT = WS_TMP_AMT;
        BARMST1.BILL_NO = BACUDD01.DATA.BILL_NO;
        if (WS_RVS_NO.trim().length() > 0) {
            BARMST1.SUSP_NO = WS_RVS_NO;
        }
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B900_CREATE_TXDL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.FUN_CD = BACUDD01.DATA.FUN_CD;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = BARMST1.ACPT_BR;
        BARTXDL.BILL_NO = BARMST1.BILL_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = WS_DD_TX_AMT;
        BARTXDL.REC_FLG = '2';
        BACFTXDL.FUNC = 'A';
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CCY-BAL", DDCSIBAL, true);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        CEP.TRC(SCCGWA, "BBBBB");
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        CEP.TRC(SCCGWA, BACFMST1.RC);
        CEP.TRC(SCCGWA, BACFMST1.RC.RC_CODE);
        if (BACFMST1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "HERE001");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        CEP.TRC(SCCGWA, GDCIQLDR.RC);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUDD01.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
