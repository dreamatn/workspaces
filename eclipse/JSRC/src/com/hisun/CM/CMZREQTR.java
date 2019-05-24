package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZREQTR {
    DBParm CMTREQTR_RD;
    boolean pgmRtn = false;
    CMZREQTR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZREQTR_WS_TEMP_VARIABLE();
    int LILIAN = 0;
    long XSECONDS = 0;
    String GREGORN = " ";
    char CEEIGZCT_RC = ' ';
    String CMZREQTR_FILLER3 = " ";
    int WS_WAIT_FOR = 0;
    int WS_COUNTER = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CMRREQTR CMRREQTR = new CMRREQTR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCREQTR CMCREQTR;
    public void MP(SCCGWA SCCGWA, CMCREQTR CMCREQTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCREQTR = CMCREQTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZREQTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        if (CMCREQTR.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_TYP_ERR, CMCREQTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (CMCREQTR.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_MUST_INP, CMCREQTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCREQTR.REQ_SEQ.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_REQ_SEQ_ERR, CMCREQTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 12 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0'; WS_TEMP_VARIABLE.WS_I += 1) {
            T000_READ_CMTREQTR();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            && WS_TEMP_VARIABLE.WS_I > 12) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_REQTR_NOTFND_ERR, CMCREQTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CMCREQTR.SRC_SYS = CMRREQTR.SRC_SYS;
        CMCREQTR.FILE_TYPE = CMRREQTR.FILE_TYPE;
        CMCREQTR.FILE_DATE = CMRREQTR.FILE_DATE;
        if (CMRREQTR.FILE_SEQ.trim().length() == 0) CMCREQTR.FILE_SEQ = 0;
        else CMCREQTR.FILE_SEQ = Integer.parseInt(CMRREQTR.FILE_SEQ);
        CEP.TRC(SCCGWA, CMRREQTR.BLOB_INP_AREA.trim().length());
        CMCREQTR.INP_AREA_TEXT = CMRREQTR.BLOB_INP_AREA;
    }
    public void T000_READ_CMTREQTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRREQTR);
        CEP.TRC(SCCGWA, CMCREQTR.REQ_SEQ);
        CMRREQTR.KEY.REQ_SEQ = CMCREQTR.REQ_SEQ;
        CMTREQTR_RD = new DBParm();
        CMTREQTR_RD.TableName = "CMTREQTR";
        IBS.READ(SCCGWA, CMRREQTR, CMTREQTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CMCREQTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CMCREQTR=");
            CEP.TRC(SCCGWA, CMCREQTR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
