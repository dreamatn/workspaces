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

import java.io.IOException;
import java.sql.SQLException;

public class DCZUTCGQ {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTSMST_RD;
    brParm DCTIQRS_BR = new brParm();
    boolean pgmRtn = false;
    String WS_OVR_NO = " ";
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    String WS_AC_NO = " ";
    String CPN_U_BPZCINTI = "BP-C-INTR-INQ";
    String K_PRDPR_TYPE = "PRDPR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_TD_MIX_LEFT_AMT = 0;
    double WS_TD_MIX_DRAW_AMT = 0;
    double WS_TOTAL_TXN_AMT = 0;
    double WS_LEFT_AMT = 0;
    short WS_PARM_CNT = 0;
    int WS_CNT = 0;
    short WS_B = 0;
    String WS_OVR_NO1 = " ";
    String WS_DD_AC1 = " ";
    String WS_LINK_ACNO1 = " ";
    String WS_DRW_USAGE = " ";
    String WS_PROD_CODE = " ";
    double WS_MAX_TRC_AMT = 0;
    double WS_TD_AVA_AMT = 0;
    short WS_PARM_BUTI_NUM = 0;
    short WS_TD_BUTI_NUM = 0;
    DCZUTCGQ_WS_BBBB WS_BBBB = new DCZUTCGQ_WS_BBBB();
    char WS_DCTIQRS_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_TD_AMT_FLG = ' ';
    char WS_DRAW_MTH_FLG = ' ';
    char WS_MMO_ERR_FLG = ' ';
    char WS_DRAW_TYPE_FLG = ' ';
    char WS_BUTI_MAX_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIQRS DCRIQRS = new DCRIQRS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDRSMST TDRSMST = new TDRSMST();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUMPRM DCCUMPRM = new DCCUMPRM();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUTCGQ DCCUTCGQ;
    public void MP(SCCGWA SCCGWA, DCCUTCGQ DCCUTCGQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUTCGQ = DCCUTCGQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUTCGQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUTCGQ.FUNC);
        CEP.TRC(SCCGWA, DCCUTCGQ.AC);
        CEP.TRC(SCCGWA, DCCUTCGQ.MMO);
        B100_GET_DCTIQR_INFO();
        if (pgmRtn) return;
        DCCUTCGQ.AVA_AMT = WS_TOTAL_TXN_AMT;
        CEP.TRC(SCCGWA, DCCUTCGQ.AVA_AMT);
    }
    public void B100_GET_DCTIQR_INFO() throws IOException,SQLException,Exception {
        WS_AC_NO = DCCUTCGQ.AC;
        WS_TOTAL_TXN_AMT = 0;
        T000_STARTBR_DCTIQRS();
        if (pgmRtn) return;
        T000_READNEXT_DCTIQRS();
        if (pgmRtn) return;
        while (WS_DCTIQRS_FLG != 'N') {
            B110_CHK_DRAW_USAGE();
            if (pgmRtn) return;
            if (WS_MMO_ERR_FLG == 'Y' 
                && WS_DRAW_TYPE_FLG == 'Y') {
            } else {
                T000_READNEXT_DCTIQRS();
                if (pgmRtn) return;
                if (WS_DCTIQRS_FLG == 'N') {
                    if (WS_MMO_ERR_FLG == 'N' 
                        && WS_DRAW_TYPE_FLG == 'Y') {
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IQRS_USEAGE_ERR, DCCUTCGQ.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IQRS_DRW_TYP_ERR, DCCUTCGQ.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                B110_CHK_DRAW_USAGE();
                if (pgmRtn) return;
            }
            WS_OVR_NO1 = DCRIQRS.KEY.OVR_NO;
            WS_DD_AC1 = DCCUTCGQ.AC;
            WS_PROD_CODE = DCRIQRS.PROD_CODE;
            WS_LINK_ACNO1 = DCRIQRS.LINK_AC1;
            WS_MAX_TRC_AMT = DCRIQRS.MAX_TRC_AMT;
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCRIQRS.PROD_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_REC_NOTFND, DCCUTCGQ.RC);
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
            WS_SMST_FLG = ' ';
            B200_GET_AVAILABLE_AMT();
            if (pgmRtn) return;
            T000_READNEXT_DCTIQRS();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIQRS();
        if (pgmRtn) return;
    }
    public void B110_CHK_DRAW_USAGE() throws IOException,SQLException,Exception {
        WS_DRW_USAGE = " ";
        WS_DRW_USAGE = DCRIQRS.DRW_USAGE;
        CEP.TRC(SCCGWA, DCRIQRS.KEY.OVR_NO);
        CEP.TRC(SCCGWA, WS_DRW_USAGE);
        CEP.TRC(SCCGWA, DCCUTCGQ.MMO);
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
        if (DCCUTCGQ.MMO == null) DCCUTCGQ.MMO = "";
        JIBS_tmp_int = DCCUTCGQ.MMO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) DCCUTCGQ.MMO += " ";
        BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DCCUTCGQ.MMO + BPCPRMR.CD.substring(6 + 9 - 1);
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
        if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '1') {
            DCCUTCGQ.DR_USAGE = '1';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '2') {
            DCCUTCGQ.DR_USAGE = '2';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '3') {
            DCCUTCGQ.DR_USAGE = '3';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '4') {
            DCCUTCGQ.DR_USAGE = '4';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '5') {
            DCCUTCGQ.DR_USAGE = '5';
        } else if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP1 == '6') {
            DCCUTCGQ.DR_USAGE = '7';
        }
        CEP.TRC(SCCGWA, DCCUTCGQ.DR_USAGE);
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
        if ((DCCUTCGQ.DR_USAGE == '1' 
            && WS_DRW_USAGE.substring(0, 1).equalsIgnoreCase("1")) 
            || (DCCUTCGQ.DR_USAGE == '2' 
            && WS_DRW_USAGE.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGQ.DR_USAGE == '3' 
            && WS_DRW_USAGE.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGQ.DR_USAGE == '4' 
            && WS_DRW_USAGE.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGQ.DR_USAGE == '5' 
            && WS_DRW_USAGE.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCUTCGQ.DR_USAGE == '7' 
            && WS_DRW_USAGE.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_MMO_ERR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_MMO_ERR_FLG);
    }
    public void B200_GET_AVAILABLE_AMT() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        if (WS_PROD_CODE.equalsIgnoreCase("9510000001") 
            || WS_PROD_CODE.equalsIgnoreCase("9510000002")) {
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
                    C100_GET_TD_START_LEFT_AMT();
                    if (pgmRtn) return;
                    if (WS_MAX_TRC_AMT > WS_TD_AVA_AMT) {
                        WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                    } else {
                        WS_BBBB.WS_TD_AMT = WS_MAX_TRC_AMT;
                        WS_LEFT_AMT = WS_TD_AVA_AMT - WS_MAX_TRC_AMT;
                        CEP.TRC(SCCGWA, WS_LEFT_AMT);
                        CEP.TRC(SCCGWA, WS_TD_MIX_LEFT_AMT);
                        if (WS_LEFT_AMT < WS_TD_MIX_LEFT_AMT) {
                            WS_BBBB.WS_TD_AMT = WS_TD_AVA_AMT;
                        }
                    }
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + WS_BBBB.WS_TD_AMT;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
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
            while (WS_SMST_FLG != 'N') {
                WS_BBBB.WS_TD_PROD_CD = " ";
                WS_TD_BUTI_NUM = 0;
                WS_BUTI_MAX_FLG = ' ';
                WS_TD_BUTI_NUM = TDRSMST.PART_NUM;
                WS_BBBB.WS_TD_PROD_CD = TDRSMST.PROD_CD;
                WS_BBBB.WS_TD_AMT = TDRSMST.BAL;
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                CEP.TRC(SCCGWA, TDRSMST.AC_NO);
                CEP.TRC(SCCGWA, TDRSMST.BAL);
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
                    C100_GET_TD_START_LEFT_AMT();
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
                    WS_TOTAL_TXN_AMT = WS_TOTAL_TXN_AMT + WS_BBBB.WS_TD_AMT;
                    CEP.TRC(SCCGWA, WS_BBBB.WS_TD_AMT);
                    CEP.TRC(SCCGWA, WS_TOTAL_TXN_AMT);
                    T000_READNEXT_TDTSMST();
                    if (pgmRtn) return;
                }
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void C100_GET_TD_START_LEFT_AMT() throws IOException,SQLException,Exception {
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
                    WS_TD_MIX_DRAW_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MDRW_AMT;
                    WS_TD_MIX_LEFT_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MLET_AMT;
                }
            }
        }
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
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DCTIQR_NOT_FND, DCCUTCGQ.RC);
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
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGQ.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUMPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-IRDD-PARM", DCCUMPRM);
        CEP.TRC(SCCGWA, DCCUMPRM.RC);
        if (DCCUMPRM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUTCGQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUTCGQ.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, "DCCUTCGQ=");
            CEP.TRC(SCCGWA, DCCUTCGQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
