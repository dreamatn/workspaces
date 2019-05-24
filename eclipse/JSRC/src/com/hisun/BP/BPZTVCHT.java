package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTVCHT {
    DBParm BPTVCHT_RD;
    brParm BPTVCHT_BR = new brParm();
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String TBL_BPTVCHT = "BPTVCHT ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRVCHT BPRVCHT = new BPRVCHT();
    SCCGWA SCCGWA;
    BPCTVCHT BPCTVCHT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVCHT BPRVCHT1;
    public void MP(SCCGWA SCCGWA, BPCTVCHT BPCTVCHT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTVCHT = BPCTVCHT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTVCHT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVCHT1 = (BPRVCHT) BPCTVCHT.INFO.POINTER;
        IBS.init(SCCGWA, BPRVCHT);
        WS_REC_LEN = 1562;
        IBS.CLONE(SCCGWA, BPRVCHT1, BPRVCHT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTVCHT.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRVCHT, BPRVCHT1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPRVCHT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_BPTVCHT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCHT_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPRVCHT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BPTVCHT();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCHT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTVCHT();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTVCHT.INFO.OPT == 'S') {
            T000_STARTBR_BPTVCHT();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.OPT == 'N') {
            T000_READNEXT_BPTVCHT();
            if (pgmRtn) return;
        } else if (BPCTVCHT.INFO.OPT == 'E') {
            T000_ENDBR_BPTVCHT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        BPTVCHT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRVCHT, BPTVCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTVCHT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVCHT.CHQ_NO);
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        IBS.WRITE(SCCGWA, BPRVCHT, BPTVCHT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTVCHT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVCHT_UPD() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        BPTVCHT_RD.upd = true;
        BPTVCHT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRVCHT, BPTVCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        IBS.REWRITE(SCCGWA, BPRVCHT, BPTVCHT_RD);
    }
    public void T000_DELETE_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        IBS.DELETE(SCCGWA, BPRVCHT, BPTVCHT_RD);
    }
    public void T000_STARTBR_BPTVCHT() throws IOException,SQLException,Exception {
        if (BPCTVCHT.INFO.INDEX_FLG == '1') {
            BPTVCHT_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
            else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
            BPTVCHT_BR.rp.where = "AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHT.KEY.SET_NO";
            BPTVCHT_BR.rp.order = "PART_NO,AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        } else if (BPCTVCHT.INFO.INDEX_FLG == '2') {
            BPRVCHT.KEY.SET_NO = BPRVCHT.KEY.SET_NO;
            if (BPRVCHT.KEY.SET_NO == null) BPRVCHT.KEY.SET_NO = "";
            JIBS_tmp_int = BPRVCHT.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHT.KEY.SET_NO += " ";
            if (BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHT.KEY.PART_NO = 0;
            else BPRVCHT.KEY.PART_NO = Short.parseShort(BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
            BPTVCHT_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
            else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
            BPTVCHT_BR.rp.where = "AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHT.KEY.SET_NO "
                + "AND PART_NO = :BPRVCHT.KEY.PART_NO";
            BPTVCHT_BR.rp.order = "PART_NO,AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTVCHT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTVCHT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTVCHT = ");
            CEP.TRC(SCCGWA, BPCTVCHT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
