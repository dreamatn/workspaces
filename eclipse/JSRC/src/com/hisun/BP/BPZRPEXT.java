package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPEXT {
    int JIBS_tmp_int;
    DBParm BPTPEXT_RD;
    String K_PGM_NAME = "BPZRPEXT";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPEXT BPRPEXT = new BPRPEXT();
    BPREXRT BPREXRT = new BPREXRT();
    SCCGWA SCCGWA;
    BPCREXRT BPCREXRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCREXRT BPCREXRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCREXRT = BPCREXRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPEXT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCREXRT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPEXT);
        IBS.init(SCCGWA, BPREXRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEXT);
        BPCREXRT.DAT_LEN = 151;
        BPRPEXT.KEY.TYPE = BPCREXRT.TYP;
        BPRPEXT.KEY.CODE = BPCREXRT.CD;
        BPRPEXT.KEY.EFF_DATE = BPCREXRT.EFF_DATE;
        if (BPCREXRT.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCREXRT.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCREXRT.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCREXRT.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCREXRT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPEXT();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPEXT_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPEXT();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPEXT_UPD();
        T000_DELETE_BPTPEXT();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPEXT();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCREXRT.DAT_LEN), BPREXRT.DAT_TXT);
        BPRPEXT.SUM_IND = BPREXRT.DAT_TXT.SUM_IND;
        BPRPEXT.FWD_IND = BPREXRT.DAT_TXT.FWD_IND;
        BPRPEXT.BASE_CCY = BPREXRT.DAT_TXT.BASE_CCY;
        BPRPEXT.GEN_MODE = BPREXRT.DAT_TXT.GEN_MODE;
        BPRPEXT.UPD_IND = BPREXRT.DAT_TXT.UPD_IND;
        BPRPEXT.UPD_APPO_TM = BPREXRT.DAT_TXT.UPD_APPO_TM;
        BPRPEXT.UPD_SRT_TM = BPREXRT.DAT_TXT.UPD_SRT_TM;
        BPRPEXT.UPD_END_TM = BPREXRT.DAT_TXT.UPD_END_TM;
        BPRPEXT.UPD_CYC = BPREXRT.DAT_TXT.UPD_CYC;
        BPRPEXT.UPD_FREQ = BPREXRT.DAT_TXT.UPD_FREQ;
        BPRPEXT.UPD_SRC = BPREXRT.DAT_TXT.UPD_SRC;
        BPRPEXT.RMK = BPREXRT.DAT_TXT.RMK;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPREXRT.DAT_TXT.SUM_IND = BPRPEXT.SUM_IND;
        BPREXRT.DAT_TXT.FWD_IND = BPRPEXT.FWD_IND;
        BPREXRT.DAT_TXT.BASE_CCY = BPRPEXT.BASE_CCY;
        BPREXRT.DAT_TXT.GEN_MODE = BPRPEXT.GEN_MODE;
        BPREXRT.DAT_TXT.UPD_IND = BPRPEXT.UPD_IND;
        BPREXRT.DAT_TXT.UPD_APPO_TM = BPRPEXT.UPD_APPO_TM;
        BPREXRT.DAT_TXT.UPD_SRT_TM = BPRPEXT.UPD_SRT_TM;
        BPREXRT.DAT_TXT.UPD_END_TM = BPRPEXT.UPD_END_TM;
        BPREXRT.DAT_TXT.UPD_CYC = BPRPEXT.UPD_CYC;
        BPREXRT.DAT_TXT.UPD_FREQ = BPRPEXT.UPD_FREQ;
        BPREXRT.DAT_TXT.UPD_SRC = BPRPEXT.UPD_SRC;
        BPREXRT.DAT_TXT.RMK = BPRPEXT.RMK;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXRT.DAT_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCREXRT.DAT_LEN);
    }
    public void T000_READ_BPTPEXT() throws IOException,SQLException,Exception {
        BPTPEXT_RD = new DBParm();
        BPTPEXT_RD.TableName = "BPTPEXT";
        IBS.READ(SCCGWA, BPRPEXT, BPTPEXT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPEXT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPEXT() throws IOException,SQLException,Exception {
        BPTPEXT_RD = new DBParm();
        BPTPEXT_RD.TableName = "BPTPEXT";
        IBS.WRITE(SCCGWA, BPRPEXT, BPTPEXT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPEXT.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPEXT_UPD() throws IOException,SQLException,Exception {
        BPTPEXT_RD = new DBParm();
        BPTPEXT_RD.TableName = "BPTPEXT";
        BPTPEXT_RD.upd = true;
        IBS.READ(SCCGWA, BPRPEXT, BPTPEXT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPEXT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPEXT() throws IOException,SQLException,Exception {
        BPTPEXT_RD = new DBParm();
        BPTPEXT_RD.TableName = "BPTPEXT";
        IBS.REWRITE(SCCGWA, BPRPEXT, BPTPEXT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPEXT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPEXT() throws IOException,SQLException,Exception {
        BPTPEXT_RD = new DBParm();
        BPTPEXT_RD.TableName = "BPTPEXT";
        IBS.DELETE(SCCGWA, BPRPEXT, BPTPEXT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPEXT.KEY) + ") NOT FOUND";
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
