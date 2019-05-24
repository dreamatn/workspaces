package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DP.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUHDRA {
    DBParm DDTNOSI_RD;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMSTR_RD;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_I_ACCU_TOT = "DD-I-ACCU-TOT";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String K_AP_MMO = "DD";
    String K_CASH_CR_AMT_TBL = "0001";
    String K_CASH_DR_AMT_TBL = "0002";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_PC_TR_AMT_TBL = "0004";
    String K_AGT_TYPE = "141290001013";
    String K_OBJ_SYSTEM = "KHMS";
    String K_SERV_CODE = "BAT004";
    String WS_ERR_MSG = " ";
    double WS_TOT_CAMT = 0;
    double WS_TOT_TAMT = 0;
    char WS_TMP_ACATTR = ' ';
    double WS_CURR_BAL = 0;
    String WS_PROD_CODE = " ";
    char WS_SETL_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCITRSR DCCITRSR = new DCCITRSR();
    DDRNOSI DDRNOSI = new DDRNOSI();
    CICMAGT CICMAGT = new CICMAGT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCNKHMS DDCNKHMS = new DDCNKHMS();
    DCCUTCGD DCCUTCGD = new DCCUTCGD();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    DDCSMHQB DDCSMHQB = new DDCSMHQB();
    SCCGWA SCCGWA;
    DDCUHDRA DDCUHDRA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCUHDRA DDCUHDRA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUHDRA = DDCUHDRA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUHDRA return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B065_GET_ACAC_INFO();
        B020_GET_AC_INF_PROC();
        B040_GET_CI_INF_PROC();
        B060_GET_PRD_INF_PROC();
        B075_GET_CCY_INF_PROC();
        B080_CHECK_CCAL_BAL_PROC();
        B090_UPD_BAL_PROC();
        B095_UPD_CCY_INF_PROC();
        B096_UPD_MST_CCY();
        B098_UPD_MST_INF_PROC();
        B100_PRT_UNPT_ITEM();
        B170_FIN_TX_HIS_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "XXX");
            B210_GEN_VCH_PROC();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDCUHDRA.AC);
        CEP.TRC(SCCGWA, DDCUHDRA.CCY);
        CEP.TRC(SCCGWA, DDCUHDRA.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUHDRA.TX_AMT);
        CEP.TRC(SCCGWA, DDCUHDRA.VAL_DATE);
        CEP.TRC(SCCGWA, DDCUHDRA.HLD_REF);
        CEP.TRC(SCCGWA, DDCUHDRA.TX_TYPE);
        CEP.TRC(SCCGWA, DDCUHDRA.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUHDRA.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCUHDRA.OTHER_AMT);
        CEP.TRC(SCCGWA, DDCUHDRA.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUHDRA.TX_MMO);
        CEP.TRC(SCCGWA, DDCUHDRA.TX_REF);
        CEP.TRC(SCCGWA, DDCUHDRA.REMARKS);
        if (DDCUHDRA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUHDRA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUHDRA.CCY_TYPE == ' ') {
            if (!DDCUHDRA.CCY.equalsIgnoreCase("156")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
                S000_ERR_MSG_PROC();
            } else {
                DDCUHDRA.CCY_TYPE = '1';
            }
        }
        if (DDCUHDRA.TX_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUHDRA.TX_AMT < 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDCUHDRA.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCUHDRA.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
            }
        } else {
            DDCUHDRA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDCUHDRA.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDCUHDRA.TX_TYPE != 'T' 
            && DDCUHDRA.TX_TYPE != 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TC_FLG_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCUHDRA.AC;
            T000_READ_UPDATE_DDTMST();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        WS_TMP_ACATTR = DDRMST.AC_TYPE;
    }
    public void B040_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUHDRA.AC;
        S000_CALL_CIZACCU();
    }
    public void B060_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        DDCIQPRD.INPUT_DATA.CCY = DDCUHDRA.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
        DPCPARMP.AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
    }
    public void B065_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXXXXXXXX");
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, DDCUHDRA.AC);
        CEP.TRC(SCCGWA, DDCUHDRA.CCY);
        CEP.TRC(SCCGWA, DDCUHDRA.CCY_TYPE);
        CICQACAC.DATA.AGR_NO = DDCUHDRA.AC;
        CICQACAC.DATA.CCY_ACAC = DDCUHDRA.CCY;
        CICQACAC.DATA.CR_FLG = DDCUHDRA.CCY_TYPE;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
    }
    public void B075_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_UPDATE_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            S000_ERR_MSG_PROC();
        }
        WS_PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, WS_PROD_CODE);
    }
    public void B080_CHECK_CCAL_BAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDCUHDRA.CCAL_BAL);
        if (DDCUHDRA.CCAL_BAL > 0) {
            IBS.init(SCCGWA, DCCUTCGD);
            DCCUTCGD.FUNC = 'C';
            DCCUTCGD.LAW_FLG = 'Y';
            DCCUTCGD.CHNL_FLG = 'O';
            DCCUTCGD.AC = DDCUHDRA.AC;
            DCCUTCGD.CCY = DDCUHDRA.CCY;
            DCCUTCGD.CCY_TYP = DDCUHDRA.CCY_TYPE;
            DCCUTCGD.AMT = DDCUHDRA.CCAL_BAL;
            CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
            CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
            if (DDRCCY.AC_TYPE == '3') {
                DCCUTCGD.FLG = 'M';
                DCCUTCGD.PROD_CD = DDRCCY.PROD_CODE;
            }
            CEP.TRC(SCCGWA, DCCUTCGD.FLG);
            CEP.TRC(SCCGWA, DCCUTCGD.PROD_CD);
            CEP.TRC(SCCGWA, DCCUTCGD.AC);
            CEP.TRC(SCCGWA, DCCUTCGD.AMT);
            S000_CALL_DCZUTCGD();
            CEP.TRC(SCCGWA, DCCUTCGD.RC);
            if (DCCUTCGD.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUTCGD.RC);
                S000_ERR_MSG_PROC();
            } else {
                CEP.TRC(SCCGWA, DCCUTCGD.BAL_FLG);
                if (DCCUTCGD.BAL_FLG == '1') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                }
            }
            IBS.init(SCCGWA, DDRCCY);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            T000_READ_UPDATE_DDTCCY();
        }
        CEP.TRC(SCCGWA, "11111111111111111111111111111111111111111111111");
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
    }
    public void B090_UPD_BAL_PROC() throws IOException,SQLException,Exception {
        if (DDRCCY.STS == 'C') {
            DDRCCY.STS = 'N';
        }
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDRCCY.LAST_BAL += DDCUHDRA.TX_AMT;
                } else {
                    DDRCCY.LAST_BAL -= DDCUHDRA.TX_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
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
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDRCCY.CURR_BAL += DDCUHDRA.TX_AMT;
            if (DDCUHDRA.TX_TYPE == 'C') {
                DDRCCY.DRW_CAMT -= DDCUHDRA.TX_AMT;
            } else {
                if (DDCUHDRA.TX_TYPE == 'T') {
                    DDRCCY.DRW_TAMT -= DDCUHDRA.TX_AMT;
                }
            }
        } else {
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            DDRCCY.CURR_BAL -= DDCUHDRA.TX_AMT;
            CEP.TRC(SCCGWA, "3333333333333333333");
            CEP.TRC(SCCGWA, DDCUHDRA.TX_AMT);
            if (DDCUHDRA.TX_TYPE == 'C') {
                DDRCCY.DRW_CAMT += DDCUHDRA.TX_AMT;
                WS_TOT_CAMT = DDRCCY.DRW_CAMT;
                CEP.TRC(SCCGWA, "2222222222222222222222222222");
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            } else {
                if (DDCUHDRA.TX_TYPE == 'T') {
                    DDRCCY.DRW_TAMT += DDCUHDRA.TX_AMT;
                    WS_TOT_TAMT = DDRCCY.DRW_TAMT;
                    CEP.TRC(SCCGWA, "11111111111111111");
                    CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                }
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        WS_CURR_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
    }
    public void B095_UPD_CCY_INF_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'A') {
            DDRMST.AC_STS = 'N';
        }
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_REWRITE_DDTCCY();
        DDCUHDRA.CURR_BAL = DDRCCY.CURR_BAL;
        DDCUHDRA.AVA_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        DDRMSTR.ADP_STS = 'O';
        T000_READ_DDTMSTR();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            if (DDRCCY.CURR_BAL < 100000) {
                IBS.init(SCCGWA, DDCSMHQB);
                DDCSMHQB.FUNC = '2';
                DDCSMHQB.CUS_AC = DDRCCY.CUS_AC;
                DDCSMHQB.CCY = DDRCCY.CCY;
                DDCSMHQB.CCY_TYP = DDRCCY.CCY_TYPE;
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("A")) {
                    DDCSMHQB.FLG = "1";
                } else {
                    DDCSMHQB.FLG = "2";
                }
                S000_CALL_DDZSMHQB();
            }
        }
    }
    public void B096_UPD_MST_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCITRSR);
        DCCITRSR.INP_DATA.OPR = 'T';
        CEP.TRC(SCCGWA, DDCUHDRA.AC);
        DCCITRSR.INP_DATA.AC = DDCUHDRA.AC;
        DCCITRSR.INP_DATA.CCY = DDCUHDRA.CCY;
        DCCITRSR.INP_DATA.CCY_TYPE = DDCUHDRA.CCY_TYPE;
        DCCITRSR.INP_DATA.DRCR_FLG = 'D';
        DCCITRSR.INP_DATA.TRS_AMT = DDCUHDRA.TX_AMT;
        DCCITRSR.INP_DATA.APP = "DD";
        DCCITRSR.INP_DATA.LAW_FLG = '1';
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.OPR);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.AC);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.DRCR_FLG);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.TRS_AMT);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.APP);
        S000_CALL_DCZITRSR();
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
        }
    }
    public void B100_PRT_UNPT_ITEM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCUHDRA.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUHDRA.AC;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDRVCH.KEY.CUS_AC = CICQACRL.O_DATA.O_AC_NO;
        }
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        T000_READ_DDTVCH();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DDCIPSBK);
            DDCIPSBK.FUNC = 'T';
            DDCIPSBK.AC = DDRVCH.KEY.CUS_AC;
            DDCIPSBK.UPT_CCY = DDCUHDRA.CCY;
            DDCIPSBK.UPT_CCY_TYPE = DDCUHDRA.CCY_TYPE;
            DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
            DDCIPSBK.UPT_MMO = DDCUHDRA.TX_MMO;
            DDCIPSBK.UPT_AMT = DDCUHDRA.TX_AMT;
            DDCIPSBK.AC_ATTR = WS_TMP_ACATTR;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCIPSBK.UPT_TXN_TYPE = 'C';
            } else {
                DDCIPSBK.UPT_TXN_TYPE = 'D';
            }
            S000_CALL_DDZIPSBK();
        }
    }
    public void B170_FIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = DDCUHDRA.TX_REF;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.AC = DDCUHDRA.AC;
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (DDCUHDRA.CARD_NO.trim().length() > 0) {
            BPCPFHIS.DATA.TX_TOOL = DDCUHDRA.CARD_NO;
        } else {
            BPCPFHIS.DATA.TX_TOOL = DDCUHDRA.AC;
        }
        if (DDCUHDRA.VAL_DATE > 0) {
            BPCPFHIS.DATA.TX_VAL_DT = DDCUHDRA.VAL_DATE;
        } else {
            BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.TX_CCY = DDCUHDRA.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUHDRA.CCY_TYPE;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TYPE);
        BPCPFHIS.DATA.TX_AMT = DDCUHDRA.TX_AMT;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.OTH_AC = DDCUHDRA.OTHER_AC;
        BPCPFHIS.DATA.RLT_AC = DDCUHDRA.OTHER_AC;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        BPCPFHIS.DATA.NARRATIVE = DDCUHDRA.NARRATIVE;
        BPCPFHIS.DATA.REMARK = DDCUHDRA.REMARKS;
        BPCPFHIS.DATA.TX_MMO = DDCUHDRA.TX_MMO;
        BPCPFHIS.DATA.PROD_CD = WS_PROD_CODE;
        BPCPFHIS.DATA.VAL_BAL = WS_CURR_BAL;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.VAL_BAL);
        IBS.init(SCCGWA, DDRFHIS);
        if (DDCIPSBK.PSBK_NO.trim().length() > 0 
            && !IBS.isNumeric(DDCIPSBK.PSBK_NO)) {
            DDRFHIS.PORT = "DDBV01";
            BPCPFHIS.DATA.REF_NO = DDCIPSBK.PSBK_NO;
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = DDCUHDRA.AC;
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        DDRFHIS.TX_AMT = DDCUHDRA.TX_AMT;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDCUHDRA.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.REF = DDCUHDRA.TX_REF;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = DDRCCY.CURR_BAL;
        DDRFHIS.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
    }
    public void B185_NOTICE_KHMS_SYS_PROC() throws IOException,SQLException,Exception {
        if (DDCUHDRA.VS_AC_FLG == 'Y') {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = K_AGT_TYPE;
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = DDCUHDRA.CARD_NO;
            S000_CALL_CIZMAGT();
            if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
                IBS.init(SCCGWA, CICMAGT);
                CICMAGT.DATA.AGT_TYP = K_AGT_TYPE;
                CICMAGT.DATA.ENTY_TYP = '1';
                CICMAGT.DATA.AGT_STS = 'N';
                CICMAGT.DATA.ENTY_NO = DDCUHDRA.AC;
                S000_CALL_CIZMAGT();
            }
        } else {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = K_AGT_TYPE;
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = DDCUHDRA.AC;
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
            S000_CALL_CIZMAGT();
        }
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        if (CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "NOTICE KHMS");
            IBS.init(SCCGWA, DDCNKHMS);
            if (DDCUHDRA.VS_AC_FLG == 'Y') {
                DDCNKHMS.VA_NO = DDCUHDRA.AC;
                DDCNKHMS.AC_NO = DDCUHDRA.CARD_NO;
            } else {
                DDCNKHMS.VA_NO = DDCUHDRA.AC;
                DDCNKHMS.AC_NO = DDCUHDRA.AC;
            }
            CEP.TRC(SCCGWA, DDCNKHMS.VA_NO);
            CEP.TRC(SCCGWA, DDCNKHMS.AC_NO);
            DDCNKHMS.CCY = DDCUHDRA.CCY;
            DDCNKHMS.CCY_TYP = DDCUHDRA.CCY_TYPE;
            if (DDCUHDRA.CARD_NO.trim().length() > 0) {
                DDCNKHMS.BV_NO = DDCUHDRA.CARD_NO;
            }
            DDCNKHMS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            DDCNKHMS.TX_MMO = DDCUHDRA.TX_MMO;
            DDCNKHMS.C_FLG = '1';
            DDCNKHMS.OT_AC = DDCUHDRA.OTHER_AC;
            DDCNKHMS.TX_AMT = DDCUHDRA.TX_AMT;
            DDCNKHMS.AC_BAL = DDRCCY.CURR_BAL;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCNKHMS.DR_CR_FLG = 'C';
                DDCNKHMS.TX_STS = '1';
            } else {
                DDCNKHMS.DR_CR_FLG = 'D';
                DDCNKHMS.TX_STS = '0';
            }
            DDCNKHMS.TX_TYP = '1';
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            DDCNKHMS.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDCNKHMS.JRN_SEQ = GWA_BP_AREA.FHIS_CUR_SEQ;
            DDCNKHMS.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
            DDCNKHMS.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
            CEP.TRC(SCCGWA, DDCNKHMS.OT_BR);
            CEP.TRC(SCCGWA, DDCNKHMS.OT_JRN_NO);
            DDCNKHMS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCNKHMS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDCNKHMS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDCNKHMS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDCNKHMS.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCNKHMS.REMARK = DDCUHDRA.REMARKS;
            DDCNKHMS.NARRATIVE = DDCUHDRA.NARRATIVE;
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM;
            SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE;
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 802;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, DDCNKHMS);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                S000_CALL_SCZTPCL();
            }
            B185_01_WRITE_DDTNOSI_PROC();
        }
    }
    public void B185_01_WRITE_DDTNOSI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRNOSI);
        DDRNOSI.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.VA_AC = DDCNKHMS.VA_NO;
        DDRNOSI.AC_NO = DDCNKHMS.AC_NO;
        DDRNOSI.CCY = DDCNKHMS.CCY;
        DDRNOSI.CCY_TYP = DDCNKHMS.CCY_TYP;
        DDRNOSI.BV_TYP = DDCNKHMS.BV_TYP;
        DDRNOSI.BV_NO = DDCNKHMS.BV_NO;
        DDRNOSI.TX_AMT = DDCNKHMS.TX_AMT;
        DDRNOSI.AC_BAL = DDCNKHMS.AC_BAL;
        DDRNOSI.DR_CR_FLG = DDCNKHMS.DR_CR_FLG;
        DDRNOSI.TX_CODE = DDCNKHMS.TX_CODE;
        DDRNOSI.TX_STS = DDCNKHMS.TX_STS;
        DDRNOSI.TX_TYP = DDCNKHMS.TX_TYP;
        DDRNOSI.TX_MMO = DDCNKHMS.TX_MMO;
        DDRNOSI.C_FLG = DDCNKHMS.C_FLG;
        DDRNOSI.KEY.JRN_NO = DDCNKHMS.JRN_NO;
        DDRNOSI.KEY.JRN_SEQ = DDCNKHMS.JRN_SEQ;
        DDRNOSI.OT_AC = DDCNKHMS.OT_AC;
        DDRNOSI.OT_NAME = DDCNKHMS.OT_NAME;
        DDRNOSI.OT_BR = DDCNKHMS.OT_BR;
        DDRNOSI.OT_JRN_NO = DDCNKHMS.OT_JRN_NO;
        DDRNOSI.OT_DATE = DDCNKHMS.OT_DATE;
        DDRNOSI.TX_TIME = DDCNKHMS.TX_TIME;
        DDRNOSI.TX_BR = DDCNKHMS.TX_BR;
        DDRNOSI.TX_TLR = DDCNKHMS.TX_TLR;
        DDRNOSI.TX_CHNL = DDCNKHMS.TX_CHNL;
        DDRNOSI.REMARK = DDCNKHMS.REMARK;
        DDRNOSI.NARRATIVE = DDCNKHMS.NARRATIVE;
        DDRNOSI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRNOSI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            DDRNOSI.SEND_FLG = 'Y';
        } else {
            DDRNOSI.SEND_FLG = 'N';
        }
        T000_WRITE_DDTNOSI();
    }
    public void B210_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUHDRA.CCY;
        if (DDCUHDRA.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPOEWA.DATA.VALUE_DATE = DDCUHDRA.VAL_DATE;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        if (DDCIPSBK.PSBK_NO.trim().length() > 0 
            && !IBS.isNumeric(DDCIPSBK.PSBK_NO)) {
            BPCPOEWA.DATA.PORT = "DDBV01";
            BPCPOEWA.DATA.REF_NO = DDCIPSBK.PSBK_NO;
        }
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDCUHDRA.TX_AMT;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        if (DDCUHDRA.OTHER_AC.trim().length() > 0) {
            BPCPOEWA.DATA.AC_NO_REL = DDCUHDRA.OTHER_AC;
            BPCPOEWA.DATA.THEIR_AC = DDCUHDRA.OTHER_AC;
        }
        S000_CALL_BPZPOEWA();
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_DDZSMHQB() throws IOException,SQLException,Exception {
        DDZSMHQB DDZSMHQB = new DDZSMHQB();
        DDZSMHQB.MP(SCCGWA, DDCSMHQB);
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        CEP.TRC(SCCGWA, DCCITRSR.RC);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUTCGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-TDCGDD-PROC", DCCUTCGD);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT, true);
        if (CICMAGT.RC.RC_CODE != 0) {
            if (CICMAGT.RETURN_INFO == 'N') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void T000_WRITE_DDTNOSI() throws IOException,SQLException,Exception {
        DDTNOSI_RD = new DBParm();
        DDTNOSI_RD.TableName = "DDTNOSI";
        IBS.WRITE(SCCGWA, DDRNOSI, DDTNOSI_RD);
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
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.eqWhere = "AC,ADP_STS";
        DDTMSTR_RD.where = "ADP_TYPE IN ( 'A' , 'B' )";
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
