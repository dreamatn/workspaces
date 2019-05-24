package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEXRS {
    brParm BPTEXRD_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTEXRS = "BPZTEXRS";
    String TBL_BPTEXRD = "BPTEXRD ";
    int WS_REC_LEN = 0;
    char WS_INPUT = '0';
    char WS_CALC = '1';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    BPCREXRS BPCREXRS;
    BPREXRD BPREXRD1;
    public void MP(SCCGWA SCCGWA, BPCREXRS BPCREXRS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCREXRS = BPCREXRS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEXRS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXRD1 = (BPREXRD) BPCREXRS.INFO.POINTER;
        IBS.init(SCCGWA, BPREXRD);
        IBS.CLONE(SCCGWA, BPREXRD1, BPREXRD);
        WS_INPUT = '0';
        WS_CALC = '1';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCREXRS.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC_S();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC_1();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '2') {
            B012_STARTBR_RECORD_PROC_2();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '3') {
            B013_STARTBR_RECORD_PROC_3();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '4') {
            B014_STARTBR_RECORD_PROC_4();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '5') {
            B015_STARTBR_RECORD_PROC_5();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '6') {
            B016_STARTBR_RECORD_PROC_6();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '6') {
            B017_STARTBR_RECORD_PROC_7();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == '6') {
            B017_STARTBR_RECORD_PROC_8();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREXRS.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCREXRS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXRD, BPREXRD1);
    }
    public void B010_STARTBR_RECORD_PROC_S() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRDS();
        if (pgmRtn) return;
    }
    public void B011_STARTBR_RECORD_PROC_1() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD1();
        if (pgmRtn) return;
    }
    public void B012_STARTBR_RECORD_PROC_2() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD2();
        if (pgmRtn) return;
    }
    public void B013_STARTBR_RECORD_PROC_3() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD3();
        if (pgmRtn) return;
    }
    public void B014_STARTBR_RECORD_PROC_4() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD4();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_RECORD_PROC_5() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD5();
        if (pgmRtn) return;
    }
    public void B016_STARTBR_RECORD_PROC_6() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD6();
        if (pgmRtn) return;
    }
    public void B017_STARTBR_RECORD_PROC_7() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD7();
        if (pgmRtn) return;
    }
    public void B017_STARTBR_RECORD_PROC_8() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRD8();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTEXRD();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTEXRD();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTEXRD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPREXRD, this, BPTEXRD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREXRS.INFO.RTN_INFO = 'Y';
            CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
            CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREXRS.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTEXRDS() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        IBS.STARTBR(SCCGWA, BPREXRD, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP LIKE :BPREXRD.KEY.EXR_TYP "
            + "AND CCY LIKE :BPREXRD.KEY.CCY";
        BPTEXRD_BR.rp.order = "EXR_TYP,PNL_SQN";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP LIKE :BPREXRD.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPREXRD.KEY.FWD_TENOR "
            + "AND CCY LIKE :BPREXRD.KEY.CCY "
            + "AND CORR_CCY = :BPREXRD.KEY.CORR_CCY";
        BPTEXRD_BR.rp.order = "EXR_TYP,CCY,FWD_TENOR";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD3() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "CCY = :BPREXRD.KEY.CCY "
            + "AND CORR_CCY = :BPREXRD.KEY.CORR_CCY";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD4() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP = :BPREXRD.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPREXRD.KEY.FWD_TENOR "
            + "AND GEN_MOD = :WS_INPUT";
        BPTEXRD_BR.rp.order = "PNL_SQN";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD5() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP = :BPREXRD.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPREXRD.KEY.FWD_TENOR "
            + "AND CORR_CCY = :BPREXRD.KEY.CORR_CCY "
            + "AND GEN_MOD = :WS_CALC";
        BPTEXRD_BR.rp.order = "PNL_SQN";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD6() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP = :BPREXRD.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPREXRD.KEY.FWD_TENOR "
            + "AND CCY < > :BPREXRD.KEY.CCY "
            + "AND CORR_CCY < > :BPREXRD.KEY.CCY "
            + "AND GEN_MOD = :WS_CALC";
        BPTEXRD_BR.rp.order = "PNL_SQN";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD7() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP = :BPREXRD.KEY.EXR_TYP "
            + "AND PNL_SQN = :BPREXRD.PNL_SQN";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_STARTBR_BPTEXRD8() throws IOException,SQLException,Exception {
        BPTEXRD_BR.rp = new DBParm();
        BPTEXRD_BR.rp.TableName = "BPTEXRD";
        BPTEXRD_BR.rp.where = "EXR_TYP = :BPREXRD.KEY.EXR_TYP";
        BPTEXRD_BR.rp.order = "PNL_SQN DESC";
        IBS.STARTBR(SCCGWA, BPREXRD, this, BPTEXRD_BR);
    }
    public void T000_ENDBR_BPTEXRD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEXRD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCREXRS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCREXRS = ");
            CEP.TRC(SCCGWA, BPCREXRS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
