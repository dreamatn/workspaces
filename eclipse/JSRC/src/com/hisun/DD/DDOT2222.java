package com.hisun.DD;

import com.hisun.LN.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.BA.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT2222 {
    String JIBS_tmp_str[] = new String[10];
    brParm DDTADIF_BR = new brParm();
    DBParm DDTADIF_RD;
    int JIBS_tmp_int;
    DBParm DDTADMN_RD;
    String WS_ERR_MSG = " ";
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
    CICQACAC CICQACAC = new CICQACAC();
    DDRADIF DDRADIF = new DDRADIF();
    DDRADMN DDRADMN = new DDRADMN();
    SCCGWA SCCGWA;
    DDB2222_AWA_2222 DDB2222_AWA_2222;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT2222 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB2222_AWA_2222>");
        DDB2222_AWA_2222 = (DDB2222_AWA_2222) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CCY_TYP);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.ADV_TYPE);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DOMI_BR);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CI_NO);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CI_CNM);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PROD_TYP);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CCY);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.AMT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DW_BK_TP);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DRAW_ACT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DRAW_AC);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DR_AC_NM);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PA_BK_TP);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PAY_AC_T);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PAY_AC);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PAY_ACNM);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PEN_RATT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PEN_IRAT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CPND_USE);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.OLC_PERU);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.START_DT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.MATU_DT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CMMT_NO);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.DRAW_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "YESAAAA");
            B020_TRANCHE_MAIN_PROC();
            B000_OUTPUT_PROC();
        } else {
            CEP.TRC(SCCGWA, "NOT");
            B010_CHECK_INPUT();
            B010_GENERATE_AC_NO();
            B020_TRANCHE_MAIN_PROC();
            B030_UPDATE_PROC();
            B000_OUTPUT_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB2222_AWA_2222.ADV_TYPE.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ADV_TYP_I_INPUT;
            if (DDB2222_AWA_2222.ADV_TYPE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB2222_AWA_2222.ADV_TYPE);
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.DRAW_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PAPER_NO_DRAW_NO;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.DOMI_BR == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_BOOK;
            WS_FLD_NO = (short) DDB2222_AWA_2222.DOMI_BR;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.CI_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_CI_NO_M_INPUT;
            if (DDB2222_AWA_2222.CI_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB2222_AWA_2222.CI_NO);
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.PROD_TYP.trim().length() == 0 
            && !DDB2222_AWA_2222.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
            && !DDB2222_AWA_2222.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
            && !DDB2222_AWA_2222.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV) 
            && !DDB2222_AWA_2222.ADV_TYPE.equalsIgnoreCase(K_SPBT_ADV)) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT;
            if (DDB2222_AWA_2222.PROD_TYP.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB2222_AWA_2222.PROD_TYP);
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.START_DT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_START_DT_M_INPUT;
            WS_FLD_NO = (short) DDB2222_AWA_2222.START_DT;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.MATU_DT != DDB2222_AWA_2222.START_DT) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_S_DT_DIF_DUE_DT;
            WS_FLD_NO = (short) DDB2222_AWA_2222.MATU_DT;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.CCY.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY;
            if (DDB2222_AWA_2222.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB2222_AWA_2222.CCY);
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.DW_BK_TP != '0' 
            && DDB2222_AWA_2222.DW_BK_TP != ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.DW_BK_TP == ' ') {
            DDB2222_AWA_2222.DW_BK_TP = '0';
        }
        if (DDB2222_AWA_2222.PA_BK_TP != '0' 
            && DDB2222_AWA_2222.PA_BK_TP != ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.PA_BK_TP == ' ') {
            DDB2222_AWA_2222.PA_BK_TP = '0';
        }
        if (DDB2222_AWA_2222.DW_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = DDB2222_AWA_2222.DRAW_AC;
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
            if (DDB2222_AWA_2222.DRAW_ACT.trim().length() == 0) {
                DDB2222_AWA_2222.DRAW_ACT = WS_DRAW_ACT;
            } else {
                if (!WS_DRAW_ACT.equalsIgnoreCase(DDB2222_AWA_2222.DRAW_ACT)) {
                    WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (DDB2222_AWA_2222.DW_BK_TP == K_OTH_BANK) {
                IBS.init(SCCGWA, AICPQIA);
                AICPQIA.CD.AC_TYP = "3";
                AICPQIA.CD.BUSI_KND = "LNDWSUS";
                AICPQIA.BR = DDB2222_AWA_2222.DOMI_BR;
                AICPQIA.CCY = DDB2222_AWA_2222.CCY;
                AICPQIA.SIGN = 'D';
                S000_CALL_AIZPQIA();
                DDB2222_AWA_2222.DRAW_AC = AICPQIA.AC;
                DDB2222_AWA_2222.DRAW_ACT = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB2222_AWA_2222.DRAW_AC.trim().length() == 0 
            && DDB2222_AWA_2222.DW_BK_TP == K_OUR_BANK) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT;
            WS_FLD_NO = DDB2222_AWA_2222.DRAW_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.PA_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = DDB2222_AWA_2222.PAY_AC;
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
            if (DDB2222_AWA_2222.PAY_AC_T.trim().length() == 0) {
                DDB2222_AWA_2222.PAY_AC_T = WS_PAY_ACT;
            } else {
                if (!WS_PAY_ACT.equalsIgnoreCase(DDB2222_AWA_2222.PAY_AC_T)) {
                    WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (DDB2222_AWA_2222.PA_BK_TP == K_OTH_BANK) {
                IBS.init(SCCGWA, AICPQIA);
                AICPQIA.CD.AC_TYP = "3";
                AICPQIA.CD.BUSI_KND = "LNDWSUS";
                AICPQIA.BR = DDB2222_AWA_2222.DOMI_BR;
                AICPQIA.CCY = DDB2222_AWA_2222.CCY;
                AICPQIA.SIGN = 'D';
                S000_CALL_AIZPQIA();
                DDB2222_AWA_2222.PAY_AC = AICPQIA.AC;
                DDB2222_AWA_2222.PAY_AC_T = K_INTERNAL;
            } else {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB2222_AWA_2222.PAY_AC.trim().length() == 0 
            && DDB2222_AWA_2222.PA_BK_TP == K_OUR_BANK) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_PAY_AC_M_I;
            if (DDB2222_AWA_2222.PAY_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB2222_AWA_2222.PAY_AC);
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.PEN_IRAT == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_E_IN_PEN_RT;
            WS_FLD_NO = (short) DDB2222_AWA_2222.PEN_IRAT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.PEN_IRAT);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CPND_USE);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CPND_USE);
        if (DDB2222_AWA_2222.CPND_USE != 'Y' 
            && DDB2222_AWA_2222.CPND_USE != 'N') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CPND_USE_VAL_ERR;
            CEP.TRC(SCCGWA, "AAA");
            S000_ERR_MSG_PROC();
        }
        if (DDB2222_AWA_2222.CPND_USE == 'Y') {
            if (DDB2222_AWA_2222.OLC_PERU != 0 
                || DDB2222_AWA_2222.CPNDRATT.trim().length() > 0) {
                CEP.TRC(SCCGWA, "BBB");
                WS_MSGID = LNCMSG_ERROR_MSG.LN_CPND_INF_CANNOT_I;
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            if (DDB2222_AWA_2222.OLC_PERU == 0) {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_PEN_IRA_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_GENERATE_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGECR);
        LNCIGECR.INPUT_INFO.DRAW_NO = DDB2222_AWA_2222.DRAW_NO;
        LNCIGECR.INPUT_INFO.PAPER_NO = DDB2222_AWA_2222.CMMT_NO;
        LNCIGECR.INPUT_INFO.CTA_FROM = K_ADV_CONT;
        LNCIGECR.INPUT_INFO.BOOK_BR = DDB2222_AWA_2222.DOMI_BR;
        LNCIGECR.INPUT_INFO.CCY = DDB2222_AWA_2222.CCY;
        LNCIGECR.INPUT_INFO.CI_NO = DDB2222_AWA_2222.CI_NO;
        S000_CALL_LNZIGECR();
        DDB2222_AWA_2222.CONT_NO = LNCIGECR.OUTPOUT_INFO.CONTRACT_NO;
        WS_CMMT_NO = DDB2222_AWA_2222.CMMT_NO;
        CEP.TRC(SCCGWA, WS_CMMT_NO);
        WS_VRTU_FLG = LNCIGECR.OUTPOUT_INFO.VRTU_FLG;
        if (DDB2222_AWA_2222.DRAW_ACT.equalsIgnoreCase(K_DD_AC) 
            && DDB2222_AWA_2222.DW_BK_TP == K_OUR_BANK) {
            R000_CHECK_STS();
        }
    }
    public void R000_CHECK_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CI_NO = DDB2222_AWA_2222.CI_NO;
        LNCSSTBL.CON_NO = DDB2222_AWA_2222.CONT_NO;
        LNCSSTBL.DRAW_AC = DDB2222_AWA_2222.DRAW_AC;
        S000_CALL_LNZSSTBL();
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLAA);
        IBS.init(SCCGWA, LNCOWLAA);
        LNCSWLAA.COMM_DATA.ADV_TYPE = "8";
        LNCSWLAA.COMM_DATA.DRAW_NO = DDB2222_AWA_2222.DRAW_NO;
        LNCSWLAA.COMM_DATA.CMMT_NO = WS_CMMT_NO;
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CONT_NO);
        LNCSWLAA.COMM_DATA.CONT_NO = DDB2222_AWA_2222.CONT_NO;
        LNCSWLAA.COMM_DATA.DOMI_BR = DDB2222_AWA_2222.DOMI_BR;
        LNCSWLAA.COMM_DATA.CI_NO = DDB2222_AWA_2222.CI_NO;
        LNCSWLAA.COMM_DATA.CI_CNM = DDB2222_AWA_2222.CI_CNM;
        LNCSWLAA.COMM_DATA.PROD_TYP = DDB2222_AWA_2222.PROD_TYP;
        LNCSWLAA.COMM_DATA.PROD_NM = DDB2222_AWA_2222.PROD_NM;
        LNCSWLAA.COMM_DATA.CCY = DDB2222_AWA_2222.CCY;
        LNCSWLAA.COMM_DATA.AMT = DDB2222_AWA_2222.AMT;
        LNCSWLAA.COMM_DATA.START_DT = DDB2222_AWA_2222.START_DT;
        LNCSWLAA.COMM_DATA.MATU_DT = DDB2222_AWA_2222.MATU_DT;
        LNCSWLAA.COMM_DATA.PEN_RRAT = DDB2222_AWA_2222.PEN_RRAT;
        LNCSWLAA.COMM_DATA.PEN_TYP = DDB2222_AWA_2222.PEN_TYP;
        LNCSWLAA.COMM_DATA.PEN_RATC = DDB2222_AWA_2222.PEN_RATC;
        LNCSWLAA.COMM_DATA.PEN_SPR = DDB2222_AWA_2222.PEN_SPR;
        LNCSWLAA.COMM_DATA.PEN_PCT = DDB2222_AWA_2222.PEN_PCT;
        LNCSWLAA.COMM_DATA.CPND_RTYPE = DDB2222_AWA_2222.CPND_RTY;
        LNCSWLAA.COMM_DATA.CPND_TYP = DDB2222_AWA_2222.CPND_TYP;
        LNCSWLAA.COMM_DATA.CPND_RATC = DDB2222_AWA_2222.CPNDRATC;
        LNCSWLAA.COMM_DATA.CPND_SPR = DDB2222_AWA_2222.CPND_SPR;
        LNCSWLAA.COMM_DATA.CPND_PCT = DDB2222_AWA_2222.CPND_PCT;
        LNCSWLAA.COMM_DATA.INT_MTH = DDB2222_AWA_2222.INT_MTH;
        LNCSWLAA.COMM_DATA.PEN_RATT = DDB2222_AWA_2222.PEN_RATT;
        LNCSWLAA.COMM_DATA.PEN_IRAT = DDB2222_AWA_2222.PEN_IRAT;
        LNCSWLAA.COMM_DATA.CPND_USE = DDB2222_AWA_2222.CPND_USE;
        LNCSWLAA.COMM_DATA.CPND_RATT = DDB2222_AWA_2222.CPNDRATT;
        LNCSWLAA.COMM_DATA.CPND_IRATE = DDB2222_AWA_2222.OLC_PERU;
        LNCSWLAA.COMM_DATA.OCAL_PD = DDB2222_AWA_2222.OCAL_PD;
        LNCSWLAA.COMM_DATA.OCAL_PDU = DDB2222_AWA_2222.OCAL_PDU;
        LNCSWLAA.COMM_DATA.DRAW_BK_TP = DDB2222_AWA_2222.DW_BK_TP;
        LNCSWLAA.COMM_DATA.DRAW_ACT = DDB2222_AWA_2222.DRAW_ACT;
        LNCSWLAA.COMM_DATA.DRAW_AC = DDB2222_AWA_2222.DRAW_AC;
        LNCSWLAA.COMM_DATA.DR_AC_NM = DDB2222_AWA_2222.DR_AC_NM;
        LNCSWLAA.COMM_DATA.DRAW_SEQ = DDB2222_AWA_2222.DRAW_SEQ;
        LNCSWLAA.COMM_DATA.PAY_BK_TP = DDB2222_AWA_2222.PA_BK_TP;
        LNCSWLAA.COMM_DATA.PAY_AC_T = DDB2222_AWA_2222.PAY_AC_T;
        LNCSWLAA.COMM_DATA.PAY_AC = DDB2222_AWA_2222.PAY_AC;
        LNCSWLAA.COMM_DATA.PAY_ACNM = DDB2222_AWA_2222.PAY_ACNM;
        LNCSWLAA.COMM_DATA.REMARK1 = DDB2222_AWA_2222.REMARK;
        LNCSWLAA.COMM_DATA.CUSTM1 = DDB2222_AWA_2222.CUSTM1;
        LNCSWLAA.COMM_DATA.CUSTM2 = DDB2222_AWA_2222.CUSTM2;
        LNCSWLAA.COMM_DATA.CUSTM3 = DDB2222_AWA_2222.CUSTM3;
        LNCSWLAA.COMM_DATA.BAR_CD = DDB2222_AWA_2222.BAR_CD;
        LNCSWLAA.COMM_DATA.UNIT_CD = DDB2222_AWA_2222.UNIT_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSWLAA.COMM_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCOWLAA.COMM_DATA);
        S000_CALL_LNZSWLAA();
        LNCOWLAA.COMM_DATA.CONT_NO = LNCSWLAA.COMM_DATA.CONT_NO;
        CEP.TRC(SCCGWA, LNCOWLAA.COMM_DATA.CONT_NO);
    }
    public void B030_UPDATE_PROC() throws IOException,SQLException,Exception {
        CICQACAC.DATA.AGR_NO = DDB2222_AWA_2222.DRAW_AC;
        CICQACAC.DATA.CCY_ACAC = DDB2222_AWA_2222.CCY;
        CICQACAC.DATA.CR_FLG = DDB2222_AWA_2222.CCY_TYP;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, DDB2222_AWA_2222.CCY_TYP);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRADIF.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (DDB2222_AWA_2222.DRAW_NO.trim().length() == 0) DDRADIF.KEY.OD_STRDATE = 0;
        else DDRADIF.KEY.OD_STRDATE = Integer.parseInt(DDB2222_AWA_2222.DRAW_NO);
        CEP.TRC(SCCGWA, DDRADIF.KEY.OD_STRDATE);
        DDRADIF.KEY.ADP_TYPE = DDB2222_AWA_2222.ADV_TYPE;
        DDRADIF.OD_EXPDATE = DDB2222_AWA_2222.MATU_DT;
        T000_READUPD_DDTADIF();
        DDRADIF.LN_AC = LNCSWLAA.COMM_DATA.CONT_NO;
        DDRADIF.OD_STS = '4';
        T000_REWRITE_DDTADIF();
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_BR.rp = new DBParm();
        DDTADIF_BR.rp.TableName = "DDTADIF";
        DDTADIF_BR.rp.where = "ADP_TYPE = '08' "
            + "OR ADP_TYPE = '10' "
            + "AND ADIF_ACO_AC = '23'";
        DDTADIF_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRADIF, this, DDTADIF_BR);
    }
    public void T000_READUPD_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        DDTADIF_RD.where = "ACO_AC = :DDRADIF.KEY.ACO_AC "
            + "AND ADP_TYPE = :DDRADIF.KEY.ADP_TYPE "
            + "AND OD_STRDATE = :DDRADIF.KEY.OD_STRDATE";
        DDTADIF_RD.upd = true;
        IBS.READ(SCCGWA, DDRADIF, this, DDTADIF_RD);
        CEP.TRC(SCCGWA, DDRADIF.OD_STS);
    }
    public void T000_REWRITE_DDTADIF() throws IOException,SQLException,Exception {
        DDRADIF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADIF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        IBS.REWRITE(SCCGWA, DDRADIF, DDTADIF_RD);
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
    public void B000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
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
    public void T000_READUPD_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void T000_REWRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.REWRITE(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void T000_COMMIT() throws IOException,SQLException,Exception {
        IBS.COMMIT(SCCGWA);
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
