package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5560 {
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCISOAC DDCISOAC = new DDCISOAC();
    SCCGWA SCCGWA;
    DDB5560_AWA_5560 DDB5560_AWA_5560;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5560 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5560_AWA_5560>");
        DDB5560_AWA_5560 = (DDB5560_AWA_5560) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5560_AWA_5560.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, DDB5560_AWA_5560.STR_DATE);
            CEP.TRC(SCCGWA, DDB5560_AWA_5560.END_DATE);
            if (DDB5560_AWA_5560.STR_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STR_DATE_M_INPUT);
            }
            if (DDB5560_AWA_5560.END_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_END_DATE_M_INPUT);
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCISOAC);
        DDCISOAC.STR_DATE = DDB5560_AWA_5560.STR_DATE;
        DDCISOAC.END_DATE = DDB5560_AWA_5560.END_DATE;
        DDCISOAC.RECV_FLG = DDB5560_AWA_5560.RECV_FLG;
        DDCISOAC.OPEN_BR = DDB5560_AWA_5560.OPEN_BR;
        DDCISOAC.CI_NO = DDB5560_AWA_5560.CI_NO;
        S000_CALL_DDZISOAC();
    }
    public void S000_CALL_DDZISOAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-INQ-SOCIAL-AC", DDCISOAC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
