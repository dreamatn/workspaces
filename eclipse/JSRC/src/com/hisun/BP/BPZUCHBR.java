package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCHBR {
    DBParm BPTORGI_RD;
    brParm BPTORGI_BR = new brParm();
    brParm BPTORGL_BR = new brParm();
    DBParm BPTORGL_RD;
    boolean pgmRtn = false;
    String K_TBL_ORGI = "BPTORGI ";
    String WS_ERR_MSG = " ";
    char WS_TBL_ORGI_FLAG = ' ';
    char WS_ORGL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGL BPRORGL = new BPRORGL();
    SCCGWA SCCGWA;
    BPCUCHBR BPCUCHBR;
    public void MP(SCCGWA SCCGWA, BPCUCHBR BPCUCHBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCHBR = BPCUCHBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCHBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCHBR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCUCHBR.FUNC == 'B') {
            B010_BRW_WHOLE_BR_CHG_INF();
            if (pgmRtn) return;
        } else if (BPCUCHBR.FUNC == 'Q') {
            B020_QUERY_WHOLE_BR_CHG_INF();
            if (pgmRtn) return;
        } else if (BPCUCHBR.FUNC == 'M') {
            B030_BACK_WHOLE_BR_CHG_INF();
            if (pgmRtn) return;
        } else if (BPCUCHBR.FUNC == 'F') {
            B040_QUERY_WHOLE_BR_CHG_BYBR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_BRW_WHOLE_BR_CHG_INF() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_WHOLE_BR_CHG_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.INCO_DATE = BPCUCHBR.INCO_DATE;
        BPRORGI.ORGI_FLG = BPCUCHBR.ORGI_FLG;
        BPRORGI.ORGI_TYP = '3';
        T000_READ_BPTORGI();
        if (pgmRtn) return;
        if (WS_TBL_ORGI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_QUERY_WHOLE_BR_CHG_BYBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.INCO_OLD_BR = BPCUCHBR.OLD_BR;
        BPRORGI.ORGI_FLG = BPCUCHBR.ORGI_FLG;
        BPRORGI.ORGI_TYP = '3';
        T000_READ_BPTORGI_BYBR();
        if (pgmRtn) return;
        if (WS_TBL_ORGI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B030_BACK_WHOLE_BR_CHG_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.KEY.AC_DT = BPCUCHBR.AC_DT;
        BPRORGI.KEY.MN_JRN = BPCUCHBR.MN_JRN;
        T000_READ_BPTORGI_UPD();
        if (pgmRtn) return;
        if (WS_TBL_ORGI_FLAG == 'N') {
            BPRORGI.ORGI_FLG = '9';
            T000_REWRITE_BPTORGI();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.AC_DT = BPCUCHBR.AC_DT;
        BPRORGL.MN_JRN = BPCUCHBR.MN_JRN;
        T000_STARTBR_BPTORGL_UPD();
        if (pgmRtn) return;
        T000_READNEXT_BPTORGL();
        if (pgmRtn) return;
        while (WS_ORGL_FLAG != 'D') {
            BPRORGL.TX_FLG = 'O';
            T000_REWRITE_BPTORGL();
            if (pgmRtn) return;
            T000_READNEXT_BPTORGL();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTORGL();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCUCHBR.OLD_BR = BPRORGI.INCO_OLD_BR;
        BPCUCHBR.NEW_BR = BPRORGI.INCO_NEW_BR;
        BPCUCHBR.AC_DT = BPRORGI.KEY.AC_DT;
        BPCUCHBR.MN_JRN = BPRORGI.KEY.MN_JRN;
        BPCUCHBR.ORGI_TYP = BPRORGI.ORGI_TYP;
        BPCUCHBR.ORGI_FLG = BPRORGI.ORGI_FLG;
        BPCUCHBR.INCO_DATE = BPRORGI.INCO_DATE;
    }
    public void T000_READ_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        BPTORGI_RD.where = "ORGI_TYP = :BPRORGI.ORGI_TYP "
            + "AND INCO_DATE = :BPRORGI.INCO_DATE "
            + "AND ORGI_FLG = :BPRORGI.ORGI_FLG";
        IBS.READ(SCCGWA, BPRORGI, this, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGI_BYBR() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        BPTORGI_RD.where = "ORGI_TYP = :BPRORGI.ORGI_TYP "
            + "AND INCO_OLD_BR = :BPRORGI.INCO_OLD_BR "
            + "AND ORGI_FLG = :BPRORGI.ORGI_FLG";
        IBS.READ(SCCGWA, BPRORGI, this, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGI_UPD() throws IOException,SQLException,Exception {
        BPTORGI_BR.rp = new DBParm();
        BPTORGI_BR.rp.TableName = "BPTORGI";
        BPTORGI_BR.rp.where = "AC_DT = :BPRORGI.KEY.AC_DT "
            + "AND MN_JRN = :BPRORGI.KEY.MN_JRN";
        BPTORGI_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRORGI, this, BPTORGI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READUPD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        IBS.REWRITE(SCCGWA, BPRORGI, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTORGL_UPD() throws IOException,SQLException,Exception {
        BPTORGL_BR.rp = new DBParm();
        BPTORGL_BR.rp.TableName = "BPTORGL";
        BPTORGL_BR.rp.where = "AC_DT = :BPRORGL.AC_DT "
            + "AND MN_JRN = :BPRORGL.MN_JRN";
        BPTORGL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRORGL, this, BPTORGL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ORGL_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ORGL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTORGL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGL, this, BPTORGL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ORGL_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ORGL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTORGL() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        IBS.REWRITE(SCCGWA, BPRORGL, BPTORGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTORGL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGL_BR);
    }
    public void T000_STARTBR_BPTORGI() throws IOException,SQLException,Exception {
        if (BPRORGI.KEY.AC_DT != 0) {
            BPTORGI_BR.rp = new DBParm();
            BPTORGI_BR.rp.TableName = "BPTORGI";
            BPTORGI_BR.rp.where = "AC_DT = :BPRORGI.KEY.AC_DT";
            IBS.STARTBR(SCCGWA, BPRORGI, this, BPTORGI_BR);
        }
        if (BPRORGI.INCO_DATE != 0 
            && BPRORGI.ORGI_FLG != ' ') {
            BPTORGI_BR.rp = new DBParm();
            BPTORGI_BR.rp.TableName = "BPTORGI";
            BPTORGI_BR.rp.where = "INCO_DATE > :BPRORGI.INCO_DATE "
                + "AND ORGI_FLG = :BPRORGI.ORGI_FLG";
            IBS.STARTBR(SCCGWA, BPRORGI, this, BPTORGI_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
        }
    }
    public void T000_READNEXT_BPTORGI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGI, this, BPTORGI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
        }
    }
    public void T000_ENDBR_BPTORGI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGI_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCHBR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCUCHBR = ");
            CEP.TRC(SCCGWA, BPCUCHBR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
