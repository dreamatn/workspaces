package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5900 {
    String CPN_S_BPPROD_DEF = "BP-S-MGM-BPPROD";
    String WS_ERR_MSG = " ";
    BPOT5900_WS_MSGID WS_MSGID = new BPOT5900_WS_MSGID();
    short WS_FLD_NO = 0;
    BPOT5900_WS_OUT_INFO WS_OUT_INFO = new BPOT5900_WS_OUT_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSPROD BPCSPROD = new BPCSPROD();
    BPCRPROD BPCRPROD = new BPCRPROD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5900_AWA_5900 BPB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5900_AWA_5900>");
        BPB5900_AWA_5900 = (BPB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
        B030_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5900_AWA_5900.PRD_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE);
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPROD);
        BPCSPROD.FUNC = 'Q';
        BPCSPROD.KEY.PRD_TYPE = BPB5900_AWA_5900.PRD_TYPE;
        S000_CALL_BPZSPROD();
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.WS_O_PRD_TYPE = BPCSPROD.KEY.PRD_TYPE;
        WS_OUT_INFO.WS_O_CONT_TYP = BPCSPROD.DATA_TXT.CONT_TYP;
        WS_OUT_INFO.WS_O_DESC = BPCSPROD.DATA_TXT.DESC;
        WS_OUT_INFO.WS_O_CDESC = BPCSPROD.DATA_TXT.CDESC;
        WS_OUT_INFO.WS_O_RMK = BPCSPROD.DATA_TXT.RMK;
        WS_OUT_INFO.WS_O_EFF_DATE = BPCSPROD.DATA_TXT.EFF_DATE;
        WS_OUT_INFO.WS_O_EXP_DATE = BPCSPROD.DATA_TXT.EXP_DATE;
        WS_OUT_INFO.WS_O_ES_FLG = BPCSPROD.DATA_TXT.ES_FLG;
        WS_OUT_INFO.WS_O_EX_TYPE = BPCSPROD.DATA_TXT.EX_TYPE;
        WS_OUT_INFO.WS_O_TRD_TYP = BPCSPROD.DATA_TXT.TRD_TYP;
        WS_OUT_INFO.WS_O_EX_GROUP = BPCSPROD.DATA_TXT.EX_GROUP;
        WS_OUT_INFO.WS_O_SET_TYPE = BPCSPROD.DATA_TXT.SET_TYPE;
        WS_OUT_INFO.WS_O_PART_FLG = BPCSPROD.DATA_TXT.PART_FLG;
        WS_OUT_INFO.WS_O_UL_FLG = BPCSPROD.DATA_TXT.UL_FLG;
        WS_OUT_INFO.WS_O_FAV_CODE1 = BPCSPROD.DATA_TXT.FAV_CODE1;
        WS_OUT_INFO.WS_O_FAV_CODE2 = BPCSPROD.DATA_TXT.FAV_CODE2;
        WS_OUT_INFO.WS_O_FAV_CODE3 = BPCSPROD.DATA_TXT.FAV_CODE3;
        WS_OUT_INFO.WS_O_FAV_CODE4 = BPCSPROD.DATA_TXT.FAV_CODE4;
        WS_OUT_INFO.WS_O_FAV_CODE5 = BPCSPROD.DATA_TXT.FAV_CODE5;
        WS_OUT_INFO.WS_O_FAV_BUY = BPCSPROD.DATA_TXT.FAV_BUY;
        WS_OUT_INFO.WS_O_FAV_SELL = BPCSPROD.DATA_TXT.FAV_SELL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = WS_OUT_INFO;
        SCCFMT.DATA_LEN = 394;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPPROD_DEF, BPCSPROD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
