package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSDATP {
    DBParm DDTMST_RD;
    String CDD_M_AUTO_TD_PLAN = "DC-M-AUTO-TD-PLAN";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    char K_CARD_F_AC = '2';
    char K_AC_NO_INPUT = '0';
    char K_CARD_NO_INPUT = '1';
    char K_PROC_STS_CLO = '4';
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    String WS_CARD_NO = "                   ";
    String WS_AC_NO = "                                ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DCCSDATP DCCSDATP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DCCSDATP DCCSDATP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSDATP = DCCSDATP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZSDATP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCSDATP.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_CALL_UNIT_CPN();
    }
    public void B200_CALL_UNIT_CPN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.OVR_NO = DCCSDATP.IO_AREA.OVR_NO;
        DCCUMATP.IO_AREA.AGR_NO = DCCSDATP.IO_AREA.AGR_NO;
        DCCUMATP.IO_AREA.CI_NAME = DCCSDATP.IO_AREA.CI_NAME;
        DCCUMATP.IO_AREA.PROC_TYP = DCCSDATP.IO_AREA.PROC_TYP;
        DCCUMATP.IO_AREA.PROC_STS = DCCSDATP.IO_AREA.PROC_STS;
        DCCUMATP.IO_AREA.PROD_CDE = DCCSDATP.IO_AREA.PROD_CDE;
        DCCUMATP.IO_AREA.FUNC_M = '3';
        S000_CALL_DCZUMATP();
    }
    public void B300_REC_HISTRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = " ";
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = " ";
        BPCPNHIS.INFO.NEW_DAT_PT = DCCSDATP;
        BPCPNHIS.INFO.FMT_ID = "DCZSDATP";
        BPCPNHIS.INFO.FMT_ID_LEN = 207;
        S000_CALL_BPZPNHIS();
    }
    public void B101_INQCARDAC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = K_CARD_F_AC;
        DCCPACTY.INPUT.AC = WS_CARD_NO;
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if (DCCPACTY.RC.RC_CODE != 0 
            || DCCPACTY.OUTPUT.STD_AC.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICACCU.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_M_AUTO_TD_PLAN, DCCUMATP);
        if (DCCUMATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCUMATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CALL HISTORY ERR");
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        DCCSDATP.O_AREA.RC_CODE = 08;
        DCCSDATP.O_AREA.MSG_ID = WS_MSG_ID;
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF ", DCCPACTY);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
