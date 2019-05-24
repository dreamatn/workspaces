package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTMOVF {
    DBParm BPTCMOV_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTMOVF";
    String K_TBL_CMOV = "BPTCMOV ";
    char WS_TBL_CMOV_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCMOV BPRCMOV = new BPRCMOV();
    SCCGWA SCCGWA;
    BPCRMOVF BPCRMOVF;
    BPRCMOV BPRCMOV1;
    public void MP(SCCGWA SCCGWA, BPCRMOVF BPCRMOVF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMOVF = BPCRMOVF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTMOVF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCMOV1 = (BPRCMOV) BPCRMOVF.POINTER;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCMOV1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCMOV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMOVF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOVF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOVF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOVF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRMOVF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCMOV, BPRCMOV1);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCMOV_UPD();
        if (pgmRtn) return;
        if (WS_TBL_CMOV_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CMOV_NOTFND, BPCRMOVF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCMOV.KEY);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.MOV_DT);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CCY);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CASH_TYP);
        T000_READ_BPTCMOV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "11111");
        CEP.TRC(SCCGWA, BPRCMOV.BV_CODE);
        if (WS_TBL_CMOV_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CMOV_NOTFND, BPCRMOVF.RC);
            CEP.TRC(SCCGWA, "ZHAOBUDAO");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_RD = new DBParm();
        BPTCMOV_RD.TableName = "BPTCMOV";
        IBS.READ(SCCGWA, BPRCMOV, BPTCMOV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CMOV_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CMOV_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_RD = new DBParm();
        BPTCMOV_RD.TableName = "BPTCMOV";
        BPTCMOV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCMOV, BPTCMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMOVF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRMOVF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CMOV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCMOV_UPD() throws IOException,SQLException,Exception {
        BPTCMOV_RD = new DBParm();
        BPTCMOV_RD.TableName = "BPTCMOV";
        BPTCMOV_RD.upd = true;
        IBS.READ(SCCGWA, BPRCMOV, BPTCMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CMOV_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CMOV_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_RD = new DBParm();
        BPTCMOV_RD.TableName = "BPTCMOV";
        IBS.REWRITE(SCCGWA, BPRCMOV, BPTCMOV_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMOVF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRMOVF = ");
            CEP.TRC(SCCGWA, BPCRMOVF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
