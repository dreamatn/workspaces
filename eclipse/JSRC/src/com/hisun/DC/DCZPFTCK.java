package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.EA.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPFTCK {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDPRT_RD;
    DBParm DCTBRARC_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTCRDLT_RD;
    DBParm DCTPRDLT_RD;
    DBParm DCTTXTOT_RD;
    DBParm DCTCITCD_RD;
    DBParm DCTCMPAC_RD;
    DBParm DCTSMKGS_RD;
    DBParm DCTINRCD_RD;
    boolean pgmRtn = false;
    String BSL_RTC_FLAG = "SIT_GN_20150606_V1";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_CRDLT_END_DT = 0;
    int WS_PRDLT_END_DT = 0;
    int WS_LAST_TXN_DT = 0;
    int WS_DATE = 0;
    char WS_TB_CRDLT_FG = ' ';
    char WS_TB_PRDLT_FG = ' ';
    char WS_TBLUP_LOST_FLG = ' ';
    char WS_TBLUP_FROZEN_FLG = ' ';
    char WS_CARD_TYPE = ' ';
    char WS_PAY_TX_FLG = ' ';
    String WS_CHNL = " ";
    String WS_CARD_PROD = " ";
    String WS_CHNL_TRM = " ";
    String WS_OWN_TXN_MMO = " ";
    double WS_AC_LIMT_AMT = 0;
    String WS_PRIM_CARDNO = " ";
    String WS_CHK_CARDNO = " ";
    String WS_CHK_STSW = " ";
    int WS_TR_DT = 0;
    int WS_TR_TM = 0;
    int WS_CNT2 = 0;
    char WS_TBL_FLAG = ' ';
    char WS_FROZEN_FLG = ' ';
    char WS_DCTCRDLT_FLG = ' ';
    char WS_BANK_FLG = ' ';
    char WS_OWN_FLG = ' ';
    char WS_AC_TYPE = ' ';
    char WS_BINQ_FLG = ' ';
    char WS_WINQ_FLG = ' ';
    char WS_DCTCMPAC_FLG = ' ';
    char WS_NOT_BIND = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCRTXTOT DCRTXTOT = new DCRTXTOT();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCSCSTS DCCSCSTS = new DCCSCSTS();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCCUCINF DCCUCINM = new DCCUCINF();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRSMKGS DCRSMKGS = new DCRSMKGS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    DCRBRARC DCRBRARC = new DCRBRARC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    EACSBINQ EACSBINQ = new EACSBINQ();
    EACSWINQ EACSWINQ = new EACSWINQ();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CICQACRL CICQACRL = new CICQACRL();
    DCRCMPAC DCRCMPAC = new DCRCMPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCPFTCK DCCPFTCK;
    public void MP(SCCGWA SCCGWA, DCCPFTCK DCCPFTCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPFTCK = DCCPFTCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZPFTCK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.REGN_TYP);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_CCY);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_AMT);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.SNAME_TRF_FLG);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.DNAME_TRF_FLG);
        CEP.TRC(SCCGWA, DCCPFTCK.CANCEL_FLG);
        CEP.TRC(SCCGWA, DCCPFTCK.FUNCTION_CODE);
        CEP.TRC(SCCGWA, DCCPFTCK.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCPFTCK.TRK3_DAT);
        CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
        CEP.TRC(SCCGWA, DCCPFTCK.BANK_FLG);
        CEP.TRC(SCCGWA, DCCPFTCK.TXN_CHNL);
        if (DCCPFTCK.TXN_CHNL.trim().length() > 0) {
            WS_CHNL = DCCPFTCK.TXN_CHNL;
        } else {
            WS_CHNL = SCCGWA.COMM_AREA.CHNL;
        }
        CEP.TRC(SCCGWA, WS_CHNL);
        if (WS_CHNL == null) WS_CHNL = "";
        JIBS_tmp_int = WS_CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) WS_CHNL += " ";
        if (WS_CHNL.substring(0, 3).equalsIgnoreCase("101")) {
            WS_CHNL = "101";
        }
        if (WS_CHNL.equalsIgnoreCase("10301") 
            || WS_CHNL.equalsIgnoreCase("10302") 
            || WS_CHNL.equalsIgnoreCase("10303") 
            || WS_CHNL.equalsIgnoreCase("10304") 
            || WS_CHNL.equalsIgnoreCase("10305") 
            || WS_CHNL.equalsIgnoreCase("10306") 
            || WS_CHNL.equalsIgnoreCase("10307") 
            || WS_CHNL.equalsIgnoreCase("10308") 
            || WS_CHNL.equalsIgnoreCase("10309") 
            || WS_CHNL.equalsIgnoreCase("10311") 
            || WS_CHNL.equalsIgnoreCase("30201")) {
            WS_CHNL = "10301";
        }
        if (WS_CHNL.equalsIgnoreCase("10310") 
            || WS_CHNL.equalsIgnoreCase("30202") 
            || WS_CHNL.equalsIgnoreCase("30205")) {
            WS_CHNL = "10310";
        }
        if (WS_CHNL.equalsIgnoreCase("30204")) {
            WS_CHNL = "10209";
        }
        CEP.TRC(SCCGWA, WS_CHNL);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCPFTCK.VAL.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.AC_TYP);
        if (DCCUCINF.AC_TYP == '3') {
            if (DCCUCINF.FACE_FLG == 'Y') {
                WS_AC_TYPE = '3';
            } else {
                WS_AC_TYPE = '5';
            }
        }
        if (DCCUCINF.AC_TYP == '2' 
            && DCCUCINF.CARD_MEDI == '4') {
            if (DCCUCINF.FACE_FLG == 'Y') {
                WS_AC_TYPE = '2';
            } else {
                WS_AC_TYPE = '4';
            }
        }
        if (DCCUCINF.AC_TYP == '1') {
            WS_AC_TYPE = '1';
        }
        CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
        if (DCCUCINF.ADSC_TYPE == 'C') {
            WS_AC_TYPE = '0';
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("1")) {
            WS_NOT_BIND = 'Y';
        } else {
            WS_NOT_BIND = 'N';
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y')) {
            if (DCCPFTCK.FUNCTION_CODE == 'S' 
                || DCCPFTCK.FUNCTION_CODE == 'B' 
                || DCCPFTCK.FUNCTION_CODE == ' ' 
                || DCCPFTCK.FUNCTION_CODE == 'M') {
                B100_CHECK_INPUT();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
                T000_READ_DCTCDDAT();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if ((DCRCDDAT.CARD_STS != 'N' 
                || DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                && (DCCPFTCK.FUNCTION_CODE != 'M')) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
            }
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y')) {
            B010_CHECK_AGT();
            if (pgmRtn) return;
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y') 
            && DCCPFTCK.FUNCTION_CODE != 'S') {
            B020_CHECK_II_III();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.FUNCTION_CODE == 'L' 
            || DCCPFTCK.FUNCTION_CODE == 'B' 
            || DCCPFTCK.FUNCTION_CODE == ' ' 
            || DCCPFTCK.FUNCTION_CODE == 'M') {
            B200_CHECK_TRANS_VALIDITY();
            if (pgmRtn) return;
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y')) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCPFTCK.VAL.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_PROD_FLG == 'S') {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                T000_READ_DCTCITCD();
                if (pgmRtn) return;
                if (DCRCITCD.STS == '0') {
                    B300_SMK_PROCESS();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCPFTCK.VAL.TXN_TYPE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXN_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PAY_TX_FLG = ' ';
        if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
            WS_PAY_TX_FLG = 'Y';
        } else {
            WS_PAY_TX_FLG = 'N';
        }
        if (DCCPFTCK.VAL.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            CEP.TRC(SCCGWA, DCRCDDAT.JOIN_CUS_FLG);
            CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
            if (DCRCDDAT.JOIN_CUS_FLG == 'Y' 
                && WS_PAY_TX_FLG == 'Y') {
                WS_CHNL_TRM = SCCGWA.COMM_AREA.CHNL;
                if ((((WS_CHNL_TRM.equalsIgnoreCase("EFT") 
                    || WS_CHNL_TRM.equalsIgnoreCase("HPS") 
                    || WS_CHNL_TRM.equalsIgnoreCase("BPS") 
                    || WS_CHNL_TRM.equalsIgnoreCase("SDS") 
                    || WS_CHNL_TRM.equalsIgnoreCase("TRM")) 
                    || (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID") 
                    && SCCGWA.COMM_AREA.REQ_CHNL2.equalsIgnoreCase("MIB1"))) 
                    && (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04"))) 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("20")) {
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUP_JOINT_CARD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCDQPRD);
            if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
                DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
            } else {
                DCCDQPRD.VAL.KEY.PROD_CD = DCRCDDAT.PROD_CD;
            }
            S000_CALL_DCZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
            if (DCRCDDAT.CARD_STS != 'C') {
                if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("02")) {
                } else {
                    if (DCRCDDAT.CARD_STS != 'N') {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                    JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                    CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(17 - 1, 17 + 1 - 1));
                    if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                    JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                    if (DCRCDDAT.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_FBID_STS);
                    }
                    CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
                    if (DCRCDDAT.CARD_LNK_TYP == '2') {
                        CEP.TRC(SCCGWA, "--附属卡需�?查主卡状�?--");
                        IBS.init(SCCGWA, CICQACRL);
                        CICQACRL.DATA.AC_NO = DCCPFTCK.VAL.CARD_NO;
                        CICQACRL.DATA.AC_REL = "03";
                        CICQACRL.FUNC = 'I';
                        S000_CALL_CIZQACRL();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
                        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
                        WS_PRIM_CARDNO = CICQACRL.O_DATA.O_REL_AC_NO;
                        CEP.TRC(SCCGWA, WS_PRIM_CARDNO);
                        IBS.init(SCCGWA, DCCUCINF);
                        DCCUCINF.CARD_NO = WS_PRIM_CARDNO;
                        S000_CALL_DCZUCINF();
                        if (pgmRtn) return;
                        IBS.CLONE(SCCGWA, DCCUCINF, DCCUCINM);
                        CEP.TRC(SCCGWA, DCCUCINM.CARD_STSW);
                        if (DCCUCINM.CARD_STSW == null) DCCUCINM.CARD_STSW = "";
                        JIBS_tmp_int = DCCUCINM.CARD_STSW.length();
                        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINM.CARD_STSW += " ";
                        if (DCCUCINM.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_FBID_STS;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "DKTEST");
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B310_CHECK_MMO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP);
            if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3 == 'Y') {
            } else {
                IBS.init(SCCGWA, DCCSCSTS);
                DCCSCSTS.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (!JIBS_tmp_str[0].equalsIgnoreCase("117580") 
                    && !JIBS_tmp_str[0].equalsIgnoreCase("260132")) {
                    B130_CHECK_LOSS_STS();
                    if (pgmRtn) return;
                }
            }
        }
        if (DCCPFTCK.VAL.REGN_TYP == '1' 
            && DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01")) {
            if (DCRCDDAT.DRAW_OVER_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OUT_DRAW_NOT_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPFTCK.VAL.SNAME_TRF_FLG == 'Y' 
            && DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04")) {
            if (DCRCDDAT.SAME_NAME_TFR_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SNAME_TRF_NOT_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPFTCK.VAL.DNAME_TRF_FLG == 'Y' 
            && DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04")) {
            if (DCRCDDAT.DIFF_NAME_TFR_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DNAME_TRF_NOT_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPFTCK.FUNCTION_CODE != 'S' 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            if ((DCCPFTCK.TRK2_DAT.trim().length() > 0 
                || DCCPFTCK.TRK3_DAT.trim().length() > 0) 
                && DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03")) {
                if (DCRCDDAT.TRAN_WITH_CARD != 'Y') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MEDI_CARD_TRAN_FBD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if ((DCCPFTCK.TRK2_DAT.trim().length() == 0 
                && DCCPFTCK.TRK3_DAT.trim().length() == 0) 
                && WS_PAY_TX_FLG == 'Y') {
                if (DCRCDDAT.TRAN_NO_CARD != 'Y') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOCARD_TRAN_FBD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DCCPFTCK.VAL.REGN_TYP != '0' 
                && WS_PAY_TX_FLG == 'Y') {
                if (DCRCDDAT.TRAN_CRS_BOR != 'Y') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRAN_BORD_FBD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CHNL.equalsIgnoreCase("10301")) {
                if (DCRCDDAT.TRAN_ATM_FLG != 'Y') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRAN_ATM_FBD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHECK_AGT() throws IOException,SQLException,Exception {
        if (DCCPFTCK.FUNCTION_CODE != 'S') {
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                IBS.init(SCCGWA, DCRCPARM);
                IBS.init(SCCGWA, BPCPRMR);
                DCRCPARM.KEY.TYP = "DCPRM";
                BPCPRMR.FUNC = ' ';
                DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
                IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
                BPCPRMR.DAT_PTR = DCRCPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.DB_FREE);
                CEP.TRC(SCCGWA, DCRCDDAT.DOUBLE_FREE_FLG);
                if (DCRCPARM.DATA_TXT.DB_FREE == 'Y') {
                    if (DCRCDDAT.DOUBLE_FREE_FLG == 'C') {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ALLOW_DB_FREE);
                    }
                } else {
                    if (DCRCDDAT.DOUBLE_FREE_FLG == 'C' 
                        || DCRCDDAT.DOUBLE_FREE_FLG == 'N') {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ALLOW_DB_FREE);
                    }
                }
            }
        }
    }
    public void B020_CHECK_II_III() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPFTCK.TO_AC_NO);
        CEP.TRC(SCCGWA, DCCPFTCK.FR_AC_NO);
        CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
        if (DCCPFTCK.FUNCTION_CODE != 'S') {
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                && (WS_AC_TYPE == '2' 
                || WS_AC_TYPE == '3') 
                && DCCPFTCK.TO_AC_NO.trim().length() > 0 
                && !DCCPFTCK.TXN_MMO.equalsIgnoreCase("A049")) {
                CEP.TRC(SCCGWA, "----1111111-----");
                IBS.init(SCCGWA, EACSBINQ);
                EACSBINQ.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                S000_CALL_EAZSBINQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
                if (!EACSBINQ.AC_ARRAY[1-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[2-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[3-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[4-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[5-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO)) {
                    if (DCCPFTCK.FUNCTION_CODE != 'M') {
                        DCCPFTCK.FUNCTION_CODE = 'L';
                    }
                } else {
                    DCCPFTCK.FUNCTION_CODE = 'S';
                }
            }
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("02") 
                && (WS_AC_TYPE == '2' 
                || WS_AC_TYPE == '3') 
                && DCCPFTCK.FR_AC_NO.trim().length() > 0) {
                IBS.init(SCCGWA, EACSBINQ);
                EACSBINQ.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                S000_CALL_EAZSBINQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
                CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
                if (!EACSBINQ.AC_ARRAY[1-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[2-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[3-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[4-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                    && !EACSBINQ.AC_ARRAY[5-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO)) {
                    if (DCCPFTCK.FUNCTION_CODE != 'M') {
                        DCCPFTCK.FUNCTION_CODE = 'L';
                    }
                } else {
                    DCCPFTCK.FUNCTION_CODE = 'S';
                }
            }
        }
        if (DCCPFTCK.TXN_MMO == null) DCCPFTCK.TXN_MMO = "";
        JIBS_tmp_int = DCCPFTCK.TXN_MMO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) DCCPFTCK.TXN_MMO += " ";
        if (DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("I") 
            || DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("D")) {
            DCCPFTCK.FUNCTION_CODE = 'S';
        }
        if (DCCPFTCK.TXN_MMO == null) DCCPFTCK.TXN_MMO = "";
        JIBS_tmp_int = DCCPFTCK.TXN_MMO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) DCCPFTCK.TXN_MMO += " ";
        if (!DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("I") 
            && !DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("D") 
            && !DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("G") 
            && !DCCPFTCK.TXN_MMO.substring(0, 1).equalsIgnoreCase("Y") 
            && !DCCPFTCK.TXN_MMO.equalsIgnoreCase("A049")) {
            if (WS_AC_TYPE == '4' 
                || WS_AC_TYPE == '5') {
                WS_BINQ_FLG = 'N';
                WS_WINQ_FLG = 'N';
                if (DCCPFTCK.TO_AC_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, EACSBINQ);
                    EACSBINQ.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    S000_CALL_EAZSBINQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
                    if (!EACSBINQ.AC_ARRAY[1-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[2-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[3-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[4-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[5-1].CON_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO)) {
                        WS_BINQ_FLG = 'N';
                    } else {
                        WS_BINQ_FLG = 'Y';
                    }
                    IBS.init(SCCGWA, EACSWINQ);
                    EACSWINQ.LST_AC = DCCPFTCK.TO_AC_NO;
                    S000_CALL_EAZSWINQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[1-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[2-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[3-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[4-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[5-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[6-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[7-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[8-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[9-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[10-1].LAST_AC);
                    if ((EACSWINQ.AC_ARRAY[1-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[1-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[2-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[2-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[3-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[3-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[4-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[4-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[5-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[5-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[6-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[6-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[7-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[7-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[8-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[8-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[9-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[9-1].DC_FLG == 'D') 
                        || (EACSWINQ.AC_ARRAY[10-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.TO_AC_NO) 
                        && EACSWINQ.AC_ARRAY[10-1].DC_FLG == 'D')) {
                        WS_WINQ_FLG = 'Y';
                    } else {
                        WS_WINQ_FLG = 'N';
                    }
                    CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
                    CEP.TRC(SCCGWA, WS_AC_TYPE);
                    CEP.TRC(SCCGWA, DCCPFTCK.FR_AC_NO);
                    CEP.TRC(SCCGWA, WS_NOT_BIND);
                    CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
                    if (WS_BINQ_FLG == 'N' 
                        && WS_WINQ_FLG == 'N') {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_B_AND_W_NOT_EXIST);
                    }
                }
                if (DCCPFTCK.FR_AC_NO.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "---EC-III---");
                    IBS.init(SCCGWA, EACSBINQ);
                    EACSBINQ.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    S000_CALL_EAZSBINQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[2-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[3-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[4-1].CON_AC);
                    CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[5-1].CON_AC);
                    if (!EACSBINQ.AC_ARRAY[1-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[2-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[3-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[4-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && !EACSBINQ.AC_ARRAY[5-1].CON_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO)) {
                        WS_BINQ_FLG = 'N';
                    } else {
                        WS_BINQ_FLG = 'Y';
                    }
                    IBS.init(SCCGWA, EACSWINQ);
                    EACSWINQ.LST_AC = DCCPFTCK.FR_AC_NO;
                    S000_CALL_EAZSWINQ();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[1-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[2-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[3-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[4-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[5-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[6-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[7-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[8-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[9-1].LAST_AC);
                    CEP.TRC(SCCGWA, EACSWINQ.AC_ARRAY[10-1].LAST_AC);
                    if ((EACSWINQ.AC_ARRAY[1-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[1-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[2-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[2-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[3-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[3-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[4-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[4-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[5-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[5-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[6-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[6-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[7-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[7-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[8-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[8-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[9-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[9-1].DC_FLG == 'C') 
                        || (EACSWINQ.AC_ARRAY[10-1].LAST_AC.equalsIgnoreCase(DCCPFTCK.FR_AC_NO) 
                        && EACSWINQ.AC_ARRAY[10-1].DC_FLG == 'C')) {
                        WS_WINQ_FLG = 'Y';
                    } else {
                        WS_WINQ_FLG = 'N';
                    }
                    CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
                    CEP.TRC(SCCGWA, WS_AC_TYPE);
                    CEP.TRC(SCCGWA, DCCPFTCK.FR_AC_NO);
                    CEP.TRC(SCCGWA, WS_NOT_BIND);
                    CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
                    if (WS_NOT_BIND == 'Y') {
                        WS_BINQ_FLG = 'Y';
                        WS_WINQ_FLG = 'Y';
                    }
                    if (WS_BINQ_FLG == 'N' 
                        && WS_WINQ_FLG == 'N') {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_B_AND_W_NOT_EXIST);
                    }
                }
                if (WS_BINQ_FLG == 'N' 
                    && WS_WINQ_FLG == 'N') {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_B_AND_W_NOT_EXIST);
                }
            }
        }
    }
    public void B130_CHECK_LOSS_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, DCRCPARM);
            IBS.init(SCCGWA, BPCPRMR);
            DCRCPARM.KEY.TYP = "DCPRM";
            BPCPRMR.FUNC = ' ';
            DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
            IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            BPCPRMR.DAT_PTR = DCRCPARM;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            BPCPLOSS.DATA_INFO.AC = DCCSCSTS.CARD_NO;
            BPCPLOSS.DATA_INFO.STS = '2';
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
            if (WS_TBL_FLAG == 'N') {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                R000_UPDATE_STS_SPCDEAL();
                if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPCPLOSS.DATA_INFO.LOS_DT;
                SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                if (SCCCLDT.RC != 0) {
                    IBS.init(SCCGWA, SCCBINF);
                    SCCBINF.ERR_TYPE = 'P';
                    SCCBINF.ERR_ACTION = 'E';
                    SCCBINF.ERR_NAME = "SCSSCLDT";
                    WS_ERR_MSG = "" + SCCCLDT.RC;
                    JIBS_tmp_int = WS_ERR_MSG.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                    SCCBINF.OTHER_INFO = "CALL SCSSCLDT ERROR!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (SCCCLDT.DAYS < DCRCPARM.DATA_TXT.LOS_TMP_DAYS) {
                    if (WS_PAY_TX_FLG == 'Y') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                            || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) 
                            && (!JIBS_tmp_str[0].equalsIgnoreCase("801155"))) {
                        } else {
                            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                } else {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                    IBS.init(SCCGWA, DCCUCINF);
                    DCCUCINF.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    S000_CALL_DCZUCINF();
                    if (pgmRtn) return;
                    if (DCCUCINF.CARD_PROD_FLG == 'S' 
                        || (DCCUCINF.MOVE_FLG == 'Y' 
                        && DCCUCINF.ADSC_TYPE == 'C')) {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS);
                    }
                    R000_UPDATE_STS_SPCDEAL();
                    if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
                }
            }
        }
        if (DCCPFTCK.TRK2_DAT.trim().length() > 0 
            || DCCPFTCK.TRK3_DAT.trim().length() > 0) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PAY_TX_FLG == 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PAY_TX_FLG == 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PAY_TX_FLG == 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PAY_TX_FLG == 'Y' 
                && DCRCDDAT.CARD_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05") 
            || WS_CHNL.equalsIgnoreCase("10208")) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_PRIM_CARDNO.trim().length() > 0) {
            WS_CHK_CARDNO = WS_PRIM_CARDNO;
            WS_CHK_STSW = DCCUCINM.CARD_STSW;
            R000_CHECK_LOSS_STS();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_LOSS_STS() throws IOException,SQLException,Exception {
        if (WS_CHK_STSW == null) WS_CHK_STSW = "";
        JIBS_tmp_int = WS_CHK_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_CHK_STSW += " ";
        if (WS_CHK_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            BPCPLOSS.DATA_INFO.AC = WS_CHK_CARDNO;
            BPCPLOSS.DATA_INFO.STS = '2';
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
            if (WS_TBL_FLAG == 'N') {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                IBS.init(SCCGWA, DCCSCSTS);
                DCCSCSTS.CARD_NO = WS_CHK_CARDNO;
                R000_UPDATE_STS_SPCDEAL();
                if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPCPLOSS.DATA_INFO.LOS_DT;
                SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
                SCSSCLDT2.MP(SCCGWA, SCCCLDT);
                if (SCCCLDT.RC != 0) {
                    IBS.init(SCCGWA, SCCBINF);
                    SCCBINF.ERR_TYPE = 'P';
                    SCCBINF.ERR_ACTION = 'E';
                    SCCBINF.ERR_NAME = "SCSSCLDT";
                    WS_ERR_MSG = "" + SCCCLDT.RC;
                    JIBS_tmp_int = WS_ERR_MSG.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                    SCCBINF.OTHER_INFO = "CALL SCSSCLDT ERROR!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (SCCCLDT.DAYS < DCRCPARM.DATA_TXT.LOS_TMP_DAYS) {
                    if (WS_PAY_TX_FLG == 'Y') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                            || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) 
                            && (!JIBS_tmp_str[0].equalsIgnoreCase("801155"))) {
                        } else {
                            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                } else {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                    IBS.init(SCCGWA, DCCSCSTS);
                    DCCSCSTS.CARD_NO = WS_CHK_CARDNO;
                    R000_UPDATE_STS_SPCDEAL();
                    if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
                }
            }
        }
        if (WS_CHK_STSW == null) WS_CHK_STSW = "";
        JIBS_tmp_int = WS_CHK_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_CHK_STSW += " ";
        if (WS_CHK_STSW == null) WS_CHK_STSW = "";
        JIBS_tmp_int = WS_CHK_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_CHK_STSW += " ";
        if (WS_CHK_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && WS_PAY_TX_FLG == 'Y' 
            && WS_CHK_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05") 
            || WS_CHNL.equalsIgnoreCase("10208")) {
            if (WS_CHK_STSW == null) WS_CHK_STSW = "";
            JIBS_tmp_int = WS_CHK_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) WS_CHK_STSW += " ";
            if (WS_CHK_STSW == null) WS_CHK_STSW = "";
            JIBS_tmp_int = WS_CHK_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) WS_CHK_STSW += " ";
            if (WS_CHK_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || WS_CHK_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CHECK_TRANS_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_LMT_FLG);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
        WS_PAY_TX_FLG = ' ';
        CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
        CEP.TRC(SCCGWA, WS_CHNL);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_AMT);
        CEP.TRC(SCCGWA, WS_AC_LIMT_AMT);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        if (DCCUCINF.ADSC_TYPE == 'C' 
            && WS_CHNL.equalsIgnoreCase("10301") 
            && DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01")) {
            IBS.init(SCCGWA, DCRCPARM);
            IBS.init(SCCGWA, BPCPRMR);
            DCRCPARM.KEY.TYP = "DCPRM";
            BPCPRMR.FUNC = ' ';
            DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
            IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            BPCPRMR.DAT_PTR = DCRCPARM;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.AC_LIMT_AMT);
            WS_AC_LIMT_AMT = DCRCPARM.DATA_TXT.AC_LIMT_AMT;
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DCCPFTCK.VAL.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            IBS.init(SCCGWA, DCRCMPAC);
            DCRCMPAC.KEY.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCMPAC.KEY.AC = CICQACRL.O_DATA.O_REL_AC_NO;
            T000_READ_UPD_DCTCMPAC();
            if (pgmRtn) return;
            if (WS_DCTCMPAC_FLG == 'Y') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                    || DCCPFTCK.CANCEL_FLG == 'Y') {
                    if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                        DCRCMPAC.DRW_CAMT_ATM = DCRCMPAC.DRW_CAMT_ATM - DCCPFTCK.VAL.TXN_AMT;
                    }
                } else {
                    DCRCMPAC.DRW_CAMT_ATM = DCRCMPAC.DRW_CAMT_ATM + DCCPFTCK.VAL.TXN_AMT;
                }
                if (DCRCMPAC.DRW_CAMT_ATM > WS_AC_LIMT_AMT) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACAMT_OVER_LIMIT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                B400_UPDATE_DCTCMPAC();
                if (pgmRtn) return;
            } else {
                if ((DCCPFTCK.VAL.TXN_AMT > WS_AC_LIMT_AMT) 
                    && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                    && DCCPFTCK.CANCEL_FLG == 'Y')) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACAMT_OVER_LIMIT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                    && DCCPFTCK.CANCEL_FLG == 'Y')) {
                    B500_ADD_DCTCMPAC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B300_SMK_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_AMT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BANK_FLG = 'Y';
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
            WS_BANK_FLG = 'N';
        }
        IBS.init(SCCGWA, DCRBRARC);
        DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_READ_DCTBRARC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if ((BPCPQORG.ATTR == '2' 
            || BPCPQORG.ATTR == '3') 
            && (BPCPQORG.TYP.equalsIgnoreCase("12") 
            || BPCPQORG.TYP.equalsIgnoreCase("21")) 
            && DCRBRARC.AREA_NO == 3288) {
            WS_OWN_FLG = 'Y';
        }
        if (WS_OWN_FLG == 'Y') {
            WS_OWN_TXN_MMO = DCCPFTCK.TXN_MMO;
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("02")) {
                if (((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    || WS_BANK_FLG == 'Y') 
                    && DCCPFTCK.VAL.TXN_AMT >= 50 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("A701") 
                    && ((!WS_OWN_TXN_MMO.equalsIgnoreCase("J001") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("T003") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J103") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J002") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J202") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("M137") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J206") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J004") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J207") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J208") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J209") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J210") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J212") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J214") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J216") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J218") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("T004") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J219") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J220") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J221") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J222") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J223") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J224") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J225") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("Q101") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J204") 
                    && !WS_OWN_TXN_MMO.equalsIgnoreCase("J205")))) {
                    IBS.init(SCCGWA, DCRSMKGS);
                    DCRSMKGS.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    DCRSMKGS.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
                    DCRSMKGS.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRSMKGS.KEY.TXN_TIME = SCCGWA.COMM_AREA.TR_TIME;
                    DCRSMKGS.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DCRSMKGS.OWN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    DCRSMKGS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRSMKGS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DCTSMKGS();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, DCRCITCD);
                    DCRCITCD.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    T000_READUPD_DCTCITCD();
                    if (pgmRtn) return;
                    DCRCITCD.STS = 'W';
                    DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_UPDATE_DCTCITCD();
                    if (pgmRtn) return;
                }
            }
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01")) {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    || WS_BANK_FLG == 'Y') {
                    IBS.init(SCCGWA, DCRSMKGS);
                    DCRSMKGS.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    DCRSMKGS.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
                    DCRSMKGS.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRSMKGS.KEY.TXN_TIME = SCCGWA.COMM_AREA.TR_TIME;
                    DCRSMKGS.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DCRSMKGS.OWN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    DCRSMKGS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRSMKGS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DCTSMKGS();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, DCRCITCD);
                    DCRCITCD.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                    T000_READUPD_DCTCITCD();
                    if (pgmRtn) return;
                    DCRCITCD.STS = 'W';
                    DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_UPDATE_DCTCITCD();
                    if (pgmRtn) return;
                }
            }
            if (DCCPFTCK.TXN_MMO.equalsIgnoreCase("A701")) {
                IBS.init(SCCGWA, DCRSMKGS);
                DCRSMKGS.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                DCRSMKGS.TXN_TYPE = "A8";
                DCRSMKGS.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRSMKGS.KEY.TXN_TIME = SCCGWA.COMM_AREA.TR_TIME;
                DCRSMKGS.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
                DCRSMKGS.OWN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                DCRSMKGS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRSMKGS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_DCTSMKGS();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                T000_READUPD_DCTCITCD();
                if (pgmRtn) return;
                DCRCITCD.STS = 'W';
                DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_UPDATE_DCTCITCD();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
            WS_OWN_TXN_MMO = DCCPFTCK.TXN_MMO;
            if ((WS_OWN_TXN_MMO.equalsIgnoreCase("J001") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("T003") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J103") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J002") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J202") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("M137") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J206") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J004") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J207") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J208") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J209") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J210") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J212") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J214") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J216") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J218") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("T004") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J219") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J220") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J221") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J222") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J223") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J224") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J225") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("Q101") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J204") 
                || WS_OWN_TXN_MMO.equalsIgnoreCase("J205")) 
                && DCCPFTCK.VAL.TXN_AMT >= 50) {
                IBS.init(SCCGWA, DCRSMKGS);
                DCRSMKGS.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                DCRSMKGS.TXN_TYPE = "A9";
                DCRSMKGS.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRSMKGS.KEY.TXN_TIME = SCCGWA.COMM_AREA.TR_TIME;
                DCRSMKGS.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
                DCRSMKGS.OWN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                DCRSMKGS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRSMKGS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_DCTSMKGS();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
                T000_READUPD_DCTCITCD();
                if (pgmRtn) return;
                DCRCITCD.STS = 'W';
                DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_UPDATE_DCTCITCD();
                if (pgmRtn) return;
            }
        }
    }
    public void B400_UPDATE_DCTCMPAC() throws IOException,SQLException,Exception {
        DCRCMPAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCMPAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCMPAC();
        if (pgmRtn) return;
    }
    public void B500_ADD_DCTCMPAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCMPAC);
        DCRCMPAC.KEY.AC = CICQACRL.O_DATA.O_REL_AC_NO;
        DCRCMPAC.KEY.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCMPAC.DRW_CAMT_ATM = DCCPFTCK.VAL.TXN_AMT;
        DCRCMPAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCMPAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCMPAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCMPAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_ADD_DCTCMPAC();
        if (pgmRtn) return;
    }
    public void B205_CHECK_TXN_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
        if (WS_CARD_TYPE == '1' 
            && WS_PAY_TX_FLG == 'Y') {
            DCRCRDLT.KEY.REGN_TYP = '0';
            DCRCRDLT.KEY.CHNL_NO = "ALL";
            DCRCRDLT.KEY.TXN_TYPE = "19";
            DCRCRDLT.KEY.LMT_CCY = "156";
        } else {
            DCRCRDLT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
            DCRCRDLT.KEY.CHNL_NO = WS_CHNL;
            DCRCRDLT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
            DCRCRDLT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
        }
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        WS_TR_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_TR_TM = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, WS_TR_DT);
        CEP.TRC(SCCGWA, WS_TR_TM);
        WS_TB_CRDLT_FG = ' ';
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (WS_DCTCRDLT_FLG == 'Y') {
            if (DCRCRDLT.SE_LMT_AMT > 0 
                && DCRCRDLT.SE_LMT_AMT < 99999999999999.99 
                && SCCGWA.COMM_AREA.AC_DATE >= DCRCRDLT.STA_DT 
                && SCCGWA.COMM_AREA.AC_DATE <= DCRCRDLT.END_DT) {
                S000_CHECK_DCTTXTOT_SE();
                if (pgmRtn) return;
            } else {
                S000_CHECK_DCTTXTOT_GEN();
                if (pgmRtn) return;
            }
            WS_TB_CRDLT_FG = 'Y';
        }
    }
    public void S000_CHECK_DCTTXTOT_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CARD_NO);
        if (WS_CARD_TYPE == '1' 
            && WS_PAY_TX_FLG == 'Y') {
            DCRTXTOT.KEY.REGN_TYP = '0';
            DCRTXTOT.KEY.CHNL_NO = "ALL";
            DCRTXTOT.KEY.TXN_TYPE = "19";
            DCRTXTOT.KEY.LMT_CCY = "156";
        } else {
            DCRTXTOT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
            DCRTXTOT.KEY.CHNL_NO = WS_CHNL;
            DCRTXTOT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
            DCRTXTOT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
        }
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.LMT_CCY);
        DCRTXTOT.STA_DT = DCRCRDLT.STA_DT;
        CEP.TRC(SCCGWA, DCRTXTOT.STA_DT);
        DCRTXTOT.STA_TM = DCRCRDLT.STA_TM;
        CEP.TRC(SCCGWA, DCRTXTOT.STA_TM);
        DCRTXTOT.END_DT = DCRCRDLT.END_DT;
        CEP.TRC(SCCGWA, DCRTXTOT.END_DT);
        DCRTXTOT.END_TM = DCRCRDLT.END_TM;
        CEP.TRC(SCCGWA, DCRTXTOT.END_TM);
        if (DCCPFTCK.FUNCTION_CODE == 'M') {
            T000_READ_DCTTXTOT();
            if (pgmRtn) return;
        } else {
            T000_READ_DCTTXTOT_UPD();
            if (pgmRtn) return;
        }
        if (WS_TBL_FLAG == 'Y') {
            T000_UPDATE_TOT_GENAL();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CARD-TXTLMT-NO-RECORD-ADD-TXT");
            DCRTXTOT.DLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.DLY_TOT_VOL = 1;
            DCRTXTOT.MLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.MLY_TOT_VOL = 1;
            DCRTXTOT.SYY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.YLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            WS_CRDLT_END_DT = DCRCRDLT.END_DT;
            DCRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y')) {
            R000_CHECK_CARD_LIMIT();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.FUNCTION_CODE != 'M') {
            DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "FND-UPDATE");
                T000_REWRITE_DCTTXTOT();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "NOT-FND-WRITE");
                T000_ADD_DCTTXTOT();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CHECK_DCTTXTOT_SE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
        if (WS_CARD_TYPE == '1' 
            && WS_PAY_TX_FLG == 'Y') {
            DCRTXTOT.KEY.REGN_TYP = '0';
            DCRTXTOT.KEY.CHNL_NO = "ALL";
            DCRTXTOT.KEY.TXN_TYPE = "19";
            DCRTXTOT.KEY.LMT_CCY = "156";
        } else {
            DCRTXTOT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
            DCRTXTOT.KEY.CHNL_NO = WS_CHNL;
            DCRTXTOT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
            DCRTXTOT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
        }
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.LMT_CCY);
        DCRTXTOT.STA_DT = DCRCRDLT.STA_DT;
        DCRTXTOT.STA_TM = DCRCRDLT.STA_TM;
        DCRTXTOT.END_DT = DCRCRDLT.END_DT;
        DCRTXTOT.END_TM = DCRCRDLT.END_TM;
        if (DCCPFTCK.FUNCTION_CODE == 'M') {
            T000_READ_DCTTXTOT();
            if (pgmRtn) return;
        } else {
            T000_READ_DCTTXTOT_UPD();
            if (pgmRtn) return;
        }
        if (WS_TBL_FLAG == 'Y') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || DCCPFTCK.CANCEL_FLG == 'Y') {
                if (DCRTXTOT.SE_LMT_AMT > 0) {
                    DCRTXTOT.SE_LMT_AMT = DCRTXTOT.SE_LMT_AMT - DCCPFTCK.VAL.TXN_AMT;
                }
            } else {
                DCRTXTOT.SE_LMT_AMT = DCRTXTOT.SE_LMT_AMT + DCCPFTCK.VAL.TXN_AMT;
            }
        } else {
            if (DCRCRDLT.STA_DT != 10101) {
                DCRTXTOT.SE_LMT_AMT = DCCPFTCK.VAL.TXN_AMT;
                DCRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
        }
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y')) {
            R000_CHECK_CARD_LIMIT_SE();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.FUNCTION_CODE != 'M') {
            DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "FND-UPDATE");
                T000_REWRITE_DCTTXTOT();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "NOT-FND-WRITE");
                T000_ADD_DCTTXTOT();
                if (pgmRtn) return;
            }
        }
    }
    public void B202_CHECK_PRD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCRCDDAT.PROD_CD;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.PROD_CD);
        DCRPRDLT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
        DCRPRDLT.KEY.CHNL_NO = WS_CHNL;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
        DCRPRDLT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
        DCRPRDLT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
        if (WS_AC_TYPE == '1') {
            DCRPRDLT.KEY.AC_CLASS = '1';
        } else if (WS_AC_TYPE == '2') {
            DCRPRDLT.KEY.AC_CLASS = '2';
            DCRPRDLT.KEY.CHNL_NO = "99999";
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                DCRPRDLT.KEY.TXN_TYPE = "06";
            } else {
                DCRPRDLT.KEY.TXN_TYPE = "07";
            }
        } else if (WS_AC_TYPE == '4') {
            DCRPRDLT.KEY.AC_CLASS = '2';
            DCRPRDLT.KEY.REGN_TYP = '5';
        } else if (WS_AC_TYPE == '3') {
            DCRPRDLT.KEY.AC_CLASS = '3';
            DCRPRDLT.KEY.CHNL_NO = "99999";
            if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                DCRPRDLT.KEY.TXN_TYPE = "06";
            } else {
                DCRPRDLT.KEY.TXN_TYPE = "07";
            }
        } else if (WS_AC_TYPE == '5') {
            DCRPRDLT.KEY.AC_CLASS = '3';
            DCRPRDLT.KEY.REGN_TYP = '5';
        } else if (WS_AC_TYPE == '0') {
            DCRPRDLT.KEY.AC_CLASS = '0';
        }
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.AC_CLASS);
        T000_READ_DCTPRDLT_FIRST();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, WS_TB_CRDLT_FG);
            WS_TB_PRDLT_FG = 'Y';
            if ((WS_TB_CRDLT_FG != 'Y') 
                || (WS_AC_TYPE != '1' 
                && WS_AC_TYPE != '0')) {
                IBS.init(SCCGWA, DCRTXTOT);
                DCRTXTOT.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
                CEP.TRC(SCCGWA, DCRTXTOT.KEY.CARD_NO);
                if (WS_CARD_TYPE == '1' 
                    && WS_PAY_TX_FLG == 'Y') {
                    DCRTXTOT.KEY.REGN_TYP = '0';
                    DCRTXTOT.KEY.CHNL_NO = "ALL";
                    DCRTXTOT.KEY.TXN_TYPE = "19";
                    DCRTXTOT.KEY.LMT_CCY = "156";
                } else {
                    DCRTXTOT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
                    DCRTXTOT.KEY.CHNL_NO = WS_CHNL;
                    DCRTXTOT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
                    DCRTXTOT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
                    if (WS_AC_TYPE == '2') {
                        DCRTXTOT.KEY.CHNL_NO = "99999";
                        if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                            DCRTXTOT.KEY.TXN_TYPE = "06";
                        } else {
                            DCRTXTOT.KEY.TXN_TYPE = "07";
                        }
                    } else if (WS_AC_TYPE == '4') {
                        DCRTXTOT.KEY.REGN_TYP = '5';
                    } else if (WS_AC_TYPE == '3') {
                        DCRTXTOT.KEY.CHNL_NO = "99999";
                        if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                            || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                            DCRTXTOT.KEY.TXN_TYPE = "06";
                        } else {
                            DCRTXTOT.KEY.TXN_TYPE = "07";
                        }
                    } else if (WS_AC_TYPE == '5') {
                        DCRTXTOT.KEY.REGN_TYP = '5';
                    }
                }
                CEP.TRC(SCCGWA, DCRTXTOT.KEY.REGN_TYP);
                CEP.TRC(SCCGWA, DCRTXTOT.KEY.CHNL_NO);
                CEP.TRC(SCCGWA, DCRTXTOT.KEY.TXN_TYPE);
                CEP.TRC(SCCGWA, DCRTXTOT.KEY.LMT_CCY);
                DCRTXTOT.STA_DT = DCRPRDLT.STA_DT;
                CEP.TRC(SCCGWA, DCRTXTOT.STA_DT);
                DCRTXTOT.STA_TM = DCRPRDLT.STA_TM;
                CEP.TRC(SCCGWA, DCRTXTOT.STA_TM);
                DCRTXTOT.END_DT = DCRPRDLT.END_DT;
                CEP.TRC(SCCGWA, DCRTXTOT.END_DT);
                DCRTXTOT.END_TM = DCRPRDLT.END_TM;
                CEP.TRC(SCCGWA, DCRTXTOT.END_TM);
                if (DCCPFTCK.FUNCTION_CODE == 'M') {
                    T000_READ_DCTTXTOT();
                    if (pgmRtn) return;
                } else {
                    T000_READ_DCTTXTOT_UPD();
                    if (pgmRtn) return;
                }
                if (WS_TBL_FLAG == 'Y') {
                    T000_UPDATE_TOT_GENAL();
                    if (pgmRtn) return;
                    if (DCCPFTCK.FUNCTION_CODE != 'M') {
                        DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_DCTTXTOT();
                        if (pgmRtn) return;
                    }
                } else {
                    DCRTXTOT.DLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
                    CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_AMT);
                    DCRTXTOT.DLY_TOT_VOL = 1;
                    CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_VOL);
                    DCRTXTOT.MLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
                    CEP.TRC(SCCGWA, DCRTXTOT.MLY_TOT_AMT);
                    DCRTXTOT.MLY_TOT_VOL = 1;
                    CEP.TRC(SCCGWA, DCRTXTOT.MLY_TOT_VOL);
                    DCRTXTOT.SYY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
                    CEP.TRC(SCCGWA, DCRTXTOT.SYY_TOT_AMT);
                    DCRTXTOT.YLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
                    CEP.TRC(SCCGWA, DCRTXTOT.YLY_TOT_AMT);
                    WS_CRDLT_END_DT = DCRCRDLT.END_DT;
                    CEP.TRC(SCCGWA, WS_CRDLT_END_DT);
                    if (DCCPFTCK.FUNCTION_CODE != 'M') {
                        DCRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DCRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                        DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_ADD_DCTTXTOT();
                        if (pgmRtn) return;
                    }
                }
            }
            if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || DCCPFTCK.CANCEL_FLG == 'Y')) {
                WS_PRDLT_END_DT = DCRPRDLT.END_DT;
                if (WS_PRDLT_END_DT != 20991231 
                    && SCCGWA.COMM_AREA.AC_DATE <= WS_PRDLT_END_DT 
                    && SCCGWA.COMM_AREA.AC_DATE >= DCRPRDLT.STA_DT) {
                    if (DCRTXTOT.SE_LMT_AMT > DCRPRDLT.SE_LMT_AMT) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AMT_OVER_PRD_SELIMIT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    R000_CHECK_PRODUCT_LIMIT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B300_ADD_DCTTXTOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCPFTCK.VAL.CARD_NO;
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CARD_NO);
        if (WS_CARD_TYPE == '1' 
            && WS_PAY_TX_FLG == 'Y') {
            DCRTXTOT.KEY.REGN_TYP = '0';
            DCRTXTOT.KEY.CHNL_NO = "ALL";
            DCRTXTOT.KEY.TXN_TYPE = "19";
            DCRTXTOT.KEY.LMT_CCY = "156";
        } else {
            DCRTXTOT.KEY.REGN_TYP = DCCPFTCK.VAL.REGN_TYP;
            DCRTXTOT.KEY.CHNL_NO = WS_CHNL;
            DCRTXTOT.KEY.TXN_TYPE = DCCPFTCK.VAL.TXN_TYPE;
            DCRTXTOT.KEY.LMT_CCY = DCCPFTCK.VAL.TXN_CCY;
            if (WS_AC_TYPE == '2') {
                DCRTXTOT.KEY.CHNL_NO = "99999";
                if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                    DCRTXTOT.KEY.TXN_TYPE = "06";
                } else {
                    DCRTXTOT.KEY.TXN_TYPE = "07";
                }
            } else if (WS_AC_TYPE == '4') {
                DCRTXTOT.KEY.REGN_TYP = '5';
            } else if (WS_AC_TYPE == '3') {
                DCRTXTOT.KEY.CHNL_NO = "99999";
                if (DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("01") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("03") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("04") 
                    || DCCPFTCK.VAL.TXN_TYPE.equalsIgnoreCase("05")) {
                    DCRTXTOT.KEY.TXN_TYPE = "06";
                } else {
                    DCRTXTOT.KEY.TXN_TYPE = "07";
                }
            } else if (WS_AC_TYPE == '5') {
                DCRTXTOT.KEY.REGN_TYP = '5';
            }
        }
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.LMT_CCY);
        DCRTXTOT.STA_DT = 10101;
        CEP.TRC(SCCGWA, DCRTXTOT.STA_DT);
        DCRTXTOT.STA_TM = 0;
        CEP.TRC(SCCGWA, DCRTXTOT.STA_TM);
        DCRTXTOT.END_DT = 20991231;
        CEP.TRC(SCCGWA, DCRTXTOT.END_DT);
        DCRTXTOT.END_TM = 235959;
        CEP.TRC(SCCGWA, DCRTXTOT.END_TM);
        T000_READ_DCTTXTOT_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            T000_UPDATE_TOT_GENAL();
            if (pgmRtn) return;
            DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTTXTOT();
            if (pgmRtn) return;
        } else {
            DCRTXTOT.DLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_AMT);
            DCRTXTOT.DLY_TOT_VOL = 1;
            CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_VOL);
            DCRTXTOT.MLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            CEP.TRC(SCCGWA, DCRTXTOT.MLY_TOT_AMT);
            DCRTXTOT.MLY_TOT_VOL = 1;
            CEP.TRC(SCCGWA, DCRTXTOT.MLY_TOT_VOL);
            DCRTXTOT.SYY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            CEP.TRC(SCCGWA, DCRTXTOT.SYY_TOT_AMT);
            DCRTXTOT.YLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            CEP.TRC(SCCGWA, DCRTXTOT.YLY_TOT_AMT);
            DCRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_ADD_DCTTXTOT();
            if (pgmRtn) return;
        }
    }
    public void B310_CHECK_MMO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPRMR.TYP = "PARMC";
        if (BPCPRMR.CD == null) BPCPRMR.CD = "";
        JIBS_tmp_int = BPCPRMR.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
        BPCPRMR.CD = "MMO" + BPCPRMR.CD.substring(3);
        if (DCCPFTCK.TXN_MMO.trim().length() > 0) {
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (DCCPFTCK.TXN_MMO == null) DCCPFTCK.TXN_MMO = "";
            JIBS_tmp_int = DCCPFTCK.TXN_MMO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) DCCPFTCK.TXN_MMO += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DCCPFTCK.TXN_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
        } else {
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (SCCGWA.COMM_AREA.TR_MMO == null) SCCGWA.COMM_AREA.TR_MMO = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.TR_MMO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.TR_MMO += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + SCCGWA.COMM_AREA.TR_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
        }
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
    }
    public void R000_CHANGE_LMT_DAILY() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y') {
            if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != WS_DATE) 
                || (DCCPFTCK.CANCEL_FLG == 'Y' 
                && DCCPFTCK.CANCEL_DATE != WS_DATE)) {
            } else {
                DCRTXTOT.DLY_TOT_AMT = DCRTXTOT.DLY_TOT_AMT - DCCPFTCK.VAL.TXN_AMT;
                DCRTXTOT.DLY_TOT_VOL = DCRTXTOT.DLY_TOT_VOL - 1;
            }
        } else {
            DCRTXTOT.DLY_TOT_AMT = DCRTXTOT.DLY_TOT_AMT + DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.DLY_TOT_VOL = DCRTXTOT.DLY_TOT_VOL + 1;
        }
    }
    public void R000_RESET_LMT_DAILY() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DCRTXTOT.DLY_TOT_AMT = 0;
            DCRTXTOT.DLY_TOT_VOL = 0;
        } else {
            DCRTXTOT.DLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.DLY_TOT_VOL = 1;
        }
    }
    public void R000_CHANGE_LMT_MONTH() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + DCCPFTCK.CANCEL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            JIBS_tmp_str[3] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[3].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
            if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && !JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) 
                || (DCCPFTCK.CANCEL_FLG == 'Y' 
                && !JIBS_tmp_str[2].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[3].substring(0, 6)))) {
            } else {
                DCRTXTOT.MLY_TOT_AMT = DCRTXTOT.MLY_TOT_AMT - DCCPFTCK.VAL.TXN_AMT;
                DCRTXTOT.MLY_TOT_VOL = DCRTXTOT.MLY_TOT_VOL - 1;
            }
        } else {
            DCRTXTOT.MLY_TOT_AMT = DCRTXTOT.MLY_TOT_AMT + DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.MLY_TOT_VOL = DCRTXTOT.MLY_TOT_VOL + 1;
        }
    }
    public void R000_RESET_LMT_MONTH() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DCRTXTOT.MLY_TOT_AMT = 0;
            DCRTXTOT.MLY_TOT_VOL = 0;
        } else {
            DCRTXTOT.MLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
            DCRTXTOT.MLY_TOT_VOL = 1;
        }
    }
    public void R000_CHANGE_LMT_SEMIYEAR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            JIBS_tmp_str[3] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[3].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
            JIBS_tmp_str[4] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[4].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
            JIBS_tmp_str[5] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[5].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
            JIBS_tmp_str[6] = "" + DCCPFTCK.CANCEL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[6].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[6] = "0"+JIBS_tmp_str[6];
            JIBS_tmp_str[7] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[7].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[7] = "0"+JIBS_tmp_str[7];
            JIBS_tmp_str[8] = "" + DCCPFTCK.CANCEL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[8].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[8] = "0"+JIBS_tmp_str[8];
            JIBS_tmp_str[9] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[9].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[9] = "0"+JIBS_tmp_str[9];
            JIBS_tmp_str[10] = "" + DCCPFTCK.CANCEL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[10].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[10] = "0"+JIBS_tmp_str[10];
            JIBS_tmp_str[11] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[11].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[11] = "0"+JIBS_tmp_str[11];
            if (((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && (!JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4)))) 
                || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && (JIBS_tmp_str[2].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[3].substring(0, 4))) 
                && (JIBS_tmp_str[4].substring(5 - 1, 5 + 2 - 1).compareTo("06") <= 0) 
                && (JIBS_tmp_str[5].substring(5 - 1, 5 + 2 - 1).compareTo("06") > 0))) 
                || ((DCCPFTCK.CANCEL_FLG == 'Y' 
                && (!JIBS_tmp_str[6].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[7].substring(0, 4)))) 
                || (DCCPFTCK.CANCEL_FLG == 'Y' 
                && (JIBS_tmp_str[8].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[9].substring(0, 4))) 
                && (JIBS_tmp_str[10].substring(5 - 1, 5 + 2 - 1).compareTo("06") <= 0) 
                && (JIBS_tmp_str[11].substring(5 - 1, 5 + 2 - 1).compareTo("06") > 0)))) {
            } else {
                DCRTXTOT.SYY_TOT_AMT = DCRTXTOT.SYY_TOT_AMT - DCCPFTCK.VAL.TXN_AMT;
            }
        } else {
            DCRTXTOT.SYY_TOT_AMT = DCRTXTOT.SYY_TOT_AMT + DCCPFTCK.VAL.TXN_AMT;
        }
    }
    public void R000_RESET_LMT_SEMIYEAR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DCRTXTOT.SYY_TOT_AMT = 0;
        } else {
            DCRTXTOT.SYY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
        }
    }
    public void R000_CHANGE_LMT_YEAR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || DCCPFTCK.CANCEL_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + DCCPFTCK.CANCEL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            JIBS_tmp_str[3] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[3].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
            if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && !JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) 
                || (DCCPFTCK.CANCEL_FLG == 'Y' 
                && !JIBS_tmp_str[2].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[3].substring(0, 4)))) {
            } else {
                DCRTXTOT.YLY_TOT_AMT = DCRTXTOT.YLY_TOT_AMT - DCCPFTCK.VAL.TXN_AMT;
            }
        } else {
            DCRTXTOT.YLY_TOT_AMT = DCRTXTOT.YLY_TOT_AMT + DCCPFTCK.VAL.TXN_AMT;
        }
    }
    public void R000_RESET_LMT_YEAR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DCRTXTOT.YLY_TOT_AMT = 0;
        } else {
            DCRTXTOT.YLY_TOT_AMT = DCCPFTCK.VAL.TXN_AMT;
        }
    }
    public void R000_CHECK_CARD_LIMIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_AMT);
        CEP.TRC(SCCGWA, DCRCRDLT.TXN_LMT_AMT);
        if (DCRTXTOT.YLY_TOT_AMT > DCRCRDLT.YLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_YEAR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.SYY_TOT_AMT > DCRCRDLT.SYY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_SYY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_AMT > DCRCRDLT.MLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_MON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_VOL > DCRCRDLT.MLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TIMES_OVER_MONTH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.DLY_TOT_AMT > DCRCRDLT.DLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.DLY_TOT_VOL > DCRCRDLT.DLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TIMES_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.VAL.TXN_AMT > DCRCRDLT.TXN_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_ONCE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CARD_LIMIT_SE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRTXTOT.SE_LMT_AMT);
        CEP.TRC(SCCGWA, DCRCRDLT.SE_LMT_AMT);
        if (DCRTXTOT.SE_LMT_AMT > DCRCRDLT.SE_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AMT_OVER_SELIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PRODUCT_LIMIT() throws IOException,SQLException,Exception {
        if (DCRTXTOT.YLY_TOT_AMT > DCRPRDLT.YLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_YEAR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.SYY_TOT_AMT > DCRPRDLT.SYY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_SYY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_AMT > DCRPRDLT.MLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_MON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_VOL > DCRPRDLT.MLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_MON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.DLY_TOT_AMT > DCRPRDLT.DLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.DLY_TOT_VOL > DCRPRDLT.DLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TIMES_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPFTCK.VAL.TXN_AMT > DCRPRDLT.TXN_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_ONCE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R001_CHECK_PRODUCT_LIMIT() throws IOException,SQLException,Exception {
        if (DCCPFTCK.VAL.TXN_AMT > DCRPRDLT.TXN_LMT_AMT) {
            if ((WS_CARD_PROD.equalsIgnoreCase("CAD16100") 
                || WS_CARD_PROD.equalsIgnoreCase("CAD12100") 
                || WS_CARD_PROD.equalsIgnoreCase("CAD16000"))) {
                S001_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_ONCE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRTXTOT.DLY_TOT_AMT > DCRPRDLT.DLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.DLY_TOT_VOL > DCRPRDLT.DLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TIMES_OVER_DAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_AMT > DCRPRDLT.MLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_MON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.MLY_TOT_VOL > DCRPRDLT.MLY_LMT_VOL) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_MON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.SYY_TOT_AMT > DCRPRDLT.SYY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_SYY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRTXTOT.YLY_TOT_AMT > DCRPRDLT.YLY_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXAMT_OVER_YEAR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PRDLT_END_DT = DCRPRDLT.END_DT;
        if (WS_PRDLT_END_DT != 20991231) {
            if (DCRTXTOT.YLY_TOT_AMT > DCRPRDLT.YLY_LMT_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AMT_OVER_PRD_SELIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R100_PROCS_DCTTXTOT_AGAIN() throws IOException,SQLException,Exception {
        T000_READ_DCTTXTOT_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCRTXTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTTXTOT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TOT_GENAL() throws IOException,SQLException,Exception {
        WS_DATE = DCRTXTOT.UPDTBL_DATE;
        CEP.TRC(SCCGWA, WS_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
            R000_CHANGE_LMT_YEAR();
            if (pgmRtn) return;
        } else {
            R000_RESET_LMT_YEAR();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_tmp_str[2] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[2].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
        JIBS_tmp_str[3] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[3].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
        JIBS_tmp_str[4] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[4].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
        JIBS_tmp_str[5] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[5].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
        if ((JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) 
            && ((JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).compareTo("06") <= 0 
            && JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).compareTo("06") <= 0) 
            || (JIBS_tmp_str[4].substring(5 - 1, 5 + 2 - 1).compareTo("06") > 0 
            && JIBS_tmp_str[5].substring(5 - 1, 5 + 2 - 1).compareTo("06") > 0))) {
            R000_CHANGE_LMT_SEMIYEAR();
            if (pgmRtn) return;
        } else {
            R000_RESET_LMT_SEMIYEAR();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
            R000_CHANGE_LMT_MONTH();
            if (pgmRtn) return;
        } else {
            R000_RESET_LMT_MONTH();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE == WS_DATE) {
            R000_CHANGE_LMT_DAILY();
            if (pgmRtn) return;
        } else {
            R000_RESET_LMT_DAILY();
            if (pgmRtn) return;
        }
    }
    public void R000_UPDATE_STS_SPCDEAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-STS", DCCSCSTS);
        if (DCCSCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCSCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        CEP.TRC(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE == 0) {
            WS_TBL_FLAG = 'Y';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("BP1993")) {
            WS_TBL_FLAG = 'N';
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("BP1993")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EAZSBINQ() throws IOException,SQLException,Exception {
        EAZSBINQ EAZSBINQ = new EAZSBINQ();
        EAZSBINQ.MP(SCCGWA, EACSBINQ);
    }
    public void S000_CALL_EAZSWINQ() throws IOException,SQLException,Exception {
        EAZSWINQ EAZSWINQ = new EAZSWINQ();
        EAZSWINQ.MP(SCCGWA, EACSWINQ);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_DCTCDPRT() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        DCTCDPRT_RD.col = "PRT_STS";
        DCTCDPRT_RD.where = "CARD_NO = :DCRCDPRT.KEY.CARD_NO "
            + "AND PRT_TYP = :DCRCDPRT.KEY.PRT_TYP";
        IBS.READ(SCCGWA, DCRCDPRT, this, DCTCDPRT_RD);
    }
    public void T000_READ_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        if (DCRCDDAT.CARD_LNK_TYP == '1') {
            WS_CARD_TYPE = '0';
        } else {
            WS_CARD_TYPE = '1';
        }
        WS_CARD_PROD = DCRCDDAT.PROD_CD;
    }
    public void R000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_STARTBR_DCTCRDLT_FIRST() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.col = "CARD_NO, REGN_TYP, CHNL_NO, TXN_TYPE, LMT_CCY, STA_DT, STA_TM, END_DT, END_TM, TXN_LMT_AMT, DLY_LMT_AMT, DLY_LMT_VOL, MLY_LMT_AMT, MLY_LMT_VOL, SYY_LMT_AMT, YLY_LMT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCRDLT_RD.where = "CARD_NO = :DCRCRDLT.KEY.CARD_NO "
            + "AND REGN_TYP = :DCRCRDLT.KEY.REGN_TYP "
            + "AND CHNL_NO = :DCRCRDLT.KEY.CHNL_NO "
            + "AND TXN_TYPE = :DCRCRDLT.KEY.TXN_TYPE "
            + "AND LMT_CCY = :DCRCRDLT.KEY.LMT_CCY";
        DCTCRDLT_RD.fst = true;
        DCTCRDLT_RD.order = "CRT_DATE DESC";
        IBS.READ(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.READ(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        WS_DCTCRDLT_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTCRDLT_FLG = 'Y';
            CEP.TRC(SCCGWA, "STARTBR-DCTCRDLT-FND");
        } else {
            WS_DCTCRDLT_FLG = 'N';
            CEP.TRC(SCCGWA, "STARTBR-DCTCRDLT-NOT-FND");
        }
        CEP.TRC(SCCGWA, DCRCRDLT.STA_DT);
        CEP.TRC(SCCGWA, DCRCRDLT.END_DT);
        CEP.TRC(SCCGWA, DCRCRDLT.END_DT);
        CEP.TRC(SCCGWA, DCRCRDLT.END_TM);
    }
    public void T000_READ_DCTPRDLT_FIRST() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            WS_TB_PRDLT_FG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRPRDLT.STA_DT);
        CEP.TRC(SCCGWA, DCRPRDLT.END_DT);
        CEP.TRC(SCCGWA, DCRPRDLT.END_DT);
        CEP.TRC(SCCGWA, DCRPRDLT.END_TM);
    }
    public void T000_READ_DCTTXTOT_UPD() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        DCTTXTOT_RD.col = "CARD_NO, REGN_TYP, CHNL_NO, TXN_TYPE, LMT_CCY, STA_DT, STA_TM, END_DT, END_TM, DLY_TOT_AMT, LAST_DLY_TOT_AMT, DLY_TOT_VOL, LAST_DLY_TOT_VOL, MLY_TOT_AMT, LAST_MLY_TOT_AMT, MLY_TOT_VOL, LAST_MLY_TOT_VOL, SYY_TOT_AMT, LAST_SYY_TOT_AMT, YLY_TOT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTTXTOT_RD.upd = true;
        IBS.READ(SCCGWA, DCRTXTOT, DCTTXTOT_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTXTOT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTTXTOT() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        IBS.READ(SCCGWA, DCRTXTOT, DCTTXTOT_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTTXTOT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        WS_TBL_FLAG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.REWRITE(SCCGWA, DCRCITCD, DCTCITCD_RD);
    }
    public void T000_READ_UPD_DCTCMPAC() throws IOException,SQLException,Exception {
        DCTCMPAC_RD = new DBParm();
        DCTCMPAC_RD.TableName = "DCTCMPAC";
        DCTCMPAC_RD.upd = true;
        IBS.READ(SCCGWA, DCRCMPAC, DCTCMPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTCMPAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTCMPAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCMPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCMPAC() throws IOException,SQLException,Exception {
        DCTCMPAC_RD = new DBParm();
        DCTCMPAC_RD.TableName = "DCTCMPAC";
        IBS.REWRITE(SCCGWA, DCRCMPAC, DCTCMPAC_RD);
    }
    public void T000_ADD_DCTCMPAC() throws IOException,SQLException,Exception {
        DCTCMPAC_RD = new DBParm();
        DCTCMPAC_RD.TableName = "DCTCMPAC";
        DCTCMPAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCMPAC, DCTCMPAC_RD);
    }
    public void T000_REWRITE_DCTTXTOT() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        DCTTXTOT_RD.col = "DLY_TOT_AMT, LAST_DLY_TOT_AMT, DLY_TOT_VOL, LAST_DLY_TOT_VOL, MLY_TOT_AMT, LAST_MLY_TOT_AMT, MLY_TOT_VOL, LAST_MLY_TOT_VOL, SYY_TOT_AMT, LAST_SYY_TOT_AMT, YLY_TOT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTTXTOT_RD.where = "CARD_NO = :DCRTXTOT.KEY.CARD_NO "
            + "AND REGN_TYP = :DCRTXTOT.KEY.REGN_TYP "
            + "AND CHNL_NO = :DCRTXTOT.KEY.CHNL_NO "
            + "AND TXN_TYPE = :DCRTXTOT.KEY.TXN_TYPE "
            + "AND LMT_CCY = :DCRTXTOT.KEY.LMT_CCY";
        IBS.REWRITE(SCCGWA, DCRTXTOT, this, DCTTXTOT_RD);
    }
    public void T000_ADD_DCTTXTOT() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        DCTTXTOT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRTXTOT, DCTTXTOT_RD);
    }
    public void T000_WRITE_DCTSMKGS() throws IOException,SQLException,Exception {
        DCTSMKGS_RD = new DBParm();
        DCTSMKGS_RD.TableName = "DCTSMKGS";
        DCTSMKGS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRSMKGS, DCTSMKGS_RD);
    }
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.col = "CARD_NO, CARD_SEQ, TR_JRNNO, INCD_TYPE, TR_BR, AUT_TLR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '01'";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC ,TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S001_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_SUP_SOCIAL_CARD, SCCBINF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
