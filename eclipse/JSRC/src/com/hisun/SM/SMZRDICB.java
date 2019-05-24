package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZRDICB {
    brParm BPTDDIC_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "SMZRDDIC";
    String K_TBL_DDIC = "BPTDDIC ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDDIC BPRDDIC = new BPRDDIC();
    SCCGWA SCCGWA;
    SMCTDICB SMCTDICB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRDDIC BPRDDICA;
    public void MP(SCCGWA SCCGWA, SMCTDICB SMCTDICB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCTDICB = SMCTDICB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZRDICB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRDDICA = (BPRDDIC) SMCTDICB.INFO.POINTER;
        IBS.init(SCCGWA, BPRDDIC);
        IBS.CLONE(SCCGWA, BPRDDICA, BPRDDIC);
        IBS.init(SCCGWA, SMCTDICB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCTDICB.INFO.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (SMCTDICB.INFO.FUNC == 'A') {
            B020_STARTBR_ALL_PROC();
            if (pgmRtn) return;
        } else if (SMCTDICB.INFO.FUNC == 'R') {
            B030_READNEXT_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRDDIC, BPRDDICA);
        } else if (SMCTDICB.INFO.FUNC == 'E') {
            B040_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCTDICB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTDDIC();
        if (pgmRtn) return;
    }
    public void B020_STARTBR_ALL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL_BPTDDIC();
        if (pgmRtn) return;
    }
    public void B030_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTDDIC();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTDDIC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_ALL_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_BR.rp = new DBParm();
        BPTDDIC_BR.rp.TableName = "BPTDDIC";
        BPTDDIC_BR.rp.order = "NAME";
        IBS.STARTBR(SCCGWA, BPRDDIC, BPTDDIC_BR);
    }
    public void T000_STARTBR_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_BR.rp = new DBParm();
        BPTDDIC_BR.rp.TableName = "BPTDDIC";
        BPTDDIC_BR.rp.where = "NAME LIKE :BPRDDIC.KEY.NAME "
            + "OR CLASSIFY_CODE LIKE :BPRDDIC.CLASSIFY_CODE";
        BPTDDIC_BR.rp.order = "NAME, CLASSIFY_CODE";
        IBS.STARTBR(SCCGWA, BPRDDIC, this, BPTDDIC_BR);
    }
    public void T000_READNEXT_BPTDDIC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRDDIC, this, BPTDDIC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SMCTDICB.INFO.FUNC = 'E';
        } else {
        }
    }
    public void T000_ENDBR_BPTDDIC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTDDIC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
