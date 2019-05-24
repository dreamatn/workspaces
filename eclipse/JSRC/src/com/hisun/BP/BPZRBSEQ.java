package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBSEQ {
    brParm BPTSEQ_BR = new brParm();
    char WS_EOF = ' ';
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSEQ BPRSEQ = new BPRSEQ();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRBSEQ BPCRBSEQ;
    BPRSEQ BPRSEQ1;
    public void MP(SCCGWA SCCGWA, BPCRBSEQ BPCRBSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBSEQ = BPCRBSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRBSEQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSEQ1 = (BPRSEQ) BPCRBSEQ.PTR;
        IBS.init(SCCGWA, BPRSEQ);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSEQ1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRSEQ);
        IBS.init(SCCGWA, BPCRBSEQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBSEQ.FUN == 'S') {
            CEP.TRC(SCCGWA, "LIULAN1");
            B001_STARTBR_TABLE();
        } else if (BPCRBSEQ.FUN == 'A') {
            CEP.TRC(SCCGWA, "LIULAN2");
            B002_STARTBR_TABLE_ALL();
        } else if (BPCRBSEQ.FUN == 'T') {
            CEP.TRC(SCCGWA, "LIULAN3");
            B002_STARTBR_TABLE_TYPE();
        } else if (BPCRBSEQ.FUN == 'N') {
            CEP.TRC(SCCGWA, "LIULAN4");
            B002_STARTBR_TABLE_NAME();
        } else if (BPCRBSEQ.FUN == 'R') {
            CEP.TRC(SCCGWA, "LIULAN5");
            B003_READNEXT_TABLE();
            IBS.CLONE(SCCGWA, BPRSEQ, BPRSEQ1);
        } else if (BPCRBSEQ.FUN == 'E') {
            CEP.TRC(SCCGWA, "LIULAN6");
            B004_ENDBR_TABLE();
        }
    }
    public void B001_STARTBR_TABLE() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSEQ();
    }
    public void B002_STARTBR_TABLE_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSEQ_ALL();
    }
    public void B002_STARTBR_TABLE_TYPE() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSEQ_TYPE();
    }
    public void B002_STARTBR_TABLE_NAME() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSEQ_NAME();
    }
    public void B003_READNEXT_TABLE() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTSEQ();
    }
    public void B004_ENDBR_TABLE() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTSEQ();
    }
    public void T000_STARTBR_BPTSEQ_ALL() throws IOException,SQLException,Exception {
        BPTSEQ_BR.rp = new DBParm();
        BPTSEQ_BR.rp.TableName = "BPTSEQ";
        BPTSEQ_BR.rp.order = "TYPE , NAME";
        IBS.STARTBR(SCCGWA, BPRSEQ, BPTSEQ_BR);
    }
    public void T000_STARTBR_BPTSEQ() throws IOException,SQLException,Exception {
        BPTSEQ_BR.rp = new DBParm();
        BPTSEQ_BR.rp.TableName = "BPTSEQ";
        BPTSEQ_BR.rp.where = "TYPE = :BPRSEQ.KEY.TYPE "
            + "AND NAME LIKE :BPRSEQ.KEY.NAME";
        BPTSEQ_BR.rp.order = "TYPE , NAME";
        IBS.STARTBR(SCCGWA, BPRSEQ, this, BPTSEQ_BR);
    }
    public void T000_STARTBR_BPTSEQ_TYPE() throws IOException,SQLException,Exception {
        BPTSEQ_BR.rp = new DBParm();
        BPTSEQ_BR.rp.TableName = "BPTSEQ";
        BPTSEQ_BR.rp.where = "TYPE = :BPRSEQ.KEY.TYPE";
        BPTSEQ_BR.rp.order = "NAME";
        IBS.STARTBR(SCCGWA, BPRSEQ, this, BPTSEQ_BR);
    }
    public void T000_STARTBR_BPTSEQ_NAME() throws IOException,SQLException,Exception {
        BPTSEQ_BR.rp = new DBParm();
        BPTSEQ_BR.rp.TableName = "BPTSEQ";
        BPTSEQ_BR.rp.where = "NAME = :BPRSEQ.KEY.NAME";
        BPTSEQ_BR.rp.order = "TYPE";
        IBS.STARTBR(SCCGWA, BPRSEQ, this, BPTSEQ_BR);
    }
    public void T000_READNEXT_BPTSEQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRSEQ, this, BPTSEQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBSEQ.FUN = 'E';
        } else {
        }
    }
    public void T000_ENDBR_BPTSEQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTSEQ_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBSEQ = ");
            CEP.TRC(SCCGWA, BPCRBSEQ);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
