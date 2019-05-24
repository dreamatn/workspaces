package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRUNIT {
    DBParm BPTUNIT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRUNIT";
    String K_TBL_UNIT = "BPTUNIT ";
    String WS_TEST_INF = " ";
    int WS_REC_COUNT = 0;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_UNIT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRUNIT BPRUNIT = new BPRUNIT();
    SCCGWA SCCGWA;
    BPCRUNIT BPCRUNIT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRUNIT BPRUNIT1;
    public void MP(SCCGWA SCCGWA, BPCRUNIT BPCRUNIT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRUNIT = BPCRUNIT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRUNIT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRUNIT1 = (BPRUNIT) BPCRUNIT.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CLONE(SCCGWA, BPRUNIT1, BPRUNIT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRUNIT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRUNIT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRUNIT.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRUNIT.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCRUNIT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRUNIT, BPRUNIT1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRUNIT.KEY.TX_CODE);
        CEP.TRC(SCCGWA, BPRUNIT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, BPRUNIT.KEY.SERV_CODE);
        CEP.TRC(SCCGWA, BPRUNIT.KEY.UNIT_NO);
        CEP.TRC(SCCGWA, BPRUNIT.UNIT_STS);
        T000_GROUP_BPTUNIT_01();
        if (pgmRtn) return;
        WS_REC_COUNT += 1;
        BPRUNIT.KEY.UNIT_NO = (short) WS_REC_COUNT;
        T000_WRITE_BPTUNIT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRUNIT.KEY.UNIT_NO);
        CEP.TRC(SCCGWA, BPCRUNIT);
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTUNIT_UPD();
        if (pgmRtn) return;
        if (WS_TBL_UNIT_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBL_NOTFND, BPCRUNIT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTUNIT();
        if (pgmRtn) return;
        if (WS_TBL_UNIT_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBL_NOTFND, BPCRUNIT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRUNIT.REDEFINES20.LINES_LST_DATA[1-1].OFFSET);
        CEP.TRC(SCCGWA, BPRUNIT.LINES_CNT);
        T000_REWRITE_BPTUNIT();
        if (pgmRtn) return;
    }
    public void T000_GROUP_BPTUNIT_01() throws IOException,SQLException,Exception {
        BPTUNIT_RD = new DBParm();
        BPTUNIT_RD.TableName = "BPTUNIT";
        BPTUNIT_RD.set = "WS-REC-COUNT=IFNULL(MAX(UNIT_NO),0)";
        BPTUNIT_RD.where = "TX_CODE = :BPRUNIT.KEY.TX_CODE";
        IBS.GROUP(SCCGWA, BPRUNIT, this, BPTUNIT_RD);
    }
    public void T000_READ_BPTUNIT() throws IOException,SQLException,Exception {
        BPTUNIT_RD = new DBParm();
        BPTUNIT_RD.TableName = "BPTUNIT";
        IBS.READ(SCCGWA, BPRUNIT, BPTUNIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUNIT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRUNIT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTUNIT() throws IOException,SQLException,Exception {
        BPTUNIT_RD = new DBParm();
        BPTUNIT_RD.TableName = "BPTUNIT";
        BPTUNIT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRUNIT, BPTUNIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUNIT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRUNIT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_UNIT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTUNIT_UPD() throws IOException,SQLException,Exception {
        BPTUNIT_RD = new DBParm();
        BPTUNIT_RD.TableName = "BPTUNIT";
        BPTUNIT_RD.upd = true;
        IBS.READ(SCCGWA, BPRUNIT, BPTUNIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_UNIT_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_UNIT_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTUNIT() throws IOException,SQLException,Exception {
        BPTUNIT_RD = new DBParm();
        BPTUNIT_RD.TableName = "BPTUNIT";
        IBS.REWRITE(SCCGWA, BPRUNIT, BPTUNIT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRUNIT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRUNIT = ");
            CEP.TRC(SCCGWA, BPCRUNIT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
