package com.hisun.DC;

import java.util.ArrayList;
import java.util.List;
import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZBSP84 {
    DCZBSP84_WS_BBBB WS_BBBB;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DCTIAACR_BR = new brParm();
    boolean pgmRtn = false;
    String WS_OVR_NO = " ";
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    String WS_AC_NO = " ";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String K_PRDPR_TYPE = "PRDPR";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_DDZIMCCY = "DD-I-NFIN-M-CCY";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_AMT = 0;
    double WS_TD_START_AMT = 0;
    double WS_DD_TXN_AMT = 0;
    double WS_TD_TXN_AMT = 0;
    double WS_LEFT_AMT = 0;
    double WS_TD_BAL = 0;
    double WS_PAYING_INT = 0;
    double WS_DD_BAL = 0;
    short WS_PARM_CNT = 0;
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    short WS_B = 0;
    List<DCZBSP84_WS_BBBB> WS_BBBB = new ArrayList<DCZBSP84_WS_BBBB>();
    char WS_DCTIAACR_FLG = ' ';
    char WS_TD_AMT_FLG = ' ';
    char WS_BSP_BAL_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCACE TDCACE = new TDCACE();
    CICACCU CICACCU = new CICACCU();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUCCAL DDCUCCAL = new DDCUCCAL();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCQINTB DDCQINTB = new DDCQINTB();
    IBCLPAY IBCLPAY = new IBCLPAY();
    SCCGWA SCCGWA;
    DCCBSP84 DCCBSP84;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    public void MP(SCCGWA SCCGWA, DCCBSP84 DCCBSP84) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCBSP84 = DCCBSP84;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZBSP84 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (DCCBSP84.PROD_CODE.equalsIgnoreCase("9510000003")) {
            B210_UPDATE_INFO();
            if (pgmRtn) return;
        } else {
            B200_UPDATE_INFO();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCBSP84.TD_MAIN_AC);
        CEP.TRC(SCCGWA, DCCBSP84.AC_NO);
        CEP.TRC(SCCGWA, DCCBSP84.CCY);
        CEP.TRC(SCCGWA, DCCBSP84.CCY_TYPE);
        if (DCCBSP84.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCBSP84.TD_MAIN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCBSP84.TRM_AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCBSP84.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCBSP84.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCBSP84.CCY_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDATE_INFO() throws IOException,SQLException,Exception {
        B300_GET_TDINFO();
        if (pgmRtn) return;
        B400_TDDR_PROC();
        if (pgmRtn) return;
        B500_DDCR_PROC();
        if (pgmRtn) return;
    }
    public void B210_UPDATE_INFO() throws IOException,SQLException,Exception {
        B310_GET_DDINFO();
        if (pgmRtn) return;
        B410_DDDR_PROC();
        if (pgmRtn) return;
        B510_DDCR_PROC();
        if (pgmRtn) return;
    }
    public void B300_GET_TDINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, DCCBSP84.AC_NO);
        CICACCU.DATA.AGR_NO = DCCBSP84.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CNT = 0;
        DCRIAACR.KEY.VIA_AC = DCCBSP84.TD_MAIN_AC;
        CEP.TRC(SCCGWA, DCCBSP84.TD_MAIN_AC);
        CEP.TRC(SCCGWA, WS_TD_AMT_FLG);
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_TD_AMT_FLG != 'Y' 
            && WS_DCTIAACR_FLG != 'N'; WS_CNT += 1) {
            if (DCRIAACR.ACCR_FLG == '1') {
                CEP.TRC(SCCGWA, "ZZZZZZZZ");
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = DCRIAACR.SUB_AC;
                TDCACE.FMT_FLG = 'N';
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCBSP84.TRM_AMT);
                if (TDCACE.DATA[1-1].BAL > 0) {
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_TD_AC = TDCACE.PAGE_INF.AC_NO;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_TD_AMT = TDCACE.DATA[1-1].BAL;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_PART_NUM = TDCACE.DATA[1-1].PART_NUM;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_TD_PROD_CD = DCCBSP84.TD_PROD_CODE;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_VCH_NO = DCRIAACR.VCH_NO;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_VCH_TYPE = DCRIAACR.VCH_TYPE;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_VCH_ID = DCRIAACR.VCH_ID;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_VIA_AC = DCRIAACR.KEY.VIA_AC;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_SEQ = DCRIAACR.KEY.SEQ;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    WS_BBBB = WS_BBBB.get(WS_CNT-1);
                    WS_BBBB.WS_SUB_AC = DCRIAACR.SUB_AC;
                    WS_BBBB.set(WS_CNT-1, WS_BBBB);
                    CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT-1).WS_TD_AMT);
                    CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT-1).WS_SUB_AC);
                    CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT-1).WS_SEQ);
                    WS_TD_BAL = WS_TD_BAL + TDCACE.DATA[1-1].BAL;
                }
                CEP.TRC(SCCGWA, WS_TD_BAL);
                if (WS_TD_BAL >= DCCBSP84.TRM_AMT) {
                    WS_TD_AMT_FLG = 'Y';
                } else {
                    WS_TD_AMT_FLG = 'N';
                }
            }
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT-1).WS_TD_AC);
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B310_GET_DDINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCCY);
        DDCIMCCY.DATA[1-1].AC = DCCBSP84.TD_MAIN_AC;
        DDCIMCCY.DATA[1-1].CCY = DCCBSP84.CCY;
        DDCIMCCY.DATA[1-1].CCY_TYPE = DCCBSP84.CCY_TYPE;
        S000_CALL_DDZIMCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CURR_BAL);
        CEP.TRC(SCCGWA, DCCBSP84.TRM_AMT);
        if (DDCIMCCY.DATA[1-1].CURR_BAL > DCCBSP84.TRM_AMT) {
            WS_BSP_BAL_FLG = '2';
            WS_DD_TXN_AMT = DCCBSP84.TRM_AMT;
        } else {
            WS_BSP_BAL_FLG = '1';
            WS_DD_TXN_AMT = DDCIMCCY.DATA[1-1].CURR_BAL;
        }
        CEP.TRC(SCCGWA, WS_DD_TXN_AMT);
        IBS.init(SCCGWA, IBCLPAY);
        IBCLPAY.AC_NO = DCCBSP84.AC_NO;
        IBCLPAY.PAY_AMT = WS_DD_TXN_AMT;
        IBCLPAY.CCY = DCCBSP84.CCY;
        S000_CALL_IBZLPAY();
        if (pgmRtn) return;
    }
    public void B400_TDDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BSP_BAL_FLG);
        WS_CNT2 = 0;
        WS_BSP_BAL_FLG = '2';
        WS_CNT2 = 0;
        WS_AMT = DCCBSP84.TRM_AMT;
        for (WS_CNT2 = 1; WS_CNT2 < WS_CNT 
            && WS_AMT != 0; WS_CNT2 += 1) {
            IBS.init(SCCGWA, BPCPQPRD);
            WS_B = 0;
            BPCPQPRD.PRDT_CODE = WS_BBBB.get(WS_CNT2-1).WS_TD_PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPRPRMT);
                IBS.init(SCCGWA, BPCPRMM);
                IBS.init(SCCGWA, TDCPRDP);
                BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
                if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
                if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
                JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
                BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
                BPCPRMM.FUNC = '3';
                BPCPRMM.DAT_PTR = BPRPRMT;
                S000_CALL_BPZPRMM();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP);
                while (WS_B < 20) {
                    WS_B += 1;
                    if (TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MIN_CCYC.equalsIgnoreCase("156")) {
                        WS_TD_START_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_B-1].MIN_AMTC;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_CNT2);
            CEP.TRC(SCCGWA, WS_TD_START_AMT);
            if (WS_BBBB.get(WS_CNT2-1).WS_TD_AMT <= WS_AMT) {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.OPT = '0';
                TDCACDRU.CHNL_FLG = 'Y';
                CEP.TRC(SCCGWA, "XHTQ");
                CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT2-1).WS_TD_AC);
                CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT2-1).WS_TD_AMT);
                CEP.TRC(SCCGWA, WS_AMT);
                CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT2-1).WS_SEQ);
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.TXN_AMT = WS_BBBB.get(WS_CNT2-1).WS_TD_AMT;
                TDCACDRU.MAC_CNO = DCCBSP84.TD_MAIN_AC;
                TDCACDRU.AC_SEQ = WS_BBBB.get(WS_CNT2-1).WS_SEQ;
                TDCACDRU.CCY = "156";
                TDCACDRU.CCY_TYP = '1';
                TDCACDRU.BV_NO = WS_BBBB.get(WS_CNT2-1).WS_VCH_NO;
                TDCACDRU.TXN_MMO = "X9D";
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                WS_AMT = WS_AMT - WS_BBBB.get(WS_CNT2-1).WS_TD_AMT;
                if (WS_AMT > TDCACDRU.PAYING_INT) {
                    WS_AMT = WS_AMT - TDCACDRU.PAYING_INT;
                } else {
                    WS_AMT = 0;
                }
                WS_DD_TXN_AMT = WS_DD_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                WS_TD_TXN_AMT = WS_TD_TXN_AMT + TDCACDRU.TXN_AMT;
                WS_PAYING_INT = WS_PAYING_INT + TDCACDRU.PAYING_INT;
            } else {
                CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT2-1).WS_TD_AMT);
                WS_LEFT_AMT = WS_BBBB.get(WS_CNT2-1).WS_TD_AMT - WS_AMT;
                IBS.init(SCCGWA, TDCACDRU);
                if (WS_LEFT_AMT >= WS_TD_START_AMT) {
                    TDCACDRU.OPT = '1';
                    TDCACDRU.TXN_AMT = WS_AMT;
                } else {
                    TDCACDRU.OPT = '0';
                    TDCACDRU.TXN_AMT = WS_BBBB.get(WS_CNT2-1).WS_TD_AMT;
                }
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.CHNL_FLG = 'Y';
                CEP.TRC(SCCGWA, "BFTQ");
                CEP.TRC(SCCGWA, WS_BBBB.get(WS_CNT2-1).WS_TD_AC);
                CEP.TRC(SCCGWA, WS_AMT);
                TDCACDRU.CCY = "156";
                TDCACDRU.CCY_TYP = '1';
                TDCACDRU.MAC_CNO = DCCBSP84.TD_MAIN_AC;
                TDCACDRU.AC_SEQ = WS_BBBB.get(WS_CNT2-1).WS_SEQ;
                TDCACDRU.BV_NO = WS_BBBB.get(WS_CNT2-1).WS_VCH_NO;
                TDCACDRU.TXN_MMO = "X9D";
                S000_CALL_TDZACDRU();
                if (pgmRtn) return;
                WS_DD_TXN_AMT = WS_DD_TXN_AMT + TDCACDRU.TXN_AMT + TDCACDRU.PAYING_INT;
                WS_TD_TXN_AMT = WS_TD_TXN_AMT + TDCACDRU.TXN_AMT;
                WS_PAYING_INT = WS_PAYING_INT + TDCACDRU.PAYING_INT;
            }
        }
        CEP.TRC(SCCGWA, WS_PAYING_INT);
    }
    public void B410_DDDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BSP_BAL_FLG);
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DCCBSP84.TD_MAIN_AC;
        DDCUDRAC.OTHER_AC = DCCBSP84.AC_NO;
        DDCUDRAC.CCY = DCCBSP84.CCY;
        DDCUDRAC.CCY_TYPE = DCCBSP84.CCY_TYPE;
        DDCUDRAC.TX_AMT = WS_DD_TXN_AMT;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B500_DDCR_PROC() throws IOException,SQLException,Exception {
        if (WS_DD_TXN_AMT > 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCBSP84.AC_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.CCY_TYPE = '1';
            DDCUCRAC.TX_AMT = WS_DD_TXN_AMT;
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BANK_CR_FLG = 'Y';
            DDCUCRAC.AUTO_TDTODD_FLG = 'Y';
            if (TDCACDRU.PAYING_INT > 0) {
                DDCUCRAC.TD_INT_AMT = TDCACDRU.PAYING_INT;
            }
            DDCUCRAC.TX_MMO = "X9D";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (WS_TD_TXN_AMT > 0) {
            IBS.init(SCCGWA, DDCUCCAL);
            DDCUCCAL.AC = DCCBSP84.AC_NO;
            DDCUCCAL.CCY = "156";
            DDCUCCAL.CCY_TYPE = '1';
            DDCUCCAL.DRCR_FLG = 'D';
            DDCUCCAL.AMT = WS_TD_TXN_AMT;
            S000_CALL_DDZUCCAL();
            if (pgmRtn) return;
        }
        if (WS_PAYING_INT > 0) {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = DCCBSP84.AC_NO;
            DDCIMCCY.DATA[1-1].CCY = "156";
            DDCIMCCY.DATA[1-1].CCY_TYPE = '1';
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            WS_DD_BAL = DDCIMCCY.DATA[1-1].CURR_BAL + DDCIMCCY.DATA[1-1].CCAL_TOT_BAL;
            CEP.TRC(SCCGWA, WS_DD_BAL);
            B610_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void B510_DDCR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = DCCBSP84.AC_NO;
        DDCUCRAC.CCY = DCCBSP84.CCY;
        DDCUCRAC.CCY_TYPE = DCCBSP84.CCY_TYPE;
        DDCUCRAC.TX_AMT = WS_DD_TXN_AMT;
        DDCUCRAC.OTHER_AC = DCCBSP84.TD_MAIN_AC;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZQINTB() throws IOException,SQLException,Exception {
        DDZQINTB DDZQINTB = new DDZQINTB();
        DDZQINTB.MP(SCCGWA, DDCQINTB);
    }
    public void B610_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = DCCBSP84.AC_NO;
        BPCPFHIS.DATA.TX_TOOL = DCCBSP84.AC_NO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_CCY = "156";
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_AMT = WS_PAYING_INT;
        BPCPFHIS.DATA.VAL_BAL = WS_DD_BAL;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.VAL_BAL);
        BPCPFHIS.DATA.TX_MMO = "C01";
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZIMCCY, DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-CCAL-PRCO", DDCUCCAL);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_MMO);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
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
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = '1' "
            + "AND FRM_APP = 'TD'";
        DCTIAACR_BR.rp.order = "SEQ DESC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIAACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTIAACR_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIAACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTIAACR_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void S000_CALL_IBZLPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-L-PAY", IBCLPAY);
        CEP.TRC(SCCGWA, IBCLPAY.RC.RC_CODE);
        if (IBCLPAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCLPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
