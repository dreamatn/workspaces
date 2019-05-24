package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRPMIB {
    DBParm AITPMIB_RD;
    brParm AITPMIB_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITPMIB = "AITPMIB";
    String WS_TX_CD = " ";
    char WS_CON_FLG = ' ';
    AIZRPMIB_WS_AC_NO WS_AC_NO = new AIZRPMIB_WS_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRPMIB AIRPMIB = new AIRPMIB();
    SCCGWA SCCGWA;
    AICRPMIB AICRPMIB;
    AIRPMIB AIRLPMIB;
    public void MP(SCCGWA SCCGWA, AICRPMIB AICRPMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRPMIB = AICRPMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRPMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRPMIB.RC);
        AIRLPMIB = (AIRPMIB) AICRPMIB.POINTER;
        IBS.init(SCCGWA, AIRPMIB);
        AICRPMIB.REC_LEN = 127;
        IBS.CLONE(SCCGWA, AIRLPMIB, AIRPMIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRPMIB.FUNC);
        CEP.TRC(SCCGWA, AIRPMIB.BR);
        CEP.TRC(SCCGWA, AIRPMIB.CCY);
        CEP.TRC(SCCGWA, AIRPMIB.ITM_NO);
        CEP.TRC(SCCGWA, AIRPMIB.SEQ);
        if (AICRPMIB.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRPMIB.FUNC == 'S') {
            B070_GET_PMIB_BAL_SUM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRPMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRPMIB, AIRLPMIB);
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRPMIB.KEY.AC_DATE);
        CEP.TRC(SCCGWA, AIRPMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRPMIB.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, AIRPMIB.BR);
        CEP.TRC(SCCGWA, AIRPMIB.CCY);
        CEP.TRC(SCCGWA, AIRPMIB.ITM_NO);
        CEP.TRC(SCCGWA, AIRPMIB.SEQ);
        CEP.TRC(SCCGWA, AIRPMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRPMIB.DR_CR);
        CEP.TRC(SCCGWA, AIRPMIB.AMT);
        AITPMIB_RD = new DBParm();
        AITPMIB_RD.TableName = "AITPMIB";
        AITPMIB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRPMIB, AITPMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY VCHNO:");
            CEP.TRC(SCCGWA, AIRPMIB.KEY.SET_NO);
            CEP.TRC(SCCGWA, "DUPKEY VCHSEQ:");
            CEP.TRC(SCCGWA, AIRPMIB.KEY.SET_SEQ);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITPMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITPMIB_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRPMIB.RETURN_INFO);
        if (AICRPMIB.RETURN_INFO == 'F') {
            AICRPMIB.RETURN_INFO = ' ';
            CEP.TRC(SCCGWA, AICRPMIB.RETURN_INFO);
            T000_STARTBR_AITPMIB();
            if (pgmRtn) return;
            T000_READNEXT_AITPMIB();
            if (pgmRtn) return;
            while (AICRPMIB.RETURN_INFO != 'N') {
                T000_READUPD_AITPMIB();
                if (pgmRtn) return;
                T000_DELETE_AITPMIB();
                if (pgmRtn) return;
                T000_READNEXT_AITPMIB();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITPMIB();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRPMIB.OPT);
        if (AICRPMIB.OPT == 'S') {
            T000_STARTBR_AITPMIB();
            if (pgmRtn) return;
        } else if (AICRPMIB.OPT == 'N') {
            T000_READNEXT_AITPMIB();
            if (pgmRtn) return;
        } else if (AICRPMIB.OPT == 'E') {
            T000_ENDBR_AITPMIB();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRPMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_GET_PMIB_BAL_SUM() throws IOException,SQLException,Exception {
        T000_READ_AITPMIB_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRPMIB.RETURN_INFO);
        if (AICRPMIB.RETURN_INFO == 'F') {
            AICRPMIB.RETURN_INFO = ' ';
            T070_GET_PMIB_BAL_SUM();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITPMIB_FIRST() throws IOException,SQLException,Exception {
        AITPMIB_RD = new DBParm();
        AITPMIB_RD.TableName = "AITPMIB";
        AITPMIB_RD.eqWhere = "BR,ITM_NO,SEQ,CCY";
        AITPMIB_RD.fst = true;
        IBS.READ(SCCGWA, AIRPMIB, AITPMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRPMIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READUPD_AITPMIB() throws IOException,SQLException,Exception {
        AITPMIB_RD = new DBParm();
        AITPMIB_RD.TableName = "AITPMIB";
        AITPMIB_RD.upd = true;
        IBS.READ(SCCGWA, AIRPMIB, AITPMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRPMIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_AITPMIB() throws IOException,SQLException,Exception {
        AITPMIB_RD = new DBParm();
        AITPMIB_RD.TableName = "AITPMIB";
        IBS.DELETE(SCCGWA, AIRPMIB, AITPMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRPMIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITPMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRPMIB.BR);
        CEP.TRC(SCCGWA, AIRPMIB.CCY);
        CEP.TRC(SCCGWA, AIRPMIB.ITM_NO);
        CEP.TRC(SCCGWA, AIRPMIB.SEQ);
        AITPMIB_BR.rp = new DBParm();
        AITPMIB_BR.rp.TableName = "AITPMIB";
        AITPMIB_BR.rp.eqWhere = "BR,ITM_NO,SEQ,CCY";
        IBS.STARTBR(SCCGWA, AIRPMIB, AITPMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_AITPMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRPMIB, this, AITPMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRPMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITPMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITPMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITPMIB_BR);
    }
    public void T070_GET_PMIB_BAL_SUM() throws IOException,SQLException,Exception {
        AITPMIB_RD = new DBParm();
        AITPMIB_RD.TableName = "AITPMIB";
        AITPMIB_RD.set = "RPMIB-AMT-SUM=SUM(AMT)";
        AITPMIB_RD.eqWhere = "BR,ITM_NO,SEQ,CCY";
        IBS.GROUP(SCCGWA, AIRPMIB, this, AITPMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRPMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRPMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITPMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRPMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRPMIB = ");
            CEP.TRC(SCCGWA, AICRPMIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
