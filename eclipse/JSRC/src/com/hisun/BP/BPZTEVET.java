package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEVET {
    DBParm BPTEVET_RD;
    boolean pgmRtn = false;
    String TBL_BPTEVET = "BPTEVET ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTEVET BPCTEVET;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPREVET BPREVET;
    public void MP(SCCGWA SCCGWA, BPCTEVET BPCTEVET) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTEVET = BPCTEVET;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEVET return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPREVET = (BPREVET) BPCTEVET.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTEVET.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVET.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPREVET.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_BPTEVET();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEVET_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPREVET.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BPTEVET();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEVET();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEVET();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTEVET.INFO.OPT == 'S') {
            T000_STARTBR_BPTEVET();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.OPT == 'N') {
            T000_READNEXT_BPTEVET();
            if (pgmRtn) return;
        } else if (BPCTEVET.INFO.OPT == 'E') {
            T000_ENDBR_BPTEVET();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVET.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEVET() throws IOException,SQLException,Exception {
        BPTEVET_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEVET_RD.TableName = "BPTEVET1";
        else BPTEVET_RD.TableName = "BPTEVET2";
        BPTEVET_RD.errhdl = true;
        IBS.READ(SCCGWA, BPREVET, BPTEVET_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVET.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVET.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVET;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTEVET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREVET.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPREVET.KEY.SET_NO);
        CEP.TRC(SCCGWA, BPREVET.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, BPREVET.JRN_NO);
        BPTEVET_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEVET_RD.TableName = "BPTEVET1";
        else BPTEVET_RD.TableName = "BPTEVET2";
        IBS.WRITE(SCCGWA, BPREVET, BPTEVET_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVET.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTEVET.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVET;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEVET_UPD() throws IOException,SQLException,Exception {
        BPTEVET_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEVET_RD.TableName = "BPTEVET1";
        else BPTEVET_RD.TableName = "BPTEVET2";
        BPTEVET_RD.upd = true;
        BPTEVET_RD.errhdl = true;
        IBS.READ(SCCGWA, BPREVET, BPTEVET_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVET.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVET.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVET;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTEVET() throws IOException,SQLException,Exception {
        BPTEVET_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEVET_RD.TableName = "BPTEVET1";
        else BPTEVET_RD.TableName = "BPTEVET2";
        IBS.REWRITE(SCCGWA, BPREVET, BPTEVET_RD);
    }
    public void T000_DELETE_BPTEVET() throws IOException,SQLException,Exception {
        BPTEVET_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEVET_RD.TableName = "BPTEVET1";
        else BPTEVET_RD.TableName = "BPTEVET2";
        IBS.DELETE(SCCGWA, BPREVET, BPTEVET_RD);
    }
    public void T000_STARTBR_BPTEVET() throws IOException,SQLException,Exception {
        if (BPCTEVET.INFO.INDEX_FLG == '1') {
            BPCTEVET.BPTEVET_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET1";
            else BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET2";
            BPCTEVET.BPTEVET_BR.rp.where = "AC_DATE = :BPREVET.KEY.AC_DATE "
                + "AND SET_NO = :BPREVET.KEY.SET_NO "
                + "AND PART_NO = :BPREVET.KEY.PART_NO";
            BPCTEVET.BPTEVET_BR.rp.order = "AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPREVET, this, BPCTEVET.BPTEVET_BR);
        } else if (BPCTEVET.INFO.INDEX_FLG == '2') {
            BPCTEVET.BPTEVET_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET1";
            else BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET2";
            BPCTEVET.BPTEVET_BR.rp.where = "AC_DATE = :BPREVET.KEY.AC_DATE "
                + "AND JRN_NO = :BPREVET.JRN_NO "
                + "AND PART_NO = :BPREVET.KEY.PART_NO";
            BPCTEVET.BPTEVET_BR.rp.order = "AC_DATE,JRN_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPREVET, this, BPCTEVET.BPTEVET_BR);
        } else if (BPCTEVET.INFO.INDEX_FLG == '3') {
            BPCTEVET.BPTEVET_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET1";
            else BPCTEVET.BPTEVET_BR.rp.TableName = "BPTEVET2";
            BPCTEVET.BPTEVET_BR.rp.where = "AC_DATE = :BPREVET.KEY.AC_DATE "
                + "AND JRN_NO = :BPREVET.JRN_NO "
                + "AND PART_NO = :BPREVET.KEY.PART_NO";
            BPCTEVET.BPTEVET_BR.rp.order = "AC_DATE,JRN_NO,SET_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPREVET, this, BPCTEVET.BPTEVET_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTEVET.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTEVET() throws IOException,SQLException,Exception {
        BPCTEVET.BPTEVET_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPREVET, this, BPCTEVET.BPTEVET_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEVET.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEVET.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEVET;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTEVET() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPCTEVET.BPTEVET_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTEVET.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTEVET = ");
            CEP.TRC(SCCGWA, BPCTEVET);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
