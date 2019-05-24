package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPUBR {
    brParm BPTPARM_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTPARM = "BPTPARM";
    int WS_BR = 0;
    char WS_PARM_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARM BPRPARM = new BPRPARM();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPUBR AICPUBR;
    public void MP(SCCGWA SCCGWA, AICPUBR AICPUBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPUBR = AICPUBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPUBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPUBR.BR);
        IBS.init(SCCGWA, AIRPAI1);
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = "PAI01";
        T000_STARTBR_BPTPARM();
        if (pgmRtn) return;
        T000_READNEXT_BPTPARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PARM_FLG);
        if (WS_PARM_FLG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (WS_PARM_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, AIRPAI1.DATA_TXT);
                WS_BR = AIRPAI1.DATA_TXT.DATA_INF.BR;
            }
        }
        AICPUBR.EXT_FLG = 'N';
        while (WS_PARM_FLG != 'N') {
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            R000_CHECK_BR_EXT();
            if (pgmRtn) return;
            T000_READNEXT_BPTPARM();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTPARM();
        if (pgmRtn) return;
        if (AICPUBR.EXT_FLG == 'N') {
            AICPUBR.BR = WS_BR;
        }
        CEP.TRC(SCCGWA, "OUTPUT  INFO");
        CEP.TRC(SCCGWA, AICPUBR.BR);
        CEP.TRC(SCCGWA, AICPUBR.EXT_FLG);
    }
    public void R000_CHECK_BR_EXT() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, AIRPAI1.DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI1.DATA_TXT.DATA_INF.BR);
        if (AICPUBR.BR == AIRPAI1.DATA_TXT.DATA_INF.BR) {
            AICPUBR.EXT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, AICPUBR.EXT_FLG);
    }
    public void T000_STARTBR_BPTPARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR BPTPARM");
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.where = "TYPE = :BPRPARM.KEY.TYPE "
            + "AND CODE LIKE 'BR%'";
        BPTPARM_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T000_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READNEXT BPTPARM");
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PARM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PARM_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTPARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ENDBR BPTPARM");
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
