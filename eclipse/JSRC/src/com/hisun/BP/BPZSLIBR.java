package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLIBR {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTALIB_RD;
    DBParm BPTAPAR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSLIBR";
    String K_TBL_ALIB = "BPTALIB ";
    String K_TBL_APAR = "BPTAPAR ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_APPD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPRALIB BPRALIB1;
    BPCSLIBR BPCSLIBR;
    public void MP(SCCGWA SCCGWA, BPCSLIBR BPCSLIBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLIBR = BPCSLIBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLIBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRALIB1 = (BPRAPAR) BPCSLIBR.POINTER;
        IBS.init(SCCGWA, BPCSLIBR.RC);
        IBS.CLONE(SCCGWA, BPRALIB1, BPRALIB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRALIB1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRAPAR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSLIBR.FUNC == 'B') {
            B100_CREATE_ALIB_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSLIBR.FUNC == 'R') {
            B100_CREATE_APAR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSLIBR.FUNC == 'Q') {
            B400_QUERY_ALIB_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSLIBR.FUNC == 'E') {
            B400_QUERY_APAR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSLIBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CREATE_ALIB_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTALIB();
        if (pgmRtn) return;
    }
    public void B100_CREATE_APAR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTAPAR();
        if (pgmRtn) return;
    }
    public void B400_QUERY_ALIB_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        T000_READ_BPTALIB();
        if (pgmRtn) return;
    }
    public void B400_QUERY_APAR_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        IBS.READ(SCCGWA, BPRALIB, BPTALIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSLIBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSLIBR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSLIBR.RC);
    }
    public void T000_READ_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        IBS.READ(SCCGWA, BPRAPAR, BPTAPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSLIBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSLIBR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPAR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSLIBR.RC);
    }
    public void T000_WRITE_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        IBS.WRITE(SCCGWA, BPRALIB, BPTALIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSLIBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCSLIBR.RETURN_INFO = 'D';
        } else {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTAPAR() throws IOException,SQLException,Exception {
        BPTAPAR_RD = new DBParm();
        BPTAPAR_RD.TableName = "BPTAPAR";
        BPTAPAR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRAPAR, BPTAPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            BPCSLIBR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            BPCSLIBR.RETURN_INFO = 'D';
        } else {
            CEP.TRC(SCCGWA, "OTH");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPAR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
