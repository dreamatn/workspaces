package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBOXP {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBOXP_BR = new brParm();
    DBParm BPTBOXP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBOXP";
    String K_TBL_BOXP = "BPTBOXP ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBOXP BPRBOXP = new BPRBOXP();
    SCCGWA SCCGWA;
    BPCRBOXP BPCRBOXP;
    BPRBOXP BPRBOXP1;
    public void MP(SCCGWA SCCGWA, BPCRBOXP BPCRBOXP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBOXP = BPCRBOXP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBOXP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBOXP1 = (BPRBOXP) BPCRBOXP.INFO.POINTER;
        IBS.init(SCCGWA, BPRBOXP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBOXP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBOXP1, BPRBOXP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBOXP.INFO.FUNC == '1') {
            T000_STARTBR_BPTBOXP_DT_BR();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == '2') {
            T010_STARTBR_BPTBOXP_DETL();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'A') {
            B040_WRITE_BPTBOXP_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'R') {
            B060_READ_UPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOXP.INFO.FUNC == 'Q') {
            B070_READ_QUERY_TLR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBOXP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBOXP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBOXP, BPRBOXP1);
    }
    public void T000_STARTBR_BPTBOXP_DT_BR() throws IOException,SQLException,Exception {
        BPTBOXP_BR.rp = new DBParm();
        BPTBOXP_BR.rp.TableName = "BPTBOXP";
        BPTBOXP_BR.rp.where = "PLAN_DATE >= :BPRBOXP.KEY.PLAN_DATE "
            + "AND BR = :BPRBOXP.KEY.BR";
        BPTBOXP_BR.rp.grp = "PLAN_DATE,BR";
        BPTBOXP_BR.rp.order = "PLAN_DATE,BR";
        IBS.STARTBR(SCCGWA, BPRBOXP, this, BPTBOXP_BR);
    }
    public void T010_STARTBR_BPTBOXP_DETL() throws IOException,SQLException,Exception {
        BPTBOXP_BR.rp = new DBParm();
        BPTBOXP_BR.rp.TableName = "BPTBOXP";
        BPTBOXP_BR.rp.where = "PLAN_DATE = :BPRBOXP.KEY.PLAN_DATE "
            + "AND BR = :BPRBOXP.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRBOXP, this, BPTBOXP_BR);
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBOXP, this, BPTBOXP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOXP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBOXP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTBOXP_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTBOXP_BR);
    }
    public void B040_WRITE_BPTBOXP_PROC() throws IOException,SQLException,Exception {
        BPTBOXP_RD = new DBParm();
        BPTBOXP_RD.TableName = "BPTBOXP";
        BPTBOXP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBOXP, BPTBOXP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOXP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBOXP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BOXP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTBOXP_RD = new DBParm();
        BPTBOXP_RD.TableName = "BPTBOXP";
        IBS.REWRITE(SCCGWA, BPRBOXP, BPTBOXP_RD);
    }
    public void B060_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTBOXP_RD = new DBParm();
        BPTBOXP_RD.TableName = "BPTBOXP";
        BPTBOXP_RD.upd = true;
        IBS.READ(SCCGWA, BPRBOXP, BPTBOXP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOXP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBOXP.RETURN_INFO = 'D';
        } else {
        }
    }
    public void B070_READ_QUERY_TLR_PROC() throws IOException,SQLException,Exception {
        BPTBOXP_RD = new DBParm();
        BPTBOXP_RD.TableName = "BPTBOXP";
        BPTBOXP_RD.where = "PLAN_DATE = :BPRBOXP.KEY.PLAN_DATE "
            + "AND BR = :BPRBOXP.KEY.BR "
            + "AND BOX_TYPE = :BPRBOXP.KEY.BOX_TYPE "
            + "AND PLAN_TLR = :BPRBOXP.PLAN_TLR";
        IBS.READ(SCCGWA, BPRBOXP, this, BPTBOXP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOXP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBOXP.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBOXP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBOXP = ");
            CEP.TRC(SCCGWA, BPCRBOXP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
