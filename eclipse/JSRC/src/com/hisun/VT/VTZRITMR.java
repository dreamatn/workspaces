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

public class VTZRITMR {
    DBParm VTTITMR_RD;
    brParm VTTITMR_BR = new brParm();
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    String WS_COA_FLG = " ";
    String WS_ITM = " ";
    int WS_AC_SEQ = 0;
    String WS_CODE = " ";
    char WS_BILL_FLG = ' ';
    String WS_CCY = " ";
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRITMR VTRITMR = new VTRITMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRITMR VTCRITMR;
    VTRITMR VTRLTMR;
    public void MP(SCCGWA SCCGWA, VTCRITMR VTCRITMR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRITMR = VTCRITMR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRITMR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRITMR.RC);
        VTRLTMR = (VTRITMR) VTCRITMR.POINTER;
        IBS.init(SCCGWA, VTRITMR);
        VTCRITMR.REC_LEN = 180;
        IBS.CLONE(SCCGWA, VTRLTMR, VTRITMR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRITMR.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRITMR.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRITMR.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRITMR.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRITMR.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRITMR.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRITMR, VTRLTMR);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRITMR.OPT == 'S') {
            T000_STARTBR_VTTITMR();
        } else if (VTCRITMR.OPT == 'C') {
            T000_STARTBR_CODE_VTTITMR();
        } else if (VTCRITMR.OPT == 'R') {
            T000_STARTBR_FOR_CODE_VTTITMR();
        } else if (VTCRITMR.OPT == 'N') {
            T000_READNEXT_VTTITMR();
        } else if (VTCRITMR.OPT == 'E') {
            T000_ENDBR_VTTITMR();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        VTTITMR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTRITMR, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRITMR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        IBS.REWRITE(SCCGWA, VTRITMR, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        VTTITMR_RD.upd = true;
        IBS.READ(SCCGWA, VTRITMR, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        IBS.READ(SCCGWA, VTRITMR, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        IBS.DELETE(SCCGWA, VTRITMR, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRITMR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTITMR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRITMR.KEY.CODE);
        CEP.TRC(SCCGWA, VTRITMR.KEY.ITM);
        CEP.TRC(SCCGWA, VTRITMR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, VTRITMR.BILL_FLG);
        WS_ITM = VTRITMR.KEY.ITM;
        WS_AC_SEQ = VTRITMR.KEY.AC_SEQ;
        WS_CODE = VTRITMR.KEY.CODE;
        WS_BILL_FLG = VTRITMR.BILL_FLG;
        CEP.TRC(SCCGWA, WS_ITM);
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, WS_CODE);
        CEP.TRC(SCCGWA, WS_BILL_FLG);
        VTTITMR_BR.rp = new DBParm();
        VTTITMR_BR.rp.TableName = "VTTITMR";
        VTTITMR_BR.rp.where = "( 0 = :WS_AC_SEQ "
            + "OR AC_SEQ = :WS_AC_SEQ ) "
            + "AND ( 0 = :WS_ITM "
            + "OR ITM LIKE :WS_ITM ) "
            + "AND ( ' ' = :WS_CODE "
            + "OR CODE = :WS_CODE ) "
            + "AND ( ' ' = :WS_BILL_FLG "
            + "OR BILL_FLG = :WS_BILL_FLG )";
        IBS.STARTBR(SCCGWA, VTRITMR, this, VTTITMR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_CODE_VTTITMR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRITMR.KEY.CODE);
        WS_CODE = VTRITMR.KEY.CODE;
        CEP.TRC(SCCGWA, WS_CODE);
        VTTITMR_BR.rp = new DBParm();
        VTTITMR_BR.rp.TableName = "VTTITMR";
        VTTITMR_BR.rp.where = "CODE = :WS_CODE";
        IBS.STARTBR(SCCGWA, VTRITMR, this, VTTITMR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_FOR_CODE_VTTITMR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRITMR.KEY.CODE);
        WS_ITM = VTRITMR.KEY.ITM;
        WS_AC_SEQ = VTRITMR.KEY.AC_SEQ;
        WS_BR = VTRITMR.KEY.BR;
        WS_CCY = VTRITMR.KEY.CCY;
        CEP.TRC(SCCGWA, WS_CODE);
        CEP.TRC(SCCGWA, WS_ITM);
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, WS_BR);
        CEP.TRC(SCCGWA, WS_CCY);
        VTTITMR_RD = new DBParm();
        VTTITMR_RD.TableName = "VTTITMR";
        VTTITMR_RD.where = "ITM = :WS_ITM "
            + "AND BR = :WS_BR "
            + "AND AC_SEQ = :WS_AC_SEQ "
            + "AND ( CCY = :WS_CCY "
            + "OR CCY = '999' ) "
            + "AND STS = 'N'";
        VTTITMR_RD.fst = true;
        IBS.READ(SCCGWA, VTRITMR, this, VTTITMR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTITMR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRITMR, this, VTTITMR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRITMR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRITMR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTITMR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTITMR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTITMR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
