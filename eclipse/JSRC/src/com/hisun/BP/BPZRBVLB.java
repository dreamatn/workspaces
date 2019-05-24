package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBVLB {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBVLT_RD;
    brParm BPTBVLT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBVLB";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBVLT BPRBVLT = new BPRBVLT();
    SCCGWA SCCGWA;
    BPCRBVLB BPCRBVLB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRBVLT BPRBVLT1;
    public void MP(SCCGWA SCCGWA, BPCRBVLB BPCRBVLB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBVLB = BPCRBVLB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBVLB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBVLT1 = (BPRBVLT) BPCRBVLB.POINTER;
        IBS.init(SCCGWA, BPRBVLT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVLT1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBVLT1, BPRBVLT);
        BPCRBVLB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBVLB.INFO.FUNC == 'B') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLB.INFO.FUNC == 'F') {
            B040_STARTBR_FIRST_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBVLB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVLT);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBVLT, BPRBVLT1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRBVLT.KEY.BV_CODE.trim().length() > 0 
                && BPRBVLT.KEY.BR != 0) {
            T000_STARTBR_BV_BR();
            if (pgmRtn) return;
        } else if (BPRBVLT.KEY.BV_CODE.trim().length() > 0 
                && BPRBVLT.KEY.BR == 0) {
            T000_STARTBR_BV_CODE();
            if (pgmRtn) return;
        } else if (BPRBVLT.KEY.BV_CODE.trim().length() == 0 
                && BPRBVLT.KEY.BR != 0) {
            T000_STARTBR_BR();
            if (pgmRtn) return;
        } else if (BPRBVLT.KEY.BV_CODE.trim().length() == 0 
                && BPRBVLT.KEY.BR == 0) {
            T000_STARTBR_ALL();
            if (pgmRtn) return;
    }
    public void B040_STARTBR_FIRST_PROC() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        BPTBVLT_RD.where = "BV_CODE = :BPRBVLT.KEY.BV_CODE";
        BPTBVLT_RD.fst = true;
        IBS.READ(SCCGWA, BPRBVLT, this, BPTBVLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "123");
        CEP.TRC(SCCGWA, BPCRBVLB.RETURN_INFO);
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRBVLT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRBVLT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BV_BR() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp = new DBParm();
        BPTBVLT_BR.rp.TableName = "BPTBVLT";
        BPTBVLT_BR.rp.where = "BR = :BPRBVLT.KEY.BR "
            + "AND BV_CODE = :BPRBVLT.KEY.BV_CODE";
        BPTBVLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVLT, this, BPTBVLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BV_CODE() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp = new DBParm();
        BPTBVLT_BR.rp.TableName = "BPTBVLT";
        BPTBVLT_BR.rp.where = "BV_CODE = :BPRBVLT.KEY.BV_CODE";
        BPTBVLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVLT, this, BPTBVLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "123");
        CEP.TRC(SCCGWA, BPCRBVLB.RETURN_INFO);
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp = new DBParm();
        BPTBVLT_BR.rp.TableName = "BPTBVLT";
        BPTBVLT_BR.rp.where = "BR = :BPRBVLT.KEY.BR";
        BPTBVLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVLT, this, BPTBVLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_ALL() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp = new DBParm();
        BPTBVLT_BR.rp.TableName = "BPTBVLT";
        BPTBVLT_BR.rp.where = "BR >= :BPRBVLT.KEY.BR";
        BPTBVLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVLT, this, BPTBVLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRBVLT, this, BPTBVLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTBVLT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBVLB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBVLB = ");
            CEP.TRC(SCCGWA, BPCRBVLB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
