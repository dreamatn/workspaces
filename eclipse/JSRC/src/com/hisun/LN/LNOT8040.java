package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8040 {
    DBParm LNTCONT_RD;
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    CICCUST CICCUST = new CICCUST();
    LNCSBIHS LNCSBIHS = new LNCSBIHS();
    SCCGWA SCCGWA;
    LNB8040_AWA_8040 LNB8040_AWA_8040;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8040_AWA_8040>");
        LNB8040_AWA_8040 = (LNB8040_AWA_8040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CEP.TRC(SCCGWA, LNB8040_AWA_8040.LOAN_AC);
        CEP.TRC(SCCGWA, LNB8040_AWA_8040.STR_DT);
        CEP.TRC(SCCGWA, LNB8040_AWA_8040.MAT_DT);
        B020_BROWSE_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8040_AWA_8040.LOAN_AC);
        if (LNB8040_AWA_8040.LOAN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB8040_AWA_8040.LOAN_AC;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSBIHS);
        if (LNB8040_AWA_8040.PAGE_ROW == 0) {
            LNCSBIHS.DATA_AREA.PAGE_ROW = 25;
        } else {
            if (LNB8040_AWA_8040.PAGE_ROW > 25) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSBIHS.DATA_AREA.PAGE_ROW = LNB8040_AWA_8040.PAGE_ROW;
            }
        }
        if (LNB8040_AWA_8040.PAGE_NUM == 0) {
            LNCSBIHS.DATA_AREA.PAGE_NUM = 1;
        } else {
            LNCSBIHS.DATA_AREA.PAGE_NUM = LNB8040_AWA_8040.PAGE_NUM;
        }
        LNCSBIHS.DATA_AREA.CTA_NO = LNB8040_AWA_8040.LOAN_AC;
        LNCSBIHS.DATA_AREA.STA_DT = LNB8040_AWA_8040.STR_DT;
        LNCSBIHS.DATA_AREA.DUE_DT = LNB8040_AWA_8040.MAT_DT;
        CEP.TRC(SCCGWA, LNB8040_AWA_8040.LOAN_AC);
        S000_CALL_LNZSBIHS();
    }
    public void S000_CALL_LNZSBIHS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-BRW-ITHS", LNCSBIHS);
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
