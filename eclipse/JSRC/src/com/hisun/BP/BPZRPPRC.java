package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPPRC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTPPRC_RD;
    String K_PGM_NAME = "BPZRPPRC";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPPRC BPRPPRC = new BPRPPRC();
    BPCPARMC BPCPARMC = new BPCPARMC();
    SCCGWA SCCGWA;
    BPCRPPRC BPCRPPRC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPPRC BPCRPPRC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPPRC = BPCRPPRC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPPRC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPPRC.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPPRC);
        IBS.init(SCCGWA, BPCPARMC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPRC);
        BPCRPPRC.DAT_LEN = 684;
        BPRPPRC.KEY.TYPE = BPCRPPRC.TYP;
        BPRPPRC.KEY.CODE = BPCRPPRC.CD;
        BPRPPRC.KEY.EFF_DATE = BPCRPPRC.EFF_DATE;
        if (BPCRPPRC.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPPRC.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPPRC.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPPRC.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPPRC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPPRC();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRC_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPPRC();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRC_UPD();
        T000_DELETE_BPTPPRC();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRC();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPPRC.DAT_LEN), BPCPARMC.DATA_TXT);
        BPRPPRC.REMARKS = BPCPARMC.DATA_TXT.REMARKS;
        BPRPPRC.OPEN_DATE = BPCPARMC.DATA_TXT.OPEN_DATE;
        BPRPPRC.LST_DATE = BPCPARMC.DATA_TXT.LST_DATE;
        BPRPPRC.LST_TLT = BPCPARMC.DATA_TXT.LST_TLT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP);
        BPRPPRC.RBASE_TYP = JIBS_tmp_str[0].charAt(0);
        BPRPPRC.CODE_NAME = BPCPARMC.DATA_TXT.CODE_NAME;
        BPRPPRC.CODE_NAME_S = BPCPARMC.DATA_TXT.CODE_NAME_S;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPCPARMC.DATA_TXT.REMARKS = BPRPPRC.REMARKS;
        BPCPARMC.DATA_TXT.OPEN_DATE = BPRPPRC.OPEN_DATE;
        BPCPARMC.DATA_TXT.LST_DATE = BPRPPRC.LST_DATE;
        BPCPARMC.DATA_TXT.LST_TLT = BPRPPRC.LST_TLT;
        IBS.CPY2CLS(SCCGWA, BPRPPRC.RBASE_TYP+"", BPCPARMC.DATA_TXT.RBASE_TYP);
        BPCPARMC.DATA_TXT.CODE_NAME = BPRPPRC.CODE_NAME;
        BPCPARMC.DATA_TXT.CODE_NAME_S = BPRPPRC.CODE_NAME_S;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARMC.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPPRC.DAT_LEN);
    }
    public void T000_READ_BPTPPRC() throws IOException,SQLException,Exception {
        BPTPPRC_RD = new DBParm();
        BPTPPRC_RD.TableName = "BPTPPRC";
        IBS.READ(SCCGWA, BPRPPRC, BPTPPRC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PARMC (" + IBS.CLS2CPY(SCCGWA, BPRPPRC.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPPRC() throws IOException,SQLException,Exception {
        BPTPPRC_RD = new DBParm();
        BPTPPRC_RD.TableName = "BPTPPRC";
        IBS.WRITE(SCCGWA, BPRPPRC, BPTPPRC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PARMC (" + IBS.CLS2CPY(SCCGWA, BPRPPRC.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPPRC_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPPRC.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRPPRC.KEY.CODE);
        CEP.TRC(SCCGWA, BPRPPRC.KEY.EFF_DATE);
        BPTPPRC_RD = new DBParm();
        BPTPPRC_RD.TableName = "BPTPPRC";
        BPTPPRC_RD.upd = true;
        IBS.READ(SCCGWA, BPRPPRC, BPTPPRC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PARMC (" + IBS.CLS2CPY(SCCGWA, BPRPPRC.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPPRC() throws IOException,SQLException,Exception {
        BPTPPRC_RD = new DBParm();
        BPTPPRC_RD.TableName = "BPTPPRC";
        IBS.REWRITE(SCCGWA, BPRPPRC, BPTPPRC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PARMC (" + IBS.CLS2CPY(SCCGWA, BPRPPRC.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPPRC() throws IOException,SQLException,Exception {
        BPTPPRC_RD = new DBParm();
        BPTPPRC_RD.TableName = "BPTPPRC";
        IBS.DELETE(SCCGWA, BPRPPRC, BPTPPRC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PARMC (" + IBS.CLS2CPY(SCCGWA, BPRPPRC.KEY) + ") NOT FOUND";
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
