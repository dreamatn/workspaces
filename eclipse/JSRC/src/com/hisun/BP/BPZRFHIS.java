package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFHIS {
    brParm BPTFEHIS_BR = new brParm();
    DBParm BPTFEHIS_RD;
    String WS_ERR_MSG = " ";
    double WS_NEW_RATE = 0;
    double WS_OLD_RATE = 0;
    char WS_TABLE_REC = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRICSP BPRICSP = new BPRICSP();
    BPRICSP BPRICSPN = new BPRICSP();
    BPRICSP BPRICSPO = new BPRICSP();
    BPREXRD BPREXRD = new BPREXRD();
    BPRTQP BPRTQP = new BPRTQP();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSFHIS BPCRFHIS;
    BPRFEHIS BPRLEHIS;
    public void MP(SCCGWA SCCGWA, BPCSFHIS BPCRFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFHIS = BPCRFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRFHIS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "3333");
        CEP.TRC(SCCGWA, BPCRFHIS.INP_DATA.DATA_LEN);
        BPRLEHIS = (BPRFEHIS) BPCRFHIS.INP_DATA.DATA_PTR;
        IBS.init(SCCGWA, BPRFEHIS);
        CEP.TRC(SCCGWA, BPCRFHIS.INP_DATA.DATA_LEN);
        IBS.CLONE(SCCGWA, BPRLEHIS, BPRFEHIS);
        BPCRFHIS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFHIS.RC.RC_CODE = 0;
        BPCRFHIS.RET_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B002_MAIN_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B002_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRFHIS.FUNC);
        if (BPCRFHIS.FUNC == 'A') {
            T000_WRITE_TABLE();
        } else if (BPCRFHIS.FUNC == 'R') {
            T000_READUPD_TABLE();
        } else if (BPCRFHIS.FUNC == 'U') {
            T000_REWRITE_TABLE();
        } else if (BPCRFHIS.FUNC == 'B') {
            T000_STARTBR_TABLE();
        } else if (BPCRFHIS.FUNC == 'N') {
            T000_READNEXT_TABLE();
        } else if (BPCRFHIS.FUNC == 'E') {
            T000_ENDBR_TABLE();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRFHIS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRFEHIS, BPRLEHIS);
        CEP.TRC(SCCGWA, BPCRFHIS.RET_INFO);
    }
    public void T000_STARTBR_TABLE() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.eqWhere = "AC_DT,JRNNO";
        IBS.STARTBR(SCCGWA, BPRFEHIS, BPTFEHIS_BR);
    }
    public void T000_READNEXT_TABLE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFHIS.RET_INFO = 'N';
        }
    }
    public void T000_ENDBR_TABLE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEHIS_BR);
    }
    public void T000_READUPD_TABLE() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFHIS.RET_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFHIS.RET_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFHIS.RET_INFO = 'N';
        } else {
            BPCRFHIS.RET_INFO = 'O';
        }
    }
    public void T000_REWRITE_TABLE() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.REWRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
    }
    public void T000_WRITE_TABLE() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFHIS.RET_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFHIS.RET_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            BPCRFHIS.RET_INFO = 'R';
        } else {
            BPCRFHIS.RET_INFO = 'O';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
