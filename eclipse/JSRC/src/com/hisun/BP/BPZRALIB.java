package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRALIB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTALIB_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRALIB";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TLT = "BPTTLVB ";
    int WS_COUNT = 0;
    int WS_APP_NO_LOW = 0;
    int WS_APP_NO_HIGH = 0;
    int WS_APP_BR_LOW = 0;
    int WS_APP_BR_HIGH = 0;
    int WS_UP_BR_LOW = 0;
    int WS_UP_BR_HIGH = 0;
    int WS_BEG_DT = 0;
    int WS_END_DT = 0;
    int WK_APP_NO_LOW = 000000000;
    int WK_APP_NO_HIGH = 999999;
    int WK_APP_BR_LOW = 000000000;
    int WK_APP_BR_HIGH = 999999;
    int WK_END_DT_HIGH = 99991231;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRALIB BPRALIB = new BPRALIB();
    SCCGWA SCCGWA;
    BPCRALIB BPCRALIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRALIB BPRALIK;
    public void MP(SCCGWA SCCGWA, BPCRALIB BPCRALIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRALIB = BPCRALIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRALIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRALIK = (BPRALIB) BPCRALIB.INFO.POINTER;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRALIK);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRALIK, BPRALIB);
        BPCRALIB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRALIB.INFO.FUNC == 'B') {
            B010_STARTBR_BR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRALIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRALIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRALIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRALIB);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRALIB, BPRALIK);
    }
    public void B010_STARTBR_BR_REC_PROC() throws IOException,SQLException,Exception {
        if (BPRALIB.KEY.APP_NO == 0) {
            WS_APP_NO_LOW = WK_APP_NO_LOW;
            WS_APP_NO_HIGH = WK_APP_NO_HIGH;
        } else {
            WS_APP_NO_LOW = BPRALIB.KEY.APP_NO;
            WS_APP_NO_HIGH = BPRALIB.KEY.APP_NO;
        }
        if (BPRALIB.APP_BR == 0) {
            WS_APP_BR_LOW = WK_APP_BR_LOW;
            WS_APP_BR_HIGH = WK_APP_BR_HIGH;
        } else {
            WS_APP_BR_LOW = BPRALIB.APP_BR;
            WS_APP_BR_HIGH = BPRALIB.APP_BR;
        }
        if (BPRALIB.UP_BR == 0) {
            WS_UP_BR_LOW = WK_APP_BR_LOW;
            WS_UP_BR_HIGH = WK_APP_BR_HIGH;
        } else {
            WS_UP_BR_LOW = BPRALIB.UP_BR;
            WS_UP_BR_HIGH = BPRALIB.UP_BR;
        }
        if (BPCRALIB.INFO.END_DT == 0) {
            WS_END_DT = WK_END_DT_HIGH;
        } else {
            WS_END_DT = BPCRALIB.INFO.END_DT;
        }
        if (BPCRALIB.INFO.BEG_DT == 0) {
            WS_BEG_DT = 0;
        } else {
            WS_BEG_DT = BPCRALIB.INFO.BEG_DT;
        }
        CEP.TRC(SCCGWA, WS_APP_NO_LOW);
        CEP.TRC(SCCGWA, WS_APP_NO_HIGH);
        CEP.TRC(SCCGWA, WS_APP_BR_LOW);
        CEP.TRC(SCCGWA, WS_APP_BR_HIGH);
        CEP.TRC(SCCGWA, WS_UP_BR_LOW);
        CEP.TRC(SCCGWA, WS_UP_BR_HIGH);
        CEP.TRC(SCCGWA, BPRALIB.APP_TYPE);
        CEP.TRC(SCCGWA, BPRALIB.APP_STS);
        CEP.TRC(SCCGWA, BPCRALIB.INFO.BEG_DT);
        CEP.TRC(SCCGWA, BPCRALIB.INFO.END_DT);
        CEP.TRC(SCCGWA, BPRALIB.ALLOT_TYPE);
        T000_STARTBR_BPTALIB();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTALIB();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTALIB();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_BR.rp = new DBParm();
        BPTALIB_BR.rp.TableName = "BPTALIB";
        BPTALIB_BR.rp.where = "( APP_NO BETWEEN :WS_APP_NO_LOW "
            + "AND :WS_APP_NO_HIGH ) "
            + "AND ( APP_BR BETWEEN :WS_APP_BR_LOW "
            + "AND :WS_APP_BR_HIGH ) "
            + "AND ( UP_BR BETWEEN :WS_UP_BR_LOW "
            + "AND :WS_UP_BR_HIGH ) "
            + "AND APP_TYPE LIKE :BPRALIB.APP_TYPE "
            + "AND APP_STS LIKE :BPRALIB.APP_STS "
            + "AND ( APP_DT BETWEEN :WS_BEG_DT "
            + "AND :WS_END_DT ) "
            + "AND ALLOT_TYPE LIKE :BPRALIB.ALLOT_TYPE";
        IBS.STARTBR(SCCGWA, BPRALIB, this, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRALIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRALIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTALIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRALIB, this, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRALIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRALIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTALIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRALIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRALIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
