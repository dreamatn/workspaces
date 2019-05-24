package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1000 {
    String CPN_S_PNZSMPRD = "PN-S-MTM-BLL-PRD    ";
    String WS_ERR_MSG = " ";
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PNCSMPRD PNCSMPRD = new PNCSMPRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB1000_AWA_1000 PNB1000_AWA_1000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1000 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1000_AWA_1000>");
        PNB1000_AWA_1000 = (PNB1000_AWA_1000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        B200_MOVE_DATA_PROC();
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        if (PNB1000_AWA_1000.FUNC == ' ') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_FUNC_NOT_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB1000_AWA_1000.FUNC_NO);
        }
        if (PNB1000_AWA_1000.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PRD_MUST_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB1000_AWA_1000.PROD_CD_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E' 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_MOVE_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCSMPRD);
        PNCSMPRD.FUNC = PNB1000_AWA_1000.FUNC;
        PNCSMPRD.PROD_CD = PNB1000_AWA_1000.PROD_CD;
        PNCSMPRD.PROD_MD = PNB1000_AWA_1000.PROD_MD;
        PNCSMPRD.PROD_MOD = PNB1000_AWA_1000.PROD_MOD;
        PNCSMPRD.PROD_CNM = PNB1000_AWA_1000.PROD_CNM;
        PNCSMPRD.PROD_ENM = PNB1000_AWA_1000.PROD_ENM;
        CEP.TRC(SCCGWA, PNB1000_AWA_1000.EFF_DATE);
        CEP.TRC(SCCGWA, PNB1000_AWA_1000.EXP_DATE);
        PNCSMPRD.EFF_DATE = PNB1000_AWA_1000.EFF_DATE;
        PNCSMPRD.EXP_DATE = PNB1000_AWA_1000.EXP_DATE;
        CEP.TRC(SCCGWA, PNCSMPRD.EFF_DATE);
        CEP.TRC(SCCGWA, PNCSMPRD.EXP_DATE);
        PNCSMPRD.CTL_INFO.CCY = PNB1000_AWA_1000.CCY;
        CEP.TRC(SCCGWA, PNCSMPRD.CTL_INFO.CCY);
        PNCSMPRD.CTL_INFO.PAY_TERM = PNB1000_AWA_1000.PAY_TERM;
        PNCSMPRD.CTL_INFO.AUTO_REL = PNB1000_AWA_1000.AUTO_REL;
        S000_CALL_PNZSMPRD();
    }
    public void S000_CALL_PNZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-PNZSMPRD", PNCSMPRD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
