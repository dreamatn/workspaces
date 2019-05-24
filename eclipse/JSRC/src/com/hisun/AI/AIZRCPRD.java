package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRCPRD {
    DBParm AITCPRD_RD;
    brParm AITCPRD_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITCPRD = "AITCPRD";
    String WS_TX_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRCPRD AIRCPRD = new AIRCPRD();
    SCCGWA SCCGWA;
    AICRCPRD AICRCPRD;
    AIRCPRD AIRLCPRD;
    public void MP(SCCGWA SCCGWA, AICRCPRD AICRCPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRCPRD = AICRCPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRCPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRCPRD.RC);
        AIRLCPRD = (AIRCPRD) AICRCPRD.POINTER;
        IBS.init(SCCGWA, AIRCPRD);
        AICRCPRD.REC_LEN = 353;
        IBS.CLONE(SCCGWA, AIRLCPRD, AIRCPRD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICRCPRD.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCPRD.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCPRD.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCPRD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCPRD.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCPRD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRCPRD, AIRLCPRD);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        AITCPRD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRCPRD, AITCPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCPRD.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CPRD_ALREADY_EXIST, AICRCPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCPRD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        AITCPRD_RD.upd = true;
        IBS.READ(SCCGWA, AIRCPRD, AITCPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCPRD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCPRD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        IBS.REWRITE(SCCGWA, AIRCPRD, AITCPRD_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        IBS.READ(SCCGWA, AIRCPRD, AITCPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCPRD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCPRD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        IBS.DELETE(SCCGWA, AIRCPRD, AITCPRD_RD);
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRCPRD.OPT == 'S') {
            T000_STARTBR_AITCPRD();
            if (pgmRtn) return;
        } else if (AICRCPRD.OPT == 'N') {
            T000_READNEXT_AITCPRD();
            if (pgmRtn) return;
        } else if (AICRCPRD.OPT == 'E') {
            T000_ENDBR_AITCPRD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITCPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE CPRD");
        CEP.TRC(SCCGWA, AIRCPRD.KEY.AC);
        CEP.TRC(SCCGWA, AIRCPRD.KEY.AC_DATE);
        CEP.TRC(SCCGWA, AIRCPRD.KEY.CI_NO);
        CEP.TRC(SCCGWA, AIRCPRD.STS);
        AITCPRD_BR.rp = new DBParm();
        AITCPRD_BR.rp.TableName = "AITCPRD";
        AITCPRD_BR.rp.where = "( 0 = :AIRCPRD.KEY.DEAL_FLG "
            + "OR DEAL_FLG = :AIRCPRD.KEY.DEAL_FLG ) "
            + "AND ( ' ' = :AIRCPRD.KEY.AC_DATE "
            + "OR AC_DATE = :AIRCPRD.KEY.AC_DATE ) "
            + "AND ( 0 = :AIRCPRD.KEY.AC "
            + "OR AC = :AIRCPRD.KEY.AC ) "
            + "AND ( ' ' = :AIRCPRD.OTH_AC "
            + "OR OTH_AC = :AIRCPRD.OTH_AC ) "
            + "AND ( ' ' = :AIRCPRD.AP_MMO "
            + "OR AP_MMO = :AIRCPRD.AP_MMO ) "
            + "AND ( ' ' = :AIRCPRD.KEY.CI_NO "
            + "OR CI_NO = :AIRCPRD.KEY.CI_NO ) "
            + "AND ( ' ' = :AIRCPRD.STS "
            + "OR STS = :AIRCPRD.STS )";
        AITCPRD_BR.rp.order = "AC_DATE,CI_NO,AC";
        IBS.STARTBR(SCCGWA, AIRCPRD, this, AITCPRD_BR);
    }
    public void T000_READNEXT_AITCPRD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRCPRD, this, AITCPRD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCPRD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCPRD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITCPRD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITCPRD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRCPRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRCPRD = ");
            CEP.TRC(SCCGWA, AICRCPRD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
