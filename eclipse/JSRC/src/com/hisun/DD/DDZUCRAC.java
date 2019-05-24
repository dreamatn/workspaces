package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCRAC {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTGPMST_RD;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_TEMP_STD_AC = " ";
    char WS_GROUP_AC_FLG = 'N';
    char WS_GPMST_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDRMST DDRMST = new DDRMST();
    DDRGPMST DDRGPMST = new DDRGPMST();
    DDCUCRDD DDCUCRDD = new DDCUCRDD();
    DDCUGPCR DDCUGPCR = new DDCUGPCR();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACRI CICQACRI = new CICQACRI();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DDCUCRAC DDCUCRAC;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCUCRAC DDCUCRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCRAC = DDCUCRAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUCRAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "======== INPUT DATA ========");
        CEP.TRC(SCCGWA, DDCUCRAC.AC);
        CEP.TRC(SCCGWA, DDCUCRAC.CCY);
        CEP.TRC(SCCGWA, DDCUCRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUCRAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUCRAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
        CEP.TRC(SCCGWA, DDCUCRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AMT);
        CEP.TRC(SCCGWA, DDCUCRAC.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
        CEP.TRC(SCCGWA, DDCUCRAC.TX_TYPE);
        CEP.TRC(SCCGWA, DDCUCRAC.TX_REF);
        CEP.TRC(SCCGWA, DDCUCRAC.REMARKS);
        CEP.TRC(SCCGWA, DDCUCRAC.BANK_CR_FLG);
        CEP.TRC(SCCGWA, DDCUCRAC.AC_NAME);
        CEP.TRC(SCCGWA, DDCUCRAC.BV_TYP);
        CEP.TRC(SCCGWA, DDCUCRAC.BV_VTYP);
        CEP.TRC(SCCGWA, DDCUCRAC.BV_VNO);
        CEP.TRC(SCCGWA, DDCUCRAC.GD_WITHDR_FLG);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_BR);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_BK_NM);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC_NM);
        CEP.TRC(SCCGWA, DDCUCRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCUCRAC.ENG_NAME);
        CEP.TRC(SCCGWA, DDCUCRAC.CHI_NAME);
        B010_CHECK_INPUT_DATA();
        B020_CHECK_CUS_AC_PROC();
        B030_CR_AC_PROCESS();
        B040_GROUP_AC_PROCESS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUCRAC.AC.trim().length() == 0 
            && DDCUCRAC.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUCRAC.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUCRAC.CCY.equalsIgnoreCase("156") 
            && DDCUCRAC.AID.trim().length() == 0 
            && DDCUCRAC.CCY_TYPE == ' ') {
            DDCUCRAC.CCY_TYPE = '1';
        }
        if (DDCUCRAC.CCY_TYPE == ' ' 
            && DDCUCRAC.AID.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
        if (DDCUCRAC.TX_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCUCRAC.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDCUCRAC.BANK_CR_FLG == ' ') {
            DDCUCRAC.BANK_CR_FLG = 'N';
        } else {
            if ((DDCUCRAC.BANK_CR_FLG != 'Y') 
                && (DDCUCRAC.BANK_CR_FLG != 'N')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_CR_FLG_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHECK_CUS_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CEP.TRC(SCCGWA, DDCUCRAC.AC);
        CEP.TRC(SCCGWA, DDCUCRAC.CARD_NO);
        if (DDCUCRAC.AC.trim().length() > 0) {
            CICQACRI.DATA.AGR_NO = DDCUCRAC.AC;
        } else {
            CICQACRI.DATA.AGR_NO = DDCUCRAC.CARD_NO;
        }
        S000_CALL_CIZQACRI();
    }
    public void B030_CR_AC_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(49 - 1, 49 + 1 - 1).equalsIgnoreCase("1") 
                && !DDCUCRAC.TX_MMO.equalsIgnoreCase("X9E")) {
                IBS.init(SCCGWA, DDRGPMST);
                DDRGPMST.KEY.AC_NO = DDCUCRAC.AC;
                DDRGPMST.KEY.AC_NO = WS_TEMP_STD_AC;
                DDRGPMST.KEY.CCY = DDCUCRAC.CCY;
                T000_READ_DDTGPMST();
                if (DDRGPMST.POOLING_TYP == '1') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_POOLING_NOT_RET;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, DDCUCRDD);
        DDCUCRDD.AC = DDCUCRAC.AC;
        DDCUCRDD.CCY = DDCUCRAC.CCY;
        DDCUCRDD.CCY_TYPE = DDCUCRAC.CCY_TYPE;
        DDCUCRDD.TX_AMT = DDCUCRAC.TX_AMT;
        DDCUCRDD.CARD_NO = DDCUCRAC.CARD_NO;
        DDCUCRDD.PSBK_NO = DDCUCRAC.PSBK_NO;
        DDCUCRDD.VAL_DATE = DDCUCRAC.VAL_DATE;
        DDCUCRDD.OTHER_AC = DDCUCRAC.OTHER_AC;
        DDCUCRDD.OTHER_CCY = DDCUCRAC.OTHER_CCY;
        DDCUCRDD.OTHER_AMT = DDCUCRAC.OTHER_AMT;
        DDCUCRDD.NARRATIVE = DDCUCRAC.NARRATIVE;
        DDCUCRDD.TX_MMO = DDCUCRAC.TX_MMO;
        DDCUCRDD.TX_TYPE = DDCUCRAC.TX_TYPE;
        DDCUCRDD.TX_REF = DDCUCRAC.TX_REF;
        DDCUCRDD.REMARKS = DDCUCRAC.REMARKS;
        DDCUCRDD.BANK_CR_FLG = DDCUCRAC.BANK_CR_FLG;
        DDCUCRDD.AC_NAME = DDCUCRAC.AC_NAME;
        DDCUCRDD.BV_TYP = DDCUCRAC.BV_TYP;
        DDCUCRDD.BV_VTYP = DDCUCRAC.BV_VTYP;
        DDCUCRDD.BV_VNO = DDCUCRAC.BV_VNO;
        DDCUCRDD.GD_WITHDR_FLG = DDCUCRAC.GD_WITHDR_FLG;
        DDCUCRDD.OTHER_BR = DDCUCRAC.OTHER_BR;
        DDCUCRDD.OTHER_BK_NM = DDCUCRAC.OTHER_BK_NM;
        DDCUCRDD.OTHER_AC_NM = DDCUCRAC.OTHER_AC_NM;
        DDCUCRDD.RLT_AC = DDCUCRAC.RLT_AC;
        DDCUCRDD.ENG_NAME = DDCUCRAC.ENG_NAME;
        DDCUCRDD.CHI_NAME = DDCUCRAC.CHI_NAME;
        DDCUCRDD.OTH_TX_TOOL = DDCUCRAC.OTH_TX_TOOL;
        DDCUCRDD.RLT_AC_NAME = DDCUCRAC.RLT_AC_NAME;
        DDCUCRDD.RLT_BANK = DDCUCRAC.RLT_BANK;
        DDCUCRDD.RLT_BK_NM = DDCUCRAC.RLT_BK_NM;
        DDCUCRDD.RLT_REF_NO = DDCUCRAC.RLT_REF_NO;
        DDCUCRDD.RLT_CCY = DDCUCRAC.RLT_CCY;
        DDCUCRDD.RLT_CI_TYP = DDCUCRAC.RLT_CI_TYP;
        CEP.TRC(SCCGWA, DDCUCRDD.RLT_CI_TYP);
        CEP.TRC(SCCGWA, DDCUCRDD.RLT_BANK);
        CEP.TRC(SCCGWA, DDCUCRDD.RLT_BK_NM);
        CEP.TRC(SCCGWA, DDCUCRDD.RLT_AC_NAME);
        CEP.TRC(SCCGWA, DDCUCRDD.RLT_AC);
        DDCUCRDD.PSBK_SEQ = DDCUCRAC.PSBK_SEQ;
        DDCUCRDD.AUTO_TDTODD_FLG = DDCUCRAC.AUTO_TDTODD_FLG;
        DDCUCRDD.LAW_DUCT_FLG = DDCUCRAC.LAW_DUCT_FLG;
        CEP.TRC(SCCGWA, DDCUCRAC.TD_INT_AMT);
        DDCUCRDD.TD_INT_AMT = DDCUCRAC.TD_INT_AMT;
        DDCUCRDD.AID = DDCUCRAC.AID;
        CEP.TRC(SCCGWA, DDCUCRAC.AID);
        DDCUCRDD.DR_ONL_FLG = DDCUCRAC.DR_ONL_FLG;
        DDCUCRDD.SUPPLY_FLG = DDCUCRAC.SUPPLY_FLG;
        DDCUCRDD.SIGN_FLG = DDCUCRAC.SIGN_FLG;
        DDCUCRDD.HIS_SHOW_FLG = DDCUCRAC.HIS_SHOW_FLG;
        DDCUCRDD.CHK_LMT_FLG = DDCUCRAC.CHK_LMT_FLG;
        DDCUCRDD.SMS_FLG = DDCUCRAC.SMS_FLG;
        DDCUCRDD.EA_CHK_FLG = DDCUCRAC.EA_CHK_FLG;
        DDCUCRDD.TRT_CTLW = DDCUCRAC.TRT_CTLW;
        DDCUCRDD.TXN_REGION = DDCUCRAC.TXN_REGION;
        S000_CALL_DDZUCRDD();
        CEP.TRC(SCCGWA, DDCUCRDD.TX_MMO);
        DDCUCRAC.TX_MMO = DDCUCRDD.TX_MMO;
        DDCUCRAC.AC = DDCUCRDD.AC;
    }
    public void B040_GROUP_AC_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
        if (WS_GROUP_AC_FLG == 'Y' 
            && !DDCUCRAC.TX_MMO.equalsIgnoreCase("GTU") 
            && !DDCUCRAC.TX_MMO.equalsIgnoreCase("GTV") 
            && !DDCUCRAC.TX_MMO.equalsIgnoreCase("GTX") 
            && !DDCUCRAC.TX_MMO.equalsIgnoreCase("X9E")) {
            CEP.TRC(SCCGWA, "*** GO INTO GROUP ACCOUNT PROCESS ***");
            IBS.init(SCCGWA, DDCUGPCR);
            DDCUGPCR.AC = WS_TEMP_STD_AC;
            DDCUGPCR.CCY = DDCUCRAC.CCY;
            DDCUGPCR.CCY_TYPE = DDCUCRAC.CCY_TYPE;
            DDCUGPCR.RT_TX_AMT = DDCUCRAC.TX_AMT;
            DDCUGPCR.TX_MMO = DDCUCRAC.TX_MMO;
            DDCUGPCR.TX_REF = DDCUCRAC.TX_REF;
            DDCUGPCR.NARRATIVE = DDCUCRAC.NARRATIVE;
            DDCUGPCR.REMARKS = DDCUCRAC.REMARKS;
            CEP.TRC(SCCGWA, DDCUGPCR.AC);
            CEP.TRC(SCCGWA, DDCUGPCR.CCY);
            CEP.TRC(SCCGWA, DDCUGPCR.CCY_TYPE);
            S000_CALL_DDZUGPCR();
            if (DDCUGPCR.REAL_TIME_CR == 'N') {
                CEP.TRC(SCCGWA, "<< NO NEED REAL TIME COLLECTED >>");
            } else {
                CEP.TRC(SCCGWA, "<< REAL TIME COLLECTED SUCCESSFUL! >>");
            }
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "AC = :DDRCCY.KEY.AC "
            + "AND CCY = :DDRCCY.CCY";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void T000_READ_DDTGPMST() throws IOException,SQLException,Exception {
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        IBS.READ(SCCGWA, DDRGPMST, DDTGPMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GPMST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GPMST_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC-DD", DDCUCRDD);
    }
    public void S000_CALL_DDZUGPCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-GRP-COLLECT", DDCUGPCR);
        if (DDCUGPCR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGPCR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
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
