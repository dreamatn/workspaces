package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZRDDIC {
    DBParm BPTDDIC_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "SMZRDDIC";
    String K_TBL_DDIC = "BPTDDIC ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDDIC BPRDDIC = new BPRDDIC();
    SCCGWA SCCGWA;
    SMCTDICM SMCTDICM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRDDIC BPRDDICA;
    public void MP(SCCGWA SCCGWA, SMCTDICM SMCTDICM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCTDICM = SMCTDICM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZRDDIC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRDDICA = (BPRDDIC) SMCTDICM.INFO.POINTER;
        IBS.init(SCCGWA, BPRDDIC);
        IBS.CLONE(SCCGWA, BPRDDICA, BPRDDIC);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCTDICM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SMCTDICM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRDDIC, BPRDDICA);
        } else if (SMCTDICM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRDDIC, BPRDDICA);
            CEP.TRC(SCCGWA, "TDICM-QUERY-END");
        } else if (SMCTDICM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SMCTDICM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCTDICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDDIC();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDDIC_UPD();
        if (pgmRtn) return;
        if (WS_TBL_BANK_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DDIC_NOTFND, SMCTDICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRDDIC.KEY.NAME);
        T000_READ_BPTDDIC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B030-END");
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDDIC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDDIC();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        IBS.READ(SCCGWA, BPRDDIC, BPTDDIC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SMCTDICM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            SMCTDICM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        BPTDDIC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRDDIC, BPTDDIC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SMCTDICM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SMCTDICM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DDIC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTDDIC_UPD() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        BPTDDIC_RD.upd = true;
        IBS.READ(SCCGWA, BPRDDIC, BPTDDIC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_BANK_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_BANK_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        IBS.REWRITE(SCCGWA, BPRDDIC, BPTDDIC_RD);
    }
    public void T000_DELETE_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        IBS.DELETE(SCCGWA, BPRDDIC, BPTDDIC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
