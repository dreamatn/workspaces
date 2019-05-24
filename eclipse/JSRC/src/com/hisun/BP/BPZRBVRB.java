package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBVRB {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBVRG_RD;
    brParm BPTBVRG_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBVRB";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBVRG BPRBVRG = new BPRBVRG();
    SCCGWA SCCGWA;
    BPCRBVRB BPCRBVRB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRBVRG BPRBVRG1;
    public void MP(SCCGWA SCCGWA, BPCRBVRB BPCRBVRB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBVRB = BPCRBVRB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBVRB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBVRG1 = (BPRBVRG) BPCRBVRB.POINTER;
        IBS.init(SCCGWA, BPRBVRG);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVRG1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBVRG1, BPRBVRG);
        BPCRBVRB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBVRB.INFO.FUNC == 'B') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVRB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVRB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVRB.INFO.FUNC == 'F') {
            B040_STARTBR_FIRST_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBVRB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVRG);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBVRG, BPRBVRG1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRBVRG.KEY.BV_CODE.trim().length() > 0 
                && BPRBVRG.KEY.BR != 0) {
            T000_STARTBR_BV_BR();
            if (pgmRtn) return;
        } else if (BPRBVRG.KEY.BV_CODE.trim().length() > 0 
                && BPRBVRG.KEY.BR == 0) {
            T000_STARTBR_BV_CODE();
            if (pgmRtn) return;
        } else if (BPRBVRG.KEY.BV_CODE.trim().length() == 0 
                && BPRBVRG.KEY.BR != 0) {
            T000_STARTBR_BR();
            if (pgmRtn) return;
        } else if (BPRBVRG.KEY.BV_CODE.trim().length() == 0 
                && BPRBVRG.KEY.BR == 0) {
            T000_STARTBR_ALL();
            if (pgmRtn) return;
    }
    public void B040_STARTBR_FIRST_PROC() throws IOException,SQLException,Exception {
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        BPTBVRG_RD.where = "BV_CODE = :BPRBVRG.KEY.BV_CODE";
        BPTBVRG_RD.fst = true;
        IBS.READ(SCCGWA, BPRBVRG, this, BPTBVRG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "123");
        CEP.TRC(SCCGWA, BPCRBVRB.RETURN_INFO);
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRBVRG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        CEP.TRC(SCCGWA, BPRBVRG.KEY.BR);
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRBVRG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BV_BR() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp = new DBParm();
        BPTBVRG_BR.rp.TableName = "BPTBVRG";
        BPTBVRG_BR.rp.where = "BR = :BPRBVRG.KEY.BR "
            + "AND BV_CODE = :BPRBVRG.KEY.BV_CODE";
        BPTBVRG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVRG, this, BPTBVRG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BV_CODE() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp = new DBParm();
        BPTBVRG_BR.rp.TableName = "BPTBVRG";
        BPTBVRG_BR.rp.where = "BV_CODE = :BPRBVRG.KEY.BV_CODE";
        BPTBVRG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVRG, this, BPTBVRG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "123");
        CEP.TRC(SCCGWA, BPCRBVRB.RETURN_INFO);
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp = new DBParm();
        BPTBVRG_BR.rp.TableName = "BPTBVRG";
        BPTBVRG_BR.rp.where = "BR = :BPRBVRG.KEY.BR";
        BPTBVRG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVRG, this, BPTBVRG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_ALL() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp = new DBParm();
        BPTBVRG_BR.rp.TableName = "BPTBVRG";
        BPTBVRG_BR.rp.where = "BR >= :BPRBVRG.KEY.BR";
        BPTBVRG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBVRG, this, BPTBVRG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRBVRG, this, BPTBVRG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVRB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVRB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTBVRG_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBVRB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBVRB = ");
            CEP.TRC(SCCGWA, BPCRBVRB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
