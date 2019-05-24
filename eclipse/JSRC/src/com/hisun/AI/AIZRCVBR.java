package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRCVBR {
    DBParm AITONB_RD;
    brParm AITONB_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITONB = "AITONB ";
    String WS_TX_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRONB AIRONB = new AIRONB();
    SCCGWA SCCGWA;
    AICRCVBR AICRCVBR;
    AIRONB AIRLONB;
    public void MP(SCCGWA SCCGWA, AICRCVBR AICRCVBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRCVBR = AICRCVBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRCVBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRCVBR.RC);
        AIRLONB = (AIRONB) AICRCVBR.POINTER;
        IBS.init(SCCGWA, AIRONB);
        AICRCVBR.REC_LEN = 122;
        IBS.CLONE(SCCGWA, AIRLONB, AIRONB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICRCVBR.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCVBR.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCVBR.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCVBR.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCVBR.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCVBR.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCVBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRONB, AIRLONB);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        AITONB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRONB, AITONB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCVBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCVBR.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CPRD_ALREADY_EXIST, AICRCVBR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITONB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        AITONB_RD.upd = true;
        IBS.READ(SCCGWA, AIRONB, AITONB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCVBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCVBR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITONB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        IBS.REWRITE(SCCGWA, AIRONB, AITONB_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        IBS.READ(SCCGWA, AIRONB, AITONB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCVBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCVBR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITONB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        IBS.DELETE(SCCGWA, AIRONB, AITONB_RD);
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRCVBR.OPT == 'S') {
            T000_STARTBR_AITONB();
            if (pgmRtn) return;
        } else if (AICRCVBR.OPT == 'N') {
            T000_READNEXT_AITONB();
            if (pgmRtn) return;
        } else if (AICRCVBR.OPT == 'E') {
            T000_ENDBR_AITONB();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRCVBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITONB() throws IOException,SQLException,Exception {
        AITONB_BR.rp = new DBParm();
        AITONB_BR.rp.TableName = "AITONB";
        AITONB_BR.rp.where = "OBR = :AIRONB.OBR";
        IBS.STARTBR(SCCGWA, AIRONB, this, AITONB_BR);
    }
    public void T000_READNEXT_AITONB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRONB, this, AITONB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCVBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCVBR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITONB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITONB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITONB_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRCVBR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRCVBR = ");
            CEP.TRC(SCCGWA, AICRCVBR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
