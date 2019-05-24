package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRUSER {
    brParm BPTUSER_BR = new brParm();
    DBParm BPTUSER_RD;
    String WS_USER_ID = " ";
    String WS_SEQ_NO = " ";
    int WS_DATE = 0;
    int WS_TIME = 0;
    String TBL_BPTUSER = "BPTUSER";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRUSER BPRUSER = new BPRUSER();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCRUSER BPCRUSER;
    BPRUSER BPRUSER1;
    public void MP(SCCGWA SCCGWA, BPCRUSER BPCRUSER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRUSER = BPCRUSER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRUSER return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRUSER.RC);
        BPRUSER1 = (BPRUSER) BPCRUSER.INFO.POINTER;
        IBS.init(SCCGWA, BPRUSER);
        IBS.CLONE(SCCGWA, BPRUSER1, BPRUSER);
        CEP.TRC(SCCGWA, BPRUSER);
        CEP.TRC(SCCGWA, BPCRUSER);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRUSER.INFO.ID);
        WS_USER_ID = BPCRUSER.INFO.ID;
        if (BPCRUSER.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRUSER.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
        } else if (BPCRUSER.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
        } else if (BPCRUSER.INFO.FUNC == 'B') {
            B070_BROWSE_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRUSER.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRUSER, BPRUSER1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTUSER();
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTUSER();
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTUSER();
    }
    public void B070_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRUSER.INFO.OPT == 'S') {
            T000_STARTBR_BPTUSER();
        } else if (BPCRUSER.INFO.OPT == 'N') {
            T000_READNEXT_BPTUSER();
        } else if (BPCRUSER.INFO.OPT == 'E') {
            T000_ENDBR_BPTUSER();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRUSER.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_BPTUSER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRUSER);
        CEP.TRC(SCCGWA, WS_USER_ID);
        if (BPCRUSER.INFO.UPD_FLAG == 'Y') {
            BPTUSER_BR.rp = new DBParm();
            BPTUSER_BR.rp.TableName = "BPTUSER";
            BPTUSER_BR.rp.where = "USER_ID = :WS_USER_ID";
            BPTUSER_BR.rp.upd = true;
            IBS.STARTBR(SCCGWA, BPRUSER, this, BPTUSER_BR);
        } else {
            BPTUSER_BR.rp = new DBParm();
            BPTUSER_BR.rp.TableName = "BPTUSER";
            BPTUSER_BR.rp.where = "USER_ID = :WS_USER_ID";
            IBS.STARTBR(SCCGWA, BPRUSER, this, BPTUSER_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUSER.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRUSER.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTUSER;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_BPTUSER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRUSER, this, BPTUSER_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUSER.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRUSER.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTUSER;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_BPTUSER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "END BROWSE");
        IBS.ENDBR(SCCGWA, BPTUSER_BR);
    }
    public void T000_WRITE_BPTUSER() throws IOException,SQLException,Exception {
        BPTUSER_RD = new DBParm();
        BPTUSER_RD.TableName = "BPTUSER";
        IBS.WRITE(SCCGWA, BPRUSER, BPTUSER_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUSER.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRUSER.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTUSER;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_BPTUSER() throws IOException,SQLException,Exception {
        BPTUSER_RD = new DBParm();
        BPTUSER_RD.TableName = "BPTUSER";
        IBS.REWRITE(SCCGWA, BPRUSER, BPTUSER_RD);
    }
    public void T000_DELETE_BPTUSER() throws IOException,SQLException,Exception {
        BPTUSER_RD = new DBParm();
        BPTUSER_RD.TableName = "BPTUSER";
        IBS.DELETE(SCCGWA, BPRUSER, BPTUSER_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRUSER.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRUSER = ");
            CEP.TRC(SCCGWA, BPCRUSER);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
