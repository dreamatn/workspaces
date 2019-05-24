package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPBVP {
    int JIBS_tmp_int;
    DBParm BPTPBVP_RD;
    String K_PGM_NAME = "BPZRPBVP";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPBVP BPRPBVP = new BPRPBVP();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
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
        CEP.TRC(SCCGWA, "BPZRPBVP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPEXT.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPBVP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCRPEXT.DAT_LEN = 429;
        BPRPBVP.KEY.TYPE = BPCRPEXT.TYP;
        BPRPBVP.KEY.CODE = BPCRPEXT.CD;
        BPRPBVP.KEY.EFF_DATE = BPCRPEXT.EFF_DATE;
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
        T000_WRITE_BPTPBVP();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPBVP_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPBVP();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPBVP_UPD();
        T000_DELETE_BPTPBVP();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPBVP();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPEXT.DAT_LEN), BPRPBPRD.DATA_TXT);
        BPRPBVP.BV_TYPE = BPRPBPRD.DATA_TXT.TYPE;
        BPRPBVP.BV_CNM = BPRPBPRD.DATA_TXT.BV_CNM;
        BPRPBVP.BV_ENM = BPRPBPRD.DATA_TXT.BV_ENM;
        BPRPBVP.BV_CNMS = BPRPBPRD.DATA_TXT.BV_CNMS;
        BPRPBVP.BV_ENMS = BPRPBPRD.DATA_TXT.BV_ENMS;
        BPRPBVP.BV_CFLG = BPRPBPRD.DATA_TXT.BV_CFLG;
        BPRPBVP.LEVEL = BPRPBPRD.DATA_TXT.LEVEL;
        BPRPBVP.HEAD_LENGTH = BPRPBPRD.DATA_TXT.HEAD_LENGTH;
        BPRPBVP.NO_LENGTH = BPRPBPRD.DATA_TXT.NO_LENGTH;
        BPRPBVP.USE_MODE = BPRPBPRD.DATA_TXT.USE_MODE;
        BPRPBVP.USE_CTL = BPRPBPRD.DATA_TXT.USE_CTL;
        BPRPBVP.CTL_FLG = BPRPBPRD.DATA_TXT.CTL_FLG;
        BPRPBVP.CNT_FLG = BPRPBPRD.DATA_TXT.CNT_FLG;
        BPRPBVP.CNT_UT = BPRPBPRD.DATA_TXT.CNT_UT;
        BPRPBVP.CCY = BPRPBPRD.DATA_TXT.CCY;
        BPRPBVP.AC_TYP = BPRPBPRD.DATA_TXT.AC_TYP;
        BPRPBVP.CUT_FLG = BPRPBPRD.DATA_TXT.CUT_FLG;
        BPRPBVP.OUT_FLG = BPRPBPRD.DATA_TXT.OUT_FLG;
        BPRPBVP.SELL_FLG = BPRPBPRD.DATA_TXT.SELL_FLG;
        BPRPBVP.AUTO_REL_DAY = BPRPBPRD.DATA_TXT.AUTO_REL_DAY;
        BPRPBVP.PP_FLG = BPRPBPRD.DATA_TXT.PP_FLG;
        BPRPBVP.VAL_DAY = BPRPBPRD.DATA_TXT.VAL_DAY;
        BPRPBVP.B_LMT = (short) BPRPBPRD.DATA_TXT.B_LMT;
        BPRPBVP.V_LMT = (short) BPRPBPRD.DATA_TXT.V_LMT;
        BPRPBVP.STS = BPRPBPRD.DATA_TXT.STS;
        BPRPBVP.EFF_DT = BPRPBPRD.DATA_TXT.EFF_DT;
        BPRPBVP.EXP_DT = BPRPBPRD.DATA_TXT.EXP_DT;
        BPRPBVP.UPT_DT = BPRPBPRD.DATA_TXT.UPT_DT;
        BPRPBVP.UPT_TLR = BPRPBPRD.DATA_TXT.UPT_TLR;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRPBPRD.DATA_TXT.TYPE = BPRPBVP.BV_TYPE;
        BPRPBPRD.DATA_TXT.BV_CNM = BPRPBVP.BV_CNM;
        BPRPBPRD.DATA_TXT.BV_ENM = BPRPBVP.BV_ENM;
        BPRPBPRD.DATA_TXT.BV_CNMS = BPRPBVP.BV_CNMS;
        BPRPBPRD.DATA_TXT.BV_ENMS = BPRPBVP.BV_ENMS;
        BPRPBPRD.DATA_TXT.BV_CFLG = BPRPBVP.BV_CFLG;
        BPRPBPRD.DATA_TXT.LEVEL = BPRPBVP.LEVEL;
        BPRPBPRD.DATA_TXT.HEAD_LENGTH = BPRPBVP.HEAD_LENGTH;
        BPRPBPRD.DATA_TXT.NO_LENGTH = BPRPBVP.NO_LENGTH;
        BPRPBPRD.DATA_TXT.USE_MODE = BPRPBVP.USE_MODE;
        BPRPBPRD.DATA_TXT.USE_CTL = BPRPBVP.USE_CTL;
        BPRPBPRD.DATA_TXT.CTL_FLG = BPRPBVP.CTL_FLG;
        BPRPBPRD.DATA_TXT.CNT_FLG = BPRPBVP.CNT_FLG;
        BPRPBPRD.DATA_TXT.CNT_UT = BPRPBVP.CNT_UT;
        BPRPBPRD.DATA_TXT.CCY = BPRPBVP.CCY;
        BPRPBPRD.DATA_TXT.AC_TYP = BPRPBVP.AC_TYP;
        BPRPBPRD.DATA_TXT.CUT_FLG = BPRPBVP.CUT_FLG;
        BPRPBPRD.DATA_TXT.OUT_FLG = BPRPBVP.OUT_FLG;
        BPRPBPRD.DATA_TXT.SELL_FLG = BPRPBVP.SELL_FLG;
        BPRPBPRD.DATA_TXT.AUTO_REL_DAY = BPRPBVP.AUTO_REL_DAY;
        BPRPBPRD.DATA_TXT.PP_FLG = BPRPBVP.PP_FLG;
        BPRPBPRD.DATA_TXT.VAL_DAY = BPRPBVP.VAL_DAY;
        BPRPBPRD.DATA_TXT.B_LMT = BPRPBVP.B_LMT;
        BPRPBPRD.DATA_TXT.V_LMT = BPRPBVP.V_LMT;
        BPRPBPRD.DATA_TXT.STS = BPRPBVP.STS;
        BPRPBPRD.DATA_TXT.EFF_DT = BPRPBVP.EFF_DT;
        BPRPBPRD.DATA_TXT.EXP_DT = BPRPBVP.EXP_DT;
        BPRPBPRD.DATA_TXT.UPT_DT = BPRPBVP.UPT_DT;
        BPRPBPRD.DATA_TXT.UPT_TLR = BPRPBVP.UPT_TLR;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPBPRD.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPEXT.DAT_LEN);
    }
    public void T000_READ_BPTPBVP() throws IOException,SQLException,Exception {
        BPTPBVP_RD = new DBParm();
        BPTPBVP_RD.TableName = "BPTPBVP";
        IBS.READ(SCCGWA, BPRPBVP, BPTPBVP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPBVP.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPBVP() throws IOException,SQLException,Exception {
        BPTPBVP_RD = new DBParm();
        BPTPBVP_RD.TableName = "BPTPBVP";
        IBS.WRITE(SCCGWA, BPRPBVP, BPTPBVP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPBVP.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPBVP_UPD() throws IOException,SQLException,Exception {
        BPTPBVP_RD = new DBParm();
        BPTPBVP_RD.TableName = "BPTPBVP";
        BPTPBVP_RD.upd = true;
        IBS.READ(SCCGWA, BPRPBVP, BPTPBVP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPBVP.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPBVP() throws IOException,SQLException,Exception {
        BPTPBVP_RD = new DBParm();
        BPTPBVP_RD.TableName = "BPTPBVP";
        IBS.REWRITE(SCCGWA, BPRPBVP, BPTPBVP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPBVP.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPBVP() throws IOException,SQLException,Exception {
        BPTPBVP_RD = new DBParm();
        BPTPBVP_RD.TableName = "BPTPBVP";
        IBS.DELETE(SCCGWA, BPRPBVP, BPTPBVP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "MSG (" + IBS.CLS2CPY(SCCGWA, BPRPBVP.KEY) + ") NOT FOUND";
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
