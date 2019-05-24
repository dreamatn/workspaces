package com.hisun.GD;

import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZUMPDR {
    int JIBS_tmp_int;
    brParm GDTHIS_BR = new brParm();
    DBParm GDTPLDR_RD;
    DBParm GDTHIS_RD;
    DBParm TDTSMST_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm GDTSTAC_RD;
    DBParm TDTCMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String K_GUAOUT = "GUAOUT";
    String WS_ERR_MSG = " ";
    double WS_DR_AMT = 0;
    char WS_TD_OPT = ' ';
    double WS_PAYING_INT = 0;
    String WS_INT_AC = " ";
    double WS_VAL_RAMT = 0;
    double WS_VAL_RAMT1 = 0;
    String WS_AC = " ";
    String WS_SUB_AC = " ";
    double WS_NEED_PLDR_AMT = 0;
    double WS_RELSE_AMT = 0;
    double WS_VAL_RBAL = 0;
    double WS_AMOUNT = 0;
    int WS_AC_SEQ = 0;
    String WS_RSEQ = " ";
    String WS_PLDR_AC = " ";
    int WS_PLDR_SEQ = 0;
    String WS_CANCEL_AC = " ";
    double WS_CANCEL_AMT = 0;
    int WS_CANCEL_AC_SEQ = 0;
    String WS_CANCEL_ACO_AC = " ";
    char WS_PLDR_FLG = ' ';
    char WS_HIS_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCRMST DDCRMST = new DDCRMST();
    TDCACDRU TDCACDRU = new TDCACDRU();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    double WS_RAMT = 0;
    double WS_KDGD_RELAT_AMT = 0;
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    TDRCMST TDRCMST = new TDRCMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDCUMPDR GDCUMPDR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCUMPDR GDCUMPDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCUMPDR = GDCUMPDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZUMPDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHK_MST_INF_PROC();
        if (pgmRtn) return;
        B030_WITHDRAW_PROC();
        if (pgmRtn) return;
        if (DDVMPRD.VAL.TD_FLG != '0') {
            B050_UPDATE_PLDR_PROC();
            if (pgmRtn) return;
            B060_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.FUNC);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.CTA_NO);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.REF_NO);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AC);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.SEQ);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AMT);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AMT);
        if (GDCUMPDR.INPUT.RSEQ.trim().length() == 0 
            && GDCUMPDR.INPUT.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCUMPDR.INPUT.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCUMPDR.INPUT.AMT == 0 
            && GDCUMPDR.INPUT.FUNC == 'D') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_MST_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
        CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
        }
        if (DDVMPRD.VAL.TD_FLG == '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                T000_READUP_GDTPLDR_1();
                if (pgmRtn) return;
            } else {
                T000_READ_GDTHIS_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, GDRHIS.RSEQ);
                CEP.TRC(SCCGWA, GDRHIS.AC);
                CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
                IBS.init(SCCGWA, GDRPLDR);
                GDRPLDR.KEY.RSEQ = GDRHIS.RSEQ;
                GDRPLDR.KEY.AC = GDRHIS.AC;
                GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
                T000_READ_UPDATE_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_AC_SEQ = GDCUMPDR.INPUT.SEQ;
            R000_READUPD_PLDR_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        if (GDRPLDR.RELAT_STS == 'R' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_RELEASED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDRPLDR.RELAT_AMT < GDCUMPDR.INPUT.AMT 
            && GDCUMPDR.INPUT.FUNC == 'D' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DDVMPRD.VAL.TD_FLG != '0') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDRPLDR.AC_TYP == '0') {
            R000_GET_DDAC_INF_PROC();
            if (pgmRtn) return;
            if (DDCRMST.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRMST.AC_STS == 'C') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (GDCUMPDR.INPUT.SEQ != 0) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
                CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                R000_GET_TDAC_INF_PROC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRCMST);
                TDRCMST.KEY.AC_NO = GDCUMPDR.INPUT.AC;
                T000_READ_TDTCMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRCMST.STS);
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    if (TDRCMST.STS == '1') {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B030_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (GDCUMPDR.INPUT.FUNC == 'R') {
                WS_DR_AMT = GDRPLDR.RELAT_AMT;
            } else {
                WS_DR_AMT = GDCUMPDR.INPUT.AMT;
            }
        } else {
            WS_DR_AMT = GDCUMPDR.INPUT.AMT;
        }
        if (GDRPLDR.AC_TYP == '0') {
            B030_01_DD_WITHDRAW_PROC();
            if (pgmRtn) return;
        } else {
            B030_02_TD_WITHDRAW_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDCUMPDR.INPUT.APP_FLG);
        }
    }
    public void B030_01_DD_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCUMPDR.INPUT.AC;
        DDRCCY.CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            DDRCCY.CCY_TYPE = '1';
        } else {
            DDRCCY.CCY_TYPE = '2';
        }
        T000_READ_UPDDATE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        if (DDVMPRD.VAL.TD_FLG == '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B025_GDKD_PLDR_PROC();
                if (pgmRtn) return;
            } else {
                B025_GDKD_CANC_PROC();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (DDVMPRD.VAL.TD_FLG != '0') {
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
                CEP.TRC(SCCGWA, WS_DR_AMT);
                WS_VAL_RAMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
                if (WS_VAL_RAMT < WS_DR_AMT) {
                    CEP.TRC(SCCGWA, "DD AMT INVAILD");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, GDRPLDR.REF_TYP);
                WS_VAL_RAMT1 = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - WS_RAMT + DDRCCY.CCAL_TOT_BAL;
                if (WS_VAL_RAMT1 < WS_DR_AMT) {
                    CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN AVL");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - WS_DR_AMT;
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, WS_DR_AMT);
                if (DDRCCY.MARGIN_BAL < 0) {
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                T000_UPDATE_DDTCCY();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUDRAC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = GDCUMPDR.INPUT.AC;
                DDCUDRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUDRAC.CCY_TYPE = '1';
                } else {
                    DDCUDRAC.CCY_TYPE = '2';
                }
                DDCUDRAC.TX_AMT = WS_DR_AMT;
                DDCUDRAC.OTHER_AC = GDCUMPDR.INPUT.OTHER_AC;
                DDCUDRAC.OTHER_AC_NM = GDCUMPDR.INPUT.OTHER_AC_NM;
                DDCUDRAC.RLT_AC = GDCUMPDR.INPUT.RLT_AC;
                DDCUDRAC.RLT_AC_NAME = GDCUMPDR.INPUT.RLT_AC_NAME;
                DDCUDRAC.RLT_BK_NM = GDCUMPDR.INPUT.RLT_BK_NM;
                DDCUDRAC.RLT_BANK = GDCUMPDR.INPUT.RLT_BANK;
                if (GDCUMPDR.INPUT.MMO.trim().length() == 0) {
                    DDCUDRAC.TX_MMO = "A019";
                } else {
                    DDCUDRAC.TX_MMO = GDCUMPDR.INPUT.MMO;
                }
                DDCUDRAC.REMARKS = GDCUMPDR.INPUT.RMK;
                S000_CALL_DDZUDRAC();
                if (pgmRtn) return;
            }
        } else {
            if (DDVMPRD.VAL.TD_FLG != '0') {
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + WS_DR_AMT;
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, WS_DR_AMT);
                if (DDRCCY.MARGIN_BAL < 0) {
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                T000_UPDATE_DDTCCY();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUDRAC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = GDCUMPDR.INPUT.AC;
                DDCUDRAC.OTHER_AC = GDCUMPDR.INPUT.OTHER_AC;
                DDCUDRAC.OTHER_AC_NM = GDCUMPDR.INPUT.OTHER_AC_NM;
                DDCUDRAC.RLT_AC = GDCUMPDR.INPUT.RLT_AC;
                DDCUDRAC.RLT_AC_NAME = GDCUMPDR.INPUT.RLT_AC_NAME;
                DDCUDRAC.RLT_BK_NM = GDCUMPDR.INPUT.RLT_BK_NM;
                DDCUDRAC.RLT_BANK = GDCUMPDR.INPUT.RLT_BANK;
                DDCUDRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUDRAC.CCY_TYPE = '1';
                } else {
                    DDCUDRAC.CCY_TYPE = '2';
                }
                DDCUDRAC.TX_AMT = WS_DR_AMT;
                if (GDCUMPDR.INPUT.MMO.trim().length() == 0) {
                    DDCUDRAC.TX_MMO = "A019";
                } else {
                    DDCUDRAC.TX_MMO = GDCUMPDR.INPUT.MMO;
                }
                DDCUDRAC.REMARKS = GDCUMPDR.INPUT.RMK;
                S000_CALL_DDZUDRAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_02_TD_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        T000_READ_UPDATE_TDTSMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.HBAL);
            WS_VAL_RAMT = TDRSMST.BAL - TDRSMST.HBAL;
            if (WS_VAL_RAMT < WS_DR_AMT) {
                CEP.TRC(SCCGWA, "TD AMT INVAILD");
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, GDRPLDR.REF_TYP);
            WS_VAL_RAMT1 = TDRSMST.BAL - TDRSMST.HBAL - WS_RAMT;
            if (WS_VAL_RAMT1 < WS_DR_AMT) {
                CEP.TRC(SCCGWA, "TD AMT INVAILD");
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - WS_DR_AMT;
        }
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCACDRU);
        CEP.TRC(SCCGWA, WS_DR_AMT);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_DR_AMT < TDRSMST.BAL) {
                TDCACDRU.OPT = '8';
            } else {
                TDCACDRU.OPT = '9';
            }
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        if (GDRPLDR.KEY.AC_SEQ == 0) {
            TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
            CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
            CEP.TRC(SCCGWA, WS_SUB_AC);
            CEP.TRC(SCCGWA, WS_AC);
            TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
            TDCACDRU.ACO_AC = CICQACAC.DATA.ACAC_NO;
        }
        TDCACDRU.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        TDCACDRU.CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            TDCACDRU.CCY_TYP = '1';
        } else {
            TDCACDRU.CCY_TYP = '2';
        }
        TDCACDRU.TXN_AMT = WS_DR_AMT;
        if (GDCUMPDR.INPUT.OTHER_AC.trim().length() > 0) {
            TDCACDRU.OPP_AC_CNO = GDCUMPDR.INPUT.OTHER_AC;
        } else {
            TDCACDRU.INT_AC = GDCUMPDR.INPUT.RLT_AC;
            TDCACDRU.INT_REMMIT_NM = GDCUMPDR.INPUT.RLT_AC_NAME;
            TDCACDRU.INT_REMMIT_BK = GDCUMPDR.INPUT.RLT_BK_NM;
        }
        TDCACDRU.PRDMO_CD = "MMDP";
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
        if (GDCUMPDR.INPUT.MMO.trim().length() == 0) {
            TDCACDRU.TXN_MMO = "A019";
        } else {
            TDCACDRU.TXN_MMO = GDCUMPDR.INPUT.MMO;
        }
        TDCACDRU.REMARK = GDCUMPDR.INPUT.RMK;
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        WS_PAYING_INT = TDCACDRU.PAYING_INT;
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCUMPDR.INPUT.AC;
        GDRSTAC.KEY.AC_SEQ = GDCUMPDR.INPUT.SEQ;
        T000_READ_GDTSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        if (GDRSTAC.INT_AC.trim().length() > 0) {
            WS_INT_AC = GDRSTAC.INT_AC;
        } else {
            WS_INT_AC = GDRSTAC.ST_AC;
        }
        CEP.TRC(SCCGWA, WS_PAYING_INT);
        CEP.TRC(SCCGWA, WS_INT_AC);
    }
    public void B030_03_TD_INT_PROC() throws IOException,SQLException,Exception {
        if (WS_PAYING_INT != 0) {
            if (WS_INT_AC.trim().length() == 0) {
                if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                    IBS.init(SCCGWA, AICPQIA);
                    AICPQIA.CD.AC_TYP = "2";
                    AICPQIA.CD.BUSI_KND = K_GUAOUT;
                    AICPQIA.BR = TDRSMST.OWNER_BR;
                    AICPQIA.CCY = GDRPLDR.CCY;
                    AICPQIA.SIGN = 'C';
                    S000_CALL_AIZPQIA();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AICPQIA.AC);
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = WS_PAYING_INT;
                    AICUUPIA.DATA.CCY = GDRPLDR.CCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_MAINTAIN_INT_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.CARD_NO = WS_INT_AC;
                DDCUCRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = '2';
                }
                DDCUCRAC.TX_AMT = WS_PAYING_INT;
                DDCUCRAC.OTHER_AC = WS_AC;
                DDCUCRAC.TX_MMO = "S101";
                S000_CALL_DDZUCRAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_UPDATE_PLDR_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT - WS_DR_AMT;
            if (GDRPLDR.RELAT_AMT == 0 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("030500")) {
                GDRPLDR.RELAT_STS = 'R';
                GDRPLDR.RELS_DATE = SCCGWA.COMM_AREA.AC_DATE;
                GDRPLDR.RELS_TIME = SCCGWA.COMM_AREA.TR_TIME;
                GDRPLDR.RELS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                GDRPLDR.RELS_CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
                GDRPLDR.RELS_USR = SCCGWA.COMM_AREA.TL_ID;
            }
            GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT + WS_DR_AMT;
            if (GDRPLDR.RELAT_STS == 'R') {
                GDRPLDR.RELAT_STS = 'N';
                GDRPLDR.RELS_DATE = 0;
                GDRPLDR.RELS_TIME = 0;
                GDRPLDR.RELS_BR = 0;
                GDRPLDR.RELS_CHNL_CD = " ";
                GDRPLDR.RELS_USR = " ";
            }
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        }
        R000_UPDATE_PLDR_PROC();
        if (pgmRtn) return;
    }
    public void B060_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
            CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
            IBS.init(SCCGWA, GDRHIS);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            CEP.TRC(SCCGWA, GDCUMPDR.INPUT.SEQ_NO);
            GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
            GDRHIS.RSEQ = GDRPLDR.KEY.RSEQ;
            GDRHIS.AC = GDRPLDR.KEY.AC;
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            GDRHIS.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            if (GDCUMPDR.INPUT.FUNC == 'D') {
                GDRHIS.FUNC = '7';
            } else {
                GDRHIS.FUNC = '8';
            }
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRHIS.TR_AMT = WS_DR_AMT;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                GDRHIS.CAN_FLG = 'N';
            }
            GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDCRHIS.FUNC = 'A';
            GDCRHIS.REC_PTR = GDRHIS;
            GDCRHIS.REC_LEN = 281;
            S000_CALL_GDZRHIS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            T000_READUP_GDTHIS();
            if (pgmRtn) return;
            GDRHIS.CAN_FLG = 'R';
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_GDTHIS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRHIS);
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
    }
    public void B060_WRITE_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AC);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCUMPDR.INPUT.AC;
        DDRCCY.CCY = GDCUMPDR.OUTPUT.CCY;
        if (GDCUMPDR.OUTPUT.CCY.equalsIgnoreCase("156")) {
            DDRCCY.CCY_TYPE = '1';
        } else {
            DDRCCY.CCY_TYPE = '2';
        }
        T000_READ_UPDDATE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCUMPDR.OUTPUT.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            T000_GROUP_KD_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_KDGD_RELAT_AMT);
            CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AMT);
            if (WS_KDGD_RELAT_AMT < WS_DR_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_NEED_PLDR_AMT = WS_DR_AMT;
        GDRPLDR.KEY.AC = GDCUMPDR.INPUT.AC;
        GDRPLDR.DEAL_CD = GDCUMPDR.INPUT.CTA_NO;
        GDRPLDR.BSREF = GDCUMPDR.INPUT.REF_NO;
        T000_STARTBR_GDTPLDR();
        if (pgmRtn) return;
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        while (WS_PLDR_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            if (GDRPLDR.RELAT_AMT > WS_NEED_PLDR_AMT) {
                WS_RELSE_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELSE_AMT = GDRPLDR.RELAT_AMT;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'S';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
            B020_RELEASE_GDKD_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - WS_RELSE_AMT;
            } else {
                TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + WS_RELSE_AMT;
            }
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELSE_AMT;
            T000_READNEXT_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
        B020_GDKD_DRPLDR_PROC();
        if (pgmRtn) return;
        B028_CR_INTAC_PROC();
        if (pgmRtn) return;
    }
    public void B025_GDKD_CANC_PROC() throws IOException,SQLException,Exception {
        B020_GDKD_DRPLDR_PROC();
        if (pgmRtn) return;
        B028_CR_INTAC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        GDRHIS.AC = GDCUMPDR.INPUT.AC;
        GDRHIS.FUNC = '7';
        CEP.TRC(SCCGWA, GDRHIS.AC);
        T000_STARTBR_BY_DTJRNTYP();
        if (pgmRtn) return;
        T000_READNEXT_HIS_PROC();
        if (pgmRtn) return;
        while (WS_HIS_FLG != 'N') {
            CEP.TRC(SCCGWA, GDRHIS.CAN_FLG);
            if (GDRHIS.CAN_FLG == 'N') {
                CEP.TRC(SCCGWA, GDRHIS.FUNC);
                CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
                B070_CANCEL_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_HIS_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B020_RELEASE_GDKD_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT - WS_RELSE_AMT;
        } else {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT + WS_RELSE_AMT;
        }
        if (GDRPLDR.RELAT_AMT == 0 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("030500")) {
            GDRPLDR.RELAT_STS = 'R';
            GDRPLDR.RELS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRPLDR.RELS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            GDRPLDR.RELS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            GDRPLDR.RELS_CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRPLDR.RELS_USR = SCCGWA.COMM_AREA.TL_ID;
        }
        if (GDRPLDR.RELAT_STS == 'R' 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            GDRPLDR.RELAT_STS = 'N';
            GDRPLDR.RELS_DATE = 0;
            GDRPLDR.RELS_TIME = 0;
            GDRPLDR.RELS_BR = 0;
            GDRPLDR.RELS_CHNL_CD = " ";
            GDRPLDR.RELS_USR = " ";
        }
        GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTPLDR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
            GDRHIS.RSEQ = GDRPLDR.KEY.RSEQ;
            GDRHIS.AC = GDRPLDR.KEY.AC;
            GDRHIS.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            GDRHIS.FUNC = '7';
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRHIS.TR_AMT = WS_RELSE_AMT;
            GDRHIS.CAN_FLG = 'N';
            GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_GDTHIS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            T000_READUP_GDTHIS();
            if (pgmRtn) return;
            GDRHIS.CAN_FLG = 'R';
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_GDTHIS();
            if (pgmRtn) return;
        }
    }
    public void B020_GDKD_DRPLDR_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - WS_DR_AMT;
            T000_UPDATE_DDTCCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, WS_DR_AMT);
        if (DDRCCY.MARGIN_BAL < 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_MBAL_LESS_THAN_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_VAL_RBAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        if (WS_VAL_RBAL < WS_RELSE_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_AMOUNT = 0;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        WS_AMOUNT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, WS_AMOUNT);
        CEP.TRC(SCCGWA, WS_DR_AMT);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        if (WS_AMOUNT < WS_DR_AMT 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "DR-TXAMT LARGER THAN ");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCUMPDR.INPUT.AC;
        if (GDCUMPDR.INPUT.OTHER_AC.trim().length() == 0) {
            DDCUDRAC.OTHER_AC = GDCUMPDR.INPUT.OTHER_AC;
            DDCUDRAC.OTHER_AC_NM = GDCUMPDR.INPUT.OTHER_AC_NM;
        } else {
            DDCUDRAC.RLT_AC = GDCUMPDR.INPUT.RLT_AC;
            DDCUDRAC.RLT_AC_NAME = GDCUMPDR.INPUT.RLT_AC_NAME;
            DDCUDRAC.RLT_BK_NM = GDCUMPDR.INPUT.RLT_BK_NM;
        }
        DDCUDRAC.CCY = DDRCCY.CCY;
        DDCUDRAC.CCY_TYPE = DDRCCY.CCY_TYPE;
        DDCUDRAC.TX_AMT = WS_DR_AMT;
        if (GDCUMPDR.INPUT.MMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A019";
        } else {
            DDCUDRAC.TX_MMO = GDCUMPDR.INPUT.MMO;
        }
        DDCUDRAC.REMARKS = GDCUMPDR.INPUT.RMK;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = GDCUMPDR.INPUT.AC;
            DDRCCY.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                DDRCCY.CCY_TYPE = '1';
            } else {
                DDRCCY.CCY_TYPE = '2';
            }
            T000_READ_UPDDATE_DDTCCY();
            if (pgmRtn) return;
            DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + WS_DR_AMT;
            T000_UPDATE_DDTCCY();
            if (pgmRtn) return;
        }
        T000_READ_UPDDATE_DDTCCY();
        if (pgmRtn) return;
        WS_PAYING_INT = DDCUDRAC.MARGIN_INT;
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
    }
    public void B028_CR_INTAC_PROC() throws IOException,SQLException,Exception {
        if (GDRPLDR.AC_TYP == '0') {
            IBS.init(SCCGWA, GDRSTAC);
            GDRSTAC.KEY.AC = GDRPLDR.KEY.AC;
            T000_READ_GDTSTAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
            CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
            if (GDRSTAC.INT_AC.trim().length() == 0) {
                GDCUMPDR.OUTPUT.STAC = GDRSTAC.ST_AC;
            } else {
                GDCUMPDR.OUTPUT.STAC = GDRSTAC.INT_AC;
            }
        }
    }
    public void B070_CANCEL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        WS_CANCEL_AC = GDRHIS.AC;
        WS_CANCEL_AMT = GDRHIS.TR_AMT;
        WS_CANCEL_AC_SEQ = GDRHIS.AC_SEQ;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'S';
        CICQACAC.DATA.AGR_NO = GDRHIS.AC;
        CICQACAC.DATA.AGR_SEQ = GDRHIS.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_CANCEL_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        IBS.init(SCCGWA, GDRPLDR);
        if (GDRHIS.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDRHIS.RSEQ;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            R000_READ_PLDR_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        CEP.TRC(SCCGWA, WS_CANCEL_AMT);
        if (WS_CANCEL_AMT != 0) {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT + WS_CANCEL_AMT;
        }
        GDRPLDR.RELAT_STS = 'N';
        GDRPLDR.RELS_DATE = 0;
        GDRPLDR.RELS_TIME = 0;
        GDRPLDR.RELS_BR = 0;
        GDRPLDR.RELS_CHNL_CD = " ";
        GDRPLDR.RELS_USR = " ";
        T000_REWRITE_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        GDRHIS.CAN_FLG = 'C';
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, TDRSMST);
            WS_AC = WS_CANCEL_ACO_AC;
            T000_READUP_TDTSMST();
            if (pgmRtn) return;
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + WS_CANCEL_AMT;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR.OUTPUT);
        GDCUMPDR.OUTPUT.ACTYP = GDRPLDR.AC_TYP;
        GDCUMPDR.OUTPUT.CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            GDCUMPDR.OUTPUT.CTYP = '1';
        } else {
            GDCUMPDR.OUTPUT.CTYP = '2';
        }
        GDCUMPDR.OUTPUT.RAMT = GDRPLDR.RELAT_AMT;
        GDCUMPDR.OUTPUT.CDT = GDRPLDR.CRT_DATE;
        GDCUMPDR.OUTPUT.CTM = GDRPLDR.RELAT_TIME;
        GDCUMPDR.OUTPUT.INT = WS_PAYING_INT;
        GDCUMPDR.OUTPUT.STAC = WS_INT_AC;
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.ACTYP);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.CCY);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.CTYP);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.RAMT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.CDT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.CTM);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
    }
    public void T000_STARTBR_BY_DTJRNTYP() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND AC = :GDRHIS.AC";
        GDTHIS_BR.rp.upd = true;
        GDTHIS_BR.rp.order = "SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HIS_FLG = 'N';
        }
    }
    public void T000_ENDBR_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTHIS_BR);
    }
    public void R000_GET_DDAC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = GDCUMPDR.INPUT.AC;
        DDCRMST.FUNC = 'I';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
    }
    public void R000_GET_TDAC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC = ");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_PLDR_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READUPD_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        if (GDCUMPDR.INPUT.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDCUMPDR.INPUT.RSEQ;
            GDRPLDR.KEY.AC = GDCUMPDR.INPUT.AC;
            GDRPLDR.KEY.AC_SEQ = WS_AC_SEQ;
        } else {
            GDRPLDR.DEAL_CD = GDCUMPDR.INPUT.CTA_NO;
            GDRPLDR.BSREF = GDCUMPDR.INPUT.REF_NO;
            GDRPLDR.KEY.AC = GDCUMPDR.INPUT.AC;
            GDRPLDR.KEY.AC_SEQ = WS_AC_SEQ;
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        T000_READ_FIRST_PROC();
        if (pgmRtn) return;
        WS_RSEQ = GDRPLDR.KEY.RSEQ;
        WS_PLDR_AC = GDRPLDR.KEY.AC;
        WS_PLDR_SEQ = GDRPLDR.KEY.AC_SEQ;
        CEP.TRC(SCCGWA, WS_RSEQ);
        CEP.TRC(SCCGWA, WS_PLDR_AC);
        CEP.TRC(SCCGWA, WS_PLDR_SEQ);
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.RSEQ = WS_RSEQ;
        GDRPLDR.KEY.AC = WS_PLDR_AC;
        GDRPLDR.KEY.AC_SEQ = WS_PLDR_SEQ;
        GDCRPLDR.FUNC = 'I';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR);
    }
    public void R000_WRITE_CANCEL_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.CAN_FLG = 'R';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.FUNC = 'A';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRHIS);
    }
    public void T000_READ_FIRST_PROC() throws IOException,SQLException,Exception {
        if (GDRPLDR.KEY.RSEQ.trim().length() > 0) {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
            GDTPLDR_RD.fst = true;
            GDTPLDR_RD.order = "RSEQ DESC";
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        } else {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
                + "AND BSREF = :GDRPLDR.BSREF "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
            GDTPLDR_RD.fst = true;
            GDTPLDR_RD.order = "RSEQ DESC";
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_GDTHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        GDRHIS.AC = GDCUMPDR.INPUT.AC;
        CEP.TRC(SCCGWA, GDRHIS.AC);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND AC = :GDRHIS.AC";
        GDTHIS_RD.fst = true;
        IBS.READ(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_GDTPLDR_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.DEAL_CD = GDCUMPDR.INPUT.CTA_NO;
        GDRPLDR.BSREF = GDCUMPDR.INPUT.REF_NO;
        GDRPLDR.KEY.AC = GDCUMPDR.INPUT.AC;
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        CEP.TRC(SCCGWA, GDRPLDR.BSREF);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.upd = true;
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_UPDATE_PLDR_PROC() throws IOException,SQLException,Exception {
        GDCRPLDR.FUNC = 'U';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
    }
    public void T000_READUP_GDTHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.DEAL_CD);
        CEP.TRC(SCCGWA, GDRHIS.BSREF);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.where = "DEAL_CD = :GDRHIS.DEAL_CD "
            + "AND BSREF = :GDRHIS.BSREF "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO";
        GDTHIS_RD.upd = true;
        IBS.READ(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.REWRITE(SCCGWA, GDRHIS, GDTHIS_RD);
    }
    public void T000_WRITE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_GROUP_KD_GDTPLDR() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCUMPDR.INPUT.AC;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-KDGD-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_GROUP_RAMT_PROC() throws IOException,SQLException,Exception {
        GDRPLDR.RELAT_STS = 'N';
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND REF_TYP IN ( '10' , '12' ) "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-RAMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_UPDDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
