package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.io.IOException;
import java.sql.SQLException;

public class VTZROTAX {
    DBParm VTTOTAX_RD;
    brParm VTTOTAX_BR = new brParm();
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    int WS_AC_DATE = 0;
    String WS_SET_NO = " ";
    int WS_SET_SEQ = 0;
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    int WS_BR = 0;
    String WS_BILL_NO = " ";
    String WS_PROD_CD = " ";
    String WS_ITM = " ";
    String WS_CI_NO = " ";
    String WS_CCY = " ";
    double WS_YJ_OBAL = 0;
    char WS_SPC_TR_FLG = ' ';
    String WS_TR_SET_NO = " ";
    String WS_CHNL_NO = " ";
    double WS_SH_OBAL = 0;
    char WS_VAT_TYPE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTROTAX VTROTAX = new VTROTAX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCROTAX VTCROTAX;
    VTROTAX VTRLOTAX;
    public void MP(SCCGWA SCCGWA, VTCROTAX VTCROTAX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCROTAX = VTCROTAX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZROTAX return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCROTAX.RC);
        VTRLOTAX = (VTROTAX) VTCROTAX.POINTER;
        IBS.init(SCCGWA, VTROTAX);
        VTCROTAX.REC_LEN = 445;
        IBS.CLONE(SCCGWA, VTRLOTAX, VTROTAX);
        CEP.TRC(SCCGWA, VTROTAX);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCROTAX.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCROTAX.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCROTAX.FUNC == 'A') {
            B030_CREATE_PROC();
        } else if (VTCROTAX.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCROTAX.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCROTAX.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTROTAX, VTRLOTAX);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCROTAX.OPT == 'S') {
            T000_STARTBR_VTTOTAX();
        } else if (VTCROTAX.OPT == 'Z') {
            T000_STARTBR_VTTOTAX_FIRST();
        } else if (VTCROTAX.OPT == 'M') {
            T000_STARTBR_VTTOTAX_UPDATE();
        } else if (VTCROTAX.OPT == 'O') {
            T000_STARTBR_VTTOTAX_OTHERS();
        } else if (VTCROTAX.OPT == 'R') {
            T000_STARTBR_VTTOTAX_RETURN();
        } else if (VTCROTAX.OPT == 'W') {
            T000_READNEXT_VTTOTAX();
        } else if (VTCROTAX.OPT == 'E') {
            T000_ENDBR_VTTOTAX();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        VTTOTAX_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTROTAX, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCROTAX.RETURN_INFO = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        IBS.REWRITE(SCCGWA, VTROTAX, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        VTTOTAX_RD.upd = true;
        IBS.READ(SCCGWA, VTROTAX, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        IBS.READ(SCCGWA, VTROTAX, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        IBS.DELETE(SCCGWA, VTROTAX, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCROTAX.RETURN_INFO = 'Y';
        } else {
        }
    }
    public void T000_STARTBR_VTTOTAX() throws IOException,SQLException,Exception {
        WS_START_DATE = VTCROTAX.START_DATE;
        WS_END_DATE = VTCROTAX.END_DATE;
        WS_SET_NO = VTROTAX.KEY.SET_NO;
        WS_SET_SEQ = VTROTAX.KEY.SET_SEQ;
        WS_BR = VTROTAX.BR;
        WS_BILL_NO = VTROTAX.BILL_NO;
        WS_PROD_CD = VTROTAX.PROD_CD;
        WS_ITM = VTROTAX.ITM;
        WS_CI_NO = VTROTAX.CI_NO;
        WS_CCY = VTROTAX.CCY;
        WS_YJ_OBAL = VTROTAX.YJ_OBAL;
        WS_SPC_TR_FLG = VTROTAX.SPC_TR_FLG.charAt(0);
        WS_VAT_TYPE = VTROTAX.VAT_TYPE;
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, WS_SET_NO);
        CEP.TRC(SCCGWA, WS_BR);
        CEP.TRC(SCCGWA, WS_BILL_NO);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        CEP.TRC(SCCGWA, WS_ITM);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_CCY);
        CEP.TRC(SCCGWA, WS_YJ_OBAL);
        VTTOTAX_BR.rp = new DBParm();
        VTTOTAX_BR.rp.TableName = "VTTOTAX";
        VTTOTAX_BR.rp.where = "( ( AC_DATE >= :WS_START_DATE "
            + "OR 0 = :WS_START_DATE ) "
            + "AND ( AC_DATE <= :WS_END_DATE "
            + "OR 0 = :WS_END_DATE ) ) "
            + "AND ( SET_NO = :WS_SET_NO "
            + "OR ' ' = :WS_SET_NO ) "
            + "AND ( SET_SEQ = :WS_SET_SEQ "
            + "OR 0 = :WS_SET_SEQ ) "
            + "AND ( BR = :WS_BR "
            + "OR 0 = :WS_BR ) "
            + "AND ( BILL_NO = :WS_BILL_NO "
            + "OR ' ' = :WS_BILL_NO ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR ' ' = :WS_PROD_CD ) "
            + "AND ( ITM = :WS_ITM "
            + "OR ' ' = :WS_ITM ) "
            + "AND ( CI_NO = :WS_CI_NO "
            + "OR ' ' = :WS_CI_NO ) "
            + "AND ( CCY = :WS_CCY "
            + "OR ' ' = :WS_CCY ) "
            + "AND ( YJ_OBAL = :WS_YJ_OBAL "
            + "OR 0 = :WS_YJ_OBAL ) "
            + "AND ( VAT_TYPE = :WS_VAT_TYPE "
            + "OR ' ' = :WS_VAT_TYPE ) "
            + "AND ( SPC_TR_FLG = :WS_SPC_TR_FLG "
            + "OR ' ' = :WS_SPC_TR_FLG )";
        IBS.STARTBR(SCCGWA, VTROTAX, this, VTTOTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTOTAX_FIRST() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTROTAX.KEY.AC_DATE;
        WS_SET_NO = VTROTAX.KEY.SET_NO;
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        VTTOTAX_RD.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO";
        VTTOTAX_RD.fst = true;
        VTTOTAX_RD.order = "SET_SEQ DESC";
        IBS.READ(SCCGWA, VTROTAX, this, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTOTAX_SETNO() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTROTAX.KEY.AC_DATE;
        WS_SET_NO = VTROTAX.KEY.SET_NO;
        VTTOTAX_BR.rp = new DBParm();
        VTTOTAX_BR.rp.TableName = "VTTOTAX";
        VTTOTAX_BR.rp.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO";
        VTTOTAX_BR.rp.order = "SET_SEQ DESC";
        IBS.STARTBR(SCCGWA, VTROTAX, this, VTTOTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTOTAX_RETURN() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTROTAX.KEY.AC_DATE;
        WS_SET_NO = VTROTAX.KEY.SET_NO;
        WS_SH_OBAL = VTROTAX.SH_OBAL;
        WS_PROD_CD = VTROTAX.PROD_CD;
        VTTOTAX_RD = new DBParm();
        VTTOTAX_RD.TableName = "VTTOTAX";
        VTTOTAX_RD.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO "
            + "AND SH_OBAL = :WS_SH_OBAL "
            + "AND PROD_CD = :WS_PROD_CD";
        VTTOTAX_RD.fst = true;
        VTTOTAX_RD.order = "SET_SEQ DESC";
        IBS.READ(SCCGWA, VTROTAX, this, VTTOTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTOTAX_UPDATE() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTCROTAX.AC_DATE;
        WS_SET_NO = VTCROTAX.SET_NO;
        CEP.TRC(SCCGWA, VTCROTAX.AC_DATE);
        CEP.TRC(SCCGWA, VTCROTAX.SET_NO);
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_SET_NO);
        VTTOTAX_BR.rp = new DBParm();
        VTTOTAX_BR.rp.TableName = "VTTOTAX";
        VTTOTAX_BR.rp.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO";
        IBS.STARTBR(SCCGWA, VTROTAX, this, VTTOTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTOTAX_OTHERS() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTROTAX.TR_DATE;
        WS_TR_SET_NO = VTROTAX.TR_SET_NO;
        WS_CHNL_NO = VTROTAX.CHNL_NO;
        WS_SET_NO = VTROTAX.KEY.SET_NO;
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_TR_SET_NO);
        CEP.TRC(SCCGWA, WS_CHNL_NO);
        CEP.TRC(SCCGWA, WS_SET_NO);
        VTTOTAX_BR.rp = new DBParm();
        VTTOTAX_BR.rp.TableName = "VTTOTAX";
        VTTOTAX_BR.rp.where = "( AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO )";
        IBS.STARTBR(SCCGWA, VTROTAX, this, VTTOTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTOTAX() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTROTAX, this, VTTOTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCROTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCROTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTOTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTOTAX() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTOTAX_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
