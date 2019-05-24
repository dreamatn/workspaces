package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPHCK {
    int JIBS_tmp_int;
    DBParm BPTPHCK_RD;
    String K_PGM_NAME = "BPZRPHCK";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPHCK BPRPHCK = new BPRPHCK();
    BPRHCHK BPRHCHK = new BPRHCHK();
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
        CEP.TRC(SCCGWA, "BPZRPHCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPEXT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPHCK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCRPEXT.DAT_LEN = 67;
        BPRPHCK.KEY.TYPE = BPCRPEXT.TYP;
        BPRPHCK.KEY.CODE = BPCRPEXT.CD;
        BPRPHCK.KEY.EFF_DATE = BPCRPEXT.EFF_DATE;
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
        T000_WRITE_BPTPHCK();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPHCK_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPHCK();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPHCK_UPD();
        T000_DELETE_BPTPHCK();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPHCK();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPEXT.DAT_LEN), BPRHCHK.DATA_TXT);
        BPRPHCK.RESI_FLG = BPRHCHK.DATA_TXT.RESI_FLG;
        BPRPHCK.CUR_FLG = BPRHCHK.DATA_TXT.CUR_FLG;
        BPRPHCK.BRAN_FLG = BPRHCHK.DATA_TXT.BRAN_FLG;
        BPRPHCK.CNTY_CD1 = BPRHCHK.DATA_TXT.CNTY_CD1;
        BPRPHCK.CITY_CD1 = BPRHCHK.DATA_TXT.CITY_CD1;
        BPRPHCK.CNTY_CD2 = BPRHCHK.DATA_TXT.CNTY_CD2;
        BPRPHCK.CITY_CD2 = BPRHCHK.DATA_TXT.CITY_CD2;
        BPRPHCK.CNTY_CD3 = BPRHCHK.DATA_TXT.CNTY_CD3;
        BPRPHCK.CITY_CD3 = BPRHCHK.DATA_TXT.CITY_CD3;
        BPRPHCK.CNTY_CD4 = BPRHCHK.DATA_TXT.CNTY_CD4;
        BPRPHCK.CITY_CD4 = BPRHCHK.DATA_TXT.CITY_CD4;
        BPRPHCK.UPT_DT = BPRHCHK.DATA_TXT.UPT_DT;
        BPRPHCK.UPT_TLR = BPRHCHK.DATA_TXT.UPT_TLR;
        BPRPHCK.EFF_DT = BPRHCHK.DATA_TXT.EFF_DT;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRHCHK.DATA_TXT.RESI_FLG = BPRPHCK.RESI_FLG;
        BPRHCHK.DATA_TXT.CUR_FLG = BPRPHCK.CUR_FLG;
        BPRHCHK.DATA_TXT.BRAN_FLG = BPRPHCK.BRAN_FLG;
        BPRHCHK.DATA_TXT.CNTY_CD1 = BPRPHCK.CNTY_CD1;
        BPRHCHK.DATA_TXT.CITY_CD1 = BPRPHCK.CITY_CD1;
        BPRHCHK.DATA_TXT.CNTY_CD2 = BPRPHCK.CNTY_CD2;
        BPRHCHK.DATA_TXT.CITY_CD2 = BPRPHCK.CITY_CD2;
        BPRHCHK.DATA_TXT.CNTY_CD3 = BPRPHCK.CNTY_CD3;
        BPRHCHK.DATA_TXT.CITY_CD3 = BPRPHCK.CITY_CD3;
        BPRHCHK.DATA_TXT.CNTY_CD4 = BPRPHCK.CNTY_CD4;
        BPRHCHK.DATA_TXT.CITY_CD4 = BPRPHCK.CITY_CD4;
        BPRHCHK.DATA_TXT.UPT_DT = BPRPHCK.UPT_DT;
        BPRHCHK.DATA_TXT.UPT_TLR = BPRPHCK.UPT_TLR;
        BPRHCHK.DATA_TXT.EFF_DT = BPRPHCK.EFF_DT;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRHCHK.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPEXT.DAT_LEN);
    }
    public void T000_READ_BPTPHCK() throws IOException,SQLException,Exception {
        BPTPHCK_RD = new DBParm();
        BPTPHCK_RD.TableName = "BPTPHCK";
        IBS.READ(SCCGWA, BPRPHCK, BPTPHCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPHCK.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPHCK() throws IOException,SQLException,Exception {
        BPTPHCK_RD = new DBParm();
        BPTPHCK_RD.TableName = "BPTPHCK";
        IBS.WRITE(SCCGWA, BPRPHCK, BPTPHCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPHCK.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPHCK_UPD() throws IOException,SQLException,Exception {
        BPTPHCK_RD = new DBParm();
        BPTPHCK_RD.TableName = "BPTPHCK";
        BPTPHCK_RD.upd = true;
        IBS.READ(SCCGWA, BPRPHCK, BPTPHCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPHCK.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPHCK() throws IOException,SQLException,Exception {
        BPTPHCK_RD = new DBParm();
        BPTPHCK_RD.TableName = "BPTPHCK";
        IBS.REWRITE(SCCGWA, BPRPHCK, BPTPHCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPHCK.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPHCK() throws IOException,SQLException,Exception {
        BPTPHCK_RD = new DBParm();
        BPTPHCK_RD.TableName = "BPTPHCK";
        IBS.DELETE(SCCGWA, BPRPHCK, BPTPHCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPHCK.KEY) + ") NOT FOUND";
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
