package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3091 {
    String CPN_DCR_QUERY = "DC-S-DCZACCBR";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCACCBR DCCACCBR = new DCCACCBR();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCB3091_AWA_3091 DCB3091_AWA_3091;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3091 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3091_AWA_3091>");
        DCB3091_AWA_3091 = (DCB3091_AWA_3091) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.CARD_NO);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.CCY);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.CCY_TYP);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.BUSI_KND);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.SPAC_STS);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.TRK2_DAT);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.TRK3_DAT);
        if (DCB3091_AWA_3091.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.FUNCTION_CODE = 'S';
        DCCPFTCK.VAL.CARD_NO = DCB3091_AWA_3091.CARD_NO;
        DCCPFTCK.VAL.TXN_TYPE = "20";
        DCCPFTCK.TRK2_DAT = DCB3091_AWA_3091.TRK2_DAT;
        DCCPFTCK.TRK3_DAT = DCB3091_AWA_3091.TRK3_DAT;
        S000_CALL_DCZPFTCK();
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
        CEP.TRC(SCCGWA, DCB3091_AWA_3091.SPAC_STS);
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCACCBR);
        DCCACCBR.CARD_NO = DCB3091_AWA_3091.CARD_NO;
        DCCACCBR.CCY = DCB3091_AWA_3091.CCY;
        DCCACCBR.CCY_TYP = DCB3091_AWA_3091.CCY_TYP;
        DCCACCBR.BUSI_KND = DCB3091_AWA_3091.BUSI_KND;
        DCCACCBR.SPAC_STS = DCB3091_AWA_3091.SPAC_STS;
        DCCACCBR.ROWS = DCB3091_AWA_3091.ROWS;
        DCCACCBR.PAGE_NUM = DCB3091_AWA_3091.PAGE_NUM;
        S000_CALL_DCZACCBR();
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_DCZACCBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCR_QUERY, DCCACCBR);
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
