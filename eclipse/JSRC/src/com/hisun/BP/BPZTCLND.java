package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTCLND {
    DBParm BPTCLND_RD;
    brParm BPTCLND_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTCLND";
    String K_TBL_CLND = "BPTCLND ";
    int WS_REC_LEN = 0;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_CLND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLND BPRCLND = new BPRCLND();
    SCCGWA SCCGWA;
    BPCTCLND BPCTCLND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRCLND BPRCLND1;
    public void MP(SCCGWA SCCGWA, BPCTCLND BPCTCLND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCLND = BPCTCLND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTCLND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRCLND1 = (BPRCLND) BPCTCLND.INFO.POINTER;
        IBS.init(SCCGWA, BPRCLND);
        WS_REC_LEN = 329;
        IBS.CLONE(SCCGWA, BPRCLND1, BPRCLND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTCLND.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.FUNC == 'I') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.FUNC == 'B') {
            B040_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.FUNC == 'R') {
            B060_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCLND, BPRCLND1);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCLND();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCLND();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCLND();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTCLND.INFO.OPT == 'S') {
            R000_STARTBR_BPTCLND();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.OPT == 'N') {
            T000_READNEXT_BPTCLND();
            if (pgmRtn) return;
        } else if (BPCTCLND.INFO.OPT == 'E') {
            T000_ENDBR_BPTCLND();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTCLND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_BPTCLND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_ID);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_RULE);
        if ((BPRCLND.KEY.CLN_RULE.trim().length() > 0 
                && BPRCLND.KEY.CLN_ID.trim().length() == 0 
                && BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND7();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() == 0 
                && BPRCLND.KEY.CLN_ID.trim().length() > 0 
                && BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND8();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() == 0 
                && BPRCLND.KEY.CLN_ID.trim().length() == 0 
                && !BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND9();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() > 0 
                && BPRCLND.KEY.CLN_ID.trim().length() > 0 
                && BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND2();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() > 0 
                && BPRCLND.KEY.CLN_ID.trim().length() == 0 
                && !BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND3();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() == 0 
                && BPRCLND.KEY.CLN_ID.trim().length() > 0 
                && !BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND4();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() > 0 
                && BPRCLND.KEY.CLN_ID.trim().length() > 0 
                && !BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND5();
            if (pgmRtn) return;
        } else if ((BPRCLND.KEY.CLN_RULE.trim().length() == 0 
                && BPRCLND.KEY.CLN_ID.trim().length() == 0 
                && BPRCLND.KEY.BNK.equalsIgnoreCase("0"))) {
            T000_STARTBR_BPTCLND6();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCLND();
        if (pgmRtn) return;
    }
    public void B060_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCLND_UPD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTCLND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_ID);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_RULE);
        BPTCLND_RD = new DBParm();
        BPTCLND_RD.TableName = "BPTCLND";
        BPTCLND_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRCLND, BPTCLND_RD);
        CEP.TRC(SCCGWA, BPRCLND.CLN_FRY);
        CEP.TRC(SCCGWA, BPRCLND.CLN_CYC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCLND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCLND.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTCLND() throws IOException,SQLException,Exception {
        BPTCLND_RD = new DBParm();
        BPTCLND_RD.TableName = "BPTCLND";
        IBS.DELETE(SCCGWA, BPRCLND, BPTCLND_RD);
    }
    public void T000_STARTBR_BPTCLND2() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_RULE = :BPRCLND.KEY.CLN_RULE "
            + "AND CLN_ID = :BPRCLND.KEY.CLN_ID";
        BPTCLND_BR.rp.order = "CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND3() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_RULE = :BPRCLND.KEY.CLN_RULE "
            + "AND BNK = :BPRCLND.KEY.BNK";
        BPTCLND_BR.rp.order = "BNK,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND4() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_ID = :BPRCLND.KEY.CLN_ID "
            + "AND BNK = :BPRCLND.KEY.BNK";
        BPTCLND_BR.rp.order = "BNK,CLN_ID";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND5() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_RULE = :BPRCLND.KEY.CLN_RULE "
            + "AND CLN_ID = :BPRCLND.KEY.CLN_ID "
            + "AND BNK = :BPRCLND.KEY.BNK";
        BPTCLND_BR.rp.order = "BNK,CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND6() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_RULE < > :BPRCLND.KEY.CLN_RULE "
            + "OR CLN_ID < > :BPRCLND.KEY.CLN_ID "
            + "OR BNK < > :BPRCLND.KEY.BNK";
        BPTCLND_BR.rp.order = "BNK,CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND7() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_RULE = :BPRCLND.KEY.CLN_RULE";
        BPTCLND_BR.rp.order = "BNK,CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND8() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "CLN_ID = :BPRCLND.KEY.CLN_ID";
        BPTCLND_BR.rp.order = "BNK,CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_STARTBR_BPTCLND9() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp = new DBParm();
        BPTCLND_BR.rp.TableName = "BPTCLND";
        BPTCLND_BR.rp.where = "BNK = :BPRCLND.KEY.BNK";
        BPTCLND_BR.rp.order = "BNK,CLN_ID,CLN_RULE";
        IBS.STARTBR(SCCGWA, BPRCLND, this, BPTCLND_BR);
    }
    public void T000_READNEXT_BPTCLND() throws IOException,SQLException,Exception {
        BPTCLND_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCLND, this, BPTCLND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCLND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCLND.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, " READNEXT-BPTCLND ERROR");
        }
    }
    public void T000_ENDBR_BPTCLND() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLND_BR);
    }
    public void T000_WRITE_BPTCLND() throws IOException,SQLException,Exception {
        BPTCLND_RD = new DBParm();
        BPTCLND_RD.TableName = "BPTCLND";
        BPTCLND_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCLND, BPTCLND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCLND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTCLND.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CLND;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_READ_BPTCLND_UPD() throws IOException,SQLException,Exception {
        BPTCLND_RD = new DBParm();
        BPTCLND_RD.TableName = "BPTCLND";
        BPTCLND_RD.upd = true;
        IBS.READ(SCCGWA, BPRCLND, BPTCLND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTCLND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTCLND.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTCLND() throws IOException,SQLException,Exception {
        BPTCLND_RD = new DBParm();
        BPTCLND_RD.TableName = "BPTCLND";
        IBS.REWRITE(SCCGWA, BPRCLND, BPTCLND_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCLND.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTCLND = ");
            CEP.TRC(SCCGWA, BPCTCLND);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
