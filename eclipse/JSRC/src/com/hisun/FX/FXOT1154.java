package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.VT.WFCCAPV;
import com.hisun.WF.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1154 {
    int JIBS_tmp_int;
    BigDecimal bigD;
    DBParm FXTTXHIS_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_FX_SUSP = "FXD";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    char K_CASH_FLG_CNY = '0';
    char K_CASH_FLG_REMIT = '1';
    String K_DD_TX_MMO = "FEX";
    String K_HIS_TX_MMO = "RCT";
    String K_HIST_TX_MMO = "OCT";
    String K_BP_PRDMO_CD = "FX";
    String K_AI_POST_NARR_NOTE = "EX MARKET -";
    String K_AI_POST_EVENT_MAB = " MAB";
    String K_AI_POST_EVENT_MAS = " MAS";
    String K_FMT_FMTID = "FX154";
    char K_STATUS_MATURED = 'M';
    char K_STATUS_CANCEL = 'R';
    char K_STATUS_ERROR = 'E';
    String K_HIS_REMARK = "FOREIGN EXCHANGE SPOT CONTRACT SETTLEMENT";
    String WS_ERR_MSG = " ";
    String WS_DDAC_ERR_MSG = " ";
    long WS_JRN_NO = 0;
    short WS_FLD_NO = 0;
    short WS_I = 0;
    String WS_PROFIT_CCY = " ";
    double WS_PROFIT_AMT = 0;
    String WS_DR_CCY = " ";
    double WS_DR_AMT = 0;
    String WS_DDAC_DB = " ";
    String WS_DR_NOSAC = " ";
    String WS_CR_CCY = " ";
    double WS_CR_AMT = 0;
    double WS_RMB_AMT = 0;
    String WS_DDAC_CR = " ";
    String WS_CR_NOSAC = " ";
    int WS_VAL_DT = 0;
    String WS_EVENT_CODE = " ";
    long WS_DIARY_BATNO = 0;
    int WS_GLMST = 0;
    String WS_CCY = " ";
    double WS_AMT = 0;
    String WS_DR_MIB = " ";
    String WS_CR_MIB = " ";
    String WS_FLG = " ";
    String WS_SELL_CCY_CNTYCD = " ";
    String WS_BUY_CCY_CNTYCD = " ";
    int WS_TEMP_DT = 0;
    int WS_SELT_DATE = 0;
    FXOT1154_WS_OUT_DATA WS_OUT_DATA = new FXOT1154_WS_OUT_DATA();
    char WS_DDAC_FLAG = ' ';
    char WS_AUTO_TAKE_UP_FLAG = ' ';
    char WS_DRCR_FLAG = ' ';
    FXCRDRFX FXCRDRFX = new FXCRDRFX();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    WFCCAPV WFCCAPV = new WFCCAPV();
    BPCUMPRT BPCUMPRT = new BPCUMPRT();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DCCSDUCT DCCSDUCT = new DCCSDUCT();
    BPCCNGL BPCCNGL = new BPCCNGL();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXRTXHIS FXRTXHIS = new FXRTXHIS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    BPCFX BPCFX = new BPCFX();
    DCCURHLD DCCURHLD = new DCCURHLD();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    int WS_FXHIS_TRN_DT = 0;
    long WS_FXHIS_JRN_NO = 0;
    SCCGWA SCCGWA;
    FXB1154_AWA_1154 FXB1154_AWA_1154;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1154 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1154_AWA_1154>");
        FXB1154_AWA_1154 = (FXB1154_AWA_1154) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_DDAC_FLAG = 'N';
        WS_AUTO_TAKE_UP_FLAG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_01_READUP_CONT();
        B020_04_DR_CR_AC();
        B020_07_UPDATE_CONT();
        C010_7_GEN_EVENT();
        C010_8_GEN_FXHIS();
        B030_SEND_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*****************************");
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.CTA_NO);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.JRN_NO);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.TRN_DT);
        if (FXB1154_AWA_1154.CTA_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CONTRACT_NO_M_INPUT);
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_01_READUP_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        IBS.init(SCCGWA, FXCRDRFX);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.TRN_DT);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.JRN_NO);
        FXRDIRFX.KEY.JRN_NO = FXB1154_AWA_1154.JRN_NO;
        FXRDIRFX.KEY.TRN_DT = FXB1154_AWA_1154.TRN_DT;
        FXCRDRFX.FUNC = 'R';
        FXCRDRFX.REC_PTR = FXRDIRFX;
        FXCRDRFX.REC_LEN = 2776;
        S000_CALL_FXZRDRFX();
        WS_JRN_NO = FXRDIRFX.KEY.JRN_NO;
        RO00_CHECK_CONT_STATUS();
    }
    public void B020_04_DR_CR_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = FXRDIRFX.HLD_NO;
        DCCURHLD.DATA.AC = FXRDIRFX.DD_AC1;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.CCY = FXRDIRFX.BUY_CCY;
        if (FXRDIRFX.B_CS_FLG == '0') {
            DCCURHLD.DATA.CCY_TYP = '1';
        }
        if (FXRDIRFX.B_CS_FLG == '1') {
            DCCURHLD.DATA.CCY_TYP = '2';
        }
        DCCURHLD.DATA.RAMT = FXRDIRFX.BUY_AMT;
        DCCURHLD.DATA.HLD_TYP = '2';
        DCCURHLD.DATA.SPR_TYP = '2';
        DCCURHLD.DATA.RMK = "AUTO-FX-T+1/2";
        CEP.TRC(SCCGWA, DCCURHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.AC);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.RHLD_TYP);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.CCY);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.RAMT);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.HLD_TYP);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.SPR_TYP);
        S000_CALL_DCZURHLD();
        if (FXRDIRFX.DD_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = FXRDIRFX.DD_AC2;
            DDCUCRAC.CCY = FXRDIRFX.SELL_CCY;
            DDCUCRAC.TX_AMT = FXRDIRFX.SELL_AMT;
            if (FXRDIRFX.S_CS_FLG == '1') {
                DDCUCRAC.CCY_TYPE = '2';
            }
            if (FXRDIRFX.S_CS_FLG == '0') {
                DDCUCRAC.CCY_TYPE = '1';
            }
            DDCUCRAC.TX_TYPE = 'T';
            if (FXRDIRFX.PAC_FLG == '1') {
                DDCUCRAC.OTHER_AC = FXRDIRFX.DD_AC1;
            }
            if (FXRDIRFX.PAC_FLG == '2' 
                || FXRDIRFX.PAC_FLG == '3') {
                DDCUCRAC.OTHER_AC = FXRDIRFX.BUY_SUPAC;
            }
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (FXRDIRFX.PROD_CD.equalsIgnoreCase("1303020403")) {
                DDCUCRAC.TX_MMO = "Y012";
            } else {
                DDCUCRAC.TX_MMO = "Y013";
            }
            DDCUCRAC.REMARKS = FXRDIRFX.RMK;
            CEP.TRC(SCCGWA, DDCUCRAC.AC);
            CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC);
            CEP.TRC(SCCGWA, DDCUCRAC.RLT_AC);
            CEP.TRC(SCCGWA, DDCUCRAC.CCY);
            CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
            CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
            S000_CALL_DDZUCRAC();
        }
        if (FXRDIRFX.DD_AC1.trim().length() > 0) {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = FXRDIRFX.DD_AC1;
            DDCUDRAC.CCY = FXRDIRFX.BUY_CCY;
            DDCUDRAC.TX_AMT = FXRDIRFX.BUY_AMT;
            if (FXRDIRFX.B_CS_FLG == '1') {
                DDCUDRAC.CCY_TYPE = '2';
            }
            if (FXRDIRFX.B_CS_FLG == '0') {
                DDCUDRAC.CCY_TYPE = '1';
            }
            DDCUDRAC.TX_TYPE = 'T';
            if (FXRDIRFX.CAC_FLG == '1') {
                DDCUDRAC.OTHER_AC = FXRDIRFX.DD_AC2;
            }
            if (FXRDIRFX.CAC_FLG == '2' 
                || FXRDIRFX.CAC_FLG == '3') {
                DDCUDRAC.OTHER_AC = FXRDIRFX.SELL_SUPAC;
            }
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (FXRDIRFX.PROD_CD.equalsIgnoreCase("1303020403")) {
                DDCUDRAC.TX_MMO = "Y014";
            } else {
                DDCUDRAC.TX_MMO = "Y015";
            }
            DDCUDRAC.REMARKS = FXRDIRFX.RMK;
            CEP.TRC(SCCGWA, DDCUDRAC.AC);
            CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
            CEP.TRC(SCCGWA, DDCUDRAC.RLT_AC);
            CEP.TRC(SCCGWA, DDCUDRAC.CCY);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
            S000_CALL_DDZUDRAC();
        }
        CEP.TRC(SCCGWA, FXRDIRFX.CAC_FLG);
        if (FXRDIRFX.CAC_FLG == '2' 
            || FXRDIRFX.CAC_FLG == '3') {
            IBS.init(SCCGWA, AICUUPIA);
            if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
            JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
            for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
            AICUUPIA.DATA.POST_NARR = "EX MARKET -" + AICUUPIA.DATA.POST_NARR.substring(11);
            if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
            JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
            for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
            if (FXRDIRFX.CTA_NO == null) FXRDIRFX.CTA_NO = "";
            JIBS_tmp_int = FXRDIRFX.CTA_NO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) FXRDIRFX.CTA_NO += " ";
            AICUUPIA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR.substring(0, 17 - 1) + FXRDIRFX.CTA_NO + AICUUPIA.DATA.POST_NARR.substring(17 + 19 - 1);
            AICUUPIA.DATA.RVS_NO = FXRDIRFX.REV_NO;
            AICUUPIA.DATA.AMT = FXRDIRFX.SELL_AMT;
            AICUUPIA.DATA.CCY = FXRDIRFX.SELL_CCY;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AC_NO = FXRDIRFX.SELL_SUPAC;
            if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
            JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
            for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
            AICUUPIA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR.substring(0, 12 - 1) + " MAS" + AICUUPIA.DATA.POST_NARR.substring(12 + 4 - 1);
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.THEIR_AC = FXRDIRFX.DD_AC1;
            AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.THEIR_CCY = FXRDIRFX.BUY_CCY;
            AICUUPIA.DATA.THEIR_AMT = FXRDIRFX.BUY_AMT;
            AICUUPIA.DATA.THEIR_RAT = FXRDIRFX.EX_RATE;
            AICUUPIA.DATA.PAY_MAN = FXRDIRFX.CI_CNM;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.POST_NARR);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.CCY);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.VALUE_DATE);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_AC);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.SETTLE_DT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_CCY);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_AMT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_RAT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
            S000_CALL_AIZUUPIA();
            WS_OUT_DATA.WS_REV_NO = AICUUPIA.DATA.RVS_NO;
        }
        CEP.TRC(SCCGWA, FXRDIRFX.DD_AC1);
        CEP.TRC(SCCGWA, FXRDIRFX.DD_AC2);
        CEP.TRC(SCCGWA, FXRDIRFX.HLD_NO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_REV_NO);
    }
    public void C010_7_GEN_EVENT() throws IOException,SQLException,Exception {
        if (FXRDIRFX.BUY_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            && !FXRDIRFX.SELL_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            WS_FLG = "SH";
        }
        if (!FXRDIRFX.BUY_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            && FXRDIRFX.SELL_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            WS_FLG = "JH";
        }
        if (!FXRDIRFX.BUY_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            && !FXRDIRFX.SELL_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            WS_FLG = "WB";
        }
        CEP.TRC(SCCGWA, WS_FLG);
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, "MAB");
        D010_COMMON_PARM();
        BPCPOEWA.DATA.EVENT_CODE = "MAB";
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = FXRDIRFX.BUY_CCY;
        BPCPOEWA.DATA.BR_OLD = FXRDIRFX.AC_BR;
        BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = FXRDIRFX.BUY_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
        BPCPOEWA.DATA.EFF_DAYS = 0;
        S000_CALL_BPZPOEWA();
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, "MAS");
        D010_COMMON_PARM();
        BPCPOEWA.DATA.EVENT_CODE = "MAS";
        BPCPOEWA.DATA.BR_OLD = FXRDIRFX.AC_BR;
        BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = FXRDIRFX.SELL_CCY;
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = FXRDIRFX.SELL_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        BPCPOEWA.DATA.EFF_DAYS = 0;
        S000_CALL_BPZPOEWA();
        if (FXRDIRFX.KEY.UPD_BR != 706660800) {
            if (!WS_FLG.equalsIgnoreCase("SH")) {
                IBS.init(SCCGWA, BPCPOEWA);
                CEP.TRC(SCCGWA, "PLB");
                D010_COMMON_PARM();
                BPCPOEWA.DATA.EVENT_CODE = "PLB";
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = FXRDIRFX.BUY_CCY;
                if (FXRDIRFX.KEY.UPD_BR != 706660800) {
                    BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
                }
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = FXRDIRFX.BUY_AMT;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
                BPCPOEWA.DATA.EFF_DAYS = 0;
                S000_CALL_BPZPOEWA();
                CEP.TRC(SCCGWA, "DEVLIH000007");
            }
            if (!WS_FLG.equalsIgnoreCase("SH")) {
                IBS.init(SCCGWA, BPCPOEWA);
                CEP.TRC(SCCGWA, "PLBR");
                D010_COMMON_PARM();
                BPCPOEWA.DATA.EVENT_CODE = "PLBR";
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
                if (FXRDIRFX.KEY.UPD_BR != 706660800) {
                    BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
                }
                CEP.TRC(SCCGWA, FXRDIRFX.BUY_AMT);
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '2';
                BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                BPCFX.BR = FXRDIRFX.AC_BR;
                BPCFX.EXR_TYPE = "TRE";
                BPCFX.BUY_CCY = FXRDIRFX.BUY_CCY;
                BPCFX.SELL_CCY = BPCRBANK.LOC_CCY1;
                BPCFX.B_CASH_FLG = FXRDIRFX.B_CS_FLG;
                CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
                CEP.TRC(SCCGWA, FXRDIRFX.VALUE_DT);
                CEP.TRC(SCCGWA, BPCFX.EFF_DATE);
                CEP.TRC(SCCGWA, BPCFX.EFF_TIME);
                S000_CALL_BPZSFX();
                CEP.TRC(SCCGWA, BPCFX.UNT);
                CEP.TRC(SCCGWA, FXRDIRFX.EX_RATE);
                CEP.TRC(SCCGWA, BPCFX.COS_RATE);
                WS_RMB_AMT = FXRDIRFX.BUY_AMT * BPCFX.COS_RATE / BPCFX.UNT;
                bigD = new BigDecimal(WS_RMB_AMT);
                WS_RMB_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                BPCPOEWA.DATA.AMT_INFO[8-1].AMT = WS_RMB_AMT;
                BPCPOEWA.DATA.EFF_DAYS = 0;
                if (BPCPOEWA.DATA.AMT_INFO[8-1].AMT != 0) {
                    CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[8-1].AMT);
                    S000_CALL_BPZPOEWA();
                    CEP.TRC(SCCGWA, "DEVLIH000007");
                }
            }
            if (!WS_FLG.equalsIgnoreCase("JH")) {
                IBS.init(SCCGWA, BPCPOEWA);
                CEP.TRC(SCCGWA, "PLS");
                D010_COMMON_PARM();
                BPCPOEWA.DATA.EVENT_CODE = "PLS";
                if (FXRDIRFX.KEY.UPD_BR != 706660800) {
                    BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
                }
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = FXRDIRFX.SELL_CCY;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = FXRDIRFX.SELL_AMT;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
                BPCPOEWA.DATA.EFF_DAYS = 0;
                S000_CALL_BPZPOEWA();
                CEP.TRC(SCCGWA, "DEVLIH000007");
            }
            if (!WS_FLG.equalsIgnoreCase("JH")) {
                IBS.init(SCCGWA, BPCPOEWA);
                CEP.TRC(SCCGWA, "PLSR");
                D010_COMMON_PARM();
                BPCPOEWA.DATA.EVENT_CODE = "PLSR";
                if (FXRDIRFX.KEY.UPD_BR != 706660800) {
                    BPCPOEWA.DATA.BR_3 = FXRDIRFX.AC_BR;
                }
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '2';
                BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                BPCFX.BR = FXRDIRFX.AC_BR;
                BPCFX.EXR_TYPE = "TRE";
                BPCFX.SELL_CCY = FXRDIRFX.SELL_CCY;
                BPCFX.S_CASH_FLG = FXRDIRFX.S_CS_FLG;
                BPCFX.BUY_CCY = BPCRBANK.LOC_CCY1;
                CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
                CEP.TRC(SCCGWA, FXRDIRFX.VALUE_DT);
                CEP.TRC(SCCGWA, BPCFX.EFF_DATE);
                CEP.TRC(SCCGWA, BPCFX.EFF_TIME);
                S000_CALL_BPZSFX();
                CEP.TRC(SCCGWA, FXRDIRFX.SELL_AMT);
                CEP.TRC(SCCGWA, BPCFX.UNT);
                CEP.TRC(SCCGWA, BPCFX.COS_RATE);
                CEP.TRC(SCCGWA, FXRDIRFX.EX_RATE);
                WS_RMB_AMT = FXRDIRFX.SELL_AMT * BPCFX.COS_RATE / BPCFX.UNT;
                bigD = new BigDecimal(WS_RMB_AMT);
                WS_RMB_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                BPCPOEWA.DATA.AMT_INFO[9-1].AMT = WS_RMB_AMT;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[9-1].AMT);
                BPCPOEWA.DATA.EFF_DAYS = 0;
                if (BPCPOEWA.DATA.AMT_INFO[9-1].AMT != 0) {
                    S000_CALL_BPZPOEWA();
                    CEP.TRC(SCCGWA, "DEVLIH000007");
                }
            }
        }
    }
    public void C010_8_GEN_FXHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, FXRDIRFX.CTA_NO);
        IBS.init(SCCGWA, FXRTXHIS);
        FXRTXHIS.KEY.TRN_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRTXHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        FXRTXHIS.CTA_NO = FXRDIRFX.CTA_NO;
        FXRTXHIS.PROD_CD = FXRDIRFX.PROD_CD;
        if (FXRDIRFX.VALUE_DT == FXRDIRFX.O_END_DT) {
            FXRTXHIS.SETL_TYPE = '0';
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = FXRDIRFX.BUY_CCY;
        S000_CALL_BPZQCCY();
        WS_BUY_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
        CEP.TRC(SCCGWA, WS_BUY_CCY_CNTYCD);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = FXRDIRFX.SELL_CCY;
        S000_CALL_BPZQCCY();
        WS_SELL_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
        CEP.TRC(SCCGWA, WS_SELL_CCY_CNTYCD);
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = FXRDIRFX.VALUE_DT;
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
        BPCPCMWD.CALCD[1-1] = "CN  ";
        S000_CALL_BPZPCMWD();
        WS_TEMP_DT = BPCPCMWD.NEXT_WORK_DAY_ALL;
        CEP.TRC(SCCGWA, WS_TEMP_DT);
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = WS_TEMP_DT;
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
        BPCPCMWD.CALCD[1-1] = "CN  ";
        S000_CALL_BPZPCMWD();
        WS_SELT_DATE = BPCPCMWD.NEXT_WORK_DAY_ALL;
        CEP.TRC(SCCGWA, WS_SELT_DATE);
        if (FXRDIRFX.O_END_DT == WS_TEMP_DT) {
            FXRTXHIS.SETL_TYPE = '1';
        }
        if (FXRDIRFX.O_END_DT == WS_SELT_DATE) {
            FXRTXHIS.SETL_TYPE = '2';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        FXRTXHIS.TRN_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        FXRTXHIS.TRN_NM = "即期兑换交易-重新交割";
        FXRTXHIS.PRE_STS = 'E';
        FXRTXHIS.POST_STS = 'M';
        FXRTXHIS.HLD_NO = FXRDIRFX.HLD_NO;
        FXRTXHIS.ST_TRCHNL = SCCGWA.COMM_AREA.CHNL;
        FXRTXHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXRTXHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        FXRTXHIS.TRN_TM = SCCGWA.COMM_AREA.TR_TIME;
        FXTTXHIS_RD = new DBParm();
        FXTTXHIS_RD.TableName = "FXTTXHIS";
        IBS.WRITE(SCCGWA, FXRTXHIS, FXTTXHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_EXIST);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B030_SEND_OUTPUT() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_CTA_NO = FXRDIRFX.CTA_NO;
        WS_OUT_DATA.WS_STS = FXRDIRFX.STATUS;
        WS_OUT_DATA.WS_TIK_NO = FXRDIRFX.TIK_NO;
        WS_OUT_DATA.WS_PROD_CD = FXRDIRFX.PROD_CD;
        WS_OUT_DATA.WS_PROD_NM = FXRDIRFX.PROD_NM;
        WS_OUT_DATA.WS_CI_NO = FXRDIRFX.CI_NO;
        WS_OUT_DATA.WS_TRA_AC = FXRDIRFX.TRA_AC;
        WS_OUT_DATA.WS_CI_CNM = FXRDIRFX.CI_CNM;
        WS_OUT_DATA.WS_CI_ENM = FXRDIRFX.CI_ENM;
        WS_OUT_DATA.WS_VALUE_DT = FXRDIRFX.VALUE_DT;
        WS_OUT_DATA.WS_O_END_DT = FXRDIRFX.O_END_DT;
        WS_OUT_DATA.WS_PAC_FLG = FXRDIRFX.PAC_FLG;
        WS_OUT_DATA.WS_DD_AC1 = FXRDIRFX.DD_AC1;
        WS_OUT_DATA.WS_B_SUPAC = FXRDIRFX.BUY_SUPAC;
        WS_OUT_DATA.WS_B_CS_FLG = FXRDIRFX.B_CS_FLG;
        WS_OUT_DATA.WS_BUY_CCY = FXRDIRFX.BUY_CCY;
        WS_OUT_DATA.WS_BUY_AMT = FXRDIRFX.BUY_AMT;
        WS_OUT_DATA.WS_CAC_FLG = FXRDIRFX.CAC_FLG;
        WS_OUT_DATA.WS_DD_AC2 = FXRDIRFX.DD_AC2;
        WS_OUT_DATA.WS_S_SUPAC = FXRDIRFX.SELL_SUPAC;
        WS_OUT_DATA.WS_REV_NO = FXRDIRFX.REV_NO;
        WS_OUT_DATA.WS_S_CS_FLG = FXRDIRFX.S_CS_FLG;
        WS_OUT_DATA.WS_SELL_CCY = FXRDIRFX.SELL_CCY;
        WS_OUT_DATA.WS_SELL_AMT = FXRDIRFX.SELL_AMT;
        WS_OUT_DATA.WS_FAIL_RSN = FXRDIRFX.FAIL_RSN;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_FMTID;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1031;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC   ", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        DDCUDRAC.CHK_PSW_FLG = 'N';
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC   ", DDCUDRAC);
    }
    public void B020_07_UPDATE_CONT() throws IOException,SQLException,Exception {
        FXCRDRFX.FUNC = 'D';
        FXCRDRFX.REC_PTR = FXRDIRFX;
        FXCRDRFX.REC_LEN = 2776;
        S000_CALL_FXZRDRFX();
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        CEP.TRC(SCCGWA, "--------");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.TRN_DT);
        CEP.TRC(SCCGWA, FXB1154_AWA_1154.JRN_NO);
        CEP.TRC(SCCGWA, "--------");
        FXCRDRFX.FUNC = 'A';
        FXRDIRFX.STATUS = 'M';
        FXRDIRFX.OSEQ = FXRDIRFX.KEY.TRN_DT;
        FXRDIRFX.KEY.TRN_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXRDIRFX.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        FXRDIRFX.OJRN_NO = WS_JRN_NO;
        FXRDIRFX.REV_NO = WS_OUT_DATA.WS_REV_NO;
        FXRDIRFX.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXRDIRFX.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FXRDIRFX.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FXCRDRFX.REC_PTR = FXRDIRFX;
        FXCRDRFX.REC_LEN = 2776;
        CEP.TRC(SCCGWA, "-----------");
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.TRN_DT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
        S000_CALL_FXZRDRFX();
    }
    public void B020_09_FHIST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, FXRDIRFX.DD_AC1);
        CEP.TRC(SCCGWA, FXRDIRFX.PROD_CD);
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.AC = FXRDIRFX.CTA_NO;
        if (FXRDIRFX.DD_AC1.trim().length() > 0) {
            BPCPFHIS.DATA.RLT_AC = FXRDIRFX.DD_AC1;
        }
        BPCPFHIS.DATA.PROD_CD = FXRDIRFX.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = K_BP_PRDMO_CD;
        BPCPFHIS.DATA.TX_TOOL = K_BP_PRDMO_CD;
        BPCPFHIS.DATA.SUMUP_FLG = 'N';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'N';
        BPCPFHIS.DATA.TX_VAL_DT = FXRDIRFX.O_END_DT;
        BPCPFHIS.DATA.TX_CCY = FXRDIRFX.BUY_CCY;
        BPCPFHIS.DATA.TX_AMT = FXRDIRFX.BUY_AMT;
        BPCPFHIS.DATA.CI_NO = FXRDIRFX.CI_NO;
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (K_AI_POST_NARR_NOTE == null) K_AI_POST_NARR_NOTE = "";
        JIBS_tmp_int = K_AI_POST_NARR_NOTE.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) K_AI_POST_NARR_NOTE += " ";
        BPCPFHIS.DATA.NARRATIVE = K_AI_POST_NARR_NOTE + BPCPFHIS.DATA.NARRATIVE.substring(12);
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (K_AI_POST_EVENT_MAB == null) K_AI_POST_EVENT_MAB = "";
        JIBS_tmp_int = K_AI_POST_EVENT_MAB.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) K_AI_POST_EVENT_MAB += " ";
        BPCPFHIS.DATA.NARRATIVE = BPCPFHIS.DATA.NARRATIVE.substring(0, 13 - 1) + K_AI_POST_EVENT_MAB + BPCPFHIS.DATA.NARRATIVE.substring(13 + 4 - 1);
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (FXRDIRFX.CTA_NO == null) FXRDIRFX.CTA_NO = "";
        JIBS_tmp_int = FXRDIRFX.CTA_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) FXRDIRFX.CTA_NO += " ";
        BPCPFHIS.DATA.NARRATIVE = BPCPFHIS.DATA.NARRATIVE.substring(0, 17 - 1) + FXRDIRFX.CTA_NO + BPCPFHIS.DATA.NARRATIVE.substring(17 + 19 - 1);
        BPCPFHIS.DATA.TX_MMO = K_HIST_TX_MMO;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARK;
        S000_CALL_BPZPFHIS();
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.AC = FXRDIRFX.CTA_NO;
        CEP.TRC(SCCGWA, FXRDIRFX.CTA_NO);
        if (FXRDIRFX.DD_AC2.trim().length() > 0) {
            BPCPFHIS.DATA.RLT_AC = FXRDIRFX.DD_AC2;
        }
        BPCPFHIS.DATA.PROD_CD = FXRDIRFX.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = K_BP_PRDMO_CD;
        BPCPFHIS.DATA.TX_TOOL = K_BP_PRDMO_CD;
        BPCPFHIS.DATA.SUMUP_FLG = 'N';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'N';
        BPCPFHIS.DATA.TX_VAL_DT = FXRDIRFX.O_END_DT;
        BPCPFHIS.DATA.TX_CCY = FXRDIRFX.SELL_CCY;
        BPCPFHIS.DATA.TX_AMT = FXRDIRFX.SELL_AMT;
        BPCPFHIS.DATA.CI_NO = FXRDIRFX.CI_NO;
        CEP.TRC(SCCGWA, FXRDIRFX.O_END_DT);
        CEP.TRC(SCCGWA, FXRDIRFX.SELL_CCY);
        CEP.TRC(SCCGWA, FXRDIRFX.SELL_AMT);
        CEP.TRC(SCCGWA, FXRDIRFX.CI_NO);
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (K_AI_POST_NARR_NOTE == null) K_AI_POST_NARR_NOTE = "";
        JIBS_tmp_int = K_AI_POST_NARR_NOTE.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) K_AI_POST_NARR_NOTE += " ";
        BPCPFHIS.DATA.NARRATIVE = K_AI_POST_NARR_NOTE + BPCPFHIS.DATA.NARRATIVE.substring(12);
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (K_AI_POST_EVENT_MAS == null) K_AI_POST_EVENT_MAS = "";
        JIBS_tmp_int = K_AI_POST_EVENT_MAS.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) K_AI_POST_EVENT_MAS += " ";
        BPCPFHIS.DATA.NARRATIVE = BPCPFHIS.DATA.NARRATIVE.substring(0, 13 - 1) + K_AI_POST_EVENT_MAS + BPCPFHIS.DATA.NARRATIVE.substring(13 + 4 - 1);
        if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
        JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
        if (FXRDIRFX.CTA_NO == null) FXRDIRFX.CTA_NO = "";
        JIBS_tmp_int = FXRDIRFX.CTA_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) FXRDIRFX.CTA_NO += " ";
        BPCPFHIS.DATA.NARRATIVE = BPCPFHIS.DATA.NARRATIVE.substring(0, 17 - 1) + FXRDIRFX.CTA_NO + BPCPFHIS.DATA.NARRATIVE.substring(17 + 19 - 1);
        BPCPFHIS.DATA.TX_MMO = K_HIST_TX_MMO;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARK;
        S000_CALL_BPZPFHIS();
    }
    public void RO00_CHECK_CONT_STATUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        if (FXRDIRFX.STATUS != K_STATUS_ERROR) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CONT_NO_RIGHT);
        }
        CEP.TRC(SCCGWA, FXRDIRFX.O_END_DT);
        if (FXRDIRFX.O_END_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_SETL_ERR);
        }
    }
    public void D010_COMMON_PARM() throws IOException,SQLException,Exception {
        BPCPOEWA.DATA.CNTR_TYPE = FXRDIRFX.PROD_TYP;
        BPCPOEWA.DATA.PROD_CODE = FXRDIRFX.PROD_CD;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = FXRDIRFX.CTA_NO;
        BPCPOEWA.DATA.CI_NO = FXRDIRFX.CI_NO;
    }
    public void S000_CALL_DCZSDUCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZSDUCT", DCCSDUCT, true);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFX.RC);
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCQCNGL.RC);
        }
    }
    public void S000_CALL_FXZRDRFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-DRFX", FXCRDRFX);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCUCNGM.RC);
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICUUPIA.RC);
        }
    }
    public void S000_CALL_BPZUMPRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MPRT-MAINT", BPCUMPRT);
        if (BPCUMPRT.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCUMPRT.RC);
        }
    }
    public void S000_CALL_WFZCAPV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "WF-GEN-GWA-GAPV", WFCCAPV);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
