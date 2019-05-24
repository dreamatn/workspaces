package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFQFBV;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDOT1340 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CI_TYP = ' ';
    short WS_I = 0;
    short WS_I2 = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_L = 0;
    String WS_CI_NO = " ";
    String WS_OPP_CI_NO = " ";
    TDOT1340_WS_GACO_AC[] WS_GACO_AC = new TDOT1340_WS_GACO_AC[200];
    long WS_BV_NUM = 0;
    double WS_BAL_TOT = 0;
    char WS_BAL_ACTI_FLG = ' ';
    char WS_END_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
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
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    TDCACCRU TDCACCRU = new TDCACCRU();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICACCU CICACCU = new CICACCU();
    TDC1340 TDC1340 = new TDC1340();
    TDRSMST TDRSMST = new TDRSMST();
    TDRIREV TDRIREV = new TDRIREV();
    CICQOPAC CICQOPAC = new CICQOPAC();
    TDCPIOD TDCPIOD = new TDCPIOD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDB1340_AWA_1340 TDB1340_AWA_1340;
    public TDOT1340() {
        for (int i=0;i<200;i++) WS_GACO_AC[i] = new TDOT1340_WS_GACO_AC();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        B800_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT1340 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1340_AWA_1340>");
        TDB1340_AWA_1340 = (TDB1340_AWA_1340) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ACO1[1-1].GACO1);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.BV_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.BV_CD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.BV_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PRT_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ID_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ID_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CI_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CI_NAME);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PROD_CD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CCY);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CCY_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.TERM);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OPEN_CNT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.EVRBAL);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.TOTBAL);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.SDT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.DDT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.RAT_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PCT_S);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.FLT_RAT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.RUL_CD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.INT_RAT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PRV_RAT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OVE_RAT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.D_MTH);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.D_ID_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.D_ID_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.D_INF);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PSW_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PSW);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CR_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.DR_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CT_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_AC);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_BVTYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_BVNO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_NAME);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_REV);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_DRAW);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_PSW);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_IDTYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_IDNO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_CVV);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_TRK2);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_TRK3);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CHQ_TYPE);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CHQ_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CHQ_DATE);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CHQ_PSW);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OP_PRT);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.INR_MTH);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.INR_TERM);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ZC_PROD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ZC_NUM);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.UP_FLG);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.STL_AC);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.DD_NAME);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.HEP_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.FRG_IND);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OIC_NO);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.RES_CD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.SUB_DP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.MON_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.ACO_TYP);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.REMARK);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B020_GET_PROD_MODEL_PROC();
            if (pgmRtn) return;
            B030_TRANS_DATA_PROC();
            if (pgmRtn) return;
        } else {
            B010_TRANS_MACO_PROC();
            if (pgmRtn) return;
            B020_GET_PROD_MODEL_PROC();
            if (pgmRtn) return;
            B030_TRANS_DATA_PROC();
            if (pgmRtn) return;
            B540_GET_GINFO();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = TDB1340_AWA_1340.CI_NO;
        S000_LINK_CIZCUST();
        if (pgmRtn) return;
        WS_CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_CI_TYP);
    }
    public void B010_TRANS_MACO_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_GET_PROD_MODEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDB1340_AWA_1340.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.EVRBAL);
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.OPEN_CNT);
        WS_BAL_TOT = TDB1340_AWA_1340.EVRBAL * TDB1340_AWA_1340.OPEN_CNT;
        CEP.TRC(SCCGWA, WS_BAL_TOT);
        IBS.init(SCCGWA, TDCPIOD);
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCPIOD.C_PROD_CD = TDB1340_AWA_1340.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        TDCPIOD.PROD_TYPE = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        TDCPIOD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDCPIOD.INTERM = TDB1340_AWA_1340.TERM;
        TDCPIOD.CCY = TDB1340_AWA_1340.CCY;
        TDCPIOD.BAL = WS_BAL_TOT;
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
        if (TDCPIOD.ACTI_NO.trim().length() == 0) {
            WS_BAL_ACTI_FLG = 'N';
        } else {
            WS_BAL_ACTI_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_BAL_ACTI_FLG);
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDC1340);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            for (WS_I = 1; WS_I <= TDB1340_AWA_1340.OPEN_CNT 
                && WS_I <= 200; WS_I += 1) {
                IBS.init(SCCGWA, TDCACCRU);
                TDCACCRU.OPT = '0';
                TDCACCRU.OPSW_FLG = 'Y';
                TDCACCRU.PRDMO_CD = "MMDP";
                TDCACCRU.PROD_CD = TDB1340_AWA_1340.PROD_CD;
                TDCACCRU.BV_CD = TDB1340_AWA_1340.BV_CD;
                TDCACCRU.BV_TYP = TDB1340_AWA_1340.BV_TYP;
                TDCACCRU.PRT_FLG = TDB1340_AWA_1340.PRT_FLG;
                TDCACCRU.ID_TYP = TDB1340_AWA_1340.ID_TYP;
                TDCACCRU.ID_NO = TDB1340_AWA_1340.ID_NO;
                TDCACCRU.CI_NO = TDB1340_AWA_1340.CI_NO;
                TDCACCRU.CCY = TDB1340_AWA_1340.CCY;
                TDCACCRU.CCY_TYP = TDB1340_AWA_1340.CCY_TYP;
                TDCACCRU.TXN_AMT = TDB1340_AWA_1340.EVRBAL;
                TDCACCRU.TERM = TDB1340_AWA_1340.TERM;
                TDCACCRU.INSTR_MTH = TDB1340_AWA_1340.INR_MTH;
                TDCACCRU.INSTR_TERM = TDB1340_AWA_1340.INR_TERM;
                TDCACCRU.DRAW_MTH = TDB1340_AWA_1340.D_MTH;
                TDCACCRU.STL_AC = TDB1340_AWA_1340.STL_AC;
                TDCACCRU.ZC_NUM = TDB1340_AWA_1340.ZC_NUM;
                CEP.TRC(SCCGWA, TDCACCRU.ZC_NUM);
                CEP.TRC(SCCGWA, TDB1340_AWA_1340.PSW);
                if (TDB1340_AWA_1340.D_MTH == '1'
                    || TDB1340_AWA_1340.D_MTH == '4') {
                    TDCACCRU.PSW = TDB1340_AWA_1340.PSW;
                } else if (TDB1340_AWA_1340.D_MTH == '2') {
                    TDCACCRU.DRAW_INF = " ";
                } else if (TDB1340_AWA_1340.D_MTH == '3') {
                    if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                    JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                    for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                    if (TDB1340_AWA_1340.ID_TYP == null) TDB1340_AWA_1340.ID_TYP = "";
                    JIBS_tmp_int = TDB1340_AWA_1340.ID_TYP.length();
                    for (int i=0;i<5-JIBS_tmp_int;i++) TDB1340_AWA_1340.ID_TYP += " ";
                    TDCACCRU.DRAW_INF = TDB1340_AWA_1340.ID_TYP + TDCACCRU.DRAW_INF.substring(5);
                    if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                    JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                    for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                    if (TDB1340_AWA_1340.ID_NO == null) TDB1340_AWA_1340.ID_NO = "";
                    JIBS_tmp_int = TDB1340_AWA_1340.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) TDB1340_AWA_1340.ID_NO += " ";
                    TDCACCRU.DRAW_INF = TDCACCRU.DRAW_INF.substring(0, 6 - 1) + TDB1340_AWA_1340.ID_NO + TDCACCRU.DRAW_INF.substring(6 + 25 - 1);
                } else if (TDB1340_AWA_1340.D_MTH == '5') {
                    TDCACCRU.DRAW_INF = " ";
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH);
                }
                TDCACCRU.CROS_CR_FLG = TDB1340_AWA_1340.CR_FLG;
                TDCACCRU.CROS_DR_FLG = TDB1340_AWA_1340.DR_FLG;
                TDCACCRU.VAL_DT = TDB1340_AWA_1340.SDT;
                TDCACCRU.INT_SEL = TDB1340_AWA_1340.RAT_TYP;
                TDCACCRU.SPRD_PCT = TDB1340_AWA_1340.PCT_S;
                TDCACCRU.SPRD_PNT = TDB1340_AWA_1340.FLT_RAT;
                TDCACCRU.INT_RUL_CD = TDB1340_AWA_1340.RUL_CD;
                TDCACCRU.INT_RAT = TDB1340_AWA_1340.INT_RAT;
                TDCACCRU.CT_FLG = TDB1340_AWA_1340.CT_FLG;
                TDCACCRU.OPP_AC_CNO = TDB1340_AWA_1340.OP_AC;
                TDCACCRU.EXP_DT = TDB1340_AWA_1340.DDT;
                TDCACCRU.XC_UP_FLG = TDB1340_AWA_1340.UP_FLG;
                TDCACCRU.D_ID_TYP = TDB1340_AWA_1340.D_ID_TYP;
                TDCACCRU.D_ID_NO = TDB1340_AWA_1340.D_ID_NO;
                TDCACCRU.DRAW_INF = TDB1340_AWA_1340.D_INF;
                TDCACCRU.CHQ_PSW = TDB1340_AWA_1340.CHQ_PSW;
                TDCACCRU.OPP_CVV = TDB1340_AWA_1340.OP_CVV;
                TDCACCRU.FRG_IND = TDB1340_AWA_1340.FRG_IND;
                TDCACCRU.OIC_NO = TDB1340_AWA_1340.OIC_NO;
                TDCACCRU.RES_CD = TDB1340_AWA_1340.RES_CD;
                TDCACCRU.SUB_DP = TDB1340_AWA_1340.SUB_DP;
                TDCACCRU.MON_TYP = TDB1340_AWA_1340.MON_TYP;
                TDCACCRU.ACO_TYP = TDB1340_AWA_1340.ACO_TYP;
                TDCACCRU.REMARK = TDB1340_AWA_1340.REMARK;
                TDCACCRU.OVE_RAT = TDB1340_AWA_1340.OVE_RAT;
                TDCACCRU.MAIN_FLG = 'N';
                TDCACCRU.AC_NAME = TDB1340_AWA_1340.CI_NAME;
                TDCACCRU.BAL_ACTI_FLG = WS_BAL_ACTI_FLG;
                CEP.TRC(SCCGWA, TDB1340_AWA_1340.BV_NO);
                IBS.init(SCCGWA, BPCFQFBV);
                BPCFQFBV.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCFQFBV.TYPE = '0';
                BPCFQFBV.BV_CODE = TDB1340_AWA_1340.BV_CD;
                BPCFQFBV.VB_FLG = '0';
                S000_CALL_BPZFQFBV();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCFQFBV.BEG_NO);
                if (WS_I == 1 
                    && !BPCFQFBV.BEG_NO.equalsIgnoreCase(TDB1340_AWA_1340.BV_NO)) {
                    CEP.TRC(SCCGWA, "INPUT BV-NO SHOULD BE THE 1ST");
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FST_BV);
                }
                TDCACCRU.BV_NO = BPCFQFBV.BEG_NO;
                if (WS_I < TDB1340_AWA_1340.OPEN_CNT) {
                    TDCACCRU.ANY_FLG = 'S';
                } else {
                    TDCACCRU.ANY_FLG = 'E';
                }
                TDCACCRU.AC_NO = " ";
                S000_CALL_TDZACCRU();
                if (pgmRtn) return;
                WS_GACO_AC[WS_I-1].WS_GACO = TDCACCRU.ACO_AC;
                TDC1340.AC_INFO[WS_I-1].AC = TDCACCRU.AC_NO;
                TDC1340.AC_INFO[WS_I-1].BV_NO = BPCFQFBV.BEG_NO;
            }
            TDC1340.BV_CD = TDB1340_AWA_1340.BV_CD;
            TDC1340.BV_TYP = TDB1340_AWA_1340.BV_TYP;
            TDC1340.TXN_AMT = TDB1340_AWA_1340.EVRBAL;
            TDC1340.NAME = TDB1340_AWA_1340.CI_NAME;
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, TDRIREV);
            TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
            TDRIREV.KEY.ACO_AC = TDCACCRU.ACO_AC;
            TDRIREV.KEY.STR_DATE = TDCACCRU.VAL_DT;
            CEP.TRC(SCCGWA, "AAAAAAAAAAA");
            CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            TDC1340.PRDAC_CD = TDRSMST.PRDAC_CD;
            TDC1340.CCY = TDRSMST.CCY;
            TDC1340.OPEN_DATE = TDRSMST.OPEN_DATE;
            TDC1340.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
            TDC1340.VAL_DATE = TDRSMST.VAL_DATE;
            TDC1340.EXP_DATE = TDRSMST.EXP_DATE;
            TDC1340.TERM = TDRSMST.TERM;
            TDC1340.INT_RAT = TDRIREV.CON_RATE;
            TDC1340.EXP_INT = TDRSMST.EXP_INT;
            TDC1340.DRAW_MTH = TDB1340_AWA_1340.D_MTH;
            TDC1340.BR = TDRSMST.OWNER_BR;
            TDC1340.FRG_IND = TDB1340_AWA_1340.FRG_IND;
            if (TDCACCRU.AC_STSW == null) TDCACCRU.AC_STSW = "";
            JIBS_tmp_int = TDCACCRU.AC_STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCACCRU.AC_STSW += " ";
            if (TDCACCRU.AC_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                if (TDC1340.AC_STSW == null) TDC1340.AC_STSW = "";
                JIBS_tmp_int = TDC1340.AC_STSW.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDC1340.AC_STSW += " ";
                TDC1340.AC_STSW = "1" + TDC1340.AC_STSW.substring(1);
            }
        } else {
            IBS.init(SCCGWA, TDCACCRU);
            TDCACCRU.OPT = '0';
            TDCACCRU.OPSW_FLG = 'Y';
            TDCACCRU.PRDMO_CD = "MMDP";
            TDCACCRU.PROD_CD = TDB1340_AWA_1340.PROD_CD;
            TDCACCRU.BV_CD = TDB1340_AWA_1340.BV_CD;
            TDCACCRU.BV_TYP = TDB1340_AWA_1340.BV_TYP;
            TDCACCRU.PRT_FLG = TDB1340_AWA_1340.PRT_FLG;
            TDCACCRU.ID_TYP = TDB1340_AWA_1340.ID_TYP;
            TDCACCRU.ID_NO = TDB1340_AWA_1340.ID_NO;
            TDCACCRU.CI_NO = TDB1340_AWA_1340.CI_NO;
            TDCACCRU.CCY = TDB1340_AWA_1340.CCY;
            TDCACCRU.CCY_TYP = TDB1340_AWA_1340.CCY_TYP;
            TDCACCRU.TXN_AMT = TDB1340_AWA_1340.EVRBAL;
            TDCACCRU.TERM = TDB1340_AWA_1340.TERM;
            TDCACCRU.INSTR_MTH = TDB1340_AWA_1340.INR_MTH;
            TDCACCRU.INSTR_TERM = TDB1340_AWA_1340.INR_TERM;
            TDCACCRU.DRAW_MTH = TDB1340_AWA_1340.D_MTH;
            TDCACCRU.STL_AC = TDB1340_AWA_1340.STL_AC;
            CEP.TRC(SCCGWA, TDB1340_AWA_1340.PSW);
            if (TDB1340_AWA_1340.D_MTH == '1'
                || TDB1340_AWA_1340.D_MTH == '5'
                || TDB1340_AWA_1340.D_MTH == '4') {
                TDCACCRU.PSW = TDB1340_AWA_1340.PSW;
            } else if (TDB1340_AWA_1340.D_MTH == '2') {
                TDCACCRU.DRAW_INF = " ";
            } else if (TDB1340_AWA_1340.D_MTH == '3') {
                if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                if (TDB1340_AWA_1340.ID_TYP == null) TDB1340_AWA_1340.ID_TYP = "";
                JIBS_tmp_int = TDB1340_AWA_1340.ID_TYP.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDB1340_AWA_1340.ID_TYP += " ";
                TDCACCRU.DRAW_INF = TDB1340_AWA_1340.ID_TYP + TDCACCRU.DRAW_INF.substring(5);
                if (TDCACCRU.DRAW_INF == null) TDCACCRU.DRAW_INF = "";
                JIBS_tmp_int = TDCACCRU.DRAW_INF.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) TDCACCRU.DRAW_INF += " ";
                if (TDB1340_AWA_1340.ID_NO == null) TDB1340_AWA_1340.ID_NO = "";
                JIBS_tmp_int = TDB1340_AWA_1340.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) TDB1340_AWA_1340.ID_NO += " ";
                TDCACCRU.DRAW_INF = TDCACCRU.DRAW_INF.substring(0, 6 - 1) + TDB1340_AWA_1340.ID_NO + TDCACCRU.DRAW_INF.substring(6 + 25 - 1);
            } else if (TDB1340_AWA_1340.D_MTH == '4'
                || TDB1340_AWA_1340.D_MTH == '5') {
                TDCACCRU.DRAW_INF = " ";
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH);
            }
            TDCACCRU.CROS_CR_FLG = TDB1340_AWA_1340.CR_FLG;
            TDCACCRU.CROS_DR_FLG = TDB1340_AWA_1340.DR_FLG;
            TDCACCRU.VAL_DT = TDB1340_AWA_1340.SDT;
            TDCACCRU.INT_SEL = TDB1340_AWA_1340.RAT_TYP;
            TDCACCRU.SPRD_PCT = TDB1340_AWA_1340.PCT_S;
            TDCACCRU.SPRD_PNT = TDB1340_AWA_1340.FLT_RAT;
            TDCACCRU.INT_RUL_CD = TDB1340_AWA_1340.RUL_CD;
            TDCACCRU.INT_RAT = TDB1340_AWA_1340.INT_RAT;
            TDCACCRU.CT_FLG = TDB1340_AWA_1340.CT_FLG;
            TDCACCRU.OPP_AC_CNO = TDB1340_AWA_1340.OP_AC;
            TDCACCRU.EXP_DT = TDB1340_AWA_1340.DDT;
            TDCACCRU.XC_UP_FLG = TDB1340_AWA_1340.UP_FLG;
            TDCACCRU.D_ID_TYP = TDB1340_AWA_1340.D_ID_TYP;
            TDCACCRU.D_ID_NO = TDB1340_AWA_1340.D_ID_NO;
            TDCACCRU.DRAW_INF = TDB1340_AWA_1340.D_INF;
            TDCACCRU.CHQ_PSW = TDB1340_AWA_1340.CHQ_PSW;
            TDCACCRU.OPP_CVV = TDB1340_AWA_1340.OP_CVV;
            TDCACCRU.FRG_IND = TDB1340_AWA_1340.FRG_IND;
            TDCACCRU.OIC_NO = TDB1340_AWA_1340.OIC_NO;
            TDCACCRU.RES_CD = TDB1340_AWA_1340.RES_CD;
            TDCACCRU.SUB_DP = TDB1340_AWA_1340.SUB_DP;
            TDCACCRU.MON_TYP = TDB1340_AWA_1340.MON_TYP;
            TDCACCRU.ACO_TYP = TDB1340_AWA_1340.ACO_TYP;
            TDCACCRU.REMARK = TDB1340_AWA_1340.REMARK;
            TDCACCRU.OVE_RAT = TDB1340_AWA_1340.OVE_RAT;
            TDCACCRU.MAIN_FLG = 'N';
            TDCACCRU.AC_NAME = TDB1340_AWA_1340.CI_NAME;
            TDCACCRU.BAL_ACTI_FLG = WS_BAL_ACTI_FLG;
            CEP.TRC(SCCGWA, "GWA-CANCEL");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            IBS.init(SCCGWA, CICQOPAC);
            TDCACCRU.ACO_AC = " ";
            CICQOPAC.DATA.AC_TYP = "A2";
            CICQOPAC.DATA.AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            CICQOPAC.DATA.JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            CICQOPAC.DATA.DEAL_FLG = '1';
            S000_CALL_CIZQOPAC();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= TDB1340_AWA_1340.OPEN_CNT 
                && WS_I <= 200; WS_I += 1) {
                TDCACCRU.AC_NO = " ";
                CEP.TRC(SCCGWA, CICQOPAC.AGR_NO_AREA[WS_I-1].AC_NO);
                TDCACCRU.ACO_AC = CICQOPAC.AGR_NO_AREA[WS_I-1].AC_NO;
                if (WS_I < TDB1340_AWA_1340.OPEN_CNT) {
                    TDCACCRU.ANY_FLG = 'S';
                } else {
                    TDCACCRU.ANY_FLG = 'E';
                }
                S000_CALL_TDZACCRU();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDB1340_AWA_1340.CT_FLG);
        if (TDB1340_AWA_1340.CT_FLG == '0') {
            B200_CALL_CSH_DR_UNT();
            if (pgmRtn) return;
        } else if (TDB1340_AWA_1340.CT_FLG == '1') {
            B210_CALL_AI_DR_UNT();
            if (pgmRtn) return;
        } else if (TDB1340_AWA_1340.CT_FLG == '2') {
            if (TDB1340_AWA_1340.BV_TYP == '4') {
                R000_GET_CI_INFO();
                if (pgmRtn) return;
                R000_CHECK_DD_CARD_PROC();
                if (pgmRtn) return;
            }
            B230_CALL_DD_DR_UNT();
            if (pgmRtn) return;
        } else {
