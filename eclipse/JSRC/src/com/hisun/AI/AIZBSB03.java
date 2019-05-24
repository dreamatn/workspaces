package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZBSB03 {
    brParm AITBSP03_BR = new brParm();
    DBParm AITBSP03_RD;
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRBSP03 AIRBSP03 = new AIRBSP03();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRBBSP SCCRBBSP;
    AIRBSP03 AIRLSP03;
    public void MP(SCCGWA SCCGWA, SCCRBBSP SCCRBBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRBBSP = SCCRBBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZBSB03 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLSP03 = (PSRBSP01) SCCRBBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AIRBSP03);
        IBS.CLONE(SCCGWA, AIRLSP03, AIRBSP03);
        SCCRBBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRBBSP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRBBSP.INFO.FUNC == 'S') {
            T000_STARTBR_BSP();
            if (pgmRtn) return;
        } else if (SCCRBBSP.INFO.FUNC == 'N') {
            T000_READNEXT_BSP();
            if (pgmRtn) return;
        } else if (SCCRBBSP.INFO.FUNC == 'E') {
            T000_ENDBR_BSP();
            if (pgmRtn) return;
        } else if (SCCRBBSP.INFO.FUNC == 'W') {
            T000_REWRITE_BSP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BSP_INPUT_FUNC_ERR, SCCRBBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRBSP03, AIRLSP03);
    }
    public void T000_STARTBR_BSP() throws IOException,SQLException,Exception {
        AITBSP03_BR.rp = new DBParm();
        AITBSP03_BR.rp.TableName = "AITBSP03";
        AITBSP03_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, AIRBSP03, AITBSP03_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRBBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRBBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRBBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRBSP03, this, AITBSP03_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRBBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRBBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRBBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITBSP03_BR);
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        AITBSP03_RD = new DBParm();
        AITBSP03_RD.TableName = "AITBSP03";
        IBS.REWRITE(SCCGWA, AIRBSP03, AITBSP03_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRBBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, SCCRBBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRBBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRBBSP = ");
            CEP.TRC(SCCGWA, SCCRBBSP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
