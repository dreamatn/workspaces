package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTXIB {
    brParm BPTTXIF_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTXIB";
    String K_TBL_TXIF = "BPTTXIF ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTXIF BPRTXIF = new BPRTXIF();
    SCCGWA SCCGWA;
    BPCRTXIB BPCRTXIB;
    BPRTXIF BPRLTXIF;
    public void MP(SCCGWA SCCGWA, BPCRTXIB BPCRTXIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTXIB = BPCRTXIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTXIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLTXIF = (BPRTXIF) BPCRTXIB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.CLONE(SCCGWA, BPRLTXIF, BPRTXIF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTXIB.INFO.FUNC == '1'
            || BPCRTXIB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTXIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTXIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTXIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTXIF, BPRLTXIF);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTXIB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTXIF_01();
            if (pgmRtn) return;
        } else if (BPCRTXIB.INFO.FUNC == '2') {
            T000_STARTBR_BPTTXIF_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTXIF();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTXIF();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTXIF_01() throws IOException,SQLException,Exception {
        BPTTXIF_BR.rp = new DBParm();
        BPTTXIF_BR.rp.TableName = "BPTTXIF";
        BPTTXIF_BR.rp.where = "IN_FLG LIKE :BPRTXIF.KEY.IN_FLG "
            + "AND SYS_MMO LIKE :BPRTXIF.KEY.SYS_MMO "
            + "AND TX_CD >= :BPRTXIF.KEY.TX_CD";
        BPTTXIF_BR.rp.order = "TX_CD";
        IBS.STARTBR(SCCGWA, BPRTXIF, this, BPTTXIF_BR);
    }
    public void T000_STARTBR_BPTTXIF_02() throws IOException,SQLException,Exception {
        BPTTXIF_BR.rp = new DBParm();
        BPTTXIF_BR.rp.TableName = "BPTTXIF";
        BPTTXIF_BR.rp.where = "IN_FLG = :BPRTXIF.KEY.IN_FLG";
        IBS.STARTBR(SCCGWA, BPRTXIF, this, BPTTXIF_BR);
    }
    public void T000_READNEXT_BPTTXIF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTXIF, this, BPTTXIF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTXIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTXIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTXIF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTXIF_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTXIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTXIB = ");
            CEP.TRC(SCCGWA, BPCRTXIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
