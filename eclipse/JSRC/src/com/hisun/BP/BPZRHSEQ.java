package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHSEQ {
    DBParm BPTHSEQ_RD;
    brParm BPTHSEQ_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTHSEQ = "BPTHSEQ";
    int WS_COUNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRHSEQ BPCRHSEQ;
    BPRHSEQ BPRHSEQ1;
    public void MP(SCCGWA SCCGWA, BPCRHSEQ BPCRHSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHSEQ = BPCRHSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHSEQ.RC);
        BPRHSEQ1 = (BPRHSEQ) BPCRHSEQ.INFO.POINTER;
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.CLONE(SCCGWA, BPRHSEQ1, BPRHSEQ);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CT_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CNTR_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_DATE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NAME);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPRHSEQ.REMARK);
        CEP.TRC(SCCGWA, BPRHSEQ);
        CEP.TRC(SCCGWA, BPCRHSEQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRHSEQ.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRHSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRHSEQ, BPRHSEQ1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTHSEQ_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTHSEQ();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRHSEQ.INFO.OPT == 'S') {
            T000_STARTBR_BPTHSEQ();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.OPT == 'N') {
            T000_READNEXT_BPTHSEQ();
            if (pgmRtn) return;
        } else if (BPCRHSEQ.INFO.OPT == 'E') {
            T000_ENDBR_BPTHSEQ();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRHSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTHSEQ() throws IOException,SQLException,Exception {
        BPTHSEQ_RD = new DBParm();
        BPTHSEQ_RD.TableName = "BPTHSEQ";
        BPTHSEQ_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRHSEQ, BPTHSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRHSEQ.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHSEQ;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTHSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NO);
        if (BPRHSEQ.KEY.TYPE.equalsIgnoreCase("CINO") 
            && BPRHSEQ.CI_NO.trim().length() > 0) {
            BPTHSEQ_RD = new DBParm();
            BPTHSEQ_RD.TableName = "BPTHSEQ";
            BPTHSEQ_RD.set = "WS-COUNT=COUNT(*)";
            BPTHSEQ_RD.where = "CI_NO <= :BPRHSEQ.CI_NO "
                + "AND MAX_CI >= :BPRHSEQ.CI_NO";
            IBS.GROUP(SCCGWA, BPRHSEQ, this, BPTHSEQ_RD);
            CEP.TRC(SCCGWA, WS_COUNT);
            if (WS_COUNT > 0) {
                BPCRHSEQ.RETURN_INFO = 'D';
                Z_RET();
                if (pgmRtn) return;
            }
        }
        BPTHSEQ_RD = new DBParm();
        BPTHSEQ_RD.TableName = "BPTHSEQ";
        BPTHSEQ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRHSEQ, BPTHSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRHSEQ.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHSEQ;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTHSEQ_UPD() throws IOException,SQLException,Exception {
        BPTHSEQ_RD = new DBParm();
        BPTHSEQ_RD.TableName = "BPTHSEQ";
        BPTHSEQ_RD.upd = true;
        IBS.READ(SCCGWA, BPRHSEQ, BPTHSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRHSEQ.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHSEQ;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTHSEQ() throws IOException,SQLException,Exception {
        BPTHSEQ_RD = new DBParm();
        BPTHSEQ_RD.TableName = "BPTHSEQ";
        IBS.REWRITE(SCCGWA, BPRHSEQ, BPTHSEQ_RD);
    }
    public void T000_DELETE_BPTHSEQ() throws IOException,SQLException,Exception {
        BPTHSEQ_RD = new DBParm();
        BPTHSEQ_RD.TableName = "BPTHSEQ";
        IBS.DELETE(SCCGWA, BPRHSEQ, BPTHSEQ_RD);
    }
    public void T000_STARTBR_BPTHSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        if (BPRHSEQ.ACCT != 0) {
            BPTHSEQ_BR.rp = new DBParm();
            BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
            BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
                + "AND CODE = :BPRHSEQ.KEY.CODE "
                + "AND AC_OFFICER LIKE :BPRHSEQ.AC_OFFICER "
                + "AND ACCT = :BPRHSEQ.ACCT";
            IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
        } else {
            BPTHSEQ_BR.rp = new DBParm();
            BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
            BPTHSEQ_BR.rp.where = "TYPE = :BPRHSEQ.KEY.TYPE "
                + "AND CODE = :BPRHSEQ.KEY.CODE "
                + "AND AC_OFFICER LIKE :BPRHSEQ.AC_OFFICER";
            IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
        }
    }
    public void T000_READNEXT_BPTHSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTHSEQ_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRHSEQ.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHSEQ;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTHSEQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTHSEQ_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHSEQ = ");
            CEP.TRC(SCCGWA, BPCRHSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
