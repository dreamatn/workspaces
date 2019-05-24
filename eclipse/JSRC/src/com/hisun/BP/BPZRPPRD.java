package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPPRD {
    int JIBS_tmp_int;
    DBParm BPTPPRD_RD;
    String K_PGM_NAME = "BPZRPPRD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPPRD BPRPPRD = new BPRPPRD();
    BPRPPDMM BPRPPDMM = new BPRPPDMM();
    SCCGWA SCCGWA;
    BPCRPRDM BPCRPRDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRPRDM BPCRPRDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPRDM = BPCRPRDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRPPRD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRPRDM.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPPRD);
        IBS.init(SCCGWA, BPRPPDMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPRD);
        BPCRPRDM.DAT_LEN = 174;
        BPRPPRD.KEY.TYPE = BPCRPRDM.TYP;
        BPRPPRD.KEY.CODE = BPCRPRDM.CD;
        BPRPPRD.KEY.EFF_DATE = BPCRPRDM.EFF_DATE;
        if (BPCRPRDM.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRPRDM.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRPRDM.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRPRDM.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRPRDM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R010_PUT_IN();
        T000_WRITE_BPTPPRD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRD_UPD();
        R010_PUT_IN();
        T000_REWRITE_BPTPPRD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRD_UPD();
        T000_DELETE_BPTPPRD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPPRD();
        R020_GET_OUT();
    }
    public void R010_PUT_IN() throws IOException,SQLException,Exception {
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRPRDM.DAT_LEN), BPRPPDMM.DATA_TXT);
        BPRPPRD.PARM_TX = BPRPPDMM.DATA_TXT.PARM_TX;
        BPRPPRD.CREATE_TX = BPRPPDMM.DATA_TXT.CREATE_TX;
        BPRPPRD.INNER_PRDT_IND = BPRPPDMM.DATA_TXT.INNER_PRDT_IND;
        BPRPPRD.DEFT_PRDT = BPRPPDMM.DATA_TXT.DEFT_PRDT;
        BPRPPRD.OPEN_DATE = BPRPPDMM.DATA_TXT.OPEN_DATE;
        BPRPPRD.LAST_DATE = BPRPPDMM.DATA_TXT.LAST_DATE;
        BPRPPRD.LAST_TLR = BPRPPDMM.DATA_TXT.LAST_TLR;
        BPRPPRD.DESC_MODEL = BPRPPDMM.DATA_TXT.DESC_MODEL;
        BPRPPRD.CONT_GROUP = BPRPPDMM.DATA_TXT.CONT_GROUP;
    }
    public void R020_GET_OUT() throws IOException,SQLException,Exception {
        BPRPPDMM.DATA_TXT.PARM_TX = BPRPPRD.PARM_TX;
        BPRPPDMM.DATA_TXT.CREATE_TX = BPRPPRD.CREATE_TX;
        BPRPPDMM.DATA_TXT.INNER_PRDT_IND = BPRPPRD.INNER_PRDT_IND;
        BPRPPDMM.DATA_TXT.DEFT_PRDT = BPRPPRD.DEFT_PRDT;
        BPRPPDMM.DATA_TXT.OPEN_DATE = BPRPPRD.OPEN_DATE;
        BPRPPDMM.DATA_TXT.LAST_DATE = BPRPPRD.LAST_DATE;
        BPRPPDMM.DATA_TXT.LAST_TLR = BPRPPRD.LAST_TLR;
        BPRPPDMM.DATA_TXT.DESC_MODEL = BPRPPRD.DESC_MODEL;
        BPRPPDMM.DATA_TXT.CONT_GROUP = BPRPPRD.CONT_GROUP;
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPPDMM.DATA_TXT);
        LK_DATA = JIBS_tmp_str[0] + LK_DATA.substring(BPCRPRDM.DAT_LEN);
    }
    public void T000_READ_BPTPPRD() throws IOException,SQLException,Exception {
        BPTPPRD_RD = new DBParm();
        BPTPPRD_RD.TableName = "BPTPPRD";
        IBS.READ(SCCGWA, BPRPPRD, BPTPPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPRD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTPPRD() throws IOException,SQLException,Exception {
        BPTPPRD_RD = new DBParm();
        BPTPPRD_RD.TableName = "BPTPPRD";
        IBS.WRITE(SCCGWA, BPRPPRD, BPTPPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPRD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTPPRD_UPD() throws IOException,SQLException,Exception {
        BPTPPRD_RD = new DBParm();
        BPTPPRD_RD.TableName = "BPTPPRD";
        BPTPPRD_RD.upd = true;
        IBS.READ(SCCGWA, BPRPPRD, BPTPPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPRD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTPPRD() throws IOException,SQLException,Exception {
        BPTPPRD_RD = new DBParm();
        BPTPPRD_RD.TableName = "BPTPPRD";
        IBS.REWRITE(SCCGWA, BPRPPRD, BPTPPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPRD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTPPRD() throws IOException,SQLException,Exception {
        BPTPPRD_RD = new DBParm();
        BPTPPRD_RD.TableName = "BPTPPRD";
        IBS.DELETE(SCCGWA, BPRPPRD, BPTPPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "PANCD (" + IBS.CLS2CPY(SCCGWA, BPRPPRD.KEY) + ") NOT FOUND";
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
