package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZUFINC {
    DBParm CMTFINCH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    String WS_TM_X = " ";
    short WS_TM_2 = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_TS = " ";
    double WS_CUR_BAL = 0;
    char WS_CMTFINCH_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CMRFINCH CMRFINCH = new CMRFINCH();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICCUST CICCUST = new CICCUST();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBINF SCCBINF;
    CMCUFINC CMCUFINC;
    public void MP(SCCGWA SCCGWA, CMCUFINC CMCUFINC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCUFINC = CMCUFINC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZUFINC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMRFINCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        B200_ADD_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCUFINC.JRN_SEQ);
        CEP.TRC(SCCGWA, CMCUFINC.AC_NO);
        CEP.TRC(SCCGWA, CMCUFINC.AC_CCY);
        CEP.TRC(SCCGWA, CMCUFINC.CCY_TYPE);
        CEP.TRC(SCCGWA, CMCUFINC.DC_FLG);
        CEP.TRC(SCCGWA, CMCUFINC.CT_FLG);
        CEP.TRC(SCCGWA, CMCUFINC.TR_AMT);
        CEP.TRC(SCCGWA, CMCUFINC.TR_CCY);
        if (CMCUFINC.JRN_SEQ == ' ' 
            || CMCUFINC.JRN_SEQ == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_SEQ_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCUFINC.AC_NO.trim().length() == 0 
            || CMCUFINC.AC_NO.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NO_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCUFINC.AC_CCY.trim().length() == 0 
            || CMCUFINC.AC_CCY.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_CCY_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((!CMCUFINC.AC_CCY.equalsIgnoreCase("156")) 
            && (CMCUFINC.CCY_TYPE == ' ' 
            || CMCUFINC.CCY_TYPE == 0X00)) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYPE_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCUFINC.DC_FLG == ' ' 
            || CMCUFINC.DC_FLG == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCUFINC.CT_FLG == ' ' 
            || CMCUFINC.CT_FLG == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_CT_FLG_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCUFINC.TR_CCY.trim().length() == 0 
            || CMCUFINC.TR_CCY.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_TR_CCY_MUST_IN, CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_ADD_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCUFINC.AC_NO;
        CMCSIQAC.I_CCY = CMCUFINC.AC_CCY;
        CMCSIQAC.I_CCY_TYP = CMCUFINC.CCY_TYPE;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            WS_CUR_BAL = CMCSIQAC.OUT_INF.DC_INF.DC_CUR_BAL;
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            WS_CUR_BAL = CMCSIQAC.OUT_INF.DD_INF.DD_CUR_BAL;
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("TD")) {
            WS_CUR_BAL = CMCSIQAC.OUT_INF.TD_INF.TD_CUR_BAL;
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("AI")) {
            WS_CUR_BAL = CMCSIQAC.OUT_INF.BAS_INF.BAS_CUR_BAL;
        }
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_ADDR);
        CEP.TRC(SCCGWA, BPCPQORG.LINK_TEL);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        IBS.init(SCCGWA, CMRFINCH);
        CEP.TRC(SCCGWA, CMCUFINC.AC_DATE);
        if (CMCUFINC.AC_DATE == 0) {
            CMRFINCH.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CMRFINCH.KEY.AC_DATE = CMCUFINC.AC_DATE;
        }
        CEP.TRC(SCCGWA, CMCUFINC.JRN_NO);
        if (CMCUFINC.JRN_NO == 0) {
            CMRFINCH.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        } else {
            CMRFINCH.KEY.JRN_NO = CMCUFINC.JRN_NO;
        }
        CMRFINCH.KEY.JRN_SEQ = CMCUFINC.JRN_SEQ;
        CMRFINCH.REQ_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        CMRFINCH.STS = '0';
        CMRFINCH.CARD_NO = CMCUFINC.CARD_NO;
        CMRFINCH.AC_NO = CMCUFINC.AC_NO;
        CMRFINCH.AC_NAME = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        CMRFINCH.AC_CCY = CMCUFINC.AC_CCY;
        CMRFINCH.ID_TYP = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP;
        CMRFINCH.ID_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
        CMRFINCH.BV_CD = CMCUFINC.BV_CD;
        CMRFINCH.BV_NO = CMCUFINC.BV_NO;
        CMRFINCH.DC_FLG = CMCUFINC.DC_FLG;
        CMRFINCH.CT_FLG = CMCUFINC.CT_FLG;
        CMRFINCH.TR_CCY = CMCUFINC.TR_CCY;
        CMRFINCH.TR_AMT = CMCUFINC.TR_AMT;
        CEP.TRC(SCCGWA, WS_CUR_BAL);
        CEP.TRC(SCCGWA, CMCUFINC.AVL_BAL);
        if (CMCUFINC.AVL_BAL != 0) {
            CMRFINCH.AVL_BAL = CMCUFINC.AVL_BAL;
        } else {
            CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_ACO_NO);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_ACO_NO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                CMRFINCH.AVL_BAL = CMCUFINC.AVL_BAL;
            } else {
                CMRFINCH.AVL_BAL = WS_CUR_BAL;
            }
        }
        CEP.TRC(SCCGWA, CMCUFINC.RLT_AC);
        if (CMCUFINC.RLT_AC.trim().length() == 0) {
            CMRFINCH.OPP_AC = CMCUFINC.OPP_AC;
        } else {
            CMRFINCH.OPP_AC = CMCUFINC.RLT_AC;
        }
        CEP.TRC(SCCGWA, CMCUFINC.RLT_CARD_NO);
        if (CMCUFINC.RLT_CARD_NO.trim().length() == 0) {
            CMRFINCH.OPP_CARD_NO = CMCUFINC.OPP_CARD_NO;
        } else {
            CMRFINCH.OPP_CARD_NO = CMCUFINC.RLT_CARD_NO;
        }
        CEP.TRC(SCCGWA, CMCUFINC.RLT_NAME);
        if (CMCUFINC.RLT_NAME.trim().length() == 0) {
            CMRFINCH.OPP_NAME = CMCUFINC.OPP_NAME;
        } else {
            CMRFINCH.OPP_NAME = CMCUFINC.RLT_NAME;
        }
        CMRFINCH.TR_BR = BPCPQORG.BR;
        CMRFINCH.TR_SITE = BPCPQORG.CHN_ADDR;
        CMRFINCH.TR_BR_SITE = BPCPQORG.CHN_ADDR;
        CMRFINCH.TR_BR_TEL = BPCPQORG.LINK_TEL;
        CMRFINCH.TR_BR_NAME = BPCPQORG.CHN_NM;
        CMRFINCH.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRFINCH.AUH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CMRFINCH.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CMRFINCH.REQ_CHNL_JRN = GWA_SC_AREA.REQ_CHNL_JRN;
        CMRFINCH.CANCEL_FLG = SCCGWA.COMM_AREA.CANCEL_IND;
        CMRFINCH.TX_MMO = CMCUFINC.TX_MMO;
        CMRFINCH.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, CMRFINCH.KEY.AC_DATE);
        CEP.TRC(SCCGWA, CMRFINCH.KEY.JRN_NO);
        CEP.TRC(SCCGWA, CMRFINCH.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, CMRFINCH.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMRFINCH.STS);
        CEP.TRC(SCCGWA, CMRFINCH.CARD_NO);
        CEP.TRC(SCCGWA, CMRFINCH.AC_NO);
        CEP.TRC(SCCGWA, CMRFINCH.AC_NAME);
        CEP.TRC(SCCGWA, CMRFINCH.AC_CCY);
        CEP.TRC(SCCGWA, CMRFINCH.ID_TYP);
        CEP.TRC(SCCGWA, CMRFINCH.ID_NO);
        CEP.TRC(SCCGWA, CMRFINCH.BV_CD);
        CEP.TRC(SCCGWA, CMRFINCH.BV_NO);
        CEP.TRC(SCCGWA, CMRFINCH.DC_FLG);
        CEP.TRC(SCCGWA, CMRFINCH.CT_FLG);
        CEP.TRC(SCCGWA, CMRFINCH.TR_CCY);
        CEP.TRC(SCCGWA, CMRFINCH.TR_AMT);
        CEP.TRC(SCCGWA, CMRFINCH.AVL_BAL);
        CEP.TRC(SCCGWA, CMRFINCH.OPP_AC);
        CEP.TRC(SCCGWA, CMRFINCH.OPP_CARD_NO);
        CEP.TRC(SCCGWA, CMRFINCH.OPP_NAME);
        CEP.TRC(SCCGWA, CMRFINCH.TR_BR);
        CEP.TRC(SCCGWA, CMRFINCH.TR_SITE);
        CEP.TRC(SCCGWA, CMRFINCH.TR_BR_SITE);
        CEP.TRC(SCCGWA, CMRFINCH.TR_BR_TEL);
        CEP.TRC(SCCGWA, CMRFINCH.TR_BR_NAME);
        CEP.TRC(SCCGWA, CMRFINCH.TR_TLR);
        CEP.TRC(SCCGWA, CMRFINCH.AUH_TLR);
        CEP.TRC(SCCGWA, CMRFINCH.TR_CODE);
        T000_WRITE_CMTFINCH();
        if (pgmRtn) return;
    }
    public void T000_WRITE_CMTFINCH() throws IOException,SQLException,Exception {
        CMTFINCH_RD = new DBParm();
        CMTFINCH_RD.TableName = "CMTFINCH";
        IBS.WRITE(SCCGWA, CMRFINCH, CMTFINCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCUFINC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC, true);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
