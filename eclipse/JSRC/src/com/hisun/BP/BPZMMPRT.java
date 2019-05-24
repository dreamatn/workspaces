package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZMMPRT {
    DBParm BPTMPRTT_RD;
    brParm BPTMPRTT_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTMPRTT = "BPTMPRTT";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRMPRTT BPRMPRTT = new BPRMPRTT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCMMPRT BPCMMPRT;
    BPRMPRTT BPRMPRT;
    public void MP(SCCGWA SCCGWA, BPCMMPRT BPCMMPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMMPRT = BPCMMPRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMMPRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMMPRT.RC);
        BPRMPRT = (BPRMPRTT) BPCMMPRT.INFO.POINTER;
        IBS.init(SCCGWA, BPRMPRTT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRMPRT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRMPRTT);
        CEP.TRC(SCCGWA, BPRMPRTT);
        CEP.TRC(SCCGWA, BPCMMPRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCMMPRT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.FUNC == 'U') {
            B030_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCMMPRT.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRMPRTT, BPRMPRT);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTMPRTT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTMPRTT_UPD();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTMPRTT();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTMPRTT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTMPRTT();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCMMPRT.INFO.OPT == 'S') {
            T000_STARTBR_BPTMPRTT();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.OPT == 'N') {
            T000_READNEXT_BPTMPRTT();
            if (pgmRtn) return;
        } else if (BPCMMPRT.INFO.OPT == 'E') {
            T000_ENDBR_BPTMPRTT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCMMPRT.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTMPRTT() throws IOException,SQLException,Exception {
        BPTMPRTT_RD = new DBParm();
        BPTMPRTT_RD.TableName = "BPTMPRTT";
        IBS.READ(SCCGWA, BPRMPRTT, BPTMPRTT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCMMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCMMPRT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTMPRTT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTMPRTT() throws IOException,SQLException,Exception {
        BPTMPRTT_RD = new DBParm();
        BPTMPRTT_RD.TableName = "BPTMPRTT";
        IBS.WRITE(SCCGWA, BPRMPRTT, BPTMPRTT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCMMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCMMPRT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTMPRTT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTMPRTT_UPD() throws IOException,SQLException,Exception {
        BPTMPRTT_RD = new DBParm();
        BPTMPRTT_RD.TableName = "BPTMPRTT";
        BPTMPRTT_RD.upd = true;
        IBS.READ(SCCGWA, BPRMPRTT, BPTMPRTT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCMMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCMMPRT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTMPRTT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTMPRTT() throws IOException,SQLException,Exception {
        BPTMPRTT_RD = new DBParm();
        BPTMPRTT_RD.TableName = "BPTMPRTT";
        IBS.REWRITE(SCCGWA, BPRMPRTT, BPTMPRTT_RD);
    }
    public void T000_DELETE_BPTMPRTT() throws IOException,SQLException,Exception {
        BPTMPRTT_RD = new DBParm();
        BPTMPRTT_RD.TableName = "BPTMPRTT";
        IBS.DELETE(SCCGWA, BPRMPRTT, BPTMPRTT_RD);
    }
    public void T000_STARTBR_BPTMPRTT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPRMPRTT.KEY.ACCT_CENTRE);
        CEP.TRC(SCCGWA, BPRMPRTT.KEY.PROD_CODE);
        CEP.TRC(SCCGWA, BPRMPRTT.KEY.TOT_CCY);
        CEP.TRC(SCCGWA, BPRMPRTT.KEY.TOT_YEAR);
        CEP.TRC(SCCGWA, BPRMPRTT.KEY.TOT_MON);
        if (BPRMPRTT.KEY.ACCT_CENTRE == 0 
            && BPRMPRTT.KEY.PROD_CODE.trim().length() == 0 
            && BPRMPRTT.KEY.TOT_CCY.trim().length() == 0 
            && BPRMPRTT.KEY.TOT_YEAR == 0 
            && BPRMPRTT.KEY.TOT_MON == 0) {
            BPTMPRTT_BR.rp = new DBParm();
            BPTMPRTT_BR.rp.TableName = "BPTMPRTT";
            IBS.STARTBR(SCCGWA, BPRMPRTT, BPTMPRTT_BR);
        } else {
            BPTMPRTT_BR.rp = new DBParm();
            BPTMPRTT_BR.rp.TableName = "BPTMPRTT";
            BPTMPRTT_BR.rp.where = "ACCT_CENTRE = :BPRMPRTT.KEY.ACCT_CENTRE "
                + "AND PROD_CODE = :BPRMPRTT.KEY.PROD_CODE "
                + "AND TOT_CCY = :BPRMPRTT.KEY.TOT_CCY "
                + "AND TOT_YEAR = :BPRMPRTT.KEY.TOT_YEAR "
                + "AND TOT_MON >= :BPRMPRTT.KEY.TOT_MON";
            IBS.STARTBR(SCCGWA, BPRMPRTT, this, BPTMPRTT_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCMMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCMMPRT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTMPRTT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCMMPRT.RETURN_INFO);
    }
    public void T000_READNEXT_BPTMPRTT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRMPRTT, this, BPTMPRTT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCMMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCMMPRT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTMPRTT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTMPRTT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTMPRTT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCMMPRT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCMMPRT = ");
            CEP.TRC(SCCGWA, BPCMMPRT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
