package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTRGDM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTRGND_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTRGDM";
    String K_TBL_RGND = "BPTRGND ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_RGND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGND BPRRGND = new BPRRGND();
    SCCGWA SCCGWA;
    BPRRGND BPRRGND1;
    BPCRRGDM BPCRRGDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRRGDM BPCRRGDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRGDM = BPCRRGDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTRGDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRGND1 = (BPRRGND) BPCRRGDM.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRGND);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRRGND1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRRGND1, BPRRGND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRGDM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGDM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGDM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGDM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRGDM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRRGDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTRGND();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRGND_UPD();
        if (pgmRtn) return;
        if (WS_TBL_RGND_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRRGDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRRGND);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRRGND, BPRRGND1);
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRGND();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRRGND);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRRGND, BPRRGND1);
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTRGND();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTRGND();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTRGND() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        IBS.READ(SCCGWA, BPRRGND, BPTRGND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRGDM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRGDM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTRGND() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        BPTRGND_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRRGND, BPTRGND_RD);
        CEP.TRC(SCCGWA, "WRITE TABLE:");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRGDM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRGDM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_RGND;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRGND_UPD() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        BPTRGND_RD.upd = true;
        IBS.READ(SCCGWA, BPRRGND, BPTRGND_RD);
        CEP.TRC(SCCGWA, "READ TABLE:");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_RGND_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_RGND_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTRGND() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        IBS.REWRITE(SCCGWA, BPRRGND, BPTRGND_RD);
    }
    public void T000_DELETE_BPTRGND() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        IBS.DELETE(SCCGWA, BPRRGND, BPTRGND_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRGDM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRRGDM = ");
            CEP.TRC(SCCGWA, BPCRRGDM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
