package com.hisun.IB;

import com.hisun.BP.*;
import com.hisun.VT.*;
import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZSEINT {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm IBTMST_RD;
    DBParm IBTINSH_RD;
    String TBL_IBTMST = "IBTMST  ";
    String TBL_IBTINSH = "IBTINSH ";
    String K_IB_PROD_MODEL = "IBDD";
    String K_OUTPUT_FMT = "IBB20";
    String K_OUTPUT_FMT1 = "IBT04";
    String WS_ERR_MSG = " ";
    int WS_SEQ = 0;
    double WS_DEL_AMT = 0;
    double WS_CNT_AMT = 0;
    double WS_SELL_AMT = 0;
    short WS_I = 0;
    double WS_ESET_AMT = 0;
    double WS_YJ_AMT = 0;
    char WS_REC_FLG = ' ';
    char WS_INSH_FLG = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCQINF IBCQINF = new IBCQINF();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    IBCINFHI IBCINFHI = new IBCINFHI();
    IBCSETCK IBCSETCK = new IBCSETCK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFX BPCFX = new BPCFX();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCSOSET IBCSOSET = new IBCSOSET();
    IBCUBAL IBCUBAL = new IBCUBAL();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    IBRMST IBRMST = new IBRMST();
    IBRINSH IBRINSH = new IBRINSH();
    SCCGWA SCCGWA;
    IBCSEINT IBCSEINT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, IBCSEINT IBCSEINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCSEINT = IBCSEINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZSEINT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_READ_MST();
        B030_UPD_MST();
        B040_UPD_IBTBALF();
        B050_PROC_VOUCHER();
        B060_WRITE_HIST();
        B070_WRITE_IBTINSH();
        B100_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCSEINT.AC_NO);
        CEP.TRC(SCCGWA, IBCSEINT.NOST_CD);
        CEP.TRC(SCCGWA, IBCSEINT.CCY);
        if (IBCSEINT.AC_NO.trim().length() == 0 
            && (IBCSEINT.NOST_CD.trim().length() == 0 
            || IBCSEINT.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCSEINT.SETT_DT);
        if (IBCSEINT.SETT_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SETTDT_NOT_GE_ACDT);
        }
        if (IBCSEINT.ASET_AMT >= 0 
            && IBCSEINT.ESET_AMT < 0 
            || IBCSEINT.ASET_AMT < 0 
            && IBCSEINT.ESET_AMT > 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_INVALID);
        }
    }
    public void B011_SETT_AMT_CHECK() throws IOException,SQLException,Exception {
        if (IBCSEINT.ASET_AMT != IBCSEINT.ESET_AMT) {
            WS_DEL_AMT = IBCSEINT.ASET_AMT - IBCSEINT.ESET_AMT;
            bigD = new BigDecimal(WS_DEL_AMT);
            WS_DEL_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        }
        if (WS_DEL_AMT != 0) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCSETCK);
            IBCSETCK.KEY.TYP = "PIB01";
            IBCSETCK.KEY.CD = "DIFCK";
            BPCPRMR.DAT_PTR = IBCSETCK;
            S000_CALL_SCSCPARM();
            WS_CNT_AMT = IBCSETCK.DATA_TXT.CNT_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].trim().length() > 0) {
                if (IBCSEINT.CCY.equalsIgnoreCase("156")) {
                    WS_SELL_AMT = WS_DEL_AMT;
                } else {
                    CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
                    CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                    BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
                    BPCFX.BUY_CCY = IBCSEINT.CCY;
                    BPCFX.BUY_AMT = WS_DEL_AMT;
                    BPCFX.SELL_CCY = BPCRBANK.LOC_CCY1;
                    S000_LINK_BPZSFX();
                    WS_SELL_AMT = BPCFX.SELL_AMT;
                }
                CEP.TRC(SCCGWA, WS_SELL_AMT);
                if (WS_SELL_AMT > WS_CNT_AMT) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ASAMT_THAN_ESAMT_MOR);
                }
            }
        }
    }
    public void B020_READ_MST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        CEP.TRC(SCCGWA, IBCSEINT.NOST_CD);
        CEP.TRC(SCCGWA, IBCSEINT.CCY);
        if (IBCSEINT.NOST_CD.trim().length() > 0 
            && IBCSEINT.AC_NO.trim().length() == 0) {
            IBRMST.NOSTRO_CODE = IBCSEINT.NOST_CD;
            IBRMST.CCY = IBCSEINT.CCY;
            T000_READ_IBTMST();
            CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
            IBCSEINT.AC_NO = IBRMST.KEY.AC_NO;
        }
        CEP.TRC(SCCGWA, IBCSEINT.AC_NO);
        IBRMST.KEY.AC_NO = IBCSEINT.AC_NO;
        T000_READ_UPD_IBTMST();
        if (WS_REC_FLG == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        CEP.TRC(SCCGWA, IBRMST.EFF_DATE);
        if (IBRMST.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EFFECTIVE_DATE);
        }
        CEP.TRC(SCCGWA, IBCSEINT.SETT_DT);
        if (IBCSEINT.SETT_DT < IBRMST.EFF_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_TH_EFF_DATE);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            CEP.TRC(SCCGWA, IBCSEINT.SETT_DT1);
            if (IBCSEINT.SETT_DT1 == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SETT_DATE1_M);
            }
            if (IBCSEINT.SETT_DT <= IBRMST.L_INTS_DT) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INP_DT_GE_LASTDT);
            }
        } else {
            if (IBCSEINT.SETT_DT != IBRMST.L_INTS_DT) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IS_NOT_LAS_DT);
            }
        }
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBCSEINT.AC_NO;
        S000_CALL_IBZQINF();
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
    }
    public void B020_01_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_SCSCPARM();
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG != 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
        }
    }
    public void B030_UPD_MST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCSEINT.ESET_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCSEINT.ASET_AMT;
            if (IBCSEINT.ESET_AMT > 0) {
                IBRMST.BUD_INT = IBRMST.BUD_INT - IBCSEINT.ESET_AMT;
            } else {
                IBRMST.BUD_INT1 = IBRMST.BUD_INT1 + IBCSEINT.ESET_AMT;
            }
        } else {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL - IBCSEINT.ASET_AMT;
            WS_ESET_AMT = IBCSEINT.ESET_AMT * 1;
            bigD = new BigDecimal(WS_ESET_AMT);
            WS_ESET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_ESET_AMT);
            if (WS_ESET_AMT > 0) {
                IBRMST.BUD_INT = IBRMST.BUD_INT + WS_ESET_AMT;
            } else {
                IBRMST.BUD_INT1 = IBRMST.BUD_INT1 - WS_ESET_AMT;
            }
        }
        CEP.TRC(SCCGWA, IBRMST.BUD_INT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRMST.L_INTS_DT = IBCSEINT.SETT_DT;
        } else {
            CEP.TRC(SCCGWA, IBCSEINT.AC_NO);
            CEP.TRC(SCCGWA, IBRINSH.REV_FLG);
            T000_READ_INSH_LAST2_SETDAT();
            if (WS_INSH_FLG == 'Y') {
                IBRMST.L_INTS_DT = IBRINSH.INTS_DATE;
                CEP.TRC(SCCGWA, IBRINSH.INTS_DATE);
                CEP.TRC(SCCGWA, IBRINSH.JRN_NO);
            } else {
                IBRMST.L_INTS_DT = 0;
            }
        }
        CEP.TRC(SCCGWA, IBRMST.L_BUD_INT);
        IBRMST.LAST_FI_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_IBTMST();
    }
    public void B040_UPD_IBTBALF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCUBAL);
        IBCUBAL.AC_NO = IBRMST.KEY.AC_NO;
        IBCUBAL.VALUE_DATE = IBCSEINT.SETT_DT;
        IBCUBAL.AMT = IBCSEINT.ASET_AMT;
        IBCUBAL.SIGN = 'D';
        IBCUBAL.FUNC = '1';
        S000_CALL_IBZUBAL();
    }
    public void B050_PROC_VOUCHER() throws IOException,SQLException,Exception {
        if (IBCSEINT.ESET_AMT != 0 
            || IBCSEINT.ASET_AMT != 0) {
            B050_01_GET_PROD_CODE();
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCPOEWA.DATA.BR_OLD = IBRMST.POST_CTR;
            BPCPOEWA.DATA.BR_NEW = IBRMST.POST_CTR;
            BPCPOEWA.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
            BPCPOEWA.DATA.AC_NO = IBRMST.ACO_AC;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = IBCSEINT.CCY;
            if (IBCSEINT.ESET_AMT >= 0) {
                BPCPOEWA.DATA.EVENT_CODE = "IS";
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = IBCSEINT.ESET_AMT;
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "IN";
                BPCPOEWA.DATA.AMT_INFO[24-1].AMT = IBCSEINT.ESET_AMT * -1;
            }
            if (IBCSEINT.ASET_AMT > 0) {
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = IBCSEINT.ASET_AMT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[25-1].AMT = IBCSEINT.ASET_AMT * -1;
            }
            WS_YJ_AMT = BPCPOEWA.DATA.AMT_INFO[4-1].AMT - BPCPOEWA.DATA.AMT_INFO[2-1].AMT;
            if (WS_YJ_AMT != 0) {
                R000_GET_TAX_AMT();
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
            }
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.PROD_CODE = IBRMST.PROD_CD;
            BPCPOEWA.DATA.POST_NARR = "TRF BAL - IB IS";
            BPCPOEWA.DATA.DESC = "TRF BAL - IB IS";
            S000_CALL_BPZPOEWA();
        }
    }
    public void B050_01_GET_PROD_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = IBCQINF.OUTPUT_DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(K_IB_PROD_MODEL)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_PROD_TYP);
        }
    }
    public void B060_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCINFHI);
        IBCINFHI.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCINFHI.NOSTRO_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCINFHI.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCINFHI.PRV_FLAG = IBCQINF.OUTPUT_DATA.PRV_FLAG;
        IBCINFHI.RATE_FLG = IBCQINF.OUTPUT_DATA.RATE_FLAG;
        IBCINFHI.RATE_TYP = IBCQINF.OUTPUT_DATA.RATE_TYPE;
        IBCINFHI.RATE_TER = IBCQINF.OUTPUT_DATA.RATE_TERM;
        IBCINFHI.RATE_PCT = IBCQINF.OUTPUT_DATA.RATE_PCT;
        IBCINFHI.RATE_SPR = IBCQINF.OUTPUT_DATA.RATE_SPREAD;
        IBCINFHI.RATE = IBCQINF.OUTPUT_DATA.RATE;
        IBCINFHI.CALR_STD = IBCQINF.OUTPUT_DATA.CALR_STD;
        IBCINFHI.SETT_DT = IBCSEINT.SETT_DT;
        IBCINFHI.ESET_AMT = IBCSEINT.ESET_AMT;
        IBCINFHI.ASET_AMT = IBCSEINT.ASET_AMT;
        IBCINFHI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBCINFHI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPFHIS.DATA.AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.ACO_AC = IBRMST.ACO_AC;
        BPCPFHIS.DATA.RLT_AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.RLT_AC_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        B060_01_GET_BR_NAME();
        BPCPFHIS.DATA.TX_CCY = IBCSEINT.CCY;
        if (IBCSEINT.CCY.equalsIgnoreCase("156")) {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        } else {
            BPCPFHIS.DATA.TX_CCY_TYP = '2';
        }
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_AMT = IBCSEINT.ASET_AMT;
        BPCPFHIS.DATA.TX_VAL_DT = IBCSEINT.SETT_DT;
        BPCPFHIS.DATA.PROD_CD = IBRMST.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = BPCPQPRD.PRDT_MODEL;
        BPCPFHIS.DATA.TX_MMO = "S109";
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.REF_NO = "INT SETT HISTORY";
        BPCPFHIS.DATA.NARRATIVE = "INT SETT HISTORY";
        BPCPFHIS.DATA.REMARK = "INT SETT HISTORY";
        BPCPFHIS.DATA.FMT_ID = "IBCINFHI";
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT1;
        BPCPFHIS.DATA.FMT_LEN = 143;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBCINFHI);
        S000_CALL_BPZPFHIS();
    }
    public void B060_01_GET_BR_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBRMST.POST_CTR;
        S000_CALL_BPZPQORG();
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BK_NM);
    }
    public void B070_WRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBCSEINT.AC_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            WS_SEQ = 0;
            IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IBTINSH_FIRST();
            CEP.TRC(SCCGWA, WS_SEQ);
            IBRINSH.KEY.SEQ = WS_SEQ;
            IBRINSH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            IBRINSH.INTS_DATE = IBCSEINT.SETT_DT;
            IBRINSH.ESET_AMT = IBCSEINT.ESET_AMT;
            IBRINSH.ASET_AMT = IBCSEINT.ASET_AMT;
            IBRINSH.SETT_TYPE = '1';
            IBRINSH.SETT_AC_NO = IBCSEINT.AC_NO;
            IBRINSH.SETT_AC_TYPE = IBCQINF.OUTPUT_DATA.AC_ATTR;
            IBRINSH.REV_FLG = 'N';
            IBRINSH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_IBTINSH();
        } else {
            T000_READ_INSH_LAST_SETDAT();
            if (WS_INSH_FLG == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INSH);
            }
            CEP.TRC(SCCGWA, IBRINSH.KEY.AC_NO);
            CEP.TRC(SCCGWA, IBRINSH.KEY.DEAL_DATE);
            CEP.TRC(SCCGWA, IBRINSH.KEY.SEQ);
            CEP.TRC(SCCGWA, IBRINSH.JRN_NO);
            T000_READUPD_IBTINSH();
            if (WS_INSH_FLG == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
            }
            IBRINSH.REV_FLG = 'R';
            IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_IBTINSH();
        }
    }
    public void B100_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCSOSET);
        IBCSOSET.NOSTRO_CODE = IBRMST.NOSTRO_CODE;
        IBCSOSET.CCY = IBRMST.CCY;
        IBCSOSET.AC_NO = IBRMST.KEY.AC_NO;
        IBCSOSET.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCSOSET.INTS_DATE = IBCSEINT.SETT_DT;
        IBCSOSET.ASET_AMT = IBCSEINT.ASET_AMT;
        IBCSOSET.ESET_AMT = IBCSEINT.ESET_AMT;
        IBCSOSET.RATE_FLAG = IBCQINF.OUTPUT_DATA.RATE_FLAG;
        IBCSOSET.PRV_FLAG = IBCQINF.OUTPUT_DATA.PRV_FLAG;
        IBCSOSET.RATE_MTH = IBCQINF.OUTPUT_DATA.RATE_MTH;
        IBCSOSET.RATE_TYPE = IBCQINF.OUTPUT_DATA.RATE_TYPE.charAt(0);
        IBCSOSET.RATE_TERM = IBCQINF.OUTPUT_DATA.RATE_TERM;
        IBCSOSET.RATE_PCT = IBCQINF.OUTPUT_DATA.RATE_PCT;
        IBCSOSET.RATE_SPREAD = IBCQINF.OUTPUT_DATA.RATE_SPREAD;
        IBCSOSET.RATE = IBCQINF.OUTPUT_DATA.RATE;
        IBCSOSET.OD_RATE = IBCQINF.OUTPUT_DATA.OD_RATE;
        IBCSOSET.CALR_STD = IBCQINF.OUTPUT_DATA.CALR_STD;
        IBCSOSET.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCSOSET.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCSOSET;
        SCCFMT.DATA_LEN = 420;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_TAX_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.BR = IBRMST.POST_CTR;
        VTCPQTAX.INPUT_DATA.PROD_CD = IBRMST.PROD_CD;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "IBDD";
        VTCPQTAX.INPUT_DATA.CCY = IBRMST.CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = WS_YJ_AMT;
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.SET_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = VTCPQTAX.INPUT_DATA.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) VTCPQTAX.INPUT_DATA.SET_NO = "0" + VTCPQTAX.INPUT_DATA.SET_NO;
        VTCPQTAX.INPUT_DATA.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.AC = IBRMST.ACO_AC;
        VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'N';
        S000_CALL_VTZPQTAX();
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZUBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-UPD-BAL", IBCUBAL);
        if (IBCUBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCUBAL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSCPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND CCY = :IBRMST.CCY";
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_UPD_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_UPDATE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBS.WRITE(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_INSH_LAST_SETDAT() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND REV_FLG = 'N'";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "DEAL_DATE DESC, SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INSH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_INSH_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_INSH_LAST2_SETDAT() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBCSEINT.AC_NO "
            + "AND INTS_DATE < :IBCSEINT.SETT_DT "
            + "AND REV_FLG = 'N'";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "INTS_DATE DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_INSH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_INSH_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTINSH_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBRINSH.KEY.DEAL_DATE);
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND DEAL_DATE = :IBRINSH.KEY.DEAL_DATE";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "1");
            CEP.TRC(SCCGWA, IBRINSH.KEY.SEQ);
            WS_SEQ = IBRINSH.KEY.SEQ + 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SEQ = WS_SEQ + 1;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READUPD_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.upd = true;
        IBS.READ(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INSH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_INSH_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBS.REWRITE(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
