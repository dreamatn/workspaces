package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRRTAL {
    brParm BPTCORT_BR = new brParm();
    DBParm BPTCORT_RD;
    boolean pgmRtn = false;
    int K_MAX_CNT = 20;
    String TBL_BPTCORT = "BPTCORT ";
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
    BPCRRTAL BPCRRTAL;
    public void MP(SCCGWA SCCGWA, BPCRRTAL BPCRRTAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRTAL = BPCRRTAL;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRRTAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_IN_RECORD_FLAG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        if (pgmRtn) return;
        if (BPCRRTAL.FUNC == 'A') {
            B200_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.FUNC == 'R') {
            B300_READUPD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.FUNC == 'U') {
            B400_UPD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.FUNC == 'D') {
            B500_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.FUNC == 'Q') {
            B600_QUERY_BR_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.FUNC == 'B') {
            B700_BROWSE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_CCY);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_RBASE);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_RTENO);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_DESC);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_OLD_BASE);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_OLD_TYPE);
    }
    public void B200_ADD_PROC() throws IOException,SQLException,Exception {
        B200_PREPARE_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCORT);
        T000_WRITE_BPTCORT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "SAVE DATA");
            BPCRRTAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRTAL.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B200_PREPARE_DATA() throws IOException,SQLException,Exception {
        BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
        BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
        BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
        BPRCORT.RT_DES_CN = BPCRRTAL.INP.INP_DESC;
        BPRCORT.RT_OLD_RBASE = BPCRRTAL.INP.INP_OLD_BASE;
        BPRCORT.RT_OLD_MTYPE = BPCRRTAL.INP.INP_OLD_TYPE;
    }
    public void B300_READUPD_PROC() throws IOException,SQLException,Exception {
        BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
        BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
        BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
        T100_READ_REC_UPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTAL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B400_UPD_PROC() throws IOException,SQLException,Exception {
        B200_PREPARE_DATA();
        if (pgmRtn) return;
        T200_READ_REC_WRITE_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTAL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCORT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B500_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORT);
        BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
        BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
        BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
        CEP.TRC(SCCGWA, BPRCORT);
        T300_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B600_QUERY_BR_PROC() throws IOException,SQLException,Exception {
        if (BPCRRTAL.QRYBR_INFO == 'T') {
            B600_QUERY_START_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.QRYBR_INFO == 'X') {
            T500_QUERY_BPTCORT_NEXT();
            if (pgmRtn) return;
        } else if (BPCRRTAL.QRYBR_INFO == 'D') {
            T501_ENDBR_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.QRYBR_INFO == 'K') {
            B601_QUERY_KEY_PROC();
            if (pgmRtn) return;
        }
    }
    public void B601_QUERY_KEY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORT);
        BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
        BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
        BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
        T600_READ_REC_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B600_QUERY_START_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORT);
        IBS.init(SCCGWA, BPCRRTAL.OUTPUT_INFO);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_CCY);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_RBASE);
        CEP.TRC(SCCGWA, BPCRRTAL.INP.INP_RTENO);
        if (BPCRRTAL.INP.INP_CCY.trim().length() > 0) {
            BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_CCY = :BPRCORT.KEY.RT_CCY";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if (BPCRRTAL.INP.INP_RBASE.trim().length() > 0) {
            BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_RBASE = :BPRCORT.KEY.RT_RBASE";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if (BPCRRTAL.INP.INP_RTENO.trim().length() > 0) {
            BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_RTENO = :BPRCORT.KEY.RT_RTENO";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if ((BPCRRTAL.INP.INP_CCY.trim().length() > 0) 
            && (BPCRRTAL.INP.INP_RBASE.trim().length() > 0)) {
            BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
            BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_CCY = :BPRCORT.KEY.RT_CCY "
                + "AND RT_RBASE = :BPRCORT.KEY.RT_RBASE";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if ((BPCRRTAL.INP.INP_CCY.trim().length() > 0) 
            && (BPCRRTAL.INP.INP_RTENO.trim().length() > 0)) {
            BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
            BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_CCY = :BPRCORT.KEY.RT_CCY "
                + "AND RT_RTENO = :BPRCORT.KEY.RT_RTENO";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if ((BPCRRTAL.INP.INP_RBASE.trim().length() > 0) 
            && (BPCRRTAL.INP.INP_RTENO.trim().length() > 0)) {
            BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
            BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_RBASE = :BPRCORT.KEY.RT_RBASE "
                + "AND RT_RTENO = :BPRCORT.KEY.RT_RTENO";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
        if (BPCRRTAL.INP.INP_CCY.trim().length() > 0 
            && BPCRRTAL.INP.INP_RBASE.trim().length() > 0 
            && BPCRRTAL.INP.INP_RTENO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "NEW CORE");
            BPRCORT.KEY.RT_CCY = BPCRRTAL.INP.INP_CCY;
            BPRCORT.KEY.RT_RBASE = BPCRRTAL.INP.INP_RBASE;
            BPRCORT.KEY.RT_RTENO = BPCRRTAL.INP.INP_RTENO;
            BPTCORT_BR.rp = new DBParm();
            BPTCORT_BR.rp.TableName = "BPTCORT";
            BPTCORT_BR.rp.where = "RT_CCY = :BPRCORT.KEY.RT_CCY "
                + "AND RT_RBASE = :BPRCORT.KEY.RT_RBASE "
                + "AND RT_RTENO = :BPRCORT.KEY.RT_RTENO";
            IBS.STARTBR(SCCGWA, BPRCORT, this, BPTCORT_BR);
        }
    }
    public void B700_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (BPCRRTAL.READBR_INFO == 'S') {
            T301_STARTBR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.READBR_INFO == 'N') {
            T302_NEXTBR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTAL.READBR_INFO == 'E') {
            T303_ENDBR_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTCORT() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        IBS.WRITE(SCCGWA, BPRCORT, BPTCORT_RD);
    }
    public void T100_READ_REC_UPD_PROC() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        BPTCORT_RD.upd = true;
        IBS.READ(SCCGWA, BPRCORT, BPTCORT_RD);
    }
    public void T200_READ_REC_WRITE_PROC() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        IBS.REWRITE(SCCGWA, BPRCORT, BPTCORT_RD);
    }
    public void T300_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        IBS.DELETE(SCCGWA, BPRCORT, BPTCORT_RD);
    }
    public void T301_STARTBR_REC_PROC() throws IOException,SQLException,Exception {
        BPTCORT_BR.rp = new DBParm();
        BPTCORT_BR.rp.TableName = "BPTCORT";
        IBS.STARTBR(SCCGWA, BPRCORT, BPTCORT_BR);
    }
    public void T302_NEXTBR_REC_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCORT, this, BPTCORT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTAL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTAL.RETURN_INFO = 'F';
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_CCY = BPRCORT.KEY.RT_CCY;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RBASE = BPRCORT.KEY.RT_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RTENO = BPRCORT.KEY.RT_RTENO;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_DESC = BPRCORT.RT_DES_CN;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE = BPRCORT.RT_OLD_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE = BPRCORT.RT_OLD_MTYPE;
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE);
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE);
        }
    }
    public void T303_ENDBR_REC_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCORT_BR);
    }
    public void T500_QUERY_BPTCORT_NEXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCORT, this, BPTCORT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTAL.RETURN_INFO = 'F';
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_CCY = BPRCORT.KEY.RT_CCY;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RBASE = BPRCORT.KEY.RT_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RTENO = BPRCORT.KEY.RT_RTENO;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_DESC = BPRCORT.RT_DES_CN;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE = BPRCORT.RT_OLD_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE = BPRCORT.RT_OLD_MTYPE;
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE);
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE);
        } else {
            CEP.TRC(SCCGWA, "NEW 11111");
            BPCRRTAL.RETURN_INFO = 'N';
        }
    }
    public void T501_ENDBR_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCORT_BR);
    }
    public void T600_READ_REC_KEY_PROC() throws IOException,SQLException,Exception {
        BPTCORT_RD = new DBParm();
        BPTCORT_RD.TableName = "BPTCORT";
        IBS.READ(SCCGWA, BPRCORT, BPTCORT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTAL.RETURN_INFO = 'F';
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_CCY = BPRCORT.KEY.RT_CCY;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RBASE = BPRCORT.KEY.RT_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_RTENO = BPRCORT.KEY.RT_RTENO;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_DESC = BPRCORT.RT_DES_CN;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE = BPRCORT.RT_OLD_RBASE;
            BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE = BPRCORT.RT_OLD_MTYPE;
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE);
            CEP.TRC(SCCGWA, BPCRRTAL.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTAL.RETURN_INFO = 'N';
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
