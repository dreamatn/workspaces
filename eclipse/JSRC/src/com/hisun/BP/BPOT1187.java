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

public class BPOT1187 {
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
    BPCOCHGF BPCOCHGF = new BPCOCHGF();
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
    BPB1187_AWA_1187 BPB1187_AWA_1187;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1187 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1187_AWA_1187>");
        BPB1187_AWA_1187 = (BPB1187_AWA_1187) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LQAOZHENGJIE");
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
        if (BPB1187_AWA_1187.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPB1187_AWA_1187.BR;
            S000_CALL_BPZPQORG();
        }
        if (BPCPQORG.ATTR != '2' 
            && BPB1187_AWA_1187.BR != 0 
            && CICACCU.DATA.BBR != 0) {
            BPB1187_AWA_1187.BR = CICACCU.DATA.BBR;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-P-INQ-ORG";
        SCCCALL.COMMAREA_PTR = BPCPQORG;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1187_AWA_1187.FEE_INF[1-1].FEE_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-LAOZHENGJIE");
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            if (BPB1187_AWA_1187.FEE_INF[1-1].FEE_CODE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
                WS_FLD_NO = BPB1187_AWA_1187.FEE_INF[1-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            B020_GET_FEE_BASIC_INFO();
            CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
            CEP.TRC(SCCGWA, BPRFBAS.AMO_FLG);
            CEP.TRC(SCCGWA, BPB1187_AWA_1187.FEE_INF[1-1].AMO_FLG);
            if (BPRFBAS.AMO_FLG == '1') {
                if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_FLG != '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPRFBAS.AMO_FLG == '0' 
                    && BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_FLG == '1') {
                    R010_GET_CHK_GLMST_CN();
                }
            }
            if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_FLG == '1' 
                && (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_STDT == 0 
                || BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMODT_MUSTINPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_FLG == '1' 
                && BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_STDT != 0 
                && BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT != 0 
                && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y')) {
                if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_STDT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_START_DATE_LESS;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT <= BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_STDT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DATE_LESS;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT > 20991231) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        if (BPB1187_AWA_1187.AC_TYP == '0') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB1187_AWA_1187.FEE_AC;
            CICQACAC.DATA.CCY_ACAC = BPB1187_AWA_1187.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB1187_AWA_1187.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
        }
        if (BPB1187_AWA_1187.AC_TYP == '2') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = BPB1187_AWA_1187.FEE_AC;
            IBCQINF.INPUT_DATA.CCY = BPB1187_AWA_1187.CHG_CCY;
            S000_CALL_IBZQINF();
        }
        if (BPB1187_AWA_1187.AC_TYP == '3') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = BPB1187_AWA_1187.FEE_AC;
            S000_CALL_AIZPQMIB();
        }
        if (BPB1187_AWA_1187.AC_TYP == '4' 
            || BPB1187_AWA_1187.AC_TYP == '5') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB1187_AWA_1187.C_P_NO;
            CICQACAC.DATA.CCY_ACAC = BPB1187_AWA_1187.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB1187_AWA_1187.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
        }
        if ((BPB1187_AWA_1187.AC_TYP == '0' 
            || BPB1187_AWA_1187.AC_TYP == '4' 
            || BPB1187_AWA_1187.AC_TYP == '5') 
            && BPB1187_AWA_1187.CHG_MOD == '1') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = BPB1187_AWA_1187.FEE_AC;
            if (BPB1187_AWA_1187.AC_TYP == '4' 
                || BPB1187_AWA_1187.AC_TYP == '5') {
                CICACCU.DATA.AGR_NO = BPB1187_AWA_1187.C_P_NO;
            }
            S000_CALL_CIZACCU_CN();
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPB1187_AWA_1187.FEE_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (BPB1187_AWA_1187.CHG_MOD == '1' 
                && (DDCIMMST.DATA.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMMST.DATA.AC_STS == 'M')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAT_CHARGE_NOT_ALLOW;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (BPRFBAS.CHG_TYPE == '0') {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                } else {
                    if (BPRFBAS.CHG_TYPE == '1') {
                        BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                    }
                }
            } else {
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
        if (BPB1187_AWA_1187.CHQ_NO.trim().length() > 0) {
            if (BPB1187_AWA_1187.AC_TYP == '6' 
                || BPB1187_AWA_1187.AC_TYP == '7') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB1187_AWA_1187.FEE_AC;
            CICQACAC.DATA.CCY_ACAC = BPB1187_AWA_1187.CHG_CCY;
            CICQACAC.DATA.CR_FLG = BPB1187_AWA_1187.CCY_TYPE;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            IBS.init(SCCGWA, DDCSBCHQ);
            DDCSBCHQ.AC_NO = BPB1187_AWA_1187.FEE_AC;
            DDCSBCHQ.CHQ_NO = BPB1187_AWA_1187.CHQ_NO;
            S000_CALL_DDZSBCHQ();
            IBS.init(SCCGWA, DDCSQCHQ);
            DDCSQCHQ.AC_NO = BPB1187_AWA_1187.FEE_AC;
            DDCSQCHQ.STR_CHQ_NO = BPB1187_AWA_1187.CHQ_NO;
            DDCSQCHQ.END_CHQ_NO = BPB1187_AWA_1187.CHQ_NO;
            S000_CALL_DDZSQCHQ();
            if (DDCSQCHQ.CHQ_STS != '1' 
                && DDCSQCHQ.CHQ_STS != '6' 
                && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_STS_ERR;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((BPB1187_AWA_1187.AC_TYP == '6' 
                && DDCSQCHQ.CHQ_TYP != '2') 
                || (BPB1187_AWA_1187.AC_TYP == '7' 
                && DDCSQCHQ.CHQ_TYP != '3')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if ((BPB1187_AWA_1187.AC_TYP == '0' 
            || BPB1187_AWA_1187.AC_TYP == '4' 
            || BPB1187_AWA_1187.AC_TYP == '5' 
            || BPB1187_AWA_1187.AC_TYP == '6' 
            || BPB1187_AWA_1187.AC_TYP == '7') 
            && BPB1187_AWA_1187.CHG_MOD == '1') {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = BPB1187_AWA_1187.FEE_AC;
            if (BPB1187_AWA_1187.AC_TYP == '0') {
                DDCIMCCY.DATA[1-1].AC = BPB1187_AWA_1187.FEE_AC;
            }
            if ((BPB1187_AWA_1187.AC_TYP == '4' 
                || BPB1187_AWA_1187.AC_TYP == '5')) {
                DDCIMCCY.DATA[1-1].AC = BPB1187_AWA_1187.C_P_NO;
            }
            if ((BPB1187_AWA_1187.AC_TYP == '6' 
                || BPB1187_AWA_1187.AC_TYP == '7')) {
                DDCIMCCY.DATA[1-1].AC = BPB1187_AWA_1187.CHQ_NO;
            }
            DDCIMCCY.DATA[1-1].CCY = BPB1187_AWA_1187.CHG_CCY;
            DDCIMCCY.DATA[1-1].CCY_TYPE = BPB1187_AWA_1187.CCY_TYPE;
            S000_CALL_DDZIMCCY();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPB1187_AWA_1187.AC_TYP == '0' 
                || BPB1187_AWA_1187.AC_TYP == '4' 
                || BPB1187_AWA_1187.AC_TYP == '5' 
                || BPB1187_AWA_1187.AC_TYP == '2' 
                || BPB1187_AWA_1187.AC_TYP == '6' 
                || BPB1187_AWA_1187.AC_TYP == '7') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CICACCU.DATA.AGR_NO = BPB1187_AWA_1187.FEE_AC;
                if (BPB1187_AWA_1187.AC_TYP == '2') {
                    CICACCU.DATA.AGR_NO = IBCQINF.INPUT_DATA.AC_NO;
                }
                if (BPB1187_AWA_1187.AC_TYP == '4' 
                    || BPB1187_AWA_1187.AC_TYP == '5') {
                    CICACCU.DATA.AGR_NO = BPB1187_AWA_1187.C_P_NO;
                }
                S000_CALL_CIZACCU_CN();
                if (((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    || BPB1187_AWA_1187.CI_NO.trim().length() > 0) 
                    && !CICACCU.DATA.CI_NO.equalsIgnoreCase(BPB1187_AWA_1187.CI_NO)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) 
                    && BPB1187_AWA_1187.CI_NO.trim().length() == 0) {
                    BPB1187_AWA_1187.CI_NO = CICACCU.DATA.CI_NO;
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
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPB1187_AWA_1187.AC_TYP;
        if (BPB1187_AWA_1187.AC_TYP == '1') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = " ";
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB1187_AWA_1187.FEE_AC;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB1187_AWA_1187.CHG_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = BPB1187_AWA_1187.CI_NO;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPB1187_AWA_1187.C_P_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHQ_NO = BPB1187_AWA_1187.CHQ_NO;
        BPCFFTXI.TX_DATA.SALE_DATE = BPB1187_AWA_1187.SALE_DT;
        BPCFFTXI.TX_DATA.FEE_CTRT = BPB1187_AWA_1187.FEE_CTRT;
        BPCFFTXI.TX_DATA.FEE_DPTM = BPB1187_AWA_1187.FEE_DPTM;
        BPCFFTXI.TX_DATA.FEE_CONT = BPB1187_AWA_1187.FEE_CONT;
        BPCFFTXI.TX_DATA.FEE_ADVICE = BPB1187_AWA_1187.FEE_ADVI;
        BPCFFTXI.TX_DATA.CCY_TYPE = BPB1187_AWA_1187.CCY_TYPE;
        BPCFFTXI.TX_DATA.BSNS_NO = BPB1187_AWA_1187.BSNS_NO;
        BPCFFTXI.TX_DATA.CREV_NO = BPB1187_AWA_1187.CREV_NO;
        if (BPB1187_AWA_1187.CHG_MOD == '1') {
            BPCFFTXI.TX_DATA.PROC_TYPE = '6';
        }
        S000_CALL_BPZFFTXI();
    }
    public void B020_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (BPCTFBAS.RETURN_INFO == 'N') {
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
    public void B025_GET_FEE_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPVFCOM.KEY.FEE_CODE = "" + WS_CNT;
        JIBS_tmp_int = BPVFCOM.KEY.FEE_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPVFCOM.KEY.FEE_CODE = "0" + BPVFCOM.KEY.FEE_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = " ";
        BPCFPARM.INFO.POINTER = BPVFCOM;
        BPCFPARM.INFO.FUNC = '3';
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCOM_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_BASIC_FEE() throws IOException,SQLException,Exception {
        BPCFFCAL.DATA.FEE_CODE = "" + WS_CNT;
        JIBS_tmp_int = BPCFFCAL.DATA.FEE_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCFFCAL.DATA.FEE_CODE = "0" + BPCFFCAL.DATA.FEE_CODE;
        BPCFFCAL.DATA.CNT_CCY = BPB1187_AWA_1187.FFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPB1187_AWA_1187.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPB1187_AWA_1187.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPB1187_AWA_1187.CHG_CCY;
        S000_CALL_BPZFFCAL();
    }
    public void B035_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB1187_AWA_1187.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB1187_AWA_1187.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB1187_AWA_1187.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB1187_AWA_1187.CI_NO;
        S000_CALL_BPZPIFAV();
    }
    public void B040_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT;
        }
        WS_FEE_AMT = WS_FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
        } else {
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
        }
    }
    public void B045_CHG_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            IBS.init(SCCGWA, BPCFFCON);
            BPCFFCON.FEE_INFO.FEE_CNT += 1;
            WS_ITM = BPCFFCON.FEE_INFO.FEE_CNT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = '0';
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE;
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPB1187_AWA_1187.PRD_CODE;
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.PROD_CODE1);
            if (BPB1187_AWA_1187.ADJ_AMT == 0) {
                BPB1187_AWA_1187.CFEE_CCY = BPB1187_AWA_1187.CHG_CCY;
                BPB1187_AWA_1187.ADJ_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
            }
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = BPB1187_AWA_1187.CFEE_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPB1187_AWA_1187.FEE_BAS;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.ACCOUNT_BR = BPB1187_AWA_1187.BR;
            BPCFFCON.FEE_INFO.EX_RATE = BPB1187_AWA_1187.RATE;
            BPCFFCON.FEE_INFO.TICKET_NO = BPB1187_AWA_1187.TICKET;
            BPCFFCON.FEE_INFO.REMARK = BPB1187_AWA_1187.RMK;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY = BPB1187_AWA_1187.AC_TYP;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = BPB1187_AWA_1187.CHG_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPB1187_AWA_1187.FEE_BAS;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].ADJ_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_FLG;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_STDT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_STDT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_EDDT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].AMO_EDDT;
            }
            if (BPB1187_AWA_1187.ACCT_CD == 999999999999) {
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].DIFF_FLG = 'Y';
            } else {
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].DIFF_FLG = 'N';
            }
            S000_CALL_BPZFFCON();
        }
    }
    public void B090_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCHGF);
        IBS.init(SCCGWA, CIRACR);
        if (BPB1187_AWA_1187.AC_TYP == '0') {
            CIRACR.KEY.AGR_NO = BPB1187_AWA_1187.FEE_AC;
        }
        if (BPB1187_AWA_1187.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB1187_AWA_1187.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            BPCOCHGF.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
            BPCOCHGF.AC_ENAME = CICCUST.O_DATA.O_CI_ENM;
        }
        BPCOCHGF.SVR_CD = BPB1187_AWA_1187.SVR_CD;
        BPCOCHGF.CHNL = BPB1187_AWA_1187.CHNL;
        BPCOCHGF.RGN_NO = BPB1187_AWA_1187.RGN_NO;
        BPCOCHGF.PRD_CODE = BPB1187_AWA_1187.PRD_CODE;
        BPCOCHGF.FREE_FMT = BPB1187_AWA_1187.FREE_FMT;
        BPCOCHGF.CFEE_CCY = BPB1187_AWA_1187.FFEE_CCY;
        BPCOCHGF.CI_NO = BPB1187_AWA_1187.CI_NO;
        BPCOCHGF.AC_NO = BPB1187_AWA_1187.AC_NO;
        for (WS_CNT = 1; ((WS_CNT <= 20) 
            && (BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE.trim().length() != 0)); WS_CNT += 1) {
            BPCOCHGF.INFO[WS_CNT-1].FEE_CODE = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE;
            BPCOCHGF.INFO[WS_CNT-1].FEE_AMT = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_AMT;
        }
        BPCOCHGF.FEE_DESC = BPRFBAS.FEE_DESC;
        BPCOCHGF.TX_MMO = BPRFBAS.TX_MMO;
        BPCOCHGF.ACC_CNT = BPB1187_AWA_1187.ACC_CNT;
        BPCOCHGF.TOT_AMT = BPB1187_AWA_1187.TOT_AMT;
        BPCOCHGF.CHG_FLG = BPB1187_AWA_1187.AC_TYP;
        BPCOCHGF.CHG_CCY = BPB1187_AWA_1187.CHG_CCY;
        BPCOCHGF.CHG_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT;
        BPCOCHGF.FEE_AC = BPB1187_AWA_1187.FEE_AC;
        BPCOCHGF.CHQ_NO = BPB1187_AWA_1187.CHQ_NO;
        BPCOCHGF.CR_BANK = BPB1187_AWA_1187.CR_BANK;
        BPCOCHGF.DR_BANK = BPB1187_AWA_1187.DR_BANK;
        BPCOCHGF.ACCT_CD = BPB1187_AWA_1187.ACCT_CD;
        BPCOCHGF.RMK = BPB1187_AWA_1187.RMK;
        BPCOCHGF.BR = BPB1187_AWA_1187.BR;
        BPCOCHGF.REMK = BPB1187_AWA_1187.REMK;
        BPCOCHGF.CARD_NO = BPB1187_AWA_1187.C_P_NO;
        BPCOCHGF.FEE_CTRT = BPB1187_AWA_1187.FEE_CTRT;
        BPCOCHGF.FEE_DPTM = BPB1187_AWA_1187.FEE_DPTM;
        BPCOCHGF.FEE_CONT = BPB1187_AWA_1187.FEE_CONT;
        BPCOCHGF.FEE_ADVICE = BPB1187_AWA_1187.FEE_ADVI;
        BPCOCHGF.CCY_TYPE = BPB1187_AWA_1187.CCY_TYPE;
        BPCOCHGF.BSNS_NO = BPB1187_AWA_1187.BSNS_NO;
        BPCOCHGF.CREV_NO = BPB1187_AWA_1187.CREV_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCHGF;
        SCCFMT.DATA_LEN = 1827;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void R010_GET_CHK_GLMST_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCICNGL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = "FEES";
        if (BPB1187_AWA_1187.BR != 0) {
            BPCQCNGL.DAT.INPUT.BR = BPB1187_AWA_1187.BR;
        }
        BPCICNGL.PROD_TYPE = BPB1187_AWA_1187.FEE_INF[WS_CNT-1].FEE_CODE;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCICNGL;
        S000_CALL_BPZQCNGL();
        if (BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO != 0) {
            IBS.init(SCCGWA, BPCUGLM);
            BPCUGLM.INFO.FUNC = 'I';
            BPCUGLM.DATA.KEY.TYP = "AMGLM";
            BPCUGLM.DATA.KEY.REDEFINES16.MSTNO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
            BPCUGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCUGLM.DATA.KEY.REDEFINES16);
            S000_CALL_BPZUGLM();
            if (BPCUGLM.DATA.DATA_TXT.REL_ITMS[8-1].ITM_NO.trim().length() == 0) {
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
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYP_NOT_DEF;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            WS_FLD_NO = BPB1187_AWA_1187.FEE_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            WS_FLD_NO = BPB1187_AWA_1187.C_P_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
        if (DDCIPSBK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIPSBK.RC);
            WS_FLD_NO = BPB1187_AWA_1187.C_P_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSBCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-BCHQ-PROC", DDCSBCHQ);
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
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
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
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
        CEP.TRC(SCCGWA, BPCOCHGF);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
