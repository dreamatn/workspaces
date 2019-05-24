package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPCRY {
    int JIBS_tmp_int;
    DBParm BPTPCRY_RD;
    String K_PGM_NAME = "BPZRPCRY";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPCRY BPRPCRY = new BPRPCRY();
    BPRCCY BPRCCY = new BPRCCY();
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
        CEP.TRC(SCCGWA, "BPZRPCRY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPEXT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPCRY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCRPEXT.DAT_LEN = 220;
        BPRPCRY.KEY.TYPE = BPCRPEXT.TYP;
        BPRPCRY.KEY.CODE = BPCRPEXT.CD;
        BPRPCRY.KEY.EFF_DATE = BPCRPEXT.EFF_DATE;
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
        T000_WRITE_BPTPCRY();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCRY_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPCRY();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCRY_UPD();
        T000_DELETE_BPTPCRY();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPCRY();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPEXT.DAT_LEN), BPRCCY.DATA_TXT);
        BPRPCRY.CCY = BPRCCY.DATA_TXT.CCY;
        BPRPCRY.CCY_CD = BPRCCY.DATA_TXT.CCY_CD;
        BPRPCRY.CUR_NM = BPRCCY.DATA_TXT.CUR_NM;
        BPRPCRY.EFF_DT = BPRCCY.DATA_TXT.EFF_DT;
        BPRPCRY.EXP_DT = BPRCCY.DATA_TXT.EXP_DT;
        BPRPCRY.CNTY_CD = BPRCCY.DATA_TXT.CNTY_CD;
        BPRPCRY.CITY_CD = BPRCCY.DATA_TXT.CITY_CD;
        BPRPCRY.UNIT_CURU_NAME = BPRCCY.DATA_TXT.UNIT_CURU_NAME;
        BPRPCRY.CENT_CURU_NAME = BPRCCY.DATA_TXT.CENT_CURU_NAME;
        BPRPCRY.RGN_CCY = BPRCCY.DATA_TXT.RGN_CCY;
        BPRPCRY.DEC_MTH = BPRCCY.DATA_TXT.DEC_MTH;
        BPRPCRY.CASH_MTH = BPRCCY.DATA_TXT.CASH_MTH;
        BPRPCRY.RND_MTH = BPRCCY.DATA_TXT.RND_MTH;
        BPRPCRY.TR_FLG = BPRCCY.DATA_TXT.TR_FLG;
        BPRPCRY.CASH_FLG = BPRCCY.DATA_TXT.CASH_FLG;
        BPRPCRY.CHGU_MTH = BPRCCY.DATA_TXT.CHGU_MTH;
        BPRPCRY.EXH_FLG = BPRCCY.DATA_TXT.EXH_FLG;
        BPRPCRY.CALR_STD = BPRCCY.DATA_TXT.CALR_STD;
        BPRPCRY.CAL_CD = BPRCCY.DATA_TXT.CAL_CD;
        BPRPCRY.UPT_DT = BPRCCY.DATA_TXT.UPT_DT;
        BPRPCRY.UPT_TLR = BPRCCY.DATA_TXT.UPT_TLR;
        BPRPCRY.ISR_DAYS = BPRCCY.DATA_TXT.ISR_DAYS;
        BPRPCRY.BAL_DAYS = BPRCCY.DATA_TXT.BAL_DAYS;
        BPRPCRY.CHG_CCY = BPRCCY.DATA_TXT.CHG_CCY;
        BPRPCRY.CUR_CNM = BPRCCY.DATA_TXT.CUR_CNM;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRCCY.DATA_TXT.CCY = BPRPCRY.CCY;
        BPRCCY.DATA_TXT.CCY_CD = BPRPCRY.CCY_CD;
        BPRCCY.DATA_TXT.CUR_NM = BPRPCRY.CUR_NM;
        BPRCCY.DATA_TXT.EFF_DT = BPRPCRY.EFF_DT;
        BPRCCY.DATA_TXT.EXP_DT = BPRPCRY.EXP_DT;
        BPRCCY.DATA_TXT.CNTY_CD = BPRPCRY.CNTY_CD;
        BPRCCY.DATA_TXT.CITY_CD = BPRPCRY.CITY_CD;
        BPRCCY.DATA_TXT.UNIT_CURU_NAME = BPRPCRY.UNIT_CURU_NAME;
        BPRCCY.DATA_TXT.CENT_CURU_NAME = BPRPCRY.CENT_CURU_NAME;
        BPRCCY.DATA_TXT.RGN_CCY = BPRPCRY.RGN_CCY;
        BPRCCY.DATA_TXT.DEC_MTH = BPRPCRY.DEC_MTH;
        BPRCCY.DATA_TXT.CASH_MTH = BPRPCRY.CASH_MTH;
        BPRCCY.DATA_TXT.RND_MTH = BPRPCRY.RND_MTH;
        BPRCCY.DATA_TXT.TR_FLG = BPRPCRY.TR_FLG;
        BPRCCY.DATA_TXT.CASH_FLG = BPRPCRY.CASH_FLG;
        BPRCCY.DATA_TXT.CHGU_MTH = BPRPCRY.CHGU_MTH;
        BPRCCY.DATA_TXT.EXH_FLG = BPRPCRY.EXH_FLG;
        BPRCCY.DATA_TXT.CALR_STD = BPRPCRY.CALR_STD;
        BPRCCY.DATA_TXT.CAL_CD = BPRPCRY.CAL_CD;
        BPRCCY.DATA_TXT.UPT_DT = BPRPCRY.UPT_DT;
        BPRCCY.DATA_TXT.UPT_TLR = BPRPCRY.UPT_TLR;
        BPRCCY.DATA_TXT.ISR_DAYS = BPRPCRY.ISR_DAYS;
        BPRCCY.DATA_TXT.BAL_DAYS = BPRPCRY.BAL_DAYS;
        BPRCCY.DATA_TXT.CHG_CCY = BPRPCRY.CHG_CCY;
        BPRCCY.DATA_TXT.CUR_CNM = BPRPCRY.CUR_CNM;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCCY.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPEXT.DAT_LEN);
    }
    public void T000_READ_BPTPCRY() throws IOException,SQLException,Exception {
        BPTPCRY_RD = new DBParm();
        BPTPCRY_RD.TableName = "BPTPCRY";
        IBS.READ(SCCGWA, BPRPCRY, BPTPCRY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPCRY.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPCRY() throws IOException,SQLException,Exception {
        BPTPCRY_RD = new DBParm();
        BPTPCRY_RD.TableName = "BPTPCRY";
        IBS.WRITE(SCCGWA, BPRPCRY, BPTPCRY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPCRY.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPCRY_UPD() throws IOException,SQLException,Exception {
        BPTPCRY_RD = new DBParm();
        BPTPCRY_RD.TableName = "BPTPCRY";
        BPTPCRY_RD.upd = true;
        IBS.READ(SCCGWA, BPRPCRY, BPTPCRY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPCRY.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPCRY() throws IOException,SQLException,Exception {
        BPTPCRY_RD = new DBParm();
        BPTPCRY_RD.TableName = "BPTPCRY";
        IBS.REWRITE(SCCGWA, BPRPCRY, BPTPCRY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPCRY.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPCRY() throws IOException,SQLException,Exception {
        BPTPCRY_RD = new DBParm();
        BPTPCRY_RD.TableName = "BPTPCRY";
        IBS.DELETE(SCCGWA, BPRPCRY, BPTPCRY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPCRY.KEY) + ") NOT FOUND";
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
