package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFESIR {
    DBParm BPTFESIR_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_NEW_RATE = 0;
    double WS_OLD_RATE = 0;
    char WS_TABLE_REC = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFESIR BPRFESIR = new BPRFESIR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCFESIR BPCFESIR;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCFESIR BPCFESIR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFESIR = BPCFESIR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFESIR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCFESIR.RET_INFO = 'N';
        BPCFESIR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFESIR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B001_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFESIR.KEY.SIR_AC);
        if (BPRFESIR.KEY.SIR_AC.trim().length() == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFESIR.KEY.SIR_AC);
        IBS.init(SCCGWA, BPRFESIR);
        BPRFESIR.KEY.SIR_AC = BPCFESIR.INP_DATA.AC_NO;
        BPTFESIR_RD = new DBParm();
        BPTFESIR_RD.TableName = "BPTFESIR";
        BPTFESIR_RD.where = "SIR_AC = :BPRFESIR.KEY.SIR_AC";
        IBS.READ(SCCGWA, BPRFESIR, this, BPTFESIR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCFESIR.RET_INFO = 'Y';
        }
        CEP.TRC(SCCGWA, BPCFESIR.RET_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
