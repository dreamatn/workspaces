package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCSOCAC;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCCUPSWM;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCUCRAC;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZUGRP {
    boolean pgmRtn = false;
    String K_IRUL_TYP = "TIRUL";
    String K_OUTPUT_FMT = "TD001";
    String K_OUTPUT_FMT2 = "TD016";
    String K_OUTPUT_FMT3 = "TD017";
    String K_OUTPUT_FMT4 = "TD018";
    String K_AP_MMO = "TD";
    String K_NORM_DR_STS_TBL = "0005";
    String K_NORM_DR_STS_TBL_P = "0015";
    String K_NORM_DR_STS_TBL_C = "0006";
    String K_NORM_DR_STS_TBL_C_P = "0016";
    String K_AUTO_DR_STS_TBL = "0010";
    String K_NORM_DR_AMT_TBL = "0002";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_NORM_LN_DR_TBL = "0030";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    int WS_NEXT_DT = 0;
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    int WS_I = 0;
    int WS_J = 0;
    int WS_AC_SEQ = 0;
    int WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    double WS_BAL = 0;
    double WS_BAL1 = 0;
    String WS_REL_AC = " ";
    double WS_BAL_FUL = 0;
    double WS_BAL_XH = 0;
    double WS_BAL_LMT1 = 0;
    double WS_BAL_LMT2 = 0;
    double WS_TXN_LMT = 0;
    double WS_TXN_AMT = 0;
    double WS_BAL_LMTX = 0;
    double WS_CUSE_BAL = 0;
    double WS_LAMT = 0;
    String WS_DOCU_NO = " ";
    char WS_AC_TYP = ' ';
    double WS_INT = 0;
    char WS_CDR_FLG = ' ';
    String WS_ACO_AC = " ";
    char WS_CAN_FLG = ' ';
    double WS_TOT_AMT = 0;
    double WS_XH_PI = 0;
    double WS_XH_INT = 0;
    double WS_XH_TAX = 0;
    char WS_HIS_FLG = ' ';
    int WS_TEST = 0;
    short WS_COUNT1 = 0;
    String WS_MMO = " ";
    char WS_CR_FLG = ' ';
    double WS_ACCRU_AMT = 0;
    double WS_AMT_1 = 0;
    short WS_TS_SEQ = 0;
    double WS_TAX_AMT = 0;
    double WS_LFT_AMT = 0;
    String WS_FRM_APP = " ";
    double WS_INT_2 = 0;
    double WS_INT_3 = 0;
    double WS_MR_BAL = 0;
    double WS_MR_BAL2 = 0;
    double WS_INT_X = 0;
    int WS_LBAL_DATE = 0;
    int WS_LDATE = 0;
    short WS_CNT = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    TDZUGRP_WS_TDRFHIS WS_TDRFHIS = new TDZUGRP_WS_TDRFHIS();
    TDZUGRP_WS_DATE1 WS_DATE1 = new TDZUGRP_WS_DATE1();
    TDZUGRP_WS_DATE2 WS_DATE2 = new TDZUGRP_WS_DATE2();
    char WS_MSREL_FLG = ' ';
    char WS_NOT_STANDARD_FLG = ' ';
    char WS_SMST_FLAG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_SPE_FLG = ' ';
    char WS_OCAC_FLAG = ' ';
    char WS_OPP_AC_OSA_FLG = ' ';
    char WS_MR_FLAG = ' ';
    char WS_INT_AC_OSA_FLG = ' ';
    char WS_STL_AC_OSA_FLG = ' ';
    char WS_GGRP_FLG = ' ';
    char WS_GROP_FLG = ' ';
    char WS_IREV_FLG = ' ';
    char WS_CMST_REC_FLG = ' ';
    double WS_SMST_AMT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    TDRSMST TDRSMST = new TDRSMST();
    TDCACDRU TDCACDRU = new TDCACDRU();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDRIREV TDRIREV = new TDRIREV();
    TDCSGRP TDCSGRP = new TDCSGRP();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDCACCRU TDCACCRU = new TDCACCRU();
    TDRFHIS TDRFHIS = new TDRFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    TDCGROP TDCGROP = new TDCGROP();
    TDCMACC TDCMACC = new TDCMACC();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    TDRCMST TDRCMST = new TDRCMST();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    CICSACAC CICSACAC = new CICSACAC();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    CICCUST CICCUST = new CICCUST();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDRGROP TDRGROP = new TDRGROP();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICCAGT CICCAGT = new CICCAGT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICACCU CICACCU = new CICACCU();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    TDRGGRP TDRGGRP = new TDRGGRP();
    CICSACRL CICSACRL = new CICSACRL();
    CICQACRL CICQACRL = new CICQACRL();
    CICSACR CICSACR = new CICSACR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    TDCCDINT TDCCDINT = new TDCCDINT();
    TDCPIOD TDCPIOD = new TDCPIOD();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCUGRP TDCUGRP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCUGRP TDCUGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCUGRP = TDCUGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        if (TDCUGRP.OPTION.equalsIgnoreCase("XXTZC")) {
            B000_MAIN_PROC_XXTZC();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B000_MAIN_PROC();
                if (pgmRtn) return;
            } else {
                B000_MAIN_PROC_CANCEL();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "TDZUGRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, TDCUGRP.AC_NO);
        CEP.TRC(SCCGWA, TDCUGRP.PROD_CD);
        CEP.TRC(SCCGWA, TDCUGRP.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCUGRP.PSW);
        CEP.TRC(SCCGWA, TDCUGRP.ID_TYP);
        CEP.TRC(SCCGWA, TDCUGRP.ID_NO);
        CEP.TRC(SCCGWA, TDCUGRP.DRAW_TYP);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
        CEP.TRC(SCCGWA, TDCUGRP.GT_FLG);
        CEP.TRC(SCCGWA, TDCUGRP.OP_AC);
        CEP.TRC(SCCGWA, TDCUGRP.HEP);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        CEP.TRC(SCCGWA, TDCUGRP.OPTION);
        WS_TXN_LMT = TDCUGRP.TXN_AMT;
    }
    public void B000_MAIN_PROC_CANCEL() throws IOException,SQLException,Exception {
        B200_GET_ACTYP();
        if (pgmRtn) return;
        if (WS_AC_TYP == '1') {
            if (SCCGWA.COMM_AREA.AC_DATE != GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CANCEL_ERR, SCCBINF);
            }
        }
        CEP.TRC(SCCGWA, WS_AC_TYP);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICQACAC.DATA.AGR_SEQ = 1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        WS_MR_BAL = TDRSMST.BAL;
        WS_MR_BAL2 = TDRSMST.BAL;
        WS_LDATE = TDRSMST.LBAL_DATE;
        CEP.TRC(SCCGWA, "@@");
        CEP.TRC(SCCGWA, WS_MR_BAL);
        CEP.TRC(SCCGWA, WS_MR_BAL2);
        if (TDRSMST.BAL == 0 
            && TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
            JIBS_tmp_int = TDCUGRP.STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
            TDCUGRP.STSW = TDCUGRP.STSW.substring(0, 5 - 1) + "1" + TDCUGRP.STSW.substring(5 + 1 - 1);
        }
        if (TDCUGRP.DRAW_TYP == '1') {
            B010_BT_CANCEL();
            if (pgmRtn) return;
        } else {
            B020_XH_CANCEL();
            if (pgmRtn) return;
        }
    }
    public void B020_XH_CANCEL() throws IOException,SQLException,Exception {
        if (TDCUGRP.TXN_AMT > 0) {
            B320_GRTZ_XH();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT, SCCBINF);
        }
    }
    public void B010_BT_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGGRP);
        TDRGGRP.AC_NO = TDCUGRP.AC_NO;
        TDRGGRP.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        TDRGGRP.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        T000_STARTBR_TDTGGRP();
        if (pgmRtn) return;
        T000_READNEXT_TDTGGRP();
        if (pgmRtn) return;
        while (WS_GGRP_FLG != 'N') {
            if (WS_AC_TYP == '1') {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.MAC_CNO = TDRGGRP.AC_NO;
                TDCACDRU.TXN_AMT = TDRGGRP.TX_AMT;
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                TDCACDRU.ACO_AC = TDRGGRP.ACO_AC;
                TDCACDRU.OPT = '1';
                CEP.TRC(SCCGWA, TDCUGRP.OP_AC);
                TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                TDCACDRU.CCY = "156";
                TDCACDRU.CCY_TYP = '1';
                TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                TDCACDRU.BV_TYP = '0';
                TDCACDRU.PRDMO_CD = "MMDP";
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                TDRSMST.KEY.ACO_AC = TDRGGRP.ACO_AC;
                TDCACDRU.TXN_MMO = WS_MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                R000_UPDATE_TDTGROP();
                if (pgmRtn) return;
                TDCUGRP.TXN_AMT_O = TDCUGRP.TXN_AMT;
            } else {
                CEP.TRC(SCCGWA, TDRGGRP.RMK);
                CEP.TRC(SCCGWA, TDRGGRP.CDR_FLG);
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = TDRGGRP.ACO_AC;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
                if (TDRGGRP.CDR_FLG == 'D') {
                    IBS.init(SCCGWA, TDCACDRU);
                    TDCACDRU.MAC_CNO = TDRGGRP.AC_NO;
                    TDCACDRU.TXN_AMT = TDRGGRP.TX_AMT;
                    CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                    TDCACDRU.ACO_AC = TDRGGRP.ACO_AC;
                    if (TDRGGRP.RMK.equalsIgnoreCase("XXTXH") 
                        || TDRGGRP.RMK.equalsIgnoreCase("SEVENDAY")) {
                        TDCACDRU.OPT = '0';
                    } else {
                        TDCACDRU.OPT = '1';
                    }
                    CEP.TRC(SCCGWA, TDCUGRP.OP_AC);
                    TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                    TDCACDRU.CCY = "156";
                    TDCACDRU.CCY_TYP = '1';
                    TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                    TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                    TDCACDRU.BV_TYP = '0';
                    TDCACDRU.PRDMO_CD = "MMDP";
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                    TDCACDRU.TXN_MMO = WS_MMO;
                    if (TDRSMST.ACO_STS != '2') {
                        S000_CALL_TDZACDRU();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                    CEP.TRC(SCCGWA, WS_TOT_AMT);
                    CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                    if (!TDRGGRP.RMK.equalsIgnoreCase("SEVENDAY") 
                        && !TDRGGRP.RMK.equalsIgnoreCase("XXTHQ")) {
                        WS_TOT_AMT += TDCACDRU.DRAW_TOT_AMT;
                        WS_INT_2 += TDCACDRU.PAYING_INT;
                    } else {
                        WS_INT_2 += TDCACDRU.PAYING_INT;
                    }
                    CEP.TRC(SCCGWA, WS_INT_2);
                    TDCUGRP.TXN_AMT_O = WS_TOT_AMT;
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
                } else {
                    if (TDRGGRP.NEW_FLG == 'Y') {
                        IBS.init(SCCGWA, TDCACCRU);
                        TDCACCRU.OPT = '0';
                        TDCACCRU.PRDMO_CD = "MMDP";
                        TDCACCRU.PROD_CD = TDCUGRP.PROD_CD;
                        TDCACCRU.AC_NO = TDRGGRP.AC_NO;
                        TDCACCRU.ACO_AC = TDRGGRP.ACO_AC;
                        IBS.init(SCCGWA, CICCUST);
                        CICCUST.FUNC = 'A';
                        CICCUST.DATA.AGR_NO = TDRGGRP.AC_NO;
                        S000_CALL_CIZCUST();
                        if (pgmRtn) return;
                        TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
                        TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
                        TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
                        TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
                        TDCACCRU.CCY = "156";
                        TDCACCRU.CCY_TYP = '1';
                        CEP.TRC(SCCGWA, TDRGGRP.TX_AMT);
                        TDCACCRU.TXN_AMT = TDRGGRP.TX_AMT;
                        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
                        TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
                        TDCACCRU.INSTR_MTH = '0';
                        TDCACCRU.DRAW_MTH = '4';
                        TDCACCRU.MAIN_FLG = 'C';
                        TDCACCRU.CT_FLG = '2';
                        TDCACCRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                        TDCACCRU.OPP_BV_TYP = '1';
                        if (TDCACCRU.BUSI_CTLW == null) TDCACCRU.BUSI_CTLW = "";
                        JIBS_tmp_int = TDCACCRU.BUSI_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.BUSI_CTLW += " ";
                        TDCACCRU.BUSI_CTLW = TDCACCRU.BUSI_CTLW.substring(0, 9 - 1) + "1" + TDCACCRU.BUSI_CTLW.substring(9 + 1 - 1);
                        CEP.TRC(SCCGWA, "ACCRU3");
                        S000_CALL_TDZACCRU();
                        if (pgmRtn) return;
                    } else {
                        IBS.init(SCCGWA, TDRSMST);
                        TDRSMST.KEY.ACO_AC = TDRGGRP.ACO_AC;
                        T000_READUP_TDTSMST();
                        if (pgmRtn) return;
                        TDRSMST.BAL = TDRSMST.BAL - TDRGGRP.TX_AMT;
                        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_TDTSMST();
                        if (pgmRtn) return;
                        B600_WRT_FHIS();
                        if (pgmRtn) return;
                    }
                }
            }
            TDRGGRP.CAN_FLG = '1';
            TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTGGRP();
            if (pgmRtn) return;
            T000_READNEXT_TDTGGRP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        T000_ENDBR_TDTGGRP();
        if (pgmRtn) return;
        R000_UPDATE_AMT();
        if (pgmRtn) return;
        WS_INT_3 = WS_INT_2;
        CEP.TRC(SCCGWA, WS_INT_2);
        CEP.TRC(SCCGWA, WS_LFT_AMT);
        if (WS_INT_2 != 0 
            && WS_AC_TYP == '2') {
            CEP.TRC(SCCGWA, "JFTESTA");
            WS_MMO = "S101";
            B500_WRT_FHIS();
            if (pgmRtn) return;
        }
        WS_MMO = "C003";
        WS_INT_2 = WS_TXN_LMT;
        CEP.TRC(SCCGWA, WS_TXN_LMT);
        CEP.TRC(SCCGWA, "JFTESTB");
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        CEP.TRC(SCCGWA, TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1));
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        if (TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_INT_2 += WS_INT_3;
        }
        if (WS_AC_TYP == '2') {
            B500_WRT_FHIS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "JFTESTC");
        CEP.TRC(SCCGWA, TDCUGRP.OPTION);
    }
    public void R000_UPDATE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCRU_AMT);
        CEP.TRC(SCCGWA, WS_LAMT);
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCACDRU.MAC_CNO;
        T000_READ_TDTSMSTMR();
        if (pgmRtn) return;
        if (WS_MR_FLAG == 'F') {
            T000_GROUP_TDTSMST();
            if (pgmRtn) return;
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "JF0531");
            CEP.TRC(SCCGWA, TDRSMST.LBAL_DATE);
            CEP.TRC(SCCGWA, WS_MR_BAL2);
            R000_7_X_24H_BAL_UPD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "UPDATE BAL1");
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            TDRSMST.BAL = WS_SMST_AMT;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
        } else {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCACDRU.MAC_CNO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "JF0531");
            CEP.TRC(SCCGWA, TDRSMST.LBAL_DATE);
            CEP.TRC(SCCGWA, WS_MR_BAL2);
            R000_7_X_24H_BAL_UPD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "UPDATE BAL2");
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            TDRSMST.BAL = 0;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void R000_7_X_24H_BAL_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "JF0524");
        CEP.TRC(SCCGWA, WS_LDATE);
        CEP.TRC(SCCGWA, WS_MR_BAL2);
        CEP.TRC(SCCGWA, WS_LDATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (SCCGWA.COMM_AREA.AC_DATE > WS_LDATE) {
                TDRSMST.LBAL = WS_MR_BAL2;
                TDRSMST.LBAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE == WS_LDATE) {
                } else {
                    TDRSMST.LBAL = TDRSMST.LBAL - TDCUGRP.TXN_AMT_O;
                }
            }
        } else {
            if (SCCGWA.COMM_AREA.AC_DATE > WS_LDATE) {
                TDRSMST.LBAL = WS_MR_BAL2;
                TDRSMST.LBAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE < WS_LDATE) {
                    TDRSMST.LBAL = TDRSMST.LBAL + TDCUGRP.TXN_AMT_O;
                }
            }
        }
    }
    public void B600_WRT_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCUGRP.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        BPCPFHIS.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        BPCPFHIS.DATA.AC = TDCUGRP.AC_NO;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICQACAC.DATA.AGR_SEQ = 1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPFHIS.DATA.OTH_AC = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.OTH_TX_TOOL = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.RLT_TX_TOOL = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.BV_CODE = " ";
        BPCPFHIS.DATA.TX_VAL_DT = TDRSMST.VAL_DATE;
        BPCPFHIS.DATA.TX_CCY = "156";
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        CEP.TRC(SCCGWA, TDRGGRP.TX_AMT);
        BPCPFHIS.DATA.TX_AMT = TDRGGRP.TX_AMT;
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL + BPCPFHIS.DATA.TX_AMT;
        }
        BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_TDRFHIS.WS_FHIS_DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        WS_TDRFHIS.WS_FHIS_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        WS_TDRFHIS.WS_FHIS_INSTR_MTH = TDRSMST.INSTR_MTH;
        WS_COUNT1 = 0;
        WS_COUNT1 = 276;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRFHIS.DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        TDRFHIS.AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        TDRFHIS.INSTR_MTH = TDRSMST.INSTR_MTH;
        BPCPFHIS.DATA.FMT_CODE = "TD099";
        CEP.TRC(SCCGWA, TDRSMST.INSTR_MTH);
        CEP.TRC(SCCGWA, TDRFHIS.DATA_FIELD_TEXT);
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0) {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        if (TDRSMST.PROD_CD.equalsIgnoreCase("CDP00580")) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        CEP.TRC(SCCGWA, WS_MMO);
        BPCPFHIS.DATA.TX_MMO = WS_MMO;
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        BPCPFHIS.DATA.NARRATIVE = " ";
        BPCPFHIS.DATA.TX_TYPE = 'T';
        if (TDCUGRP.OP_AC == null) TDCUGRP.OP_AC = "";
        JIBS_tmp_int = TDCUGRP.OP_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUGRP.OP_AC += " ";
        if (TDCUGRP.OP_AC.substring(26 - 1, 26 + 1 - 1).trim().length() == 0) {
            BPCPFHIS.DATA.RLT_AC = TDCUGRP.OP_AC;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCUGRP.OP_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
            }
            BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC_XXTZC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AC_DATE+"", WS_DATE1);
        SCCCLDT.DAYS = 1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, SCCCLDT.DATE2+"", WS_DATE2);
        CEP.TRC(SCCGWA, WS_DATE1);
        CEP.TRC(SCCGWA, WS_DATE2);
        CEP.TRC(SCCGWA, WS_DATE1.WS_M1);
        CEP.TRC(SCCGWA, WS_DATE2.WS_M2);
        B310_SEVEEN_XH();
        if (pgmRtn) return;
        B310_SEVEEN_CR();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_GET_FRMAPP();
        if (pgmRtn) return;
        B200_GET_ACTYP();
        if (pgmRtn) return;
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (!TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            B130_CHK_STS_TBL();
            if (pgmRtn) return;
        }
        if (TDCUGRP.DRAW_TYP == '1') {
            if (WS_AC_TYP == '1') {
                if (TDCUGRP.TXN_MMO.trim().length() == 0) {
                    WS_MMO = "C003";
                } else {
                    WS_MMO = TDCUGRP.TXN_MMO;
                }
                B310_GRP_ACO();
                if (pgmRtn) return;
            } else {
                if (TDCUGRP.TXN_MMO.trim().length() == 0) {
                    WS_MMO = "C003";
                } else {
                    WS_MMO = TDCUGRP.TXN_MMO;
                }
                B310_TZT_ACO();
                if (pgmRtn) return;
            }
        } else {
            if (WS_AC_TYP == '1') {
                if (TDCUGRP.TXN_MMO.trim().length() == 0) {
                    WS_MMO = "C004";
                } else {
                    WS_MMO = TDCUGRP.TXN_MMO;
                }
            } else {
                if (TDCUGRP.TXN_MMO.trim().length() == 0) {
                    WS_MMO = "C004";
                } else {
                    WS_MMO = TDCUGRP.TXN_MMO;
                }
            }
            if (TDCUGRP.TXN_AMT > 0) {
                B320_GRTZ_XH();
                if (pgmRtn) return;
            } else {
                B320_GRTZ_XH_0();
                if (pgmRtn) return;
            }
        }
        B800_OUTPUT();
        if (pgmRtn) return;
    }
    public void B200_GET_FRMAPP() throws IOException,SQLException,Exception {
        if (TDCUGRP.OP_AC == null) TDCUGRP.OP_AC = "";
        JIBS_tmp_int = TDCUGRP.OP_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUGRP.OP_AC += " ";
        if (TDCUGRP.OP_AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
            WS_FRM_APP = "AI";
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCSGRP.DRAW_MTH == '1' 
            && TDCSGRP.PSW.trim().length() == 0) {
            CEP.TRC(SCCGWA, "PASSWORD MUST BE INPUT");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT, SCCBINF);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICQACAC.DATA.AGR_SEQ = 1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        WS_MR_BAL = TDRSMST.BAL;
        WS_MR_BAL2 = TDRSMST.BAL;
        WS_LDATE = TDRSMST.LBAL_DATE;
        CEP.TRC(SCCGWA, "@@");
        CEP.TRC(SCCGWA, WS_MR_BAL);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
        if (TDRSMST.BAL < TDCUGRP.TXN_AMT) {
            IBS.CPY2CLS(SCCGWA, TDRSMST.BAL+"", SCCBINF);
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BTA_TOO_LARGE, SCCBINF);
        }
        if (TDCUGRP.DRAW_TYP == '0' 
            && TDRSMST.BAL != TDCUGRP.TXN_AMT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_ERR, SCCBINF);
        }
        WS_CUSE_BAL = TDRSMST.BAL - TDRSMST.HBAL;
        CEP.TRC(SCCGWA, WS_CUSE_BAL);
        if (WS_CUSE_BAL < TDCUGRP.TXN_AMT 
            && !TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            IBS.CPY2CLS(SCCGWA, WS_CUSE_BAL+"", SCCBINF);
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AMT_HOLD, SCCBINF);
        }
        WS_LAMT = TDRSMST.BAL - TDCUGRP.TXN_AMT;
        CEP.TRC(SCCGWA, WS_LAMT);
        if (WS_LAMT < 50000 
            && WS_LAMT > 0 
            && WS_AC_TYP == '2' 
            && TDCUGRP.DRAW_TYP == '1') {
            WS_CUSE_BAL = TDRSMST.BAL - TDCUGRP.TXN_AMT;
            IBS.CPY2CLS(SCCGWA, WS_LAMT+"", SCCBINF);
            if (!TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LAMT_TOO_LITTLE, SCCBINF);
            }
        }
        if (WS_AC_TYP == '2' 
            && TDCUGRP.TXN_AMT < 50000 
            && TDCUGRP.DRAW_TYP == '1') {
            IBS.CPY2CLS(SCCGWA, "50000", SCCBINF);
            if (!TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AMT_LESS_MIN, SCCBINF);
            }
        }
        if (TDCUGRP.DRAW_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_ALLOWED_INPUT, SCCBINF);
        }
        WS_LFT_AMT = TDRSMST.BAL - TDCUGRP.TXN_AMT;
        CEP.TRC(SCCGWA, WS_LFT_AMT);
        if (TDCUGRP.CHK_PSW != 'N') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_NO = TDCUGRP.OP_AC;
            DCCPCDCK.CARD_PSW = TDCUGRP.PSW;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_ACTYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCUGRP.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("035")) {
            WS_AC_TYP = '1';
        } else if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            WS_AC_TYP = '2';
        } else {
            CEP.TRC(SCCGWA, "SHOULD BE 035 OR 036");
            CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
        }
    }
    public void R000_INT_XH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        T000_READ_TDTSMST_INT();
        if (pgmRtn) return;
        TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
        TDCACDRU.TXN_AMT = TDRSMST.BAL;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCACDRU.OPT = '1';
        if (TDCACDRU.OPT == '1' 
            || TDCACDRU.OPT == '2' 
            || TDCACDRU.OPT == '3' 
            || TDCACDRU.OPT == '4') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
        }
        TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
        TDCACDRU.CCY = TDRSMST.CCY;
        TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
        TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
        TDCACDRU.DRAW_INF = TDCUGRP.PSW;
        TDCACDRU.BV_TYP = '0';
        TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
        TDCACDRU.PRDMO_CD = "MMDP";
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
        TDCACDRU.TXN_MMO = "S101";
        WS_HIS_FLG = 'Y';
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        R000_GET_GROPSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TS_SEQ);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        TDRGGRP.KEY.SEQ = WS_TS_SEQ;
        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRGGRP.CAN_FLG = '0';
        TDRGGRP.NEW_FLG = 'N';
        TDRGGRP.CDR_FLG = 'D';
        TDRGGRP.AC_NO = TDCUGRP.AC_NO;
        TDRGGRP.TX_AMT = TDCACDRU.TXN_AMT;
        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGGRP.RMK = "XXTHQ";
        T000_WRITE_TDTGGRP();
        if (pgmRtn) return;
        TDCUGRP.TXN_AMT_O += TDCACDRU.TXN_AMT;
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        TDCUGRP.STSW = TDCUGRP.STSW.substring(0, 5 - 1) + "1" + TDCUGRP.STSW.substring(5 + 1 - 1);
    }
    public void B310_GRP_ACO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLAG != 'N' 
            && TDCUGRP.TXN_AMT > WS_TOT_AMT) {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
            CEP.TRC(SCCGWA, WS_TOT_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, "CZZHBT");
            if (TDCUGRP.TXN_AMT > WS_TOT_AMT 
                && TDRSMST.BAL > 0) {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
                if (TDRSMST.BAL < ( TDCUGRP.TXN_AMT - WS_TOT_AMT )) {
                    TDCACDRU.TXN_AMT = TDRSMST.BAL;
                    TDCACDRU.OPT = '1';
                } else {
                    TDCACDRU.TXN_AMT = TDCUGRP.TXN_AMT - WS_TOT_AMT;
                    TDCACDRU.OPT = '1';
                }
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.CCY = TDRSMST.CCY;
                TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
                TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                TDCACDRU.BV_TYP = '0';
                TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
                TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.INT_FLG = '0';
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                TDCACDRU.TXN_MMO = WS_MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                WS_BAL_FUL += TDCACDRU.TXN_AMT;
                WS_TAX_AMT += TDCACDRU.PAYING_TAX;
                WS_TOT_AMT += TDCACDRU.TXN_AMT;
                CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                R000_UPDATE_TDTGROP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRGGRP);
                R000_GET_GROPSEQ();
                if (pgmRtn) return;
                TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                TDRGGRP.CDR_FLG = 'D';
                TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDRGGRP.AC_NO = TDCUGRP.AC_NO;
                TDRGGRP.TX_AMT = TDCACDRU.TXN_AMT;
                TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.NEW_FLG = 'N';
                TDRGGRP.CAN_FLG = '0';
                T000_WRITE_TDTGGRP();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        if (WS_AC_TYP == '2') {
            TDCUGRP.TXN_AMT_O = WS_TOT_AMT;
        } else {
            TDCUGRP.TXN_AMT_O = TDCUGRP.TXN_AMT;
        }
        R000_UPDATE_AMT();
        if (pgmRtn) return;
    }
    public void B310_TZT_ACO() throws IOException,SQLException,Exception {
        B310_SEVEEN_XH();
        if (pgmRtn) return;
        B310_SEVEEN_CR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        WS_BAL_FUL = TDRSMST.BAL;
        while (WS_SMST_FLAG != 'N') {
            CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
            CEP.TRC(SCCGWA, "XXT1");
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            if (TDRSMST.BAL < TDCUGRP.TXN_AMT 
                || WS_LFT_AMT == 0) {
                WS_I += 1;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, "XXTXH");
                if ((WS_BAL_FUL > 0 
                    && WS_BAL_FUL <= TDCUGRP.TXN_AMT) 
                    || WS_LFT_AMT == 0) {
                    IBS.init(SCCGWA, TDCACDRU);
                    TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
                    TDCACDRU.TXN_AMT = TDRSMST.BAL;
                    CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                    TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                    TDCACDRU.OPT = '0';
                    if (TDCACDRU.OPT == '1' 
                        || TDCACDRU.OPT == '2' 
                        || TDCACDRU.OPT == '3' 
                        || TDCACDRU.OPT == '4') {
                        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                        JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
                    }
                    TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                    TDCACDRU.CCY = TDRSMST.CCY;
                    TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
                    TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                    TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                    TDCACDRU.BV_TYP = '0';
                    TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
                    TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                    TDCACDRU.PRDMO_CD = "MMDP";
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                    TDCACDRU.TXN_MMO = WS_MMO;
                    S000_CALL_TDZACDRU();
                    if (pgmRtn) return;
                    WS_TOT_AMT += TDCACDRU.DRAW_TOT_AMT;
                    CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                    WS_ACCRU_AMT += TDCACDRU.PAYING_INT;
                    CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                    CEP.TRC(SCCGWA, "JF1130XH");
                    CEP.TRC(SCCGWA, WS_TOT_AMT);
                    WS_TAX_AMT += TDCACDRU.PAYING_TAX;
                    TDCUGRP.TXN_AMT = TDCUGRP.TXN_AMT - TDRSMST.BAL;
                    WS_BAL_FUL = 0;
                    IBS.init(SCCGWA, TDRGGRP);
                    R000_GET_GROPSEQ();
                    if (pgmRtn) return;
                    TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                    TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                    TDRGGRP.CAN_FLG = '0';
                    TDRGGRP.NEW_FLG = 'N';
                    TDRGGRP.CDR_FLG = 'D';
                    TDRGGRP.AC_NO = TDCUGRP.AC_NO;
                    TDRGGRP.TX_AMT = TDRSMST.BAL;
                    TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRGGRP.RMK = "XXTXH";
                    T000_WRITE_TDTGGRP();
                    if (pgmRtn) return;
                }
            } else {
                WS_J += 1;
                CEP.TRC(SCCGWA, WS_J);
                if (WS_J == 1) {
                    CEP.TRC(SCCGWA, "XXTBT");
                    WS_BAL_LMTX = TDRSMST.BAL - TDCUGRP.TXN_AMT;
                    CEP.TRC(SCCGWA, WS_BAL_LMTX);
                    if (WS_BAL_LMTX > 0) {
                        IBS.init(SCCGWA, TDCACDRU);
                        TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
                        TDCACDRU.TXN_AMT = TDCUGRP.TXN_AMT;
                        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                        TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDCACDRU.OPT = '1';
                        if (TDCACDRU.OPT == '1' 
                            || TDCACDRU.OPT == '2' 
                            || TDCACDRU.OPT == '3' 
                            || TDCACDRU.OPT == '4') {
                            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                            JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
                            JIBS_tmp_int = JIBS_tmp_str[0].length();
                            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
                        }
                        TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                        TDCACDRU.CCY = TDRSMST.CCY;
                        TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
                        TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                        TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                        TDCACDRU.BV_TYP = '0';
                        TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
                        TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                        TDCACDRU.PRDMO_CD = "MMDP";
                        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                        TDCACDRU.TXN_MMO = WS_MMO;
                        S000_CALL_TDZACDRU();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, "1");
                        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                        WS_TOT_AMT += TDCACDRU.DRAW_TOT_AMT;
                        CEP.TRC(SCCGWA, "JF1130BT");
                        CEP.TRC(SCCGWA, WS_TOT_AMT);
                        WS_TAX_AMT += TDCACDRU.PAYING_TAX;
                        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                        WS_ACCRU_AMT += TDCACDRU.PAYING_INT;
                        IBS.init(SCCGWA, TDRGGRP);
                        R000_GET_GROPSEQ();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, WS_TS_SEQ);
                        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
                        TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGGRP.CAN_FLG = '0';
                        TDRGGRP.NEW_FLG = 'N';
                        TDRGGRP.CDR_FLG = 'D';
                        TDRGGRP.AC_NO = TDCUGRP.AC_NO;
                        TDRGGRP.TX_AMT = TDCACDRU.TXN_AMT;
                        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.RMK = "XXTBT";
                        T000_WRITE_TDTGGRP();
                        if (pgmRtn) return;
                    } else {
                        CEP.TRC(SCCGWA, "3");
                        IBS.init(SCCGWA, TDCACDRU);
                        TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
                        TDCACDRU.TXN_AMT = TDRSMST.BAL;
                        TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDCACDRU.OPT = '0';
                        if (TDCACDRU.OPT == '1' 
                            || TDCACDRU.OPT == '2' 
                            || TDCACDRU.OPT == '3' 
                            || TDCACDRU.OPT == '4') {
                            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                            JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
                            JIBS_tmp_int = JIBS_tmp_str[0].length();
                            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
                        }
                        TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                        TDCACDRU.CCY = TDRSMST.CCY;
                        TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
                        TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                        TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                        TDCACDRU.BV_TYP = '0';
                        TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
                        TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                        TDCACDRU.PRDMO_CD = "MMDP";
                        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                        TDCACDRU.TXN_MMO = WS_MMO;
                        S000_CALL_TDZACDRU();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                        WS_TOT_AMT += TDCACDRU.DRAW_TOT_AMT;
                        WS_TAX_AMT += TDCACDRU.PAYING_TAX;
                        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                        WS_ACCRU_AMT += TDCACDRU.PAYING_INT;
                        IBS.init(SCCGWA, TDRGGRP);
                        R000_GET_GROPSEQ();
                        if (pgmRtn) return;
                        TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGGRP.CAN_FLG = '0';
                        TDRGGRP.NEW_FLG = 'N';
                        TDRGGRP.CDR_FLG = 'D';
                        TDRGGRP.AC_NO = TDCUGRP.AC_NO;
                        TDRGGRP.TX_AMT = TDCACDRU.TXN_AMT;
                        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.RMK = "XXTXH";
                        T000_WRITE_TDTGGRP();
                        if (pgmRtn) return;
                    }
                    WS_BAL_LMT1 += TDRSMST.BAL;
                } else {
                    WS_BAL_LMT2 += TDRSMST.BAL;
                }
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            WS_BAL_FUL += TDRSMST.BAL;
            CEP.TRC(SCCGWA, WS_BAL_FUL);
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_BAL_LMT1);
        CEP.TRC(SCCGWA, WS_BAL_LMT2);
        CEP.TRC(SCCGWA, WS_BAL_FUL);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        CEP.TRC(SCCGWA, WS_BAL_LMTX);
        CEP.TRC(SCCGWA, WS_BAL_XH);
        CEP.TRC(SCCGWA, WS_INT);
        WS_BAL = WS_BAL_LMT1 + WS_BAL_LMT2 + WS_BAL_FUL - TDCUGRP.TXN_AMT + WS_TOT_AMT;
        WS_BAL1 = WS_BAL_LMTX + WS_BAL_FUL + WS_TOT_AMT;
        WS_BAL_XH = WS_BAL_XH + WS_INT;
        CEP.TRC(SCCGWA, "------");
        CEP.TRC(SCCGWA, WS_BAL);
        CEP.TRC(SCCGWA, WS_BAL1);
        CEP.TRC(SCCGWA, WS_BAL_XH);
        if (WS_AC_TYP == '2') {
            CEP.TRC(SCCGWA, WS_TOT_AMT);
            CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
            TDCUGRP.TXN_AMT_O = WS_TOT_AMT;
            CEP.TRC(SCCGWA, WS_ACCRU_AMT);
            CEP.TRC(SCCGWA, WS_INT_X);
        } else {
            TDCUGRP.TXN_AMT_O = TDCUGRP.TXN_AMT;
        }
        WS_INT_2 += WS_ACCRU_AMT;
        CEP.TRC(SCCGWA, WS_INT_2);
        WS_INT_3 = WS_INT_2;
        if (WS_INT_2 > 0) {
            WS_MMO = "S101";
            B500_WRT_FHIS();
            if (pgmRtn) return;
        }
        WS_MMO = "C003";
        WS_INT_2 = WS_TXN_LMT;
        if (WS_LFT_AMT == 0 
            && !TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            WS_INT_2 += WS_INT_3;
        }
        B500_WRT_FHIS();
        if (pgmRtn) return;
        WS_TOT_AMT = 0;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_SMST_FLAG);
        while (WS_SMST_FLAG != 'N') {
            WS_TEST += 1;
            CEP.TRC(SCCGWA, WS_TEST);
            WS_TOT_AMT += TDRSMST.BAL;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        CEP.TRC(SCCGWA, TDCUGRP.AC_NO);
        CEP.TRC(SCCGWA, WS_LFT_AMT);
        CEP.TRC(SCCGWA, WS_INT_3);
        CEP.TRC(SCCGWA, TDCUGRP.OPTION);
        if ((WS_LFT_AMT == 0 
            && WS_ACCRU_AMT > 0) 
            && !TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            R000_INT_XH();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "XXTKH");
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        R000_UPDATE_AMT();
        if (pgmRtn) return;
    }
    public void B310_SEVEEN_XH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, TDRSMST);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.DAYS = -7;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_NEXT_DT = SCCCLDT.DATE2;
        TDRSMST.VAL_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTSMST_DAY();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLAG != 'N') {
            CEP.TRC(SCCGWA, "XXT7D");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_CR_FLG = 'C';
            WS_BAL_XH += TDRSMST.BAL;
            IBS.init(SCCGWA, TDCACDRU);
            TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
            TDCACDRU.TXN_AMT = TDRSMST.BAL;
            TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDCACDRU.OPT = '0';
            if (TDCACDRU.OPT == '1' 
                || TDCACDRU.OPT == '2' 
                || TDCACDRU.OPT == '3' 
                || TDCACDRU.OPT == '4') {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
            }
            TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
            TDCACDRU.CCY = TDRSMST.CCY;
            TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
            TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
            TDCACDRU.DRAW_INF = TDCUGRP.PSW;
            TDCACDRU.BV_TYP = '0';
            TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
            TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
            TDCACDRU.PRDMO_CD = "MMDP";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
            TDCACDRU.TXN_MMO = WS_MMO;
            S000_CALL_TDZACDRU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "JF7DAY");
            CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
            CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
            WS_INT += TDCACDRU.DRAW_TOT_AMT;
            WS_INT_2 += TDCACDRU.PAYING_INT;
            WS_INT_X += TDCACDRU.PAYING_INT;
            WS_TAX_AMT += TDCACDRU.PAYING_TAX;
            TDRSMST.ACO_STS = '1';
            TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRGGRP);
            R000_GET_GROPSEQ();
            if (pgmRtn) return;
            TDRGGRP.KEY.SEQ = WS_TS_SEQ;
            TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRGGRP.CAN_FLG = '0';
            TDRGGRP.NEW_FLG = 'N';
            TDRGGRP.CDR_FLG = 'D';
            TDRGGRP.AC_NO = TDCUGRP.AC_NO;
            TDRGGRP.INT += TDCACDRU.PAYING_INT;
            TDRGGRP.TX_AMT = TDRSMST.BAL;
            TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.RMK = "SEVENDAY";
            T000_WRITE_TDTGGRP();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INT);
    }
    public void B310_SEVEEN_CR() throws IOException,SQLException,Exception {
        if (WS_CR_FLG == 'C') {
            IBS.init(SCCGWA, TDCACCRU);
            TDCACCRU.OPT = '0';
            TDCACCRU.PRDMO_CD = "MMDP";
            TDCACCRU.PROD_CD = TDCUGRP.PROD_CD;
            TDCACCRU.AC_NO = TDCUGRP.AC_NO;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = TDCUGRP.AC_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
            TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
            TDCACCRU.CCY = "156";
            TDCACCRU.CCY_TYP = '1';
            CEP.TRC(SCCGWA, "YES2");
            TDCACCRU.TXN_AMT = WS_INT;
            TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            TDCACCRU.INSTR_MTH = '0';
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCUGRP.AC_NO;
            T000_READ_TDTSMST_FST();
            if (pgmRtn) return;
            TDCACCRU.TERM = TDRSMST.TERM;
            TDCACCRU.DRAW_MTH = '4';
            TDCACCRU.CHNL_FLG = 'N';
            TDCACCRU.MAIN_FLG = 'C';
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IREV();
            if (pgmRtn) return;
            if (WS_IREV_FLG == 'N') {
                TDCACCRU.INT_SEL = '0';
            } else {
                TDCACCRU.SPRD_PNT = TDRIREV.SPRD_PNT;
                TDCACCRU.SPRD_PCT = TDRIREV.SPRD_PCT;
                TDCACCRU.INT_RUL_CD = TDRIREV.INT_RUL_CD;
                TDCACCRU.INT_SEL = TDRIREV.INT_SEL;
            }
            TDCACCRU.PRT_FLG = '1';
            TDCACCRU.OIC_NO = SCCGWA.COMM_AREA.TL_ID;
            if (WS_FRM_APP.equalsIgnoreCase("AI")) {
                TDCACCRU.CT_FLG = '1';
            } else {
                TDCACCRU.CT_FLG = '2';
            }
            TDCACCRU.OPP_AC_CNO = TDCUGRP.OP_AC;
            TDCACCRU.CALR_STD = TDRSMST.CALR_STD;
            TDCACCRU.OIC_NO = TDRSMST.OIC_NO;
            TDCACCRU.RES_CD = TDRSMST.RES_CD;
            TDCACCRU.SUB_DP = TDRSMST.SUB_DP;
            TDCACCRU.MON_TYP = TDRSMST.MON_TYP;
            TDCACCRU.REMARK = " ";
            TDCACCRU.SHOW = '1';
            if (TDCUGRP.OPTION.equalsIgnoreCase("XXTZC")) {
                TDCACCRU.XXTLM_FLG = '1';
            }
            CEP.TRC(SCCGWA, "ACCRU1");
            if (!TDCUGRP.OPTION.equalsIgnoreCase("XXTZC")) {
                TDCACCRU.HIS_RMK = "N";
            }
            TDCACCRU.XXTINT_FLG = 'Y';
            S000_CALL_TDZACCRU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_INT_2);
        }
    }
    public void B310_LTMOTH_CR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACCRU);
        TDCACCRU.OPT = '0';
        TDCACCRU.PRDMO_CD = "MMDP";
        TDCACCRU.PROD_CD = TDCUGRP.PROD_CD;
        TDCACCRU.AC_NO = TDCUGRP.AC_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCUGRP.AC_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
        TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
        TDCACCRU.CCY = "156";
        TDCACCRU.CCY_TYP = '1';
        CEP.TRC(SCCGWA, WS_ACCRU_AMT);
        TDCACCRU.TXN_AMT = WS_ACCRU_AMT;
        TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDCACCRU.INSTR_MTH = '0';
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_READ_TDTSMST_FST();
        if (pgmRtn) return;
        TDCACCRU.TERM = TDRSMST.TERM;
        TDCACCRU.DRAW_MTH = '5';
        TDCACCRU.CHNL_FLG = 'N';
        TDCACCRU.MAIN_FLG = 'C';
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_IREV();
        if (pgmRtn) return;
        if (WS_IREV_FLG == 'N') {
            TDCACCRU.INT_SEL = '0';
        } else {
            TDCACCRU.SPRD_PNT = TDRIREV.SPRD_PNT;
            TDCACCRU.SPRD_PCT = TDRIREV.SPRD_PCT;
            TDCACCRU.INT_RUL_CD = TDRIREV.INT_RUL_CD;
            TDCACCRU.INT_SEL = TDRIREV.INT_SEL;
        }
        TDCACCRU.PRT_FLG = '1';
        TDCACCRU.OIC_NO = SCCGWA.COMM_AREA.TL_ID;
        if (WS_FRM_APP.equalsIgnoreCase("AI")) {
            TDCACCRU.CT_FLG = '1';
        } else {
            TDCACCRU.CT_FLG = '2';
        }
        TDCACCRU.OPP_AC_CNO = TDCUGRP.OP_AC;
        TDCACCRU.CALR_STD = TDRSMST.CALR_STD;
        TDCACCRU.OIC_NO = TDRSMST.OIC_NO;
        TDCACCRU.RES_CD = TDRSMST.RES_CD;
        TDCACCRU.SUB_DP = TDRSMST.SUB_DP;
        TDCACCRU.MON_TYP = TDRSMST.MON_TYP;
        TDCACCRU.SHOW = '1';
        TDCACCRU.XXTINT_FLG = 'Y';
        TDCACCRU.TXN_MMO = "S101";
        CEP.TRC(SCCGWA, "ACCRU2");
        TDCACCRU.HIS_RMK = "N";
        S000_CALL_TDZACCRU();
        if (pgmRtn) return;
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = TDCUGRP.OP_AC;
        DDCUCRAC.CARD_NO = TDCUGRP.OP_AC;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = WS_XH_PI;
        DDCUCRAC.OTHER_AC = TDCUGRP.AC_NO;
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.OTH_TX_TOOL = TDCUGRP.AC_NO;
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'N';
        DDCUCRAC.BV_TYP = '1';
        DDCUCRAC.TX_MMO = "S104";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B320_GRTZ_XH_0() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTSMST_XH0();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLAG != 'N') {
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            TDRSMST.ACO_STS = '1';
            TDRSMST.BAL = 0;
            TDRSMST.FBAL = 0;
            TDRSMST.PBAL = 0;
            TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                B630_WRT_BPTOCAC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICSACAC);
            CICSACAC.FUNC = 'D';
            CICSACAC.DATA.PROD_CD = TDRSMST.PROD_CD;
            CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            CICSACAC.DATA.AGR_NO = TDRSMST.AC_NO;
            CICSACAC.DATA.FRM_APP = "TD";
            CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_CIZSACAC();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "03091");
        IBS.init(SCCGWA, TDCMACC);
        TDCMACC.AC_NO = TDCUGRP.AC_NO;
        TDCMACC.BV_TYPE = '0';
        TDCMACC.DRAW_MTH = 'G';
        S000_CALL_TDZMACC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "03092");
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_GROP_PI();
            if (pgmRtn) return;
        } else {
            R000_GROP_PI_CANCEL();
            if (pgmRtn) return;
        }
    }
    public void B320_GRTZ_XH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICQACAC.DATA.AGR_SEQ = 1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUP_TDTSMST();
        if (pgmRtn) return;
        WS_ACO_AC = TDRSMST.KEY.ACO_AC;
        WS_BAL = TDRSMST.BAL;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R000_CANCEL_MAINXH();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUGRP.AC_NO;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDRSMST.ACO_STS = '0';
        } else {
            TDRSMST.ACO_STS = '1';
        }
        if (WS_AC_TYP == '2') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                T000_STARTBR_TDTSMST_XH();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_TDTSMST_XHC();
                if (pgmRtn) return;
            }
        } else {
            T000_STARTBR_TDTSMST_XH();
            if (pgmRtn) return;
        }
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLAG != 'N') {
            WS_I += 1;
            CEP.TRC(SCCGWA, "JFTD");
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (!TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.MAC_CNO = TDCUGRP.AC_NO;
                TDCACDRU.TXN_AMT = TDRSMST.BAL;
                TDCACDRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDCACDRU.OPT = '0';
                if (TDCACDRU.OPT == '1' 
                    || TDCACDRU.OPT == '2' 
                    || TDCACDRU.OPT == '3' 
                    || TDCACDRU.OPT == '4') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    JIBS_tmp_str[0] = "" + TDCACDRU.OPT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
                }
                TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                TDCACDRU.CCY = TDRSMST.CCY;
                TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
                TDCACDRU.DRAW_MTH = TDCUGRP.DRAW_MTH;
                TDCACDRU.DRAW_INF = TDCUGRP.PSW;
                TDCACDRU.BV_TYP = '0';
                TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
                TDCACDRU.OPP_AC_CNO = TDCUGRP.OP_AC;
                TDCACDRU.PRDMO_CD = "MMDP";
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
                TDCACDRU.TXN_MMO = WS_MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "GROPXH");
                CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
                WS_INT += TDCACDRU.DRAW_TOT_AMT;
                WS_TAX_AMT += TDCACDRU.PAYING_TAX;
                CEP.TRC(SCCGWA, WS_INT);
                WS_BAL_FUL += TDRSMST.BAL;
            } else {
                TDRSMST.ACO_STS = '1';
                TDRSMST.RMK_100 = "1900XH";
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    TDRSMST.ACO_STS = '0';
                    TDRSMST.RMK_100 = "1900XHCANCEL";
                }
                TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
                B250_GEN_CI_AC_INF();
                if (pgmRtn) return;
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_TDTSMST();
                if (pgmRtn) return;
                B630_WRT_BPTOCAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, TDCMACC);
            TDCMACC.AC_NO = TDCUGRP.AC_NO;
            TDCMACC.BV_TYPE = '0';
            TDCMACC.DRAW_MTH = 'G';
            S000_CALL_TDZMACC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "03093");
        TDCUGRP.TXN_AMT_O = WS_INT;
        CEP.TRC(SCCGWA, "03094");
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_GROP_PI();
            if (pgmRtn) return;
        } else {
            R000_GROP_PI_CANCEL();
            if (pgmRtn) return;
        }
    }
    public void R000_GROP_PI_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGGRP);
        TDRGGRP.AC_NO = TDCUGRP.AC_NO;
        TDRGGRP.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        TDRGGRP.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        T000_STARTBR_TDTGGRP();
        if (pgmRtn) return;
        T000_READNEXT_TDTGGRP();
        if (pgmRtn) return;
        while (WS_GGRP_FLG != 'N') {
            if (TDRGGRP.RMK.equalsIgnoreCase("CZZHXH")) {
                TDRGGRP.CAN_FLG = '1';
                TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_TDTGGRP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRGROP);
                TDRGROP.KEY.ACO_AC = TDRGGRP.ACO_AC;
                TDRGROP.KEY.DRAW_DT = TDRGGRP.KEY.TR_DATE;
                T000_READUP_TDTGROP();
                if (pgmRtn) return;
                TDRGROP.TYPE = "Y";
                TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                WS_XH_INT += TDRGROP.INT_BAL;
                WS_XH_TAX += TDRGROP.INT_TAX;
                T000_REWRITE_TDTGROP();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTGGRP();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTGGRP();
        if (pgmRtn) return;
        WS_XH_PI = WS_XH_INT - WS_XH_TAX;
        if (WS_XH_PI > 0) {
            B230_CALL_DD_CR_UNT();
            if (pgmRtn) return;
            WS_INT_2 = WS_XH_PI;
            WS_MMO = "S104";
        }
    }
    public void R000_GROP_PI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGROP);
        CEP.TRC(SCCGWA, TDCUGRP.AC_NO);
        TDRGROP.AC_NO = TDCUGRP.AC_NO;
        T000_STARTBR_TDTGROP();
        if (pgmRtn) return;
        T000_READNEXT_TDTGROP();
        if (pgmRtn) return;
        while (WS_GROP_FLG != 'N') {
            TDRGROP.TYPE = "N";
            TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            R000_REWRITE_GROP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRGROP.INT_BAL);
            CEP.TRC(SCCGWA, TDRGROP.INT_TAX);
            WS_XH_INT += TDRGROP.INT_BAL;
            WS_XH_TAX += TDRGROP.INT_TAX;
            B230_CALL_OEWA();
            if (pgmRtn) return;
            TDRSMST.VAL_DATE = TDRGROP.KEY.DRAW_DT;
            IBS.init(SCCGWA, TDRGGRP);
            R000_GET_GROPSEQ();
            if (pgmRtn) return;
            TDRGGRP.KEY.SEQ = WS_TS_SEQ;
            TDRGGRP.CDR_FLG = 'D';
            TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            TDRGGRP.KEY.TR_DATE = TDRGROP.KEY.DRAW_DT;
            TDRGGRP.ACO_AC = TDRGROP.KEY.ACO_AC;
            TDRGGRP.AC_NO = TDCUGRP.AC_NO;
            TDRGGRP.TX_AMT = TDRGROP.INT_BAL - TDRGROP.INT_TAX;
            TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.NEW_FLG = 'N';
            TDRGGRP.CAN_FLG = '0';
            TDRGGRP.RMK = "CZZHXH";
            T000_WRITE_TDTGGRP();
            if (pgmRtn) return;
            T000_READNEXT_TDTGROP();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTGROP();
        if (pgmRtn) return;
        WS_XH_PI = WS_XH_INT - WS_XH_TAX;
        CEP.TRC(SCCGWA, WS_XH_INT);
        CEP.TRC(SCCGWA, WS_XH_TAX);
        CEP.TRC(SCCGWA, WS_XH_PI);
        if (WS_XH_PI > 0) {
            B230_CALL_DD_CR_UNT();
            if (pgmRtn) return;
            WS_INT_2 = WS_XH_PI;
            WS_MMO = "S104";
        }
    }
    public void R000_REWRITE_GROP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, TDRSMST);
        TDRCMST.KEY.AC_NO = TDRGROP.AC_NO;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        TDRSMST.KEY.ACO_AC = TDRGROP.KEY.ACO_AC;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRGROP.AC_NO);
        IBS.init(SCCGWA, TDCPIOD);
        ROOO_GET_PRD_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCPIOD.C_PROD_CD = TDRSMST.PROD_CD;
        TDCPIOD.ACTI_NO = TDRSMST.ACTI_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (TDRSMST.ACTI_NO.trim().length() == 0) {
            TDCPIOD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDCPIOD.INTERM = TDRSMST.TERM;
            TDCPIOD.CCY = TDRSMST.CCY;
        }
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 12; WS_CNT += 1) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDRSMST.CCY)) {
                WS_DOCU_NO = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].DOCU_NO;
                WS_CNT = 99;
            }
        }
        R000_GET_INT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        if (TDRGROP.INT_BAL == 0) {
            TDRGROP.INT_BAL = TDCCDINT.PAYING_INT;
        }
        if (TDRGROP.INT_TAX == 0) {
            TDRGROP.INT_TAX = TDCCDINT.PAYING_TAX;
        }
        TDRGROP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGROP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGROP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_TDTGROP();
        if (pgmRtn) return;
    }
    public void ROOO_GET_PRD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
    }
    public void B230_CALL_OEWA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRGROP.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = TDRGROP.INT_BAL;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = TDRGROP.INT_TAX;
        BPCPOEWA.DATA.DESC = "S101";
        BPCPOEWA.DATA.BR_OLD = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = "156";
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = TDRGROP.KEY.ACO_AC;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDRGROP.AC_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
        } else {
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDINT);
        TDCCDINT.CALL_SRC = 'M';
        TDCCDINT.OPT = '0';
        TDCCDINT.INT_STSW.FX_FLG = '0';
        TDCCDINT.PRDP_PTR = TDCPIOD;
        TDCCDINT.TXN_DATE = TDRGROP.KEY.DRAW_DT;
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        TDCCDINT.OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDCCDINT.AC = TDRSMST.KEY.ACO_AC;
        TDCCDINT.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCCDINT.CCY = TDRSMST.CCY;
        TDCCDINT.TERM = TDRSMST.TERM;
        TDCCDINT.BAL = TDRGROP.HAND_BAL + TDRGROP.AUTO_BAL - TDRGROP.REP_BAL;
        TDCCDINT.TXN_AMT = TDCCDINT.BAL;
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        TDCCDINT.VAL_DATE = TDRSMST.VAL_DATE;
        TDCCDINT.EXP_DATE = TDRSMST.EXP_DATE;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        TDCCDINT.PAYING_INT = TDRSMST.EXP_INT;
        TDCCDINT.TRANS_INT = TDRSMST.DRW_INT;
        TDCCDINT.EDU_FLG = 'N';
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        CEP.TRC(SCCGWA, TDCCDINT.PRDAC_CD);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if ((TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).trim().length() > 0 
            && !TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0"))) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDCCDINT.INT_STSW.RULE = TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).charAt(0);
        }
        TDCCDINT.REG_FLG = '1';
        TDCCDINT.DOCU_NO = WS_DOCU_NO;
        S000_CALL_TDZCDINT();
        if (pgmRtn) return;
    }
    public void B060_CHK_CI_CONTRACT() throws IOException,SQLException,Exception {
    }
    public void B250_GEN_CI_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.PROD_CD = TDRSMST.PROD_CD;
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        CICSACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICSACAC.DATA.AGR_SEQ = 1;
        CICSACAC.DATA.BV_NO = TDCACDRU.BV_NO;
        CICSACAC.DATA.CCY = "156";
        CICSACAC.DATA.CR_FLG = '1';
        CICSACAC.DATA.FRM_APP = "TD";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B630_WRT_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDRSMST.AC_NO;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.WORK_TYP = TDRSMST.PRDAC_CD;
        BPCSOCAC.CI_TYPE = '1';
        if (TDCACDRU.BV_TYP == '0') {
            BPCSOCAC.BV_TYP = '8';
        } else if (TDCACDRU.BV_TYP == '1') {
            BPCSOCAC.BV_TYP = '6';
        } else if (TDCACDRU.BV_TYP == '2') {
            BPCSOCAC.BV_TYP = '4';
        } else if (TDCACDRU.BV_TYP == '3') {
            BPCSOCAC.BV_TYP = '5';
        } else if (TDCACDRU.BV_TYP == '7') {
            BPCSOCAC.BV_TYP = '7';
        } else if (TDCACDRU.BV_TYP == '8') {
            BPCSOCAC.BV_TYP = '8';
        } else if (TDCACDRU.BV_TYP == '4') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_TYP.equalsIgnoreCase("1")) {
                BPCSOCAC.BV_TYP = '2';
            }
            if (DCCUCINF.CARD_TYP.equalsIgnoreCase("2")) {
                BPCSOCAC.BV_TYP = '1';
            }
        }
        BPCSOCAC.ID_TYP = TDCUGRP.ID_TYP;
        BPCSOCAC.ID_NO = TDCUGRP.ID_NO;
        BPCSOCAC.SEQ = 1;
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.CLOSE_DATE = TDRSMST.CLO_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.OPEN_DATE = TDRSMST.OPEN_DATE;
        BPCSOCAC.OPEN_TLR = TDRSMST.CRT_TLR;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.OPEN_AMT = TDRSMST.BAL;
        BPCSOCAC.PROD_CD = TDRSMST.PROD_CD;
        BPCSOCAC.BR = TDRSMST.OWNER_BR;
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCSOCAC.LOSS_INT = TDCACDRU.DRAW_TOT_AMT - TDRSMST.BAL;
        BPCSOCAC.LOSS_TAX = TDCACDRU.PAYING_TAX;
        BPCSOCAC.LOSS_AMT = TDCACDRU.DRAW_TOT_AMT;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B130_CHK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        IBS.init(SCCGWA, TDRCMST);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        if (TDCACDRU.SPEC_S.trim().length() > 0) {
            BPCFCSTS.TBL_NO = TDCACDRU.SPEC_S;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                BPCFCSTS.TBL_NO = K_NORM_DR_STS_TBL_C_P;
                BPCFCSTS.TBL_NO = K_NORM_DR_STS_TBL_C;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (JIBS_tmp_str[0].equalsIgnoreCase("0132219") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132239") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132949") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132959")) {
                    BPCFCSTS.TBL_NO = K_NORM_LN_DR_TBL;
                }
            } else {
                BPCFCSTS.TBL_NO = K_NORM_DR_STS_TBL_P;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (JIBS_tmp_str[0].equalsIgnoreCase("0132210") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132230") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0662170") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0662171") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132941") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0132950")) {
                    BPCFCSTS.TBL_NO = K_NORM_LN_DR_TBL;
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                        R000_CHK_LAY_HOLD();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        BPCFCSTS.STATUS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        CEP.TRC(SCCGWA, "TEST CSTS-STATUS-WORD");
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        if ((TDRCMST.BV_TYP == '0' 
            || TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7' 
            || TDRCMST.BV_TYP == '8')) {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRCMST.STSW.substring(0, 32) + BPCFCSTS.STATUS_WORD.substring(101 + 32 - 1);
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(201 + 32 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(101 - 1, 101 + 32 - 1));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(151 - 1, 151 + 32 - 1));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(201 - 1, 201 + 32 - 1));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(251 - 1, 251 + 32 - 1));
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_SP_FROZEN);
        }
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void R000_GET_GROPSEQ() throws IOException,SQLException,Exception {
        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_TDTGGRP_MAX();
        if (pgmRtn) return;
        WS_TS_SEQ += 1;
    }
    public void R000_CANCEL_MAINXH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCUGRP.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        B270_UPD_TDTCMST_PROC();
        if (pgmRtn) return;
        B250_17_CRE_ACRL_PROC();
        if (pgmRtn) return;
        B240_UPD_AC_CI_REL_PROC();
        if (pgmRtn) return;
    }
    public void B240_UPD_AC_CI_REL_PROC() throws IOException,SQLException,Exception {
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICSACR.DATA.CI_NO = CICACCU.DATA.CI_NO;
        CICSACR.DATA.ENTY_TYP = '3';
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICSACR.DATA.ENTY_TYP);
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B270_UPD_TDTCMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCUGRP.AC_NO;
        T000_READ_UPD_TDTCMST();
        if (pgmRtn) return;
        TDRCMST.STS = '0';
        TDRCMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTCMST();
        if (pgmRtn) return;
    }
    public void B250_17_CRE_ACRL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCUGRP.AC_NO;
        T000_READ_UPD_TDTCMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRCMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("044") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'A';
            CICSACRL.DATA.AC_NO = TDCUGRP.AC_NO;
            CICSACRL.DATA.REL_AC_NO = TDCUGRP.OP_AC;
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
                CICSACRL.DATA.AC_REL = "05";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                CICSACRL.DATA.AC_REL = "06";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
                CICSACRL.DATA.AC_REL = "11";
            }
            S000_CALL_CIZSACRL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = TDCUGRP.AC_NO;
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
                CICQACRL.DATA.AC_REL = "05";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                CICQACRL.DATA.AC_REL = "06";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
                CICQACRL.DATA.AC_REL = "11";
            }
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            CEP.TRC(SCCGWA, WS_REL_AC);
        }
    }
    public void R000_UPDATE_TDTGROP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_READUP_TDTGROP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
            TDRGROP.AC_NO = TDRSMST.AC_NO;
            if (TDCUGRP.AUTO_FLG == 'Y') {
                TDRGROP.AUTO_BAL = TDCACDRU.TXN_AMT;
            } else {
                TDRGROP.HAND_BAL = TDCACDRU.TXN_AMT;
            }
            TDRGROP.TYPE = "Y";
            TDRGROP.OWNER_BK = BPCPORUP.DATA_INFO.BR;
            TDRGROP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRGROP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGROP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_TDTGROP();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (TDCUGRP.AUTO_FLG == 'Y') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    TDRGROP.AUTO_BAL = TDRGROP.AUTO_BAL + TDCACDRU.TXN_AMT;
                } else {
                    TDRGROP.AUTO_BAL = TDRGROP.AUTO_BAL - TDCACDRU.TXN_AMT;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    TDRGROP.HAND_BAL = TDRGROP.HAND_BAL + TDCACDRU.TXN_AMT;
                } else {
                    CEP.TRC(SCCGWA, "1");
                    CEP.TRC(SCCGWA, TDRGROP.HAND_BAL);
                    CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                    TDRGROP.HAND_BAL = TDRGROP.HAND_BAL - TDCACDRU.TXN_AMT;
                }
            }
            if (TDRGROP.HAND_BAL + TDRGROP.AUTO_BAL - TDRGROP.REP_BAL > 0) {
                TDRGROP.TYPE = "Y";
            } else {
                TDRGROP.TYPE = "N";
            }
            TDRGROP.OWNER_BK = BPCPORUP.DATA_INFO.BR;
            TDRGROP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRGROP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGROP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_TDTGROP();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
            }
        }
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
    }
    public void R000_CHK_LAY_HOLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIQHLD);
        DCCIQHLD.INP_DATA.AC = TDRSMST.KEY.ACO_AC;
        S000_CALL_DCZIQHLD();
        if (pgmRtn) return;
        if (DCCIQHLD.OUT_DATA.LAW_AC == 'Y') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_HAS_LAW_HLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
        if (TDCPIOD.RC.RC_RTNCODE != 0) {
            WS_MSGID = "" + TDCPIOD.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCDINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-DRW-INT", TDCCDINT);
        if (TDCCDINT.RC_MSG.RC != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCCDINT.RC_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_MSGID = "" + BPCSOCAC.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD);
        if (DCCIQHLD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCIQHLD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
        if (CICSACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
        }
    }
    public void S000_CALL_TDZMACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ZM-ACC-PROC", TDCMACC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU);
    }
    public void B500_WRT_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCUGRP.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        BPCPFHIS.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        BPCPFHIS.DATA.AC = TDCUGRP.AC_NO;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCUGRP.AC_NO;
        CICQACAC.DATA.AGR_SEQ = 1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPFHIS.DATA.OTH_AC = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.OTH_TX_TOOL = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.RLT_TX_TOOL = TDCUGRP.OP_AC;
        BPCPFHIS.DATA.BV_CODE = " ";
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCPFHIS.DATA.TX_VAL_DT = TDRSMST.VAL_DATE;
        BPCPFHIS.DATA.TX_CCY = "156";
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_AMT = WS_INT_2;
        CEP.TRC(SCCGWA, WS_MMO);
        CEP.TRC(SCCGWA, WS_INT_2);
        CEP.TRC(SCCGWA, WS_INT_3);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            if (WS_MMO.equalsIgnoreCase("S101")) {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL + WS_INT_2;
                } else {
                    BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL - TDCUGRP.TXN_AMT;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL + WS_INT_3 - WS_INT_2;
                } else {
                    BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL;
                }
            }
        }
        BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_TDRFHIS.WS_FHIS_DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        WS_TDRFHIS.WS_FHIS_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        WS_TDRFHIS.WS_FHIS_INSTR_MTH = TDRSMST.INSTR_MTH;
        WS_COUNT1 = 0;
        WS_COUNT1 = 276;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRFHIS.DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        TDRFHIS.AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        TDRFHIS.INSTR_MTH = TDRSMST.INSTR_MTH;
        BPCPFHIS.DATA.FMT_CODE = "TD099";
        CEP.TRC(SCCGWA, TDRFHIS.DATA_FIELD_TEXT);
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0) {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        if (TDRSMST.PROD_CD.equalsIgnoreCase("CDP00580")) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        if (WS_MMO.equalsIgnoreCase("S101")) {
            if (WS_AC_TYP == '1') {
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
        } else {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        }
        CEP.TRC(SCCGWA, WS_MMO);
        if (WS_MMO.equalsIgnoreCase("A003") 
            && TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            WS_MMO = "A314";
        }
        BPCPFHIS.DATA.TX_MMO = WS_MMO;
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        BPCPFHIS.DATA.NARRATIVE = " ";
        BPCPFHIS.DATA.TX_TYPE = 'T';
        if (TDCUGRP.OP_AC == null) TDCUGRP.OP_AC = "";
        JIBS_tmp_int = TDCUGRP.OP_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUGRP.OP_AC += " ";
        if (TDCUGRP.OP_AC.substring(26 - 1, 26 + 1 - 1).trim().length() == 0) {
            BPCPFHIS.DATA.RLT_AC = TDCUGRP.OP_AC;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCUGRP.OP_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
            }
            BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        if (BPCPFHIS.DATA.TX_AMT <= 0) {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        }
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B800_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCGROP);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        TDCGROP.NAME = CICQACRI.DATA.AC_CNM;
        TDCGROP.AC_NO = TDCUGRP.AC_NO;
        TDCGROP.AC_SEQ = 1;
        TDCGROP.BAL = TDRSMST.BAL;
        TDCGROP.TERM = TDRSMST.TERM;
        TDCGROP.RMK = " ";
        TDCGROP.INT = TDCUGRP.TXN_AMT_O - TDCUGRP.TXN_AMT;
        TDCGROP.INT_TAX = WS_TAX_AMT;
        TDCGROP.TOT_BAL = TDCUGRP.TXN_AMT_O;
        TDCGROP.TR = TDRSMST.UPD_TLT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD190";
        SCCFMT.DATA_PTR = TDCGROP;
        SCCFMT.DATA_LEN = 492;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_UPD_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READU_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.upd = true;
        IBS.READ(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_READ_TDTSMSTMR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MR_FLAG = 'N';
        } else {
            WS_MR_FLAG = 'F';
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_SMST_FX() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTGGRP_MAX() throws IOException,SQLException,Exception {
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        TDTGGRP_RD.where = "TR_DATE = :TDRGGRP.KEY.TR_DATE "
            + "AND JRNNO = :TDRGGRP.KEY.JRNNO";
        TDTGGRP_RD.fst = true;
        TDTGGRP_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, TDRGGRP, this, TDTGGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TS_SEQ = 0;
        } else {
            WS_TS_SEQ = TDRGGRP.KEY.SEQ;
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_READ_TDTSMST_INT() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_WRITE_TDTGGRP() throws IOException,SQLException,Exception {
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.WRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_STARTBR_TDTGROP() throws IOException,SQLException,Exception {
        WS_GROP_FLG = 'N';
        TDTGROP_BR.rp = new DBParm();
        TDTGROP_BR.rp.TableName = "TDTGROP";
        TDTGROP_BR.rp.where = "AC_NO = :TDRGROP.AC_NO "
            + "AND TYPE = 'Y'";
        TDTGROP_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRGROP, this, TDTGROP_BR);
    }
    public void T000_STARTBR_TDTGGRP() throws IOException,SQLException,Exception {
        WS_GGRP_FLG = 'N';
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        TDTGGRP_BR.rp = new DBParm();
        TDTGGRP_BR.rp.TableName = "TDTGGRP";
        TDTGGRP_BR.rp.where = "AC_NO = :TDRGGRP.AC_NO "
            + "AND JRNNO = :TDRGGRP.KEY.JRNNO "
            + "AND CAN_FLG = '0' "
            + "AND TR_DATE = :TDRGGRP.KEY.TR_DATE";
        TDTGGRP_BR.rp.upd = true;
        TDTGGRP_BR.rp.order = "SEQ DESC";
        IBS.STARTBR(SCCGWA, TDRGGRP, this, TDTGGRP_BR);
    }
    public void T000_READNEXT_TDTGROP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGROP, this, TDTGROP_BR);
        CEP.TRC(SCCGWA, TDRGROP.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GROP_FLG = 'Y';
        } else {
            WS_GROP_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTGGRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGGRP, this, TDTGGRP_BR);
        CEP.TRC(SCCGWA, TDRGGRP.ACO_AC);
        CEP.TRC(SCCGWA, TDRGGRP.KEY.TR_DATE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GGRP_FLG = 'Y';
        } else {
            WS_GGRP_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTGROP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGROP_BR);
    }
    public void T000_ENDBR_TDTGGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGGRP_BR);
    }
    public void T000_READ_TDTGGRP_FST() throws IOException,SQLException,Exception {
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        TDTGGRP_RD.where = "AC_NO = :TDRGGRP.AC_NO "
            + "AND CAN_FLG = '0' "
            + "AND TR_DATE = :TDRGGRP.KEY.TR_DATE";
        TDTGGRP_RD.fst = true;
        TDTGGRP_RD.order = "JRNNO DESC";
        IBS.READ(SCCGWA, TDRGGRP, this, TDTGGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CAN_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTSMST_XH0() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_BR.rp.order = "VAL_DATE DESC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_STARTBR_TDTSMST_XH() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = :TDRSMST.ACO_STS";
        TDTSMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_STARTBR_TDTSMST_XHC() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = :TDRSMST.ACO_STS "
            + "AND RMK_100 = '1900XH'";
        TDTSMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_STARTBR_TDTSMST_DAY() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND VAL_DATE <= :TDRSMST.VAL_DATE "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "VAL_DATE DESC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLAG = 'F';
        } else {
            WS_SMST_FLAG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_READ_TDTSMST_FST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_READ_IREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.KEY.STR_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IREV_FLG = 'N';
        } else {
            WS_IREV_FLG = 'Y';
        }
    }
    public void T000_READUP_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.where = "ACO_AC = :TDRGROP.KEY.ACO_AC "
            + "AND DRAW_DT = :TDRGROP.KEY.DRAW_DT";
        TDTGROP_RD.upd = true;
        IBS.READ(SCCGWA, TDRGROP, this, TDTGROP_RD);
    }
    public void T000_REWRITE_TDTGGRP() throws IOException,SQLException,Exception {
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.REWRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
    }
    public void T000_WRITE_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        IBS.WRITE(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        IBS.REWRITE(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_GROUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-SMST-AMT=SUM(BAL)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
        CEP.TRC(SCCGWA, DCCUPSWM.RC.RC_CODE);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 5 - 1) + "9" + TDCACDRU.BUSI_CTLW.substring(5 + 1 - 1);
        if (TDCACDRU.OPP_AC_CNO == null) TDCACDRU.OPP_AC_CNO = "";
        JIBS_tmp_int = TDCACDRU.OPP_AC_CNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.OPP_AC_CNO += " ";
        if (TDCACDRU.OPP_AC_CNO.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
            TDCACDRU.STL_MTH = '1';
            TDCACDRU.CT_FLG = '1';
        } else {
            TDCACDRU.STL_MTH = '2';
            TDCACDRU.CT_FLG = '2';
        }
        TDCACDRU.STL_AC = TDCUGRP.OP_AC;
        CEP.TRC(SCCGWA, WS_AC_TYP);
        if (WS_AC_TYP == '2') {
            TDCACDRU.HIS_RMK = "N";
        } else {
            CEP.TRC(SCCGWA, WS_MR_BAL);
            CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
            TDCACDRU.HIS_RMK = "Y";
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_MR_BAL = WS_MR_BAL - TDCACDRU.TXN_AMT;
            } else {
                WS_MR_BAL = WS_MR_BAL + TDCACDRU.TXN_AMT;
            }
            TDCACDRU.VAL_BAL = WS_MR_BAL;
        }
        if (TDCUGRP.DRAW_TYP == '0') {
            TDCACDRU.HIS_RMK = "Y";
        }
        if (TDCUGRP.OPTION.equalsIgnoreCase("XXTKH")) {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "S" + TDCACDRU.BUSI_CTLW.substring(1);
        }
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void S000_CALL_CIZCAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-AC-AGT", CICCAGT);
