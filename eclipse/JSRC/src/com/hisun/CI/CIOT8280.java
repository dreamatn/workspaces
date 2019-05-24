package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8280 {
    DBParm CITACR_RD;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    String WS_ENTY_NO = " ";
    char WS_ACR_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSGDLS CICSGDLS = new CICSGDLS();
    CIRACR CIRACR = new CIRACR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    CIB8280_AWA_8280 CIB8280_AWA_8280;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8280 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8280_AWA_8280>");
        CIB8280_AWA_8280 = (CIB8280_AWA_8280) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSGDLS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB8280_AWA_8280.CI_NO.trim().length() > 0 
            && CIB8280_AWA_8280.ID_NO.trim().length() > 0 
            && CIB8280_AWA_8280.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "3.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8280_AWA_8280.ENTY_NO.trim().length() > 0 
            && CIB8280_AWA_8280.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8280_AWA_8280.CI_NO.trim().length() > 0 
            && CIB8280_AWA_8280.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8280_AWA_8280.CI_NO.trim().length() > 0 
            && CIB8280_AWA_8280.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8280_AWA_8280.ID_TYPE.trim().length() > 0 
            && CIB8280_AWA_8280.ID_NO.trim().length() > 0 
            && CIB8280_AWA_8280.CI_NM.trim().length() > 0) {
            CICSGDLS.FUNC = 'I';
        }
        if (CIB8280_AWA_8280.ID_NO.trim().length() > 0 
            && CIB8280_AWA_8280.CI_NM.trim().length() == 0) {
            CICSGDLS.FUNC = 'D';
        }
        if (CIB8280_AWA_8280.ENTY_NO.trim().length() > 0) {
            CICSGDLS.FUNC = 'A';
        }
        if (CIB8280_AWA_8280.CI_NO.trim().length() > 0) {
            CICSGDLS.FUNC = 'C';
        }
        CEP.TRC(SCCGWA, CICSGDLS.FUNC);
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CIB8280_AWA_8280.ENTY_NO.trim().length() == 0) {
            CICSGDLS.INPUT_DATA.ENTY_NO = CIB8280_AWA_8280.ENTY_NO;
        } else {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIB8280_AWA_8280.ENTY_NO;
            T000_READ_CITACR();
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (WS_ACR_FLAG == 'N') {
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.AC = CIB8280_AWA_8280.ENTY_NO;
                S000_CALL_DCZPACTY();
                WS_ENTY_NO = DCCPACTY.OUTPUT.STD_AC;
                if (!WS_ENTY_NO.equalsIgnoreCase("0")) {
                    CICSGDLS.INPUT_DATA.ENTY_NO = WS_ENTY_NO;
                    CEP.TRC(SCCGWA, CICSGDLS.INPUT_DATA.ENTY_NO);
                }
            }
            if (WS_ACR_FLAG == 'Y') {
                CICSGDLS.INPUT_DATA.ENTY_NO = CIRACR.KEY.AGR_NO;
            }
        }
        CICSGDLS.INPUT_DATA.ID_TYPE = CIB8280_AWA_8280.ID_TYPE;
        CICSGDLS.INPUT_DATA.ID_NO = CIB8280_AWA_8280.ID_NO;
        CICSGDLS.INPUT_DATA.CI_NM = CIB8280_AWA_8280.CI_NM;
        CICSGDLS.INPUT_DATA.CI_NO = CIB8280_AWA_8280.CI_NO;
        CICSGDLS.INPUT_DATA.FRM_APP = CIB8280_AWA_8280.FRM_APP;
        CICSGDLS.INPUT_DATA.FLG = CIB8280_AWA_8280.FLG;
        CICSGDLS.INPUT_DATA.PAGE_ROW = CIB8280_AWA_8280.PAGE_ROW;
        CICSGDLS.INPUT_DATA.PAGE_NUM = CIB8280_AWA_8280.PAGE_NUM;
        S000_CALL_CIZSGDLS();
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-DCZPACTY-BEGIN");
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY, true);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            WS_ERR_INFO = "DCZPACTY AC:(" + DCCPACTY.INPUT.AC + ")";
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_CALL_CIZSGDLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-SGDLS", CICSGDLS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
