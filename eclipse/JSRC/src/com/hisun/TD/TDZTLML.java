package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZTLML {
    DBParm TDTOTHE_RD;
    DBParm TDTTLMT_RD;
    String JIBS_tmp_str[] = new String[10];
    String WS_MSGID = " ";
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    short WS_ST = 0;
    short WS_EN = 0;
    char WS_TML_FU = ' ';
    String WS_O_PROD_TYP = " ";
    short WS_FR = 0;
    short WS_DT = 0;
    String WS_CCY = " ";
    double WS_TOT_BAL = 0;
    char TDZTLML_FILLER6 = 0X01;
    double WS_AGN_BAL = 0;
    char TDZTLML_FILLER8 = 0X01;
    double WS_AGN_BAL2 = 0;
    String K_OUTPUT_FMT1 = "TD577";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMACE TDCMACE = new TDCMACE();
    TDRCIIN TDRCIIN = new TDRCIIN();
    TDROTHE TDROTHE = new TDROTHE();
    TDRLMT TDRLMT = new TDRLMT();
    TDCPIOD TDCPIOD = new TDCPIOD();
    TDRTLMT TDRTLMT = new TDRTLMT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    TDCTLML TDCTLML;
    public void MP(SCCGWA SCCGWA, TDCTLML TDCTLML) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCTLML = TDCTLML;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZTLML return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRTLMT);
        if (TDCTLML.FUNC == 'I') {
            B020_I_FUNC();
            B120_I_OUT_MSG();
        } else if (TDCTLML.FUNC == 'A') {
            B010_A_CHECK_INPUT();
            T000_ADD_TDTTLMT();
            B120_I_OUT_MSG();
        } else if (TDCTLML.FUNC == 'M') {
            B030_M_FUNC();
            B120_I_OUT_MSG();
        } else if (TDCTLML.FUNC == 'D') {
            T000_DELETE_TDTLMT();
        } else if (TDCTLML.FUNC == 'S') {
            B030_S_FUNC();
            B120_O_OUT_MSG();
        } else if (TDCTLML.FUNC == 'O') {
            B030_O_FUNC();
            B120_O_OUT_MSG();
    }
    public void B020_I_FUNC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        T000_READ_TDTTLMT();
    }
    public void B120_I_OUT_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PROD_TYP);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PERS);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.YEAR);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.CCY);
        if (TDCTLML.FUNC == 'I') {
            WS_O_PROD_TYP = TDRTLMT.KEY.PROD_TYP;
            WS_FR = TDRTLMT.KEY.PERS;
            WS_DT = TDRTLMT.KEY.YEAR;
            WS_CCY = TDRTLMT.KEY.CCY;
            WS_TOT_BAL = TDRTLMT.TOT_BAL;
            WS_AGN_BAL = TDRTLMT.AGN_BAL;
        } else {
            WS_O_PROD_TYP = TDCTLML.PROD_TYP;
            WS_FR = TDCTLML.FR;
            WS_DT = TDCTLML.DT;
            WS_CCY = TDCTLML.CCY;
            WS_TOT_BAL = TDCTLML.TOT_BAL;
            WS_AGN_BAL = TDRTLMT.AGN_BAL;
        }
        CEP.TRC(SCCGWA, WS_O_PROD_TYP);
        CEP.TRC(SCCGWA, WS_FR);
        CEP.TRC(SCCGWA, WS_DT);
        CEP.TRC(SCCGWA, WS_CCY);
        CEP.TRC(SCCGWA, WS_TOT_BAL);
        CEP.TRC(SCCGWA, WS_AGN_BAL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDZTLML_WS5;
        SCCFMT.DATA_LEN = 51;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B120_O_OUT_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PROD_TYP);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PERS);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.YEAR);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.CCY);
        TDCTLML.TOT_BAL = TDRTLMT.TOT_BAL;
        TDCTLML.AGN_BAL = TDRTLMT.AGN_BAL;
    }
    public void B010_A_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = (short) (TDCTLML.DT - 1);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.YEAR);
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        T000_O_READ_TDTTLMT();
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PROD_TYP);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PERS);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.CCY);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.YEAR);
        T000_A_READ_TDTTLMT();
        if (WS_TML_FU == 'Y') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AMT_LIVE_MOD);
        }
        if (TDCTLML.TOT_BAL < WS_AGN_BAL) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, TDCTLML.TOT_BAL);
        CEP.TRC(SCCGWA, WS_AGN_BAL);
        TDRTLMT.TOT_BAL = TDCTLML.TOT_BAL;
        CEP.TRC(SCCGWA, TDRTLMT.AGN_BAL);
        TDRTLMT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRTLMT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void T000_DELETE_TDTLMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        T000_READ_TDTTLMT();
        if (TDRTLMT.AGN_BAL != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DELETE_TB_ERR);
        }
        T000_DELETE_TDTTLMT();
    }
    public void B030_M_FUNC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        CEP.TRC(SCCGWA, TDCTLML.TOT_BAL);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        T000_READ_TDTTLMT();
        if (TDCTLML.TOT_BAL < TDRTLMT.AGN_BAL) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
        TDRTLMT.TOT_BAL = TDCTLML.TOT_BAL;
        TDRTLMT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRTLMT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_READUP_TDTTLMT();
    }
    public void B030_S_FUNC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PROD_TYP);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.PERS);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.YEAR);
        CEP.TRC(SCCGWA, TDRTLMT.KEY.CCY);
        T000_READ_TDTTLMT();
        WS_AGN_BAL = 0;
        WS_AGN_BAL2 = 0;
        WS_AGN_BAL = TDRTLMT.AGN_BAL;
        WS_AGN_BAL2 = TDCTLML.AGN_BAL;
        WS_AGN_BAL = WS_AGN_BAL - WS_AGN_BAL2;
        TDRTLMT.AGN_BAL = WS_AGN_BAL;
        T000_READUP_TDTTLMT();
    }
    public void B030_O_FUNC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCTLML.FUNC);
        CEP.TRC(SCCGWA, TDCTLML.PROD_TYP);
        CEP.TRC(SCCGWA, TDCTLML.FR);
        CEP.TRC(SCCGWA, TDCTLML.DT);
        CEP.TRC(SCCGWA, TDCTLML.CCY);
        IBS.init(SCCGWA, TDRTLMT);
        TDRTLMT.KEY.PROD_TYP = TDCTLML.PROD_TYP;
        TDRTLMT.KEY.PERS = TDCTLML.FR;
        TDRTLMT.KEY.YEAR = TDCTLML.DT;
        TDRTLMT.KEY.CCY = TDCTLML.CCY;
        T000_READ_TDTTLMT();
        WS_AGN_BAL = 0;
        WS_AGN_BAL2 = 0;
        WS_AGN_BAL = TDRTLMT.AGN_BAL;
        WS_AGN_BAL2 = TDCTLML.AGN_BAL;
        CEP.TRC(SCCGWA, WS_AGN_BAL);
        WS_AGN_BAL = WS_AGN_BAL + WS_AGN_BAL2;
        CEP.TRC(SCCGWA, WS_AGN_BAL);
        if (TDRTLMT.TOT_BAL < WS_AGN_BAL) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
        TDRTLMT.AGN_BAL = WS_AGN_BAL;
        T000_READUP_TDTTLMT();
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "ACTI_NO = :TDROTHE.PROD_CD";
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        TDTTLMT_RD.where = "PROD_TYP = :TDRTLMT.KEY.PROD_TYP "
            + "AND PERS = :TDRTLMT.KEY.PERS "
            + "AND YEAR = :TDRTLMT.KEY.YEAR "
            + "AND CCY = :TDRTLMT.KEY.CCY";
        TDTTLMT_RD.upd = true;
        IBS.READ(SCCGWA, TDRTLMT, this, TDTTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRO_LIVE_NEW);
        }
    }
    public void T000_A_READ_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        TDTTLMT_RD.where = "PROD_TYP = :TDRTLMT.KEY.PROD_TYP "
            + "AND PERS = :TDRTLMT.KEY.PERS "
            + "AND YEAR = :TDRTLMT.KEY.YEAR "
            + "AND CCY = :TDRTLMT.KEY.CCY";
        IBS.READ(SCCGWA, TDRTLMT, this, TDTTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TML_FU = 'N';
        } else {
            WS_TML_FU = 'Y';
        }
    }
    public void T000_O_READ_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        TDTTLMT_RD.where = "PROD_TYP = :TDRTLMT.KEY.PROD_TYP "
            + "AND PERS = :TDRTLMT.KEY.PERS "
            + "AND YEAR = :TDRTLMT.KEY.YEAR "
            + "AND CCY = :TDRTLMT.KEY.CCY";
        IBS.READ(SCCGWA, TDRTLMT, this, TDTTLMT_RD);
    }
    public void T000_READUP_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        IBS.REWRITE(SCCGWA, TDRTLMT, TDTTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_UPDATE_TA_ERR);
        }
    }
    public void T000_ADD_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        IBS.WRITE(SCCGWA, TDRTLMT, TDTTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DOCU_WRITE_ERR);
        }
    }
    public void T000_DELETE_TDTTLMT() throws IOException,SQLException,Exception {
        TDTTLMT_RD = new DBParm();
        TDTTLMT_RD.TableName = "TDTTLMT";
        TDTTLMT_RD.where = "PROD_TYP = :TDRTLMT.KEY.PROD_TYP "
            + "AND PERS = :TDRTLMT.KEY.PERS "
            + "AND YEAR = :TDRTLMT.KEY.YEAR "
            + "AND CCY = :TDRTLMT.KEY.CCY";
        IBS.DELETE(SCCGWA, TDRTLMT, this, TDTTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DELETE_TB_ERR);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
