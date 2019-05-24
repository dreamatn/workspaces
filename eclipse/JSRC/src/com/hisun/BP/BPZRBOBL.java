package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBOBL {
    brParm BPTOBL_BR = new brParm();
    DBParm BPTOBL_RD;
    BPZRBOBL_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZRBOBL_WS_TEMP_VARIABLE();
    char WS_OBL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPROBL BPROBL = new BPROBL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCRBOBL BPCRBOBL;
    BPROBL BPROBL1;
    public void MP(SCCGWA SCCGWA, BPCRBOBL BPCRBOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBOBL = BPCRBOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRBOBL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPROBL1 = (BPROBL) BPCRBOBL.PTR;
        IBS.init(SCCGWA, BPROBL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPROBL1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPROBL);
        IBS.init(SCCGWA, BPCRBOBL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBOBL.FUN == 'S') {
            B001_STARTBR_TABLE();
        } else if (BPCRBOBL.FUN == 'P') {
            B003_STARTBR_TABLE_PART();
        } else if (BPCRBOBL.FUN == 'T') {
            B003_STARTBR_TABLE_TYPE();
        } else if (BPCRBOBL.FUN == 'N') {
            B003_STARTBR_TABLE_NAME();
        } else if (BPCRBOBL.FUN == 'R') {
            B004_READNEXT_TABLE();
            IBS.CLONE(SCCGWA, BPROBL, BPROBL1);
        } else if (BPCRBOBL.FUN == 'E') {
            B005_ENDBR_TABLE();
        } else if (BPCRBOBL.FUN == 'U') {
            B006_STARTBR_TABLE_UPD();
        } else if (BPCRBOBL.FUN == 'D') {
            B007_DELETE_TABLE();
        }
    }
    public void B001_STARTBR_TABLE() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTOBL();
    }
    public void B003_STARTBR_TABLE_PART() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTOBL_PART();
    }
    public void B003_STARTBR_TABLE_TYPE() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTOBL_TYPE();
    }
    public void B003_STARTBR_TABLE_NAME() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTOBL_NAME();
    }
    public void B004_READNEXT_TABLE() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTOBL();
    }
    public void B005_ENDBR_TABLE() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTOBL();
    }
    public void B006_STARTBR_TABLE_UPD() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTOBL_UPD();
    }
    public void B007_DELETE_TABLE() throws IOException,SQLException,Exception {
        T000_DELETE_BPTOBL();
    }
    public void T000_STARTBR_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_BR.rp = new DBParm();
        BPTOBL_BR.rp.TableName = "BPTOBL";
        BPTOBL_BR.rp.where = "TYPE = :BPROBL.KEY.TYPE "
            + "AND NAME LIKE :BPROBL.KEY.NAME";
        BPTOBL_BR.rp.order = "NAME";
        IBS.STARTBR(SCCGWA, BPROBL, this, BPTOBL_BR);
    }
    public void T000_STARTBR_BPTOBL_TYPE() throws IOException,SQLException,Exception {
        BPTOBL_BR.rp = new DBParm();
        BPTOBL_BR.rp.TableName = "BPTOBL";
        BPTOBL_BR.rp.where = "TYPE = :BPROBL.KEY.TYPE";
        BPTOBL_BR.rp.order = "NAME";
        IBS.STARTBR(SCCGWA, BPROBL, this, BPTOBL_BR);
    }
    public void T000_STARTBR_BPTOBL_NAME() throws IOException,SQLException,Exception {
        BPTOBL_BR.rp = new DBParm();
        BPTOBL_BR.rp.TableName = "BPTOBL";
        BPTOBL_BR.rp.where = "NAME = :BPROBL.KEY.NAME";
        BPTOBL_BR.rp.order = "TYPE";
        IBS.STARTBR(SCCGWA, BPROBL, this, BPTOBL_BR);
    }
    public void T000_STARTBR_BPTOBL_PART() throws IOException,SQLException,Exception {
        BPTOBL_BR.rp = new DBParm();
        BPTOBL_BR.rp.TableName = "BPTOBL";
        BPTOBL_BR.rp.where = "TYPE = :BPROBL.KEY.TYPE "
            + "AND NAME = :BPROBL.KEY.NAME "
            + "AND ( ( MIN_SEQ_NO < :BPROBL.KEY.MIN_SEQ_NO "
            + "AND MAX_SEQ_NO >= :BPROBL.KEY.MIN_SEQ_NO ) "
            + "OR ( MIN_SEQ_NO >= :BPROBL.KEY.MIN_SEQ_NO "
            + "AND MIN_SEQ_NO <= :BPROBL.MAX_SEQ_NO ) )";
        BPTOBL_BR.rp.order = "MIN_SEQ_NO";
        IBS.STARTBR(SCCGWA, BPROBL, this, BPTOBL_BR);
    }
    public void T000_STARTBR_BPTOBL_UPD() throws IOException,SQLException,Exception {
        BPTOBL_BR.rp = new DBParm();
        BPTOBL_BR.rp.TableName = "BPTOBL";
        BPTOBL_BR.rp.upd = true;
        BPTOBL_BR.rp.where = "TYPE = :BPROBL.KEY.TYPE "
            + "AND NAME = :BPROBL.KEY.NAME";
        IBS.STARTBR(SCCGWA, BPROBL, this, BPTOBL_BR);
    }
    public void T000_READNEXT_BPTOBL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPROBL, this, BPTOBL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOBL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBOBL.RETURN_INFO = 'N';
            BPCRBOBL.FUN = 'E';
        } else {
        }
    }
    public void T000_ENDBR_BPTOBL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTOBL_BR);
    }
    public void T000_DELETE_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_RD = new DBParm();
        BPTOBL_RD.TableName = "BPTOBL";
        IBS.DELETE(SCCGWA, BPROBL, BPTOBL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBOBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBOBL = ");
            CEP.TRC(SCCGWA, BPCRBOBL);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
