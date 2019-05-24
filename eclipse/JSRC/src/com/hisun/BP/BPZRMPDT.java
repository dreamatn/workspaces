package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMPDT {
    DBParm BPTPDTP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRMPDT";
    String K_TBL_PDTP = "BPTPDTP ";
    String WS_TEST_INF = " ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_PDTP_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPDTP BPRPDTP = new BPRPDTP();
    SCCGWA SCCGWA;
    BPCRMPDT BPCRMPDT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRPDTP BPRPDTPL;
    public void MP(SCCGWA SCCGWA, BPCRMPDT BPCRMPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMPDT = BPCRMPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPDTPL = (BPRPDTP) BPCRMPDT.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPDTP);
        IBS.CLONE(SCCGWA, BPRPDTPL, BPRPDTP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMPDT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRPDTP, BPRPDTPL);
        } else if (BPCRMPDT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRPDTP, BPRPDTPL);
        } else if (BPCRMPDT.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRPDTP, BPRPDTPL);
        } else if (BPCRMPDT.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRPDTP, BPRPDTPL);
        } else if (BPCRMPDT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCRMPDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPDTP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDTP_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDTP();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPDTP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPDTP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTPDTP() throws IOException,SQLException,Exception {
        BPTPDTP_RD = new DBParm();
        BPTPDTP_RD.TableName = "BPTPDTP";
        IBS.READ(SCCGWA, BPRPDTP, BPTPDTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "1");
            BPCRMPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "2");
            BPCRMPDT.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "3");
        }
    }
    public void T000_WRITE_BPTPDTP() throws IOException,SQLException,Exception {
        BPTPDTP_RD = new DBParm();
        BPTPDTP_RD.TableName = "BPTPDTP";
        BPTPDTP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPDTP, BPTPDTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRMPDT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PDTP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPDTP_UPD() throws IOException,SQLException,Exception {
        BPTPDTP_RD = new DBParm();
        BPTPDTP_RD.TableName = "BPTPDTP";
        BPTPDTP_RD.upd = true;
        IBS.READ(SCCGWA, BPRPDTP, BPTPDTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTPDTP() throws IOException,SQLException,Exception {
        BPTPDTP_RD = new DBParm();
        BPTPDTP_RD.TableName = "BPTPDTP";
        IBS.REWRITE(SCCGWA, BPRPDTP, BPTPDTP_RD);
    }
    public void T000_DELETE_BPTPDTP() throws IOException,SQLException,Exception {
        BPTPDTP_RD = new DBParm();
        BPTPDTP_RD.TableName = "BPTPDTP";
        IBS.DELETE(SCCGWA, BPRPDTP, BPTPDTP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMPDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMPDT = ");
            CEP.TRC(SCCGWA, BPCRMPDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
