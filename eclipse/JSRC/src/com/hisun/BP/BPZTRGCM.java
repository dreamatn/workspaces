package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTRGCM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTRGNC_RD;
    boolean pgmRtn = false;
    String PGM_BPZTRGCM = "BPZTRGCM";
    String TBL_BPTRGNC = "BPTRGNC ";
    String WS_TEMP_RECORD = " ";
    int WS_LEN = 0;
    char WS_TBL_ORG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGNC BPRRGNC = new BPRRGNC();
    SCCGWA SCCGWA;
    BPCRRGCM BPCRRGCM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRRGNC BPRRGNC1;
    public void MP(SCCGWA SCCGWA, BPCRRGCM BPCRRGCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRGCM = BPCRRGCM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTRGCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRGNC1 = (BPRRGNC) BPCRRGCM.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRGNC);
        WS_LEN = 249;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRRGNC1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRRGNC1, BPRRGNC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRGCM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGCM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGCM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGCM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRRGCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRRGNC, BPRRGNC1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTRGNC();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRGNC_UPD();
        if (pgmRtn) return;
        if (WS_TBL_ORG_FLAG == 'D') {
            BPCRRGCM.RETURN_INFO = 'N';
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRGNC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTRGNC();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTRGNC() throws IOException,SQLException,Exception {
        BPTRGNC_RD = new DBParm();
        BPTRGNC_RD.TableName = "BPTRGNC";
        IBS.READ(SCCGWA, BPRRGNC, BPTRGNC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRGCM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRGCM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTRGNC() throws IOException,SQLException,Exception {
        BPTRGNC_RD = new DBParm();
        BPTRGNC_RD.TableName = "BPTRGNC";
        BPTRGNC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRRGNC, BPTRGNC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRGCM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRGCM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRGNC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRGNC_UPD() throws IOException,SQLException,Exception {
        BPTRGNC_RD = new DBParm();
        BPTRGNC_RD.TableName = "BPTRGNC";
        BPTRGNC_RD.upd = true;
        IBS.READ(SCCGWA, BPRRGNC, BPTRGNC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORG_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORG_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTRGNC() throws IOException,SQLException,Exception {
        BPTRGNC_RD = new DBParm();
        BPTRGNC_RD.TableName = "BPTRGNC";
        IBS.REWRITE(SCCGWA, BPRRGNC, BPTRGNC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRGCM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRRGCM = ");
            CEP.TRC(SCCGWA, BPCRRGCM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
