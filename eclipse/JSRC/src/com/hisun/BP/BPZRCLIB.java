package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCLIB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCLIB_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRCLIB";
    String K_TBL_CLIB = "BPTCLIB ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCGWA SCCGWA;
    BPCRCLIB BPCRCLIB;
    BPRCLIB BPRCLIB1;
    public void MP(SCCGWA SCCGWA, BPCRCLIB BPCRCLIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCLIB = BPCRCLIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCLIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCLIB1 = (BPRCLIB) BPCRCLIB.INFO.POINTER;
        IBS.init(SCCGWA, BPRCLIB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLIB1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCLIB1, BPRCLIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCLIB.INFO.FUNC == '1'
            || BPCRCLIB.INFO.FUNC == '2') {
            CEP.TRC(SCCGWA, "STARTBR-01");
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCLIB.INFO.FUNC == 'N') {
            CEP.TRC(SCCGWA, "READNEXT");
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCLIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCLIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLIB);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCLIB, BPRCLIB1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRCLIB.INFO.FUNC == '1') {
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            T000_STARTBR_BPTCLIB_01();
            if (pgmRtn) return;
        } else if (BPCRCLIB.INFO.FUNC == '2') {
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            T000_STARTBR_BPTCLIB_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTCLIB();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTCLIB_01() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO LIKE :BPRCLIB.KEY.PLBOX_NO";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BPTCLIB_02() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR";
        BPTCLIB_BR.rp.order = "BR";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCLIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCLIB.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLIB_NOTFND, BPCRCLIB.RC);
        } else {
        }
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCLIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCLIB = ");
            CEP.TRC(SCCGWA, BPCRCLIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
