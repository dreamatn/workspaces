package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPCNT {
    int JIBS_tmp_int;
    DBParm BPTPCNT_RD;
    String K_PGM_NAME = "BPZRPCNT";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPCNT BPRPCNT = new BPRPCNT();
    BPRCNTY BPRCNTY = new BPRCNTY();
    SCCGWA SCCGWA;
    BPCRPCNT BPCRPCNT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPCNT BPCRPCNT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPCNT = BPCRPCNT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPCNT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPCNT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPCNT);
        IBS.init(SCCGWA, BPRCNTY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCNT);
        BPCRPCNT.DAT_LEN = 78;
        BPRPCNT.KEY.TYPE = BPCRPCNT.TYP;
        BPRPCNT.KEY.CODE = BPCRPCNT.CD;
        BPRPCNT.KEY.EFF_DATE = BPCRPCNT.EFF_DATE;
        if (BPCRPCNT.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPCNT.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPCNT.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPCNT.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPCNT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPCNT();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCNT_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPCNT();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCNT_UPD();
        T000_DELETE_BPTPCNT();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCNT();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPCNT.DAT_LEN), BPRCNTY.DATA_TXT);
        BPRPCNT.PDAYS = BPRCNTY.DATA_TXT.PDAYS;
        BPRPCNT.P_CUTTIME = BPRCNTY.DATA_TXT.P_CUTTIME;
        BPRPCNT.C_CUTTIME = BPRCNTY.DATA_TXT.C_CUTTIME;
        BPRPCNT.SWIFT_CODE = BPRCNTY.DATA_TXT.SWIFT_CODE;
        BPRPCNT.IBAN = BPRCNTY.DATA_TXT.IBAN;
        BPRPCNT.CALR_CODE = BPRCNTY.DATA_TXT.CALR_CODE;
        BPRPCNT.ISO_CODE = BPRCNTY.DATA_TXT.ISO_CODE;
        BPRPCNT.ISO_NUMER = BPRCNTY.DATA_TXT.ISO_NUMER;
        BPRPCNT.CUR = BPRCNTY.DATA_TXT.CUR;
        BPRPCNT.TIMEZONE = BPRCNTY.DATA_TXT.TIMEZONE;
        BPRPCNT.LANG_CODE = BPRCNTY.DATA_TXT.LANG_CODE;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRCNTY.DATA_TXT.PDAYS = BPRPCNT.PDAYS;
        BPRCNTY.DATA_TXT.P_CUTTIME = BPRPCNT.P_CUTTIME;
        BPRCNTY.DATA_TXT.C_CUTTIME = BPRPCNT.C_CUTTIME;
        BPRCNTY.DATA_TXT.SWIFT_CODE = BPRPCNT.SWIFT_CODE;
        BPRCNTY.DATA_TXT.IBAN = BPRPCNT.IBAN;
        BPRCNTY.DATA_TXT.CALR_CODE = BPRPCNT.CALR_CODE;
        BPRCNTY.DATA_TXT.ISO_CODE = BPRPCNT.ISO_CODE;
        BPRCNTY.DATA_TXT.ISO_NUMER = BPRPCNT.ISO_NUMER;
        BPRCNTY.DATA_TXT.CUR = BPRPCNT.CUR;
        BPRCNTY.DATA_TXT.TIMEZONE = BPRPCNT.TIMEZONE;
        BPRCNTY.DATA_TXT.LANG_CODE = BPRPCNT.LANG_CODE;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCNTY.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPCNT.DAT_LEN);
    }
    public void T000_READ_BPTPCNT() throws IOException,SQLException,Exception {
        BPTPCNT_RD = new DBParm();
        BPTPCNT_RD.TableName = "BPTPCNT";
        IBS.READ(SCCGWA, BPRPCNT, BPTPCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CNTY (" + IBS.CLS2CPY(SCCGWA, BPRPCNT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPCNT() throws IOException,SQLException,Exception {
        BPTPCNT_RD = new DBParm();
        BPTPCNT_RD.TableName = "BPTPCNT";
        IBS.WRITE(SCCGWA, BPRPCNT, BPTPCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CNTY (" + IBS.CLS2CPY(SCCGWA, BPRPCNT.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPCNT_UPD() throws IOException,SQLException,Exception {
        BPTPCNT_RD = new DBParm();
        BPTPCNT_RD.TableName = "BPTPCNT";
        BPTPCNT_RD.upd = true;
        IBS.READ(SCCGWA, BPRPCNT, BPTPCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CNTY (" + IBS.CLS2CPY(SCCGWA, BPRPCNT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPCNT() throws IOException,SQLException,Exception {
        BPTPCNT_RD = new DBParm();
        BPTPCNT_RD.TableName = "BPTPCNT";
        IBS.REWRITE(SCCGWA, BPRPCNT, BPTPCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CNTY (" + IBS.CLS2CPY(SCCGWA, BPRPCNT.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPCNT() throws IOException,SQLException,Exception {
        BPTPCNT_RD = new DBParm();
        BPTPCNT_RD.TableName = "BPTPCNT";
        IBS.DELETE(SCCGWA, BPRPCNT, BPTPCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CNTY (" + IBS.CLS2CPY(SCCGWA, BPRPCNT.KEY) + ") NOT FOUND";
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
