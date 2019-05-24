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

public class TDZTZLPI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "TD219";
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
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    TDCTZLPI TDCTZLPI;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCTZLPI TDCTZLPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCTZLPI = TDCTZLPI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZTZLPI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTZLPI.AC_NO);
        B100_CALL_TD_DR_UNT();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B240_OUTPUT_PROC();
        }
        if (TDCTZLPI.CT_FLG == '0') {
            B200_CALL_CSH_CR_UNT();
        } else if (TDCTZLPI.CT_FLG == '1') {
            B210_CALL_AI_CR_UNT();
        } else if (TDCTZLPI.CT_FLG == '2'
            || TDCTZLPI.CT_FLG == '3'
            || TDCTZLPI.CT_FLG == '4') {
            if (TDCTZLPI.OPP_AC.trim().length() > 0) {
                R000_CHECK_DDAC_INFO();
            }
            B230_CALL_DD_CR_UNT();
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 21);
        }
    }
    public void B100_CALL_TD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.OPT = 'B';
        TDCACDRU.PRDMO_CD = "MMDP";
        TDCACDRU.BV_CD = TDCTZLPI.BV_CODE;
        TDCACDRU.BV_TYP = TDCTZLPI.BV_TYP;
        TDCACDRU.BV_NO = TDCTZLPI.BV_NO;
        TDCACDRU.MAC_CNO = TDCTZLPI.AC_NO;
        TDCACDRU.AC_SEQ = TDCTZLPI.AC_SEQ;
        TDCACDRU.CCY = TDCTZLPI.CCY;
        TDCACDRU.CCY_TYP = TDCTZLPI.CCY_TYP;
        TDCACDRU.DRAW_MTH = TDCTZLPI.DRAW_MTH;
        if (TDCTZLPI.DRAW_MTH == '1'
            || TDCTZLPI.DRAW_MTH == '4') {
            TDCACDRU.DRAW_INF = TDCTZLPI.PSW;
        } else if (TDCTZLPI.DRAW_MTH == '3') {
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTZLPI.ID_TYP == null) TDCTZLPI.ID_TYP = "";
            JIBS_tmp_int = TDCTZLPI.ID_TYP.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) TDCTZLPI.ID_TYP += " ";
            TDCACDRU.DRAW_INF = TDCTZLPI.ID_TYP + TDCACDRU.DRAW_INF.substring(5);
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTZLPI.ID_NO == null) TDCTZLPI.ID_NO = "";
            JIBS_tmp_int = TDCTZLPI.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) TDCTZLPI.ID_NO += " ";
            TDCACDRU.DRAW_INF = TDCACDRU.DRAW_INF.substring(0, 6 - 1) + TDCTZLPI.ID_NO + TDCACDRU.DRAW_INF.substring(6 + 25 - 1);
        } else if (TDCTZLPI.DRAW_MTH == '2'
            || TDCTZLPI.DRAW_MTH == '5') {
            TDCACDRU.DRAW_INF = " ";
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH, 16);
        }
        TDCACDRU.ID_TYP = TDCTZLPI.ID_TYP;
        TDCACDRU.ID_NO = TDCTZLPI.ID_NO;
        TDCACDRU.PRT_FLG = TDCTZLPI.PRT_FLG;
        TDCACDRU.CT_FLG = TDCTZLPI.CT_FLG;
        TDCACDRU.OPP_AC_CNO = TDCTZLPI.OPP_AC;
        TDCACDRU.AC_TRK2 = TDCTZLPI.TRK2_DAT;
        TDCACDRU.AC_TRK3 = TDCTZLPI.TRK3_DAT;
        TDCACDRU.TXN_AMT = TDCTZLPI.AMT;
        S000_CALL_TDZACDRU();
    }
    public void B200_CALL_CSH_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = TDCTZLPI.CCY;
        BPCUSBOX.CCY_TYP = TDCTZLPI.CCY_TYP;
        BPCUSBOX.AMT = TDCACDRU.TXN_AMT;
        BPCUSBOX.OPP_AC = TDCTZLPI.AC_NO;
        BPCUSBOX.ID_TYP = TDCTZLPI.ID_TYP;
        BPCUSBOX.IDNO = TDCTZLPI.ID_NO;
        S000_CALL_BPZUSBOX();
    }
    public void B210_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AMT = TDCACDRU.TXN_AMT;
        AICUUPIA.DATA.AC_NO = TDCTZLPI.OPP_AC;
        AICUUPIA.DATA.RVS_NO = TDCTZLPI.RVS_NO;
        AICUUPIA.DATA.CCY = TDCTZLPI.CCY;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTZLPI.OPP_AC);
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = TDCTZLPI.OPP_AC;
        DDCUCRAC.CCY = TDCTZLPI.CCY;
        DDCUCRAC.CCY_TYPE = TDCTZLPI.CCY_TYP;
        DDCUCRAC.TX_AMT = TDCACDRU.TXN_AMT;
        DDCUCRAC.OTHER_AC = TDCTZLPI.AC_NO;
        DDCUCRAC.OTHER_CCY = TDCTZLPI.CCY;
        if (TDCTZLPI.CT_FLG == '0') {
            DDCUCRAC.TX_TYPE = 'C';
        } else {
            DDCUCRAC.TX_TYPE = 'T';
        }
        DDCUCRAC.OTH_TX_TOOL = TDCTZLPI.AC_NO;
        DDCUCRAC.TX_MMO = "A004";
        DDCUCRAC.BANK_CR_FLG = 'N';
        S000_CALL_DDZUCRAC();
    }
    public void B240_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOTRAC);
        TDCOTRAC.TX_TYP = 'T';
        TDCOTRAC.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOTRAC.FR_AC = TDCTZLPI.AC_NO;
        TDCOTRAC.ENG_NM1 = " ";
        TDCOTRAC.CHN_NM1 = " ";
        TDCOTRAC.TO_AC = TDCTZLPI.OPP_AC;
        TDCOTRAC.ENG_NM2 = " ";
        TDCOTRAC.CHN_NM2 = TDCTZLPI.CNAME;
        TDCOTRAC.FR_CCY = TDCTZLPI.CCY;
        TDCOTRAC.FR_CCY_TYPE = TDCTZLPI.CCY_TYP;
        TDCOTRAC.TO_CCY = TDCTZLPI.CCY;
        TDCOTRAC.TO_CCY_TYPE = TDCTZLPI.CCY_TYP;
        TDCOTRAC.FR_AMT = TDCACDRU.TXN_AMT;
        TDCOTRAC.TO_AMT = TDCACDRU.TXN_AMT;
        TDCOTRAC.FR_PSBK = TDCTZLPI.BV_NO;
        TDCOTRAC.FR_CARD = TDCTZLPI.AC_NO;
        TDCOTRAC.TO_CARD = TDCTZLPI.OPP_AC;
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
        CICACCU.DATA.AGR_NO = TDCTZLPI.OPP_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
    }
    public void R000_CHECK_DD_CARD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = TDCTZLPI.OPP_AC;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "03";
        DCCPFTCK.VAL.TXN_CCY = TDCTZLPI.CCY;
        DCCPFTCK.VAL.TXN_AMT = TDCACDRU.TXN_AMT;
        DCCPFTCK.TRK2_DAT = TDCTZLPI.OTRK2_DT;
        DCCPFTCK.TRK3_DAT = TDCTZLPI.OTRK3_DT;
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
