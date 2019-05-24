package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCORT {
    DBParm BPTCORT_RD;
    brParm BPTCORT_BR = new brParm();
    int K_MAX_CNT = 20;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_IN_RECORD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPRCORT BPRCORT = new BPRCORT();
    SCCGWA SCCGWA;
    BPCSCORT BPCSCORT;
    public void MP(SCCGWA SCCGWA, BPCSCORT BPCSCORT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCORT = BPCSCORT;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCORT return!");
        Z_RET();
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_IN_RECORD_FLAG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        B200_SEARCH_PROC();
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_CCY);
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_RBASE);
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_RTENO);
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_DESC);
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_OLD_BASE);
        CEP.TRC(SCCGWA, BPCSCORT.INP.INP_OLD_TYPE);
    }
    public void B200_SEARCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORT);
        IBS.init(SCCGWA, BPCSCORT.OUTPUT_INFO);
        if (BPCSCORT.INP.INP_CCY.trim().length() > 0 
            && BPCSCORT.INP.INP_RBASE.trim().length() > 0 
            && BPCSCORT.INP.INP_RTENO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "NEW CORE");
            BPRCORT.KEY.RT_CCY = BPCSCORT.INP.INP_CCY;
            BPRCORT.KEY.RT_RBASE = BPCSCORT.INP.INP_RBASE;
            BPRCORT.KEY.RT_RTENO = BPCSCORT.INP.INP_RTENO;
            T000_READ_BPTCORT();
            if (WS_IN_RECORD_FLAG == 'Y') {
                BPCSCORT.OUTPUT_INFO.OUT_OUT.OUT_OLD_BASE = BPRCORT.RT_OLD_RBASE;
                BPCSCORT.OUTPUT_INFO.OUT_OUT.OUT_OLD_TYPE = BPRCORT.RT_OLD_MTYPE;
                CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_OUT.OUT_OLD_BASE);
                CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_OUT.OUT_OLD_TYPE);
            }
        }
        if (BPCSCORT.INP.INP_OLD_BASE.trim().length() > 0 
            && BPCSCORT.INP.INP_OLD_TYPE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "OLD CORE");
            if (BPCSCORT.FUNC == 'A') {
                CEP.TRC(SCCGWA, "ADD");
                if (BPCSCORT.OP_FUNC == 'S') {
                    BPRCORT.RT_OLD_RBASE = BPCSCORT.INP.INP_OLD_BASE;
                    BPRCORT.RT_OLD_MTYPE = BPCSCORT.INP.INP_OLD_TYPE;
                    T102_STARTBR_BPTCORT_ALL_OLD();
                } else if (BPCSCORT.OP_FUNC == 'R') {
                    T102_NEXTBR_BPTCORT_ALL_OLD();
                } else if (BPCSCORT.OP_FUNC == 'E') {
                    T102_ENDBR_BPTCORT_ALL_OLD();
                }
                if (WS_IN_RECORD_FLAG == 'Y') {
                    BPCSCORT.SEACH_FUNC = 'Y';
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_CCY = BPRCORT.KEY.RT_CCY;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RBASE = BPRCORT.KEY.RT_RBASE;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RTENO = BPRCORT.KEY.RT_RTENO;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_DESC = BPRCORT.RT_DES_CN;
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_CCY);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RBASE);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RTENO);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_DESC);
                } else {
                    CEP.TRC(SCCGWA, "NOT DATA");
                    BPCSCORT.SEACH_FUNC = 'N';
                }
            } else {
                CEP.TRC(SCCGWA, "NOT ADD");
                BPRCORT.RT_OLD_RBASE = BPCSCORT.INP.INP_OLD_BASE;
                BPRCORT.RT_OLD_MTYPE = BPCSCORT.INP.INP_OLD_TYPE;
                T100_READ_BPTCORT_OLD();
                if (WS_IN_RECORD_FLAG == 'Y') {
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_CCY = BPRCORT.KEY.RT_CCY;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RBASE = BPRCORT.KEY.RT_RBASE;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RTENO = BPRCORT.KEY.RT_RTENO;
                    BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_DESC = BPRCORT.RT_DES_CN;
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_CCY);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RBASE);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RTENO);
                    CEP.TRC(SCCGWA, BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_DESC);
                }
            }
        }
    }
    public void T000_READ_BPTCORT() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        IBS.READ(SCCGWA, BPRCORT, BPTCORT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_IN_RECORD_FLAG = 'N';
        } else {
            WS_IN_RECORD_FLAG = 'Y';
        }
    }
    public void T100_READ_BPTCORT_OLD() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        BPTCORT_RD.where = "RT_OLD_RBASE = :BPRCORT.RT_OLD_RBASE "
            + "AND RT_OLD_MTYPE = :BPRCORT.RT_OLD_MTYPE";
        BPTCORT_RD.fst = true;
        IBS.READ(SCCGWA, BPRCORT, this, BPTCORT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_IN_RECORD_FLAG = 'N';
        } else {
            WS_IN_RECORD_FLAG = 'Y';
        }
    }
    public void T102_STARTBR_BPTCORT_ALL_OLD() throws IOException,SQLException,Exception {
        BPTCORT_BR.rp = new DBParm();
        BPTCORT_BR.rp.TableName = "BPTCORT";
        BPTCORT_BR.rp.where = "RT_OLD_RBASE = :BPRCORT.RT_OLD_RBASE "
            + "AND RT_OLD_MTYPE = :BPRCORT.RT_OLD_MTYPE";
        IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
    }
    public void T102_NEXTBR_BPTCORT_ALL_OLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCORT, this, BPTCORT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_IN_RECORD_FLAG = 'N';
        } else {
            WS_IN_RECORD_FLAG = 'Y';
        }
    }
    public void T102_ENDBR_BPTCORT_ALL_OLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCORT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
