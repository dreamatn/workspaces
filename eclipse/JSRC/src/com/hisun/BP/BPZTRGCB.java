package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTRGCB {
    brParm BPTRGNC_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTRGCB = "BPZTRGCB";
    String TBL_BPTRGNC = "BPTRGNC ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGNC BPRRGNC = new BPRRGNC();
    SCCGWA SCCGWA;
    BPCRRGCB BPCRRGCB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRRGCB BPCRRGCB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRGCB = BPCRRGCB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTRGCB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRGCB.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGCB.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGCB.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRRGCB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGNC);
        BPRRGNC.KEY.BNK = BPCRRGCB.BNK;
        if (BPCRRGCB.RGN_TYPE.trim().length() == 0) {
            T000_STARTBR_BPTRGNC_ALL();
            if (pgmRtn) return;
        } else {
            BPRRGNC.KEY.RGN_TYPE = BPCRRGCB.RGN_TYPE;
            T000_STARTBR_BPTRGNC_TYPE();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTRGNC();
        if (pgmRtn) return;
        BPCRRGCB.RGN_TYPE = BPRRGNC.KEY.RGN_TYPE;
        BPCRRGCB.RGN_SEQ = BPRRGNC.KEY.RGN_SEQ;
        BPCRRGCB.EFF_DATE = BPRRGNC.EFF_DT;
        BPCRRGCB.EXP_DATE = BPRRGNC.EXP_DT;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTRGNC();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTRGNC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRGNC, this, BPTRGNC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRGCB.RC_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRGCB.RC_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTRGNC_ALL() throws IOException,SQLException,Exception {
        BPTRGNC_BR.rp = new DBParm();
        BPTRGNC_BR.rp.TableName = "BPTRGNC";
        BPTRGNC_BR.rp.where = "BNK = :BPRRGNC.KEY.BNK";
        IBS.STARTBR(SCCGWA, BPRRGNC, this, BPTRGNC_BR);
    }
    public void T000_STARTBR_BPTRGNC_TYPE() throws IOException,SQLException,Exception {
        BPTRGNC_BR.rp = new DBParm();
        BPTRGNC_BR.rp.TableName = "BPTRGNC";
        BPTRGNC_BR.rp.where = "BNK = :BPRRGNC.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGNC.KEY.RGN_TYPE";
        IBS.STARTBR(SCCGWA, BPRRGNC, this, BPTRGNC_BR);
    }
    public void T000_ENDBR_BPTRGNC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRGNC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRGCB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRRGCB = ");
            CEP.TRC(SCCGWA, BPCRRGCB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
