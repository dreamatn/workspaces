package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTQPHM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTQPH_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTQPHM";
    String K_TBL_TQPH = "BPTTQPH ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    SCCGWA SCCGWA;
    BPCTQPHM BPCTQPHM;
    BPRTQPH BPRTQPH1;
    public void MP(SCCGWA SCCGWA, BPCTQPHM BPCTQPHM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTQPHM = BPCTQPHM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTQPHM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTQPH1 = (BPRTQPH) BPCTQPHM.INFO.POINTER;
        IBS.init(SCCGWA, BPRTQPH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQPH1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRTQPH1, BPRTQPH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTQPHM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQPHM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQPHM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQPHM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQPHM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTQPHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQPH);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRTQPH, BPRTQPH1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTQPH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTQPH_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTQPH();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTQPH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTQPH();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        IBS.READ(SCCGWA, BPRTQPH, BPTTQPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQPHM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTQPHM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        BPTTQPH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTQPH, BPTTQPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQPHM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTQPHM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TQPH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTQPH_UPD() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        BPTTQPH_RD.upd = true;
        IBS.READ(SCCGWA, BPRTQPH, BPTTQPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQPHM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTQPHM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        IBS.REWRITE(SCCGWA, BPRTQPH, BPTTQPH_RD);
    }
    public void T000_DELETE_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        IBS.DELETE(SCCGWA, BPRTQPH, BPTTQPH_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTQPHM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTQPHM = ");
            CEP.TRC(SCCGWA, BPCTQPHM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
