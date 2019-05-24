package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBHIB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBHIS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBHIB";
    String K_TBL_BHIS = "BPTBHIS ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBHIS BPRBHIS = new BPRBHIS();
    SCCGWA SCCGWA;
    BPCRBHIB BPCRBHIB;
    BPRBHIS BPRBHIS1;
    public void MP(SCCGWA SCCGWA, BPCRBHIB BPCRBHIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBHIB = BPCRBHIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBHIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBHIS1 = (BPRBHIS) BPCRBHIB.INFO.POINTER;
        IBS.init(SCCGWA, BPRBHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBHIS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBHIS1, BPRBHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBHIB.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBHIB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBHIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBHIS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBHIS, BPRBHIS1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRBHIB.INFO.FUNC == '1') {
            T000_STARTBR_BPTBHIS_01();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBHIS();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTBHIS_01() throws IOException,SQLException,Exception {
        BPTBHIS_BR.rp = new DBParm();
        BPTBHIS_BR.rp.TableName = "BPTBHIS";
        IBS.STARTBR(SCCGWA, BPRBHIS, BPTBHIS_BR);
    }
    public void T000_READNEXT_BPTBHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBHIS, this, BPTBHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBHIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBHIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTBHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBHIS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBHIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBHIB = ");
            CEP.TRC(SCCGWA, BPCRBHIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
