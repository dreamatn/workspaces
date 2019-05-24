package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSTGDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm GDTSTAC_RD;
    DBParm GDTHIS_RD;
    brParm GDTHIS_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD121";
    String K_DDEG3 = "DDEG3";
    String WS_ERR_MSG = " ";
    double WS_VAVL_BAL = 0;
    char WS_OPT = ' ';
    double WS_PAYING_INT = 0;
    String WS_INT_AC = " ";
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC1 = " ";
    String WS_OTHER_AC = " ";
    char WS_HIS_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRHIS GDRHIS = new GDRHIS();
    GDCRHIS GDCRHIS = new GDCRHIS();
    GDCOTGDR GDCOTGDR = new GDCOTGDR();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    CICACCU CICACCU = new CICACCU();
    AICPQIA AICPQIA = new AICPQIA();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    GDRSTAC GDRSTAC = new GDRSTAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDB1210_AWA_1210 GDB1210_AWA_1210;
    GDCSTGDR GDCSTGDR;
    public void MP(SCCGWA SCCGWA, GDCSTGDR GDCSTGDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSTGDR = GDCSTGDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSTGDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHECK_CI_NO();
            if (pgmRtn) return;
        }
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B350_UPDATE_STAC_PROC();
        if (pgmRtn) return;
        B400_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK CI_NO");
        if (GDCSTGDR.VAL.INTDD_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSTGDR.VAL.INTDD_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            WS_INT_AC1 = CICQACRI.O_DATA.O_AGR_NO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = WS_INT_AC1;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_NO1 = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, CICACCU);
            if ((GDCSTGDR.VAL.MAC.trim().length() > 0) 
                && (GDCSTGDR.VAL.SEQ != 0)) {
                CICACCU.DATA.AGR_NO = GDCSTGDR.VAL.MAC;
            } else {
                if (GDCSTGDR.VAL.DD_AC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = GDCSTGDR.VAL.DD_AC;
                }
            }
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_NO2 = CICACCU.DATA.CI_NO;
            GDCSTGDR.VAL.CI_NO = CICACCU.DATA.CI_NO;
            GDCSTGDR.VAL.CI_NM = CICACCU.DATA.CI_CNM;
            CEP.TRC(SCCGWA, GDCSTGDR.VAL.CI_NM);
            CEP.TRC(SCCGWA, WS_CI_NO1);
            CEP.TRC(SCCGWA, WS_CI_NO2);
            CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
            CEP.TRC(SCCGWA, GDCSTGDR.VAL.INTDD_AC);
            if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2)) {
                if ((!TDRSMST.OPEN_DR_AC.equalsIgnoreCase(GDCSTGDR.VAL.INTDD_AC)) 
                    && (GDCSTGDR.VAL.INTDD_AC.trim().length() > 0)) {
                    IBS.init(SCCGWA, CICSOEC);
                    CICSOEC.DATA.CI_NO = WS_CI_NO1;
                    CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                    S000_CALL_CIZSOEC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICSOEC.DATA.CI_NO);
                    CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                    CEP.TRC(SCCGWA, WS_CI_NO2);
                    if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_CI_NO2)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (GDCSTGDR.VAL.STLT == '3') {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSTGDR.VAL.INTDD_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = GDCSTGDR.VAL.INTDD_AC;
            DDRCCY.CCY = GDCSTGDR.VAL.CCY;
            DDRCCY.CCY_TYPE = GDCSTGDR.VAL.TYP;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'M') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CLOSE_AND_REDEMP;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.MAC);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.SEQ);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DD_AC);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.CCY);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.TYP);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DRAWAMT_S);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.INT_F);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DDAC_NM);
        if (GDCSTGDR.VAL.DD_AC.trim().length() == 0) {
            if (GDCSTGDR.VAL.MAC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_M_AC_M_INPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDCSTGDR.VAL.MAC.trim().length() > 0) {
            if (GDCSTGDR.VAL.SEQ == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SEQ_MST_INPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDCSTGDR.VAL.STLT == '1' 
            || GDCSTGDR.VAL.STLT == '3') {
            if (GDCSTGDR.VAL.EIADD_AC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_EIADD_AC_MST_INPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'S';
        CICQACAC.DATA.AGR_NO = GDCSTGDR.VAL.MAC;
        CICQACAC.DATA.AGR_SEQ = GDCSTGDR.VAL.SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DD_AC);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        T000_READUPD_TDTSMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1));
            if (TDRSMST.ACO_STS == '0') {
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
                    if (pgmRtn) return;
                }
            }
            if (TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.ACO_STS == '2') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_REVERSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = GDCSTGDR.VAL.MAC;
        T000_READUPD_TDTCMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
        B200_DRAW_PROC();
        if (pgmRtn) return;
    }
    public void B200_DRAW_PROC() throws IOException,SQLException,Exception {
        WS_VAVL_BAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        CEP.TRC(SCCGWA, WS_VAVL_BAL);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (GDCSTGDR.VAL.DRAWAMT_S > WS_VAVL_BAL) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDCSTGDR.VAL.DRAWAMT_S < TDRSMST.BAL) {
            WS_OPT = '8';
        } else {
            WS_OPT = '9';
        }
        CEP.TRC(SCCGWA, "11111111111111111111");
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.MAC_CNO = GDCSTGDR.VAL.MAC;
        TDCACDRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        TDCACDRU.OPT = WS_OPT;
        TDCACDRU.AC_SEQ = GDCSTGDR.VAL.SEQ;
        TDCACDRU.CCY = GDCSTGDR.VAL.CCY;
        TDCACDRU.CCY_TYP = GDCSTGDR.VAL.TYP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDCACDRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            TDCACDRU.VAL_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        TDCACDRU.DRAW_MTH = '4';
        TDCACDRU.TXN_AMT = GDCSTGDR.VAL.DRAWAMT_S;
        TDCACDRU.OPP_AC_CNO = GDCSTGDR.VAL.EIADD_AC;
        if (GDCSTGDR.VAL.STLT == '3') {
            TDCACDRU.CT_FLG = '2';
        } else {
            TDCACDRU.CT_FLG = GDCSTGDR.VAL.STLT;
        }
        TDCACDRU.PRDMO_CD = "MMDP";
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
        TDCACDRU.TXN_MMO = "A019";
        TDCACDRU.REMARK = GDCSTGDR.VAL.SMR;
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.INTDD_AC);
        CEP.TRC(SCCGWA, TDCACDRU.INT_AC);
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        WS_PAYING_INT = 0;
        if (GDCSTGDR.VAL.INTDD_AC.trim().length() == 0) {
            WS_PAYING_INT = TDCACDRU.PAYING_INT;
        }
        B300_10_BAL_PRO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.STLT);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.INTDD_AC);
        CEP.TRC(SCCGWA, TDCACDRU.INT_AC);
        if (GDCSTGDR.VAL.INTDD_AC.trim().length() == 0) {
        } else {
            WS_INT_AC = GDCSTGDR.VAL.INTDD_AC;
            WS_PAYING_INT = TDCACDRU.PAYING_INT;
            B300_20_INT_PRO();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
            CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
            IBS.init(SCCGWA, GDRHIS);
            GDCRHIS.FUNC = 'A';
            GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
            GDRHIS.AC_SEQ = GDCSTGDR.VAL.SEQ;
            if (GDCSTGDR.VAL.MAC.trim().length() > 0) {
                GDRHIS.AC = GDCSTGDR.VAL.MAC;
            } else {
                GDRHIS.AC = GDCSTGDR.VAL.DD_AC;
            }
            GDRHIS.FUNC = '2';
            GDRHIS.TR_AMT = GDCSTGDR.VAL.DRAWAMT_S;
            GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDRHIS.CAN_FLG = 'N';
            GDRHIS.TR_AC = GDCSTGDR.VAL.EIADD_AC;
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDCRHIS.REC_PTR = GDRHIS;
            GDCRHIS.REC_LEN = 281;
            S000_CALL_GDZRHIS();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            T000_STARTBR_BY_DTJRN();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            while (WS_HIS_FLG != 'N') {
                if (GDRHIS.CAN_FLG == 'N') {
                    GDRHIS.CAN_FLG = 'C';
                    GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_UPDATE_GDTHIS();
                    if (pgmRtn) return;
                    B300_WRITE_CANCEL_HIS();
                    if (pgmRtn) return;
                }
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_10_BAL_PRO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DEP_NM);
        if (GDCSTGDR.VAL.STLT == '1') {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.AC_NO = GDCSTGDR.VAL.EIADD_AC;
            AICUUPIA.DATA.PAY_MAN = GDCSTGDR.VAL.DEP_NM;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = GDCSTGDR.VAL.DRAWAMT_S + WS_PAYING_INT;
            AICUUPIA.DATA.CCY = GDCSTGDR.VAL.CCY;
            AICUUPIA.DATA.POST_NARR = " ";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        if (GDCSTGDR.VAL.STLT == '3') {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = GDCSTGDR.VAL.EIADD_AC;
            DDCUCRAC.CCY = GDCSTGDR.VAL.CCY;
            DDCUCRAC.CCY_TYPE = GDCSTGDR.VAL.TYP;
            DDCUCRAC.TX_AMT = GDCSTGDR.VAL.DRAWAMT_S + WS_PAYING_INT;
            DDCUCRAC.TX_MMO = "A001";
            DDCUCRAC.OTHER_AC = GDCSTGDR.VAL.MAC;
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            if (pgmRtn) return;
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            DDCUCRAC.REMARKS = GDCSTGDR.VAL.SMR;
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
            GDCSTGDR.VAL.DEP_NM = DDCUCRAC.AC_NAME;
            CEP.TRC(SCCGWA, DDCUCRAC.AC_NAME);
        }
    }
    public void B300_20_INT_PRO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INT_AC);
        CEP.TRC(SCCGWA, WS_PAYING_INT);
        if (WS_PAYING_INT != 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = WS_INT_AC;
            DDCUCRAC.CCY = GDCSTGDR.VAL.CCY;
            DDCUCRAC.CCY_TYPE = GDCSTGDR.VAL.TYP;
            DDCUCRAC.TX_AMT = WS_PAYING_INT;
            DDCUCRAC.TX_MMO = "S101";
            if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
            JIBS_tmp_int = DDCUCRAC.REMARKS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
            if (GDCSTGDR.VAL.MAC == null) GDCSTGDR.VAL.MAC = "";
            JIBS_tmp_int = GDCSTGDR.VAL.MAC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) GDCSTGDR.VAL.MAC += " ";
            DDCUCRAC.REMARKS = GDCSTGDR.VAL.MAC + DDCUCRAC.REMARKS.substring(32);
            if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
            JIBS_tmp_int = DDCUCRAC.REMARKS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
            JIBS_tmp_str[0] = "" + GDCSTGDR.VAL.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCUCRAC.REMARKS = DDCUCRAC.REMARKS.substring(0, 33 - 1) + JIBS_tmp_str[0] + DDCUCRAC.REMARKS.substring(33 + 6 - 1);
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B300_WRITE_CANCEL_HIS() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.CAN_FLG = 'R';
        GDRHIS.TR_AC = GDCSTGDR.VAL.EIADD_AC;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void B350_UPDATE_STAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCSTGDR.VAL.MAC;
        GDRSTAC.KEY.AC_SEQ = GDCSTGDR.VAL.SEQ;
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
        GDRSTAC.RELAT_STS = 'R';
        GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B400_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOTGDR);
        GDCOTGDR.VAL.CI_NO = GDCSTGDR.VAL.CI_NO;
        GDCOTGDR.VAL.CI_NM = GDCSTGDR.VAL.CI_NM;
        GDCOTGDR.VAL.MAC = GDCSTGDR.VAL.MAC;
        GDCOTGDR.VAL.SEQ = GDCSTGDR.VAL.SEQ;
        GDCOTGDR.VAL.DD_AC = GDCSTGDR.VAL.DD_AC;
        GDCOTGDR.VAL.DDAC_NM = GDCSTGDR.VAL.DDAC_NM;
        GDCOTGDR.VAL.CCY = GDCSTGDR.VAL.CCY;
        GDCOTGDR.VAL.TYP = GDCSTGDR.VAL.TYP;
        GDCOTGDR.VAL.DRAWAMT = GDCSTGDR.VAL.DRAWAMT_S;
        GDCOTGDR.VAL.INTAMT = WS_PAYING_INT;
        GDCOTGDR.VAL.EIADD_AC = GDCSTGDR.VAL.EIADD_AC;
        GDCOTGDR.VAL.INTDD_AC = WS_INT_AC;
        GDCOTGDR.VAL.DEP_NM = GDCSTGDR.VAL.DEP_NM;
        GDCOTGDR.VAL.BAL = TDRSMST.BAL;
        GDCOTGDR.VAL.SMR = GDCSTGDR.VAL.SMR;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, "GD121");
        SCCFMT.DATA_PTR = GDCOTGDR;
        SCCFMT.DATA_LEN = 1081;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B410_GET_INTDD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'I';
        GDRSTAC.KEY.AC = GDCSTGDR.VAL.MAC;
        GDRSTAC.KEY.AC_SEQ = GDCSTGDR.VAL.SEQ;
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        GDCSTGDR.VAL.INTDD_AC = GDRSTAC.INT_AC;
        if (GDCSTGDR.VAL.INTDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INTAC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTHER_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_OTHER_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
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
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        CEP.TRC(SCCGWA, DCCUQSAC.RC.RC_CODE);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.REWRITE(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DTJRN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO";
        GDTHIS_BR.rp.upd = true;
        GDTHIS_BR.rp.order = "SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HIS_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTHIS_BR);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
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
