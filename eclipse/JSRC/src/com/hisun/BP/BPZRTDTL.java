package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTDTL {
    brParm BPTTDTL_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTDTL";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TDTL = "BPTTDTL  ";
    int WS_COUNT = 0;
    int WS_MIN_BR = 0;
    int WS_MAX_BR = 0;
    String WS_MIN_TLR = " ";
    String WS_MAX_TLR = " ";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTDTL BPRTDTL = new BPRTDTL();
    SCCGWA SCCGWA;
    BPCRTDTL BPCRTDTL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTDTL BPRTDLL;
    public void MP(SCCGWA SCCGWA, BPCRTDTL BPCRTDTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTDTL = BPCRTDTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTDTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTDLL = (BPRTDTL) BPCRTDTL.INFO.POINTER;
        IBS.init(SCCGWA, BPRTDTL);
        IBS.CLONE(SCCGWA, BPRTDLL, BPRTDTL);
        BPCRTDTL.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, BPRTDTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTDTL.INFO.FUNC == 'T') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTDTL.INFO.FUNC == 'B') {
            B015_STARTBR_BR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTDTL.INFO.FUNC == 'M') {
            B020_STARTBR_END_DT_MAX_PROC();
            if (pgmRtn) return;
        } else if (BPCRTDTL.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTDTL.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTDTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTDTL, BPRTDLL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTDTL();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_BR_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTDTL_BR();
        if (pgmRtn) return;
    }
    public void B020_STARTBR_END_DT_MAX_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTDTL_MAX();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTDTL();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTDTL();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTDTL() throws IOException,SQLException,Exception {
        BPTTDTL_BR.rp = new DBParm();
        BPTTDTL_BR.rp.TableName = "BPTTDTL";
        BPTTDTL_BR.rp.where = "TLR = :BPRTDTL.KEY.TLR "
            + "AND OUT_BR = :BPRTDTL.OUT_BR "
            + "AND TYPE = :BPRTDTL.KEY.TYPE";
        IBS.STARTBR(SCCGWA, BPRTDTL, this, BPTTDTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTDTL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTDTL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTDTL_BR() throws IOException,SQLException,Exception {
        BPTTDTL_BR.rp = new DBParm();
        BPTTDTL_BR.rp.TableName = "BPTTDTL";
        BPTTDTL_BR.rp.where = "OUT_BR = :BPRTDTL.OUT_BR "
            + "AND TYPE = :BPRTDTL.KEY.TYPE";
        IBS.STARTBR(SCCGWA, BPRTDTL, this, BPTTDTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTDTL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTDTL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTDTL_MAX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTDTL.KEY.TLR);
        BPTTDTL_BR.rp = new DBParm();
        BPTTDTL_BR.rp.TableName = "BPTTDTL";
        BPTTDTL_BR.rp.where = "TLR = :BPRTDTL.KEY.TLR "
            + "AND TYPE = 'T' "
            + "AND BEGIN_DT > 0";
        BPTTDTL_BR.rp.order = "END_DT,END_TIME DESC";
        IBS.STARTBR(SCCGWA, BPRTDTL, this, BPTTDTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTDTL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTDTL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTTDTL() throws IOException,SQLException,Exception {
        BPTTDTL_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTDTL, this, BPTTDTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTDTL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTDTL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTDTL() throws IOException,SQLException,Exception {
        BPTTDTL_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTDTL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTDTL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTDTL = ");
            CEP.TRC(SCCGWA, BPCRTDTL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
