package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRMIBH {
    DBParm AITMIBH_RD;
    brParm AITMIBH_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITMIBH = "AITMIBH";
    String WS_TX_CD = " ";
    char WS_CON_FLG = ' ';
    AIZRMIBH_WS_AC_NO WS_AC_NO = new AIZRMIBH_WS_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRMIBH AIRMIBH = new AIRMIBH();
    SCCGWA SCCGWA;
    AICRMIBH AICRMIBH;
    AIRMIBH AIRLMIBH;
    public void MP(SCCGWA SCCGWA, AICRMIBH AICRMIBH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRMIBH = AICRMIBH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRMIBH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMIBH.RC);
        AIRLMIBH = (AIRMIBH) AICRMIBH.POINTER;
        IBS.init(SCCGWA, AIRMIBH);
        AICRMIBH.REC_LEN = 637;
        IBS.CLONE(SCCGWA, AIRLMIBH, AIRMIBH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRMIBH.FUNC);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        if (AICRMIBH.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBH.FUNC == 'S') {
            B070_GET_MIBH_BAL_SUM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIBH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRMIBH, AIRLMIBH);
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITMIBH_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIBH.RETURN_INFO);
        if (AICRMIBH.RETURN_INFO == 'F') {
            AICRMIBH.RETURN_INFO = ' ';
            CEP.TRC(SCCGWA, AICRMIBH.RETURN_INFO);
            T000_STARTBR_AITMIBH();
            if (pgmRtn) return;
            T000_READNEXT_AITMIBH();
            if (pgmRtn) return;
            while (AICRMIBH.RETURN_INFO != 'N') {
                T000_READUPD_AITMIBH();
                if (pgmRtn) return;
                T000_DELETE_AITMIBH();
                if (pgmRtn) return;
                T000_READNEXT_AITMIBH();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITMIBH();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRMIBH.OPT);
        if (AICRMIBH.OPT == 'S') {
            T000_STARTBR_AITMIBH();
            if (pgmRtn) return;
        } else if (AICRMIBH.OPT == 'N') {
            T000_READNEXT_AITMIBH();
            if (pgmRtn) return;
        } else if (AICRMIBH.OPT == 'E') {
            T000_ENDBR_AITMIBH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIBH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_GET_MIBH_BAL_SUM() throws IOException,SQLException,Exception {
        T000_READ_AITMIBH_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIBH.RETURN_INFO);
        if (AICRMIBH.RETURN_INFO == 'F') {
            AICRMIBH.RETURN_INFO = ' ';
            T070_GET_MIBH_BAL_SUM();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITMIBH_FIRST() throws IOException,SQLException,Exception {
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.eqWhere = "GL_BOOK,BR,ITM_NO,SEQ,CCY";
        AITMIBH_RD.fst = true;
        IBS.READ(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READUPD_AITMIBH() throws IOException,SQLException,Exception {
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.upd = true;
        IBS.READ(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_AITMIBH() throws IOException,SQLException,Exception {
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        IBS.DELETE(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITMIBH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        AITMIBH_BR.rp = new DBParm();
        AITMIBH_BR.rp.TableName = "AITMIBH";
        AITMIBH_BR.rp.eqWhere = "GL_BOOK,BR,ITM_NO,SEQ,CCY";
        AITMIBH_BR.rp.order = "PART_NO";
        IBS.STARTBR(SCCGWA, AIRMIBH, AITMIBH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_AITMIBH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIBH, this, AITMIBH_BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITMIBH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIBH_BR);
    }
    public void T070_GET_MIBH_BAL_SUM() throws IOException,SQLException,Exception {
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.set = "RMIBH-LBAL-SUM=SUM(LBAL),RMIBH-CBAL-SUM=SUM(CBAL)";
        AITMIBH_RD.eqWhere = "GL_BOOK,BR,ITM_NO,SEQ,CCY";
        IBS.GROUP(SCCGWA, AIRMIBH, this, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRMIBH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRMIBH = ");
            CEP.TRC(SCCGWA, AICRMIBH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
