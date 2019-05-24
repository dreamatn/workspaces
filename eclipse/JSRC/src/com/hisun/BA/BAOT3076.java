package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT3076 {
    String JIBS_tmp_str[] = new String[10];
    BAOT3076_WS_ERR_MSG WS_ERR_MSG = new BAOT3076_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_REM_DAYS = 0;
    int WS_STP_DAYS = 0;
    int WS_TR_BRAN = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BACUFEDL BACUFEDL = new BACUFEDL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BAB3076_AWA_3076 BAB3076_AWA_3076;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT3076 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB3076_AWA_3076>");
        BAB3076_AWA_3076 = (BAB3076_AWA_3076) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUFEDL);
        BACUFEDL.DATA.BILL_NO = BAB3076_AWA_3076.ID_NO;
        BACUFEDL.DATA.PZ_TYP = BAB3076_AWA_3076.PZ_TYP;
        BACUFEDL.DATA.TX_DT = BAB3076_AWA_3076.TX_DT;
        CEP.TRC(SCCGWA, BACUFEDL.DATA.TX_DT);
        BACUFEDL.DATA.PAGE_ROW = BAB3076_AWA_3076.PAGE_ROW;
        CEP.TRC(SCCGWA, BACUFEDL.DATA.PAGE_ROW);
        BACUFEDL.DATA.PAGE_NUM = BAB3076_AWA_3076.PAGE_NUM;
        S000_CALL_BAZUFEDL();
    }
    public void S000_CALL_BAZUFEDL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-BRW-FEDL", BACUFEDL);
        if (BACUFEDL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUFEDL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
