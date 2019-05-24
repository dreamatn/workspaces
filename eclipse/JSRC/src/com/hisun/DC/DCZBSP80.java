package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZBSP80 {
    DBParm DCTIQR_RD;
    brParm TDTBVT_BR = new brParm();
    String WS_TD_AC = " ";
    String WS_BSPNAME = " ";
    String WS_ERR_MSG = " ";
    DCZBSP80_WS_DATE1 WS_DATE1 = new DCZBSP80_WS_DATE1();
    int WS_DATE2 = 0;
    int WS_DATE3 = 0;
    char WS_TBL_FARM_FLAG = ' ';
    char WS_ERR_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCACCRU TDCACCRU = new TDCACCRU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDRBVT TDRBVT = new TDRBVT();
    CICACCU CICACCU = new CICACCU();
    DCRIQR DCRIQR = new DCRIQR();
    TDCKHCR TDCKHCR = new TDCKHCR();
    SCCGWA SCCGWA;
    DCCBSP80 DCCBSP80;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    public void MP(SCCGWA SCCGWA, DCCBSP80 DCCBSP80) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCBSP80 = DCCBSP80;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZBSP80 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        if (DCCBSP80.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT);
        }
        if (DCCBSP80.PROD_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000002")) {
            IBS.init(SCCGWA, TDCKHCR);
            TDCKHCR.CT_FLG = '2';
            TDCKHCR.MPROD_CD = "9520000002";
            TDCKHCR.PROD_CD = DCCBSP80.TD_PROD_CODE;
            TDCKHCR.CCY = DCCBSP80.CCY;
            TDCKHCR.CCY_TYP = DCCBSP80.CCY_TYPE;
            TDCKHCR.BV_TYP = '0';
            TDCKHCR.AC = DCCBSP80.TRC_AC;
            CEP.TRC(SCCGWA, DCCBSP80.TR_TD_AMT);
            TDCKHCR.TXN_AMT = DCCBSP80.TR_TD_AMT;
            TDCKHCR.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            TDCKHCR.TERM = DCCBSP80.TERM;
            TDCKHCR.INSTR_MTH = '3';
            TDCKHCR.CROS_DR_FLG = '2';
            TDCKHCR.OPT = '4';
            TDCKHCR.MMO = "A002";
            TDCKHCR.OPP_AC = DCCBSP80.AC_NO;
            TDCKHCR.AUTO_CR = 'Y';
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DCCBSP80.TRC_AC;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
            CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            TDCKHCR.ID_TYP = CICACCU.DATA.ID_TYPE;
            TDCKHCR.ID_NO = CICACCU.DATA.ID_NO;
            TDCKHCR.CI_NO = CICACCU.DATA.CI_NO;
            TDCKHCR.NAME = CICACCU.DATA.AC_CNM;
            S000_CALL_TDZKHCR();
        } else {
            IBS.init(SCCGWA, TDCACCRU);
            TDCACCRU.OPT = '0';
            TDCACCRU.PRDMO_CD = "MMDP";
            TDCACCRU.PROD_CD = DCCBSP80.TD_PROD_CODE;
            TDCACCRU.TXN_MMO = "A005";
            if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000006")) {
                CEP.TRC(SCCGWA, "HZH");
                CEP.TRC(SCCGWA, DCCBSP80.TRC_AC);
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = DCCBSP80.TRC_AC;
                if (DCCBSP80.CCY.trim().length() == 0) {
                    DDCUCRAC.CCY = "156";
                } else {
                    DDCUCRAC.CCY = DCCBSP80.CCY;
                }
                if (DCCBSP80.CCY_TYPE == ' ') {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = DCCBSP80.CCY_TYPE;
                }
                DDCUCRAC.TX_AMT = DCCBSP80.TR_TD_AMT;
                DDCUCRAC.OTHER_AC = DCCBSP80.AC_NO;
                DDCUCRAC.TX_MMO = "A001";
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.SIGN_FLG = 'Y';
                S000_CALL_DDZUCRAC();
            } else {
                IBS.init(SCCGWA, CICACCU);
                if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000004")) {
                    CEP.TRC(SCCGWA, "KANEI");
                    CEP.TRC(SCCGWA, DCCBSP80.AC_NO);
                    TDCACCRU.BV_TYP = '4';
                    CICACCU.DATA.AGR_NO = DCCBSP80.AC_NO;
                }
                if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000005")) {
                    CEP.TRC(SCCGWA, "KAJIAN");
                    CEP.TRC(SCCGWA, DCCBSP80.TRC_AC);
                    TDCACCRU.BV_TYP = '4';
                    CICACCU.DATA.AGR_NO = DCCBSP80.TRC_AC;
                }
                if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000001")) {
                    CEP.TRC(SCCGWA, "CHENGZHANG");
                    CEP.TRC(SCCGWA, DCCBSP80.TRC_AC);
                    TDCACCRU.BV_TYP = '0';
                    CICACCU.DATA.AGR_NO = DCCBSP80.TRC_AC;
                }
                S000_CALL_CIZACCU();
                CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
                CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
                TDCACCRU.AC_NAME = CICACCU.DATA.AC_CNM;
                TDCACCRU.ID_TYP = CICACCU.DATA.ID_TYPE;
                TDCACCRU.ID_NO = CICACCU.DATA.ID_NO;
                TDCACCRU.CI_NO = CICACCU.DATA.CI_NO;
                TDCACCRU.AC_NO = DCCBSP80.TRC_AC;
                if (DCCBSP80.CCY.trim().length() == 0) {
                    TDCACCRU.CCY = "156";
                } else {
                    TDCACCRU.CCY = DCCBSP80.CCY;
                }
                if (DCCBSP80.CCY_TYPE == ' ') {
                    TDCACCRU.CCY_TYP = '1';
                } else {
                    TDCACCRU.CCY_TYP = DCCBSP80.CCY_TYPE;
                }
                TDCACCRU.TXN_AMT = DCCBSP80.TR_TD_AMT;
                TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
                TDCACCRU.TERM = DCCBSP80.TERM;
                if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000001")) {
                    TDCACCRU.INSTR_MTH = '1';
                    TDCACCRU.STL_AC = DCCBSP80.AC_NO;
                } else {
                    TDCACCRU.INSTR_MTH = '3';
                }
                TDCACCRU.CROS_DR_FLG = '2';
                TDCACCRU.OPP_AC_CNO = DCCBSP80.AC_NO;
                TDCACCRU.CT_FLG = '2';
                S000_CALL_TDZACCRU();
            }
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = DCCBSP80.AC_NO;
            DDCUDRAC.OTHER_AC = DCCBSP80.TRC_AC;
            if (DCCBSP80.CCY.trim().length() == 0) {
                DDCUDRAC.CCY = "156";
            } else {
                DDCUDRAC.CCY = DCCBSP80.CCY;
            }
            if (DCCBSP80.CCY_TYPE == ' ') {
                DDCUDRAC.CCY_TYPE = '1';
            } else {
                DDCUDRAC.CCY_TYPE = DCCBSP80.CCY_TYPE;
            }
            DDCUDRAC.TX_AMT = DCCBSP80.TR_TD_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.BANK_DR_FLG = 'Y';
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.SIGN_FLG = 'Y';
            if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000005")) {
                DDCUDRAC.OTH_TX_TOOL = DCCBSP80.TRC_AC;
            }
            if (DCCBSP80.PROD_CODE.equalsIgnoreCase("9510000006")) {
                DDCUDRAC.TX_MMO = "A019";
            } else {
                DDCUDRAC.TX_MMO = "A005";
            }
            DDCUDRAC.AUTO_DDTOTD_FLG = 'Y';
            S000_CALL_DDZUDRAC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, "CIF");
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR);
    }
    public void T000_READ_DCTIQR_UPD() throws IOException,SQLException,Exception {
        DCTIQR_RD = new DBParm();
        DCTIQR_RD.TableName = "DCTIQR";
        DCTIQR_RD.upd = true;
        IBS.READ(SCCGWA, DCRIQR, DCTIQR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIQR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.where = "AC = :WS_TD_AC";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
    }
    public void T000_READNEXT_TDTBVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
    }
    public void T000_ENDBR_TDTBVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
    }
    public void T000_REWRITE_DCTIQR() throws IOException,SQLException,Exception {
        DCTIQR_RD = new DBParm();
        DCTIQR_RD.TableName = "DCTIQR";
        IBS.REWRITE(SCCGWA, DCRIQR, DCTIQR_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
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
