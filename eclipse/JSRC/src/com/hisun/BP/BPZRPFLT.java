package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPFLT {
    int JIBS_tmp_int;
    DBParm BPTPFLT_RD;
    String K_PGM_NAME = "BPZRPFLT";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPFLT BPRPFLT = new BPRPFLT();
    BPCFLOAT BPCFLOAT = new BPCFLOAT();
    SCCGWA SCCGWA;
    BPCRPEXT BPCRPEXT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPEXT BPCRPEXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPEXT = BPCRPEXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPFLT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPEXT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPFLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCRPEXT.DAT_LEN = 142;
        BPRPFLT.KEY.TYPE = BPCRPEXT.TYP;
        BPRPFLT.KEY.CODE = BPCRPEXT.CD;
        BPRPFLT.KEY.EFF_DATE = BPCRPEXT.EFF_DATE;
        if (BPCRPEXT.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPEXT.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPEXT.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPEXT.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPEXT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPFLT();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPFLT_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPFLT();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPFLT_UPD();
        T000_DELETE_BPTPFLT();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPFLT();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPEXT.DAT_LEN), BPCFLOAT.DAT_TXT);
        BPRPFLT.FLT_RULE = BPCFLOAT.DAT_TXT.FLT_RULE;
        BPRPFLT.FLOAT_TO_ITEM = BPCFLOAT.DAT_TXT.TO_ITEM;
        BPRPFLT.FLOAT_OLD_FLT_RULE = BPCFLOAT.DAT_TXT.OLD_FLT_RULE;
        BPRPFLT.FLOAT_OLD_TO_ITEM = BPCFLOAT.DAT_TXT.OLD_TO_ITEM;
        BPRPFLT.FLOAT_RMK = BPCFLOAT.DAT_TXT.RMK;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPCFLOAT.DAT_TXT.FLT_RULE = BPRPFLT.FLT_RULE;
        BPCFLOAT.DAT_TXT.TO_ITEM = BPRPFLT.FLOAT_TO_ITEM;
        BPCFLOAT.DAT_TXT.OLD_FLT_RULE = BPRPFLT.FLOAT_OLD_FLT_RULE;
        BPCFLOAT.DAT_TXT.OLD_TO_ITEM = BPRPFLT.FLOAT_OLD_TO_ITEM;
        BPCFLOAT.DAT_TXT.RMK = BPRPFLT.FLOAT_RMK;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFLOAT.DAT_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPEXT.DAT_LEN);
    }
    public void T000_READ_BPTPFLT() throws IOException,SQLException,Exception {
        BPTPFLT_RD = new DBParm();
        BPTPFLT_RD.TableName = "BPTPFLT";
        IBS.READ(SCCGWA, BPRPFLT, BPTPFLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPFLT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPFLT() throws IOException,SQLException,Exception {
        BPTPFLT_RD = new DBParm();
        BPTPFLT_RD.TableName = "BPTPFLT";
        IBS.WRITE(SCCGWA, BPRPFLT, BPTPFLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPFLT.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPFLT_UPD() throws IOException,SQLException,Exception {
        BPTPFLT_RD = new DBParm();
        BPTPFLT_RD.TableName = "BPTPFLT";
        BPTPFLT_RD.upd = true;
        IBS.READ(SCCGWA, BPRPFLT, BPTPFLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPFLT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPFLT() throws IOException,SQLException,Exception {
        BPTPFLT_RD = new DBParm();
        BPTPFLT_RD.TableName = "BPTPFLT";
        IBS.REWRITE(SCCGWA, BPRPFLT, BPTPFLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPFLT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPFLT() throws IOException,SQLException,Exception {
        BPTPFLT_RD = new DBParm();
        BPTPFLT_RD.TableName = "BPTPFLT";
        IBS.DELETE(SCCGWA, BPRPFLT, BPTPFLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPFLT.KEY) + ") NOT FOUND";
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
