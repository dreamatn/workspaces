package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRAOBL {
    int JIBS_tmp_int;
    DBParm BPTOBL_RD;
    boolean pgmRtn = false;
    BPZRAOBL_WS_VARIABLES WS_VARIABLES = new BPZRAOBL_WS_VARIABLES();
    BPZRAOBL_WS_COND_FLG WS_COND_FLG = new BPZRAOBL_WS_COND_FLG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPROBL BPROBL = new BPROBL();
    int RTCD = 0;
    SCCGWA SCCGWA;
    BPCRAOBL BPCRAOBL;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCRAOBL BPCRAOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRAOBL = BPCRAOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRAOBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BPCRAOBL.PTR);
        IBS.init(SCCGWA, BPROBL);
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
        IBS.CPY2CLS(SCCGWA, LK_REC.substring(0, BPCRAOBL.LEN), BPROBL);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCRAOBL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRAOBL.FUNC);
        if (BPCRAOBL.FUNC == 'C') {
            B001_WRITE_TABLE();
            if (pgmRtn) return;
        } else if (BPCRAOBL.FUNC == 'R') {
            B002_READUPD_TABLE();
            if (pgmRtn) return;
            if (LK_REC == null) LK_REC = "";
            JIBS_tmp_int = LK_REC.length();
            for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPROBL);
            LK_REC = JIBS_tmp_str[0] + LK_REC.substring(BPCRAOBL.LEN);
        } else if (BPCRAOBL.FUNC == 'U') {
            B003_UPDATE_TABLE();
            if (pgmRtn) return;
        } else if (BPCRAOBL.FUNC == 'D') {
            B004_DELETE_TABLE();
            if (pgmRtn) return;
        }
    }
    public void B001_WRITE_TABLE() throws IOException,SQLException,Exception {
        T000_WRITE_BPTOBL();
        if (pgmRtn) return;
    }
    public void B002_READUPD_TABLE() throws IOException,SQLException,Exception {
        T000_READUPD_BPTOBL();
        if (pgmRtn) return;
    }
    public void B003_UPDATE_TABLE() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTOBL();
        if (pgmRtn) return;
    }
    public void B004_DELETE_TABLE() throws IOException,SQLException,Exception {
        T000_DELETE_BPTOBL();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_RD = new DBParm();
        BPTOBL_RD.TableName = "BPTOBL";
        BPTOBL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPROBL, BPTOBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_EXIST, BPCRAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTOBL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_RD = new DBParm();
        BPTOBL_RD.TableName = "BPTOBL";
        BPTOBL_RD.upd = true;
        IBS.READ(SCCGWA, BPROBL, BPTOBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAOBL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_NOTFND, BPCRAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_REWRITE_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_RD = new DBParm();
        BPTOBL_RD.TableName = "BPTOBL";
        IBS.REWRITE(SCCGWA, BPROBL, BPTOBL_RD);
    }
    public void T000_DELETE_BPTOBL() throws IOException,SQLException,Exception {
        BPTOBL_RD = new DBParm();
        BPTOBL_RD.TableName = "BPTOBL";
        BPTOBL_RD.where = "VAL_DATE = :BPROBL.VAL_DATE "
            + "AND INIT_ZERO = :BPROBL.INIT_ZERO";
        IBS.DELETE(SCCGWA, BPROBL, this, BPTOBL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRAOBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRAOBL = ");
            CEP.TRC(SCCGWA, BPCRAOBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
