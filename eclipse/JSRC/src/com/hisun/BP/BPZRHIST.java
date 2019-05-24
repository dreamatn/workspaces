package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHIST {
    brParm BPTNHIST_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRHIST";
    String K_TBL_NHIST = "BPTNHIST";
    int WS_I = 0;
    char WS_TBL_HIST_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRNHIST BPRNHIST = new BPRNHIST();
    SCCGWA SCCGWA;
    BPCRHIST BPCRHIST;
    public void MP(SCCGWA SCCGWA, BPCRHIST BPCRHIST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHIST = BPCRHIST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHIST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNHIST);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRHIST.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCRHIST.FUNC == 'O') {
            BPRNHIST.TX_CD = "9994930";
        } else if (BPCRHIST.FUNC == 'F') {
            BPRNHIST.TX_CD = "9994931";
        } else if (BPCRHIST.FUNC == 'T') {
            BPRNHIST.TX_CD = "9994932";
        } else if (BPCRHIST.FUNC == 'C') {
            BPRNHIST.TX_CD = "9994620";
        } else {
            CEP.TRC(SCCGWA, BPCRHIST.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_BROWSER_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCRHIST.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSER_PROC() throws IOException,SQLException,Exception {
        if (BPCRHIST.FUNC != 'C') {
            BPRNHIST.TX_TLR = BPCRHIST.TLR;
            T000_STARTBR_BPTNHIST_1();
            if (pgmRtn) return;
        } else {
            BPRNHIST.REF_NO = BPCRHIST.TLR;
            T000_STARTBR_BPTNHIST_2();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTNHIST();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 30 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            BPCRHIST.INFO[WS_I-1].AC_DT = BPRNHIST.KEY.AC_DT;
            BPCRHIST.INFO[WS_I-1].TX_DT = BPRNHIST.TX_DT;
            BPCRHIST.INFO[WS_I-1].TX_TM = BPRNHIST.TX_TM;
            BPCRHIST.INFO[WS_I-1].RMK = BPRNHIST.TX_RMK;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[WS_I-1].AC_DT);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[WS_I-1].TX_DT);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[WS_I-1].TX_TM);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[WS_I-1].RMK);
            T000_READNEXT_BPTNHIST();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTNHIST_1() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "TX_CD LIKE :BPRNHIST.TX_CD "
            + "AND TX_TLR LIKE :BPRNHIST.TX_TLR";
        BPTNHIST_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_BPTNHIST_2() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "TX_CD LIKE :BPRNHIST.TX_CD "
            + "AND REF_NO LIKE :BPRNHIST.REF_NO";
        BPTNHIST_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_READNEXT_BPTNHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_ENDBR_BPTNHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTNHIST_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHIST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHIST = ");
            CEP.TRC(SCCGWA, BPCRHIST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
