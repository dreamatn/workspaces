package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMRTD {
    DBParm BPTRTID_RD;
    brParm BPTRTID_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTRTID = "BPTRTID";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRRTID BPRRTID = new BPRRTID();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRMRTD BPCRMRTD;
    BPRRTID BPRRTIDL;
    public void MP(SCCGWA SCCGWA, BPCRMRTD BPCRMRTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMRTD = BPCRMRTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMRTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRMRTD.RC);
        BPRRTIDL = (BPRRTID) BPCRMRTD.INFO.POINTER;
        IBS.init(SCCGWA, BPRRTID);
        IBS.CLONE(SCCGWA, BPRRTIDL, BPRRTID);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRMRTD);
        CEP.TRC(SCCGWA, BPRRTID);
        if (BPCRMRTD.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRRTID, BPRRTIDL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTRTID();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRTID_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTRTID();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRMRTD.INFO.OPT == 'I') {
            T000_READ_BPTRTID_IDX();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTRTID();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTRTID();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRMRTD.INFO.OPT == 'S') {
            T000_STARTBR_BPTRTID();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.OPT == 'N') {
            T000_READNEXT_BPTRTID();
            if (pgmRtn) return;
        } else if (BPCRMRTD.INFO.OPT == 'E') {
            T000_ENDBR_BPTRTID();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_CREATE_BPTRTID() throws IOException,SQLException,Exception {
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        IBS.WRITE(SCCGWA, BPRRTID, BPTRTID_RD);
    }
    public void T000_READ_BPTRTID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRRTID.KEY.ID);
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        IBS.READ(SCCGWA, BPRRTID, BPTRTID_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMRTD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMRTD.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRMRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTID;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRTID_IDX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRRTID.CCY);
        CEP.TRC(SCCGWA, BPRRTID.BASE_TYP);
        CEP.TRC(SCCGWA, BPRRTID.TENOR);
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        BPTRTID_RD.where = "CCY = :BPRRTID.CCY "
            + "AND BASE_TYP = :BPRRTID.BASE_TYP "
            + "AND TENOR = :BPRRTID.TENOR";
        IBS.READ(SCCGWA, BPRRTID, this, BPTRTID_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMRTD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMRTD.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRMRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTID;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRTID_UPD() throws IOException,SQLException,Exception {
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        BPTRTID_RD.upd = true;
        IBS.READ(SCCGWA, BPRRTID, BPTRTID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMRTD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMRTD.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRMRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTID;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTRTID() throws IOException,SQLException,Exception {
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        IBS.REWRITE(SCCGWA, BPRRTID, BPTRTID_RD);
    }
    public void T000_DELETE_BPTRTID() throws IOException,SQLException,Exception {
        BPTRTID_RD = new DBParm();
        BPTRTID_RD.TableName = "BPTRTID";
        IBS.DELETE(SCCGWA, BPRRTID, BPTRTID_RD);
    }
    public void T000_STARTBR_BPTRTID() throws IOException,SQLException,Exception {
        BPTRTID_BR.rp = new DBParm();
        BPTRTID_BR.rp.TableName = "BPTRTID";
        IBS.STARTBR(SCCGWA, BPRRTID, BPTRTID_BR);
    }
    public void T000_READNEXT_BPTRTID() throws IOException,SQLException,Exception {
        BPTRTID_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRRTID, this, BPTRTID_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMRTD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMRTD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTID;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTRTID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRTID_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMRTD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMRTD = ");
            CEP.TRC(SCCGWA, BPCRMRTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
