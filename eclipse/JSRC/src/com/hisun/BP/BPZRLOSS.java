package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRLOSS {
    DBParm BPTLOSS_RD;
    boolean pgmRtn = false;
    String K_TBL_LOSS = "BPTLOSS ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_LOSS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLOSS BPRLOSS = new BPRLOSS();
    SCCGWA SCCGWA;
    BPCRLOSS BPCRLOSS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRLOSS BPRIOSS;
    public void MP(SCCGWA SCCGWA, BPCRLOSS BPCRLOSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRLOSS = BPCRLOSS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRLOSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRIOSS = (BPRLOSS) BPCRLOSS.INFO.POINTER;
        IBS.init(SCCGWA, BPCRLOSS.RC);
        IBS.init(SCCGWA, BPRLOSS);
        IBS.CLONE(SCCGWA, BPRIOSS, BPRLOSS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
        if (BPCRLOSS.INFO.FUNC == 'C' 
            || BPCRLOSS.INFO.FUNC == 'R') {
            if (BPRLOSS.KEY.LOS_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRLOSS.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRLOSS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRLOSS.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRLOSS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRLOSS, BPRIOSS);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTLOSS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLOSS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_LOSS_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOSS_NOTFND, BPCRLOSS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTLOSS();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTLOSS() throws IOException,SQLException,Exception {
        BPTLOSS_RD = new DBParm();
        BPTLOSS_RD.TableName = "BPTLOSS";
        BPTLOSS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRLOSS, BPTLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRLOSS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRLOSS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_LOSS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTLOSS_UPD() throws IOException,SQLException,Exception {
        BPTLOSS_RD = new DBParm();
        BPTLOSS_RD.TableName = "BPTLOSS";
        BPTLOSS_RD.upd = true;
        IBS.READ(SCCGWA, BPRLOSS, BPTLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_LOSS_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_LOSS_FLAG = 'D';
            BPCRLOSS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTLOSS() throws IOException,SQLException,Exception {
        BPTLOSS_RD = new DBParm();
        BPTLOSS_RD.TableName = "BPTLOSS";
        IBS.REWRITE(SCCGWA, BPRLOSS, BPTLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRLOSS.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_LOSS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRLOSS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRLOSS = ");
            CEP.TRC(SCCGWA, BPCRLOSS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
