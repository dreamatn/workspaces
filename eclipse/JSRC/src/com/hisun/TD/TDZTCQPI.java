package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZTCQPI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "DD129";
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    SCCFMT SCCFMT = new SCCFMT();
    TDCOTRAC TDCOTRAC = new TDCOTRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    TDCTCQPI TDCTCQPI;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCTCQPI TDCTCQPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCTCQPI = TDCTCQPI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZTCQPI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        if (TDCTCQPI.BV_TYP != ' ' 
            && TDCTCQPI.BV_TYP != '4') {
            IBS.init(SCCGWA, BPCPRMM);
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = "TDP";
            BPRPRMT.KEY.CD = "" + TDCTCQPI.BV_TYP;
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCBVCD);
            TDCTCQPI.BV_CD = TDCBVCD.CD;
            CEP.TRC(SCCGWA, TDCTCQPI.BV_CD);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CALL_TD_DR_UNT();
        if (TDCTCQPI.CT_FLG == '0') {
            B200_CALL_CSH_CR_UNT();
        } else if (TDCTCQPI.CT_FLG == '1') {
            B210_CALL_AI_CR_UNT();
        } else if (TDCTCQPI.CT_FLG == '2'
            || TDCTCQPI.CT_FLG == '3'
            || TDCTCQPI.CT_FLG == '4') {
            if (TDCTCQPI.OPP_CARD_NO.trim().length() > 0) {
                R000_CHECK_DDAC_INFO();
            }
            B230_CALL_DD_CR_UNT();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B240_OUTPUT_PROC();
            }
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 19);
        }
    }
    public void B100_CALL_TD_DR_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTCQPI.BV_CD);
        CEP.TRC(SCCGWA, TDCTCQPI.AC);
        CEP.TRC(SCCGWA, TDCTCQPI.CCY);
        CEP.TRC(SCCGWA, TDCTCQPI.FC_CD);
        CEP.TRC(SCCGWA, TDCTCQPI.CT_FLG);
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_AC);
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_BV_NO);
        CEP.TRC(SCCGWA, TDCTCQPI.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCTCQPI.ID_TYP);
        CEP.TRC(SCCGWA, TDCTCQPI.ID_NO);
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.OPT = '3';
        TDCACDRU.PRDMO_CD = "MMDP";
        TDCACDRU.BV_CD = TDCTCQPI.BV_CD;
        TDCACDRU.BV_TYP = TDCTCQPI.BV_TYP;
        TDCACDRU.BV_NO = TDCTCQPI.BV_NO;
        if (TDCTCQPI.BV_TYP == '4') {
            TDCACDRU.MAC_CNO = TDCTCQPI.CARD_NO;
            TDCACDRU.CVV = TDCTCQPI.CVV;
        } else {
            TDCACDRU.MAC_CNO = TDCTCQPI.AC;
        }
        TDCACDRU.AC_SEQ = TDCTCQPI.AC_SEQ;
        TDCACDRU.NAME = TDCTCQPI.NAME;
        TDCACDRU.ADDR = TDCTCQPI.ADDR;
        TDCACDRU.CCY = TDCTCQPI.CCY;
        TDCACDRU.CCY_TYP = TDCTCQPI.CCY_TYP;
        TDCACDRU.DRAW_MTH = TDCTCQPI.DRAW_MTH;
        if (TDCTCQPI.DRAW_MTH == '1'
            || TDCTCQPI.DRAW_MTH == '4') {
            TDCACDRU.DRAW_INF = TDCTCQPI.PSW;
        } else if (TDCTCQPI.DRAW_MTH == '2') {
        } else if (TDCTCQPI.DRAW_MTH == '3') {
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTCQPI.ID_TYP == null) TDCTCQPI.ID_TYP = "";
            JIBS_tmp_int = TDCTCQPI.ID_TYP.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) TDCTCQPI.ID_TYP += " ";
            TDCACDRU.DRAW_INF = TDCTCQPI.ID_TYP + TDCACDRU.DRAW_INF.substring(5);
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTCQPI.ID_NO == null) TDCTCQPI.ID_NO = "";
            JIBS_tmp_int = TDCTCQPI.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) TDCTCQPI.ID_NO += " ";
            TDCACDRU.DRAW_INF = TDCACDRU.DRAW_INF.substring(0, 6 - 1) + TDCTCQPI.ID_NO + TDCACDRU.DRAW_INF.substring(6 + 25 - 1);
        } else if (TDCTCQPI.DRAW_MTH == '5') {
            TDCACDRU.DRAW_INF = " ";
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH, 15);
        }
        TDCACDRU.ID_TYP = TDCTCQPI.ID_TYP;
        TDCACDRU.ID_NO = TDCTCQPI.ID_NO;
        TDCACDRU.PRT_FLG = TDCTCQPI.PRT_FLG;
        TDCACDRU.CT_FLG = TDCTCQPI.CT_FLG;
        if (TDCTCQPI.OPP_CARD_NO.trim().length() > 0) {
            TDCACDRU.OPP_AC_CNO = TDCTCQPI.OPP_CARD_NO;
        } else {
            TDCACDRU.OPP_AC_CNO = TDCTCQPI.OPP_AC;
        }
        TDCACDRU.TXN_CHNL = TDCTCQPI.TXN_CHNL;
        TDCACDRU.TXN_PNT = TDCTCQPI.TXN_PNT;
        TDCACDRU.AC_TRK2 = TDCTCQPI.AC_TRK2;
        TDCACDRU.AC_TRK3 = TDCTCQPI.AC_TRK3;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCACDRU.PAYING_INT = TDCTCQPI.PAYINT_INT;
        }
        S000_CALL_TDZACDRU();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDCTCQPI.PAYINT_INT = TDCACDRU.PAYING_INT;
        }
    }
    public void B200_CALL_CSH_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = TDCTCQPI.CCY;
        BPCUSBOX.CCY_TYP = TDCTCQPI.CCY_TYP;
        BPCUSBOX.AMT = TDCACDRU.PAYING_INT;
        BPCUSBOX.OPP_AC = TDCTCQPI.AC;
        BPCUSBOX.OPP_ACNM = TDCTCQPI.NAME;
        BPCUSBOX.ID_TYP = TDCTCQPI.ID_TYP;
        BPCUSBOX.IDNO = TDCTCQPI.ID_NO;
        S000_CALL_BPZUSBOX();
    }
    public void B210_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
        AICUUPIA.DATA.AC_NO = TDCTCQPI.OPP_AC;
        AICUUPIA.DATA.RVS_NO = TDCTCQPI.CREV_NO;
        AICUUPIA.DATA.CCY = TDCTCQPI.CCY;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (AICUUPIA.DATA.DESC == null) AICUUPIA.DATA.DESC = "";
        JIBS_tmp_int = AICUUPIA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) AICUUPIA.DATA.DESC += " ";
        if (TDCTCQPI.AC == null) TDCTCQPI.AC = "";
        JIBS_tmp_int = TDCTCQPI.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCTCQPI.AC += " ";
        AICUUPIA.DATA.DESC = TDCTCQPI.AC + AICUUPIA.DATA.DESC.substring(32);
        if (AICUUPIA.DATA.DESC == null) AICUUPIA.DATA.DESC = "";
        JIBS_tmp_int = AICUUPIA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) AICUUPIA.DATA.DESC += " ";
        JIBS_tmp_str[0] = "" + TDCTCQPI.AC_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        AICUUPIA.DATA.DESC = AICUUPIA.DATA.DESC.substring(0, 33 - 1) + JIBS_tmp_str[0] + AICUUPIA.DATA.DESC.substring(33 + 6 - 1);
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_CARD_NO);
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_AC);
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.CARD_NO = TDCTCQPI.OPP_CARD_NO;
        DDCUCRAC.AC = TDCTCQPI.OPP_AC;
        DDCUCRAC.CCY = TDCTCQPI.CCY;
        DDCUCRAC.CCY_TYPE = TDCTCQPI.CCY_TYP;
        DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
        if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
        JIBS_tmp_int = DDCUCRAC.REMARKS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
        if (TDCTCQPI.AC == null) TDCTCQPI.AC = "";
        JIBS_tmp_int = TDCTCQPI.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCTCQPI.AC += " ";
        DDCUCRAC.REMARKS = TDCTCQPI.AC + DDCUCRAC.REMARKS.substring(32);
        if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
        JIBS_tmp_int = DDCUCRAC.REMARKS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
        JIBS_tmp_str[0] = "" + TDCTCQPI.AC_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DDCUCRAC.REMARKS = DDCUCRAC.REMARKS.substring(0, 33 - 1) + JIBS_tmp_str[0] + DDCUCRAC.REMARKS.substring(33 + 6 - 1);
        DDCUCRAC.OTHER_CCY = TDCTCQPI.CCY;
        if (TDCTCQPI.CT_FLG == '0') {
            DDCUCRAC.TX_TYPE = 'C';
        } else {
            DDCUCRAC.TX_TYPE = 'T';
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DDCUCRAC.PSBK_NO = TDCTCQPI.OPP_BV_NO;
        }
        DDCUCRAC.TX_MMO = "S106";
        DDCUCRAC.BANK_CR_FLG = 'N';
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCTCQPI.OPP_AC;
        if (TDCTCQPI.OPP_AC.trim().length() == 0) {
            CICACCU.DATA.AGR_NO = TDCTCQPI.OPP_CARD_NO;
        }
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.CHK_LMT_FLG = '4';
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        if (CICACCU.DATA.ENTY_TYP == '5' 
            || CICACCU.DATA.ENTY_TYP == '6') {
            DDCUCRAC.EA_CHK_FLG = 'N';
        }
        S000_CALL_DDZUCRAC();
    }
    public void B240_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOTRAC);
        TDCOTRAC.TX_TYP = 'T';
        TDCOTRAC.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOTRAC.FR_AC = TDCTCQPI.AC;
        TDCOTRAC.ENG_NM1 = " ";
        TDCOTRAC.CHN_NM1 = TDCTCQPI.NAME;
        TDCOTRAC.TO_AC = TDCTCQPI.OPP_AC;
        TDCOTRAC.ENG_NM2 = " ";
        TDCOTRAC.CHN_NM2 = TDCTCQPI.OPP_NAME;
        TDCOTRAC.FR_CCY = TDCTCQPI.CCY;
        TDCOTRAC.FR_CCY_TYPE = TDCTCQPI.CCY_TYP;
        TDCOTRAC.TO_CCY = TDCTCQPI.CCY;
        TDCOTRAC.TO_CCY_TYPE = TDCTCQPI.CCY_TYP;
        TDCOTRAC.FR_AMT = TDCACDRU.PAYING_INT;
        TDCOTRAC.TO_AMT = TDCACDRU.PAYING_INT;
        TDCOTRAC.FR_PSBK = TDCTCQPI.BV_NO;
        TDCOTRAC.FR_CARD = TDCTCQPI.CARD_NO;
        TDCOTRAC.TO_CARD = TDCTCQPI.OPP_CARD_NO;
        TDCOTRAC.TX_REF = " ";
        TDCOTRAC.TICKET_NO = " ";
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOTRAC;
        SCCFMT.DATA_LEN = 1292;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_DDAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCTCQPI.OPP_CARD_NO;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
    }
    public void R000_CHECK_DD_CARD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_TRK2);
        CEP.TRC(SCCGWA, TDCTCQPI.OPP_TRK3);
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = TDCTCQPI.OPP_CARD_NO;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "03";
        DCCPFTCK.VAL.TXN_CCY = TDCTCQPI.CCY;
        DCCPFTCK.VAL.TXN_AMT = TDCACDRU.PAYING_INT;
        DCCPFTCK.TRK2_DAT = TDCTCQPI.OPP_TRK2;
        DCCPFTCK.TRK3_DAT = TDCTCQPI.OPP_TRK3;
        S000_CALL_DCZPFTCK();
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
