package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBRIB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBRIS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBRIB";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TLT = "BPTBRIS ";
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBRIS BPRBRIS = new BPRBRIS();
    SCCGWA SCCGWA;
    BPCRBRIB BPCRBRIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRBRIS BPRBRIS1;
    public void MP(SCCGWA SCCGWA, BPCRBRIB BPCRBRIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBRIB = BPCRBRIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBRIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBRIS1 = (BPRBRIS) BPCRBRIB.INFO.POINTER;
        IBS.init(SCCGWA, BPRBRIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBRIS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBRIS1, BPRBRIS);
        BPCRBRIB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBRIB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBRIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBRIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBRIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBRIS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBRIS, BPRBRIS1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRBRIS.KEY.BR == 0) {
            T000_STARTBR_BPRBRIS_BR();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPRBRIS();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRBRIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRBRIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPRBRIS_BR() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp = new DBParm();
        BPTBRIS_BR.rp.TableName = "BPTBRIS";
        BPTBRIS_BR.rp.where = "BR >= :BPRBRIS.KEY.BR";
        BPTBRIS_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBRIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBRIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp = new DBParm();
        BPTBRIS_BR.rp.TableName = "BPTBRIS";
        BPTBRIS_BR.rp.where = "BR = :BPRBRIS.KEY.BR";
        BPTBRIS_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBRIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBRIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_INQ() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp = new DBParm();
        BPTBRIS_BR.rp.TableName = "BPTBRIS";
        BPTBRIS_BR.rp.where = "BR >= :BPRBRIS.KEY.BR";
        BPTBRIS_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBRIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBRIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBRIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBRIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTBRIS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBRIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBRIB = ");
            CEP.TRC(SCCGWA, BPCRBRIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
