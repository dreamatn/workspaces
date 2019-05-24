package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPCIT {
    int JIBS_tmp_int;
    DBParm BPTPCIT_RD;
    String K_PGM_NAME = "BPZRPCIT";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPCIT BPRPCIT = new BPRPCIT();
    BPRCITY BPRCITY = new BPRCITY();
    SCCGWA SCCGWA;
    BPCRPCIT BPCRPCIT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPCIT BPCRPCIT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPCIT = BPCRPCIT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPCIT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPCIT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPCIT);
        IBS.init(SCCGWA, BPRCITY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCIT);
        BPCRPCIT.DAT_LEN = 52;
        BPRPCIT.KEY.TYPE = BPCRPCIT.TYP;
        BPRPCIT.KEY.CODE = BPCRPCIT.CD;
        BPRPCIT.KEY.EFF_DATE = BPCRPCIT.EFF_DATE;
        if (BPCRPCIT.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPCIT.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPCIT.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPCIT.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPCIT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPCIT();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCIT_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPCIT();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCIT_UPD();
        T000_DELETE_BPTPCIT();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCIT();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPCIT.DAT_LEN), BPRCITY.DATA_TXT);
        BPRPCIT.PDAYS = BPRCITY.DATA_TXT.PDAYS;
        BPRPCIT.P_CUTTIME = BPRCITY.DATA_TXT.P_CUTTIME;
        BPRPCIT.C_CUTTIME = BPRCITY.DATA_TXT.C_CUTTIME;
        BPRPCIT.CALR_CODE = BPRCITY.DATA_TXT.CALR_CODE;
        BPRPCIT.TIMEZONE = BPRCITY.DATA_TXT.TIMEZONE;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRCITY.DATA_TXT.PDAYS = BPRPCIT.PDAYS;
        BPRCITY.DATA_TXT.P_CUTTIME = BPRPCIT.P_CUTTIME;
        BPRCITY.DATA_TXT.C_CUTTIME = BPRPCIT.C_CUTTIME;
        BPRCITY.DATA_TXT.CALR_CODE = BPRPCIT.CALR_CODE;
        BPRCITY.DATA_TXT.TIMEZONE = BPRPCIT.TIMEZONE;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCITY.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPCIT.DAT_LEN);
    }
    public void T000_READ_BPTPCIT() throws IOException,SQLException,Exception {
        BPTPCIT_RD = new DBParm();
        BPTPCIT_RD.TableName = "BPTPCIT";
        IBS.READ(SCCGWA, BPRPCIT, BPTPCIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CITY (" + IBS.CLS2CPY(SCCGWA, BPRPCIT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPCIT() throws IOException,SQLException,Exception {
        BPTPCIT_RD = new DBParm();
        BPTPCIT_RD.TableName = "BPTPCIT";
        IBS.WRITE(SCCGWA, BPRPCIT, BPTPCIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CITY (" + IBS.CLS2CPY(SCCGWA, BPRPCIT.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPCIT_UPD() throws IOException,SQLException,Exception {
        BPTPCIT_RD = new DBParm();
        BPTPCIT_RD.TableName = "BPTPCIT";
        BPTPCIT_RD.upd = true;
        IBS.READ(SCCGWA, BPRPCIT, BPTPCIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CITY (" + IBS.CLS2CPY(SCCGWA, BPRPCIT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPCIT() throws IOException,SQLException,Exception {
        BPTPCIT_RD = new DBParm();
        BPTPCIT_RD.TableName = "BPTPCIT";
        IBS.REWRITE(SCCGWA, BPRPCIT, BPTPCIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CITY (" + IBS.CLS2CPY(SCCGWA, BPRPCIT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPCIT() throws IOException,SQLException,Exception {
        BPTPCIT_RD = new DBParm();
        BPTPCIT_RD.TableName = "BPTPCIT";
        IBS.DELETE(SCCGWA, BPRPCIT, BPTPCIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CITY (" + IBS.CLS2CPY(SCCGWA, BPRPCIT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
