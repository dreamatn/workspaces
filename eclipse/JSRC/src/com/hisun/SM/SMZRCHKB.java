package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZRCHKB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTDCHK_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "SMZRCHKB";
    String K_TBL_DCHK = "BPTDCHK ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDCHK BPRDCHK = new BPRDCHK();
    SCCGWA SCCGWA;
    SMCTCHKB SMCTCHKB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRDCHK BPRDCHK1;
    public void MP(SCCGWA SCCGWA, SMCTCHKB SMCTCHKB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCTCHKB = SMCTCHKB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZRCHKB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRDCHK1 = (BPRDCHK) SMCTCHKB.INFO.POINTER;
        IBS.init(SCCGWA, BPRDCHK);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDCHK1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRDCHK1, BPRDCHK);
        IBS.init(SCCGWA, SMCTCHKB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCTCHKB.INFO.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (SMCTCHKB.INFO.FUNC == 'A') {
            B020_STARTBR_ALL_PROC();
            if (pgmRtn) return;
        } else if (SMCTCHKB.INFO.FUNC == 'R') {
            B030_READNEXT_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRDCHK, BPRDCHK1);
        } else if (SMCTCHKB.INFO.FUNC == 'E') {
            B040_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCTCHKB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B020_STARTBR_ALL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B030_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTDCHK();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_ALL_BPTDCHK() throws IOException,SQLException,Exception {
        BPTDCHK_BR.rp = new DBParm();
        BPTDCHK_BR.rp.TableName = "BPTDCHK";
        BPTDCHK_BR.rp.order = "NAME, FMT";
        IBS.STARTBR(SCCGWA, BPRDCHK, BPTDCHK_BR);
    }
    public void T000_STARTBR_BPTDCHK() throws IOException,SQLException,Exception {
        BPTDCHK_BR.rp = new DBParm();
        BPTDCHK_BR.rp.TableName = "BPTDCHK";
        BPTDCHK_BR.rp.where = "NAME LIKE :BPRDCHK.KEY.NAME "
            + "AND FMT LIKE :BPRDCHK.KEY.FMT";
        BPTDCHK_BR.rp.order = "NAME";
        IBS.STARTBR(SCCGWA, BPRDCHK, this, BPTDCHK_BR);
    }
    public void T000_READNEXT_BPTDCHK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRDCHK, this, BPTDCHK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SMCTCHKB.INFO.FUNC = 'E';
        } else {
        }
    }
    public void T000_ENDBR_BPTDCHK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTDCHK_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
