package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDEVM {
    DBParm BPTIDEV_RD;
    brParm BPTIDEV_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTIDEV = "BPTIDEV";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRIDEV BPRIDEV = new BPRIDEV();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRDEVM BPCRDEVM;
    BPRIDEV BPRIDEV1;
    public void MP(SCCGWA SCCGWA, BPCRDEVM BPCRDEVM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDEVM = BPCRDEVM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRDEVM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRDEVM.RC);
        BPRIDEV1 = (BPRIDEV) BPCRDEVM.INFO.POINTER;
        IBS.init(SCCGWA, BPRIDEV);
        IBS.CLONE(SCCGWA, BPRIDEV1, BPRIDEV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDEVM.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRDEVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRIDEV, BPRIDEV1);
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCRDEVM.INFO.FUNC != 'I' 
            && BPCRDEVM.INFO.FUNC != 'A' 
            && BPCRDEVM.INFO.FUNC != 'U' 
            && BPCRDEVM.INFO.FUNC != 'D' 
            && BPCRDEVM.INFO.FUNC != 'B') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRDEVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTIDEV();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTIDEV_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTIDEV();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTIDEV();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTIDEV();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRDEVM.INFO.OPT_A == 'S') {
            T000_STARTBR_BPTIDEV();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.OPT_A == 'N') {
            T000_READNEXT_BPTIDEV();
            if (pgmRtn) return;
        } else if (BPCRDEVM.INFO.OPT_A == 'E') {
            T000_ENDBR_BPTIDEV();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRDEVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTIDEV() throws IOException,SQLException,Exception {
        BPTIDEV_RD = new DBParm();
        BPTIDEV_RD.TableName = "BPTIDEV";
        BPTIDEV_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRIDEV, BPTIDEV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDEVM.RETURN_INFO = 'F';
            BPCRDEVM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDEVM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRDEVM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIDEV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTIDEV() throws IOException,SQLException,Exception {
        BPTIDEV_RD = new DBParm();
        BPTIDEV_RD.TableName = "BPTIDEV";
        BPTIDEV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRIDEV, BPTIDEV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDEVM.RETURN_INFO = 'F';
            BPCRDEVM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRDEVM.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCRDEVM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIDEV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTIDEV_UPD() throws IOException,SQLException,Exception {
        BPTIDEV_RD = new DBParm();
        BPTIDEV_RD.TableName = "BPTIDEV";
        BPTIDEV_RD.upd = true;
        IBS.READ(SCCGWA, BPRIDEV, BPTIDEV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDEVM.RETURN_INFO = 'F';
            BPCRDEVM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDEVM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRDEVM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIDEV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTIDEV() throws IOException,SQLException,Exception {
        BPTIDEV_RD = new DBParm();
        BPTIDEV_RD.TableName = "BPTIDEV";
        IBS.REWRITE(SCCGWA, BPRIDEV, BPTIDEV_RD);
    }
    public void T000_DELETE_BPTIDEV() throws IOException,SQLException,Exception {
        BPTIDEV_RD = new DBParm();
        BPTIDEV_RD.TableName = "BPTIDEV";
        IBS.DELETE(SCCGWA, BPRIDEV, BPTIDEV_RD);
    }
    public void T000_STARTBR_BPTIDEV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRIDEV.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRIDEV.KEY.BR);
        CEP.TRC(SCCGWA, BPRIDEV.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIDEV.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIDEV.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRIDEV.FORMAT);
        CEP.TRC(SCCGWA, BPRIDEV);
        if (BPRIDEV.KEY.TYPE == ' ') {
            BPRIDEV.KEY.TYPE = ALL.charAt(0);
        }
        BPTIDEV_BR.rp = new DBParm();
        BPTIDEV_BR.rp.TableName = "BPTIDEV";
        BPTIDEV_BR.rp.where = "TYPE LIKE :BPRIDEV.KEY.TYPE "
            + "AND BR >= :BPRIDEV.KEY.BR "
            + "AND CCY >= :BPRIDEV.KEY.CCY "
            + "AND BASE_TYP >= :BPRIDEV.KEY.BASE_TYP "
            + "AND TENOR >= :BPRIDEV.KEY.TENOR "
            + "AND FORMAT >= :BPRIDEV.FORMAT";
        IBS.STARTBR(SCCGWA, BPRIDEV, this, BPTIDEV_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_BPTIDEV() throws IOException,SQLException,Exception {
        BPTIDEV_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRIDEV, this, BPTIDEV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDEVM.RETURN_INFO = 'F';
            BPCRDEVM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDEVM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIDEV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTIDEV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTIDEV_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRDEVM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRDEVM = ");
            CEP.TRC(SCCGWA, BPCRDEVM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
