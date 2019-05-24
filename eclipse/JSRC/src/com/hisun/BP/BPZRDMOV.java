package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDMOV {
    int JIBS_tmp_int;
    DBParm BPTDMOV_RD;
    brParm BPTDMOV_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRDMOV";
    int WS_COUNT = 0;
    char WS_TBL_DMOV_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDMOV BPRDMOV = new BPRDMOV();
    SCCGWA SCCGWA;
    BPCRDMOV BPCRDMOV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCRDMOV BPCRDMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDMOV = BPCRDMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRDMOV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BPCRDMOV.POINTER);
        CEP.TRC(SCCGWA, "00");
        CEP.TRC(SCCGWA, "11");
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
        IBS.CPY2CLS(SCCGWA, LK_REC.substring(0, BPCRDMOV.LEN), BPRDMOV);
        CEP.TRC(SCCGWA, "22");
        BPCRDMOV.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "33");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDMOV.FUNC == 'A') {
            B010_RECORD_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'Q') {
            B020_RECORD_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'D') {
            B030_RECORD_DLE_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'U') {
            B040_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'S') {
            B060_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'I') {
            B110_STARTBR_IN_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'J') {
            B100_STARTBR_JRN_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'R') {
            B070_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'E') {
            B080_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRDMOV.FUNC == 'P') {
            B090_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRDMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDMOV);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_RECORD_ADD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B030_RECORD_DLE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPDATE_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B070_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B090_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_UPDATE_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_JRN_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_JRN_BPRDMOV();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_IN_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_IN_BPRDMOV();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_RD = new DBParm();
        BPTDMOV_RD.TableName = "BPTDMOV";
        IBS.WRITE(SCCGWA, BPRDMOV, BPTDMOV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRDMOV.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_RD = new DBParm();
        BPTDMOV_RD.TableName = "BPTDMOV";
        IBS.READ(SCCGWA, BPRDMOV, BPTDMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_UPDATE_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "IN_BR = :BPRDMOV.IN_BR "
            + "AND MOV_STS = :BPRDMOV.MOV_STS";
        BPTDMOV_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_RD = new DBParm();
        BPTDMOV_RD.TableName = "BPTDMOV";
        IBS.REWRITE(SCCGWA, BPRDMOV, BPTDMOV_RD);
    }
    public void T000_DELETE_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_RD = new DBParm();
        BPTDMOV_RD.TableName = "BPTDMOV";
        IBS.DELETE(SCCGWA, BPRDMOV, BPTDMOV_RD);
    }
    public void T000_STARTBR_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "( IN_BR = :BPRDMOV.IN_BR "
            + "AND IN_TLR LIKE :BPRDMOV.IN_TLR "
            + "AND MOV_STS LIKE :BPRDMOV.MOV_STS ) "
            + "OR ( OUT_BR = :BPRDMOV.IN_BR "
            + "AND OUT_TLR LIKE :BPRDMOV.IN_TLR "
            + "AND MOV_STS LIKE :BPRDMOV.MOV_STS )";
        BPTDMOV_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_IN_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "IN_BR = :BPRDMOV.IN_BR "
            + "AND IN_TLR LIKE :BPRDMOV.IN_TLR "
            + "AND MOV_STS LIKE :BPRDMOV.MOV_STS";
        BPTDMOV_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_JRN_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "CONF_NO = :BPRDMOV.KEY.CONF_NO "
            + "AND MOV_DT = :BPRDMOV.KEY.MOV_DT";
        BPTDMOV_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTDMOV_BR);
    }
    public void T000_UPDATE_BPRDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_RD = new DBParm();
        BPTDMOV_RD.TableName = "BPTDMOV";
        BPTDMOV_RD.upd = true;
        IBS.READ(SCCGWA, BPRDMOV, BPTDMOV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDMOV.RETURN_INFO = 'N';
            CEP.TRC(SCCGWA, BPCRDMOV.RETURN_INFO);
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRDMOV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRDMOV = ");
            CEP.TRC(SCCGWA, BPCRDMOV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
