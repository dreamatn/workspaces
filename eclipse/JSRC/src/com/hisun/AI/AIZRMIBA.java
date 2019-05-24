package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRMIBA {
    DBParm AITMIBA_RD;
    boolean pgmRtn = false;
    String TBL_AITMIBA = "AITMIBA";
    String WS_TX_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRMIBA AIRMIBA = new AIRMIBA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICRMIBA AICRMIBA;
    AIRMIBA AIRLMIBA;
    public void MP(SCCGWA SCCGWA, AICRMIBA AICRMIBA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRMIBA = AICRMIBA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRMIBA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICRMIBA.RC);
        AIRLMIBA = (AIRMIBA) AICRMIBA.POINTER;
        IBS.init(SCCGWA, AIRMIBA);
        AICRMIBA.REC_LEN = 132;
        IBS.CLONE(SCCGWA, AIRLMIBA, AIRMIBA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRMIBA.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBA.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBA.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBA.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBA.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIBA.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIBA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRMIBA, AIRLMIBA);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRMIBA, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRMIBA.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_ALREADY_EXIST, AICRMIBA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.upd = true;
        IBS.READ(SCCGWA, AIRMIBA, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBA.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        IBS.REWRITE(SCCGWA, AIRMIBA, AITMIBA_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITMIBA();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        IBS.DELETE(SCCGWA, AIRMIBA, AITMIBA_RD);
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRMIBA.OPT == 'F') {
            T000_STARTBR_AITMIBA_FIRST();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIBA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITMIBA_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE");
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.where = "BOOK_FLG = :AIRMIBA.KEY.BOOK_FLG "
            + "AND AC = :AIRMIBA.KEY.AC "
            + "AND AC_DT <= :AIRMIBA.KEY.AC_DT";
        AITMIBA_RD.fst = true;
        AITMIBA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, AIRMIBA, this, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBA.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITMIBA() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        IBS.READ(SCCGWA, AIRMIBA, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIBA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIBA.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIBA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRMIBA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRMIBA = ");
            CEP.TRC(SCCGWA, AICRMIBA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
