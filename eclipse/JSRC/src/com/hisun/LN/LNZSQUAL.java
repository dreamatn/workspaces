package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSQUAL {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCUQUAL LNCUQUAL = new LNCUQUAL();
    SCCGWA SCCGWA;
    LNCSQUAL LNCSQUAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSQUAL LNCSQUAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSQUAL = LNCSQUAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZSQUAL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSQUAL.FUNC);
        if (LNCSQUAL.FUNC == 'A') {
            B030_QUAL_INQ_PROC();
            B010_QUAL_ADD_PROC();
        } else if (LNCSQUAL.FUNC == 'M') {
            B020_QUAL_MOD_PROC();
        } else if (LNCSQUAL.FUNC == 'I') {
            B030_QUAL_INQ_PROC();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUQUAL.DATA_AREA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSQUAL.DATA_AREA);
        } else if (LNCSQUAL.FUNC == 'D') {
            B040_QUAL_DEL_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SQUAL-FUNC(" + LNCSQUAL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, LNCSQUAL);
        CEP.TRC(SCCGWA, LNCUQUAL);
    }
    public void B010_QUAL_ADD_PROC() throws IOException,SQLException,Exception {
        if (LNCUQUAL.RETURN_INFO == 'F') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_QUAL_EXIST;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCUQUAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSQUAL.DATA_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUQUAL.DATA_AREA);
        LNCUQUAL.FUNC = 'A';
        S000_CALL_LNZUQUAL();
    }
    public void B020_QUAL_MOD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUQUAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSQUAL.DATA_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUQUAL.DATA_AREA);
        LNCUQUAL.FUNC = 'M';
        S000_CALL_LNZUQUAL();
    }
    public void B030_QUAL_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUQUAL);
        LNCUQUAL.DATA_AREA.KEY.CONTRACT_NO = LNCSQUAL.DATA_AREA.KEY.CONTRACT_NO;
        LNCUQUAL.FUNC = 'I';
        S000_CALL_LNZUQUAL();
    }
    public void B040_QUAL_DEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUQUAL);
        LNCUQUAL.DATA_AREA.KEY.CONTRACT_NO = LNCSQUAL.DATA_AREA.KEY.CONTRACT_NO;
        LNCUQUAL.FUNC = 'D';
        S000_CALL_LNZUQUAL();
    }
    public void S000_CALL_LNZUQUAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-U-QUAL-PROC", LNCUQUAL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
