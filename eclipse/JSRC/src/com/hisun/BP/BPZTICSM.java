package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTICSM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTICSP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTICSM";
    String K_TBL_ICSP = "BPTICSP ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRICSP BPRICSP = new BPRICSP();
    BPREXRD BPREXRX = new BPREXRD();
    SCCGWA SCCGWA;
    BPCTICSM BPCTICSM;
    BPRICSP BPRICSP1;
    public void MP(SCCGWA SCCGWA, BPCTICSM BPCTICSM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTICSM = BPCTICSM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTICSM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRICSP1 = (BPRICSP) BPCTICSM.POINTER;
        IBS.init(SCCGWA, BPRICSP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRICSP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRICSP1, BPRICSP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELPME");
        if (BPCTICSM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTICSM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTICSM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTICSM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTICSM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTICSM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRICSP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRICSP, BPRICSP1);
        CEP.TRC(SCCGWA, "5555");
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTICSP();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTICSP() throws IOException,SQLException,Exception {
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        BPTICSP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRICSP, BPTICSP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTICSM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTICSM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ICSP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTICSP_UPD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTICSP_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        BPTICSP_RD.upd = true;
        IBS.READ(SCCGWA, BPRICSP, BPTICSP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTICSM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTICSM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTICSP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111");
    }
    public void T000_READ_BPTICSP() throws IOException,SQLException,Exception {
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        IBS.READ(SCCGWA, BPRICSP, BPTICSP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTICSM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTICSM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTICSP();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_BPTICSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        IBS.REWRITE(SCCGWA, BPRICSP, BPTICSP_RD);
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTICSP();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTICSP() throws IOException,SQLException,Exception {
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        IBS.DELETE(SCCGWA, BPRICSP, BPTICSP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTICSM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTICSM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTICSM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTICSM = ");
            CEP.TRC(SCCGWA, BPCTICSM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
