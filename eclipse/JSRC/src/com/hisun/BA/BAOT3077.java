package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT3077 {
    String JIBS_tmp_str[] = new String[10];
    BAOT3077_WS_ERR_MSG WS_ERR_MSG = new BAOT3077_WS_ERR_MSG();
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
    BACUBMST BACUBMST = new BACUBMST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BAB3077_AWA_3077 BAB3077_AWA_3077;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT3077 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB3077_AWA_3077>");
        BAB3077_AWA_3077 = (BAB3077_AWA_3077) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUBMST);
        BACUBMST.DATA.BILL_TYP = BAB3077_AWA_3077.BILL_TYP;
        BACUBMST.DATA.ID_TYPE = BAB3077_AWA_3077.ID_TYPE;
        BACUBMST.DATA.ID_NO = BAB3077_AWA_3077.ID_NO;
        BACUBMST.DATA.CI_CNM = BAB3077_AWA_3077.CI_CNM;
        CEP.TRC(SCCGWA, BAB3077_AWA_3077.PAGE_ROW);
        BACUBMST.DATA.PAGE_ROW = BAB3077_AWA_3077.PAGE_ROW;
        CEP.TRC(SCCGWA, BACUBMST.DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, BAB3077_AWA_3077.PAGE_NUM);
        BACUBMST.DATA.PAGE_NUM = BAB3077_AWA_3077.PAGE_NUM;
        CEP.TRC(SCCGWA, BACUBMST.DATA.PAGE_NUM);
        S000_CALL_BAZUBMST();
    }
    public void S000_CALL_BAZUBMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-BRW-MST1", BACUBMST);
        if (BACUBMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUBMST.RC);
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
