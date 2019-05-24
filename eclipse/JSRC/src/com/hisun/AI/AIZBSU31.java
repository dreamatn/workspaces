package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZBSU31 {
    DBParm AITBSP31_RD;
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRBSP31 AIRBSP31 = new AIRBSP31();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    AIRBSP31 AIRLSP31;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZBSU31 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLSP31 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AIRBSP31);
        IBS.CLONE(SCCGWA, AIRLSP31, AIRBSP31);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRWBSP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, AIRBSP31, AIRLSP31);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRBSP31.KEY);
        CEP.TRC(SCCGWA, AIRBSP31.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, AIRBSP31.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, AIRBSP31.KEY.BAT_NO);
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        AITBSP31_RD = new DBParm();
        AITBSP31_RD.TableName = "AITBSP31";
        IBS.READ(SCCGWA, AIRBSP31, AITBSP31_RD);
        CEP.TRC(SCCGWA, "AFTER READ BSP31");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRBSP31.KEY);
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        AITBSP31_RD = new DBParm();
        AITBSP31_RD.TableName = "AITBSP31";
        AITBSP31_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRBSP31, AITBSP31_RD);
        CEP.TRC(SCCGWA, "AFTER WRITE BSP31");
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRBSP31.KEY);
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        AITBSP31_RD = new DBParm();
        AITBSP31_RD.TableName = "AITBSP31";
        AITBSP31_RD.upd = true;
        IBS.READ(SCCGWA, AIRBSP31, AITBSP31_RD);
        CEP.TRC(SCCGWA, "AFTER READ BSP31 UPDATE");
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRBSP31.KEY);
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        AITBSP31_RD = new DBParm();
        AITBSP31_RD.TableName = "AITBSP31";
        IBS.REWRITE(SCCGWA, AIRBSP31, AITBSP31_RD);
        CEP.TRC(SCCGWA, "AFTER REWRITE BSP31");
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRBSP31.KEY);
        CEP.TRC(SCCGWA, AIRBSP31.STATUS);
        CEP.TRC(SCCGWA, AIRBSP31.RT_CODE);
        AITBSP31_RD = new DBParm();
        AITBSP31_RD.TableName = "AITBSP31";
        IBS.DELETE(SCCGWA, AIRBSP31, AITBSP31_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP = ");
            CEP.TRC(SCCGWA, SCCRWBSP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
