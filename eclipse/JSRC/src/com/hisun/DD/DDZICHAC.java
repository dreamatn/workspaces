package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZICHAC {
    DBParm DDTMST_RD;
    String K_STS_TABLE_APP = "DD";
    String K_CUS_CR_STS_TBL = "0003";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_I_INQ_BAL = "DD-I-INQ-CCY-BAL";
    String CPN_VWA_CPNT = "BP-P-VWA-WRITE";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_DCZUAINQ = "DC-U-CARD-AC-INQ";
    String CPN_BPZPRMR = "BP-PARM-READ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_AP_MMO = "DD";
    String WS_ERR_MSG = " ";
    String WS_VA_AC = " ";
    char WS_VA_AC_FLG = ' ';
    char WS_OTHER_CITYPE = ' ';
    char WS_AC_CITYPE = ' ';
    char WS_TMP_ACATTR = ' ';
    int WS_EXP_DATE = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMST DDRMST = new DDRMST();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DDRVCH DDRVCH = new DDRVCH();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCOCRNT DDCOCRNT = new DDCOCRNT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPCCPRL BPCCPRL = new BPCCPRL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUAINQ DCCUAINQ = new DCCUAINQ();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCITRSR DCCITRSR = new DCCITRSR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDCUADVT DDCUADVT = new DDCUADVT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCNKHMS DDCNKHMS = new DDCNKHMS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICMAGT CICMAGT = new CICMAGT();
    DDRNOSI DDRNOSI = new DDRNOSI();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCICHAC DDCICHAC;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCICHAC DDCICHAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCICHAC = DDCICHAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZICHAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B015_GET_STD_AC_INFO();
        B020_CHECK_CARD_PROC();
        B030_GET_AC_INF_PROC();
        B035_CHK_AC_STS();
        B040_CI_INF_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_CHECK_STS_TBL_PROC();
        }
        B060_CHK_NAME();
        B070_GET_PRD_INF_PROC();
        B165_UPD_RTV_CCY();
    }
    public void B015_GET_STD_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        CEP.TRC(SCCGWA, DDCICHAC.CCY);
        CEP.TRC(SCCGWA, DDCICHAC.CCY_TYPE);
        if (DDCICHAC.AC.trim().length() > 0) {
            DCCPACTY.INPUT.FUNC = '3';
            DCCPACTY.INPUT.CCY = DDCICHAC.CCY;
            DCCPACTY.INPUT.CCY_TYPE = DDCICHAC.CCY_TYPE;
            DCCPACTY.INPUT.AC = DDCICHAC.AC;
        } else {
            if (DDCICHAC.CARD_NO.trim().length() > 0) {
                DCCPACTY.INPUT.FUNC = '1';
                DCCPACTY.INPUT.CCY = DDCICHAC.CCY;
                DCCPACTY.INPUT.CCY_TYPE = DDCICHAC.CCY_TYPE;
                DCCPACTY.INPUT.AC = DDCICHAC.CARD_NO;
            }
        }
        S000_CALL_DCZPACTY();
        DDCICHAC.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.SUB_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if (DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("3")) {
            WS_VA_AC_FLG = 'Y';
            WS_VA_AC = DDCICHAC.AC;
        } else {
            WS_VA_AC_FLG = 'N';
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCICHAC.AC);
        CEP.TRC(SCCGWA, DDCICHAC.CCY);
        CEP.TRC(SCCGWA, DDCICHAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCICHAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCICHAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCICHAC.TX_AMT);
        CEP.TRC(SCCGWA, DDCICHAC.OTHER_AC);
        CEP.TRC(SCCGWA, DDCICHAC.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCICHAC.TX_MMO);
        CEP.TRC(SCCGWA, DDCICHAC.TX_TYPE);
        CEP.TRC(SCCGWA, DDCICHAC.REMARKS);
        if (DDCICHAC.AC.trim().length() == 0 
            && DDCICHAC.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCICHAC.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCICHAC.CCY.equalsIgnoreCase("156") 
            && DDCICHAC.CCY_TYPE == ' ') {
            DDCICHAC.CCY_TYPE = '1';
        }
        if (DDCICHAC.TX_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
        }
        if (DDCICHAC.TX_MMO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TX_MMO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_CARD_PROC() throws IOException,SQLException,Exception {
        if (DDCICHAC.CARD_NO.trim().length() > 0 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, DCCPFTCK);
            DCCPFTCK.VAL.CARD_NO = DDCICHAC.CARD_NO;
            DCCPFTCK.VAL.REGN_TYP = '0';
            if (DDCICHAC.TX_TYPE == 'C') {
                DCCPFTCK.VAL.TXN_TYPE = "02";
            }
            if (DDCICHAC.TX_TYPE == 'T') {
                DCCPFTCK.VAL.TXN_TYPE = "03";
            }
            DCCPFTCK.VAL.TXN_CCY = DDCICHAC.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCICHAC.TX_AMT;
            S000_CALL_DCZPFTCK();
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        T000_READ_UPDATE_DDTMST();
        WS_TMP_ACATTR = DDRMST.AC_TYPE;
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.EXP_DATE > 0 
            && DDRMST.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_EXPIRED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        if (DDRMST.CROS_CR_FLG == '2') {
            if (DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CROS_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDRMST.AC_TYPE == 'H' 
            && DDRMST.CI_TYP == '2') {
            IBS.init(SCCGWA, SCCCLDT);
            if (SCCGWA.COMM_AREA.AC_DATE > DDRMST.EXP_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEMP_AC_IS_EXPIRED;
                S000_ERR_MSG_PROC();
            } else {
                SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                SCCCLDT.MTHS = 3;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, SCCCLDT.MTHS);
                SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                if (SCCCLDT.RC != 0) {
                    WS_ERR_MSG = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
                    S000_ERR_MSG_PROC();
                }
                WS_EXP_DATE = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_EXP_DATE);
                if (WS_EXP_DATE > DDRMST.EXP_DATE 
                    || WS_EXP_DATE == DDRMST.EXP_DATE) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEMP_AC_EXPIRE_WARN;
                    S000_ERR_MSG_PROC1();
                }
            }
        }
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        CEP.TRC(SCCGWA, DDCICHAC.TX_TYPE);
        if ((DDRMST.CASH_FLG == '3' 
            || DDRMST.CASH_FLG == '4') 
            && DDCICHAC.TX_TYPE == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CANT_CRD;
            S000_ERR_MSG_PROC();
        }
        if (DDCICHAC.GD_WITHDR_FLG != 'Y' 
            && DDRMST.AC_TYPE == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_GDWR_CNEL_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCICHAC.TX_TYPE == 'T' 
            && SCCGWA.COMM_AREA.AP_MMO.equalsIgnoreCase(K_AP_MMO) 
            && DDCICHAC.BV_TYP == '2') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DDCICHAC.OTHER_AC;
            S000_CALL_CIZACCU();
            WS_OTHER_CITYPE = CICACCU.DATA.CI_TYP;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCICHAC.AC;
        S000_CALL_CIZACCU();
        WS_AC_CITYPE = CICACCU.DATA.CI_TYP;
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B060_CHK_NAME() throws IOException,SQLException,Exception {
        if (DDCICHAC.AC_NAME.trim().length() > 0) {
            if (!DDCICHAC.AC_NAME.equalsIgnoreCase(CICACCU.DATA.CI_CNM) 
                && !DDCICHAC.AC_NAME.equalsIgnoreCase(CICACCU.DATA.CI_ENM)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NAME_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B070_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCICHAC.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCICHAC.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (DDRMST.AC_STS == 'A' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AP_MMO);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
        DPCPARMP.AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
    }
    public void B165_UPD_RTV_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCITRSR);
        DCCITRSR.INP_DATA.OPR = 'C';
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.SUB_AC);
        DCCITRSR.INP_DATA.AC = DCCPACTY.OUTPUT.SUB_AC;
        DCCITRSR.INP_DATA.CCY = DDCICHAC.CCY;
        DCCITRSR.INP_DATA.CCY_TYPE = DDCICHAC.CCY_TYPE;
        DCCITRSR.INP_DATA.DRCR_FLG = 'C';
        DCCITRSR.INP_DATA.TRS_AMT = DDCICHAC.TX_AMT;
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
    public void B210_ADVICE_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCRNT);
        DDCOCRNT.AC_NO = DDCICHAC.AC;
        DDCOCRNT.CCY = DDCICHAC.CCY;
        DDCOCRNT.CASH_AMT = 0;
        DDCOCRNT.CHQ_TYP = ' ';
        DDCOCRNT.CHQ_AMT = 0;
        DDCOCRNT.TRF_AMT = DDCICHAC.TX_AMT;
        DDCOCRNT.RMKS = DDCICHAC.REMARKS;
        CEP.TRC(SCCGWA, DDCOCRNT.AC_NO);
        CEP.TRC(SCCGWA, DDCOCRNT.ENG_NM);
        CEP.TRC(SCCGWA, DDCOCRNT.ADRESS);
        CEP.TRC(SCCGWA, DDCOCRNT.CCY);
        CEP.TRC(SCCGWA, DDCOCRNT.CASH_AMT);
        CEP.TRC(SCCGWA, DDCOCRNT.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCOCRNT.CHQ_AMT);
        CEP.TRC(SCCGWA, DDCOCRNT.TRF_AMT);
        CEP.TRC(SCCGWA, DDCOCRNT.RMKS);
    }
    public void B240_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPRGST();
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void R000_INQ_CCY_AVL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = DDCICHAC.AC;
        DDCIQBAL.DATA.CCY = DDCICHAC.CCY;
        S00_CALL_DDZIQBAL();
    }
    public void R000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (DDCICHAC.CARD_NO.trim().length() > 0) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = DDCICHAC.CARD_NO;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCICHAC.AC;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCICHAC.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCICHAC.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        if (BPCPRGST.BRANCH_FLG == 'Y') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
        }
        BPCTCALF.INPUT_AREA.TX_AC = DDCICHAC.AC;
        BPCTCALF.INPUT_AREA.TX_CCY = DDCICHAC.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = DDCICHAC.TX_AMT;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.OTHER_AC = DDCICHAC.OTHER_AC;
        BPCTCALF.INPUT_AREA.TX_CI = CICACCU.DATA.CI_NO;
        S000_CALL_BPZFCALF();
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S00_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_BAL, DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
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
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCCPRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Z-CPRL-MAINTAIN", BPCCPRL);
        if (BPCCPRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCPRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
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
    public void S000_CALL_VCH_CPNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_VWA_CPNT, BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUAINQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUAINQ, DCCUAINQ);
        if (DCCUAINQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUAINQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZPRMR, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        CEP.TRC(SCCGWA, DCCITRSR.RC);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC1() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_EXP_DATE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
