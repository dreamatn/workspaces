package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5820 {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC = " ";
    String WS_CUT_AC = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    TDCKHCR TDCKHCR = new TDCKHCR();
    CICACCU CICACCU = new CICACCU();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    GDRHIS GDRHIS = new GDRHIS();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    CICCUST CICCUST = new CICCUST();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACRI CICQACRI = new CICQACRI();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    GDRSTAC GDRSTAC = new GDRSTAC();
    CICSOEC CICSOEC = new CICSOEC();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDB5820_AWA_5820 GDB5820_AWA_5820;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDOT5820 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5820_AWA_5820>");
        GDB5820_AWA_5820 = (GDB5820_AWA_5820) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUCI_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CU_CCY);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUID_TYP);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUID_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CU_TERM);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CT_FLG);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUAMT_S);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUAC_NM);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUT_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.RES_AC);
        B010_CHECK_CI_NO();
        if (pgmRtn) return;
        B020_CHECK_INPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_CI_NO() throws IOException,SQLException,Exception {
        if (GDB5820_AWA_5820.CUT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = GDB5820_AWA_5820.CUT_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            WS_INT_AC = CICQACRI.O_DATA.O_AGR_NO;
        }
        CEP.TRC(SCCGWA, "CHECK CI_NO");
        CEP.TRC(SCCGWA, "LUO");
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUT_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.AC_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CT_FLG);
        if (GDB5820_AWA_5820.CT_FLG == '3' 
            && (GDB5820_AWA_5820.OPP_AC.trim().length() > 0) 
            && (GDB5820_AWA_5820.AC_NO.trim().length() > 0)) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = GDB5820_AWA_5820.AC_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_NO1 = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = GDB5820_AWA_5820.OPP_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_NO2 = CICACCU.DATA.CI_NO;
            CEP.TRC(SCCGWA, WS_CI_NO1);
            CEP.TRC(SCCGWA, WS_CI_NO2);
            if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2)) {
                IBS.init(SCCGWA, CICSOEC);
                CICSOEC.DATA.CI_NO = WS_CI_NO2;
                CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                S000_CALL_CIZSOEC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                CEP.TRC(SCCGWA, WS_CI_NO1);
                if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_CI_NO1)) {
                    WS_MSGID = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (GDB5820_AWA_5820.CUT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDRMST);
            CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUT_AC);
            DDRMST.KEY.CUS_AC = GDB5820_AWA_5820.CUT_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = GDB5820_AWA_5820.CUT_AC;
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if ((CICQACRL.RC.RC_CODE == 0 
                    && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("01") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")))) {
                WS_CUT_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
                WS_CUT_AC = GDB5820_AWA_5820.CUT_AC;
            }
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_CUT_AC;
            DDRCCY.CCY = GDB5820_AWA_5820.CU_CCY;
            DDRCCY.CCY_TYPE = GDB5820_AWA_5820.CUC_TYP;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (DDRMST.AC_TYPE == 'N') {
                WS_MSGID = GDCMSG_ERROR_MSG.GD_INTAC_NO_GD_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
            if (DDRMST.AC_STS == 'D') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
            if (DDRMST.AC_STS == 'V') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
            if (DDRMST.AC_STS == 'O') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
            if (DDRMST.AC_STS == 'M') {
                WS_MSGID = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
        }
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CT_FLG);
        if (GDB5820_AWA_5820.CT_FLG == '3') {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDB5820_AWA_5820.OPP_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'M') {
                WS_MSGID = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
                CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
            }
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB5820_AWA_5820.CUPRD_CD.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.CU_CCY.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.CU_TERM.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_TERM_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.CT_FLG == ' ') {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_INVALID_CT_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.CUAMT_S == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.CUAC_NM.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_ACNM_MST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.INT_SEL == ' ') {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_CUL_TYP_M_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDB5820_AWA_5820.INT_SEL == '1') {
            if (GDB5820_AWA_5820.SPRD_PCT == 0) {
                WS_MSGID = GDCMSG_ERROR_MSG.GD_CUPCT_S_M_IPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDB5820_AWA_5820.INT_SEL == '2') {
            if (GDB5820_AWA_5820.SPRD_PNT == 0) {
                WS_MSGID = GDCMSG_ERROR_MSG.GD_CUFT_RAT_M_IPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDB5820_AWA_5820.CUT_AC.trim().length() > 0) {
            if (GDB5820_AWA_5820.AC_NO.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_INT_AC);
                IBS.init(SCCGWA, DDCIMCCY);
                DDCIMCCY.DATA[1-1].AC = WS_INT_AC;
                DDCIMCCY.DATA[1-1].CCY = GDB5820_AWA_5820.CU_CCY;
                DDCIMCCY.DATA[1-1].CCY_TYPE = GDB5820_AWA_5820.CUC_TYP;
                S000_CALL_DDZIMCCY();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
                if (!GDB5820_AWA_5820.CU_CCY.equalsIgnoreCase(DDCIMCCY.DATA[1-1].CCY)) {
                    WS_MSGID = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = GDB5820_AWA_5820.CUPRD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUPRD_CD);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        IBS.init(SCCGWA, TDCKHCR);
        TDCKHCR.CHNL_FLG = 'Y';
        B030_GET_LIST();
        if (pgmRtn) return;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        B040_GET_FC_CD();
        if (pgmRtn) return;
        TDCKHCR.PROD_CD = GDB5820_AWA_5820.CUPRD_CD;
        TDCKHCR.BV_TYP = '7';
        TDCKHCR.OPP_AC = GDB5820_AWA_5820.OPP_AC;
        TDCKHCR.ID_TYP = GDB5820_AWA_5820.CUID_TYP;
        TDCKHCR.ID_NO = GDB5820_AWA_5820.CUID_NO;
        TDCKHCR.CI_NO = GDB5820_AWA_5820.CUCI_NO;
        if (GDB5820_AWA_5820.CUAC_NM.trim().length() > 0) {
            TDCKHCR.AC_NAME = GDB5820_AWA_5820.CUAC_NM;
        }
        TDCKHCR.CCY = GDB5820_AWA_5820.CU_CCY;
        TDCKHCR.CCY_TYP = GDB5820_AWA_5820.CUC_TYP;
        TDCKHCR.TXN_AMT = GDB5820_AWA_5820.CUAMT_S;
        TDCKHCR.TERM = GDB5820_AWA_5820.CU_TERM;
        TDCKHCR.INSTR_MTH = GDB5820_AWA_5820.INSTR_MT;
        TDCKHCR.OPP_PSW = GDB5820_AWA_5820.PSW;
        TDCKHCR.CROS_DR_FLG = GDB5820_AWA_5820.CRDR_FLG;
        TDCKHCR.INT_SEL = GDB5820_AWA_5820.INT_SEL;
        TDCKHCR.SPRD_PCT = GDB5820_AWA_5820.SPRD_PCT;
        TDCKHCR.SPRD_PNT = GDB5820_AWA_5820.SPRD_PNT;
        TDCKHCR.INT_RAT = GDB5820_AWA_5820.INT_RAT;
        TDCKHCR.CT_FLG = GDB5820_AWA_5820.CT_FLG;
        TDCKHCR.OPP_AC = GDB5820_AWA_5820.OPP_AC;
        TDCKHCR.OPP_NAME = GDB5820_AWA_5820.OPP_NAME;
        TDCKHCR.OPP_REV_NO = GDB5820_AWA_5820.OPP_REV;
        TDCKHCR.CHQ_TYPE = GDB5820_AWA_5820.CHQ_TYPE;
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CHQ_TYPE);
        TDCKHCR.CHQ_NO = GDB5820_AWA_5820.CHQ_NO;
        TDCKHCR.CHQ_DATE = GDB5820_AWA_5820.CHQ_DATE;
        TDCKHCR.VAL_DT = GDB5820_AWA_5820.VAL_DT;
        TDCKHCR.EXP_DT = GDB5820_AWA_5820.EXP_DT;
        TDCKHCR.OPP_ID_NO = GDB5820_AWA_5820.OPP_ID_T;
        TDCKHCR.OPP_ID_TYP = GDB5820_AWA_5820.OPP_ID_N;
        TDCKHCR.INT_AC = GDB5820_AWA_5820.CUT_AC;
        TDCKHCR.OPT = '2';
        TDCKHCR.REMARK = GDB5820_AWA_5820.CURMK_60;
        if (GDB5820_AWA_5820.TZZD_MMO.trim().length() > 0) {
            TDCKHCR.MMO = GDB5820_AWA_5820.TZZD_MMO;
        }
        TDCKHCR.AC = GDB5820_AWA_5820.AC_NO;
        TDCKHCR.OIC_NO = GDB5820_AWA_5820.OIC_NO;
        TDCKHCR.RES_CD = GDB5820_AWA_5820.RES_CENT;
        TDCKHCR.SUB_DP = GDB5820_AWA_5820.SUB_DP;
        CEP.TRC(SCCGWA, "AYAYAYAYAYAYAYAYAYAY");
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUID_TYP);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUID_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUCI_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUAC_NM);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CU_CCY);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUC_TYP);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.FC_CD);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUAMT_S);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CU_TERM);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.INSTR_MT);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.PSW);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CRDR_FLG);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.INT_SEL);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.SPRD_PCT);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.SPRD_PNT);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.INT_RAT);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_NAME);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_REV);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CHQ_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CHQ_DATE);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.VAL_DT);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.AC_NO);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUT_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_ID_T);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.OPP_ID_N);
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        TDCKHCR.BV_TYP = '0';
        S000_CALL_TDZKHCR();
        if (pgmRtn) return;
        GDB5820_AWA_5820.GACO_AC = TDCKHCR.GACO_AC;
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.GACO_AC);
        R000_WRITE_GDTSTAC();
        if (pgmRtn) return;
        R000_WRITE_GDTHIS();
        if (pgmRtn) return;
    }
    public void B040_GET_FC_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        if (GDB5820_AWA_5820.FC_CD != 0) {
            TDCKHCR.FC_CD = GDB5820_AWA_5820.FC_CD;
        } else {
            if (GDB5820_AWA_5820.CUCI_NO.trim().length() > 0) {
                CICCUST.DATA.CI_NO = GDB5820_AWA_5820.CUCI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
                if (CICCUST.O_DATA.O_CI_TYP != '1' 
                    && !GDB5820_AWA_5820.CU_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
                    TDCKHCR.FC_CD = 4200;
                }
            } else {
                if (GDB5820_AWA_5820.AC_NO.trim().length() > 0) {
                    if (CICACCU.DATA.CI_TYP != '1' 
                        && !GDB5820_AWA_5820.CU_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
                        TDCKHCR.FC_CD = 4200;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, TDCKHCR.FC_CD);
    }
    public void R000_WRITE_GDTHIS() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        CEP.TRC(SCCGWA, TDCKHCR.AC_SEQ);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        if (GDB5820_AWA_5820.AC_NO.trim().length() > 0) {
            GDRHIS.AC = GDB5820_AWA_5820.AC_NO;
            GDRHIS.AC_SEQ = TDCKHCR.AC_SEQ;
        } else {
            GDRHIS.AC = TDCKHCR.AC;
        }
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.FUNC = '1';
        GDRHIS.TR_AMT = GDB5820_AWA_5820.CUAMT_S;
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.CAN_FLG = 'N';
        if (GDB5820_AWA_5820.CUT_AC.trim().length() > 0) {
            GDRHIS.TR_AC = GDB5820_AWA_5820.CUT_AC;
        } else {
            GDRHIS.TR_AC = GDB5820_AWA_5820.OPP_AC;
        }
        CEP.TRC(SCCGWA, GDRHIS.TR_AC);
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void R000_WRITE_GDTSTAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.RES_AC);
        CEP.TRC(SCCGWA, GDB5820_AWA_5820.CUT_AC);
        CEP.TRC(SCCGWA, TDCKHCR.AC_SEQ);
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        IBS.init(SCCGWA, CICQACRI);
        GDCRSTAC.FUNC = 'A';
        GDRSTAC.KEY.AC = GDB5820_AWA_5820.AC_NO;
        GDRSTAC.KEY.AC_SEQ = TDCKHCR.AC_SEQ;
        if (GDB5820_AWA_5820.RES_AC.trim().length() > 0) {
            CICQACRI.DATA.AGR_NO = GDB5820_AWA_5820.RES_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (GDB5820_AWA_5820.CT_FLG == '3') {
                GDRSTAC.ST_AC = CICQACRI.O_DATA.O_AGR_NO;
            } else {
                GDRSTAC.ST_AC = GDB5820_AWA_5820.CUT_AC;
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        }
        if (GDB5820_AWA_5820.CUT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = GDB5820_AWA_5820.CUT_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            GDRSTAC.INT_AC = CICQACRI.O_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        }
        GDRSTAC.RELAT_STS = 'N';
        GDRSTAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRSTAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDCRHIS.RC.RC_CODE);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    if (CICCUST.RC.RC_CODE != 0) {
        WS_MSGID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR);
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
