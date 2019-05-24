package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCNGL {
    DBParm BPTCNGL_RD;
    int JIBS_tmp_int;
    brParm BPTCNGL_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_FARM = "BPTCNGL ";
    int WS_REC_LEN = 0;
    String WS_CNTR_TYPE = " ";
    String WS_PROD_CD = " ";
    String WS_PRDCP_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCNGL BPRCNGL = new BPRCNGL();
    SCCGWA SCCGWA;
    BPCRCNGL BPCRCNGL;
    BPRCNGL BPRCNGL1;
    public void MP(SCCGWA SCCGWA, BPCRCNGL BPCRCNGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCNGL = BPCRCNGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCNGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        BPRCNGL1 = (BPRCNGL) BPCRCNGL.DAT_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        IBS.CLONE(SCCGWA, BPRCNGL1, BPRCNGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCNGL.FUNC);
        if (BPCRCNGL.FUNC == 'I') {
            T000_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'A') {
            T000_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'U') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'M') {
            T000_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'D') {
            T000_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'S') {
            T000_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'N') {
            T000_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCNGL.FUNC == 'E') {
            T000_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCRCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCNGL, BPRCNGL1);
    }
    public void T000_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        IBS.READ(SCCGWA, BPRCNGL, BPTCNGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCNGL);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        BPTCNGL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCNGL, BPTCNGL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_9382, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCNGL.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.BR);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        BPTCNGL_RD.upd = true;
        IBS.READ(SCCGWA, BPRCNGL, BPTCNGL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        IBS.REWRITE(SCCGWA, BPRCNGL, BPTCNGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_9382, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        IBS.DELETE(SCCGWA, BPRCNGL, BPTCNGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        WS_CNTR_TYPE = BPRCNGL.KEY.CNTR_TYPE;
        if (BPRCNGL.KEY.OTH == null) BPRCNGL.KEY.OTH = "";
        JIBS_tmp_int = BPRCNGL.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPRCNGL.KEY.OTH += " ";
        WS_PROD_CD = BPRCNGL.KEY.OTH.substring(0, 10);
        BPTCNGL_BR.rp = new DBParm();
        BPTCNGL_BR.rp.TableName = "BPTCNGL";
        BPTCNGL_BR.rp.where = "( :WS_CNTR_TYPE = ' ' "
            + "OR CNTR_TYPE = :WS_CNTR_TYPE ) "
            + "AND ( :WS_PROD_CD = ' ' "
            + "OR SUBSTR ( OTH , 1 , 10 ) = :WS_PROD_CD )";
        BPTCNGL_BR.rp.order = "CNTR_TYPE,BOOK_FLG,BR,OTH";
        IBS.STARTBR(SCCGWA, BPRCNGL, this, BPTCNGL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCNGL, this, BPTCNGL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST, BPCRCNGL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCNGL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCNGL.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCNGL = ");
            CEP.TRC(SCCGWA, BPCRCNGL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
