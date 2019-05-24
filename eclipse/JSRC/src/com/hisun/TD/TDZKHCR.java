package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZKHCR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTGGRP_RD;
    DBParm TDTSMST_RD;
    brParm TDTGGRP_BR = new brParm();
    brParm TDTSMST_BR = new brParm();
    DBParm TDTCMST_RD;
    DBParm TDTIREV_RD;
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "DD129";
    String K_OUTPUT_FMT1 = "TD016";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_SYS_ERR = "SC6001";
    char WS_MSREL_FLG = ' ';
    char WS_NOT_STANDARD_FLG = ' ';
    char WS_SMST_FLAG = ' ';
    char WS_GGRP_FLG = ' ';
    char WS_IREV_FLG = ' ';
    String WS_MSGID = " ";
    short WS_TS_SEQ = 0;
    String WS_CI_NO = " ";
    short WS_RC = 0;
    double WS_TAX_AMT = 0;
    double WS_TXN_AMT = 0;
    int WS_NEXT_DT = 0;
    double WS_INT = 0;
    double WS_ACCRU_INT = 0;
    String WS_MMO = " ";
    short WS_COUNT1 = 0;
    String WS_KHCR_MMO = " ";
    short WS_CI_TYP = 0;
    TDZKHCR_WS_TDRFHIS WS_TDRFHIS = new TDZKHCR_WS_TDRFHIS();
    char WS_CR_FLG = ' ';
    char WS_GRP_FLG = ' ';
    double WS_BAL_XH = 0;
    String WS_OPP_CI_NO = " ";
    String WS_PROD_CD = " ";
    String WS_AC_TYPE = " ";
    String WS_AC_TYPE_2 = " ";
    short WS_RC_DISP = 0;
    char WS_CRE_AC_FLG = ' ';
    int WS_SMST_CNT = 0;
    double WS_SMST_AMT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUABOX BPCUABOX = new BPCUABOX();
    TDCACCRU TDCACCRU = new TDCACCRU();
    TDCACDRU TDCACDRU = new TDCACDRU();
    CICSACAC CICSACAC = new CICSACAC();
    CICCUST CICCUST = new CICCUST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    SCCBINF SCCBINF = new SCCBINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    TDRGGRP TDRGGRP = new TDRGGRP();
    SCCFMT SCCFMT = new SCCFMT();
    TDCOTRAC TDCOTRAC = new TDCOTRAC();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICACCU CICACCU = new CICACCU();
    TDCOTZZC TDCOTZZC = new TDCOTZZC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCMACO TDCMACO = new TDCMACO();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    TDCMACC TDCMACC = new TDCMACC();
    TDRIREV TDRIREV = new TDRIREV();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDRFHIS TDRFHIS = new TDRFHIS();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    TDCACM TDCACM = new TDCACM();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCOCINM DDCOCINM = new DDCOCINM();
    SCCGWA SCCGWA;
    TDCKHCR TDCKHCR;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCKHCR TDCKHCR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCKHCR = TDCKHCR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A101_CHECK_IPUT();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZKHCR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, TDCKHCR.CT_FLG);
        if (TDCKHCR.CT_FLG == '3') {
            TDCKHCR.CT_FLG = '2';
        }
        if (TDCKHCR.JRNNO != 0) {
            GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO = TDCKHCR.JRNNO;
            SCCGWA.COMM_AREA.CANCEL_IND = 'Y';
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
    }
    public void A100_CHECK_FRG_IND() throws IOException,SQLException,Exception {
        if (TDCKHCR.CT_FLG == '2') {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = TDCKHCR.OPP_AC;
            DDCSCINM.INPUT_DATA.CCY = TDCKHCR.CCY;
            DDCSCINM.INPUT_DATA.FUNC = '2';
            S000_CALL_DDZSCINM();
            CEP.TRC(SCCGWA, DDCOCINM.FRG_IND);
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCKHCR.AC;
            T000_READ_TDTCMST();
            CEP.TRC(SCCGWA, TDRCMST.FRG_IND);
            if (!TDRCMST.FRG_IND.equalsIgnoreCase(DDCOCINM.FRG_IND)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FRG_IND_ERR, SCCBINF);
            }
        }
    }
    public void A101_CHECK_IPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCKHCR.CI_NO);
        if (TDCKHCR.CI_NO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDCKHCR.CI_NO;
            S000_CALL_CIZCUST();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_ERR_CUS_ID_EXP;
                S000_ERR_MSG_PROC();
            }
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_ERR_CUS_ID_COLSED;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCKHCR.MPROD_CD.trim().length() > 0) {
            WS_PROD_CD = TDCKHCR.MPROD_CD;
            R000_GET_SALE_PROD_INFO();
            WS_AC_TYPE = BPCPQPRD.AC_TYPE;
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        if ((WS_AC_TYPE.equalsIgnoreCase("043") 
            || WS_AC_TYPE.equalsIgnoreCase("044")) 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCKHCR.AC;
            T000_READ_SMST_MR();
            R000_CHECK_TSSTS();
            if (WS_AC_TYPE.equalsIgnoreCase("043")) {
                if (TDCKHCR.GFST != '1') {
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
                    CEP.TRC(SCCGWA, TDRGGRP.KEY.JRNNO);
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
                    if (SCCGWA.COMM_AREA.AC_DATE != GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CANCEL_ERR, SCCBINF);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        if (WS_AC_TYPE.equalsIgnoreCase("044") 
            && TDCKHCR.AC.trim().length() > 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B310_SEVEEN_XH();
                B310_SEVEEN_CR();
                if (WS_CR_FLG == 'C') {
                    CEP.TRC(SCCGWA, "TESTA1");
                    B230_CALL_DD_DR_UNT();
                }
            } else {
                if (TDCKHCR.GFST != '1') {
                    CEP.TRC(SCCGWA, TDCKHCR.TXN_AMT);
                    IBS.init(SCCGWA, TDRGGRP);
                    WS_TXN_AMT = TDCKHCR.TXN_AMT;
                    TDRGGRP.AC_NO = TDCKHCR.AC;
                    TDRGGRP.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
                    TDRGGRP.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                    CEP.TRC(SCCGWA, TDCKHCR.AC);
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
                    T000_STARTBR_TDTGGRP();
                    T000_READNEXT_TDTGGRP();
                    while (WS_GGRP_FLG != 'N') {
                        if (TDRGGRP.CDR_FLG == 'D') {
                            CEP.TRC(SCCGWA, "1214-A");
                            IBS.init(SCCGWA, TDCACDRU);
                            TDCACDRU.MAC_CNO = TDCKHCR.AC;
                            TDCACDRU.TXN_AMT = TDRGGRP.TX_AMT;
                            TDCACDRU.ACO_AC = TDRGGRP.ACO_AC;
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
                            TDCACDRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
                            TDCACDRU.CCY = "156";
                            TDCACDRU.CCY_TYP = '1';
                            TDCACDRU.DRAW_MTH = TDCKHCR.DRAW_MTH;
                            TDCACDRU.DRAW_INF = TDCKHCR.PSW;
                            TDCACDRU.BV_TYP = '0';
                            TDCACDRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
                            TDCACDRU.PRDMO_CD = "MMDP";
                            TDCACDRU.HIS_RMK = "N";
                            S000_CALL_TDZACDRU();
                            WS_ACCRU_INT += TDCACDRU.PAYING_INT;
                        } else {
                            if (TDRGGRP.NEW_FLG == 'Y' 
                                && !TDRGGRP.RMK.equalsIgnoreCase("XXTXHZC")) {
                                CEP.TRC(SCCGWA, "1214-B");
                                TDCKHCR.GAC_NO = TDRGGRP.AC_NO;
                                TDCKHCR.GACO_AC = TDRGGRP.ACO_AC;
                                TDCKHCR.TXN_AMT = TDRGGRP.TX_AMT;
                                CEP.TRC(SCCGWA, TDCKHCR.GACO_AC);
                                CEP.TRC(SCCGWA, TDCKHCR.GAC_NO);
                                CEP.TRC(SCCGWA, TDCKHCR.TXN_AMT);
                                B100_CALL_TD_CR_UNT();
                            } else {
                                CEP.TRC(SCCGWA, "1214-C");
                                IBS.init(SCCGWA, TDRSMST);
                                TDRSMST.KEY.ACO_AC = TDRGGRP.ACO_AC;
                                T000_READU_SMST();
                                TDRSMST.BAL = TDRSMST.BAL - TDRGGRP.TX_AMT;
                                if (TDRSMST.BAL == 0) {
                                    IBS.init(SCCGWA, CICSACAC);
                                    CICSACAC.FUNC = 'A';
                                    CICSACAC.DATA.PROD_CD = TDRSMST.PROD_CD;
                                    CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
                                    CICSACAC.DATA.AGR_NO = TDRGGRP.AC_NO;
                                    CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                                    CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
                                    CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                                    S000_CALL_CIZSACAC();
                                    TDRSMST.ACO_STS = '2';
                                }
                                T000_REWRITE_SMST();
                            }
                            if (WS_ACCRU_INT > 0) {
                                R000_GEN_CANCEL_DATA();
                                WS_MMO = "S101";
                                TDCACCRU.HIS_RMK = "Y";
                                TDCACCRU.TXN_AMT = WS_ACCRU_INT;
                                B270_WRT_FHIS();
                            }
                        }
                        IBS.init(SCCGWA, TDRSMST);
                        TDRSMST.AC_NO = TDRGGRP.AC_NO;
                        T000_GROUP_TDTSMST();
                        CICQACAC.FUNC = 'R';
                        CICQACAC.DATA.AGR_NO = TDRGGRP.AC_NO;
                        S000_CALL_CIZQACAC();
                        IBS.init(SCCGWA, TDRSMST);
                        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                        T000_READU_SMST();
                        CEP.TRC(SCCGWA, "UPDATE BAL");
                        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
                        CEP.TRC(SCCGWA, TDRSMST.BAL);
                        TDRSMST.BAL = WS_SMST_AMT;
                        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        T000_REWRITE_SMST();
                        TDRGGRP.CAN_FLG = '1';
                        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        T000_REWRITE_TDTGGRP();
                        T000_READNEXT_TDTGGRP();
                    }
                    T000_ENDBR_TDTGGRP();
                    TDCKHCR.TXN_AMT = WS_TXN_AMT;
                    CEP.TRC(SCCGWA, "TESTA2");
                    B230_CALL_DD_DR_UNT();
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CR_FLG);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCKHCR.GFST);
        if ((WS_CR_FLG != 'C' 
            && WS_AC_TYPE.equalsIgnoreCase("044") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || !WS_AC_TYPE.equalsIgnoreCase("044") 
            || TDCKHCR.GFST == '1') {
            B000_MAIN_PROC_KH();
        }
    }
    public void R000_CHECK_TSSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDCKHCR.TXN_AMT);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_TMP_HLD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
        if (TDRSMST.BAL - TDRSMST.HBAL < TDCKHCR.TXN_AMT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
    }
    public void R000_GEN_CANCEL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACCRU);
        TDCACCRU.OPT = '0';
        TDCACCRU.PRDMO_CD = "MMDP";
        TDCACCRU.PROD_CD = TDCKHCR.PROD_CD;
        TDCACCRU.AC_NO = TDCKHCR.GAC_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCKHCR.GAC_NO;
        S000_CALL_CIZCUST();
        TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
        TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
        if (CICCUST.O_DATA.O_CI_TYP == ' ') WS_CI_TYP = 0;
        else WS_CI_TYP = Short.parseShort(""+CICCUST.O_DATA.O_CI_TYP);
        TDCACCRU.CCY = "156";
        TDCACCRU.CCY_TYP = '1';
        CEP.TRC(SCCGWA, "YES2");
        TDCACCRU.TXN_AMT = WS_INT;
        CEP.TRC(SCCGWA, WS_INT);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCACCRU.AC_NO;
        TDRSMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READUP_SMST_GRP();
        TDCACCRU.TXN_AMT += TDCKHCR.TXN_AMT;
        TDCACCRU.BAL = TDCKHCR.TXN_AMT;
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDCACCRU.INSTR_MTH = '0';
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCKHCR.AC;
        T000_READ_TDTSMST_FST();
        TDCACCRU.TERM = TDRSMST.TERM;
        TDCACCRU.DRAW_MTH = '4';
        TDCACCRU.CHNL_FLG = 'N';
        TDCACCRU.MAIN_FLG = 'C';
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_IREV();
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
        TDCACCRU.CT_FLG = '2';
        TDCACCRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
        TDCACCRU.CALR_STD = TDRSMST.CALR_STD;
        TDCACCRU.OIC_NO = TDRSMST.OIC_NO;
        TDCACCRU.RES_CD = TDRSMST.RES_CD;
        TDCACCRU.SUB_DP = TDRSMST.SUB_DP;
        TDCACCRU.MON_TYP = TDRSMST.MON_TYP;
        TDCACCRU.REMARK = " ";
        TDCACCRU.SHOW = '1';
        TDCACCRU.OPP_BV_TYP = '1';
        if (TDCACCRU.BUSI_CTLW == null) TDCACCRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACCRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.BUSI_CTLW += " ";
        TDCACCRU.BUSI_CTLW = TDCACCRU.BUSI_CTLW.substring(0, 11 - 1) + "N" + TDCACCRU.BUSI_CTLW.substring(11 + 1 - 1);
        TDCACCRU.HIS_AMT = TDCKHCR.TXN_AMT;
        TDCACCRU.HIS_RMK = "N";
        TDCACCRU.XHZC_FLG = 'Y';
    }
    public void B000_MAIN_PROC_KH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCKHCR.BV_TYP);
        CEP.TRC(SCCGWA, TDCKHCR.MPROD_CD);
        if ((TDCKHCR.BV_TYP == '7' 
            || TDCKHCR.BV_TYP == '1' 
            || TDCKHCR.BV_TYP == '0' 
            || TDCKHCR.BV_TYP == '8') 
            && TDCKHCR.MPROD_CD.trim().length() > 0) {
            if (TDCKHCR.AC.trim().length() == 0) {
                IBS.init(SCCGWA, TDCMACO);
                TDCMACO.PROD_CD = TDCKHCR.MPROD_CD;
                TDCMACO.ID_TYPE = TDCKHCR.ID_TYP;
                TDCMACO.ID_NO = TDCKHCR.ID_NO;
                TDCMACO.CI_NO = TDCKHCR.CI_NO;
                TDCMACO.AC_NAME = TDCKHCR.AC_NAME;
                TDCMACO.BV_CD = TDCKHCR.BV_CD;
                TDCMACO.BV_TYPE = TDCKHCR.BV_TYP;
                TDCMACO.CCY = TDCKHCR.CCY;
                if (TDCKHCR.BV_TYP == '7' 
                    || TDCKHCR.BV_TYP == '8') {
                    TDCMACO.BV_TYPE = '0';
                }
                TDCMACO.BV_NO = TDCKHCR.BV_NO;
                TDCMACO.DRAW_MTH = TDCKHCR.DRAW_MTH;
                TDCMACO.DRAW_PSW = TDCKHCR.PSW;
                TDCMACO.D_ID_TYP = TDCKHCR.ID_TYP;
                TDCMACO.D_ID_NO = TDCKHCR.ID_NO;
                TDCMACO.DRAW_INF = TDCKHCR.D_INF;
                TDCMACO.CROS_CR = TDCKHCR.CROS_CR_FLG;
                TDCMACO.CROS_DR = TDCKHCR.CROS_DR_FLG;
                TDCMACO.OPP_AC = TDCKHCR.OPP_AC;
                TDCMACO.ACBR_FLG = TDCKHCR.ACBR_FLG;
                TDCMACO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_TDZMACO();
                TDCKHCR.AC = TDCMACO.MAIN_AC;
                WS_CRE_AC_FLG = 'Y';
                if (WS_AC_TYPE.equalsIgnoreCase("043")) {
                    WS_KHCR_MMO = "C001";
                }
                if (WS_AC_TYPE.equalsIgnoreCase("044")) {
                    WS_KHCR_MMO = "C001";
                }
            }
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        CEP.TRC(SCCGWA, TDCKHCR.MMO);
        B100_CALL_TD_CR_UNT();
        B900_CRE_GGROP();
        CEP.TRC(SCCGWA, TDCKHCR.CT_FLG);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READ_TDTSMST();
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDCKHCR.VAL_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            if (TDCKHCR.CT_FLG == '0') {
                B200_CALL_CSH_DR_UNT();
            } else if (TDCKHCR.CT_FLG == '1') {
                B210_CALL_AI_DR_UNT();
            } else if (TDCKHCR.CT_FLG == '2'
                || TDCKHCR.CT_FLG == '3'
                || TDCKHCR.CT_FLG == '4'
                || TDCKHCR.CT_FLG == '5') {
                CEP.TRC(SCCGWA, "TESTA3");
                B230_CALL_DD_DR_UNT();
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 37);
            }
        } else {
            R000_CHK_DDPSW();
            if (TDCKHCR.OPP_BV != '1' 
                && TDCKHCR.OPP_BV != '4') {
                R000_CHK_DDINFO();
            }
        }
        if (TDCKHCR.CT_FLG == '2' 
            || TDCKHCR.CT_FLG == '3' 
            || TDCKHCR.CT_FLG == '4') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 0121101) {
                    B240_OUTPUT_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, TDCKHCR.CHNL_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (TDCKHCR.CHNL_FLG == 'Y') {
            CEP.TRC(SCCGWA, "OTHER SYSTEM OUTPUT");
            B250_OUTPUT_PROC();
        }
    }
    public void B900_CRE_GGROP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGGRP);
    }
    public void B310_SEVEEN_XH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, TDRSMST);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.DAYS = -7;
        S000_CALL_SCSSCLDT();
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_NEXT_DT = SCCCLDT.DATE2;
        TDRSMST.VAL_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        TDRSMST.AC_NO = TDCKHCR.AC;
        T000_STARTBR_TDTSMST_DAY();
        T000_READNEXT_TDTSMST();
        while (WS_SMST_FLAG != 'N') {
            CEP.TRC(SCCGWA, "XXT7D");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_CR_FLG = 'C';
            WS_BAL_XH += TDRSMST.BAL;
            IBS.init(SCCGWA, TDCACDRU);
            TDCACDRU.MAC_CNO = TDCKHCR.AC;
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
            TDCACDRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
            TDCACDRU.CCY = TDRSMST.CCY;
            TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
            TDCACDRU.DRAW_MTH = TDCKHCR.DRAW_MTH;
            TDCACDRU.DRAW_INF = TDCKHCR.PSW;
            TDCACDRU.BV_TYP = '0';
            TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
            TDCACDRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
            TDCACDRU.PRDMO_CD = "MMDP";
            TDCACDRU.HIS_RMK = "N";
            S000_CALL_TDZACDRU();
            CEP.TRC(SCCGWA, "JF7DAY");
            CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
            WS_INT += TDCACDRU.DRAW_TOT_AMT;
            WS_ACCRU_INT += TDCACDRU.PAYING_INT;
            WS_TAX_AMT += TDCACDRU.PAYING_TAX;
            TDRSMST.ACO_STS = '1';
            TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTSMST();
            IBS.init(SCCGWA, TDRGGRP);
            R000_GET_GROPSEQ();
            TDRGGRP.KEY.SEQ = WS_TS_SEQ;
            TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRGGRP.CAN_FLG = '0';
            TDRGGRP.NEW_FLG = 'N';
            TDRGGRP.CDR_FLG = 'D';
            TDRGGRP.AC_NO = TDCKHCR.AC;
            TDRGGRP.TX_AMT = TDRSMST.BAL;
            TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRGGRP.RMK = "SEVENDAY";
            T000_WRITE_TDTGGRP();
            T000_READNEXT_TDTSMST();
        }
        T000_ENDBR_TDTSMST();
        CEP.TRC(SCCGWA, WS_INT);
    }
    public void B310_SEVEEN_CR() throws IOException,SQLException,Exception {
        if (WS_CR_FLG == 'C') {
            IBS.init(SCCGWA, TDCACCRU);
            TDCACCRU.OPT = '0';
            TDCACCRU.PRDMO_CD = "MMDP";
            TDCACCRU.PROD_CD = TDCKHCR.PROD_CD;
            TDCACCRU.AC_NO = TDCKHCR.AC;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = TDCKHCR.AC;
            S000_CALL_CIZCUST();
            TDCACCRU.CI_NO = CICCUST.O_DATA.O_CI_NO;
            TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
            TDCACCRU.CCY = "156";
            TDCACCRU.CCY_TYP = '1';
            CEP.TRC(SCCGWA, "YES2");
            TDCACCRU.TXN_AMT = WS_INT;
            CEP.TRC(SCCGWA, WS_INT);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACCRU.AC_NO;
            TDRSMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READUP_SMST_GRP();
            TDCACCRU.TXN_AMT += TDCKHCR.TXN_AMT;
            TDCACCRU.BAL = TDCKHCR.TXN_AMT;
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
            TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            TDCACCRU.INSTR_MTH = '0';
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCKHCR.AC;
            T000_READ_TDTSMST_FST();
            TDCACCRU.TERM = TDRSMST.TERM;
            TDCACCRU.DRAW_MTH = '4';
            TDCACCRU.CHNL_FLG = 'N';
            TDCACCRU.MAIN_FLG = 'C';
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IREV();
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
            TDCACCRU.CT_FLG = '2';
            TDCACCRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
            TDCACCRU.CALR_STD = TDRSMST.CALR_STD;
            TDCACCRU.OIC_NO = TDRSMST.OIC_NO;
            TDCACCRU.RES_CD = TDRSMST.RES_CD;
            TDCACCRU.SUB_DP = TDRSMST.SUB_DP;
            TDCACCRU.MON_TYP = TDRSMST.MON_TYP;
            TDCACCRU.REMARK = " ";
            TDCACCRU.SHOW = '1';
            TDCACCRU.OPP_BV_TYP = '1';
            if (TDCACCRU.BUSI_CTLW == null) TDCACCRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACCRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.BUSI_CTLW += " ";
            TDCACCRU.BUSI_CTLW = TDCACCRU.BUSI_CTLW.substring(0, 11 - 1) + "N" + TDCACCRU.BUSI_CTLW.substring(11 + 1 - 1);
            TDCACCRU.HIS_AMT = TDCKHCR.TXN_AMT;
            TDCACCRU.HIS_RMK = "N";
            TDCACCRU.XHZC_FLG = 'Y';
            TDCACCRU.RECOM_TYP = TDRSMST.RECOM_TYP;
            TDCACCRU.RECOM_ID_TYPE = TDRSMST.RECOM_ID_TYPE;
            TDCACCRU.RECOM_ID_NO = TDRSMST.RECOM_ID_NO;
            S000_CALL_TDZACCRU();
            WS_PROD_CD = TDRSMST.PROD_CD;
            R000_GET_SALE_PROD_INFO();
            WS_AC_TYPE_2 = BPCPQPRD.AC_TYPE;
            TDCACCRU.HIS_RMK = "Y";
            TDCACCRU.TXN_AMT = TDCKHCR.TXN_AMT;
            WS_MMO = "C002";
            B270_WRT_FHIS();
            if (WS_ACCRU_INT > 0) {
                WS_MMO = "S101";
                TDCACCRU.HIS_RMK = "Y";
                TDCACCRU.TXN_AMT = WS_ACCRU_INT;
                B270_WRT_FHIS();
            }
        }
    }
    public void B270_WRT_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.CI_NO = TDCACCRU.CI_NO;
        if (TDCACCRU.CT_FLG == '0') {
            BPCPFHIS.DATA.TX_TYPE = 'C';
        } else {
            BPCPFHIS.DATA.TX_TYPE = 'T';
        }
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCPFHIS.DATA.AC = TDCACCRU.AC_NO;
        BPCPFHIS.DATA.ACO_AC = TDCACCRU.ACO_AC;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCACCRU.AC_NO;
        T000_READ_TDTSMST_MR();
        BPCPFHIS.DATA.ACO_AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        if (TDCACCRU.BV_TYP == '4') {
            BPCPFHIS.DATA.TX_TOOL = TDCACCRU.AC_NO;
        }
        BPCPFHIS.DATA.OTH_AC = TDCACCRU.OPP_AC_CNO;
        if (TDCACCRU.OPP_BV_TYP == '1') {
            BPCPFHIS.DATA.OTH_TX_TOOL = TDCACCRU.OPP_AC_CNO;
            BPCPFHIS.DATA.RLT_TX_TOOL = TDCACCRU.OPP_AC_CNO;
        } else {
            BPCPFHIS.DATA.RLT_AC = TDCACCRU.OPP_AC_CNO;
        }
        BPCPFHIS.DATA.BV_NO = TDCACCRU.BV_NO;
        BPCPFHIS.DATA.BV_CODE = TDCACCRU.BV_CD;
        if (TDCACCRU.BV_CD.equalsIgnoreCase("TDBV4")) {
            BPCPFHIS.DATA.BV_CODE = " ";
        }
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        CEP.TRC(SCCGWA, TDCACCRU.CCY_TYP);
        BPCPFHIS.DATA.TX_VAL_DT = TDCACCRU.VAL_DT;
        BPCPFHIS.DATA.TX_CCY = TDCACCRU.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = TDCACCRU.CCY_TYP;
        CEP.TRC(SCCGWA, "PHIS");
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDCACCRU.BAL);
        CEP.TRC(SCCGWA, TDCACCRU.BUSI_CTLW);
        BPCPFHIS.DATA.TX_AMT = TDCACCRU.TXN_AMT;
        BPCPFHIS.DATA.TX_MMO = WS_MMO;
        if (TDCACCRU.OPT == '0' 
            || TDCACCRU.OPT == '2' 
            || TDCACCRU.OPT == '3' 
            || TDCACCRU.OPT == '4') {
            BPCPFHIS.DATA.PROD_CD = TDCACCRU.PROD_CD;
        } else {
            BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        }
        WS_TDRFHIS.WS_FHIS_POST_CD = TDCACCRU.BV_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_TDRFHIS.WS_FHIS_DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        WS_TDRFHIS.WS_FHIS_AC_NAME = TDCACCRU.NAME;
        WS_TDRFHIS.WS_FHIS_INSTR_MTH = TDCACCRU.INSTR_MTH;
        WS_COUNT1 = 0;
        WS_COUNT1 = 276;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.POST_CD = TDCACCRU.BV_CD;
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRFHIS.DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        TDRFHIS.AC_NAME = TDCACCRU.NAME;
        TDRFHIS.INSTR_MTH = TDCACCRU.INSTR_MTH;
        BPCPFHIS.DATA.FMT_CODE = "TD099";
        CEP.TRC(SCCGWA, TDRFHIS.DATA_FIELD_TEXT);
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0) {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        if (TDCACCRU.PROD_CD.equalsIgnoreCase("CDP00580")) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        CEP.TRC(SCCGWA, "WWWWW");
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("0")) {
            BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        } else {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        BPCPFHIS.DATA.RLT_AC_NAME = TDCACCRU.OPP_NAME;
        if (TDCACCRU.CT_FLG == '1' 
            && TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = TDCACCRU.OPP_AC_CNO;
            S000_CALL_AIZPQMIB();
            BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
            BPCPFHIS.DATA.RLT_BANK = "" + AICPQMIB.INPUT_DATA.BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
            BPCPFHIS.DATA.RLT_CCY = AICPQMIB.INPUT_DATA.CCY;
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        if (TDCACCRU.CT_FLG == '2' 
            && TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            BPCPFHIS.DATA.RLT_AC = TDCACCRU.OPP_AC_CNO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
            S000_CALL_CIZACCU();
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
            }
            BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        S000_CALL_BPZPFHIS();
    }
    public void B100_CALL_TD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACCRU);
        CEP.TRC(SCCGWA, TDCKHCR.OPT);
        TDCACCRU.DEPOSIT_TYP = 'Z';
        if (TDCKHCR.OPT == '1') {
            TDCACCRU.OPT = '2';
        }
        if (TDCKHCR.OPT == '2') {
            TDCACCRU.OPT = '3';
        }
        if (TDCKHCR.OPT == '3') {
            TDCACCRU.OPT = '4';
        }
        if (TDCKHCR.OPT != '1' 
            && TDCKHCR.OPT != '2' 
            && TDCKHCR.OPT != '3') {
            TDCACCRU.OPT = '0';
        }
        TDCACCRU.PRDMO_CD = "MMDP";
        TDCACCRU.PROD_CD = TDCKHCR.PROD_CD;
        TDCACCRU.BV_CD = TDCKHCR.BV_CD;
        TDCACCRU.BV_TYP = TDCKHCR.BV_TYP;
        TDCACCRU.BV_NO = TDCKHCR.BV_NO;
        if (TDCKHCR.BV_TYP == '4') {
            TDCACCRU.AC_NO = TDCKHCR.CARD_NO;
            TDCACCRU.CVV = TDCKHCR.CVV;
        }
        TDCACCRU.AC_NO = TDCKHCR.AC;
        TDCACCRU.ID_TYP = TDCKHCR.ID_TYP;
        TDCACCRU.ID_NO = TDCKHCR.ID_NO;
        TDCACCRU.CI_NO = TDCKHCR.CI_NO;
        TDCACCRU.NAME = TDCKHCR.NAME;
        TDCACCRU.TEL = TDCKHCR.TEL;
        TDCACCRU.ADDR = TDCKHCR.ADDR;
        CEP.TRC(SCCGWA, TDCKHCR.CCY);
        TDCACCRU.CCY = TDCKHCR.CCY;
        TDCACCRU.CCY_TYP = TDCKHCR.CCY_TYP;
        TDCACCRU.FC_CD = TDCKHCR.FC_CD;
        TDCACCRU.FC_NO = TDCKHCR.FC_NO;
        CEP.TRC(SCCGWA, "YES2");
        TDCACCRU.TXN_AMT = TDCKHCR.TXN_AMT;
        CEP.TRC(SCCGWA, "YES3");
        TDCACCRU.VAL_DT = TDCKHCR.VAL_DT;
        TDCACCRU.EXP_DT = TDCKHCR.EXP_DT;
        TDCACCRU.INSTR_MTH = TDCKHCR.INSTR_MTH;
        TDCACCRU.INSTR_TERM = TDCKHCR.INSTR_TERM;
        TDCACCRU.TERM = TDCKHCR.TERM;
        CEP.TRC(SCCGWA, TDCKHCR.GRPAUTO_FLG);
        TDCACCRU.GRPAUTO_FLG = TDCKHCR.GRPAUTO_FLG;
        CEP.TRC(SCCGWA, "YES4");
        CEP.TRC(SCCGWA, TDCKHCR.BV_TYP);
        TDCACCRU.CD_AMT = TDCKHCR.CD_AMT;
        TDCACCRU.CD_AMT = TDCKHCR.GK_BAL;
        TDCACCRU.DRAW_MTH = TDCKHCR.DRAW_MTH;
        TDCACCRU.CHNL_FLG = TDCKHCR.CHNL_FLG;
        if (TDCKHCR.TXN_CHNL == null) TDCKHCR.TXN_CHNL = "";
        JIBS_tmp_int = TDCKHCR.TXN_CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCKHCR.TXN_CHNL += " ";
        TDCACCRU.CHNL_FLG = TDCKHCR.TXN_CHNL.substring(0, 1).charAt(0);
        if (TDCKHCR.AC.trim().length() > 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCKHCR.AC;
            if (TDCKHCR.BV_TYP != '4' 
                && TDCACCRU.OPT != '2') {
                T000_READ_TDTCMST();
            }
            if (TDRCMST.PROD_CD.trim().length() > 0) {
                TDCACCRU.MAIN_FLG = 'Y';
            } else {
                TDCACCRU.MAIN_FLG = 'N';
            }
            if (TDCACCRU.OPT == '2') {
                TDCACCRU.MAIN_FLG = 'Y';
            }
        } else {
            if (TDCKHCR.MPROD_CD.trim().length() == 0) {
                TDCACCRU.MAIN_FLG = 'N';
            } else {
                TDCACCRU.MAIN_FLG = 'Y';
            }
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("043") 
            || WS_AC_TYPE.equalsIgnoreCase("044"))) {
            if (WS_CRE_AC_FLG == 'Y') {
                TDCACCRU.FST_FLG = '1';
                TDCKHCR.GFST = '1';
            } else {
                TDCKHCR.SHOW = '1';
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.AC_SEQ);
        CEP.TRC(SCCGWA, TDCKHCR.BV_TYP);
        CEP.TRC(SCCGWA, TDCKHCR.DRAW_MTH);
        if (TDCKHCR.AC.trim().length() == 0) {
            if (TDCKHCR.DRAW_MTH == '1'
                || TDCKHCR.DRAW_MTH == '4') {
                TDCACCRU.CUS_PSW_FLG = TDCKHCR.CUS_PSW_FLG;
                TDCACCRU.DRAW_INF = TDCKHCR.PSW;
                TDCACCRU.PSW = TDCKHCR.PSW;
            } else if (TDCKHCR.DRAW_MTH == '2') {
                TDCACCRU.DRAW_INF = "" + TDCKHCR.SEAL_NO;
                JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF = "0" + TDCACCRU.DRAW_INF;
            } else if (TDCKHCR.DRAW_MTH == '3') {
                if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                if (TDCKHCR.ID_TYP == null) TDCKHCR.ID_TYP = "";
                JIBS_tmp_int = TDCKHCR.ID_TYP.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDCKHCR.ID_TYP += " ";
                TDCACCRU.DRAW_INF = TDCKHCR.ID_TYP + TDCACCRU.DRAW_INF.substring(5);
                if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                if (TDCKHCR.ID_NO == null) TDCKHCR.ID_NO = "";
                JIBS_tmp_int = TDCKHCR.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) TDCKHCR.ID_NO += " ";
                TDCACCRU.DRAW_INF = TDCACCRU.DRAW_INF.substring(0, 6 - 1) + TDCKHCR.ID_NO + TDCACCRU.DRAW_INF.substring(6 + 25 - 1);
            } else if (TDCKHCR.DRAW_MTH == '5') {
                TDCACCRU.DRAW_INF = " ";
            } else {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
                    && TDCKHCR.MPROD_CD.trim().length() > 0) {
                } else {
                    CEP.TRC(SCCGWA, WS_CI_TYP);
                    CEP.TRC(SCCGWA, TDCKHCR.MPROD_CD);
                    CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH, 25);
                }
            }
        }
        CEP.TRC(SCCGWA, "YES5");
        TDCACCRU.CROS_DR_FLG = TDCKHCR.CROS_DR_FLG;
        TDCACCRU.CROS_CR_FLG = TDCKHCR.CROS_CR_FLG;
        TDCACCRU.SPRD_PNT = TDCKHCR.SPRD_PNT;
        TDCACCRU.SPRD_PCT = TDCKHCR.SPRD_PCT;
        TDCACCRU.INT_RUL_CD = TDCKHCR.AC_RUL_CD;
        TDCACCRU.INT_SEL = TDCKHCR.INT_SEL;
        TDCACCRU.JRNNO = TDCKHCR.JRNNO;
        CEP.TRC(SCCGWA, TDCKHCR.CROS_DR_FLG);
        CEP.TRC(SCCGWA, TDCKHCR.CROS_CR_FLG);
        CEP.TRC(SCCGWA, TDCKHCR.SPRD_PNT);
        CEP.TRC(SCCGWA, TDCKHCR.SPRD_PCT);
        CEP.TRC(SCCGWA, TDCKHCR.AC_RUL_CD);
        CEP.TRC(SCCGWA, TDCKHCR.INT_SEL);
        CEP.TRC(SCCGWA, TDCKHCR.INT_RAT);
        TDCACCRU.INT_RAT = TDCKHCR.INT_RAT;
        TDCACCRU.PRT_FLG = TDCKHCR.PRT_FLG;
        TDCACCRU.OIC_NO = SCCGWA.COMM_AREA.TL_ID;
        TDCACCRU.CT_FLG = TDCKHCR.CT_FLG;
        CEP.TRC(SCCGWA, TDCKHCR.OPP_CARD_NO);
        CEP.TRC(SCCGWA, TDCKHCR.OPP_AC);
        if (TDCKHCR.OPP_CARD_NO.trim().length() > 0) {
            TDCACCRU.OPP_AC_CNO = TDCKHCR.OPP_CARD_NO;
        } else {
            TDCACCRU.OPP_AC_CNO = TDCKHCR.OPP_AC;
        }
        TDCACCRU.TXN_CHNL = TDCKHCR.TXN_CHNL;
        if (TDCKHCR.TXN_PNT == ' ') TDCACCRU.TXN_PNT = 0;
        else TDCACCRU.TXN_PNT = Integer.parseInt(""+TDCKHCR.TXN_PNT);
        TDCACCRU.CALR_STD = TDCKHCR.CALR_STD;
        TDCACCRU.INT_AC = TDCKHCR.INT_AC;
        TDCACCRU.STL_AC = TDCKHCR.STL_INT_AC;
        TDCACCRU.DH_FLG = TDCKHCR.DH_FLG;
        TDCACCRU.PRV_RAT = TDCKHCR.PRV_RAT;
        TDCACCRU.AG_BR = TDCKHCR.AG_BR;
        TDCACCRU.XC_UP_FLG = TDCKHCR.XC_UP_FLG;
        TDCACCRU.TXN_MMO = TDCKHCR.MMO;
        if (WS_AC_TYPE.equalsIgnoreCase("043") 
            || WS_AC_TYPE.equalsIgnoreCase("044") 
            && WS_KHCR_MMO.trim().length() > 0) {
            TDCACCRU.TXN_MMO = WS_KHCR_MMO;
        }
        TDCACCRU.AC_NAME = TDCKHCR.AC_NAME;
        TDCACCRU.TRK2 = TDCKHCR.AC_TRK2;
        TDCACCRU.TRK3 = TDCKHCR.AC_TRK3;
        TDCACCRU.AC_NAME = TDCKHCR.AC_NAME;
        TDCACCRU.ACTI_NO = TDCKHCR.ACTI_NO;
        TDCACCRU.EXP_DT = TDCKHCR.EXP_DT;
        TDCACCRU.D_ID_TYP = TDCKHCR.ATTY_ID_TYP;
        TDCACCRU.REMMIT_BK = TDCKHCR.RMIT_BK;
        TDCACCRU.D_ID_NO = TDCKHCR.ATTY_ID_NO;
        TDCACCRU.DRAW_INF = TDCKHCR.D_INF;
        TDCACCRU.RATE_TYP = TDCKHCR.RATE_TYP;
        TDCACCRU.TENOR = TDCKHCR.TENOR;
        TDCACCRU.CHQ_PSW = TDCKHCR.CHQ_PSW;
        TDCACCRU.OPP_CVV = TDCKHCR.OPP_CVV;
        TDCACCRU.TXN_MMO = TDCKHCR.MMO;
        TDCACCRU.SEAL_NO = TDCKHCR.SEAL_NO;
        TDCACCRU.CD_AUTO_FLG = TDCKHCR.AUTO_FLG;
        TDCACCRU.PERD_TYP = TDCKHCR.PERD_TYP;
        TDCACCRU.CD_PERD = TDCKHCR.CD_PERD;
        TDCACCRU.FRAT_DT = TDCKHCR.FRAT_DT;
        TDCACCRU.GK_BVTYP = TDCKHCR.GK_BVTYP;
        TDCACCRU.CD_AMT = TDCKHCR.GK_BAL;
        TDCACCRU.GK_PSW = TDCKHCR.GK_PSW;
        TDCACCRU.INOUT_FLG = TDCKHCR.INOUT;
        TDCACCRU.REMMIT_NM = TDCKHCR.RMIT_NM;
        TDCACCRU.FRG_IND = TDCKHCR.FRG_IND;
        TDCACCRU.OIC_NO = TDCKHCR.OIC_NO;
        TDCACCRU.RES_CD = TDCKHCR.RES_CD;
        TDCACCRU.SUB_DP = TDCKHCR.SUB_DP;
        TDCACCRU.MON_TYP = TDCKHCR.MON_TYP;
        TDCACCRU.ACO_TYP = TDCKHCR.ACO_TYP;
        TDCACCRU.REMARK = TDCKHCR.REMARK;
        TDCACCRU.OVE_RAT = TDCKHCR.OVE_RAT;
        TDCACCRU.DUE_RAT = TDCKHCR.DUE_RAT;
        TDCACCRU.SHOW = TDCKHCR.SHOW;
        TDCACCRU.ZC_NUM = TDCKHCR.ZC_NUM;
        TDCACCRU.BVPRT_FLG = TDCKHCR.BVPRT_FLG;
        TDCACCRU.RULE = TDCKHCR.RULE;
        TDCACCRU.OPP_BV_TYP = TDCKHCR.OPP_BV;
        if (TDCKHCR.GRPAUTO_FLG == 'Y') {
            TDCACCRU.ACO_AC = TDCKHCR.ACO_AC;
        }
        TDCACCRU.OPP_NAME = TDCKHCR.OPP_NAME;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCACCRU.ACO_AC = TDCKHCR.GACO_AC;
            TDCACCRU.AC_NO = TDCKHCR.GAC_NO;
            TDCACCRU.FST_FLG = TDCKHCR.GFST;
        }
        TDCACCRU.CD_AC = TDCKHCR.CD_AC;
        TDCACCRU.CDS_DT = TDCKHCR.CDS_DT;
        TDCACCRU.JBR_CI_NM = TDCKHCR.JBR_CI_NM;
        TDCACCRU.JBR_TEL_NO = TDCKHCR.JBR_TEL_NO;
        TDCACCRU.JBR_ID_TYP = TDCKHCR.JBR_ID_TYP;
        TDCACCRU.JBR_ID_NO = TDCKHCR.JBR_ID_NO;
        TDCACCRU.RECOM_TYP = TDCKHCR.RECOM_TYP;
        TDCACCRU.RECOM_ID_TYPE = TDCKHCR.RECOM_ID_TYPE;
        TDCACCRU.RECOM_ID_NO = TDCKHCR.RECOM_ID_NO;
        CEP.TRC(SCCGWA, TDCKHCR.NOTI_FLG);
        TDCACCRU.NOTI_FLG = TDCKHCR.NOTI_FLG;
        TDCACCRU.ACBR_FLG = TDCKHCR.ACBR_FLG;
        if (TDCKHCR.CHNL_FLG != 'Y') {
            S000_CALL_TDZACCRU();
        } else {
            S000_CALL_TDZACCRU_NOFMT();
        }
        TDCKHCR.EXP_DT = TDCACCRU.EXP_DT;
        TDCKHCR.INT_RAT = TDCACCRU.INT_RAT;
        TDCKHCR.EXP_INT = TDCACCRU.EXP_INT;
        TDCKHCR.AG_BR = TDCACCRU.AG_BR;
        TDCKHCR.GACO_AC = TDCACCRU.ACO_AC;
        TDCKHCR.AC = TDCACCRU.AC_NO;
        TDCKHCR.AC_SEQ = TDCACCRU.AC_SEQ;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && TDCKHCR.GFST == '1') {
            IBS.init(SCCGWA, TDCMACC);
            TDCMACC.AC_NO = TDCKHCR.GAC_NO;
            TDCMACC.BV_TYPE = '0';
            TDCMACC.DRAW_INF = "GROPDRAW";
            S000_CALL_TDZMACC();
            IBS.init(SCCGWA, BPCSOCAC);
            BPCSOCAC.AC = TDCKHCR.GAC_NO;
            BPCSOCAC.STS = 'N';
            S000_CALL_BPZSOCAC();
        }
    }
    public void B200_CALL_CSH_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = TDCKHCR.CCY;
        BPCUABOX.CCY_TYP = TDCKHCR.CCY_TYP;
        BPCUABOX.AMT = TDCKHCR.TXN_AMT;
        BPCUABOX.OPP_AC = TDCKHCR.AC;
        BPCUABOX.OPP_ACNM = TDCKHCR.NAME;
        BPCUABOX.CI_NO = TDCKHCR.CI_NO;
        BPCUABOX.ID_TYP = TDCKHCR.ID_TYP;
        BPCUABOX.IDNO = TDCKHCR.ID_NO;
        if (WS_CI_TYP == 1) {
            BPCUABOX.CASH_NO = "050";
        } else {
            if (WS_CI_TYP == 2) {
                BPCUABOX.CASH_NO = "010";
            }
        }
        BPCUABOX.AGT_IDTYP = TDCKHCR.ATTY_ID_TYP;
        BPCUABOX.AGT_IDNO = TDCKHCR.ATTY_ID_NO;
        BPCUABOX.AGT_NAME = TDCKHCR.ATTY_NAME;
        BPCUABOX.APP_NO = TDCKHCR.APP_NO;
        S000_CALL_BPZUABOX();
    }
    public void B210_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = TDCKHCR.OPP_AC;
        AICUUPIA.DATA.RVS_NO = TDCKHCR.OPP_REV_NO;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        AICUUPIA.DATA.AMT = TDCKHCR.TXN_AMT;
        AICUUPIA.DATA.CCY = TDCKHCR.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.THEIR_AC = TDCKHCR.AC;
        AICUUPIA.DATA.PAY_MAN = TDCKHCR.AC_NAME;
        AICUUPIA.DATA.PAY_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
    }
    public void R000_GET_GROPSEQ() throws IOException,SQLException,Exception {
        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_TDTGGRP_MAX();
        WS_TS_SEQ += 1;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_DISP = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_MSGID = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR  " + WS_RC_DISP;
            CEP.ERR(SCCGWA, K_SYS_ERR, SCCBINF);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_MSGID = "" + BPCSOCAC.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZMACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ZM-ACC-PROC", TDCMACC);
    }
    public void B220_CALL_DC_DR_UNT() throws IOException,SQLException,Exception {
    }
    public void B230_CALL_DD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, TDCKHCR.TXN_AMT);
        DDCUDRAC.AC = TDCKHCR.OPP_AC;
        IBS.init(SCCGWA, CICACCU);
        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
        DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 4 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(4 + 1 - 1);
        CICACCU.DATA.AGR_NO = TDCKHCR.OPP_AC;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = TDCKHCR.OPP_AC;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        DDCUDRAC.PSWD = TDCKHCR.OPP_PSW;
        DDCUDRAC.PAY_TYPE = TDCKHCR.OPP_DRAW_MTH;
        DDCUDRAC.ID_TYPE = TDCKHCR.OPP_ID_TYP;
        DDCUDRAC.ID_NO = TDCKHCR.OPP_ID_NO;
        DDCUDRAC.PSBK_NO = TDCKHCR.OPP_BV_NO;
        DDCUDRAC.CCY = TDCKHCR.CCY;
        DDCUDRAC.CCY_TYPE = TDCKHCR.CCY_TYP;
        DDCUDRAC.TX_AMT = TDCKHCR.TXN_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        DDCUDRAC.CHQ_TYPE = TDCKHCR.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = TDCKHCR.CHQ_NO;
        DDCUDRAC.PAY_PSWD = TDCKHCR.CHQ_PSW;
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.AC;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.OTH_TX_TOOL = TDCKHCR.AC;
        }
        if (TDCKHCR.CT_FLG == '0') {
            DDCUDRAC.TX_TYPE = 'C';
        } else {
            DDCUDRAC.TX_TYPE = 'T';
        }
        if (TDCKHCR.CT_FLG == '2') {
            DDCUDRAC.CHK_PSW = 'P';
        }
        CEP.TRC(SCCGWA, TDCKHCR.ATTY_ID_TYP);
        DDCUDRAC.BV_TYP = TDCKHCR.OPP_BV;
        if (TDCKHCR.MMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A005";
        } else {
            DDCUDRAC.TX_MMO = TDCKHCR.MMO;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
        CEP.TRC(SCCGWA, TDCKHCR.CUS_PSW_FLG);
        if (TDCACCRU.OPT == '3' 
            || TDCKHCR.OPT == '4' 
            || TDCKHCR.CUS_PSW_FLG == 'N') {
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.PCHQ_FLG = 'Y';
            CEP.TRC(SCCGWA, DDCUDRAC.PCHQ_FLG);
        }
        CEP.TRC(SCCGWA, DDCUDRAC.PCHQ_FLG);
        CEP.TRC(SCCGWA, TDCKHCR.PAY_PSW);
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW);
        DDCUDRAC.BANK_DR_FLG = 'N';
        DDCUDRAC.CHQ_ISS_DATE = TDCKHCR.CHQ_DATE;
        DDCUDRAC.OTHER_AC_NM = TDCKHCR.AC_NAME;
        DDCUDRAC.OTHER_CCY = TDCKHCR.CCY;
        DDCUDRAC.OTHER_AC = TDCKHCR.AC;
        DDCUDRAC.OTHER_AMT = TDCKHCR.TXN_AMT;
        if (TDCKHCR.AUTO_CR == 'Y') {
            DDCUDRAC.SIGN_FLG = 'Y';
        }
        if (TDCKHCR.SHOW == '1') {
            if (!WS_AC_TYPE.equalsIgnoreCase("043") 
                && !WS_AC_TYPE.equalsIgnoreCase("044")) {
                DDCUDRAC.HIS_SHOW_FLG = 'N';
            }
        }
        DDCUDRAC.SUPPLY_FLG = 'N';
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        if (WS_AC_TYPE.equalsIgnoreCase("043") 
            || WS_AC_TYPE.equalsIgnoreCase("044")) {
            DDCUDRAC.CHK_LMT_FLG = '4';
        }
        CEP.TRC(SCCGWA, TDCKHCR.OPP_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.OPP_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CHK_LMT_FLG = '4';
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        if (CICACCU.DATA.ENTY_TYP == '6' 
            || CICACCU.DATA.ENTY_TYP == '7') {
            if (TDCKHCR.PROD_CD.equalsIgnoreCase("1202011101")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_ERR);
            }
        }
        CEP.TRC(SCCGWA, "PR0125");
        CEP.TRC(SCCGWA, TDCKHCR.BV_TYP);
        CEP.TRC(SCCGWA, TDCKHCR.OPP_BV);
        if (CICACCU.DATA.ENTY_TYP == '5' 
            || CICACCU.DATA.ENTY_TYP == '6') {
            DDCUDRAC.EA_CHK_FLG = 'N';
        }
        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
        DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 4 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(4 + 1 - 1);
        S000_CALL_DDZUDRAC();
        CEP.TRC(SCCGWA, "YSY111");
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        TDCKHCR.DD_SUB_AC = DDCUDRAC.AC;
    }
    public void B240_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOTRAC);
        TDCOTRAC.TX_TYP = 'T';
        TDCOTRAC.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOTRAC.FR_AC = TDCKHCR.OPP_AC;
        TDCOTRAC.ENG_NM1 = " ";
        TDCOTRAC.CHN_NM1 = TDCKHCR.OPP_NAME;
        TDCOTRAC.TO_AC = TDCKHCR.AC;
        TDCOTRAC.ENG_NM2 = " ";
        TDCOTRAC.CHN_NM2 = TDCKHCR.NAME;
        TDCOTRAC.FR_CCY = TDCKHCR.CCY;
        TDCOTRAC.FR_CCY_TYPE = TDCKHCR.CCY_TYP;
        TDCOTRAC.TO_CCY = TDCKHCR.CCY;
        TDCOTRAC.TO_CCY_TYPE = TDCKHCR.CCY_TYP;
        TDCOTRAC.FR_AMT = TDCKHCR.TXN_AMT;
        TDCOTRAC.TO_AMT = TDCKHCR.TXN_AMT;
        TDCOTRAC.FR_PSBK = TDCKHCR.OPP_BV_NO;
        TDCOTRAC.FR_CARD = TDCKHCR.OPP_CARD_NO;
        TDCOTRAC.TO_CARD = TDCKHCR.CARD_NO;
        TDCOTRAC.VAL_DATE = TDCKHCR.VAL_DT;
        TDCOTRAC.TX_REF = " ";
        TDCOTRAC.TICKET_NO = " ";
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOTRAC;
        SCCFMT.DATA_LEN = 1292;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B250_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOTZZC);
        CEP.TRC(SCCGWA, TDCACCRU.CHNL_OUTPUT_INFO.O_MAIN_SEQ);
        TDCOTZZC.PRDAC_CD = TDCACCRU.CHNL_OUTPUT_INFO.O_PRDAC_CD;
        TDCOTZZC.BV_TYP = TDCACCRU.CHNL_OUTPUT_INFO.O_BV_TYP;
        TDCOTZZC.BV_CD = TDCACCRU.CHNL_OUTPUT_INFO.O_BV_CD;
        TDCOTZZC.BV_NO = TDCACCRU.CHNL_OUTPUT_INFO.O_BV_NO;
        TDCOTZZC.MAIN_AC = TDCACCRU.CHNL_OUTPUT_INFO.O_MAIN_AC;
        TDCOTZZC.MAIN_SEQ = TDCACCRU.CHNL_OUTPUT_INFO.O_MAIN_SEQ;
        TDCOTZZC.AC = TDCACCRU.CHNL_OUTPUT_INFO.O_AC;
        TDCOTZZC.NAME = TDCACCRU.CHNL_OUTPUT_INFO.O_NAME;
        TDCOTZZC.TXN_AMT = TDCACCRU.CHNL_OUTPUT_INFO.O_TXN_AMT;
        TDCOTZZC.CCY = TDCACCRU.CHNL_OUTPUT_INFO.O_CCY;
        TDCOTZZC.OPEN_DATE = TDCACCRU.CHNL_OUTPUT_INFO.O_OPEN_DATE;
        TDCOTZZC.TX_DATE = TDCACCRU.CHNL_OUTPUT_INFO.O_TX_DATE;
        TDCOTZZC.VAL_DATE = TDCACCRU.CHNL_OUTPUT_INFO.O_VAL_DATE;
        TDCOTZZC.EXP_DATE = TDCACCRU.CHNL_OUTPUT_INFO.O_EXP_DATE;
        TDCOTZZC.TERMS = TDCACCRU.CHNL_OUTPUT_INFO.O_TERMS;
        TDCOTZZC.INT_RAT = TDCACCRU.CHNL_OUTPUT_INFO.O_INT_RAT;
        TDCOTZZC.EXP_INT = TDCACCRU.CHNL_OUTPUT_INFO.O_EXP_INT;
        TDCOTZZC.DRAW_MTH = TDCACCRU.CHNL_OUTPUT_INFO.O_DRAW_MTH;
        TDCOTZZC.CI_NAME = TDCKHCR.AC_NAME;
        IBS.init(SCCGWA, DCCPACTY);
        if (TDCKHCR.OPP_CARD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, TDCKHCR.OPP_CARD_NO);
            DCCPACTY.INPUT.AC = TDCKHCR.OPP_CARD_NO;
        } else {
            if (TDCKHCR.OPP_AC.trim().length() > 0) {
                CEP.TRC(SCCGWA, TDCKHCR.OPP_AC);
                DCCPACTY.INPUT.AC = TDCKHCR.OPP_AC;
            }
        }
        CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        TDCKHCR.DD_SUB_AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, TDCKHCR.OPP_CARD_NO);
        CEP.TRC(SCCGWA, TDCKHCR.DD_SUB_AC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCOTZZC;
        SCCFMT.DATA_LEN = 1035;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_SALE_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
    }
    public void R000_CHK_DDPSW() throws IOException,SQLException,Exception {
        if ((TDCKHCR.OPP_DRAW_MTH == '4' 
            || TDCKHCR.OPP_DRAW_MTH == '1') 
            && (SCCGWA.COMM_AREA.CANCEL_IND != 'Y')) {
            if (TDCKHCR.OPP_BV == '2') {
                IBS.init(SCCGWA, DDCIMPAY);
                DDCIMPAY.AC = TDCKHCR.OPP_AC;
                DDCIMPAY.FUNC = 'C';
                DDCIMPAY.PSWD_OLD = TDCKHCR.OPP_PSW;
                DDCIMPAY.ID_TYPE = TDCKHCR.OPP_ID_TYP;
                DDCIMPAY.ID_NO = TDCKHCR.OPP_ID_NO;
                DDCIMPAY.AC_CNAME = TDCKHCR.AC_NAME;
                S000_CALL_DDZIMPAY();
            } else {
                if (TDCKHCR.OPP_BV == '1' 
                    || TDCKHCR.OPP_BV == '4') {
                    CEP.TRC(SCCGWA, TDCKHCR.OPP_PSW);
                    IBS.init(SCCGWA, DCCPCDCK);
                    DCCPCDCK.FUNC_CODE = 'P';
                    DCCPCDCK.CARD_NO = TDCKHCR.OPP_AC;
                    CEP.TRC(SCCGWA, DCCPCDCK.CARD_NO);
                    DCCPCDCK.CARD_PSW = TDCKHCR.OPP_PSW;
                    DCCPCDCK.TRK2_DAT = TDCKHCR.AC_TRK2;
                    DCCPCDCK.TRK3_DAT = TDCKHCR.AC_TRK3;
                    S000_CALL_DCZPCDCK();
                }
            }
        }
    }
    public void R000_CHK_DDINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQIFA);
        DDCSQIFA.INPUT_DATA.AC_NO = TDCKHCR.OPP_AC;
        DDCSQIFA.INPUT_DATA.CCY = TDCKHCR.CCY;
        DDCSQIFA.INPUT_DATA.CCY_TYPE = TDCKHCR.CCY_TYP;
        DDCSQIFA.INPUT_DATA.AC_OLD = TDCKHCR.OPP_AC;
        S000_CALL_DDZSQIFA();
        CEP.TRC(SCCGWA, DDCSQIFA.OUTPUT_DATA.STS_WORD);
        CEP.TRC(SCCGWA, DDCSQIFA.OUTPUT_DATA.AC_STS);
        if (DDCSQIFA.OUTPUT_DATA.STS_WORD == null) DDCSQIFA.OUTPUT_DATA.STS_WORD = "";
        JIBS_tmp_int = DDCSQIFA.OUTPUT_DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCSQIFA.OUTPUT_DATA.STS_WORD += " ";
        if (DDCSQIFA.OUTPUT_DATA.AC_STS == 'O' 
            || DDCSQIFA.OUTPUT_DATA.STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE);
        }
    }
    public void S000_CALL_DDZSQINF() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DDZSQIFA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-IFA", DDCSQIFA);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CPN-DCZUPSWM", DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
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
    public void T000_READ_SMST_MR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READUP_SMST_GRP() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND OPEN_DATE = :TDRSMST.OPEN_DATE "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRP_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRP_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void T000_STARTBR_TDTGGRP() throws IOException,SQLException,Exception {
        WS_GGRP_FLG = 'N';
        TDTGGRP_BR.rp = new DBParm();
        TDTGGRP_BR.rp.TableName = "TDTGGRP";
        TDTGGRP_BR.rp.where = "AC_NO = :TDRGGRP.AC_NO "
            + "AND JRNNO = :TDRGGRP.KEY.JRNNO "
            + "AND CAN_FLG = '0' "
            + "AND TR_DATE = :TDRGGRP.KEY.TR_DATE";
        TDTGGRP_BR.rp.upd = true;
        TDTGGRP_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, TDRGGRP, this, TDTGGRP_BR);
    }
    public void T000_REWRITE_TDTGGRP() throws IOException,SQLException,Exception {
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.REWRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
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
    public void T000_READ_TDTSMST_MR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_ENDBR_TDTGGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGGRP_BR);
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
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, SCCBINF);
        }
    }
    public void T000_READU_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void R000_GET_AC_NM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.DD_SUB_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICACCU.DATA.ENTY_TYP = '1';
        S000_CALL_CIZACCU();
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
        CICACCU.DATA.ENTY_TYP = '1';
        S000_CALL_CIZACCU();
        WS_OPP_CI_NO = CICACCU.DATA.CI_NO;
    }
    public void R000_CHECK_DD_CARD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OPP_CI_NO);
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = TDCKHCR.OPP_CARD_NO;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "04";
        if (WS_CI_NO.equalsIgnoreCase(WS_OPP_CI_NO)) {
            DCCPFTCK.VAL.SNAME_TRF_FLG = 'Y';
        } else {
            DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
        }
        DCCPFTCK.VAL.TXN_CCY = TDCKHCR.CCY;
        DCCPFTCK.VAL.TXN_AMT = TDCKHCR.TXN_AMT;
        DCCPFTCK.TRK2_DAT = TDCKHCR.OPP_TRK2;
        DCCPFTCK.TRK3_DAT = TDCKHCR.OPP_TRK3;
        if (TDCKHCR.CT_FLG == '2') {
            S000_CALL_DCZPFTCK();
        }
    }
    public void T000_STARTBR_TDTSMST_DAY() throws IOException,SQLException,Exception {
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
            CEP.TRC(SCCGWA, "1");
            WS_SMST_FLAG = 'F';
        } else {
            CEP.TRC(SCCGWA, "2");
            WS_SMST_FLAG = 'N';
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_COUNT_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-SMST-CNT=COUNT(*)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_WRITE_TDTGGRP() throws IOException,SQLException,Exception {
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.WRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
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
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
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
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCKHCR.EXP_DT);
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU);
    }
    public void S000_CALL_TDZACCRU_NOFMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU, true);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZPDRAW() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MACO-CR", TDCMACO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
