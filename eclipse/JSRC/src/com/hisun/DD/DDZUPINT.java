package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.VT.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUPINT {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTVCH_RD;
    DBParm DDTMSTR_RD;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "0003";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ";
    String CPN_I_ACCU_TOT = "DD-I-ACCU-TOT";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC   ";
    String WS_ERR_MSG = " ";
    double WS_TX_AMT = 0;
    double WS_TMP_AMT = 0;
    double WS_VCH_AMT = 0;
    double WS_YJ_AMT = 0;
    double WS_NRA_TAX = 0;
    double WS_SPC_TAX = 0;
    double WS_OD_TAX = 0;
    short WS_POS = 0;
    char WS_SIGN = ' ';
    DDZUPINT_WS_INT_INFO WS_INT_INFO = new DDZUPINT_WS_INT_INFO();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMSTR DDRMSTR = new DDRMSTR();
    CICACCU CICACCU = new CICACCU();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCITOT DDCITOT = new DDCITOT();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DCCITRSR DCCITRSR = new DCCITRSR();
    DDRVCH DDRVCH = new DDRVCH();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    SCCGWA SCCGWA;
    DDCUPINT DDCUPINT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCUPINT DDCUPINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUPINT = DDCUPINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUPINT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_GET_AC_INF_PROC();
        B035_CHK_AC_STS();
        B040_CI_INF_PROC();
        B075_GET_CCY_INF_PROC();
        B070_GET_PRD_INF_PROC();
        B080_UPD_TOT_PROC();
        B085_CAL_INT_PROC();
        if (DDCUPINT.TX_TYP != 'I') {
            B090_UPD_BAL_PROC();
            B095_UPD_CCY_INF_PROC();
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                B098_UPD_MST_INF_PROC();
                B100_UPD_PSBK_ITEM();
            }
            B170_FIN_TX_HIS_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B180_REG_TAX_PROC();
                B190_GEN_VCH_PROC();
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUPINT.TX_MMO);
        CEP.TRC(SCCGWA, DDCUPINT.AC);
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CARD_NO);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        if (DDCUPINT.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUPINT.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCUPINT.AC;
        CICQACAC.DATA.CCY_ACAC = DDCUPINT.CCY;
        CICQACAC.DATA.CR_FLG = DDCUPINT.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCUPINT.AC;
            T000_READ_UPDATE_DDTMST();
        }
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'O') {
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUPINT.AC;
        S000_CALL_CIZACCU();
    }
    public void B070_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCUPINT.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, "DDVMRAT");
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
    }
    public void B075_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        T000_READ_UPDATE_DDTCCY();
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B080_UPD_TOT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUPINT.TX_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if ((DDCUPINT.TX_TYP == 'O' 
            || DDCUPINT.TX_TYP == 'I' 
            || DDCUPINT.TX_TYP == 'C')) {
            IBS.init(SCCGWA, DDCITOT);
            DDCITOT.AC = DDCUPINT.AC;
            DDCITOT.CCY = DDCUPINT.CCY;
            DDCITOT.CCY_TYPE = DDCUPINT.CCY_TYPE;
            DDCITOT.PROD_CD = DDRCCY.PROD_CODE;
            DDCITOT.CURR_BAL = DDRCCY.LAST_DAYEND_BAL;
            DDCITOT.NINT_BAL = DDRCCY.NOT_INT_BAL;
            DDCITOT.BR = DDRCCY.OWNER_BRDP;
            if (DDCUPINT.TX_TYP == 'C') {
                DDCITOT.FUNC = 'C';
            }
            if (DDCUPINT.TX_TYP == 'O') {
                DDCITOT.FUNC = 'P';
            }
            if (DDCUPINT.TX_TYP == 'I') {
                DDCITOT.FUNC = 'I';
            }
            CEP.TRC(SCCGWA, DDCITOT.FUNC);
            S000_CALL_DDZITOT();
            if (DDRCCY.CINT_FLG == 'N') {
                DDCITOT.OUTPUT_INFO.DEP_INT = 0;
                DDCITOT.OUTPUT_INFO.SPC_INT = 0;
                DDCITOT.OUTPUT_INFO.NRA_TAX = 0;
                DDCITOT.OUTPUT_INFO.TAX = 0;
                DDCITOT.OUTPUT_INFO.OD_INT = 0;
                DDCITOT.OUTPUT_INFO.UOD_INT = 0;
            }
        }
    }
    public void B085_CAL_INT_PROC() throws IOException,SQLException,Exception {
        if (DDCUPINT.TX_TYP == 'O' 
            || DDCUPINT.TX_TYP == 'C') {
            WS_INT_INFO.WS_DEP_INT = DDCITOT.OUTPUT_INFO.DEP_INT;
            WS_INT_INFO.WS_SPC_INT = DDCITOT.OUTPUT_INFO.SPC_INT;
            WS_NRA_TAX = DDCITOT.OUTPUT_INFO.NRA_TAX;
            WS_INT_INFO.WS_INT_TAX = DDCITOT.OUTPUT_INFO.TAX;
            WS_INT_INFO.WS_OD_INT = DDCITOT.OUTPUT_INFO.OD_INT;
            WS_INT_INFO.WS_UOD_INT = DDCITOT.OUTPUT_INFO.UOD_INT;
        }
        if (DDCUPINT.TX_TYP == 'B' 
            || (DDCUPINT.TX_TYP == 'O' 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y')) {
            WS_INT_INFO.WS_DEP_INT = DDCUPINT.DEP_INT;
            WS_NRA_TAX = DDCUPINT.NRA_TAX;
            WS_INT_INFO.WS_INT_TAX = DDCUPINT.INT_TAX;
            WS_INT_INFO.WS_OD_INT = DDCUPINT.OD_INT;
            WS_INT_INFO.WS_UOD_INT = DDCUPINT.UOD_INT;
        }
        if (DDCUPINT.TX_TYP == 'I') {
            if (DDRMST.AC_STS == 'M' 
                || DDRCCY.CINT_FLG == 'N') {
                DDCUPINT.DEP_INT = 0;
                DDCUPINT.INT_TAX = 0;
                DDCUPINT.OD_INT = 0;
                DDCUPINT.UOD_INT = 0;
            } else {
                DDCUPINT.DEP_INT = DDCITOT.OUTPUT_INFO.DEP_INT;
                DDCUPINT.DEP_INT += DDCITOT.OUTPUT_INFO.SPC_INT;
                DDCUPINT.NRA_TAX = DDCITOT.OUTPUT_INFO.NRA_TAX;
                DDCUPINT.INT_TAX = DDCITOT.OUTPUT_INFO.TAX;
                DDCUPINT.OD_INT = DDCITOT.OUTPUT_INFO.OD_INT;
                DDCUPINT.UOD_INT = DDCITOT.OUTPUT_INFO.UOD_INT;
            }
        }
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_DEP_INT);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_SPC_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, WS_NRA_TAX);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_INT_TAX);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_OD_INT);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_UOD_INT);
    }
    public void B090_UPD_BAL_PROC() throws IOException,SQLException,Exception {
        if (WS_INT_INFO.WS_DEP_INT != 0 
            || WS_INT_INFO.WS_SPC_INT != 0) {
            WS_INT_INFO.WS_INT_AMT = WS_INT_INFO.WS_DEP_INT - WS_INT_INFO.WS_INT_TAX - WS_NRA_TAX + WS_INT_INFO.WS_SPC_INT;
            bigD = new BigDecimal(WS_INT_INFO.WS_INT_AMT);
            WS_INT_INFO.WS_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, "DEP INT");
            B090_1_POST_INT_PROC();
        }
        if (WS_INT_INFO.WS_OD_INT != 0 
            || WS_INT_INFO.WS_UOD_INT != 0) {
            WS_INT_INFO.WS_INT_AMT = WS_INT_INFO.WS_OD_INT + WS_INT_INFO.WS_UOD_INT;
            bigD = new BigDecimal(WS_INT_INFO.WS_INT_AMT);
            WS_INT_INFO.WS_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, "OD INT");
            B090_1_POST_INT_PROC();
        }
    }
    public void B090_1_POST_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDRCCY.LAST_BAL_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    CEP.TRC(SCCGWA, "BBBBBBBBBBBB");
                    DDRCCY.LAST_BAL = DDRCCY.LAST_BAL + WS_INT_INFO.WS_INT_AMT;
                } else {
                    DDRCCY.LAST_BAL = DDRCCY.LAST_BAL - WS_INT_INFO.WS_INT_AMT;
                }
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, WS_INT_INFO.WS_INT_AMT);
            DDRCCY.CURR_BAL = DDRCCY.CURR_BAL + WS_INT_INFO.WS_INT_AMT;
            if (DDRCCY.CURR_BAL < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
        } else {
            DDRCCY.CURR_BAL = DDRCCY.CURR_BAL - WS_INT_INFO.WS_INT_AMT;
            if (DDRCCY.CURR_BAL < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B095_UPD_CCY_INF_PROC() throws IOException,SQLException,Exception {
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
            DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
            DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
            DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
            DDRCCY.DRW_CAMT = 0;
            DDRCCY.DEP_CAMT = 0;
            DDRCCY.DRW_TAMT = 0;
            DDRCCY.DEP_TAMT = 0;
        }
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_INT_AMT);
        if (WS_INT_INFO.WS_INT_AMT > 0) {
            IBS.init(SCCGWA, DCCITRSR);
            DCCITRSR.INP_DATA.OPR = 'T';
            DCCITRSR.INP_DATA.AC = DDCUPINT.AC;
            DCCITRSR.INP_DATA.CCY = DDCUPINT.CCY;
            DCCITRSR.INP_DATA.CCY_TYPE = DDCUPINT.CCY_TYPE;
            DCCITRSR.INP_DATA.DRCR_FLG = 'C';
            DCCITRSR.INP_DATA.TRS_AMT = WS_INT_INFO.WS_INT_AMT;
            DCCITRSR.INP_DATA.APP = "DD";
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.OPR);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.AC);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY_TYPE);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.DRCR_FLG);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.TRS_AMT);
            CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.APP);
            S000_CALL_DCZITRSR();
        }
        if (WS_INT_INFO.WS_SPC_INT < 0) {
            WS_INT_INFO.WS_SPC_INT = 0 - WS_INT_INFO.WS_SPC_INT;
        }
        if (WS_INT_INFO.WS_OD_INT < 0) {
            WS_INT_INFO.WS_OD_INT = 0 - WS_INT_INFO.WS_OD_INT;
        }
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
    }
    public void B100_UPD_PSBK_ITEM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUPINT.AC;
        DDRVCH.VCH_TYPE = '1';
        T000_READ_UPDATE_DDTVCH();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_INT_AMT);
        if (DDRVCH.PSBK_NO.trim().length() > 0) {
            if (DDRVCH.PSBK_NO.trim().length() > 0 
                && WS_INT_INFO.WS_DEP_INT != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = DDCUPINT.TX_MMO;
                DDCIPSBK.UPT_AMT = WS_INT_INFO.WS_DEP_INT;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                }
                S000_CALL_DDZIPSBK();
            }
            CEP.TRC(SCCGWA, WS_INT_INFO.WS_SPC_INT);
            if (WS_INT_INFO.WS_SPC_INT != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = DDCUPINT.TX_MMO;
                DDCIPSBK.UPT_AMT = WS_INT_INFO.WS_SPC_INT;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                }
                S000_CALL_DDZIPSBK();
            }
            CEP.TRC(SCCGWA, WS_NRA_TAX);
            if (WS_NRA_TAX != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = "TAX";
                DDCIPSBK.UPT_AMT = WS_NRA_TAX;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                }
                S000_CALL_DDZIPSBK();
            }
            CEP.TRC(SCCGWA, WS_INT_INFO.WS_INT_TAX);
            if (WS_INT_INFO.WS_INT_TAX != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = "TAX";
                DDCIPSBK.UPT_AMT = DDCUPINT.INT_TAX;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                }
                S000_CALL_DDZIPSBK();
            }
            CEP.TRC(SCCGWA, WS_INT_INFO.WS_OD_INT);
            if (WS_INT_INFO.WS_OD_INT != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = DDCUPINT.TX_MMO;
                DDCIPSBK.UPT_AMT = WS_INT_INFO.WS_OD_INT;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                }
                S000_CALL_DDZIPSBK();
            }
            CEP.TRC(SCCGWA, WS_INT_INFO.WS_UOD_INT);
            if (WS_INT_INFO.WS_UOD_INT != 0) {
                IBS.init(SCCGWA, DDCIPSBK);
                DDCIPSBK.FUNC = 'T';
                DDCIPSBK.AC = DDCUPINT.AC;
                DDCIPSBK.UPT_CCY = DDCUPINT.CCY;
                DDCIPSBK.UPT_CCY_TYPE = DDCUPINT.CCY_TYPE;
                DDCIPSBK.UPT_MMO = DDCUPINT.TX_MMO;
                DDCIPSBK.UPT_AMT = DDCUPINT.UOD_INT;
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDCIPSBK.UPT_TXN_TYPE = 'C';
                } else {
                    DDCIPSBK.UPT_TXN_TYPE = 'D';
                }
                S000_CALL_DDZIPSBK();
            }
        }
    }
    public void B170_FIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        if (WS_INT_INFO.WS_DEP_INT != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_INT_INFO.WS_DEP_INT;
            BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            R000_TRANS_HIS_DATA();
        }
        if (WS_INT_INFO.WS_SPC_INT != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_INT_INFO.WS_SPC_INT;
            if (WS_INT_INFO.WS_SPC_INT > 0) {
                WS_SIGN = 'D';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                WS_SIGN = 'C';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
            R000_TRANS_HIS_DATA();
        }
        if (WS_NRA_TAX != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_NRA_TAX;
            if (WS_NRA_TAX > 0) {
                WS_SIGN = 'D';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                WS_SIGN = 'C';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
            R000_TRANS_HIS_DATA();
        }
        if (WS_INT_INFO.WS_INT_TAX != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_INT_INFO.WS_INT_TAX;
            if (WS_INT_INFO.WS_INT_TAX > 0) {
                WS_SIGN = 'D';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                WS_SIGN = 'C';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
            R000_TRANS_HIS_DATA();
        }
        if (WS_INT_INFO.WS_OD_INT != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_INT_INFO.WS_OD_INT;
            if (WS_INT_INFO.WS_OD_INT > 0) {
                WS_SIGN = 'D';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                WS_SIGN = 'C';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
            R000_TRANS_HIS_DATA();
        }
        if (WS_INT_INFO.WS_UOD_INT != 0) {
            IBS.init(SCCGWA, BPCPFHIS);
            WS_TX_AMT = WS_INT_INFO.WS_UOD_INT;
            if (WS_INT_INFO.WS_UOD_INT > 0) {
                WS_SIGN = 'D';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            } else {
                WS_SIGN = 'C';
                BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            }
            R000_TRANS_HIS_DATA();
        }
    }
    public void B180_REG_TAX_PROC() throws IOException,SQLException,Exception {
        if (WS_INT_INFO.WS_OD_INT > 0) {
            WS_YJ_AMT = WS_INT_INFO.WS_OD_INT;
            C180_REG_TAX_PROC();
            WS_OD_TAX = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        }
    }
    public void C180_REG_TAX_PROC() throws IOException,SQLException,Exception {
        if (WS_YJ_AMT > 0) {
            IBS.init(SCCGWA, VTCPQTAX);
            VTCPQTAX.INPUT_DATA.BR = DDRCCY.OWNER_BR;
            VTCPQTAX.INPUT_DATA.PROD_CD = DDRCCY.PROD_CODE;
            VTCPQTAX.INPUT_DATA.CNTR_TYPE = "CAAC";
            VTCPQTAX.INPUT_DATA.AC = DDRCCY.KEY.AC;
            VTCPQTAX.INPUT_DATA.CCY = DDRCCY.CCY;
            VTCPQTAX.INPUT_DATA.YJ_AMT = WS_YJ_AMT;
            VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTCPQTAX.INPUT_DATA.SET_NO = SCCGWA.COMM_AREA.VCH_NO;
            VTCPQTAX.INPUT_DATA.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
            VTCPQTAX.INPUT_DATA.TR_BR = DDRCCY.OWNER_BR;
            VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'N';
            S000_CALL_VTZPQTAX();
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void B190_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_DEP_INT);
        CEP.TRC(SCCGWA, WS_INT_INFO.WS_SPC_INT);
        CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_DEP_INT);
        CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_SPC_INT);
        if (WS_INT_INFO.WS_DEP_INT != 0 
            || DDCITOT.OUTPUT_INFO.ACCU_DEP_INT != 0 
            || WS_INT_INFO.WS_OD_INT != 0 
            || DDCITOT.OUTPUT_INFO.ACCU_OD_INT != 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
            BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
            BPCPOEWA.DATA.EVENT_CODE = "IS";
            BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
            BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUPINT.CCY;
            if (BPCPOEWA.DATA.FILLER == null) BPCPOEWA.DATA.FILLER = "";
            JIBS_tmp_int = BPCPOEWA.DATA.FILLER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPCPOEWA.DATA.FILLER += " ";
            JIBS_tmp_str[0] = "" + DDCUPINT.CCY_TYPE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCPOEWA.DATA.FILLER = JIBS_tmp_str[0] + BPCPOEWA.DATA.FILLER.substring(1);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
            R000_INQ_MSTR_INFO();
            if (DDCUPINT.TX_TYP == 'B') {
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("4") 
                    || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5") 
                    || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("6")) {
                    if (DDCUPINT.LST_DEP_INT > 0) {
                        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = DDCUPINT.LST_DEP_INT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[15-1].AMT = 0 - DDCUPINT.LST_DEP_INT;
                    }
                } else {
                    if (DDCUPINT.LST_DEP_INT > 0) {
                        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = DDCUPINT.LST_DEP_INT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[13-1].AMT = 0 - DDCUPINT.LST_DEP_INT;
                    }
                }
                BPCPOEWA.DATA.AMT_INFO[22-1].AMT = 0 - DDCITOT.OUTPUT_INFO.ACCU_OD_INT;
            } else {
                CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_INT);
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("4") 
                    || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5") 
                    || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("6")) {
                    if (DDCITOT.OUTPUT_INFO.ACCU_INT > 0) {
                        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = DDCITOT.OUTPUT_INFO.ACCU_INT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[15-1].AMT = 0 - DDCITOT.OUTPUT_INFO.ACCU_DEP_INT;
                    }
                } else {
                    if (DDCITOT.OUTPUT_INFO.ACCU_INT > 0) {
                        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = DDCITOT.OUTPUT_INFO.ACCU_INT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[13-1].AMT = 0 - DDCITOT.OUTPUT_INFO.ACCU_INT;
                    }
                }
                CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_OD_INT);
                if (DDCITOT.OUTPUT_INFO.ACCU_OD_INT > 0) {
                    BPCPOEWA.DATA.AMT_INFO[27-1].AMT = DDCITOT.OUTPUT_INFO.ACCU_OD_INT;
                } else {
                    BPCPOEWA.DATA.AMT_INFO[22-1].AMT = 0 - DDCITOT.OUTPUT_INFO.ACCU_OD_INT;
                }
            }
            if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("4") 
                || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5") 
                || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("6")) {
                if (WS_INT_INFO.WS_DEP_INT > 0) {
                    BPCPOEWA.DATA.AMT_INFO[10-1].AMT = WS_INT_INFO.WS_DEP_INT;
                } else {
                    BPCPOEWA.DATA.AMT_INFO[16-1].AMT = 0 - WS_INT_INFO.WS_DEP_INT;
                }
            } else {
                if (WS_INT_INFO.WS_DEP_INT > 0) {
                    BPCPOEWA.DATA.AMT_INFO[4-1].AMT = WS_INT_INFO.WS_DEP_INT;
                } else {
                    BPCPOEWA.DATA.AMT_INFO[14-1].AMT = 0 - WS_INT_INFO.WS_DEP_INT;
                }
            }
            BPCPOEWA.DATA.AMT_INFO[3-1].AMT = WS_INT_INFO.WS_INT_TAX;
            BPCPOEWA.DATA.AMT_INFO[6-1].AMT = WS_NRA_TAX;
            if (WS_INT_INFO.WS_OD_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[23-1].AMT = WS_INT_INFO.WS_OD_INT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[28-1].AMT = 0 - WS_INT_INFO.WS_OD_INT;
            }
            if (WS_OD_TAX > 0) {
                BPCPOEWA.DATA.AMT_INFO[24-1].AMT = WS_OD_TAX;
            } else {
                BPCPOEWA.DATA.AMT_INFO[29-1].AMT = 0 - WS_OD_TAX;
            }
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.AC_NO = DDCUPINT.AC;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
            BPCPOEWA.DATA.REF_NO = DDCUPINT.TX_REF;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[13-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[14-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[9-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[10-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[15-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[16-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[31-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[32-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[33-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[34-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[12-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[22-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[23-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[24-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[27-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[28-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[29-1].AMT);
            S000_CALL_BPZPOEWA();
        }
        if (WS_INT_INFO.WS_SPC_INT != 0 
            || DDCITOT.OUTPUT_INFO.ACCU_SPC_INT != 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
            BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
            BPCPOEWA.DATA.EVENT_CODE = "IN";
            BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
            BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUPINT.CCY;
            if (BPCPOEWA.DATA.FILLER == null) BPCPOEWA.DATA.FILLER = "";
            JIBS_tmp_int = BPCPOEWA.DATA.FILLER.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPCPOEWA.DATA.FILLER += " ";
            JIBS_tmp_str[0] = "" + DDCUPINT.CCY_TYPE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCPOEWA.DATA.FILLER = JIBS_tmp_str[0] + BPCPOEWA.DATA.FILLER.substring(1);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
            if (DDCITOT.OUTPUT_INFO.ACCU_SPC_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[15-1].AMT = DDCITOT.OUTPUT_INFO.ACCU_SPC_INT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[15-1].AMT = 0 - DDCITOT.OUTPUT_INFO.ACCU_SPC_INT;
            }
            if (WS_INT_INFO.WS_SPC_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[16-1].AMT = WS_INT_INFO.WS_SPC_INT;
            }
            if (WS_SPC_TAX > 0) {
                BPCPOEWA.DATA.AMT_INFO[24-1].AMT = WS_SPC_TAX;
            } else {
                BPCPOEWA.DATA.AMT_INFO[29-1].AMT = 0 - WS_SPC_TAX;
            }
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.AC_NO = DDCUPINT.AC;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
            BPCPOEWA.DATA.REF_NO = DDCUPINT.TX_REF;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[15-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[16-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[24-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[29-1].AMT);
            S000_CALL_BPZPOEWA();
        }
    }
    public void R000_INQ_MSTR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = 'O';
        T000_READ_DDTMSTR();
        CEP.TRC(SCCGWA, DDRMSTR.KEY.ADP_TYPE);
    }
    public void R000_TRANS_HIS_DATA() throws IOException,SQLException,Exception {
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = DDCUPINT.TX_REF;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.AC = DDCUPINT.AC;
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = DDCUPINT.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUPINT.CCY_TYPE;
        BPCPFHIS.DATA.TX_AMT = WS_TX_AMT;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.TX_TOOL = DDCUPINT.CARD_NO;
        CEP.TRC(SCCGWA, DDCUPINT.TX_MMO);
        if (DDCUPINT.TX_MMO.trim().length() > 0) {
            BPCPFHIS.DATA.TX_MMO = DDCUPINT.TX_MMO;
        } else {
            if (DDCUPINT.TX_TYP == 'O' 
                || DDCUPINT.TX_TYP == 'C') {
                BPCPFHIS.DATA.TX_MMO = "S101";
            }
        }
        BPCPFHIS.DATA.REMARK = DDCUPINT.REMARK;
        BPCPFHIS.DATA.REMARK = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.NARRATIVE = DDCUPINT.NARRATIVE;
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.VAL_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.VAL_BAL);
        IBS.init(SCCGWA, DDRFHIS);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = DDCUPINT.AC;
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        DDRFHIS.TX_AMT = WS_TX_AMT;
        DDRFHIS.DOMBR = DDRMST.OWNER_BRDP;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDCUPINT.CCY;
        DDRFHIS.CRDR_FLG = WS_SIGN;
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.REF = DDCUPINT.TX_REF;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = 0;
        DDRFHIS.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, WS_SIGN);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZITOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_ACCU_TOT, DDCITOT);
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        CEP.TRC(SCCGWA, DCCITRSR.RC);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_INTR_INQ, BPCCINTI);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_UPDATE_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.eqWhere = "AC, ADP_STS";
        DDTMSTR_RD.where = "ADP_STRDATE <= :SCCGWA.COMM_AREA.AC_DATE";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
