package com.hisun.VT;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class VTZRHTAX {
    boolean pgmRtn = false;
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
    VTRHTAX VTRHTAX = new VTRHTAX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRHTAX VTCRHTAX;
    VTRHTAX VTRLTAX;
    public void MP(SCCGWA SCCGWA, VTCRHTAX VTCRHTAX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRHTAX = VTCRHTAX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZRHTAX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRHTAX.RC);
        VTRLTAX = (VTRHTAX) VTCRHTAX.POINTER;
        IBS.init(SCCGWA, VTRHTAX);
        VTCRHTAX.REC_LEN = 445;
        IBS.CLONE(SCCGWA, VTRLTAX, VTRHTAX);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCRHTAX.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (VTCRHTAX.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (VTCRHTAX.FUNC == 'A') {
            B030_CREATE_PROC();
            if (pgmRtn) return;
        } else if (VTCRHTAX.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (VTCRHTAX.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (VTCRHTAX.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
        }
        IBS.CLONE(SCCGWA, VTRHTAX, VTRLTAX);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRHTAX.OPT == 'S') {
            T000_STARTBR_VTTHTAX();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'Z') {
            T000_STARTBR_VTTHTAX_FIRST();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'M') {
            T000_STARTBR_VTTHTAX_UPDATE();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'O') {
            T000_STARTBR_VTTHTAX_OTHERS();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'R') {
            T000_STARTBR_VTTHTAX_RETURN();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'W') {
            T000_READNEXT_VTTHTAX();
            if (pgmRtn) return;
        } else if (VTCRHTAX.OPT == 'E') {
            T000_ENDBR_VTTHTAX();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        VTTHTAX_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTRHTAX, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRHTAX.RETURN_INFO = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        IBS.REWRITE(SCCGWA, VTRHTAX, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        VTTHTAX_RD.upd = true;
        IBS.READ(SCCGWA, VTRHTAX, VTTHTAX_RD);
        CEP.TRC(SCCGWA, "GO NEXT");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GO NEXT1");
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GO NEXT2");
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "GO NEXT3");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        IBS.READ(SCCGWA, VTRHTAX, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "SUCCESS");
            CEP.TRC(SCCGWA, VTRHTAX.KEY.SET_NO);
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FAIL");
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        IBS.DELETE(SCCGWA, VTRHTAX, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRHTAX.RETURN_INFO = 'Y';
        } else {
        }
    }
    public void T000_STARTBR_VTTHTAX() throws IOException,SQLException,Exception {
        WS_START_DATE = VTCRHTAX.START_DATE;
        WS_END_DATE = VTCRHTAX.END_DATE;
        WS_SET_NO = VTRHTAX.KEY.SET_NO;
        WS_SET_SEQ = VTRHTAX.KEY.SET_SEQ;
        WS_BR = VTRHTAX.BR;
        WS_BILL_NO = VTRHTAX.BILL_NO;
        WS_PROD_CD = VTRHTAX.PROD_CD;
        WS_ITM = VTRHTAX.ITM;
        WS_CI_NO = VTRHTAX.CI_NO;
        WS_CCY = VTRHTAX.CCY;
        WS_YJ_OBAL = VTRHTAX.YJ_OBAL;
        WS_SPC_TR_FLG = VTRHTAX.SPC_TR_FLG.charAt(0);
        WS_VAT_TYPE = VTRHTAX.VAT_TYPE;
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
        CEP.TRC(SCCGWA, WS_VAT_TYPE);
        VTTHTAX_BR.rp = new DBParm();
        VTTHTAX_BR.rp.TableName = "VTTHTAX";
        VTTHTAX_BR.rp.where = "( ( AC_DATE >= :WS_START_DATE "
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
        IBS.STARTBR(SCCGWA, VTRHTAX, this, VTTHTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_VTTHTAX_FIRST() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTRHTAX.KEY.AC_DATE;
        WS_SET_NO = VTRHTAX.KEY.SET_NO;
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        VTTHTAX_RD.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO";
        VTTHTAX_RD.fst = true;
        VTTHTAX_RD.order = "SET_SEQ DESC";
        IBS.READ(SCCGWA, VTRHTAX, this, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_VTTHTAX_RETURN() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTRHTAX.KEY.AC_DATE;
        WS_SET_NO = VTRHTAX.KEY.SET_NO;
        WS_SH_OBAL = VTRHTAX.SH_OBAL;
        WS_PROD_CD = VTRHTAX.PROD_CD;
        VTTHTAX_RD = new DBParm();
        VTTHTAX_RD.TableName = "VTTHTAX";
        VTTHTAX_RD.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO "
            + "AND SH_OBAL = :WS_SH_OBAL "
            + "AND PROD_CD = :WS_PROD_CD";
        VTTHTAX_RD.fst = true;
        VTTHTAX_RD.order = "SET_SEQ DESC";
        IBS.READ(SCCGWA, VTRHTAX, this, VTTHTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_VTTHTAX_UPDATE() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTCRHTAX.AC_DATE;
        WS_SET_NO = VTCRHTAX.SET_NO;
        CEP.TRC(SCCGWA, VTCRHTAX.AC_DATE);
        CEP.TRC(SCCGWA, VTCRHTAX.SET_NO);
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_SET_NO);
        VTTHTAX_BR.rp = new DBParm();
        VTTHTAX_BR.rp.TableName = "VTTHTAX";
        VTTHTAX_BR.rp.where = "AC_DATE = :WS_AC_DATE "
            + "AND SET_NO = :WS_SET_NO";
        IBS.STARTBR(SCCGWA, VTRHTAX, this, VTTHTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRHTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRHTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTHTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_VTTHTAX_OTHERS() throws IOException,SQLException,Exception {
        WS_AC_DATE = VTRHTAX.TR_DATE;
        WS_TR_SET_NO = VTRHTAX.TR_SET_NO;
        WS_CHNL_NO = VTRHTAX.CHNL_NO;
        WS_SET_NO = VTRHTAX.KEY.SET_NO;
