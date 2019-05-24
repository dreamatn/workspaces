package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTROB {
    brParm BPTTROL_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTROB";
    String K_TBL_TROL = "BPTTROL ";
    char WS_TBL_TROL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTROL BPRTROL = new BPRTROL();
    SCCGWA SCCGWA;
    BPCRTROB BPCRTROB;
    BPRTROL BPRTRSL;
    public void MP(SCCGWA SCCGWA, BPCRTROB BPCRTROB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTROB = BPCRTROB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTROB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTRSL = (BPRTROL) BPCRTROB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTROL);
        IBS.CLONE(SCCGWA, BPRTRSL, BPRTROL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTROB.INFO.FUNC == '1'
            || BPCRTROB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTROB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTROL, BPRTRSL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTROB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTROL_01();
            if (pgmRtn) return;
        } else if (BPCRTROB.INFO.FUNC == '2') {
            T000_STARTBR_BPTTROL_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTROL();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTROL();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTROL_01() throws IOException,SQLException,Exception {
        BPTTROL_BR.rp = new DBParm();
        BPTTROL_BR.rp.TableName = "BPTTROL";
        BPTTROL_BR.rp.where = "IN_FLG LIKE :BPRTROL.KEY.IN_FLG "
            + "AND SYS_MMO LIKE :BPRTROL.KEY.SYS_MMO "
            + "AND TX_CD LIKE :BPRTROL.KEY.TX_CD "
            + "AND ATH_TYP LIKE :BPRTROL.KEY.ATH_TYP "
            + "AND ROLE_CD >= :BPRTROL.KEY.ROLE_CD";
        BPTTROL_BR.rp.order = "ROLE_CD";
        IBS.STARTBR(SCCGWA, BPRTROL, this, BPTTROL_BR);
    }
    public void T000_STARTBR_BPTTROL_02() throws IOException,SQLException,Exception {
        BPTTROL_BR.rp = new DBParm();
        BPTTROL_BR.rp.TableName = "BPTTROL";
        IBS.STARTBR(SCCGWA, BPRTROL, BPTTROL_BR);
    }
    public void T000_READNEXT_BPTTROL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTROL, this, BPTTROL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTROB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTROB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTROL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTROL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTROB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTROB = ");
            CEP.TRC(SCCGWA, BPCRTROB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
