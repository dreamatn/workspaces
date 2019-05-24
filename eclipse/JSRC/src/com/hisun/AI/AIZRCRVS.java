package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRCRVS {
    DBParm AITCRVS_RD;
    brParm AITCRVS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "AIZRCRVS";
    String K_TBL_FARM = "AITCRVS";
    String WS_TEMP_RECORD = " ";
    int WS_RCD_SEQ = 0;
    double WS_DB_STR_AMT = 0;
    double WS_DB_END_AMT = 0;
    int WS_DB_STR_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_THEIR_AC = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRCRVS AIRCRVS = new AIRCRVS();
    SCCGWA SCCGWA;
    AICRCRVS AICRCRVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIRCRVS AIRLCRVS;
    public void MP(SCCGWA SCCGWA, AICRCRVS AICRCRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRCRVS = AICRCRVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRCRVS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLCRVS = (AIRCRVS) AICRCRVS.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AIRCRVS);
        AICRCRVS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        AICRCRVS.RC.RC_CODE = 0;
        WS_DB_STR_AMT = AICRCRVS.INFO.STR_AMT;
        WS_DB_END_AMT = AICRCRVS.INFO.END_AMT;
        WS_DB_STR_DATE = AICRCRVS.INFO.STR_DATE;
        WS_DB_END_DATE = AICRCRVS.INFO.END_DATE;
        IBS.CLONE(SCCGWA, AIRLCRVS, AIRCRVS);
        CEP.TRC(SCCGWA, AIRCRVS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRCRVS.INFO.FUNC);
        if (AICRCRVS.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.FUNC == 'I') {
            B070_INQ_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, AICRCRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRCRVS, AIRLCRVS);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_AITCRVS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITCRVS_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITCRVS();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_AITCRVS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRCRVS.INFO.OPT);
        if (AICRCRVS.INFO.OPT == '1') {
            T000_STARTBR_AITCRVS_GETMAX();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == '2') {
            T000_STARTBR_AITCRVS_5503();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == '3') {
            T000_STARTBR_AITCRVS_5522();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == '4') {
            T000_STARTBR_AITCRVS_5525();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == '5') {
            T000_STARTBR_AITCRVS_5528();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == '6') {
            T000_STARTBR_AITCRVS_5505();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == 'N') {
            T000_READNEXT_AITCRVS();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == 'G') {
            T000_READNEXT_AITCRVS_FIRST();
            if (pgmRtn) return;
        } else if (AICRCRVS.INFO.OPT == 'E') {
            T000_ENDBR_AITCRVS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, AICRCRVS.RC);
        }
    }
    public void B070_INQ_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INQ RVS NO FOR CANCEL");
        T000_READ_AITCRVS_BY_INFO();
        if (pgmRtn) return;
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        IBS.DELETE(SCCGWA, AIRCRVS, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCRVS.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_AITCRVS() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        IBS.READ(SCCGWA, AIRCRVS, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCRVS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_AITCRVS_BY_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.AC);
        CEP.TRC(SCCGWA, AIRCRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRCRVS.TR_CODE);
        CEP.TRC(SCCGWA, AIRCRVS.TR_BR);
        CEP.TRC(SCCGWA, AIRCRVS.TR_TELLER);
        CEP.TRC(SCCGWA, AIRCRVS.SET_NO);
        CEP.TRC(SCCGWA, AIRCRVS.SET_SEQ);
        CEP.TRC(SCCGWA, "13222");
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.eqWhere = "AC,TX_DT,TR_CODE,TR_BR,TR_TELLER, SET_NO,SET_SEQ";
        AITCRVS_RD.where = "STS < > 'R'";
        AITCRVS_RD.fst = true;
        AITCRVS_RD.order = "RVS_NO";
        IBS.READ(SCCGWA, AIRCRVS, this, AITCRVS_RD);
        CEP.TRC(SCCGWA, "13225");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "1322M");
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "1322D");
            AICRCRVS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1322N");
            AICRCRVS.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "13221");
        }
    }
    public void T000_STARTBR_AITCRVS_5503() throws IOException,SQLException,Exception {
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "( 0 = :AIRCRVS.BR "
            + "OR BR = :AIRCRVS.BR ) "
            + "AND ( 0 = :AIRCRVS.ITM "
            + "OR ITM = :AIRCRVS.ITM ) "
            + "AND ( ' ' = :AIRCRVS.SEQ "
            + "OR SEQ = :AIRCRVS.SEQ ) "
            + "AND ( 0 = :AIRCRVS.CCY "
            + "OR CCY = :AIRCRVS.CCY ) "
            + "AND ( ' ' = :AIRCRVS.KEY.RVS_NO "
            + "OR RVS_NO = :AIRCRVS.KEY.RVS_NO ) "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( TX_DT >= :WS_DB_STR_DATE "
            + "AND TX_DT <= :WS_DB_END_DATE )";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITCRVS_5522() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITCRVS_5528() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITCRVS_5525() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_THEIR_AC);
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( ' ' = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITCRVS_5505() throws IOException,SQLException,Exception {
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITCRVS_GETMAX() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( :AIRCRVS.STS = ' ' "
            + "OR STS = :AIRCRVS.STS )";
        AITCRVS_RD.fst = true;
        AITCRVS_RD.order = "RVS_SEQ DESC";
        IBS.READ(SCCGWA, AIRCRVS, this, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_AITCRVS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_AITCRVS_FIRST() throws IOException,SQLException,Exception {
        WS_RCD_SEQ = AICRCRVS.RCD_SEQ;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITCRVS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITCRVS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITCRVS_BR);
    }
    public void T000_WRITE_AITCRVS() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRCRVS, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCRVS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITCRVS_UPD() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.upd = true;
        IBS.READ(SCCGWA, AIRCRVS, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRCRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_AITCRVS() throws IOException,SQLException,Exception {
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        IBS.REWRITE(SCCGWA, AIRCRVS, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRCRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRCRVS.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRCRVS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRCRVS = ");
            CEP.TRC(SCCGWA, AICRCRVS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
