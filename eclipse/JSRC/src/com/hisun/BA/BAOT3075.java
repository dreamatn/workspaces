package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT3075 {
    String JIBS_tmp_str[] = new String[10];
    String PGM_SCSSCLDT = "SCSSCLDT";
    BAOT3075_WS_ERR_MSG WS_ERR_MSG = new BAOT3075_WS_ERR_MSG();
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
    BACUQAMT BACUQAMT = new BACUQAMT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BAB3075_AWA_3075 BAB3075_AWA_3075;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A100_CHECK_INPUT_PROC();
        A200_STARTBR_PROC();
        CEP.TRC(SCCGWA, "BAOT3075 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB3075_AWA_3075>");
        BAB3075_AWA_3075 = (BAB3075_AWA_3075) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB3075_AWA_3075.INQ_TYP);
        CEP.TRC(SCCGWA, BAB3075_AWA_3075.BILL_NO);
        CEP.TRC(SCCGWA, BAB3075_AWA_3075.TX_DT);
        CEP.TRC(SCCGWA, BAB3075_AWA_3075.PAGE_ROW);
        CEP.TRC(SCCGWA, BAB3075_AWA_3075.PAGE_NUM);
    }
    public void A100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_INPUT_ERR, WS_ERR_MSG);
        S000_ERR_MSG_PROC_LAST();
    }
    public void A200_STARTBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUQAMT);
        BACUQAMT.DATA.INQ_TYP = BAB3075_AWA_3075.INQ_TYP;
        BACUQAMT.DATA.BILL_NO = BAB3075_AWA_3075.BILL_NO;
        BACUQAMT.DATA.TX_DT = BAB3075_AWA_3075.TX_DT;
        BACUQAMT.DATA.PAGE_NUM = BAB3075_AWA_3075.PAGE_NUM;
        if (BAB3075_AWA_3075.PAGE_ROW == 0) {
            BACUQAMT.DATA.PAGE_ROW = 25;
        } else {
            BACUQAMT.DATA.PAGE_ROW = BAB3075_AWA_3075.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, BACUQAMT.DATA.PAGE_ROW);
        BACUQAMT.DATA.PAGE_NUM = BAB3075_AWA_3075.PAGE_NUM;
        S000_CALL_BAZUQAMT();
    }
    public void S000_CALL_BAZUQAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-AMT", BACUQAMT);
        if (BACUQAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUQAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
