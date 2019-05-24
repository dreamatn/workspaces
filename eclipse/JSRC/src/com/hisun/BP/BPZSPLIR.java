package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPLIR {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTAPLI_RD;
    DBParm BPTADTL_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSPLIR";
    String K_TBL_APLI = "BPTAPLI ";
    String K_TBL_ADTL = "BPTADTL ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_APPD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPRAPLI BPRAPLI1;
    BPCSPLIR BPCSPLIR;
    public void MP(SCCGWA SCCGWA, BPCSPLIR BPCSPLIR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPLIR = BPCSPLIR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPLIR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRAPLI1 = (BPRADTL) BPCSPLIR.POINTER;
        IBS.init(SCCGWA, BPCSPLIR.RC);
        CEP.TRC(SCCGWA, BPCSPLIR.LEN);
        IBS.CLONE(SCCGWA, BPRAPLI1, BPRAPLI);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPLI1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRADTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSPLIR.FUNC == 'B') {
            B100_CREATE_APLI_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLIR.FUNC == 'R') {
            B100_CREATE_ADTL_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLIR.FUNC == 'Q') {
            B400_QUERY_APLI_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSPLIR.FUNC == 'E') {
            B400_QUERY_ADTL_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSPLIR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CREATE_APLI_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTAPLI();
        if (pgmRtn) return;
    }
    public void B100_CREATE_ADTL_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTADTL();
        if (pgmRtn) return;
    }
    public void B400_QUERY_APLI_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        T000_READ_BPTAPLI();
        if (pgmRtn) return;
    }
    public void B400_QUERY_ADTL_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        T000_READ_BPTADTL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.READ(SCCGWA, BPRAPLI, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSPLIR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSPLIR.RETURN_INFO = 'N';
        } else {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
        CEP.TRC(SCCGWA, BPCSPLIR.RC);
    }
    public void T000_READ_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        IBS.READ(SCCGWA, BPRADTL, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSPLIR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSPLIR.RETURN_INFO = 'N';
        } else {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
        CEP.TRC(SCCGWA, BPCSPLIR.RC);
    }
    public void T000_WRITE_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.WRITE(SCCGWA, BPRAPLI, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSPLIR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            BPCSPLIR.RETURN_INFO = 'D';
        } else {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_WRITE_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        BPTADTL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRADTL, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            BPCSPLIR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            BPCSPLIR.RETURN_INFO = 'D';
        } else {
            CEP.TRC(SCCGWA, "OTH");
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
