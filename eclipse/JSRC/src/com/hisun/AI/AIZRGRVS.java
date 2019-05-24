package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRGRVS {
    DBParm AITGRVS_RD;
    brParm AITGRVS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "AIZRGRVS";
    String K_TBL_FARM = "AITGRVS";
    String WS_TEMP_RECORD = " ";
    int WS_RCD_SEQ = 0;
    double WS_DB_STR_AMT = 0;
    double WS_DB_END_AMT = 0;
    int WS_DB_STR_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_THEIR_AC = " ";
    char WS_TBL_FARM_FLAG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRGRVS AIRGRVS = new AIRGRVS();
    SCCGWA SCCGWA;
    AICRGRVS AICRGRVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIRGRVS AIRLGRVS;
    public void MP(SCCGWA SCCGWA, AICRGRVS AICRGRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRGRVS = AICRGRVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRGRVS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        AIRLGRVS = (AIRGRVS) AICRGRVS.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AIRGRVS);
        AICRGRVS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        AICRGRVS.RC.RC_CODE = 0;
        WS_DB_STR_AMT = AICRGRVS.INFO.STR_AMT;
        WS_DB_END_AMT = AICRGRVS.INFO.END_AMT;
        WS_DB_STR_DATE = AICRGRVS.INFO.STR_DATE;
        WS_DB_END_DATE = AICRGRVS.INFO.END_DATE;
        IBS.CLONE(SCCGWA, AIRLGRVS, AIRGRVS);
        CEP.TRC(SCCGWA, "22222");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRGRVS.INFO.FUNC);
        if (AICRGRVS.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "12223");
        } else if (AICRGRVS.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.FUNC == 'I') {
            B070_INQ_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRGRVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRGRVS, AIRGRVS);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_AITGRVS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITGRVS_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITGRVS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "12224");
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_AITGRVS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRGRVS.INFO.OPT == '2') {
            T000_STARTBR_AITGRVS_CRVS();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '3') {
            T000_STARTBR_AITGRVS_GETMAX();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '4') {
            T000_STARTBR_AITGRVS_FORCAN();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '5') {
            T000_STARTBR_AITGRVS_5500();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '6') {
            T000_STARTBR_AITGRVS_5522();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '7') {
            T000_STARTBR_AITGRVS_5525();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == '9') {
            T000_STARTBR_AITGRVS_5502();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == 'N') {
            T000_READNEXT_AITGRVS();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == 'G') {
            T000_READNEXT_AITGRVS_FIRST();
            if (pgmRtn) return;
        } else if (AICRGRVS.INFO.OPT == 'E') {
            T000_ENDBR_AITGRVS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRGRVS.RC);
        }
    }
    public void B070_INQ_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INQ RVS NO FOR CANCEL");
        T000_READ_AITGRVS_BY_INFO();
        if (pgmRtn) return;
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        IBS.DELETE(SCCGWA, AIRGRVS, AITGRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRGRVS.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_AITGRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, "12222");
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        IBS.READ(SCCGWA, AIRGRVS, AITGRVS_RD);
        CEP.TRC(SCCGWA, "12225");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "1222M");
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "1222D");
            AICRGRVS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1222N");
            AICRGRVS.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "12221");
        }
    }
    public void T000_READ_AITGRVS_BY_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.AC);
        CEP.TRC(SCCGWA, AIRGRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRGRVS.TR_CODE);
        CEP.TRC(SCCGWA, AIRGRVS.TR_BR);
        CEP.TRC(SCCGWA, AIRGRVS.TR_TELLER);
        CEP.TRC(SCCGWA, AIRGRVS.SET_NO);
        CEP.TRC(SCCGWA, AIRGRVS.SET_SEQ);
        CEP.TRC(SCCGWA, "13222");
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.eqWhere = "AC,TX_DT,TR_CODE,TR_BR,TR_TELLER, SET_NO,SET_SEQ";
        AITGRVS_RD.where = "STS < > 'R'";
        AITGRVS_RD.fst = true;
        AITGRVS_RD.order = "RVS_NO";
        IBS.READ(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        CEP.TRC(SCCGWA, "13225");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "1322M");
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "1322D");
            AICRGRVS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1322N");
            AICRGRVS.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "13221");
        }
    }
    public void T000_STARTBR_AITGRVS_5500() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "( 0 = :AIRGRVS.BR "
            + "OR BR = :AIRGRVS.BR ) "
            + "AND ( 0 = :AIRGRVS.ITM "
            + "OR ITM = :AIRGRVS.ITM ) "
            + "AND ( ' ' = :AIRGRVS.SEQ "
            + "OR SEQ = :AIRGRVS.SEQ ) "
            + "AND ( 0 = :AIRGRVS.CCY "
            + "OR CCY = :AIRGRVS.CCY ) "
            + "AND ( ' ' = :AIRGRVS.KEY.RVS_NO "
            + "OR RVS_NO = :AIRGRVS.KEY.RVS_NO ) "
            + "AND ( ' ' = :AIRGRVS.STS "
            + "OR STS = :AIRGRVS.STS ) "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( TX_DT >= :WS_DB_STR_DATE "
            + "AND TX_DT <= :WS_DB_END_DATE )";
        AITGRVS_BR.rp.order = "BR,AC,RVS_NO,RVS_SEQ";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_5502() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_5522() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_5525() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_THEIR_AC);
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_CRVS() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND STS = 'N'";
        AITGRVS_BR.rp.order = "RVS_SEQ";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_GETMAX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.STS);
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        AITGRVS_RD.fst = true;
        AITGRVS_RD.order = "RVS_SEQ DESC";
        IBS.READ(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_AITGRVS_FORCAN() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND STS = 'N'";
        AITGRVS_BR.rp.order = "RVS_SEQ DESC";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_AITGRVS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_AITGRVS_FIRST() throws IOException,SQLException,Exception {
        WS_RCD_SEQ = AICRGRVS.RCD_SEQ;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRVS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITGRVS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGRVS_BR);
    }
    public void T000_WRITE_AITGRVS() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRGRVS, AITGRVS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRGRVS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITGRVS_UPD() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.upd = true;
        IBS.READ(SCCGWA, AIRGRVS, AITGRVS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGRVS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_AITGRVS() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        IBS.REWRITE(SCCGWA, AIRGRVS, AITGRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGRVS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRGRVS.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRGRVS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRGRVS = ");
            CEP.TRC(SCCGWA, AICRGRVS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
