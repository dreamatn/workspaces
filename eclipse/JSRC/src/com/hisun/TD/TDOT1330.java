package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFQFBV;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPCUSBOX;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDOT1330 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CI_TYP = ' ';
    short WS_I = 0;
    short WS_I2 = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_L = 0;
    TDOT1330_WS_GACO_AC[] WS_GACO_AC = new TDOT1330_WS_GACO_AC[200];
    long WS_BV_NUM = 0;
    String WS_CUS_AC = " ";
    String WS_ZZD_MMO = " ";
    String WS_CI_NO = " ";
    String WS_TD_CI_NO = " ";
    String WS_DD_CI_NO = " ";
    String WS_OPP_CI_NO = " ";
    double WS_BAL = 0;
    double WS_TXN_AMT = 0;
    double WS_BAL_CR = 0;
    String WS_CDR_FLG = " ";
    double WS_TOT_AMT = 0;
    String WS_TD_AC = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCMACO TDCMACO = new TDCMACO();
    TDCTZZC TDCTZZC = new TDCTZZC();
    TDCTTZC TDCTTZC = new TDCTTZC();
    TDCTDHC TDCTDHC = new TDCTDHC();
    TDCTXYC TDCTXYC = new TDCTXYC();
    TDCOOADV TDCOOADV = new TDCOOADV();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCTCDEC TDCTCDEC = new TDCTCDEC();
    TDCKHCR TDCKHCR = new TDCKHCR();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    TDCTDDR TDCTDDR = new TDCTDDR();
    TDCACDRU TDCACDRU = new TDCACDRU();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    CICQACRI CICQACRI = new CICQACRI();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDRINST TDRINST = new TDRINST();
    CICACCU CICACCU = new CICACCU();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDCACCRU TDCACCRU = new TDCACCRU();
    BPCUABOX BPCUABOX = new BPCUABOX();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    TDB1330_AWA_1330 TDB1330_AWA_1330;
    public TDOT1330() {
        for (int i=0;i<200;i++) WS_GACO_AC[i] = new TDOT1330_WS_GACO_AC();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT1330 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1330_AWA_1330>");
        TDB1330_AWA_1330 = (TDB1330_AWA_1330) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.AC_NO);
        if (TDB1330_AWA_1330.CT_FLG == '2') {
            CEP.TRC(SCCGWA, TDB1330_AWA_1330.CT_FLG);
            WS_CUS_AC = TDB1330_AWA_1330.OP_AC;
            R000_GET_APP();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDB1330_AWA_1330.OP_AC;
            CICACCU.DATA.ENTY_TYP = '1';
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_DD_CI_NO = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, DDCIMMST);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                DDCIMMST.TX_TYPE = 'I';
                DDCIMMST.DATA.KEY.AC_NO = TDB1330_AWA_1330.OP_AC;
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_TYPE == 'B') {
                for (WS_I = 1; WS_I <= 9 
                    && TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO.trim().length() != 0; WS_I += 1) {
                    if (WS_I > 1 
                        && TDB1330_AWA_1330.CT_FLG != '0') {
                        IBS.init(SCCGWA, CICACCU);
                        CICACCU.DATA.AGR_NO = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO;
                        CICACCU.DATA.ENTY_TYP = '1';
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                        WS_TD_CI_NO = CICACCU.DATA.CI_NO;
                        CEP.TRC(SCCGWA, WS_I);
                        CEP.TRC(SCCGWA, WS_TD_CI_NO);
                        CEP.TRC(SCCGWA, WS_DD_CI_NO);
                        if (TDB1330_AWA_1330.CT_FLG == '1' 
                            || (TDB1330_AWA_1330.CT_FLG == '2' 
                            && !WS_TD_CI_NO.equalsIgnoreCase(WS_DD_CI_NO))) {
                            CEP.TRC(SCCGWA, "E418");
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.SVAC_AC_ONL_TO_SVAC);
                        }
                    }
                }
            }
        }
        for (WS_I = 1; WS_I <= 9 
            && TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY);
            if (WS_I > 1) {
                if (!TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY.equalsIgnoreCase(TDB1330_AWA_1330.DRAW_INF[WS_I - 1-1].DCCY)) {
                    WS_ERR_MSG = "TD4514";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                }
            }
            B010_TRANS_DATA_DR();
            if (pgmRtn) return;
            WS_TOT_AMT += TDCACDRU.DRAW_TOT_AMT;
        }
        if (TDB1330_AWA_1330.TXN_AMT > 0) {
            B020_GET_PROD_MODEL_PROC();
            if (pgmRtn) return;
            B030_TRANS_DATA_CR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.TXN_AMT);
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        WS_BAL = WS_TOT_AMT - TDB1330_AWA_1330.TXN_AMT;
        if (WS_BAL > 0) {
            WS_TXN_AMT = WS_BAL;
            B010_TRANS_ENT_CR();
            if (pgmRtn) return;
        }
        if (WS_BAL < 0) {
            WS_TXN_AMT = -1 * WS_BAL;
            B030_TRANS_ENT_DR();
            if (pgmRtn) return;
        }
    }
    public void B010_TRANS_DATA_DR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.OPT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_CD);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_TYP);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_NO);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_SEQ);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DACO_AC);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DNAME);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCVV);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTRK2);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTRK3);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPRT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY_TYP);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPBAL);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPVAL_DT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DVAL_DT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DEXP_DT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPV_FLG);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DOF_AMT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBT_FLG);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTXN_AMT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DINT_FLG);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DINT_RAT);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DD_MTH);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPSW);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DID_TYP);
        CEP.TRC(SCCGWA, TDB1330_AWA_1330.DRAW_INF[WS_I-1].DID_NO);
        IBS.init(SCCGWA, TDCTDDR);
        TDCTDDR.OPT = TDB1330_AWA_1330.OPT;
        TDCTDDR.BV_CD = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_CD;
        TDCTDDR.BV_TYP = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_TYP;
        TDCTDDR.BV_NO = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBV_NO;
        TDCTDDR.CUS_AC = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO;
        WS_TD_AC = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_NO;
        TDCTDDR.AC_SEQ = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DAC_SEQ;
        TDCTDDR.ACO_AC = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DACO_AC;
        TDCTDDR.NAME = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DNAME;
        TDCTDDR.CVV = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCVV;
        TDCTDDR.TRK2_DAT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTRK2;
        TDCTDDR.TRK3_DAT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTRK3;
        TDCTDDR.OPRT_FLG = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPRT;
        TDCTDDR.CCY = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY;
        TDCTDDR.CCY_TYP = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DCCY_TYP;
        TDCTDDR.BAL = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPBAL;
        TDCTDDR.PVAL_DT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPVAL_DT;
        TDCTDDR.VAL_DT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DVAL_DT;
        TDCTDDR.EXP_DT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DEXP_DT;
        TDCTDDR.PREV_FLG = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPV_FLG;
        TDCTDDR.OFD_AMT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DOF_AMT;
        TDCTDDR.PROC_FLG = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DBT_FLG;
        TDCTDDR.TXN_AMT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DTXN_AMT;
        TDCTDDR.INT_FLG = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DINT_FLG;
        TDCTDDR.INT_RAT = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DINT_RAT;
        TDCTDDR.DRAW_MTH = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DD_MTH;
        TDCTDDR.PSW = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DPSW;
        TDCTDDR.ID_TYP = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DID_TYP;
        TDCTDDR.ID_NO = TDB1330_AWA_1330.DRAW_INF[WS_I-1].DID_NO;
        TDCTDDR.CT_FLG = TDB1330_AWA_1330.CT_FLG;
        TDCTDDR.OPP_AC = TDB1330_AWA_1330.OP_AC;
        TDCTDDR.MMO = "C004";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCTDDR.MMO = "G004";
        }
        B100_CALL_TD_DR_UNT();
        if (pgmRtn) return;
    }
    public void B010_TRANS_ENT_CR() throws IOException,SQLException,Exception {
        if (TDB1330_AWA_1330.CT_FLG == '0') {
            B200_CALL_CSH_CR_UNT();
            if (pgmRtn) return;
        } else if (TDB1330_AWA_1330.CT_FLG == '1') {
            B210_CALL_AI_CR_UNT();
            if (pgmRtn) return;
        } else if (TDB1330_AWA_1330.CT_FLG == '2'
            || TDB1330_AWA_1330.CT_FLG == '3'
            || TDB1330_AWA_1330.CT_FLG == '4'
            || TDB1330_AWA_1330.CT_FLG == '5') {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                R000_CHECK_DDAC_INFO();
                if (pgmRtn) return;
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    R000_CHECK_DD_CARD_PROC();
                    if (pgmRtn) return;
                }
            }
            B230_CALL_DD_CR_UNT();
            if (pgmRtn) return;
        } else {
