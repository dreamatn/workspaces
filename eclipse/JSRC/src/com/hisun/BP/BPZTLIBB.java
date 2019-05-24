package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTLIBB {
    brParm BPTCLIB_BR = new brParm();
    DBParm BPTCLIB_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTLIBB";
    String K_TBL_CLIB = "BPTCLIB ";
    int WS_BR = 0;
    char WS_TBL_CLIB_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCGWA SCCGWA;
    BPRCLIB BPRCLIB1;
    BPCTLIBB BPCTLIBB;
    public void MP(SCCGWA SCCGWA, BPCTLIBB BPCTLIBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTLIBB = BPCTLIBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTLIBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCLIB1 = (BPRCLIB) BPCTLIBB.POINTER;
        IBS.init(SCCGWA, BPRCLIB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLIB1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCLIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTLIBB.INFO.FUNC == '1'
            || BPCTLIBB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '3') {
            B060_STARTBR_BY_BR();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '4') {
            B070_STARTBR_ALL_BR();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '5') {
            B080_STARTBR_BY_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '6') {
            B090_STARTBR_ALL_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '7') {
            B100_STARTBR_BY_BR_PLTP();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '8') {
            B110_STARTBR_BY_BR_CCY_PLTP();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRCLIB, BPRCLIB1);
        } else if (BPCTLIBB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == 'U') {
            B040_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRCLIB, BPRCLIB1);
        } else if (BPCTLIBB.INFO.FUNC == 'W') {
            B050_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTLIBB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTLIBB.INFO.FUNC == '1') {
            T000_STARTBR_BPTCLIB_HOLD();
            if (pgmRtn) return;
        } else if (BPCTLIBB.INFO.FUNC == '2') {
            T000_STARTBR_BPTCLIB();
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
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B050_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_BY_BR() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BR();
        if (pgmRtn) return;
    }
    public void B070_STARTBR_ALL_BR() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL_BR();
        if (pgmRtn) return;
    }
    public void B080_STARTBR_BY_BR_CCY() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BR_CCY();
        if (pgmRtn) return;
    }
    public void B090_STARTBR_ALL_BR_CCY() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL_BR_CCY();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_BY_BR_PLTP() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BR_PLTP();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_BY_BR_CCY_PLTP() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BR_CCY_PLTP();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTCLIB_HOLD() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        BPTCLIB_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BY_BR() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR";
        BPTCLIB_BR.rp.order = "BR,CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_ALL_BR() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.order = "CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, BPTCLIB_BR);
    }
    public void T000_STARTBR_BY_BR_CCY() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND CCY = :BPRCLIB.KEY.CCY";
        BPTCLIB_BR.rp.order = "BR,CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_ALL_BR_CCY() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "CCY = :BPRCLIB.KEY.CCY";
        BPTCLIB_BR.rp.order = "BR,CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BY_BR_PLTP() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_TP = :BPRCLIB.PLBOX_TP";
        BPTCLIB_BR.rp.order = "BR,CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BY_BR_CCY_PLTP() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND CCY = :BPRCLIB.KEY.CCY "
            + "AND PLBOX_TP = :BPRCLIB.PLBOX_TP";
        BPTCLIB_BR.rp.order = "BR,CCY,CASH_TYP,PLBOX_TP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLIBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLIB_NOTFND, BPCTLIBB.RC);
        } else {
        }
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void T000_REWRITE_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.REWRITE(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void T000_READUPD_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTLIBB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTLIBB = ");
            CEP.TRC(SCCGWA, BPCTLIBB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
