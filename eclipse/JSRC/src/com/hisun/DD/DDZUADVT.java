package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUADVT {
    int JIBS_tmp_int;
    DBParm DDTVSABI_RD;
    DBParm DDTVSBAL_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String WS_VS_AC = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    String WS_DD_AC = " ";
    String WS_TMP_VS_AC = "                                ";
    String WS_TMP_CCY = "   ";
    char WS_TMP_CCY_TYP = ' ';
    int WS_TMP_TRAN_DT = 0;
    long WS_TMP_JNL = 0;
    String WS_PARENT_AC = " ";
    String WS_RLT_TX_TOOL = " ";
    String WS_RLT_AC_NAME = " ";
    long WS_RLT_BANK = 0;
    String WS_RLT_BA_NM = " ";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CPN_S_UPD_NARR = "BP-S-UPD-HIS-NARR";
    char K_DD_AC_STS_CLO = 'C';
    String WS_MSG_ID = "      ";
    double WS_TMP_BAL = 0;
    int WS_TMP_LST_TRN_DT = 0;
    int WS_TMP_NEXT_LST_DT = 0;
    double WS_VAL_BAL = 0;
    double WS_MARGIN_BAL = 0;
    char WS_TBL_FLAG = ' ';
    char WS_TBL_FLAG_U = ' ';
    char WS_TBL_FLAG_READ = ' ';
    char WS_TBL_FLAG_F = ' ';
    char WS_INSERT_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCUGTIN DDCUGTIN = new DDCUGTIN();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICCUST CICCUST = new CICCUST();
    CICQACRL CICQACRL = new CICQACRL();
    DDCUCRDD DDCUCRDD = new DDCUCRDD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCUNARR BPCUNARR = new BPCUNARR();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRVSBAL DDRVSBAL = new DDRVSBAL();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCUADVT DDCUADVT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUADVT DDCUADVT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUADVT = DDCUADVT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUADVT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        CEP.TRC(SCCGWA, DDCUADVT.CCY);
        CEP.TRC(SCCGWA, DDCUADVT.CCY_TYP);
        CEP.TRC(SCCGWA, DDCUADVT.VALUE_DT);
        CEP.TRC(SCCGWA, DDCUADVT.DD_AC);
        CEP.TRC(SCCGWA, DDCUADVT.TNJNL);
        CEP.TRC(SCCGWA, DDCUADVT.DR_CR_F);
        CEP.TRC(SCCGWA, DDCUADVT.TRAN_AMT);
        CEP.TRC(SCCGWA, DDCUADVT.UPD_FHIS);
        CEP.TRC(SCCGWA, DDCUADVT.RLT_AC);
        CEP.TRC(SCCGWA, DDCUADVT.RLT_AC_NAME);
        WS_TMP_VS_AC = DDCUADVT.VS_AC;
        WS_TMP_CCY = DDCUADVT.CCY;
        WS_TMP_CCY_TYP = DDCUADVT.CCY_TYP;
        WS_TMP_TRAN_DT = DDCUADVT.VALUE_DT;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_VS_INFO();
        B020_CHECK_INFO();
        B030_MOD_DDTCCY();
        if (DDCUADVT.UPD_FHIS == 'Y') {
            B060_UP_BP_FHIS();
        }
        B031_MOD_DDTCCY();
        CEP.TRC(SCCGWA, DDRVSABI.VS_INTAC);
        if (DDCUADVT.DR_CR_F == 'C') {
            if (DDRVSABI.VS_INTAC.equalsIgnoreCase("1")) {
                B035_MOD_DDTCCY();
                B065_UP_BP_FHIS();
            }
        }
    }
    public void B020_CHNL_TZ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTPCL);
        IBS.init(SCCGWA, DDCUGTIN);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.TRANS_FLG = '0';
        SCCTPCL.SERV_AREA.SERV_TYPE = 'Y';
        DDCUGTIN.HOST_SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        DDCUGTIN.TRAN_SEQ_NO_CNT = 0;
        DDCUGTIN.LIST_TRAN_CD = " ";
        DDCUGTIN.ACCT_NO = DDCUADVT.VS_AC;
        DDCUGTIN.CODE_CTRL_FLD = " ";
        DDCUGTIN.DR_CR_FLG = DDCUADVT.DR_CR_F;
        DDCUGTIN.LIST_GL_TYPE = ' ';
        DDCUGTIN.CCY = "156";
        DDCUGTIN.OCCUR_AMT = DDCUADVT.TRAN_AMT;
        DDCUGTIN.EFF_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDCUGTIN.MACHINE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDCUGTIN.RMK_CODE = " ";
        if (DDCUADVT.RLT_AC.trim().length() > 0) {
            DDCUGTIN.CNT_ACCT_NO = DDCUADVT.RLT_AC;
            DDCUGTIN.CNT_ACCT_NM = DDCUADVT.RLT_AC_NAME;
            DDCUGTIN.CNT_BR_NM = DDCUADVT.RLT_BA_NM;
            DDCUGTIN.CNT_BR_ID = DDCUADVT.RLT_BANK;
            DDCUGTIN.OWN_BR_FLG = '0';
        } else {
            DDCUGTIN.CNT_ACCT_NO = DDCUADVT.OTH_AC;
            DDCUGTIN.CNT_ACCT_NM = WS_RLT_AC_NAME;
            DDCUGTIN.CNT_BR_NM = WS_RLT_BA_NM;
            DDCUGTIN.CNT_BR_ID = WS_RLT_BANK;
            DDCUGTIN.OWN_BR_FLG = '1';
        }
        CEP.TRC(SCCGWA, DDCUGTIN.CNT_ACCT_NO);
        CEP.TRC(SCCGWA, DDCUGTIN.CNT_ACCT_NM);
        CEP.TRC(SCCGWA, DDCUGTIN.CNT_BR_NM);
        CEP.TRC(SCCGWA, DDCUADVT.OTH_AC);
        DDCUGTIN.TRAN_TYPE = ' ';
        DDCUGTIN.SEQ_STS = ' ';
        DDCUGTIN.ORG_SYS_ID = " ";
        CEP.TRC(SCCGWA, DDCUADVT.DD_AC);
        DDCUGTIN.MAIN_ACCT = DDCUADVT.DD_AC;
        DDCUGTIN.OWN_CH_NM = CICQACRI.O_DATA.O_AC_CNM;
        CEP.TRC(SCCGWA, DDCUGTIN.OWN_CH_NM);
        DDCUGTIN.CASH_TRAN_FLG = ' ';
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 419;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, DDCUGTIN);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
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
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
        S000_CALL_SCZTPCL();
    }
    public void B010_CHECK_VS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUADVT.DR_CR_F);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUADVT.VS_AC;
        DDRCCY.CCY = DDCUADVT.CCY;
        DDRCCY.CCY_TYPE = DDCUADVT.CCY_TYP;
        T000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
    }
    public void B020_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, BPCPQORG);
        if (DDCUADVT.OTH_AC.trim().length() > 0) {
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCUADVT.OTH_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            WS_RLT_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
            WS_RLT_BANK = CICQACRI.O_DATA.O_OPN_BR;
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_RLT_TX_TOOL = DDCUADVT.OTH_AC;
            }
            if (WS_RLT_BANK != 0) {
                BPCPQORG.BR = (int) WS_RLT_BANK;
                S000_CALL_BPZPQORG();
                WS_RLT_BA_NM = BPCPQORG.CHN_NM;
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCUADVT.VS_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        DDRMST.KEY.CUS_AC = DDCUADVT.DD_AC;
        T000_READ_DDTMST();
        if (DDRMST.AC_STS == K_DD_AC_STS_CLO) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDCUADVT.DR_CR_F == 'D') {
            WS_VAL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL + DDRCCY.CCAL_TOT_BAL;
            if (WS_VAL_BAL < DDCUADVT.TRAN_AMT) {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_AMT_N_E;
                S000_ERR_MSG_PROC();
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRVSABI.VS_FLG == '0' 
                    && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101")) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_UNALLOW_DRAW);
                }
            } else {
            }
        }
    }
    public void B030_MOD_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUADVT.VS_AC;
        DDRCCY.CCY = DDCUADVT.CCY;
        DDRCCY.CCY_TYPE = DDCUADVT.CCY_TYP;
        T000_READ_DDTCCY_UPDATE();
        if (WS_TBL_FLAG_READ == 'Y') {
            CEP.TRC(SCCGWA, "HAVE DATA, USING UPDATE");
            if (DDCUADVT.DR_CR_F == 'D') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    if (DDRCCY.CURR_BAL < DDCUADVT.TRAN_AMT) {
                        WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_AMT_N_E;
                        S000_ERR_MSG_PROC();
                    }
                    DDRCCY.CURR_BAL -= DDCUADVT.TRAN_AMT;
                } else {
                    DDRCCY.CURR_BAL += DDCUADVT.TRAN_AMT;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDRCCY.CURR_BAL += DDCUADVT.TRAN_AMT;
                } else {
                    DDRCCY.CURR_BAL -= DDCUADVT.TRAN_AMT;
                }
            }
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            T000_REWRITE_DDTCCY();
        }
    }
    public void B031_MOD_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCUADVT.VS_AC;
        CICQACRL.DATA.AC_REL = "01";
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        DDRCCY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        DDRCCY.CCY = "156";
        DDRCCY.CCY_TYPE = '1';
        T000_READ_DDTCCY_UPDATE();
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDCUADVT.TRAN_AMT);
        if (DDCUADVT.DR_CR_F == 'D') {
            CEP.TRC(SCCGWA, "A1");
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUADVT.TRAN_AMT;
            } else {
                WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUADVT.TRAN_AMT;
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUADVT.TRAN_AMT;
            } else {
                WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUADVT.TRAN_AMT;
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRVSABI.VS_INTAC);
        CEP.TRC(SCCGWA, WS_MARGIN_BAL);
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        T000_REWRITE_DDTCCY();
    }
    public void B035_MOD_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCUADVT.VS_AC;
        CICQACRL.DATA.AC_REL = "01";
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        DDRCCY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        DDRCCY.CCY = "156";
        DDRCCY.CCY_TYPE = '1';
        T000_READ_DDTCCY_UPDATE();
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUADVT.TRAN_AMT;
        } else {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUADVT.TRAN_AMT;
        }
        CEP.TRC(SCCGWA, WS_MARGIN_BAL);
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        T000_REWRITE_DDTCCY();
        DDRCCY.CUS_AC = DDCUADVT.VS_AC;
        DDRCCY.CCY = DDCUADVT.CCY;
        DDRCCY.CCY_TYPE = DDCUADVT.CCY_TYP;
        T000_READ_DDTCCY_UPDATE();
        if (WS_TBL_FLAG_READ == 'Y') {
            CEP.TRC(SCCGWA, "HAVE DATA, USING UPDATE");
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRCCY.CURR_BAL < DDCUADVT.TRAN_AMT) {
                    WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_AMT_N_E;
                    S000_ERR_MSG_PROC();
                }
                DDRCCY.CURR_BAL -= DDCUADVT.TRAN_AMT;
            } else {
                DDRCCY.CURR_BAL += DDCUADVT.TRAN_AMT;
            }
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
    }
    public void B060_UP_BP_FHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUADVT.VS_AC;
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '4';
        if (DDCUADVT.DR_CR_F == 'D') {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        } else {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        }
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        BPCPFHIS.DATA.AC = DDCUADVT.VS_AC;
        BPCPFHIS.DATA.TX_TYPE = DDCUADVT.TX_TYPE;
        CEP.TRC(SCCGWA, DDCUADVT.TX_TYPE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TYPE);
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        BPCPFHIS.DATA.TX_TOOL = DDCUADVT.VS_AC;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = DDCUADVT.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUADVT.CCY_TYP;
        BPCPFHIS.DATA.TX_AMT = DDCUADVT.TRAN_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (DDCUCRDD.TX_MMO.trim().length() == 0) {
                DDCUCRDD.TX_MMO = "G004";
            }
        }
        BPCPFHIS.DATA.TX_MMO = DDCUADVT.TX_MMO;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.REMARK = DDCUADVT.SMR;
        BPCPFHIS.DATA.PROD_CD = DDRMST.PROD_CODE;
        BPCPFHIS.DATA.OTH_AC = DDCUADVT.OTH_AC;
        if (DDCUADVT.RLT_AC.trim().length() > 0) {
            BPCPFHIS.DATA.RLT_AC = DDCUADVT.RLT_AC;
            BPCPFHIS.DATA.RLT_AC_NAME = DDCUADVT.RLT_AC_NAME;
            CEP.TRC(SCCGWA, DDCUADVT.RLT_AC_NAME);
            BPCPFHIS.DATA.RLT_BANK = "" + DDCUADVT.RLT_BANK;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            BPCPFHIS.DATA.RLT_BK_NM = DDCUADVT.RLT_BA_NM;
        } else {
            BPCPFHIS.DATA.RLT_AC = DDCUADVT.OTH_AC;
            BPCPFHIS.DATA.RLT_AC_NAME = WS_RLT_AC_NAME;
            BPCPFHIS.DATA.RLT_BANK = "" + WS_RLT_BANK;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            BPCPFHIS.DATA.RLT_BK_NM = WS_RLT_BA_NM;
        }
        BPCPFHIS.DATA.OTH_TX_TOOL = WS_RLT_TX_TOOL;
        BPCPFHIS.DATA.RLT_CCY = CICACCU.DATA.CCY;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BK_NM);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        BPCPFHIS.DATA.VAL_BAL_CCY = DDCUADVT.CCY;
        BPCPFHIS.DATA.VAL_BAL = DDRCCY.CURR_BAL;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
    }
    public void B065_UP_BP_FHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUADVT.VS_AC;
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '4';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        BPCPFHIS.DATA.AC = DDCUADVT.VS_AC;
        BPCPFHIS.DATA.TX_TYPE = DDCUADVT.TX_TYPE;
        CEP.TRC(SCCGWA, DDCUADVT.TX_TYPE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TYPE);
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        BPCPFHIS.DATA.TX_TOOL = DDCUADVT.VS_AC;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = DDCUADVT.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUADVT.CCY_TYP;
        BPCPFHIS.DATA.TX_AMT = DDCUADVT.TRAN_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (DDCUCRDD.TX_MMO.trim().length() == 0) {
                DDCUCRDD.TX_MMO = "G004";
            }
        }
        BPCPFHIS.DATA.TX_MMO = "A019";
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.REMARK = DDCUADVT.SMR;
        BPCPFHIS.DATA.PROD_CD = DDRMST.PROD_CODE;
        BPCPFHIS.DATA.OTH_AC = DDCUADVT.OTH_AC;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        CICQACRI.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        if (!CICQACRI.O_DATA.O_AC_CNM.equalsIgnoreCase("0")) {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
            S000_CALL_BPZPQORG();
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        BPCPFHIS.DATA.RLT_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        BPCPFHIS.DATA.RLT_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        BPCPFHIS.DATA.RLT_BANK = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        BPCPFHIS.DATA.OTH_TX_TOOL = WS_RLT_TX_TOOL;
        BPCPFHIS.DATA.RLT_CCY = CICACCU.DATA.CCY;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BK_NM);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        BPCPFHIS.DATA.VAL_BAL_CCY = DDCUADVT.CCY;
        BPCPFHIS.DATA.VAL_BAL = DDRCCY.CURR_BAL;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.where = "VS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYP = :WS_CCY_TYP";
        DDTVSABI_RD.upd = true;
        IBS.READ(SCCGWA, DDRVSABI, this, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.REWRITE(SCCGWA, DDRVSABI, DDTVSABI_RD);
    }
    public void T000_WRITE_DDTVSBAL() throws IOException,SQLException,Exception {
        DDTVSBAL_RD = new DBParm();
        DDTVSBAL_RD.TableName = "DDTVSBAL";
        IBS.WRITE(SCCGWA, DDRVSBAL, DDTVSBAL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VSAC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSBAL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY_UPDATE() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG_READ = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG_READ = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
    }
    public void S000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
    }
    public void S000_CALL_BPZUNARR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_UPD_NARR, BPCUNARR);
        CEP.TRC(SCCGWA, BPCUNARR.RC);
        if (BPCUNARR.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCUNARR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
