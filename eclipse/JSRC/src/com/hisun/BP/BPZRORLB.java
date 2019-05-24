package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRORLB {
    brParm BPTORGL_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_ORGL = "BPTORGL ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORGL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGL BPRORGL = new BPRORGL();
    SCCGWA SCCGWA;
    BPCRORLB BPCRORLB;
    BPRORGL BPRORGLL;
    public void MP(SCCGWA SCCGWA, BPCRORLB BPCRORLB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRORLB = BPCRORLB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRORLB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGLL = (BPRORGL) BPCRORLB.INFO.POINTER;
        IBS.init(SCCGWA, BPCRORLB.RC);
        IBS.CLONE(SCCGWA, BPRORGLL, BPRORGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRORLB.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRORLB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRORLB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRORLB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGL, BPRORGLL);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTORGL();
        if (pgmRtn) return;
        if (WS_TBL_ORGL_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRORLB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGL();
        if (pgmRtn) return;
        if (WS_TBL_ORGL_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRORLB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGL();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTORGL() throws IOException,SQLException,Exception {
        if (BPRORGL.KEY.TX_DATE != 0 
            && BPRORGL.KEY.TX_JRN != 0) {
            BPTORGL_BR.rp = new DBParm();
            BPTORGL_BR.rp.TableName = "BPTORGL";
            BPTORGL_BR.rp.where = "TX_DATE = :BPRORGL.KEY.TX_DATE "
                + "AND TX_JRN = :BPRORGL.KEY.TX_JRN";
            IBS.STARTBR(SCCGWA, BPRORGL, this, BPTORGL_BR);
        }
        if (BPRORGL.TX_TOOL.trim().length() > 0 
            && BPRORGL.TX_FLG != ' ') {
            BPTORGL_BR.rp = new DBParm();
            BPTORGL_BR.rp.TableName = "BPTORGL";
            BPTORGL_BR.rp.where = "TX_TOOL = :BPRORGL.TX_TOOL "
                + "AND TX_FLG = :BPRORGL.TX_FLG";
            IBS.STARTBR(SCCGWA, BPRORGL, this, BPTORGL_BR);
        }
    }
    public void T000_READNEXT_BPTORGL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGL, this, BPTORGL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_TBL_ORGL_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_TBL_ORGL_FLAG = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRORLB.RC);
        } else {
            CEP.TRC(SCCGWA, "333");
        }
    }
    public void T000_ENDBR_BPTORGL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRORLB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRORLB = ");
            CEP.TRC(SCCGWA, BPCRORLB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
