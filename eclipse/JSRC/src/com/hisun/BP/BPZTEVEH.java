package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEVEH {
    DBParm BPTEVEH_RD;
    brParm BPTEVEH_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTEVEH = "BPTEVEH ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTEVEH BPCTEVEH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPREVEH BPREVEH;
    public void MP(SCCGWA SCCGWA, BPCTEVEH BPCTEVEH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTEVEH = BPCTEVEH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEVEH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPREVEH = (BPREVEH) BPCTEVEH.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTEVEH.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVEH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPREVEH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_BPTEVEH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEVEH_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPREVEH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BPTEVEH();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEVEH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEVEH();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTEVEH.INFO.OPT == 'S') {
            T000_STARTBR_BPTEVEH();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.OPT == 'N') {
            T000_READNEXT_BPTEVEH();
            if (pgmRtn) return;
        } else if (BPCTEVEH.INFO.OPT == 'E') {
            T000_ENDBR_BPTEVEH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVEH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEVEH() throws IOException,SQLException,Exception {
        BPTEVEH_RD = new DBParm();
        BPTEVEH_RD.TableName = "BPTEVEH";
        BPTEVEH_RD.errhdl = true;
        IBS.READ(SCCGWA, BPREVEH, BPTEVEH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVEH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVEH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVEH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTEVEH() throws IOException,SQLException,Exception {
        BPTEVEH_RD = new DBParm();
        BPTEVEH_RD.TableName = "BPTEVEH";
        BPTEVEH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPREVEH, BPTEVEH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVEH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTEVEH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVEH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEVEH_UPD() throws IOException,SQLException,Exception {
        BPTEVEH_RD = new DBParm();
        BPTEVEH_RD.TableName = "BPTEVEH";
        BPTEVEH_RD.upd = true;
        BPTEVEH_RD.errhdl = true;
        IBS.READ(SCCGWA, BPREVEH, BPTEVEH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVEH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVEH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVEH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTEVEH() throws IOException,SQLException,Exception {
        BPTEVEH_RD = new DBParm();
        BPTEVEH_RD.TableName = "BPTEVEH";
        IBS.REWRITE(SCCGWA, BPREVEH, BPTEVEH_RD);
    }
    public void T000_DELETE_BPTEVEH() throws IOException,SQLException,Exception {
        BPTEVEH_RD = new DBParm();
        BPTEVEH_RD.TableName = "BPTEVEH";
        IBS.DELETE(SCCGWA, BPREVEH, BPTEVEH_RD);
    }
    public void T000_STARTBR_BPTEVEH() throws IOException,SQLException,Exception {
        if (BPCTEVEH.INFO.INDEX_FLG == '1') {
            BPTEVEH_BR.rp = new DBParm();
            BPTEVEH_BR.rp.TableName = "BPTEVEH";
            BPTEVEH_BR.rp.where = "AC_DATE = :BPREVEH.KEY.AC_DATE "
                + "AND SET_NO = :BPREVEH.KEY.SET_NO "
                + "AND PART_NO = :BPREVEH.KEY.PART_NO";
            BPTEVEH_BR.rp.order = "AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPREVEH, this, BPTEVEH_BR);
        } else if (BPCTEVEH.INFO.INDEX_FLG == '2') {
            BPTEVEH_BR.rp = new DBParm();
            BPTEVEH_BR.rp.TableName = "BPTEVEH";
            BPTEVEH_BR.rp.where = "AC_DATE = :BPREVEH.KEY.AC_DATE "
                + "AND JRN_NO = :BPREVEH.JRN_NO "
                + "AND PART_NO = :BPREVEH.KEY.PART_NO";
            BPTEVEH_BR.rp.order = "AC_DATE,JRN_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPREVEH, this, BPTEVEH_BR);
        } else if (BPCTEVEH.INFO.INDEX_FLG == '3') {
            BPTEVEH_BR.rp = new DBParm();
            BPTEVEH_BR.rp.TableName = "BPTEVEH";
            BPTEVEH_BR.rp.where = "AC_DATE = :BPREVEH.KEY.AC_DATE "
                + "AND JRN_NO = :BPREVEH.JRN_NO "
                + "AND PART_NO = :BPREVEH.KEY.PART_NO";
            BPTEVEH_BR.rp.order = "AC_DATE,JRN_NO,SET_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPREVEH, this, BPTEVEH_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVEH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTEVEH() throws IOException,SQLException,Exception {
        BPTEVEH_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPREVEH, this, BPTEVEH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVEH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVEH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVEH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTEVEH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEVEH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTEVEH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTEVEH = ");
            CEP.TRC(SCCGWA, BPCTEVEH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
