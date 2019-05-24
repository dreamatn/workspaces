package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.BA.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2200 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTAGRE_RD;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    String WS_CMMT_NO = " ";
    String WS_LMT_SYS = " ";
    String WS_TEMP_CMMT_NO = " ";
    char WS_VRTU_FLG = ' ';
    String WS_DRAW_ACT = " ";
    String WS_PAY_ACT = " ";
    String K_HIS_REMARKS = "ADVANCE TRADE";
    String K_PROD_CD = "ADVANCE";
    String K_HIS_CPB_NM1 = "ADVANCE";
    String K_HIS_RMKS = "ADVANCE TRADE";
    String K_OUTPUT_FMT = "LN220";
    String K_OUTPUT_FMT_REVS = "LNT82";
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "08";
    String K_DD_AP = "DD";
    String K_INTERNAL_AP = "AI";
    String K_IB_AP = "IB";
    String K_DC_AP = "DC";
    String K_EB_AP = "EB";
    String K_GUAR_ADV = "1";
    String K_FACTR_ADV = "2";
    String K_BIL_A_ADV = "3";
    String K_LC_ADV = "4";
    String K_GUAR_0_ADV = "5";
    String K_DISC_ADV = "6";
    String K_SPBT_ADV = "7";
    String K_OTH_ADV = "9";
    char K_ADV_CONT = '1';
    char K_OUR_BANK = '0';
    char K_OTH_BANK = '1';
    String K_INP_OLD_TYPE = "RMDKLL";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSWLAA LNCSWLAA = new LNCSWLAA();
    LNCSWLAA LNCOWLAA = new LNCSWLAA();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    CICSSTC CICSSTC = new CICSSTC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCIGECR LNCIGECR = new LNCIGECR();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    BPCSCORT BPCSCORT = new BPCSCORT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    AICPQIA AICPQIA = new AICPQIA();
    CICQACRI CICQACRI = new CICQACRI();
    BACUBINF BACUBINF = new BACUBINF();
    SCCGWA SCCGWA;
    LNB2200_AWA_2200 LNB2200_AWA_2200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2200_AWA_2200>");
        LNB2200_AWA_2200 = (LNB2200_AWA_2200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B020_TRANCHE_MAIN_PROC();
            B000_OUTPUT_PROC();
        } else {
            B010_CHECK_INPUT();
            B010_GENERATE_AC_NO();
            B020_TRANCHE_MAIN_PROC();
            B000_OUTPUT_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB2200_AWA_2200.ADV_TYPE.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ADV_TYP_I_INPUT;
            if (LNB2200_AWA_2200.ADV_TYPE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.ADV_TYPE);
            S000_ERR_MSG_PROC();
        }
        if (!LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_LC_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_GUAR_0_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_DISC_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_SPBT_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_OTH_ADV)) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ADV_TYPE;
            if (LNB2200_AWA_2200.ADV_TYPE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.ADV_TYPE);
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.DRAW_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PAPER_NO_DRAW_NO;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.DOMI_BR == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_BOOK;
            WS_FLD_NO = (short) LNB2200_AWA_2200.DOMI_BR;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.CI_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_CI_NO_M_INPUT;
            if (LNB2200_AWA_2200.CI_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.CI_NO);
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.PROD_TYP.trim().length() == 0 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV) 
            && !LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_SPBT_ADV)) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT;
            if (LNB2200_AWA_2200.PROD_TYP.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.PROD_TYP);
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.START_DT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_START_DT_M_INPUT;
            WS_FLD_NO = (short) LNB2200_AWA_2200.START_DT;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_START_DT_GR_AC_DT;
            WS_FLD_NO = (short) LNB2200_AWA_2200.START_DT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNB2200_AWA_2200.MATU_DT);
        CEP.TRC(SCCGWA, LNB2200_AWA_2200.START_DT);
        if (LNB2200_AWA_2200.MATU_DT != LNB2200_AWA_2200.START_DT) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_S_DT_DIF_DUE_DT;
            WS_FLD_NO = (short) LNB2200_AWA_2200.MATU_DT;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.CCY.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY;
            if (LNB2200_AWA_2200.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.CCY);
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.DW_BK_TP != '0' 
            && LNB2200_AWA_2200.DW_BK_TP != ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.DW_BK_TP == ' ') {
            LNB2200_AWA_2200.DW_BK_TP = '0';
        }
        if (LNB2200_AWA_2200.PA_BK_TP != '0' 
            && LNB2200_AWA_2200.PA_BK_TP != ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.PA_BK_TP == ' ') {
            LNB2200_AWA_2200.PA_BK_TP = '0';
        }
        if (LNB2200_AWA_2200.DW_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNB2200_AWA_2200.DRAW_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_IB_AP)) {
                WS_DRAW_ACT = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_DD_AP)) {
                WS_DRAW_ACT = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_DC_AP)) {
                WS_DRAW_ACT = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_INTERNAL_AP)) {
                WS_DRAW_ACT = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
            if (LNB2200_AWA_2200.DRAW_ACT.trim().length() == 0) {
                LNB2200_AWA_2200.DRAW_ACT = WS_DRAW_ACT;
            } else {
                if (!WS_DRAW_ACT.equalsIgnoreCase(LNB2200_AWA_2200.DRAW_ACT)) {
                    WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (LNB2200_AWA_2200.DW_BK_TP == K_OTH_BANK) {
                IBS.init(SCCGWA, AICPQIA);
                AICPQIA.CD.AC_TYP = "3";
                AICPQIA.CD.BUSI_KND = "LNDWSUS";
                AICPQIA.BR = LNB2200_AWA_2200.DOMI_BR;
                AICPQIA.CCY = LNB2200_AWA_2200.CCY;
                AICPQIA.SIGN = 'D';
                S000_CALL_AIZPQIA();
                LNB2200_AWA_2200.DRAW_AC = AICPQIA.AC;
                LNB2200_AWA_2200.DRAW_ACT = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB2200_AWA_2200.DRAW_AC.trim().length() == 0 
            && LNB2200_AWA_2200.DW_BK_TP == K_OUR_BANK) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT;
            WS_FLD_NO = LNB2200_AWA_2200.DRAW_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.PA_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNB2200_AWA_2200.PAY_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_IB_AP)) {
                WS_PAY_ACT = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_DD_AP)) {
                WS_PAY_ACT = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_DC_AP)) {
                WS_PAY_ACT = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase(K_INTERNAL_AP)) {
                WS_PAY_ACT = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
            if (LNB2200_AWA_2200.PAY_AC_T.trim().length() == 0) {
                LNB2200_AWA_2200.PAY_AC_T = WS_PAY_ACT;
            } else {
                if (!WS_PAY_ACT.equalsIgnoreCase(LNB2200_AWA_2200.PAY_AC_T)) {
                    WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (LNB2200_AWA_2200.PA_BK_TP == K_OTH_BANK) {
                IBS.init(SCCGWA, AICPQIA);
                AICPQIA.CD.AC_TYP = "3";
                AICPQIA.CD.BUSI_KND = "LNDWSUS";
                AICPQIA.BR = LNB2200_AWA_2200.DOMI_BR;
                AICPQIA.CCY = LNB2200_AWA_2200.CCY;
                AICPQIA.SIGN = 'D';
                S000_CALL_AIZPQIA();
                LNB2200_AWA_2200.PAY_AC = AICPQIA.AC;
                LNB2200_AWA_2200.PAY_AC_T = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB2200_AWA_2200.PAY_AC.trim().length() == 0 
            && LNB2200_AWA_2200.PA_BK_TP == K_OUR_BANK) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PAY_AC_M_I;
            if (LNB2200_AWA_2200.PAY_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB2200_AWA_2200.PAY_AC);
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.PEN_IRAT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_E_IN_PEN_RT;
            WS_FLD_NO = (short) LNB2200_AWA_2200.PEN_IRAT;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.CPND_USE != 'Y' 
            && LNB2200_AWA_2200.CPND_USE != 'N') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CPND_USE_VAL_ERR;
            S000_ERR_MSG_PROC();
        }
        if (LNB2200_AWA_2200.CPND_USE == 'Y') {
            if (LNB2200_AWA_2200.OLC_PERU != 0 
                || LNB2200_AWA_2200.CPNDRATT.trim().length() > 0) {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_CPND_INF_CANNOT_I;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (LNB2200_AWA_2200.OLC_PERU == 0) {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_PEN_IRA_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_GENERATE_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGECR);
        LNCIGECR.INPUT_INFO.DRAW_NO = LNB2200_AWA_2200.DRAW_NO;
        LNCIGECR.INPUT_INFO.PAPER_NO = LNB2200_AWA_2200.CMMT_NO;
        LNCIGECR.INPUT_INFO.CTA_FROM = K_ADV_CONT;
        LNCIGECR.INPUT_INFO.BOOK_BR = LNB2200_AWA_2200.DOMI_BR;
        LNCIGECR.INPUT_INFO.CCY = LNB2200_AWA_2200.CCY;
        LNCIGECR.INPUT_INFO.CI_NO = LNB2200_AWA_2200.CI_NO;
        S000_CALL_LNZIGECR();
        LNB2200_AWA_2200.CONT_NO = LNCIGECR.OUTPOUT_INFO.CONTRACT_NO;
        WS_CMMT_NO = LNB2200_AWA_2200.CMMT_NO;
        CEP.TRC(SCCGWA, WS_CMMT_NO);
        WS_VRTU_FLG = LNCIGECR.OUTPOUT_INFO.VRTU_FLG;
        if (LNB2200_AWA_2200.DRAW_ACT.equalsIgnoreCase(K_DD_AC) 
            && LNB2200_AWA_2200.DW_BK_TP == K_OUR_BANK) {
            R000_CHECK_STS();
        }
    }
    public void R000_CHECK_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CI_NO = LNB2200_AWA_2200.CI_NO;
        LNCSSTBL.CON_NO = LNB2200_AWA_2200.CONT_NO;
        LNCSSTBL.DRAW_AC = LNB2200_AWA_2200.DRAW_AC;
        S000_CALL_LNZSSTBL();
    }
    public void B000_GET_OCONT_DATA() throws IOException,SQLException,Exception {
        if (LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV)) {
            IBS.init(SCCGWA, BACUBINF);
            BACUBINF.COMM_DATA.CNTR_NO = LNB2200_AWA_2200.CMMT_NO;
            if (LNB2200_AWA_2200.DRAW_NO.trim().length() == 0) BACUBINF.COMM_DATA.ACCT_CNT = 0;
            else BACUBINF.COMM_DATA.ACCT_CNT = Short.parseShort(LNB2200_AWA_2200.DRAW_NO);
            S000_CALL_BAZUBINF();
        } else {
            if (LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
                || LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
                || LNB2200_AWA_2200.ADV_TYPE.equalsIgnoreCase(K_SPBT_ADV)) {
                IBS.init(SCCGWA, LNRAGRE);
            }
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLAA);
        IBS.init(SCCGWA, LNCOWLAA);
        LNCSWLAA.COMM_DATA.ADV_TYPE = LNB2200_AWA_2200.ADV_TYPE;
        LNCSWLAA.COMM_DATA.DRAW_NO = LNB2200_AWA_2200.DRAW_NO;
        LNCSWLAA.COMM_DATA.CMMT_NO = WS_CMMT_NO;
        CEP.TRC(SCCGWA, LNB2200_AWA_2200.CONT_NO);
        LNCSWLAA.COMM_DATA.CONT_NO = LNB2200_AWA_2200.CONT_NO;
        LNCSWLAA.COMM_DATA.DOMI_BR = LNB2200_AWA_2200.DOMI_BR;
        LNCSWLAA.COMM_DATA.CI_NO = LNB2200_AWA_2200.CI_NO;
        LNCSWLAA.COMM_DATA.CI_CNM = LNB2200_AWA_2200.CI_CNM;
        LNCSWLAA.COMM_DATA.PROD_TYP = LNB2200_AWA_2200.PROD_TYP;
        LNCSWLAA.COMM_DATA.PROD_NM = LNB2200_AWA_2200.PROD_NM;
        LNCSWLAA.COMM_DATA.CCY = LNB2200_AWA_2200.CCY;
        LNCSWLAA.COMM_DATA.CCY_TYP = LNB2200_AWA_2200.CCY_TYP;
        LNCSWLAA.COMM_DATA.AMT = LNB2200_AWA_2200.AMT;
        LNCSWLAA.COMM_DATA.START_DT = LNB2200_AWA_2200.START_DT;
        LNCSWLAA.COMM_DATA.MATU_DT = LNB2200_AWA_2200.MATU_DT;
        LNCSWLAA.COMM_DATA.PEN_RRAT = LNB2200_AWA_2200.PEN_RRAT;
        LNCSWLAA.COMM_DATA.PEN_TYP = LNB2200_AWA_2200.PEN_TYP;
        LNCSWLAA.COMM_DATA.PEN_RATC = LNB2200_AWA_2200.PEN_RATC;
        LNCSWLAA.COMM_DATA.PEN_SPR = LNB2200_AWA_2200.PEN_SPR;
        LNCSWLAA.COMM_DATA.PEN_PCT = LNB2200_AWA_2200.PEN_PCT;
        LNCSWLAA.COMM_DATA.CPND_RTYPE = LNB2200_AWA_2200.CPND_RTY;
        LNCSWLAA.COMM_DATA.CPND_TYP = LNB2200_AWA_2200.CPND_TYP;
        LNCSWLAA.COMM_DATA.CPND_RATC = LNB2200_AWA_2200.CPNDRATC;
        LNCSWLAA.COMM_DATA.CPND_SPR = LNB2200_AWA_2200.CPND_SPR;
        LNCSWLAA.COMM_DATA.CPND_PCT = LNB2200_AWA_2200.CPND_PCT;
        LNB2200_AWA_2200.INT_MTH = 'Y';
        LNCSWLAA.COMM_DATA.INT_MTH = LNB2200_AWA_2200.INT_MTH;
        LNCSWLAA.COMM_DATA.PEN_RATT = LNB2200_AWA_2200.PEN_RATT;
        LNCSWLAA.COMM_DATA.PEN_IRAT = LNB2200_AWA_2200.PEN_IRAT;
        LNCSWLAA.COMM_DATA.CPND_USE = LNB2200_AWA_2200.CPND_USE;
        LNCSWLAA.COMM_DATA.CPND_RATT = LNB2200_AWA_2200.CPNDRATT;
        LNCSWLAA.COMM_DATA.CPND_IRATE = LNB2200_AWA_2200.OLC_PERU;
        LNCSWLAA.COMM_DATA.OCAL_PD = LNB2200_AWA_2200.OCAL_PD;
        LNCSWLAA.COMM_DATA.OCAL_PDU = LNB2200_AWA_2200.OCAL_PDU;
        LNCSWLAA.COMM_DATA.DRAW_BK_TP = LNB2200_AWA_2200.DW_BK_TP;
        LNCSWLAA.COMM_DATA.DRAW_ACT = LNB2200_AWA_2200.DRAW_ACT;
        LNCSWLAA.COMM_DATA.DRAW_AC = LNB2200_AWA_2200.DRAW_AC;
        LNCSWLAA.COMM_DATA.DR_AC_NM = LNB2200_AWA_2200.DR_AC_NM;
        LNCSWLAA.COMM_DATA.DRAW_SEQ = LNB2200_AWA_2200.DRAW_SEQ;
        LNCSWLAA.COMM_DATA.PAY_BK_TP = LNB2200_AWA_2200.PA_BK_TP;
        LNCSWLAA.COMM_DATA.PAY_AC_T = LNB2200_AWA_2200.PAY_AC_T;
        LNCSWLAA.COMM_DATA.PAY_AC = LNB2200_AWA_2200.PAY_AC;
        LNCSWLAA.COMM_DATA.PAY_ACNM = LNB2200_AWA_2200.PAY_ACNM;
        LNCSWLAA.COMM_DATA.REMARK1 = LNB2200_AWA_2200.REMARK;
        LNCSWLAA.COMM_DATA.CUSTM1 = LNB2200_AWA_2200.CUSTM1;
        LNCSWLAA.COMM_DATA.CUSTM2 = LNB2200_AWA_2200.CUSTM2;
        LNCSWLAA.COMM_DATA.CUSTM3 = LNB2200_AWA_2200.CUSTM3;
        LNCSWLAA.COMM_DATA.BAR_CD = LNB2200_AWA_2200.BAR_CD;
        LNCSWLAA.COMM_DATA.UNIT_CD = LNB2200_AWA_2200.UNIT_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSWLAA.COMM_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCOWLAA.COMM_DATA);
        S000_CALL_LNZSWLAA();
        LNCOWLAA.COMM_DATA.CONT_NO = LNCSWLAA.COMM_DATA.CONT_NO;
    }
    public void S000_CALL_BPZSCORT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-SEL-RT", BPCSCORT);
        if (BPCSCORT.OUTPUT_INFO.RC.RC_CODE != 0) {
            WS_MSGID = "" + BPCSCORT.OUTPUT_INFO.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZIGECR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GENERATE-CNTRNO", LNCIGECR);
        if (LNCIGECR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, LNCIGECR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSWLAA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-LNZSWLAA", LNCSWLAA);
        if (LNCSWLAA.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, LNCSWLAA.RC);
            SCCMSG.INFO = "CALL-LNZSWLAA ERROR";
            S000_ERR_MSG_PROC();
        }
    }
    public void B000_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSWLAA.COMM_DATA.CONT_NO;
        BPCPFHIS.DATA.AC = LNCSWLAA.COMM_DATA.CONT_NO;
        BPCPFHIS.DATA.CI_NO = LNCSWLAA.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSWLAA.COMM_DATA.START_DT;
        BPCPFHIS.DATA.TX_CCY = LNCSWLAA.COMM_DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSWLAA.COMM_DATA.AMT;
        BPCPFHIS.DATA.PROD_CD = LNCSWLAA.COMM_DATA.PROD_TYP;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARKS;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT_REVS;
        } else {
            BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT;
        }
        BPCPFHIS.DATA.FMT_LEN = 1391;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCSWLAA.COMM_DATA);
        S000_CALL_BPZPFHIS();
    }
    public void B000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("132200")) {
            SCCFMT.FMTID = K_OUTPUT_FMT;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT_REVS;
        }
        SCCFMT.DATA_PTR = LNCOWLAA.COMM_DATA;
        SCCFMT.DATA_LEN = 1391;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "NEXT_SEQ DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_AGRE_CONT() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.PAPER_NO "
            + "AND PAPER_NO = :LNRAGRE.DRAW_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "PAPER_NO DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
