package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTLSC {
    DBParm BPTTLSC_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTLSC";
    int WS_COUNT = 0;
    char WS_TBL_TLSC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCRTLSC BPCRTLSC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLSC BPRTLSL;
    public void MP(SCCGWA SCCGWA, BPCRTLSC BPCRTLSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTLSC = BPCRTLSC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTLSC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLSL = (BPRTLSC) BPCRTLSC.POINTER;
        CEP.TRC(SCCGWA, "00");
        CEP.TRC(SCCGWA, "11");
        IBS.CLONE(SCCGWA, BPRTLSL, BPRTLSC);
        CEP.TRC(SCCGWA, "22");
        BPCRTLSC.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "33");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTLSC.FUNC == 'A') {
            B010_RECORD_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSC.FUNC == 'Q') {
            B020_RECORD_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSC.FUNC == 'D') {
            B030_RECORD_DLE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSC.FUNC == 'U') {
            B040_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSC.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSC.FUNC == 'T') {
            B060_RECORD_INQ_TLR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTLSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLSC, BPRTLSL);
    }
    public void B010_RECORD_ADD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRTLSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
    }
    public void B020_RECORD_INQ_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B030_RECORD_DLE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_UPDATE_TLR();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B060_RECORD_INQ_TLR_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_TLR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.WRITE(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTLSC.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_BPRTLSC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND CODE_NO = :BPRTLSC.KEY.CODE_NO";
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            BPCRTLSC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSC.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_UPDATE_TLR() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND CODE_NO = :BPRTLSC.KEY.CODE_NO";
        BPTTLSC_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSC.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.REWRITE(SCCGWA, BPRTLSC, BPTTLSC_RD);
    }
    public void T000_DELETE_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND CODE_NO = :BPRTLSC.KEY.CODE_NO";
        IBS.DELETE(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
    }
    public void T000_QUERY_TLR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND UPD_TLR = :BPRTLSC.UPD_TLR";
        BPTTLSC_RD.fst = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSC.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRTLSC.RETURN_INFO);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTLSC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTLSC = ");
            CEP.TRC(SCCGWA, BPCRTLSC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
