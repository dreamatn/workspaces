package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUQCAC {
    DBParm DDTMST_RD;
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String WS_ERR_MSG = " ";
    char WS_AC_ENTY_TYP = ' ';
    String WS_AC_FRM_APP = " ";
    int WS_IDX = 0;
    int WS_CNT = 0;
    char WS_MST_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDRMST DDRMST = new DDRMST();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACR CICQACR = new CICQACR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    DDCUQCAC DDCUQCAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCUQCAC DDCUQCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUQCAC = DDCUQCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUQCAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B010_TYPE_DCZPACTY();
        B010_TYPE_CIZACCU();
        B010_GET_ACTYPE_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUQCAC.DETAIL_REC.TR_AC);
        CEP.TRC(SCCGWA, DDCUQCAC.DETAIL_REC.IM_BR);
        if (DDCUQCAC.DETAIL_REC.TR_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUQCAC.RC);
            S000_ERR_MSG_PROC();
        }
        if (DDCUQCAC.DETAIL_REC.IM_BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_M_INPUT, DDCUQCAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_TYPE_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCUQCAC.DETAIL_REC.TR_AC;
        S000_CALL_CIZQACAC();
    }
    public void B010_TYPE_CIZACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUQCAC.DETAIL_REC.TR_AC;
        S000_CALL_CIZACCU();
        WS_AC_ENTY_TYP = CICACCU.DATA.ENTY_TYP;
        WS_AC_FRM_APP = CICACCU.DATA.FRM_APP;
        if (CICACCU.DATA.BBR == DDCUQCAC.DETAIL_REC.IM_BR) {
            return;
        }
    }
    public void B010_GET_ACTYPE_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP != '1') {
            DDRMST.KEY.CUS_AC = DDCUQCAC.DETAIL_REC.TR_AC;
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_TYPE == 'C' 
                || DDRMST.AC_TYPE == 'D') {
                IBS.init(SCCGWA, CICQCIAC);
                CICQCIAC.FUNC = '1';
                CICQCIAC.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                S000_CALL_CIZQCIAC();
            }
        }
    }
    public void S000_CALL_CIZQACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR", CICQACR);
        if (CICQACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        WS_MST_FLG = 'F';
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MST_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ DDTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE == 0) {
            CEP.ERR(SCCGWA, CICQCIAC.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUQCAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDZUQCAC=");
            CEP.TRC(SCCGWA, DDCUQCAC);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
