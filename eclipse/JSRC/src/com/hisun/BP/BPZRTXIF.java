package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTXIF {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTXIF_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTXIF";
    String K_TBL_TXIF = "BPTTXIF ";
    char WS_TXIF_FOUND_FLG = 'N';
    char WS_TXIF_FIRST_FLG = 'N';
    char WS_LAST_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTXIF BPRTXIF = new BPRTXIF();
    SCCGWA SCCGWA;
    BPCRTXIF BPCRTXIF;
    BPRTXIF BPRTXIF1;
    public void MP(SCCGWA SCCGWA, BPCRTXIF BPCRTXIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTXIF = BPCRTXIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTXIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTXIF1 = (BPRTXIF) BPCRTXIF.INFO.POINTER;
        IBS.init(SCCGWA, BPRTXIF);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTXIF1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRTXIF1, BPRTXIF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTXIF.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
            WS_LAST_FUNC_FLG = 'C';
        } else if (BPCRTXIF.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            WS_LAST_FUNC_FLG = 'R';
        } else if (BPCRTXIF.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
            WS_LAST_FUNC_FLG = 'M';
        } else if (BPCRTXIF.INFO.FUNC == 'D') {
            B022_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            WS_LAST_FUNC_FLG = 'D';
        } else if (BPCRTXIF.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            WS_LAST_FUNC_FLG = 'Q';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTXIF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTXIF, BPRTXIF1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTXIF();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTXIF_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTXIF();
        if (pgmRtn) return;
    }
    public void B022_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTXIF();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTXIF();
        if (pgmRtn) return;
        WS_TXIF_FIRST_FLG = 'N';
    }
    public void T000_READ_BPTTXIF() throws IOException,SQLException,Exception {
        BPTTXIF_RD = new DBParm();
        BPTTXIF_RD.TableName = "BPTTXIF";
        IBS.READ(SCCGWA, BPRTXIF, BPTTXIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTXIF.RETURN_INFO = 'F';
            WS_TXIF_FOUND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTXIF.RETURN_INFO = 'N';
            WS_TXIF_FOUND_FLG = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "CGBYWSTRUE1");
    }
    public void T000_WRITE_BPTTXIF() throws IOException,SQLException,Exception {
        BPTTXIF_RD = new DBParm();
        BPTTXIF_RD.TableName = "BPTTXIF";
        BPTTXIF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTXIF, BPTTXIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTXIF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTXIF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TXIF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTXIF_UPD() throws IOException,SQLException,Exception {
        BPTTXIF_RD = new DBParm();
        BPTTXIF_RD.TableName = "BPTTXIF";
        BPTTXIF_RD.upd = true;
        IBS.READ(SCCGWA, BPRTXIF, BPTTXIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTXIF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTXIF.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, "CGBYWSTRUE2");
    }
    public void T000_REWRITE_BPTTXIF() throws IOException,SQLException,Exception {
        BPTTXIF_RD = new DBParm();
        BPTTXIF_RD.TableName = "BPTTXIF";
        IBS.REWRITE(SCCGWA, BPRTXIF, BPTTXIF_RD);
    }
    public void T000_DELETE_BPTTXIF() throws IOException,SQLException,Exception {
        BPTTXIF_RD = new DBParm();
        BPTTXIF_RD.TableName = "BPTTXIF";
        IBS.DELETE(SCCGWA, BPRTXIF, BPTTXIF_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTXIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTXIF = ");
            CEP.TRC(SCCGWA, BPCRTXIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
