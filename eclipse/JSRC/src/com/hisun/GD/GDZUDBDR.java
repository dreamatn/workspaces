package com.hisun.GD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZUDBDR {
    int JIBS_tmp_int;
    DBParm GDTSTAC_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
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
    char WS_PLDR_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
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
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    double WS_RAMT = 0;
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
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZUDBDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B020_CHK_MST_INF_PROC();
        B030_WITHDRAW_PROC();
        B070_OUTPUT_PROCESS();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.FUNC);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AC);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.SEQ);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AMT);
        CEP.TRC(SCCGWA, GDCUMPDR.INPUT.AMT);
        if (GDCUMPDR.INPUT.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCUMPDR.INPUT.AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHK_MST_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
        CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
        S000_CALL_CIZQACAC();
        GDCUMPDR.OUTPUT.CCY = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
        if (GDCUMPDR.INPUT.SEQ == 0) {
            R000_GET_DDAC_INF_PROC();
            if (DDCRMST.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
                S000_ERR_MSG_PROC();
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRMST.AC_STS == 'C') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HOLD;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (GDCUMPDR.INPUT.SEQ != 0) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
                CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
                S000_CALL_CIZQACAC();
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            }
            R000_GET_TDAC_INF_PROC();
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = GDCUMPDR.INPUT.AC;
            T000_READ_TDTCMST();
            CEP.TRC(SCCGWA, TDRCMST.STS);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (TDRCMST.STS == '1') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                }
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HOLD;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B030_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_DR_AMT = GDCUMPDR.INPUT.AMT;
        }
        if (GDCUMPDR.INPUT.SEQ == 0) {
            B030_01_DD_WITHDRAW_PROC();
        } else {
            B030_02_TD_WITHDRAW_PROC();
        }
    }
    public void B030_01_DD_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCUMPDR.INPUT.AC;
        DDRCCY.CCY = GDCUMPDR.OUTPUT.CCY;
        if (GDCUMPDR.OUTPUT.CCY.equalsIgnoreCase("156")) {
            DDRCCY.CCY_TYPE = '1';
        } else {
            DDRCCY.CCY_TYPE = '2';
        }
        T000_READ_UPDDATE_DDTCCY();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
            CEP.TRC(SCCGWA, WS_DR_AMT);
            WS_VAL_RAMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
            if (WS_VAL_RAMT < WS_DR_AMT) {
                CEP.TRC(SCCGWA, "DD AMT INVAILD");
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PAY_AMT_GREATER_BAL;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCUMPDR.INPUT.AC;
        DDCUDRAC.CCY = GDCUMPDR.OUTPUT.CCY;
        if (GDCUMPDR.OUTPUT.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = '2';
        }
        DDCUDRAC.TX_AMT = GDCUMPDR.INPUT.AMT;
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
        WS_PAYING_INT = DDCUDRAC.MARGIN_INT;
        if (WS_PAYING_INT > 0) {
            IBS.init(SCCGWA, GDCRSTAC);
            IBS.init(SCCGWA, GDRSTAC);
            GDCRSTAC.FUNC = 'I';
            GDRSTAC.KEY.AC = GDCUMPDR.INPUT.AC;
            GDCRSTAC.REC_PTR = GDRSTAC;
            GDCRSTAC.REC_LEN = 401;
            S000_CALL_GDZRSTAC();
            CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
            WS_INT_AC = GDRSTAC.INT_AC;
        }
    }
    public void B030_02_TD_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        T000_READ_UPDATE_TDTSMST();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.HBAL);
            WS_VAL_RAMT = TDRSMST.BAL - TDRSMST.HBAL;
            if (WS_VAL_RAMT < WS_DR_AMT) {
                CEP.TRC(SCCGWA, "TD AMT INVAILD");
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PAY_AMT_GREATER_BAL;
                S000_ERR_MSG_PROC();
            }
        }
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
        if (GDCUMPDR.INPUT.SEQ == 0) {
            TDCACDRU.MAC_CNO = GDCUMPDR.INPUT.AC;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDCUMPDR.INPUT.AC;
            CICQACAC.DATA.AGR_SEQ = GDCUMPDR.INPUT.SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
            CEP.TRC(SCCGWA, WS_SUB_AC);
            CEP.TRC(SCCGWA, WS_AC);
            TDCACDRU.MAC_CNO = GDCUMPDR.INPUT.AC;
            TDCACDRU.ACO_AC = CICQACAC.DATA.ACAC_NO;
        }
        TDCACDRU.AC_SEQ = GDCUMPDR.INPUT.SEQ;
        TDCACDRU.CCY = GDCUMPDR.OUTPUT.CCY;
        if (GDCUMPDR.OUTPUT.CCY.equalsIgnoreCase("156")) {
            TDCACDRU.CCY_TYP = '1';
        } else {
            TDCACDRU.CCY_TYP = '2';
        }
        TDCACDRU.TXN_AMT = GDCUMPDR.INPUT.AMT;
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
        WS_PAYING_INT = TDCACDRU.PAYING_INT;
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCUMPDR.INPUT.AC;
        GDRSTAC.KEY.AC_SEQ = GDCUMPDR.INPUT.SEQ;
        T000_READ_GDTSTAC();
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
                    AICPQIA.CCY = GDCUMPDR.OUTPUT.CCY;
                    AICPQIA.SIGN = 'C';
                    S000_CALL_AIZPQIA();
                    CEP.TRC(SCCGWA, AICPQIA.AC);
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = WS_PAYING_INT;
                    AICUUPIA.DATA.CCY = GDCUMPDR.OUTPUT.CCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                } else {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_MAINTAIN_INT_AC;
                    S000_ERR_MSG_PROC();
                }
            } else {
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.AC = WS_INT_AC;
                DDCUCRAC.CCY = GDCUMPDR.OUTPUT.CCY;
                if (GDCUMPDR.OUTPUT.CCY.equalsIgnoreCase("156")) {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = '2';
                }
                DDCUCRAC.TX_AMT = WS_PAYING_INT;
                DDCUCRAC.OTHER_AC = GDCUMPDR.INPUT.AC;
                DDCUCRAC.TX_MMO = "S101";
                S000_CALL_DDZUCRAC();
            }
        }
    }
    public void B060_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.AC = GDCUMPDR.INPUT.AC;
        GDRHIS.FUNC = '2';
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCUMPDR.INPUT.AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            GDRHIS.CAN_FLG = 'R';
        } else {
            GDRHIS.CAN_FLG = 'N';
        }
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.FUNC = 'A';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
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
    public void R000_GET_DDAC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = GDCUMPDR.INPUT.AC;
        DDCRMST.FUNC = 'I';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
    }
    public void R000_GET_TDAC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        T000_READ_TDTSMST();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC = ");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
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
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
