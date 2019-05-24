package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class CMZBSP43 {
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CMRBSP43 CMRBSP43 = new CMRBSP43();
    SCCGWA SCCGWA;
    SCCRBBSP SCCRBBSP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMRBSP43 CMRLSP43;
    public void MP(SCCGWA SCCGWA, SCCRBBSP SCCRBBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRBBSP = SCCRBBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZBSP43 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CMRLSP43 = (PSRBSP01) SCCRBBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMRBSP43);
        IBS.CLONE(SCCGWA, CMRLSP43, CMRBSP43);
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
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRBBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, CMRBSP43, CMRLSP43);
    }
    public void T000_STARTBR_BSP() throws IOException,SQLException,Exception {
        CMTBSP43_BR.rp = new DBParm();
        CMTBSP43_BR.rp.TableName = "CMTBSP43";
        CMTBSP43_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CMRBSP43, CMTBSP43_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRBBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_READNEXT_BSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRBSP43, this, CMTBSP43_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRBBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRBBSP.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, CMRBSP43.STATUS);
