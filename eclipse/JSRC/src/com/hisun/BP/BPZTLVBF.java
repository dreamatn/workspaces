package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTLVBF {
    DBParm BPTTLVB_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTLVBF";
    String K_TBL_CLIB = "BPTTLVB ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_TLVB_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLVB BPRTLVB = new BPRTLVB();
    SCCGWA SCCGWA;
    BPRTLVB BPRTLVB1;
    BPCTLVBF BPCTLVBF;
    public void MP(SCCGWA SCCGWA, BPCTLVBF BPCTLVBF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTLVBF = BPCTLVBF;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, BPRTLVB);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTLVBF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLVB1 = (BPRTLVB) BPCTLVBF.POINTER;
        IBS.init(SCCGWA, BPCTLVBF.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTLVB1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTLVB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTLVBF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'I') {
            B600_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'T') {
            B600_QUERY_TLR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLVBF.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTLVBF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLVB, BPRTLVB1);
        CEP.TRC(SCCGWA, BPRTLVB);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTLVB_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        T000_READ_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B600_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        T010_READ_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B600_QUERY_TLR_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        T010_READ_BPTTLVB_TLR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        IBS.READ(SCCGWA, BPRTLVB, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLVBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLVBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND, BPCTLVBF.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTLVBF.RC);
    }
    public void T010_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "( PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR ) "
            + "OR ( PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR1 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR2 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR3 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLVBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLVBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND, BPCTLVBF.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTLVBF.RC);
    }
    public void T010_READ_BPTTLVB_TLR() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR";
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLVBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLVBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND, BPCTLVBF.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTLVBF.RC);
    }
    public void T000_WRITE_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTLVB, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLVBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTLVBF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CLIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTLVB_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLVB, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLVBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLVBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND, BPCTLVBF.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        IBS.REWRITE(SCCGWA, BPRTLVB, BPTTLVB_RD);
    }
    public void T000_DELETE_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        IBS.DELETE(SCCGWA, BPRTLVB, BPTTLVB_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTLVBF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTLVBF = ");
            CEP.TRC(SCCGWA, BPCTLVBF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
