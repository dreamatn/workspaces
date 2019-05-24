package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTSTB {
    brParm BPTTSTS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTSTB";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TSTS = "BPTTSTS ";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTSTS BPRTSTS = new BPRTSTS();
    SCCGWA SCCGWA;
    BPCRTSTB BPCRTSTB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTSTS BPRLTSTS;
    public void MP(SCCGWA SCCGWA, BPCRTSTB BPCRTSTB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTSTB = BPCRTSTB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTSTB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLTSTS = (BPRTSTS) BPCRTSTB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTSTS);
        IBS.CLONE(SCCGWA, BPRLTSTS, BPRTSTS);
        BPCRTSTB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTSTB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTSTB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTSTB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTSTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTSTS, BPRLTSTS);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTSTS();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTSTS();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTSTS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTSTS() throws IOException,SQLException,Exception {
        BPTTSTS_BR.rp = new DBParm();
        BPTTSTS_BR.rp.TableName = "BPTTSTS";
        BPTTSTS_BR.rp.where = "TSTS_APP = :BPRTSTS.KEY.TSTS_APP";
        BPTTSTS_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTSTS, this, BPTTSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTSTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTSTB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTSTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTTSTS() throws IOException,SQLException,Exception {
        BPTTSTS_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTSTS, this, BPTTSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTSTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTSTB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTSTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTSTS() throws IOException,SQLException,Exception {
        BPTTSTS_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTSTS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTSTB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTSTB = ");
            CEP.TRC(SCCGWA, BPCRTSTB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
