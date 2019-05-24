package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUGTBV {
    int JIBS_tmp_int;
    DBParm DCTCDORD_RD;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDORD DCRCDORD = new DCRCDORD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUGTBV DCCUGTBV;
    public void MP(SCCGWA SCCGWA, DCCUGTBV DCCUGTBV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUGTBV = DCCUGTBV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZUGTBV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_BV_CODE();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUGTBV.OUTPUT);
        if (DCCUGTBV.INPUT.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_BV_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUGTBV.INPUT.CARD_NO);
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = "%%%%%%%%%%%%%%%%%%%";
        if (DCCUGTBV.INPUT.CARD_NO == null) DCCUGTBV.INPUT.CARD_NO = "";
        JIBS_tmp_int = DCCUGTBV.INPUT.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCUGTBV.INPUT.CARD_NO += " ";
        if (DCRCDORD.KEY.CARD_NO == null) DCRCDORD.KEY.CARD_NO = "";
        JIBS_tmp_int = DCRCDORD.KEY.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCRCDORD.KEY.CARD_NO += " ";
        DCRCDORD.KEY.CARD_NO = DCCUGTBV.INPUT.CARD_NO.substring(0, 18) + DCRCDORD.KEY.CARD_NO.substring(18);
        T000_READ_DCTCDORD_FIRST();
        DCCUGTBV.OUTPUT.BV_CODE = DCRCDORD.BV_CD_NO;
    }
    public void T000_READ_DCTCDORD_FIRST() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_NO, EXC_CARD_TMS, BV_CD_NO, EMBS_TYP";
        DCTCDORD_RD.where = "CARD_NO LIKE :DCRCDORD.KEY.CARD_NO";
        DCTCDORD_RD.fst = true;
        DCTCDORD_RD.order = "EXC_CARD_TMS DESC";
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
