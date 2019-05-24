package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRONA {
    DBParm AITONA_RD;
    boolean pgmRtn = false;
    String TBL_AITONA = "AITONA";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRONA AIRONA = new AIRONA();
    SCCGWA SCCGWA;
    AICRONA AICRONA;
    AIRONA AIRLONA;
    public void MP(SCCGWA SCCGWA, AICRONA AICRONA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRONA = AICRONA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRONA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRONA.RC);
        AIRLONA = (AIRONA) AICRONA.POINTER;
        IBS.init(SCCGWA, AIRONA);
        AICRONA.REC_LEN = 82;
        IBS.CLONE(SCCGWA, AIRLONA, AIRONA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRONA.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRONA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRONA, AIRLONA);
    }
    public void T000_READ_AITONA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRONA.KEY.OAC_NO);
        AITONA_RD = new DBParm();
        AITONA_RD.TableName = "AITONA";
        AITONA_RD.where = "OAC_NO = :AIRONA.KEY.OAC_NO";
        AITONA_RD.fst = true;
        IBS.READ(SCCGWA, AIRONA, this, AITONA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, AIRONA.AC_NO);
            AICRONA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRONA.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITONA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITONA();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRONA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRONA = ");
            CEP.TRC(SCCGWA, AICRONA);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, AICRONA.RC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
