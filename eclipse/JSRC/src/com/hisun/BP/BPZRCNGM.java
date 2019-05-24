package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCNGM {
    DBParm BPTCNGM_RD;
    String CPN_UPD_FHIST = "BP-UPD-FHIST";
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    char WS_BRW_FHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCRCNGM BPCRCNGM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRCNGM BPRCNGM;
    public void MP(SCCGWA SCCGWA, BPCRCNGM BPCRCNGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCNGM = BPCRCNGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRCNGM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCNGM = (BPRCNGM) BPCRCNGM.POINTER;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRCNGM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCNGM.FUNC == 'Q') {
            B010_QUERY_PROCESS();
        } else if (BPCRCNGM.FUNC == 'A') {
            B020_ADD_PROCESS();
        } else if (BPCRCNGM.FUNC == 'U') {
            B030_UPDATE_PROCESS();
        } else if (BPCRCNGM.FUNC == 'D') {
            B040_DELETE_PROCESS();
        } else if (BPCRCNGM.FUNC == 'M') {
            B050_READ_UPDATE_PROCESS();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZRCNGM INVALID FUNC(" + BPCRCNGM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        T010_READ_PROCESS();
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        T020_WRITE_PROCESS();
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        T030_REWRITE_PROCESS();
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        T040_DELETE_PROCESS();
    }
    public void B050_READ_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        T050_READ_UPDATE_PROCESS();
    }
    public void T010_READ_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPRCNGM.KEY.REF);
        CEP.TRC(SCCGWA, BPRCNGM.KEY.CNTR_TYPE);
        BPTCNGM_RD = new DBParm();
        BPTCNGM_RD.TableName = "BPTCNGM";
        IBS.READ(SCCGWA, BPRCNGM, BPTCNGM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCNGM.RC.RC_MMO = "BP";
            BPCRCNGM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_ITEM_NOFND, BPCRCNGM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCNGM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T020_WRITE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCNGM.KEY);
        BPTCNGM_RD = new DBParm();
        BPTCNGM_RD.TableName = "BPTCNGM";
        BPTCNGM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCNGM, BPTCNGM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCNGM.RC.RC_MMO = "BP";
            BPCRCNGM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_ITEM_EXIST, BPCRCNGM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCNGM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T030_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPTCNGM_RD = new DBParm();
        BPTCNGM_RD.TableName = "BPTCNGM";
        IBS.REWRITE(SCCGWA, BPRCNGM, BPTCNGM_RD);
    }
    public void T040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPTCNGM_RD = new DBParm();
        BPTCNGM_RD.TableName = "BPTCNGM";
        IBS.DELETE(SCCGWA, BPRCNGM, BPTCNGM_RD);
    }
    public void T050_READ_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        BPTCNGM_RD = new DBParm();
        BPTCNGM_RD.TableName = "BPTCNGM";
        BPTCNGM_RD.upd = true;
        IBS.READ(SCCGWA, BPRCNGM, BPTCNGM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCNGM.RC.RC_MMO = "BP";
            BPCRCNGM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_ITEM_NOFND, BPCRCNGM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCNGM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCNGM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCNGM = ");
            CEP.TRC(SCCGWA, BPCRCNGM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
