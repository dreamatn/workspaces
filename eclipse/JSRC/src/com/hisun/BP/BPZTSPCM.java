package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTSPCM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTSPCA_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTSPCM";
    String K_TBL_SPCA = "BPTSPCA ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSPCA BPRSPCA = new BPRSPCA();
    SCCGWA SCCGWA;
    BPCTSPCM BPCTSPCM;
    BPRSPCA BPRSPCA1;
    public void MP(SCCGWA SCCGWA, BPCTSPCM BPCTSPCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTSPCM = BPCTSPCM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTSPCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSPCA1 = (BPRSPCA) BPCTSPCM.INFO.POINTER;
        IBS.init(SCCGWA, BPRSPCA);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSPCA1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSPCA1, BPRSPCA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTSPCM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSPCM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSPCM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSPCM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSPCM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTSPCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSPCA);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSPCA, BPRSPCA1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTSPCA();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSPCA_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSPCA();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSPCA();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSPCA();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTSPCA() throws IOException,SQLException,Exception {
        BPTSPCA_RD = new DBParm();
        BPTSPCA_RD.TableName = "BPTSPCA";
        IBS.READ(SCCGWA, BPRSPCA, BPTSPCA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSPCM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTSPCM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTSPCA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CHK_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CCY);
        BPTSPCA_RD = new DBParm();
        BPTSPCA_RD.TableName = "BPTSPCA";
        BPTSPCA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRSPCA, BPTSPCA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSPCM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTSPCM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_SPCA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTSPCA_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CHK_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CCY);
        BPTSPCA_RD = new DBParm();
        BPTSPCA_RD.TableName = "BPTSPCA";
        BPTSPCA_RD.upd = true;
        IBS.READ(SCCGWA, BPRSPCA, BPTSPCA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSPCM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTSPCM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTSPCA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CHK_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSPCA.KEY.CCY);
        BPTSPCA_RD = new DBParm();
        BPTSPCA_RD.TableName = "BPTSPCA";
        IBS.REWRITE(SCCGWA, BPRSPCA, BPTSPCA_RD);
    }
    public void T000_DELETE_BPTSPCA() throws IOException,SQLException,Exception {
        BPTSPCA_RD = new DBParm();
        BPTSPCA_RD.TableName = "BPTSPCA";
        IBS.DELETE(SCCGWA, BPRSPCA, BPTSPCA_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTSPCM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTSPCM = ");
            CEP.TRC(SCCGWA, BPCTSPCM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
