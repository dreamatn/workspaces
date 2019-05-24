package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSXGOD {
    DBParm CITCNT_RD;
    DBParm CITBAS_RD;
    String WS_ERR_MSG = " ";
    char SXGOD_OUT_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_BAS_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCNT CIRCNT = new CIRCNT();
    CIRBAS CIRBAS = new CIRBAS();
    SCCGWA SCCGWA;
    CICSXGOD CICSXGOD;
    public void MP(SCCGWA SCCGWA, CICSXGOD CICSXGOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSXGOD = CICSXGOD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSXGOD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CICSXGOD.RC.RC_MMO = "CI";
        CICSXGOD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSXGOD.INPUT_DATA.IN_CI);
        B200_GET_TEL_NO();
        B300_GET_CI_NM();
    }
    public void B200_GET_TEL_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CICSXGOD.INPUT_DATA.IN_CI;
        T200_READ_CITCNT();
        if (WS_CNT_FLG == 'Y') {
            CICSXGOD.OUTPUT_DATA.CNT_ZONE = CIRCNT.CNT_ZONE;
            CICSXGOD.OUTPUT_DATA.TEL_NO = CIRCNT.TEL_NO;
            CICSXGOD.OUTPUT_DATA.TEL_NO1 = CIRCNT.TEL_NO1;
            CICSXGOD.OUTPUT_DATA.CNT_INFO = CIRCNT.CNT_INFO;
        } else {
            CICSXGOD.RC.RC_CODE = 1024;
        }
    }
    public void B300_GET_CI_NM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSXGOD.INPUT_DATA.IN_CI;
        T300_READ_CITBAS();
        if (WS_BAS_FLG == 'Y') {
            CICSXGOD.OUTPUT_DATA.CI_NM = CIRBAS.CI_NM;
        } else {
            CICSXGOD.RC.RC_CODE = 2048;
        }
    }
    public void T200_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.where = "CI_NO = :CIRCNT.KEY.CI_NO "
            + "AND CNT_TYPE = '13'";
        IBS.READ(SCCGWA, CIRCNT, this, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CNT_FLG = 'Y';
        } else {
            WS_CNT_FLG = 'N';
        }
    }
    public void T300_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.where = "CI_NO = :CIRBAS.KEY.CI_NO";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BAS_FLG = 'Y';
        } else {
            WS_BAS_FLG = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
