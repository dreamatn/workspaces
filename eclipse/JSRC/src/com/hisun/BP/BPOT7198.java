package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7198 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP078";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL = "0016";
    String K_CUS_CR_STS_TBL = "0016";
    String K_STS_TABLE_DC = "DC";
    String K_DC_DR_STS_TBL = "0002";
    String K_DC_CR_STS_TBL = "0001";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    double WS_FEE_AMT = 0;
    short WS_CNT = 0;
    String WS_REG_CODE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCOCHGS BPCOCHGS = new BPCOCHGS();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPRFBAS BPRFBAS = new BPRFBAS();
    CIRACR CIRACR = new CIRACR();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    CICACCU CICACCU = new CICACCU();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCSBCHQ DDCSBCHQ = new DDCSBCHQ();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCSQCHQ DDCSQCHQ = new DDCSQCHQ();
    CICCUST CICCUST = new CICCUST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCUGLM BPCUGLM = new BPCUGLM();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    BPB7198_AWA_7198 BPB7198_AWA_7198;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT7198 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7198_AWA_7198>");
        BPB7198_AWA_7198 = (BPB7198_AWA_7198) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[01-1].FEE_AMT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
        CEP.TRC(SCCGWA, "1111111111      ");
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B015_SET_FEE_INFO_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B045_CHG_FEE_PROC();
        B090_OUTPUT_INFO_CN();
    }
    public void B024_ACCOUNT_BR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.BR);
        if (BPB7198_AWA_7198.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPB7198_AWA_7198.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        }
        if (BPCPQORG.ATTR != '2' 
            && BPB7198_AWA_7198.BR != 0 
            && CICACCU.DATA.BBR != 0) {
            BPB7198_AWA_7198.BR = CICACCU.DATA.BBR;
        }
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-P-INQ-ORG";
        SCCCALL.COMMAREA_PTR = BPCPQORG;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CI_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_FLG);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_CCY);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_AC);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHQ_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.RMK);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.C_P_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.SALE_DT);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.BSNS_NO);
        if (BPB7198_AWA_7198.FEE_INF[1-1].FEE_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CI_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_FLG);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_CCY);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_AC);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHQ_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.RMK);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.C_P_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.SALE_DT);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.BSNS_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (BPB7198_AWA_7198.AC_TYP == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE);
            if (BPB7198_AWA_7198.FEE_INF[1-1].FEE_CODE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
                WS_FLD_NO = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            B020_GET_FEE_BASIC_INFO();
            CEP.TRC(SCCGWA, BPRFBAS.AMO_FLG);
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG);
            if (BPRFBAS.AMO_FLG == '1') {
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPRFBAS.AMO_FLG == '0' 
                    && BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG == 'Y') {
                    R010_GET_CHK_GLMST_CN();
                }
            }
            if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG == 'Y' 
                && (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT == 0 
                || BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMODT_MUSTINPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG);
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT);
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT);
            if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG == 'Y' 
                && BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT != 0 
                && BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT != 0 
                && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y')) {
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_START_DATE_LESS;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT <= BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DATE_LESS;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT > 20991231) {
                    CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_AC);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.C_P_NO);
        if (BPB7198_AWA_7198.AC_TYP == '0') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7198_AWA_7198.FEE_AC;
            CICQACAC.DATA.CCY_ACAC = BPB7198_AWA_7198.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB7198_AWA_7198.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
        }
        if (BPB7198_AWA_7198.AC_TYP == '2') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.NOSTRO_CD = BPB7198_AWA_7198.FEE_AC;
            IBCQINF.INPUT_DATA.CCY = BPB7198_AWA_7198.CHG_CCY;
            S000_CALL_IBZQINF();
        }
        if (BPB7198_AWA_7198.AC_TYP == '3') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = BPB7198_AWA_7198.FEE_AC;
            S000_CALL_AIZPQMIB();
        }
        if (BPB7198_AWA_7198.AC_TYP == '4' 
            || BPB7198_AWA_7198.AC_TYP == '5') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7198_AWA_7198.FEE_AC;
            CICQACAC.DATA.CCY_ACAC = BPB7198_AWA_7198.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB7198_AWA_7198.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
        }
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        if (BPB7198_AWA_7198.AC_TYP == '5' 
            && BPB7198_AWA_7198.CHG_MOD == '1') {
            CEP.TRC(SCCGWA, "CHECK PSBK STATUS");
            IBS.init(SCCGWA, DCCIMSTR);
            DCCIMSTR.KEY.VIA_AC = DCCPACTY.OUTPUT.VIA_AC;
            S000_CALL_DCZIMSTR();
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_DC;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
            CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, " NOT CANCEL");
                if (BPRFBAS.CHG_TYPE == '0') {
                    BPCFCSTS.TBL_NO = K_DC_DR_STS_TBL;
                } else {
                    if (BPRFBAS.CHG_TYPE == '1') {
                        BPCFCSTS.TBL_NO = K_DC_CR_STS_TBL;
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "CANCEL");
                if (BPRFBAS.CHG_TYPE == '0') {
                    BPCFCSTS.TBL_NO = K_DC_CR_STS_TBL;
                } else {
                    if (BPRFBAS.CHG_TYPE == '1') {
                        BPCFCSTS.TBL_NO = K_DC_DR_STS_TBL;
                    }
                }
            }
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DCCIMSTR.DATA.STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + DCCIMSTR.DATA.STS_WORD.length() - 1);
        }
        if ((BPB7198_AWA_7198.AC_TYP == '0' 
            || BPB7198_AWA_7198.AC_TYP == '4' 
            || BPB7198_AWA_7198.AC_TYP == '5') 
            && BPB7198_AWA_7198.CHG_MOD == '1') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = BPB7198_AWA_7198.FEE_AC;
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            S000_CALL_CIZACCU_CN();
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPB7198_AWA_7198.FEE_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(0, 20));
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(21 - 1, 21 + 20 - 1));
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(42 - 1, 42 + 20 - 1));
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1));
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_MOD);
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (BPB7198_AWA_7198.CHG_MOD == '1' 
                && (DDCIMMST.DATA.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMMST.DATA.AC_STS == 'M')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAT_CHARGE_NOT_ALLOW;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
            CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, " NOT CANCEL");
                if (BPRFBAS.CHG_TYPE == '0') {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                } else {
                    if (BPRFBAS.CHG_TYPE == '1') {
                        BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "CANCEL");
                if (BPRFBAS.CHG_TYPE == '0') {
                    BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                } else {
                    if (BPRFBAS.CHG_TYPE == '1') {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                    }
                }
            }
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDCIMMST.DATA.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && BPRFBAS.CHG_TYPE == '0') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 110 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(110 + 1 - 1);
            }
        }
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHQ_NO);
        if (BPB7198_AWA_7198.CHQ_NO.trim().length() > 0) {
            if (BPB7198_AWA_7198.AC_TYP == '6' 
                || BPB7198_AWA_7198.AC_TYP == '7') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7198_AWA_7198.FEE_AC;
            CICQACAC.DATA.CCY_ACAC = BPB7198_AWA_7198.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB7198_AWA_7198.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            IBS.init(SCCGWA, DDCSBCHQ);
            DDCSBCHQ.AC_NO = BPB7198_AWA_7198.FEE_AC;
            DDCSBCHQ.CHQ_NO = BPB7198_AWA_7198.CHQ_NO;
            S000_CALL_DDZSBCHQ();
            CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
            CEP.TRC(SCCGWA, DDCSBCHQ.CI_ID);
            CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_STS);
            CEP.TRC(SCCGWA, DDCSBCHQ.AMT);
            IBS.init(SCCGWA, DDCSQCHQ);
            DDCSQCHQ.AC_NO = BPB7198_AWA_7198.FEE_AC;
            DDCSQCHQ.STR_CHQ_NO = BPB7198_AWA_7198.CHQ_NO;
            DDCSQCHQ.END_CHQ_NO = BPB7198_AWA_7198.CHQ_NO;
            S000_CALL_DDZSQCHQ();
            CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
            if (DDCSQCHQ.CHQ_STS != '1' 
                && DDCSQCHQ.CHQ_STS != '6' 
                && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_STS_ERR;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
            if ((BPB7198_AWA_7198.AC_TYP == '6' 
                && DDCSQCHQ.CHQ_TYP != '2') 
                || (BPB7198_AWA_7198.AC_TYP == '7' 
                && DDCSQCHQ.CHQ_TYP != '3')) {
                CEP.TRC(SCCGWA, "CHQ TYPE NOT MATCH");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if ((BPB7198_AWA_7198.AC_TYP == '0' 
            || BPB7198_AWA_7198.AC_TYP == '4' 
            || BPB7198_AWA_7198.AC_TYP == '5' 
            || BPB7198_AWA_7198.AC_TYP == '6' 
            || BPB7198_AWA_7198.AC_TYP == '7') 
            && BPB7198_AWA_7198.CHG_MOD == '1') {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = BPB7198_AWA_7198.FEE_AC;
            DDCIMCCY.DATA[1-1].CCY = BPB7198_AWA_7198.CHG_CCY;
            DDCIMCCY.DATA[1-1].CCY_TYPE = BPB7198_AWA_7198.CCY_TYPE;
            S000_CALL_DDZIMCCY();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPB7198_AWA_7198.AC_TYP == '0' 
                || BPB7198_AWA_7198.AC_TYP == '4' 
                || BPB7198_AWA_7198.AC_TYP == '5' 
                || BPB7198_AWA_7198.AC_TYP == '2' 
                || BPB7198_AWA_7198.AC_TYP == '6' 
                || BPB7198_AWA_7198.AC_TYP == '7') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_AC);
                CICACCU.DATA.AGR_NO = BPB7198_AWA_7198.FEE_AC;
                if (BPB7198_AWA_7198.AC_TYP == '2') {
                    CICACCU.DATA.AGR_NO = IBCQINF.INPUT_DATA.AC_NO;
                }
                CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
                CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
                S000_CALL_CIZACCU_CN();
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                CEP.TRC(SCCGWA, BPB7198_AWA_7198.CI_NO);
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    && BPB7198_AWA_7198.CI_NO.trim().length() > 0 
                    && !CICACCU.DATA.CI_NO.equalsIgnoreCase(BPB7198_AWA_7198.CI_NO)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) 
                    && BPB7198_AWA_7198.CI_NO.trim().length() == 0) {
                    BPB7198_AWA_7198.CI_NO = CICACCU.DATA.CI_NO;
                }
                B024_ACCOUNT_BR_PROC();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B015_SET_FEE_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPB7198_AWA_7198.AC_TYP;
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHG_FLG);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.AC_TYP);
        if (BPB7198_AWA_7198.AC_TYP == '1') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = " ";
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB7198_AWA_7198.FEE_AC;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB7198_AWA_7198.CHG_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = BPB7198_AWA_7198.CI_NO;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPB7198_AWA_7198.FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHQ_NO = BPB7198_AWA_7198.CHQ_NO;
        BPCFFTXI.TX_DATA.SALE_DATE = BPB7198_AWA_7198.SALE_DT;
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CI_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CHQ_NO);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.SALE_DT);
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_CTRT);
        BPCFFTXI.TX_DATA.FEE_CTRT = BPB7198_AWA_7198.FEE_CTRT;
        BPCFFTXI.TX_DATA.FEE_DPTM = 0;
        BPCFFTXI.TX_DATA.FEE_CONT = " ";
        BPCFFTXI.TX_DATA.FEE_ADVICE = " ";
        BPCFFTXI.TX_DATA.CCY_TYPE = BPB7198_AWA_7198.CCY_TYPE;
        BPCFFTXI.TX_DATA.BSNS_NO = BPB7198_AWA_7198.BSNS_NO;
        BPCFFTXI.TX_DATA.CREV_NO = BPB7198_AWA_7198.CREV_NO;
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.CREV_NO);
        if (BPB7198_AWA_7198.CHG_MOD == '1') {
            BPCFFTXI.TX_DATA.PROC_TYPE = '6';
        }
        S000_CALL_BPZFFTXI();
    }
    public void B020_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT);
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPRFBAS.KEY);
        CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
        S000_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, BPCTFBAS.RETURN_INFO);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "ENTER NOTFND ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00")) {
            WS_REG_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                WS_REG_CODE = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_REG_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_REG_CODE = "0" + WS_REG_CODE;
            } else {
                R000_GET_REG_CODE_BY_TYPE();
            }
        }
    }
    public void B045_CHG_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            IBS.init(SCCGWA, BPCFFCON);
            BPCFFCON.FEE_INFO.FEE_CNT += 1;
            WS_ITM = BPCFFCON.FEE_INFO.FEE_CNT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = '0';
            CEP.TRC(SCCGWA, BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE;
            if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].PRD_CODE.trim().length() == 0 
                && BPB7198_AWA_7198.FEE_INF[WS_CNT-1].PRD_AC_R.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].PRD_CODE;
            if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].PRD_CODE.trim().length() == 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].PRD_AC_R;
                S000_CALL_CIZACCU_CN();
                BPCFFCON.FEE_INFO.PROD_CODE1 = CICACCU.DATA.PROD_CD;
            }
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = BPB7198_AWA_7198.CHG_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.ACCOUNT_BR = BPB7198_AWA_7198.BR;
            BPCFFCON.FEE_INFO.EX_RATE = 0;
            BPCFFCON.FEE_INFO.REMARK = " ";
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY = BPB7198_AWA_7198.AC_TYP;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = BPB7198_AWA_7198.CHG_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].ADJ_AMT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC);
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                if (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG == 'Y') {
                    BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG = '1';
                } else {
                    BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG;
                }
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_STDT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_EDDT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT;
            }
            S000_CALL_BPZFFCON();
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        }
    }
    public void B090_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCHGS);
        IBS.init(SCCGWA, CIRACR);
        if (BPB7198_AWA_7198.AC_TYP == '0') {
            CIRACR.KEY.AGR_NO = BPB7198_AWA_7198.FEE_AC;
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        }
        if (BPB7198_AWA_7198.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB7198_AWA_7198.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_ENM);
            BPCOCHGS.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
            BPCOCHGS.AC_ENAME = CICCUST.O_DATA.O_CI_ENM;
        }
        BPCOCHGS.CI_NO = BPB7198_AWA_7198.CI_NO;
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            BPCOCHGS.INFO[WS_CNT-1].FEE_CODE = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE;
            BPCOCHGS.INFO[WS_CNT-1].FEE_AMT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCOCHGS.INFO[WS_CNT-1].AMO_FLG = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_FLG;
            BPCOCHGS.INFO[WS_CNT-1].AMO_STDT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_STDT;
            BPCOCHGS.INFO[WS_CNT-1].AMO_EDDT = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].AMO_EDDT;
        }
        BPCOCHGS.TX_MMO = BPRFBAS.TX_MMO;
        BPCOCHGS.CHG_FLG = BPB7198_AWA_7198.CHG_FLG;
        BPCOCHGS.AC_TYP = BPB7198_AWA_7198.AC_TYP;
        BPCOCHGS.CHG_CCY = BPB7198_AWA_7198.CHG_CCY;
        BPCOCHGS.FEE_AC = BPB7198_AWA_7198.FEE_AC;
        BPCOCHGS.CHQ_NO = BPB7198_AWA_7198.CHQ_NO;
        BPCOCHGS.RMK = BPB7198_AWA_7198.RMK;
        BPCOCHGS.BR = BPB7198_AWA_7198.BR;
        BPCOCHGS.CARD_NO = BPB7198_AWA_7198.C_P_NO;
        BPCOCHGS.FEE_CTRT = BPB7198_AWA_7198.FEE_CTRT;
        BPCOCHGS.CCY_TYPE = BPB7198_AWA_7198.CCY_TYPE;
        BPCOCHGS.BSNS_NO = BPB7198_AWA_7198.BSNS_NO;
        BPCOCHGS.CREV_NO = BPB7198_AWA_7198.CREV_NO;
        BPCOCHGS.DATE = BPB7198_AWA_7198.SALE_DT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCHGS;
        SCCFMT.DATA_LEN = 1729;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void R010_GET_CHK_GLMST_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCICNGL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = "FEES";
        if (BPB7198_AWA_7198.BR != 0) {
            BPCQCNGL.DAT.INPUT.BR = BPB7198_AWA_7198.BR;
        }
        BPCICNGL.PROD_TYPE = BPB7198_AWA_7198.FEE_INF[WS_CNT-1].FEE_CODE;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCICNGL;
        S000_CALL_BPZQCNGL();
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO);
        if (BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO != 0) {
            IBS.init(SCCGWA, BPCUGLM);
            BPCUGLM.INFO.FUNC = 'I';
            BPCUGLM.DATA.KEY.TYP = "AMGLM";
            BPCUGLM.DATA.KEY.REDEFINES16.MSTNO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
            BPCUGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCUGLM.DATA.KEY.REDEFINES16);
            S000_CALL_BPZUGLM();
            CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.REL_ITMS[8-1].ITM_NO);
            if (BPCUGLM.DATA.DATA_TXT.REL_ITMS[8-1].ITM_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, "FEE CODE SHOULE NOT AMO");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOT_AMO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZUGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-GLM", BPCUGLM);
        if (BPCUGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        CEP.TRC(SCCGWA, AICPQMIB.RC);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYP_NOT_DEF;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            WS_FLD_NO = BPB7198_AWA_7198.FEE_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            WS_FLD_NO = BPB7198_AWA_7198.C_P_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
        if (DDCIPSBK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIPSBK.RC);
            WS_FLD_NO = BPB7198_AWA_7198.C_P_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSBCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-BCHQ-PROC", DDCSBCHQ);
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSQCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-QCHQ-PROC", DDCSQCHQ);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM_MAIN, BPCFPARM);
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC.RC_CODE);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_FEE, BPCFFCAL);
    }
    public void S000_CALL_BPZPIFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_IFAV, BPCTIFAV);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
