package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUTCGD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm DCTTTOD_RD;
    brParm DCTTTOD_BR = new brParm();
    DBParm DDTCCY_RD;
    brParm DCTIQRS_BR = new brParm();
    boolean pgmRtn = false;
    String WS_OVR_NO = " ";
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    String WS_AC_NO = " ";
    String CPN_U_BPZCINTI = "BP-C-INTR-INQ";
    String K_PRDPR_TYPE = "PRDPR";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_DDZIMCCY = "DD-I-NFIN-M-CCY";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_VA_AC = " ";
    double WS_AMT = 0;
    double WS_PAYING_INT = 0;
    double WS_DD_BAL = 0;
    double WS_TD_START_AMT = 0;
    double WS_TD_MIX_DRW_AMT = 0;
    double WS_TOTAL_TXN_AMT = 0;
    double WS_DD_TXN_AMT = 0;
    double WS_TD_TXN_AMT = 0;
    double WS_LEFT_AMT = 0;
    double WS_TD_BAL = 0;
    short WS_PARM_CNT = 0;
    int WS_CNT = 0;
    int WS_CNT2 = 0;
    short WS_B = 0;
    String WS_OVR_NO1 = " ";
    String WS_DD_AC1 = " ";
    String WS_LINK_ACNO1 = " ";
    String WS_LINK_ACNO2 = " ";
    String WS_LINK_ACNO3 = " ";
    String WS_LINK_ACNO4 = " ";
    String WS_LINK_ACNO5 = " ";
    String WS_DRW_USAGE = " ";
    String WS_PROD_CODE = " ";
    double WS_TD_PAY_INT = 0;
    double WS_TD_PRINCIPAL_AMT = 0;
    double WS_MAX_TRC_AMT = 0;
    double WS_TD_AVA_AMT = 0;
    double WS_XXTCZZH_AMT = 0;
    double WS_XXTCZ_PRINCIPAL_AMT = 0;
    short WS_PARM_BUTI_NUM = 0;
    short WS_TD_BUTI_NUM = 0;
    DCZUTCGD_WS_BBBB WS_BBBB = new DCZUTCGD_WS_BBBB();
    char WS_DCTIQRS_FLG = ' ';
    char WS_TCCY_FLG = ' ';
    char WS_DCTCIDEP_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_TTOD_FLG = ' ';
    char WS_TD_AMT_FLG = ' ';
    char WS_DRAW_MTH_FLG = ' ';
    char WS_MMO_ERR_FLG = ' ';
    char WS_LNK_ACNO_FLG = ' ';
    char WS_BUKUAN_FLG = ' ';
    char WS_DRAW_TYPE_FLG = ' ';
    char WS_BUTI_MAX_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    TDCACE TDCACE = new TDCACE();
    DCRTTOD DCRTTOD = new DCRTTOD();
    DCRIQRS DCRIQRS = new DCRIQRS();
    DCCUTTOD DCCUTTOD = new DCCUTTOD();
    TDCACDRU TDCACDRU = new TDCACDRU();
    TDCACCRU TDCACCRU = new TDCACCRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUCCAL DDCUCCAL = new DDCUCCAL();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    DCRCIDEP DCRCIDEP = new DCRCIDEP();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDRCCY DDRCCY = new DDRCCY();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDCUGRP TDCUGRP = new TDCUGRP();
    DDCSPINT DDCSPINT = new DDCSPINT();
    TDRSMST TDRSMST = new TDRSMST();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUMPRM DCCUMPRM = new DCCUMPRM();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUTCGD DCCUTCGD;
    public void MP(SCCGWA SCCGWA, DCCUTCGD DCCUTCGD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUTCGD = DCCUTCGD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUTCGD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B500_CANCEL_YES_INFO();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, DCCUTCGD.FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.CHNL_FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.FUNC);
            CEP.TRC(SCCGWA, DCCUTCGD.AC);
            CEP.TRC(SCCGWA, DCCUTCGD.CCY);
            CEP.TRC(SCCGWA, DCCUTCGD.CCY_TYP);
            CEP.TRC(SCCGWA, DCCUTCGD.AMT);
            CEP.TRC(SCCGWA, DCCUTCGD.MMO);
            CEP.TRC(SCCGWA, DCCUTCGD.CANC_FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.SIGN_FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.ZERO_FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.PROD_CD);
            if (DCCUTCGD.SIGN_FLG == 'Y') {
                if (DCCUTCGD.FUNC == 'P') {
                    B100_GET_PARM_INFO();
                    if (pgmRtn) return;
                    B400_DDCR_PROC();
                    if (pgmRtn) return;
                }
            } else {
                B300_UNSIGN_TDDR_PROC();
                if (pgmRtn) return;
                B400_DDCR_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
            if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                DCCUTCGD.BAL_FLG = '2';
            } else {
                DCCUTCGD.BAL_FLG = '1';
            }
            CEP.TRC(SCCGWA, DCCUTCGD.BAL_FLG);
        }
        DCCUTCGD.AVA_AMT = WS_TOTAL_TXN_AMT;
        DCCUTCGD.PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT;
        DCCUTCGD.INTEREST_AMT = WS_TD_PAY_INT;
        CEP.TRC(SCCGWA, DCCUTCGD.AVA_AMT);
        CEP.TRC(SCCGWA, DCCUTCGD.PRINCIPAL_AMT);
        CEP.TRC(SCCGWA, DCCUTCGD.INTEREST_AMT);
    }
    public void B100_GET_PARM_INFO() throws IOException,SQLException,Exception {
        WS_AC_NO = DCCUTCGD.AC;
        WS_AMT = DCCUTCGD.AMT;
        WS_TOTAL_TXN_AMT = 0;
        WS_TD_PRINCIPAL_AMT = 0;
        WS_TD_PAY_INT = 0;
        T000_STARTBR_DCTIQRS();
        if (pgmRtn) return;
        T000_READNEXT_DCTIQRS();
        if (pgmRtn) return;
        while (WS_DCTIQRS_FLG != 'N' 
            && WS_AMT != 0) {
            B110_CHK_DRAW_USAGE();
            if (pgmRtn) return;
            if (WS_MMO_ERR_FLG == 'Y' 
                && WS_DRAW_TYPE_FLG == 'Y') {
            } else {
                while ((WS_MMO_ERR_FLG != 'Y' 
                    || WS_DRAW_TYPE_FLG != 'Y') 
                    && WS_DCTIQRS_FLG != 'N') {
                    CEP.TRC(SCCGWA, "TEST PPP:");
                    T000_READNEXT_DCTIQRS();
                    if (pgmRtn) return;
                    B110_CHK_DRAW_USAGE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "TEST PPP EXIT:");
                }
            }
            if (WS_DCTIQRS_FLG == 'N') {
                if (WS_MMO_ERR_FLG == 'N' 
                    && WS_DRAW_TYPE_FLG == 'Y') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IQRS_USEAGE_ERR, DCCUTCGD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IQRS_DRW_TYP_ERR, DCCUTCGD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            WS_OVR_NO1 = DCRIQRS.KEY.OVR_NO;
            WS_DD_AC1 = DCCUTCGD.AC;
            WS_PROD_CODE = DCRIQRS.PROD_CODE;
            WS_LINK_ACNO1 = DCRIQRS.LINK_AC1;
            WS_LINK_ACNO2 = DCRIQRS.LINK_AC2;
            WS_LINK_ACNO3 = DCRIQRS.LINK_AC3;
            WS_LINK_ACNO4 = DCRIQRS.LINK_AC4;
            WS_LINK_ACNO5 = DCRIQRS.LINK_AC5;
            WS_MAX_TRC_AMT = DCRIQRS.MAX_TRC_AMT;
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCRIQRS.PROD_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_REC_NOTFND, DCCUTCGD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCCUMPRM);
            IBS.init(SCCGWA, DCRIRPRD);
            DCCUMPRM.FUNC = 'I';
            DCCUMPRM.DATA.KEY.PROD_CODE = BPCPQPRD.PARM_CODE;
            S000_CALL_DCZUMPRM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRIRPRD);
            if (DCRIRPRD.DRAW_ORDER == '1') {
                WS_DRAW_MTH_FLG = 'V';
            } else {
                WS_DRAW_MTH_FLG = 'D';
            }
            CEP.TRC(SCCGWA, WS_DRAW_MTH_FLG);
            WS_PARM_CNT = 30;
            B200_GET_TDINFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DR AFTER:");
            CEP.TRC(SCCGWA, WS_AMT);
            if (WS_AMT > 0) {
                T000_READNEXT_DCTIQRS();
                if (pgmRtn) return;
            } else {
                WS_DCTIQRS_FLG = 'N';
            }
        }
        T000_ENDBR_DCTIQRS();
        if (pgmRtn) return;
    }
    public void B110_CHK_DRAW_USAGE() throws IOException,SQLException,Exception {
        WS_DRW_USAGE = " ";
        WS_DRW_USAGE = DCRIQRS.DRW_USAGE;
        CEP.TRC(SCCGWA, DCRIQRS.KEY.OVR_NO);
        CEP.TRC(SCCGWA, WS_DRW_USAGE);
        CEP.TRC(SCCGWA, DCCUTCGD.MMO);
        CEP.TRC(SCCGWA, DCRIQRS.DRW_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (DCRIQRS.DRW_TYPE == '2') {
            WS_DRAW_TYPE_FLG = 'Y';
        } else if (DCRIQRS.DRW_TYPE == '1') {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                WS_DRAW_TYPE_FLG = 'Y';
            } else {
                WS_DRAW_TYPE_FLG = 'N';
            }
        } else if (DCRIQRS.DRW_TYPE == '0') {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                WS_DRAW_TYPE_FLG = 'N';
            } else {
                WS_DRAW_TYPE_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_DRAW_TYPE_FLG);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPRMR.TYP = "PARMC";
        if (BPCPRMR.CD == null) BPCPRMR.CD = "";
        JIBS_tmp_int = BPCPRMR.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
        BPCPRMR.CD = "MMO" + BPCPRMR.CD.substring(3);
        if (BPCPRMR.CD == null) BPCPRMR.CD = "";
        JIBS_tmp_int = BPCPRMR.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
        if (DCCUTCGD.MMO == null) DCCUTCGD.MMO = "";
        JIBS_tmp_int = DCCUTCGD.MMO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) DCCUTCGD.MMO += " ";
        BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DCCUTCGD.MMO + BPCPRMR.CD.substring(6 + 9 - 1);
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MMO_NOT_EXIST);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
        CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP);
        IBS.CPY2CLS(SCCGWA, "1", BPCPARMC.DATA_TXT.RBASE_TYP);
        if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '1') {
            DCCUTCGD.DR_USAGE = '1';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '2') {
            DCCUTCGD.DR_USAGE = '2';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '3') {
            DCCUTCGD.DR_USAGE = '3';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '4') {
            DCCUTCGD.DR_USAGE = '4';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '5') {
            DCCUTCGD.DR_USAGE = '5';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '6') {
            DCCUTCGD.DR_USAGE = '6';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MMO_NO_BK_FUNC, DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        DCCUTCGD.DR_USAGE = '4';
        CEP.TRC(SCCGWA, DCCUTCGD.DR_USAGE);
        WS_MMO_ERR_FLG = 'N';
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if (WS_DRW_USAGE == null) WS_DRW_USAGE = "";
        JIBS_tmp_int = WS_DRW_USAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DRW_USAGE += " ";
        if ((DCCUTCGD.DR_USAGE == '1' 
            && WS_DRW_USAGE.substring(0, 1).equalsIgnoreCase("1")) 
            || (DCCUTCGD.DR_USAGE == '2' 
            && WS_DRW_USAGE.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGD.DR_USAGE == '3' 
            && WS_DRW_USAGE.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGD.DR_USAGE == '4' 
            && WS_DRW_USAGE.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGD.DR_USAGE == '5' 
            && (WS_DRW_USAGE.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            || WS_DRW_USAGE.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1"))) 
            || (DCCUTCGD.DR_USAGE == '6' 
            && WS_DRW_USAGE.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_MMO_ERR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_MMO_ERR_FLG);
    }
    public void B200_GET_TDINFO() throws IOException,SQLException,Exception {
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        WS_CNT = 0;
        WS_TD_AMT_FLG = 'N';
        if (WS_PROD_CODE.equalsIgnoreCase("8821020000") 
            || WS_PROD_CODE.equalsIgnoreCase("8821010000")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = WS_LINK_ACNO1;
            TDRSMST.KEY.ACO_AC = "" + 0X00;
            JIBS_tmp_int = TDRSMST.KEY.ACO_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRSMST.KEY.ACO_AC = "0" + TDRSMST.KEY.ACO_AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (WS_SMST_FLG == 'Y') {
                R000_GET_TD_AVAIL_BAL_OTHER();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_MAX_TRC_AMT);
                CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
                if (WS_TD_AVA_AMT > 0) {
                    WS_BBBB.WS_TD_AC = TDRSMST.AC_NO;
                    WS_BBBB.WS_ACO_AC = TDRSMST.KEY.ACO_AC;
                    WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                    WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_PROD_CD);
                    C500_GET_TD_START_AMT();
                    if (pgmRtn) return;
                    if (WS_MAX_TRC_AMT > WS_TD_AVA_AMT) {
                        WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                    } else {
                        WS_BBBB.WS_TD_AMT = WS_MAX_TRC_AMT;
                        WS_LEFT_AMT = WS_TD_AVA_AMT - WS_MAX_TRC_AMT;
                        CEP.TRC(SCCGWA, WS_LEFT_AMT);
                        CEP.TRC(SCCGWA, WS_TD_START_AMT);
                        if (WS_LEFT_AMT < WS_TD_START_AMT) {
                            WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                        }
                    }
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        T000_READ_DCTTTOD_FIRST();
                        if (pgmRtn) return;
                        if (WS_TTOD_FLG == 'Y') {
                            CEP.TRC(SCCGWA, DCRTTOD.KEY.SEQ_NO);
                            WS_CNT = DCRTTOD.KEY.SEQ_NO + 1;
                        } else {
                            WS_CNT += 1;
                        }
                        CEP.TRC(SCCGWA, WS_CNT);
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (WS_PROD_CODE.equalsIgnoreCase("9510000006")) {
            WS_LNK_ACNO_FLG = 'N';
            while (WS_TD_AMT_FLG != 'Y' 
                && WS_LNK_ACNO_FLG != 'Y') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = WS_LINK_ACNO1;
                DDRCCY.CCY = "156";
                DDRCCY.CCY_TYPE = '1';
                DDRCCY.KEY.AC = "" + 0X00;
                JIBS_tmp_int = DDRCCY.KEY.AC.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) DDRCCY.KEY.AC = "0" + DDRCCY.KEY.AC;
                T000_READ_DDTCCY();
                if (pgmRtn) return;
                R000_GET_DD_AVAIL_BAL();
                if (pgmRtn) return;
                WS_BBBB.WS_DD_AC = DDRCCY.CUS_AC;
                WS_BBBB.WS_ACO_AC = DDRCCY.KEY.AC;
                CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                CEP.TRC(SCCGWA, WS_LINK_ACNO1);
                CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                C200_NOT_CANC_SIGN_DR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                    WS_TD_AMT_FLG = 'Y';
                    WS_AMT = 0;
                } else {
                    WS_TD_AMT_FLG = 'N';
                }
                if (WS_DD_TXN_AMT > 0) {
                    WS_CNT += 1;
                    CEP.TRC(SCCGWA, WS_CNT);
                    B600_WRITE_TTOD_HANDLE();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, DDRCCY);
                if (WS_LINK_ACNO2.trim().length() == 0) {
                    WS_LNK_ACNO_FLG = 'Y';
                } else {
                    CEP.TRC(SCCGWA, WS_LINK_ACNO2);
                    DDRCCY.CUS_AC = WS_LINK_ACNO2;
                    DDRCCY.KEY.AC = "" + 0X00;
                    JIBS_tmp_int = DDRCCY.KEY.AC.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) DDRCCY.KEY.AC = "0" + DDRCCY.KEY.AC;
                    DDRCCY.CCY = "156";
                    DDRCCY.CCY_TYPE = '1';
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    R000_GET_DD_AVAIL_BAL();
                    if (pgmRtn) return;
                    WS_BBBB.WS_DD_AC = DDRCCY.CUS_AC;
                    WS_BBBB.WS_ACO_AC = DDRCCY.KEY.AC;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                    CEP.TRC(SCCGWA, WS_LINK_ACNO2);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, DDRCCY);
                if (WS_LINK_ACNO3.trim().length() == 0) {
                    WS_LNK_ACNO_FLG = 'Y';
                } else {
                    CEP.TRC(SCCGWA, WS_LINK_ACNO3);
                    DDRCCY.CUS_AC = WS_LINK_ACNO3;
                    DDRCCY.CCY = "156";
                    DDRCCY.CCY_TYPE = '1';
                    DDRCCY.KEY.AC = "" + 0X00;
                    JIBS_tmp_int = DDRCCY.KEY.AC.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) DDRCCY.KEY.AC = "0" + DDRCCY.KEY.AC;
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    R000_GET_DD_AVAIL_BAL();
                    if (pgmRtn) return;
                    WS_BBBB.WS_DD_AC = DDRCCY.CUS_AC;
                    WS_BBBB.WS_ACO_AC = DDRCCY.KEY.AC;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                    CEP.TRC(SCCGWA, WS_LINK_ACNO3);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, DDRCCY);
                if (WS_LINK_ACNO4.trim().length() == 0) {
                    WS_LNK_ACNO_FLG = 'Y';
                } else {
                    DDRCCY.CUS_AC = WS_LINK_ACNO4;
                    DDRCCY.CCY = "156";
                    DDRCCY.CCY_TYPE = '1';
                    DDRCCY.KEY.AC = "" + 0X00;
                    JIBS_tmp_int = DDRCCY.KEY.AC.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) DDRCCY.KEY.AC = "0" + DDRCCY.KEY.AC;
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    R000_GET_DD_AVAIL_BAL();
                    if (pgmRtn) return;
                    WS_BBBB.WS_DD_AC = DDRCCY.CUS_AC;
                    WS_BBBB.WS_ACO_AC = DDRCCY.KEY.AC;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                    CEP.TRC(SCCGWA, WS_LINK_ACNO4);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, DDRCCY);
                if (WS_LINK_ACNO5.trim().length() == 0) {
                    WS_LNK_ACNO_FLG = 'Y';
                } else {
                    CEP.TRC(SCCGWA, WS_LINK_ACNO5);
                    DDRCCY.CUS_AC = WS_LINK_ACNO5;
                    DDRCCY.CCY = "156";
                    DDRCCY.CCY_TYPE = '1';
                    DDRCCY.KEY.AC = "" + 0X00;
                    JIBS_tmp_int = DDRCCY.KEY.AC.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) DDRCCY.KEY.AC = "0" + DDRCCY.KEY.AC;
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    R000_GET_DD_AVAIL_BAL();
                    if (pgmRtn) return;
                    WS_BBBB.WS_DD_AC = DDRCCY.CUS_AC;
                    WS_BBBB.WS_ACO_AC = DDRCCY.KEY.AC;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (WS_PROD_CODE.equalsIgnoreCase("9510000004")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = WS_LINK_ACNO1;
            TDRSMST.KEY.ACO_AC = "" + 0X00;
            JIBS_tmp_int = TDRSMST.KEY.ACO_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRSMST.KEY.ACO_AC = "0" + TDRSMST.KEY.ACO_AC;
            T000_STARTBR_TDTSMST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            while (WS_SMST_FLG != 'N' 
                && WS_TD_AMT_FLG != 'Y') {
                WS_BBBB.WS_TD_PROD_CD = " ";
                WS_TD_BUTI_NUM = 0;
                WS_BUTI_MAX_FLG = ' ';
                WS_TD_BUTI_NUM = TDRSMST.PART_NUM;
                WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    T000_READNEXT_TDTSMST();
                    if (pgmRtn) return;
                } else {
                    C500_GET_TD_START_AMT();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, TDRSMST.PART_NUM);
                    CEP.TRC(SCCGWA, WS_TD_BUTI_NUM);
                    CEP.TRC(SCCGWA, WS_PARM_BUTI_NUM);
                    if (WS_TD_BUTI_NUM >= WS_PARM_BUTI_NUM) {
                        WS_BUTI_MAX_FLG = 'Y';
                    }
                    R000_GET_TD_AVAIL_BAL_OTHER();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_MAX_TRC_AMT);
                    CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    if (WS_MAX_TRC_AMT > WS_TD_AVA_AMT) {
                        WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                    } else {
                        WS_BBBB.WS_TD_AMT = WS_MAX_TRC_AMT;
                    }
                    if (WS_BUTI_MAX_FLG == 'Y') {
                        WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                    }
                    CEP.TRC(SCCGWA, TDRSMST.AC_NO);
                    CEP.TRC(SCCGWA, TDRSMST.BAL);
                    WS_BBBB.WS_TD_AC = TDRSMST.AC_NO;
                    WS_BBBB.WS_ACO_AC = TDRSMST.KEY.ACO_AC;
                    WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                    C200_NOT_CANC_SIGN_DR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                        WS_TD_AMT_FLG = 'Y';
                        WS_AMT = 0;
                    } else {
                        WS_TD_AMT_FLG = 'N';
                    }
                    if (WS_DD_TXN_AMT > 0) {
                        T000_READ_DCTTTOD_FIRST();
                        if (pgmRtn) return;
                        if (WS_TTOD_FLG == 'Y') {
                            CEP.TRC(SCCGWA, DCRTTOD.KEY.SEQ_NO);
                            WS_CNT = DCRTTOD.KEY.SEQ_NO + 1;
                        } else {
                            WS_CNT += 1;
                        }
                        CEP.TRC(SCCGWA, WS_CNT);
                        B600_WRITE_TTOD_HANDLE();
                        if (pgmRtn) return;
                    }
                    T000_READNEXT_TDTSMST();
                    if (pgmRtn) return;
                }
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
        }
        if (DCCUTCGD.ZERO_FLG == 'Y') {
            WS_TD_AMT_FLG = 'Y';
        }
    }
    public void B300_UNSIGN_TDDR_PROC() throws IOException,SQLException,Exception {
        WS_BBBB.WS_TD_AMT = 0;
        WS_BBBB.WS_DD_AMT = 0;
        WS_CNT = 0;
        WS_BBBB.WS_TD_PROD_CD = " ";
        WS_BBBB.WS_DD_AC = " ";
        WS_BBBB.WS_TD_AC = " ";
        WS_BBBB.WS_ACO_AC = " ";
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        WS_TD_AMT_FLG = 'N';
        WS_AMT = DCCUTCGD.AMT;
        if (DCCUTCGD.FLG == 'M') {
            WS_DRAW_MTH_FLG = 'V';
        }
        if (DCCUTCGD.CANC_FLG == 'Y') {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = DCCUTCGD.AC;
            TDRSMST.KEY.ACO_AC = "" + 0X00;
            JIBS_tmp_int = TDRSMST.KEY.ACO_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRSMST.KEY.ACO_AC = "0" + TDRSMST.KEY.ACO_AC;
            T000_STARTBR_TDTSMST_MARGIN();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            while (WS_SMST_FLG != 'N') {
                WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                WS_BBBB.WS_TD_AC = TDRSMST.AC_NO;
                WS_BBBB.WS_ACO_AC = TDRSMST.KEY.ACO_AC;
                if (DCCUTCGD.FLG == 'M') {
                    WS_BBBB.WS_TD_PROD_CD = DCCUTCGD.PROD_CD;
                } else {
                    WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                }
                R000_GET_TD_AVAIL_BAL_GD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
                CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                if (WS_TD_AVA_AMT > 0 
                    || (WS_TD_AVA_AMT == 0 
                    && WS_BBBB.WS_TD_AMT == 0)) {
                    C100_NOT_SIGN_DR_HANDLE();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                    WS_TD_AMT_FLG = 'Y';
                } else {
                    WS_TD_AMT_FLG = 'N';
                }
                if (WS_DD_TXN_AMT > 0 
                    || (WS_TD_AVA_AMT == 0 
                    && WS_BBBB.WS_TD_AMT == 0)) {
                    T000_READ_DCTTTOD_FIRST();
                    if (pgmRtn) return;
                    if (WS_TTOD_FLG == 'Y') {
                        CEP.TRC(SCCGWA, DCRTTOD.KEY.SEQ_NO);
                        WS_CNT = DCRTTOD.KEY.SEQ_NO + 1;
                    } else {
                        WS_CNT += 1;
                    }
                    CEP.TRC(SCCGWA, WS_CNT);
                    B600_WRITE_TTOD_HANDLE();
                    if (pgmRtn) return;
                }
                T000_READNEXT_TDTSMST();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = DCCUTCGD.AC;
            TDRSMST.KEY.ACO_AC = "" + 0X00;
            JIBS_tmp_int = TDRSMST.KEY.ACO_AC.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRSMST.KEY.ACO_AC = "0" + TDRSMST.KEY.ACO_AC;
            T000_STARTBR_TDTSMST_MARGIN();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            while (WS_SMST_FLG != 'N' 
                && WS_TD_AMT_FLG != 'Y') {
                WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                WS_BBBB.WS_TD_AC = TDRSMST.AC_NO;
                WS_BBBB.WS_ACO_AC = TDRSMST.KEY.ACO_AC;
                if (DCCUTCGD.FLG == 'M') {
                    WS_BBBB.WS_TD_PROD_CD = DCCUTCGD.PROD_CD;
                } else {
                    WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                }
                R000_GET_TD_AVAIL_BAL_GD();
                if (pgmRtn) return;
                if (WS_TD_AVA_AMT > 0) {
                    C100_NOT_SIGN_DR_HANDLE();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                if (WS_TOTAL_TXN_AMT >= DCCUTCGD.AMT) {
                    WS_TD_AMT_FLG = 'Y';
                } else {
                    WS_TD_AMT_FLG = 'N';
                }
                if (WS_DD_TXN_AMT > 0) {
                    T000_READ_DCTTTOD_FIRST();
                    if (pgmRtn) return;
                    if (WS_TTOD_FLG == 'Y') {
                        CEP.TRC(SCCGWA, DCRTTOD.KEY.SEQ_NO);
                        WS_CNT = DCRTTOD.KEY.SEQ_NO + 1;
                    } else {
                        WS_CNT += 1;
                    }
                    CEP.TRC(SCCGWA, WS_CNT);
                    B600_WRITE_TTOD_HANDLE();
                    if (pgmRtn) return;
                }
                T000_READNEXT_TDTSMST();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B400_DDCR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
        CEP.TRC(SCCGWA, WS_TD_PRINCIPAL_AMT);
        if ((WS_TOTAL_TXN_AMT > 0 
            && DCCUTCGD.FLG != 'M') 
            || (WS_TD_PRINCIPAL_AMT > 0 
            && DCCUTCGD.FLG == 'M')) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCUTCGD.AC;
            if (DCCUTCGD.CCY.trim().length() == 0) {
                DDCUCRAC.CCY = "156";
            } else {
                DDCUCRAC.CCY = DCCUTCGD.CCY;
            }
            if (DCCUTCGD.CCY_TYP == ' ') {
                DDCUCRAC.CCY_TYPE = '1';
            } else {
                DDCUCRAC.CCY_TYPE = DCCUTCGD.CCY_TYP;
            }
            if (DCCUTCGD.FLG == 'M') {
                DDCUCRAC.TX_AMT = WS_TD_PRINCIPAL_AMT;
            } else {
                DDCUCRAC.TX_AMT = WS_TOTAL_TXN_AMT;
            }
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BANK_CR_FLG = 'Y';
            DDCUCRAC.SUPPLY_FLG = 'N';
            if (DCCUTCGD.FUNC == 'C') {
                DDCUCRAC.AUTO_TDTODD_FLG = 'Y';
                if (TDCACDRU.PAYING_INT > 0) {
                    DDCUCRAC.TD_INT_AMT = TDCACDRU.PAYING_INT;
                }
            }
            CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
            if (DCCUTCGD.LAW_FLG == 'Y') {
                DDCUCRAC.LAW_DUCT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, DCCUTCGD.SIGN_FLG);
            if (DCCUTCGD.SIGN_FLG == 'Y') {
                DDCUCRAC.TX_MMO = "A035";
            } else {
                DDCUCRAC.TX_MMO = DCCUTCGD.MMO;
            }
            if (DCCUTCGD.FLG == 'M') {
                DDCUCRAC.HIS_SHOW_FLG = 'N';
            }
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B410_TDDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUTCGD.AC);
        IBS.init(SCCGWA, TDCACDRU);
        if (DCRTTOD.TRC_STS == 'B') {
            TDCACDRU.OPT = '1';
        } else {
            TDCACDRU.OPT = '0';
        }
        if (DCRTTOD.TRC_STS == 'B' 
            && DCCUTCGD.FLG == 'M') {
            TDCACDRU.OPT = '8';
        }
        if (DCRTTOD.TRC_STS == 'X' 
            && DCCUTCGD.FLG == 'M') {
            TDCACDRU.OPT = '9';
        }
        TDCACDRU.CHNL_FLG = 'Y';
        TDCACDRU.PRDMO_CD = "MMDP";
        TDCACDRU.TXN_AMT = DCRTTOD.TRC_AMT;
        TDCACDRU.MAC_CNO = DCCUTCGD.AC;
        TDCACDRU.ACO_AC = DCRTTOD.KEY.TRC_AC;
        if (DCCUTCGD.FLG == 'C') {
            TDCACDRU.BV_TYP = '4';
        }
        CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
        if (DCCUTCGD.LAW_FLG == 'Y') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
        }
        if (DCCUTCGD.FLG == 'M') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
        if (DCCUTCGD.CCY.trim().length() == 0) {
            TDCACDRU.CCY = "156";
        } else {
            TDCACDRU.CCY = DCCUTCGD.CCY;
        }
        if (DCCUTCGD.CCY_TYP == ' ') {
            TDCACDRU.CCY_TYP = '1';
        } else {
            TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
        }
        TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUTCGD.FLG);
        if (DCCUTCGD.FLG == 'M') {
            WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
            WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
        } else {
            WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
            WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
        }
    }
    public void B500_CANCEL_YES_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
        CEP.TRC(SCCGWA, DCCUTCGD.AC);
        CEP.TRC(SCCGWA, DCCUTCGD.CANC_FLG);
        CEP.TRC(SCCGWA, DCCUTCGD.SIGN_FLG);
        IBS.init(SCCGWA, DCRTTOD);
        WS_AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        WS_AC_NO = DCCUTCGD.AC;
        T000_STARTBR_DCTTTOD();
        if (pgmRtn) return;
        T000_READNEXT_DCTTTOD();
        if (pgmRtn) return;
        while (WS_TTOD_FLG != 'N') {
            CEP.TRC(SCCGWA, DCRTTOD.TD_PROD_CODE);
            if (DCRTTOD.TD_PROD_CODE.equalsIgnoreCase("6311370000") 
                || DCRTTOD.TD_PROD_CODE.equalsIgnoreCase("1202030201")) {
                B700_SPECIAL_TDDR_PROC();
                if (pgmRtn) return;
            } else {
                B410_TDDR_PROC();
                if (pgmRtn) return;
            }
            B610_UPDATE_DCTTTOD();
            if (pgmRtn) return;
            T000_READNEXT_DCTTTOD();
            if (pgmRtn) return;
        }
        B400_DDCR_PROC();
        if (pgmRtn) return;
    }
    public void B600_WRITE_TTOD_HANDLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTTOD);
        DCRTTOD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        DCRTTOD.KEY.SEQ_NO = WS_CNT;
        DCRTTOD.KEY.TRC_AC = WS_BBBB.WS_ACO_AC;
        DCRTTOD.OVR_NO = WS_OVR_NO1;
        DCRTTOD.TRC_AMT = WS_DD_TXN_AMT;
        DCRTTOD.TRC_STS = WS_BUKUAN_FLG;
        CEP.TRC(SCCGWA, WS_BUKUAN_FLG);
        CEP.TRC(SCCGWA, WS_PROD_CODE);
        if (WS_PROD_CODE.equalsIgnoreCase("8821010000") 
            && WS_BUKUAN_FLG == 'X') {
            CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
            DCRTTOD.TRC_AMT = WS_BBBB.WS_TD_AMT;
        }
        DCRTTOD.TD_PROD_CODE = WS_BBBB.WS_TD_PROD_CD;
        DCRTTOD.DD_AC = DCCUTCGD.AC;
        DCRTTOD.TX_MMO = DCCUTCGD.MMO;
        DCRTTOD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRTTOD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTTTOD();
        if (pgmRtn) return;
    }
    public void B610_UPDATE_DCTTTOD() throws IOException,SQLException,Exception {
        DCRTTOD.CZ_STS = 'S';
        DCRTTOD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRTTOD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTTTOD();
        if (pgmRtn) return;
    }
    public void B700_SPECIAL_TDDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = DCRTTOD.KEY.TRC_AC;
        T000_READ_TDTSMST_ACOAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        IBS.init(SCCGWA, TDCUGRP);
        TDCUGRP.AC_NO = TDRSMST.AC_NO;
        TDCUGRP.CHK_PSW = 'N';
        TDCUGRP.PROD_CD = DCRTTOD.TD_PROD_CODE;
        TDCUGRP.AUTO_FLG = 'Y';
        TDCUGRP.TXN_AMT = DCRTTOD.TRC_AMT;
        TDCUGRP.OP_AC = DCCUTCGD.AC;
        TDCUGRP.DRAW_TYP = '1';
        TDCUGRP.TXN_MMO = DCCUTCGD.MMO;
        S000_CALL_TDZUGRP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCUGRP.STSW);
        CEP.TRC(SCCGWA, DCRTTOD.TD_PROD_CODE);
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        if (TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            && DCRTTOD.TD_PROD_CODE.equalsIgnoreCase("1202030201")) {
            WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && TDCUGRP.DRAW_TYP == '1') {
                WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT;
            } else {
                WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
            }
        }
        CEP.TRC(SCCGWA, WS_XXTCZZH_AMT);
        WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + WS_XXTCZZH_AMT;
    }
    public void C100_NOT_SIGN_DR_HANDLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_BBBB.WS_TD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, TDCQPMP);
            IBS.init(SCCGWA, TDCPROD);
            IBS.init(SCCGWA, TDCPRDP);
            TDCQPMP.FUNC = 'I';
            TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
            TDCQPMP.DAT_PTR = TDCPROD;
            S000_CALL_TDZQPMP();
            if (pgmRtn) return;
            TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
            CEP.TRC(SCCGWA, TDCPRDP);
            WS_B = 0;
            while (WS_B < 20) {
                WS_B += 1;
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MIN_CCYC.equalsIgnoreCase("156")) {
                    WS_TD_START_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MLET_AMT;
                    WS_TD_MIX_DRW_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MDRW_AMT;
                }
            }
        }
        if (DCCUTCGD.CANC_FLG == 'Y') {
            if (DCCUTCGD.FLG == 'M') {
                WS_DD_TXN_AMT = 0;
                CEP.TRC(SCCGWA, "XIAOHU TIQU:");
                IBS.init(SCCGWA, TDCACDRU);
                CEP.TRC(SCCGWA, DCCUTCGD.AC);
                CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                TDCACDRU.OPT = '9';
                WS_BUKUAN_FLG = 'X';
                TDCACDRU.CHNL_FLG = 'Y';
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.TXN_AMT = WS_BBBB.WS_TD_AMT;
                TDCACDRU.MAC_CNO = DCCUTCGD.AC;
                TDCACDRU.ACO_AC = WS_BBBB.WS_ACO_AC;
                if (DCCUTCGD.FLG == 'C') {
                    TDCACDRU.BV_TYP = '4';
                }
                CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
                if (DCCUTCGD.LAW_FLG == 'Y') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
                }
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
                TDCACDRU.CCY = DCCUTCGD.CCY;
                TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
                TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
                WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                WS_DD_TXN_AMT = TDCACDRU.TXN_AMT;
            }
        } else {
            CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
            CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
            CEP.TRC(SCCGWA, WS_AMT);
            if (WS_TD_AVA_AMT <= WS_AMT 
                && WS_BBBB.WS_TD_AMT == WS_TD_AVA_AMT) {
                WS_DD_TXN_AMT = 0;
                CEP.TRC(SCCGWA, "XIAOHU TIQU:");
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.OPT = '9';
                WS_BUKUAN_FLG = 'X';
                TDCACDRU.CHNL_FLG = 'Y';
                CEP.TRC(SCCGWA, DCCUTCGD.AC);
                CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.TXN_AMT = WS_TD_AVA_AMT;
                TDCACDRU.MAC_CNO = DCCUTCGD.AC;
                TDCACDRU.ACO_AC = WS_BBBB.WS_ACO_AC;
                if (DCCUTCGD.FLG == 'C') {
                    TDCACDRU.BV_TYP = '4';
                }
                CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
                if (DCCUTCGD.LAW_FLG == 'Y') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
                }
                if (DCCUTCGD.FLG == 'M') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                }
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
                TDCACDRU.CCY = DCCUTCGD.CCY;
                TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
                TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                if (DCCUTCGD.FLG == 'M') {
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT;
                    WS_AMT = WS_AMT - WS_TD_AVA_AMT;
                } else {
                    if (WS_AMT <= TDCACDRU.PAYING_INT) {
                        WS_AMT = 0;
                    } else {
                        WS_AMT = WS_AMT - TDCACDRU.PAYING_INT;
                    }
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                }
            } else {
                WS_DD_TXN_AMT = 0;
                WS_LEFT_AMT = WS_TD_AVA_AMT - WS_AMT;
                CEP.TRC(SCCGWA, "BUFEN TIQU:");
                CEP.TRC(SCCGWA, WS_LEFT_AMT);
                CEP.TRC(SCCGWA, WS_TD_START_AMT);
                IBS.init(SCCGWA, TDCACDRU);
                if (WS_LEFT_AMT >= WS_TD_START_AMT) {
                    TDCACDRU.OPT = '8';
                    WS_BUKUAN_FLG = 'B';
                    TDCACDRU.TXN_AMT = WS_AMT;
                } else {
                    TDCACDRU.OPT = '9';
                    WS_BUKUAN_FLG = 'X';
                    TDCACDRU.TXN_AMT = WS_TD_AVA_AMT;
                }
                CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
                CEP.TRC(SCCGWA, WS_AMT);
                CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                if (WS_TD_AVA_AMT <= WS_AMT 
                    && WS_BBBB.WS_TD_AMT != WS_TD_AVA_AMT) {
                    TDCACDRU.OPT = '8';
                    WS_BUKUAN_FLG = 'B';
                    TDCACDRU.TXN_AMT = WS_TD_AVA_AMT;
                }
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.CHNL_FLG = 'Y';
                CEP.TRC(SCCGWA, DCCUTCGD.AC);
                CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                CEP.TRC(SCCGWA, WS_AMT);
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                if (DCCUTCGD.FLG == 'C') {
                    TDCACDRU.BV_TYP = '4';
                }
                CEP.TRC(SCCGWA, DCCUTCGD.LAW_FLG);
                if (DCCUTCGD.LAW_FLG == 'Y') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
                }
                if (DCCUTCGD.FLG == 'M') {
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                }
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
                TDCACDRU.CCY = DCCUTCGD.CCY;
                TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
                TDCACDRU.MAC_CNO = DCCUTCGD.AC;
                TDCACDRU.ACO_AC = WS_BBBB.WS_ACO_AC;
                TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                if (DCCUTCGD.FLG == 'M') {
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT;
                    WS_AMT = WS_AMT - WS_DD_TXN_AMT;
                } else {
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + TDCACDRU.TXN_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_AMT = WS_AMT - WS_DD_TXN_AMT;
                }
            }
        }
    }
    public void C200_NOT_CANC_SIGN_DR() throws IOException,SQLException,Exception {
        if (DCCUTCGD.SIGN_FLG == 'Y') {
            if (WS_PROD_CODE.equalsIgnoreCase("9510000004")) {
                CEP.TRC(SCCGWA, "KANEI:");
                WS_DD_TXN_AMT = 0;
                if (WS_BBBB.WS_TD_AMT <= WS_AMT 
                    || WS_BUTI_MAX_FLG == 'Y') {
                    IBS.init(SCCGWA, TDCACDRU);
                    TDCACDRU.OPT = '0';
                    WS_BUKUAN_FLG = 'X';
                    TDCACDRU.CHNL_FLG = 'Y';
                    CEP.TRC(SCCGWA, "XHTQ");
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AC);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    CEP.TRC(SCCGWA, WS_AMT);
                    if (DCCUTCGD.FLG == 'C') {
                        TDCACDRU.BV_TYP = '4';
                    }
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
                    TDCACDRU.PRDMO_CD = "MMDP";
                    TDCACDRU.TXN_AMT = WS_BBBB.WS_TD_AMT;
                    TDCACDRU.MAC_CNO = WS_BBBB.WS_TD_AC;
                    TDCACDRU.ACO_AC = WS_BBBB.WS_ACO_AC;
                    TDCACDRU.CCY = DCCUTCGD.CCY;
                    TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
                    TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
                    S000_CALL_TDZACDRU();
                    if (pgmRtn) return;
                    WS_AMT = WS_AMT - WS_BBBB.WS_TD_AMT;
                    if (WS_AMT <= TDCACDRU.PAYING_INT) {
                        WS_AMT = 0;
                    } else {
                        WS_AMT = WS_AMT - TDCACDRU.PAYING_INT;
                    }
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_TD_PRINCIPAL_AMT = TDCACDRU.TXN_AMT + WS_TD_PRINCIPAL_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    CEP.TRC(SCCGWA, WS_AMT);
                } else {
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    WS_LEFT_AMT = WS_BBBB.WS_TD_AMT - WS_AMT;
                    CEP.TRC(SCCGWA, WS_LEFT_AMT);
                    CEP.TRC(SCCGWA, WS_TD_START_AMT);
                    IBS.init(SCCGWA, TDCACDRU);
                    if (WS_LEFT_AMT >= WS_TD_START_AMT) {
                        TDCACDRU.OPT = '1';
                        WS_BUKUAN_FLG = 'B';
                        TDCACDRU.TXN_AMT = WS_AMT;
                    } else {
                        TDCACDRU.OPT = '0';
                        WS_BUKUAN_FLG = 'X';
                        TDCACDRU.TXN_AMT = WS_BBBB.WS_TD_AMT;
                    }
                    if (DCCUTCGD.FLG == 'C') {
                        TDCACDRU.BV_TYP = '4';
                    }
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
                    if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                    JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                    TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 8 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(8 + 1 - 1);
                    TDCACDRU.PRDMO_CD = "MMDP";
                    TDCACDRU.CHNL_FLG = 'Y';
                    CEP.TRC(SCCGWA, "BFTQ");
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AC);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_ACO_AC);
                    CEP.TRC(SCCGWA, WS_AMT);
                    TDCACDRU.CCY = DCCUTCGD.CCY;
                    TDCACDRU.CCY_TYP = DCCUTCGD.CCY_TYP;
                    TDCACDRU.MAC_CNO = WS_BBBB.WS_TD_AC;
                    TDCACDRU.ACO_AC = WS_BBBB.WS_ACO_AC;
                    TDCACDRU.TXN_MMO = DCCUTCGD.MMO;
                    S000_CALL_TDZACDRU();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_TD_PRINCIPAL_AMT = TDCACDRU.TXN_AMT + WS_TD_PRINCIPAL_AMT;
                    WS_TD_PAY_INT = WS_TD_PAY_INT + TDCACDRU.PAYING_INT;
                    WS_DD_TXN_AMT = TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                    WS_AMT = 0;
                }
            }
            if (WS_PROD_CODE.equalsIgnoreCase("9510000006")) {
                CEP.TRC(SCCGWA, "HZH :");
                WS_DD_TXN_AMT = 0;
                IBS.init(SCCGWA, DDCUDRAC);
                if (WS_BBBB.WS_DD_AMT <= WS_AMT) {
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AC);
                    CEP.TRC(SCCGWA, DCCUTCGD.AC);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AMT);
                    DDCUDRAC.AC = WS_BBBB.WS_DD_AC;
                    DDCUDRAC.OTHER_AC = DCCUTCGD.AC;
                    DDCUDRAC.CCY = "156";
                    DDCUDRAC.CCY_TYPE = '1';
                    DDCUDRAC.TX_AMT = WS_BBBB.WS_DD_AMT;
                    DDCUDRAC.TX_TYPE = 'T';
                    DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDCUDRAC.BANK_DR_FLG = 'Y';
                    DDCUDRAC.CHK_PSW_FLG = 'N';
                    DDCUDRAC.SUPPLY_FLG = 'N';
                    DDCUDRAC.TX_MMO = DCCUTCGD.MMO;
                    DDCUDRAC.AUTO_DDTOTD_FLG = 'Y';
                    S000_CALL_DDZUDRAC();
                    if (pgmRtn) return;
                    WS_AMT = WS_AMT - WS_BBBB.WS_DD_AMT;
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + DDCUDRAC.TX_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + DDCUDRAC.TX_AMT;
                    WS_DD_TXN_AMT = DDCUDRAC.TX_AMT;
                } else {
                    CEP.TRC(SCCGWA, WS_BBBB.WS_DD_AC);
                    CEP.TRC(SCCGWA, DCCUTCGD.AC);
                    DDCUDRAC.AC = WS_BBBB.WS_DD_AC;
                    DDCUDRAC.OTHER_AC = DCCUTCGD.AC;
                    DDCUDRAC.CCY = "156";
                    DDCUDRAC.CCY_TYPE = '1';
                    DDCUDRAC.TX_AMT = WS_AMT;
                    DDCUDRAC.TX_TYPE = 'T';
                    DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDCUDRAC.BANK_DR_FLG = 'Y';
                    DDCUDRAC.CHK_PSW_FLG = 'N';
                    DDCUDRAC.SUPPLY_FLG = 'N';
                    DDCUDRAC.TX_MMO = DCCUTCGD.MMO;
                    DDCUDRAC.AUTO_DDTOTD_FLG = 'Y';
                    S000_CALL_DDZUDRAC();
                    if (pgmRtn) return;
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + DDCUDRAC.TX_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + DDCUDRAC.TX_AMT;
                    WS_DD_TXN_AMT = DDCUDRAC.TX_AMT;
                    WS_AMT = 0;
                }
            }
            if (WS_PROD_CODE.equalsIgnoreCase("8821020000") 
                || WS_PROD_CODE.equalsIgnoreCase("8821010000")) {
                CEP.TRC(SCCGWA, "CZZH OR XXT:");
                WS_DD_TXN_AMT = 0;
                CEP.TRC(SCCGWA, WS_MAX_TRC_AMT);
                CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                CEP.TRC(SCCGWA, WS_AMT);
                CEP.TRC(SCCGWA, WS_TD_START_AMT);
                CEP.TRC(SCCGWA, WS_TD_MIX_DRW_AMT);
                if (WS_BBBB.WS_TD_AMT <= WS_AMT) {
                    CEP.TRC(SCCGWA, "QUANBU TIQU:");
                    IBS.init(SCCGWA, TDCUGRP);
                    TDCUGRP.AC_NO = WS_BBBB.WS_TD_AC;
                    TDCUGRP.CHK_PSW = 'N';
                    if (WS_PROD_CODE.equalsIgnoreCase("8821020000")) {
                        TDCUGRP.PROD_CD = "6311370000";
                    } else {
                        TDCUGRP.PROD_CD = "1202030201";
                    }
                    CEP.TRC(SCCGWA, WS_MAX_TRC_AMT);
                    CEP.TRC(SCCGWA, WS_TD_AVA_AMT);
                    if (WS_MAX_TRC_AMT > WS_TD_AVA_AMT) {
                        WS_LEFT_AMT = WS_TD_AVA_AMT - WS_MAX_TRC_AMT;
                        CEP.TRC(SCCGWA, WS_LEFT_AMT);
                        CEP.TRC(SCCGWA, WS_TD_START_AMT);
                        if (WS_LEFT_AMT < WS_TD_START_AMT) {
                            WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                        }
                    }
                    TDCUGRP.AUTO_FLG = 'Y';
                    TDCUGRP.TXN_AMT = WS_BBBB.WS_TD_AMT;
                    WS_XXTCZ_PRINCIPAL_AMT = WS_BBBB.WS_TD_AMT;
                    TDCUGRP.OP_AC = DCCUTCGD.AC;
                    TDCUGRP.DRAW_TYP = '1';
                    TDCUGRP.TXN_MMO = DCCUTCGD.MMO;
                    S000_CALL_TDZUGRP();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, TDCUGRP.STSW);
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
                    if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
                    JIBS_tmp_int = TDCUGRP.STSW.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
                    if (TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                        && WS_PROD_CODE.equalsIgnoreCase("8821010000")) {
                        WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
                    } else {
                        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                            && TDCUGRP.DRAW_TYP == '1') {
                            WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT;
                        } else {
                            WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
                        }
                    }
                    CEP.TRC(SCCGWA, WS_XXTCZZH_AMT);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    if (WS_XXTCZZH_AMT < WS_BBBB.WS_TD_AMT) {
                        WS_BUKUAN_FLG = 'B';
                    } else {
                        WS_BUKUAN_FLG = 'X';
                    }
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + WS_XXTCZZH_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + WS_XXTCZ_PRINCIPAL_AMT;
                    WS_DD_TXN_AMT = WS_XXTCZZH_AMT;
                    WS_AMT = WS_AMT - WS_XXTCZZH_AMT;
                    CEP.TRC(SCCGWA, WS_AMT);
                } else {
                    CEP.TRC(SCCGWA, "BUFEN TIQU:");
                    if (WS_TD_MIX_DRW_AMT > WS_AMT) {
                        WS_LEFT_AMT = WS_BBBB.WS_TD_AMT - WS_TD_MIX_DRW_AMT;
                    } else {
                        WS_LEFT_AMT = WS_BBBB.WS_TD_AMT - WS_AMT;
                    }
                    CEP.TRC(SCCGWA, WS_LEFT_AMT);
                    CEP.TRC(SCCGWA, WS_TD_START_AMT);
                    IBS.init(SCCGWA, TDCUGRP);
                    if (WS_LEFT_AMT >= WS_TD_START_AMT) {
                        if (WS_TD_MIX_DRW_AMT > WS_AMT) {
                            TDCUGRP.TXN_AMT = WS_TD_MIX_DRW_AMT;
                            WS_XXTCZ_PRINCIPAL_AMT = WS_TD_MIX_DRW_AMT;
                        } else {
                            TDCUGRP.TXN_AMT = WS_AMT;
                            WS_XXTCZ_PRINCIPAL_AMT = WS_AMT;
                        }
                    } else {
                        TDCUGRP.TXN_AMT = WS_BBBB.WS_TD_AMT;
                        WS_XXTCZ_PRINCIPAL_AMT = WS_BBBB.WS_TD_AMT;
                    }
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
                    TDCUGRP.AC_NO = WS_BBBB.WS_TD_AC;
                    TDCUGRP.CHK_PSW = 'N';
                    if (WS_PROD_CODE.equalsIgnoreCase("8821020000")) {
                        TDCUGRP.PROD_CD = "6311370000";
                    } else {
                        TDCUGRP.PROD_CD = "1202030201";
                    }
                    TDCUGRP.AUTO_FLG = 'Y';
                    TDCUGRP.OP_AC = DCCUTCGD.AC;
                    TDCUGRP.DRAW_TYP = '1';
                    TDCUGRP.TXN_MMO = DCCUTCGD.MMO;
                    S000_CALL_TDZUGRP();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, TDCUGRP.STSW);
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
                    CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT);
                    if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
                    JIBS_tmp_int = TDCUGRP.STSW.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
                    if (TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                        && WS_PROD_CODE.equalsIgnoreCase("8821010000")) {
                        WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
                    } else {
                        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                            && TDCUGRP.DRAW_TYP == '1') {
                            WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT;
                        } else {
                            WS_XXTCZZH_AMT = TDCUGRP.TXN_AMT_O;
                        }
                    }
                    CEP.TRC(SCCGWA, WS_XXTCZZH_AMT);
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    if (WS_XXTCZZH_AMT < WS_BBBB.WS_TD_AMT) {
                        WS_BUKUAN_FLG = 'B';
                    } else {
                        WS_BUKUAN_FLG = 'X';
                    }
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + WS_XXTCZZH_AMT;
                    WS_TD_PRINCIPAL_AMT = WS_TD_PRINCIPAL_AMT + WS_XXTCZ_PRINCIPAL_AMT;
                    WS_DD_TXN_AMT = WS_XXTCZZH_AMT;
                    WS_AMT = 0;
                }
            }
        }
    }
    public void C500_GET_TD_START_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BBBB.WS_TD_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_BBBB.WS_TD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, TDCQPMP);
            IBS.init(SCCGWA, TDCPROD);
            IBS.init(SCCGWA, TDCPRDP);
            TDCQPMP.FUNC = 'I';
            TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
            TDCQPMP.DAT_PTR = TDCPROD;
            S000_CALL_TDZQPMP();
            if (pgmRtn) return;
            TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
            CEP.TRC(SCCGWA, TDCPRDP);
            WS_B = 0;
            WS_PARM_BUTI_NUM = TDCPRDP.TXN_PRM.PART_NUM;
            while (WS_B < 20) {
                WS_B += 1;
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MIN_CCYC.equalsIgnoreCase("156")) {
                    WS_TD_MIX_DRW_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MDRW_AMT;
                    WS_TD_START_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MLET_AMT;
                }
            }
        }
    }
    public void R000_GET_DD_AVAIL_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        WS_BBBB.WS_DD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
    }
    public void R000_GET_TD_AVAIL_BAL_GD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        WS_TD_AVA_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
    }
    public void R000_GET_TD_AVAIL_BAL_OTHER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_TD_AVA_AMT = 0;
        } else {
            WS_TD_AVA_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZIMCCY, DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-GROUP", TDCUGRP);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        if (WS_DRAW_MTH_FLG == 'V') {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND ACO_STS = '0'";
            TDTSMST_BR.rp.order = "VAL_DATE DESC,EXP_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        } else {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND ACO_STS = '0'";
            TDTSMST_BR.rp.order = "EXP_DATE";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTSMST_MARGIN() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
            + "AND AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.order = "VAL_DATE";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_READ_TDTSMST_ACOAC() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTTTOD_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_JRN_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        DCTTTOD_RD.where = "'DATE' = :WS_AC_DATE "
            + "AND JRN_NO = :WS_JRN_NO "
            + "AND DD_AC = :WS_AC_NO";
        DCTTTOD_RD.fst = true;
        DCTTTOD_RD.order = "SEQ_NO DESC";
        IBS.READ(SCCGWA, DCRTTOD, this, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TTOD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTTTOD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_JRN_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        if (DCCUTCGD.SIGN_FLG == 'Y') {
            DCTTTOD_BR.rp = new DBParm();
            DCTTTOD_BR.rp.TableName = "DCTTTOD";
            DCTTTOD_BR.rp.where = "'DATE' = :WS_AC_DATE "
                + "AND JRN_NO = :WS_JRN_NO "
                + "AND DD_AC = :WS_AC_NO";
            DCTTTOD_BR.rp.upd = true;
            DCTTTOD_BR.rp.order = "SEQ_NO DESC";
            IBS.STARTBR(SCCGWA, DCRTTOD, this, DCTTTOD_BR);
        } else {
            DCTTTOD_BR.rp = new DBParm();
            DCTTTOD_BR.rp.TableName = "DCTTTOD";
            DCTTTOD_BR.rp.where = "'DATE' = :WS_AC_DATE "
                + "AND JRN_NO = :WS_JRN_NO "
                + "AND DD_AC = :WS_AC_NO";
            DCTTTOD_BR.rp.upd = true;
            DCTTTOD_BR.rp.order = "SEQ_NO";
            IBS.STARTBR(SCCGWA, DCRTTOD, this, DCTTTOD_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTTTOD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRTTOD, this, DCTTTOD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TTOD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TTOD_FLG = 'N';
            CEP.TRC(SCCGWA, "NOT FIND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTTTOD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTTTOD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B610_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = DCCUTCGD.AC;
        BPCPFHIS.DATA.TX_TOOL = DCCUTCGD.AC;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_CCY = "156";
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_AMT = WS_PAYING_INT;
        BPCPFHIS.DATA.VAL_BAL = WS_DD_BAL;
        BPCPFHIS.DATA.TX_MMO = "C01";
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.VAL_BAL);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDCUCCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-CCAL-PRCO", DDCUCCAL);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU, true);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TCCY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TCCY_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIQRS() throws IOException,SQLException,Exception {
        DCTIQRS_BR.rp = new DBParm();
        DCTIQRS_BR.rp.TableName = "DCTIQRS";
        DCTIQRS_BR.rp.where = "AGR_NO = :WS_AC_NO "
            + "AND PROC_STS = '1'";
        DCTIQRS_BR.rp.order = "PROD_LVL DESC";
        IBS.STARTBR(SCCGWA, DCRIQRS, this, DCTIQRS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIQRS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DCTIQR_NOT_FND, DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIQRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTIQRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIQRS, this, DCTIQRS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIQRS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTIQRS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIQRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTIQRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIQRS_BR);
    }
    public void T000_WRITE_DCTTTOD() throws IOException,SQLException,Exception {
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        IBS.WRITE(SCCGWA, DCRTTOD, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTTTOD() throws IOException,SQLException,Exception {
        DCTTTOD_RD = new DBParm();
        DCTTTOD_RD.TableName = "DCTTTOD";
        IBS.REWRITE(SCCGWA, DCRTTOD, DCTTTOD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTTOD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUMPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-IRDD-PARM", DCCUMPRM);
        CEP.TRC(SCCGWA, DCCUMPRM.RC);
        if (DCCUMPRM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUTCGD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, "DCCUTCGD=");
            CEP.TRC(SCCGWA, DCCUTCGD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
