package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCUSBOX;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZTDDR {
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT1 = "TD018";
    String K_OUTPUT_FMT = "DD129";
    String WS_MSGID = " ";
    String WS_ZZD_MMO = " ";
    String WS_CUS_AC = " ";
    char WS_ENTY_TYP = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    CICCUST CICCUST = new CICCUST();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCFMT SCCFMT = new SCCFMT();
    TDCOTRAC TDCOTRAC = new TDCOTRAC();
    TDRINST TDRINST = new TDRINST();
    TDRSMST TDRSMST = new TDRSMST();
    CICACCU CICACCU = new CICACCU();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    TDCTDDR TDCTDDR;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCTDDR TDCTDDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCTDDR = TDCTDDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZTDDR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, TDRSMST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTDDR.OPT);
        CEP.TRC(SCCGWA, TDCTDDR.BV_CD);
        CEP.TRC(SCCGWA, TDCTDDR.BV_TYP);
        CEP.TRC(SCCGWA, TDCTDDR.BV_NO);
        CEP.TRC(SCCGWA, TDCTDDR.CUS_AC);
        CEP.TRC(SCCGWA, TDCTDDR.AC_SEQ);
        CEP.TRC(SCCGWA, TDCTDDR.ACO_AC);
        CEP.TRC(SCCGWA, TDCTDDR.NAME);
        CEP.TRC(SCCGWA, TDCTDDR.CVV);
        CEP.TRC(SCCGWA, TDCTDDR.TRK2_DAT);
        CEP.TRC(SCCGWA, TDCTDDR.TRK3_DAT);
        CEP.TRC(SCCGWA, TDCTDDR.OPRT_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.CCY);
        CEP.TRC(SCCGWA, TDCTDDR.CCY_TYP);
        CEP.TRC(SCCGWA, TDCTDDR.BAL);
        CEP.TRC(SCCGWA, TDCTDDR.PVAL_DT);
        CEP.TRC(SCCGWA, TDCTDDR.VAL_DT);
        CEP.TRC(SCCGWA, TDCTDDR.EXP_DT);
        CEP.TRC(SCCGWA, TDCTDDR.PREV_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.OFD_AMT);
        CEP.TRC(SCCGWA, TDCTDDR.EDU_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.PROC_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.TXN_AMT);
        CEP.TRC(SCCGWA, TDCTDDR.CALL_NO);
        CEP.TRC(SCCGWA, TDCTDDR.INT_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.INT_RAT);
        CEP.TRC(SCCGWA, TDCTDDR.N_BVTYP);
        CEP.TRC(SCCGWA, TDCTDDR.N_BVCD);
        CEP.TRC(SCCGWA, TDCTDDR.N_BVNO);
        CEP.TRC(SCCGWA, TDCTDDR.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCTDDR.PSW);
        CEP.TRC(SCCGWA, TDCTDDR.ID_TYP);
        CEP.TRC(SCCGWA, TDCTDDR.ID_NO);
        CEP.TRC(SCCGWA, TDCTDDR.CT_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.OPP_AC);
        CEP.TRC(SCCGWA, TDCTDDR.OPPBVNO);
        CEP.TRC(SCCGWA, TDCTDDR.OPPCVV);
        CEP.TRC(SCCGWA, TDCTDDR.OTRK2DAT);
        CEP.TRC(SCCGWA, TDCTDDR.OTRK3DAT);
        CEP.TRC(SCCGWA, TDCTDDR.OPP_NAME);
        CEP.TRC(SCCGWA, TDCTDDR.CREV_NO);
        CEP.TRC(SCCGWA, TDCTDDR.IPRT_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.TXN_CHNL);
        CEP.TRC(SCCGWA, TDCTDDR.CHNL_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.PSW_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.MMO);
        CEP.TRC(SCCGWA, TDCTDDR.REMARK);
        B100_CALL_TD_DR_UNT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, TDCACDRU.MAC_CNO);
        CEP.TRC(SCCGWA, TDCTDDR.OPT);
        CEP.TRC(SCCGWA, TDCTDDR.CT_FLG);
        CEP.TRC(SCCGWA, TDCTDDR.PROC_FLG);
        if (TDCTDDR.PROC_FLG != '3' 
            && TDCTDDR.PROC_FLG != 'J') {
            if (TDCTDDR.CT_FLG == '0') {
                if (TDCTDDR.OPT != '1' 
                    && TDCTDDR.OPT != '2') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 35);
                } else {
                    B200_CALL_CSH_CR_UNT();
                    if (pgmRtn) return;
                }
            } else if (TDCTDDR.CT_FLG == '1') {
                B210_CALL_AI_CR_UNT();
                if (pgmRtn) return;
            } else if (TDCTDDR.CT_FLG == '2'
                || TDCTDDR.CT_FLG == '3'
                || TDCTDDR.CT_FLG == '4'
                || TDCTDDR.CT_FLG == '5') {
                if (TDCTDDR.OPT == '4') {
                    B310_GET_MAT_INST_INF();
                    if (pgmRtn) return;
                    B330_CALL_DD_DR_UNT();
                    if (pgmRtn) return;
                } else {
                    WS_CUS_AC = TDCTDDR.OPP_AC;
                    R000_GET_APP();
                    if (pgmRtn) return;
                    if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                        || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                        R000_CHECK_DDAC_INFO();
                        if (pgmRtn) return;
                    }
                    if (TDCTDDR.OPT == '5') {
                        B310_GET_MAT_INST_INF();
                        if (pgmRtn) return;
                        B340_CALL_DD_CR_UNT();
                        if (pgmRtn) return;
                    } else {
                        B230_CALL_DD_CR_UNT();
                        if (pgmRtn) return;
                    }
                }
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 35);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, "EXIT");
    }
    public void B100_CALL_TD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        if (TDCTDDR.PROC_FLG == '0') {
            TDCACDRU.OPT = '1';
            TDCACDRU.NEW_BV_CD = TDCTDDR.N_BVCD;
            TDCACDRU.NEW_BV_NO = TDCTDDR.N_BVNO;
            WS_ZZD_MMO = "561";
        } else if (TDCTDDR.PROC_FLG == '1') {
            TDCACDRU.OPT = '0';
            WS_ZZD_MMO = "560";
        } else if (TDCTDDR.PROC_FLG == '3'
            || TDCTDDR.PROC_FLG == '4'
            || TDCTDDR.PROC_FLG == '5'
            || TDCTDDR.PROC_FLG == '6') {
            TDCACDRU.OPT = '5';
            TDCACDRU.INRT_MTH = TDCTDDR.PROC_FLG;
        } else if (TDCTDDR.PROC_FLG == '7') {
            TDCACDRU.OPT = '7';
            WS_ZZD_MMO = "561";
        } else if (TDCTDDR.PROC_FLG == 'A') {
            TDCACDRU.OPT = '6';
            WS_ZZD_MMO = "560";
        } else if (TDCTDDR.PROC_FLG == 'J') {
            TDCACDRU.OPT = 'A';
            TDCACDRU.INRT_MTH = '3';
        }
        CEP.TRC(SCCGWA, TDCACDRU.OPT);
        if ((TDCTDDR.OPT == '2' 
            || TDCTDDR.OPT == '3' 
            || TDCTDDR.OPT == '4') 
            && TDCACDRU.OPT == '0') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            JIBS_tmp_str[0] = "" + TDCTDDR.OPT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDCACDRU.BUSI_CTLW);
        TDCACDRU.ACO_AC = TDCTDDR.ACO_AC;
        TDCACDRU.PRDMO_CD = "MMDP";
        TDCACDRU.BV_CD = TDCTDDR.BV_CD;
        TDCACDRU.BV_TYP = TDCTDDR.BV_TYP;
        TDCACDRU.BV_NO = TDCTDDR.BV_NO;
        CEP.TRC(SCCGWA, TDCACDRU.NEW_BV_CD);
        CEP.TRC(SCCGWA, TDCACDRU.NEW_BV_NO);
        TDCACDRU.MAC_CNO = TDCTDDR.CUS_AC;
        TDCACDRU.CVV = TDCTDDR.CVV;
        TDCACDRU.AC_SEQ = TDCTDDR.AC_SEQ;
        TDCACDRU.NAME = TDCTDDR.NAME;
        TDCACDRU.CCY = TDCTDDR.CCY;
        TDCACDRU.CCY_TYP = TDCTDDR.CCY_TYP;
        TDCACDRU.PBAL = TDCTDDR.BAL;
        TDCACDRU.TXN_AMT = TDCTDDR.TXN_AMT;
        TDCACDRU.DRAW_MTH = TDCTDDR.DRAW_MTH;
        TDCACDRU.INT_RAT = TDCTDDR.INT_RAT;
        TDCACDRU.EDU_FLG = TDCTDDR.EDU_FLG;
        if (TDCTDDR.PSW_FLG == 'N') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
        }
        if (TDCTDDR.CHNL_FLG == 'Y' 
            && TDCTDDR.PSW_FLG == 'Y') {
        } else {
            if (TDCTDDR.DRAW_MTH == '1'
                || TDCTDDR.DRAW_MTH == '4') {
                TDCACDRU.DRAW_INF = TDCTDDR.PSW;
            } else if (TDCTDDR.DRAW_MTH == '2') {
            } else if (TDCTDDR.DRAW_MTH == '3') {
                if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
                if (TDCTDDR.ID_TYP == null) TDCTDDR.ID_TYP = "";
                JIBS_tmp_int = TDCTDDR.ID_TYP.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDCTDDR.ID_TYP += " ";
                TDCACDRU.DRAW_INF = TDCTDDR.ID_TYP + TDCACDRU.DRAW_INF.substring(5);
                if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
                if (TDCTDDR.ID_NO == null) TDCTDDR.ID_NO = "";
                JIBS_tmp_int = TDCTDDR.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) TDCTDDR.ID_NO += " ";
                TDCACDRU.DRAW_INF = TDCACDRU.DRAW_INF.substring(0, 6 - 1) + TDCTDDR.ID_NO + TDCACDRU.DRAW_INF.substring(6 + 25 - 1);
            } else if (TDCTDDR.DRAW_MTH == '5') {
                TDCACDRU.DRAW_INF = " ";
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH, 31);
            }
        }
        TDCACDRU.ID_TYP = TDCTDDR.ID_TYP;
        TDCACDRU.ID_NO = TDCTDDR.ID_NO;
        TDCACDRU.INT_FLG = TDCTDDR.INT_FLG;
        TDCACDRU.PVAL_DATE = TDCTDDR.PVAL_DT;
        TDCACDRU.PRT_FLG = TDCTDDR.OPRT_FLG;
        TDCACDRU.CT_FLG = TDCTDDR.CT_FLG;
        TDCACDRU.OPP_AC_CNO = TDCTDDR.OPP_AC;
        TDCACDRU.RLT_AC_NAME = TDCTDDR.OPP_NAME;
        TDCACDRU.TXN_CHNL = TDCTDDR.TXN_CHNL;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCACDRU.PAYING_INT = TDCTDDR.PAYING_INT;
            TDCACDRU.PAYING_TAX = TDCTDDR.PAYING_TAX;
            TDCACDRU.DRAW_TOT_AMT = TDCTDDR.DRAW_TOT_AMT;
            TDCACDRU.BAL = TDCTDDR.BAL;
            TDCACDRU.VAL_DT = TDCTDDR.VAL_DT;
            TDCACDRU.EXP_DT = TDCTDDR.EXP_DT;
            TDCACDRU.EXP_INT = TDCTDDR.EXP_INT;
            TDCACDRU.INT_RAT = TDCTDDR.INT_RAT;
        }
        TDCACDRU.TXN_MMO = TDCTDDR.MMO;
        TDCACDRU.STL_MTH = TDCTDDR.CT_FLG;
        TDCACDRU.STL_AC = TDCTDDR.OPP_AC;
        TDCACDRU.CHNL_FLG = TDCTDDR.CHNL_FLG;
        TDCACDRU.AC_TRK2 = TDCTDDR.TRK2_DAT;
        TDCACDRU.AC_TRK3 = TDCTDDR.TRK3_DAT;
        TDCACDRU.REMARK = TDCTDDR.REMARK;
        if (TDCTDDR.STSW == null) TDCTDDR.STSW = "";
        JIBS_tmp_int = TDCTDDR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCTDDR.STSW += " ";
        CEP.TRC(SCCGWA, TDCTDDR.STSW.substring(8 - 1, 8 + 1 - 1));
        if (TDCTDDR.STSW == null) TDCTDDR.STSW = "";
        JIBS_tmp_int = TDCTDDR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCTDDR.STSW += " ";
        if (TDCTDDR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
            JIBS_tmp_int = TDCACDRU.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
            TDCACDRU.STSW = TDCACDRU.STSW.substring(0, 8 - 1) + "1" + TDCACDRU.STSW.substring(8 + 1 - 1);
        }
        if (TDCTDDR.STSW == null) TDCTDDR.STSW = "";
        JIBS_tmp_int = TDCTDDR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCTDDR.STSW += " ";
        if (TDCTDDR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
            if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
            JIBS_tmp_int = TDCACDRU.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
            TDCACDRU.STSW = TDCACDRU.STSW.substring(0, 8 - 1) + "2" + TDCACDRU.STSW.substring(8 + 1 - 1);
        }
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDCTDDR.PAYING_INT = TDCACDRU.PAYING_INT;
            TDCTDDR.PAYING_TAX = TDCACDRU.PAYING_TAX;
            TDCTDDR.BAL = TDCACDRU.BAL;
            TDCTDDR.VAL_DT = TDCACDRU.VAL_DT;
            TDCTDDR.EXP_DT = TDCACDRU.EXP_DT;
            TDCTDDR.EXP_INT = TDCACDRU.EXP_INT;
            TDCTDDR.INT_RAT = TDCACDRU.INT_RAT;
            if (TDCTDDR.PROC_FLG == 'J'
                || TDCTDDR.PROC_FLG == '3') {
                TDCTDDR.DRAW_TOT_AMT = 0;
            } else if (TDCTDDR.PROC_FLG == '4'
                || TDCTDDR.PROC_FLG == '5') {
                TDCTDDR.DRAW_TOT_AMT = TDCTDDR.TXN_AMT;
            } else if (TDCTDDR.PROC_FLG == '6') {
                TDCTDDR.DRAW_TOT_AMT = TDCACDRU.PAYING_INT;
            } else if (TDCTDDR.PROC_FLG == 'A') {
                TDCTDDR.DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
            } else {
                TDCTDDR.DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCTDDR.DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
        }
        CEP.TRC(SCCGWA, TDCTDDR.DRAW_TOT_AMT);
    }
    public void B200_CALL_CSH_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = TDCTDDR.CCY;
        BPCUSBOX.CCY_TYP = TDCTDDR.CCY_TYP;
        BPCUSBOX.AMT = TDCTDDR.DRAW_TOT_AMT;
        BPCUSBOX.OPP_AC = TDCTDDR.CUS_AC;
        BPCUSBOX.OPP_ACNM = TDCTDDR.NAME;
        BPCUSBOX.ID_TYP = TDCTDDR.ID_TYP;
        BPCUSBOX.IDNO = TDCTDDR.ID_NO;
