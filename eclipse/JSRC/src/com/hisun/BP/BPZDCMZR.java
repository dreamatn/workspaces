package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZDCMZR {
    DBParm BPTCMZR_RD;
    String WS_ERR_MSG = " ";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCMZR BPRCMZR = new BPRCMZR();
    SCCGWA SCCGWA;
    BPCDCMZR BPCDCMZR;
    public void MP(SCCGWA SCCGWA, BPCDCMZR BPCDCMZR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCDCMZR = BPCDCMZR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZDCMZR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCDCMZR.RC.RC_MMO = "BP";
        BPCDCMZR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_GET_DATA();
    }
    public void B200_GET_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMZR);
        CEP.TRC(SCCGWA, BPCDCMZR.DATA.CMZ_AC);
        BPRCMZR.KEY.CMZ_AC = BPCDCMZR.DATA.CMZ_AC;
        BPRCMZR.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCMZR.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_BPTCMZR();
        CEP.TRC(SCCGWA, BPCDCMZR.DATA.OUT_FLG);
        if (BPCDCMZR.DATA.OUT_FLG == 'Y') {
            if ((BPRCMZR.CMZ_FLG1 == '0' 
                && BPRCMZR.CMZ_FLG2 == '1' 
                && BPRCMZR.CMZ_PCN == 1) 
                || (BPRCMZR.CMZ_FLG1 == '1' 
                && BPRCMZR.CMZ_AMT == 0)) {
                BPCDCMZR.DATA.OUT_FLG = 'A';
            } else {
                BPCDCMZR.DATA.OUT_FLG = 'P';
            }
        }
        CEP.TRC(SCCGWA, BPCDCMZR.DATA.OUT_FLG);
    }
    public void T000_READ_BPTCMZR() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        BPTCMZR_RD.where = "CMZ_FLG = '2' "
            + "AND CMZ_AC = :BPRCMZR.KEY.CMZ_AC "
            + "AND ( ( ENTI_FLG = '1' "
            + "AND FEE_CODE = '401000402' ) "
            + "OR ENTI_FLG = '0' ) "
            + "AND EFF_DATE <= :BPRCMZR.KEY.EFF_DATE "
            + "AND EXP_DATE >= :BPRCMZR.EXP_DATE";
        IBS.READ(SCCGWA, BPRCMZR, this, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCDCMZR.DATA.OUT_FLG = 'Y';
        } else {
            BPCDCMZR.DATA.OUT_FLG = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
