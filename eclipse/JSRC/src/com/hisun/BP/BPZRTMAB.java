package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTMAB {
    brParm BPTTMAP_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTMAB";
    String K_TBL_TMAP = "BPTMAP  ";
    short WS_I = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTMAP BPRTMAP = new BPRTMAP();
    SCCGWA SCCGWA;
    BPCRTMAB BPCRTMAB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTMAP BPRTMAL;
    public void MP(SCCGWA SCCGWA, BPCRTMAB BPCRTMAB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTMAB = BPCRTMAB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTMAB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTMAL = (BPRTMAP) BPCRTMAB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTMAP);
        IBS.CLONE(SCCGWA, BPRTMAL, BPRTMAP);
        BPCRTMAB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMAB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTMAB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTMAP, BPRTMAL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTMAP.KEY.DATA_SEQ <= 0) {
            T000_STARTBR_BPTTMAP();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTMAP_BY_SEQ();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTMAP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTMAP();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_BR.rp = new DBParm();
        BPTTMAP_BR.rp.TableName = "BPTTMAP";
        BPTTMAP_BR.rp.where = "FROM_AREA LIKE :BPRTMAP.KEY.FROM_AREA "
            + "AND TX_CODE LIKE :BPRTMAP.KEY.TX_CODE "
            + "AND DATA_NM LIKE :BPRTMAP.KEY.DATA_NM";
        BPTTMAP_BR.rp.errhdl = true;
        BPTTMAP_BR.rp.order = "TX_CODE , FROM_AREA";
        IBS.STARTBR(SCCGWA, BPRTMAP, this, BPTTMAP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTMAP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTTMAP_BY_SEQ() throws IOException,SQLException,Exception {
        BPTTMAP_BR.rp = new DBParm();
        BPTTMAP_BR.rp.TableName = "BPTTMAP";
        BPTTMAP_BR.rp.where = "FROM_AREA LIKE :BPRTMAP.KEY.FROM_AREA "
            + "AND TX_CODE LIKE :BPRTMAP.KEY.TX_CODE "
            + "AND DATA_NM LIKE :BPRTMAP.KEY.DATA_NM "
            + "AND DATA_SEQ = :BPRTMAP.KEY.DATA_SEQ";
        BPTTMAP_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTMAP, this, BPTTMAP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTMAP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTMAP, this, BPTTMAP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTMAP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTMAP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTMAB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTMAB = ");
            CEP.TRC(SCCGWA, BPCRTMAB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
