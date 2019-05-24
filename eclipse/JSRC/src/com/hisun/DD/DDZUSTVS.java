package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUSTVS {
    DBParm DDTVSABI_RD;
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRVSABI DDRVSABI = new DDRVSABI();
    SCCGWA SCCGWA;
    DDCUSTVS DDCUSTVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUSTVS DDCUSTVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUSTVS = DDCUSTVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUSTVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DDCUSTVS.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        B020_MAINTAIN_VS_INFO();
        B030_BP_NFHIS();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        DDRVSABI.KEY.VS_AC = DDCUSTVS.IO_AREA.VS_AC;
        DDRVSABI.KEY.CCY = DDCUSTVS.IO_AREA.CCY;
        DDRVSABI.KEY.CCY_TYP = DDCUSTVS.IO_AREA.CCY_TYP;
        T000_READ_DDTVSABI();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VSAC_NOT_FND;
            S000_ERR_MSG_PROC();
        }
        if (DDRVSABI.AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_CLO;
            S000_ERR_MSG_PROC();
        }
        if (DDRVSABI.AC_STS == 'F') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FREEZE;
            S000_ERR_MSG_PROC();
        }
        if (DDCUSTVS.IO_AREA.VS_STS == DDRVSABI.AC_STS) {
            if (DDRVSABI.AC_STS == 'N') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_STS_NML;
                S000_ERR_MSG_PROC();
            } else {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VAC_STS_FBID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_MAINTAIN_VS_INFO() throws IOException,SQLException,Exception {
        DDRVSABI.AC_STS = DDCUSTVS.IO_AREA.VS_STS;
        DDRVSABI.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRVSABI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTVSABI();
    }
    public void B030_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = DDCUSTVS.IO_AREA.VS_AC;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = DDRVSABI.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCUSTVS;
        BPCPNHIS.INFO.FMT_ID = "DDZUSTVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 47;
        S000_CALL_BPZPNHIS();
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.upd = true;
        IBS.READ(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.REWRITE(SCCGWA, DDRVSABI, DDTVSABI_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        DDCUSTVS.O_AREA.RC_CODE = 08;
        DDCUSTVS.O_AREA.MSG_ID = WS_MSG_ID;
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUSTVS.O_AREA.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUSTVS=");
            CEP.TRC(SCCGWA, DDCUSTVS);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
