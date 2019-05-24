package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTCALR {
    DBParm BPTCALR_RD;
    brParm BPTCALR_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTCALR = "BPTCALR ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCALR BPRCALR = new BPRCALR();
    SCCGWA SCCGWA;
    BPCTCALR BPCTCALR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRCALR BPRCALR1;
    public void MP(SCCGWA SCCGWA, BPCTCALR BPCTCALR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCALR = BPCTCALR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTCALR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRCALR1 = (BPRCALR) BPCTCALR.INFO.POINTER;
        IBS.init(SCCGWA, BPRCALR);
        WS_REC_LEN = 56;
        IBS.CLONE(SCCGWA, BPRCALR1, BPRCALR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTCALR.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTCALR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCALR, BPRCALR1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCALR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCALR_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCALR();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCALR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCALR();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTCALR.INFO.OPT == 'S') {
            T000_STARTBR_BPTCALR();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.OPT == 'N') {
            T000_READNEXT_BPTCALR();
            if (pgmRtn) return;
        } else if (BPCTCALR.INFO.OPT == 'E') {
            T000_ENDBR_BPTCALR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTCALR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCALR() throws IOException,SQLException,Exception {
        BPTCALR_RD = new DBParm();
        BPTCALR_RD.TableName = "BPTCALR";
        BPTCALR_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRCALR, BPTCALR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCALR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCALR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCALR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTCALR() throws IOException,SQLException,Exception {
        BPTCALR_RD = new DBParm();
        BPTCALR_RD.TableName = "BPTCALR";
        BPTCALR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCALR, BPTCALR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCALR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTCALR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCALR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCALR_UPD() throws IOException,SQLException,Exception {
        BPTCALR_RD = new DBParm();
        BPTCALR_RD.TableName = "BPTCALR";
        BPTCALR_RD.upd = true;
        IBS.READ(SCCGWA, BPRCALR, BPTCALR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCALR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCALR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCALR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTCALR() throws IOException,SQLException,Exception {
        BPTCALR_RD = new DBParm();
        BPTCALR_RD.TableName = "BPTCALR";
        IBS.REWRITE(SCCGWA, BPRCALR, BPTCALR_RD);
    }
    public void T000_DELETE_BPTCALR() throws IOException,SQLException,Exception {
        BPTCALR_RD = new DBParm();
        BPTCALR_RD.TableName = "BPTCALR";
        IBS.DELETE(SCCGWA, BPRCALR, BPTCALR_RD);
    }
    public void T000_STARTBR_BPTCALR() throws IOException,SQLException,Exception {
        if (BPCTCALR.INFO.INDEX_FLG == '1') {
            BPTCALR_BR.rp = new DBParm();
            BPTCALR_BR.rp.TableName = "BPTCALR";
            BPTCALR_BR.rp.where = "CODE >= :BPRCALR.KEY.CODE "
                + "AND YEAR >= :BPRCALR.KEY.YEAR";
            BPTCALR_BR.rp.order = "CODE,YEAR";
            IBS.STARTBR(SCCGWA, BPRCALR, this, BPTCALR_BR);
        } else if (BPCTCALR.INFO.INDEX_FLG == '2') {
            BPTCALR_BR.rp = new DBParm();
            BPTCALR_BR.rp.TableName = "BPTCALR";
            BPTCALR_BR.rp.where = "BASE_CODE >= :BPRCALR.BASE_CODE "
                + "AND BASE_YEAR >= :BPRCALR.BASE_YEAR";
            BPTCALR_BR.rp.order = "BASE_CODE,BASE_YEAR";
            IBS.STARTBR(SCCGWA, BPRCALR, this, BPTCALR_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTCALR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTCALR() throws IOException,SQLException,Exception {
        BPTCALR_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCALR, this, BPTCALR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCALR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCALR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCALR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCALR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCALR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCALR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTCALR = ");
            CEP.TRC(SCCGWA, BPCTCALR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
