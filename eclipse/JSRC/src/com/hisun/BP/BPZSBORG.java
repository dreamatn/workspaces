package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBORG {
    brParm BPTRGND_BR = new brParm();
    String K_PGM_NAME = "BPZSBORG";
    String K_TBL_RGND = "BPTRGND ";
    short K_MAX_COL_NO = 100;
    short WS_COL_CNT = 0;
    short WS_I = 0;
    int WS_RCD_SEQ = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGND BPRRGND = new BPRRGND();
    SCCGWA SCCGWA;
    BPCSBORG BPCSBORG;
    public void MP(SCCGWA SCCGWA, BPCSBORG BPCSBORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBORG = BPCSBORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBORG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBORG.RGN_TYPE);
        CEP.TRC(SCCGWA, BPCSBORG.RGN_SEQ);
        CEP.TRC(SCCGWA, BPCSBORG.RCD_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        BPRRGND.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRRGND.KEY.RGN_TYPE = BPCSBORG.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCSBORG.RGN_SEQ;
        IBS.init(SCCGWA, BPCSBORG.RC);
        IBS.init(SCCGWA, BPCSBORG.OUTPUT);
        B010_STARTBR_PROC();
        WS_RCD_SEQ = BPCSBORG.RCD_SEQ + 1;
        B020_READNEXT_ABSOLUTE();
        for (WS_COL_CNT = 1; BPCSBORG.RETURN_INFO != 'N' 
            && WS_COL_CNT <= K_MAX_COL_NO; WS_COL_CNT += 1) {
            B040_OUT_DATA();
            T000_READNEXT_BPTRGND();
        }
        B030_ENDBR_PROC();
        BPCSBORG.RCD_SEQ = BPCSBORG.RCD_SEQ + WS_COL_CNT - 1;
        BPCSBORG.OUTPUT.COL_NO = WS_COL_CNT - 1;
        if (BPCSBORG.OUTPUT.COL_NO == 0) {
            BPCSBORG.RETURN_INFO = 'N';
        } else if (BPCSBORG.OUTPUT.COL_NO == K_MAX_COL_NO) {
            BPCSBORG.RETURN_INFO = 'C';
        } else {
            BPCSBORG.RETURN_INFO = 'F';
        }
        CEP.TRC(SCCGWA, WS_COL_CNT);
        CEP.TRC(SCCGWA, BPCSBORG.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCSBORG.RCD_SEQ);
        CEP.TRC(SCCGWA, BPCSBORG.OUTPUT.COL_NO);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BANK_TYPE_SEQ();
    }
    public void B020_READNEXT_ABSOLUTE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRGND, this, BPTRGND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSBORG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSBORG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTRGND();
    }
    public void T000_STARTBR_BY_BANK_TYPE_SEQ() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE "
            + "AND RGN_NO = :BPRRGND.KEY.RGN_NO";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSBORG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSBORG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTRGND() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRGND, this, BPTRGND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSBORG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSBORG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTRGND() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRGND_BR);
    }
    public void B040_OUT_DATA() throws IOException,SQLException,Exception {
        BPCSBORG.OUTPUT.RGN_UNIT[WS_COL_CNT-1] = BPRRGND.KEY.RGN_UNIT;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
