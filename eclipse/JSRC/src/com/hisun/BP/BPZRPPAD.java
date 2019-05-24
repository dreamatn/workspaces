package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPPAD {
    int JIBS_tmp_int;
    DBParm BPTPPAD_RD;
    String K_PGM_NAME = "BPZRPPAD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPPAD BPRPPAD = new BPRPPAD();
    BPCSMACD BPCSMACD = new BPCSMACD();
    SCCGWA SCCGWA;
    BPCRPPAD BPCRPPAD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPPAD BPCRPPAD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPPAD = BPCRPPAD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPPAD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPPAD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPPAD);
        IBS.init(SCCGWA, BPCSMACD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPAD);
        BPCRPPAD.DAT_LEN = 138;
        BPRPPAD.KEY.TYPE = BPCRPPAD.TYP;
        BPRPPAD.KEY.CODE = BPCRPPAD.CD;
        BPRPPAD.KEY.EFF_DATE = BPCRPPAD.EFF_DATE;
        if (BPCRPPAD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPPAD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPPAD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPPAD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPPAD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPPAD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPAD_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPPAD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPAD_UPD();
        T000_DELETE_BPTPPAD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPAD();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPPAD.DAT_LEN), BPCSMACD.PARM_DATA);
        BPRPPAD.FLD_DESCE = BPCSMACD.PARM_DATA.FLD_DESCE;
        BPRPPAD.FLD_DESC = BPCSMACD.PARM_DATA.FLD_DESC;
        BPRPPAD.FLD_TYPE = BPCSMACD.PARM_DATA.FLD_TYPE;
        BPRPPAD.DISPLAY_SEQ = BPCSMACD.PARM_DATA.DISPLAY_SEQ;
        BPRPPAD.FLD_LEN = BPCSMACD.PARM_DATA.FLD_LEN;
        BPRPPAD.DEC_LEN = BPCSMACD.PARM_DATA.DEC_LEN;
        BPRPPAD.SG_FLG = BPCSMACD.PARM_DATA.SG_FLG;
        BPRPPAD.FLD_IPT_FLG = BPCSMACD.PARM_DATA.FLD_IPT_FLG;
        BPRPPAD.PBCD_TYPE = BPCSMACD.PARM_DATA.PBCD_TYPE;
        BPRPPAD.OPEN_DATE = BPCSMACD.PARM_DATA.OPEN_DATE;
        BPRPPAD.LST_DATE = BPCSMACD.PARM_DATA.LST_DATE;
        BPRPPAD.LST_TLT = BPCSMACD.PARM_DATA.LST_TLT;
        BPRPPAD.FLD_MAX_LEN = BPCSMACD.PARM_DATA.FLD_MAX_LEN;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPCSMACD.PARM_DATA.FLD_DESCE = BPRPPAD.FLD_DESCE;
        BPCSMACD.PARM_DATA.FLD_DESC = BPRPPAD.FLD_DESC;
        BPCSMACD.PARM_DATA.FLD_TYPE = BPRPPAD.FLD_TYPE;
        BPCSMACD.PARM_DATA.DISPLAY_SEQ = BPRPPAD.DISPLAY_SEQ;
        BPCSMACD.PARM_DATA.FLD_LEN = BPRPPAD.FLD_LEN;
        BPCSMACD.PARM_DATA.DEC_LEN = BPRPPAD.DEC_LEN;
        BPCSMACD.PARM_DATA.SG_FLG = BPRPPAD.SG_FLG;
        BPCSMACD.PARM_DATA.FLD_IPT_FLG = BPRPPAD.FLD_IPT_FLG;
        BPCSMACD.PARM_DATA.PBCD_TYPE = BPRPPAD.PBCD_TYPE;
        BPCSMACD.PARM_DATA.OPEN_DATE = BPRPPAD.OPEN_DATE;
        BPCSMACD.PARM_DATA.LST_DATE = BPRPPAD.LST_DATE;
        BPCSMACD.PARM_DATA.LST_TLT = BPRPPAD.LST_TLT;
        BPCSMACD.PARM_DATA.FLD_MAX_LEN = BPRPPAD.FLD_MAX_LEN;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMACD.PARM_DATA);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPPAD.DAT_LEN);
    }
    public void T000_READ_BPTPPAD() throws IOException,SQLException,Exception {
        BPTPPAD_RD = new DBParm();
        BPTPPAD_RD.TableName = "BPTPPAD";
        IBS.READ(SCCGWA, BPRPPAD, BPTPPAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPAD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPPAD() throws IOException,SQLException,Exception {
        BPTPPAD_RD = new DBParm();
        BPTPPAD_RD.TableName = "BPTPPAD";
        IBS.WRITE(SCCGWA, BPRPPAD, BPTPPAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPAD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPPAD_UPD() throws IOException,SQLException,Exception {
        BPTPPAD_RD = new DBParm();
        BPTPPAD_RD.TableName = "BPTPPAD";
        BPTPPAD_RD.upd = true;
        IBS.READ(SCCGWA, BPRPPAD, BPTPPAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPAD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPPAD() throws IOException,SQLException,Exception {
        BPTPPAD_RD = new DBParm();
        BPTPPAD_RD.TableName = "BPTPPAD";
        IBS.REWRITE(SCCGWA, BPRPPAD, BPTPPAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPAD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPPAD() throws IOException,SQLException,Exception {
        BPTPPAD_RD = new DBParm();
        BPTPPAD_RD.TableName = "BPTPPAD";
        IBS.DELETE(SCCGWA, BPRPPAD, BPTPPAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPAD.KEY) + ") NOT FOUND";
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
