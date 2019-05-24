package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZTXYPI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    String K_AP_MMO = "TD";
    String WS_MSGID = " ";
    int WS_OPN_BR = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICQACRI CICQACRI = new CICQACRI();
    TDRSMST TDRSMST = new TDRSMST();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    TDCTXYPI TDCTXYPI;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCTXYPI TDCTXYPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCTXYPI = TDCTXYPI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZTXYPI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        if (TDCTXYPI.BV_TYP != ' ' 
            && TDCTXYPI.BV_TYP != '4') {
            IBS.init(SCCGWA, BPCPRMM);
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = "TDP";
            BPRPRMT.KEY.CD = "" + TDCTXYPI.BV_TYP;
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCBVCD);
            TDCTXYPI.BV_CD = TDCBVCD.CD;
        }
        CEP.TRC(SCCGWA, TDCTXYPI.BV_CD);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTXYPI.AC);
        CEP.TRC(SCCGWA, TDCTXYPI.CT_FLG);
        B100_CALL_TD_DR_UNT();
        CICQACRI.DATA.AGR_NO = TDCTXYPI.AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (TDCTXYPI.NAME.trim().length() == 0) {
            TDCTXYPI.NAME = CICQACRI.O_DATA.O_AC_CNM;
        }
        WS_OPN_BR = CICQACRI.O_DATA.O_OPN_BR;
        if (TDCTXYPI.CT_FLG == '0') {
            B200_CALL_CSH_CR_UNT();
        } else if (TDCTXYPI.CT_FLG == '1') {
            B210_CALL_AI_CR_UNT();
        } else if (TDCTXYPI.CT_FLG == '2'
            || TDCTXYPI.CT_FLG == '3'
            || TDCTXYPI.CT_FLG == '4') {
            if (TDCTXYPI.INOUT == '2') {
            } else {
                B230_CALL_DD_CR_UNT();
            }
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG, 19);
        }
    }
    public void B100_CALL_TD_DR_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTXYPI.DRAW_MTH);
        IBS.init(SCCGWA, TDCACDRU);
        IBS.init(SCCGWA, TDRSMST);
        TDCACDRU.OPT = '3';
        TDCACDRU.PRDMO_CD = "MMDP";
        TDCACDRU.BV_CD = TDCTXYPI.BV_CD;
        TDCACDRU.BV_TYP = TDCTXYPI.BV_TYP;
        TDCACDRU.BV_NO = TDCTXYPI.BV_NO;
        if (TDCTXYPI.BV_TYP == '4') {
            TDCACDRU.MAC_CNO = TDCTXYPI.CARD_NO;
            TDCACDRU.CVV = TDCTXYPI.CVV;
        }
        TDCACDRU.AC_SEQ = TDCTXYPI.AC_SEQ;
        TDCACDRU.MAC_CNO = TDCTXYPI.AC;
        TDCACDRU.NAME = TDCTXYPI.NAME;
        TDCACDRU.ADDR = TDCTXYPI.ADDR;
        TDCACDRU.CCY = TDCTXYPI.CCY;
        TDCACDRU.CCY_TYP = TDCTXYPI.CCY_TYP;
        TDCACDRU.DRAW_MTH = TDCTXYPI.DRAW_MTH;
        if (TDCTXYPI.DRAW_MTH == '1') {
            TDCACDRU.DRAW_INF = TDCTXYPI.PSW;
        } else if (TDCTXYPI.DRAW_MTH == '2'
            || TDCTXYPI.DRAW_MTH == '3') {
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTXYPI.ID_TYP == null) TDCTXYPI.ID_TYP = "";
            JIBS_tmp_int = TDCTXYPI.ID_TYP.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) TDCTXYPI.ID_TYP += " ";
            TDCACDRU.DRAW_INF = TDCTXYPI.ID_TYP + TDCACDRU.DRAW_INF.substring(5);
            if (TDCACDRU.DRAW_INF == null) TDCACDRU.DRAW_INF = "";
            JIBS_tmp_int = TDCACDRU.DRAW_INF.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) TDCACDRU.DRAW_INF += " ";
            if (TDCTXYPI.ID_NO == null) TDCTXYPI.ID_NO = "";
            JIBS_tmp_int = TDCTXYPI.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) TDCTXYPI.ID_NO += " ";
            TDCACDRU.DRAW_INF = TDCACDRU.DRAW_INF.substring(0, 6 - 1) + TDCTXYPI.ID_NO + TDCACDRU.DRAW_INF.substring(6 + 25 - 1);
        } else if (TDCTXYPI.DRAW_MTH == '5') {
            TDCACDRU.DRAW_INF = " ";
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH, 15);
        }
        TDCACDRU.ID_TYP = TDCTXYPI.ID_TYP;
        TDCACDRU.ID_NO = TDCTXYPI.ID_NO;
        TDCACDRU.PRT_FLG = TDCTXYPI.PRT_FLG;
        TDCACDRU.CT_FLG = TDCTXYPI.CT_FLG;
        if (TDCTXYPI.OPP_CARD_NO.trim().length() > 0) {
            TDCACDRU.OPP_AC_CNO = TDCTXYPI.OPP_CARD_NO;
        } else {
            TDCACDRU.OPP_AC_CNO = TDCTXYPI.OPP_AC;
        }
        TDCACDRU.TXN_CHNL = TDCTXYPI.TXN_CHNL;
        TDCACDRU.TXN_PNT = TDCTXYPI.TXN_PNT;
        TDCACDRU.INOUT = TDCTXYPI.INOUT;
        TDCTXYPI.INT_AC = TDCACDRU.OPP_AC_CNO;
        TDCACDRU.INT_STL_AC = TDCTXYPI.INT_AC;
        TDCACDRU.INT_REMMIT_BK = TDCTXYPI.OTH_BK;
        TDCACDRU.INT_REMMIT_NM = TDCTXYPI.OTH_NM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCACDRU.PAYING_INT = TDCTXYPI.PAYINT_INT;
        }
        S000_CALL_TDZACDRU();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            TDCTXYPI.PAYINT_INT = TDCACDRU.PAYING_INT;
        }
    }
    public void B200_CALL_CSH_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = TDCTXYPI.CCY;
        BPCUSBOX.CCY_TYP = TDCTXYPI.CCY_TYP;
        BPCUSBOX.AMT = TDCACDRU.PAYING_INT;
        BPCUSBOX.OPP_AC = TDCTXYPI.AC;
        BPCUSBOX.OPP_ACNM = TDCTXYPI.NAME;
        BPCUSBOX.ID_TYP = TDCTXYPI.ID_TYP;
        BPCUSBOX.IDNO = TDCTXYPI.ID_NO;
        BPCUSBOX.CASH_NO = "" + 0;
        JIBS_tmp_int = BPCUSBOX.CASH_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) BPCUSBOX.CASH_NO = "0" + BPCUSBOX.CASH_NO;
        S000_CALL_BPZUSBOX();
    }
    public void B210_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = TDCTXYPI.OPP_AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.RVS_NO = TDCTXYPI.CREV_NO;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
        AICUUPIA.DATA.CCY = TDCTXYPI.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        if (AICUUPIA.DATA.DESC == null) AICUUPIA.DATA.DESC = "";
        JIBS_tmp_int = AICUUPIA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) AICUUPIA.DATA.DESC += " ";
        if (TDCTXYPI.AC == null) TDCTXYPI.AC = "";
        JIBS_tmp_int = TDCTXYPI.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCTXYPI.AC += " ";
        AICUUPIA.DATA.DESC = TDCTXYPI.AC + AICUUPIA.DATA.DESC.substring(32);
        if (AICUUPIA.DATA.DESC == null) AICUUPIA.DATA.DESC = "";
        JIBS_tmp_int = AICUUPIA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) AICUUPIA.DATA.DESC += " ";
        JIBS_tmp_str[0] = "" + TDCTXYPI.AC_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        AICUUPIA.DATA.DESC = AICUUPIA.DATA.DESC.substring(0, 33 - 1) + JIBS_tmp_str[0] + AICUUPIA.DATA.DESC.substring(33 + 6 - 1);
        AICUUPIA.DATA.PAY_MAN = TDCTXYPI.NAME;
        CEP.TRC(SCCGWA, TDCTXYPI.NAME);
        AICUUPIA.DATA.THEIR_AC = TDCTXYPI.AC;
        AICUUPIA.DATA.PAY_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AICUUPIA();
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        IBS.init(SCCGWA, TDRSMST);
        DDCUCRAC.CARD_NO = TDCTXYPI.OPP_CARD_NO;
        DDCUCRAC.AC = TDCTXYPI.OPP_AC;
        DDCUCRAC.PSBK_NO = TDCTXYPI.OPP_BV_NO;
        DDCUCRAC.CCY = TDCTXYPI.CCY;
        DDCUCRAC.CCY_TYPE = TDCTXYPI.CCY_TYP;
        DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
        DDCUCRAC.OTHER_AC = TDCTXYPI.AC;
        DDCUCRAC.RLT_AC = TDCTXYPI.AC;
        DDCUCRAC.RLT_AC_NAME = TDCTXYPI.NAME;
        DDCUCRAC.RLT_BANK = "" + WS_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.RLT_BANK = "0" + DDCUCRAC.RLT_BANK;
        if (TDCTXYPI.CT_FLG == '1' 
            && TDCTXYPI.OPP_AC.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = TDCTXYPI.OPP_AC;
            S000_CALL_AIZPQMIB();
            DDCUCRAC.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
            DDCUCRAC.RLT_BANK = "" + AICPQMIB.INPUT_DATA.BR;
            JIBS_tmp_int = DDCUCRAC.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.RLT_BANK = "0" + DDCUCRAC.RLT_BANK;
            DDCUCRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
            DDCUCRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        if (TDCTXYPI.CT_FLG == '2' 
            && TDCTXYPI.OPP_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCTXYPI.AC;
            S000_CALL_CIZACCU();
        }
        DDCUCRAC.OTHER_CCY = TDCTXYPI.CCY;
        if (TDCTXYPI.CT_FLG == '0') {
            DDCUCRAC.TX_TYPE = 'C';
        } else {
            DDCUCRAC.TX_TYPE = 'T';
        }
        DDCUCRAC.TX_MMO = "S105";
        DDCUCRAC.BANK_CR_FLG = 'N';
        if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
        JIBS_tmp_int = DDCUCRAC.REMARKS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
        if (TDCTXYPI.AC == null) TDCTXYPI.AC = "";
        JIBS_tmp_int = TDCTXYPI.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCTXYPI.AC += " ";
        DDCUCRAC.REMARKS = TDCTXYPI.AC + DDCUCRAC.REMARKS.substring(32);
        if (DDCUCRAC.REMARKS == null) DDCUCRAC.REMARKS = "";
        JIBS_tmp_int = DDCUCRAC.REMARKS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) DDCUCRAC.REMARKS += " ";
        JIBS_tmp_str[0] = "" + TDCTXYPI.AC_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DDCUCRAC.REMARKS = DDCUCRAC.REMARKS.substring(0, 33 - 1) + JIBS_tmp_str[0] + DDCUCRAC.REMARKS.substring(33 + 6 - 1);
        S000_CALL_DDZUCRAC();
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
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
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_AICUUPIA() throws IOException,SQLException,Exception {
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
