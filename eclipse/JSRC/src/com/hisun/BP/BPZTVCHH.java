package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTVCHH {
    DBParm BPTVCHH_RD;
    brParm BPTVCHH_BR = new brParm();
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    int WS_STARTD = 0;
    int WS_ENDD = 0;
    String TBL_BPTVCHH = "BPTVCHH ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRVCHH BPRVCHH = new BPRVCHH();
    SCCGWA SCCGWA;
    BPCTVCHH BPCTVCHH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVCHH BPRVCHI;
    public void MP(SCCGWA SCCGWA, BPCTVCHH BPCTVCHH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTVCHH = BPCTVCHH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTVCHH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVCHI = (BPRVCHH) BPCTVCHH.INFO.POINTER;
        IBS.init(SCCGWA, BPRVCHH);
        WS_REC_LEN = 1565;
        IBS.CLONE(SCCGWA, BPRVCHI, BPRVCHH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTVCHH.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRVCHH, BPRVCHI);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPRVCHH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_BPTVCHH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCHH_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPRVCHH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BPTVCHH();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCHH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTVCHH();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STARTD = BPCTVCHH.DATA.START_DT;
        WS_ENDD = BPCTVCHH.DATA.END_DT;
        if (BPCTVCHH.INFO.OPT == 'S') {
            T000_STARTBR_BPTVCHH();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.OPT == 'N') {
            T000_READNEXT_BPTVCHH();
            if (pgmRtn) return;
        } else if (BPCTVCHH.INFO.OPT == 'E') {
            T000_ENDBR_BPTVCHH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_RD = new DBParm();
        BPTVCHH_RD.TableName = "BPTVCHH";
        BPTVCHH_RD.where = "AC_DATE = :BPRVCHH.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCHH.KEY.SET_NO "
            + "AND SET_SEQ = :BPRVCHH.KEY.SET_SEQ";
        BPTVCHH_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRVCHH, this, BPTVCHH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_RD = new DBParm();
        BPTVCHH_RD.TableName = "BPTVCHH";
        BPTVCHH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRVCHH, BPTVCHH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTVCHH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVCHH_UPD() throws IOException,SQLException,Exception {
        BPTVCHH_RD = new DBParm();
        BPTVCHH_RD.TableName = "BPTVCHH";
        BPTVCHH_RD.upd = true;
        BPTVCHH_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRVCHH, BPTVCHH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_RD = new DBParm();
        BPTVCHH_RD.TableName = "BPTVCHH";
        IBS.REWRITE(SCCGWA, BPRVCHH, BPTVCHH_RD);
    }
    public void T000_DELETE_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_RD = new DBParm();
        BPTVCHH_RD.TableName = "BPTVCHH";
        IBS.DELETE(SCCGWA, BPRVCHH, BPTVCHH_RD);
    }
    public void T000_STARTBR_BPTVCHH() throws IOException,SQLException,Exception {
        if (BPCTVCHH.INFO.INDEX_FLG == '1') {
            BPTVCHH_BR.rp = new DBParm();
            BPTVCHH_BR.rp.TableName = "BPTVCHH";
            BPTVCHH_BR.rp.where = "AC_DATE = :BPRVCHH.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHH.KEY.SET_NO";
            BPTVCHH_BR.rp.order = "PART_NO,AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        } else if (BPCTVCHH.INFO.INDEX_FLG == '2') {
            if (BPRVCHH.KEY.SET_NO == null) BPRVCHH.KEY.SET_NO = "";
            JIBS_tmp_int = BPRVCHH.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHH.KEY.SET_NO += " ";
            if (BPRVCHH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHH.KEY.PART_NO = 0;
            else BPRVCHH.KEY.PART_NO = Integer.parseInt(BPRVCHH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
            BPTVCHH_BR.rp = new DBParm();
            BPTVCHH_BR.rp.TableName = "BPTVCHH";
            BPTVCHH_BR.rp.where = "AC_DATE = :BPRVCHH.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHH.KEY.SET_NO "
                + "AND PART_NO = :BPRVCHH.KEY.PART_NO";
            IBS.STARTBR(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTVCHH() throws IOException,SQLException,Exception {
        BPTVCHH_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHH.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTVCHH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTVCHH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTVCHH = ");
            CEP.TRC(SCCGWA, BPCTVCHH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
